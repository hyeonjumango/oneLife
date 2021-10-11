package user.VisitCar.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.VisitCar.model.service.VisitCarService;
import user.member.model.vo.Member;

/**
 * Servlet implementation class VisitCarRegisterServlet
 */
@WebServlet("/visitCarRegister")
public class VisitCarRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisitCarRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member m = (Member)request.getSession().getAttribute("loginUser");
		
		if (m == null) {
			request.getSession().setAttribute("msg", "입주민 전용 공간입니다.");
			response.sendRedirect(request.getContextPath()+"/main");
		}  else {
			request.getRequestDispatcher("WEB-INF/views/user/jsp/VisitCar/VisitCarRegister.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dateString = request.getParameter("date");
		String carNo = request.getParameter("carNo");
		String purpose = request.getParameter("purpose");
		String phone = request.getParameter("phone");
		
	
		
//		Date date = null;
//		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
//		try {
//			date = transFormat.parse(dateString);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		
		Member m = (Member)request.getSession().getAttribute("loginUser");
		int userNo = m.getU_NO();
		
		int vid = new VisitCarService().insertVisitCar(dateString, carNo, purpose, phone, userNo);
		
		if (vid > 0) {
			request.getSession().setAttribute("vid", vid);
			request.getSession().setAttribute("msg", "등록이 완료되었습니다.");
			response.sendRedirect(request.getContextPath() + "/visitCarConfirm");
			
		} else {
//			request.setAttribute("msg", "게시글 등록에 실패하였습니다.");
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/user/common/errorpage.jsp");
			view.forward(request, response);
		}
		
		
	}

}
