package com.hx.cs_one2multi.server.test;

import java.util.Scanner;

import com.hx.cs_one2multi.serve.core.One2MultiServer;

public class TestForServer {

	public static void main(String[] args) {
		One2MultiServer server = new One2MultiServer().setPort(54188);
		Scanner in = new Scanner(System.in);
		
		String mess = "";
		while (!mess.equalsIgnoreCase("exit")) {
			mess = in.nextLine();
			if (mess.equals("startup")) {
				server.startup();
			} else if (mess.equals("shutdown")) {
				server.shutdown();
			} else if (mess.equalsIgnoreCase("exit")) {
				break;
			} else {
				System.out.println("Œﬁ–ß√¸¡Ó");
			}
		}
		in.close();
	}

}
