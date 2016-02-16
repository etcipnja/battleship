package battleship;
import java.util.*;
public class BattleBoard {

	private Cell[][] matrix;
	private final int max_row=10;
	private final int max_col=10;
	
	public BattleBoard()
	{
		matrix=new Cell[max_row][max_col];
		for (int row=0;row<max_row;row++)
		{
			for (int col=0;col<max_col;col++)
			{
				matrix[row][col]=new Cell();
			}
		}
	}
	
	public void placePeieces()
	{
			
		BattlePiece[] pieces = new BattlePiece[] { BattlePiece.Submarine, BattlePiece.Destroyer , BattlePiece.Cruiser  } ;

		for (int i=0;i<pieces.length; i++)
			placePiece(pieces[i]);
		
	}
	
	private void placePiece(BattlePiece bp)
	{
		Random gen = new Random();
		
		do
		{
			boolean bVertical = gen.nextBoolean();	
			
			int col = gen.nextInt(10);
			int row = gen.nextInt(10);
			
			if(bVertical)
			{
				for(int i =0; i < bp.getLength(); i++)
				{
					if(matrix[row][col].getPiece() != null)
					{
						
					}
				}
				
			}
			
		}while(true);
		
		
	}
	
	private boolean checkPos(BattlePiece bp, col , row)
	{
		for(int i = 0; i < bp.getLength(); i++)
		{
			
		}
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
	
	
	public String toString()
	{
		String toRet="  ";
		for (int col=0;col<max_col;col++)
			toRet+=" "+col+" ";
		toRet+="\r\n";
		char c='A';
		for (int row=0;row<max_row;row++)
		{
			toRet+=c+" ";
			for (int col=0;col<max_col;col++)
			{
				Cell cell=matrix[col][row];
				toRet+=cell.toString();
			}
			toRet+="\r\n";
			c++;
		}
		return toRet;
	}
}
