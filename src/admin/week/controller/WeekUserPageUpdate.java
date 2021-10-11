package admin.week.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import admin.week.model.service.WeekService;
import admin.week.model.vo.Week;

/**
 * Servlet implementation class WeekUserPageUpdate
 */
@WebServlet("/admin/week/upUpdate")
public class WeekUserPageUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WeekUserPageUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dateStr = request.getParameter("date1");
		String dateStr2 = request.getParameter("date2");
		String title = request.getParameter("title");
		String type = request.getParameter("type");
		int nno = Integer.parseInt(request.getParameter("nno"));
		
		int deleteResult = new WeekService().deleteWeekUP(nno);
		if (deleteResult < 1) {
			response.setContentType("application/json; charset=utf-8"); new
			 Gson().toJson(deleteResult, response.getWriter());
		} else {
			Date date = Date.valueOf(dateStr);
			Date date2 = null;
			if (!dateStr2.equals("")) {
				 date2 = Date.valueOf(dateStr2);
			} 
			Week week = new Week();
			week.setScCateCode(type);
			week.setScOpenDate(date);
			week.setScTitle(title);
			week.setNno(nno);
			if (!dateStr2.equals("")) {
				week.setScEndDate(date2);
			}
			
			 int result = new WeekService().insertWeekUP(week);
			 
			 response.setContentType("application/json; charset=utf-8"); new
			 Gson().toJson(result, response.getWriter());
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
