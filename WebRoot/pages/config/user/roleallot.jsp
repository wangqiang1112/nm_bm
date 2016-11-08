<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>角色分配</title>
		<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-validform.js"></script>
		<script type="text/javascript">
			$(function() {
				$(".btn1").click(function() {
					closeWindow();
				});
			});
		</script>
		<style type="text/css">
			.permission {}
			
			.permission ul {
				margin-left: 20px;
			}
			
			.permission ul li {
				padding: 4px 0px;
			}
			
			.permission ul li input[type=checkbox] {
				position: relative;
				top: 3px;
				margin-right: 5px;
			}
		</style>
	</head>

	<body class="bodyGrey">
		<div class="formbody">
			<form id="form1" action="saveRole.do">
			<input type="hidden" name="userId" value="${user.userId}"/>
				<table class="forminfo" height="100%">
					<tr>
						<td class="permission">
							<ul>
				<s:iterator value="rolesList" status="list" id="role">
					<li ><input type="radio" name="roleId" value="${roleId}" <c:if test="${user.roleId==roleId}">checked</c:if>/>${role.roleName}</li>
				</s:iterator>
							</ul>
						</td>
					</tr>
				</table>
				<div class="btnpanel" style="position: fixed;bottom:0px;width:320px;padding-bottom:15px;">
					<input name="" type="submit" class="btn" value="保存" />
					<input type="button" class="btn1" value="取消" />
				</div>
			</form>
		</div>
	</body>

</html>