package ns.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ns.major.audit.service.SysUseAuditService;
import ns.major.config.dao.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class LoginFilter  implements Filter {
	@Autowired 
	SysUseAuditService sysUseService;

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest=(HttpServletRequest) request;
		HttpServletResponse httpResponse=(HttpServletResponse) response;
		ServletContext sc=httpRequest.getSession().getServletContext();
				
		XmlWebApplicationContext cxt=(XmlWebApplicationContext)WebApplicationContextUtils.getWebApplicationContext(sc);
		if(cxt.getBean("sysUseService")!=null){
			sysUseService=(SysUseAuditService)cxt.getBean("sysUseService");
		}
		
		HttpSession session=httpRequest.getSession();
        //获取URI
		String path=httpRequest.getRequestURI();
		if(path.indexOf("login.jsp")!=-1||path.indexOf("userLogin.do")!=-1){
			chain.doFilter(httpRequest, httpResponse);
			return;
		}
		//从session里面拿到用户的userName;
		User user=(User)session.getAttribute("User");
		if(user==null){
			httpResponse.sendRedirect(httpRequest.getContextPath()+"/logout.jsp");
		}
		else{
			String userName=user.getUserName();
			chain.doFilter(httpRequest, httpResponse);
		}
		
		// 系统使用审计
		String methodUrl = httpRequest.getRequestURL().toString();
		String[] temp = methodUrl.split("/");
		StringBuffer url = new StringBuffer();
		for(int i = 3 ; i < temp.length ; i++){
			url.append("/"+temp[i]);
		}
		try {
			sysUseService.addSysUse(url.toString(), request.getRemoteAddr().toString(),user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public SysUseAuditService getSysUseService() {
		return sysUseService;
	}

	public void setSysUseService(SysUseAuditService sysUseService) {
		this.sysUseService = sysUseService;
	}

	public void init(FilterConfig arg0) throws ServletException {
		getSysUseService();
		setSysUseService(this.getSysUseService());
	}

}
