<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/inc/taglibs.inc" %>
<%@ include file="/commons/pager.jsp" %>
<%@ include file="/commons/message.jsp" %>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>任务处理</title>
	<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js"></script>
	<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
	<script type="text/javascript" src="/yw-nm/resources/js/package-datagrid.js"></script>
	<script type="text/javascript">
		$(document).ready(function(e) {
			//下拉选择框渲染
			$(".select3").uedSelect({
				width: 152
			});
			//查询
			$("#btnSearch").click(function(){
				$("#myform").submit();
			});
		});
		
		// 确认接收
		function confirm(id){
			$("#myform").attr("action","/yw-nm/taskUnit/update.do?pageType=1&status=1&taskId="+id);
			$("#myform").submit();
		}
		
		// 处理跳转
		function handle(id){
			openWindow('任务处理','/yw-nm/taskUnit/jump.do?pageType=1&taskId='+id,['800px','500px']);
		}
		
		// 查看跳转
		function detail(id){
			openWindow('任务处理','/yw-nm/taskUnit/jump.do?pageType=2&taskId='+id,['800px','500px']);
		}
		
	</script>
</head>

<body>
	<div class="rightinfo">
		<form id="myform" method="post" action="/yw-nm/taskUnit/list.do?pageType=1">
			<!--查询-->
			<ul class="seachform">
				<li><label>任务名称</label><input name="taskName" type="text" class="scinput" value="${query.taskName}" ></li>
				<li><label>下发部门</label><div class="vocation">
					<select class="select3" name="departId">
						<option value="" >全部</option>
						<s:iterator id="departId" value="deptList" status="list" >
							<option value="${DepartID}"<s:if test="query.departId == #departId.DepartID">selected</s:if>>${DepartName}</option>
						</s:iterator>
					</select>
				</div></li>
				<li><label>下发时间</label><input name="issuedTime" type="text" class="scinput" onfocus="WdatePicker()" value="${query.issuedTime}"></li>
				<li><label>&nbsp;</label><input name="btnSearch" id="btnSearch"  type="submit" class="scbtn" value="查询"></li>
			</ul>
		
			<!--列表-->
			<table id="tablelist">
			</table>
			
		</form>
	</div>
	<script type="text/javascript">
	$(function(){
		//列
   		var cols = [
	        { title:'任务名称',width:100,name:'taskName',align: 'center'},
	        { title:'下发部门',width:100,name:'departName',align:'center'},
	        { title:'任务描述',width:100,name:'taskDescript',align:'center'},
	        { title:'任务附件',width:100,name:'taskFilePath',align:'center',renderer:function(val,item,rowIndex){
		        return '<a href="/yw-nm'+ item.taskFilePath +'">'+ item.taskFileName +'</a>';
		    }},
	        { title:'下发时间',width:80,name:'issuedTime_str',align:'center'},
	        { title:'任务状态',width:80,name:'status',align:'center',renderer:function(val,item,rowIndex){
	        	// 任务状态
	        	if(item.status == 1){
	        		return '<p>已下发</p>';
	        	}
	        	if(item.status == 2){
	        		return '<p>已接收</p>';
	        	}
	        	if(item.status == 3){
	        		return '<p>已打回</p>';
	        	}
	        	if(item.status == 4){
	        		return '<p>已上报</p>';
	        	}
	        	
	        }},
	        { title:'任务处理',width:80,name:'',align:'center',renderer:function(val,item,rowIndex){
		        if(item.clas != 0){
			        if(item.status == 1){
		        		return '<a href="#" onclick="confirm('+ item.taskId +')" class="tablelink" >确认接收</a>';
		        	}
		        	if(item.status == 2){
		        		return '<a href="#" onclick="handle('+ item.taskId +')" class="tablelink" >处理</a>';
		        	}
		        	if(item.status == 3){
		        		return '<a href="#" onclick="handle('+ item.taskId +')" class="tablelink" >处理</a>';
		        	}
		        	if(item.status == 4){
		        		return '<a href="#" onclick="detail('+ item.taskId +')" class="tablelink" >查看详情</a>';
		        	}
	        	}else{
		        	return '<a href="#" onclick="detail('+ item.taskId +')" class="tablelink" >查看详情</a>';
	        	}
	        }}
	    ];
		//本地示例
	    mmg =  $('#tablelist').mmGrid({
	    	items:${jsondata},//后台列表传过来的JSON数据
	        cols:cols,
	        height:'%',
	        width:'%',
	        multiSelect:false,
	        fullWidthRows:true,
	        showBackboard:false,
	        nowrap:true,
	        indexCol:true,
	        checkCol:false,
	        indexColWidth:20
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
			siteName: '业务管理',
			siteChild: {
				siteName: '任务接收',
				siteChild: {
					siteName: '任务处理'
				}
			}
		};
		topSite(siteData);
	</script>
</body>

</html>