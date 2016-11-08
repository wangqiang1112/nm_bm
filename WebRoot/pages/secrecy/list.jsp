<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/inc/taglibs.inc" %>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>宣传与教育</title>
	<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js"></script>
	<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
	<script type="text/javascript" src="/yw-nm/resources/lib/echarts/echarts.min.js" ></script>
	<script type="text/javascript">
		$(document).ready(function(e) {
		});
		
		function check(pid){
			$("#form1").attr("action","/yw-nm/secrecy/list.do?pid="+pid);
			$("#form1").submit();
		}
	</script>
</head>
<style type="text/css">
	.rightinfo{margin-left:140px;padding:0px 20px;}
	.pagin{position:relative;top:10px;}
</style>
<body>
	<form id="form1" method="post" action="" ></form>
	<form id="myform" method="post" action="/yw-nm/secrecy/list.do">
		<input type="hidden" id="pid" name="pid" value="${pid}" />
			<div class="newcontent" style="margin:0px auto;padding:0px 20px;" >
				<div class="newtype">
					<ul id="showTypeList">
						<s:iterator id="topicType" value="topicType" var="type" >
							<li <s:if test="#type.pid == query.pid"> class="seleted" </s:if> >
							<a href="#" onclick="check('${pid}');" ><s:property value="topicType" /></a>
							</li>
						</s:iterator>
					</ul>
				</div>
				<div class="rightinfo" style="padding-top:20px">
					<div class="newlist">
						<ul>
							<s:iterator id="info" value="page.result" status="list">
								<s:if test="#list.First">
									<li style="border:none;padding-top:21px;">
										<a href="/yw-nm/secrecy/query.do?articleId=${articleId}" style="color:#db4127;font-weight:bold;">${topic}</a><span><s:date name="#info.PublishTime" format="yyyy-MM-dd" /></span>
									</li>
									<li style="width:auto;padding:5px 35px 15px 0px;text-indent:24px;">
										<a href="/yw-nm/secrecy/query.do?articleId=${articleId}" style="color:#919191;margin:0px;">
											<div style="height:40px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;word-break:keep-all" >${contentLevel}</div>
										</a>
									</li>
								</s:if>
								<s:else>
									<li <s:if test="!#list.even">class="hover"</s:if>>
										<a href="/yw-nm/secrecy/query.do?articleId=${articleId}" >${topic}</a>
										<span><s:date name="#info.PublishTime" format="yyyy-MM-dd" /></span>
									</li>
								</s:else>
							</s:iterator>
						</ul>
					</div>
				<%@ include file="/commons/pager.jsp" %>	
			</div>
	</form>
</body>
<script type="text/javascript">
	//设置头部位置
	var siteData = {
		siteName: '保密宣传教育',
		siteChild: {
			siteName: '<a href="#" onclick="toPage('+'&apos;main.do&apos;'+');">宣传与教育</a>',
			siteChild:{
				siteName:'${query.typeName}'
			}
		}
	};
	topSite(siteData);
</script>
</html>