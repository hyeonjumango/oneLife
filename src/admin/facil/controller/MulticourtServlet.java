package admin.facil.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.facil.model.service.FacilService;
import admin.facil.model.vo.Facil;

@WebServlet("/admin/facil/multicourt")
public class MulticourtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String day = request.getParameter("day");
		// String time = request.getParameter("time");
		
		List<Facil> fList = new FacilService().multicourtSelectList(day);
		// System.out.println(fList);
		
		request.setAttribute("fList", fList);
		request.getRequestDispatcher("/WEB-INF/views/admin/jsp/facil/multicourt.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
