<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/inc/taglibs.inc" %>
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>部门分析</title>
		<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/lib/echarts/echarts.min.js" ></script>
		<script type="text/javascript">
			$(document).ready(function(e) {
				//下拉选择框渲染
				$(".select3").uedSelect({
					width: 100
				});
				//全选
				$("#ckall").click(function(){
					$(".tablelist input[type=checkbox]").each(function(){
						if($(this).attr("id")!="ckall"){
							$(this).prop("checked",$("#ckall").prop("checked"));
							
							//批量删除的条件组装，写在这里
						}
					});
				});
				dateFmt = 'yyyy';
				issQuarter = false;
				disabledDates=[''];
				//时间单位
				$("#timeUnit").change(function(){
					$('#startTime_str').val("");
					$('#endTime_str').val("");
					
					 if($(this).val()=="1"){
					 	dateFmt = 'yyyy';
					 }
					 if($(this).val()=="2"){
						dateFmt = 'yyyy年MM季度';
						disabledDates=['....-0[5-9]-..','....-1[0-2]-..'];
						startDate='%y-01-01',
					 	issQuarter=true;
					 }
					 if($(this).val()=="3"){
					 	dateFmt = 'yyyy-MM';
					 }
				});
				//重新载入时间单位
				var unit=$('#unit').val();
				if(unit!=""&&unit!=0){
					if("1"==unit){
						dateFmt = 'yyyy';
					}else if("2"==unit){
						dateFmt = 'yyyy年MM季度';
						startDate='%y-01-01',
					 	issQuarter=true;
					}else if("3"==unit){
						dateFmt = 'yyyy-MM';
					}
				}
			});
		</script>
	</head>

	<body>
		<div class="rightinfo">
			<form id="form1" action="depart.do" method="post">
				<!--查询-->
				<ul class="seachform">
					<li><label>时间粒度</label>
						<div class="vocation">
							<input type="hidden" id="unit" value="${query.timeUnit}"/>
							<select class="select3" id="timeUnit" name="timeUnit">
								<!-- <option value="">-请选择-</option> -->
								<option
									<c:if test="${query.timeUnit==1}">selected='selected'</c:if>
									value="1">年
								</option>
								<option
									<c:if test="${query.timeUnit==2}">selected='selected'</c:if>
									value="2">季
								</option>
								<option
									<c:if test="${query.timeUnit==3}">selected='selected'</c:if>
									value="3">月
								</option>
							</select>
						</div>
					</li>
					<li><label>时间</label>
						<input name="startTime_str" value="${query.startTime_str}" id="startTime_str" type="text" class="scinput" 
							onFocus="WdatePicker(
								{	
									dateFmt:dateFmt,
									isQuarter:issQuarter,
									disabledDates:disabledDates,
									isShowOK:false,
									maxDate:'#F{$dp.$D(\'endTime_str\')}'
								})">
						<em>-</em>
						<input name="endTime_str" value="${query.endTime_str}" id="endTime_str" type="text" class="scinput" 
							onFocus="WdatePicker(
								{	
									dateFmt:dateFmt,
									isQuarter:issQuarter,
									disabledDates:disabledDates,
									isShowOK:false,
									minDate:'#F{$dp.$D(\'startTime_str\')}'
								})">
					</li>
					<li><label>&nbsp;</label><input name="btnSearch" id="btnSearch"  type="submit" class="scbtn" value="查询"></li>
				</ul>
			
			<!--操作-->
			<div id="echart" style="height:355px;width:auto"></div>
			<div style="height:50px"></div>
			<!--列表-->
			<table class="tablelist" style="margin-top:20px;">
				<thead>
					<tr>
						<th>序号</th>
						<th>部门名称</th>
						<th>任务下发量</th>
						<th>已关闭</th>
						<th>完成百分比</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#request.departmentAnalyseList" var="var">
					<tr>
						<td><s:property value="#var.num"/></td>
						<td><s:property value="#var.departName"/></td>
						<td><s:property value="#var.deptAmount"/></td>
						<td><s:property value="#var.closeAmount"/></td>
						<td><s:property value="#var.percent"/>%</td>
					</tr>		
					</s:iterator>
				</tbody>
			</table>
			</form>
		</div>
	</body>
	<script type="text/javascript">
		$(function(){
			var myChart = echarts.init(document.getElementById("echart"));
			/**
			 * 此部分数据通过ajax或直接请求的方式加载
			 * 每个数组下标数据一一对应
			 */
			//部门
			var depts=new Array();
			var deptAmount=new Array();
			var closeAmount=new Array();
			var percent=new Array();
			<c:forEach items="${departmentAnalyseList}" var="t">
			depts.push('${t.departName}');
			deptAmount.push('${t.deptAmount}');
			closeAmount.push('${t.closeAmount}');
			percent.push('${t.percent}');
			</c:forEach>
			/* var depts = ['督导中心','法规处','科技处','综合处','测评中心','销毁中心'];
			//任务下发量
			var deptAmount = [20, 49, 70, 23, 25, 76];
			//已关闭量
			var closeAmount = [26, 59, 90, 24, 27, 70];
			//完成百分比
			var percent = [20, 22, 33, 45, 63, 12]; */
			option = {
				title:{text:'部门分析',textStyle:{fontWeight:'normal'},left:'center'},
			    tooltip: {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['任务下发量','已关闭','完成百分比'],
			        left:'right',
			        top:'middle',
			        orient:'vertical'
			    },
			    grid:{
			    	left:'60px',
			    	right:'150px'
			    },
			    xAxis: [
			        {
			            type: 'category',
			            splitLine:{
	  						 show:false
						 },
	 					axisLabel:{
	  					 	interval:0,
	  						 /* rotate: -20 */
	  					 	formatter: function(v) {
								return v = v.replace(/(.{6})/g,'$1\n');
							}
					 	},
			            data: depts
			        }
			    ],
			    yAxis: [
			        {
			            type: 'value',
			            name: '任务量',
			            min: 0,
			            max: '${department_max}',
			            interval: ${department_interval},
			            axisLabel: {
			                formatter: '{value}'
			            }
			        },
			        {
			            type: 'value',
			            name: '百分比',
			            min: 0,
			            max:100,
			            interval: 10,
			            axisLabel: {
			                formatter: '{value} %'
			            }
			        }
			    ],
			    series: [
			        {
			            name:'任务下发量',
			            type:'bar',
			            data:deptAmount,
			            itemStyle:{normal:{color:'#329df0'}}
			        },
			        {
			            name:'已关闭',
			            type:'bar',
			            data:closeAmount,
			            itemStyle:{normal:{color:'#eaeaea'}}
			        },
			        {
			            name:'完成百分比',
			            type:'line',
			            yAxisIndex: 1,
			            data:percent,
			            itemStyle:{normal:{color:'#ff8c53'}}
			        }
			    ]
			};

			myChart.setOption(option);
			
			window.onresize = function(){
					myChart.resize();
			}
		});
		
	</script>
	<script type="text/javascript">
		//设置头部位置
		var siteData = {
			siteName: '统计分析',
			siteChild: {
				siteName: '任务处理分析',
				siteChild: {
					siteName: '部门分析'
				}
			}
		};
		topSite(siteData);
	</script>
</html>