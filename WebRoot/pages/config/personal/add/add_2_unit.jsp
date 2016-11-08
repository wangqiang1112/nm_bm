<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/inc/taglibs.inc" %>
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>新增人员</title>
		<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-personal-add.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-validform.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/jquery.idTabs.min.js"></script>
		<script type="text/javascript">
			$(function() {
				//下拉选择框渲染
				$(".select3").uedSelect({
					width: 162
				});

				//单位信息验证
				$("#form2").Validform({
					datatype:{
						 //中文 英文 
						  "Email":function(gets,obj,curform,regxp){
						 	var reg=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
						 	if(null==gets||""==gets)return true;
						 	if(reg.test(gets)){
						 		return true;
						 	}else{
						 		return false;
						 	}
						 },
					},
					tipSweep:true,
					tiptype:function(msg){
						layer.msg(msg);
					}
				});
			
			});
		</script>
		<style type="text/css">
			.dfinput {width: 160px}
			.textinput {width: 398px;height: 100px}
			.forminfo {float: left;display: inline;}
			.faceupload {margin-left: 50px;margin-top: 5px;display: inline-block;}
			input[name=level],input[name=Passport],input[name=GreenCard]{float:left;}
			.leveltable td p{padding:15px 0px;}
			.leveltable td label{font-weight:bold;float:left;line-height:10px;margin-left:10px;}
			#enclosures li{float:left;padding-right: 10px;}
		</style>
	</head>

	<body class="bodyGrey">
		<div class="formbody">
			<div id="usual1" class="usual">
				<div class="itab">
					<ul>
						<li>
							<a onclick="f_page(1)">
								基本资料
							</a>
						</li>
						<li>
							<a href="#tab2"class='selected'>
								部门信息
							</a>
						</li>
						<li>
							<a onclick="f_page(3)">
								选择密级
							</a>
						</li>
						<li>
							<a onclick="f_page(4)">
								出境信息
							</a>
						</li>
						<li>
							<a onclick="f_page(5)">
								教育经历
							</a>
						</li>
						<li>
							<a onclick="f_page(6)">工作经历</a>
						</li>
						<li>
							<a onclick="f_page(7)">家庭成员</a>
						</li>
						<li>
							<a onclick="f_page(8)">附件</a>
						</li>
					</ul>
				</div>
				<!--单位信息-->
				<div id="tab2" class="tabson">
					<form id="form2" action="unitSave.do">
						<input name="stafferId" id="stafferId" 
							type="hidden" value="${bean_Staff.stafferId}"/>
						<table class="forminfo ">
							<tr>
								<!-- <td>
									<label>单位名称<b style="color:red">*</b></label>
								</td>
								<td>
									<select name="SUnit" class="select3" datatype="*" nullmsg="请选择单位名称">
										<option value="">-请选择-</option>
										<s:iterator value="#request.list_SUnit" var="var">
										<option 
											<c:if test="${bean_Staff.SUnit==var.unitId}">selected='selected'</c:if>											
												value='<s:property value="#var.unitId"/>'>
											<s:property value="#var.unitName"/>
										</option>
										</s:iterator>
									</select>
								</td>
								<td style="width:150px">
									<span style="float:left" class="Validform_checktip"></span>
								</td> -->
								<td>
									<label>部门<b style="color:red">*</b></label>
								</td>
								<td>
									<select name="SDepart" class="select3" datatype="*" nullmsg="请选择部门">
										<option value="">-请选择-</option>
										<s:iterator value="#request.list_SDepart" var="var">
										<option 
											<c:if test="${bean_Staff.SDepart==var.departID}">selected='selected'</c:if>											
												value='<s:property value="#var.departID"/>'>
											<s:property value="#var.departName"/>
										</option>
										</s:iterator>
									</select>
								</td>
								<td>
									<span style="float:left" class="Validform_checktip"></span>
								</td>
								
								<td>
									<label>部门负责人<b style="color:red">*</b></label>
								</td>
								<td>
									<input type="radio" name="flags3"  value="1"
										<c:if test="${bean_Staff.flags3==1}">checked="true"</c:if>
									/>
									<span>是</span>
									&nbsp;
									<input type="radio" name="flags3"  value="2"
										<c:if test="${null==bean_Staff.flags3||
											bean_Staff.flags3==0||bean_Staff.flags3==2}">
											checked="true"
										</c:if>
									/>
									<span>否</span>
								</td>
							</tr>
							<tr>
								<td>
									<label>职务<b style="color:red">*</b></label>
								</td>
								<td>
									<select name="SPost" class="select3" datatype="*" nullmsg="请选择职务">
										<option value="">-请选择-</option>
										<s:iterator value="#request.list_SPost" var="var">
										<option 
											<c:if test="${bean_Staff.SPost==var.dictId}">selected='selected'</c:if>											
												value='<s:property value="#var.dictId"/>'>
											<s:property value="#var.firstName"/>
										</option>
										</s:iterator>
									</select>
								</td>
								<td>
									<span style="float:left" class="Validform_checktip"></span>
								</td>
								<td>
									<label>职称<b style="color:red">*</b></label>
								</td>
								<td>
									<select name="SJob" class="select3" datatype="*" nullmsg="请选择单位职称">
										<option value="">-请选择-</option>
										<s:iterator value="#request.list_SJob" var="var">
										<option 
										<c:if test="${bean_Staff.SJob==var.dictId}">selected='selected'</c:if>											
											value='<s:property value="#var.dictId"/>'>
											<s:property value="#var.firstName"/>
										</option>
										</s:iterator>
									</select>
								</td>
								<td>
									<span style="float:left" class="Validform_checktip"></span>
								</td>
							</tr>
							<tr>
								<td>
									<label>电子邮件</label>
								</td>
								<td>
									<input name="SEmail" value="${bean_Staff.SEmail}"
										type="text" class="dfinput" 
										datatype="Email"  errormsg="邮件格式有误" />
								</td>
								<td>
									<span style="float:left" class="Validform_checktip"></span>
								</td>
								<!--<td>
									<label>单位电话<b style="color:red">*</b></label>
								</td>
								<td>
									<input name="SUnitPhone_a" type="text" class="dfinput" 
										datatype="n4-4" errormsg="区号输入有误" style="width:50px" maxlength="4"
										value="${bean_Staff.SUnitPhone_a}"
									/>
										<em>-</em>
									<input name="SUnitPhone_b" type="text" class="dfinput" 
										style="width:104px" datatype="n8-8" errormsg="号码格式输入有误" maxlength="8"
										value="${bean_Staff.SUnitPhone_b}"
									/>
								</td>
								<td>
									<span style="float:left" class="Validform_checktip"></span>
								</td> -->
							</tr>
							<tr>
								<td>
									<label style="width:76px;line-height:18px;">
										从事涉密岗位累计年限<b style="color:red">*</b>
									</label>
								</td>
								<td>
									<input name="secrecyYears" value=${bean_Staff.secrecyYears}
										type="number" min="0" class="dfinput" 
										datatype="n" nullmsg="请输入从事涉密岗位年限" 
										style="width:50px" /> 年
								</td>
								<td colspan="4">
									<span style="float:left" class="Validform_checktip"></span>
								</td>
							</tr>
							<tr>
								<td></td>
								<td colspan="2">
									<input name="" type="submit" class="btn" value="保存" />
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			//设置头部位置
			var siteData = {
				siteName: '配置管理',
				siteChild: {
					siteName: '人员管理',
					siteChild: {
						siteName: '人员新增'
					}
				}
			};
			topSite(siteData);

			$("#usual1 ul").idTabs();
		</script>
	</body>

</html>