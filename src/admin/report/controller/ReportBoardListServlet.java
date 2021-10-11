package admin.report.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.report.model.service.ReportService;
import admin.report.model.vo.Search;

@WebServlet("/admin/report/bList")
public class ReportBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		String status = request.getParameter("status");
		String reportNum = "";
		String searchName = request.getParameter("searchName");
		String searchValue = request.getParameter("searchValue");
		
		Search sc = new Search(status, reportNum, searchName, searchValue);
		// System.out.println(sc);
		
		Map<String, Object> map = new ReportService().selectReportList(page, sc); 
		
		request.setAttribute("pi", map.get("pi"));
		request.setAttribute("rList", map.get("rList"));
		request.getRequestDispatcher("/WEB-INF/views/admin/jsp/report/reportBoardList.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
