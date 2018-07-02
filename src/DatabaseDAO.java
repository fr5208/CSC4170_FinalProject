
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDAO {

	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/sampledb?user=john&password=pass1234";
	private static final String USER = "john";
	private static final String PASS = "pass1234";
	private boolean loggedIn = false;
	private static String loggedInUsername;
	
	protected void initalizeDatabase()
	{	
		
		Connection connection = null;
		Statement statement = null;
		
		try
		{
			//JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//Open Connection
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			statement = connection.createStatement();
			
			//Initialize the database tables
			statement.executeUpdate("CREATE TABLE PCMembers (Username VARCHAR(32), Password VARCHAR(32), PRIMARY KEY(Username))");
			statement.executeUpdate("CREATE TABLE Papers (PaperID INT NOT NULL AUTO_INCREMENT, Title VARCHAR(64), Abstract TEXT, Pdf BLOB, PRIMARY KEY(PaperID))");
			statement.executeUpdate("CREATE TABLE Authors (Name VARCHAR(32), Email VARCHAR(64), University VARCHAR(64), PRIMARY KEY(Name))");
			statement.executeUpdate("CREATE TABLE ReviewReports (ReviewID INT NOT NULL, Description TEXT, Recommendation VARCHAR(8), PRIMARY KEY(ReviewID))");
			statement.executeUpdate("CREATE TABLE PCMemberPapers (PaperID INT, PCMember VARCHAR(32), FOREIGN KEY (PaperID) REFERENCES Papers(PaperID), FOREIGN KEY (PCMember) REFERENCES PCMembers(Username))");
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
		Connection connection = null;
		Statement statement = null;
		String loginQuery = 	"SELECT * FROM PCMembers " +
								"WHERE Username='" + username + "'" +
								" AND Password='" + password + "'";
		
		try
		{
			//JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//Open Connection
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
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
		
		Connection connection = null;
		Statement statement = null;
		
		try
		{
			//JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//Open Connection
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			statement = connection.createStatement();
			
			//Add records
			statement.executeUpdate("INSERT INTO PCMembers (Username, Password) " + " VALUES ('" + username +"', '" + password + "')");
			

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
	
	protected void AssignAuthors(int paperID, String author1, String author2, String author3)
	{
		Connection connection = null;
		Statement statement = null;
		
		if(loggedIn == false)
			return;
			
		try
		{
			//JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//Open Connection
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			statement = connection.createStatement();
			
			//Assign authors
			if(author1 != "")
				statement.executeUpdate("INSERT INTO AuthorPapers (PaperID, Author) " + "VALUES ('" + paperID + "', '" + author1 + "')" );
			if(author2 != "")
				statement.executeUpdate("INSERT INTO AuthorPapers (PaperID, Author) " + "VALUES ('" + paperID + "', '" + author2 + "')" );
			if(author3 != "")
				statement.executeUpdate("INSERT INTO AuthorPapers (PaperID, Author) " + "VALUES ('" + paperID + "', '" + author3 + "')" );
		
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
		Connection connection = null;
		Statement statement = null;
		
		try
		{
			//JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//Open Connection
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
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
			//JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//Open Connection
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			statement = connection.createStatement();
			
			//Query
			ResultSet results = statement.executeQuery("SELECT Username FROM pcmembers WHERE USERNAME NOT IN (SELECT PCMember FROM pcmemberpapers AS Username)");
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

	public List<PCMember> getPCMembersTable()
	{
		Connection connection = null;
		Statement statement = null;
		List<PCMember> listBook = new ArrayList<>();
		
		try
		{
			//JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//Open Connection
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			statement = connection.createStatement();
			
			String query = "SELECT * FROM PCMembers";
			
			ResultSet resultSet = statement.executeQuery(query);
			
			while (resultSet.next())
			{
	            String username = resultSet.getString("username");
	            String password = resultSet.getString("password");
	             
	            PCMember pcmember = new PCMember(username, password);
	            listBook.add(pcmember);
	        }
			
			resultSet.close();
	        statement.close();
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
         
        return listBook;
    }
}