package com.peter.grm;

import java.io.IOException;
import java.util.ArrayList;

import org.omg.CORBA.PUBLIC_MEMBER;

public class main_load {

	public static void main(String[] args) throws Exception
	{
		System.out.println("Hello user, you are using peter's GetGrmData program");
		
		
		ArrayList<GrmEqu> grmEquArr = new ArrayList<GrmEqu>();
		
		grmEquArr.add(new GrmEqu("新天地西侧","20437182836","32345598"));		
		//grmEquArr.add(new GrmEqu("新天地东侧","20437182827","32345598"));
		//grmEquArr.add(new GrmEqu("天潼路", "50102582967", "dreamblue5598"));
		
		
		
		for (GrmEqu grmEqu : grmEquArr) 
		{
			grmEqu.grmLogon();
			grmEqu.grmReadAllVar();
			
			System.out.println("==============================" + grmEqu.getGrmName() + " data============================");
			for(GrmData grmData : grmEqu.getGrmEquVars())
			{
				System.out.println(grmData.varName + "," + grmData.varData);
			}
			
			
//			System.out.println("==============================" + "Write var to grm equ" + "===============================");
//			String strRequest = "";
//			strRequest += "1\n";
//			strRequest += "新天地站台指数\n";
//			strRequest += "21\n";
//			grmEqu.grmWriteVar(strRequest);
			
			grmEqu.grmGetLocation();
			System.out.println("==============================" + "Get grm equ location" + "===============================");
			System.out.println(grmEqu.getGrmLocationInfo().originData);
			System.out.println(grmEqu.getGrmLocationInfo().longtitude);
			System.out.println(grmEqu.getGrmLocationInfo().lantitude);
			System.out.println(grmEqu.getGrmLocationInfo().province);
			System.out.println(grmEqu.getGrmLocationInfo().city);
			System.out.println(grmEqu.getGrmLocationInfo().district);
			System.out.println(grmEqu.getGrmLocationInfo().street);
			System.out.println(grmEqu.getGrmLocationInfo().fullLocation);
			System.out.println(grmEqu.getGrmLocationInfo().nearMarkPoint);
		}
	}
	
	

}




