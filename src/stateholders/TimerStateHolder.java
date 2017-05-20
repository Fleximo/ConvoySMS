package stateholders;

public class TimerStateHolder 
{
	//Channel1
	private boolean cb_UTC_UniversalChannel1_enabled = true;
	private boolean cb_UTC_UniversalChannel1_checked = false;
	public void setCbUniversalChannel1Enabled(boolean enabled) { cb_UTC_UniversalChannel1_enabled = enabled; }
	public void setCbUniversalChannel1Checked(boolean checked) { cb_UTC_UniversalChannel1_checked = checked; }
	public boolean getCbUniversalChannel1Enabled() { return cb_UTC_UniversalChannel1_enabled; }
	public boolean getCbUniversalChannel1Checked() { return cb_UTC_UniversalChannel1_checked; }
	
	private boolean cb_UTC_ArmingUC1_enabled = false;
	private boolean cb_UTC_ArmingUC1_chedked = false;
	public void setCbArmingUC1Enabled(boolean enabled) { cb_UTC_ArmingUC1_enabled = enabled; }
	public void setCbArmingUC1Checked(boolean checked) { cb_UTC_ArmingUC1_chedked = checked; }
	public boolean getCbArmingUC1Enabled() { return cb_UTC_ArmingUC1_enabled; }
	public boolean getCbArmingUC1Checked() { return cb_UTC_ArmingUC1_chedked; }
	
	private boolean cb_UTC_SysDisarmUC1_enabled = false;
	private boolean cb_UTC_SysDisarmUC1_checked = false;
	public void setCbSysDisarmUC1Enabled(boolean enabled) { cb_UTC_SysDisarmUC1_enabled = enabled; }
	public void setCbSysDisarmUC1Checked(boolean checked) { cb_UTC_SysDisarmUC1_checked = checked; }
	public boolean getCbSysDisarmUC1Enabled() { return cb_UTC_SysDisarmUC1_enabled; }
	public boolean getCbSysDisarmUC1Checked() { return cb_UTC_SysDisarmUC1_checked; }
	
	private boolean cb_UTC_IgnitionSwitchOnUC1_enabled = false;
	private boolean cb_UTC_IgnitionSwitchOnUC1_checked = false;
	public void setCbIgnitionSwitchOnUC1Enabled(boolean enabled) { cb_UTC_IgnitionSwitchOnUC1_enabled = enabled; }
	public void setCbIgnitionSwitchOnUC1Checked(boolean checked) { cb_UTC_IgnitionSwitchOnUC1_checked = checked; }
	public boolean getCbIgnitionSwitchOnUC1Enabled() { return cb_UTC_IgnitionSwitchOnUC1_enabled; }
	public boolean getCbIgnitionSwitchOnUC1Checked() { return cb_UTC_IgnitionSwitchOnUC1_checked; }
	
	private boolean cb_UTC_IgnitiOffSwitchOffUC1_enabled = false;
	private boolean cb_UTC_IgnitiOffSwitchOffUC1_checked = false;
	public void setCbIgnitiOffSwitchOffUC1Enabled(boolean enabled) { cb_UTC_IgnitiOffSwitchOffUC1_enabled = enabled; }
	public void setCbIgnitiOffSwitchOffUC1Checked(boolean checked) { cb_UTC_IgnitiOffSwitchOffUC1_checked = checked; }
	public boolean getCbIgnitiOffSwitchOffUC1Enabled() { return cb_UTC_IgnitiOffSwitchOffUC1_enabled; }
	public boolean getCbIgnitiOffSwitchOffUC1Checked() { return cb_UTC_IgnitiOffSwitchOffUC1_checked; }
	
	private boolean cb_UTC_CommandFromPhoneUC1_enabled = false;
	private boolean cb_UTC_CommandFromPhoneUC1_checked = true;
	public void setCbCommandFromPhoneUC1Enabled(boolean enabled) { cb_UTC_CommandFromPhoneUC1_enabled = enabled; }
	public void setCbCommandFromPhoneUC1Checked(boolean checked) { cb_UTC_CommandFromPhoneUC1_checked = checked; }
	public boolean getCbCommandFromPhoneUC1Enabled() { return cb_UTC_CommandFromPhoneUC1_enabled; }
	public boolean getCbCommandFromPhoneUC1Checked() { return cb_UTC_CommandFromPhoneUC1_checked; }
	
