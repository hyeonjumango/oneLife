package admin.facil.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.facil.model.service.FacilService;
import admin.facil.model.vo.Search;

@WebServlet("/admin/facil/list")
public class FacilListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		// 검색 조건 가져오기
		String facilName = request.getParameter("facilName");
		String facilType = request.getParameter("facilType");
		String facilStatus = request.getParameter("facilStatus");
		String facilDay = request.getParameter("facilDay");
		String allDay = request.getParameter("allDay");
		String searchName = request.getParameter("searchName");
		String searchValue = request.getParameter("searchValue");
		
		Search sc = new Search(facilName, facilType, facilStatus, facilDay, allDay, searchName, searchValue);
		// System.out.println(sc);
		Map<String, Object> map = new FacilService().selectListFacil(page, sc);
		
		request.setAttribute("pi", map.get("pi"));
		request.setAttribute("fList", map.get("fList"));
		
		request.getRequestDispatcher("/WEB-INF/views/admin/jsp/facil/facil_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
