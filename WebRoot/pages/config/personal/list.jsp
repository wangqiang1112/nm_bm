<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/inc/taglibs.inc" %>
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>人员查询</title>
		<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-datagrid.js"></script>
		<script type="text/javascript">
			$(document).ready(function(e) {
				//下拉选择框渲染
				$(".select3").uedSelect({
					width: 100
				});
				//新增
				$(".add").click(function(){
				 	 window.location.href = 'baseAdd.do';
				});
				$(".editone").click(function(){
					window.location.href = 'add.jsp';
				});
				//导入
				$(".set").click(function(){
                $("#file").click();
                });
                
                $("#file").change(function(){ 
                	if(this.value.indexOf("xls")==-1&&this.value.indexOf("xlsx")==-1){
	                	alert("格式错误,必须是xls或者xlsx格式");
	                	return false;
               		}
	                var size = 0;
	                if(this.files){
	                   size = this.files[0].size;
	                }
	                if(size>20971520)
	                {	
		                alert("文件总大小超过20M");
		                return false;
                	}
                	$("#importExcel").submit();
                });
               
				//批量删
				$(".del").click(function(){
				    var arr = [];
					for(var i = 0;i<mmg.selectedRows().length;i++){
						arr.push(mmg.selectedRows()[i].stafferId);
					}
					$("#stafferIds").val(arr.join(","));
					if(mmg.selectedRows().length==0){
					alert("请选择要删除的人员");
					return false;
					}
					confirm("确定删除吗",function(){
					$("#myform").attr("action","delete.do");
					$("#myform").submit();
				})
				});
				
			});
  		  //单个删除
		 function deleteOne(userId){
  		  confirm("确定删除吗",function(){
  		        $("#stafferIds").val(userId);
				$("#myform").attr("action","delete.do");
				$("#myform").submit();
				})
		 }
		 //编辑用户
		 function editOne(stafferId){
		 window.location.href = 'baseAdd.do?stafferId='+stafferId
		 }
		 function view(stafferId){
		 window.location.href='view.do?stafferId='+stafferId;
		 }
		</script>
	</head>

	<body>
		<div class="rightinfo">
		    <form id="importExcel" action="importExcel.do" method="post" enctype="multipart/form-data">
		    <input type="file" name="file" id="file" lay-type="file"  style="display:none"/>
		    </form>
			<form id="myform" action="list.do" method="post">
			<input type="hidden" name="stafferIds" id="stafferIds" />
				<!--查询-->
				<ul class="seachform">
					<li><label>部门名称</label><input name="SDepart" value="${query.SDepart}" type="text" class="scinput"></li>
					<li><label>职位名称</label><input name="SPost" type="text" value="${query.SPost}" class="scinput"></li>
					<li><label>姓名</label><input name="stafferName" type="text" value="${query.stafferName}" class="scinput"></li>
					<li><label>&nbsp;</label><input name="btnSearch" id="btnSearch"  type="submit" class="scbtn" value="查询"></li>
				</ul>
			
			<!--操作-->
			<div class="tools">
				<ul class="toolbar">
					<li><span class="add">新增</span></li>
					<li><span class="del">删除</span></li>
					<li><span class="data"><a href="importModel.do">模版下载</a></span></li>
					<li><span class="set">批量导入</span></li>
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
		        { title:'部门名称',width:100,name:'SDepart_str',align: 'center'},
		        { title:'姓名',width:100,name:'stafferName',align: 'center'},
		        { title:'职位',width:100,name:'SPost_str',align: 'center'},
		        { title:'密级',width:100,name:'level',align: 'center',renderer:function(val,item,rowIndex){
		        	return   item.level==1?"绝密级": item.level==2?"机密级": item.level==3?"秘密级":"未知";
		        }},
		        { title:'操作',width:100,name:'',align:'center',renderer:function(val,item,rowIndex){
		        	return   "<a href='javascript:editOne("+item.stafferId+")' class='tablelink editone' >编辑</a>&nbsp"
							+"<a href='javascript:deleteOne("+item.stafferId+")'class='tablelink delone'>删除</a>&nbsp"
							+"<a href='javascript:view("+item.stafferId+")'class='tablelink view'>预览</a>";
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
					siteName: '人员管理',
					siteChild: {
						siteName: '人员查询'
					}
				}
			};
			topSite(siteData);
			</script>
	</body>

</html>