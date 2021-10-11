package admin.vehicle.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import admin.vehicle.model.service.MemberCarService;
import admin.vehicle.model.vo.InvehicleSearch;
import user.VisitCar.model.vo.PageInfo;

@WebServlet("/admin/in/list")
public class InVehicleListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = 1;
		if (request.getParameter("page")  != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		//검색 조건 추가하기
		
		String dongStr = request.getParameter("dong");
		String hoStr = request.getParameter("ho");
		String carNo = request.getParameter("carNo");
		
		if (carNo != null && (carNo.equals("전체") || carNo.equals(""))) {
			carNo = "all";
		}
		
//		int dong = 0;
//		int ho = 0;
//		if (dongStr != null && !dongStr.equals("all")) {
//			dong = Integer.parseInt(dongStr);
//		}
//		
//		if (hoStr != null && !hoStr.equals("all")) {
//			ho = Integer.parseInt(hoStr);
//		}
		
		
		Map<String, Object> map = new MemberCarService().selectMemberCar(page, new InvehicleSearch(dongStr, hoStr, carNo));

		
		
		/*if (map != null && (dongStr != null || hoStr != null || carNO != null)) {
			response.setContentType("application/json; charset=utf-8");		
			new Gson().toJson(map, response.getWriter());
		} 
		else*/ if (map != null) {
			request.setAttribute("pi", map.get("pi"));
			request.setAttribute("houseHoldCarList", map.get("houseHoldCarList"));
			request.getSession().setAttribute("excelDownList", map.get("excelDownList"));
			request.getRequestDispatcher("/WEB-INF/views/admin/jsp/vehicle/inVehicle_list.jsp").forward(request,
					response);
		} else {
			request.setAttribute("msg", "입주차량 조회에 실패하였습니다.");
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/user/common/errorpage.jsp");
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
