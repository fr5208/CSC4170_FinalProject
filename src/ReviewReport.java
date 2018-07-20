
public class ReviewReport
{
	protected int reviewID;
	protected int paperID;
	protected int reviewerID;
	protected String description;
	protected String recommendation;
	
	public ReviewReport(int reviewID)
	{
		this.reviewID = reviewID;
	}
	
	public ReviewReport(int reviewID, int paperID, int reviewerID, String description, String recommendation)
	{
		this.reviewID = reviewID;
		this.paperID = paperID;
		this.reviewerID = reviewerID;
		this.description = description;
		this.recommendation = recommendation;
	}
	
	public ReviewReport(int paperID, int reviewerID, String description, String recommendation)
	{
		this.paperID = paperID;
		this.reviewerID = reviewerID;
		this.description = description;
		this.recommendation = recommendation;
	}
	
	public int getReviewID()
	{
		return this.reviewID;
	}
	
	public int getPaperID()
	{
		return this.paperID;
	}
	
	public int getReviewerID()
	{
		return this.reviewerID;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public String getRecommendation()
	{
		return this.recommendation;
	}
	
	public void setReviewID(int reviewID)
	{
		this.reviewID = reviewID;
	}
	
	public void serPaperID(int paperID)
	{
		this.paperID = paperID;
	}
	
	public void setReviewerID(int reviewerID)
	{
		this.reviewerID = reviewerID;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public void setRecommendation(String recommendation)
	{
		this.recommendation = recommendation;
	}
}
