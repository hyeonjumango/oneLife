package user.VisitCar.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.VisitCar.model.service.VisitCarService;

/**
 * Servlet implementation class VisitCarDeleteServlet
 */
@WebServlet("/visitCarDelete")
public class VisitCarDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisitCarDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int vid = Integer.parseInt(request.getParameter("vid"));
		
		int result = new VisitCarService().deleteVisitCar(vid);
		
		if (result > 0) {
			request.getSession().setAttribute("msg", "예약 내역이 삭제되었습니다.");
			response.sendRedirect(request.getContextPath() + "/visitCarList");
			
		} else {
//			request.setAttribute("msg", "게시글 등록에 실패하였습니다.");
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/user/common/errorpage.jsp");
			view.forward(request, response);
		}
		
	}

}
