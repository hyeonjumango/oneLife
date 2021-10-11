package admin.manager.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.manager.model.service.ManagerService;
import admin.manager.model.vo.Search;


@WebServlet("/admin/manager/list")
public class ManagerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int page = 1;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		// 검색조건, 결과 담기 
		String search = request.getParameter("managerListSearch");
		String value = request.getParameter("managerListValue");
		// System.out.println("search : " + search);
		// System.out.println("value : " + value);
		Search sc = new Search(search, value);
		// System.out.println(sc);
		
		Map<String, Object> map = new ManagerService().managerList(page, sc);
		
		request.setAttribute("pi", map.get("pi"));
		request.setAttribute("mList", map.get("mList"));
		request.getRequestDispatcher("/WEB-INF/views/admin/jsp/manager/manager_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
