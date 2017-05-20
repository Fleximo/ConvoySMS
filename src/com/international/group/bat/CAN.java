package com.international.group.bat;

import java.util.Locale;

import stateholders.CANStateHolder;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class CAN extends Activity implements OnCheckedChangeListener
{
	public static CAN instance = null;
	
	CheckBox cb_CAN_SystArmDisarm = null;
	Spinner spin_CAN_SystArmDisarm = null;
	
	CheckBox cb_CAN_Ignition = null;
	Spinner spin_CAN_Ignition = null;
	
	CheckBox cb_CAN_DriveControl = null;
	Spinner spin_CAN_DriveControl = null;
	
	CheckBox cb_CAN_ComfortSignal = null;
	Spinner spin_CAN_ComfortSignal = null;
	
	CheckBox cb_CAN_ManagingStaffSecuritySystem = null;
	Spinner spin_CAN_ManagingStaffSecuritySystem = null;
	
	//State holder
	static CANStateHolder m_stateholder = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		instance = this;
		setLanguage();
		setContentView(R.layout.can);
		
		initMembers();
		
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
	
	private void initMembers()
	{
		cb_CAN_SystArmDisarm = (CheckBox) findViewById(R.id.cb_CAN_SystArmDisarm);
		spin_CAN_SystArmDisarm = (Spinner) findViewById(R.id.spin_CAN_SystArmDisarm);
		ArrayAdapter<String> inputSystArmDisarm = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_can_sys_arm_disarm));
		spin_CAN_SystArmDisarm.setAdapter(inputSystArmDisarm);
		
		cb_CAN_Ignition = (CheckBox) findViewById(R.id.cb_CAN_Ignition);
		spin_CAN_Ignition = (Spinner) findViewById(R.id.spin_CAN_Ignition);
		ArrayAdapter<String> inputIgnition = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_can_ignition));
		spin_CAN_Ignition.setAdapter(inputIgnition);
		
		cb_CAN_DriveControl = (CheckBox) findViewById(R.id.cb_CAN_DriveControl);
		spin_CAN_DriveControl = (Spinner) findViewById(R.id.spin_CAN_DriveControl);
		ArrayAdapter<String> inputDriveControl = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_can_drive_control));
		spin_CAN_DriveControl.setAdapter(inputDriveControl);
		
		cb_CAN_ComfortSignal = (CheckBox) findViewById(R.id.cb_CAN_ComfortSignal);
		spin_CAN_ComfortSignal = (Spinner) findViewById(R.id.spin_CAN_ComfortSignal);
		ArrayAdapter<String> inputComfortSignal = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_can_comfort_signal));
		spin_CAN_ComfortSignal.setAdapter(inputComfortSignal);
		
		cb_CAN_ManagingStaffSecuritySystem = (CheckBox) findViewById(R.id.cb_CAN_ManagingStaffSecuritySystem);
		spin_CAN_ManagingStaffSecuritySystem = (Spinner) findViewById(R.id.spin_CAN_ManagingStaffSecuritySystem);
		ArrayAdapter<String> inputManagingStaffSecuritySystem = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_can_managing_staff_system));
		spin_CAN_ManagingStaffSecuritySystem.setAdapter(inputManagingStaffSecuritySystem);
		
		//---------------------------------------- SET STATES ----------------------------------------
		//1) System arm/disarm
		spin_CAN_SystArmDisarm.setEnabled(false);
		spin_CAN_SystArmDisarm.setSelection(1);
		//2) Ignition
		spin_CAN_Ignition.setEnabled(false);
		spin_CAN_Ignition.setSelection(1);
		//3) Drive control
		spin_CAN_DriveControl.setEnabled(false);
		spin_CAN_DriveControl.setSelection(1);
		//4) Comfort signal
		spin_CAN_ComfortSignal.setEnabled(false);
		spin_CAN_ComfortSignal.setSelection(1);
		//5) Managing staff security system
		spin_CAN_ManagingStaffSecuritySystem.setEnabled(false);
		spin_CAN_ManagingStaffSecuritySystem.setSelection(1);
	}
	
	private void setListeners()
	{
		cb_CAN_SystArmDisarm.setOnCheckedChangeListener(this);
		cb_CAN_Ignition.setOnCheckedChangeListener(this);
		cb_CAN_DriveControl.setOnCheckedChangeListener(this);
		cb_CAN_ComfortSignal.setOnCheckedChangeListener(this);
		cb_CAN_ManagingStaffSecuritySystem.setOnCheckedChangeListener(this);
	}
	
	@Override
	protected void onPause() 
	{
		//Super onPause
		super.onPause();
		
		//Save Activity State
		saveState();
	}
	
	private void saveState()
	{
		if(m_stateholder == null)
			m_stateholder = MainActivity.stateholderCAN;
		
		//1)
		m_stateholder.setCbSystArmDisarmEnabled(cb_CAN_SystArmDisarm.isEnabled());
		m_stateholder.setCbSystArmDisarmChecked(cb_CAN_SystArmDisarm.isChecked());
		m_stateholder.setSpinSystArmDisarmEnabled(spin_CAN_SystArmDisarm.isEnabled());
		m_stateholder.setSpinSystArmDisarmValue(spin_CAN_SystArmDisarm.getSelectedItemPosition());
		
		//2)
		m_stateholder.setCbIgnitionEnabled(cb_CAN_Ignition.isEnabled());
		m_stateholder.setCbIgnitionChecked(cb_CAN_Ignition.isChecked());
		m_stateholder.setSpinIgnitionEnabled(spin_CAN_Ignition.isEnabled());
		m_stateholder.setSpinIgnitionValue(spin_CAN_Ignition.getSelectedItemPosition());
		
		//3)
		m_stateholder.setCbDriveControlEnabled(cb_CAN_DriveControl.isEnabled());
		m_stateholder.setCbDriveControlChecked(cb_CAN_DriveControl.isChecked());
		m_stateholder.setSpinDriveControlEnabled(spin_CAN_DriveControl.isEnabled());
		m_stateholder.setSpinDriveControlValue(spin_CAN_DriveControl.getSelectedItemPosition());
		
		//4)
		m_stateholder.setCbComfortSignalEnabled(cb_CAN_ComfortSignal.isEnabled());
		m_stateholder.setCbComfortSignalChecked(cb_CAN_ComfortSignal.isChecked());
		m_stateholder.setSpinComfortSignalEnabled(spin_CAN_ComfortSignal.isEnabled());
		m_stateholder.setSpinComfortSignalValue(spin_CAN_ComfortSignal.getSelectedItemPosition());
		
		//5)
		m_stateholder.setCbManagingStaffSecuritySystemEnabled(cb_CAN_ManagingStaffSecuritySystem.isEnabled());
		m_stateholder.setCbManagingStaffSecuritySystemChecked(cb_CAN_ManagingStaffSecuritySystem.isChecked());
		m_stateholder.setSpinManagingStaffSecuritySystemEnabled(spin_CAN_ManagingStaffSecuritySystem.isEnabled());
		m_stateholder.setSpinManagingStaffSecuritySystemValue(spin_CAN_ManagingStaffSecuritySystem.getSelectedItemPosition());
	}
	
	private void restoreState()
	{
		if (m_stateholder != null) 
		{
			//1)
			cb_CAN_SystArmDisarm.setEnabled(m_stateholder.getCbSystArmDisarmEnabled());
			cb_CAN_SystArmDisarm.setChecked(m_stateholder.getCbSystArmDisarmChecked());
			spin_CAN_SystArmDisarm.setEnabled(m_stateholder.getSpinSystArmDisarmEnabled());
			spin_CAN_SystArmDisarm.setSelection(m_stateholder.getSpinSystArmDisarmValue());
			
			//2)
			cb_CAN_Ignition.setEnabled(m_stateholder.getCbIgnitionEnabled());
			cb_CAN_Ignition.setChecked(m_stateholder.getCbIgnitionChecked());
			spin_CAN_Ignition.setEnabled(m_stateholder.getSpinIgnitionEnabled());
			spin_CAN_Ignition.setSelection(m_stateholder.getSpinIgnitionValue());
			
			//3)
			cb_CAN_DriveControl.setEnabled(m_stateholder.getCbDriveControlEnabled());
			cb_CAN_DriveControl.setChecked(m_stateholder.getCbDriveControlChecked());
			spin_CAN_DriveControl.setEnabled(m_stateholder.getSpinDriveControlEnabled());
			spin_CAN_DriveControl.setSelection(m_stateholder.getSpinDriveControlValue());
			
			//4)
			cb_CAN_ComfortSignal.setEnabled(m_stateholder.getCbComfortSignalEnabled());
			cb_CAN_ComfortSignal.setChecked(m_stateholder.getCbComfortSignalChecked());
			spin_CAN_ComfortSignal.setEnabled(m_stateholder.getSpinComfortSignalEnabled());
			spin_CAN_ComfortSignal.setSelection(m_stateholder.getSpinComfortSignalValue());
			
			//5)
			cb_CAN_ManagingStaffSecuritySystem.setEnabled(m_stateholder.getCbManagingStaffSecuritySystemEnabled());
			cb_CAN_ManagingStaffSecuritySystem.setChecked(m_stateholder.getCbManagingStaffSecuritySystemChecked());
			spin_CAN_ManagingStaffSecuritySystem.setEnabled(m_stateholder.getSpinManagingStaffSecuritySystemEnabled());
			spin_CAN_ManagingStaffSecuritySystem.setSelection(m_stateholder.getSpinManagingStaffSecuritySystemValue());
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) 
	{
		switch(buttonView.getId())
		{
			case R.id.cb_CAN_SystArmDisarm:
			{
				spin_CAN_SystArmDisarm.setEnabled(isChecked);
				break;
			}
			
			case R.id.cb_CAN_Ignition:
			{
				spin_CAN_Ignition.setEnabled(isChecked);
				break;
			}
			
			case R.id.cb_CAN_DriveControl:
			{
				spin_CAN_DriveControl.setEnabled(isChecked);
				break;
			}
			
			case R.id.cb_CAN_ComfortSignal:
			{
				spin_CAN_ComfortSignal.setEnabled(isChecked);
				break;
			}
			
			case R.id.cb_CAN_ManagingStaffSecuritySystem:
			{
				spin_CAN_ManagingStaffSecuritySystem.setEnabled(isChecked);
				break;
			}
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
		setTitle(R.string.btn_Main_CAN);
		//1)
		cb_CAN_SystArmDisarm.setText(R.string.cb_CAN_SystArmDisarm);
		int pos_CAN_SystArmDisarm = spin_CAN_SystArmDisarm.getSelectedItemPosition();
		ArrayAdapter<String> inputSystArmDisarm = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_can_sys_arm_disarm));
		spin_CAN_SystArmDisarm.setAdapter(inputSystArmDisarm);
		spin_CAN_SystArmDisarm.setSelection(pos_CAN_SystArmDisarm);
		//2)
		cb_CAN_Ignition.setText(R.string.cb_CAN_Ignition);
		int pos_CAN_Ignition = spin_CAN_Ignition.getSelectedItemPosition();
		ArrayAdapter<String> inputIgnition = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_can_ignition));
		spin_CAN_Ignition.setAdapter(inputIgnition);
		spin_CAN_Ignition.setSelection(pos_CAN_Ignition);
		//3)
		cb_CAN_DriveControl.setText(R.string.cb_CAN_DriveControl);
		int pos_CAN_DriveControl = spin_CAN_DriveControl.getSelectedItemPosition();
		ArrayAdapter<String> inputDriveControl = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_can_drive_control));
		spin_CAN_DriveControl.setAdapter(inputDriveControl);
		spin_CAN_DriveControl.setSelection(pos_CAN_DriveControl);
		//4)
		cb_CAN_ComfortSignal.setText(R.string.cb_CAN_ComfortSignal);
		int pos_CAN_ComfortSignal = spin_CAN_ComfortSignal.getSelectedItemPosition();
		ArrayAdapter<String> inputComfortSignal = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_can_comfort_signal));
		spin_CAN_ComfortSignal.setAdapter(inputComfortSignal);
		spin_CAN_ComfortSignal.setSelection(pos_CAN_ComfortSignal);
		//5)
		cb_CAN_ManagingStaffSecuritySystem.setText(R.string.cb_CAN_ManagingStaffSecuritySystem);
		int pos_CAN_ManagingStaffSecuritySystem = spin_CAN_ManagingStaffSecuritySystem.getSelectedItemPosition();
		ArrayAdapter<String> inputManagingStaffSecuritySystem = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_can_managing_staff_system));
		spin_CAN_ManagingStaffSecuritySystem.setAdapter(inputManagingStaffSecuritySystem);
		spin_CAN_ManagingStaffSecuritySystem.setSelection(pos_CAN_ManagingStaffSecuritySystem);
	}
	
	@Override
	public void finish() 
	{
	    super.finish();
	    //instance = null;
	}
	
	@Override
	protected void onDestroy() {
		//instance=null;
		super.onDestroy();
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
