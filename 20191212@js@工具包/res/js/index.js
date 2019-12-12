(function () {
	$$.package("com.mec.video");

	var Student = $$.model("Student");
	var student = new Student("12345678", "张三", "44324", true);
	$$.model("Student.student", student);

	var stu = $$.model("student");
	console.log("stu:", stu)

	console.log(window);

	$$.addEvent("#btnOk", "click", $$.action("clickOk"));
	$$.addEvent({
		element : "#btnExit",
		name : "click",
		action : function (event) {
			alert("事件2");
		}
	});
	$$.addEvent([{
		element : "#btnExit",
		name : "click",
		action : function (event) {
			alert("事件3");
		}
	}, {
		element : "#btnCancel",
		name : "click",
		action : function (event) {
			alert("事件4");
		}
	}]);
}) ();
