package admin.vehicle.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.vehicle.model.service.MemberCarService;

/**
 * Servlet implementation class DeleteMemberCarServlet
 */
@WebServlet("/admin/in/delete")
public class DeleteMemberCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMemberCarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mcId = Integer.parseInt(request.getParameter("mcId"));
		
		int result = new MemberCarService().deleteMemberCar(mcId);
		
		if (result > 0) {
			request.getSession().setAttribute("msg", "삭제가 완료되었습니다.");
			response.sendRedirect(request.getContextPath() + "/admin/in/list");
		} else {
			request.setAttribute("msg", "입주차량 삭제에 실패하였습니다.");
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
