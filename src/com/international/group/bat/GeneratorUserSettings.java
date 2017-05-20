package com.international.group.bat;



import java.util.HashMap;
import java.util.Map;

import stateholders.UserSettingsStateHolder;


public class GeneratorUserSettings 
{
	private Map<String, String> m_generatedMap = new HashMap<String, String>();
	private UserSettingsStateHolder m_stateholder = null;
	
	public Map<String, String> getGeneratedMap()
	{
		if(m_stateholder == null)
			m_stateholder = MainActivity.stateholderUserSettings;
		
		//update map
		m_generatedMap.clear();
		
		//fill map
		m_generatedMap.put(TextSMS.USERSETTINGS_CURRENTPIN, getCurrentPin());
		m_generatedMap.put(TextSMS.USERSETTINGS_NEWPIN, getNewPin());
		m_generatedMap.put(TextSMS.USERSETTINGS_BALANCECHECKINGNUMBER, getBalanceCheckingNumber());
		m_generatedMap.put(TextSMS.USERSETTINGS_ACCBALANCE, getAccBalance());
		m_generatedMap.put(TextSMS.USERSETTINGS_NUMBERCALLATTEMPTS, getNumberCallAttempts());
		m_generatedMap.put(TextSMS.USERSETTINGS_SIREN, getSiren());
		m_generatedMap.put(TextSMS.USERSETTINGS_DISARMSYSTEM, getDisarmingSystem());
		m_generatedMap.put(TextSMS.USERSETTINGS_ALLSYSTSENSORS, getAllSystemSensors());
		m_generatedMap.put(TextSMS.USERSETTINGS_SHOCKSENSSETTINGS, getShockSensSettings());
		m_generatedMap.put(TextSMS.USERSETTINGS_TILTSENSOR, getTiltSensor());
		m_generatedMap.put(TextSMS.USERSETTINGS_PHONESENSITIVITY, getPhoneSensitivity());
		m_generatedMap.put(TextSMS.USERSETTINGS_SPEAKERVOLUME, getSpeakerVolume());
		m_generatedMap.put(TextSMS.USERSETTINGS_SENSITIVITYLEVEL, getSensitivityLevel());
		m_generatedMap.put(TextSMS.USERSETTINGS_LABELENTER, getLabelEnter());
		m_generatedMap.put(TextSMS.USERSETTINGS_LABELEXIT, getLabelExit());
		m_generatedMap.put(TextSMS.USERSETTINGS_LABELCONFIRMFUNCDISARMING, getLabelConfirmFuncDisarming());
		m_generatedMap.put(TextSMS.USERSETTINGS_SMSREPORTINGARMING, getSMSREPORTINGARMING());
		m_generatedMap.put(TextSMS.USERSETTINGS_MONITORINGSETTINGS, getMonitoringSettings());
		m_generatedMap.put(TextSMS.USERSETTINGS_ACCESSPOINTNUMBMOBOPER, getAccessPointNameMobOper());
		m_generatedMap.put(TextSMS.USERSETTINGS_REARM, getRearm());
		//return generated map
		return m_generatedMap;
	}
	
	//0) NEW PIN
	private String getCurrentPin()
	{
		return m_stateholder.getEtCurrentPINValue()+" ";
	}
	
	//1) NEW PIN
	private String getNewPin()
	{
		if(m_stateholder.getEtPINEnabled())
			return "NEWPIN " + m_stateholder.getEtPINValue()+" ";
		else
			return "";
	}
	
	//Balance Checking Number
	private String getBalanceCheckingNumber()
	{
		String code = "BALANS "; 
		if(m_stateholder.getBalanceChekingNumberSpinEnabled())
		{
			switch(m_stateholder.getBalanceChekingNumberSpinValue())
			{
				case 0: //KyivStar
				{
					code+="\"*111#\"";
					break;
				}
				
				case 1: //MTC
				{
					code+="\"*101#\"";
					break;
				}
				
				case 2: //Ukrtelecom
				{
					code+="\"*100#\"";
					break;
				}
				
				case 3: //Another
				{
					code+="\""+m_stateholder.getEtBalanceCheckingAnotherValue()+"\"";
					break;
				}
			}
			code+=" ";
			
			return code;
		}
		else 
			return "";
	}
	
	//Account balance autocheck parameters
	private String getAccBalance()
	{
		String code = "AUTOCHECK "; 
		if(m_stateholder.getCbAccBalanceChecked())
		{
			code+=m_stateholder.getEtNumSumPositionValue()+" ";
			code+=m_stateholder.getEtCriticalBalanceValue()+" ";
			code+=m_stateholder.getEtBalanceCheckingFrequencyValue()+" ";
			return code;
		}
		else 
			return "";
	}
	
	//Number call attempts
	private String getNumberCallAttempts()
	{
		String code = "CALLCNT "; 
		if(m_stateholder.getEtNumberCallAttemptsEnabled())
		{
			code+=m_stateholder.getEtNumberCallAttemptsValue()+" ";
			return code;
		}
		else 
			return "";
	}
	
	//Siren
	private String getSiren()
	{
		String code = "SIREN "; 
		if(m_stateholder.getCbSirenChecked())
		{
			code+=m_stateholder.getSpinSystemSetUnsetValue()+"";
			code+=m_stateholder.getSpinAlarmModeValue()+"";
			code+=m_stateholder.getSpinSignalsNumberWarningZoneValue()+" ";
			return code;
		}
		else 
			return "";
	}
	
