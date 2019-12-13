(function () {
	$$.package("com.mec.video");
	
	$$.model(function Student (_id, _name, _password, _status) {
		var id = _id;
		var name = _name;
		var password = _password;
		var status = _status;

		this.getId = function () {
			return id;
		}
		this.setId = function (_id) {
			id = _id;
		}
		this.getName = function () {
			return name;
		}
		this.setName = function (_name) {
			name = _name;
		}

		this.toString = function () {
			return id + (status ? " √ " : " × ") + name;
		}
	});
}) ();
