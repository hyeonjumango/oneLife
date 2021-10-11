package user.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.member.model.service.MemberService;
import user.member.model.vo.Member;

/**
 * Servlet implementation class userFindId
 */
@WebServlet("/findIdPwd")
public class findIdPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public findIdPwdServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/user/jsp/member/findIdPwd.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 한글 값 인코딩
		request.setCharacterEncoding("UTF-8");

		// 2. request에 담긴 값 꺼내서 변수에 저장
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String userId = request.getParameter("userId");

		Member findUserId = new MemberService().findUserId(name, email);

		Member findUserPwd = new MemberService().findUserPwd(userId, name, email);

		// 유저 Id 넘어오는지 확인
		// System.out.println(findUserId);

		// 유저 Pwd 넘어오는지 확인
		// System.out.println(findUserPwd);

		if (findUserId != null && findUserPwd == null) {
			response.sendRedirect(request.getContextPath() + "/fIdSuccess");

		} else if (findUserPwd != null) {
			response.sendRedirect(request.getContextPath() + "/fPwdSuccess");

		} else {
			// userId && 유저 userPwd가 넘어오지 않았을 때 alert
			// request.setAttribute("msg", "해당 정보를 가진 주민은 존재하지 않습니다 다시 입력해주세요.");
			// RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/user/common/errorpage.jsp");
			// view.forward(request, response);
			request.getSession().setAttribute("msg", "해당 정보를 가진 회원은 존재하지 않습니다 다시 입력해주세요.");
			response.sendRedirect(request.getContextPath()+"/findIdPwd");

		}

		// mail server 설정
		String host = "smtp.naver.com";
		String user = "onelife4st@naver.com"; // 자신의 네이버 계정
		String password = "onelife1q@W";// 자신의 네이버 패스워드

		// 메일 받을 주소
		String to_email = request.getParameter("email");

		// SMTP 서버 정보를 설정한다.
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 465);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// email 전송
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(user, "OneLife"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));

			if (findUserId != null && findUserPwd == null) {
				// 메일 제목
				msg.setSubject("OneLife 아이디 찾기 메일입니다.");
				// 메일 내용
				msg.setText("회원님의 아이디는  : " + findUserId.getU_ID());

				Transport.send(msg);
				System.out.println("아이디 전송");

			} else if (findUserPwd != null) {
				// 메일 제목
				msg.setSubject("OneLife 비밀번호 찾기 메일입니다.");
				// 메일 내용
				msg.setText("회원님의 비밀번호는  : " + findUserPwd.getU_PW());

				Transport.send(msg);
				System.out.println("비밀번호 전송");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
