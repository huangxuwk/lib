package com.hx.complex;

public class SubComplex extends Complex {
	private int mem;

	public SubComplex() {
		super();
	}

	public SubComplex(Complex c) {
		super(c);
	}

	public SubComplex(double real, double vir) {
		super(real, vir);
	}

	public SubComplex(double real) {
		super(real);
	}

	public int getMem() {
		return mem;
	}

	public void setMem(int mem) {
		this.mem = mem;
	}

	@Override
	public String toString() {
		return super.toString() + "\n" + mem;
	}
	
}
