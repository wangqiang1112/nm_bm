<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/inc/taglibs.inc" %>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>资料查询</title>
	<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js"></script>
	<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
	<script type="text/javascript" src="/yw-nm/resources/js/package-datagrid.js"></script>
	<script type="text/javascript">
		$(document).ready(function(e) {
			//下拉选择框渲染
			$(".select3").uedSelect({
				width: 100
			});
			//查询
			$("#btnSearch").click(function(){
				$("#myform").attr("action","/yw-nm/employ/list.do");
				$("#myform").submit();
			});
		});
		
		// 基本信息(保密)
		function detail(unitId){
			openWindow('基本信息','/yw-nm/employ/detail.do?unitId='+unitId,['500px','400px']);
		}
		
		// 基本信息(单位)
		function detailPhoto(id){
			openWindow('基本信息','/yw-nm/employ/detail.do?id='+id,['500px','400px']);
		}
		
		// 变更对比
		function change(unitId){
			openWindow('变更对比','/yw-nm/employ/getContrast.do?unitId='+unitId,['500px','400px']);
		}
		
	</script>
</head>

<body>
	<div class="rightinfo">
		<form id="myform" action="/yw-nm/employ/list.do" method="post">
		<input type="hidden" name="page1" id="page1" value="2" />
			<!--查询-->
			<s:if test="query.level != 1" >
			<ul class="seachform">
				<li><label>单位名称</label><input name="unitName" type="text" class="scinput" value="${query.unitName}"></li>
				<li><label>所在地市</label><div class="vocation">
						<select class="select3" name="unitCity">
							<option value="" >全部</option>
							<s:iterator id="unitCity" value="cityMap" status="list" >
								<option value="${Pid}" <s:if test="query.unitCity == #unitCity.Pid">selected</s:if>>${City}</option>
							</s:iterator>
						</select>
					</div></li>
				<li><label>单位性质</label><div class="vocation">
						<select class="select3" name="unitProperty">
							<option value="">全部</option>
							<s:iterator id="unitProperty" value="propertyMap" status="list" >
								<option value="${Pid}" <s:if test="query.unitProperty == #unitProperty.Pid">selected</s:if>>${Property}</option>
							</s:iterator>
						</select>
					</div></li>
				<li><label>状态</label>
					<div class="vocation">
						<select id="checkStatus" class="select3" name="checkStatus">
							<option value="0" <s:if test="query.checkStatus == 0">selected</s:if> >全部</option>
							<option value="1" <s:if test="query.checkStatus == 1">selected</s:if> >待审核</option>
							<option value="2" <s:if test="query.checkStatus == 2">selected</s:if> >已通过</option>
							<option value="3" <s:if test="query.checkStatus == 3">selected</s:if> >未通过</option>
						</select>
					</div></li>
				<li><label>&nbsp;</label><input name="btnSearch" id="btnSearch"  type="submit" class="scbtn" value="查询"></li>
			</ul>
			</s:if>
			<!--列表-->
			<table id="tablelist" >
			</table>
		<%@ include file="/commons/pager.jsp" %>
		</form>	
	</div>
	<script type="text/javascript">
		$(function(){
			//列
    		var cols = [
		        { title:'单位名称',width:100,name:'unitName',align: 'center'},
		        { title:'所在地市',width:100,name:'cityName',align:'center'},
		        { title:'单位性质',width:100,name:'property',align:'center'},
		        { title:'联系人',width:100,name:'principal',align:'center'},
		        { title:'审核状态',width:100,name:'checkStatus',align:'center',renderer:function(val,item,rowIndex){
		        	// 审核状态
		        	if(item.checkStatus == 1){
		        		return '<p style="color:blue" >待审核</p>'
		        	}
		        	if(item.checkStatus == 2){
		        		return '<p style="color:green" >已通过</p>'
		        	}
		        	if(item.checkStatus == 3){
		        		return '<p style="color:red" >未通过</p>'
		        	}
		        }},
		        { title:'更改时间',width:100,name:'updateTime',align:'center',renderer:function(val,item,rowIndex){
		        	//格式化日期
		        	var date = '';
		        	return date = item.updateTime.substring(0,19);
		        }},
		        { title:'操作',width:80,name:'exceptionCont',align:'center',renderer:function(val,item,rowIndex){
		        	var str = '';
		        	// 保密用户的操作
		        	if(item.id == 0){
		        		str += '<a href="#" class="tablelink" onclick="detail('+item.unitId+')" >基本信息</a>&nbsp;&nbsp;';
		        		str += '<a href="#" class="tablelink" onclick="change('+item.unitId+')" >变更对比</a>' ;
		        	}
		        	// 单位用户
		        	else{
		        		str += '<a href="#" class="tablelink" onclick="detailPhoto('+item.id+')" >基本信息</a>';	
		        	}
		        	return str ;
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
		    	$('#tablelist').find("tr:first-child").addClass("selected");
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
				siteName: '资料查询'
			}
		};
		topSite(siteData);
		</script>
</body>

</html>