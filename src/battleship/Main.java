//Battleship v1.0
package battleship;
import java.io.*;
import java.net.*;


public class Main {

	public static void main(String[] args){

		BattleBoard board = new BattleBoard();
		board.placePeieces();
		
		//BattlePlayer player = new BattlePlayer(board);
		
		BattlePlayerStep player2 = new BattlePlayerStep(board);
		
		do
		{
			
			
		}while(player2.smartStep() != 4);
		
		System.out.println("You won!");
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
