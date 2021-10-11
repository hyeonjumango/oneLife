package user.amenities.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.amenities.model.service.AmentiesService;
import user.member.model.vo.Member;

/**
 * Servlet implementation class mcReservationServlet
 */
@WebServlet("/mcRes")
public class mcReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mcReservationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member member = ((Member)request.getSession().getAttribute("loginUser"));
		int uNo = member.getU_NO();
		// 이미 예약된 날짜 가져오기
		List<String> dateList = new AmentiesService().mcDateList(uNo);
		// System.out.println(dateList);
		
		request.setAttribute("dateList", dateList);
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/user/jsp/amenities/multiCourtReservation.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member member = ((Member)request.getSession().getAttribute("loginUser"));
		
		String dayInput = request.getParameter("dayInput");
		int seatNumber = Integer.parseInt(request.getParameter("seatNumber"));
		int uNo = member.getU_NO();
		
		int result = new AmentiesService().mcResInsert(dayInput, seatNumber,uNo);
		
		String msg = "";
		if(result > 0) {
			msg = "신청이 완료 되었습니다. "
			    + member.getR_DONG() + "동 " + member.getR_HO() + "호 " + member.getR_NAME() + "님 "
			    + "코트번호 " + seatNumber + " "
			    + dayInput + "예약 되었습니다!";
			request.getSession().setAttribute("msg", msg);
		}else {
			request.getSession().setAttribute("msg", "신청이 실패 하였습니다.");
		}
		
		response.sendRedirect(request.getContextPath() + "/mcHistory");		
		
	}

}
