/** layui-v1.0.2 经典模块化前端框架 LGPL license By www.layui.com */
;
layui.define("layer",
function(e) {
    "use strict";
    var a = layui.jquery,
    t = layui.layer,
    i = (layui.device(), "layui-upload-enter"),
    n = "layui-upload-iframe",
    r = {
        icon: 2,
        shift: 6
    },
    o = {
        file: "文件",
        video: "视频",
        audio: "音频"
    },
    s = function(e) {
        this.options = e
    };
    s.prototype.init = function() {
        var e = this,
        t = e.options,
        r = a("body"),
        s = a(t.elem || ".layui-upload-file"),
        u = a('<iframe id="' + n + '" class="' + n + '" name="' + n + '"></iframe>');
        return a("#" + n)[0] || r.append(u),
        s.each(function(r, s) {
            s = a(s);
            var u = '<form target="' + n + '" method="' + (t.method || "post") + '" key="set-mine" enctype="multipart/form-data" action="' + (t.url || "") + '"></form>',
            l = s.attr("lay-type") || t.type;
            t.unwrap || (u = '<div class="layui-box layui-upload-button">' + u + '<span class="layui-upload-icon"><i class="layui-icon">&#xe608;</i>' + (s.attr("lay-title") || t.title || "上传" + (o[l] || "图片")) + "</span></div>"),
            u = a(u),
            t.unwrap || u.on("dragover",
            function(e) {
                e.preventDefault(),
                a(this).addClass(i)
            }).on("dragleave",
            function() {
                a(this).removeClass(i)
            }).on("drop",
            function() {
                a(this).removeClass(i)
            }),
            s.parent("form").attr("target") === n && (t.unwrap ? s.unwrap() : (s.parent().next().remove(), s.unwrap().unwrap())),
            s.wrap(u),
            s.off("change").on("change",
            function() {
                e.action(this, l)
            })
        })
    },
    s.prototype.action = function(e, i) {
        var o = this,
        s = o.options,
        u = e.value,
        f = e.files || 0,
        l = a(e),
        p = l.attr("lay-ext") || s.ext || "",
        z = 0;
        if(l.length>5){
        	t.msg("文件最多不能超过5个",r);
        }else{
	        for(var k = 0;k<f.length;k++){
	        	z += f[k].size;
	        }
	        if(z>20971520){
	        	t.msg("文件总大小超过20M",r);
	        }else{
		        if (u) {
		            switch (i) {
		            case "file":
		                if (p && !RegExp("\\w\\.(" + p + ")$", "i").test(escape(u))) return t.msg("不支持该文件格式", r),
		                e.value = "";
		                break;
		            case "video":
		                if (!RegExp("\\w\\.(" + (p || "avi|mp4|wma|rmvb|rm|flash|3gp|flv") + ")$", "i").test(escape(u))) return t.msg("不支持该视频格式", r),
		                e.value = "";
		                break;
		            case "audio":
		                if (!RegExp("\\w\\.(" + (p || "mp3|wav|mid") + ")$", "i").test(escape(u))) return t.msg("不支持该音频格式", r),
		                e.value = "";
		                break;
		            default:
		                if (!RegExp("\\w\\.(" + (p || "jpg|png|gif|bmp|jpeg") + ")$", "i").test(escape(u))) return t.msg("不支持该图片格式", r),
		                e.value = ""
		            }
		        	s.before = s.before || function(input){return true};
		        	if(s.before(e)){
			            l.parent().submit();
			            var c = a("#" + n),
			            f = setInterval(function() {
			            	var docEle = document.getElementById(n).contentDocument;
			                var a;
			                try {
			                    a = docEle.body.innerHTML;
			                } catch(i) {
			                    t.msg("上传接口存在跨域", r),
			                    clearInterval(f)
			                }
			                if (a) {
			                    clearInterval(f),
			                    docEle.body.innerHTML="";
			                    try {
			                        a = JSON.parse(a)
			                    } catch(i) {
			                        return a = {},
			                        t.msg("请对上传接口返回JSON字符", r)
			                    }
			                    "function" == typeof s.success && s.success(a, e)
			                }
			            },
			            30);
			            e.value = ""
		        	}
		        }
	        }
        }
    },
    e("upload",
    function(e) {
        var a = new s(e = e || {});
        a.init()
    })
});