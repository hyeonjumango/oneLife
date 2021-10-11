package admin.week.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.week.model.service.WeekService;
import admin.week.model.vo.Search;

@WebServlet("/admin/week/list")
public class WeekDayListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page  = 1;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		// 검색결과 저장
		String sType = request.getParameter("sType");
		String sReveal = request.getParameter("sReveal");
		String searchDay = request.getParameter("searchDay");
		String allDay = request.getParameter("allDay");
		String searchName = request.getParameter("searchName");
		String searchValue = request.getParameter("searchValue");
		Search sc = new Search(sType, sReveal, searchDay, allDay, searchName, searchValue);
		
		Map<String, Object> map = new WeekService().weekSelectList(page, sc);
		// System.out.println(wList);
		
		request.setAttribute("pi", map.get("pi"));
		request.setAttribute("wList", map.get("wList"));
		request.getRequestDispatcher("/WEB-INF/views/admin/jsp/week/weekday_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

