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

/*
.开头表示类名称
#开头表示id名称
$开头表示name名称
只有一个*，表示所有
没有都没有，直接是标签名称
 */

var get = function (element) {
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
};

function addEvent (element, event, fun, type) {
	var innerAddEvent = element.attachEvent 
			? function (element, event, fun, type) {
				element.attachEvent("on" + event, fun);
			}
			: function (element, event, fun, type) {
				element.addEventListener(event, fun, type);
			};

	addEvent = function (element, event, fun, type) {
		if (arguments.length == 1 && (element instanceof Array 
				|| typeof element === 'object')) {
			if (element instanceof Array) {
				// TODO
			}
			return;
		}
		console.log("hehhe")
        alert("me")
        // console.log(element)
		if (arguments.length < 2) {
			throw new Error("缺少必要的参数:[元素]和[事件名称]");
		}
		if (typeof element != 'object' || typeof event != 'string') {
			throw new Error("错误的参数类型");
		}
		fun = fun ? fun : function () {};
		type = type ? type : false;

		innerAddEvent(element, event, fun, type);
	};
    console.log("index.js")
	addEvent(element, event, fun, type);
};
