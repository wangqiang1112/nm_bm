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
				//基本信息验证
				$("#form1").Validform({
					tipSweep:true, //在按钮提交时，进行验证
					tiptype:function(msg){
						layer.msg(msg);
					},
					datatype:{
						"idcard":function(gets,obj,curform,regxp){
							    return isIdCardNo($(obj).val());
						 }
					}
				});
			});
			
			function f_add(){
				var id=$('#stafferId').val();
				openWindow("家庭成员","familyAdd.do?stafferId="+id,['480px','350px']);
			}
			function f_edit(infoID){
				openWindow("家庭成员","familyAdd.do?infoID="+infoID,['480px','350px']);
			}
			function f_del(infoID){
				window.location.href="familyDelete.do?infoID="+infoID;
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
							<a onclick="f_page(6)">工作经历</a>
						</li>
						<li>
							<a  href="#tab7" class='selected'>家庭成员</a>
						</li>
						<li>
							<a onclick="f_page(8)">附件</a>
						</li>
					</ul>
				</div>
				<!--家庭成员-->
				<div id="tab7" class="tabson">
					<!-- 全局变量 -->
					<input name="stafferId" id="stafferId" type="hidden" value="${stafferId}"/>
					<!--操作-->
					<div class="tools">
						<ul class="toolbar">
							<li><span class="add" id="familyInfo_add" onclick="f_add()">新增</span></li>
						</ul>
					</div>
					<table class="tablelist">
						<thead>
							<tr>
								<th>关系</th>
								<th>姓名</th>
								<th>性别</th>
								<th>年龄</th>
								<th>国籍</th>
								<th>单位</th>
								<th>职务</th>
								<th>职称</th>
								<th>住址</th>
								<th>手机</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="#request.familyInfoList" var="var">
								<tr>
									<!-- 关系 -->
									<td><s:property value="#var.relation"/></td>
									<!--姓名 -->
									<td><s:property value="#var.FName"/></td>
									<!--性别-->
									<td><s:property value="#var.FSex_str"/></td>
									<!--年龄 -->
									<td><s:property value="#var.FAge"/></td>
									<!--国籍 -->
									<td><s:property value="#var.FNationality_str"/></td>
									<!--单位-->
									<td><s:property value="#var.FUnit"/></td>
									<!--职务-->
									<td><s:property value="#var.FPost"/></td>
									<!--职称-->
									<td><s:property value="#var.FJob"/></td>
									<!--住址-->
									<td><s:property value="#var.FAddress"/></td>
									<!--手机-->
									<td><s:property value="#var.FPhone"/></td>
									<td>
									<a onclick="f_edit(<s:property value='#var.infoID'/>)" 
										class="tablelink">编辑</a>
									<a onclick="f_del(<s:property value='#var.infoID'/>)" 
										class="tablelink">删除</a>
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