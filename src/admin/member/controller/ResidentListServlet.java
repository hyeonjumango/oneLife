package admin.member.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.member.model.service.ResidentService;
import admin.member.model.vo.Search;

// 입주자명부 목록
@WebServlet("/admin/member/list")
public class ResidentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		Search sc = null;
		// 검색결과 받기
		if(request.getParameter("rDong") != null) {
			String rDong = request.getParameter("rDong");
			String rHo = request.getParameter("rHo");
			String rType = request.getParameter("rType");
			String rStatus = request.getParameter("rStatus");
			String searchName = request.getParameter("searchName");
			String searchValue = request.getParameter("searchValue");
			sc = new Search(rDong, rHo, rType, rStatus, searchName, searchValue);
		}
		
		// System.out.println(sc);
		
		Map<String, Object> map = new ResidentService().residentList(page, sc);
		
		request.setAttribute("pi", map.get("pi"));
		request.setAttribute("rList", map.get("rList"));
		request.getRequestDispatcher("/WEB-INF/views/admin/jsp/member/resident_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
