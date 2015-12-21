package com.olegbrovko.smsgeneratorconvoy;

import java.util.HashMap;
import java.util.Map;

import stateholders.NotificationStateHolder;

public class GeneratorNotifications 
{
	private String m_generatedString = "";
	private Map<String, String> m_generatedMap = new HashMap<String, String>();
	private NotificationStateHolder m_stateholder = null;
	
	public String getGeneratedString()
	{	
		if(m_stateholder == null)
			m_stateholder = MainActivity.stateholderNotification;
		
		//update string
		m_generatedString = "";
		
		//Set settings
		m_generatedString += getUser1();
		
		//return generated string from user settings
		return m_generatedString;
	}
	
	public Map<String, String> getGeneratedMap()
	{
		if(m_stateholder == null)
			m_stateholder = MainActivity.stateholderNotification;
		
		//update map
		m_generatedMap.clear();
		
		//fill map
		m_generatedMap.put(TextSMS.NOTIFICATIONS_USER1, getUser1());
		m_generatedMap.put(TextSMS.NOTIFICATIONS_USER2, getUser2());
		m_generatedMap.put(TextSMS.NOTIFICATIONS_USER3, getUser3());
		m_generatedMap.put(TextSMS.NOTIFICATIONS_USER4, getUser4());
		m_generatedMap.put(TextSMS.NOTIFICATIONS_USER5, getUser5());
		//return generated map
		return m_generatedMap;
	}
	
	//USER1
	private String getUser1()
	{
		String code = "USER1 ";
		int count = 0;
		final int userId = 0;
		
		if(m_stateholder.getCbAllowNotificationsTELChecked(userId))
		{
			String callString = "C";
			
			if(m_stateholder.getBtnCallBtnPressedTELChecked(userId) && m_stateholder.getBtnCallBtnPressedTELEnabled(userId))
			{
				callString += 0;
			}
			if(m_stateholder.getBtnIngineActiveTELChecked(userId) && m_stateholder.getBtnIngineActiveTELEnabled(userId))
			{
				callString += 1;
			}
			if(m_stateholder.getBtnLoadLimitSwitchTELChecked(userId) && m_stateholder.getBtnLoadLimitSwitchTELEnabled(userId))
			{
				callString += 2;
			}
			if(m_stateholder.getBtnLoadShockSensorTELChecked(userId) && m_stateholder.getBtnLoadShockSensorTELEnabled(userId))
			{
				callString += 3;
			}
			if(m_stateholder.getBtnLoadUniversalLimitSwitchTELChecked(userId) && m_stateholder.getBtnLoadUniversalLimitSwitchTELEnabled(userId))
			{
				callString += 5;
			}
			if(m_stateholder.getBtnLowBatteryTELChecked(userId) && m_stateholder.getBtnLowBatteryTELEnabled(userId))
			{
				callString += 6;
			}
			if(m_stateholder.getBtnGTELMSChecked(userId) && m_stateholder.getBtnGTELMSEnabled(userId))
			{
				callString += 7;
			}  
			if(m_stateholder.getBtnSystemDisarmTELChecked(userId) && m_stateholder.getBtnSystemDisarmTELEnabled(userId))
			{
				callString += 8;
			}
			if(m_stateholder.getBtnSystemArmingTELChecked(userId) && m_stateholder.getBtnSystemArmingTELEnabled(userId))
			{
				callString += 9;
			}
			
			code += callString + " ";
		}
		
		if(m_stateholder.getCbAllowNotificationsSMSChecked(userId))
		{
			String messageString = "M";
			
			if(m_stateholder.getBtnCallBtnPressedSMSChecked(userId) && m_stateholder.getBtnCallBtnPressedSMSEnabled(userId))
			{
				messageString += 0;
			}
			if(m_stateholder.getBtnIngineActiveSMSChecked(userId) && m_stateholder.getBtnIngineActiveSMSEnabled(userId))
			{
				messageString += 1;
			}
			if(m_stateholder.getBtnLoadLimitSwitchSMSChecked(userId) && m_stateholder.getBtnLoadLimitSwitchSMSEnabled(userId))
			{
				messageString += 2;
			}
			if(m_stateholder.getBtnLoadShockSensorSMSChecked(userId) && m_stateholder.getBtnLoadShockSensorSMSEnabled(userId))
			{
				messageString += 3;
			}
			if(m_stateholder.getBtnLoadUniversalLimitSwitchSMSChecked(userId) && m_stateholder.getBtnLoadUniversalLimitSwitchSMSEnabled(userId))
			{
				messageString += 5;
			}
			if(m_stateholder.getBtnLowBatterySMSChecked(userId) && m_stateholder.getBtnLowBatterySMSEnabled(userId))
			{
				messageString += 6;
			}
			if(m_stateholder.getBtnGSMSMSChecked(userId) && m_stateholder.getBtnGSMSMSEnabled(userId))
			{
				messageString += 7;
			}
			if(m_stateholder.getBtnSystemDisarmSMSChecked(userId) && m_stateholder.getBtnSystemDisarmSMSEnabled(userId))
			{
				messageString += 8;
			}
			if(m_stateholder.getBtnSystemArmingSMSChecked(userId) && m_stateholder.getBtnSystemArmingSMSEnabled(userId))
			{
				messageString += 9;
			}
			
			code += messageString + " ";
		}
		
		if(m_stateholder.getCbInputsInChecked(userId))
		{
			++count;
			if(m_stateholder.getEtPhoneNumberValue(userId).length() != 0)
				code += "\"" + m_stateholder.getEtPhoneNumberValue(userId) + "\" ";
			else code += " ";
		}
		
		if(count != 0)
			return code;
		else
			return "";
	}
	
