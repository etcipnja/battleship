package battleship;

public enum BattlePiece {
	Submarine(2,"submarine"), Destroer(3,"destroer");
	
	private int m_iLength;
	private int m_iLeftAlive;
	private String m_sName;
	
	private BattlePiece(int length, String name)
	{
		m_iLength=length;
		m_iLeftAlive=length;
		m_sName=name;
	}
	
	public boolean hit()
	{
		m_iLeftAlive--;
		return m_iLeftAlive>0;
	}
	
	public boolean isAlive()
	{
		return m_iLeftAlive>0;
	}
	
	public String toString()
	{
		return m_sName+"("+m_iLength+")";
	}
}
