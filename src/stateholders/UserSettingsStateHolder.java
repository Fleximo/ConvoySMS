package stateholders;

public class UserSettingsStateHolder 
{
	//0) CURRENT PIN
	private boolean cb_CurrentPIN_enabled = true;
	private boolean cb_CurrentPIN_checked = false;
	public void setCurrentPinEnabled(boolean enabled) { cb_CurrentPIN_enabled = enabled; }
	public void setCurrentPinChecked(boolean checked) { cb_CurrentPIN_checked = checked; }
	public boolean getCurrentPinEnabled() { return cb_CurrentPIN_enabled; }
	public boolean getCurrentPinChecked() { return cb_CurrentPIN_checked; }
	
	private boolean et_CurrentPIN_enabled = false;
	private String et_CurrentPIN_value = "0000";
	public void setEtCurrentPINEnabled(boolean enabled) { et_CurrentPIN_enabled = enabled; }
	public void setEtCurrentPINValue(String value) { et_CurrentPIN_value = value; }
	public boolean getEtCurrentPINEnabled() { return et_CurrentPIN_enabled; }
	public String getEtCurrentPINValue() { return et_CurrentPIN_value; }
	
	//1) NEW PIN
	private boolean cb_PIN_enabled = true;
	private boolean cb_PIN_checked = false;
	public void setPinEnabled(boolean enabled) { cb_PIN_enabled = enabled; }
	public void setPinChecked(boolean checked) { cb_PIN_checked = checked; }
	public boolean getPinEnabled() { return cb_PIN_enabled; }
	public boolean getPinChecked() { return cb_PIN_checked; }

	private boolean et_PIN_enabled = false;
	private String et_PIN_value = "0000";
	public void setEtPINEnabled(boolean enabled) { et_PIN_enabled = enabled; }
	public void setEtPINValue(String value) { et_PIN_value = value; }
	public boolean getEtPINEnabled() { return et_PIN_enabled; }
	public String getEtPINValue() { return et_PIN_value; }
	
	//2) BALANCE CHECKING NUMBER
	private boolean cb_BalanceChecking_enabled = true;
	private boolean cb_BalanceChecking_checked = false;
	public void setBalanceChekingNumberEnabled(boolean enabled) { cb_BalanceChecking_enabled = enabled; }
	public void setBalanceChekingNumberChecked(boolean checked) { cb_BalanceChecking_checked = checked; }
	public boolean getBalanceChekingNumberEnabled() { return cb_BalanceChecking_enabled; }
	public boolean getBalanceChekingNumberChecked() { return cb_BalanceChecking_checked; }
	
	private boolean spinBalanceChecking_enabled = false;
	private int spinBalanceChecking_value = 0;
	public void setBalanceChekingNumberSpinEnabled(boolean enabled) { spinBalanceChecking_enabled = enabled; }
	public void setBalanceChekingNumberSpinValue(int value) { spinBalanceChecking_value = value; }
	public boolean getBalanceChekingNumberSpinEnabled() { return spinBalanceChecking_enabled; }
	public int getBalanceChekingNumberSpinValue() { return spinBalanceChecking_value; }
	
	private boolean et_UserSettings_BalanceCheckingAnother_enabled = false;
	private String et_UserSettings_BalanceCheckingAnother_value = "";
	public void setEtBalanceCheckingAnotherEnabled(boolean enabled) { et_UserSettings_BalanceCheckingAnother_enabled = enabled; }
	public void setEtBalanceCheckingAnotherValue(String value) { et_UserSettings_BalanceCheckingAnother_value = value; }
	public boolean getEtBalanceCheckingAnotherEnabled() { return et_UserSettings_BalanceCheckingAnother_enabled; }
	public String getEtBalanceCheckingAnotherValue() { return et_UserSettings_BalanceCheckingAnother_value; }
	
