package admin.manager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import admin.manager.model.service.ManagerService;
import admin.manager.model.vo.Manager;

@WebServlet("/admin/manager/classChange")
public class ManagerClassChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int mNo = Integer.parseInt(request.getParameter("mNo"));
		// System.out.println("mNo : " + mNo);
		
		Manager manager = new ManagerService().classManager(mNo);
		// System.out.println(manager);
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(manager, response.getWriter());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 정보가져오기
		int page = 1;
		if(request.getParameter("page") != null && request.getParameter("page").length() > 0) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int mNo = Integer.parseInt(request.getParameter("mNo"));
		String mJobcode = request.getParameter("mClass");
		
		int result = new ManagerService().classChangeManager(mNo, mJobcode);
		
		if(result > 0) {// 계급변경에 성공하였을시
			request.getSession().setAttribute("msgHead", "관리자 계급변경");
			request.getSession().setAttribute("msgBody", mNo + "번 회원의 계급 변경을 성공 하였습니다.");
		}else {
			request.getSession().setAttribute("msgHead", "관리자 계급변경");
			request.getSession().setAttribute("msgBody", mNo + "번 회원의 계급 변경을 실패 하였습니다.");
		}
		
		response.sendRedirect(request.getContextPath() + "/admin/manager/list");
		
		
		
		
		
		
		
		
	}

}
