/**
 * 描述：数据表格空间
 * 功能：用于面积有限却要显示较多数据
 */
//分页
document.write('<link rel="stylesheet" href="/yw-nm/resources/lib/mmGrid-master/src/mmGrid-customer.css"/>');
document.write('<script type="text/javascript" src="/yw-nm/resources/lib/mmGrid-master/src/mmGrid.js"></script>');

//重新绘制页面上的mmGrid的高度
function resizeGrid(){
	var gridHeight = ($(document).height() - $(".seachform").height() - $(".tools").height() - 61)+"px";
	$(".mmGrid").css("height",gridHeight);
}
