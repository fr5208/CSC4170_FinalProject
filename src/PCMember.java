
public class PCMember
{
	protected int memberID;
	protected String username;
	protected String password;
	protected String email;
	
	public PCMember(int memberID)
	{
		this.memberID = memberID;
	}
	
	public PCMember(int memberID, String username, String password, String email)
	{
		this.memberID = memberID;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public PCMember(String username, String password, String email)
	{
		this.username = username;
		this.password = password;
		this.email = email;
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
	
	public String getEmail()
	{
		return this.email;
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
	
	public void setEmail(String e)
	{
		this.email = e;
	}
}
