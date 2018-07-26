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
	private SearchDAO search = new SearchDAO();
	private DatabaseDAO database = new DatabaseDAO();
	private PCMemberDAO pcmemberDAO = new PCMemberDAO();
	private PaperDAO paperDAO = new PaperDAO();
	private ReviewReportDAO rrDAO = new ReviewReportDAO();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		HttpSession session = request.getSession();
		
		if(request.getParameter("initializeDatabase") != null)
		{
			database.initalizeDatabase();
			session.setAttribute("loginStatus", database.getLoginStatusMessage());
		}
		else if(request.getParameter("login") != null)
		{
			database.loginUser(request.getParameter("username"), request.getParameter("password"));
			session.setAttribute("loginStatus", database.getLoginStatusMessage());
		}
		else if(request.getParameter("register") != null)
		{
			database.registerUser(request.getParameter("username"), request.getParameter("password"));
			session.setAttribute("loginStatus", database.getLoginStatusMessage());

		}
		
		if(database.getLoginStatus() == false)
		{
			session.setAttribute("loginStatus", database.getLoginStatusMessage());
			response.sendRedirect("../main.jsp");
			return;
		}
		
		if(request.getParameter("assignReviewers") != null)
		{
			if(request.getParameter("paperID") != "")
			{
				int paperIDInt = Integer.parseInt(request.getParameter("paperID"));
				String reviewer1 = request.getParameter("reviewer1");
				String reviewer2 = request.getParameter("reviewer2");
				String reviewer3 = request.getParameter("reviewer3");
				database.AssignReviewers(paperIDInt, reviewer1, reviewer2, reviewer3);
			}
			try {
				listReviewReports(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		else if(request.getParameter("searchDatabaseForm") != null)
		{
			showSearchForm(request, response);
			return;
		}
		else if(request.getParameter("singleAuthorNameSearch") != null)
		{
			try {
				listSingleAuthorSearchResults(request, response, request.getParameter("author1"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		else if(request.getParameter("firstAuthorNameSearch") != null)
		{
			listFirstAuthorNameSearchResults(request, response, request.getParameter("author1"));
			return;
		}
		else if(request.getParameter("twoAuthorNameSearch") != null)
		{
			listTwoAuthorSearchResults(request, response, request.getParameter("author1"), request.getParameter("author2"));
			return;

		}
		else if(request.getParameter("listPCMemberWithMostPapers") != null)
		{
			try {
				listPCMemberMostPapers(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		else if(request.getParameter("listPCMemberNoAssignedPapers") != null)
		{
			try {
				listPCMembersWithNoAssignedPapers(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		else if(request.getParameter("listAcceptedPapers") != null)
		{
			
		}
		else if(request.getParameter("insertPCMember") != null)
		{
			try {
				insertPCMember(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			try {
				insertPaper(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		else if(request.getParameter("listReviewReports") != null)
		{
			try {
				listReviewReports(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		else if(request.getParameter("editReviewReport") != null)
		{
			try {
				showEditReviewReportForm(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}else if(request.getParameter("updateReviewReport") != null)
		{
			try {
				updateReviewReport(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		else if(request.getParameter("deleteReviewReport") != null)
		{
			try {
				deleteReviewReport(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		else if(request.getParameter("insertReviewReport") != null)
		{
			try {
				insertReviewReport(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		else if(request.getParameter("newReviewReport") != null)
		{
			showNewReviewReportForm(request, response);
			return;
		}
		else if(request.getParameter("assignReviewersForm") != null)
		{
			showAssignReviewersForm(request, response);
			return;
		}
		else if(request.getParameter("showAcceptedPapers") != null)
		{
			try {
				listAcceptedPapers(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		else if(request.getParameter("rejectedByMattJohn") != null)
		{
			try {
				showPapersRejectedByMattAndJohn(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		
		///
		session.setAttribute("loginStatus", database.getLoginStatusMessage());
		response.sendRedirect("../main.jsp");
	}

	private void listFirstAuthorNameSearchResults(HttpServletRequest request, HttpServletResponse response, String name) throws ServletException, IOException
	{
		List<Paper> listPapers = search.firstAuthorNameSearch(name);
		request.setAttribute("searchResults", listPapers);
		RequestDispatcher disp = request.getRequestDispatcher("../SearchForm.jsp");
		disp.forward(request, response);
	}

	private void listPCMemberMostPapers(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		List<PCMember> listPCMembers = pcmemberDAO.listPCMemberMostPapers();
		request.setAttribute("listPCMembers", listPCMembers);
		RequestDispatcher disp = request.getRequestDispatcher("../PCMemberList.jsp");
		disp.forward(request, response);
	}

	private void listPCMembersWithNoAssignedPapers(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	{
		List<PCMember> listPCMembers = pcmemberDAO.listPCMembersWithNoAssignedPapers();
		request.setAttribute("listPCMembers", listPCMembers);
		RequestDispatcher disp = request.getRequestDispatcher("../PCMemberList.jsp");
		disp.forward(request, response);
	}

	private void listTwoAuthorSearchResults(HttpServletRequest request, HttpServletResponse response, String name1, String name2) throws ServletException, IOException
	{
		List<Paper> listPapers = search.twoAuthorNameSearch(name1, name2);
		request.setAttribute("searchResults", listPapers);
		RequestDispatcher disp = request.getRequestDispatcher("../SearchForm.jsp");
		disp.forward(request, response);
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
	
	private void listSingleAuthorSearchResults(HttpServletRequest request, HttpServletResponse response, String name) throws SQLException, ServletException, IOException
	{
		List<Paper> listAuthors = search.singleAuthorNameSearch(name);
		request.setAttribute("searchResults", listAuthors);
		RequestDispatcher disp = request.getRequestDispatcher("../SearchForm.jsp");
		disp.forward(request, response);
	}
	
	private void listPapers(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	{
		List<Paper> listPapers = paperDAO.listPapers();
		request.setAttribute("listPapers", listPapers);
		RequestDispatcher disp = request.getRequestDispatcher("../PaperList.jsp");
		disp.forward(request, response);
	}
	
	private void showPapersRejectedByMattAndJohn(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	{
		List<Paper> listPapers = paperDAO.listPapersRejectedByMattAndJohn();
		request.setAttribute("listPapers", listPapers);
		RequestDispatcher disp = request.getRequestDispatcher("../PaperList.jsp");
		disp.forward(request, response);
	}
	
	private void listReviewReports(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	{
		List<ReviewReport> listReviewReports = rrDAO.listReviewReports();
		request.setAttribute("listReviewReports", listReviewReports);
		RequestDispatcher disp = request.getRequestDispatcher("../ReviewReportList.jsp");
		disp.forward(request, response);
	}
	
	private void listAcceptedPapers(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	{
		List<Paper> listAcceptedPapers = paperDAO.listAcceptedPapers();
		request.setAttribute("listAcceptedPapers", listAcceptedPapers);
		RequestDispatcher disp = request.getRequestDispatcher("../AcceptedPapers.jsp");
		disp.forward(request, response);
	}

	private void updatePCMember(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
	{
		int memberID = Integer.parseInt(request.getParameter("memberID"));
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		String email = request.getParameter("email");
		
		PCMember pcmember = new PCMember(memberID, user, pass, email);
		pcmemberDAO.updatePCMember(pcmember);
		listPCMembers(request, response);
	}
	
	private void updatePaper(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
	{
		int paperID = Integer.parseInt(request.getParameter("paperID"));
		String title = request.getParameter("title");
		String summary = request.getParameter("summary");
		
		Paper paper = new Paper(paperID, title, summary);
		paperDAO.updatePaper(paper);
		listPapers(request, response);
	}
	
	private void updateReviewReport(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
	{
		int reviewID = Integer.parseInt(request.getParameter("reviewID"));
		int paperID = Integer.parseInt(request.getParameter("paperID"));
		int reviewerID = Integer.parseInt(request.getParameter("reviewerID"));
		String description = request.getParameter("description");
		String recommendation = request.getParameter("recommendation");
		
		ReviewReport rr = new ReviewReport(reviewID, paperID, reviewerID, description, recommendation);
		rrDAO.updateReviewReport(rr);
		listReviewReports(request, response);
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
	
	private void showEditReviewReportForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	{
		int reviewID = Integer.parseInt(request.getParameter("reviewReportID"));
		ReviewReport currentrr = rrDAO.getReviewReport(reviewID);
		RequestDispatcher disp = request.getRequestDispatcher("../ReviewReportForm.jsp");
		request.setAttribute("reviewreport", currentrr);
		disp.forward(request, response);
	}

	private void deletePCMember(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
	{
		int memberID = Integer.parseInt(request.getParameter("memberID"));
		PCMember pcMember = new PCMember(memberID);
		pcmemberDAO.deletePCMember(pcMember);
		listPCMembers(request, response);
	}
	
	private void deletePaper(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
	{
		int paperID = Integer.parseInt(request.getParameter("paperID"));
		Paper paper = new Paper(paperID);
		paperDAO.deletePaper(paper);
		listPapers(request, response);
	}
	
	private void deleteReviewReport(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
	{
		int reviewID = Integer.parseInt(request.getParameter("reviewReportID"));
		ReviewReport rr = new ReviewReport(reviewID);
		rrDAO.deleteReviewReport(rr);
		listReviewReports(request, response);
	}

	private void insertPCMember(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException
	{
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		String email = request.getParameter("email");
		PCMember member = new PCMember(user, pass, email);
		pcmemberDAO.insertPCMember(member);
		listPCMembers(request, response);
	}
	
	private void insertPaper(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException
	{
		String title = request.getParameter("title");
		String summary = request.getParameter("summary");
		Paper paper = new Paper(title, summary);
		paperDAO.insertPaper(paper);
		listPapers(request, response);
	}
	
	private void insertReviewReport(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException
	{
		int paperID = Integer.parseInt(request.getParameter("paperID"));
		int reviewerID = Integer.parseInt(request.getParameter("reviewerID"));
		String description = request.getParameter("description");
		String recommendation = request.getParameter("recommendation");
		ReviewReport rr = new ReviewReport(paperID, reviewerID, description, recommendation);
		rrDAO.insertReviewReport(rr);
		listReviewReports(request, response);
	}

	private void showNewPCMemberForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.sendRedirect("../PCMemberForm.jsp");
	}
	
	private void showNewPaperForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.sendRedirect("../PaperForm.jsp");
	}
	
	private void showNewReviewReportForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.sendRedirect("../ReviewReportForm.jsp");
	}
	
	private void showAssignReviewersForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.sendRedirect("../ManageReviewers.jsp");
	}
	private void showSearchForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.sendRedirect("../SearchForm.jsp");
	}
}