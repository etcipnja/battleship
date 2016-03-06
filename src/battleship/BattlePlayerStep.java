package battleship;
import java.util.*;


public class BattlePlayerStep {

	private Cell [] [] matrix;
	BattleBoardInterface board;
	
	private int hitStep;

	private int lastHit; //TODO: last hit shall be boolean
	
	//TODO class variable names shall start with the lowercase and shall have different naming from local variables in the method.  
	//for example two variables below shall be named as "_ohit" or "m_xOHit" 
	private Position OHit;	
	private Position CHit;

	private int numD;

	public BattlePlayerStep(BattleBoardInterface a)
	{
		
		matrix = new Cell[10][10];

		for(int row = 0; row<10; row++)
			for(int col = 0; col<10; col++)
				matrix[row][col] = new Cell();

		board = a;

	}

	//TODO this method shall return the Position that was selected for the move
	public int smartStep()
	{
		Random gen = new Random(2);
		
		if(lastHit != 1) //previous shoot was a miss
		{
			do //find a position where you have not hit yet
			{
				OHit = new Position();

			}while(matrix[OHit.getRow()][OHit.getCol()].isHit());

			int row = OHit.getRow();
			int col = OHit.getCol();
			
			matrix[row][col].hit(); //mark position as hit in the own matrix
			int hitRet = board.hit(row, col); //check the board
			
			if(hitRet == 0)
				System.out.println("You missed!");
			else if (hitRet == 2)  //killed
				numD++;

			lastHit = hitRet;
			CHit = new Position(row,col);
		}
		else //previous shoot was a hit
		{
			int [] hosDisp = {-1,0,1,0};//rows
			int [] vetDisp = {0,1,0,-1};//cols

			int hitRet = 0;	
			
			int curHitR = CHit.getRow();
			int curHitC = CHit.getCol();
			
			if(curHitR+hosDisp[hitStep] < 0 || curHitR+hosDisp[hitStep] >= 10 || curHitC+vetDisp[hitStep] < 0 || curHitC+vetDisp[hitStep] >= 10)
			{
				hitStep++;
				CHit = new Position(OHit.getRow(),OHit.getCol());
				return numD;  //TODO are you sure you need to return here?
			}
			
			
			if(!matrix[curHitR+hosDisp[hitStep]][curHitC+vetDisp[hitStep]].isHit())
				hitRet = board.hit(curHitR+hosDisp[hitStep], curHitC+vetDisp[hitStep]);

			if(hitRet == 0) //missed
			{
				hitStep++;
				CHit = new Position(OHit.getRow(),OHit.getCol());
				System.out.println("You missed!");		
			}
			else if(hitRet == 1) //wounded
			{
				matrix[curHitR+hosDisp[hitStep]][curHitC+vetDisp[hitStep]].hit();
				
				CHit.setCol(curHitC+vetDisp[hitStep]);
				CHit.setRow(curHitR+hosDisp[hitStep]);
			}
			else if(hitRet == 2) //destroyed
			{
				matrix[curHitR+hosDisp[hitStep]][curHitC+vetDisp[hitStep]].hit();
				lastHit = hitRet;
				hitStep =0;
				numD++;
			}
			else if(hitRet == 17) //TODO WTF?
			{
				hitStep++;
				CHit = new Position(OHit.getRow(),OHit.getCol());
			}
		}

		return numD;
	}
}