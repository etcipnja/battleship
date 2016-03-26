package battleship;
import java.util.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class BattleBoard implements BattleBoardInterface{

	private Cell[][] matrix;
	private Socket m_xConnection;

	
	private final Position max = new Position(10,10);


	public BattleBoard()
	{
		matrix=new Cell[max.getRow()][max.getCol()];

		for (int row=0;row<max.getRow();row++)
			for (int col=0;col<max.getCol();col++)
				matrix[row][col]=new Cell();

	}

	public BattleBoard(Socket client)

	{
		m_xConnection=client;
		matrix=new Cell[max.getRow()][max.getCol()];

		for (int row=0;row<max.getRow();row++)
			for (int col=0;col<max.getCol();col++)
				matrix[row][col]=new Cell();

	}

	public Cell[][] getCellMatrix()
	{
		return matrix;
	}

	public void run()
	{
		System.out.println("new client connected");

		try
		{

			placePeieces();

			DataOutputStream toClient= new DataOutputStream(m_xConnection.getOutputStream());
			BufferedReader fromClient = new BufferedReader(new InputStreamReader(m_xConnection.getInputStream()));

			for (int i=1;true;i++)
			{
				toClient.writeBytes("MOVE "+i+"\r\n--------------------------------\r\n");

				toClient.writeBytes(toString());
				toClient.writeBytes("\r\n");	//signal for end of message

				String move=fromClient.readLine();
				if (move==null) break;

				System.out.println("Player move:"+move);

				try
				{
					move(move);
				}
				catch (IndexOutOfBoundsException e)
				{
					toClient.writeBytes("your move <"+move+"> is bad and you shall feel bad!\r\n");
				}
			}
		}
		catch (IOException e)
		{
			System.out.println(e);
		}
		System.out.println("client disconnected");

	}


	public void placePeieces()
	{

		BattlePiece[] pieces = new BattlePiece[]{ 
				BattlePiece.Boat1,BattlePiece.Boat2,BattlePiece.Boat3,BattlePiece.Boat4,
				BattlePiece.Submarine1,BattlePiece.Submarine2,BattlePiece.Submarine3,
				BattlePiece.Destroyer1,BattlePiece.Destroyer2,
				BattlePiece.Cruiser1
		};

		for (int i=0;i<pieces.length; i++)
			placePiece(pieces[i]);

		

	}

	private void placePiece(BattlePiece bp)
	{
		Random gen = new Random(3);

		int row;
		int col;
		boolean bVertical;

		do
		{
			bVertical = gen.nextBoolean();	

			col = gen.nextInt(10);
			row = gen.nextInt(10);

		}while(!checkPos(bp,col,row,bVertical));	

		for(int i = 0; i<bp.getLength(); i++)
		{
			int [] vetDisp = {-1,-1,-1,0,0,1,1,1};//cols
			int [] hosDisp = {-1,0,1,-1,1,-1,0,1};//rows


			if(bVertical)
			{
				matrix[row+i][col] = new Cell(bp);


				for(int f =0; f<8; f++)
					if (row+i+vetDisp[f] >=0 && col+hosDisp[f] >= 0 && row+i+vetDisp[f] < matrix.length && col+hosDisp[f] < matrix[0].length)
						matrix[row+i+vetDisp[f]][col+hosDisp[f]].cantPlace();

			}	
			else
			{
				matrix[row][col+i] = new Cell(bp);

				for(int f =0; f<8; f++)
					if (row+vetDisp[f] >=0 && col+i+hosDisp[f] >= 0 && row+vetDisp[f] < matrix.length && col+i+hosDisp[f] < matrix[0].length)
						matrix[row+vetDisp[f]][col+i+hosDisp[f]].cantPlace();

			}
		}
	}

	private boolean checkPos(BattlePiece bp, int col , int row, boolean bVertical)   
	{
		for(int i =0; i<bp.getLength(); i++)
		{
			if(bVertical)
			{
				if(row+i>=max.getRow() || col>=max.getCol())
					return false;

				else if (matrix[row+i][col].getCantPlace())
					return false;	
			}

			else
			{
				if(row>=max.getRow() || col+i>=max.getCol())
					return false;

				else if (matrix[row][col+i].getCantPlace())
					return false;

			}

		}
		return true;	
	}

	public void move(String move) throws IndexOutOfBoundsException
	{
		if (move.length()!=2)
			throw new IndexOutOfBoundsException();
		int col=move.charAt(0)-'A';
		int row=move.charAt(1)-'0';
		if (row<0 || row>9 || col<0 || col>9)
			throw new IndexOutOfBoundsException();

		matrix[row][col].hit();
	}

	public int hit(int row, int col)  //TODO shall use Position 
	{
		if(row<0 || row>=10 || col<0 || col>= 10)
			return 17;

		Cell toExamin = matrix[row][col];


		if(toExamin.getPiece() == null)
		{
			toExamin.hit();
			return 0;
		}

		else if(toExamin.getPiece() != null && !toExamin.isHit())
		{
			if(toExamin.getPiece().hit() )
			{
				toExamin.hit();
				return 1;
			}
			else
			{
				System.out.println("You destryoed the "+toExamin.getPiece().toString());
				toExamin.hit();
				return 2;
			}
		}

		return 17;
	}

	public String toString()
	{
		String toRet="  ";
		for (int col=0;col<max.getCol();col++)
			toRet+=" "+col+" ";
		toRet+="\r\n";
		char c='A';
		for (int row=0;row<max.getRow();row++)
		{
			toRet+=c+" ";
			for (int col=0;col<max.getCol();col++)
			{
				Cell cell=matrix[row][col];
				toRet+=cell.toString();
			}
			toRet+="\r\n";
			c++;
		}
		return toRet;
	}
}