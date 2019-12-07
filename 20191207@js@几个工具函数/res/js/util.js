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
}

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
}
	// var funs = {
	// 	'*' : function () {
	// 		return document.getElementsByTagName("*");
	// 	},
	// 	'.' : function (name) {
	// 		return document.getElementsByClass(name.substring(1));
	// 	},
	// 	'#' : function (name) {
	// 		return document.getElementById(name.substring(1));
	// 	},
	// 	'$' : function (name) {
	// 		return document.getElementsByName(name.substring(1));
	// 	},
	// 	'T' : function (name) {
	// 		return document.getElementsTagByName(name);
	// 	},
	// };

	// var searchFun = funs[element.substring(0, 1)];

	// return (searchFun ? searchFun : funs['T'])(element);

	// if (element === '*') {
	// 	return document.getElementsByTagName("*");
	// }
	// var startAlpha = element.substring(0, 1);
	// if (startAlpha == '.') {
	// 	return docuemnt.getElementsByClass(element.substring(1));
	// }
	// if ()
