<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/inc/taglibs.inc" %>

<head>
	<title>任务进度</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js"></script>
	<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
	<script type="text/javascript">
		$(function() {
			// 任务状态
			var status = $("#status").val();
			if(status == 3){
				$("#s1").attr("class","active");
				$("#s2").attr("class","active");
			}else if(1 <= status){
					$("#s1").attr("class","active");
					if(2 <= status){
						$("#s2").attr("class","active");
						if(4 <= status){
							$("#s3").attr("class","active");
						}
					}
				}
		});
		
		// 文件上传
		layui.use('upload',function(){
			layui.upload({
				url:'uploadFile.do',
				ext:'zip|doc|xlsx',
				title:'附件格式为*.zip *.doc *.xlsx',
				success:function(res,input){
					//返回JSON为 {"filePath":"/upload/temp/image/2016/10/23/1477152441733.png","code":0,"fileUrl":"http://localhost:8080/yw-nm/upload/temp/image/2016/10/23/1477152441733.png"}
					//可根据返回的信息，赋值到隐藏文本域保存到数据库
					if(res.code==0){
						$("#feedBackFilePath").val(res.filePath);
						var fileName = res.filePath;
						var list = fileName.split("/");
						var name = list[list.length-1];
						$("#fileName").append(name);
					}else{
						alert(res.msg);
					}
				}
			});
		});
		
		//保存
		function update(){
			$("#myform").submit();
		}
	</script>
</head>

<body class="bodyGrey">
	<div class="formbody">
		<form id="myform" method="post" action="/yw-nm/taskUnit/update.do?pageType=3">
			<input type="hidden" id="feedBackFilePath" name="feedBackFilePath" />
			<input type="hidden" id="taskId" name="taskId" value="${task.taskId}" />
			<table class="formview">
				<tr>
					<td>
						<label>任务名称</label>
						<input type="hidden" id="taskName" name="taskName" value="${task.taskName}" />
					</td>
					<td>
						${task.taskName}
					</td>
				</tr>
				<tr>
					<td>
						<label>下发部门</label>
						<input type="hidden" id="departName" name="departName" value="${task.departName}" />
					</td>
					<td>
						${task.departName}
					</td>
					<td></td>
				</tr>
				<tr>
					<td>
						<label>接收单位</label>
						<input type="hidden" id="receiveUnit" name="receiveUnit" value="${task.receiveUnit}" />
					</td>
					<td>
						${task.receiveUnit}
					</td>
				</tr>
				<tr>
					<td valign="top">
						<label>任务描述</label>
						<input type="hidden" id="taskDescript" name="taskDescript" value="${task.taskDescript}" />
					</td>
					<td>
						${task.taskDescript}
					</td>
				</tr>
				<tr>
					<td><label>任务进度</label></td>
					<td style="padding:5px 0px 35px 0px" class="progress">
					<input type="hidden" id="status" name="status" value="${task.status}" />
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
						<textarea name="taskDetail" cols="" rows="" class="textinput" style="height:80px" value="${task.taskDetail}" ></textarea>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<label>反馈信息</label>
					</td>
					<td>
						<textarea name="feedBackDetail" cols="" rows="" class="textinput" style="height:80px" value="${task.feedBackDetail}" ></textarea>
					</td>
				</tr>
			</table>
		</form>
			<tr>
				<td>
					<label style="margin-top:10px;margin-right:57px" >附件</label>
				</td>
				<td>
					<input type="file" name="file" id="file" lay-type="file" class="layui-upload-file" />
				</td>
				<span id="fileName" ></span>
			</tr>
			<div class="btnpanel">
					<input name="button" type="button" class="btn" value="保存" onclick="update();" />
			</div>
	</div>
</body>
</html>