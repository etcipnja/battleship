package battleship;

public class Position {
	
	public final int max_row=10;
	public final int max_col=10;
	
	int m_iCol;
	int m_iRow;
	
	//creates a random position within the limits
	public Position() throws IndexOutOfBoundsException
	{
	
	}
	
	//creates position with the given coordinates
	//throws exceptions if positions are invalid
	public Position(int iCol, int iRow)
	{
	
	}
	
	public int getCol()
	{
		return 0;
	}
	
	public int getRow()
	{
		return 0;
	}
	
	public void setCol(int iCol) throws IndexOutOfBoundsException
	{
	
	}
	
	public void setRow(int iRow) throws IndexOutOfBoundsException
	{
	
	}
	
	public String toString()
	{
		return "";
	}
}
