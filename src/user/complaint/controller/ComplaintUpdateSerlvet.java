package user.complaint.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.complaint.model.service.complaintService;
import user.complaint.model.vo.complaint;
import user.notice.model.service.NoticeService;
import user.notice.model.vo.Notice;

/**
 * Servlet implementation class ComplaintUpdateSerlvet
 */
@WebServlet("/complaint/update")
public class ComplaintUpdateSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComplaintUpdateSerlvet() {
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
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String open = request.getParameter("open");
		
		complaint c = new complaint(c_no, title, content, open);
		
		int result = new complaintService().updatecomplaint(c);
		
		if (result > 0) {
			request.getSession().setAttribute("c_no", c_no);
			response.sendRedirect(request.getContextPath() + "/complaint/updatefinish");
		} else {
			request.setAttribute("msg", "아파트 민원 수정에 실패했습니다.");
			request.getRequestDispatcher("/WEB-INF/views/user/common/errorpage.jsp").forward(request, response);
		}
	

	}

}
