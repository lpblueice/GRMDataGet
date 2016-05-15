package com.peter.grm;

import java.util.ArrayList;
import java.util.Arrays;

public class GrmOperate {
	
	private String strGrmID = "20437182836";
	private String strPasswd = "32345598";
	
	private String strADDR = "";
	private String strSID = "";
	
	private boolean isLogon = false; 
	
	private String errLogonID = "";
	private String errLogonDes = ""; 
	
	private String errGetDataID ="";
	private String errGetDataDes = "";
	
	private ArrayList varArray = new ArrayList();
	
	public boolean grmLogon()
	{
		String sr = HttpRequest.sendPost("http://www.yunplc.com:7080/exlog", "GRM="+strGrmID+"&"+"PASS="+strPasswd);
		//String sr = HttpRequest.sendPost("http://www.yunplc.com:7080/exlog?GRM="+strGrmID+"&"+"PASS="+strPasswd,"");
		
		System.out.println(sr);
		
		String[] strSplit = sr.split("\n");
		
		String isSuccessful = strSplit[0];
		
		if(isSuccessful.equals("ERROR"))
		{
			errLogonID  = ((strSplit[1]).split("="))[1];
			errLogonDes = ((strSplit[2]).split("="))[1];
			isLogon = false;
		}
		else if(isSuccessful.equals("OK"))
		{
			strADDR = ((strSplit[1]).split("="))[1];
			strSID  = ((strSplit[2]).split("="))[1];
			isLogon = true;
			
			System.out.println("strADDR:"+strADDR);
			System.out.println("strSID:"+strSID);
		}
		
		return isLogon;	
	}
	
	
	public boolean grmGetEnumVar()
	{
		
		String sr = HttpRequest.sendPost("http://"+strADDR+"/exdata?SID="+strSID+"&"+"OP=E", "NTRPGC");
		
		System.out.println(sr);
		
		String[] strSplit = sr.split("\n");
		
		String isSuccessful = strSplit[0];
		
		if(isSuccessful.equals("ERROR"))
		{
			errGetDataID  = strSplit[1];
			errGetDataDes = strSplit[2];
			return false;
		}
		else if(isSuccessful.equals("OK"))
		{
			int num = Integer.parseInt(strSplit[1].trim());
			
			for(int i=2;i<strSplit.length;i++)
			{
				ArrayList<String> varPro = new ArrayList<String>();
				String[] str = strSplit[i].split(",");
				for(int j=0;j<str.length;j++)
				{
					varPro.add(str[j]);
				}
				for(int k=varPro.size();k<6;k++)//If there is no elements, it means there are all empty string.
				{
					varPro.add("");
				}
				
				GrmData grmData = new GrmData();				
				grmData.varName =       (varPro.get(0) == null) ?  "" : varPro.get(0).trim();
				grmData.varType =       (varPro.get(1) == null) ?  "" : varPro.get(1).trim();
				grmData.varRWType =     (varPro.get(2) == null) ?  "" : varPro.get(2).trim();
				grmData.netPermission = (varPro.get(3) == null) ?  "" : varPro.get(3).trim();
				grmData.groupName =     (varPro.get(4) == null) ?  "" : varPro.get(4).trim();
				grmData.webVarDes =     (varPro.get(5) == null) ?  "" : varPro.get(5).trim();
				
				varArray.add(grmData);
			}
			
			return true;
		}
		
		return false;
	}

}
