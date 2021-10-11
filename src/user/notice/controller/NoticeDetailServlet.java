package user.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.week.model.service.WeekService;
import admin.week.model.vo.Week;
import user.notice.model.service.NoticeService;
import user.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeDetailServlet
 */
@WebServlet("/notice/detail")
public class NoticeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int n_no = Integer.parseInt(request.getParameter("n_no"));
		NoticeService ns = new NoticeService();
		
		// 요청으로 부터 쿠키 정보 읽어옴
		Cookie[] cookies = request.getCookies();
		
		// n_count라는 쿠키 값 담을 변수
		String n_count = "";
		
		// 쿠키가 잘 넘어왔다면
		if (cookies != null && cookies.length > 0) {
			// 쿠키 값 배열을 반복하며 탐색
			for (Cookie c : cookies) {
				// 읽은 게시물 정보를 저장해두는 클립의 이름 n_count가 있는지 확인
				if (c.getName().equals("n_count")) {
					n_count = c.getValue();
				}
			}
		}
		
		// 처음 읽는 게시글인 경우 
		if (n_count.indexOf("|"+ n_no + "|") == -1) {
			// 해당 게시글 번호를 붙여서
			Cookie newBcount = new Cookie("n_count", n_count + "|" + n_no + "|");
			// 응답에 쿠키를 담음
			response.addCookie(newBcount);
			// 조회수 증가 로직 실행
			int result = ns.increaseCount(n_no);
			
		} 
		// 게시글 조회
		Notice n = ns.selectNotice(n_no);
		Week week = new WeekService().checkNno(n_no);
		
		if(n != null) {
			request.setAttribute("check", week);
			request.setAttribute("notice", n);
			request.getRequestDispatcher("/WEB-INF/views/user/jsp/notice/noticeDetailView.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "게시글 상세 조회에 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/user/common/errorpage.jsp").forward(request, response);
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
