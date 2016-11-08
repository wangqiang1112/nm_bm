<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/inc/taglibs.inc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>新增部门</title>
		<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js" ></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-public.js" ></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-validform.js" ></script>
		<script type="text/javascript">
			$(function() {
				//下拉选择框渲染
				$(".select3").uedSelect({
					width: 382
				});
				$("#myform").Validform({
					tipSweep: true,
					tiptype: function(msg) {
						layer.msg(msg);
					},
					datatype:{
						"idcard":function(gets,obj,curform,regxp){
							    return isIdCardNo($(obj).val());
						 },
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
				$("#save").click(function(){
				$("#DPrincipal").val($("#DPrincipalId option:selected").html());
				$("#myform").submit();
				})
			});
		</script>
		<style type="text/css">
			.dfinput{width:380px}
			.textinput{width:360px}
		</style>
	</head>
	<body class="bodyGrey">
		<div class="formbody">
			<form id="myform" action="add.do" method="post">
			<input type="hidden" name="DPrincipal" id="DPrincipal" />
			<input type="hidden" name="departID" value="${dept.departID}" />
			<input type="hidden" value="${type}" name="type"/>
				<table class="forminfo">
					<tr>
						<td>
							<label>单位名称</label>
						</td>
						<td>
							<div class="vocation">
								<select class="select3" name="parentDepart">
									<option selected value="保密局">保密局</option>
								</select>
							</div>
						</td>
						<td>
							<span style="float:left" class="Validform_checktip"></span>
						</td>
					</tr>
					<tr>
						<td>
							<label>部门名称</label>
						</td>
						<td>
						<c:if test="${dept.departID ==\"\"||dept.departID==null }">
							<input name="departName" type="text" class="dfinput" datatype="ChEn" value="${dept.departName }" ajaxurl="deptNameCheck.do"  nullmsg="部门名字不能为空" errormsg="部门名称不符合规范"/>
						</c:if>
						<c:if test="${dept.departID!=\"\"&&dept.departID!=null }">
							<input name="departName" type="text" class="dfinput" datatype="ChEn" value="${dept.departName }" readonly="readonly"/>
						</c:if>
						</td>
						<td>
							<span style="float:left" class="Validform_checktip"></span>
						</td>
					</tr>
					<tr>
						<td>
							<label>负责人</label>
						</td>
						<td>
							<select class="select3" name="DPrincipalId" id="DPrincipalId" datatype="*" nullmsg="请选择负责人">
							        <option value="">请选择</option>
							  <s:iterator value="stafflist" id="staff">
	                            <option value="${staff.stafferId}" <c:if test="${dept.DPrincipalId==staff.stafferId}">selected</c:if>> ${staff.stafferName}</option>
	                          </s:iterator>
							</select>
						</td>
						<td>
							<span style="float:left" class="Validform_checktip"></span>
						</td>
					</tr>
					<tr>
						<td>
							<label>联系方式</label>
						</td>
						<td>
							<input name="DPhone" type="text" class="dfinput" value="${dept.DPhone}" datatype="/[\d]{3,4}-[\d]{7,8}/ | m" nullMsg="请填写联系方式" errormsg="请输入正确格式的电话"/>
						</td>
						<td>
							<span style="float:left" class="Validform_checktip"></span>
						</td>
					</tr>
					<tr>
						<td valign="top">
							<label>部门描述</label>
						</td>
						<td>
							<textarea cols="" rows="" name="DDescription" class="textinput">${dept.DDescription}</textarea>
						</td>
						<td valign="top">
							<span style="float:left" class="Validform_checktip"></span>
						</td>
					</tr>
					<tr>
						<td></td>
						<td colspan="2">
							<input  type="button" class="btn" value="保存"   id="save"/>
							<input type="button" class="btn1" value="取消" />
						</td>
					</tr>
				</table>
				<%@ include file="/commons/message.jsp" %>
			</form>
		</div>
	</body>
</html>