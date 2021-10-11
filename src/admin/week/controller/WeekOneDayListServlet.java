package admin.week.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import admin.week.model.service.WeekService;
import admin.week.model.vo.Week;

/**
 * Servlet implementation class WeekOneDayListServlet
 */
@WebServlet("/admin/week/oneDayList")
public class WeekOneDayListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WeekOneDayListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		
		List<Week> wList = new WeekService().oneDayList(year, month, day);
		
		Calendar cal = Calendar.getInstance();
		int intYear = Integer.parseInt(year);
		int intMonth = Integer.parseInt(month);
		int intDay = Integer.parseInt(day);
		cal.set(intYear, intMonth-1, intDay);
		
		/*
		 * SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd"); String pickDate =
		 * ft.format(cal.getTime());
		 */
		
		Map<String, Object> map = new HashMap<>();
		map.put("pickDate", cal.getTime());
		map.put("wList", wList);
		
		
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy년 MM월 dd일 E요일").create();
		gson.toJson(map, response.getWriter());
		
	}

}