	//USER2
	private String getUser2()
	{
		String code = "USER2 ";
		int count = 0;
		final int userId = 1;
		
		if(m_stateholder.getCbAllowNotificationsTELChecked(userId))
		{
			String callString = "C";
			
			if(m_stateholder.getBtnCallBtnPressedTELChecked(userId) && m_stateholder.getBtnCallBtnPressedTELEnabled(userId))
			{
				callString += 0;
			}
			if(m_stateholder.getBtnIngineActiveTELChecked(userId) && m_stateholder.getBtnIngineActiveTELEnabled(userId))
			{
				callString += 1;
			}
			if(m_stateholder.getBtnLoadLimitSwitchTELChecked(userId) && m_stateholder.getBtnLoadLimitSwitchTELEnabled(userId))
			{
				callString += 2;
			}
			if(m_stateholder.getBtnLoadShockSensorTELChecked(userId) && m_stateholder.getBtnLoadShockSensorTELEnabled(userId))
			{
				callString += 3;
			}
			if(m_stateholder.getBtnLoadUniversalLimitSwitchTELChecked(userId) && m_stateholder.getBtnLoadUniversalLimitSwitchTELEnabled(userId))
			{
				callString += 5;
			}
			if(m_stateholder.getBtnLowBatteryTELChecked(userId) && m_stateholder.getBtnLowBatteryTELEnabled(userId))
			{
				callString += 6;
			}
			if(m_stateholder.getBtnGTELMSChecked(userId) && m_stateholder.getBtnGTELMSEnabled(userId))
			{
				callString += 7;
			}
			if(m_stateholder.getBtnSystemDisarmTELChecked(userId) && m_stateholder.getBtnSystemDisarmTELEnabled(userId))
			{
				callString += 8;
			}
			if(m_stateholder.getBtnSystemArmingTELChecked(userId) && m_stateholder.getBtnSystemArmingTELEnabled(userId))
			{
				callString += 9;
			}
			
			code += callString + " ";
		}
		
		if(m_stateholder.getCbAllowNotificationsSMSChecked(userId))
		{
			String messageString = "M";
			
			if(m_stateholder.getBtnCallBtnPressedSMSChecked(userId) && m_stateholder.getBtnCallBtnPressedSMSEnabled(userId))
			{
				messageString += 0;
			}
			if(m_stateholder.getBtnIngineActiveSMSChecked(userId) && m_stateholder.getBtnIngineActiveSMSEnabled(userId))
			{
				messageString += 1;
			}
			if(m_stateholder.getBtnLoadLimitSwitchSMSChecked(userId) && m_stateholder.getBtnLoadLimitSwitchSMSEnabled(userId))
			{
				messageString += 2;
			}
			if(m_stateholder.getBtnLoadShockSensorSMSChecked(userId) && m_stateholder.getBtnLoadShockSensorSMSEnabled(userId))
			{
				messageString += 3;
			}
			if(m_stateholder.getBtnLoadUniversalLimitSwitchSMSChecked(userId) && m_stateholder.getBtnLoadUniversalLimitSwitchSMSEnabled(userId))
			{
				messageString += 5;
			}
			if(m_stateholder.getBtnLowBatterySMSChecked(userId) && m_stateholder.getBtnLowBatterySMSEnabled(userId))
			{
				messageString += 6;
			}
			if(m_stateholder.getBtnGSMSMSChecked(userId) && m_stateholder.getBtnGSMSMSEnabled(userId))
			{
				messageString += 7;
			}
			if(m_stateholder.getBtnSystemDisarmSMSChecked(userId) && m_stateholder.getBtnSystemDisarmSMSEnabled(userId))
			{
				messageString += 8;
			}
			if(m_stateholder.getBtnSystemArmingSMSChecked(userId) && m_stateholder.getBtnSystemArmingSMSEnabled(userId))
			{
				messageString += 9;
			}
			
			code += messageString + " ";
		}
		
		if(m_stateholder.getCbInputsInChecked(userId))
		{
			++count;
			if(m_stateholder.getEtPhoneNumberValue(userId).length() != 0)
				code += "\"" + m_stateholder.getEtPhoneNumberValue(userId) + "\" ";
			else code += " ";
		}
		
		if(count != 0)
			return code;
		else
			return "";
	}
		