	private boolean et_UTC_Arming_DelayUC1_enabled = false;
	private String et_UTC_Arming_DelayUC1_value = "0";
	public void setArming_DelayUC1Enabled(boolean enabled) { et_UTC_Arming_DelayUC1_enabled = enabled; }
	public void setArming_DelayUC1Value(String value) { et_UTC_Arming_DelayUC1_value = value; }
	public boolean getArming_DelayUC1Enabled() { return et_UTC_Arming_DelayUC1_enabled; }
	public String getArming_DelayUC1Value() { return et_UTC_Arming_DelayUC1_value; }
	
	private boolean et_UTC_Arming_OperationTimeUC1_enabled = false;
	private String et_UTC_Arming_OperationTimeUC1_value = "1";
	public void setArming_OperationTimeUC1Enabled(boolean enabled) { et_UTC_Arming_OperationTimeUC1_enabled = enabled; }
	public void setArming_OperationTimeUC1Value(String value) { et_UTC_Arming_OperationTimeUC1_value = value; }
	public boolean getArming_OperationTimeUC1Enabled() { return et_UTC_Arming_OperationTimeUC1_enabled; }
	public String getArming_OperationTimeUC1Value() { return et_UTC_Arming_OperationTimeUC1_value; }
	
	private boolean et_UTC_SysDisarm_DelayUC1_enabled = false;
	private String et_UTC_SysDisarm_DelayUC1_value = "0";
	public void setSysDisarm_DelayUC1Enabled(boolean enabled) { et_UTC_SysDisarm_DelayUC1_enabled = enabled; }
	public void setSysDisarm_DelayUC1Value(String value) { et_UTC_SysDisarm_DelayUC1_value = value; }
	public boolean getSysDisarm_DelayUC1Enabled() { return et_UTC_SysDisarm_DelayUC1_enabled; }
	public String getSysDisarm_DelayUC1Value() { return et_UTC_SysDisarm_DelayUC1_value; }
	
	private boolean et_UTC_SysDisarm_OperationTimeUC1_enabled = false;
	private String et_UTC_SysDisarm_OperationTimeUC1_value = "1";
	public void setSysDisarm_OperationTimeUC1Enabled(boolean enabled) { et_UTC_SysDisarm_OperationTimeUC1_enabled = enabled; }
	public void setSysDisarm_OperationTimeUC1Value(String value) { et_UTC_SysDisarm_OperationTimeUC1_value = value; }
	public boolean getSysDisarm_OperationTimeUC1Enabled() { return et_UTC_SysDisarm_OperationTimeUC1_enabled; }
	public String getSysDisarm_OperationTimeUC1Value() { return et_UTC_SysDisarm_OperationTimeUC1_value; }
	
	private boolean et_UTC_IgnitionSwitchOn_DelayUC1_enabled = false;
	private String et_UTC_IgnitionSwitchOn_DelayUC1_checked = "0";
	public void setIgnitionSwitchOn_DelayUC1Enabled(boolean enabled) { et_UTC_IgnitionSwitchOn_DelayUC1_enabled = enabled; }
	public void setIgnitionSwitchOn_DelayUC1Value(String value) { et_UTC_IgnitionSwitchOn_DelayUC1_checked = value; }
	public boolean getIgnitionSwitchOn_DelayUC1Enabled() { return et_UTC_IgnitionSwitchOn_DelayUC1_enabled; }
	public String getIgnitionSwitchOn_DelayUC1Value() { return et_UTC_IgnitionSwitchOn_DelayUC1_checked; }
	
	private boolean et_UTC_IgnitionSwitchOn_OperationTimeUC1_enabled = false;
	private String et_UTC_IgnitionSwitchOn_OperationTimeUC1_checked = "1";
	public void setIgnitionSwitchOn_OperationTimeUC1Enabled(boolean enabled) { et_UTC_IgnitionSwitchOn_OperationTimeUC1_enabled = enabled; }
	public void setIgnitionSwitchOn_OperationTimeUC1Value(String value) { et_UTC_IgnitionSwitchOn_OperationTimeUC1_checked = value; }
	public boolean getIgnitionSwitchOn_OperationTimeUC1Enabled() { return et_UTC_IgnitionSwitchOn_OperationTimeUC1_enabled; }
	public String getIgnitionSwitchOn_OperationTimeUC1Value() { return et_UTC_IgnitionSwitchOn_OperationTimeUC1_checked; }
	
	private boolean et_UTC_IgnitionSwitchOff_DelayUC1_enabled = false;
	private String et_UTC_IgnitionSwitchOff_DelayUC1_checked = "0";
	public void setIgnitionSwitchOff_DelayUC1Enabled(boolean enabled) { et_UTC_IgnitionSwitchOff_DelayUC1_enabled = enabled; }
	public void setIgnitionSwitchOff_DelayUC1Value(String value) { et_UTC_IgnitionSwitchOff_DelayUC1_checked = value; }
	public boolean getIgnitionSwitchOff_DelayUC1Enabled() { return et_UTC_IgnitionSwitchOff_DelayUC1_enabled; }
	public String getIgnitionSwitchOff_DelayUC1Value() { return et_UTC_IgnitionSwitchOff_DelayUC1_checked; }
	
