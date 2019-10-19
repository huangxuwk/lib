function initOptions(start, end, defaultSelected) {
    var i;
    var result = "";
    for (i = start; i <= end; i++) {
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

function initBirthDate(birthDate, selectedYear, selectedMonth) {
    var date = new Date();
    date.setYear(selectedYear);
    date.setMonth(selectedMonth);
    date.setDate(0);
    var lastDate = date.getDate();

    birthDate.innerHTML = initOptions(1, lastDate);
}

function initBirthYear(birthYear, startAge, endAge, defaultAge) {
    var today = new Date();
    var thisYear = today.getFullYear();
    var startYear = thisYear - endAge;
    var endYear = thisYear - startAge;
    var defaultYear = thisYear - defaultAge;

    birthYear.innerHTML = initOptions(startYear, endYear, defaultYear);
}

function initBirthMonth(birthMonth) {
    birthMonth.innerHTML = initOptions(1, 12);
}
var birthYear = document.getElementById("birthYear");
var birthMonth = document.getElementById("birthMonth");
var birthDate = document.getElementById("birthDate");
initBirthYear(birthYear, 16, 35, 18);
initBirthMonth(birthMonth);
initBirthDate(birthDate, birthYear.value, birthMonth.value);
