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

@WebServlet("/admin/facil/multiInfo")
public class MulticourtInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MulticourtInfoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int fcNo = Integer.parseInt(request.getParameter("fcNo"));
		String dayInput = request.getParameter("dayInput");
		// String time = request.getParameter("time");
		
		Facil facil = new FacilService().multiInfo(fcNo, dayInput);
		
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy년 MM월 dd일").create();
		gson.toJson(facil, response.getWriter());
		
		
	}

}
