package com.olegbrovko.smsgeneratorconvoy;

import java.util.HashMap;
import java.util.Map;

import stateholders.InputsStateHolder;

public class GeneratorInputs 
{
	private String m_generatedString = "";
	private Map<String, String> m_generatedMap = new HashMap<String, String>();
	private InputsStateHolder m_stateholder = null;
	
	public String getGeneratedString()
	{	
		if(m_stateholder == null)
			m_stateholder = MainActivity.stateholderInputs;
		
		//update string
		m_generatedString = "";
		
		//Set settings
		m_generatedString += getInput1();
		m_generatedString += getInput2();
		m_generatedString += getInput3();
		m_generatedString += getInput4();
		m_generatedString += getInput5();
		m_generatedString += getInput6();
		m_generatedString += getInput7();
		m_generatedString += getInput8();
		
		//return generated string from user settings
		return m_generatedString;
	}
	
	public Map<String, String> getGeneratedMap()
	{
		if(m_stateholder == null)
			m_stateholder = MainActivity.stateholderInputs;
		
		//update map
		m_generatedMap.clear();
		
		//fill map
		m_generatedMap.put(TextSMS.INPUTS_INPUT1, getInput1());
		m_generatedMap.put(TextSMS.INPUTS_INPUT2, getInput2());
		m_generatedMap.put(TextSMS.INPUTS_INPUT3, getInput3());
		m_generatedMap.put(TextSMS.INPUTS_INPUT4, getInput4());
		m_generatedMap.put(TextSMS.INPUTS_INPUT5, getInput5());
		m_generatedMap.put(TextSMS.INPUTS_INPUT6, getInput6());
		m_generatedMap.put(TextSMS.INPUTS_INPUT7, getInput7());
		m_generatedMap.put(TextSMS.INPUTS_INPUT8, getInput8());
		
		//return generated map
		return m_generatedMap;
	}
	
	//IN1
	private String getInput1()
	{
		String code = "IN1 ";
		if(m_stateholder.getCbInputsIn1Checked())
		{ 
			//Polarity
			if(m_stateholder.getSpinPolarityIN1Value() == 0)
				code += "+" + " ";
			else
				code += "-" + " ";
			//Input is active
			if(m_stateholder.getSpinInputIsActiveIN1Value() == 0)
				code += "1" + " ";
			else
				code += "2" + " ";
			//Input function
			code += m_stateholder.getSpinInputFunctionsIN1Value()+ " ";
			//Min Closing Time
			code += convertValue(m_stateholder.getEtMinClosingTimeIN1Value()) + " ";
			//Min Opening Time
			code += convertValue(m_stateholder.getEtMinOpeningTimeIN1Value()) + " ";
			
			return code;
		}
		else
			return "";
	}
	
	//IN2
	private String getInput2()
	{
		String code = "IN2 ";
		if(m_stateholder.getCbInputsIn2Checked())
		{ 
			//Polarity
			if(m_stateholder.getSpinPolarityIN2Value() == 0)
				code += "+" + " ";
			else
				code += "-" + " ";
			//Input is active
			if(m_stateholder.getSpinInputIsActiveIN2Value() == 0)
				code += "1" + " ";
			else
				code += "2" + " ";
			//Input function
			code += m_stateholder.getSpinInputFunctionsIN2Value()+ " ";
			//Min Closing Time
			code += convertValue(m_stateholder.getEtMinClosingTimeIN2Value()) + " ";
			//Min Opening Time
			code += convertValue(m_stateholder.getEtMinOpeningTimeIN2Value()) + " ";
				
			return code;
		}
		else
			return "";
	}
	
	//IN3
	private String getInput3()
	{
		String code = "IN3 ";
		if(m_stateholder.getCbInputsIn3Checked())
		{ 
			//Polarity
			if(m_stateholder.getSpinPolarityIN3Value() == 0)
				code += "+" + " ";
			else
				code += "-" + " ";
			//Input is active
			if(m_stateholder.getSpinInputIsActiveIN3Value() == 0)
				code += "1" + " ";
			else
				code += "2" + " ";
			//Input function
			code += m_stateholder.getSpinInputFunctionsIN3Value() + " ";
			//Min Closing Time
			code += convertValue(m_stateholder.getEtMinClosingTimeIN3Value()) + " ";
			//Min Opening Time
			code += convertValue(m_stateholder.getEtMinOpeningTimeIN3Value()) + " ";
					
			return code;
		}
		else
			return "";
	}
	
