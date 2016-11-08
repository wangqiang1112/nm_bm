<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/inc/taglibs.inc" %>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>资料审核</title>
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
			//通过
			$(".adopt").click(function(){
				if(mmg.selectedRows().length == 0){
					alert("请选择通过资料");
					return;
				}
				var arr = [];
				for(var i = 0;i<mmg.selectedRows().length;i++){
					arr.push(mmg.selectedRows()[i].unitId);
				};
				$("#unitIds").val(arr.join(","));
				var type2 = document.getElementById("type");
				type2.value = "2";
				$("#myform").attr("action","/yw-nm/employ/update.do");
				$("#myform").submit();
			});
			//打回
			$(".return").click(function(){
				if(mmg.selectedRows().length == 0){
					alert("请选择打回资料");
					return;
				}
				var arr = [];
				for(var i = 0;i<mmg.selectedRows().length;i++){
					arr.push(mmg.selectedRows()[i].unitId);
				};
				$("#unitIds").val(arr.join(","));
				var type2 = document.getElementById("type");
				type2.value = "3";
				$("#myform").attr("action","/yw-nm/employ/update.do");
				$("#myform").submit();
			});
		});
		
		// 审核跳转
		function check(unitId){
			openWindow('资料审核','/yw-nm/employ/detail.do?type=1&unitId='+unitId,['500px','400px']);
		}
		
		
	</script>
</head>

<body>
	<div class="rightinfo">
		<form id="myform" action="/yw-nm/employ/list.do" method="post">
		<input type="hidden" name="unitIds" id="unitIds" />
		<input type="hidden" name="type" id="type"/>
		<input type="hidden" name="page1" id="page1" value="1" />
			<!--查询-->
			<ul class="seachform">
				<li><label>单位名称</label><input name="unitName" type="text" class="scinput" value="${query.unitName}"></li>
				<li><label>所在地市</label><div class="vocation">
						<select class="select3" name="unitCity">
							<option value="" >全部</option>
							<s:iterator id="unitCity" value="cityMap" status="list" >
								<option value="${Pid}"<s:if test="query.unitCity == #unitCity.Pid">selected</s:if>>${City}</option>
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
				<li><label>&nbsp;</label><input name="btnSearch" id="btnSearch"  type="submit" class="scbtn" value="查询" ></li>
			</ul>
		<!--操作-->
		<div class="tools">
			<ul class="toolbar">
				<li><span class="adopt">通过</span></li>
				<li><span class="return">打回</span></li>
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
		        { title:'单位名称',width:100,name:'unitName',align: 'center'},
		        { title:'所在地市',width:100,name:'cityName',align:'center'},
		        { title:'单位性质',width:100,name:'property',align:'center'},
		        { title:'联系人',width:100,name:'principal',align:'center'},
		        { title:'审核状态',width:80,name:'checkStatus',align:'center',renderer:function(val,item,rowIndex){
		        	//审核状
		        	return '<a href="#" onclick="check('+ item.unitId +')" class="tablelink" >待审核</a>';
		        	
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
		        checkCol:true,
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
			siteName: '单位管理',
			siteChild: {
				siteName: '资料审核'
			}
		};
		topSite(siteData);
	</script>
</body>
</html>