(function (){
	$$.package("com.mec.video");
	
	$$.action("doLogin", function () {
		var id = $("#txtId").val();
		var password = $("#pswPassword").val();
		password = $$.hashcode(password);
		$("#modalLogin").modal("hide");
		
		var success = function (text) {
			console.log("success:" + text);
		};
		var error = function (text) {
			console.log(text);
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
		console.log("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
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
