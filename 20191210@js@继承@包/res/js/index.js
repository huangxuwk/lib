function Extends(Parent, Child) {
	if (Parent == undefined 
		|| Child == undefined
		|| Parent.prototype == undefined
		|| Child.prototype == undefined) {
		return;
	}

	var TempClass = new Function;
	TempClass.prototype = Parent.prototype;
	var tmp = new TempClass;
	Child.prototype = tmp;
	Child.prototype.constructor = Child;

	// Child.__proto__ = Parent.prototype;
	// Child.prototype.__proto__ = Parent.prototype;
};

function Animal (name) {
	this.name = (name ? name : "匿名");
	this.cry = function () {
		return "哺乳动物" + name + "在叫.";
	}
};
Animal.prototype.count = 0;

function Dog (name, type) {
	Animal.call(this, name);
	this.type = (type ? type : "未知品种");
	this.cry = function () {
		return type + name + "在汪汪。";
	}
};
// 在这里实现继承于Animal
Extends(Animal, Dog);
Dog.prototype.dog = "dog";

function Dogie (name, type, owner) {
	Dog.call(this, name, type);
	this.owner = owner;
	this.cry = function () {
		return owner + "家的" + type + name + "在汪汪。";
	}
};
Extends(Dog, Dogie);
Dogie.prototype.dogie = "dogie";

var animal = new Animal("狗");
var dog = new Dog("狗", "哈巴");
var dogie = new Dogie("狗", "哈巴", "小白");
console.log("animal:", animal);
console.log("dog:", dog);
console.log("dogie:", dogie);
console.log("-----------------------------------------------------")
console.log("dogie instanceof Object:", dogie instanceof Object);
console.log("dogie instanceof Animal:", dogie instanceof Animal);
console.log("dogie instanceof Dog:", dogie instanceof Dog);
console.log("dogie instanceof Dogie:", dogie instanceof Dogie);

console.log("Dog:", Dog);
console.log("Animal:", Animal);

console.log("window:", window);
