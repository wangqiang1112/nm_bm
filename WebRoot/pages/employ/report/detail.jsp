<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/inc/taglibs.inc" %>

	<head>
		<title>基本信息</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
		<script type="text/javascript">
		</script>
	</head>

	<body class="bodyGrey">
		<div class="formbody">
			<form id="form1" action="" method="post">
				<table class="formview">
					<s:iterator id="unit" value="unit" status="list">
					<input type="hidden" name="unitId" id="unitId" value="${unitId}" />
					<tr>
						<td>
							<label>所在区域</label>
						</td>
						<td>
							${unitCity}
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
				</s:iterator>
				</table>
			</form>
		</div>
	</body>

</html>