	private boolean et_UTC_IgnitionSwitchOff_OperationTimeUC1_enabled = false;
	private String et_UTC_IgnitionSwitchOff_OperationTimeUC1_checked = "1";
	public void setIgnitionSwitchOff_OperationTimeUC1Enabled(boolean enabled) { et_UTC_IgnitionSwitchOff_OperationTimeUC1_enabled = enabled; }
	public void setIgnitionSwitchOff_OperationTimeUC1Value(String value) { et_UTC_IgnitionSwitchOff_OperationTimeUC1_checked = value; }
	public boolean getIgnitionSwitchOff_OperationTimeUC1Enabled() { return et_UTC_IgnitionSwitchOff_OperationTimeUC1_enabled; }
	public String getIgnitionSwitchOff_OperationTimeUC1Value() { return et_UTC_IgnitionSwitchOff_OperationTimeUC1_checked; }
	
	private boolean et_UTC_CommandFromPhone_DelayUC1_enabled = false;
	private String et_UTC_CommandFromPhone_DelayUC1_value = "0";
	public void setCommandFromPhone_DelayUC1Enabled(boolean enabled) { et_UTC_CommandFromPhone_DelayUC1_enabled = enabled; }
	public void setCommandFromPhone_DelayUC1Value(String value) { et_UTC_CommandFromPhone_DelayUC1_value = value; }
	public boolean getCommandFromPhone_DelayUC1Enabled() { return et_UTC_CommandFromPhone_DelayUC1_enabled; }
	public String getCommandFromPhone_DelayUC1Value() { return et_UTC_CommandFromPhone_DelayUC1_value; }
	
	private boolean et_UTC_CommandFromPhone_OperationTimeUC1_enabled = false;
	private String et_UTC_CommandFromPhone_OperationTimeUC1_value = "1";
	public void setCommandFromPhone_OperationTimeUC1Enabled(boolean enabled) { et_UTC_CommandFromPhone_OperationTimeUC1_enabled = enabled; }
	public void setCommandFromPhone_OperationTimeUC1Value(String value) { et_UTC_CommandFromPhone_OperationTimeUC1_value = value; }
	public boolean getCommandFromPhone_OperationTimeUC1Enabled() { return et_UTC_CommandFromPhone_OperationTimeUC1_enabled; }
	public String getCommandFromPhone_OperationTimeUC1Value() { return et_UTC_CommandFromPhone_OperationTimeUC1_value; }
	
	//Channel2
	private boolean cb_UTC_UniversalChannel2_enabled = true;
	private boolean cb_UTC_UniversalChannel2_checked = false;
	public void setCbUniversalChannel2Enabled(boolean enabled) { cb_UTC_UniversalChannel2_enabled = enabled; }
	public void setCbUniversalChannel2Checked(boolean checked) { cb_UTC_UniversalChannel2_checked = checked; }
	public boolean getCbUniversalChannel2Enabled() { return cb_UTC_UniversalChannel2_enabled; }
	public boolean getCbUniversalChannel2Checked() { return cb_UTC_UniversalChannel2_checked; }
		
	private boolean cb_UTC_ArmingUC2_enabled = false;
	private boolean cb_UTC_ArmingUC2_chedked = false;
	public void setCbArmingUC2Enabled(boolean enabled) { cb_UTC_ArmingUC2_enabled = enabled; }
	public void setCbArmingUC2Checked(boolean checked) { cb_UTC_ArmingUC2_chedked = checked; }
	public boolean getCbArmingUC2Enabled() { return cb_UTC_ArmingUC2_enabled; }
	public boolean getCbArmingUC2Checked() { return cb_UTC_ArmingUC2_chedked; }
		
	private boolean cb_UTC_SysDisarmUC2_enabled = false;
	private boolean cb_UTC_SysDisarmUC2_checked = false;
	public void setCbSysDisarmUC2Enabled(boolean enabled) { cb_UTC_SysDisarmUC2_enabled = enabled; }
	public void setCbSysDisarmUC2Checked(boolean checked) { cb_UTC_SysDisarmUC2_checked = checked; }
	public boolean getCbSysDisarmUC2Enabled() { return cb_UTC_SysDisarmUC2_enabled; }
	public boolean getCbSysDisarmUC2Checked() { return cb_UTC_SysDisarmUC2_checked; }
	
