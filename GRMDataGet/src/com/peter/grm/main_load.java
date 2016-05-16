package com.peter.grm;

import java.io.IOException;

public class main_load {

	public static void main(String[] args) throws IOException
	{
		System.out.println("hello!");
		
		//String strGrmID = "20437182836";
		//String strPasswd = "32345598";
		
		//String strADDR = "";
		//String strSID = "";
		
		
		
		GrmOperate grmOperate = new GrmOperate();
		
		grmOperate.grmLogon();
		grmOperate.grmGetEnumVar();
		
		
		grmOperate.grmLogon();
		grmOperate.grmGetEnumVar();

	}
	


}




