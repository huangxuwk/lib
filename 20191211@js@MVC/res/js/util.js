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
				if (curPackage["view"] == undefined) {
					curPackage["view"] = {};
					curPackage["action"] = {};
					curPackage["model"] = {};
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
				$$.curPackage["model"][model.name] = model;
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
			return obj.prototype != undefined;
		},
		get : function (element) {
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

