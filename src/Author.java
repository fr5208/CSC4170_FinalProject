
public class Author
{
	protected String name;
	protected String email;
	protected String university;
	
	public Author(String name)
	{
		this.name = name;
	}
	
	public Author(String name, String email, String university)
	{
		this.name = name;
		this.email = email;
		this.university = university;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getEmail()
	{
		return this.email;
	}
	
	public String getUni()
	{
		return this.university;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public void setUni(String uni)
	{
		this.university = uni;
	}
}
