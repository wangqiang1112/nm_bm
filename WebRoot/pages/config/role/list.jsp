<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>角色配置</title>
		<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-datagrid.js"></script>
		<script type="text/javascript">
			$(document).ready(function(e) {
				$(".add").click(function(){
					openWindow("新增角色","addOneRole.do?type=add",['560px','380px']);
				});
				$(".del").click(function(){
				    var arr = [];
					for(var i = 0;i<mmg.selectedRows().length;i++){
						arr.push(mmg.selectedRows()[i].roleId);
					}
					$("#roleIds").val(arr.join(","));
					if(mmg.selectedRows().length==0){
					alert("请选择要删除的角色");
					return false;
					}
					$.ajax({
					url:"/yw-nm/config/user/checkUser.do",
					type:"POST",
					data:{roleIds:$("#roleIds").val()},
					dataType:"text",
					success:function(result){
					alert(result)
						if(result=='true'){
						alert("该角色下还存在用户");
					}else{
					confirm("确定删除吗",function(){
					$("#myform").attr("action","roleDelete.do");
					$("#myform").submit();
				})
						}
					},
				});
					
				});
			
			});
			
  		 function editOne(roleId){
  		  openWindow("编辑角色","addOneRole.do?type=edit&roleId="+roleId,['560px','380px']);
  		 }
  		 function permission(roleId){
  		 openWindow("权限分配","getPermission.do?roleId="+roleId,['360px','500px']);
  		 }
		</script>
	</head>
	<body>
		<div class="rightinfo">
		<form id="myform" action="getRoleList.do" method="post">
				<!--操作-->
			<input type="hidden" name="roleIds" id="roleIds" />	
			<div class="tools">
				<ul class="toolbar">
					<li><span class="add">新增角色</span></li>
					<li><span class="del">删除</span></li>
				</ul>
			</div>
			<!--列表-->
			<table id="tablelist">
			</table>
			<%@ include file="/commons/pager.jsp" %>
			<%@ include file="/commons/message.jsp" %>
			</form>
		</div>
		<script type="text/javascript">
		$(function(){
		                     
			//列
    		var cols = [
		        { title:'角色',width:100,name:'roleName',align: 'center'},
		        { title:'描述',width:100,name:'roleDescrip',align: 'center'},
		        { title:'操作',width:100,name:'',align:'center',renderer:function(val,item,rowIndex){
		        	return   "<a href='javascript:editOne("+item.roleId+")' class='tablelink editone' >编辑</a>&nbsp"
							+"<a href='javascript:permission("+item.roleId+")' class='tablelink permission'>分配权限</a>";
		        }},
		    ];
			//本地示例
		    mmg =  $('#tablelist').mmGrid({
		    	items:${jsondata},//后台列表传过来的JSON数据
		        cols:cols,
		        height:'%',
		        width:'%',
		        multiSelect:true,
		        fullWidthRows:true,
		        showBackboard:false,
		        nowrap:true,
		        indexCol:true,
		        checkCol:true
		    }).on("loadSuccess",function(){
		    	mmg.resize(); //重新设置宽高
		    	//$('#tablelist').find("tr:first-child").addClass("selected");
		    });
		 	//重新绘制Grid高度
		    resizeGrid();
		});
		window.onresize=function(){
			resizeGrid();
		}
	</script>
		<script type="text/javascript">
			//设置头部位置
			var siteData = {
				siteName: '配置管理',
				siteChild: {
					siteName: '权限管理',
					siteChild: {
						siteName: '角色配置'
					}
				}
			};
			topSite(siteData);
		</script>
	</body>

</html>