	//3) BALANCE AUTO-CHECKING
	private boolean cbAccBalance_enabled = true;
	private boolean cbAccBalance_checked = false;
	public void setCbAccBalanceEnabled(boolean enabled) { cbAccBalance_enabled = enabled; }
	public void setCbAccBalanceChecked(boolean checked) { cbAccBalance_checked = checked; }
	public boolean getCbAccBalanceEnabled() { return cbAccBalance_enabled; }
	public boolean getCbAccBalanceChecked() { return cbAccBalance_checked; }
		
	private boolean etNumSumPosition_enabled = false;
	private String etNumSumPosition_value = "1";
	public void setEtNumSumPositionEnabled(boolean enabled) { etNumSumPosition_enabled = enabled; }
	public void setEtNumSumPositionValue(String value) { etNumSumPosition_value = value; }
	public boolean getEtNumSumPositionEnabled() { return etNumSumPosition_enabled; }
	public String getEtNumSumPositionValue() { return etNumSumPosition_value; }
		
	private boolean etCriticalBalance_enabled = false;
	private String etCriticalBalance_value = "10";
	public void setEtCriticalBalanceEnabled(boolean enabled) { etCriticalBalance_enabled = enabled; }
	public void setEtCriticalBalanceValue(String value) { etCriticalBalance_value = value; }
	public boolean getEtCriticalBalanceEnabled() { return etCriticalBalance_enabled; }
	public String getEtCriticalBalanceValue() { return etCriticalBalance_value; }
		
	private boolean etBalanceCheckingFrequency_enabled = false;
	private String etBalanceCheckingFrequency_value = "24";
	public void setEtBalanceCheckingFrequencyEnabled(boolean enabled) { etBalanceCheckingFrequency_enabled = enabled; }
	public void setEtBalanceCheckingFrequencyValue(String value) { etBalanceCheckingFrequency_value = value; }
	public boolean getEtBalanceCheckingFrequencyEnabled() { return etBalanceCheckingFrequency_enabled; }
	public String getEtBalanceCheckingFrequencyValue() { return etBalanceCheckingFrequency_value; }
	
	//4) NUMBER OF CALL ATTEMPTS
	private boolean cbNumberCallAttempts_enabled = true;
	private boolean cbNumberCallAttempts_checked = false;
	public void setNuberCallAttemptsEnabled(boolean enabled) { cbNumberCallAttempts_enabled = enabled; }
	public void setNuberCallAttemptsChecked(boolean checked) { cbNumberCallAttempts_checked = checked; }
	public boolean getNuberCallAttemptsEnabled() { return cbNumberCallAttempts_enabled; }
	public boolean getNuberCallAttemptsChecked() { return cbNumberCallAttempts_checked; }
		
	private boolean etNumberCallAttempts_enabled = false;
	private String etNumberCallAttempts_value = "1";
	public void setEtNumberCallAttemptsEnabled(boolean enabled) { etNumberCallAttempts_enabled = enabled; }
	public void setEtNumberCallAttemptsValue(String value) { etNumberCallAttempts_value = value; }
	public boolean getEtNumberCallAttemptsEnabled() { return etNumberCallAttempts_enabled; }
	public String getEtNumberCallAttemptsValue() { return etNumberCallAttempts_value; }

	//REARM
	private boolean cb_UserSettings_Rearm_enabled = true;
	private boolean cb_UserSettings_Rearm_checked = false;
	public void setRearmEnabled(boolean enabled) { cb_UserSettings_Rearm_enabled = enabled; }
	public void setRearmChecked(boolean checked) { cb_UserSettings_Rearm_checked = checked; }
	public boolean getRearmEnabled() { return cb_UserSettings_Rearm_enabled; }
	public boolean getRearmChecked() { return cb_UserSettings_Rearm_checked; }

