package com.peter.grm;

import java.io.IOException;
import java.util.ArrayList;

import org.omg.CORBA.PUBLIC_MEMBER;

public class main_load {

	public static void main(String[] args) throws Exception
	{
		System.out.println("hello!");
		
		//String strGrmID = "20437182836";
		//String strPasswd = "32345598";
		
		//String strADDR = "";
		//String strSID = "";
		
		
		
//		GrmOperate grmOperate = new GrmOperate();
//		
//		grmOperate.grmLogon();
//		grmOperate.grmGetEnumVar();
//		
//		
//		grmOperate.grmLogon();
//		grmOperate.grmGetEnumVar();
		
		
		ArrayList<GrmEqu> grmEquArr = new ArrayList<GrmEqu>();
		
		//grmEquArr.add(new GrmEqu("新天地西侧","20437182836","32345598"));		
		//grmEquArr.add(new GrmEqu("新天地东侧","20437182827","32345598"));
		grmEquArr.add(new GrmEqu("天潼路", "50102582967", "32345598"));
		
		
		for (GrmEqu grmEqu : grmEquArr) 
		{
			grmEqu.grmLogon();
		//	grmEqu.grmGetEnumVar();
		}
	}
	
	

}




