package admin.vehicle.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.vehicle.model.vo.VisitCarSearch;
import user.VisitCar.model.service.VisitCarService;

@WebServlet("/admin/visit/list")
public class VisitVehicleListServlet extends HttpServlet {
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
		String dateStr = request.getParameter("date");
		String status = request.getParameter("status");
		
		// System.out.println("dong :" + dongStr);
		// System.out.println("ho : " + hoStr);
		// System.out.println(carNo);
		// System.out.println(dateStr);
		// System.out.println(status);
		
		if (dateStr != null && (dateStr.equals("") || dateStr.equals("전체"))) {
			dateStr ="all";
		}
		
		if (carNo != null && (carNo.equals("") || carNo.equals("전체"))) {
			carNo = "all";
		}
		
		Map<String, Object> map = new VisitCarService().adminSelectList(page, new VisitCarSearch(dongStr, hoStr, carNo, dateStr, status));
		// 응답 페이지 구성 시 사용할 데이터 설정
		request.setAttribute("pi", map.get("pi"));
		request.setAttribute("visitCarList", map.get("visitCarList"));

		request.getRequestDispatcher("/WEB-INF/views/admin/jsp/vehicle/visitVehicle_list.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