	private boolean cb_UTC_IgnitionSwitchOnUC2_enabled = false;
	private boolean cb_UTC_IgnitionSwitchOnUC2_checked = false;
	public void setCbIgnitionSwitchOnUC2Enabled(boolean enabled) { cb_UTC_IgnitionSwitchOnUC2_enabled = enabled; }
	public void setCbIgnitionSwitchOnUC2Checked(boolean checked) { cb_UTC_IgnitionSwitchOnUC2_checked = checked; }
	public boolean getCbIgnitionSwitchOnUC2Enabled() { return cb_UTC_IgnitionSwitchOnUC2_enabled; }
	public boolean getCbIgnitionSwitchOnUC2Checked() { return cb_UTC_IgnitionSwitchOnUC2_checked; }
		
	private boolean cb_UTC_IgnitiOffSwitchOffUC2_enabled = false;
	private boolean cb_UTC_IgnitiOffSwitchOffUC2_checked = false;
	public void setCbIgnitiOffSwitchOffUC2Enabled(boolean enabled) { cb_UTC_IgnitiOffSwitchOffUC2_enabled = enabled; }
	public void setCbIgnitiOffSwitchOffUC2Checked(boolean checked) { cb_UTC_IgnitiOffSwitchOffUC2_checked = checked; }
	public boolean getCbIgnitiOffSwitchOffUC2Enabled() { return cb_UTC_IgnitiOffSwitchOffUC2_enabled; }
	public boolean getCbIgnitiOffSwitchOffUC2Checked() { return cb_UTC_IgnitiOffSwitchOffUC2_checked; }
		
	private boolean cb_UTC_CommandFromPhoneUC2_enabled = false;
	private boolean cb_UTC_CommandFromPhoneUC2_checked = true;
	public void setCbCommandFromPhoneUC2Enabled(boolean enabled) { cb_UTC_CommandFromPhoneUC2_enabled = enabled; }
	public void setCbCommandFromPhoneUC2Checked(boolean checked) { cb_UTC_CommandFromPhoneUC2_checked = checked; }
	public boolean getCbCommandFromPhoneUC2Enabled() { return cb_UTC_CommandFromPhoneUC2_enabled; }
	public boolean getCbCommandFromPhoneUC2Checked() { return cb_UTC_CommandFromPhoneUC2_checked; }
		
	private boolean et_UTC_Arming_DelayUC2_enabled = false;
	private String et_UTC_Arming_DelayUC2_value = "0";
	public void setArming_DelayUC2Enabled(boolean enabled) { et_UTC_Arming_DelayUC2_enabled = enabled; }
	public void setArming_DelayUC2Value(String value) { et_UTC_Arming_DelayUC2_value = value; }
	public boolean getArming_DelayUC2Enabled() { return et_UTC_Arming_DelayUC2_enabled; }
	public String getArming_DelayUC2Value() { return et_UTC_Arming_DelayUC2_value; }
		
	private boolean et_UTC_Arming_OperationTimeUC2_enabled = false;
	private String et_UTC_Arming_OperationTimeUC2_value = "1";
	public void setArming_OperationTimeUC2Enabled(boolean enabled) { et_UTC_Arming_OperationTimeUC2_enabled = enabled; }
	public void setArming_OperationTimeUC2Value(String value) { et_UTC_Arming_OperationTimeUC2_value = value; }
	public boolean getArming_OperationTimeUC2Enabled() { return et_UTC_Arming_OperationTimeUC2_enabled; }
	public String getArming_OperationTimeUC2Value() { return et_UTC_Arming_OperationTimeUC2_value; }
		
	private boolean et_UTC_SysDisarm_DelayUC2_enabled = false;
	private String et_UTC_SysDisarm_DelayUC2_value = "0";
	public void setSysDisarm_DelayUC2Enabled(boolean enabled) { et_UTC_SysDisarm_DelayUC2_enabled = enabled; }
	public void setSysDisarm_DelayUC2Value(String value) { et_UTC_SysDisarm_DelayUC2_value = value; }
	public boolean getSysDisarm_DelayUC2Enabled() { return et_UTC_SysDisarm_DelayUC2_enabled; }
	public String getSysDisarm_DelayUC2Value() { return et_UTC_SysDisarm_DelayUC2_value; }
		
	private boolean et_UTC_SysDisarm_OperationTimeUC2_enabled = false;
	private String et_UTC_SysDisarm_OperationTimeUC2_value = "1";
	public void setSysDisarm_OperationTimeUC2Enabled(boolean enabled) { et_UTC_SysDisarm_OperationTimeUC2_enabled = enabled; }
	public void setSysDisarm_OperationTimeUC2Value(String value) { et_UTC_SysDisarm_OperationTimeUC2_value = value; }
	public boolean getSysDisarm_OperationTimeUC2Enabled() { return et_UTC_SysDisarm_OperationTimeUC2_enabled; }
	public String getSysDisarm_OperationTimeUC2Value() { return et_UTC_SysDisarm_OperationTimeUC2_value; }
		
