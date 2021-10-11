package user.VisitCar.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.VisitCar.model.service.VisitCarService;
import user.VisitCar.model.vo.UserVisitCarSearch;
import user.member.model.vo.Member;

/**
 * Servlet implementation class VisitCarListServlet
 */
@WebServlet("/visitCarList")
public class VisitCarListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VisitCarListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Member m = (Member) request.getSession().getAttribute("loginUser");
		if (m == null) {
			request.getSession().setAttribute("msg", "입주민 전용 공간입니다.");
			response.sendRedirect(request.getContextPath()+"/main");
		} else {
			int dong = m.getR_DONG();
			int ho = m.getR_HO();
			int page = 1;
			if (request.getParameter("page")  != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			String date = request.getParameter("date");
			String carNo = request.getParameter("carNo");
			String applicant = request.getParameter("applicant");
			
			if (date == null || date.equals("전체") || date.equals("")) {
				date = "all";
			}
			
			if (carNo != null && (carNo.equals("전체") || carNo.equals(""))) {
				carNo = "all";
			}
			
			if (applicant != null && (applicant.equals("전체") || applicant.equals(""))) {
				applicant = "all";
			}
			
			Map<String, Object> map = new VisitCarService().selectList(page, dong, ho, new UserVisitCarSearch(date, carNo, applicant));
//			System.out.println(map);
			//응답 페이지 구성 시 사용할 데이터 설정O
			request.setAttribute("pi", map.get("pi"));
			request.setAttribute("visitCarList", map.get("visitCarList"));
			
			request.getRequestDispatcher("WEB-INF/views/user/jsp/VisitCar/VisitCarList.jsp").forward(request, response);
		}
	}    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
