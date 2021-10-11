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
 * Servlet implementation class BoardNoneheartServlet
 */
@WebServlet("/board/noneheart")
public class BoardNoneheartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardNoneheartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int b_no = Integer.parseInt(request.getParameter("b_no"));
		int u_no = ((Member)request.getSession().getAttribute("loginUser")).getU_NO();
		
		
		Board_Like bl = new Board_Like();
		bl.setB_no(b_no);
		bl.setU_no(u_no);
		
		
		Board b = new boardService().deleteHeart(bl);
	
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(b, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
