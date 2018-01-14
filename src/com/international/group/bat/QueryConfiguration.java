package com.international.group.bat;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

import java.util.Locale;

import stateholders.QueryConfigurationStateHolder;

public class QueryConfiguration extends Activity implements OnCheckedChangeListener
{
	public static QueryConfiguration instance = null;
	
	private CheckBox cb_QueryConfigurations_QueryConfigurations = null;
	private CheckBox cb_QueryConfigurations_HardwareConfigSystem = null;
	private CheckBox cb_QueryConfigurations_UserSystemSettings = null;
	private CheckBox cb_QueryConfigurations_MonitoringModeSettings = null;
	private CheckBox cb_QueryConfigurations_SubscriberNotificationSettings = null;
	private EditText et_QueryConfigurations_Phone = null;
	
	//State holder
	public static QueryConfigurationStateHolder m_stateholder = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		instance = this;
		setLanguage();
		setContentView(R.layout.query_configuration);
		
		//Initialization of widgets
		init();
		//Restore state
		restoreState();
		//Set listeners
		setListeners();
		//update UI
		updateUI();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		int id = item.getItemId();
		if (id == R.id.menu_title) 
		{
			Intent intent = new Intent(this, CustomMenuPopup.class);
			startActivityForResult(intent, 0);
			return true;
		}
		if(id == R.id.menu_item_change_language)
		{
			Intent intent = new Intent(this, ChangeLanguagePopup.class);
			intent.putExtra(ChangeLanguagePopup.PARENT_ACTIVITY_NAME, getClass().getName());
			startActivityForResult(intent, 0);
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onPause() 
	{
		//Super onPause
		super.onPause();
		
		//Save Activity State
		saveState();
	}
	
	private void init()
	{
		//1.Query configurations
		cb_QueryConfigurations_QueryConfigurations = (CheckBox) findViewById(R.id.cb_QueryConfigurations_QueryConfigurations);
		et_QueryConfigurations_Phone = (EditText) findViewById(R.id.et_QueryConfigurations_Phone);
		et_QueryConfigurations_Phone.setEnabled(false);
		//2.Hardware configuration system
		cb_QueryConfigurations_HardwareConfigSystem = (CheckBox) findViewById(R.id.cb_QueryConfigurations_HardwareConfigSystem);
		//3.User system settings
		cb_QueryConfigurations_UserSystemSettings = (CheckBox) findViewById(R.id.cb_QueryConfigurations_UserSystemSettings);
		//4.Monitoring mode settings
		cb_QueryConfigurations_MonitoringModeSettings = (CheckBox) findViewById(R.id.cb_QueryConfigurations_MonitoringModeSettings);
		//5.Subscriber notification settings -->
		cb_QueryConfigurations_SubscriberNotificationSettings  = (CheckBox) findViewById(R.id.cb_QueryConfigurations_SubscriberNotificationSettings);
	}
	
	private void setListeners()
	{
		cb_QueryConfigurations_QueryConfigurations.setOnCheckedChangeListener(this);
	}
	
	private void saveState()
	{
		if(m_stateholder == null)
			m_stateholder = MainActivity.stateholderQueryConfigurations;
		
		//1.Query configurations
		m_stateholder.setCbQueryConfigurationsEnabled(cb_QueryConfigurations_QueryConfigurations.isEnabled());
		m_stateholder.setCbQueryConfigurationsChecked(cb_QueryConfigurations_QueryConfigurations.isChecked());
		m_stateholder.setQueryConfigurationsEnabled(et_QueryConfigurations_Phone.isEnabled());
		m_stateholder.setQueryConfigurationsValue(et_QueryConfigurations_Phone.getText().toString());
		
		//2.Hardware configuration system
		m_stateholder.setCbHardwareConfigSystEnabled(cb_QueryConfigurations_HardwareConfigSystem.isEnabled());
		m_stateholder.setCbHardwareConfigSystChecked(cb_QueryConfigurations_HardwareConfigSystem.isChecked());
		
		//3.User system settings
		m_stateholder.setCbUserSystSettingsEnabled(cb_QueryConfigurations_UserSystemSettings.isEnabled());
		m_stateholder.setCbUserSystSettingsChecked(cb_QueryConfigurations_UserSystemSettings.isChecked());

		//4. Monitoring mode settings
		m_stateholder.setCbMonitoringModeSettingsEnabled(cb_QueryConfigurations_MonitoringModeSettings.isEnabled());
		m_stateholder.setCbMonitoringModeSettingsChecked(cb_QueryConfigurations_MonitoringModeSettings.isChecked());

		//5.Subscriber notification settings
		m_stateholder.setCbSubscriberNotificationSettingsEnabled(cb_QueryConfigurations_SubscriberNotificationSettings.isEnabled());
		m_stateholder.setCbSubscriberNotificationSettingsChecked(cb_QueryConfigurations_SubscriberNotificationSettings.isChecked());
	}
	
	private void restoreState()
	{
		if (m_stateholder != null) 
		{
			//1.Query configurations
			cb_QueryConfigurations_QueryConfigurations.setEnabled(m_stateholder.getCbQueryConfigurationsEnabled());
			cb_QueryConfigurations_QueryConfigurations.setChecked(m_stateholder.getCbQueryConfigurationsChecked());
			et_QueryConfigurations_Phone.setEnabled(m_stateholder.getQueryConfigurationsEnabled());
			et_QueryConfigurations_Phone.setText(m_stateholder.getQueryConfigurationsValue());
			
			//2.Hardware configuration system
			cb_QueryConfigurations_HardwareConfigSystem.setEnabled(m_stateholder.getCbHardwareConfigSystEnabled());
			cb_QueryConfigurations_HardwareConfigSystem.setChecked(m_stateholder.getCbHardwareConfigSystChecked());
			
			//3.User system settings
			cb_QueryConfigurations_UserSystemSettings.setEnabled(m_stateholder.getCbUserSystSettingsEnabled());
			cb_QueryConfigurations_UserSystemSettings.setChecked(m_stateholder.getCbUserSystSettingsChecked());

			//4. Monitoring mode settings
			cb_QueryConfigurations_MonitoringModeSettings.setEnabled(m_stateholder.getCbMonitoringModeSettingsEnabled());
			cb_QueryConfigurations_MonitoringModeSettings.setChecked(m_stateholder.getCbMonitoringModeSettingsChecked());

			//5.Subscriber notification settings
			cb_QueryConfigurations_SubscriberNotificationSettings.setEnabled(m_stateholder.getCbSubscriberNotificationSettingsEnabled());
			cb_QueryConfigurations_SubscriberNotificationSettings.setChecked(m_stateholder.getCbSubscriberNotificationSettingsChecked());
		}
	}
	
	private void setLanguage()
	{
		String language = MainActivity.m_sharedPreferences.getString(ChangeLanguagePopup.LANGUAGE, "ru");
		Locale locale = new Locale(language);
		Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
	}
	
	public void updateUI()
	{
		//HEADER
		setTitle(R.string.btn_Main_QueryConfigurations);
		//
		cb_QueryConfigurations_QueryConfigurations.setText(R.string.tv_QueryConfigurations_Phone);
		cb_QueryConfigurations_HardwareConfigSystem.setText(R.string.cb_QueryConfigurations_HardwareConfigSystem);
		cb_QueryConfigurations_UserSystemSettings.setText(R.string.cb_QueryConfigurations_UserSystemSettings);
		cb_QueryConfigurations_MonitoringModeSettings.setText(R.string.cb_QueryConfigurations_MonitoringModeSettigs);
		cb_QueryConfigurations_SubscriberNotificationSettings.setText(R.string.cb_QueryConfigurations_SubscriberNotificationSettings);
	}
	
	@Override
	public void finish() 
	{
	    super.finish();
	}
	
	@Override
	protected void onDestroy() 
	{
		super.onDestroy();
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) 
	{
		switch(buttonView.getId())
		{
			case R.id.cb_QueryConfigurations_QueryConfigurations:
			{
				et_QueryConfigurations_Phone.setEnabled(isChecked);
				break;
			}
		}
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		if (resultCode == RESULT_OK) 
		{
			restoreState();
		}
	}
}