	private boolean et_UTC_IgnitionSwitchOn_DelayUC2_enabled = false;
	private String et_UTC_IgnitionSwitchOn_DelayUC2_checked = "0";
	public void setIgnitionSwitchOn_DelayUC2Enabled(boolean enabled) { et_UTC_IgnitionSwitchOn_DelayUC2_enabled = enabled; }
	public void setIgnitionSwitchOn_DelayUC2Value(String value) { et_UTC_IgnitionSwitchOn_DelayUC2_checked = value; }
	public boolean getIgnitionSwitchOn_DelayUC2Enabled() { return et_UTC_IgnitionSwitchOn_DelayUC2_enabled; }
	public String getIgnitionSwitchOn_DelayUC2Value() { return et_UTC_IgnitionSwitchOn_DelayUC2_checked; }
		
	private boolean et_UTC_IgnitionSwitchOn_OperationTimeUC2_enabled = false;
	private String et_UTC_IgnitionSwitchOn_OperationTimeUC2_checked = "1";
	public void setIgnitionSwitchOn_OperationTimeUC2Enabled(boolean enabled) { et_UTC_IgnitionSwitchOn_OperationTimeUC2_enabled = enabled; }
	public void setIgnitionSwitchOn_OperationTimeUC2Value(String value) { et_UTC_IgnitionSwitchOn_OperationTimeUC2_checked = value; }
	public boolean getIgnitionSwitchOn_OperationTimeUC2Enabled() { return et_UTC_IgnitionSwitchOn_OperationTimeUC2_enabled; }
	public String getIgnitionSwitchOn_OperationTimeUC2Value() { return et_UTC_IgnitionSwitchOn_OperationTimeUC2_checked; }
		
	private boolean et_UTC_IgnitionSwitchOff_DelayUC2_enabled = false;
	private String et_UTC_IgnitionSwitchOff_DelayUC2_checked = "0";
	public void setIgnitionSwitchOff_DelayUC2Enabled(boolean enabled) { et_UTC_IgnitionSwitchOff_DelayUC2_enabled = enabled; }
	public void setIgnitionSwitchOff_DelayUC2Value(String value) { et_UTC_IgnitionSwitchOff_DelayUC2_checked = value; }
	public boolean getIgnitionSwitchOff_DelayUC2Enabled() { return et_UTC_IgnitionSwitchOff_DelayUC2_enabled; }
	public String getIgnitionSwitchOff_DelayUC2Value() { return et_UTC_IgnitionSwitchOff_DelayUC2_checked; }
		
	private boolean et_UTC_IgnitionSwitchOff_OperationTimeUC2_enabled = false;
	private String et_UTC_IgnitionSwitchOff_OperationTimeUC2_checked = "1";
	public void setIgnitionSwitchOff_OperationTimeUC2Enabled(boolean enabled) { et_UTC_IgnitionSwitchOff_OperationTimeUC2_enabled = enabled; }
	public void setIgnitionSwitchOff_OperationTimeUC2Value(String value) { et_UTC_IgnitionSwitchOff_OperationTimeUC2_checked = value; }
	public boolean getIgnitionSwitchOff_OperationTimeUC2Enabled() { return et_UTC_IgnitionSwitchOff_OperationTimeUC2_enabled; }
	public String getIgnitionSwitchOff_OperationTimeUC2Value() { return et_UTC_IgnitionSwitchOff_OperationTimeUC2_checked; }
		
	private boolean et_UTC_CommandFromPhone_DelayUC2_enabled = false;
	private String et_UTC_CommandFromPhone_DelayUC2_value = "0";
	public void setCommandFromPhone_DelayUC2Enabled(boolean enabled) { et_UTC_CommandFromPhone_DelayUC2_enabled = enabled; }
	public void setCommandFromPhone_DelayUC2Value(String value) { et_UTC_CommandFromPhone_DelayUC2_value = value; }
	public boolean getCommandFromPhone_DelayUC2Enabled() { return et_UTC_CommandFromPhone_DelayUC2_enabled; }
	public String getCommandFromPhone_DelayUC2Value() { return et_UTC_CommandFromPhone_DelayUC2_value; }
		
