package user.vote.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.member.model.vo.Member;
import user.vote.model.voteService;
import user.vote.vo.Vote;
import user.vote.vo.Vote_choice;

/**
 * Servlet implementation class VoteVoteFinishServlet
 */
@WebServlet("/vote/votefinish")
public class VoteVoteFinishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoteVoteFinishServlet() {
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
		int ve_no = Integer.parseInt(request.getParameter("ve_no"));
		String v_choice = request.getParameter("v_choice");
		int u_no = ((Member)request.getSession().getAttribute("loginUser")).getU_NO();
		String checkbox_val1 = null;
		String checkbox_val2 = null;
		String checkbox_val3 = null;
		String checkbox_val4 = null;
		String checkbox_val5 = null;
		
		
		int result = 0;
		if (v_choice.equals("checkbox")) {
			checkbox_val1 = request.getParameter("checkbox_val1");
			checkbox_val2 = request.getParameter("checkbox_val2");
			checkbox_val3 = request.getParameter("checkbox_val3");
			checkbox_val4 = request.getParameter("checkbox_val4");
			checkbox_val5 = request.getParameter("checkbox_val5");
			Vote_choice vc = new Vote_choice(ve_no, v_no, checkbox_val1, checkbox_val2, checkbox_val3, checkbox_val4, checkbox_val5, u_no);
			result = new voteService().insertVoteval(vc);
		} else {
			String radio_val = request.getParameter("radio_val");
			Vote_choice vc = new Vote_choice(ve_no, v_no, radio_val, u_no);
			result = new voteService().insertVoteval(vc);
		}
	
		
		if (result > 0) {

			request.setAttribute("msg", "투표가 완료되었습니다.");
			request.getRequestDispatcher("/WEB-INF/views/user/jsp/vote/voteUserfinishpage.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "투표 등록에 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/user/common/errorpage.jsp").forward(request, response);
		}
		
	}

}
