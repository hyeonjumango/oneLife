package user.complaint.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.complaint.model.service.complaintService;
import user.complaint.model.vo.complaint;
import user.member.model.vo.Member;

/**
 * Servlet implementation class ComplaintInsertServlet
 */
@WebServlet("/complaint/insert")
public class ComplaintInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComplaintInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/user/jsp/complaint/complaintinsertView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String open = request.getParameter("open");
		int u_no = ((Member)request.getSession().getAttribute("loginUser")).getU_NO();
		
		complaint c = new complaint(title, content, open, u_no);
		
		int c_no = new complaintService().insertComplaint(c);
		
		if (c_no > 0) {
			request.getSession().setAttribute("c_no", c_no);
			response.sendRedirect(request.getContextPath() + "/complaint/finish");
		} else {
			request.setAttribute("msg", "건의사항 등록에 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/user/common/errorpage.jsp").forward(request, response);
		}
	}

}
