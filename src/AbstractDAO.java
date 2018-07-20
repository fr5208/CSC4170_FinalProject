
import java.sql.*;

public class AbstractDAO
{
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/sampledb?user=john&password=pass1234";
	private static final String USER = "john";
	private static final String PASS = "pass1234";
	private Connection connection;
	
	public AbstractDAO()
	{
		
	}
	
	protected Connection connect() throws SQLException
	{
		if(connection == null || connection.isClosed())
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
			}
			catch (ClassNotFoundException e)
			{
				throw new SQLException(e);
			}
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
		}
		
		return connection;
	}
	
	protected void disconnect() throws SQLException
	{
		if(connection != null && !connection.isClosed())
		{
			connection.close();
		}
	}
}
