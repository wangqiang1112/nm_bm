<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/inc/taglibs.inc" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>系统异常审计</title>
	<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js" ></script>
   	<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
   	<script type="text/javascript" src="/yw-nm/resources/js/package-datagrid.js"></script>
	<script type="text/javascript">
		$(document).ready(function(e) {
			//下拉选择框渲染
			$(".select3").uedSelect({
				width: 152
			});
			//导出
			$(".set").click(function(){
						$("#export").submit();
						
			//window.location = "export.do";
			});
			//删除
			$(".del").click(function(){
					if(mmg.selectedRows().length == 0){
						alert("请选择要删除的信息");
						return;
					}
					var arr = [];
					for(var i = 0;i<mmg.selectedRows().length;i++){
						arr.push(mmg.selectedRows()[i].sysExceptId);
					}
					$("#sysIDs").val(arr.join(","));
					
					confirm("确认删吗？",function(){
				        var url="delete.do";
						$("#myform").attr("action",url);
						$("#myform").submit();
					}); 
			});
		});
	</script>
</head>
<body>
	<div class="rightinfo">
	    <form id="export" action="export.do" method="post">
	    	<input type="hidden" name="userName"  value="${query.userName}"/>
	    	<input type="hidden" name="eModuleName"  value="${query.eModuleName}"/>
	    	<input type="hidden" name="startDate"  value="${query.startDate }"/>
	        <input type="hidden" name="endDate"  value="${query.endDate }"/>
	        <input type="hidden" name="pageIndex"  value="${page.pageIndex}" />
	    </form>
		<form id="myform" action="list.do">
		   <input type="hidden" name="sysIDs" id="sysIDs" />
			<!--查询-->
			<ul class="seachform">
				<li><label>用户名</label><input name="userName" id="userName" type="text" class="scinput" value="${query.userName}"></li>
				<li><label>功能模块</label>
					<div class="vocation">
						<select class="select3" name="eModuleName" >
							<option value="">全部</option>
							<s:iterator value="#request.InitEModuleName" id="item" >
							   <option value=${item} <c:if test="${query.eModuleName==item}">selected</c:if>>${item}</option>
							</s:iterator>
						</select>	
					</div>
				</li>
				<li><label>操作时间</label>
					<input name="startDate" id="startDate" type="text" class="scinput" value="${query.startDate }" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})">
					<em>-</em>
					<input name="endDate" id="endDate" type="text" class="scinput" value="${query.endDate }" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})">
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
		        { title:'操作IP',width:100,name:'sysExceptIp',align:'center'},
		        { title:'功能模块',width:100,name:'eModuleName',align:'center'},
		        { title:'异常时间',width:100,name:'sysExceptTime',align:'center',renderer:function(val,item,rowIndex){
		        	//格式化日期
		        	return formatDate(val.time);
		        }},
		        { title:'异常内容',width:100,name:'exceptionCont',align:'center',renderer:function(val,item,rowIndex){
		        	//格式化日期
		        	return "<span title='"+item.exceptionCont+"'>"+item.exceptionCont+"</span>";
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
			siteName: '安全审计',
			siteChild: {
				siteName: '系统异常审计'
			}
		};
		topSite(siteData);
	</script>
</body>
</html>