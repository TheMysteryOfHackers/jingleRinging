function scanPermission() {
	$("[has-permission]").each(function(i, v) {
		if(!hasPermission($(v).attr("has-permission"))) {
			$(v).remove();
		}
	})
}

function hasPermission(code, data) {
	var permission = cache.code.get();
	var index = $.inArray(code, permission.split(","));
	if(index >= 0) {
		// 传入数据
		return true;
	} else {
		return false;
	}
}

(function() {
	scanPermission();
})();