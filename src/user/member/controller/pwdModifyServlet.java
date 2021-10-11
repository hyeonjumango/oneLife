package user.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.member.model.service.MemberService;
import user.member.model.vo.Member;

/**
 * Servlet implementation class pwdModifyServlet
 */
// @WebServlet("/pwdModify")
@WebServlet(name="pwdModifyServlet", urlPatterns="/pwdModify")
public class pwdModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pwdModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view="";
		if(request.getSession().getAttribute("loginUser") == null) {
			request.setAttribute("msg", "올바르지 않은 요청입니다.");
			view="WEB-INF/views/user/common/errorpage.jsp";
		} else {
			// 비밀번호 팝업 창 이동
			view="WEB-INF/views/user/jsp/member/pwdModifyForm.jsp";
		}
		request.getRequestDispatcher(view).forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 넘어온 값
		// 현재 비밀번호
		String userPwd = request.getParameter("userPwd");
		// 수정할 비밀번호
		String newPwd1 = request.getParameter("newPwd1");
		// userNo - > loginUser에서 갖고옴
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getU_NO();
		
		Member updateMember = new MemberService().updatePwd(userNo, userPwd, newPwd1);
		
		// System.out.println("비밀번호 수정 된 객체 : " + updateMember);
		
		// 결과 따른 응답 처리
		if(updateMember != null) {
			// 비밀번호 수정 success로 표시
			request.setAttribute("result", "success");
			// 수정 된 객체 loginUser에 저장
			request.getSession().setAttribute("loginUser", updateMember);
		} else {
			request.setAttribute("result", "fail");
		}
		
		request.getRequestDispatcher("WEB-INF/views/user/jsp/member/pwdModifyForm.jsp").forward(request, response);
	
	}

}
