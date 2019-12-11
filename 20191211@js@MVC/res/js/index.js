(function () {
	$$.package("com.mec.video");

	var Student = $$.model("Student");
	var student = new Student("12345678", "张三", "44324", true);
	$$.model("Student.student", student);

	var stu = $$.model("student");
	console.log("stu:", stu)

	console.log(window);
}) ();
