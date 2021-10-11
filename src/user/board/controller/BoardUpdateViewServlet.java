package user.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.board.model.service.boardService;
import user.board.model.vo.Board;
import user.complaint.model.service.complaintService;
import user.complaint.model.vo.complaint;

/**
 * Servlet implementation class BoardUpdateViewServlet
 */
@WebServlet("/board/updateView")
public class BoardUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int b_no = Integer.parseInt(request.getParameter("b_no"));
		
		Board board = new boardService().selectBoard(b_no);
		
		if (board != null) {
			request.setAttribute("board", board);
			request.getRequestDispatcher("/WEB-INF/views/user/jsp/board/boardupdateSummerView.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "도란도란 수정 페이지 이동에 실패했습니다.");
			request.getRequestDispatcher("/WEB-INF/views/user/common/errorpage.jsp").forward(request, response);
		}
	
	}

}
