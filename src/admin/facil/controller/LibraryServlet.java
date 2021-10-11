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

@WebServlet("/admin/facil/library")
public class LibraryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 날짜 가져오기
		String day = request.getParameter("day");
		
		// 독서실 예약 현황
		List<Facil> fList = new FacilService().librarySelectList(day);
		
		request.setAttribute("fList", fList);
		// System.out.println(fList);
		request.getRequestDispatcher("/WEB-INF/views/admin/jsp/facil/library.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