	//USER3
	private String getUser3()
	{
		String code = "USER3 ";
		int count = 0;
		final int userId = 2;
		
		if(m_stateholder.getCbAllowNotificationsTELChecked(userId))
		{
			String callString = "C";
			
			if(m_stateholder.getBtnCallBtnPressedTELChecked(userId) && m_stateholder.getBtnCallBtnPressedTELEnabled(userId))
			{
				callString += 0;
			}
			if(m_stateholder.getBtnIngineActiveTELChecked(userId) && m_stateholder.getBtnIngineActiveTELEnabled(userId))
			{
				callString += 1;
			}
			if(m_stateholder.getBtnLoadLimitSwitchTELChecked(userId) && m_stateholder.getBtnLoadLimitSwitchTELEnabled(userId))
			{
				callString += 2;
			}
			if(m_stateholder.getBtnLoadShockSensorTELChecked(userId) && m_stateholder.getBtnLoadShockSensorTELEnabled(userId))
			{
				callString += 3;
			}
			if(m_stateholder.getBtnLoadUniversalLimitSwitchTELChecked(userId) && m_stateholder.getBtnLoadUniversalLimitSwitchTELEnabled(userId))
			{
				callString += 5;
			}
			if(m_stateholder.getBtnLowBatteryTELChecked(userId) && m_stateholder.getBtnLowBatteryTELEnabled(userId))
			{
				callString += 6;
			}
			if(m_stateholder.getBtnGTELMSChecked(userId) && m_stateholder.getBtnGTELMSEnabled(userId))
			{
				callString += 7;
			}
			if(m_stateholder.getBtnSystemDisarmTELChecked(userId) && m_stateholder.getBtnSystemDisarmTELEnabled(userId))
			{
				callString += 8;
			}
			if(m_stateholder.getBtnSystemArmingTELChecked(userId) && m_stateholder.getBtnSystemArmingTELEnabled(userId))
			{
				callString += 9;
			}
			
			code += callString + " ";
		}
		
		if(m_stateholder.getCbAllowNotificationsSMSChecked(userId))
		{
			String messageString = "M";
			
			if(m_stateholder.getBtnCallBtnPressedSMSChecked(userId) && m_stateholder.getBtnCallBtnPressedSMSEnabled(userId))
			{
				messageString += 0;
			}
			if(m_stateholder.getBtnIngineActiveSMSChecked(userId) && m_stateholder.getBtnIngineActiveSMSEnabled(userId))
			{
				messageString += 1;
			}
			if(m_stateholder.getBtnLoadLimitSwitchSMSChecked(userId) && m_stateholder.getBtnLoadLimitSwitchSMSEnabled(userId))
			{
				messageString += 2;
			}
			if(m_stateholder.getBtnLoadShockSensorSMSChecked(userId) && m_stateholder.getBtnLoadShockSensorSMSEnabled(userId))
			{
				messageString += 3;
			}
			if(m_stateholder.getBtnLoadUniversalLimitSwitchSMSChecked(userId) && m_stateholder.getBtnLoadUniversalLimitSwitchSMSEnabled(userId))
			{
				messageString += 5;
			}
			if(m_stateholder.getBtnLowBatterySMSChecked(userId) && m_stateholder.getBtnLowBatterySMSEnabled(userId))
			{
				messageString += 6;
			}
			if(m_stateholder.getBtnGSMSMSChecked(userId) && m_stateholder.getBtnGSMSMSEnabled(userId))
			{
				messageString += 7;
			}
			if(m_stateholder.getBtnSystemDisarmSMSChecked(userId) && m_stateholder.getBtnSystemDisarmSMSEnabled(userId))
			{
				messageString += 8;
			}
			if(m_stateholder.getBtnSystemArmingSMSChecked(userId) && m_stateholder.getBtnSystemArmingSMSEnabled(userId))
			{
				messageString += 9;
			}
			
			code += messageString + " ";
		}
		
		if(m_stateholder.getCbInputsInChecked(userId))
		{
			++count;
			if(m_stateholder.getEtPhoneNumberValue(userId).length() != 0)
				code += "\"" + m_stateholder.getEtPhoneNumberValue(userId) + "\" ";
			else code += " ";
		}
		
		if(count != 0)
			return code;
		else
			return "";
	}
				
