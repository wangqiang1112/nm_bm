<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/inc/taglibs.inc" %>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>新增角色</title>
		<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js" ></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-public.js" ></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-validform.js" ></script>
		<script type="text/javascript">
			$(function() {
				$("#form1").Validform({
					tipSweep:true,
					tiptype: function(msg){
						layer.msg(msg);
					}
				});
				$(".btn1").click(function(){
					closeWindow();
				});
			});
		</script>
	</head>
	<body class="bodyGrey">
		<div class="formbody">
			<form id="form1" action="roleAdd.do" method="post"> 
			<input type="hidden" value="${type}" name="type"/>
			<input type="hidden" value="${role.roleId }" name="roleId"/>
				<table class="forminfo">
					<tr>
						<td>
							<label>角色名</label>
						</td>
						<td>
							<input value="${role.roleName}" name="roleName" type="text" class="dfinput" datatype="*" nullmsg="请输入角色名" style="width:380px" ajaxurl="roleNameCheck.do?type=${type}&preName=${role.roleName}"/>
						</td>
						<td>
							<span style="float:left" class="Validform_checktip"></span>
						</td>
					</tr>
					<tr>
						<td valign="top">
							<label>角色描述</label>
						</td>
						<td>
							<textarea cols="" rows=""  class="textinput"  name="roleDescrip" style="width:360px">${role.roleDescrip}</textarea>
						</td>
						<td valign="top">
							<span style="float:left" class="Validform_checktip"></span>
						</td>
					</tr>
					<tr>
						<td></td>
						<td colspan="2">
							<input name="" type="submit" class="btn" value="保存" />
							<input type="button" class="btn1" value="取消" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>