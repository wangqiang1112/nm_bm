<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="ns.major.config.dao.domain.User"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js" ></script>
   	<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
   	<%SimpleDateFormat format=new  SimpleDateFormat("yyyy年MM月dd日 E"); String now=format.format(new Date());%>
   	<script type="text/javascript">
   		function edit(){
   			window.parent.frames["rightFrame"].editUser(<%=((User)session.getAttribute("User")).getUserId()%>);
   		}
   		function toPage(method){
			window.parent.frames["rightFrame"].toPage(method);
			
		}
   	</script>
	<title></title>
<body>
	<div class="headBg">
		<div class="headLogoimg"></div>
		<div class="headWelcome"><span><a href="javascript:void(0);" onclick="edit()" style="color:#f1dac2;">您好   [<%=((User)session.getAttribute("User")).getUserName()%>]</a></span>今天是：<%=now%> <span class="logOut"><a href="/yw-nm/logOut.do" target="_top" >[退出]</a></span></div>
		<div class="clear"></div>
	</div>
	<div class="headSite">
		<div class="lefttop"><span></span>系统菜单</div>
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
			</ul>
		</div>
	</div>
</body>
</html>