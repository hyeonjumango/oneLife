package admin.report.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.report.model.service.ReportService;

@WebServlet("/admin/report/remove")
public class ReportBoardRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReportBoardRemoveServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] reportChecks = request.getParameterValues("reportChecks");
		
		int result = new ReportService().removeReport(reportChecks);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(result);
	}

}