	private boolean et_UTC_CommandFromPhone_OperationTimeUC2_enabled = false;
	private String et_UTC_CommandFromPhone_OperationTimeUC2_value = "1";
	public void setCommandFromPhone_OperationTimeUC2Enabled(boolean enabled) { et_UTC_CommandFromPhone_OperationTimeUC2_enabled = enabled; }
	public void setCommandFromPhone_OperationTimeUC2Value(String value) { et_UTC_CommandFromPhone_OperationTimeUC2_value = value; }
	public boolean getCommandFromPhone_OperationTimeUC2Enabled() { return et_UTC_CommandFromPhone_OperationTimeUC2_enabled; }
	public String getCommandFromPhone_OperationTimeUC2Value() { return et_UTC_CommandFromPhone_OperationTimeUC2_value; }
	
	//OutputTimerOnArmDelay
	private boolean cbOutputTimerOnArmDelay_enabled = true;
	private boolean cbOutputTimerOnArmDelay_checked = false;
	public void setCbOutputTimerOnArmDelayEnabled(boolean enabled) { cbOutputTimerOnArmDelay_enabled = enabled; }
	public void setCbOutputTimerOnArmDelayChecked(boolean checked) { cbOutputTimerOnArmDelay_checked = checked; }
	public boolean getCbOutputTimerOnArmDelayEnabled() { return cbOutputTimerOnArmDelay_enabled; }
	public boolean getCbOutputTimerOnArmDelayChecked() { return cbOutputTimerOnArmDelay_checked; }
	
	private boolean et_UTC_OutputTimerOnArmOperationTime_enabled = false;
	private String et_UTC_OutputTimerOnArmOperationTime_value = "30";
	public void setOutputTimerOnArmOperationTimeEnabled(boolean enabled) { et_UTC_OutputTimerOnArmOperationTime_enabled = enabled; }
	public void setOutputTimerOnArmOperationTimeValue(String value) { et_UTC_OutputTimerOnArmOperationTime_value = value; }
	public boolean getOutputTimerOnArmOperationTimeEnabled() { return et_UTC_OutputTimerOnArmOperationTime_enabled; }
	public String getOutputTimerOnArmOperationTimeValue() { return et_UTC_OutputTimerOnArmOperationTime_value; }

	private boolean et_UTC_OutputTimerOnArmDelay_enabled = false;
	private String et_UTC_OutputTimerOnArmDelay_value = "3";
	public void setOutputTimerOnArmDelayEnabled(boolean enabled) { et_UTC_OutputTimerOnArmDelay_enabled = enabled; }
	public void setOutputTimerOnArmDelayValue(String value) { et_UTC_OutputTimerOnArmDelay_value = value; }
	public boolean getOutputTimerOnArmDelayEnabled() { return et_UTC_OutputTimerOnArmDelay_enabled; }
	public String getOutputTimerOnArmDelayValue() { return et_UTC_OutputTimerOnArmDelay_value; }

	//Turbotimer
	private boolean cbTurboTimerSettings_enabled = true;
	private boolean cbTurboTimerSettings_checked = false;
	public void setCbTurboTimerSettingsEnabled(boolean enabled) { cbTurboTimerSettings_enabled = enabled; }
	public void setCbTurboTimerSettingsChecked(boolean checked) { cbTurboTimerSettings_checked = checked; }
	public boolean getCbTurboTimerSettingsEnabled() { return cbTurboTimerSettings_enabled; }
	public boolean getCbTurboTimerSettingsChecked() { return cbTurboTimerSettings_checked; }

	private boolean et_TurbotimerWorkingTime_enabled = false;
	private String et_TurbotimerWorkingTime_value = "0";
	public void setTurbotimerWorkingTimeEnabled(boolean enabled) { et_TurbotimerWorkingTime_enabled = enabled; }
	public void setTurbotimerWorkingTimeValue(String value) { et_TurbotimerWorkingTime_value = value; }
	public boolean getTurbotimerWorkingTimeEnabled() { return et_TurbotimerWorkingTime_enabled; }
	public String getTurbotimerWorkingTimeValue() { return et_TurbotimerWorkingTime_value; }

	private boolean et_IngnTimeLockedEngine_enabled = false;
	private String et_IngnTimeLockedEngine_value = "0";
	public void setIngnTimeLockedEngineEnabled(boolean enabled) { et_IngnTimeLockedEngine_enabled = enabled; }
	public void setIngnTimeLockedEngineValue(String value) { et_IngnTimeLockedEngine_value = value; }
	public boolean getIngnTimeLockedEngineEnabled() { return et_IngnTimeLockedEngine_enabled; }
	public String getIngnTimeLockedEngineValue() { return et_IngnTimeLockedEngine_value; }

