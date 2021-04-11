package ttps.spring.filters;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import ttps.spring.serviciosImp.TokenServices;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Servlet Filter implementation class JWTFilter
 */
@WebFilter(filterName = "jwt-auth-filter", urlPatterns = "*")
public class JWTFilter implements Filter {
	private FilterConfig filterConf;
    /**
     * Default constructor. 
     */
    public JWTFilter() {
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
		System.out.println("Entre al filtro");
		HttpServletRequest req = (HttpServletRequest) request;
		Boolean foodtruckerURL = ("/ttps-spring/usuario/foodtrucker".equals(req.getRequestURI()));
		Boolean organizadorURL = ("/ttps-spring/usuario/organizador".equals(req.getRequestURI()));
		Boolean loginURL = ("/ttps-spring/usuario/autenticacion".equals(req.getRequestURI()));
        // El login del usuarios es publico
        if (foodtruckerURL || organizadorURL || loginURL || HttpMethod.OPTIONS.matches(req.getMethod())) {
        	System.out.println("Fue excepcion");
            chain.doFilter(request, response);
            return;
        }

        String token = req.getHeader(HttpHeaders.AUTHORIZATION);

        if (token == null || !TokenServices.validateToken(token)) {
            HttpServletResponse res = (HttpServletResponse) response;
            res.setStatus(HttpStatus.FORBIDDEN.value());
            return;
        }

        chain.doFilter(request, response);
	}
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.filterConf=fConfig;
	}

}
