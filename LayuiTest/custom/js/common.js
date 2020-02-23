var common={
		projectname:"LayuiTest",
		//是否开启开发模式
		dev: true,
		//开发时使用的后台地址
		devUrl:"127.0.0.1:8080",
		//上线时使用的后台地址
		prodUrl:"xxxxxxx",
		//服务器名
		serverName:"/jingleRinging/"
	};
var staticurl='';
(function() {
	staticurl="//" + window.location.host +"/"+common.projectname;

	// 动态加载js
	dynamicLoadJs(staticurl+"/custom/js/constant.js", function() {
		dynamicLoadJs(staticurl+"/custom/js/cache.js", function() {
			ajaxInit();
			dynamicLoadJs(staticurl+ "/custom/js/permission.js");
		});
	});
	dynamicLoadJs(staticurl+ "/custom/js/validate.js");

})()

$(".close-btn").click(function() {
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
})

function checkAll(form) {
	form.on('checkbox(check-all)', function(data) {
		var checkedState = data.elem.checked;
		if(checkedState == true) {
			$(".check-one").not("[disabled]").prop("checked", true);
			form.render('checkbox');
		} else {
			$(".check-one").prop("checked", false);
			form.render('checkbox');
		}
	});
}

function getCheckedId() {
	var ids = new Array();
	$('.layui-table-fixed  .check-one:checked').each(function(i, v) {
		ids.push($(v).val())
	});
	return ids;
}

function ajaxInit() {
	$.ajaxSetup({
		headers: { // 默认添加请求头
			"jwttoken": cache.token.get()
		},
		layerIndex: -1,
		beforeSend: function() {
			this.layerIndex = layer.msg('数据提交中，请稍候', {
				icon: 16,
				time: false,
				shade: 0.2
			});
		},
		complete: function() {
			layer.close(this.layerIndex);
		},
		error: function(xhr, status, error) {
			console.log('发送AJAX请求到"' + this.url + '"时出错[' + xhr.status + ']：' + error);
			layer.alert('部分数据加载失败，可能会导致页面显示异常，请刷新后重试', {
				skin: 'layui-layer-molv',
				closeBtn: 0,
				shift: 4
			});
		}
	});

}

/**
 * 动态加载JS
 * 
 * @param {string}
 *            url 脚本地址
 * @param {function}
 *            callback 回调函数
 */
function dynamicLoadJs(url, callback) {
	var head = document.getElementsByTagName('head')[0];
	var script = document.createElement('script');
	script.type = 'text/javascript';
	script.src = url;
	if(typeof(callback) == 'function') {
		script.onload = script.onreadystatechange = function() {
			if(!this.readyState || this.readyState === "loaded" || this.readyState === "complete") {
				callback();
				script.onload = script.onreadystatechange = null;
			}
		};
	}
	head.appendChild(script);
}

function openAreaLayer(title, cotent, width, height, maxmin, success) {
	var index = layui.layer.open({
		title: title,
		type: 2,
		area: [width, height],
		maxmin: maxmin,
		content: cotent,
		success: success
	})
}

function loadAjax(url, method, data, success) {
	$.ajax({
		url: url,
		method: method,
		data: data,
		success: function(json) {
			if(json.code != 1006) {
				success(json);
			} else {
				//续约逻辑
				cache.token.set(json.data);
				//初始化ajax
				ajaxInit();
				console.log("续约成功")
				//应为被后端重新做了续约逻辑，原有业务请求要重新做一次
				loadAjax(url, method, data, success);
			}

		}
	});
}

function openFullLayer(title, cotent, success) {
	var index = layui.layer.open({
		title: title,
		type: 2,
		content: cotent,
		success: success
	})
	layui.layer.full(index);
	window.sessionStorage.setItem("index", index);
	// 改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
	$(window).on("resize", function() {
		layui.layer.full(window.sessionStorage.getItem("index"));
	})
}

function confirmLayer(title, msg, success) {
	layer.confirm(msg, {
		icon: 3,
		title: title
	}, function(index) {
		success(layer, index);
	});
}

function splitId(data, id, paramStr) {
	var result = '';
	if(!id) {
		id = "id";
	}
	if(!paramStr) {
		paramStr = 'id';
	}
	if(data && data.length > 0) {
		for(var i in data) {
			for(var key in data[i]) {
				if(key == id) {
					result += paramStr + '=' + data[i][key] + "&";
				}
			}
		}
		result = result.substring(0, result.length - 1);
	}
	return result;
}

function fomrVal(form, filter, data) {
	var formHtml = $("form[lay-filter='" + filter + "']");
	if(data) {
		for(var key in data) {
			var tmp = formHtml.find('[name="' + key + '"]');
			if($(tmp)[0]) {
				if($(tmp)[0].tagName == "INPUT") {
					if($(tmp)[0].type == 'text') {
						$(tmp).val(data[key]);
					} else if($(tmp)[0].type == 'hidden') {
						$(tmp).val(data[key]);
					} else if($(tmp)[0].type == 'radio') {
						$(tmp).each(function(i, v) {
							if(v.value == data[key]) {
								$(v).attr("checked", "true")
							}
						});
					}
				} else if($(tmp)[0].tagName == "TEXTAREA") {
					$(tmp).html(data[key]);
				} else if($(tmp)[0].tagName == "SELECT") {
					$(tmp).val(data[key]);
				}

			}
		}
	}
	// form.val(filter, JSON.parse(JSON.stringify(data)));
	form.render(); // 更新全部
}

function getsearchVal() {
	var jsonData = {};
	$(".searchVal").each(function(i, v) {
		jsonData[$(v).attr("name")] = $(v).val();
	})
	return jsonData;
}

function merger(obj1, obj2) {
	return $.extend(obj1, obj2);
}

// 获取url后的参数值
function getUrlParam(key) {
	var href = window.location.href;
	var url = href.split("?");
	if(url.length <= 1) {
		return "";
	}
	var params = url[1].split("&");

	for(var i = 0; i < params.length; i++) {
		var param = params[i].split("=");
		if(key == param[0]) {
			return param[1];
		}
	}
}
/**
 * 公共返回
 * 
 * @param responseloadAjax
 * @param successCallBack
 * @param failCallBackgetsearchVal
 * @returns
 */
function baseCallBack(response, successCallBack, failCallBack) {
	if(response.code == constant.response.code.success) {
		successCallBack(response);
	} else if(response.code == constant.response.code.fail) {
		if(failCallBack) {
			failCallBack(response);
		} else {
			layer.msg(response.msg, constant.msg.fail);
		}
	} else if(response.code == constant.response.code.exception) {
		layer.msg(response.msg, constant.msg.fail);
	} else if(response.code == constant.response.code.nologin || response.code == constant.response.code.otherlogin) {
		layer.msg(response.msg, constant.msg.fail, function() {
			location.href = staticurl  + "/login.html";
		});
		cache.cleanAll();
	} else if(response.code == constant.response.code.noauth) {
		layer.msg(response.msg, constant.msg.warn);
	}
}

var parentReloadCallBack = function(response) {
	layer.msg(response.msg, constant.msg.success, function() {
		parent.layer.closeAll("iframe");
		// 刷新父页面
		parent.location.reload();
	});

}