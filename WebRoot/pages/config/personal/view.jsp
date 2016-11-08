<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/inc/taglibs.inc" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>人员资料预览</title> 
	<script type="text/javascript"src="/yw-nm/resources/js/package-golbal.js"></script>
	<script type="text/javascript"src="/yw-nm/resources/js/package-public.js"></script>
	<script type="text/javascript" src="/yw-nm/resources/js/jquery.idTabs.min.js"></script>
	<script type="text/javascript"></script>
	<style type="text/css">
		.formview td{
			padding:10px
		}
		.formview tr{
			margin:10px
		}
	</style>
</head>
<body class="bodyGrey">
	<div class="formbody">
		<div class="formtitle">
			<span>基本资料</span>
		</div>
		<div>
		<table class="formview" style="float:left;">
			<tr>
				<td align="right"><label><b>姓名:</b></label></td>
				<td>${bean_Staff.stafferName}</td>
				<td align="right"><label><b>性别:</b></label></td>
				<td>
					<c:if test="${bean_Staff.stafferSex==0}">男</c:if>
					<c:if test="${bean_Staff.stafferSex==1}">女</c:if>
				</td>
			</tr>
			<tr>
				<td align="right"><label><b>出生日期:</b></label></td>
				<td>${bean_Staff.birthDay_str}</td>
				<td align="right"><label><b>国籍:</b></label></td>
				<td>${bean_Staff.nationality_str}</td>
			</tr>
			<tr>
				<td valign="top"align="right"><label><b>政治面貌:</b></label></td>
				<td>${bean_Staff.politicalStatus_str}</td>
				<td valign="top"align="right"><label><b>民族:</b></label></td>
				<td>${bean_Staff.nation_str}</td>
			</tr>
			<tr>
				<td valign="top"align="right"><label><b>身份证号:</b></label></td>
				<td>${bean_Staff.IDNumber_decode}</td>
				<td valign="top"align="right"><label><b>社团组织:</b></label></td>
				<td>${bean_Staff.associations}</td>
			</tr>
			<tr>
				<td valign="top"align="right"><label><b>手机:</b></label></td>
				<td>${bean_Staff.mobilePone}</td>
				<td valign="top"align="right"><label><b>固话:</b></label></td>
				<td>${bean_Staff.phone}</td>
			</tr>
			<tr>
				<td align="right"><label><b>婚姻状况:</b></label></td>
				<td>
					<c:if test="${bean_Staff.isMarry==1}">已婚</c:if>
					<c:if test="${bean_Staff.isMarry==2}">未婚</c:if>
				</td>
				<td align="right"><label><b>户籍地址:</b></label></td>
				<td>${bean_Staff.censusAddress}</td>
			</tr>
			<tr>
				<td align="right"><label><b>居住地:</b></label></td>
				<td colspan="3">${bean_Staff.liveAddress}</td>
			</tr>
			<tr>
				<td align="right"><label><b>档案所在地:</b></label></td>
				<td colspan="3">${bean_Staff.archivesAdd}</td>
			</tr>
			<tr>
				<td align="right"><label><b>犯罪记录:</b></label></td>
				<td colspan="3">${bean_Staff.crimeRecord}</td>
			</tr>
			<tr>
				<td align="right"><label><b>境外婚史:</b></label></td>
				<td colspan="3">${bean_Staff.overSeasMarry}</td>
			</tr>
		</table>
		<div class="faceupload">
			<!--图片上传-->
			<div class="site-upload">
			<c:if test="${bean_Staff.IDCardFile!=null&&bean_Staff.IDCardFile!=\"\"}">
				<img src="${DOMAIN}${bean_Staff.IDCardFile}"/>
			</c:if>
			<c:if test="${bean_Staff.IDCardFile==null||bean_Staff.IDCardFile==\"\"}">
				<img src="/yw-nm/resources/images/p_01.png"/>
			</c:if>
				
			</div>
		</div>
			<div style="clear:both"></div>
		</div>
		
		<div class="formtitle">
			<span>部门信息</span>
		</div>
		<table class="formview">
			<tr>
				<td align="right"><label><b>部门:</b></label></td>
				<td>${bean_Staff.SDepart_str}</td>
				<td align="right"><label><b>部门负责人:</b></label></td>
				<td>
					<c:if test="${bean_Staff.flags3==1}">
						是
					</c:if>
					<c:if test="${bean_Staff.flags3==2}">
						否
					</c:if>
				</td>
				
			</tr>
			<tr>
				<td align="right"><label><b>职务:</b></label></td>
				<td>${bean_Staff.SPost_str}</td>
				<td align="right"><label><b>职称:</b></label></td>
				<td>${bean_Staff.SJob_str}</td>
			</tr>
			<tr>
				<td align="right"><label><b>电子邮件:</b></label></td>
				<td>${bean_Staff.SEmail}</td>
			
			</tr>
			<tr>
				<td align="right"><label><b>从事涉密岗位累计年限:</b></label></td>
				<td colspan="3">${bean_Staff.secrecyYears}</td>
			</tr>
		</table>
		<div class="formtitle">
			<span>密级、出境信息</span>
		</div>
		<table class="formview">
			<tr>
				<td align="right"><label><b>核心涉密人员:</b></label></td>
				<td>
					<c:if test="${bean_Staff.level==1}">
						（知悉、使用、管理<em class="red">绝密级</em>国家秘密事项的人员）
					</c:if>
					<c:if test="${bean_Staff.level==2}">
						（知悉、使用、管理<em class="red">机密级</em>国家秘密事项的人员）
					</c:if>
					<c:if test="${bean_Staff.level==3}">
						（知悉、使用、管理<em class="red">秘密级</em>国家秘密事项的人员）
					</c:if>
				</td>
			</tr>
			<tr>
				<td align="right"><label><b>出国护照:</b></label></td>
				<td>
					<c:if test="${bean_Staff.passport==1}">
						有
					</c:if>
					<c:if test="${bean_Staff.passport==2}">
						无
					</c:if>
				</td>
			</tr>
			<tr>
				<td align="right"><label><b>国外绿卡:</b></label></td>
				<td>
					<c:if test="${bean_Staff.greenCard==1}">
						有
					</c:if>
					<c:if test="${bean_Staff.greenCard==2}">
						无
					</c:if>
				</td>
			</tr>
		</table>
		<div class="formtitle">
			<span>教育经历</span>
		</div>
		<table class="tablelist">
			<thead>
				<tr>
					<th>起止时间</th>
					<th>结束时间</th>
					<th>院校名称</th>
					<th>专业</th>
					<th>学制(年)</th>
					<th>学位</th>
					<th>证明人</th>
					<th>备注</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="#request.staffEduList" var="var">
				<tr>
				<!-- 起止时间 -->
				<td><s:property value="#var.EStartTime_str"/></td>
				<!--结束时间 -->
				<td><s:property value="#var.EEndTime_str"/></td>
				<!--院校名称 -->
				<td><s:property value="#var.institutionName"/></td>
				<!--专业 -->
				<td><s:property value="#var.discipline"/></td>
				<!--学制 -->
				<td><s:property value="#var.ESystem"/></td>
				<!--学位 -->
				<td><s:property value="#var.degree_str"/></td>
				<!--证明人 -->
				<td><s:property value="#var.ewitness"/></td>
				<!--备注 -->
				<td><s:property value="#var.ERemarks"/></td>
				</tr>		
				</s:iterator>
			</tbody>
		</table>
		<div class="formtitle">
			<span>工作经历</span>
		</div>
		<table class="tablelist">
			<thead>
				<tr>
					<th>起止时间</th>
					<th>结束时间</th>
					<th>单位名称</th>
					<th>职务</th>
					<th>职称</th>
					<th>工作职责</th>
					<th>证明人</th>
					<th>手机</th>
					<th>备注</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="#request.staffJobList" var="var">
				<tr>
				<!-- 起止时间 -->
				<td><s:property value="#var.WStartTime_str"/></td>
				<!--结束时间 -->
				<td><s:property value="#var.WEndTime_str"/></td>
				<!--单位名称-->
				<td><s:property value="#var.WUnitName"/></td>
				<!--职务 -->
				<td><s:property value="#var.WPost_str"/></td>
				<!--职称 -->
				<td><s:property value="#var.WJob_str"/></td>
				<!--工作职责 -->
				<td><s:property value="#var.WDuty"/></td>
				<!--证明人-->
				<td><s:property value="#var.wwitness"/></td>
				<!--手机-->
				<td><s:property value="#var.WPhone"/></td>
				<!--备注 -->
				<td><s:property value="#var.WRemarks"/></td>
				</tr>		
				</s:iterator>
			</tbody>
		</table>
		<div class="formtitle">
			<span>家庭成员</span>
		</div>
		<table class="tablelist">
			<thead>
				<tr>
					<th>关系</th>
					<th>姓名</th>
					<th>性别</th>
					<th>年龄</th>
					<th>国籍</th>
					<th>单位</th>
					<th>职务</th>
					<th>职称</th>
					<th>住址</th>
					<th>手机</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="#request.familyInfoList" var="var">
				<tr>
				<!-- 关系 -->
				<td><s:property value="#var.relation"/></td>
				<!--姓名 -->
				<td><s:property value="#var.FName"/></td>
				<!--性别-->
				<td><s:property value="#var.FSex_str"/></td>
				<!--年龄 -->
				<td><s:property value="#var.FAge"/></td>
				<!--国籍 -->
				<td><s:property value="#var.FNationality_str"/></td>
				<!--单位-->
				<td><s:property value="#var.FUnit"/></td>
				<!--职务-->
				<td><s:property value="#var.FPost"/></td>
				<!--职称-->
				<td><s:property value="#var.FJob"/></td>
				<!--住址-->
				<td><s:property value="#var.FAddress"/></td>
				<!--手机-->
				<td><s:property value="#var.FPhone"/></td>
				</tr>		
				</s:iterator>
			</tbody>
		</table>
		<div class="formtitle">
			<span>附件</span>
		</div>
		<ul class="enclosures">
				<c:choose>
							<c:when test="${bean_Staff.SFilePath != null && bean_Staff.SFilePath !=\"\" }">
								<c:forEach items="${fn:split(bean_Staff.SFilePath,\",\") }" var="item">
									<li>
										<div class="site-upload">
											<img src="${DOMAIN}${item}"/>
										</div>
									</li>
								</c:forEach>
							</c:when>
						</c:choose>
		</ul>
	</div>
	<script type="text/javascript">
		//设置头部位置
		var siteData = {
			siteName : '配置管理',
			siteChild : {
				siteName : '人员管理',
				siteChild : {
					siteName : '人员预览'
				}
			}
		};
		topSite(siteData);
	</script>
</body>
</html>