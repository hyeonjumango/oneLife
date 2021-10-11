package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import admin.manager.model.vo.Manager;
import user.member.model.vo.Member;



@WebFilter("/*")
public class LoginCheckFilter implements Filter {
	// 비로그인 상태에서 가능한 요청 리스트
	private List<String> permitList;
	// css,js,image와 같은 리소스 리스트
	private List<String> resourceList;
    /**
     * Default constructor. 
     */
    public LoginCheckFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest hreq = (HttpServletRequest)request;
		String uri = hreq.getRequestURI();
		// System.out.println(uri);
		
		// 로그인 하지 않은 상태에서 허용 되는 요청이 아니라면
		if(!permitList.contains(uri)) {
			// css,js,image와 같이 /resources/ 하위 요청인지 확인
			boolean isResourceFile = false;
			for(String str : resourceList) {
				if(uri.contains(str)) {
					isResourceFile = true;
					break;
				}
			}
			
			if(!isResourceFile) {
				Member loginUser = (Member)hreq.getSession().getAttribute("loginUser");
				Manager loginManager= (Manager)hreq.getSession().getAttribute("loginManager");
        
				// System.out.println(loginManager);
        
				if(loginUser == null && loginManager == null) {
					hreq.setAttribute("msg", "올바르지 않은 요청입니다.");
					hreq.getRequestDispatcher("WEB-INF/views/user/common/errorpage.jsp").forward(request, response);
					return; 
				}
			}
			
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// 인스턴스 초기화 시 permitList, resourceList 값 설정하기 
		// 비로그인 상태에서도!
		permitList = new ArrayList<>();
		permitList.add("/oneLife/");
		permitList.add("/oneLife/login");
		permitList.add("/oneLife/userAgree");			// 회원가입 이용약관  
		permitList.add("/oneLife/userJoin"); 			// 회원가입 회원정보입력
		permitList.add("/oneLife/idCheck");				// 회원가입 ID 체크
		permitList.add("/oneLife/nickCheck");			// 회원가입 닉네임 체크
		permitList.add("/oneLife/numberCheck");			// 회원가입 인증번호 체크
		permitList.add("/oneLife/mailVerification");	
		
		permitList.add("/oneLife/userJoinSuccess");		// 회원가입 성공 
		permitList.add("/oneLife/findIdPwd");			// 아이디 비밀번호 찾기
		permitList.add("/oneLife/fIdSuccess");			// 아이디 찾기 성공
		permitList.add("/oneLife/fPwdSuccess");			// 비밀번호 찾기 성공
		
		permitList.add("/oneLife/admin/");
		
		resourceList = new ArrayList<>();
		resourceList.add("/resources/");
	}

}
