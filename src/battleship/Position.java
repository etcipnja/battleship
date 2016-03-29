package battleship;
import java.util.*;

public class Position {

	public final static int max_row=10;
	public final static int max_col=10;

	int m_iRow;
	int m_iCol;
	
	//creates a random position within the limits
	public Position() 
	{
		Random gen = new Random();

		m_iCol = gen.nextInt(max_col);
		m_iRow = gen.nextInt(max_row);

	}

	//creates position with the given coordinates
	//throws exceptions if positions are invalid
	public Position(int iRow, int iCol) throws IllegalArgumentException
	{
		if(iCol < 0 || iCol >= max_col || iRow < 0 || iRow >= max_row )
			throw new IllegalArgumentException("Cant make that position");

		m_iCol = iCol;
		m_iRow = iRow;
	}

	public int getCol()
	{
		return m_iCol;
	}

	public int getRow()
	{
		return m_iRow;
	}

	public void setCol(int iCol) throws IllegalArgumentException
	{
		if(iCol < 0 || iCol >= max_col)
			throw new IllegalArgumentException("The column is invalid");
		
		m_iCol = iCol;
	}

	public void setRow(int iRow) throws IllegalArgumentException
	{
		if( iRow < 0 || iRow >= max_row )
			throw new IllegalArgumentException("The row is invalid");
		
		m_iRow = iRow;
	}

	public String toString()
	{
		return "("+m_iRow+","+m_iCol+")";
	}
}
