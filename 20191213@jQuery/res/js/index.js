(function () {
	// var btnOk = $("#btnOk");
	// console.log("btnOk:", btnOk);
	// console.log($("input[type='text']"));
	// console.log("$ === jQuery:", $ === jQuery);
	// console.log(window);
	var doSomething = function () {
		alert("111111111111");
	};
	$("#btnOk").click(function (event) {
		$("#btnExit").fadeToggle(1000, doSomething);
	});

	var newVar = $("<div></div>")
			.prop("id", "myDiv")
			.css({
				width : "300px",
				height : "300px",
				background : "pink",
			});
	$("#div").append(newVar);
	console.log("newVar:", newVar);
}) ();
