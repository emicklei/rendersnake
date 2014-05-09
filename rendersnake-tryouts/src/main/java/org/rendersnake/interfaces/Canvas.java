package org.rendersnake.interfaces;


public class Canvas implements Html,Body,Head,Div{
	public Html html(){
		return this;
	}

	
	public Body body() {
		return this;
	}

	
	public Head head() {
		return this;
	}

	
	public Html _body() {
		return this;
	}

	
	public Html _head() {
		return this;
	}

	
	public Body _div() {
		return this;
	}

	
	public Canvas _html() {
		return this;
	}

	
	public Div div() {
		return this;
	}
	
	public Body write(String s) {
		return this;
	}
}
