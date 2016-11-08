<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/inc/taglibs.inc" %>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>信息发布</title>
		<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js" ></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-public.js" ></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-validform.js" ></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-ueditor.js"></script>
		<script type="text/javascript">
			$(function() {
				//下拉选择框渲染
				$(".select3").uedSelect({
					width: 504
				});
				$("#form1").Validform({
					tipSweep: true,
					tiptype: function(msg) {
						layer.msg(msg);
					},
					datatype:{
						 "publisher":function(gets,obj,curform,regxp){
						 	var reg=/^[\u4e00-\u9fa5]+$/;
						 	if(reg.test(gets)){
						 		return true;
						 	}else{
						 		return false;
						 	}
						 },
						 "topic":function(gets,obj,curform,regxp){
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
			
			//保存
			function addInfo(){
				$("#form1").submit();
			}
			
			//取消跳转至资料审核
			function returnindex(){
				window.location = "/yw-nm/secrecy/index.do";
			}
			
			// 文件上传
			layui.use('upload',function(){
				layui.upload({
					url:'uploadFile.do',
					ext:'zip|rar|doc|docx|xlsx|xls',
					title:'附件格式为*.zip *.rar *.doc *.docx *.xlsx *.xls ',
					success:function(res,input){
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
		</script>
	</head>
	<body class="bodyGrey">
		<div class="formbody">
			<div class="formtitle"><span>文章发布</span></div>
			<form id="form1" action="/yw-nm/secrecy/save.do" method="post">
				<table class="forminfo" style="width:100%">
				<input type="hidden" id="fileTemp" name="fileTemp" />
					<tr>
						<td style="width:100px">
							<label>主题类别</label>
						</td>
						<td>
							<div class="vocation">
								<select class="select3" name="pid" datatype="*" nullmsg="请选择类别">
									<option value="">-请选择-</option>
									<s:iterator id="type" value="topicType" status="list" >
										<option value="${pid}">${topicType}</option>
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
							<label>部门</label>
						</td>
						<td>
							<div class="vocation">
								<select class="select3" name="departId" datatype="*" nullmsg="请选择部门">
									<option value="">-请选择-</option>
									<s:iterator id="dept" value="dept" status="list" >
										<option value="${departId}">${departName}</option>
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
							<label>发布人</label>
						</td>
						<td>
							<input id="publisher" name="publisher" type="text" class="dfinput" datatype="publisher" placeholder="请输入中文" nullmsg="请输入发布人" errormsg="发布人格式错误" />
						</td>
					</tr>
					<tr>
						<td>
							<label>密级</label>
						</td>
						<td>
							<div class="vocation">
								<select class="select3" id="articleLevel" name="articleLevel" datatype="*" nullmsg="请选择密级">
									<option value="">-请选择-</option>
									<option value="1">秘密级</option>
									<option value="2">机密级</option>
									<option value="3">绝密级</option>
								</select>
							</div>
						</td>
						<td>
							<span style="float:left" class="Validform_checktip"></span>
						</td>
					</tr>
					<tr>
						<td>
							<label>主题</label>
						</td>
						<td>
							<input id="topic" name="topic" type="text" class="dfinput" datatype="topic" nullmsg="请输入主题" errormsg="主题格式错误" maxlength="50" />
						</td>
					</tr>
					<tr>
						<td valign="top"><label>正文内容</label></td>
						<td>
							<script id="editor" name="contentLevel" type="text/plain" style="width:80%;height:200px;" datatype="*" placeholder="请输入正文内容" nullmsg="请输入正文内容" errormsg="正文内容格式错误" ></script>
						</td>
					</tr>
					
				</table>
			</form>
				<tr>
					<td>
						<label style="margin-left:95px;margin-top:10px" >附件</label>
					</td>
					<td>
						<input type="file" name="file" id="file" lay-type="file" class="layui-upload-file" />
						<ul style="margin-left:122px;margin-top:10px;margin-right:9px" id="fileName"> </ul>
					</td>
				</tr>
				<div style="margin-left:123px;margin-top:10px" >
					<tr>
						<td colspan="2">
							<input name="submit" type="submit" class="btn" value="保存" onclick="addInfo();" />
							<input type="button" class="btn1" value="取消" onclick="returnindex();" />
						</td>
					</tr>	
				</div>
		</div>
	</body>
	<script type="text/javascript">
			//设置头部位置
			var siteData = {siteName:'配置管理',siteChild:{siteName:'信息管理',siteChild:{siteName:'信息发布'}}};
			topSite(siteData);
			
	</script>
	 <script type="text/javascript">
        //实例化编辑器
        //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
       	try{
        	var ue = UE.getEditor('editor');
        }catch(e){
        }
	</script>
</html>