	//USER4
	private String getUser4()
	{
		String code = "USER4 ";
		int count = 0;
		final int userId = 3;
		
		if(m_stateholder.getCbAllowNotificationsTELChecked(userId))
		{
			String callString = "C";
			
			if(m_stateholder.getBtnCallBtnPressedTELChecked(userId) && m_stateholder.getBtnCallBtnPressedTELEnabled(userId))
			{
				callString += 0;
			}
			if(m_stateholder.getBtnIngineActiveTELChecked(userId) && m_stateholder.getBtnIngineActiveTELEnabled(userId))
			{
				callString += 1;
			}
			if(m_stateholder.getBtnLoadLimitSwitchTELChecked(userId) && m_stateholder.getBtnLoadLimitSwitchTELEnabled(userId))
			{
				callString += 2;
			}
			if(m_stateholder.getBtnLoadShockSensorTELChecked(userId) && m_stateholder.getBtnLoadShockSensorTELEnabled(userId))
			{
				callString += 3;
			}
			if(m_stateholder.getBtnLoadUniversalLimitSwitchTELChecked(userId) && m_stateholder.getBtnLoadUniversalLimitSwitchTELEnabled(userId))
			{
				callString += 5;
			}
			if(m_stateholder.getBtnLowBatteryTELChecked(userId) && m_stateholder.getBtnLowBatteryTELEnabled(userId))
			{
				callString += 6;
			}
			if(m_stateholder.getBtnGTELMSChecked(userId) && m_stateholder.getBtnGTELMSEnabled(userId))
			{
				callString += 7;
			}
			if(m_stateholder.getBtnSystemDisarmTELChecked(userId) && m_stateholder.getBtnSystemDisarmTELEnabled(userId))
			{
				callString += 8;
			}
			if(m_stateholder.getBtnSystemArmingTELChecked(userId) && m_stateholder.getBtnSystemArmingTELEnabled(userId))
			{
				callString += 9;
			}
			
			code += callString + " ";
		}
		
		if(m_stateholder.getCbAllowNotificationsSMSChecked(userId))
		{
			String messageString = "M";
			
			if(m_stateholder.getBtnCallBtnPressedSMSChecked(userId) && m_stateholder.getBtnCallBtnPressedSMSEnabled(userId))
			{
				messageString += 0;
			}
			if(m_stateholder.getBtnIngineActiveSMSChecked(userId) && m_stateholder.getBtnIngineActiveSMSEnabled(userId))
			{
				messageString += 1;
			}
			if(m_stateholder.getBtnLoadLimitSwitchSMSChecked(userId) && m_stateholder.getBtnLoadLimitSwitchSMSEnabled(userId))
			{
				messageString += 2;
			}
			if(m_stateholder.getBtnLoadShockSensorSMSChecked(userId) && m_stateholder.getBtnLoadShockSensorSMSEnabled(userId))
			{
				messageString += 3;
			}
			if(m_stateholder.getBtnLoadUniversalLimitSwitchSMSChecked(userId) && m_stateholder.getBtnLoadUniversalLimitSwitchSMSEnabled(userId))
			{
				messageString += 5;
			}
			if(m_stateholder.getBtnLowBatterySMSChecked(userId) && m_stateholder.getBtnLowBatterySMSEnabled(userId))
			{
				messageString += 6;
			}
			if(m_stateholder.getBtnGSMSMSChecked(userId) && m_stateholder.getBtnGSMSMSEnabled(userId))
			{
				messageString += 7;
			}
			if(m_stateholder.getBtnSystemDisarmSMSChecked(userId) && m_stateholder.getBtnSystemDisarmSMSEnabled(userId))
			{
				messageString += 8;
			}
			if(m_stateholder.getBtnSystemArmingSMSChecked(userId) && m_stateholder.getBtnSystemArmingSMSEnabled(userId))
			{
				messageString += 9;
			}
			
			code += messageString + " ";
		}
		
		if(m_stateholder.getCbInputsInChecked(userId))
		{
			++count;
			if(m_stateholder.getEtPhoneNumberValue(userId).length() != 0)
				code += "\"" + m_stateholder.getEtPhoneNumberValue(userId) + "\" ";
			else code += " ";
		}
		
		if(count != 0)
			return code;
		else
			return "";
	}
				
