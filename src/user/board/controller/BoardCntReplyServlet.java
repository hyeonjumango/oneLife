package user.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import user.board.model.service.boardService;
import user.board.model.vo.Board;
import user.board.model.vo.Board_Like;
import user.member.model.vo.Member;

/**
 * Servlet implementation class BoardCntReplyServlet
 */
@WebServlet("/board/cntReply")
public class BoardCntReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardCntReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int b_no = Integer.parseInt(request.getParameter("b_no"));
		
		
		int replyCnt = new boardService().replyCnt(b_no);
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(replyCnt, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
