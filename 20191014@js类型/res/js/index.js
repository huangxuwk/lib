function makeOptions(start, end, defaultSelected) {
	// console.log("defaultSelected:", defaultSelected);
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
	var hobbies = document.getElementsByName("hobby");
	for (var index = 0; index < hobbies.length; index++) {
		if (checked == undefined) {
			hobbies[index].checked = !hobbies[index].checked;
		} else {
			hobbies[index].checked = checked;
		}
	}
}

var birthYear = document.getElementById("birthYear");
var birthYearStr = initBirthYear(16, 35, 19);
birthYear.innerHTML = birthYearStr;
var birthMonth = document.getElementById("birthMonth");
initBirthMonth(birthMonth);
var birthDate = document.getElementById("birthDate");
initBirthDate(birthYear.value, birthMonth.value, birthDate);

var btnHobbyAll = document.getElementById("btnHobbyAll");
btnHobbyAll.onclick = function (event) {
	setHobbiesStatus(true);
}

var btnHobbyClear = document.getElementById("btnHobbyClear");
btnHobbyClear.onclick = function (event) {
	setHobbiesStatus(false);
}

var btnHobbyRev = document.getElementById("btnHobbyRev");
btnHobbyRev.onclick = function (event) {
	setHobbiesStatus();
}

birthYear.onclick = function (event) {
	initBirthDate(birthYear.value, birthMonth.value, birthDate);
}

birthMonth.onclick = function (event) {
	initBirthDate(birthYear.value, birthMonth.value, birthDate);
}

/*
console.log("birthYear.innerHTML:", birthYear.innerHTML);
alert("Hello, javascript");
console.log("Hello, javascript!");
*/
