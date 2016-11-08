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
		<script type="text/javascript" src="/yw-nm/resources/js/package-validform.js"></script>
		<script type="text/javascript" src="/yw-nm/resources/js/jquery.idTabs.min.js"></script>
		<script type="text/javascript">
			$(function() {
				//下拉选择框渲染
				$(".select3").uedSelect({
					width: 162
				});
			});
		</script>
		<style type="text/css">
			.dfinput {width: 160px}
			.textinput {width: 398px;height: 100px}
			.forminfo {float: left;display: inline;}
			.faceupload {margin-left: 50px;margin-top: 5px;display: inline-block;}
			input[name=level],input[name=Passport],input[name=GreenCard]{float:left;}
			.leveltable td p{padding:15px 0px;}
			.leveltable td label{font-weight:bold;float:left;line-height:10px;margin-left:10px;}
			#enclosures li{float:left;padding-right: 10px;}
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
							<a href="#tab4"class='selected'>
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
							<a onclick="f_page(8)">附件</a>
						</li>
					</ul>
				</div>
				<!--出境信息-->
				<div id="tab4" class="tabson">
					<form id="form4" action="expelSave.do">
						<input name="stafferId" id="stafferId" type="hidden" 
							value="${bean_Staff.stafferId}"/>
					<table class="forminfo leveltable">
						<tr>
							<td style="height:40px;">
								<label>出国护照</label>
							</td>
							<td>
								<div style="float:left">
									<input type="radio" name="passport" id="Passport1" value="1"
										<c:if test="${bean_Staff.passport==1}">checked="true"</c:if>
									/>
									<label for="Passport1" style="font-weight: normal;">有</label>
								</div>
								<div style="float:right">
									<input type="radio" name="passport" id="Passport2" value="2"
										<c:if test="${null==bean_Staff.passport||
											''==bean_Staff.passport||bean_Staff.passport==2}">
											checked="true"
										</c:if>
									/>
									<label for="Passport2" style="font-weight: normal;">无</label>
								</div>
							</td>
						</tr>
						<tr>
							<td style="height:40px;">
								<label>国外绿卡</label>
							</td>
							<td>
								<div style="float:left">
									<input type="radio" name="greenCard" id="GreenCard1" value="1"
										<c:if test="${bean_Staff.greenCard==1}">checked="true"</c:if>
									/>
									<label for="GreenCard1" style="font-weight: normal;">有</label>
								</div>
								<div style="float:right">
									<input type="radio" name="greenCard" id="GreenCard2" value="2"
										<c:if test="${null==bean_Staff.greenCard||
											''==bean_Staff.greenCard||bean_Staff.greenCard==2}">
											checked="true"
										</c:if>
									/>
									<label for="GreenCard2" style="font-weight: normal;">无</label>
								</div>
							</td>
						</tr>
						<tr>
							<td></td>
							<td colspan="2">
								<input name="" type="submit" class="btn" value="保存" />
							</td>
						</tr>
					</table>
					</form>
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