	private boolean spin_UserSettings_Rearm_enabled = false;
	private int spin_UserSettings_Rearm_value = 0;
	public void setSpinRearmEnabled(boolean enabled) { spin_UserSettings_Rearm_enabled = enabled; }
	public void setSpinRearmValue(int value) { spin_UserSettings_Rearm_value = value; }
	public boolean getSpinRearmEnabled() { return spin_UserSettings_Rearm_enabled; }
	public int getSpinRearmValue() { return spin_UserSettings_Rearm_value; }
	
	//5) SIREN
	private boolean cbSiren_enabled = true;
	private boolean cbSiren_checked = false;
	public void setCbSirenEnabled(boolean enabled) { cbSiren_enabled = enabled; }
	public void setCbSirenChecked(boolean checked) { cbSiren_checked = checked; }
	public boolean getCbSirenEnabled() { return cbSiren_enabled; }
	public boolean getCbSirenChecked() { return cbSiren_checked; }
	
	private boolean spinSystemSetUnset_enabled = false;
	private int spinSystemSetUnset_value = 1;
	public void setSpinSystemSetUnsetEnabled(boolean enabled) { spinSystemSetUnset_enabled = enabled; }
	public void setSpinSystemSetUnsetValue(int value) { spinSystemSetUnset_value = value; }
	public boolean getSpinSystemSetUnsetEnabled() { return spinSystemSetUnset_enabled; }
	public int getSpinSystemSetUnsetValue() { return spinSystemSetUnset_value; }
	
	private boolean spinAlarmMode_enabled = false;
	private int spinAlarmMode_value = 1;
	public void setSpinAlarmModeEnabled(boolean enabled) { spinAlarmMode_enabled = enabled; }
	public void setSpinAlarmModeValue(int value) { spinAlarmMode_value = value; }
	public boolean getSpinAlarmModeEnabled() { return spinAlarmMode_enabled; }
	public int getSpinAlarmModeValue() { return spinAlarmMode_value; }
	
	//7) ALL SYSTEM SENSORS
	private boolean cb_UserSettings_AllSensorsDisabled_enabled = true;
	private boolean cb_UserSettings_AllSensorsDisabled_checked = false;
	public void setCbAllSystemSensorsEnabled(boolean enabled) { cb_UserSettings_AllSensorsDisabled_enabled = enabled; }
	public void setCbAllSystemSensorsChecked(boolean checked) { cb_UserSettings_AllSensorsDisabled_checked = checked; }
	public boolean geCbtAllSystemSensorsEnabled() { return cb_UserSettings_AllSensorsDisabled_enabled; }
	public boolean getCbAllSystemSensorsChecked() { return cb_UserSettings_AllSensorsDisabled_checked; }
	
	private boolean spinTiltSensor_enabled = false;
	private int spinTiltSensor_value = 1;
	public void setSpinTiltSensorEnabled(boolean enabled) { spinTiltSensor_enabled = enabled; }
	public void setSpinTiltSensorValue(int value) { spinTiltSensor_value = value; }
	public boolean getSpinTiltSensorEnabled() { return spinTiltSensor_enabled; }
	public int getSpinTiltSensorValue() { return spinTiltSensor_value; }
	
	//2) ACCESS POINT NAME MOB OPER
	private boolean cb_AccessPointNameMobOper_enabled = true;
	private boolean cb_AccessPointNameMobOper_checked = false;
	public void setAccessPointNameMobOperEnabled(boolean enabled) { cb_AccessPointNameMobOper_enabled = enabled; }
	public void setAccessPointNameMobOperChecked(boolean checked) { cb_AccessPointNameMobOper_checked = checked; }
	public boolean getAccessPointNameMobOperEnabled() { return cb_AccessPointNameMobOper_enabled; }
	public boolean getAccessPointNameMobOperChecked() { return cb_AccessPointNameMobOper_checked; }
		
