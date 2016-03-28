//Battleship v1.0
package battleship;
import java.io.*;
import java.net.*;


public class Main {

	public static void main(String[] args){

		BattleBoard board = new BattleBoard();
		board.placePeieces();
		
		//TODO: player shall not take the board as a parameter 
		//instead, player shall make his move and Main shall check if that move was a hit 
		//then Main tells the player if its move was a hit or not
		
		// so the code shall look like
		
		//while(!board.isAllDead()) //returns true when all ships are destroyed
		//{
		//	Position xPos=player2.smartStep();
		//	int iResult=board.hit(xPos);
		//	player2.smartStepResult(xPos,iResult);
		//}
		
		
		BattlePlayerStep player2 = new BattlePlayerStep(board);
		
		int iStep=0;
		do
		{
			System.out.println(board.toString());
			iStep++;
			System.out.println("--------------------------------");
			System.out.println("Step "+iStep+":");
		}while(player2.smartStep() != 10);
		
		System.out.println(board.toString());
		System.out.println("You won in "+iStep+" steps");
	}
}
		







		/*try
=======
	public static void main(String[] args)
	{
		try
>>>>>>> branch 'master' of https://github.com/etcipnja/battleship.git
		{
			ServerSocket server = new ServerSocket(6789);
<<<<<<< HEAD
			Socket connection=server.accept();
			System.out.println("new client connected");

			BattleBoard xBoard=new BattleBoard();
			xBoard.placePeieces();

			DataOutputStream toClient= new DataOutputStream(connection.getOutputStream());
			BufferedReader fromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			for (int i=1;true;i++)
=======
			
			while(true)
>>>>>>> branch 'master' of https://github.com/etcipnja/battleship.git
			{
<<<<<<< HEAD
				toClient.writeBytes("MOVE "+i+"\r\n--------------------------------\r\n");

				toClient.writeBytes(xBoard.toString());
				toClient.writeBytes("\r\n");	//signal for end of message

				String move=fromClient.readLine();
				if (move==null) break;

				System.out.println("Player move:"+move);

				try
				{
					xBoard.move(move);
				}
				catch (IndexOutOfBoundsException e)
				{
					toClient.writeBytes("your move <"+move+"> is bad and you shall feel bad!\r\n");
				}
=======
				Socket connection=server.accept();
				new Thread( new BattleBoard(connection)).start();
>>>>>>> branch 'master' of https://github.com/etcipnja/battleship.git
			}
		}
		catch (IOException e)
		{
			System.out.println(e);
<<<<<<< HEAD
<<<<<<< HEAD
		}*/
