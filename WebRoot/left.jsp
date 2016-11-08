<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/inc/taglibs.inc" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js" ></script>
		<script type="text/javascript" src="/yw-nm/resources/js/vMenu.js" ></script>
		<title></title>
	</head>
	<body style="background: #f6f0f0;position:absolute;height:100%">
			<div class="list">
				<ul class="first_ul">
				<!-- 第一层 -->
				<s:iterator value="#request.privList"  var="priv">
				<s:if test="#request.priv.pid==0">
				<li><img src="/yw-nm/resources/images/Icon0${priv.privilegeId}.png" class="Icon01" />
				<a href="#" class="inactive ">${ priv.privilegeName}</a>
				<!-- 第二层 -->
				<s:iterator value="#request.secondPri" var="second">
				<s:if test="#request.priv.privilegeId==#request.second.pid">
				<ul class="second_ul" style="display: <s:if test="#request.second.privilegeId==11">block</s:if><s:else>none</s:else>;">
							<li class="selected2"><img src="/yw-nm/resources/images/list.gif" class="Icon02" />
								<a href="#" <s:if test="#request.second.url!=''&&#request.second.url!=null"> onclick="showPage('${second.url}',this)"</s:if><s:else> class="inactive"</s:else>>${second.privilegeName }</a>
							<!-- 第三层 -->
							<s:iterator value="#request.thridPri" var="thrid">
							<s:if test="#request.thrid.pid==#request.second.privilegeId">
							<ul class="three_ul">
									<li>
										<a href="#" onclick="showPage('${thrid.url}',this)">${thrid.privilegeName}</a>
									</li>
								</ul>
							</s:if>
							</s:iterator>
							</li>
				</ul>
				</s:if>
				</s:iterator>
                 <div class="bottom_border"></div>
				</li>
				</s:if>
				</s:iterator> 
					</li> 
					</li> 
					
				</ul>
			</div>
	</body>
</html>