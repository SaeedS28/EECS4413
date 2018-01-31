package filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class Throttle
 */

@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
, urlPatterns = { "/Start","/Start/*", "/StartUp","/StartUp/*"}, servletNames = { "Start" })

public class Throttle implements Filter {

	private static int firstTime=0;
	private String ddosPage="/AttemptedDDOS.jspx";
    /**
     * Default constructor. 
     */
    public Throttle() {
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
		
		HttpServletRequest req = (HttpServletRequest) request;
		long requestTime= req.getSession().getLastAccessedTime();
		long currentTime= System.currentTimeMillis();
		
		//Makes sure the program doesn't get throttled the first time
		if(firstTime<1) {
			firstTime++;
			chain.doFilter(request, response);
		}
		else if(currentTime - requestTime < 5000) {
			request.getRequestDispatcher(ddosPage).forward(request, response);
		}
		else {
			chain.doFilter(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
