import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PCMemberDAO extends AbstractDAO
{
	Connection connection = null;
	Statement statement = null;
	
	public PCMemberDAO()
	{
		
	}
	
	protected boolean insertPCMember(PCMember pcmember)
	{
		String sql = "INSERT INTO pcmembers (username, password) VALUES (?, ?)";
		try
		{
			connection = connect();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, pcmember.getUsername());
			ps.setString(2, pcmember.getPassword());
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
	
	protected List<PCMember> listPCMembers() throws SQLException
	{
		List<PCMember> listPCMembers = new ArrayList<>();
		String sql = "SELECT * FROM pcmembers";
		connection = connect();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
			
		while(resultSet.next())
		{
			int memberID = resultSet.getInt("memberID");
			String username = resultSet.getString("username");
			String password = resultSet.getString("password");
				
			PCMember member = new PCMember(memberID, username, password);
			listPCMembers.add(member);
		}
		resultSet.close();
		statement.close();
		disconnect();
		return listPCMembers;
	}
	
	protected Boolean deletePCMember(PCMember member) throws SQLException
	{
		String sql = "DELETE FROM pcmembers WHERE memberID = ?";
		try
		{
			connection = connect();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, member.getMemberID());
			
			Boolean deleted = ps.executeUpdate() > 0;
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
	
	protected Boolean updatePCMember(PCMember member) throws SQLException
	{
		String sql = "UPDATE pcmembers SET username = ?, password = ?";
		sql += "WHERE memberID = ?";
		try
		{
			connection = connect();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, member.getUsername());
			ps.setString(2, member.getPassword());
			ps.setInt(3, member.getMemberID());
			
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
	
	protected PCMember getPCMember(int memberID) throws SQLException
	{
		PCMember member = null;
		String sql = "SELECT * FROM pcmembers WHERE memberID = ?";
		try
		{
			connection = connect();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, memberID);
			
			ResultSet resultSet = ps.executeQuery();
			
			if(resultSet.next())
			{
				String user = resultSet.getString("username");
				String pass = resultSet.getString("password");
				
				member = new PCMember(memberID, user, pass);
			}
			resultSet.close();
			ps.close();
			return member;
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
