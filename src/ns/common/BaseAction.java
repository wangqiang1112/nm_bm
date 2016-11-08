package ns.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import ns.major.audit.service.SysExceptionService;
import ns.major.config.dao.domain.User;
import ns.util.BeanUtils;
import ns.util.HttpUtils;

import org.apache.struts2.ServletActionContext;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	protected static Logger logger = LoggerFactory.getLogger(BaseAction.class);
	private Page page;
	private BaseQuery query;
	private String jsondata;
	@Autowired
	private SysExceptionService sysExcepServ;
	
	/**
	 * 公共的插入系统异常的方法
	 * @throws Exception 
	 */
	protected void logSysExcption(String model,String context) throws Exception{
		HttpServletRequest request=getHttpRequest();
		sysExcepServ.insertOneSysExcption(model,context,request);
	}
	
	
	/**
	 * 获取session里面存储的用户信息
	 * 
	 */
	protected User getUserInfo(){
		User user=(User)getHttpSession().getAttribute("User");
		return user;
	}
	
	/**
	 * 得到请求的form
	 */
	protected <T> T getForm(Class<T> clas) {
		return copyProperties(clas, getParameterMap(true));
	}
	
	protected <T> T copyProperties(Class<T> dest,Object orig){
		return BeanUtils.copyProperties(dest, orig);
	}
	
	protected Map<String,Object> getParameterMap(boolean escape){
		Map<String,Object> map = new LinkedHashMap<String, Object>();
		HttpServletRequest req = this.getHttpRequest();
		Enumeration<?> names = req.getParameterNames();
		while(names.hasMoreElements()){
			String name = (String)names.nextElement();
			String [] values = escape? HttpUtils.getParameterValues(name, Whitelist.basic()):req.getParameterValues(name);
			if(name.endsWith("[]")){
				name = name.substring(0,name.getBytes().length - 2);
			}
		    if(null == values || values.length == 0){
		    	map.put(name, values);
		    	continue;
		    }
		    if(values.length == 1){
		    	map.put(name, values[0]);
		    	continue;
		    }
		    StringBuilder sb = new StringBuilder();
		    for(String value : values){
		    	sb.append("'"+value+"',");
		    }
		    sb = sb.deleteCharAt(sb.length()-1);
		    map.put(name, sb.toString());
			
		}
		return map;
	}
	
	
	public void removeMessage(){
		if(getHttpSession().getAttribute("success")!=null){
			getHttpSession().setAttribute("success", null);
		}
		if(getHttpSession().getAttribute("error")!=null){
			getHttpSession().setAttribute("error",null);
		}
		if(getHttpSession().getAttribute("close")!=null){
			getHttpSession().setAttribute("close", null);
		}
	}
	//移除状态标记
	protected void removeSelected(){
		if(getHttpSession().getAttribute("opratId")!=null){
			getHttpSession().setAttribute("opratId", null);
		}
	}
	protected void setSelected(int opratId){
		getHttpSession().setAttribute("opratId",opratId);
	}
	/**
	 * 输出
	 */
	protected void success(String msg){
		getHttpSession().setAttribute("success",msg);
	}
	
	protected void success(String msg,boolean isclose){
		getHttpSession().setAttribute("success",msg);
		getHttpSession().setAttribute("close",isclose?"true":"false");
	}
	
	protected void error(String msg){
		getHttpSession().setAttribute("error", msg);
	}
	
	protected void error(String msg,boolean isclose){
		getHttpSession().setAttribute("error", msg);
		getHttpSession().setAttribute("close", isclose?"true":"false");
	}
	
	protected void outJsonArray(Object obj){
		outJsonString(JSONArray.fromObject(obj).toString());
	}
	
	protected void outJsonString(String str){
		getHttpResponse().setContentType("text/html;charset=UTF-8");
		outString(str);
	}
	
	protected void outString(String str){
		try {
			PrintWriter out = getHttpResponse().getWriter();
			String jsoncallback = getParameter("jsoncallback");
			if((jsoncallback!= null) && (!jsoncallback.equals(""))){
				out.write(new StringBuilder().append(jsoncallback).append("{").append(str).append("}").toString());
			}else{
				out.write(str);
			}
		} catch (IOException e) {
			logger.error("printWriter write exception:",e);
		}
		
	}
	
	/**
	 * 基础
	 */
	protected String getParameter(String name){
		return HttpUtils.getParameter(name);
	}
	
	protected void setBean(String name,Object obj){
		getHttpRequest().setAttribute(name, obj);
	}
	protected String getBean(String name){
		return getHttpRequest().getParameter(name);
	}

	/**
	 * 获取域名
	 */
	protected String getHostURL(){
		StringBuffer url = this.getHttpRequest().getRequestURL();
		String hostUrl = url.delete(url.length()-getHttpRequest().getRequestURI().length(),url.length()).append(getContextPath()).toString();
		return hostUrl;
	}
	
	protected String getCurrentURI() {
		return ServletActionContext.getRequest().getRequestURI();
	}

	public String getPageContext() {
		return getContextPath();
	}

	protected String getContextPath() {
		return ServletActionContext.getRequest().getContextPath();
	}
	protected String getRealPath(String fileName) {
		return ServletActionContext.getServletContext().getRealPath(fileName);
	}

	protected ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	public HttpServletRequest getHttpRequest() {
		return ServletActionContext.getRequest();
	}

	protected HttpSession getHttpSession() {
		return ServletActionContext.getRequest().getSession();
	}
	
	protected HttpServletResponse getHttpResponse() {
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		return ServletActionContext.getResponse();
	}
	
	protected ServletOutputStream getOutputStream() {
		try {
			return ServletActionContext.getResponse().getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected ActionContext getActionContext() {
		return ActionContext.getContext();
	}


	/**
	 * get set 方法
	 */

	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
	public BaseQuery getQuery() {
		return query;
	}
	public void setQuery(BaseQuery query) {
		this.query = query;
	}
	
	public String getJsondata() {
		if(this.getPage().getResult()!=null)
			return JSONArray.fromObject(this.getPage().getResult()).toString();
		return jsondata;
	}

	public SysExceptionService getSysExcepServ() {
		return sysExcepServ;
	}

	public void setSysExcepServ(SysExceptionService sysExcepServ) {
		this.sysExcepServ = sysExcepServ;
	}


	
}
