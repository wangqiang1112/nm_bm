<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/inc/taglibs.inc" %>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>变更对比</title>
	<script type="text/javascript" src="/yw-nm/resources/js/package-golbal.js"></script>
	<script type="text/javascript" src="/yw-nm/resources/js/package-public.js"></script>
	<script type="text/javascript">
		$(document).ready(function(e) {
		});
	</script>
</head>

<body>
	<div class="rightinfo">
		<!--列表-->
		<table class="tablelist">
			<thead>
				<tr>
					<th>名称</th>
					<th>原信息</th>
					<th>更改后信息</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>单位名称</td>
					<td>${unit.unitName}</td>
					<td>${unitTemp.unitName}</td>
				</tr>
				<tr class="odd">
					<td>所在地市</td>
					<td>${unit.cityName}</td>
					<td>${unitTemp.cityName}</td>
				</tr>
				<tr>
					<td>单位性质</td>
					<td>${unit.property}</td>
					<td>${unitTemp.property}</td>
				</tr>
				<tr class="odd">
					<td>单位地址</td>
					<td>${unit.unitAddress}</td>
					<td>${unitTemp.unitAddress}</td>
				</tr>
				<tr>
					<td>联系人</td>
					<td>${unit.unitContact}</td>
					<td>${unitTemp.unitContact}</td>
				</tr>
				<tr>
					<td>联系人电话</td>
					<td>${unit.unitTelePhone}</td>
					<td>${unitTemp.unitTelePhone}</td>
				</tr>
				<tr class="odd">
					<td>组织机构代码证号</td>
					<td>${unit.credentialsNum}</td>
					<td>${unitTemp.credentialsNum}</td>
				</tr>
				<tr>
					<td>责任人</td>
					<td>${unit.principal}</td>
					<td>${unitTemp.principal}</td>
				</tr>
				<tr class="odd">
					<td>责任人电话</td>
					<td>${unit.pricipalPhone}</td>
					<td>${unitTemp.pricipalPhone}</td>
				</tr>
				<tr>
					<td>附件</td>
					<td>
						<c:if test="${unit.filePath!=null&&unit.filePath!=\"\"}">
							<c:forEach items="${fn:split(unit.filePath,\",\") }" var="item1">
								<c:forEach items="${fn:split(item1,'/')}" var="item2" varStatus="list">
									<div >
										<c:if test="${list.last}">
											<span>${item2}</span>
										</c:if>
									</div>
								</c:forEach>
							</c:forEach>
						</c:if>
					</td>
					<td>
						<c:if test="${unitTemp.filePath!=null&&unitTemp.filePath!=\"\"}">
							<c:forEach items="${fn:split(unitTemp.filePath,\",\") }" var="item1">
								<c:forEach items="${fn:split(item1,'/')}" var="item2" varStatus="list">
									<div >
										<c:if test="${list.last}">
											<span>${item2}</span>
										</c:if>
									</div>
								</c:forEach>
							</c:forEach>
						</c:if>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>