package user.amenities.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.facil.model.service.FacilService;
import admin.facil.model.vo.Facil;
import user.member.model.vo.Member;

@WebServlet("/srPopup")
public class SrPopup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SrPopup() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 날짜 가져오기
		String day = request.getParameter("today");
		// 유저번호 가져오기
		int uNo = ((Member)request.getSession().getAttribute("loginUser")).getU_NO();
		// System.out.println(uNo);
		
		// 예약내역있는지
		int result = new FacilService().libraryDayCount(day, uNo);
		
		// 독서실 예약 현황
		List<Facil> fList = new FacilService().librarySelectList(day);
		
		if(result == -1) {
			request.setAttribute("status", "fail");
		}else {
			request.setAttribute("status", "success");
			
		}
		
		request.setAttribute("fList", fList);
		request.getRequestDispatcher("/WEB-INF/views/user/jsp/amenities/studyRoomPopup.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
