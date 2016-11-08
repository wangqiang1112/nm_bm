<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!--分页-->
<div class="pagin">
	<div class="pagemessage">
		共<i class="red">&nbsp;${page.totalCount }&nbsp;</i>条记录，当前显示第&nbsp;<i class="red">${page.pageIndex}</i>&nbsp;页
	</div>
	<div id="pagelist" class="pagelist"></div>
</div>
<input type="hidden" name="pageIndex" id="pageIndex" value="${page.pageIndex}" />
<script type="text/javascript">
	$(function(){
	        //点击搜索重置页数
			    $("#btnSearch").click(function(){
			    $("#pageIndex").val(1)
			    })
			//分页初始化
			 $("#pagelist").pagination({
				items:${page.totalCount},
				itemsOnPage:${page.pageSize},
				currentPage:${page.pageIndex},
				prevText:' ',
				nextText:' ',
				displayedPages:3,
				edges:2,
				cssStyle:'customer-theme',
				onPageClick:function(pageNumber,event){
					$("#pageIndex").val(pageNumber);
					$("#myform").submit();
				}
			});
	});
</script>