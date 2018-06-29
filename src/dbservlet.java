import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet("/dbservlet")
public class dbservlet extends HttpServlet
{
	private static ResultSet resultSet = null;
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/sampledb?user=john&password=pass1234";
	private static final String USER = "john";
	private static final String PASS = "pass1234";
	static Database database = new Database();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		HttpSession session = request.getSession();
		session.setAttribute("loginStatus", database.getLoginStatusMessage());

		request.setAttribute("loginStatus", database.getLoginStatusMessage());
		if(request.getParameter("initializeDatabase") != null)
		{
			database.initalizeDatabase();
		}
		else if(request.getParameter("login") != null)
		{
			database.loginUser(request.getParameter("username"), request.getParameter("password"));
		}
		else if(request.getParameter("register") != null)
		{
			database.registerUser(request.getParameter("username"), request.getParameter("password"));
		}
		else if(request.getParameter("assignAuthors") != null)
		{
			if(request.getParameter("paperID") != null)
			{
				int paperIDInt = Integer.parseInt(request.getParameter("paperID"));
				String author1 = request.getParameter("author1");
				String author2 = request.getParameter("author2");
				String author3 = request.getParameter("author3");
				database.AssignAuthors(paperIDInt, author1, author2, author3);
			}
		}
		response.sendRedirect("../main.jsp");
		
	}
}