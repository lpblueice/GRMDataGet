package com.peter.grm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GrmEqu {
	
	private String strGrmName = "";
	public String getGrmName(){return strGrmName;}
	
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
	public ArrayList<GrmData> getGrmEquVars(){return varArray;}
	
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
		String sr = HttpRequest.sendPost("http://"+strADDR+"/exdata?SID="+strSID+"&"+"OP=E", "NTRPGC");
		
		String[] strSplit = sr.split("\n");
		
		String isSuccessful = strSplit[0];
		
		if(isSuccessful.equals("ERROR"))
		{
			errGetDataID  = ((strSplit[1]).split("="))[1].trim();
			errGetDataDes = ((strSplit[2]).split("="))[1].trim();			
			
			if(Integer.parseInt(errGetDataID) == 8)//If errorcode is 8, it means the SID is not available. We should log in again.
			{
				System.out.println(strGrmName + "'s current SID is not available, log in again!");
				grmLogon();
			}
			else
			{
				throw new Exception("登陆设备出错:"+strSplit[1]+"--"+strSplit[2]);	
			}
		}
		else
		{
			System.out.println(strGrmName + "'s current SID is available!");
		}
	}
	
	
	private void grmEnumVarInfo() throws Exception
	{
		GetAvailableSID();
		
		String sr = HttpRequest.sendPost("http://"+strADDR+"/exdata?SID="+strSID+"&"+"OP=E", "NTRPGC");
		
		
		System.out.println("=========================Enum all var info==========================");
		System.out.println(strGrmName);
		System.out.println(sr);
		
		String[] strSplit = sr.split("\n");
		
		String isSuccessful = strSplit[0];
		
		if(isSuccessful.equals("ERROR"))
		{
			errGetDataID  = ((strSplit[1]).split("="))[1].trim();
			errGetDataDes = ((strSplit[2]).split("="))[1].trim();
			
			throw new Exception("枚举设备变量 信息出错:"+strSplit[1]+"--"+strSplit[2]);	
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
	
	
	private void grmReadVar(ArrayList<String> varList) throws Exception
	{
		GetAvailableSID();
		
		String strRequest = "";
		strRequest += varList.size() + "\n";
		for(String varName : varList)
		{
			strRequest += varName + "\n";
		}
		
		String sr = HttpRequest.sendPost("http://"+strADDR+"/exdata?SID="+strSID+"&"+"OP=R", strRequest);
		
		//System.out.println(strSID);
		System.out.println("=========================Read var==========================");
		System.out.println(strGrmName);
		System.out.println(sr);
		
		String[] strSplit = sr.split("\n");
		
		String isSuccessful = strSplit[0];
		
		if(isSuccessful.equals("ERROR"))
		{
			errGetDataID  = ((strSplit[1]).split("="))[1].trim();
			errGetDataDes = ((strSplit[2]).split("="))[1].trim();
			
			throw new Exception("读取变量出错:"+strSplit[1]+"--"+strSplit[2]);	
		}
		
		int num = Integer.parseInt(strSplit[1].trim());
		
		for(int i=2;i<strSplit.length;i++)
		{
			varArray.get(i-2).varData = (strSplit[i].trim() == null) ?  "" : strSplit[i].trim();
		}
		
	}
	
	
	private ArrayList<String> grmGetVarNameList() throws Exception
	{
		ArrayList<String> equVarNameList = new ArrayList<String>();
		
		for(GrmData var : varArray)
		{
			equVarNameList.add(var.varName);
		}
		
		return equVarNameList;
	}
	
	public void grmReadAllVar() throws Exception
	{
		grmEnumVarInfo();
		
		ArrayList<String> equVarNameList = grmGetVarNameList();
		
		grmReadVar(equVarNameList);
	}
	
	//pattern:
	//2
	//变量名 1
	//变量值 1
	//变量名 2
	//变量值 2
	//.....
	public void grmWriteVar(String strRequest) throws Exception
	{
		GetAvailableSID();
		
		String sr = HttpRequest.sendPost("http://"+strADDR+"/exdata?SID="+strSID+"&"+"OP=W", strRequest);
		
		//System.out.println(strSID);
		System.out.println("=========================Read var==========================");
		System.out.println(strGrmName);
		System.out.println(sr);
		
		String[] strSplit = sr.split("\n");
		
		String isSuccessful = strSplit[0];
		
		if(isSuccessful.equals("ERROR"))
		{
			errGetDataID  = ((strSplit[1]).split("="))[1].trim();
			errGetDataDes = ((strSplit[2]).split("="))[1].trim();
			
			throw new Exception("写入变量出错:"+strSplit[1]+"--"+strSplit[2]);	
		}
		
		int num = Integer.parseInt(strSplit[1].trim());
		
		//HashMap
		//key: index
		//value: 0 means Success; else means Error ID.
		HashMap<String, String> errorVar = new HashMap<String, String>(); 
		for(int i=2;i<strSplit.length;i++)
		{
			if(strSplit[i].trim().equals("0"))
			{
				continue;
			}
			else
			{
				errorVar.put(Integer.toString(i-2), strSplit[i].trim());
			}
		}
	}

}
