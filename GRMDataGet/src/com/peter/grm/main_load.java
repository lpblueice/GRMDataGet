package com.peter.grm;

import java.io.IOException;

public class main_load {

	public static void main(String[] args) throws IOException
	{
		System.out.println("hello!");
		
		GrmOperate grmOperate = new GrmOperate();
		
		grmOperate.grmLogon();
		grmOperate.grmGetEnumVar();

	}
	


}




