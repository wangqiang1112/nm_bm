<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/inc/taglibs.inc" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>新建任务</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-validform.js"></script>
		<script type="text/javascript">
			$(function() {	
				var leftWin=window.top.document.getElementById("leftFrame").contentWindow;
		   		var obj=leftWin.$('li[class="selected2"]');
		   		if("任务查询"==obj.children().eq(0).html()){
		   			var obj2=leftWin.$('.list li[class="selected2"]').parent('ul').prev();
		   			var obj3=obj2.children().eq(0);
		   			leftWin.$('.list li').removeClass('selected2');
		   			obj3.addClass('selected2');
		   		}
						
				//下拉选择框渲染
				$(".select3").uedSelect({
					width: 504
				});
				//基本信息验证
				$("#form1").Validform({
					tipSweep:true, //在按钮提交时，进行验证
					tiptype:function(msg){
						layer.msg(msg);
					},
					datatype:{
						"idcard":function(gets,obj,curform,regxp){
							    return isIdCardNo($(obj).val());
						 },
						 "taskName":function(gets,obj,curform,regxp){
						 	var reg=/^[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
						 	if(reg.test(gets)){
						 		return true;
						 	}else{
						 		return false;
						 	}
						 }
					}
				});
				
				layui.use('upload',function(){
					layui.upload({
						url:'${DOMAIN}/uploadFile.do',
						//ext:'jpg|png|gif',
						ext:'zip|rar',
						title:'附件格式为*.zip *.rar',
						success:function(res,input){
							//可根据返回的信息，赋值到隐藏文本域保存到数据库
							if(res.code==0){
								var path=res.filePath;
								$("#taskFilePath").val(path);
								var list=path.split("/");
								$("#taskFileName").val(list[list.length-1]);
								
								var str="&nbsp;&nbsp;&nbsp;"+list[list.length-1];
								$("#file_tip").html(str);
								$("#file_tip").css('color','black');
							}else{	
								alert(res.msg);
							}
						}
					});
				});				
			});
			
			//保存
			function addInfo(){
				$("#form1").submit();
			}
		</script>
		<style type="text/css">
			.uew-select-value{
			width:142px;
			height:28px;
			overflow:hidden
			}
		</style>
	</head>
	<body class="bodyGrey">
		<div class="formbody">
			<div class="formtitle"><span>任务信息</span></div>
			<form id="form1" action="/yw-nm/business/taskSave.do" method="post">
				<table class="forminfo">
						<input name="taskId" id="taskId" type="hidden" value="${bean_Task.taskId}"/>
						<input name="taskFilePath" id="taskFilePath" 
							type="hidden" value="${bean_Task.taskFilePath}"/>
						<input name="taskFileName" id="taskFileName" 
							type="hidden" value="${bean_Task.taskFileName}"/>
					<tr>
						<td>
							<label>任务名称<b style="color:red">*</b></label>
						</td>
						<td>
							<input  type="text" class="dfinput" maxlength="50"
								datatype="taskName" nullmsg="请输入任务名称" errormsg="任务名称中含有非法字符" 
								name="taskName" value="${bean_Task.taskName}"
							/>
						</td>
					</tr>
					<tr>
						<td>
							<label>任务编号<b style="color:red">*</b></label>
						</td>
						<td>
							<!-- 新增页面 -->
							<c:if test="${null==bean_Task.taskId||\"\"==bean_Task.taskId}">
								<input type="text" class="dfinput" 
								datatype="n5-10"nullmsg="请输入任务编号" errormsg="任务编号只能是5到10位的数字"
								name="taskNumber"  value="${bean_Task.taskNumber}"
								ajaxurl="judgeTaskNumber.do" 
								/>
							</c:if>
							<!-- 编辑页面 -->
							<c:if test="${null!=bean_Task.taskId&&\"\"!=bean_Task.taskId}">
								<input type="text" class="dfinput" readonly="readonly"
									name="taskNumber"  value="${bean_Task.taskNumber}"
								/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>
							<label>下发部门<b style="color:red">*</b></label>
						</td>
						<td>
							<input name="departName" type="hidden" value="${userInfo.departName}"/>
							<span>${userInfo.departName}</span>
							<input name="departId" type="hidden" value="${userInfo.departId}"/>
						</td>
					</tr>
					<tr>
						<td>
							<label>接收单位<b style="color:red">*</b></label>
						</td>
						<td>
							<div class="vocation">
								<select name="receiveUnit" class="select3" datatype="*" nullmsg="请选择单位名称" >
									<option value="">-请选择-</option>
									<s:iterator value="#request.list_SUnitName" var="var">
										<option
											<c:if test="${bean_Task.receiveUnit==var.unitId}">selected='selected'</c:if>
											value='<s:property value="#var.unitId"/>'>
											<s:property value="#var.unitName"/>
										</option>
									</s:iterator>
								</select>
								
							</div>
						</td>
						<td>
							<span style="float:left" class="Validform_checktip"></span>
						</td>
					</tr>
					<tr>
						<td>
							<label>下发时间&nbsp;</label>
						</td>
						<td>
							<input name="issuedTime_str" 
								type="text" class="dfinput"
								value="${dateStr}"
								 onfocus="WdatePicker()"/>
						</td>
					</tr>
					<tr>
						<td valign="top">
							<label>任务描述&nbsp;</label>
						</td>
						<td>
							<textarea cols="" rows="" class="textinput"
								name="taskDescript"
							>${bean_Task.taskDescript}</textarea>
						</td>
					</tr>
					<tr>
						<td valign="top">
							<label>任务详情&nbsp;</label>
						</td>
						<td>
							<textarea cols="" rows="" class="textinput"
								name="taskDetail"
							>${bean_Task.taskDetail}</textarea>
						</td>
					</tr>
					<tr>
						<td><label>任务期限<b style="color:red">*</b></label></td>
						<td>
							<input type="text" class="dfinput" 
							datatype="n" nullmsg="请输入任务期限" 
							errormsg="任务期限格式有误" sucmsg=" "
							name="taskTerm" value="${bean_Task.taskTerm}"/>&nbsp;天
						</td>
					</tr>
				</table>
			</form>
				<table class="forminfo">
					<tr>
						<td><label>任务附件&nbsp;&nbsp;</label></td>
						<td>
							
							<input type="file" name="file" id="file" lay-type="file" class="layui-upload-file" />
						</td>
						<td>
							<c:if test="${bean_Task.taskFileName!=null&&bean_Task.taskFileName!=''}">
								<span id="file_tip" style="color:black">${bean_Task.taskFileName}	
							</c:if>
							<c:if test="${bean_Task.taskFileName==null||bean_Task.taskFileName==''}">
								<span id="file_tip" style="color:red">&nbsp;&nbsp;所有文件打包上传
							</c:if>
								
							</span>
						</td>
						
					</tr>
					<tr>
						<td colspan="3" align="center">
							<input name="submit" type="submit" class="btn" value="确认保存" onclick="addInfo();" />
						</td>
					</tr>
				</table>
		</div>
		<script type="text/javascript">
			//设置头部位置
			var siteData = {
				siteName: '业务管理',
				siteChild: {
					siteName: '任务发布',
					siteChild: {
						siteName: '新建任务'
					}
				}
			};
			topSite(siteData);
		</script>
	</body>
</html>