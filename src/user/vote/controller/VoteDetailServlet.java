package user.vote.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.board.model.service.boardService;
import user.board.model.vo.Board;
import user.member.model.vo.Member;
import user.vote.model.voteService;
import user.vote.vo.Vote;
import user.vote.vo.Vote_choice;

/**
 * Servlet implementation class VoteDetailServlet
 */
@WebServlet("/vote/detail")
public class VoteDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoteDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int v_no = Integer.parseInt(request.getParameter("v_no"));
		
		
		voteService vs = new voteService();
		
		// 요청으로 부터 쿠키 정보 읽어옴
		Cookie[] cookies = request.getCookies();
		
		// v_count라는 쿠키 값 담을 변수
		String v_count = "";
		
		// 쿠키가 잘 넘어왔다면
		if (cookies != null && cookies.length > 0) {
			// 쿠키 값 배열을 반복하며 탐색
			for (Cookie c : cookies) {
				// 읽은 게시물 정보를 저장해두는 클립의 이름 v_count가 있는지 확인
				if (c.getName().equals("v_count")) {
					v_count = c.getValue();
				}
			}
		}
		
		// 처음 읽는 게시글인 경우 
		if (v_count.indexOf("|"+ v_no + "|") == -1) {
			// 해당 게시글 번호를 붙여서
			Cookie newBcount = new Cookie("v_count", v_count + "|" + v_no + "|");
			// 응답에 쿠키를 담음
			response.addCookie(newBcount);
			// 조회수 증가 로직 실행
			int result = vs.increaseCount(v_no);
			
			
		} 
		// 게시글 조회
		Vote v = vs.selectVote(v_no);
		
		Vote_choice vc = vs.selectVal(v_no);
	
		
		if(v != null) {
			request.setAttribute("vote", v);
			request.setAttribute("voteval", vc);
			request.getRequestDispatcher("/WEB-INF/views/user/jsp/vote/voteDetailView.jsp").forward(request, response);
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
