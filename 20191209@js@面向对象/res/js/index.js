function Point (row, col) {
	this.setRow = function (row) {
		this.row = row ? row : 1;
	}

	this.setCol = function (col) {
		this.col = col ? col : 1;
	}

	this.getRow = function () {
		return this.row;
	}

	this.getCol = function () {
		return this.col;
	}

	this.toString = function () {
		return '(' + row + ", " + col + ')';
	}

	this.setRow(row);
	this.setCol(col);

}

function Complex() {

}

console.log("Point:", Point);
var point1 = new Point(4, 6);
var point2 = new Point(5);
var point3 = new Point();
console.log(point1);
console.log(point2);
console.log(point3);

point2.__proto__ = Complex.prototype;

console.log("point1 instanceof Point:", point1 instanceof Point);
console.log("point2 instanceof Point:", point2 instanceof Point);
console.log("point1 instanceof Complex:", point1 instanceof Complex);
console.log("point2 instanceof Complex:", point2 instanceof Complex);

console.log("Function:", Function);
console.log("Function.prototype === Point.__proto__:", Function.prototype === Point.__proto__);
console.log("Point instanceof Function:", Point instanceof Function);

console.log("Function.prototype === Function.__proto__:", Function.prototype === Function.__proto__);
console.log("Function instanceof Function:", Function instanceof Function);

console.log("Object:", Object);
console.log("Object.__proto__ === Function.prototype:", Object.__proto__ === Function.prototype);
console.log("Object.prototype.__proto__ === Object.prototype:", Object.prototype.__proto__ === Object.prototype);
console.log("Object.prototype.__proto__:", Object.prototype.__proto__);

console.log("Object instanceof Function:", Object instanceof Function);
console.log("Object instanceof Object:", Object instanceof Object);
console.log("Function instanceof Object:", Function instanceof Object);
