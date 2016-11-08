<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/inc/taglibs.inc" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>新增家庭成员</title>
		<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js" ></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-public.js" ></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-validform.js" ></script>
		<script type="text/javascript">
			$(function() {
				//下拉选择框渲染
				$(".select3").uedSelect({
					width: 142
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
			.dfinput{width:140px}
		</style>
	</head>
	<body class="bodyGrey">
		<div class="formbody">
			<form id="form1" action="familySave.do">
				<input name="stafferId" id="stafferId" type="hidden" 
					value="${stafferId}"/>
				<input name="infoID" type="hidden" value="${bean_FamilyInfo.infoID}"/>
				<table class="forminfo">
					<tr>
						<td>
							<label>姓名</label>
						</td>
						<td>
							<input name="FName" type="text" class="dfinput" 
								 nullmsg="请输入姓名" errormsg="姓名中含有非法字符" 
								 datatype="ChEn"
								 value="${bean_FamilyInfo.FName}"
							/>
						</td>
						<td style="width:80px">
							<label>性别</label>
						</td>
						<td>
							<select class="select3" datatype="*" nullmsg="请选择性别" name="FSex">
								<option value="">-请选择-</option>
								<option value="0"
									<s:if test="#request.bean_FamilyInfo.FSex==0">selected='selected'</s:if>
									>男</option>
								<option value="1"
									<s:if test="#request.bean_FamilyInfo.FSex==1">selected='selected'</s:if>
									>女</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							<label>年龄</label>
						</td>
						<td>
							<input  type="text" class="dfinput" datatype="n" 
								nullmsg="请填写年龄" errormsg="年龄填写不正确"
								name="FAge" value="${bean_FamilyInfo.FAge}"></input>
						</td>
						<td>
							<label>关系</label>
						</td>
						<td>
							<input type="text" class="dfinput" datatype="*" 
								nullmsg="请填写人员关系" errormsg="关系中含有非法字符"
								datatype="ChEnNum"
								name="relation" value="${bean_FamilyInfo.relation}"></input>
						</td>
					</tr>
					<tr>
						<td>
							<label>国籍</label>
						</td>
						<td>
							<select class="select3" datatype="*" nullmsg="请选择国籍"  name="FNationality">
								<option value="">-请选择-</option>
								<s:iterator value="#request.list_Nationality" var="var">
									<option 
										<c:if test="${bean_FamilyInfo.FNationality==var.dictId}">selected='selected'</c:if>											
											value='<s:property value="#var.DictId"/>'>
										<s:property value="#var.FirstName"/>		
									</option>
								</s:iterator>
							</select>
						</td>
						<td>
							<label>单位</label>
						</td>
						<td>
							<input type="text" class="dfinput" 
								datatype="ChEnNum"
								nullmsg="请填写单位" errormsg="单位中含有非法字符"
								name="FUnit" value="${bean_FamilyInfo.FUnit}"></input>
						</td>
					</tr>
					<tr>
						<td>
							<label>职务</label>
						</td>
						<td>
							<input type="text" class="dfinput" 
								datatype="ChEnNum"
								nullmsg="请填写职务" errormsg="职务中含有非法字符"
								name="FPost" value="${bean_FamilyInfo.FPost}"></input>
						</td>
						<td>
							<label>职称</label>
						</td>
						<td>
							<input type="text" class="dfinput"
								datatype="ChEnNum"
								nullmsg="请填写职称" errormsg="职称中含有非法字符"
								name="FJob" value="${bean_FamilyInfo.FJob}"></input>
						</td>
					</tr>
					<tr>
						<td>
							<label>住址</label>
						</td>
						<td>
							<input type="text" class="dfinput" 
								datatype="ChEnNum"
								nullmsg="请填写住址" errormsg="住址中含有非法字符"
								name="FAddress" value="${bean_FamilyInfo.FAddress}"></input>
						</td>
						<td>
							<label>手机</label>
						</td>
						<td>
							<input name="FPhone" type="text" class="dfinput" 
								maxlength="11" datatype="m" nullmsg="请输入手机号" errormsg="手机格式有误" 
								value="${bean_FamilyInfo.FPhone}"
							/>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<input name="" type="submit" class="btn" value="保存" />
							<input type="button" class="btn1" value="取消" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>