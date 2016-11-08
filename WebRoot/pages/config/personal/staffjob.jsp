<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/inc/taglibs.inc" %>
<%@ include file="/commons/message.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>新增工作经历</title>
		<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js" ></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-public.js" ></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-validform.js" ></script>
		<script type="text/javascript" src="/yw-nm/resources/js/jquery.idTabs.min.js"></script>
		<script type="text/javascript">
			$(function() {
				//下拉选择框渲染
				$(".select3").uedSelect({
					width: 316
				});
				$("#form1").Validform({
					tipSweep:true,
					tiptype:function(msg){
						layer.msg(msg);
					},
					datatype:{
						 //中文 英文 
						  "ChEn":function(gets,obj,curform,regxp){
						 	var reg=/^[a-zA-Z\u4e00-\u9fa5]+$/;
						 	if(reg.test(gets)){
						 		return true;
						 	}else{
						 		return false;
						 	}
						 },
						 //中文 英文 数字
						  "ChEnNum":function(gets,obj,curform,regxp){
						 	var reg=/^[a-zA-Z0-9\u4e00-\u9fa5]+$/;
						 	if(reg.test(gets)){
						 		return true;
						 	}else{
						 		return false;
						 	}
						 },
					}
				});
				$(".btn1").click(function(){
					closeWindow();
				});
			});
		</script>
		<style type="text/css">
			.dfinput{width:314px}
		</style>
	</head>
	<body class="bodyGrey">
		<div class="formbody">
			<form id="form1" action="jobSave.do">
				<input name="stafferId" id="stafferId"type="hidden" 
					value="${stafferId}"/>
				<input name="jobID" type="hidden" value="${bean_StaffJob.jobID}"/>
				<table class="forminfo">
					<tr>
						<td>
							<label>起止时间</label>
						</td>
						<td>
							<input
								 name="WStartTime_str" id="WStartTime_str" type="text"  
								 class="scinput"  datatype="*" 
								 nullmsg="请选择起止时间" value="${bean_StaffJob.WStartTime_str}"
							     onclick="WdatePicker({maxDate:'#F{$dp.$D(\'WEndTime_str\')}'})"
							/>
							<em>-</em>
							<input name="WEndTime_str" id="WEndTime_str" type="text" 
								class="scinput" datatype="*"
								nullmsg="请选择结束时间"value="${bean_StaffJob.WEndTime_str}"
								onclick="WdatePicker({minDate:'#F{$dp.$D(\'WStartTime_str\')}'})"
							/>
						</td>
					</tr>
					<tr>
						<td>
							<label>单位名称</label>
						</td>
						<td >
							<input name="WUnitName" type="text" class="dfinput" 
								nullmsg="请输入单位名称" errormsg="单位名称格式有误" 
								datatype="ChEnNum"
								value="${bean_StaffJob.WUnitName}"
							/>
						</td>
					</tr>
					<tr>
						<td>
							<label>职务</label>
						</td>
						<td>
							<select class="select3" datatype="*" nullmsg="请选择职务" name="WPost" >
								<option value="">-请选择-</option>
								<s:iterator value="#request.list_SPost" var="var">
									<option 
										<c:if test="${bean_StaffJob.WPost==var.dictId}">selected='selected'</c:if>
										value='<s:property value="#var.DictId"/>'>
										<s:property value="#var.FirstName"/>
									</option>
								</s:iterator>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							<label>职称 </label>
						</td >
						<td>
							<select class="select3" datatype="*" nullmsg="请选择职称" name="WJob" >
								<option value="">-请选择-</option>
								<s:iterator value="#request.list_SJob" var="var">
									<option 
										<c:if test="${bean_StaffJob.WJob==var.dictId}">selected='selected'</c:if>
										value='<s:property value="#var.DictId"/>'>
										<s:property value="#var.FirstName"/>
									</option>
								</s:iterator>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							<label>工作职责</label>
						</td>
						<td>
							<input name="WDuty" type="text" class="dfinput" 
								nullmsg="请输入工作职责" errormsg="工作职责中含有非法字符" 
								 datatype="ChEnNum"
								value="${bean_StaffJob.WDuty}"
							/>
						</td>
					</tr>
					<tr>
						<td>
							<label>证明人</label>
						</td>
						<td>
							<input name="wwitness" type="text" class="dfinput" 
								nullmsg="请输入证明人" errormsg="证明人中含有非法字符" 
								 datatype="ChEn"
								value="${bean_StaffJob.wwitness}"
							/>
						</td>
					</tr>
					<tr>
						<td>
							<label>手机</label>
						</td>
						<td>
							<input name="WPhone" type="text" class="dfinput" 
										maxlength="11" datatype="m" nullmsg="请输入手机号" errormsg="手机格式有误" 
										value="${bean_StaffJob.WPhone}"
							/>
						</td>
					</tr>
					<tr>
						<td>
							<label>备注</label>
						</td>
						<td>
							<input name="WRemarks" type="text" class="dfinput" 
								 nullmsg="请输入备注" errormsg="备注格式有误" 
									value="${bean_StaffJob.WRemarks}"
							/>
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<input name="" type="submit" class="btn" value="保存" />
							<input type="button" class="btn1" value="取消" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>