	//USER5
	private String getUser5()
	{
		String code = "USER5 ";
		int count = 0;
		final int userId = 4;
		
		if(m_stateholder.getCbAllowNotificationsTELChecked(userId))
		{
			String callString = "C";
			
			if(m_stateholder.getBtnCallBtnPressedTELChecked(userId) && m_stateholder.getBtnCallBtnPressedTELEnabled(userId))
			{
				callString += 0;
			}
			if(m_stateholder.getBtnIngineActiveTELChecked(userId) && m_stateholder.getBtnIngineActiveTELEnabled(userId))
			{
				callString += 1;
			}
			if(m_stateholder.getBtnLoadLimitSwitchTELChecked(userId) && m_stateholder.getBtnLoadLimitSwitchTELEnabled(userId))
			{
				callString += 2;
			}
			if(m_stateholder.getBtnLoadShockSensorTELChecked(userId) && m_stateholder.getBtnLoadShockSensorTELEnabled(userId))
			{
				callString += 3;
			}
			if(m_stateholder.getBtnLoadUniversalLimitSwitchTELChecked(userId) && m_stateholder.getBtnLoadUniversalLimitSwitchTELEnabled(userId))
			{
				callString += 5;
			}
			if(m_stateholder.getBtnLowBatteryTELChecked(userId) && m_stateholder.getBtnLowBatteryTELEnabled(userId))
			{
				callString += 6;
			}
			if(m_stateholder.getBtnGTELMSChecked(userId) && m_stateholder.getBtnGTELMSEnabled(userId))
			{
				callString += 7;
			}
			if(m_stateholder.getBtnSystemDisarmTELChecked(userId) && m_stateholder.getBtnSystemDisarmTELEnabled(userId))
			{
				callString += 8;
			}
			if(m_stateholder.getBtnSystemArmingTELChecked(userId) && m_stateholder.getBtnSystemArmingTELEnabled(userId))
			{
				callString += 9;
			}
			
			code += callString + " ";
		}
		
		if(m_stateholder.getCbAllowNotificationsSMSChecked(userId))
		{
			String messageString = "M";
			
			if(m_stateholder.getBtnCallBtnPressedSMSChecked(userId) && m_stateholder.getBtnCallBtnPressedSMSEnabled(userId))
			{
				messageString += 0;
			}
			if(m_stateholder.getBtnIngineActiveSMSChecked(userId) && m_stateholder.getBtnIngineActiveSMSEnabled(userId))
			{
				messageString += 1;
			}
			if(m_stateholder.getBtnLoadLimitSwitchSMSChecked(userId) && m_stateholder.getBtnLoadLimitSwitchSMSEnabled(userId))
			{
				messageString += 2;
			}
			if(m_stateholder.getBtnLoadShockSensorSMSChecked(userId) && m_stateholder.getBtnLoadShockSensorSMSEnabled(userId))
			{
				messageString += 3;
			}
			if(m_stateholder.getBtnLoadUniversalLimitSwitchSMSChecked(userId) && m_stateholder.getBtnLoadUniversalLimitSwitchSMSEnabled(userId))
			{
				messageString += 5;
			}
			if(m_stateholder.getBtnLowBatterySMSChecked(userId) && m_stateholder.getBtnLowBatterySMSEnabled(userId))
			{
				messageString += 6;
			}
			if(m_stateholder.getBtnGSMSMSChecked(userId) && m_stateholder.getBtnGSMSMSEnabled(userId))
			{
				messageString += 7;
			}
			if(m_stateholder.getBtnSystemDisarmSMSChecked(userId) && m_stateholder.getBtnSystemDisarmSMSEnabled(userId))
			{
				messageString += 8;
			}
			if(m_stateholder.getBtnSystemArmingSMSChecked(userId) && m_stateholder.getBtnSystemArmingSMSEnabled(userId))
			{
				messageString += 9;
			}
			
			code += messageString + " ";
		}
		
		if(m_stateholder.getCbInputsInChecked(userId))
		{
			++count;
			if(m_stateholder.getEtPhoneNumberValue(userId).length() != 0)
				code += "\"" + m_stateholder.getEtPhoneNumberValue(userId) + "\" ";
			else code += " ";
		}
		
		if(count != 0)
			return code;
		else
			return "";
	}
}