	//Starttimer
	private boolean cbStarttimer_enabled = true;
	private boolean cbStarttimer_checked = false;
	public void setCbStarttimerEnabled(boolean enabled) { cbStarttimer_enabled = enabled; }
	public void setCbStarttimerChecked(boolean checked) { cbStarttimer_checked = checked; }
	public boolean getCbStarttimerEnabled() { return cbStarttimer_enabled; }
	public boolean getCbStarttimerChecked() { return cbStarttimer_checked; }

	private boolean et_Starttimer_enabled = false;
	private String et_Starttimer_value = "60";
	public void setStarttimerEnabled(boolean enabled) { et_Starttimer_enabled = enabled; }
	public void setStarttimerValue(String value) { et_Starttimer_value = value; }
	public boolean getStarttimerEnabled() { return et_Starttimer_enabled; }
	public String getStarttimerValue() { return et_Starttimer_value; }

	//Armtimer
	private boolean cbArmtimer_enabled = true;
	private boolean cbArmtimer_checked = false;
	public void setCbArmtimerEnabled(boolean enabled) { cbArmtimer_enabled = enabled; }
	public void setCbArmtimerChecked(boolean checked) { cbArmtimer_checked = checked; }
	public boolean getCbArmtimerEnabled() { return cbArmtimer_enabled; }
	public boolean getCbArmtimerChecked() { return cbArmtimer_checked; }

	private boolean et_Armtimer_enabled = false;
	private String et_Armtimer_value = "15";
	public void setArmtimerEnabled(boolean enabled) { et_Armtimer_enabled = enabled; }
	public void setArmtimerValue(String value) { et_Armtimer_value = value; }
	public boolean getArmtimerEnabled() { return et_Armtimer_enabled; }
	public String getArmtimerValue() { return et_Armtimer_value; }

	//Function 13
	private boolean cb_UTC_CentralLockTimer13_enabled = true;
	private boolean cb_UTC_CentralLockTimer13_checked = false;
	public void setCbCentralLockTimer13Enabled(boolean enabled) { cb_UTC_CentralLockTimer13_enabled = enabled; }
	public void setCbCentralLockTimer13Checked(boolean checked) { cb_UTC_CentralLockTimer13_checked = checked; }
	public boolean getCbCentralLockTimer13Enabled() { return cb_UTC_CentralLockTimer13_enabled; }
	public boolean getCbCentralLockTimer13Checked() { return cb_UTC_CentralLockTimer13_checked; }

	private boolean spin_UTC_LockImpulsCloseCL13_enabled = false;
	private int spin_UTC_LockImpulsCloseCL13_value = 1;
	public void setSpinLockImpulsCloseCL13Enabled(boolean enabled) { spin_UTC_LockImpulsCloseCL13_enabled = enabled; }
	public void setSpinLockImpulsCloseCL13Value(int value) { spin_UTC_LockImpulsCloseCL13_value = value; }
	public boolean getSpinLockImpulsCloseCL13Enabled() { return spin_UTC_LockImpulsCloseCL13_enabled; }
	public int getSpinLockImpulsCloseCL13Value() { return spin_UTC_LockImpulsCloseCL13_value; }

	private boolean et_UTC_FirstImpulsLongtime13_enabled = false;
	private String et_UTC_FirstImpulsLongtime13_value = "1";
	public void setFirstImpulsLongtime13Enabled(boolean enabled) { et_UTC_FirstImpulsLongtime13_enabled = enabled; }
	public void setFirstImpulsLongtime13Value(String value) { et_UTC_FirstImpulsLongtime13_value = value; }
	public boolean getFirstImpulsLongtime13Enabled() { return et_UTC_FirstImpulsLongtime13_enabled; }
	public String getFirstImpulsLongtime13Value() { return et_UTC_FirstImpulsLongtime13_value; }

	private boolean et_UTC_PauseTimeBetwImpulses13_enabled = false;
	private String et_UTC_PauseTimeBetwImpulses13_value = "0";
	public void setPauseTimeBetwImpulses13Enabled(boolean enabled) { et_UTC_PauseTimeBetwImpulses13_enabled = enabled; }
	public void setPauseTimeBetwImpulses13Value(String value) { et_UTC_PauseTimeBetwImpulses13_value = value; }
	public boolean getPauseTimeBetwImpulses13Enabled() { return et_UTC_PauseTimeBetwImpulses13_enabled; }
	public String getPauseTimeBetwImpulses13Value() { return et_UTC_PauseTimeBetwImpulses13_value; }

