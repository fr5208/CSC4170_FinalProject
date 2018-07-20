
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDAO extends AbstractDAO
{	
	protected Boolean loggedIn = false;
	protected String loggedInUsername = null;
	Connection connection = null;
	Statement statement = null;
	
	protected void initalizeDatabase()
	{	
		try
		{
			connection = connect();
			
			statement = connection.createStatement();
			
			//Initialize the database tables
			statement.executeUpdate("CREATE TABLE PCMembers (MemberID INT NOT NULL AUTO_INCREMENT, Username VARCHAR(32) UNIQUE, Password VARCHAR(32), PRIMARY KEY(MemberID))");
			statement.executeUpdate("CREATE TABLE Papers (PaperID INT NOT NULL AUTO_INCREMENT, Title VARCHAR(64), Summary TEXT, PRIMARY KEY(PaperID))");
			statement.executeUpdate("CREATE TABLE Authors (Name VARCHAR(32), Email VARCHAR(64), University VARCHAR(64), PRIMARY KEY(Name))");
			statement.executeUpdate("CREATE TABLE ReviewReports (ReviewID INT NOT NULL, Description TEXT, Recommendation VARCHAR(8), PRIMARY KEY(ReviewID))");
			statement.executeUpdate("CREATE TABLE PCMemberPapers (PaperID INT, PCMember INT, FOREIGN KEY (PaperID) REFERENCES Papers(PaperID), FOREIGN KEY (PCMember) REFERENCES PCMembers(MemberID))");
			statement.executeUpdate("CREATE TABLE AuthorPapers (PaperID INT, Author VARCHAR(32), FOREIGN KEY (PaperID) REFERENCES Papers(PaperID), FOREIGN KEY (Author) REFERENCES Authors(Name))");

			
			//Add records
			statement.executeUpdate("INSERT INTO PCMembers (Username, Password) " + " VALUES ('John', 'pass1234')");
			statement.executeUpdate("INSERT INTO PCMembers (Username, Password) " + " VALUES ('James', 'testpass')");
			statement.executeUpdate("INSERT INTO PCMembers (Username, Password) " + " VALUES ('Mary', '1234')");
			statement.executeUpdate("INSERT INTO PCMembers (Username, Password) " + " VALUES ('Jim', '54321')");
			statement.executeUpdate("INSERT INTO PCMembers (Username, Password) " + " VALUES ('Steve', '121212')");
			statement.executeUpdate("INSERT INTO PCMembers (Username, Password) " + " VALUES ('Bob', '000000')");
			statement.executeUpdate("INSERT INTO PCMembers (Username, Password) " + " VALUES ('Linus', '010101')");
			statement.executeUpdate("INSERT INTO PCMembers (Username, Password) " + " VALUES ('Richard', '101010')");
			statement.executeUpdate("INSERT INTO PCMembers (Username, Password) " + " VALUES ('Alan', '435621')");
			statement.executeUpdate("INSERT INTO PCMembers (Username, Password) " + " VALUES ('Ada', 'testpass2')");
			
			statement.executeUpdate("INSERT INTO Papers (Title) " + " VALUES ('Paper 1')");
			statement.executeUpdate("INSERT INTO Papers (Title) " + " VALUES ('Paper 2')");
			statement.executeUpdate("INSERT INTO Papers (Title) " + " VALUES ('Paper 3')");
			statement.executeUpdate("INSERT INTO Papers (Title) " + " VALUES ('Paper 4')");
			statement.executeUpdate("INSERT INTO Papers (Title) " + " VALUES ('Paper 5')");
			statement.executeUpdate("INSERT INTO Papers (Title) " + " VALUES ('Paper 6')");
			statement.executeUpdate("INSERT INTO Papers (Title) " + " VALUES ('Paper 7')");
			statement.executeUpdate("INSERT INTO Papers (Title) " + " VALUES ('Paper 8')");
			statement.executeUpdate("INSERT INTO Papers (Title) " + " VALUES ('Paper 9')");
			statement.executeUpdate("INSERT INTO Papers (Title) " + " VALUES ('Paper 10')");
			
			statement.executeUpdate("INSERT INTO Authors (Name) " + " VALUES ('John')");
			statement.executeUpdate("INSERT INTO Authors (Name) " + " VALUES ('James')");
			statement.executeUpdate("INSERT INTO Authors (Name) " + " VALUES ('Mary')");
			statement.executeUpdate("INSERT INTO Authors (Name) " + " VALUES ('Jim')");
			statement.executeUpdate("INSERT INTO Authors (Name) " + " VALUES ('Steve')");
			statement.executeUpdate("INSERT INTO Authors (Name) " + " VALUES ('Bob')");
			statement.executeUpdate("INSERT INTO Authors (Name) " + " VALUES ('Linus')");
			statement.executeUpdate("INSERT INTO Authors (Name) " + " VALUES ('Richard')");
			statement.executeUpdate("INSERT INTO Authors (Name) " + " VALUES ('Alan')");
			statement.executeUpdate("INSERT INTO Authors (Name) " + " VALUES ('Ada')");
			
			statement.executeUpdate("INSERT INTO AuthorPapers (PaperID, Author) " + " VALUES (1, 'Bob')");
			statement.executeUpdate("INSERT INTO AuthorPapers (PaperID, Author) " + " VALUES (1, 'Mary')");
			statement.executeUpdate("INSERT INTO AuthorPapers (PaperID, Author) " + " VALUES (1, 'Linus')");
			statement.executeUpdate("INSERT INTO AuthorPapers (PaperID, Author) " + " VALUES (3, 'Bob')");
			statement.executeUpdate("INSERT INTO AuthorPapers (PaperID, Author) " + " VALUES (4, 'John')");
			statement.executeUpdate("INSERT INTO AuthorPapers (PaperID, Author) " + " VALUES (2, 'Richard')");
			statement.executeUpdate("INSERT INTO AuthorPapers (PaperID, Author) " + " VALUES (2, 'Ada')");
			statement.executeUpdate("INSERT INTO AuthorPapers (PaperID, Author) " + " VALUES (5, 'Alan')");


			disconnect();
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
	}
	
	protected void loginUser(String username, String password)
	{
		String loginQuery = 	"SELECT * FROM PCMembers " +
								"WHERE Username='" + username + "'" +
								" AND Password='" + password + "'";
		try
		{
			connection = connect();
			statement = connection.createStatement();
			
			ResultSet results = statement.executeQuery(loginQuery);
			
			if(results.next())
			{
				loggedIn = true;
				loggedInUsername = username;
			}
			else
			{
				loggedIn = false;
				loggedInUsername = null;
			}
			disconnect();
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
	}
	
	protected void registerUser(String username, String password)
	{
		try
		{
			connection = connect();
			String sql = "INSERT INTO PCMembers (Username, Password) " + " VALUES ('" + username +"', '" + password + "')";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.executeUpdate();
			disconnect();

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
	}
	
	protected void AssignReviewers(int paperID, String reviewer1, String reviewer2, String reviewer3)
	{
		Connection connection = null;
		Statement statement = null;
		
		if(loggedIn == false)
			return;
			
		try
		{
			connection = connect();
			statement = connection.createStatement();
			
			//Assign reviewers
			if(reviewer1 != "")
				statement.executeUpdate("INSERT INTO pcmemberpapers (PaperID, PCMember) " + "VALUES ('" + paperID + "', '" + reviewer1 + "')" );
			if(reviewer2 != "")
				statement.executeUpdate("INSERT INTO pcmemberpapers (PaperID, PCMember) " + "VALUES ('" + paperID + "', '" + reviewer2 + "')" );
			if(reviewer3 != "")
				statement.executeUpdate("INSERT INTO pcmemberpapers (PaperID, PCMember) " + "VALUES ('" + paperID + "', '" + reviewer3 + "')" );
			disconnect();
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
	}
	
	public boolean getLoginStatus()
	{
		return loggedIn;
	}
	
	public String getLoggedInUsername()
	{
		return loggedInUsername;
	}
	
	public String getLoginStatusMessage()
	{
		if(loggedIn)
			return "You are logged in as " + getLoggedInUsername() + ".";
		else
			return "You are not logged in.";
	}
	
	public String singleAuthorNameSearch(String nameSearchQuery)
	{
		try
		{
			connection = connect();
			statement = connection.createStatement();
			
			//Query
			ResultSet results = statement.executeQuery("SELECT * FROM authorpapers WHERE Author = '" + nameSearchQuery + "'");
			ResultSetMetaData metadata = results.getMetaData();
			int numColumns = metadata.getColumnCount();
			StringBuilder resultBuilder = new StringBuilder();
			
			while(results.next())
			{
				for(int i = 1; i <= numColumns; i++)
				{
					if(i > 1)
						resultBuilder.append(", ");
					String columnData = results.getString(i);
					resultBuilder.append(metadata.getColumnName(i) + " " + columnData + "\n");
				}
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
			ResultSetMetaData metadata = results.getMetaData();
			int numColumns = metadata.getColumnCount();
			StringBuilder resultBuilder = new StringBuilder();
			
			while(results.next())
			{
				for(int i = 1; i <= numColumns; i++)
				{
					if(i > 1)
						resultBuilder.append(", ");
					String columnData = results.getString(i);
					resultBuilder.append(metadata.getColumnName(i) + " " + columnData + "\n");
				}
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

}