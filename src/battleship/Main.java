//Battleship v1.0
package battleship;
import java.io.*;
import java.net.*;


public class Main {
	public static void main(String[] args)
	{
		try
		{
			ServerSocket server = new ServerSocket(6789);
			
			while(true)
			{
				Socket connection=server.accept();
				new Thread( new BattleBoard(connection)).start();
			}
		}
		catch (IOException e)
		{
			System.out.println(e);
		}
		System.out.println("game over! - new line "); bug here
	}
}
