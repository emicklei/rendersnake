package com.company.web;

import org.rendershark.http.HttpServer;

public class Launcher {
	public static void main(String[] args) {
		HttpServer.main(new String[]{ "./src/main/resources/rendershark.properties"} );
	}
}
