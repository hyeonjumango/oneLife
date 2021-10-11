package user.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.board.model.service.boardService;
import user.member.model.vo.Member;

/**
 * Servlet implementation class BoardReportServlet
 */
@WebServlet("/board/report")
public class BoardReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReportServlet() {
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
		String bno = request.getParameter("bno");
		String bcno = request.getParameter("bcno");
		
		Member m = (Member)request.getSession().getAttribute("loginUser");
		int uno = m.getU_NO();
		
		int check = new boardService().chekcUno(bno, bcno, uno);
			
		if (check > 0) {
			request.setAttribute("result", "already");
			request.getRequestDispatcher("/WEB-INF/views/user/jsp/board/popup.jsp").forward(request, response);
		
		} else {
			int result = new boardService().report(bno, bcno, uno);
			if (result > 0) {
				request.setAttribute("result", "success");
				request.getRequestDispatcher("/WEB-INF/views/user/jsp/board/popup.jsp").forward(request, response);
			} else {
				request.setAttribute("result", "fail");
				request.getRequestDispatcher("/WEB-INF/views/user/jsp/board/popup.jsp").forward(request, response);
			
		      }
		}
		
		
	}

}