	private boolean et_UTC_SecondImpulsLongtime13_enabled = false;
	private String et_UTC_SecondImpulsLongtime13_value = "0";
	public void setSecondImpulsLongtime13Enabled(boolean enabled) { et_UTC_SecondImpulsLongtime13_enabled = enabled; }
	public void setSecondImpulsLongtime13Value(String value) { et_UTC_SecondImpulsLongtime13_value = value; }
	public boolean getSecondImpulsLongtime13Enabled() { return et_UTC_SecondImpulsLongtime13_enabled; }
	public String getSecondImpulsLongtime13Value() { return et_UTC_SecondImpulsLongtime13_value; }

	private boolean et_UTC_PauseTimeAfterIngAndImpStart13_enabled = false;
	private String et_UTC_PauseTimeAfterIngAndImpStart13_value = "8";
	public void setPauseTimeAfterIngAndImpStart13Enabled(boolean enabled) { et_UTC_PauseTimeAfterIngAndImpStart13_enabled = enabled; }
	public void setPauseTimeAfterIngAndImpStart13Value(String value) { et_UTC_PauseTimeAfterIngAndImpStart13_value = value; }
	public boolean getPauseTimeAfterIngAndImpStart13Enabled() { return et_UTC_PauseTimeAfterIngAndImpStart13_enabled; }
	public String getPauseTimeAfterIngAndImpStart13Value() { return et_UTC_PauseTimeAfterIngAndImpStart13_value; }

	//Function 14
	private boolean cb_UTC_CentralLockTimer14_enabled = true;
	private boolean cb_UTC_CentralLockTimer14_checked = false;
	public void setCbCentralLockTimer14Enabled(boolean enabled) { cb_UTC_CentralLockTimer14_enabled = enabled; }
	public void setCbCentralLockTimer14Checked(boolean checked) { cb_UTC_CentralLockTimer14_checked = checked; }
	public boolean getCbCentralLockTimer14Enabled() { return cb_UTC_CentralLockTimer14_enabled; }
	public boolean getCbCentralLockTimer14Checked() { return cb_UTC_CentralLockTimer14_checked; }

	private boolean spin_UTC_LockImpulsCloseCL14_enabled = false;
	private int spin_UTC_LockImpulsCloseCL14_value = 1;
	public void setSpinLockImpulsCloseCL14Enabled(boolean enabled) { spin_UTC_LockImpulsCloseCL14_enabled = enabled; }
	public void setSpinLockImpulsCloseCL14Value(int value) { spin_UTC_LockImpulsCloseCL14_value = value; }
	public boolean getSpinLockImpulsCloseCL14Enabled() { return spin_UTC_LockImpulsCloseCL14_enabled; }
	public int getSpinLockImpulsCloseCL14Value() { return spin_UTC_LockImpulsCloseCL14_value; }

	private boolean et_UTC_FirstImpulsLongtime14_enabled = false;
	private String et_UTC_FirstImpulsLongtime14_value = "1";
	public void setFirstImpulsLongtime14Enabled(boolean enabled) { et_UTC_FirstImpulsLongtime14_enabled = enabled; }
	public void setFirstImpulsLongtime14Value(String value) { et_UTC_FirstImpulsLongtime14_value = value; }
	public boolean getFirstImpulsLongtime14Enabled() { return et_UTC_FirstImpulsLongtime14_enabled; }
	public String getFirstImpulsLongtime14Value() { return et_UTC_FirstImpulsLongtime14_value; }

	private boolean et_UTC_PauseTimeBetwImpulses14_enabled = false;
	private String et_UTC_PauseTimeBetwImpulses14_value = "0";
	public void setPauseTimeBetwImpulses14Enabled(boolean enabled) { et_UTC_PauseTimeBetwImpulses14_enabled = enabled; }
	public void setPauseTimeBetwImpulses14Value(String value) { et_UTC_PauseTimeBetwImpulses14_value = value; }
	public boolean getPauseTimeBetwImpulses14Enabled() { return et_UTC_PauseTimeBetwImpulses14_enabled; }
	public String getPauseTimeBetwImpulses14Value() { return et_UTC_PauseTimeBetwImpulses14_value; }

	private boolean et_UTC_SecondImpulsLongtime14_enabled = false;
	private String et_UTC_SecondImpulsLongtime14_value = "0";
	public void setSecondImpulsLongtime14Enabled(boolean enabled) { et_UTC_SecondImpulsLongtime14_enabled = enabled; }
	public void setSecondImpulsLongtime14Value(String value) { et_UTC_SecondImpulsLongtime14_value = value; }
	public boolean getSecondImpulsLongtime14Enabled() { return et_UTC_SecondImpulsLongtime14_enabled; }
	public String getSecondImpulsLongtime14Value() { return et_UTC_SecondImpulsLongtime14_value; }
}
