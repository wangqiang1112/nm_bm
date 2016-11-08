<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>信息发布</title>
		<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-validform.js"></script>
		<script type="text/javascript">
			$(function() {
				$("#form1").Validform({
					tiptype: 2
				});
			});
		</script>
	</head>

	<body class="bodyGrey">
		<div class="formbody">
			<div class="formtitle"><span>参数设置</span></div>
			<form id="form1" action="savaConfSet.do">
				<table class="forminfo">
					<tr>
						<td>
							用户密码输入错误&nbsp;&nbsp;<input value="${paraterConfig.failTimes}" name="failTimes" type="text"  class="dfinput" datatype="n" sucmsg=" " nullmsg="请输入值" errormsg="格式输入不正确" style="width:55px" />&nbsp;&nbsp;次后锁定
						</td>
						<td>

						</td>
					</tr>
					<tr>
						<td>
							口令长度不少于&nbsp;&nbsp;<input value="${paraterConfig.pwLength}" name="pwLength" type="text"  class="dfinput" datatype="n" sucmsg=" " nullmsg="请输入值" errormsg="格式输入不正确" style="width:55px" />&nbsp;&nbsp;位
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input name="" type="submit" class="btn" value="保存" />
						</td>
					</tr>
				</table>
				<%@ include file="/commons/message.jsp" %>
			</form>
		</div>
	</body>
	<script type="text/javascript">
		//设置头部位置
		var siteData = {
			siteName: '配置管理',
			siteChild: {
				siteName: '权限管理',
				siteChild: {
					siteName: '参数设置'
				}
			}
		};
		topSite(siteData);
	</script>

</html>