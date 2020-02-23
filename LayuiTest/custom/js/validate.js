layui.use([ 'form' ], function() {
	var form = layui.form;
	// 自定义验证规则
	form.verify({
		// 长度限制
		size : function(value, item) {
			var maxsize = $(item).attr("maxsize");
			var minsize = $(item).attr("minsize");
			if (maxsize) {
				maxsize=parseInt(maxsize);
				if (value.length>maxsize) {
					return "最大长度："+maxsize;
				}
			}
			if (minsize) {
				minsize=parseInt(minsize);
				if (value.length < minsize) {
					return "最小长度："+minsize;
				}
			}
		},
		//确认验证
		confirm :function(value, item) {
			var equalTo = $(item).attr("equalTo");
			if (equalTo) {
				 
				if (value != $("#"+equalTo).val()) {
					return "两个值不相等";
				}
			}
			 
		},
		//范围
		range:function(value, item) {
			var maxrange = $(item).attr("maxrange");
			var minrange = $(item).attr("minrange");
			if (maxrange) {
				maxrange=parseInt(maxrange);
				if (value>maxrange) {
					return "最大值为："+maxsize;
				}
			}
			if (minrange) {
				minrange=parseInt(minrange);
				if (value < minrange) {
					return "最小值为："+minrange;
				}
			}
		},
	});
});