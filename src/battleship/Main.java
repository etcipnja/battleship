//Battleship v1.0
package battleship;
import java.io.*;
import java.net.*;


public class Main {
	public static void main(String[] args){
		
		try
		{
			ServerSocket server = new ServerSocket(6789);
			Socket connection=server.accept();
			System.out.println("new client connected");
			
			BattleBoard xBoard=new BattleBoard();
			xBoard.placePeieces();
			
			DataOutputStream toClient= new DataOutputStream(connection.getOutputStream());
			BufferedReader fromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			for (int i=1;true;i++)
			{
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
			}
			System.out.println("game over");
			server.close();
		}
		catch (IOException e)
		{
			System.out.println(e);
		}

	}
}