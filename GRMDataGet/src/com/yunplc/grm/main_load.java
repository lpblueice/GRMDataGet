package com.yunplc.grm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.omg.CORBA.PUBLIC_MEMBER;

public class main_load {


	public static void main(String[] args) throws Exception
	{
		System.out.println("Hello user, you are using peter's GetGrmData program");
		
		
		ArrayList<GrmEqu> grmEquArr = new ArrayList<GrmEqu>();
		
		grmEquArr.add(new GrmEqu("���������","20437182836","32345598"));		
		//grmEquArr.add(new GrmEqu("����ض���","20437182827","32345598"));
		//grmEquArr.add(new GrmEqu("����·", "50102582967", "dreamblue5598"));	
		
		for (GrmEqu grmEqu : grmEquArr) 
		{
			grmEqu.grmLogon();
			grmEqu.grmGetGrmHistory();

		}
	}
	
//	public static void main(String[] args) throws Exception
//	{
//		System.out.println("Hello user, you are using peter's GetGrmData program");
//		
//		
//		ArrayList<GrmEqu> grmEquArr = new ArrayList<GrmEqu>();
//		
//		grmEquArr.add(new GrmEqu("���������","20437182836","32345598"));		
//		grmEquArr.add(new GrmEqu("����ض���","20437182827","32345598"));
//		grmEquArr.add(new GrmEqu("����·", "50102582967", "dreamblue5598"));
//		
//		
//		Timer freshDataTimer = new Timer();
//		
//		freshDataTimer.schedule(new TimerTask() {
//	        public void run(){
//
//	    		for (GrmEqu grmEqu : grmEquArr) 
//	    		{
//	    			try {
//						grmEqu.grmLogon();
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//	    			try {
//						grmEqu.grmReadAllVar();
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//	    			
//	    			System.out.println("==============================" + grmEqu.getGrmName() + " data============================");
//	    			for(GrmData grmData : grmEqu.getGrmEquVars())
//	    			{
//	    				System.out.println(grmData.varName + "," + grmData.varData);
//	    			}
//	    			
//	    			
////	    			System.out.println("==============================" + "Write var to grm equ" + "===============================");
////	    			String strRequest = "";
////	    			strRequest += "1\n";
////	    			strRequest += "�����վָ̨��\n";
////	    			strRequest += "21\n";
////	    			grmEqu.grmWriteVar(strRequest);
//	    			
//	    			try {
//						grmEqu.grmGetLocation();
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//	    			System.out.println("==============================" + "Get grm equ location" + "===============================");
//	    			System.out.println(grmEqu.getGrmLocationInfo().originData);
//	    			System.out.println(grmEqu.getGrmLocationInfo().longtitude);
//	    			System.out.println(grmEqu.getGrmLocationInfo().lantitude);
//	    			System.out.println(grmEqu.getGrmLocationInfo().province);
//	    			System.out.println(grmEqu.getGrmLocationInfo().city);
//	    			System.out.println(grmEqu.getGrmLocationInfo().district);
//	    			System.out.println(grmEqu.getGrmLocationInfo().street);
//	    			System.out.println(grmEqu.getGrmLocationInfo().fullLocation);
//	    			System.out.println(grmEqu.getGrmLocationInfo().nearMarkPoint);
//	    		}
//	        }
//		}, 2000 , 1000);
//		
//		
//	}
	
	
	
//	//ִ��һ�� 
//	public static void main(String[] args) throws Exception
//	{
//		System.out.println("Hello user, you are using peter's GetGrmData program");
//		
//		
//		ArrayList<GrmEqu> grmEquArr = new ArrayList<GrmEqu>();
//		
//		grmEquArr.add(new GrmEqu("���������","20437182836","32345598"));		
//		//grmEquArr.add(new GrmEqu("����ض���","20437182827","32345598"));
//		//grmEquArr.add(new GrmEqu("����·", "50102582967", "dreamblue5598"));	
//		
//		for (GrmEqu grmEqu : grmEquArr) 
//		{
//			grmEqu.grmLogon();
//			grmEqu.grmReadAllVar();
//			
//			System.out.println("==============================" + grmEqu.getGrmName() + " data============================");
//			for(GrmData grmData : grmEqu.getGrmEquVars())
//			{
//				System.out.println(grmData.varName + "," + grmData.varData);
//			}
//			
//			
////			System.out.println("==============================" + "Write var to grm equ" + "===============================");
////			String strRequest = "";
////			strRequest += "1\n";
////			strRequest += "�����վָ̨��\n";
////			strRequest += "21\n";
////			grmEqu.grmWriteVar(strRequest);
//			
//			grmEqu.grmGetLocation();
//			System.out.println("==============================" + "Get grm equ location" + "===============================");
//			System.out.println(grmEqu.getGrmLocationInfo().originData);
//			System.out.println(grmEqu.getGrmLocationInfo().longtitude);
//			System.out.println(grmEqu.getGrmLocationInfo().lantitude);
//			System.out.println(grmEqu.getGrmLocationInfo().province);
//			System.out.println(grmEqu.getGrmLocationInfo().city);
//			System.out.println(grmEqu.getGrmLocationInfo().district);
//			System.out.println(grmEqu.getGrmLocationInfo().street);
//			System.out.println(grmEqu.getGrmLocationInfo().fullLocation);
//			System.out.println(grmEqu.getGrmLocationInfo().nearMarkPoint);
//		}
//	}
	

}




