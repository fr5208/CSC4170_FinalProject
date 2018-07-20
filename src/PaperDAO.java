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
		try
		{
			connection = connect();
			PreparedStatement ps = connection.prepareStatement(sql);
			PreparedStatement ps2 = connection.prepareStatement(sql2);
			ps.setInt(1, paper.getPaperID());
			ps2.setInt(1, paper.getPaperID());

		
			Boolean deleted = (ps2.executeUpdate() > 0 && ps.executeUpdate() > 0);
			ps.close();
			ps2.close();
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
}
