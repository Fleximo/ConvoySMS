package com.olegbrovko.smsgeneratorconvoy;

import java.util.HashMap;
import java.util.Map;

import stateholders.CANStateHolder;

public class GeneratorCAN 
{
	private String m_generatedString = "";
	private Map<String, String> m_generatedMap = new HashMap<String, String>();
	private CANStateHolder m_stateholder = null;
	
	public String getGeneratedString()
	{	
		if(m_stateholder == null)
			m_stateholder = MainActivity.stateholderCAN;
		
		//update string
		m_generatedString = "";
		
		//Set settings
		m_generatedString += getSystArmDisarm();
		m_generatedString += getIgnition();
		m_generatedString += getDriveControl();
		m_generatedString += getComfortSignal();
		m_generatedString += getManagingStaffSecuritySystem();
		
		//return generated string from user settings
		return m_generatedString;
	}
	
	public Map<String, String> getGeneratedMap()
	{
		if(m_stateholder == null)
			m_stateholder = MainActivity.stateholderCAN;
		
		//update map
		m_generatedMap.clear();
		
		//fill map
		m_generatedMap.put(TextSMS.CAN_SYSARMDISARM, getSystArmDisarm());
		m_generatedMap.put(TextSMS.CAN_IGNITION, getIgnition());
		m_generatedMap.put(TextSMS.CAN_DRIVECONTROL, getDriveControl());
		m_generatedMap.put(TextSMS.CAN_COMFORTSIGNAL, getComfortSignal());
		m_generatedMap.put(TextSMS.CAN_MANAGSTAFFSEQURSYST, getManagingStaffSecuritySystem());
		
		//return generated map
		return m_generatedMap;
	}
	
	private String getSystArmDisarm()
	{
		if(m_stateholder.getSpinSystArmDisarmEnabled())
		{
			String code = "CANARM ";
			
			code += m_stateholder.getSpinSystArmDisarmValue() + " ";
			
			return code;
		}
		else
			return "";
	}
	
	private String getIgnition()
	{
		if(m_stateholder.getSpinIgnitionEnabled())
		{
			String code = "CANIGN ";
			
			code += m_stateholder.getSpinIgnitionValue() + " ";
			
			return code;
		}
		else
			return "";
	}
	
	private String getDriveControl()
	{
		if(m_stateholder.getSpinDriveControlEnabled())
		{
			String code = "CANTRUNK ";
			
			code += m_stateholder.getSpinDriveControlValue() + " ";
			
			return code;
		}
		else
			return "";
	}
	
	private String getComfortSignal()
	{
		if(m_stateholder.getSpinComfortSignalEnabled())
		{
			String code = "CANWIN ";
			
			code += m_stateholder.getSpinComfortSignalValue() + " ";
			
			return code;
		}
		else
			return "";
	}
	
	private String getManagingStaffSecuritySystem()
	{
		if(m_stateholder.getSpinManagingStaffSecuritySystemEnabled())
		{
			String code = "CANCARARM ";
			
			code += m_stateholder.getSpinManagingStaffSecuritySystemValue() + " ";
			
			return code;
		}
		else
			return "";
	}
}
