<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>权限分配</title>
		<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-validform.js"></script>
		
		<script type="text/javascript" src="/yw-nm/resources/lib/zTree/jquery.ztree.core.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/lib/zTree/jquery.ztree.excheck.js"></script>
		<link rel="stylesheet" href="/yw-nm/resources/lib/zTree/zTreeStyle/zTreeStyle.css" />
		
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
			
			.ztree li span {
				font-family: "微软雅黑";
				font-size: 14px;
			}
			.ztree li a {
				margin:3px;
			}
			.ztree li span.button.ico_docu,.ztree li span.button.ico_open,.ztree li span.button.ico_close{
				display:none;
			}
		</style>
		
		<SCRIPT type="text/javascript">
			var setting = {
				check: {
					enable: true
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				callback:{
				onCheck:onCheck
				}
			};

			var zNodes =[
			    { id:1, pId:0, name:"保密宣传教育", open:false},
				{ id:11, pId:1, name:"宣传与教育", open:false},
				{ id:2, pId:0, name:"单位管理", open:false},
				{ id:21, pId:2, name:"资料上报", open:false},
				{ id:22, pId:2, name:"资料审核", open:false},
				{ id:23, pId:2, name:"资料查询", open:false},
				{ id:24, pId:2, name:"变更通知", open:false},
				{ id:3, pId:0, name:"配置管理", open:false},
				{ id:31, pId:3, name:"人员管理", open:false},
				{ id:311, pId:31, name:"新增人员", open:false},
				{ id:312, pId:31, name:"人员查询", open:false},
				{ id:32, pId:3, name:"部门管理", open:false},
				{ id:321, pId:32, name:"部门配置", open:false},
				{ id:33, pId:3, name:"权限管理", open:false},
				{ id:331, pId:33, name:"用户配置", open:false},
				{ id:332, pId:33, name:"角色配置", open:false},
				{ id:333, pId:33, name:"参数配置", open:false},
				{ id:34, pId:3, name:"信息管理", open:false},
				{ id:341, pId:34, name:"信息发布", open:false},
				
				{ id:4, pId:0, name:"业务管理", open:false},
				{ id:41, pId:4, name:"任务发布", open:false},
				{ id:411, pId:41, name:"新建任务", open:false},
				{ id:412, pId:41, name:"任务查询", open:false},
				{ id:42, pId:4, name:"任务接收", open:false},
				{ id:421, pId:42, name:"任务处理", open:false},
				{ id:43, pId:4, name:"任务预警", open:false},
				
				{ id:5, pId:0, name:"统计分析", open:false},
				{ id:51, pId:5, name:"任务处理分析", open:false},
				{ id:511, pId:51, name:"部门分析", open:false},
				{ id:512, pId:51, name:"单位分析", open:false},
				{ id:52, pId:5, name:"任务预警分析", open:false},
				
				{ id:6, pId:0, name:"安全审计", open:false},
				{ id:61, pId:6, name:"系统使用审计", open:false},
				{ id:62, pId:6, name:"用户登录审计", open:false},
				{ id:63, pId:6, name:"登录异常审计", open:false},
				{ id:64, pId:6, name:"系统异常审计", open:false} 
				
			];
			
			var code;
			
			function setCheck() {
				var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
				py = $("#py").attr("checked")? "p":"",
				sy = $("#sy").attr("checked")? "s":"",
				pn = $("#pn").attr("checked")? "p":"",
				sn = $("#sn").attr("checked")? "s":"",
				type = { "Y":py + sy, "N":pn + sn};
				zTree.setting.check.chkboxType = type;
				showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
			}
			function showCode(str) {
				if (!code) code = $("#code");
				code.empty();
				code.append("<li>"+str+"</li>");
			}
			
			$(document).ready(function(){
			   
			    if($("#permissions").val()!=null&&$("#permissions").val()!=''){
			    var data = $("#permissions").val();
			    var dataArr = data.split("-");
			        zNodes.splice(0, zNodes.length)
			    for(var i = 0;i < dataArr.length;i++){
			    	zNodes.push(eval("("+dataArr[i]+")"));
			    }
			    }
			     
				$.fn.zTree.init($("#treeDemo"), setting, zNodes);
				setCheck();
				$("#py").bind("change", setCheck);
				$("#sy").bind("change", setCheck);
				$("#pn").bind("change", setCheck);
				$("#sn").bind("change", setCheck);
			});
			
		   function onCheck(){
			var zTree=$.fn.zTree.getZTreeObj("treeDemo");
			
			//alert("节点名="+zTree.getCheckedNodes(true)[0].id);
			var PrivilegeIds="";
			for(var i=0;i<zTree.getCheckedNodes(true).length;i++){
			PrivilegeIds=PrivilegeIds+zTree.getCheckedNodes(true)[i].id+","
			}
			PrivilegeIds=PrivilegeIds.substring(0, PrivilegeIds.length-1);

			$("#permissions").val(PrivilegeIds);
			
			//alert($("#permissions").val());
			}
			//-->
	</SCRIPT>
		
		
		
	</head>

	<body class="bodyGrey">
		<div class="formbody" id="quanxian">
			<form id="form1" action="savePrivilege.do" method="post">
			<input type="hidden" name="roleId" value="${roleId}"/>
			<input type="hidden" name="permissions"  id="permissions" value="${privileges}"/>
				<table class="forminfo">
					<!--
					<tr>
						<td class="permission">
							<ul>
								<li>
									<input type="checkbox" name="" />保密宣传教育
									<ul>
										<li>
											<input type="checkbox" name="" />宣传与教育
										</li>
									</ul>
								</li>
								<li>
									<input type="checkbox" name="" />单位管理
									<ul>
										<li>
											<input type="checkbox" name="" />资料上报
										</li>
										<li>
											<input type="checkbox" name="" />资料审核
										</li>
										<li>
											<input type="checkbox" name="" />资料查询
										</li>
										<li>
											<input type="checkbox" name="" />变更通知
										</li>
									</ul>
								</li>
								<li>
									<input type="checkbox" name="" />配置管理
									<ul>
										<li>
											<input type="checkbox" name="" />人员管理
											<ul>
												<li>
													<input type="checkbox" name="" />新增人员
												</li>
												<li>
													<input type="checkbox" name="" />人员查询
												</li>
											</ul>
										</li>
										<li>
											<input type="checkbox" name="" />部门管理
											<ul>
												<li>
													<input type="checkbox" name="" />部门配置
												</li>
											</ul>
										</li>
										<li>
											<input type="checkbox" name="" />权限管理
											<ul>
												<li>
													<input type="checkbox" name="" />用户配置
												</li>
												<li>
													<input type="checkbox" name="" />角色配置
												</li>
												<li>
													<input type="checkbox" name="" />参数配置
												</li>
											</ul>
										</li>
										<li>
											<input type="checkbox" name="" />信息管理
											<ul>
												<li>
													<input type="checkbox" name="" />信息发布
												</li>
											</ul>
										</li>
									</ul>
								</li>
							</ul>
						</td>
					</tr>
					-->
					
					<tr>
						<td>
							<div id="treeDemo" class="ztree"></div>
							<div style="display:none;">
								<input type="checkbox" id="py" class="checkbox first" checked/>
								<input type="checkbox" id="sy" class="checkbox first" checked/>
								<input type="checkbox" id="pn" class="checkbox first" checked/>
								<input type="checkbox" id="sn" class="checkbox first" checked/>
							</div>
						</td>
					</tr>
					
					<tr>
						<td>
							<input name="" type="submit" class="btn" value="保存" />
							<input type="button" class="btn1" value="取消" />
						</td>
					</tr>
				</table>

			</form>
		</div>
		
		
	</body>

</html>