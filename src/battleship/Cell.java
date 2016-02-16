package battleship;

public class Cell {

	private boolean m_isHit;
	private BattlePiece m_ePiece;
	private boolean m_cantPlace;
	
	public Cell()
	{
		m_isHit=false;
		m_ePiece = null;
		m_cantPlace=true;
	}
	public Cell(BattlePiece bp)
	{
		m_ePiece=bp;
		m_isHit = false;
		m_cantPlace=true;
	}
	
	public BattlePiece getPiece()
	{
		return m_ePiece;	 
	}
	
	public void hit()
	{
		m_isHit=true;
	}
	
	public void setHit()
	{
		m_cantPlace = true;
	}
	
	public String toString()
	{
		String sToRet="";
		if (!m_isHit && m_ePiece == null) sToRet=" . ";
		if (!m_isHit && m_ePiece != null) sToRet=" * ";
		if (m_isHit && m_ePiece == null) sToRet=" 0 ";
		if (m_isHit && m_ePiece != null) sToRet=" x ";
		return sToRet;
	}
}
