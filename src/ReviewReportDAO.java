import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewReportDAO extends AbstractDAO
{
	Connection connection = null;
	Statement statement = null;
	
	public ReviewReportDAO()
	{
		
	}
	
	protected Boolean insertReviewReport(ReviewReport rr)
	{
		String sql = "INSERT INTO ReviewReports (paperID, reviewerID, description, recommendation) VALUES (?, ?, ?, ?)";
		try
		{
			connection = connect();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, rr.getPaperID());
			ps.setInt(2, rr.getReviewerID());
			ps.setString(3, rr.getDescription());
			ps.setString(4, rr.getRecommendation());
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

	protected List<ReviewReport> listReviewReports() throws SQLException
	{
		List<ReviewReport> listReviewReports = new ArrayList<>();
		String sql = "SELECT * FROM reviewreports";
		connection = connect();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
			
		while(resultSet.next())
		{
			int reviewID = resultSet.getInt("reviewID");
			int paperID = resultSet.getInt("paperID");
			int reviewerID = resultSet.getInt("reviewerID");
			String description = resultSet.getString("description");
			String recommendation = resultSet.getString("recommendation");
				
			ReviewReport rr = new ReviewReport(reviewID, paperID, reviewerID, description, recommendation);
			listReviewReports.add(rr);
		}
		resultSet.close();
		statement.close();
		disconnect();
		return listReviewReports;
	}
	
	protected Boolean deleteReviewReport(ReviewReport rr) throws SQLException
	{
		String sql = "DELETE FROM reviewreports WHERE reviewID = ?";
		try
		{
			connection = connect();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, rr.getReviewID());

		
			Boolean deleted = (ps.executeUpdate() > 0);
			ps.close();
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

	protected Boolean updateReviewReport(ReviewReport rr) throws SQLException
	{
		String sql = "UPDATE reviewreports SET paperID = ?, reviewerID = ?, description = ?, recommendation = ?";
		sql += "WHERE reviewID = ?";
		try
		{
			connection = connect();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, rr.getPaperID());
			ps.setInt(2, rr.getReviewerID());
			ps.setString(3, rr.getDescription());
			ps.setString(4, rr.getRecommendation());
			ps.setInt(5, rr.getReviewID());
			
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

	protected ReviewReport getReviewReport(int reviewID) throws SQLException
	{
		ReviewReport rr = null;
		String sql = "SELECT * FROM reviewreports WHERE reviewID = ?";
		try
		{
			connection = connect();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, reviewID);
			
			ResultSet resultSet = ps.executeQuery();
			
			if(resultSet.next())
			{
				int paperID = resultSet.getInt("paperID");
				int reviewerID = resultSet.getInt("reviewerID");
				String description = resultSet.getString("description");
				String recommendation = resultSet.getString("recommendation");
				
				rr = new ReviewReport(reviewID, paperID, reviewerID, description, recommendation);
			}
			resultSet.close();
			ps.close();
			return rr;
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
