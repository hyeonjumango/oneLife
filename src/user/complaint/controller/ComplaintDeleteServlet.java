package user.complaint.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.complaint.model.service.complaintService;
import user.notice.model.service.NoticeService;

/**
 * Servlet implementation class ComplaintDeleteServlet
 */
@WebServlet("/complaint/delete")
public class ComplaintDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComplaintDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int c_no = Integer.parseInt(request.getParameter("c_no"));
		
		int result = new complaintService().deleteComplaint(c_no);
		
		if (result > 0) {
			request.getSession().setAttribute("msg", "아파트 민원 게시글 삭제 되었습니다.");
			response.sendRedirect(request.getContextPath() + "/complaint/list");
		} else {
			request.setAttribute("msg", "아파트 민원 게시글 삭제 실패했습니다.");
			request.getRequestDispatcher("/WEB-INF/views/user/common/errorpage.jsp").forward(request, response);
		}
	
	}

}
