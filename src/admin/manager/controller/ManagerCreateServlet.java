package admin.manager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.manager.model.service.ManagerService;
import admin.manager.model.vo.Manager;

@WebServlet(name="managerCreate", urlPatterns="/admin/manager/create")
public class ManagerCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ManagerCreateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String mId = request.getParameter("mId");
		String mPwd = request.getParameter("mPwd1");
		String mName = request.getParameter("mName");
		String mPhone = request.getParameter("mPhone");
		
		Manager manager = new Manager(mId, mPwd, mName, mPhone);
		
		
		int result = new ManagerService().createManager(manager);
		
		if(result > 0) {
			request.getSession().setAttribute("msg", "회원가입에 성공하였습니다.");
			response.sendRedirect(request.getContextPath() + "/admin/manager/list");
		}else {
			request.getSession().setAttribute("msg", "회원가입에 실패하였습니다. 다시 진행해주세요!");
			response.sendRedirect(request.getContextPath() + "/admin/manager/list");
		}
		
		
		
	}

}
