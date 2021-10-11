package user.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.manager.model.service.ManagerService;
import admin.manager.model.vo.Manager;
import user.member.model.service.MemberService;
import user.member.model.vo.Member;

// @WebServlet("/login")
@WebServlet(name="loginServlet", urlPatterns="/login")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public loginServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		Member loginUser = new MemberService().loginMember(userId, userPwd);
		// System.out.println(loginUser);

		Manager loginManager = new ManagerService().ManagerLogin(userId, userPwd);
		// System.out.println(loginManager);
       
		if(loginUser != null) {
	         HttpSession session = request.getSession();
	         
	         session.setAttribute("loginUser", loginUser);
	         session.setAttribute("loginNickName", loginUser.getU_NICKNAME());
	         response.sendRedirect(request.getContextPath()+"/main");
	      

	      } else if(loginManager != null && (loginManager.getmJobcode().equals("M_CODE1") || loginManager.getmJobcode().equals("M_CODE2"))) {
	    	  // 관리자로그인
	    	  HttpSession session = request.getSession();
	    	  
		      session.setAttribute("loginManager", loginManager);
		      response.sendRedirect(request.getContextPath()+"/main");
	    	  
	      } else {
	    	  request.getSession().setAttribute("msg", "로그인에 실패하였습니다. 다시 로그인 해주세요.");
			  response.sendRedirect(request.getContextPath());
	      }

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
