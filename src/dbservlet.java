import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.List;

@WebServlet("/dbservlet")
public class dbservlet extends HttpServlet
{
	private DatabaseDAO database = new DatabaseDAO();
	private PCMemberDAO pcmemberDAO = new PCMemberDAO();
	private PaperDAO paperDAO = new PaperDAO();
	
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
		else if(request.getParameter("assignReviewers") != null)
		{
			if(request.getParameter("paperID") != "")
			{
				int paperIDInt = Integer.parseInt(request.getParameter("paperID"));
				String reviewer1 = request.getParameter("reviewer1");
				String reviewer2 = request.getParameter("reviewer2");
				String reviewer3 = request.getParameter("reviewer3");
				database.AssignReviewers(paperIDInt, reviewer1, reviewer2, reviewer3);
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
		else if(request.getParameter("insertPCMember") != null)
		{
			insertPCMember(request, response);
			return;
		}
		else if(request.getParameter("newPCMember") != null)
		{
			showNewPCMemberForm(request, response);
			return;
		}
		else if(request.getParameter("listPCMembers") != null)
		{
			try {
				listPCMembers(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return; 
		}
		else if(request.getParameter("editPCMember") != null)
		{
			try {
				showEditPCMemberForm(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		else if(request.getParameter("deletePCMember") != null)
		{
			try {
				deletePCMember(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		else if(request.getParameter("updatePCMember") != null)
		{
			try {
				updatePCMember(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		else if(request.getParameter("listPapers") != null)
		{
			try {
				listPapers(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return; 
		}
		else if(request.getParameter("editPaper") != null)
		{
			try {
				showEditPaperForm(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return; 
		}
		else if(request.getParameter("updatePaper") != null)
		{
			try {
				updatePaper(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		else if(request.getParameter("newPaper") != null)
		{
			showNewPaperForm(request, response);
			return;
		}
		else if(request.getParameter("deletePaper") != null)
		{
			try {
				deletePaper(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		else if(request.getParameter("insertPaper") != null)
		{
			insertPaper(request, response);
			return;
		}
		
		///
		response.sendRedirect("../PCMemberList.jsp");

		session.setAttribute("loginStatus", database.getLoginStatusMessage());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
	}

	private void listPCMembers(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	{
		List<PCMember> listPCMembers = pcmemberDAO.listPCMembers();
		request.setAttribute("listPCMembers", listPCMembers);
		RequestDispatcher disp = request.getRequestDispatcher("../PCMemberList.jsp");
		disp.forward(request, response);
	}
	
	private void listPapers(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	{
		List<Paper> listPapers = paperDAO.listPapers();
		request.setAttribute("listPapers", listPapers);
		RequestDispatcher disp = request.getRequestDispatcher("../PaperList.jsp");
		disp.forward(request, response);
	}

	private void updatePCMember(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
	{
		int memberID = Integer.parseInt(request.getParameter("memberID"));
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		
		PCMember pcmember = new PCMember(memberID, user, pass);
		pcmemberDAO.updatePCMember(pcmember);
		response.sendRedirect("../PCMemberList.jsp");
	}
	
	private void updatePaper(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
	{
		int paperID = Integer.parseInt(request.getParameter("paperID"));
		String title = request.getParameter("title");
		String summary = request.getParameter("summary");
		
		Paper paper = new Paper(paperID, title, summary);
		paperDAO.updatePaper(paper);
		response.sendRedirect("../PaperList.jsp");
	}

	private void showEditPCMemberForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	{
		int memberID = Integer.parseInt(request.getParameter("memberID"));
		PCMember currentMember = pcmemberDAO.getPCMember(memberID);
		RequestDispatcher disp = request.getRequestDispatcher("../PCMemberForm.jsp");
		request.setAttribute("pcmember", currentMember);
		disp.forward(request, response);
	}
	
	private void showEditPaperForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	{
		int paperID = Integer.parseInt(request.getParameter("paperID"));
		Paper currentPaper = paperDAO.getPaper(paperID);
		RequestDispatcher disp = request.getRequestDispatcher("../PaperForm.jsp");
		request.setAttribute("paper", currentPaper);
		disp.forward(request, response);
	}

	private void deletePCMember(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
	{
		int memberID = Integer.parseInt(request.getParameter("memberID"));
		PCMember pcMember = new PCMember(memberID);
		pcmemberDAO.deletePCMember(pcMember);
		response.sendRedirect("../PCMemberList.jsp");
	}
	
	private void deletePaper(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
	{
		int paperID = Integer.parseInt(request.getParameter("paperID"));
		Paper paper = new Paper(paperID);
		paperDAO.deletePaper(paper);
		response.sendRedirect("../PaperList.jsp");
	}

	private void insertPCMember(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		PCMember member = new PCMember(user, pass);
		pcmemberDAO.insertPCMember(member);
		response.sendRedirect("../PCMemberList.jsp");
	}
	
	private void insertPaper(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String title = request.getParameter("title");
		String summary = request.getParameter("summary");
		Paper paper = new Paper(title, summary);
		paperDAO.insertPaper(paper);
		response.sendRedirect("../PaperList.jsp");
	}

	private void showNewPCMemberForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.sendRedirect("../PCMemberForm.jsp");
	}
	
	private void showNewPaperForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.sendRedirect("../PaperForm.jsp");
	}
}