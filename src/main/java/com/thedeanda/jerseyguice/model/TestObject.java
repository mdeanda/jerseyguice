package com.thedeanda.jerseyguice.model;

import java.io.Serializable;

public class TestObject implements Serializable {
	private static final long serialVersionUID = 1L;
	private String a;
	private String b;

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}
}
