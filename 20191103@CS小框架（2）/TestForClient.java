package com.hx.cs_framework.test.client;

import java.io.IOException;

public class TestForClient {

	public static void main(String[] args) {
		try {
			new ClientView().connectToServer();;
		} catch (IOException e) {
			e.printStackTrace();
		} 	
	}
	
}
