package user.vote.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.board.model.service.boardService;
import user.vote.model.voteService;

/**
 * Servlet implementation class VoteDeleteServlet
 */
@WebServlet("/vote/delete")
public class VoteDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoteDeleteServlet() {
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
		int v_no = Integer.parseInt(request.getParameter("v_no"));
		
		int result = new voteService().deleteVote(v_no);
		
		if (result > 0) {
			request.getSession().setAttribute("msg", "투표 게시글이 삭제 되었습니다.");
			response.sendRedirect(request.getContextPath() + "/vote/list");
		} else {
			request.setAttribute("msg", "투표 게시글 삭제 실패했습니다.");
			request.getRequestDispatcher("/WEB-INF/views/user/common/errorpage.jsp").forward(request, response);
		}
	}
}
