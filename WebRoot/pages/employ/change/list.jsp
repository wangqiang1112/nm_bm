<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/inc/taglibs.inc" %>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>变更通知</title>
	<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js"></script>
	<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
	<script type="text/javascript" src="/yw-nm/resources/js/package-datagrid.js"></script>
	<script type="text/javascript">
		$(function(){
		});
		
		// 查看
		function check(id,unitId){
			openWindow('查看','/yw-nm/employ/getContrast.do?id='+id+'&unitId='+unitId,['600px','500px']);
		}
		
	</script>
</head>

<body>
	<div class="rightinfo">
		<form id="myform" action="/yw-nm/employ/photoList.do" method="post">
			<!--列表-->
			<table class="mmg-body" id="tablelist" >
			</table>
		<%@ include file="/commons/pager.jsp" %>
		</form>
	</div>
	<script type="text/javascript">
		$(function(){
			//列
    		var cols = [
		        { title:'变更信息',width:200,name:'unitName',align: 'center'},
		        { title:'变更时间',width:100,name:'updateTime',align:'center',renderer:function(val,item,rowIndex){
		        	//格式化日期
		        	var date = '';
		        	return date = item.updateTime.substring(0,19);
		        }},
		        { title:'操作',width:100,name:'',align:'center' ,renderer:function(val,item,rowIndex){
		        	// 查看
		        	return '<a href="#" class="tablelink" onclick="check('+item.id+','+item.unitId+')" >查看</a>' ;
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
		        checkCol:false
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
			siteName: '单位管理',
			siteChild: {
				siteName: '变更通知'
			}
		};
		topSite(siteData);
	</script>
</body>

</html>