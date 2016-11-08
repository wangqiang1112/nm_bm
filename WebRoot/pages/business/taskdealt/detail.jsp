<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/inc/taglibs.inc" %>

<head>
	<title>任务详情</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js"></script>
	<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
	<script type="text/javascript">
		$(function() {
			$(".btn").click(function(){
					closeWindow();
			});
			
			// 任务状态
			var status = $("#status").val();
			if(1 <= status){
				$("#s1").attr("class","active");
				if(2 <= status){
					$("#s2").attr("class","active");
					if(3 <= status){
						$("#s3").attr("class","active");
					}
				}
			}
		});
	</script>
</head>

<body class="bodyGrey">
	<div class="formbody">
		<form id="myform" method="post" action="">
			<table class="formview">
				<tr>
					<td>
						<label>任务名称</label>
					</td>
					<td>
						${task.taskName}
					</td>
				</tr>
				<tr>
					<td>
						<label>下发部门</label>
					</td>
					<td>
						${task.departName}
					</td>
					<td></td>
				</tr>
				<tr>
					<td>
						<label>接收单位</label>
					</td>
					<td>
						${task.receiveUnit}
					</td>
				</tr>
				<tr>
					<td valign="top">
						<label>任务描述</label>
					</td>
					<td>
						${task.taskDescript}
					</td>
				</tr>
				<tr>
					<td valign="top">
						<label>任务期限</label>
					</td>
					<td>
						${task.taskTerm}
					</td>
				</tr>
				<tr>
					<td><label>业务进度</label></td>
					<td style="padding:5px 0px 35px 0px" class="progress">
					<input type="hidden" id="status" value="${task.status}" />
						<ul>
							<li id="s1" >
								<span>已下发</span>
							</li>
							<li id="s2" >
								<span>已接收</span>
							</li>
							<li id="s3" >
								<span>已上报</span>
							</li>
							<li class="last" >
								<span>已关闭</span>
							</li>
						</ul>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<label>任务详情</label>
					</td>
					<td>
						${task.taskDetail}
					</td>
				</tr>
				<tr>
					<td valign="top">
						<label>反馈详情</label>
					</td>
					<td>
						${task.feedBackDetail}
					</td>
				</tr>
				<tr>
					<td valign="top">
						<label>反馈附件</label>
					</td>
					<td id="newfj" >
						<a href="/yw-nm/${task.feedBackFilePath}">${task.feedBackFileName}</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>