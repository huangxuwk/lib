function makeOptions(start, end, defaultSelected) {
	var result = "";
	for (var i = start; i <= end; i++) {
		result += "<option value='";
		result += i;
		result += "'";
		if (defaultSelected == i) {
			result += " selected='selected'"
		}
		result += ">" + i;
		result += "</option>";
	}

	return result;
}

function initBirthDate(selectedYear, selectedMonth, element) {
	element.innerHTML = "";
	var date = new Date();
	date.setYear(selectedYear);
	date.setMonth(selectedMonth);
	date.setDate(0);
	var lastDate = date.getDate();

	element.innerHTML = makeOptions(1, lastDate);
}

function initBirthYear(startAge, endAge, defaultAge) {
	var today = new Date();
	var thisYear = today.getFullYear();
	var startYear = thisYear - endAge;
	var endYear = thisYear - startAge;
	var defaultYear = thisYear - defaultAge;

	return makeOptions(startYear, endYear, defaultYear);
}

function initBirthMonth(birthMonth) {
	var optionMonth = makeOptions(1, 12);
	birthMonth.innerHTML = optionMonth;
}

function setHobbiesStatus(checked) {
	var hobbies = get("$hobby");
	for (var index = 0; index < hobbies.length; index++) {
		if (checked == undefined) {
			hobbies[index].checked = !hobbies[index].checked;
		} else {
			hobbies[index].checked = checked;
		}
	}
}

var birthYear = get("#birthYear");
var birthYearStr = initBirthYear(16, 35, 19);
birthYear.innerHTML = birthYearStr;
var birthMonth = get("#birthMonth");
initBirthMonth(birthMonth);
var birthDate = get("#birthDate");
initBirthDate(birthYear.value, birthMonth.value, birthDate);

var btnHobbyAll = get("#btnHobbyAll");
btnHobbyAll.onclick = function (event) {
	console.log("hehe");
	setHobbiesStatus(true);
}

var btnHobbyRev = get("#btnHobbyRev");
addEvent(btnHobbyRev, "click", function (event) {
	setHobbiesStatus();
});

var btnHobbyClear = get("#btnHobbyClear");
addEvent(btnHobbyClear, "click", function (event) {
	setHobbiesStatus(false);
});

/*
var btnHobbyClear = get("#btnHobbyClear");
var btnHobbyRev = get("#btnHobbyRev");
addEvent([{
	ele : btnHobbyClear,
	event : "click",
	action : function () {
		// ...
	},
}, {
	ele : btnHobbyRev,
	event : "click",
	action : function () {
		// ... ...
	},
}]);
addEvent({
	ele : btnHobbyClear,
	event : "click",
	action : function () {
		// ...
	},
});
 */

birthYear.onclick = function (event) {
	initBirthDate(birthYear.value, birthMonth.value, birthDate);
}

birthMonth.onclick = function (event) {
	initBirthDate(birthYear.value, birthMonth.value, birthDate);
}
