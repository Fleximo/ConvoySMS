package stateholders;

public class QueryConfigurationStateHolder 
{
	//1.Query configurations
	private boolean cb_QueryConfigurations_QueryConfigurations_enabled = true;
	private boolean cb_QueryConfigurations_QueryConfigurations_checked = false;
	public void setCbQueryConfigurationsEnabled(boolean enabled) { cb_QueryConfigurations_QueryConfigurations_enabled = enabled; }
	public void setCbQueryConfigurationsChecked(boolean checked) { cb_QueryConfigurations_QueryConfigurations_checked = checked; }
	public boolean getCbQueryConfigurationsEnabled() { return cb_QueryConfigurations_QueryConfigurations_enabled; }
	public boolean getCbQueryConfigurationsChecked() { return cb_QueryConfigurations_QueryConfigurations_checked; }
	
	private boolean et_QueryConfigurations_PhoneNumber_enabled = false;
	private String et_QueryConfigurations_PhoneNumber_value = "";
	public void setQueryConfigurationsEnabled(boolean enabled) { et_QueryConfigurations_PhoneNumber_enabled = enabled; }
	public void setQueryConfigurationsValue(String value) { et_QueryConfigurations_PhoneNumber_value = value; }
	public boolean getQueryConfigurationsEnabled() { return et_QueryConfigurations_PhoneNumber_enabled; }
	public String getQueryConfigurationsValue() { return et_QueryConfigurations_PhoneNumber_value; }
	
	//2.Hardware configuration system
	private boolean cb_QueryConfigurations_HardwareConfigSyst_enabled = true;
	private boolean cb_QueryConfigurations_HardwareConfigSyst_checked = false;
	public void setCbHardwareConfigSystEnabled(boolean enabled) { cb_QueryConfigurations_HardwareConfigSyst_enabled = enabled; }
	public void setCbHardwareConfigSystChecked(boolean checked) { cb_QueryConfigurations_HardwareConfigSyst_checked = checked; }
	public boolean getCbHardwareConfigSystEnabled() { return cb_QueryConfigurations_HardwareConfigSyst_enabled; }
	public boolean getCbHardwareConfigSystChecked() { return cb_QueryConfigurations_HardwareConfigSyst_checked; }
	
	//3.User system settings
	private boolean cb_QueryConfigurations_UserSystSettings_enabled = true;
	private boolean cb_QueryConfigurations_UserSystSettings_checked = false;
	public void setCbUserSystSettingsEnabled(boolean enabled) { cb_QueryConfigurations_UserSystSettings_enabled = enabled; }
	public void setCbUserSystSettingsChecked(boolean checked) { cb_QueryConfigurations_UserSystSettings_checked = checked; }
	public boolean getCbUserSystSettingsEnabled() { return cb_QueryConfigurations_UserSystSettings_enabled; }
	public boolean getCbUserSystSettingsChecked() { return cb_QueryConfigurations_UserSystSettings_checked; }

	//4. Monitoring mode settings
	private boolean cb_QueryConfigurations_MonitoringModeSettings_enabled = true;
	private boolean cb_QueryConfigurations_MonitoringModeSettings_checked = false;
	public void setCbMonitoringModeSettingsEnabled(boolean enabled) { cb_QueryConfigurations_MonitoringModeSettings_enabled = enabled; }
	public void setCbMonitoringModeSettingsChecked(boolean checked) { cb_QueryConfigurations_MonitoringModeSettings_checked = checked; }
	public boolean getCbMonitoringModeSettingsEnabled() { return cb_QueryConfigurations_MonitoringModeSettings_enabled; }
	public boolean getCbMonitoringModeSettingsChecked() { return cb_QueryConfigurations_MonitoringModeSettings_checked; }

	//5. Subscriber notification settings
	private boolean cb_QueryConfigurations_SubscriberNotificationSettings_enabled = true;
	private boolean cb_QueryConfigurations_SubscriberNotificationSettings_checked = false;
	public void setCbSubscriberNotificationSettingsEnabled(boolean enabled) { cb_QueryConfigurations_SubscriberNotificationSettings_enabled = enabled; }
	public void setCbSubscriberNotificationSettingsChecked(boolean checked) { cb_QueryConfigurations_SubscriberNotificationSettings_checked = checked; }
	public boolean getCbSubscriberNotificationSettingsEnabled() { return cb_QueryConfigurations_SubscriberNotificationSettings_enabled; }
	public boolean getCbSubscriberNotificationSettingsChecked() { return cb_QueryConfigurations_SubscriberNotificationSettings_checked; }
}
