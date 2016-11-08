<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用户配置</title>
		<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-datagrid.js"></script>
		<script type="text/javascript">
		    
			$(document).ready(function(e) {
				//查询
				$("#btnSearch").click(function(){
				});
				//新增
				$(".add").click(function(){
					openWindow('新增用户','addOneUser.do?type=add',['570px','390px']);
				});
				//删除
				$(".del").click(function(){ 
				
				    var arr = [];
					for(var i = 0;i<mmg.selectedRows().length;i++){
					if(mmg.selectedRows()[i].userName=="sysAdministrator"){
						continue;
						}
						console.log(arr);
						arr.push(mmg.selectedRows()[i].userId);
					}
					$("#userIds").val(arr.join(","));
					 if(mmg.selectedRows().length==0){
					alert("请选择要删除的用户");
					return false;
					}
					confirm("确定删除吗",function(){
					$("#myform").attr("action","delete.do");
					$("#myform").submit();
					}) 
				})
				});
			
			    //编辑
				function editOne(userId){
						openWindow('编辑信息','addOneUser.do?type=edit&userId='+userId,['570px','390px']);
				}
				
		  		 
		  		 //单个删除
				 function deleteOne(userId){
				 
		  		confirm("确定删除吗",function(){
		  		        $("#userIds").val(userId);
						$("#myform").attr("action","delete.do");
						$("#myform").submit();
						})
						}	
				//锁定
				function lockOne(userId,isEnable){
						$("#isEnable").val(isEnable);
						$("#userId").val(userId);
						$("#myform").attr("action","lockUser.do");
						$("#myform").submit();
						};
				function roleallot(userId){//角色分配
				
						openWindow('角色分配','roleallot.do?userId='+userId,['360px','500px']);
				
				}
		</script>
	</head>

	<body>
		<div class="rightinfo">
			<form id="myform" action="list.do" method="post">
				<!--查询-->
				<input name="userId" type="hidden" id="userId"/>
				<input name="isEnable" type="hidden" id="isEnable"/>
				<input type="hidden" name="userIds" id="userIds" />
				<ul class="seachform">
					<li><label>用户名</label><input name="userName" type="text" class="scinput" value="${query.userName}"></li>
					<li><label>&nbsp;</label><input name="btnSearch" id="btnSearch"  type="submit" class="scbtn" value="查询"></li>
				</ul>
			
			<!--操作-->
			<div class="tools">
				<ul class="toolbar">
					<li><span class="add">新增用户</span></li>
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
		        { title:'用户名',width:100,name:'userName',align: 'center'},
		        { title:'姓名',width:100,name:'stafferName',align: 'center'},
		        { title:'部门',width:100,name:'departName',align:'center'},
		        { title:'单位',width:100,name:'unitName',align:'center'},
		        { title:'操作',width:100,name:'',align:'center',renderer:function(val,item,rowIndex){
		         var isEnable=item.isEnable==0?"锁定":"解锁"
		            if(item.userName=='sysAdministrator'){
		            return "<a href='javascript:lockOne("+item.userId+","+item.isEnable+")' class='tablelink lockone'>"+isEnable+"</a>&nbsp"
		            	  +"<a href='javascript:roleallot("+item.userId+")' class='tablelink permission'>角色分配</a>";
		            }
		        	return   "<a href='javascript:editOne("+item.userId+")' class='tablelink editone' >编辑</a>&nbsp"
							+"<a href='javascript:deleteOne("+item.userId+")' class='tablelink delone'>删除</a>&nbsp"
							+"<a href='javascript:lockOne("+item.userId+","+item.isEnable+")' class='tablelink lockone'>"+isEnable+"</a>&nbsp"
							+"<a href='javascript:roleallot("+item.userId+")' class='tablelink permission'>角色分配</a>";
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
						siteName: '用户配置'
					}
				}
			};
			topSite(siteData);
			</script>
	</body>

</html>