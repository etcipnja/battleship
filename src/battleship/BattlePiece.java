package battleship;

public enum BattlePiece {
	
	Boat1(1,"Happy Ending"), Boat2(1,"Serendipity"), Boat3(1,"Quiet Riot"), Boat4(1,"Joint Effort"),
	Submarine1(2,"Corsair"), Submarine2(2,"Remora"), Submarine3(2,"Abraham Lincoln"), 
	Destroyer1(3,"Blakeley"), Destroyer2(3,"Claxton"),
	Cruiser1(4,"Saratoga") ;
	
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
		String sToRet;
		switch(m_iLength)
		{
		case 1:
			sToRet="boat"; break;
		case 2:
			sToRet="submarine"; break;
		case 3:
			sToRet="destroyer"; break;
		case 4:
			sToRet="cruiser"; break;
		default:
			sToRet="WTF?"; break;
		}
		return sToRet+" \""+m_sName+"\" ("+m_iLeftAlive+"/"+m_iLength+")";
	}
	public int getLength()
	{
		return m_iLength;
	}
}
