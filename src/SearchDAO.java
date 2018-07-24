import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SearchDAO extends AbstractDAO
{
	Connection connection = null;
	Statement statement = null;
	
	public SearchDAO()
	{
		
	}
	
	public List<Paper> singleAuthorNameSearch(String nameSearchQuery)
	{
		List<Paper> paperList = new ArrayList<>();
		String sql = "SELECT DISTINCT p.paperid, title, summary FROM papers p INNER JOIN (SELECT * from authorpapers WHERE author = '" + nameSearchQuery + "') ap ON p.paperid = ap.paperid";
		try
		{
			connection = connect();
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			while(resultSet.next())
			{
				int paperID = resultSet.getInt("paperID");
				String title = resultSet.getString("title");
				String summary = resultSet.getString("summary");
				
				Paper paper = new Paper(paperID, title, summary);
				paperList.add(paper);
			}
			resultSet.close();
			statement.close();
			disconnect();
			return paperList;
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(statement != null)
					statement.close();
			}
			catch(SQLException se2){}
			try
			{
				if(connection != null)
					connection.close();
			}
			catch(SQLException se3)
			{
				se3.printStackTrace();
			}
		}
		return paperList;
	}

	public List<Paper> twoAuthorNameSearch(String name1, String name2)
	{
		List<Paper> paperList = new ArrayList<>();
		String sql = "SELECT * FROM Papers WHERE paperID IN (SELECT ap.paperID FROM (SELECT * FROM AuthorPapers WHERE Author = '" + name1 + "') AS ap JOIN (SELECT * FROM AuthorPapers WHERE Author = '" + name2 + "') AS ap2 ON ap2.paperID = ap.paperID);";
		try
		{
			connection = connect();
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			while(resultSet.next())
			{
				int paperID = resultSet.getInt("paperID");
				String title = resultSet.getString("title");
				String summary = resultSet.getString("summary");
				
				Paper paper = new Paper(paperID, title, summary);
				paperList.add(paper);
			}
			resultSet.close();
			statement.close();
			disconnect();
			return paperList;
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(statement != null)
					statement.close();
			}
			catch(SQLException se2){}
			try
			{
				if(connection != null)
					connection.close();
			}
			catch(SQLException se3)
			{
				se3.printStackTrace();
			}
		}
		return paperList;
	}
	
	public String getNoAssignedPapers()
	{
		Connection connection = null;
		Statement statement = null;
		
		try
		{
			connection = connect();
			statement = connection.createStatement();
			
			//Query
			ResultSet results = statement.executeQuery("SELECT Username FROM pcmembers WHERE Username NOT IN (SELECT PCMember FROM pcmemberpapers AS Username)");
			StringBuilder resultBuilder = new StringBuilder();
			
			while(results.next())
			{
				
			}
			disconnect();
			return resultBuilder.toString();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(statement != null)
					statement.close();
			}
			catch(SQLException se2){}
			try
			{
				if(connection != null)
					connection.close();
			}
			catch(SQLException se3)
			{
				se3.printStackTrace();
			}
		}
		return "";
	}

	public List<Paper> firstAuthorNameSearch(String name)
	{
		List<Paper> paperList = new ArrayList<>();
		String sql = "SELECT p.paperID, p.title, p.summary FROM " + 
				"(SELECT p2.paperID, author, title, summary FROM Papers p2 " + 
				" INNER JOIN AuthorPapers ap ON ap.paperID = p2.paperID " + 
				" GROUP BY p2.paperID) " + 
				" AS p WHERE p.author = '" + name + "'";
		try
		{
			connection = connect();
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			while(resultSet.next())
			{
				int paperID = resultSet.getInt("paperID");
				String title = resultSet.getString("title");
				String summary = resultSet.getString("summary");
				
				Paper paper = new Paper(paperID, title, summary);
				paperList.add(paper);
			}
			resultSet.close();
			statement.close();
			disconnect();
			return paperList;
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(statement != null)
					statement.close();
			}
			catch(SQLException se2){}
			try
			{
				if(connection != null)
					connection.close();
			}
			catch(SQLException se3)
			{
				se3.printStackTrace();
			}
		}
		return paperList;
	}
}
