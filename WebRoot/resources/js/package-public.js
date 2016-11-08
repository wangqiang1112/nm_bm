/**
 * 公共JS，封装一些常用方法
 */

/**
 * 设置头部位置
 * @param {Object} s 数据格式：{siteName:'第一级',siteChild:{siteName:'第二级'}}
 */
function topSite(s){
   var firstStr = '<li><a href="/yw-nm/secrecy/main.do" target="rightFrame">首页</a></li>';
   function eachSites(t){
		if(t.siteChild){
			return '<li>'+t.siteName +'</li>' + eachSites(t.siteChild);
		}else{
			return '<li>'+t.siteName+'</li>';
		}
	}
   try{
   		$(".placeul",window.parent.frames["topFrame"].document).html(firstStr + eachSites(s));
   }catch(e){}
}

/**
 * 用户信息编辑
 * @param userId
 */
function editUser(userId){
	openWindow('编辑信息','/yw-nm/config/user/addOneUser.do?type=edit&userId='+userId,['570px','390px']);
}

/**
 * 跳转
 * @param url
 * @returns
 */
function toPage(method){
	window.location.href = '/yw-nm/secrecy/'+method;
}
	
/**
 * 打开一个新窗体
 * @param {String} title 标题
 * @param {String} url   需要加载的URL地址
 * @param {Object} area  在默认状态下，layer是宽高都自适应的，但当你只想定义宽度时，你可以area: '500px'，高度仍然是自适应的。当你宽高都要定义时，你可以area: ['500px', '300px']
 */
function openWindow(title,url,area){
	layer.open({
			  id:'openWin',
		      type: 2,
		      title: title,
		      shadeClose: true,
		      shade: [0.4, '#eee'],
		      area: area,
		      content: url
	});
}

/**
 * 关闭打开的窗体
 * 注意此方法只适用于在打开的窗体(子)内执行关闭
 * 如果要在打开窗体的页面(父)关闭窗体的话使用layer.close
 */
function closeWindow(){
	try{
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		parent.layer.close(index);
	}catch(e){
		
	}
}
/**
 * 弹出框重写
 * @param {Object} msg
 * @param {Number} icontype 左侧图标类型 0-无  1－成功 2－失败
 */
window.alert = function(msg,callback){
	var _callback = callback || null;
	var _icontype = arguments[2] || 0;
	if(_icontype == 0){
		layer.alert(msg,{title:'提示'},_callback);
	}else{
		layer.alert(msg,{icon:_icontype,title:'提示'},_callback);
	}
}
/**
 * 询问框重写
 * @param {Object} msg
 * @param {Object} callback
 */
window.confirm = function(msg,callback){
	layer.confirm(msg,{icon:3,title:'提示'},callback);
}

Date.prototype.format = function(fmt){
	var o = {
			"M+":this.getMonth()+1, //月份
			"d+":this.getDate(),	//日
			"h+":this.getHours(),   //小时
			"m+":this.getMinutes(), //分
			"s+":this.getSeconds(), //秒
			"q+":Math.floor((this.getMonth()+3)/3), //季度
			"S":this.getMilliseconds() //毫秒
	};
	if(/(y+)/.test(fmt)){
		fmt = fmt.replace(RegExp.$1,(this.getFullYear()+"").substr(4-RegExp.$1.length));
	}
	for(var k in o){
		if(new RegExp("("+k+")").test(fmt)){
			fmt = fmt.replace(RegExp.$1,(RegExp.$1.length==1)?(o[k]):(("00"+o[k]).substr((""+o[k]).length)));
		}
	}
	return fmt;
}

/**
 * 时间戳格式化成时间2010-01-01 10:22:23
 */
formatDate = function(now){
	var d = new Date(now);
	return d.format("yyyy-MM-dd hh:mm:ss");
}
