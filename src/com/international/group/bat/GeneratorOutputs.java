package com.international.group.bat;

import java.util.HashMap;
import java.util.Map;

import stateholders.OutputsStateHolder;

public class GeneratorOutputs 
{
	private Map<String, String> m_generatedMap = new HashMap<String, String>();
	private OutputsStateHolder m_stateholder = null;
	
	public Map<String, String> getGeneratedMap()
	{
		if(m_stateholder == null)
			m_stateholder = MainActivity.stateholderOutputs;
		
		//update map
		m_generatedMap.clear();
		
		//fill map
		m_generatedMap.put(TextSMS.OUTPUTS_OUTPUT1, getOutput1());
		m_generatedMap.put(TextSMS.OUTPUTS_OUTPUT2, getOutput2());
		m_generatedMap.put(TextSMS.OUTPUTS_OUTPUT3, getOutput3());
		m_generatedMap.put(TextSMS.OUTPUTS_OUTPUT4, getOutput4());
		m_generatedMap.put(TextSMS.OUTPUTS_OUTPUT5, getOutput5());
		m_generatedMap.put(TextSMS.OUTPUTS_OUTPUT6, getOutput6());
		
		//return generated map
		return m_generatedMap;
	}
	
	//IN1
	private String getOutput1()
	{
		if(m_stateholder.getCbOutputsOUT1Checked())
		{
			String code = "OUT1 ";
			
			code += (m_stateholder.getSpinOutputFunctionsOUT1Value() + 1) + " ";
			
			return code;
		}
		else
			return "";
	}
	
	//IN2
	private String getOutput2()
	{
		if(m_stateholder.getCbOutputsOUT2Checked())
		{
			String code = "OUT2 ";
				
			code += (m_stateholder.getSpinOutputFunctionsOUT2Value() + 1) + " ";
				
			return code;
		}
		else
		
			return "";
	}
	
	//IN3
	private String getOutput3()
	{
		if(m_stateholder.getCbOutputsOUT3Checked())
		{
			String code = "OUT3 ";
					
			code += (m_stateholder.getSpinOutputFunctionsOUT3Value() + 1) + " ";
					
			return code;
		}
		else
			
			return "";
	}
	
	//IN4
	private String getOutput4()
	{
		if(m_stateholder.getCbOutputsOUT4Checked())
		{
			String code = "OUT4 ";
						
			code += (m_stateholder.getSpinOutputFunctionsOUT4Value() + 1) + " ";
						
			return code;
		}
		else
				
			return "";
	}
	
	//IN5
	private String getOutput5()
	{
		if(m_stateholder.getCbOutputsOUT5Checked())
		{
			String code = "OUT5 ";
							
			code += (m_stateholder.getSpinOutputFunctionsOUT5Value() + 1) + " ";
							
			return code;
		}
		else
					
			return "";
	}
	
	//IN6
	private String getOutput6()
	{
		if(m_stateholder.getCbOutputsOUT6Checked())
		{
			String code = "OUT6 ";
								
			code += (m_stateholder.getSpinOutputFunctionsOUT6Value() + 1) + " ";
								
			return code;
		}
		else
						
			return "";
	}
}
