
public class PCMember
{
	protected int memberID;
	protected String username;
	protected String password;
	
	public PCMember(int memberID)
	{
		this.memberID = memberID;
	}
	
	public PCMember(int memberID, String username, String password)
	{
		this.memberID = memberID;
		this.username = username;
		this.password = password;
	}
	
	public PCMember(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	
	public int getMemberID()
	{
		return this.memberID;
	}
	
	public String getUsername()
	{
		return this.username;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	
	public void setMemberID(int memberID)
	{
		this.memberID = memberID;
	}
	
	public void setUsername(String u)
	{
		this.username = u;
	}
	
	public void setPassword(String p)
	{
		this.password = p;
	}
}
