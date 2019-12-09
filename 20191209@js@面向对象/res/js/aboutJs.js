var arr = [3, 4, 5, 6, 7, 8, 9];
console.log("arr[0]:", arr[0]);
console.log("arr['0']:", arr['0']);

var obj = {
	key1 : 123,
	key2 : "abcde",
	index : true,
};

var index = "key1";
console.log("obj[index]:", obj[index]);

/*
var obj = {};
console.log("obj:", obj);
obj.attr1 = 123;
console.log("obj:", obj);
obj.attr1 = "abc";
console.log("obj:", obj);
obj.attr2 = [4, 2, 6, 8, 1, 9];
console.log("obj:", obj);
obj.attr3 = {
	member1 : 100,
	member2 : {
		innerMem : "abcdeffg",
	},
	member3 : function () {
		alert("这是深深隐藏的咚咚！");
	},
};
console.log("obj:", obj);
obj.attr3.member3();

obj.attr3.member3 = function () {
	alert("被更改的深深隐藏的咚咚！");
}
obj.attr3.member3();
console.log("typeof obj.attr3.member3:", typeof obj.attr3.member3, obj.attr3.member3);

console.log('obj["attr3"]', obj["attr3"]);
*/

/*
function fun(val1, val2) {
	// console.log(val1);
	// console.log("arguments.length:", arguments.length);
	if (arguments.length == 0) {
		console.log("没有传递任何参数！");
		return;
	}
	if (arguments.length == 1) {
		console.log("传递的参数有一个:", val1);
		return;
	}
	if (arguments.length == 2) {
		console.log("传递的参数有2个:", val1, val2);
		return;
	}
}

console.log("fun:", fun);
fun(true, "abde");
*/

/*
var a = 123;
var b = 5 + a;
console.log(b);
b = a - 3;
console.log("b:", b);
console.log(typeof b);
b = a - undefined;
console.log("b:", b);
console.log(typeof b);
b = a + false;
console.log("b:", b);
console.log(typeof b);

a = 123;
b = "123";
console.log(a == b);
console.log(a === b);
console.log((typeof a) == 'number');
console.log((typeof b) == 'string');
var c = [1, 2, 3];
console.log(typeof c);
console.log(c instanceof Array);

a = 50;
a = 0 ? a : 10;
console.log("a:", a);
a = null ? a : 20;
console.log("a:", a);
a = undefined ? a : 30;
console.log("a:", a);
*/

/*
var a;
console.log(typeof a);
console.log("a:", a);
a = null;
console.log(typeof a);
console.log("a:", a);
a = "123";
console.log(typeof a);
console.log(a.length != undefined);
console.log("a:", a);
a = true;
console.log(typeof a);
console.log("a:", a);
a = 3.14;
console.log(typeof a);
console.log(a.length != undefined);
console.log("a:", a);
a = new Date;
console.log(typeof a);
console.log("a:", a);
a = [];
console.log(typeof a);
console.log(a instanceof Array);
console.log(a.length != undefined);
console.log("a:", a);
a = {};
console.log(typeof a);
console.log("a:", a);
*/
