package core.authentication.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * Servlet Filter implementation class CORSFilter
 */
// Enable it for Servlet 3.x implementations
/* @ WebFilter(asyncSupported = true, urlPatterns = { "/*" }) */
public class CorsFilter implements Filter {
	
	private static final String URI_ORIGIN_VACCINATORS = "http://vacunadores07.web.elasticloud.uy";
	private static final String URI_ORIGIN_CITIZENS = "http://vacunas07.web.elasticloud.uy";
	
    /**	
     * Default constructor.
     */
    public CorsFilter() {
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
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
 
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("CORSFilter HTTP Request: " + request.getMethod());
        String allowedOrigin = null;
        
        // Authorize (allow) all domains to consume the content
        if (request.getHeader("Origin").equalsIgnoreCase(URI_ORIGIN_CITIZENS)) {
        	allowedOrigin = URI_ORIGIN_CITIZENS;
        }
        else if (request.getHeader("Origin").equalsIgnoreCase(URI_ORIGIN_VACCINATORS)) {
        	allowedOrigin = URI_ORIGIN_VACCINATORS;
         }
    
     
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", allowedOrigin);
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept");
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Credentials", "true");
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");
 
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
 
        // For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS handshake
        if (request.getMethod().equals("OPTIONS")) {
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }
 
        // pass the request along the filter chain
        chain.doFilter(request, servletResponse);
    }
 
    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }
 
}