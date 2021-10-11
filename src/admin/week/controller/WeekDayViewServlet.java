package admin.week.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.week.model.service.WeekService;
import admin.week.model.vo.Week;

@WebServlet("/admin/week/view")
public class WeekDayViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int scNo = -1;
		if(request.getParameter("scNo") != null) {
			scNo = Integer.parseInt(request.getParameter("scNo"));
			Week weekOne = new WeekService().selectOneList(scNo);
			request.setAttribute("weekOne", weekOne);
		}
		
		request.getRequestDispatcher("/WEB-INF/views/admin/jsp/week/weekday_view.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int scNo = -1;
		String type = request.getParameter("type");
		String scType = request.getParameter("scType");
		char scStatus = request.getParameter("sc_status").charAt(0);
		String searchDay = request.getParameter("searchDay");
		String scTitle = request.getParameter("scTitle");
		String scContent = request.getParameter("scContent");
		int result = 0;
		
		Date sDate = Date.valueOf(searchDay);
		Week week = new Week();
		week.setScStatus(scStatus);
		week.setScCateCode(scType);
		week.setScOpenDate(sDate);
		week.setScTitle(scTitle);
		week.setScContent(scContent);
		 
		// 게시글 추가일시
		if(type.equals("insert")) {
			
			result = new WeekService().insertWeek(week);
			
		}else if(type.equals("modify")){// 게시글 수정일시
			scNo = Integer.parseInt(request.getParameter("scNo"));
			week.setScNo(scNo);
			
			result = new WeekService().updateWeek(week);
		}
		
		if(result > 0) {
			request.getSession().setAttribute("msgHead", "주요일정");
			request.getSession().setAttribute("msgBody", "주요일정 추가/수정 이 완료되었습니다.");
			response.sendRedirect(request.getContextPath() + "/admin/week/list?allDay=on");
		}else {
			request.getSession().setAttribute("msgHead", "주요일정");
			request.getSession().setAttribute("msgBody", "주요일정 추가/수정 이 실패하였습니다.");
			response.sendRedirect(request.getContextPath() + "/admin/week/list?allDay=on");
		}
		
		
		
	}

}
