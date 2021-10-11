package user.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.vehicle.model.service.MemberCarService;
import admin.vehicle.model.vo.MemberCar;
import user.member.model.service.MemberService;
import user.member.model.vo.Member;

/**
 * Servlet implementation class userModifyServlet
 */
@WebServlet("/userModify")
public class userModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member loginUser = (Member)request.getSession().getAttribute("loginUser");
		int dong = loginUser.getR_DONG();
		int ho = loginUser.getR_HO();
		List<MemberCar> memberCarList = new MemberCarService().userSelectMemberCar(dong, ho);
		
		request.setAttribute("memberCarList", memberCarList);
		request.getRequestDispatcher("/WEB-INF/views/user/jsp/member/userModify.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 한글 값 인코딩
		request.setCharacterEncoding("UTF-8");
				
		// 2. 회원정보 수정에 필요한 값 추출
		// 2. request에 담긴 값 꺼내서 변수에 저장
		String userId = request.getParameter("userId");
		String name = request.getParameter("name");
		String nickName = request.getParameter("nickName");
		String userPwd = request.getParameter("userPwd");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		

//		// 로그인 유저로부터 userNo 추출
		Member loginUser = (Member)request.getSession().getAttribute("loginUser");
		int userNo = loginUser.getU_NO();
		
		Member mem = new Member(userNo, userId, name, nickName, userPwd, phone, email);
		// System.out.println("수정하고자 하는 정보 : " + mem);
	
		// 3. 비즈니스 로직 수행(DB update)
		// 개인정보 수정 후에 session에 저장 된 loginUser 객체의 정보도 수정 되어야
		// 개인정보 수정 화면에서 수정 된 loginUser로 부터 정보를 사용하므로 수정 사항 반영 됨
		Member updateMember = new MemberService().updateMember(mem);
		
		// System.out.println("DB에서 수정 된 loginUser 정보 : " + updateMember);
		
		// 4. 응답 화면
		if(updateMember != null) {
			// 수정 된 값으로 loginUser 객체 저장
			request.getSession().setAttribute("loginUser", updateMember);
			request.getSession().setAttribute("loginNickName", updateMember.getU_NICKNAME());
			// 회원정보수정완료 페이지로 
			request.getRequestDispatcher("/WEB-INF/views/user/jsp/member/userModifySuccess.jsp").forward(request, response);
		} else {
			// 수정 실패 시 error 페이지로 foward
			request.setAttribute("msg", "회원 정보 수정에 실패하였습니다.");
			request.getRequestDispatcher("WEB-INF/views/user/common/errorpage.jsp").forward(request, response);
		}

	}
	
}
