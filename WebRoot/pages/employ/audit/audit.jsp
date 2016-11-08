<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/inc/taglibs.inc" %>

<head>
	<title>资料审核</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js"></script>
	<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
	<script type="text/javascript">
		function update(a){
			var type = document.getElementById("type");
			if(a == 1){
				type.value = "2";
				$("#myform").submit();
			}
			else{
				type.value = "3";
				$("#myform").submit();
			}
		}
	</script>
</head>

<body class="bodyGrey">
	<div class="formbody">
		<form id="myform" action="/yw-nm/employ/update.do" method="post">
			<input type="hidden" name="type" id="type"/>
			<table class="formview">
			<s:iterator id="unit" value="unit" status="list">
				<input type="hidden" name="unitId" id="unitId" value="${unitId}" />
				<tr>
					<td>
						<label>所在区域</label>
					</td>
					<td>
						${cityName}
					</td>
				</tr>
				<tr>
					<td>
						<label>单位名称</label>
					</td>
					<td>
						${unitName}
					</td>
					<td></td>
				</tr>
				<tr>
					<td>
						<label>单位性质</label>
					</td>
					<td>
						${Property}
					</td>
				</tr>
				<tr>
					<td valign="top">
						<label>单位地址</label>
					</td>
					<td>
						${unitAddress}
					</td>
				</tr>
				<tr>
					<td valign="top">
						<label>联系人</label>
					</td>
					<td>
						${unitContact}
					</td>
				</tr>
				<tr>
					<td valign="top">
						<label>联系人电话</label>
					</td>
					<td>
						${unitTelePhone}
					</td>
				</tr>
				<tr>
					<td valign="top">
						<label>组织机构代码</label>
					</td>
					<td>
						${credentialsNum}
					</td>
				</tr>
				<tr>
					<td valign="top">
						<label>负责人姓名</label>
					</td>
					<td>
						${principal}
					</td>
				</tr>
				<tr>
					<td valign="top">
						<label>负责人电话</label>
					</td>
					<td>
						${pricipalPhone}
					</td>
				</tr>
				<tr>
					<td valign="top">
						<label>附件</label>
					</td>
					<td>
						<c:if test="${unit.filePath!=null&&unit.filePath!=\"\"}">
							<c:forEach items="${fn:split(unit.filePath,\",\") }" var="item1">
								<c:forEach items="${fn:split(item1,'/')}" var="item2" varStatus="list">
									<div >
										<c:if test="${list.last}">
											<span><a href="${DOMAIN}${item1}">${item2}</a></span>
										</c:if>
									</div>
								</c:forEach>
							</c:forEach>
						</c:if>
					</td>
				</tr>
			</table>
			<s:if test="#unit.type != null">
				<div class="btnpanel">
					<input name="ok" type="button" class="btn" value="通过" onclick="update(1);" />
					<input name="off" type="button" class="btn" value="不通过" onclick="update(2);" />
				</div>
			</s:if>
			</s:iterator>
		</form>
	</div>
</body>

</html>