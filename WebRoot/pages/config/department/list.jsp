<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>部门配置</title>
		<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-datagrid.js"></script>
		<script type="text/javascript">
			$(document).ready(function(e) {
				//新增
				$(".add").click(function(){
					openWindow("新增部门","addOneDept.do?type=add",['540px','500px']);
				});
			});
			function deleteOne(departID){
			$.ajax({
					url:"/yw-nm/config/department/checkStaff.do",
					type:"POST",
					data:{SDepart:departID},
					dataType:"text",
					success:function(result){
						if(result=='true'){
						alert("该部门下还存在员工");
					}else{
					confirm("确定删除该部门",function(){
					    $("#myform").attr("action","delete.do?departId="+departID);
						$("#myform").submit();
						})
						}
					},
				});
			
			}
			function editOne(departID){
			 openWindow('编辑部门','addOneDept.do?type=edit&departId='+departID,['540px','500px']);
			}
			function searchStaff(departID){
			openWindow("部门人员列表","searchStaff.do?departID="+departID,["580px","450px"]);
			}
		</script>
	</head>

	<body>
		<div class="rightinfo">
			<form id="myform" action="list.do" method="post">
				<!--查询-->
				<ul class="seachform">
					<li><label>部门名称</label><input name="departName" value="${query.departName}" type="text" class="scinput"></li>
					<li><label>负责人</label><input name="DPrincipal" value="${query.DPrincipal}" type="text" class="scinput"></li>
					<li><label>&nbsp;</label><input name="btnSearch" id="btnSearch"  type="submit" class="scbtn" value="查询"></li>
				</ul>
			<!--操作-->
			<div class="tools">
				<ul class="toolbar">
					<li><span class="add">新增</span></li>
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
		        { title:'部门名称',width:100,name:'departName',align: 'center'},
		        { title:'负责人',width:100,name:'DPrincipal',align: 'center'},
		        { title:'职位',width:100,name:'DPrincPost',align:'center'},
		        { title:'部门描述',width:100,name:'DDescription',align:'center'},
		        { title:'操作',width:100,name:'',align:'center',renderer:function(val,item,rowIndex){
		        	return   "<a href='javascript:editOne("+item.departID+")' class='tablelink editone' >编辑</a>&nbsp"
							+"<a href='javascript:deleteOne("+item.departID+")' class='tablelink delone'>删除</a>&nbsp"
							+"<a href='javascript:searchStaff("+item.departID+")' class='tablelink permission'>查看人员列表</a>";
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
						siteName: '部门配置'
					}
				}
			};
			topSite(siteData);
			</script>
	</body>

</html>