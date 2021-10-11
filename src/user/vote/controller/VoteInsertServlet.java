package user.vote.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.manager.model.vo.Manager;

import user.member.model.vo.Member;
import user.vote.model.voteService;
import user.vote.vo.Vote;

/**
 * Servlet implementation class VoteInsertServlet
 */
@WebServlet("/vote/insert")
public class VoteInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoteInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/user/jsp/vote/voteinsertView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String choice1 = request.getParameter("choice1");
		String choice2 = request.getParameter("choice2");
		String choice3 = request.getParameter("choice3");
		String choice4 = request.getParameter("choice4");
		String choice5 = request.getParameter("choice5");
		String radiobtn = request.getParameter("check_choice");
		
		String dateIn = request.getParameter("dateIn");

		int mno = ((Manager)request.getSession().getAttribute("loginManager")).getmNo();
		
		Vote v = new Vote(title, content, dateIn, mno, radiobtn);
		
		int v_no = new voteService().insertVote(v);
		
		Vote vv = new Vote(v_no, choice1, choice2, choice3, choice4, choice5);
		
		int result = new voteService().insertVoteExample(vv);
		
		if (v_no > 0 && result > 0) {
			request.getSession().setAttribute("v_no", v_no);
			response.sendRedirect(request.getContextPath() + "/vote/finish");
			
		} else {
			request.setAttribute("msg", "게시글 등록에 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/user/common/errorpage.jsp").forward(request, response);
		}
	}

}
