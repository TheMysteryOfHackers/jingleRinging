var constant = {
	baseUrl: "http://" + (common.dev?common.devUrl:common.prodUrl) + common.serverName,
	jwttoken: "jwttoken",
	permission: "permission",
	attribute: "attribute",
	code: "code",
	user: "user",
	msg: {
		success: {
			icon: 1,
			time: 1000 //2秒关闭（如果不配置，默认是3秒）
		},
		fail: {
			icon: 2,
			time: 1000 //2秒关闭（如果不配置，默认是3秒）
		},
		warn: {
			icon: 4,
			time: 2000 //2秒关闭（如果不配置，默认是3秒）
		}
	},
	response: {
		code: {
			success: 1000,
			fail: 1001,
			exception: 1002,
			nologin: 1003,
			noauth: 1004,
			otherlogin: 1005
		}
	},
	table: {
		cellMinWidth: 95,
		height: "full-245",
		request: {
			pageName: 'current', // 页码的参数名称，默认：page
			limitName: 'size' // 每页数据量的参数名，默认：limit
		},
		response: {
			statusCode: 1000, // 规定成功的状态码，默认：0
		},
		parseData: function(res) { // res 即为原始返回的数据
			return {
				"code": res.code, // 解析接口状态
				"msg": res.msg, // 解析提示文本
				"count": res.data.total, // 解析数据长度
				"data": res.data.records
				// 解析数据列表
			};
		}
	},
	page: {
		limits: [5, 10, 15, 20, 25, 50, 100],
		limit: 10,
		layout: ['prev', 'page', 'next', 'limit', 'refresh', 'skip', 'count'],
		toUri: function(obj) {
			var tmp = null;
			if (obj) {
				tmp = {
					current: obj.curr,
					size: obj.limit
				}
			} else {
				tmp = {
					current: 1,
					size: 10
				}
			}
			return tmp;
		}
	}
}
