package com.olegbrovko.smsgeneratorconvoy;

import java.util.HashMap;
import java.util.Map;

import stateholders.TimerStateHolder;

public class GeneratorTimer 
{
	private Map<String, String> m_generatedMap = new HashMap<String, String>();
	private TimerStateHolder m_stateholder = null;
	
	public Map<String, String> getGeneratedMap()
	{
		if(m_stateholder == null)
			m_stateholder = MainActivity.stateholderTimer;
		
		//update map
		m_generatedMap.clear();
		
		//fill map
		m_generatedMap.put(TextSMS.TIMER_TIMER1, getTimer1());
		m_generatedMap.put(TextSMS.TIMER_TIMER2, getTimer2());
		m_generatedMap.put(TextSMS.TIMER_OUTPUTTIMERONARMMODE, getOutputTimerOnArmDelay());
		m_generatedMap.put(TextSMS.TIMER_FUNCTION13, getFunction13());
		m_generatedMap.put(TextSMS.TIMER_FUNCTION14, getFunction14());
		
		//return generated map
		return m_generatedMap;
	}
	
// Universal channel #1
	private String getTimer1()
	{
		String code = "TIMER1 ";
		int count = 0;
		if(m_stateholder.getCbArmingUC1Checked() && m_stateholder.getCbArmingUC1Enabled())
		{
			++count;
			code += "M1 ";
			code += "P"+m_stateholder.getArming_DelayUC1Value() + " ";
			code += "T"+m_stateholder.getArming_OperationTimeUC1Value() + " ";
		}
		
		if(m_stateholder.getCbSysDisarmUC1Checked() && m_stateholder.getCbSysDisarmUC1Enabled())
		{
			++count;
			code += "M2 ";
			code += "P"+m_stateholder.getSysDisarm_DelayUC1Value() + " ";
			code += "T"+m_stateholder.getSysDisarm_OperationTimeUC1Value() + " ";
		}
		
		if(m_stateholder.getCbIgnitionSwitchOnUC1Checked() && m_stateholder.getCbIgnitionSwitchOnUC1Enabled())
		{
			++count;
			code += "M3 ";
			code += "P"+m_stateholder.getIgnitionSwitchOn_DelayUC1Value() + " ";
			code += "T"+m_stateholder.getIgnitionSwitchOn_OperationTimeUC1Value() + " ";
		}
		
		if(m_stateholder.getCbIgnitiOffSwitchOffUC1Checked() && m_stateholder.getCbIgnitiOffSwitchOffUC1Enabled())
		{
			++count;
			code += "M4 ";
			code += "P"+m_stateholder.getIgnitionSwitchOff_DelayUC1Value() + " ";
			code += "T"+m_stateholder.getIgnitionSwitchOff_OperationTimeUC1Value() + " ";
		}
		
		if(m_stateholder.getCbCommandFromPhoneUC1Checked() && m_stateholder.getCbCommandFromPhoneUC1Enabled())
		{
			++count;
			code += "M5 ";
			code += "P"+m_stateholder.getCommandFromPhone_DelayUC1Value() + " ";
			code += "T"+m_stateholder.getCommandFromPhone_OperationTimeUC1Value() + " ";
		}
		
		if(count != 0)
		{
			return code.substring(0, code.length()-1) + "; ";
		}
		else
			return "";
	}
	
	// Universal channel #2
		private String getTimer2()
		{
			String code = "TIMER2 ";
			int count = 0;
			if(m_stateholder.getCbArmingUC2Checked() && m_stateholder.getCbArmingUC2Enabled())
			{
				++count;
				code += "M1 ";
				code += "P"+m_stateholder.getArming_DelayUC2Value() + " ";
				code += "T"+m_stateholder.getArming_OperationTimeUC2Value() + " ";
			}
			
			if(m_stateholder.getCbSysDisarmUC2Checked() && m_stateholder.getCbSysDisarmUC2Enabled())
			{
				++count;
				code += "M2 ";
				code += "P"+m_stateholder.getSysDisarm_DelayUC2Value() + " ";
				code += "T"+m_stateholder.getSysDisarm_OperationTimeUC2Value() + " ";
			}
			
			if(m_stateholder.getCbIgnitionSwitchOnUC2Checked() && m_stateholder.getCbIgnitionSwitchOnUC2Enabled())
			{
				++count;
				code += "M3 ";
				code += "P"+m_stateholder.getIgnitionSwitchOn_DelayUC2Value() + " ";
				code += "T"+m_stateholder.getIgnitionSwitchOn_OperationTimeUC2Value() + " ";
			}
			
			if(m_stateholder.getCbIgnitiOffSwitchOffUC2Checked() && m_stateholder.getCbIgnitiOffSwitchOffUC2Enabled())
			{
				++count;
				code += "M4 ";
				code += "P"+m_stateholder.getIgnitionSwitchOff_DelayUC2Value() + " ";
				code += "T"+m_stateholder.getIgnitionSwitchOff_OperationTimeUC2Value() + " ";
			}
			
			if(m_stateholder.getCbCommandFromPhoneUC2Checked() && m_stateholder.getCbCommandFromPhoneUC2Enabled())
			{
				++count;
				code += "M5 ";
				code += "P"+m_stateholder.getCommandFromPhone_DelayUC2Value() + " ";
				code += "T"+m_stateholder.getCommandFromPhone_OperationTimeUC2Value() + " ";
			}
			
			if(count != 0)
			{
				return code.substring(0, code.length()-1) + "; ";
			}
			else
				return "";
		}
		
//OutputTimerOnArmDelay
	private String getOutputTimerOnArmDelay()
	{
		if(m_stateholder.getCbOutputTimerOnArmDelayChecked())
		{
			String code = "LOCKTIMER ";
				
			code += (m_stateholder.getOutputTimerOnArmOperationTimeValue()) + " ";
			code += (m_stateholder.getOutputTimerOnArmDelayValue()) + " ";
				
			return code;
		}
		else
		
			return "";
	}

	private String getFunction13()
	{
		if(m_stateholder.getCbCentralLockTimer13Checked())
		{
			String code = "OUTLOCK ";

			code += m_stateholder.getSpinLockImpulsCloseCL13Value() + " ";
			code += m_stateholder.getFirstImpulsLongtime13Value() + " ";
			code += m_stateholder.getPauseTimeBetwImpulses13Value() + " ";
			code += m_stateholder.getSecondImpulsLongtime13Value() + " ";
			code += m_stateholder.getPauseTimeAfterIngAndImpStart13Value() + " ";

			return code;
		}
		else
			return "";
	}

	private String getFunction14()
	{
		if(m_stateholder.getCbCentralLockTimer14Checked())
		{
			String code = "OUTUNLOCK ";

			code += m_stateholder.getSpinLockImpulsCloseCL14Value() + " ";
			code += m_stateholder.getFirstImpulsLongtime14Value() + " ";
			code += m_stateholder.getPauseTimeBetwImpulses14Value() + " ";
			code += m_stateholder.getSecondImpulsLongtime14Value() + " ";

			return code;
		}
		else
			return "";
	}
}

