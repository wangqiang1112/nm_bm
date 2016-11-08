package ns.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {
	String encoding = null;

	public void destroy() {

	}

	public void doFilter(ServletRequest srequest, ServletResponse sresponse,
			FilterChain chain) throws IOException, ServletException {
		if (null != encoding) {
			HttpServletRequest request = (HttpServletRequest) srequest;
			HttpServletResponse response = (HttpServletResponse) sresponse;
			request.setCharacterEncoding(encoding);
			response.setContentType("text/html;charset=" + encoding);
			response.setCharacterEncoding(encoding);
		}
		chain.doFilter(srequest, sresponse);

	}

	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter("encoding");
	}

}
