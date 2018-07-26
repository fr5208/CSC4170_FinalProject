
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
			
			statement.executeUpdate("DROP TABLE IF EXISTS ReviewReports");
			statement.executeUpdate("DROP TABLE IF EXISTS AuthorPapers");
			statement.executeUpdate("DROP TABLE IF EXISTS Papers");
			statement.executeUpdate("DROP TABLE IF EXISTS Authors");
			statement.executeUpdate("DROP TABLE IF EXISTS PCMembers");


			
			//Initialize the database tables
			statement.executeUpdate("CREATE TABLE PCMembers (MemberID INT NOT NULL AUTO_INCREMENT, Username VARCHAR(32) UNIQUE, Email VARCHAR(255), AssignedReports INT DEFAULT 0, Password VARCHAR(32), PRIMARY KEY(MemberID))");
			statement.executeUpdate("CREATE TABLE Papers (PaperID INT NOT NULL AUTO_INCREMENT, Title VARCHAR(64), Summary TEXT, Pdf BLOB, PRIMARY KEY(PaperID))");
			statement.executeUpdate("CREATE TABLE Authors (Name VARCHAR(64) NOT NULL, Email VARCHAR(64), University VARCHAR(64), PRIMARY KEY(Name))");
			statement.executeUpdate("CREATE TABLE ReviewReports (ReviewID INT NOT NULL AUTO_INCREMENT, PaperID INT, ReviewerID INT, Description TEXT, Recommendation ENUM('pending', 'rejected', 'accepted'), PRIMARY KEY(ReviewID), FOREIGN KEY (PaperID) REFERENCES Papers(PaperID), FOREIGN KEY (ReviewerID) REFERENCES PCMembers(memberID))");
			statement.executeUpdate("CREATE TABLE AuthorPapers (PaperID INT, Author VARCHAR(64), FOREIGN KEY (PaperID) REFERENCES Papers(PaperID), FOREIGN KEY (Author) REFERENCES Authors(Name))");
			
			//Drop triggers
			statement.execute("DROP TRIGGER IF EXISTS limit5assignedreviews");
			statement.execute("DROP TRIGGER IF EXISTS decrementassignedreports");
			statement.execute("DROP TRIGGER IF EXISTS limit3reviewers");

			
			//Trigger constraints
			statement.execute("CREATE TRIGGER limit5assignedreviews BEFORE INSERT ON ReviewReports "
									+ "FOR EACH ROW "
									+ "BEGIN "
									+ "IF((SELECT AssignedReports FROM PCMembers WHERE PCMembers.memberID = NEW.reviewerID) >= 5) "
									+ "THEN "
									+ "SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cannot have more than 5 reports assigned to a PCMember.'; "
									+ "ELSE "
									+ "UPDATE PCMembers SET AssignedReports = AssignedReports + 1 WHERE memberID = NEW.reviewerID; "
									+ "END IF; "
									+ "END;");
			statement.execute("CREATE TRIGGER decrementassignedreports AFTER DELETE ON ReviewReports "
									+ "FOR EACH ROW "
									+ "BEGIN "
									+ "IF((SELECT AssignedReports FROM PCMembers WHERE PCMembers.memberID = OLD.reviewerID) <= 0) "
									+ "THEN "
									+ "SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cannot have less than zero reports assigned to a PCMember.'; "
									+ "ELSE "
									+ "UPDATE PCMembers SET AssignedReports = AssignedReports - 1 WHERE memberID = OLD.reviewerID; "
									+ "END IF; "
									+ "END;");
			statement.execute("CREATE TRIGGER limit3reviews BEFORE INSERT ON ReviewReports "
									+ "FOR EACH ROW "
									+ "BEGIN "
									+ "IF((SELECT COUNT(paperID) FROM ReviewReports WHERE ReviewReports.paperID = NEW.paperID) >= 3) "
									+ "THEN "
									+ "SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cannot have a paper assigned to more than 3 reviewers.'; "
									+ "END IF; "
									+ "END;");
			
			//Add records
			statement.executeUpdate("INSERT INTO PCMembers (Username, Password) " + " VALUES ('John', 'pass1234')");
			statement.executeUpdate("INSERT INTO PCMembers (Username, Password) " + " VALUES ('James', 'testpass')");
			statement.executeUpdate("INSERT INTO PCMembers (Username, Password) " + " VALUES ('Mary', '1234')");
			statement.executeUpdate("INSERT INTO PCMembers (Username, Password) " + " VALUES ('Jim', '54321')");
			statement.executeUpdate("INSERT INTO PCMembers (Username, Password) " + " VALUES ('Steve', '121212')");
			statement.executeUpdate("INSERT INTO PCMembers (Username, Password) " + " VALUES ('Matt', '000000')");
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
			statement.executeUpdate("INSERT INTO Authors (Name) " + " VALUES ('Zhang')");
			statement.executeUpdate("INSERT INTO Authors (Name) " + " VALUES ('Steve')");
			statement.executeUpdate("INSERT INTO Authors (Name) " + " VALUES ('Lu')");
			statement.executeUpdate("INSERT INTO Authors (Name, Email) " + " VALUES ('Linus', 'linus@linus.com')");
			statement.executeUpdate("INSERT INTO Authors (Name) " + " VALUES ('Richard')");
			statement.executeUpdate("INSERT INTO Authors (Name) " + " VALUES ('Alan')");
			statement.executeUpdate("INSERT INTO Authors (Name) " + " VALUES ('Ada')");
			
			statement.executeUpdate("INSERT INTO AuthorPapers (PaperID, Author) " + " VALUES (1, 'Lu')");
			statement.executeUpdate("INSERT INTO AuthorPapers (PaperID, Author) " + " VALUES (1, 'John')");
			statement.executeUpdate("INSERT INTO AuthorPapers (PaperID, Author) " + " VALUES (1, 'James')");
			statement.executeUpdate("INSERT INTO AuthorPapers (PaperID, Author) " + " VALUES (3, 'Ada')");
			statement.executeUpdate("INSERT INTO AuthorPapers (PaperID, Author) " + " VALUES (4, 'Steve')");
			statement.executeUpdate("INSERT INTO AuthorPapers (PaperID, Author) " + " VALUES (2, 'Zhang')");
			statement.executeUpdate("INSERT INTO AuthorPapers (PaperID, Author) " + " VALUES (2, 'Ada')");
			statement.executeUpdate("INSERT INTO AuthorPapers (PaperID, Author) " + " VALUES (5, 'Richard')");
			statement.executeUpdate("INSERT INTO AuthorPapers (PaperID, Author) " + " VALUES (5, 'Mary')");
			
			statement.executeUpdate("INSERT INTO ReviewReports (PaperID, ReviewerID, Description, Recommendation) " + " VALUES (3, 5, 'Good!', 'accepted')");
			statement.executeUpdate("INSERT INTO ReviewReports (PaperID, ReviewerID, Description, Recommendation) " + " VALUES (3, 6, 'I like this.', 'accepted')");
			statement.executeUpdate("INSERT INTO ReviewReports (PaperID, ReviewerID, Description, Recommendation) " + " VALUES (2, 1, 'Not sure about this.', 'rejected')");
			statement.executeUpdate("INSERT INTO ReviewReports (PaperID, ReviewerID, Description, Recommendation) " + " VALUES (2, 3, 'Not sure about this.', 'rejected')");
			statement.executeUpdate("INSERT INTO ReviewReports (PaperID, ReviewerID, Description, Recommendation) " + " VALUES (1, 3, 'Pending review.', 'pending')");
			statement.executeUpdate("INSERT INTO ReviewReports (PaperID, ReviewerID, Description, Recommendation) " + " VALUES (3, 2, 'Pending review.', 'pending')");
			statement.executeUpdate("INSERT INTO ReviewReports (PaperID, ReviewerID, Description, Recommendation) " + " VALUES (2, 6, 'Not up to par.', 'rejected')");

			//Drop views
			statement.executeUpdate("DROP VIEW IF EXISTS AcceptedPapers");
			statement.executeUpdate("DROP VIEW IF EXISTS ReviewCount");
			statement.executeUpdate("DROP VIEW IF EXISTS ListPapers");

			
			//Create accepted papers view
			statement.executeUpdate("CREATE VIEW AcceptedPapers AS SELECT paperID FROM ReviewReports HAVING COUNT(Recommendation = 'accepted') >= 2");
			statement.executeUpdate("CREATE VIEW ReviewCount AS SELECT * FROM (SELECT *, COUNT(ReviewReports.reviewerID) totalcount FROM PCMembers RIGHT JOIN ReviewReports ON PCMembers.MemberID = ReviewReports.reviewerID GROUP BY ReviewReports.reviewerID ORDER BY COUNT(*) DESC) AS reviewcount");


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
				statement.executeUpdate("INSERT INTO ReviewReports (PaperID, ReviewerID) " + "VALUES ('" + paperID + "', '" + Integer.parseInt(reviewer1) + "')" );
			if(reviewer2 != "")
				statement.executeUpdate("INSERT INTO ReviewReports (PaperID, ReviewerID) " + "VALUES ('" + paperID + "', '" + Integer.parseInt(reviewer2) + "')" );
			if(reviewer3 != "")
				statement.executeUpdate("INSERT INTO ReviewReports (PaperID, ReviewerID) " + "VALUES ('" + paperID + "', '" + Integer.parseInt(reviewer3) + "')" );
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
}