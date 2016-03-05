package battleship;
import java.util.*;

public class BattlePlayer {

	public BattlePlayer(BattleBoardInterface a)
	{
		smartPlay(a);
	}

	public void smartPlay(BattleBoardInterface a)
	{
		Random gen = new Random(1);

		int numD = 0;


		do
		{
			int row = gen.nextInt(10);
			int col = gen.nextInt(10);

			int hitRet = a.hit(row, col);

			if(hitRet != 17)
				System.out.println(a.toString());

			if(hitRet == 2)
				numD++;

			else if(hitRet == 1)
			{
				int oRow = row;
				int oCol = col;


				int [] vetDisp = {0,1,0,-1};//cols
				int [] hosDisp = {-1,0,1,0};//rows


				for(int i = 0; i < 4; i++)
				{
					int theHit = a.hit(oRow+hosDisp[i], oCol+vetDisp[i]);
					
					if(theHit != 17)
						System.out.println(a.toString());


					if(theHit == 1)
					{
						int curRow = oRow+hosDisp[i];
						int curCol = oCol+vetDisp[i];
						int ret;

						do
						{
							curRow += hosDisp[i];
							curCol += vetDisp[i];

							ret = a.hit(curRow, curCol);

							if(ret != 17)
								System.out.println(a.toString());

						}while(ret == 1);
					}
				}

				numD++;
			}

		}while(numD < 4);

		System.out.println("You won!");
	}
}