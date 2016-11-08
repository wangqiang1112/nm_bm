<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script type="text/javascript" src="resources/js/package-golbal.js"></script>
	<script type="text/javascript" src="resources/js/package-public.js"></script>
	<script type="text/javascript"
		src="/yw-nm/resources/js/package-validform.js"></script>
	<title>登录</title>
	<style type="text/css">
</style>
</head>
<body class="loginBgcolor">
	<div class="loginBg">
		<form id="myform" action="userLogin.do" method="post">
			<!--<img src="resources/images/loginbg0.png" width="100%" height="100%" />-->
			<div id="loginBg">
				<div id="Layer1">
					<dt>
						<input type="text" name="userName" id="userName"
							placeholder="请输入用户名" datatype="*" nullmsg="请输入用户" />
					</dt>
					<dt>
						<input type="password" name="upassword" id="upassword"
							placeholder="密码" datatype="*6-20" nullmsg="请输入密码"
							errormsg="密码长度为6-20位任意字符" />
					</dt>
				</div>
				<input type="submit" name="btnlogin" id="Layer2" value="" />
			</div>
			<%@ include file="/commons/message.jsp"%>
		</form>
	</div>
</body>
</html>