	//6) TWO STAGE DISARMING SYSTEM
	private String getDisarmingSystem()
	{
		if(m_stateholder.getSpinDisarmingSystemEnabled())
		{
			String code = "AVFUN ";	
			code += m_stateholder.getSpinDisarmingSystemValue() + " ";	
			return code;
		}
		else
			
			return "";
	}
	
	//7) ALL SYSTEM SENSORS
	private String getAllSystemSensors()
	{
		String code = "SENSOR "; 
		if(m_stateholder.getCbAllSystemSensorsChecked())
		{
			code += m_stateholder.getSpinTiltSensorValue() + " ";
			return code;
		}
		else 
			return "";
	}
	
	//Shock sensor settings
	private String getShockSensSettings()
	{
		String code = "SHOCK "; 
		if(m_stateholder.getCbShockSensSettingsChecked() && m_stateholder.getCbShockSensSettingsEnabled())
		{
			if(m_stateholder.getCbAlarmZoneDisabledChecked())
				code+= "A"+"255"+" ";
			else
				code+= "A"+m_stateholder.getEtAlarmTrasholdValue()+" ";
			
			if(m_stateholder.getCbWarningZoneDisabledChecked())
				code+= "W"+255+" ";
			else
				code+= "W"+m_stateholder.getEtWarningTrasholdValue()+" ";
			return code;
		}
		else 
			return "";
	}
	
	//TILT SENSOR
	private String getTiltSensor()
	{
		String code = "SENSOR 1 "; 
		if(m_stateholder.getCbTiltSensorChecked() && m_stateholder.getCbTiltSensorEnabled())
		{
			if(!m_stateholder.getCbTiltSensorDisabledChecked())
				code += m_stateholder.getEtTiltSensorValue() + " ";
			else
				code += "0" + " ";
			return code;
		}
		else 
			return "";
	}
	
	//9) MICROPHONE
	private String getPhoneSensitivity()
	{
		String code = "MIC "; 
		if(m_stateholder.getCbPhoneSensitivityChecked())
		{
			if(m_stateholder.getCbPhoneSensitivityDisabledChecked())
				code += "0"+" ";
			else
				code += m_stateholder.getEtPhoneSensitivityValue()+" ";
			return code;
		}
		else 
			return "";
	}
	
	//10) SPEAKER
	private String getSpeakerVolume()
	{
		String code = "VOL "; 
		if(m_stateholder.getCbSpeakerVolumeChecked())
		{
			if(m_stateholder.getCbSpeakerVolumeDisabledChecked())
				code += "0"+" ";
			else
				code += m_stateholder.getEtSpeakerVolumeValue()+" ";
			return code;
		}
		else 
			return "";
	}

    //11) LABEL SETTINGS
    private String getSensitivityLevel()
    {
        String code = "TAGLEVEL ";
        if(m_stateholder.getCbLabelSettingsChecked())
        {
            if(m_stateholder.getCbLabelSettingsDisabledChecked())
                code += "0"+" ";
            else
                code += m_stateholder.getEtLabelSettingsValue()+" ";
            return code;
        }
        else
            return "";
    }

	private String getLabelEnter()
	{
		String code = "TAGENTER ";
		if(m_stateholder.getCbLabelSettingsChecked())
		{
			code += m_stateholder.getLabelEnterValue()+" ";
			return code;
		}
		else
			return "";
	}

	private String getLabelExit()
	{
		String code = "TAGEXIT ";
		if(m_stateholder.getCbLabelSettingsChecked())
		{
			code += m_stateholder.getLabelExitValue()+" ";
			return code;
		}
		else
			return "";
	}

	private String getLabelConfirmFuncDisarming()
	{
		String code = "TAGAVFUN ";
		if(m_stateholder.getCbLabelSettingsChecked())
		{
			code += m_stateholder.getLabelConfirmFuncDisarmingValue()+" ";
			return code;
		}
		else
			return "";
	}

	//SMS Reporting Arming
	private String getSMSREPORTINGARMING()
	{
		String code = "REPORT ";
		if(m_stateholder.getCbSMSReportingArmingChecked())
		{
			code += m_stateholder.getSMSReportingArmingValue()+" ";
			return code;
		}
		else
			return "";
	}
	
	//Monitoring
	private String getMonitoringSettings()
	{
		String code = "MONITOR "; 
		if(m_stateholder.getCbMonitoringSettingsChecked())
		{
			code += m_stateholder.getMonitoringSettingsValue()+" ";
			return code;
		}
		else 
			return "";
	}
	
	//12) ACCESS POINT NAME OF MOBILE OPERATOR
	private String getAccessPointNameMobOper()
	{
		String code = "APN "; 
		if(m_stateholder.getAccessPointNameMobOperChecked())
		{
			switch(m_stateholder.getAccessPointNameMobOperSpinValue())
			{
				case 0: //MTC
				{
					code+="\"Internet\"";
					break;
				}
				
				case 1: //KyivStar
				{
					code+="\"www.ab.kyivstar.net\"";
					break;
				}
				
				case 2: //Djuice
				{
					code+="\"www.djuice.com.ua\"";
					break;
				}
				
				case 3: //Another
				{
					code+="\""+m_stateholder.getEtAccessPointNameMobOperValue()+"\"";
					break;
				}
				
			}
		code+=" ";
		return code;
		}
		else 
			return "";
	}

	private String getRearm()
	{
		String code = "REARM ";
		if(m_stateholder.getRearmChecked())
		{
			code += m_stateholder.getSpinRearmValue()+" ";
			return code;
		}
		else
			return "";
	}
}
