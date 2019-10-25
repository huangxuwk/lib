package com.hx.complex;

/**
 * 关于复数的类；<br>
 * 这里向通过这种注释给大家演示“说明性”注释的写法
 * <ol type='i'> 成员
 * 		<li>real 实部，<font color="red" size="3">double</font>类型</li>
 * 		<li>vir 虚部，<font color="red" size="3">double</font>类型</li>
 * </ol>
 * @author 铁血教主
 * @version v0.0.1
 *
 */
public class Complex {
	private double real;
	private double vir;
	
	public Complex() {
		this(0.0, 0.0);
	}

	public Complex(double real) {
		this(real, 0.0);
	}

	public Complex(double real, double vir) {
		this.real = real;
		this.vir = vir;
	}
	
	public Complex(Complex c) {
		this(c.real, c.vir);
	}
	
	/**
	 * 本方法功能：c1 += c2
	 * @param other 参加加法的另一个运算数，相当于上式中的c2
	 * @return 对象本身
	 */
	public Complex add(Complex other) {
		this.real += other.real;
		this.vir += other.vir;
		return this;
	}
	
	/**
	 * 方法功能：c1 = c2 + c3
	 * @param one	被加数
	 * @param other 加数
	 * @return 和值；一个新Complex对象
	 */
	public static Complex add(Complex one, Complex other) {
		return new Complex(one).add(other);
	}
	
	private static Complex opposite(Complex c) {
		return new Complex(-c.real, -c.vir);
	}
	
	public Complex sub(Complex other) {
		return this.add(opposite(other));
	}
	
	public static Complex sub(Complex one, Complex other) {
		return new Complex(one).add(opposite(other));
	}
	
	public Complex mul(Complex other) {
		double realSave = real;
		real = real * other.real - vir * other.vir;
		vir = realSave * other.vir + vir * other.real;
//		(a1 + b1i)*(a2 + b2i)
//		a1*a2 - b1*b2 + (a1*b2 + a2*b1)i
		
		return this;
	}
	
	public static Complex mul(Complex one, Complex other) {
		return new Complex(one).mul(other);
	}
	
	private static Complex reciprocal(Complex c) {
		double model = c.real*c.real + c.vir*c.vir;
		if (Math.abs(model) < 1e-6) {
			return null;
		}
		return new Complex(c.real / model, -c.vir / model);
	}
	
	public Complex div(Complex one) {
		Complex c = reciprocal(one);
		return c == null ? null : mul(c);
	}
	
	public static Complex div(Complex one, Complex other) {
		Complex c = reciprocal(other);
		
		return c == null ? null : new Complex(one).mul(c);
	}
	
	public double getReal() {
		System.out.println("这行了getReal()方法！");
		return real;
	}

	public void setReal(double real) {
		this.real = real;
	}

	public double getVir() {
		return vir;
	}

	public void setVir(double vir) {
		this.vir = vir;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Complex)) {
			return false;
		}
		
		Complex c = (Complex) obj;
		return Math.abs(c.real - real) < 1e-6
				&& Math.abs(c.vir - vir) < 1e-6;
	}

	@Override
	public String toString() {
		return "(" + real + ", " + vir + ")";
	}
	
}
