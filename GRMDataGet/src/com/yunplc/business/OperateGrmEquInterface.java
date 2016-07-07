package com.yunplc.business;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.HashMap;

import com.yunplc.grm.GrmData;
import com.yunplc.grm.GrmEqu;

public abstract class OperateGrmEquInterface {

	private GrmEqu grmEqu = null;
	
	//����������
	public ArrayList<String> varGroupsName = new ArrayList<String>();
	//�������飬���������arraylistһһ��Ӧ��
	//---1---2---3--- //��������
	//   .   .   .    //����
	//   .   .   . 
	//   .   .   .
	public ArrayList<ArrayList<GrmData>> varGroups = new ArrayList<ArrayList<GrmData>>();
	
	public float longtitude = 0f;
	public float lantitude = 0f;
	
	public abstract void Logon(String GrmID, String Passwd);
	
	public abstract void GetAllGrmData();
	
	//hashmap<������������ֵ>
	public abstract void WriteGrmData(HashMap<String, String> requestData);
	
	//�������飬ͨ���������Դ���groupName�����ֺͷ���
	public abstract void VarsDivideToGroups();
	
	public abstract void GetMapLocation();
	
	public abstract void RefershCurrentData();
	
}
