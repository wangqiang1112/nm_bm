<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/inc/taglibs.inc" %>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>信息管理列表</title>
	<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js"></script>
	<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
	<script type="text/javascript" src="/yw-nm/resources/js/package-datagrid.js"></script>
	<script type="text/javascript">
		$(document).ready(function(e) {
		
			//下拉选择框渲染
			$(".select3").uedSelect({
				width: 100
			});
			//编辑
			$(".edit").click(function() {
				window.location.href = '/yw-nm/secrecy/jump.do';
			});
			//单个删除
			$(".tablelink").click(function() {
			});
		});
		
		function delInfo(articleId){
			confirm("确认删除 ?",function(){
				window.location.href = '/yw-nm/secrecy/delete.do?articleId='+articleId;
			});
		}
		
		function query(){
			$("#myform").submit();
		}
		
	</script>
</head>
<body>
	<form id="myform" action="/yw-nm/secrecy/index.do" method="post">
		<div class="rightinfo">
			<!--查询-->
			<ul class="seachform">
				<li><label>主题类别</label>
					<div class="vocation">
						<select class="select3" name="pid" >
							<option value="" >-请选择-</option>
							<s:iterator id="pid" value="topicType" status="list">
								<option value="${pid}" <s:if test="query.pid == #pid.pid">selected</s:if>>${topicType}</option>
							</s:iterator>
						</select>
					</div>
				</li>
				<li><label>部门名称</label>
					<div class="vocation">
						<select class="select3" name="departId" >
							<option value="" >-请选择-</option>
							<s:iterator id="depart" value="dept" status="list" >
								<option value="${departId}" <s:if test="query.departId == #depart.departId">selected</s:if>>${departName}</option>
							</s:iterator>
						</select>
					</div>
				</li>
				<li><label>主题</label>
					<input id="topic" name="topic" type="text" class="scinput" value="${query.topic}" >
				</li>
				<li><label>发布人</label>
					<input id="publisher" name="publisher" type="text" class="scinput" value="${query.publisher}" >
				</li>
				<li><label>发布时间</label>
					<input name="startDate" id="startDate" value="${query.startDate}" type="text" class="scinput" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})">
					<em>-</em>
					<input name="endDate" id="endDate"  value="${query.endDate}" type="text" class="scinput" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})">
				</li>
				<li><label>&nbsp;</label>
				<input name="btnSearch" id="btnSearch" type="submit" class="scbtn" value="查询" onclick="query();"></li>
			</ul>
			<!--操作-->
			<div class="tools">
				<ul class="toolbar">
					<li><span class="edit" >发布信息</span></li>
				</ul>
			</div>
			<!--列表-->
			<table id="tablelist">
			</table>
		</div>
		<%@ include file="/commons/pager.jsp" %>
		<%@ include file="/commons/message.jsp" %>
	</form>
</body>
	<script type="text/javascript">
		$(function(){
			//列
    		var cols = [
		        { title:'主题类别',width:100,name:'typeName',align: 'center'},
		        { title:'部门名称',width:100,name:'departName',align:'center'},
		        { title:'发布人',width:100,name:'publisher',align:'center'},
		        { title:'主题',width:100,name:'topic',align:'center'},
		        { title:'密级',width:100,name:'articleLevel',align:'center',renderer:function(val,item,rowIndex){
		        	// 密级
		        	if(item.articleLevel == 1){
		        		return '秘密级'
		        	}
		        	if(item.articleLevel == 2){
		        		return '机密级'
		        	}
		        	if(item.articleLevel == 3){
		        		return '绝密级'
		        	}
		        }},
		        { title:'操作',width:100,name:'',align:'center',renderer:function(val,item,rowIndex){
		        	// 操作
		        		return '<a href="#" class="tablelink" onclick="delInfo('+item.articleId+')" >删除</a>' ;
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
			siteName: '配置管理',
			siteChild: {
				siteName: '信息管理',
				siteChild: {
					siteName: '信息发布'
				}
			}
		};
		topSite(siteData); 
	</script>
</html>