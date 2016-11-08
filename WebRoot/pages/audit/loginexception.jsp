<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/inc/taglibs.inc" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>登录异常审计</title>
		<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js" ></script>
	   	<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
	   	<script type="text/javascript" src="/yw-nm/resources/js/package-datagrid.js"></script>
		<script type="text/javascript">
			$(document).ready(function(e) {
				//下拉选择框渲染
				$(".select3").uedSelect({
					width: 100
				});
				//导出
				$(".set").click(function(){
					$("#export").submit();
				});
				//删除
			    $(".del").click(function(){
			    	if(mmg.selectedRows().length==0){
						alert("请选择要删除的信息");
						return;
					}
					var arr = [];
					for(var i = 0;i<mmg.selectedRows().length;i++){
						arr.push(mmg.selectedRows()[i].loginExceptId);
					}
					$("#logIds").val(arr.join(","));
					confirm("确认删吗？",function(){
				        var url="delete.do";
						$("#myform").attr("action",url);
						$("#myform").submit();
					}); 
			});
			})
		</script>
	</head>

	<body>
	    <form id="export" action="export.do" method="post">
		    <input name="userName" type="hidden"value="${query.userName}">
		    <input name="startDate"  value="${query.startDate }" type="hidden" >
			<input name="endDate" value="${query.endDate }" type="hidden">
			<input type="hidden" name="pageIndex"  value="${page.pageIndex}" />
	    </form>
		<div class="rightinfo">
			<form id="myform" action="list.do" method="post">
			<input type="hidden"  name="logIds" id="logIds"/>
				<!--查询-->
				<ul class="seachform">
					<li><label>用户名</label><input name="userName" type="text" class="scinput" value="${query.userName}"></li>
					<li><label>操作时间</label>
						<input name="startDate" id="startDate" value="${query.startDate }" type="text" class="scinput" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})">
						<em>-</em>
						<input name="endDate" id="endDate" value="${query.endDate }" type="text" class="scinput" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})">
					</li>
					<li><label>&nbsp;</label><input name="btnSearch" id="btnSearch"  type="submit" class="scbtn" value="查询"></li>
				</ul>
			<!--操作-->
			<div class="tools">
				<ul class="toolbar">
					<li><span class="set">导出Excel</span></li>
					<li><span class="del">删除</span></li>
				</ul>
			</div>
			<!--列表-->
			<table  id="tablelist">
			</table>
			<!--分页-->
			<%@ include file="/commons/pager.jsp" %>
			<%@ include file="/commons/message.jsp" %>
		</form>
		</div>
	</body>
	<script type="text/javascript">
		$(function(){
			//列
    		var cols = [
		        { title:'用户名',width:100,name:'userName',align: 'center'},
		        { title:'操作IP',width:100,name:'loginExceptIp',align:'center'},
		        { title:'操作时间',width:100,name:'loginExceptTime',align:'center',renderer:function(val,item,rowIndex){
		        	//格式化日期
		        	return formatDate(val.time);
		        }},
		        { title:'异常内容',width:100,name:'LException',align:'center'}
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
			siteName: '安全审计',
			siteChild: {
				siteName: '登录异常审计'
			}
		};
		topSite(siteData);
	</script>
</html>