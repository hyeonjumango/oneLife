package user.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.board.model.service.boardService;
import user.board.model.vo.Board;
import user.board.model.vo.Board_Like;
import user.member.model.vo.Member;


/**
 * Servlet implementation class BoardDetailServlet
 */
@WebServlet("/board/detail")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int b_no = Integer.parseInt(request.getParameter("b_no"));
		int u_no = ((Member)request.getSession().getAttribute("loginUser")).getU_NO();
		boardService bs = new boardService();
		
		// 요청으로 부터 쿠키 정보 읽어옴
		Cookie[] cookies = request.getCookies();
		
		// b_count라는 쿠키 값 담을 변수
		String b_count = "";
		
		// 쿠키가 잘 넘어왔다면
		if (cookies != null && cookies.length > 0) {
			// 쿠키 값 배열을 반복하며 탐색
			for (Cookie c : cookies) {
				// 읽은 게시물 정보를 저장해두는 클립의 이름 b_count가 있는지 확인
				if (c.getName().equals("b_count")) {
					b_count = c.getValue();
				}
			}
		}
		
		// 처음 읽는 게시글인 경우 
		if (b_count.indexOf("|"+ b_no + "|") == -1) {
			// 해당 게시글 번호를 붙여서
			Cookie newBcount = new Cookie("b_count", b_count + "|" + b_no + "|");
			// 응답에 쿠키를 담음
			response.addCookie(newBcount);
			// 조회수 증가 로직 실행
			int result = bs.increaseCount(b_no);
			
			
		} 
		// 게시글 조회
		Board b = bs.selectBoard(b_no);
		
		int result = bs.selectUsercnt(b_no, u_no);
		
		if(b != null) {
			request.setAttribute("board", b);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/WEB-INF/views/user/jsp/board/boardDetailView.jsp").forward(request, response);
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
