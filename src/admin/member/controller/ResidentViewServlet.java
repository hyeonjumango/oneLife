package admin.member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.member.model.service.ResidentService;
import admin.member.model.vo.Resident;

// 입주자명부 목록
@WebServlet("/admin/member/view")
public class ResidentViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rDong = Integer.parseInt(request.getParameter("rDong"));
		int rHo = Integer.parseInt(request.getParameter("rHo"));
		
		// 목록 조회 해오기
		// 동호 저장
		Resident r = new Resident(rDong, rHo);
		
		List<Resident> rList = new ResidentService().oneSelectResident(r);
		// System.out.println(rList);
		
		if(rList != null && rList.size() > 0) {
			request.setAttribute("rList", rList);
			request.getRequestDispatcher("/WEB-INF/views/admin/jsp/member/resident_view.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rDong = Integer.parseInt(request.getParameter("rDong"));
		int rHo = Integer.parseInt(request.getParameter("rHo"));
		String holderName = request.getParameter("holderName");
		String holderEmail = request.getParameter("holderEmail");
		String[] memberNames = request.getParameterValues("memberName");
		String[] memberEmails = request.getParameterValues("memberEmail");
		
		List<Resident> rList = new ArrayList<>();
		
		// 이전세대 저장
		List<Resident> oldList = new ResidentService().oneSelectResident(new Resident(rDong, rHo));
		
		// 세대주 저장
		rList.add(new Resident(rDong, rHo, holderName, holderEmail, "세대주"));
		// 세대원 저장
		if(request.getParameterValues("memberName") != null && request.getParameterValues("memberEmail") != null) {
			for(int i = 0; i < memberNames.length; i++) {
				rList.add(new Resident(rDong, rHo, memberNames[i], memberEmails[i], "세대원"));
			}
		}
		
		// System.out.println(rList);
		
		// 세대 정보 변경
		int result1 = new ResidentService().changeResident(rList);
		
		// 세대 정보 삭제
		int result2 = new ResidentService().deleteResident(rList);
		
		if(result1 > 0) {
			request.getSession().setAttribute("msg", "success");
		}else {
			request.getSession().setAttribute("msg", "fail");
		}
		
		response.sendRedirect(request.getContextPath() + "/admin/member/list");
		
		
	}

}
