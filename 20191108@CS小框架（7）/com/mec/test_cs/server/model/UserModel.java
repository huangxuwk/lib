package com.mec.test_cs.server.model;

public class UserModel {
	private String id;
	private String nick;
	private String password;
	private boolean status;
	private Complex complex;
	
	public UserModel() {
	}

	public UserModel(String id, String nick, String password, boolean status) {
		this.id = id;
		this.nick = nick;
		this.password = password;
		this.status = status;
		this.complex = new Complex(1.2, 3.4);
	}

	public Complex getComplex() {
		return complex;
	}

	public void setComplex(Complex complex) {
		this.complex = complex;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return id + "(" + nick + ") " + (status ? "√" : "×") + " " + this.complex;
	}
	
}
