package user.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import user.board.model.service.boardService;
import user.board.model.vo.Board_Comment;


/**
 * Servlet implementation class BoardDeleteReplyServlet
 */
@WebServlet("/board/deleteReply")
public class BoardDeleteReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bc_no = Integer.parseInt(request.getParameter("bc_no"));
		int b_no = Integer.parseInt(request.getParameter("b_no"));
		
		Board_Comment r = new Board_Comment(); 
		r.setBc_no(bc_no);
		r.setB_no(b_no);
		 
		
		List<Board_Comment> replyList = new boardService().deleteReply(r);
		
		// GSON 라이브러리 추가 후 replyList 응답
		// GSON 사용 시 날짜 값 Date 포맷에 대한 컨트롤 가능(GsonBuilder 객체가 가진 기능)
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy.MM.dd HH:mm").create();
		gson.toJson(replyList, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
