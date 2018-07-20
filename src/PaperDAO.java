import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaperDAO extends AbstractDAO
{
	Connection connection = null;
	Statement statement = null;
	
	public PaperDAO()
	{
		
	}

	protected Boolean insertPaper(Paper paper)
	{
		String sql = "INSERT INTO papers (title, summary) VALUES (?, ?)";
		try
		{
			connection = connect();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, paper.getTitle());
			ps.setString(2, paper.getSummary());
			Boolean inserted = ps.executeUpdate() > 0;
			ps.close();
			disconnect();
			return inserted;
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
		return false;
	}
	
	protected List<Paper> listPapers() throws SQLException
	{
		List<Paper> listPapers = new ArrayList<>();
		String sql = "SELECT * FROM papers";
		connection = connect();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
			
		while(resultSet.next())
		{
			int paperID = resultSet.getInt("paperID");
			String title = resultSet.getString("title");
			String summary = resultSet.getString("summary");
				
			Paper paper = new Paper(paperID, title, summary);
			listPapers.add(paper);
		}
		resultSet.close();
		statement.close();
		disconnect();
		return listPapers;
	}


	protected Boolean deletePaper(Paper paper) throws SQLException
	{
		String sql = "DELETE FROM papers WHERE paperID = ?";
		String sql2 = "DELETE FROM authorpapers WHERE PaperID = ?";
		String sql3 = "DELETE FROM pcmemberpapers WHERE PaperID = ?";
		try
		{
			connection = connect();
			PreparedStatement ps = connection.prepareStatement(sql);
			PreparedStatement ps2 = connection.prepareStatement(sql2);
			PreparedStatement ps3 = connection.prepareStatement(sql3);
			ps.setInt(1, paper.getPaperID());
			ps2.setInt(1, paper.getPaperID());
			ps3.setInt(1, paper.getPaperID());

		
			Boolean deleted = (ps3.executeUpdate() > 0);
			deleted = (ps2.executeUpdate() > 0);
			deleted = (ps.executeUpdate() > 0);
			ps.close();
			ps2.close();
			ps3.close();
			disconnect();
			return deleted;
		
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
		return false;
	}
	
	protected Boolean updatePaper(Paper paper) throws SQLException
	{
		String sql = "UPDATE papers SET title = ?, summary = ?";
		sql += "WHERE paperID = ?";
		try
		{
			connection = connect();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, paper.getTitle());
			ps.setString(2, paper.getSummary());
			ps.setInt(3, paper.getPaperID());
			
			Boolean updated = ps.executeUpdate() > 0;
			ps.close();
			disconnect();
			return updated;
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
		return false;
	}
	
	protected Paper getPaper(int paperID) throws SQLException
	{
		Paper paper = null;
		String sql = "SELECT * FROM papers WHERE paperID = ?";
		try
		{
			connection = connect();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, paperID);
			
			ResultSet resultSet = ps.executeQuery();
			
			if(resultSet.next())
			{
				String title = resultSet.getString("title");
				String summary = resultSet.getString("summary");
				
				paper = new Paper(paperID, title, summary);
			}
			resultSet.close();
			ps.close();
			return paper;
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
		return null;
	}

	public List<Paper> listAcceptedPapers() throws SQLException {
		List<Paper> listPapers = new ArrayList<>();
		String sql = "SELECT * FROM AcceptedPapers ap INNER JOIN Papers p ON ap.paperID = p.paperID";
		connection = connect();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
			
		while(resultSet.next())
		{
			int paperID = resultSet.getInt("paperID");
			String title = resultSet.getString("title");
			String summary = resultSet.getString("summary");
				
			Paper paper = new Paper(paperID, title, summary);
			listPapers.add(paper);
		}
		resultSet.close();
		statement.close();
		disconnect();
		return listPapers;
	}

	public List<Paper> listPapersRejectedByMattAndJohn() throws SQLException {
		List<Paper> listPapers = new ArrayList<>();
		String sql = "SELECT DISTINCT * "
					+ "FROM Papers p "
					+ "INNER JOIN "
					+ "(SELECT * FROM ReviewReports WHERE reviewerID = 1 AND recommendation = 'rejected') "
					+ "AS rr ON rr.paperID = p.paperID "
					+ "INNER JOIN "
					+ "(SELECT * FROM ReviewReports WHERE reviewerID = 6 AND recommendation = 'rejected')"
					+ "AS rr2 ON rr2.paperID = p.paperID";
		connection = connect();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
			
		while(resultSet.next())
		{
			int paperID = resultSet.getInt("paperID");
			String title = resultSet.getString("title");
			String summary = resultSet.getString("summary");
				
			Paper paper = new Paper(paperID, title, summary);
			listPapers.add(paper);
		}
		resultSet.close();
		statement.close();
		disconnect();
		return listPapers;
	}
}
