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

var birthYear = document.getElementById("birthYear");
var birthYearStr = initBirthYear(16, 35, 19);
birthYear.innerHTML = birthYearStr;
var birthMonth = document.getElementById("birthMonth");
initBirthMonth(birthMonth);
initBirthDate(birthYear.value, birthMonth.value, document.getElementById("birthDate"));

/*
console.log("birthYear.innerHTML:", birthYear.innerHTML);
alert("Hello, javascript");
console.log("Hello, javascript!");
*/
