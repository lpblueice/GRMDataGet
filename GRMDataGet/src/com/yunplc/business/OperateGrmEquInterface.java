package com.yunplc.business;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.HashMap;

import com.yunplc.grm.GrmData;
import com.yunplc.grm.GrmEqu;

public abstract class OperateGrmEquInterface {

	private GrmEqu grmEqu = null;
	
	//变量分组名
	public ArrayList<String> varGroupsName = new ArrayList<String>();
	//变量分组，与分组名的arraylist一一对应。
	//---1---2---3--- //变量组名
	//   .   .   .    //变量
	//   .   .   . 
	//   .   .   .
	public ArrayList<ArrayList<GrmData>> varGroups = new ArrayList<ArrayList<GrmData>>();
	
	public float longtitude = 0f;
	public float lantitude = 0f;
	
	public abstract void Logon(String GrmID, String Passwd);
	
	public abstract void GetAllGrmData();
	
	//hashmap<变量名，变量值>
	public abstract void WriteGrmData(HashMap<String, String> requestData);
	
	//变量分组，通过变量中自带的groupName来区分和分组
	public abstract void VarsDivideToGroups();
	
	public abstract void GetMapLocation();
	
	public abstract void RefershCurrentData();
	
}
