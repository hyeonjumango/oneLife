package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import wrapper.EncryptWrapper;

/**
 * Servlet Filter implementation class EncryptFilter
 */
// 암호화
// Login, Join, Modify
@WebFilter(filterName="encrypt", servletNames= {"loginServlet",
												"userJoinServlet",
												"pwdModifyServlet",
												"infoChange",
												"managerCreate"})
public class EncryptFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncryptFilter() {
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
		// System.out.println("EncryptFilter 통과");

		EncryptWrapper encWrapper = new EncryptWrapper((HttpServletRequest)request);
		
		
		// pass the request along the filter chain
		// request 객체를 가공한 encWrapper 객체로 변경하여 서블릿으로 넘김
		chain.doFilter(encWrapper, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
