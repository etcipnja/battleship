package battleship;
import java.util.*;

public class BattlePlayerStep {

	private Cell [] [] matrix;
	BattleBoardInterface board;
	
	private int hitStep;

	private int lastHit;
	private int OHitR;
	private int OHitC;

	private int curHitR;
	private int curHitC;

	private int numD;

	public BattlePlayerStep(BattleBoardInterface a)
	{
		
		matrix = new Cell[10][10];

		for(int row = 0; row<10; row++)
			for(int col = 0; col<10; col++)
				matrix[row][col] = new Cell();

		board = a;

	}

	public int smartStep()
	{
		Random gen = new Random(2);
		int row;
		int col;


		if(lastHit != 1)
		{
			do
			{
				row = gen.nextInt(10);
				col = gen.nextInt(10);

			}while(matrix[row][col].isHit());

			matrix[row][col].hit();
			int hitRet = board.hit(row, col);


			if(hitRet == 0)
			{
				System.out.println("You missed!");
				System.out.println(board.toString());
			}

			else if (hitRet == 1)
				System.out.println(board.toString());
			
			else
			{
				numD++;
				System.out.println(board.toString());
			}

			lastHit = hitRet;
			OHitR = row;
			OHitC = col;
			curHitR = OHitR;
			curHitC = OHitC;
		}

		else
		{
			int [] hosDisp = {-1,0,1,0};//rows
			int [] vetDisp = {0,1,0,-1};//cols

			int hitRet = 0;	
			
			
			if(curHitR+hosDisp[hitStep] < 0 || curHitR+hosDisp[hitStep] >= 10 || curHitC+vetDisp[hitStep] < 0 || curHitC+vetDisp[hitStep] >= 10)
			{
				hitStep++;
				curHitR = OHitR;
				curHitC = OHitC;
				return numD;
			}
			
			
			if(!matrix[curHitR+hosDisp[hitStep]][curHitC+vetDisp[hitStep]].isHit())
				hitRet = board.hit(curHitR+hosDisp[hitStep], curHitC+vetDisp[hitStep]);


			if(hitRet == 0)
			{

				hitStep++;

				curHitR = OHitR;
				curHitC = OHitC;

				System.out.println("You missed!");
				System.out.println(board.toString());
			}

			else if(hitRet == 1)
			{
				matrix[curHitR+hosDisp[hitStep]][curHitC+vetDisp[hitStep]].hit();
				
				curHitR += hosDisp[hitStep];
				curHitC += vetDisp[hitStep];
				
				System.out.println(board.toString());
			}

			else if(hitRet == 2)
			{
				matrix[curHitR+hosDisp[hitStep]][curHitC+vetDisp[hitStep]].hit();
				lastHit = hitRet;
				hitStep =0;
				numD++;
				System.out.println(board.toString());
			}
			else if(hitRet == 17)
			{
				hitStep++;
				curHitR = OHitR;
				curHitC = OHitC;
			}
				

		}

		return numD;
	}
}