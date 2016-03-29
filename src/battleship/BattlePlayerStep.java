package battleship;
import java.util.*;
/*
 * MADE THE CORRECT CLASS IN BattlePlayerRetStepPos
 * 
 */
public class BattlePlayerStep {

	private Cell [] [] matrix;

	
	private int hitStep;

	private boolean lastHit; 
	
	private Position m_xOHit;	
	private Position m_xCHit;

	private int numD;

	public BattlePlayerStep(BattleBoardInterface a)
	{
		
		matrix = new Cell[10][10];

		for(int row = 0; row<10; row++)
			for(int col = 0; col<10; col++)
				matrix[row][col] = new Cell();

		

	}

	//TODO2 this method shall return the Position that was selected for the move
	public int smartStep()
	{
		Random gen = new Random(2);
		
		if(!lastHit) //previous shoot was a miss
		{
			do //find a position where you have not hit yet
			{
				m_xOHit = new Position();

			}while(matrix[m_xOHit.getRow()][m_xOHit.getCol()].isHit());

			int row = m_xOHit.getRow();
			int col = m_xOHit.getCol();
			
			matrix[row][col].hit(); //mark position as hit in the own matrix
			
			
			lastHit = false;
			
			if(hitRet == 0)
				System.out.println("You missed!");
			else if (hitRet == 2)  //killed
				numD++;
			else
				lastHit = true;

			
			m_xCHit = new Position(row,col);
		}
		else //previous shoot was a hit
		{
			int [] hosDisp = {-1,0,1,0};//rows
			int [] vetDisp = {0,1,0,-1};//cols

			int hitRet = 0;	
			
			int curHitR = m_xCHit.getRow();
			int curHitC = m_xCHit.getCol();
			
			if(curHitR+hosDisp[hitStep] < 0 || curHitR+hosDisp[hitStep] >= 10 || curHitC+vetDisp[hitStep] < 0 || curHitC+vetDisp[hitStep] >= 10)
			{
				hitStep++;
				m_xCHit = new Position(m_xOHit.getRow(),m_xOHit.getCol());
				return numD;   //TODO2 are you sure you need to return here?
			}
			
			
			if(!matrix[curHitR+hosDisp[hitStep]][curHitC+vetDisp[hitStep]].isHit())
				hitRet = board.hit(curHitR+hosDisp[hitStep], curHitC+vetDisp[hitStep]);

			if(hitRet == 0) //missed
			{
				hitStep++;
				m_xCHit = new Position(m_xOHit.getRow(),m_xOHit.getCol());
				System.out.println("You missed!");		
			}
			else if(hitRet == 1) //wounded
			{
				matrix[curHitR+hosDisp[hitStep]][curHitC+vetDisp[hitStep]].hit();
				
				m_xCHit.setCol(curHitC+vetDisp[hitStep]);
				m_xCHit.setRow(curHitR+hosDisp[hitStep]);
			}
			else if(hitRet == 2) //destroyed
			{
				matrix[curHitR+hosDisp[hitStep]][curHitC+vetDisp[hitStep]].hit();
				lastHit = false;
				hitStep =0;
				numD++;
			}
			
		}

		return numD;
	}
}