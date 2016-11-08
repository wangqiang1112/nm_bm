<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/inc/taglibs.inc" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>宣传与教育</title>
	<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js"></script>
	<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
	<style type="text/css">
		.n-panel{width:49%;float:left;position:relative;height:288px;margin-bottom:10px;border:1px solid #d7e4ea;overflow:hidden;}
		.n-panel .n-title{padding:5px 0px;margin-top:15px;}
		.n-panel .n-title h3{position:relative;display:inline;left:-1px;color:white;background:#e19476;padding:5px 25px;border-radius:0px 4px 4px 0px}
		.n-panel .n-title a{position:relative;float:right;line-height:18px;right:-1px;height:20px;color:white;background:#b4b4b4;padding:0px 15px}
		.n-panel .n-title a:hover{background:#db4129}
		.n-panel ul{width:100%;}
		.n-panel ul li{padding:8px 0px;float:left;width:100%;font-size:12px;border-bottom:1px solid #d7e4ea}
		.n-panel ul li.hover{background:#edf6fb}
		.n-panel ul li a{float:left;margin-left:15px;}
		.n-panel ul li span{float:right;margin-right:15px;}
	</style>
	<script type="text/javascript">
		function check(pid){
			$("#form1").attr("action","/yw-nm/secrecy/list.do?pid="+pid);
			$("#form1").submit();
		}
		
	</script>
</head>
<body>
	<div class="newcontent" style="padding:0px 40px;">
		<form id="form1" action="" method="post" />
			<s:iterator id="type" value="topicType" var="type" status="st">
				<div class="n-panel" <s:if test="#st.even">style="float:right"</s:if>>
					<div class="n-title">
						<h3><s:property value="topicType"/></h3>
						<a href="#" onclick="check('${pid}');" >更多>></a>
					</div>
					<ul>
						<s:iterator id="list" value="list" var="list" status="st1">
							<s:if test="#list.topicType == #type.pid">
								<s:if test="#st1.index == 5*(#st1.index/5)">
									<li style="border:none;padding-top:21px;">
										<a href="/yw-nm/secrecy/query.do?articleId=${articleId}" class="ellipsis" style="width:500px;color:#db4127;font-weight:bold;"><s:property value="#list.topic" /></a>
										<span style="color:#db4127;font-weight:bold;"><s:property value="#list.publishTime" /></span>
									</li>
									<li>
										<div style="padding:0px 15px;line-height:20px;height:40px;text-indent:24px;overflow:hidden">${contentLevel}</div>
									</li>
								</s:if>
								<s:else>
										<li <s:if test="#st1.even">class="hover"</s:if>>
											<a class="ellipsis" href="/yw-nm/secrecy/query.do?articleId=${articleId}"><s:property value="#list.topic"/></a>
											<span><s:property value="#list.publishTime" /></span>
										</li>
								</s:else>
							</s:if>
						</s:iterator>
					</ul>
				</div>
			</s:iterator>
		<div style="clear:both"></div>
	</div>
</body>
<script type="text/javascript">
	//设置头部位置
	var siteData = {
		siteName: '保密宣传教育',
		siteChild: {
			siteName: '宣传与教育'
		}
	};
	topSite(siteData);
	
	//解决登录时，如果有错误提示，登录成功进系统后会重复弹一次提示的问题
	if('${error}' || '${success}'){
		$.post('removeMessage.do');
	}
</script>
</html>