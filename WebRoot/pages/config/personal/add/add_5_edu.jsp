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
				openWindow("教育经历","eduAdd.do?stafferId="+id,['480px','450px']);
			}
			function f_edit(eduID){
				openWindow("教育经历","eduAdd.do?eduID="+eduID,['480px','450px']);
			}
			function fun_delete(eduId){
				//alert(eduId);
				window.location.href="eduDelete.do?eduID="+eduId;
			}
			/* function f_del(eduID){
				window.location.href="eduDelete.do?eduID="+eduID;
			} */
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
							<a href="#tab5"class='selected'>
								教育经历
							</a>
						</li>
						<li>
							<a onclick="f_page(6)">工作经历</a>
						</li>
						<li>
							<a onclick="f_page(7)">家庭成员</a>
						</li>
						<li>
							<a onclick="f_page(8)">附件</a>
						</li>
					</ul>
				</div>
				<!--教育经历-->
				<div id="tab5" class="tabson">
					<!-- 全局变量 -->
					<input name="stafferId" id="stafferId" type="hidden" value="${stafferId}"/>
					<!--操作-->
					<div class="tools">
						<ul class="toolbar">
							<li><span class="add" id="staffEdu_add" onclick="f_add()">新增</span></li>
						</ul>
					</div>
					<table class="tablelist">
						<thead>
							<tr>
								<th>起止时间</th>
								<th>结束时间</th>
								<th>院校名称</th>
								<th>专业</th>
								<th>学制(年)</th>
								<th>学位</th>
								<th>证明人</th>
								<th>备注</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="#request.staffEduList" var="var">
								<tr>
									<!-- 起止时间 -->
									<td><s:property value="#var.EStartTime_str"/></td>
									<!--结束时间 -->
									<td><s:property value="#var.EEndTime_str"/></td>
									<!--院校名称 -->
									<td><s:property value="#var.institutionName"/></td>
									<!--专业 -->
									<td><s:property value="#var.discipline"/></td>
									<!--学制 -->
									<td><s:property value="#var.ESystem"/></td>
									<!--学位 -->
									<td><s:property value="#var.degree_str"/></td>
									<!--证明人 -->
									<td><s:property value="#var.ewitness"/></td>
									<!--备注 -->
									<td><s:property value="#var.ERemarks"/></td>
									<td>
									<a onclick="f_edit(<s:property value='#var.eduID'/>)" 
										class="tablelink">编辑</a>
									<%-- <a onclick="f_del(<s:property value='#var.eduID'/>)" 
										class="tablelink">删除</a>
										eduDelete.do?eduID="+eduID;--%>	
									<a href="javascript:fun_delete(<s:property value='#var.eduID'/>)"+ class="tablelink">删除</a>
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