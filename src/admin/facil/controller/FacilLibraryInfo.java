package admin.facil.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import admin.facil.model.service.FacilService;
import admin.facil.model.vo.Facil;

@WebServlet("/admin/facil/libraryInfo")
public class FacilLibraryInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FacilLibraryInfo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dayInput = request.getParameter("dayInput");
		int fcSeatNo = Integer.parseInt(request.getParameter("fcSeatNo"));
		
		Facil facil = new FacilService().libraryInfo(fcSeatNo, dayInput);
		System.out.println(facil);
		
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy년 MM월 dd일").create();
		gson.toJson(facil, response.getWriter());
		
	}

}
