package com.mec.xml_parser.test;

public class Test {

	public static void main(String[] args) {
//		System.out.println(AboutNativaBlock.num);
		System.out.println("--------------------");
		
		AboutNativaBlock aboutNativaBlock = new AboutNativaBlock();
		aboutNativaBlock.fun();
		
		System.out.println("----------------------------------");
		AboutNativaBlock aboutNativaBlock2 = new AboutNativaBlock();
		aboutNativaBlock2.fun();
		
		System.out.println("**************************");
		new AboutNativaBlock();
	}

}
