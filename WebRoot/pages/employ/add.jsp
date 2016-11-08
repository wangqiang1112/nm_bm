<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/inc/taglibs.inc" %>


	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>单位上报</title>
		<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-validform.js"></script>
		<script type="text/javascript">
		
			$(function() {
				//下拉选择框渲染
				$("#unitCity").uedSelect({
					width: 220
				});
				$("#unitCountry").uedSelect({
					width: 220
				});
				$("#unitProperty").uedSelect({
					width: 504
				});
				$("#unitForm").Validform({
					tipSweep: true,
					tiptype: function(msg) {
						layer.msg(msg);
					},
					datatype:{
						 "unitName":function(gets,obj,curform,regxp){
						 	var reg=/^[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
						 	if(reg.test(gets)){
						 		return true;
						 	}else{
						 		return false;
						 	}
						 },
						 "unitTelePhone":function(gets,obj,curform,regxp){
						 	var reg=/^((0\d{2,3}-\d{7,8})|(1[3458]\d{9}))$/;
						 	if(reg.test(gets)){
						 		return true;
						 	}else{
						 		return false;
						 	}
						 },
						 "unitAddress":function(gets,obj,curform,regxp){
						 	var reg=/^[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
						 	if(reg.test(gets)){
						 		return true;
						 	}else{
						 		return false;
						 	}
						 },
						 "unitContact":function(gets,obj,curform,regxp){
						 	var reg=/^[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
						 	if(reg.test(gets)){
						 		return true;
						 	}else{
						 		return false;
						 	}
						 }
					}
				});
				
			});
			
			//获取区(县)信息
			function getCountry(){
				$("#unitCountry").empty();
				$.ajax({
					url:"/yw-nm/employ/getCountry.do",
					type:"POST",
					data:{Pid:$("#unitCity").val()},
					dataType:"Text",
					success:function(result){
						$("#unitCountry").append(result);
					},
				});
			}
			
			//保存
			function check(){
				$("#unitForm").submit();
			}
			
			// 文件上传
			layui.use('upload',function(){
				layui.upload({
					url:'uploadFile.do',
					ext:'zip|rar|doc|docx|xlsx|xls|jpg|png|gif',
					title:'附件格式为*.zip *.docx *.xlsx; 图片格式为*.jpg *.png *.gif',
					success:function(res,input){
						//返回JSON为 {"filePath":"/upload/temp/image/2016/10/23/1477152441733.png","code":0,"fileUrl":"http://localhost:8080/yw-nm/upload/temp/image/2016/10/23/1477152441733.png"}
						//可根据返回的信息，赋值到隐藏文本域保存到数据库
						if(res.code==0){
							var fileName = res.filePath;
							$("#fileName").append("<li>"+fileName.split("/")[fileName.split("/").length-1]+"</li>");
							if($("#fileTemp").val() == "" ||$("#fileTemp").val() == null ){
								$("#fileTemp").val(res.filePath);
							}else{
								$("#fileTemp").val($("#fileTemp").val()+","+res.filePath);
							}
						}else{
							alert(res.msg);
						}
					}
				});
			});	
			
			// 信息判重
			
		</script>
		
	</head>

	<body class="bodyGrey">
		<div class="formbody">
			<div class="formtitle"><span>资料信息</span></div>
			<form id="unitForm" action="/yw-nm/employ/save.do" method="post">
			<input type="hidden" id="fileTemp" name="fileTemp" />
			<input type="hidden" name="filePath" value="${unit.filePath}"/>
			<input type="hidden" name="dealType"  value="${unit.dealType}" />
			<input type="hidden" name="unitId" value="${unit.unitId }" />
				<table class="forminfo">
					<tr>
						<td>
							<label>所在区域<b style="color:red">*</b></label>
						</td>
						<td>
							<div class="vocation">
								<select id="unitCity" name="unitCity" onchange="getCountry();" datatype="*" nullmsg="请选择所在市" >
									<option value="">-请选择-</option>
									<s:iterator id="city" value="cityMap" status="list">
										<option value="${Pid}" <s:if test="unit.unitCity == #city.Pid"> selected </s:if> >${City}</option>
									</s:iterator>
								</select>
							</div>
							<label style="float:left;width:20px;text-align:center;">市</label>
							<s:if test="unit.unitId == 0" >
								<div id="select_2" class="vocation">
									<select id="unitCountry" name="unitCountry" datatype="*" nullmsg="请选择所在区(县)">
										
									</select>
								</div>
								<label style="float:left;width:50px;text-align:center;">区(县)</label>
							</s:if>
							<s:else>
								<div id="select_2" class="vocation">
									<select id="unitCountry" name="unitCountry" datatype="*" nullmsg="请选择所在区(县)">
										<s:iterator id="country" value="countryMap" status="list" >
											<option value="${Pid}" <s:if test="unit.unitCountry == #country.Pid"> selected </s:if>  >${Country}</option>
										</s:iterator>
									</select>
								</div>
								<label style="float:left;width:50px;text-align:center;">区(县)</label>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
							<label>单位名称<b style="color:red">*</b></label>
						</td>
						<td>
							<input id="unitName" name="unitName" type="text" class="dfinput" datatype="unitName" nullmsg="请输入单位名称" errormsg="单位格式错误" value="${unit.unitName}" <s:if test="unit.unitId == 0">  ajaxurl="/yw-nm/employ/queryName.do" </s:if>  />
						</td>
					</tr>
					<tr>
						<td>
							<label>单位性质<b style="color:red">*</b></label>
						</td>
						<td>
							<div class="vocation">
								<select id="unitProperty" name="unitProperty" class="property" datatype="*" nullmsg="请选择单位性质">
									<option value="">-请选择-</option>
									<s:iterator id="property" value="propertyMap" status="list">
										<option value="${Pid}" <s:if test="unit.unitProperty == #property.Pid"> selected </s:if>>${Property}</option>
									</s:iterator>
								</select>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<label>单位地址<b style="color:red">*</b></label>
						</td>
						<td>
							<input name="unitAddress" type="text" class="dfinput" datatype="unitAddress" nullmsg="请输入单位地址" errormsg="单位地址格式错误" value="${unit.unitAddress }" />
						</td>
					</tr>
					<tr>
						<td>
							<label>联系人姓名<b style="color:red">*</b></label>
						</td>
						<td>
							<input name="unitContact" type="text" class="dfinput" datatype="unitContact" nullmsg="请输入联系人姓名" errormsg="联系人姓名格式错误" value="${unit.unitContact }" />
							<span style="color:grey" >例：0470-123457 或 15812345678</span>
						</td>
					</tr>
					<tr>
						<td>
							<label>联系人电话<b style="color:red">*</b></label>
						</td>
						<td>
							<input name="unitTelePhone" type="text" class="dfinput" datatype="n8-13" nullmsg="请输入单位电话" errormsg="联系人电话格式错误" value="${unit.unitTelePhone}" />
						</td>
					</tr>
					<tr>
						<td>
							<label>组织机构代码证号<b style="color:red">*</b></label>
						</td>	
						<td>
							<input name="credentialsNum" type="text" class="dfinput" datatype="/^[0-9A-Z\d]{8}\-[0-9A-Z\d]$/" nullmsg="请输入组织机构代码证号" errormsg="组织代码格式错误" value="${unit.credentialsNum}" <s:if test="unit.unitId == 0"> ajaxurl="/yw-nm/employ/credCheck.do" </s:if> />
							<span style="color:grey" >例：12345678-9</span>
						</td>
					</tr>
					<tr>
						<td>
							<label>负责人姓名<b style="color:red">*</b></label>
						</td>
						<td>
							<input name="principal" type="text" class="dfinput" datatype="s2-10" nullmsg="请输入负责人姓名" errormsg="负责人姓名格式错误" value="${unit.principal}" />
						</td>
					</tr>
					<tr>
						<td>
							<label>负责人电话<b style="color:red">*</b></label>
						</td>
						<td>
							<input name="pricipalPhone" type="text" class="dfinput" datatype="n8-13" nullmsg="请输入负责人电话" errormsg="负责人电话格式错误" value="${unit.pricipalPhone}" />
							<span style="color:grey" >例：0470-123457 或 15812345678</span>
						</td>
					</tr>
				</table>
				
			</form>
				<tr>
					<td>
						<label style="margin-left:105px;margin-top:10px;margin-right:9px" >附件</label>
					</td>
					<td>
						<input type="file" name="file" id="file" lay-type="file" class="layui-upload-file" />
						<ul style="margin-left:142px;margin-top:10px;margin-right:9px" id="fileName"> </ul>
					</td>
				</tr>
				<div style="margin-left:133px;margin-top:10px" >
					<tr>
						<td></td>
						<td colspan="2">
							<input name="submit" type="submit" class="btn" value="保存" onclick="check();" />
						</td>
					</tr>
				</div>
		</div>
		<%@ include file="/commons/message.jsp" %>
		<script type="text/javascript">
			//设置头部位置
			var siteData = {
				siteName: '单位管理',
				siteChild: {
					siteName: '资料上报'
				}
			};
			topSite(siteData);
		</script>
	</body>
</html>