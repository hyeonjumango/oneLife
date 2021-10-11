package admin.week.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.week.model.service.WeekService;

@WebServlet("/admin/week/remove")
public class WeekDayRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 선택한 배열값
		String[] weeks = request.getParameterValues("weekCheck");
		
		int result = new WeekService().removeWeek(weeks);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(result);
		
		
		
	}

}