	private boolean spinAccessPointNameMobOper_enabled = false;
	private int spinAccessPointNameMobOper_value = 0;
	public void setAccessPointNameMobOperSpinEnabled(boolean enabled) { spinAccessPointNameMobOper_enabled = enabled; }
	public void setAccessPointNameMobOperSpinValue(int value) { spinAccessPointNameMobOper_value = value; }
	public boolean getAccessPointNameMobOperSpinEnabled() { return spinAccessPointNameMobOper_enabled; }
	public int getAccessPointNameMobOperSpinValue() { return spinAccessPointNameMobOper_value; }
	
	private boolean et_UserSettings_AccessPointNameMobOper_enabled = false;
	private String et_UserSettings_AccessPointNameMobOper_value = "";
	public void setEtAccessPointNameMobOperEnabled(boolean enabled) { et_UserSettings_AccessPointNameMobOper_enabled = enabled; }
	public void setEtAccessPointNameMobOperValue(String value) { et_UserSettings_AccessPointNameMobOper_value = value; }
	public boolean getEtAccessPointNameMobOperEnabled() { return et_UserSettings_AccessPointNameMobOper_enabled; }
	public String getEtAccessPointNameMobOperValue() { return et_UserSettings_AccessPointNameMobOper_value; }
	
	//4) SENSOR IN SECURITY MODE
	private boolean cbSensInSecurityMode_enabled = false;
	private boolean cbSensInSecurityMode_checked = false;
	public void setCbSensInSecurityModeEnabled(boolean enabled) { cbSensInSecurityMode_enabled = enabled; }
	public void setCbSensInSecurityModeChecked(boolean enabled) { cbSensInSecurityMode_checked = enabled; }
	public boolean getCbSensInSecurityModeEnabled() { return cbSensInSecurityMode_enabled; }
	public boolean getCbSensInSecurityModeChecked() { return cbSensInSecurityMode_checked; }
	
	private boolean spinSensInSecurityMode_enabled = false;
	private int spinSensInSecurityMode_value = 0;
	public void setSpinSensInSecurityModeEnabled(boolean enabled) { spinSensInSecurityMode_enabled = enabled; }
	public void setSpinSensInSecurityModeValue(int value) { spinSensInSecurityMode_value = value; }
	public boolean getSpinSensInSecurityModeEnabled() { return spinSensInSecurityMode_enabled; }
	public int getSpinSensInSecurityModeValue() { return spinSensInSecurityMode_value; }
	
	//5) SHOCK SENSOR SETTINGS
	private boolean cbShockSensSettings_enabled = true;
	private boolean cbShockSensSettings_checked = false;
	public void setCbShockSensSettingsEnabled(boolean enabled) { cbShockSensSettings_enabled = enabled; }
	public void setCbShockSensSettingsChecked(boolean checked) { cbShockSensSettings_checked = checked; }
	public boolean getCbShockSensSettingsEnabled() { return cbShockSensSettings_enabled; }
	public boolean getCbShockSensSettingsChecked() { return cbShockSensSettings_checked; }
	
	private boolean cbWarningZoneDisabled_enabled = false;
	private boolean cbWarningZoneDisabled_checked = false;
	public void setCbWarningZoneDisabledEnabled(boolean enabled) { cbWarningZoneDisabled_enabled = enabled; }
	public void setCbWarningZoneDisabledChecked(boolean checked) { cbWarningZoneDisabled_checked = checked; }
	public boolean getCbWarningZoneDisabledEnabled() { return cbWarningZoneDisabled_enabled; }
	public boolean getCbWarningZoneDisabledChecked() { return cbWarningZoneDisabled_checked; }
	
	private boolean sbWarningTrashold_enabled = false;
	private int sbWarningTrashold_value = 82;
	public void setSbWarningTrasholdEnabled(boolean enabled) { sbWarningTrashold_enabled = enabled; }
	public void setSbWarningTrasholdValue(int value) { sbWarningTrashold_value = value; }
	public boolean getSbWarningTrasholdEnabled() { return sbWarningTrashold_enabled; }
	public int getSbWarningTrasholdValue() { return sbWarningTrashold_value; }
	
