
public class AuthorPaper
{
	protected int paperID;
	protected String author; 
	
	public AuthorPaper(int paperID, String author)
	{
		this.paperID = paperID;
		this.author = author;
	}
	
	public int getPaperID()
	{
		return this.paperID;
	}
	
	public String getAuthorName()
	{
		return this.author;
	}
	
	public void setPaperID(int id)
	{
		this.paperID = id;
	}
	
	public void setAuthor(String author)
	{
		this.author = author;
	}
}
