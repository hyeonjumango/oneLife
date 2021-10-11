package admin.vehicle.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import user.VisitCar.model.service.VisitCarService;

/**
 * Servlet implementation class VisitVehicleCheckServlet
 */
@WebServlet("/admin/visit/check")
public class VisitVehicleCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisitVehicleCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int vid = Integer.parseInt(request.getParameter("vid"));
		
		int result = new VisitCarService().checkVisitCar(vid);
		if (result > 0) {
			request.getSession().setAttribute("msg", "확인이 완료되었습니다.");
			response.sendRedirect(request.getContextPath() + "/admin/visit/list");
			
		} else {
			request.setAttribute("msg", "방문차량 확인에 실패하였습니다.");
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
