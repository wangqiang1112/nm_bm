<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/inc/taglibs.inc" %>
<%@ include file="/commons/message.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>新增教育经历</title>
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
			<form id="form1" action="eduSave.do">
				<input name="stafferId"  id="stafferId" type="hidden" 
					value="${stafferId}"/>
				<input name="eduID" type="hidden" value="${bean_StaffEdu.eduID}"/>
				<table class="forminfo">
					<tr>
						<td>
							<label>起止时间</label>
						</td>
						<td>
							<input
								 name="EStartTime_str" id="EStartTime_str" type="text"  
								 class="scinput"  datatype="*" 
								 nullmsg="请选择起止时间" value="${bean_StaffEdu.EStartTime_str}"
								 onclick="WdatePicker({maxDate:'#F{$dp.$D(\'EEndTime_str\')}'})"
							/>
							<em>-</em>
							<input name="EEndTime_str" id="EEndTime_str" type="text" 
								class="scinput" datatype="*" 
								nullmsg="请选择结束时间"value="${bean_StaffEdu.EEndTime_str}"
								onclick="WdatePicker({minDate:'#F{$dp.$D(\'EStartTime_str\')}'})"
							/>
						</td>
					</tr>
					<tr>
						<td>
							<label>院校名称</label>
						</td>
						<td >
							<input name="institutionName" type="text" class="dfinput" 
								nullmsg="请输入院校名称" errormsg="院校名称中含有非法字符" 
								maxlength="50"
								 datatype="ChEnNum"
								value="${bean_StaffEdu.institutionName}"
							/>
						</td>
					</tr>
					<tr>
						<td>
							<label>专业</label>
						</td>
						<td >
							<input name="discipline" type="text" class="dfinput" 
								nullmsg="请输入专业" errormsg="专业中含有非法字符" 
								maxlength="50"
								datatype="ChEnNum"
								value="${bean_StaffEdu.discipline}"
							/>
						</td>
					</tr>
					<tr>
						<td>
							<label>学制(年)</label>
						</td >
						<td>
							<select name="ESystem" class="select3" datatype="*" nullmsg="请选择学制">
								<option value="">-请选择-</option>
								<option
									<c:if test="${bean_StaffEdu.ESystem==1}">selected='selected'</c:if>
									value="1">1年
								</option>
								<option
									<c:if test="${bean_StaffEdu.ESystem==2}">selected='selected'</c:if>
									value="2">2年
								</option>
								<option
									<c:if test="${bean_StaffEdu.ESystem==3}">selected='selected'</c:if>
									value="3">3年
								</option>
								<option
									<c:if test="${bean_StaffEdu.ESystem==4}">selected='selected'</c:if>
									value="4">4年
								</option>
								<option
									<c:if test="${bean_StaffEdu.ESystem==5}">selected='selected'</c:if>
									value="5">5年
								</option>
								<option
									<c:if test="${bean_StaffEdu.ESystem==6}">selected='selected'</c:if>
									value="6">6年
								</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							<label>学位</label>
						</td>
						<td>
							<select name="degree" class="select3" datatype="*" nullmsg="请选择学位">
								<option value="">-请选择-</option>
								<option
									<c:if test="${bean_StaffEdu.degree==1}">selected='selected'</c:if>
									value="1">小学
								</option>
								<option
									<c:if test="${bean_StaffEdu.degree==2}">selected='selected'</c:if>
									value="2">初中
								</option>
								<option
									<c:if test="${bean_StaffEdu.degree==3}">selected='selected'</c:if>
									value="3">中专
								</option>
								<option
									<c:if test="${bean_StaffEdu.degree==4}">selected='selected'</c:if>
									value="4">高中
								</option>
								<option
									<c:if test="${bean_StaffEdu.degree==5}">selected='selected'</c:if>
									value="5">大专
								</option>
								<option
									<c:if test="${bean_StaffEdu.degree==6}">selected='selected'</c:if>
									value="6">本科
								</option>
								<option
									<c:if test="${bean_StaffEdu.degree==7}">selected='selected'</c:if>
									value="7">硕士
								</option>
								<option
									<c:if test="${bean_StaffEdu.degree==8}">selected='selected'</c:if>
									value="8">博士
								</option>
								<option
									<c:if test="${bean_StaffEdu.degree==9}">selected='selected'</c:if>
									value="9">博士后
								</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							<label>证明人</label>
						</td>
						<td>
							<input name="ewitness" type="text" class="dfinput" 
								 nullmsg="请输入证明人" errormsg="证明人中含有非法字符" 
								 maxlength="20"
								  datatype="ChEn"
								value="${bean_StaffEdu.ewitness}"
							/>
						</td>
					</tr>
					<tr>
						<td>
							<label>备注</label>
						</td>
						<td>
							<input name="ERemarks" type="text" class="dfinput" 
								nullmsg="请输入备注" errormsg="备注格式有误" 
								maxlength="200"
								value="${bean_StaffEdu.ERemarks}"
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