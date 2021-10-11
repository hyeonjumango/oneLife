package user.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.manager.model.vo.Manager;
import user.member.model.vo.Member;
import user.notice.model.service.NoticeService;
import user.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeInsertSelvet
 */
@WebServlet("/notice/insert")
public class NoticeInsertSelvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertSelvet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/user/jsp/notice/noticeinsertSummerView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int mno = ((Manager)request.getSession().getAttribute("loginManager")).getmNo();
		
		Notice n = new Notice(title, content, mno);
		
		int n_no = new NoticeService().insertNotice(n);
		
		if(n_no > 0) {
			request.getSession().setAttribute("n_no", n_no);
			response.sendRedirect(request.getContextPath() + "/notice/finish");
		} else {
			request.setAttribute("msg", "공지사항 등록에 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/user/common/errorpage.jsp").forward(request, response);
			
		}
	}

}
