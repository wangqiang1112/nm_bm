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
				var leftWin=window.top.document.getElementById("leftFrame").contentWindow;
		   		var obj=leftWin.$('li[class="selected2"]');
		   		if("人员查询"==obj.children().eq(0).html()){
		   			var obj2=leftWin.$('.list li[class="selected2"]').parent('ul').prev();
		   			var obj3=obj2.children().eq(0);
		   			leftWin.$('.list li').removeClass('selected2');
		   			obj3.addClass('selected2');
		   		}
			
				//下拉选择框渲染
				$(".select3").uedSelect({
					width: 162
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
				layui.use('upload',function(){
					layui.upload({
						url:'uploadFile.do',
						ext:'jpg|png|gif',
						title:'图片格式为*.jpg *.png *.gif',
						success:function(res,input){
							//可根据返回的信息，赋值到隐藏文本域保存到数据库
							if(res.code==0){
								$("#IDCardFile").val(res.filePath);
								$("#imgface").attr("src",res.fileUrl);
							}else{	
								alert(res.msg);
							}
						}
					});
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
			.layui-upload-button{position:absolute;width:100%;height:100%;opacity:0;left:0;top:0;}
		</style>
	</head>

	<body class="bodyGrey">
		<div class="formbody">
			<div id="usual1" class="usual">
				<div class="itab">
					<ul>
						<li>
							<a href="#tab1"class="selected">
								基本资料
							</a>
						</li>
						<li>
							<a onclick="f_page(2)">
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
				<!--基本信息-->
				<div id="tab1" class="tabson">
					<form id="form1" action="baseSave.do">
						<input name="stafferId" id="stafferId" type="hidden" value="${bean_Staff.stafferId}"/>
						<input name="IDCardFile" id="IDCardFile" type="hidden"/>
						<table class="forminfo">
							<tr>
								<td>
									<label>姓名<b style="color:red">*</b></label>
								
								</td>
								<td>
									<input name="stafferName" type="text" 
										class="dfinput"  nullmsg="请输入姓名"
										datatype="ChEn" errormsg="姓名中含有非法字符"
										value="${bean_Staff.stafferName}"
									 />
								</td>
								<td>
									<label>性别<b style="color:red">*</b></label>
								</td>
								<td>
									<div class="vocation">
										<select class="select3" datatype="*" nullmsg="请选择性别" name="stafferSex">
											<option value="">-请选择-</option>
											<option value="0"
												<s:if test="#request.bean_Staff.stafferSex==0">selected='selected'</s:if>
												>男</option>
											<option value="1"
												<s:if test="#request.bean_Staff.stafferSex==1">selected='selected'</s:if>
												>女</option>
										</select>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<label>出生日期<b style="color:red">*</b></label>
								</td>
								<td>
									<input name="birthDay_str" type="text" class="dfinput" 
									onFocus="WdatePicker()" datatype="*" nullmsg="请选择出生日期" 
									value="${bean_Staff.birthDay_str}"
									/>
								</td>
								<td>
									<label>国籍<b style="color:red">*</b></label>
								</td>
								<td>
									<div class="vocation">
										<select class="select3" datatype="*" nullmsg="请选择国籍"  name="nationality">
											<option value="">-请选择-</option>
											<s:iterator value="#request.list_Nationality" var="var">
												<option 
													<c:if test="${bean_Staff.nationality==var.dictId}">selected='selected'</c:if>											
													value='<s:property value="#var.dictId"/>'>
													<s:property value="#var.firstName"/>
													
												</option>
											</s:iterator>
										</select>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<label>政治面貌<b style="color:red">*</b></label>
								</td>
								<td>
									<div class="vocation">
										<select class="select3" datatype="*" nullmsg="请选择政治面貌"name="politicalStatus">
											<option value="">-请选择-</option>
											<s:iterator value="#request.list_PoliticalStatus" var="var">
												<option
													<c:if test="${bean_Staff.politicalStatus==var.dictId}">selected='selected'</c:if>
												
													value='<s:property value="#var.DictId"/>'>
													<s:property value="#var.FirstName"/>
												</option>
											</s:iterator>
										</select>
									</div>
								</td>
								<td>
									<label>民族<b style="color:red">*</b></label>
								</td>
								<td>
									<div class="vocation">
										<select class="select3" datatype="*" nullmsg="请选择民族" name="nation" >
											<option value="">-请选择-</option>
											<s:iterator value="#request.list_Nation" var="var">
												<option 
													<c:if test="${bean_Staff.nation==var.dictId}">selected='selected'</c:if>
													value='<s:property value="#var.DictId"/>'>
													<s:property value="#var.FirstName"/>
												</option>
											</s:iterator>
										</select>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<label>身份证号<b style="color:red">*</b></label>
								</td>
								<td>
									<input id="IDNumber" name="IDNumber" type="text" class="dfinput" 
										maxlength="18" datatype="idcard" nullmsg="请输入身份证号" errormsg="身份证号输入有误"
										value="${bean_Staff.IDNumber_decode}"
									/>
								</td>
								<td>
									<label>社团组织<b style="color:red">*</b></label>
								</td>
								<td>
									<input name="associations" type="text" 
										class="dfinput" nullmsg="请输入社团组织" 
										datatype="ChEnNum" errormsg="社团组织中含有非法字符"
									value="${bean_Staff.associations}"/>
								</td>
							</tr>
							<tr>
								<td>
									<label>手机<b style="color:red">*</b></label>
								</td>
								<td>
									<input name="mobilePone" type="text" class="dfinput" 
										maxlength="11" datatype="m" nullmsg="请输入手机号" errormsg="手机格式有误" 
										value="${bean_Staff.mobilePone}"
									/>
								</td>
								<td>
									<label>固话<b style="color:red">*</b></label>
								</td>
								<td colspan="4">
									<input name="phone_a" type="text" class="dfinput" 
										style="width:50px" maxlength="4" datatype="n4-4"
										nullmsg="请输入固话信息"	errormsg="固话格式有误" 
										value="${bean_Staff.phone_a}"
									/>
									<em>-</em>
									<input name="phone_b" type="text" class="dfinput" 
										style="width:104px" maxlength="8" datatype="n8-8"
										nullmsg="请输入固话信息" errormsg="固话格式有误" 
										value="${bean_Staff.phone_b}"
									/>
								</td>
							</tr>
							<tr>
								<td>
									<label>婚姻状况<b style="color:red">*</b></label>
								</td>
								<td colspan="4">
									<div class="vocation">
										<select class="select3" datatype="*" nullmsg="请选择婚姻状况"name="isMarry">
											<option value="">-请选择-</option>
											<option
												<c:if test="${bean_Staff.isMarry==1}">selected='selected'</c:if>
												value="1">已婚
											</option>
											<option 
												<c:if test="${bean_Staff.isMarry==2}">selected='selected'</c:if>
												value="2">未婚
											</option>
										</select>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<label>户籍地址<b style="color:red">*</b></label>
								</td>
								<td colspan="3">
									<input name="censusAddress" type="text" class="dfinput" 
										 nullmsg="请填写户籍地址" style="width:418px" 
										 datatype="ChEnNum" errormsg="户籍地址中含有非法字符"
										value="${bean_Staff.censusAddress}"
									/>
								</td>
							</tr>
							<tr>
								<td>
									<label>居住地<b style="color:red">*</b></label>
								</td>
								<td colspan="3">
									<input name="liveAddress" type="text" class="dfinput" 
										nullmsg="请填写居住地" style="width:418px"
										datatype="ChEnNum" errormsg="居住地中含有非法字符"
										value="${bean_Staff.liveAddress}"
									/>
								</td>
							</tr>
							<tr>
								<td>
									<label>档案所在地<b style="color:red">*</b></label>
								</td>
								<td colspan="3">
									<input name="archivesAdd" type="text" class="dfinput" 
										 nullmsg="请填写档案所在地" style="width:418px"
										 datatype="ChEnNum" errormsg="档案所在地中含有非法字符"
										 value="${bean_Staff.archivesAdd}"
									/>
								</td>
							</tr>
							<tr>
								<td valign="top">
									<label>犯罪记录</label>
								</td>
								<td colspan="3">
									<textarea cols="" rows="" class="textinput" 
										name="crimeRecord">${bean_Staff.crimeRecord}
									</textarea>
								</td>
							</tr>
							<tr>
								<td valign="top">
									<label>境外婚史</label>
								</td>
								<td colspan="3">
									<textarea cols="" rows="" class="textinput" 
										name="overSeasMarry">${bean_Staff.overSeasMarry}
									</textarea>
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
					<div class="faceupload">
							<!--图片上传-->
							<div class="site-upload">
								<c:if test="${bean_Staff.IDCardFile != null && bean_Staff.IDCardFile !=\"\" }">
									<img id="imgface" src="/yw-nm${bean_Staff.IDCardFile}"/>
								</c:if>
								<c:if test="${bean_Staff.IDCardFile == null || bean_Staff.IDCardFile ==\"\" }">
									<img id="imgface"src="/yw-nm/resources/images/p_01.png"/>
								</c:if>
								<input type="file" name="file" id="file" lay-type="images" class="layui-upload-file" />
							</div>
					</div>
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