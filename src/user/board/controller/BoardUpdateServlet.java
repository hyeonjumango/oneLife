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
 * Servlet implementation class BoardUpdateServlet
 */
@WebServlet("/board/update")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int b_no = Integer.parseInt(request.getParameter("b_no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Board b = new Board(b_no, title, content);
		
		int result = new boardService().updateBoard(b);
		
		if (result > 0) {
			request.getSession().setAttribute("b_no", b_no);
			response.sendRedirect(request.getContextPath() + "/board/updatefinish");
		} else {
			request.setAttribute("msg", "도란도란 글 수정에 실패했습니다.");
			request.getRequestDispatcher("/WEB-INF/views/user/common/errorpage.jsp").forward(request, response);
		}
	
	}

}