	private boolean etWarningTrashold_enabled = false;
	private String etWarningTrashold_value = "18";
	public void setEtWarningTrasholdEnabled(boolean enabled) { etWarningTrashold_enabled = enabled; }
	public void setEtWarningTrasholdValue(String value) { etWarningTrashold_value = value; }
	public boolean getEtWarningTrasholdEnabled() { return etWarningTrashold_enabled; }
	public String getEtWarningTrasholdValue() { return etWarningTrashold_value; }
	
	private boolean cbAlarmZoneDisabled_enabled = false;
	private boolean cbAlarmZoneDisabled_checked = false;
	public void setCbAlarmZoneDisabledEnabled(boolean enabled) { cbAlarmZoneDisabled_enabled = enabled; }
	public void setCbAlarmZoneDisabledChecked(boolean checked) { cbAlarmZoneDisabled_checked = checked; }
	public boolean getCbAlarmZoneDisabledEnabled() { return cbAlarmZoneDisabled_enabled; }
	public boolean getCbAlarmZoneDisabledChecked() { return cbAlarmZoneDisabled_checked; }
	
	private boolean sbAlarmTrashold_enabled = false;
	private int sbAlarmTrashold_value = 27;
	public void setSbAlarmTrasholdEnabled(boolean enabled) { sbAlarmTrashold_enabled = enabled; }
	public void setSbAlarmTrasholdValue(int value) { sbAlarmTrashold_value = value; }
	public boolean getSbAlarmTrasholdEnabled() { return sbAlarmTrashold_enabled; }
	public int getSbAlarmTrasholdValue() { return sbAlarmTrashold_value; }
	
	private boolean etAlarmTrashold_enabled = false;
	private String etAlarmTrashold_value = "32";
	public void setEtAlarmTrasholdEnabled(boolean enabled) { etAlarmTrashold_enabled = enabled; }
	public void setEtAlarmTrasholdValue(String value) { etAlarmTrashold_value = value; }
	public boolean getEtAlarmTrasholdEnabled() { return etAlarmTrashold_enabled; }
	public String getEtAlarmTrasholdValue() { return etAlarmTrashold_value; }
	
	//6) TILT SENSOR
	private boolean cbTiltSensor_enabled = true;
	private boolean cbTiltSensor_checked = false;
	public void setCbTiltSensorEnabled(boolean enabled) { cbTiltSensor_enabled = enabled; }
	public void setCbTiltSensorChecked(boolean checked) { cbTiltSensor_checked = checked; }
	public boolean getCbTiltSensorEnabled() { return cbTiltSensor_enabled; }
	public boolean getCbTiltSensorChecked() { return cbTiltSensor_checked; }
	
	private boolean cb_UserSettings_TiltSensorDisabled_enabled = false;
	private boolean cb_UserSettings_TiltSensorDisabled_checked = true;
	public void setCbTiltSensorDisabledEnabled(boolean enabled) { cb_UserSettings_TiltSensorDisabled_enabled = enabled; }
	public void setCbTiltSensorDisabledChecked(boolean checked) { cb_UserSettings_TiltSensorDisabled_checked = checked; }
	public boolean getCbTiltSensorDisabledEnabled() { return cb_UserSettings_TiltSensorDisabled_enabled; }
	public boolean getCbTiltSensorDisabledChecked() { return cb_UserSettings_TiltSensorDisabled_checked; }
	
	private boolean sbTiltSensor_enabled = false;
	private int sbTiltSensor_value = 0;
	public void setSbTiltSensorEnabled(boolean enabled) { sbTiltSensor_enabled = enabled; }
	public void setSbTiltSensorValue(int value) { sbTiltSensor_value = value; }
	public boolean getSbTiltSensorEnabled() { return sbTiltSensor_enabled; }
	public int getSbTiltSensorValue() { return sbTiltSensor_value; }
	
