(function (){
	$$.package("com.mec.video");
	
	$$.action("doLogin", function () {
		var id = $("#txtId").val();
		var password = $("#pswPassword").val();
		password = $$.hashcode(password);
		$("#modalLogin").modal("hide");
		
		var success = function (text) {
			var result = text.result;
			if (result == "SUCCESS") {
				// TODO 跳转到下一个页面
				alert("欢迎！");
			} else {
				alert("用户账号或密码错误！");
			}
		};
		var error = function (text) {
			console.log("error:", text);
		};
		$.ajax({
			url : "login",
			type : "get",
			data : {
				id : id,
				password : password,
			},
			async : false,
			success : success,
			error : error,
		});
	});
})();
//var xhr = new XMLHttpRequest;
//xhr.onreadystatechange = function (context) {
//	if (xhr.readyState == 4) {
//		if (xhr.status < 400) {
//			success(xhr.responseText);
//		} else {
//			error(xhr.responseText);
//		}
//	}
//}
//xhr.open('get', "login?id=" + id + "&password=" + password);
//xhr.setRequestHeader('Content-Type', 
//'application/x-www-form-urlencoded; charset-UTF-8');
//xhr.send();

//		var loginRequest = "login?id=" + id + "&password=" + password;
//		window.location = loginRequest;
