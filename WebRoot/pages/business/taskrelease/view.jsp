<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/inc/taglibs.inc" %>
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<title>任务进度</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
		<script type="text/javascript">
			$(function() {
				/*$(".btn").click(function(){
					alert("成功",function(){
						closeWindow();
					});
				});*/
			});
			//已上报 通过
			function f_statusTo5(){
				var taskId=$('#taskId').val();
				window.location.href="taskDetail.do?status=5&taskId="+taskId;
			}
			//已上报 打回
			function f_statusTo3(){
				var taskId=$('#taskId').val();
				window.location.href="taskDetail.do?status=3&taskId="+taskId;
			}
			function zm(url){
				var y=encodeURI(url);
				window.location.href=url;
			}
		</script>
	</head>

	<body class="bodyGrey">
		<div class="formbody">
			<form id="form1" action="">
				<input name="taskId" id="taskId" type="hidden" value="${bean_Task.taskId}"/>
				<table class="formview">
					<tr>
						<td>
							<label>任务编号</label>
						</td>
						<td>
							<span>${bean_Task.taskNumber}</span>
						</td>
					</tr>
					<tr>
						<td>
							<label>任务名称</label>
						</td>
						<td>
							<span>${bean_Task.taskName}</span>
						</td>
					</tr>
					<tr>
						<td>
							<label>下发部门</label>
						</td>
						<td>
							<span>${bean_Task.departName}</span>
						</td>
					</tr>
					<tr>
						<td>
							<label>下发时间</label>
						</td>
						<td>
							<span>${bean_Task.issuedTime_str}</span>
						</td>
					</tr>
					<tr>
						<td>
							<label>任务期限</label>
						</td>
						<td>
							<span>${bean_Task.taskTerm}</span>
						</td>
					</tr>
					<tr>
						<td>
							<label>接收单位</label>
						</td>
						<td>
							<span>${bean_Task.receiveUnit_name}</span>
						</td>
					</tr>
					<tr>
						<td valign="top">
							<label>任务描述</label>
						</td>
						<td>
							<span>${bean_Task.taskDescript}</span>
						</td>
					</tr>
					<tr>
						<td><label>任务进度</label></td>
						<td style="padding:5px 0px 35px 0px" class="progress">
							<ul>
								<li class="active">
									<span>已下发</span>
								</li>
								
								<li
									<c:if test="${bean_Task.status>=2}">class="active"</c:if>
								>
									<span>已接收</span>
								</li>
								
								<li
									<c:if test="${bean_Task.status>=4}">class="active"</c:if>
								>
									<span>已上报</span>
								</li>	
								
								<li class="last 
									<c:if test="${bean_Task.status>=5}">lastactive</c:if>
									"
								>
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
							<span>${bean_Task.taskDetail}</span>
						</td>
					</tr>
						<tr>
						<td valign="top">
							<label>反馈详情</label>
						</td>
						<td>
							<span>${bean_Task.feedBackDetail}</span>
						</td>
					</tr>
					<tr>
						<td><label>反馈附件</label></td>
						<td>
							<a href="javascript:zm('${DOMAIN}${bean_Task.feedBackFilePath}')" class="tablelink">
								${bean_Task.feedBackFileName}
							</a>
						</td>
						<td></td>
					</tr>
				</table>
				<c:if test="${bean_Task.status==4}">
					<div class="btnpanel">
						<input onclick="f_statusTo5()" type="button" class="btn" value="通过" />
						<input onclick="f_statusTo3()" type="button" class="btn" value="打回" />
					</div>
				</c:if>
			</form>
		</div>
	</body>
</html>