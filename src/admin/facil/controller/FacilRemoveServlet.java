package admin.facil.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.facil.model.service.FacilService;

@WebServlet("/admin/facil/remove")
public class FacilRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] facilCheck = request.getParameterValues("facilCheck");
		
		int result = new FacilService().removeFacil(facilCheck);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(result);
		
		
	}

}
