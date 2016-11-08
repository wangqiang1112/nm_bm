<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>新增用户</title>
		<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-validform.js"></script>
		<script type="text/javascript">
		
				$(function() {
				    $("#myform").Validform({
				    tipSweep:true,
				    tiptype: function(msg){
					layer.msg(msg);
				    }
			     });
				//下拉选择框渲染
				$(".select3").uedSelect({
					width: 142
				});
				$(".btn1").click(function() {
					closeWindow();
				});
				$(".btn").click(function() {
					$("#myform").submit();
				});
				//编辑，初始密码变为输入密码
                if($("#type").val()=="edit"){
                var html="<input name='upassword' type='text' class='dfinput' datatype='*' nullmsg='请输入密码' value='${user.upassword}'  ajaxurl='pwLenthCheck.do'/>"
                $("#password").html("");
                $("#password").html(html);
                $("#mima").html("密码");
                }
                
                var userTypeId = $("#userTypeId").val();
                changeType(userTypeId);
                
                
			});
			//根据部门改变名字
			function changeName(departName){
			$.ajax({
					url:"/yw-nm/config/personal/getStaff.do",
					type:"POST",
					data:{ departName:departName},
					dataType:"json",
					success:function(result){
					    $("#stafferName").html("")
						var html="<option value=''>-请选择-</option>"
						for(var i=0;i<result.length;i++){
						html+="<option value='"+result[i].stafferId+"'>"+result[i].stafferName+"</option>"
						}
						$("#stafferName").html(html);
					},
				});
			}
			function changeUserInfo(stafferId){
			//把名字赋给name="stafferName"的input
			$("#stafferName2").val($("#stafferName").find("option:selected").text());
			if($("#type").val()=="edit"){
			return false
			}
			$.ajax({
					url:"/yw-nm/config/personal/getStaffInfo.do",
					type:"POST",
					data:{stafferId:stafferId},
					dataType:"json",
					success:function(result){
					$("#userName").val(result.mobilePone);
					$("#userPhone").val(result.mobilePone);
					$("#offerPhone").val(result.phone);
					$("#email").val(result.SEmail);
					},
				});
			}
			function changeType(type){
				//var type = $("#userType option:selected").val();
				if(type == 1){
					$("#changeName label").html("部门名称");
					$("#unitIsHidden").css("display","none");
					$("#departIsHidden").css("display","block");
					$("#unitName").removeAttr("datatype");
					$("#departName").attr("datatype","*");
					$("#nameIsHidden").css("display","block");
					$("#selName").css("display","block");
					$("#selNameInput").css("display","none");
					$("#stafferName2").removeAttr("datatype");
					$("#stafferName").attr("datatype","*");
				}else{
					$("#changeName label").html("单位名称");
					$("#unitIsHidden").css("display","block");
					$("#departIsHidden").css("display","none");
					$("#unitName").attr("datatype","*");
					$("#departName").removeAttr("datatype");
					$("#selName").css("display","none");
					$("#selNameInput").css("display","block");
					$("#stafferName2").attr("datatype","*");
					$("#stafferName").removeAttr("datatype");
				}	
			}
			
			/* function save(){
				$("form#addUserTodb").submit();
			} */
			
		</script>
		<style type="text/css">
			.dfinput {
				width: 140px
			}
			.uew-select-value{
			width:142px;
			height:28px;
			overflow:hidden
			}
		</style>
	</head>

	<body class="bodyGrey">
		<div class="formbody">
			<form id="myform" action="add.do" method="post">
				<input type="hidden" id="userTypeId" value="${user.userType }"/>
				<input type="hidden"  value="${user.userId}" name="userId"/>
				<input type="hidden" value="${type}" name="type" id="type"/>
				<table class="forminfo">
				<tr>
						<td>
							<label>用户类型</label>
						</td>
						<td>
							<select id="userType"  onchange="changeType(this.options[this.options.selectedIndex].value)" class="select3" datatype="*" nullmsg="请选择类型"  name="userType"> 
								<option value="">-请选择-</option>
	                            <option value="1" <c:if test="${user.userType==1}">selected</c:if>>保密局用户</option>
	                          	<option value="2" <c:if test="${user.userType==2}">selected</c:if>>单位用户</option>
							</select>
						</td>
						<td></td>
						<td id="changeName">
							<label>${user.userType }<s:if test="user.userType==1">部门名称</s:if><s:else>单位名称</s:else></label>
						</td>
						
						<td id="unitIsHidden">
							<c:if test="${type=='edit'}"><input type="text" name="unitName" readonly="readonly" class="dfinput" value="${user.unitName}" title="${user.unitName}"/></c:if>
							<c:if test="${type=='add'}">
							<select class="select3"  nullmsg="请选择单位" name="unitName" id="unitName"  ajaxurl="cheakUnit.do">
										<option value="">-请选择-</option>
			                          <s:iterator value="units" id="unit">
			                            <option value="${unit}" <c:if test="${user.unitName == unit}">selected</c:if>> ${unit}</option>
			                          </s:iterator>
							</select>
							</c:if>
						</td> 
						<td id="departIsHidden" style="display:none;">
							<select class="select3"  nullmsg="请选择部门" name="departName" id="departName" onchange="changeName(this.options[this.options.selectedIndex].value)">  
								<option value="">-请选择-</option>
	                          <s:iterator value="deptNames" id="deptName" status="d">
	                            <option value="${deptName}" <c:if test="${user.departName == deptName}">selected</c:if>>${deptName}</option>
	                          </s:iterator>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							<label>姓名</label>
						</td>
						<td id="selName">
						<select class="select3"  nullmsg="请选择姓名" value='${user.stafferName }'   id="stafferName" onchange="changeUserInfo(this.options[this.options.selectedIndex].value)" >
								<option value="">-请选择-</option>
								<s:iterator value="#request.staffNames" id="staff">
	                            <option value="${staff}" <c:if test="${user.stafferName == staff}">selected</c:if>> ${staff}</option>
	                            </s:iterator>
						</select>
						</td>
						<td id="selNameInput" style="display:none">
						<input  name="stafferName"  type='text' class='dfinput' datatype='ChEn' value='${user.stafferName }' nullmsg='请输入姓名' errormsg="姓名格式不符合规范" id="stafferName2">
						</td>
						<td style="width:40px">
						</td>
						<td>
							<label>用户名</label>
						</td>
						<td>
							<input id="userName" name="userName" type="text" class="dfinput" datatype="*2-16" value="${user.userName}" nullmsg="请输入用户名" errormsg="用户名不符合规范" ajaxurl="userNameCheck.do?type=edit&preName=${user.userName}"/>
						</td>
					</tr>
					<tr>
						<td>
							<label id="mima">初始密码</label>
						</td>
						<td colspan="4" id="password">
							<c:if test="${type == 'edit'}"><input name="upassword" type="text" class="dfinput" datatype="*" value="${user.upassword}" nullmsg="请输入密码" errormsg="密码格式不正确"/></c:if>
							<c:if test="${type != 'edit'}">123456</c:if>
						</td>
					</tr>
					<tr>
						<td>
							<label>手机</label>
						</td>
						<td>
							<input id="userPhone" name="userPhone" type="text" class="dfinput" datatype="m" nullmsg="请输入手机" value="${user.userPhone}" errormsg="手机格式不正确" />
						</td>
						<td></td>
						<td>
							<label>办公电话</label>
						</td>
						<td>
							<input id="offerPhone" name="offerPhone" type="text" class="dfinput" datatype="/[\d]{3,4}-[\d]{7,8}/" nullmsg="请输入办公电话" value="${user.offerPhone}" errormsg="办公电话格式不正确" placeholder="0379-1234567"/>
						</td>
					</tr>
					<tr>
						<td>
							<label>邮箱地址</label>
						</td>
						<td>
							<input id="email" name="email" type="text" class="dfinput" datatype="e" nullmsg="请输入邮箱" value="${user.email}" errormsg="邮箱格式不正确" />
						</td>
						<td></td>
						<td>
							<label>传真</label>
						</td>
						<td>
							<input name="userFax" type="text" class="dfinput" datatype="/[\d]{3,4}-[\d]{7,8}/" nullmsg="请输入传真" value="${user.userFax}" errormsg="传真格式不正确" placeholder="0379-1234567"/>
						</td>
					</tr>
					<tr>
						<td>
							<label>备注</label>
						</td>
						<td colspan="4">
							<input name="uRemarks" type="text" class="dfinput" style="width:388px" value="${user.uRemarks}" />
						</td>
					</tr>
				</table>
				<div class="btnpanel">
					<input type="button" class="btn" value="保存" />
					<input type="button" class="btn1" value="取消" />
				</div>
				<%@ include file="/commons/message.jsp" %>
			</form>
			
		</div>
	</body>

</html>