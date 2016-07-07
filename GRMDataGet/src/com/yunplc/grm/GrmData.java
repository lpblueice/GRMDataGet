package com.yunplc.grm;

public class GrmData {

	public String varName = "";
	public String varType = "";
	public String varRWType = "";//�ɶ�д����
	public String netPermission = ""; //����Ȩ�� 
	public String groupName = "";//��������
	public String webVarDes = "";//Web��������
	
	public String varData = "";
	
	public enum varTypeEnum
	{
		VAR_TYPE_UNKNOW, //δ֪���� 
		VAR_TYPE_B, //������
		VAR_TYPE_I, //������
		VAR_TYPE_F, //������
	}
	
	public enum varRWTypeEnum
	{
		VAR_RW_YPE_UNKNOW,
		VAR_RW_TYPE_R, //ֻ�� 
		VAR_RW_TYPE_RW, //�ɶ�д
	}
	
	public enum netPermissionEnum
	{
		NET_PERM_UNKNOW,
		NET_PERM_LOW, //����Ȩ�޵�
		NET_PERM_MID, //�� 
		NET_PERM_HIGH, //��
	}
	
	//�˱����ɷ��д
	public varRWTypeEnum getVarRW()
	{
		if(varRWType.trim().equals("W"))
		{
			return varRWTypeEnum.VAR_RW_TYPE_RW;
		}
		else if(varRWType.trim().equals("R"))
		{
			return varRWTypeEnum.VAR_RW_TYPE_R;
		}
		else 
		{
			return varRWTypeEnum.VAR_RW_YPE_UNKNOW;
		}
	}
	
	//��������
	public varTypeEnum getVarType()
	{
		if(varType.trim().equals("B"))
		{
			return varTypeEnum.VAR_TYPE_B;
		}
		else if(varType.trim().equals("I"))
		{
			return varTypeEnum.VAR_TYPE_I;
		}
		else if(varType.trim().equals("F"))
		{
			return varTypeEnum.VAR_TYPE_F;
		}
		else 
		{
			return varTypeEnum.VAR_TYPE_UNKNOW;
		}
	}
	
		
}
