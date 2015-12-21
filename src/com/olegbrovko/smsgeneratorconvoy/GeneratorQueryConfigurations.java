package com.olegbrovko.smsgeneratorconvoy;

import java.util.HashMap;
import java.util.Map;

import stateholders.QueryConfigurationStateHolder;

public class GeneratorQueryConfigurations 
{
	private Map<String, String> m_generatedMap = new HashMap<String, String>();
	private QueryConfigurationStateHolder m_stateholder = null;
	
	public Map<String, String> getGeneratedMap()
	{
		if(m_stateholder == null)
			m_stateholder = MainActivity.stateholderQueryConfigurations;
		
		//update map
		m_generatedMap.clear();
		
		//fill map
		m_generatedMap.put(TextSMS.QUERYCONFIGURATIONS_PHONE, getPhone());
		m_generatedMap.put(TextSMS.QUERYCONFIGURATIONS_HARDWARECONFIGSYST, getHardwareConfigSyst());
		m_generatedMap.put(TextSMS.QUERYCONFIGURATIONS_USERSYSTEMSETTINGS, getUserSystemSettings());
		
		//return generated map
		return m_generatedMap;
	}
	
	private String getPhone()
	{
		if(m_stateholder.getQueryConfigurationsEnabled())
		{
			String code = "ECHO ";
			code += "\""+m_stateholder.getQueryConfigurationsValue()+"\"" + " ";
			return code;
		}
		else
			return "";
	}
	
	private String getHardwareConfigSyst()
	{
		if(m_stateholder.getCbHardwareConfigSystChecked())
		{
			String code = "CONFIG? ";
			return code;
		}
		else
			return "";
	}
	
	private String getUserSystemSettings()
	{
		if(m_stateholder.getCbUserSystSettingsChecked())
		{
			String code = "USERSETTINGS? ";
			return code;
		}
		else
			return "";
	}
}
