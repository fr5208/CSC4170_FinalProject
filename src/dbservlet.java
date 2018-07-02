import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet("/dbservlet")
public class dbservlet extends HttpServlet
{
	static DatabaseDAO database = new DatabaseDAO();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		HttpSession session = request.getSession();
		
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
			if(request.getParameter("paperID") != "")
			{
				int paperIDInt = Integer.parseInt(request.getParameter("paperID"));
				String author1 = request.getParameter("author1");
				String author2 = request.getParameter("author2");
				String author3 = request.getParameter("author3");
				database.AssignAuthors(paperIDInt, author1, author2, author3);
			}
		}
		else if(request.getParameter("singleAuthorNameSearch") != null)
		{
			session.setAttribute("searchResults", database.singleAuthorNameSearch(request.getParameter("authorNameSearch")));
		}
		else if(request.getParameter("firstAuthorNameSearch") != null)
		{
			
		}
		else if(request.getParameter("twoAuthorNameSearch") != null)
		{
			
		}
		else if(request.getParameter("listPCMemberWithMostPapers") != null)
		{
			
		}
		else if(request.getParameter("listPCMemberNoAssignedPapers") != null)
		{
			session.setAttribute("searchResults", database.getNoAssignedPapers());
		}
		else if(request.getParameter("rejectedByMattJohn") != null)
		{
			
		}
		else if(request.getParameter("listAcceptedPapers") != null)
		{
			
		}
		response.sendRedirect("../main.jsp");
		
		session.setAttribute("loginStatus", database.getLoginStatusMessage());
		session.setAttribute("listPCMember", database.getPCMembersTable());
	}
}