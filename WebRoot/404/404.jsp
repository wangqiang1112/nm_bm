<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<base href="<%=basePath%>"/>
<link href="404/style.css" rel="stylesheet" type="text/css" media="screen"/>

<script src="/yw-nm/resources/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script language="javascript">
 
 //5秒后定时跳转到首页
 $(function(){
        var timer = document.getElementById('timeoutToLogin');
        var timeout = 5;
        var url;
        //普通帐户和特殊帐户跳转不同的主页面
        
           url = '/yw-nm/secrecy/main.do';
         
        var interval = setInterval(function () {
            if (timeout <= 0) {
                window.location = url;
                clearInterval(interval);
            } else {
                timer.innerHTML = timeout;
                timeout--;
            }
        }, 1000);

 }); 
</script>
</head>

	<body  >
		<div class="demo">
			<p><span>4</span><span>0</span><span>4</span></p>
			
			<p>该页面不存在(´･ω･`)</p><br/><br/>
			 
			<p><span id="timeoutToLogin">5</span>后自动跳转</p>
			 
		</div>
	</body>
</html>
