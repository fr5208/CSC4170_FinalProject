
public class Paper
{
	protected int paperID;
	protected String title;
	protected String summary;
	
	public Paper(int paperID)
	{
		this.paperID = paperID;
	}
	
	public Paper(int paperID, String title, String summary)
	{
		this.paperID = paperID;
		this.title = title;
		this.summary = summary;
	}
	
	public Paper(String title, String summary)
	{
		this.title = title;
		this.summary = summary;
	}
	
	public int getPaperID()
	{
		return this.paperID;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public String getSummary()
	{
		return this.summary;
	}
	
	public void setPaperID(int paperID)
	{
		this.paperID = paperID;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setSummary(String summary)
	{
		this.summary = summary;
	}
}
