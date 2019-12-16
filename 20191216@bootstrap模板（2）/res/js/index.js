(function () {
	$$.package("com.mec.video");

	var normalNav = $$.model("normalNav");
	var str = $$.modelStruct(normalNav);
	console.log(str);
	str = $$.model(normalNav, {
		firstHref : '#abcd',
		firstItem : '微易码视频',
		liList : [{
			itemActive : 'active',
			itemId : 'stuLogin',
			itemHref : '#',
			itemContext : '登录',
		}, {
			itemActive : '',
			itemId : 'stuRegistry',
			itemHref : '#',
			itemContext : '注册',
		}, {
			itemActive : '',
			itemId : 'stuTeacher',
			itemHref : '#',
			itemContext : '金牌讲师',
		}, {
			itemActive : '',
			itemId : 'stuCourse',
			itemHref : '#',
			itemContext : '名牌课程',
		}, {
			itemActive : '',
			itemId : 'stuStudent',
			itemHref : '#',
			itemContext : '优秀学员',
		}],
	});
	$("#headNav").html(str);

	$("#stuCourse").click(function (event) {
		alert(1);
	});

	var listNav = $$.model("listNav");
	var structStr = $$.modelStruct(listNav);
	console.log(structStr);
}) ();

/*
{
	firstHref : 'index.html',
	firstItem : 'First',
	liList : [{
		itemActive : 'active',
		itemId : '',
		itemHref : '#',
		itemContext : 'Item',
	},],
} 
{
	firstHref : '#index.html',
	firstItem : 'First',
	liList : [{
		itemActive : 'active',
		itemId : '',
		itemHref : '',
		itemContext : 'Item',
	},],
}
*/

/*
	"/*{\n"
		+ "\tfirstHref : 'index.html',\n"
		+ "\tfirstItem : 'First',\n"
		+ "\tliList : [{\n"
			+ "\t\titemActive : 'active',\n"
			+ "\t\titemId : '',\n"
			+ "\t\titemHref : '#',\n"
			+ "\t\titemContext : 'Item',\n"
		+ "\t},],\n"
	+ "}\n*\/";
	console.log(normalNavObj);
 */
