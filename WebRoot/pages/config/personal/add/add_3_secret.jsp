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
							<a href="#tab3"class='selected'>
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
							<a onclick="f_page(8)">附件</a>
						</li>
					</ul>
				</div>
				<!--密级-->
				<div id="tab3" class="tabson">
					<form id="form2" action="secretSave.do">
						<input name="stafferId" id="stafferId"  
							 type="hidden" value="${bean_Staff.stafferId}"/>
					<table class="forminfo leveltable">
						<tr>
							<td>
								<div>
									<input type="radio" name="level" id="level1" value="1"
										<c:if test="${bean_Staff.level==1}">checked="true"</c:if>
									/>
									<label for="level1">核心涉密人员</label><div class="clear"></div>
								</div>
								<p>（知悉、使用、管理<em class="red">绝密级</em>国家秘密事项的人员）</p>
							</td>
						</tr>
						<tr>
							<td>
								<div>
									<input type="radio" name="level" id="level2"  value="2"
										<c:if test="${bean_Staff.level==2}">checked="true"</c:if>
									/>
									<label for="level2">重要涉密人员</label><div class="clear"></div>
								</div>
								<p>知悉、使用、管理<em class="red">机密级</em>国家秘密事项的人员）</p>
							</td>
						</tr>
						<tr>
							<td>
								<div>
									<input type="radio" name="level" id="level3" value="3"
										<c:if test="${null==bean_Staff.level||
													''==bean_Staff.level||
											bean_Staff.level==3}">
											checked="true"
										</c:if>
									/>
									<label for="level3"> 一般涉密人员</label><div class="clear"></div>
								</div>
								<p>（知悉、使用、管理<em class="red">秘密级</em>国家秘密事项的人员）</p>
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