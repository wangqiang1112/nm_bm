<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/inc/taglibs.inc" %>

<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>任务查询</title>
		<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-datagrid.js"></script>
		<script type="text/javascript">
		   $(document).ready(function() {
		   		var leftWin=window.top.document.getElementById("leftFrame").contentWindow;
		   		var obj=leftWin.$('li[class="selected2"]');
		   		//alert(obj.children().eq(0).html());
		   		if("新建任务"==obj.children().eq(0).html()){
		   			var obj2=leftWin.$('.list li[class="selected2"]').parent('ul').next();
		   			var obj3=obj2.children().eq(0);
		   			leftWin.$('.list li').removeClass('selected2');
		   			obj3.addClass('selected2');
		   		}
		   			
		   });
			$(document).ready(function(e) {
				
				$(".select3").uedSelect({
					width: 152
				});
				//查询
				$("#btnSearch").click(function(){
				});
				//新增
				$(".add").click(function(){
					///yw-nm/business/
					window.location.href="taskAdd.do";
				});
				//删除
				$(".del").click(function(){ 
					if(mmg.selectedRows().length==0){
						alert("请选择删除项");
						return;
					}
				    var arr = [];
					for(var i = 0;i<mmg.selectedRows().length;i++){
						arr.push(mmg.selectedRows()[i].taskId);
					}
					$("#taskIds").val(arr.join(","));
					confirm("确定删除吗",function(){
					$("#myform").attr("action","taskDel.do");
					$("#myform").submit();
					});
				});
			});
				function zzy(url){
					//String url=encodeURI(url);
					window.location.href=url;
				}
			    //编辑
				function editOne(taskId){
					window.location.href="taskEdit.do?taskId="+taskId;
				}
				
		  		 //单个删除
				 function deleteOne(taskId){
		  			confirm("确定删除吗",function(){
		  		        $("#taskIds").val(taskId);
						$("#myform").attr("action","taskDel.do");
						$("#myform").submit();
						}
					);
				}
			
				//详情
				function detail(taskId){
					openWindow('查看详情','taskDetail.do?taskId='+taskId,['570px','390px']);
				}	
		</script>
	</head>

	<body>
		<div class="rightinfo">
			<form id="myform" action="taskList.do" method="post">
				<!--查询-->
				<input name="taskId"  id="taskId" type="hidden"/>
				<input name="taskIds" id="taskIds"type="hidden"/>
				<ul class="seachform">
					<li><label>下发部门</label><div class="vocation">
						<select class="select3" name="departId">
							<option value="" >全部</option>
							<s:iterator id="departId" value="deptList" status="list" >
								<option value="${DepartID}"<s:if test="query.departId == #departId.DepartID">selected</s:if>>${DepartName}</option>
							</s:iterator>
						</select>
					</div></li>
					<li>
						<label>任务名称</label>
						<input name="taskName" type="text" 
							class="scinput" value="${query.taskName}"/>
					</li>
					<li>
						<label>任务编号</label>
						<input name="taskNumber" type="text"
							class="scinput" value="${query.taskNumber}"/>
					</li>
					<li>
						<label>处理状态</label>
						<div class="vocation">
							<select name="status"class="select3">
								<option>-请选择-</option>
								<option
									<c:if test="${query.status==1}">selected='selected'</c:if>
									value="1">已下发
								</option>
								<option
									<c:if test="${query.status==2}">selected='selected'</c:if>
									value="2">已接收
								</option>
								<option
									<c:if test="${query.status==3}">selected='selected'</c:if>
									value="3">已打回
								</option>
								<option
									<c:if test="${query.status==4}">selected='selected'</c:if>
									value="4">已上报
								</option>
								<option
									<c:if test="${query.status==5}">selected='selected'</c:if>
									value="5">已关闭
								</option>
							</select>
						</div>
					</li>
					<li><label>&nbsp;</label><input name="btnSearch" id="btnSearch"  type="submit" class="scbtn" value="查询"></li>
				</ul>
			
			<!--操作-->
			<div class="tools">
				<ul class="toolbar">
					<li><span class="add">新增</span></li>
					<li><span class="del">删除</span></li>
					<!--<li><span class="set">打回</span></li>-->
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
			var domain='${DOMAIN}';
    		var cols = [
    			{ title:'id',width:0,name:'taskId',align: 'center',hidden:true},	
		        { title:'任务编号',width:100,name:'taskNumber',align: 'center'},	
		        { title:'任务名称',width:100,name:'taskName',align: 'center'},
		        { title:'下发部门',width:100,name:'departName',align: 'center'},
		        { title:'任务描述',width:100,name:'taskDescript',align:'center'},
		        { title:'接收单位',width:100,name:'receiveUnit_name',align:'center'},
		        { title:'任务附件',width:100,name:'taskFileName',align:'center',
		        	renderer:function(val,item,rowIndex){
		        		//return "<a href=javascript:zm('${DOMAIN}"+item.taskFilePath+"')>"+item.taskFileName+"</a>&nbsp";
		        	//	return "<a href=${DOMAIN}"+item.taskFilePath+">"+item.taskFileName+"</a>&nbsp";
		        	return "<a href=${DOMAIN}"+encodeURI(item.taskFilePath)+">"+item.taskFileName+"</a>&nbsp";
		        	}
		        },
		        { title:'处理时间',width:100,name:'processTime_str',align:'center'},
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
		        	if(item.status == 5){
		        		return '<p>已关闭</p>';
		        	}
		        	
		        }},
		        { title:'操作',width:100,name:'',align:'center',
		        	renderer:function(val,item,rowIndex){
						if(item.status==1){
		        		 return "<a href='javascript:detail("+item.taskId+")' class='tablelink '>查看详情</a>&nbsp"+
		        		 	"<a href='javascript:editOne("+item.taskId+")' class='tablelink editone' >编辑</a>&nbsp"+
							"<a href='javascript:deleteOne("+item.taskId+")' class='tablelink delone'>删除</a>&nbsp";
		        		}
		        		if(item.status>1){
		        			 return "<a href='javascript:detail("+item.taskId+")' class='tablelink '>查看详情</a>&nbsp"+
		        			 "<span style='color:grey'>编辑</span>&nbsp"+
							"<a href='javascript:deleteOne("+item.taskId+")' class='tablelink delone'>删除</a>&nbsp";
		        		}
		        	}
		        },
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
		    	
		    	var opratId=${query.opratId};
		    	var arrData = mmg.rows();
		    	for(var i = 0;i<arrData.length;i++){
		    		if(arrData[i].taskId==opratId){
		    			mmg.select(i);
		    			break;
		    		}
		    	}
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
					siteName: '任务发布',
					siteChild: {
						siteName: '任务查询'
					}
				}
			};
			topSite(siteData);
			</script>
	</body>

</html>