var cache = {
	token: {
		get: function() {
			return localStorage.getItem(constant.jwttoken);
		},
		set: function(token) {
			localStorage.setItem(constant.jwttoken, token);
		},
		remove: function() {
			localStorage.removeItem(constant.jwttoken);
		}
	},
	permission: {
		get: function() {
			return localStorage.getItem(constant.permission);
		},
		set: function(permission) {
			localStorage.setItem(constant.permission, permission);
		},
		remove: function() {
			localStorage.removeItem(constant.permission);
		}
	},
	user: {
		get: function() {
			return localStorage.getItem(constant.user);
		},
		set: function(user) {
			localStorage.setItem(constant.user, user);
		},
		remove: function() {
			localStorage.removeItem(constant.user);
		}
	},
	code: {
		get: function() {
			return localStorage.getItem(constant.code);
		},
		set: function(code) {
			localStorage.setItem(constant.code, code);
		},
		remove: function() {
			localStorage.removeItem(constant.code);
		}
	},
	clean: function() {
		localStorage.removeItem(constant.permission);
		localStorage.removeItem(constant.user);
		localStorage.removeItem(constant.code);
	},
	cleanAll: function() {
		localStorage.removeItem(constant.permission);
		localStorage.removeItem(constant.user);
		localStorage.removeItem(constant.code);
		localStorage.removeItem(constant.jwttoken);
	}
}