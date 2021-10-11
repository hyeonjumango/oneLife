package admin.vehicle.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.Member;

import admin.manager.model.vo.Manager;
import user.VisitCar.model.service.VisitCarService;

/**
 * Servlet implementation class VisitCarOnsiteServlet
 */
@WebServlet("/admin/visit/register")
public class VisitCarOnsiteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisitCarOnsiteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Member m = (Member)request.getSession().getAttribute("loginUser");
//		String rname = m.getName();
		String dateString = request.getParameter("date");
		String carNo = request.getParameter("carNo");
		String purpose = request.getParameter("purpose");
		int dong = Integer.parseInt(request.getParameter("dong"));
		int ho = Integer.parseInt(request.getParameter("ho"));
		String phone = request.getParameter("phone");
		
		int mno = ((Manager)request.getSession().getAttribute("loginManager")).getmNo(); //관리자 번호
		int vid = new VisitCarService().adminInsertVisitCar(mno, dateString, carNo, purpose, dong, ho, phone);
		
		if (vid > 0) {
			request.getSession().setAttribute("vid", vid);
			request.getSession().setAttribute("msg", "등록이 완료되었습니다.");
			response.sendRedirect(request.getContextPath() + "/admin/visit/list");
			
		} else {
//			request.setAttribute("msg", "게시글 등록에 실패하였습니다.");
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/user/common/errorpage.jsp");
			view.forward(request, response);
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
