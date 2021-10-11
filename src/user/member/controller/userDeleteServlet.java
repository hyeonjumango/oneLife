package user.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.member.model.service.MemberService;
import user.member.model.vo.Member;

/**
 * Servlet implementation class userDeleteServlet
 */
@WebServlet("/userDelete")
public class userDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member loginUser = (Member)request.getSession().getAttribute("loginUser");
		
		if(loginUser == null) {
			// 로그인 하지 않은 상태에서 userDelete 요청하면 안되므로
			// "올바르지 않은 요청입니다"라는 메세지
			request.setAttribute("msg", "올바르지 않은 요청입니다.");
			request.getRequestDispatcher("WEB-INF/views/user/common/errorpage.jsp").forward(request, response);
			return;
		}
		
		int userNo = loginUser.getU_NO();
		
		// userNo 기준으로 로그인 한 유저 삭제
		int result = new MemberService().deleteMember(userNo);
		
		if(result > 0) {
			// 회원 탈퇴 성공 시
			HttpSession session = request.getSession();
			// 로그인 세션 정보 삭제
			session.removeAttribute("loginUser");
			// msg 설정
			session.setAttribute("msg", "회원 탈퇴가 완료 되었습니다.");
			// 회원탈퇴완료 페이지로 이동
			request.getRequestDispatcher("WEB-INF/views/user/jsp/member/userDeleteSuccess.jsp").forward(request, response);
		} else {
			// 탈퇴에 실패 한 경우
			request.setAttribute("msg", "회원 탈퇴에 실패했습니다.");
			request.getRequestDispatcher("WEB-INF/views/user/common/errorpage.jsp").forward(request, response);
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
