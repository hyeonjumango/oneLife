package user.VisitCar.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.VisitCar.model.service.VisitCarService;
import user.VisitCar.model.vo.VisitCar;
import user.member.model.vo.Member;

/**
 * Servlet implementation class VisitCarFixServlet
 */
@WebServlet("/visitCarFix")
public class VisitCarFixServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisitCarFixServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int vid = Integer.parseInt(request.getParameter("vid"));
//		System.out.println(vid);
		
		VisitCar visitCarDetail = new VisitCarService().selectDetail(vid);
		
//		System.out.println(vs);
		
		request.setAttribute("visitCarDetail", visitCarDetail);
		request.getRequestDispatcher("WEB-INF/views/user/jsp/VisitCar/VisitCarFix.jsp").forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String dateString = request.getParameter("date");
		String carNo = request.getParameter("carNo");
		String purpose = request.getParameter("purpose");
		String phone = request.getParameter("phone");
		int vid = Integer.parseInt(request.getParameter("vid"));
		
		
		int result = new VisitCarService().updateVisitCar(dateString, carNo, purpose, phone, vid);
		
		if (result > 0) {
			request.getSession().setAttribute("vid", vid);
			request.getSession().setAttribute("msg", "수정이 완료되었습니다.");
			response.sendRedirect(request.getContextPath() + "/visitCarConfirm");
			
		} else {
//			request.setAttribute("msg", "게시글 등록에 실패하였습니다.");
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/user/common/errorpage.jsp");
			view.forward(request, response);
		}
		
	}

}
