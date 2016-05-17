package com.peter.grm;

import java.util.ArrayList;

public class GrmEqu {
	
	private String strGrmName = "";
	
	private String strGrmID = ""; 
	private String strPasswd = "";
	

	private String strADDR = "";
	private String strSID = "";
	
	private boolean isLogon = false; 
	
	private String errLogonID = "";
	private String errLogonDes = ""; 
	
	private String errGetDataID ="";
	private String errGetDataDes = "";
	
	private ArrayList<GrmData> varArray = new ArrayList<GrmData>();
	
	public GrmEqu(String GrmName,String GrmID, String Passwd) throws Exception
	{
		strGrmName = GrmName;
		
		if(GrmID.equals(""))
		{
			throw new Exception("GRM 设备ID不能为空");
		}
		else
		{
			strGrmID = GrmID;
		}
		
		strPasswd = Passwd;
	}
	
	
	public void grmLogon() throws Exception
	{
		
		//If SID exists, we can reuse it. If it is not available, we should log in again.
		if(!strSID.equals(""))
		{
			return;
		}
		
		
		//String sr = HttpRequest.sendPost("http://www.yunplc.com:7080/exlog", "GRM="+strGrmID+"&"+"PASS="+strPasswd);
		String sr = HttpRequest.sendPost("http://www.yunplc.com:7080/exlog", "GRM="+strGrmID+"&"+"PASS="+strPasswd);
		System.out.println(sr);
		
		String[] strSplit = sr.split("\n");
		
		String isSuccessful = strSplit[0];
		
		if(isSuccessful.equals("ERROR"))
		{
			errLogonID  = strSplit[1];
			errLogonDes = strSplit[2];
			
			throw new Exception("登陆设备出错:"+strSplit[1]+"--"+strSplit[2]);			
		}
		else if(isSuccessful.equals("OK"))
		{
			strADDR = ((strSplit[1]).split("="))[1];
			strSID  = ((strSplit[2]).split("="))[1];		
			
			System.out.println("strADDR:"+strADDR);
			System.out.println("strSID:"+strSID);
		}	
	}
	
	
	private void GetAvailableSID() throws Exception
	{
		String sr = HttpRequest.sendGet("http://"+strADDR+"/exdata?SID="+strSID+"&"+"OP=E", "NTRPGC");
		
		String[] strSplit = sr.split("\n");
		
		String isSuccessful = strSplit[0];
		
		if(isSuccessful.equals("ERROR"))
		{
			errGetDataID  = ((strSplit[1]).split("="))[1].trim();
			errGetDataDes = ((strSplit[2]).split("="))[1].trim();			
			
			if(Integer.parseInt(errGetDataID) == 8)//If errorcode is 8, it means the SID is not available. We should log in again.
			{
				grmLogon();
			}
			else
			{
				throw new Exception("登陆设备出错:"+strSplit[1]+"--"+strSplit[2]);	
			}
		}
	}
	
	
	public void grmGetEnumVar() throws Exception
	{
		GetAvailableSID();
		
		String sr = HttpRequest.sendPost("http://"+strADDR+"/exdata?SID="+strSID+"&"+"OP=E", "NTRPGC");
		
		//System.out.println(strSID);
		System.out.println(strGrmName);
		System.out.println(sr);
		
		String[] strSplit = sr.split("\n");
		
		String isSuccessful = strSplit[0];
		
		if(isSuccessful.equals("ERROR"))
		{
			errGetDataID  = ((strSplit[1]).split("="))[1].trim();
			errGetDataDes = ((strSplit[2]).split("="))[1].trim();
			
			throw new Exception("登陆设备出错:"+strSplit[1]+"--"+strSplit[2]);	
		}
		
		
	
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
		
		return;

	}
	
	

}
