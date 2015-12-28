package battleship;

public class BattleBoard {

	private Cell[][] mattrix;
	private final int max_row=10;
	private final int max_col=10;
	
	public BattleBoard()
	{
		mattrix=new Cell[max_row][max_col];
		for (int row=0;row<max_row;row++)
		{
			for (int col=0;col<max_col;col++)
			{
				mattrix[row][col]=new Cell();
			}
		}
	}
	
	public void placePeieces()
	{
			
		BattlePiece[] pieces = new BattlePiece[] { BattlePiece.Submarine, BattlePiece.Destroer } ;
		//placing submarine
		for (int i=0;i<pieces.length; i++)
			placePiece(pieces[i]);
		
	}
	
	private void placePiece(BattlePiece bp)
	{
		int col, row;
		boolean bVertical;
	}
	
	public void move(String move) throws IndexOutOfBoundsException
	{
		if (move.length()!=2)
			throw new IndexOutOfBoundsException();
		int col=move.charAt(0)-'A';
		int row=move.charAt(1)-'0';
		if (row<0 || row>9 || col<0 || col>9)
			throw new IndexOutOfBoundsException();
		
		mattrix[row][col].hit();
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
				Cell cell=mattrix[col][row];
				toRet+=cell.toString();
			}
			toRet+="\r\n";
			c++;
		}
		return toRet;
	}
}
