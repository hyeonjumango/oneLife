package admin.manager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.manager.model.service.ManagerService;

@WebServlet("/admin/idCheck")
public class ManagerIdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String mId = request.getParameter("mId");
		// System.out.println("mId : " + mId);
		
		int result = new ManagerService().idCheck(mId);
		
		// result 값이 0이면 생성할수있는 계정
		// result 값이 1이면 중복되는계정이 존재하여 생성할수 없음
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(result);
		
	}

}
