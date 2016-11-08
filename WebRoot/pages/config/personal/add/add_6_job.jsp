<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/inc/taglibs.inc" %>
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>新增人员</title>
		<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-personal-add.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-validform.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/jquery.idTabs.min.js"></script>
		<script type="text/javascript">
			$(function() {
				//下拉选择框渲染
				$(".select3").uedSelect({
					width: 162
				});
			});
			
			function f_add(){
				var id=$('#stafferId').val();
				openWindow("工作经历","jobAdd.do?stafferId="+id,['480px','500px']);
			}
			function f_edit(jobID){
				openWindow("工作经历","jobAdd.do?jobID="+jobID,['480px','500px']);
			}
			function f_del(jobID){
				window.location.href="jobDelete.do?jobID="+jobID;
			}
		</script>
		<style type="text/css">
			.dfinput {width: 160px}
			.textinput {width: 398px;height: 100px}
			.forminfo {float: left;display: inline;}
			.faceupload {margin-left: 50px;margin-top: 5px;display: inline-block;}
			input[name=level],input[name=Passport],input[name=GreenCard]{float:left;}
			.leveltable td p{padding:15px 0px;}
			.leveltable td label{font-weight:bold;float:left;line-height:10px;margin-left:10px;}
			#enclosures li{float:left;padding-right: 10px;}
			a{
				cursor:pointer;
			}
		</style>
	</head>

	<body class="bodyGrey">
		<div class="formbody">
			<div id="usual1" class="usual">
				<div class="itab">
					<ul>
						<li>
							<a onclick="f_page(1)">
								基本资料
							</a>
						</li>
						<li>
							<a onclick="f_page(2)">
								部门信息
							</a>
						</li>
						<li>
							<a onclick="f_page(3)">
								选择密级
							</a>
						</li>
						<li>
							<a onclick="f_page(4)">
								出境信息
							</a>
						</li>
						<li>
							<a onclick="f_page(5)">
								教育经历
							</a>
						</li>
						<li>
							<a href="#tab6" class='selected'>工作经历</a>
						</li>
						<li>
							<a onclick="f_page(7)">家庭成员</a>
						</li>
						<li>
							<a onclick="f_page(8)">附件</a>
						</li>
					</ul>
				</div>
				<!--工作经历-->
				<div id="tab6" class="tabson">
					<!-- 全局变量 -->
					<input name="stafferId" id="stafferId" type="hidden" value="${stafferId}"/>
					<!--操作-->
					<div class="tools">
						<ul class="toolbar">
							<li><span class="add" id="staffJob_add" onclick="f_add()">新增</span></li>
						</ul>
					</div>
					<table class="tablelist">
						<thead>
							<tr>
								<th>起止时间</th>
								<th>结束时间</th>
								<th>单位名称</th>
								<th>职务</th>
								<th>职称</th>
								<th>工作职责</th>
								<th>证明人</th>
								<th>手机</th>
								<th>备注</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="#request.staffJobList" var="var">
								<tr>
									<!-- 起止时间 -->
									<td><s:property value="#var.WStartTime_str"/></td>
									<!--结束时间 -->
									<td><s:property value="#var.WEndTime_str"/></td>
									<!--单位名称-->
									<td><s:property value="#var.WUnitName"/></td>
									<!--职务 -->
									<td><s:property value="#var.WPost_str"/></td>
									<!--职称 -->
									<td><s:property value="#var.WJob_str"/></td>
									<!--工作职责 -->
									<td><s:property value="#var.WDuty"/></td>
									<!--证明人-->
									<td><s:property value="#var.wwitness"/></td>
									<!--手机-->
									<td><s:property value="#var.WPhone"/></td>
									<!--备注 -->
									<td><s:property value="#var.WRemarks"/></td>
									<td>
									<a onclick="f_edit(<s:property value='#var.jobID'/>)" 
									style="cursor:pointer"	class="tablelink">编辑</a>
									<a onclick="f_del(<s:property value='#var.jobID'/>)" 
									style="cursor:pointer"	class="tablelink">删除</a>
								</td>
								</tr>		
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<%@ include file="/commons/message.jsp" %>
		<script type="text/javascript">
			//设置头部位置
			var siteData = {
				siteName: '配置管理',
				siteChild: {
					siteName: '人员管理',
					siteChild: {
						siteName: '人员新增'
					}
				}
			};
			topSite(siteData);

			$("#usual1 ul").idTabs();
		</script>
	</body>

</html>