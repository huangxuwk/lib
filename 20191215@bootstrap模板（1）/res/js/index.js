(function () {
	$$.package("com.mec.video");

	var cmd = "var num = 10;";
	eval(cmd);
	console.log(num);

	console.log(window);
	var normalNav = $$.model("normalNav");
	var str = $$.model(normalNav, {
		firstHref : '#abcd',
		firstItem : '微易码视频',
		liList : [{
			itemActive : 'active',
			itemHref : '#',
			itemContext : '登录',
		}, {
			itemActive : '',
			itemHref : '#',
			itemContext : '注册',
		}],
	});
	$("#headNav").html(str);
}) ();