	//IN4
	private String getInput4()
	{
		String code = "IN4 ";
		if(m_stateholder.getCbInputsIn4Checked())
		{ 
			//Polarity
			if(m_stateholder.getSpinPolarityIN4Value() == 0)
				code += "+" + " ";
			else
				code += "-" + " ";
			//Input is active
			if(m_stateholder.getSpinInputIsActiveIN4Value() == 0)
				code += "1" + " ";
			else
				code += "2" + " ";
			//Input function
			code += m_stateholder.getSpinInputFunctionsIN4Value() + " ";
			//Min Closing Time
			code += convertValue(m_stateholder.getEtMinClosingTimeIN4Value()) + " ";
			//Min Opening Time
			code += convertValue(m_stateholder.getEtMinOpeningTimeIN4Value()) + " ";
						
			return code;
		}
		else
			return "";
	}
	
	//IN5
	private String getInput5()
	{
		String code = "IN5 ";
		if(m_stateholder.getCbInputsIn5Checked())
		{ 
			//Polarity
			if(m_stateholder.getSpinPolarityIN5Value() == 0)
				code += "+" + " ";
			else
				code += "-" + " ";
			//Input is active
			if(m_stateholder.getSpinInputIsActiveIN5Value() == 0)
				code += "1" + " ";
			else
				code += "2" + " ";
			//Input function
			code += m_stateholder.getSpinInputFunctionsIN5Value() + " ";
			//Min Closing Time
			code += convertValue(m_stateholder.getEtMinClosingTimeIN5Value()) + " ";
			//Min Opening Time
			code += convertValue(m_stateholder.getEtMinOpeningTimeIN5Value()) + " ";
							
			return code;
		}
		else
			return "";
	}
	
	//IN6
	private String getInput6()
	{
		String code = "IN6 ";
		if(m_stateholder.getCbInputsIn6Checked())
		{ 
			//Polarity
			if(m_stateholder.getSpinPolarityIN6Value() == 0)
				code += "+" + " ";
			else
				code += "-" + " ";
			//Input is active
			if(m_stateholder.getSpinInputIsActiveIN6Value() == 0)
				code += "1" + " ";
			else
				code += "2" + " ";
			//Input function
			code += m_stateholder.getSpinInputFunctionsIN6Value() + " ";
			//Min Closing Time
			code += convertValue(m_stateholder.getEtMinClosingTimeIN6Value()) + " ";
			//Min Opening Time
			code += convertValue(m_stateholder.getEtMinOpeningTimeIN6Value()) + " ";
								
			return code;
		}
		else
			return "";
	}
	
	//IN7
	private String getInput7()
	{
		String code = "IN7 ";
		if(m_stateholder.getCbInputsIn7Checked())
		{ 
			//Polarity
			if(m_stateholder.getSpinPolarityIN7Value() == 0)
				code += "+" + " ";
			else
				code += "-" + " ";
			//Input is active
			if(m_stateholder.getSpinInputIsActiveIN7Value() == 0)
				code += "1" + " ";
			else
				code += "2" + " ";
			//Input function
			code += m_stateholder.getSpinInputFunctionsIN7Value() + " ";
			//Min Closing Time
			code += convertValue(m_stateholder.getEtMinClosingTimeIN7Value()) + " ";
			//Min Opening Time
			code += convertValue(m_stateholder.getEtMinOpeningTimeIN7Value()) + " ";
									
			return code;
		}
		else
			return "";
	}
	
	//IN8
	private String getInput8()
	{
		String code = "IN8 ";
		if(m_stateholder.getCbInputsIn8Checked())
		{ 
			//Polarity
			if(m_stateholder.getSpinPolarityIN8Value() == 0)
				code += "+" + " ";
			else
				code += "-" + " ";
			//Input is active
			if(m_stateholder.getSpinInputIsActiveIN8Value() == 0)
				code += "1" + " ";
			else
				code += "2" + " ";
			//Input function
			if(convertValue(m_stateholder.getEtMinClosingTimeIN8Value()).length() <= 1)
				code += m_stateholder.getSpinInputFunctionsIN8Value() + " ";
			else
				code += m_stateholder.getSpinInputFunctionsIN8Value() + ",";
			//Min Closing Time
			code += convertValue(m_stateholder.getEtMinClosingTimeIN8Value()) + " ";
			//Min Opening Time
			code += convertValue(m_stateholder.getEtMinOpeningTimeIN8Value()) + " ";
										
			return code;
		}
		else
			return "";
	}
	
// =========================================== CONVERT FUNCTION =============================================
	private String convertValue(String valueToConvert)
	{
		Double doubleValue = 10*Double.parseDouble(valueToConvert);
		Integer intValue = doubleValue.intValue();
		return intValue.toString();
	}
}
