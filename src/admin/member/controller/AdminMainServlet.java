package admin.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.facil.model.vo.Facil;
import admin.member.model.service.MainService;
import admin.week.model.vo.Week;
import user.VisitCar.model.vo.VisitCar;
import user.complaint.model.vo.complaint;
import user.notice.model.vo.Notice;

@WebServlet("/admin/")
public class AdminMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 금일 주요일정
		List<Week> weekList = new MainService().weekTodayList();
		
		// 공지사항 목록
		List<Notice> noticeList = new MainService().noticeList();
		
		// 미답변 건의현황
		List<complaint> comList = new MainService().complaintList();
		
		// 금일 부대시설 예약현황
		List<Facil> facilList = new MainService().facilList();
		
		// 금일 방문차량 예약현황
		List<VisitCar> visitcarList = new MainService().visitCarList();
		
		request.setAttribute("weekList", weekList);
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("comList", comList);
		request.setAttribute("facilList", facilList);
		request.setAttribute("visitcarList", visitcarList);
		request.getRequestDispatcher("/WEB-INF/views/admin/main.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
