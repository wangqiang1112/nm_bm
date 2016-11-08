<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<c:set var="DATE_FORMAT1" value="yyyy.MM.dd" />
<c:set var="DATE_FORMAT2" value="yyyy-MM-dd" />
<c:set var="DATE_FORMAT3" value="yyyyMMdd" />
<c:set var="DATE_FORMAT4" value="yyyy-MM-dd HH:mm" />
<c:set var="DATE_FORMAT5" value="yyyy-MM-dd HH:mm:ss" />
<c:set var="AMT_FORMAT" value="#,##0.00" />
<c:set var="DOMAIN" value="<%=basePath %>"></c:set>