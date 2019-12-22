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
		modelStruct : function (model, tab) {
			var keyValueDeal = function (model, tab) {
				var result = "";
				var reg = /{(.*?)=(.*?)}/;
				var res = reg.exec(model);
				if (res != null) {
					var context = res[0];
					var key = res[1];
					var value = res[2];
					var endIndex = res.index + context.length;
					var tailStr = model.substring(endIndex);

					result += tab + key + " : '" + value + "',\n";
					result += innerDeal(tailStr, tab);
				}
				return result;
			}

			var innerDeal = function (model, tab) {
				var reg = /for\((.*?)\){/;
				var res = reg.exec(model);
				if (res == null) {
					return keyValueDeal(model, tab);
				}

				var result = "";

				var forId = res[1];
				result += tab + forId + " : [{\n";
				var regStr = "forReg = /for\\(" + forId + "\\){(.*?)}" + forId + "/";
				eval(regStr);
				res = forReg.exec(model);

				var startIndex = res.index;
				var endIndex = startIndex + res[0].length;
				var headStr = model.substring(0, startIndex);
				var forContext = res[1];
				var tailStr = model.substring(endIndex);

				return keyValueDeal(headStr, tab) + result
						+ innerDeal(forContext, tab + "\t")
						+ tab + "},],\n"
						+ innerDeal(tailStr, tab);
			};

			if (arguments.length <= 0) {
				return null;
			}
			tab = tab === undefined ? "\t" : tab;

			return "{\n" + innerDeal(model, tab) + "}\n";
		},
		model : function (model, data) {
			var dealTargetStr = function (orgStr, data) {
				var key = orgStr[1];
				var value = data[key];

				return value === undefined ? orgStr[2] : value;
			};

			var dealForStr = function (orgStr, res, data) {
				var forId = res[1];
				var regStr = "forReg = /for\\(" + forId + "\\){(.*?)}" + forId + "/";
				eval(regStr);
				res = forReg.exec(model);

				if (res == null) {
					throw new Error("for(" + forId + ")不匹配！");
				}

				var forContext = res[1];
				var startIndex = res.index;
				var endIndex = startIndex + res[0].length;

				var headStr = model.substring(0, startIndex);
				var tailStr = model.substring(endIndex);

				var forData = data[forId];
				var forResult = "";
				for (var index = 0; index < forData.length; index++) {
					var dataObj = forData[index];
					forResult += $$.model(forContext, dataObj);
				}

				return headStr + forResult + tailStr;
			}

			if (arguments.length == 1 && typeof model === 'string') {
				return $$.htmlModel[model];
			}
			if (arguments.length == 2 && typeof model === 'string' 
				&& typeof data === 'object') {
				var forReg = /for\((.*?)\){/;
				var res = forReg.exec(model);
				if (res != null) {
					model = dealForStr(model, res, data);
				}
				var reg = /{([A-Za-z_]+[\w_]*?)=(.*?)}/;
				var res = reg.exec(model);

				if (res == null) {
					return model;
				}
				var startIndex = res.index;
				var endIndex = startIndex + res[0].length;
				var headStr = model.substring(0, startIndex);
				var targetStr = res[0];
				var tailStr = model.substring(endIndex);

				targetStr = dealTargetStr(res, data);

				return $$.model(headStr + targetStr + tailStr, data);
			}
		},
		hashcode : function(string) {
			var isNull = function(string) {
				return string == null || string.value == "";
			};
			
			var switchHashCodeToInt = function(num) {
				var MAX_VALUE = 0x7fffffff;  
				var MIN_VALUE = -0x80000000;  
				if(num > MAX_VALUE || num < MIN_VALUE) {  
				    return num &= 0xFFFFFFFF;  
				}  
				return num;  
			};

			var hashCode = 0;
			
			if(!isNull(string)) {
				for(var index = 0; index < string.length; index++) {
					hashCode = hashCode * 31 + string.charCodeAt(index);  
					hashCode = switchHashCodeToInt(hashCode);
				}
			}
			
			return hashCode;
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

