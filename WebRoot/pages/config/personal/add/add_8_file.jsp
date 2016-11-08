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
		<script type="text/javascript" src="/yw-nm/resources/js/jquery.idTabs.min.js"></script>
		<script type="text/javascript">
			$(function() {
				layui.use('upload',function(){
					layui.upload({
						url:'uploadFile.do',
						ext:'jpg|png|gif',
						before:function(input){
							var paths = $("#SFilePath").val().split(",");
							if(paths.length>4){
								alert("附件图片最多上传5张");
								return false;//阻止上传
							}
							return true;
						},
						success:function(res,input){
							//返回JSON为 {"filePath":"/upload/image/2016/10/22","fileName":"p_02.png","realName":"1477149174789.png","code":0,"fileUrl":"http://localhost:8080/yw-nm/upload/image/2016/10/22/1477149174789.png"}
							//可根据返回的信息，赋值到隐藏文本域保存到数据库
							if(res.code==0){
								var html = '';
								html+='<li>';
								html+='<div class="site-upload">';
								html+='<img src="${DOMAIN}'+res.filePath+'"/>';
								html+='</div>';
								html+='</li>';
								$("#showImgs").append(html);
								if($("#SFilePath").val()){
									$("#SFilePath").val($("#SFilePath").val()+","+res.filePath);
								}else{
									$("#SFilePath").val(res.filePath);
								}
								$.post("fileAdd.do",$("#form1").serialize());
							}else{
								alert(res.msg);
							}
						}
					 });
				 });
			});
			
		</script>
		<style type="text/css">
			.layui-upload-button{position:absolute;width:100%;height:100%;opacity:0;left:0;top:0;}
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
							<a href="#tab8">附件</a>
						</li>
					</ul>
				</div>
				<!-- 全局变量 -->
				<form id="form1" >
					<input name="stafferId" id="stafferId" type="hidden" value="${bean.stafferId}"/>
					<input name="SFilePath" id="SFilePath" type="hidden" value="${bean.SFilePath }"/>
				</form>
				<div id="tab8" class="tabson">
					<ul id="showImgs" class="enclosures">
						<c:choose>
							<c:when test="${bean.SFilePath != null && bean.SFilePath !=\"\" }">
								<c:forEach items="${fn:split(bean.SFilePath,\",\") }" var="item">
									<li>
										<div class="site-upload">
											<img src="${DOMAIN}${item}"/>
										</div>
									</li>
								</c:forEach>
							</c:when>
						</c:choose>
					</ul>
					<ul class="enclosures">
						<li>
							<div class="site-upload">
									<img src="/yw-nm/resources/images/p_02.png"/>
									<input type="file" name="file" id="file" lay-type="images" class="layui-upload-file" />
							</div>
						</li>
					</ul>
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