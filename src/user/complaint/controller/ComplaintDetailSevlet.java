package user.complaint.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.complaint.model.service.complaintService;
import user.complaint.model.vo.complaint;

/**
 * Servlet implementation class ComplaintDetailSevlet
 */
@WebServlet("/complaint/detail")
public class ComplaintDetailSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComplaintDetailSevlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int c_no = Integer.parseInt(request.getParameter("c_no"));
		
		complaint complaint = new complaintService().selectComplaint(c_no);
		
		
		if (complaint != null) {
			request.setAttribute("complaint", complaint);
			request.getRequestDispatcher("/WEB-INF/views/user/jsp/complaint/complaintDetailView.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "아파트민원 상세 페이지를 불러오는데 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/user/common/errorpage.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