	private boolean etTiltSensor_enabled = false;
	private String etTiltSensor_value = "1";
	public void setEtTiltSensorEnabled(boolean enabled) { etTiltSensor_enabled = enabled; }
	public void setEtTiltSensorValue(String value) { etTiltSensor_value = value; }
	public boolean getEtTiltSensorEnabled() { return etTiltSensor_enabled; }
	public String getEtTiltSensorValue() { return etTiltSensor_value; }
	
	private boolean cbTiltSensorInArmMode_enabled = false;
	private boolean cbTiltSensorInArmMode_value = false;
	public void setCbTiltSensorInArmModeEnabled(boolean enabled) { cbTiltSensorInArmMode_enabled = enabled; }
	public void setTiltSensorInArmModeChecked(boolean checked) { cbTiltSensorInArmMode_value = checked; }
	public boolean getCbTiltSensorInArmModeEnabled() { return cbTiltSensorInArmMode_enabled; }
	public boolean getTiltSensorInArmModeChecked() { return cbTiltSensorInArmMode_value; }
	
	//7) MONITORING SETTINGS
	private boolean cbMonitoringSettings_enabled = true;
	private boolean cbMonitoringSettings_checked = false;
	public void setCbMonitoringSettingsEnabled(boolean enabled) { cbMonitoringSettings_enabled = enabled; }
	public void setCbMonitoringSettingsChecked(boolean checked) { cbMonitoringSettings_checked = checked; }
	public boolean getCbMonitoringSettingsEnabled() { return cbMonitoringSettings_enabled; }
	public boolean getCbMonitoringSettingsChecked() { return cbMonitoringSettings_checked; }
	
	private boolean spinMonitoringSettings_enabled = false;
	private int spinMonitoringSettings_value = 0;
	public void setMonitoringSettingsEnabled(boolean enabled) { spinMonitoringSettings_enabled = enabled; }
	public void setMonitoringSettingsValue(int value) { spinMonitoringSettings_value = value; }
	public boolean getMonitoringSettingsEnabled() { return spinMonitoringSettings_enabled; }
	public int getMonitoringSettingsValue() { return spinMonitoringSettings_value; }
	
	//8) DISARMING SYSTEM
	private boolean cbDisarmingSystem_enabled = true;
	private boolean cbDisarmingSystem_checked = false;
	public void setCbDisarmingSystemEnabled(boolean enabled) { cbDisarmingSystem_enabled = enabled; }
	public void setCbDisarmingSystemChecked(boolean checked) { cbDisarmingSystem_checked = checked; }
	public boolean getCbDisarmingSystemEnabled() { return cbDisarmingSystem_enabled; }
	public boolean getCbDisarmingSystemChecked() { return cbDisarmingSystem_checked; }
	
	private boolean spinDisarmingSystem_enabled = false;
	private int spinDisarmingSystem_value = 0;
	public void setSpinDisarmingSystemEnabled(boolean enabled) { spinDisarmingSystem_enabled = enabled; }
	public void setSpinDisarmingSystemValue(int value) { spinDisarmingSystem_value = value; }
	public boolean getSpinDisarmingSystemEnabled() { return spinDisarmingSystem_enabled; }
	public int getSpinDisarmingSystemValue() { return spinDisarmingSystem_value; }
	
	//9) MICROPHONE
	private boolean cbPhoneSensitivity_enabled = true;
	private boolean cbPhoneSensitivity_checked = false;
	public void setCbPhoneSensitivityEnabled(boolean enabled) { cbPhoneSensitivity_enabled = enabled; }
	public void setCbPhoneSensitivityChecked(boolean checked) { cbPhoneSensitivity_checked = checked; }
	public boolean getCbPhoneSensitivityEnabled() { return cbPhoneSensitivity_enabled; }
	public boolean getCbPhoneSensitivityChecked() { return cbPhoneSensitivity_checked; }
	
