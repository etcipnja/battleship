package battleship;

public class Cell {

	public boolean m_isHit;
	public boolean m_isShip;
	private BattlePiece m_ePiece;
	
	public Cell()
	{
		//m_sShip="";
		m_isHit=false;
		m_isShip=false;
	}
	public Cell(BattlePiece bp)
	{
		m_ePiece=bp;
		m_isShip=true;
	}
	
	public String getPiece()
	{
		if (m_ePiece==null) return "nothing here";
		return ""; //m_ePiece.
	}
	
	public void hit()
	{
		m_isHit=true;
	}
	
	public String toString()
	{
		String sToRet="";
		if (!m_isHit && !m_isShip) sToRet=" . ";
		if (!m_isHit && m_isShip) sToRet=" * ";
		if (m_isHit && !m_isShip) sToRet=" 0 ";
		if (m_isHit && m_isShip) sToRet=" x ";
		return sToRet;
	}
}
