<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>部门人员列表</title>
		<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-datagrid.js"></script>
		<script type="text/javascript">
			$(function(){
				$(".back").click(function(){
					
				});
			});
			function detail(stafferId){
			 var url=window.location.host+'/yw-nm/config/personal/view.do?stafferId='+stafferId;
			 parent.location.href="http://"+url;
			 closeWindow();
			}
		</script>
	</head>

	<body>
	<form id="myform">
		<div class="rightinfo">
			<!--操作-->
			<!--  <div class="tools">
				<ul class="toolbar">
					<li><span class="back">返回</span></li>
				</ul>
			</div>-->
			<!--列表-->
			<table id="tablelist" >
			</table>
			<%@ include file="/commons/pager.jsp" %>
			<%@ include file="/commons/message.jsp" %>
			</form>
		</div>
		<script type="text/javascript">
		$(function(){
		                     
			//列
    		var cols = [
		        { title:'部门名称',width:100,name:'SDepart_str',align: 'center'},
		        { title:'姓名',width:100,name:'stafferName',align: 'center'},
		        { title:'职位',width:100,name:'SPost_str',align: 'center'},
		        { title:'密级',width:100,name:'level',align: 'center',renderer:function(val,item,rowIndex){
		        	return   item.level==1?"绝密级": item.level==2?"机密级": item.level==3?"秘密级":"未知";
		        }},
		        { title:'操作',width:100,name:'',align:'center',renderer:function(val,item,rowIndex){
		        	return   "<a href='javascript:detail("+item.stafferId+")' class='tablelink'>查看人员详情</a>"
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
						siteName: '部门配置',
						siteChild: {
							siteName: '人员列表'
						}
					}
				}
			};
			topSite(siteData);
		</script>
	</body>

</html>