	private boolean cbPhoneSensitivityDisabled_enabled = false;
	private boolean cbPhoneSensitivityDisabled_checked = false;
	public void setCbPhoneSensitivityDisabledEnabled(boolean enabled) { cbPhoneSensitivityDisabled_enabled = enabled; }
	public void setCbPhoneSensitivityDisabledChecked(boolean checked) { cbPhoneSensitivityDisabled_checked = checked; }
	public boolean getCbPhoneSensitivityDisabledEnabled() { return cbPhoneSensitivityDisabled_enabled; }
	public boolean getCbPhoneSensitivityDisabledChecked() { return cbPhoneSensitivityDisabled_checked; }
	
	private boolean sbPhoneSensitivity_enabled = false;
	private int sbPhoneSensitivity_value = 4;
	public void setSbPhoneSensitivityEnabled(boolean enabled) { sbPhoneSensitivity_enabled = enabled; }
	public void setSbPhoneSensitivityValue(int value) { sbPhoneSensitivity_value = value; }
	public boolean getSbPhoneSensitivityEnabled() { return sbPhoneSensitivity_enabled; }
	public int getSbPhoneSensitivityValue() { return sbPhoneSensitivity_value; }
	
	private boolean etPhoneSensitivity_enabled = false;
	private String etPhoneSensitivity_value = "5";
	public void setEtPhoneSensitivityEnabled(boolean enabled) { etPhoneSensitivity_enabled = enabled; }
	public void setEtPhoneSensitivityValue(String value) { etPhoneSensitivity_value = value; }
	public boolean getEtPhoneSensitivityEnabled() { return etPhoneSensitivity_enabled; }
	public String getEtPhoneSensitivityValue() { return etPhoneSensitivity_value; }
	
	//10) SPEAKER
	private boolean cbSpeakerVolume_enabled = true;
	private boolean cbSpeakerVolume_checked = false;
	public void setCbSpeakerVolumeEnabled(boolean enabled) { cbSpeakerVolume_enabled = enabled; }
	public void setCbSpeakerVolumeChecked(boolean checked) { cbSpeakerVolume_checked = checked; }
	public boolean getCbSpeakerVolumeEnabled() { return cbSpeakerVolume_enabled; }
	public boolean getCbSpeakerVolumeChecked() { return cbSpeakerVolume_checked; }
	
	private boolean cbSpeakerVolumeDisabled_enabled = false;
	private boolean cbSpeakerVolumeDisabled_checked = false;
	public void setCbSpeakerVolumeDisabledEnabled(boolean enabled) { cbSpeakerVolumeDisabled_enabled = enabled; }
	public void setCbSpeakerVolumeDisabledChecked(boolean checked) { cbSpeakerVolumeDisabled_checked = checked; }
	public boolean getCbSpeakerVolumeDisabledEnabled() { return cbSpeakerVolumeDisabled_enabled; }
	public boolean getCbSpeakerVolumeDisabledChecked() { return cbSpeakerVolumeDisabled_checked; }
	
	private boolean sbSpeakerVolume_enabled = false;
	private int sbSpeakerVolume_value = 49;
	public void setSbSpeakerVolumeEnabled(boolean enabled) { sbSpeakerVolume_enabled = enabled; }
	public void setSbSpeakerVolumeValue(int value) { sbSpeakerVolume_value = value; }
	public boolean getSbSpeakerVolumeEnabled() { return sbSpeakerVolume_enabled; }
	public int getSbSpeakerVolumeValue() { return sbSpeakerVolume_value; }
	
	private boolean etSpeakerVolume_enabled = false;
	private String etSpeakerVolume_value = "50";
	public void setEtSpeakerVolumeEnabled(boolean enabled) { etSpeakerVolume_enabled = enabled; }
	public void setEtSpeakerVolumeValue(String value) { etSpeakerVolume_value = value; }
	public boolean getEtSpeakerVolumeEnabled() { return etSpeakerVolume_enabled; }
	public String getEtSpeakerVolumeValue() { return etSpeakerVolume_value; }
}
