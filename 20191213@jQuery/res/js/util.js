(function () {
	$$ = {
		curPackage : undefined,
		package : function (packageName) {
			if (packageName != undefined && typeof packageName === 'string') {
				var names = packageName.split(".");
				var curPackage = $$;
				for (var name in names) {
					var pn = names[name];
					if (curPackage[pn] == undefined) {
						curPackage[names[name]] = {};
					}
					curPackage = curPackage[names[name]];
				}
				if (curPackage.view == undefined) {
					curPackage["view"] = {};
					curPackage.action = {};
					curPackage.model = {};
				}
				$$.curPackage = curPackage;
				return;
			}
			if (packageName == undefined) {
				return $$.curPackage;
			}
		},
		model : function (model, obj) {
			if ($$.isClass(model)) {
				$$.curPackage.model[model.name] = model;
				return model;
			}
			if (arguments.length == 1 && typeof model === 'string') {
				return $$.curPackage.model[model];
			}
			if (typeof model === 'string' && typeof obj === 'object') {
				var names = model.split(".");
				var Klass = $$.curPackage.model[names[0]];
				$$.curPackage.model[names[1]] = obj;
			}
		},
		action : function (name, callback) {
			if (callback === undefined) {
				if (typeof name != 'string') {
					throw new Error("action()的参数类型不正确！");
				}
				return $$.curPackage.action[name];
			}
			if (typeof name != 'string' || !(callback instanceof Function)) {
				throw new Error("action()参数不合法！");
			}
			var fun = $$.curPackage.action[name];
			if (fun != undefined) {
				throw new Error("函数:" + name + "()已经存在！");
			}
			
			$$.curPackage.action[name] = callback;
		},
		addEvent : function (element, event, action) {
			var dealObject = function (obj) {
				if (obj.element === undefined 
					|| obj.name === undefined 
					|| obj.action === undefined) {
					throw new Error("缺少参数！");
				}
				var ele = ((typeof obj.element) === 'string' 
					? $$.get(obj.element) : obj.element);
				if (typeof obj.name != 'string'
					|| ele["on" + obj.name] === undefined) {
					throw new Error("事件名称错误，或事件不存在！");
				}
				if (!(obj.action instanceof Function)) {
					throw new Error("事件代码错误！");
				}
				ele.addEventListener(obj.name, obj.action, true);
			};

			if (arguments.length == 3) {
				if (typeof element === 'string') {
					element = $$.get(element);
				} else if (typeof element != 'object') {
					throw new Error("第一个参数不合法！");
				}
				if (typeof event != 'string') {
					throw new Error("第二个参数必须是事件名称(不带on)！");
				}
				if (element["on" + event] === undefined) {
					throw new Error("事件名称错误！");
				}
				if (!$$.isClass(action)) {
					throw new Error("缺少事件代码！");
				}

				element.addEventListener(event, action);
				return element;
			}

			if (arguments.length != 1) {
				return;
			}
			if (element instanceof Array) {
				for (var index = 0; index < element.length; index++) {
					dealObject(element[index]);
				}
			} else {
				dealObject(element);
			}
		},
		Extends : function (Parent, Child) {
			if (Parent == undefined 
				|| Child == undefined
				|| Parent.prototype == undefined
				|| Child.prototype == undefined) {
				return;
			}

			var TempClass = new Function;
			TempClass.prototype = Parent.prototype;
			var tmp = new TempClass;
			Child.prototype = tmp;
			Child.prototype.constructor = Child;
		},
		isClass : function (obj) {
			return obj != null && obj.prototype != undefined;
		},
		get : function (element) {
			if (typeof element === 'object') {
				return element;
			}
			if (typeof element != 'string') {
				return null;
			}
			var funs = {
				'*' : function () {
					return document.getElementsByTagName("*");
				},
				'.' : function (name) {
					return document.getElementsByClass(name.substring(1));
				},
				'#' : function (name) {
					return document.getElementById(name.substring(1));
				},
				'$' : function (name) {
					return document.getElementsByName(name.substring(1));
				},
				'T' : function (name) {
					return document.getElementsByTagName(name);
				},
			};

			var searchFun = funs[element.substring(0, 1)];

			return (searchFun ? searchFun : funs['T'])(element);
		},
	}

	document.getElementsByClass = function (className) {
		var result = [];
		var i = 0;

		var elementAll = document.getElementsByTagName("*");
		for (var index = 0; index < elementAll.length; index++) {
			if (elementAll[index].className.length > 0) {
				var classNameString = elementAll[index].className;
				var classNameList = classNameString.split(" ");
				for (var name in classNameList) {
					if (classNameList[name] === className) {
						result[i++] = elementAll[index];
					}
				}
			}
		}

		return result;
	};
}) ();

