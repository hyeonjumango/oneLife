package user.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.member.model.service.MemberService;
import user.member.model.vo.Member;

/**
 * Servlet implementation class userJoinServlet
 */
// @WebServlet("/userJoin")
@WebServlet(name = "userJoinServlet", urlPatterns = "/userJoin")
public class userJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public userJoinServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/user/jsp/member/userJoin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 회원 정보 입력 후 회원 가입 버튼을 눌렀을 때 DB에 Insert 처리
		// 인코딩 처리
		// request.setCharacterEncoding("UTF-8");

		// request에 담긴 값 꺼내서 변수에 저장
		String userId = request.getParameter("userId");
		String name = request.getParameter("name");
		String nickName = request.getParameter("nickName");
		String userPwd = request.getParameter("userPwd");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");

		int rno = new MemberService().checkR(name, email);

		if (rno > 0) {
			int checkJoin = new MemberService().checkJoin(rno);
			if (checkJoin > 0) {
				request.getSession().setAttribute("msg", "이미 회원가입이 완료된 정보입니다.");
				response.sendRedirect(request.getContextPath());
			} else {
				// 가입 정보를 담은 Member 객체 생성
				Member mem = new Member(userId, nickName, userPwd, phone, rno);
				// 3. 비즈니스 로직을 수행할 서비스 메소드로 Member 객체 전달 후 결과 값 리턴 받기
				new MemberService().insertMember(mem);
				new MemberService().changeStatus(rno);
				request.getRequestDispatcher("WEB-INF/views/user/jsp/member/userJoinSuccess.jsp").forward(request, response);
			}
		} else {
			request.getSession().setAttribute("msg", "아파트 주민이 아닙니다.");
			response.sendRedirect(request.getContextPath());
		}

	}

}
