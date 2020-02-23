var $, tab, dataStr, layer;
layui.config({
	base : "/"+common.projectname+"/js/"
}).extend({
	"bodyTab" : "bodyTab"
})
layui.use([ 'bodyTab', 'form', 'element', 'layer', 'jquery' ], function() {

	var form = layui.form, element = layui.element;
	$ = layui.$;
	layer = parent.layer === undefined ? layui.layer : top.layer;
	tab = layui.bodyTab({
		openTabNum : "50", // 最大可打开窗口数量
		url : "json/navs.json" // 获取菜单json地址
	});

	// 通过顶部菜单获取左侧二三级菜单 注：此处只做演示之用，实际开发中通过接口传参的方式获取导航数据
	function getData(json) {
		$.getJSON(tab.tabConfig.url, function(data) {
			if (json == "contentManagement") {
				dataStr = data.contentManagement;
				// 重新渲染左侧菜单
				tab.render();
			} else if (json == "memberCenter") {
				dataStr = data.memberCenter;
				// 重新渲染左侧菜单
				tab.render();
			} else if (json == "systemeSttings") {
				dataStr = data.systemeSttings;
				// 重新渲染左侧菜单
				tab.render();
			} else if (json == "seraphApi") {
				dataStr = data.seraphApi;
				// 重新渲染左侧菜单
				tab.render();
			}
		})
	}
	// 页面加载时判断左侧菜单是否显示
	// 通过顶部菜单获取左侧菜单
	$(".topLevelMenus li,.mobileTopLevelMenus dd").click(function() {
		if ($(this).parents(".mobileTopLevelMenus").length != "0") {
			$(".topLevelMenus li").eq($(this).index()).addClass("layui-this").siblings().removeClass("layui-this");
		} else {
			$(".mobileTopLevelMenus dd").eq($(this).index()).addClass("layui-this").siblings().removeClass("layui-this");
		}
		$(".layui-layout-admin").removeClass("showMenu");
		$("body").addClass("site-mobile");
		getData($(this).data("menu"));
		// 渲染顶部窗口
		tab.tabMove();
	})

	// 隐藏左侧导航
	$(".hideMenu").click(function() {
		if ($(".topLevelMenus li.layui-this a").data("url")) {
			layer.msg("此栏目状态下左侧菜单不可展开"); // 主要为了避免左侧显示的内容与顶部菜单不匹配
			return false;
		}
		$(".layui-layout-admin").toggleClass("showMenu");
		// 渲染顶部窗口
		tab.tabMove();
	})

	// 通过顶部菜单获取左侧二三级菜单 注：此处只做演示之用，实际开发中通过接口传参的方式获取导航数据
	//getData("contentManagement");

	// 手机设备的简单适配
	$('.site-tree-mobile').on('click', function() {
		$('body').addClass('site-mobile');
	});
	$('.site-mobile-shade').on('click', function() {
		$('body').removeClass('site-mobile');
	});

	// 添加新窗口
	$("body").on("click", ".layui-nav .layui-nav-item a:not('.mobileTopLevelMenus .layui-nav-item a')", function() {
		// 如果不存在子级
		if ($(this).siblings().length == 0) {
			addTab($(this));
			$('body').removeClass('site-mobile'); // 移动端点击菜单关闭菜单层
		}
		$(this).parent("li").siblings().removeClass("layui-nav-itemed");
	})

	// 清除缓存
	

//锁屏
	function lockPage(){
		layer.open({
			title : false,
			type : 1,
			content : '<div class="admin-header-lock" id="lock-box">'+
					'<div class="input_btn">'+
				'<input type="password" class="admin-header-lock-input layui-input" autocomplete="off" placeholder="请输入密码解锁.." name="lockPwd" id="lockPwd" />'+
				'<button class="layui-btn" id="unlock">解锁</button>'+
				'</div>'+
				'<p>请输入“123456”，否则不会解锁成功哦！！！</p>'+
				'</div>',
			closeBtn : 0,
			shade : 0.9,
			success : function(){
				//判断是否设置过头像，如果设置过则修改顶部、左侧和个人资料中的头像，否则使用默认头像
				if(window.sessionStorage.getItem('userFace') &&  $(".userAvatar").length > 0){
					$(".userAvatar").attr("src",$(".userAvatar").attr("src").split("images/")[0] + "images/" + window.sessionStorage.getItem('userFace').split("images/")[1]);
				}
			}
		})
		$(".admin-header-lock-input").focus();
	}
	$(".lockcms").on("click",function(){
		window.sessionStorage.setItem("lockcms",true);
		lockPage();
	})
	// 解锁
	$("body").on("click","#unlock",function(){
		if($(this).siblings(".admin-header-lock-input").val() == ''){
			layer.msg("请输入解锁密码！");
			$(this).siblings(".admin-header-lock-input").focus();
		}else{
			if($(this).siblings(".admin-header-lock-input").val() == "123456"){
				window.sessionStorage.setItem("lockcms",false);
				$(this).siblings(".admin-header-lock-input").val('');
				layer.closeAll("page");
			}else{
				layer.msg("密码错误，请重新输入！");
				$(this).siblings(".admin-header-lock-input").val('').focus();
			}
		}
	});
})

// 打开新窗口
function addTab(_this) {
	tab.tabAdd(_this);
}


