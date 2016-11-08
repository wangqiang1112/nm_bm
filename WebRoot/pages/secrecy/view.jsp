<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/inc/taglibs.inc" %>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>宣传与教育</title>
	<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js"></script>
	<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
	<script type="text/javascript" src="/yw-nm/resources/lib/echarts/echarts.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(e) {
			//文件下载
			var fileName = $("#fileName").val();
			var list = fileName.split("/");
			var name = list[list.length-1];
			var url="<a href='${DOMAIN}${infoPublish.articleFilePath}'>"+name+"</a>";
			$(".newfj").append(url);
			
			// 文件密级
			var level = $("#level").val();
			if(level == 1){
				$(".subtitle").append("秘密级");
			}
			if(level == 2){
				$(".subtitle").append("机密级");
			}
			if(level == 3){
				$(".subtitle").append("绝密级");
			}
			
		});
		

		
	</script>
</head>
<body>
	<div class="newcontent" style="max-width:1200px">
		<h2 class="newtitle">${infoPublish.topic}</h2>
		<h3 class="subtitle">密级：
		<input type="hidden" id="level" value="${infoPublish.articleLevel}" />
		</h3>
		<p class="newinfo"><span>发布人：${infoPublish.publisher}</span>&nbsp;&nbsp;&nbsp;&nbsp;<span>发布日期：${infoPublish.publishTime}</span></p>
		<div class="newtext">
			<p>${infoPublish.contentLevel}</p>
		</div>
		<p class="newfj">
			<input type="hidden" id="fileName" value="${infoPublish.articleFilePath}"/>
			附件：
		</p>
	</div>
</body>
<script type="text/javascript">
	//设置头部位置
	var siteData = {
		siteName: '保密宣传教育',
		siteChild: {
			siteName: '<a href="#" onclick="javascript:toPage('+'&apos;main.do&apos;'+');">宣传与教育</a>',
			siteChild:{
				siteName:'<a href="#" onclick="toPage('+'&apos;list.do?pid='+'${infoPublish.topicType}&apos;'+');">${infoPublish.typeName}</a>',
				siteChild:{
					siteName:'${infoPublish.topic}'
				}
			}
		}
	};
	topSite(siteData);
</script>

</html>