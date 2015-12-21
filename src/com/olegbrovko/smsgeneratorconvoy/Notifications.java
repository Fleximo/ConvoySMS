package com.olegbrovko.smsgeneratorconvoy;

import java.util.Locale;

import stateholders.NotificationStateHolder;
import utils.GenericTextWatcher;
import widgets.CheckableImageButton;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.olegbrovko.smsgeneratorconvoy.R;

public class Notifications extends Activity implements OnCheckedChangeListener, OnClickListener
{
	public static Notifications instance = null;
	
	GenericTextWatcher genericTextWatcher = null;
	
	TextView tv_Notifications_UserNumber = null;
	ImageButton btn_Notifications_Prev = null;
	ImageButton btn_Notifications_Next = null;
	
	CheckBox cb_Notifications_User = null;
	EditText et_Notifications_PhoneNumber = null;
	
	CheckBox cb_Notidications_AllowNotificationsSMS = null;
	CheckBox cb_Notidications_AllowNotificationsTEL = null;
	
	CheckableImageButton btn_Notifications_CallBtnPressedSMS = null; 
	CheckableImageButton btn_Notifications_CallBtnPressedTEL = null;
	CheckableImageButton btn_Notifications_IngineActiveSMS = null;
	CheckableImageButton btn_Notifications_IngineActiveTEL = null;
	CheckableImageButton btn_Notifications_LoadLimitSwitchSMS = null;
	CheckableImageButton btn_Notifications_LoadLimitSwitchTEL = null;
	CheckableImageButton btn_Notifications_LoadShockSensorSMS = null;
	CheckableImageButton btn_Notifications_LoadShockSensorTEL = null;
	//CheckableImageButton btn_Notifications_WarningZoneSMS = null;
	//CheckableImageButton btn_Notifications_WarningZoneTEL = null;
	CheckableImageButton btn_Notifications_LoadUniversalLimitSwitchSMS = null;
	CheckableImageButton btn_Notifications_LoadUniversalLimitSwitchTEL = null;
	CheckableImageButton btn_Notifications_LowBatterySMS = null;
	//CheckableImageButton btn_Notifications_LowBatteryTEL = null;
	CheckableImageButton btn_Notifications_GSM_SMS = null;
	//CheckableImageButton btn_Notifications_GSM_TEL = null;
	CheckableImageButton btn_Notifications_SystemDisarmSMS = null;
	CheckableImageButton btn_Notifications_SystemDisarmTEL = null;
	CheckableImageButton btn_Notifications_SystemArmingSMS = null;
	CheckableImageButton btn_Notifications_SystemArmingTEL = null;
	
	//State holder
	static NotificationStateHolder m_stateholder = null;
	
	//Current user
	int currentUserNumber = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		if(Notifications.instance != null) 
		{
	        try 
	        {  Notifications.instance.finish(); } 
	        catch (Exception e) {}
	    }
		super.onCreate(savedInstanceState);
		instance = this;
		setLanguage();
		setContentView(R.layout.notifications);
		
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
	
	private void init()
	{
		//---------------------------------------- INITIALIZATION ------------------------------------
		tv_Notifications_UserNumber = (TextView) findViewById(R.id.tv_Notifications_UserNumber);
		btn_Notifications_Prev = (ImageButton) findViewById(R.id.btn_Notifications_Prev);
		btn_Notifications_Next = (ImageButton) findViewById(R.id.btn_Notifications_Next);
		cb_Notifications_User = (CheckBox) findViewById(R.id.cb_Notifications_User);
		et_Notifications_PhoneNumber = (EditText) findViewById(R.id.et_Notifications_PhoneNumber);
		cb_Notidications_AllowNotificationsSMS = (CheckBox) findViewById(R.id.cb_Notidications_AllowNotificationsSMS);
		cb_Notidications_AllowNotificationsTEL = (CheckBox) findViewById(R.id.cb_Notidications_AllowNotificationsTEL);
		
		btn_Notifications_CallBtnPressedSMS = (CheckableImageButton) findViewById(R.id.btn_Notifications_CallBtnPressedSMS);
		btn_Notifications_CallBtnPressedTEL = (CheckableImageButton) findViewById(R.id.btn_Notifications_CallBtnPressedTEL);
		btn_Notifications_IngineActiveSMS = (CheckableImageButton) findViewById(R.id.btn_Notifications_IngineActiveSMS);
		btn_Notifications_IngineActiveTEL = (CheckableImageButton) findViewById(R.id.btn_Notifications_IngineActiveTEL);
		btn_Notifications_LoadLimitSwitchSMS = (CheckableImageButton) findViewById(R.id.btn_Notifications_LoadLimitSwitchSMS);
		btn_Notifications_LoadLimitSwitchTEL = (CheckableImageButton) findViewById(R.id.btn_Notifications_LoadLimitSwitchTEL);
		btn_Notifications_LoadShockSensorSMS = (CheckableImageButton) findViewById(R.id.btn_Notifications_LoadShockSensorSMS);
		btn_Notifications_LoadShockSensorTEL = (CheckableImageButton) findViewById(R.id.btn_Notifications_LoadShockSensorTEL);
		//btn_Notifications_WarningZoneSMS = (CheckableImageButton) findViewById(R.id.btn_Notifications_WarningZoneSMS);
		//btn_Notifications_WarningZoneTEL = (CheckableImageButton) findViewById(R.id.btn_Notifications_WarningZoneTEL);
		btn_Notifications_LoadUniversalLimitSwitchSMS = (CheckableImageButton) findViewById(R.id.btn_Notifications_LoadUniversalLimitSwitchSMS);
		btn_Notifications_LoadUniversalLimitSwitchTEL = (CheckableImageButton) findViewById(R.id.btn_Notifications_LoadUniversalLimitSwitchTEL);
		btn_Notifications_LowBatterySMS = (CheckableImageButton) findViewById(R.id.btn_Notifications_LowBatterySMS);
		//btn_Notifications_LowBatteryTEL = (CheckableImageButton) findViewById(R.id.btn_Notifications_LowBatteryTEL);
		btn_Notifications_GSM_SMS = (CheckableImageButton) findViewById(R.id.btn_Notifications_GSM_SMS);
		//btn_Notifications_GSM_TEL = (CheckableImageButton) findViewById(R.id.btn_Notifications_GSM_TEL);
		btn_Notifications_SystemDisarmSMS = (CheckableImageButton) findViewById(R.id.btn_Notifications_SystemDisarmSMS);
		btn_Notifications_SystemDisarmTEL = (CheckableImageButton) findViewById(R.id.btn_Notifications_SystemDisarmTEL);
		btn_Notifications_SystemArmingSMS = (CheckableImageButton) findViewById(R.id.btn_Notifications_SystemArmingSMS);
		btn_Notifications_SystemArmingTEL = (CheckableImageButton) findViewById(R.id.btn_Notifications_SystemArmingTEL);
		
		genericTextWatcher = new GenericTextWatcher(et_Notifications_PhoneNumber);
		
		//---------------------------------------- SET VALUES ----------------------------------------
		tv_Notifications_UserNumber.setText("USER " + (currentUserNumber + 1) );
		
		//---------------------------------------- SET STATES ---------------------------------------
		et_Notifications_PhoneNumber.setEnabled(false);
		cb_Notidications_AllowNotificationsSMS.setEnabled(false);
		cb_Notidications_AllowNotificationsTEL.setEnabled(false);
		
		btn_Notifications_CallBtnPressedSMS.setEnabled(false);
		btn_Notifications_CallBtnPressedTEL.setEnabled(false);
		btn_Notifications_IngineActiveSMS.setEnabled(false);
		btn_Notifications_IngineActiveTEL.setEnabled(false);
		btn_Notifications_LoadLimitSwitchSMS.setEnabled(false);
		btn_Notifications_LoadLimitSwitchTEL.setEnabled(false);
		btn_Notifications_LoadShockSensorSMS.setEnabled(false);
		btn_Notifications_LoadShockSensorTEL.setEnabled(false);
		//btn_Notifications_WarningZoneSMS.setEnabled(false);
		//btn_Notifications_WarningZoneTEL.setEnabled(false);
		btn_Notifications_LoadUniversalLimitSwitchSMS.setEnabled(false);
		btn_Notifications_LoadUniversalLimitSwitchTEL.setEnabled(false);
		btn_Notifications_LowBatterySMS.setEnabled(false);
		//btn_Notifications_LowBatteryTEL.setEnabled(false);
		btn_Notifications_GSM_SMS.setEnabled(false);
		//btn_Notifications_GSM_TEL.setEnabled(false);
		btn_Notifications_SystemDisarmSMS.setEnabled(false);
		btn_Notifications_SystemDisarmTEL.setEnabled(false);
		btn_Notifications_SystemArmingSMS.setEnabled(false);
		btn_Notifications_SystemArmingTEL.setEnabled(false);
	}
	
	private void setListeners()
	{
		btn_Notifications_Prev.setOnClickListener(this);
		btn_Notifications_Next.setOnClickListener(this);
		
		cb_Notifications_User.setOnCheckedChangeListener(this);
		cb_Notidications_AllowNotificationsSMS.setOnCheckedChangeListener(this);
		cb_Notidications_AllowNotificationsTEL.setOnCheckedChangeListener(this);
		et_Notifications_PhoneNumber.addTextChangedListener(genericTextWatcher);
		
		//Buttons
		btn_Notifications_CallBtnPressedSMS.setOnClickListener(this);
		btn_Notifications_CallBtnPressedTEL.setOnClickListener(this);
		btn_Notifications_IngineActiveSMS.setOnClickListener(this);
		btn_Notifications_IngineActiveTEL.setOnClickListener(this);
		btn_Notifications_LoadLimitSwitchSMS.setOnClickListener(this);
		btn_Notifications_LoadLimitSwitchTEL.setOnClickListener(this);
		btn_Notifications_LoadShockSensorSMS.setOnClickListener(this);
		btn_Notifications_LoadShockSensorTEL.setOnClickListener(this);
		//btn_Notifications_WarningZoneSMS.setOnClickListener(this);
		//btn_Notifications_WarningZoneTEL.setOnClickListener(this);
		btn_Notifications_LoadUniversalLimitSwitchSMS.setOnClickListener(this);
		btn_Notifications_LoadUniversalLimitSwitchTEL.setOnClickListener(this);
		btn_Notifications_LowBatterySMS.setOnClickListener(this);
		//btn_Notifications_LowBatteryTEL.setOnClickListener(this);
		btn_Notifications_GSM_SMS.setOnClickListener(this);
		//btn_Notifications_GSM_TEL.setOnClickListener(this);
		btn_Notifications_SystemDisarmSMS.setOnClickListener(this);
		btn_Notifications_SystemDisarmTEL.setOnClickListener(this);
		btn_Notifications_SystemArmingSMS.setOnClickListener(this);
		btn_Notifications_SystemArmingTEL.setOnClickListener(this);
	}
	
	private void unsetListeners()
	{
		btn_Notifications_Prev.setOnClickListener(null);
		btn_Notifications_Next.setOnClickListener(null);
		
		cb_Notifications_User.setOnCheckedChangeListener(null);
		cb_Notidications_AllowNotificationsSMS.setOnCheckedChangeListener(null);
		cb_Notidications_AllowNotificationsTEL.setOnCheckedChangeListener(null);
		//et_Notifications_PhoneNumber.addTextChangedListener(null);
		
		//Buttons
		btn_Notifications_CallBtnPressedSMS.setOnClickListener(null);
		btn_Notifications_CallBtnPressedTEL.setOnClickListener(null);
		btn_Notifications_IngineActiveSMS.setOnClickListener(null);
		btn_Notifications_IngineActiveTEL.setOnClickListener(null);
		btn_Notifications_LoadLimitSwitchSMS.setOnClickListener(null);
		btn_Notifications_LoadLimitSwitchTEL.setOnClickListener(null);
		btn_Notifications_LoadShockSensorSMS.setOnClickListener(null);
		btn_Notifications_LoadShockSensorTEL.setOnClickListener(null);
		//btn_Notifications_WarningZoneSMS.setOnClickListener(null);
		//btn_Notifications_WarningZoneTEL.setOnClickListener(null);
		btn_Notifications_LoadUniversalLimitSwitchSMS.setOnClickListener(null);
		btn_Notifications_LoadUniversalLimitSwitchTEL.setOnClickListener(null);
		btn_Notifications_LowBatterySMS.setOnClickListener(null);
		//btn_Notifications_LowBatteryTEL.setOnClickListener(null);
		btn_Notifications_GSM_SMS.setOnClickListener(null);
		//btn_Notifications_GSM_TEL.setOnClickListener(null);
		btn_Notifications_SystemDisarmSMS.setOnClickListener(null);
		btn_Notifications_SystemDisarmTEL.setOnClickListener(null);
		btn_Notifications_SystemArmingSMS.setOnClickListener(null);
		btn_Notifications_SystemArmingTEL.setOnClickListener(null);
	}
	
	@Override
	protected void onPause() 
	{
		super.onPause();
		saveState();
	}
	
	private void saveState()
	{
		if(m_stateholder == null)
			m_stateholder = MainActivity.stateholderNotification;
		
		//Current user Id
		m_stateholder.setCurrentUserId(currentUserNumber);
		
		//UserChecked
		m_stateholder.setCbInputsInEnabled(currentUserNumber, cb_Notifications_User.isEnabled());
		m_stateholder.setCbInputsInChecked(currentUserNumber, cb_Notifications_User.isChecked());
		
		//Phone number
		m_stateholder.setEtPhoneNumberEnabled(currentUserNumber, et_Notifications_PhoneNumber.isEnabled());
		m_stateholder.setEtPhoneNumberValue(currentUserNumber, et_Notifications_PhoneNumber.getText().toString());
		
		//Allow Notification
		m_stateholder.setCbAllowNotificationsSMSEnabled(currentUserNumber, cb_Notidications_AllowNotificationsSMS.isEnabled());
		m_stateholder.setCbAllowNotificationsSMSChecked(currentUserNumber, cb_Notidications_AllowNotificationsSMS.isChecked());
		m_stateholder.setCbAllowNotificationsTELEnabled(currentUserNumber, cb_Notidications_AllowNotificationsTEL.isEnabled());
		m_stateholder.setCbAllowNotificationsTELChecked(currentUserNumber, cb_Notidications_AllowNotificationsTEL.isChecked());
		
		//Button Call button pressed
		m_stateholder.setBtnCallBtnPressedSMSEnabled(currentUserNumber, btn_Notifications_CallBtnPressedSMS.isEnabled());
		m_stateholder.setBtnCallBtnPressedSMSChecked(currentUserNumber, btn_Notifications_CallBtnPressedSMS.isChecked());
		m_stateholder.setBtnCallBtnPressedTELEnabled(currentUserNumber, btn_Notifications_CallBtnPressedTEL.isEnabled());
		m_stateholder.setBtnCallBtnPressedTELChecked(currentUserNumber, btn_Notifications_CallBtnPressedTEL.isChecked());
		
		//Button Ingine Active
		m_stateholder.setBtnIngineActiveSMSEnabled(currentUserNumber, btn_Notifications_IngineActiveSMS.isEnabled());
		m_stateholder.setBtnIngineActiveSMSChecked(currentUserNumber, btn_Notifications_IngineActiveSMS.isChecked());
		m_stateholder.setBtnIngineActiveTELEnabled(currentUserNumber, btn_Notifications_IngineActiveTEL.isEnabled());
		m_stateholder.setBtnIngineActiveTELChecked(currentUserNumber, btn_Notifications_IngineActiveTEL.isChecked());
		
		//Button Load Limit Switch
		m_stateholder.setBtnLoadLimitSwitchSMSEnabled(currentUserNumber, btn_Notifications_LoadLimitSwitchSMS.isEnabled());
		m_stateholder.setBtnLoadLimitSwitchSMSChecked(currentUserNumber, btn_Notifications_LoadLimitSwitchSMS.isChecked());
		m_stateholder.setBtnLoadLimitSwitchTELEnabled(currentUserNumber, btn_Notifications_LoadLimitSwitchTEL.isEnabled());
		m_stateholder.setBtnLoadLimitSwitchTELChecked(currentUserNumber, btn_Notifications_LoadLimitSwitchTEL.isChecked());
		
		//Button Load Shock Sensor
		m_stateholder.setBtnLoadShockSensorSMSEnabled(currentUserNumber, btn_Notifications_LoadShockSensorSMS.isEnabled());
		m_stateholder.setBtnLoadShockSensorSMSChecked(currentUserNumber, btn_Notifications_LoadShockSensorSMS.isChecked());
		m_stateholder.setBtnLoadShockSensorTELEnabled(currentUserNumber, btn_Notifications_LoadShockSensorTEL.isEnabled());
		m_stateholder.setBtnLoadShockSensorTELChecked(currentUserNumber, btn_Notifications_LoadShockSensorTEL.isChecked());
		
		//Warning Zone
		//m_stateholder.setBtnWarningZoneSMSEnabled(currentUserNumber, btn_Notifications_WarningZoneSMS.isEnabled());
		//m_stateholder.setBtnWarningZoneSMSChecked(currentUserNumber, btn_Notifications_WarningZoneSMS.isChecked());
		//m_stateholder.setBtnWarningZoneTELEnabled(currentUserNumber, btn_Notifications_WarningZoneTEL.isEnabled());
		//m_stateholder.setBtnWarningZoneTELChecked(currentUserNumber, btn_Notifications_WarningZoneTEL.isChecked());
		
		//Button Load Universal Limit Switch
		m_stateholder.setBtnLoadUniversalLimitSwitchSMSEnabled(currentUserNumber, btn_Notifications_LoadUniversalLimitSwitchSMS.isEnabled());
		m_stateholder.setBtnLoadUniversalLimitSwitchSMSChecked(currentUserNumber, btn_Notifications_LoadUniversalLimitSwitchSMS.isChecked());
		m_stateholder.setBtnLoadUniversalLimitSwitchTELEnabled(currentUserNumber, btn_Notifications_LoadUniversalLimitSwitchTEL.isEnabled());
		m_stateholder.setBtnLoadUniversalLimitSwitchTELChecked(currentUserNumber, btn_Notifications_LoadUniversalLimitSwitchTEL.isChecked());
		
		//Button Low Buttery
		m_stateholder.setBtnLowBatterySMSEnabled(currentUserNumber, btn_Notifications_LowBatterySMS.isEnabled());
		m_stateholder.setBtnLowBatterySMSChecked(currentUserNumber, btn_Notifications_LowBatterySMS.isChecked());
		//m_stateholder.setBtnLowBatteryTELEnabled(currentUserNumber, btn_Notifications_LowBatteryTEL.isEnabled());
		//m_stateholder.setBtnLowBatteryTELChecked(currentUserNumber, btn_Notifications_LowBatteryTEL.isChecked());
		
		//GSM
		m_stateholder.setBtnGSMSMSEnabled(currentUserNumber, btn_Notifications_GSM_SMS.isEnabled());
		m_stateholder.setBtnGSMSMSChecked(currentUserNumber, btn_Notifications_GSM_SMS.isChecked());
		//m_stateholder.setBtnGTELMSEnabled(currentUserNumber, btn_Notifications_GSM_TEL.isEnabled());
		//m_stateholder.setBtnGTELMSChecked(currentUserNumber, btn_Notifications_GSM_TEL.isChecked());
		
		//Button System Disarm
		m_stateholder.setBtnSystemDisarmSMSEnabled(currentUserNumber, btn_Notifications_SystemDisarmSMS.isEnabled());
		m_stateholder.setBtnSystemDisarmSMSChecked(currentUserNumber, btn_Notifications_SystemDisarmSMS.isChecked());
		m_stateholder.setBtnSystemDisarmTELEnabled(currentUserNumber, btn_Notifications_SystemDisarmTEL.isEnabled());
		m_stateholder.setBtnSystemDisarmTELChecked(currentUserNumber, btn_Notifications_SystemDisarmTEL.isChecked());
		
		//Button System Arming
		m_stateholder.setBtnSystemArmingSMSEnabled(currentUserNumber, btn_Notifications_SystemArmingSMS.isEnabled());
		m_stateholder.setBtnSystemArmingSMSChecked(currentUserNumber, btn_Notifications_SystemArmingSMS.isChecked());
		m_stateholder.setBtnSystemArmingTELEnabled(currentUserNumber, btn_Notifications_SystemArmingTEL.isEnabled());
		m_stateholder.setBtnSystemArmingTELChecked(currentUserNumber, btn_Notifications_SystemArmingTEL.isChecked());
	}
	
	private void restoreState()
	{
		if (m_stateholder != null) 
		{
			int idToRestore = m_stateholder.getCurrentUserId();
			//Current user Id
			currentUserNumber = m_stateholder.getCurrentUserId();
			tv_Notifications_UserNumber.setText("USER " + ( idToRestore + 1) );
			
			//UserChecked
			cb_Notifications_User.setEnabled(m_stateholder.getCbInputsInEnabled(idToRestore));
			cb_Notifications_User.setChecked(m_stateholder.getCbInputsInChecked(idToRestore));
			
			//Phone number
			et_Notifications_PhoneNumber.setEnabled(m_stateholder.getEtPhoneNumberEnabled(idToRestore));
			et_Notifications_PhoneNumber.setText(m_stateholder.getEtPhoneNumberValue(idToRestore));
			
			//Allow Notification
			cb_Notidications_AllowNotificationsSMS.setEnabled(m_stateholder.getCbAllowNotificationsSMSEnabled(idToRestore));
			cb_Notidications_AllowNotificationsSMS.setChecked(m_stateholder.getCbAllowNotificationsSMSChecked(idToRestore));
			cb_Notidications_AllowNotificationsTEL.setEnabled(m_stateholder.getCbAllowNotificationsTELEnabled(idToRestore));
			cb_Notidications_AllowNotificationsTEL.setChecked(m_stateholder.getCbAllowNotificationsTELChecked(idToRestore));
			
			//Button Call button pressed
			btn_Notifications_CallBtnPressedSMS.setEnabled(m_stateholder.getBtnCallBtnPressedSMSEnabled(idToRestore));
			btn_Notifications_CallBtnPressedSMS.setChecked(m_stateholder.getBtnCallBtnPressedSMSChecked(idToRestore));
			setImageForSMS(btn_Notifications_CallBtnPressedSMS, m_stateholder.getBtnCallBtnPressedSMSChecked(idToRestore));
			btn_Notifications_CallBtnPressedTEL.setEnabled(m_stateholder.getBtnCallBtnPressedTELEnabled(idToRestore));
			btn_Notifications_CallBtnPressedTEL.setChecked(m_stateholder.getBtnCallBtnPressedTELChecked(idToRestore));
			setImageForTEL(btn_Notifications_CallBtnPressedTEL, m_stateholder.getBtnCallBtnPressedTELChecked(idToRestore));
			
			//Button Ingine Active
			btn_Notifications_IngineActiveSMS.setEnabled(m_stateholder.getBtnIngineActiveSMSEnabled(idToRestore));
			btn_Notifications_IngineActiveSMS.setChecked(m_stateholder.getBtnIngineActiveSMSChecked(idToRestore));
			setImageForSMS(btn_Notifications_IngineActiveSMS, m_stateholder.getBtnIngineActiveSMSChecked(idToRestore));
			btn_Notifications_IngineActiveTEL.setEnabled(m_stateholder.getBtnIngineActiveTELEnabled(idToRestore));
			btn_Notifications_IngineActiveTEL.setChecked(m_stateholder.getBtnIngineActiveTELChecked(idToRestore));
			setImageForTEL(btn_Notifications_IngineActiveTEL, m_stateholder.getBtnIngineActiveTELChecked(idToRestore));
			
			//Button Load Limit Switch
			btn_Notifications_LoadLimitSwitchSMS.setEnabled(m_stateholder.getBtnLoadLimitSwitchSMSEnabled(idToRestore));
			btn_Notifications_LoadLimitSwitchSMS.setChecked(m_stateholder.getBtnLoadLimitSwitchSMSChecked(idToRestore));
			setImageForSMS(btn_Notifications_LoadLimitSwitchSMS, m_stateholder.getBtnLoadLimitSwitchSMSChecked(idToRestore));
			btn_Notifications_LoadLimitSwitchTEL.setEnabled(m_stateholder.getBtnLoadLimitSwitchTELEnabled(idToRestore));
			btn_Notifications_LoadLimitSwitchTEL.setChecked(m_stateholder.getBtnLoadLimitSwitchTELChecked(idToRestore));
			setImageForTEL(btn_Notifications_LoadLimitSwitchTEL, m_stateholder.getBtnLoadLimitSwitchTELChecked(idToRestore));
			
			//Button Load Shock Sensor
			btn_Notifications_LoadShockSensorSMS.setEnabled(m_stateholder.getBtnLoadShockSensorSMSEnabled(idToRestore));
			btn_Notifications_LoadShockSensorSMS.setChecked(m_stateholder.getBtnLoadShockSensorSMSChecked(idToRestore));
			setImageForSMS(btn_Notifications_LoadShockSensorSMS, m_stateholder.getBtnLoadShockSensorSMSChecked(idToRestore));
			btn_Notifications_LoadShockSensorTEL.setEnabled(m_stateholder.getBtnLoadShockSensorTELEnabled(idToRestore));
			btn_Notifications_LoadShockSensorTEL.setChecked(m_stateholder.getBtnLoadShockSensorTELChecked(idToRestore));
			setImageForTEL(btn_Notifications_LoadShockSensorTEL, m_stateholder.getBtnLoadShockSensorTELChecked(idToRestore));
			
			//Warning zone
//			btn_Notifications_WarningZoneSMS.setEnabled(m_stateholder.getBtnWarningZoneSMSEnabled(idToRestore));
//			btn_Notifications_WarningZoneSMS.setChecked(m_stateholder.getBtnWarningZoneSMSChecked(idToRestore));
//			setImageForSMS(btn_Notifications_WarningZoneSMS, m_stateholder.getBtnWarningZoneSMSChecked(idToRestore));
//			btn_Notifications_WarningZoneTEL.setEnabled(m_stateholder.getBtnWarningZoneTELEnabled(idToRestore));
//			btn_Notifications_WarningZoneTEL.setChecked(m_stateholder.getBtnWarningZoneTELChecked(idToRestore));
//			setImageForTEL(btn_Notifications_WarningZoneTEL, m_stateholder.getBtnWarningZoneTELChecked(idToRestore));
			
			//Button Load Universal Limit Switch
			btn_Notifications_LoadUniversalLimitSwitchSMS.setEnabled(m_stateholder.getBtnLoadUniversalLimitSwitchSMSEnabled(idToRestore));
			btn_Notifications_LoadUniversalLimitSwitchSMS.setChecked(m_stateholder.getBtnLoadUniversalLimitSwitchSMSChecked(idToRestore));
			setImageForSMS(btn_Notifications_LoadUniversalLimitSwitchSMS, m_stateholder.getBtnLoadUniversalLimitSwitchSMSChecked(idToRestore));
			btn_Notifications_LoadUniversalLimitSwitchTEL.setEnabled(m_stateholder.getBtnLoadUniversalLimitSwitchTELEnabled(idToRestore));
			btn_Notifications_LoadUniversalLimitSwitchTEL.setChecked(m_stateholder.getBtnLoadUniversalLimitSwitchTELChecked(idToRestore));
			setImageForTEL(btn_Notifications_LoadUniversalLimitSwitchTEL, m_stateholder.getBtnLoadUniversalLimitSwitchTELChecked(idToRestore));
			
			//Button Low Buttery
			btn_Notifications_LowBatterySMS.setEnabled(m_stateholder.getBtnLowBatterySMSEnabled(idToRestore));
			btn_Notifications_LowBatterySMS.setChecked(m_stateholder.getBtnLowBatterySMSChecked(idToRestore));
			setImageForSMS(btn_Notifications_LowBatterySMS, m_stateholder.getBtnLowBatterySMSChecked(idToRestore));
//			btn_Notifications_LowBatteryTEL.setEnabled(m_stateholder.getBtnLowBatteryTELEnabled(idToRestore));
//			btn_Notifications_LowBatteryTEL.setChecked(m_stateholder.getBtnLowBatteryTELChecked(idToRestore));
//			setImageForTEL(btn_Notifications_LowBatteryTEL, m_stateholder.getBtnLowBatteryTELChecked(idToRestore));
			
			//GSM
			btn_Notifications_GSM_SMS.setEnabled(m_stateholder.getBtnGSMSMSEnabled(idToRestore));
			btn_Notifications_GSM_SMS.setChecked(m_stateholder.getBtnGSMSMSChecked(idToRestore));
			setImageForSMS(btn_Notifications_GSM_SMS, m_stateholder.getBtnGSMSMSChecked(idToRestore));
//			btn_Notifications_GSM_TEL.setEnabled(m_stateholder.getBtnGTELMSEnabled(idToRestore));
//			btn_Notifications_GSM_TEL.setChecked(m_stateholder.getBtnGTELMSChecked(idToRestore));
//			setImageForTEL(btn_Notifications_GSM_TEL, m_stateholder.getBtnGTELMSChecked(idToRestore));
			
			//Button System Disarm
			btn_Notifications_SystemDisarmSMS.setEnabled(m_stateholder.getBtnSystemDisarmSMSEnabled(idToRestore));
			btn_Notifications_SystemDisarmSMS.setChecked(m_stateholder.getBtnSystemDisarmSMSChecked(idToRestore));
			setImageForSMS(btn_Notifications_SystemDisarmSMS, m_stateholder.getBtnSystemDisarmSMSChecked(idToRestore));
			btn_Notifications_SystemDisarmTEL.setEnabled(m_stateholder.getBtnSystemDisarmTELEnabled(idToRestore));
			btn_Notifications_SystemDisarmTEL.setChecked(m_stateholder.getBtnSystemDisarmTELChecked(idToRestore));
			setImageForTEL(btn_Notifications_SystemDisarmTEL, m_stateholder.getBtnSystemDisarmTELChecked(idToRestore));
			
			//Button System Arming
			btn_Notifications_SystemArmingSMS.setEnabled(m_stateholder.getBtnSystemArmingSMSEnabled(idToRestore));
			btn_Notifications_SystemArmingSMS.setChecked(m_stateholder.getBtnSystemArmingSMSChecked(idToRestore));
			setImageForSMS(btn_Notifications_SystemArmingSMS, m_stateholder.getBtnSystemArmingSMSChecked(idToRestore));
			btn_Notifications_SystemArmingTEL.setEnabled(m_stateholder.getBtnSystemArmingTELEnabled(idToRestore));
			btn_Notifications_SystemArmingTEL.setChecked(m_stateholder.getBtnSystemArmingTELChecked(idToRestore));
			setImageForTEL(btn_Notifications_SystemArmingTEL, m_stateholder.getBtnSystemArmingTELChecked(idToRestore));
		}
	}
	
	//================================= LISENERS ==============================================
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) 
	{
		switch(buttonView.getId())
		{
			case R.id.cb_Notifications_User:
			{
				et_Notifications_PhoneNumber.setEnabled(isChecked);
				cb_Notidications_AllowNotificationsSMS.setEnabled(isChecked);
				cb_Notidications_AllowNotificationsTEL.setEnabled(isChecked);
				
				if(isChecked == true)
				{
					if(cb_Notidications_AllowNotificationsSMS.isChecked())
					{
						btn_Notifications_CallBtnPressedSMS.setEnabled(true);
						btn_Notifications_IngineActiveSMS.setEnabled(true);
						btn_Notifications_LoadLimitSwitchSMS.setEnabled(true);
						btn_Notifications_LoadShockSensorSMS.setEnabled(true);
						//btn_Notifications_WarningZoneSMS.setEnabled(true);
						btn_Notifications_LoadUniversalLimitSwitchSMS.setEnabled(true);
						btn_Notifications_LowBatterySMS.setEnabled(true);
						btn_Notifications_GSM_SMS.setEnabled(true);
						btn_Notifications_SystemDisarmSMS.setEnabled(true);
						btn_Notifications_SystemArmingSMS.setEnabled(true);
					}
					
					if(cb_Notidications_AllowNotificationsTEL.isChecked())
					{
						btn_Notifications_CallBtnPressedTEL.setEnabled(true);
						btn_Notifications_IngineActiveTEL.setEnabled(true);
						btn_Notifications_LoadLimitSwitchTEL.setEnabled(true);
						btn_Notifications_LoadShockSensorTEL.setEnabled(true);
						//btn_Notifications_WarningZoneTEL.setEnabled(true);
						btn_Notifications_LoadUniversalLimitSwitchTEL.setEnabled(true);
//						btn_Notifications_LowBatteryTEL.setEnabled(true);
//						btn_Notifications_GSM_TEL.setEnabled(true);
						btn_Notifications_SystemDisarmTEL.setEnabled(true);
						btn_Notifications_SystemArmingTEL.setEnabled(true);
					}
				}
				
				if(isChecked == false)
				{
					btn_Notifications_CallBtnPressedSMS.setEnabled(false);
					btn_Notifications_IngineActiveSMS.setEnabled(false);
					btn_Notifications_LoadLimitSwitchSMS.setEnabled(false);
					btn_Notifications_LoadShockSensorSMS.setEnabled(false);
					//btn_Notifications_WarningZoneSMS.setEnabled(false);
					btn_Notifications_LoadUniversalLimitSwitchSMS.setEnabled(false);
					btn_Notifications_LowBatterySMS.setEnabled(false);
					btn_Notifications_GSM_SMS.setEnabled(false);
					btn_Notifications_SystemDisarmSMS.setEnabled(false);
					btn_Notifications_SystemArmingSMS.setEnabled(false);
					btn_Notifications_CallBtnPressedTEL.setEnabled(false);
					btn_Notifications_IngineActiveTEL.setEnabled(false);
					btn_Notifications_LoadLimitSwitchTEL.setEnabled(false);
					btn_Notifications_LoadShockSensorTEL.setEnabled(false);
					//btn_Notifications_WarningZoneTEL.setEnabled(false);
					btn_Notifications_LoadUniversalLimitSwitchTEL.setEnabled(false);
//					btn_Notifications_LowBatteryTEL.setEnabled(false);
//					btn_Notifications_GSM_TEL.setEnabled(false);
					btn_Notifications_SystemDisarmTEL.setEnabled(false);
					btn_Notifications_SystemArmingTEL.setEnabled(false);
				}
				break;
			}
			
			case R.id.cb_Notidications_AllowNotificationsSMS:
			{
				btn_Notifications_CallBtnPressedSMS.setEnabled(isChecked);
				btn_Notifications_IngineActiveSMS.setEnabled(isChecked);
				btn_Notifications_LoadLimitSwitchSMS.setEnabled(isChecked);
				btn_Notifications_LoadShockSensorSMS.setEnabled(isChecked);
				//btn_Notifications_WarningZoneSMS.setEnabled(isChecked);
				btn_Notifications_LoadUniversalLimitSwitchSMS.setEnabled(isChecked);
				btn_Notifications_LowBatterySMS.setEnabled(isChecked);
				btn_Notifications_GSM_SMS.setEnabled(isChecked);
				btn_Notifications_SystemDisarmSMS.setEnabled(isChecked);
				btn_Notifications_SystemArmingSMS.setEnabled(isChecked);
				break;
			}
			
			case R.id.cb_Notidications_AllowNotificationsTEL:
			{
				btn_Notifications_CallBtnPressedTEL.setEnabled(isChecked);
				btn_Notifications_IngineActiveTEL.setEnabled(isChecked);
				btn_Notifications_LoadLimitSwitchTEL.setEnabled(isChecked);
				btn_Notifications_LoadShockSensorTEL.setEnabled(isChecked);
				//btn_Notifications_WarningZoneTEL.setEnabled(isChecked);
				btn_Notifications_LoadUniversalLimitSwitchTEL.setEnabled(isChecked);
//				btn_Notifications_LowBatteryTEL.setEnabled(isChecked);
//				btn_Notifications_GSM_TEL.setEnabled(isChecked);
				btn_Notifications_SystemDisarmTEL.setEnabled(isChecked);
				btn_Notifications_SystemArmingTEL.setEnabled(isChecked);
				break;
			}
		}
	}
	
	private void setImageForSMS(CheckableImageButton button, boolean checked)
	{
		if(checked)
			button.setImageResource(R.drawable.btn_sms_enabled);
		else
			button.setImageResource(R.drawable.btn_sms_disabled);
	}
	
	private void setImageForTEL(CheckableImageButton button, boolean checked)
	{
		if(checked)
			button.setImageResource(R.drawable.btn_tel_enabled);
		else
			button.setImageResource(R.drawable.btn_tel_disabled);
	}

	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{
			case R.id.btn_Notifications_Prev:
			{
				saveState();
				
				--currentUserNumber;
				if(currentUserNumber<0)
					currentUserNumber = 0;
				m_stateholder.setCurrentUserId(currentUserNumber);
				
				tv_Notifications_UserNumber.setText("USER " + (currentUserNumber + 1) );
				
				unsetListeners();
				restoreState();
				setListeners();
				break;
			}
			case R.id.btn_Notifications_Next:
			{
				saveState();
				
				++currentUserNumber;
				if(currentUserNumber>4)
					currentUserNumber = 4;
				m_stateholder.setCurrentUserId(currentUserNumber);
				
				tv_Notifications_UserNumber.setText("USER " + (currentUserNumber + 1) );
				
				unsetListeners();
				restoreState();
				setListeners();
				break;
			}
		
			case R.id.btn_Notifications_CallBtnPressedSMS:
			{
				boolean checked = btn_Notifications_CallBtnPressedSMS.isChecked();
				btn_Notifications_CallBtnPressedSMS.setChecked(!checked);
				setImageForSMS(btn_Notifications_CallBtnPressedSMS, !checked);
				break;
			}
			case R.id.btn_Notifications_CallBtnPressedTEL:
			{
				boolean checked = btn_Notifications_CallBtnPressedTEL.isChecked();
				btn_Notifications_CallBtnPressedTEL.setChecked(!checked);
				setImageForTEL(btn_Notifications_CallBtnPressedTEL, !checked);
				break;
			}
			
			case R.id.btn_Notifications_IngineActiveSMS:
			{
				boolean checked = btn_Notifications_IngineActiveSMS.isChecked();
				btn_Notifications_IngineActiveSMS.setChecked(!checked);
				if(!checked)
					btn_Notifications_IngineActiveSMS.setImageResource(R.drawable.btn_sms_enabled);
				else
					btn_Notifications_IngineActiveSMS.setImageResource(R.drawable.btn_sms_disabled);
				break;
			}
			case R.id.btn_Notifications_IngineActiveTEL:
			{
				boolean checked = btn_Notifications_IngineActiveTEL.isChecked();
				btn_Notifications_IngineActiveTEL.setChecked(!checked);
				if(!checked)
					btn_Notifications_IngineActiveTEL.setImageResource(R.drawable.btn_tel_enabled);
				else
					btn_Notifications_IngineActiveTEL.setImageResource(R.drawable.btn_tel_disabled);
				break;
			}
			
			case R.id.btn_Notifications_LoadLimitSwitchSMS:
			{
				boolean checked = btn_Notifications_LoadLimitSwitchSMS.isChecked();
				btn_Notifications_LoadLimitSwitchSMS.setChecked(!checked);
				if(!checked)
					btn_Notifications_LoadLimitSwitchSMS.setImageResource(R.drawable.btn_sms_enabled);
				else
					btn_Notifications_LoadLimitSwitchSMS.setImageResource(R.drawable.btn_sms_disabled);
				break;
			}
			case R.id.btn_Notifications_LoadLimitSwitchTEL:
			{
				boolean checked = btn_Notifications_LoadLimitSwitchTEL.isChecked();
				btn_Notifications_LoadLimitSwitchTEL.setChecked(!checked);
				if(!checked)
					btn_Notifications_LoadLimitSwitchTEL.setImageResource(R.drawable.btn_tel_enabled);
				else
					btn_Notifications_LoadLimitSwitchTEL.setImageResource(R.drawable.btn_tel_disabled);
				break;
			}
			
			case R.id.btn_Notifications_LoadShockSensorSMS:
			{
				boolean checked = btn_Notifications_LoadShockSensorSMS.isChecked();
				btn_Notifications_LoadShockSensorSMS.setChecked(!checked);
				if(!checked)
					btn_Notifications_LoadShockSensorSMS.setImageResource(R.drawable.btn_sms_enabled);
				else
					btn_Notifications_LoadShockSensorSMS.setImageResource(R.drawable.btn_sms_disabled);
				break;
			}
			case R.id.btn_Notifications_LoadShockSensorTEL:
			{
				boolean checked = btn_Notifications_LoadShockSensorTEL.isChecked();
				btn_Notifications_LoadShockSensorTEL.setChecked(!checked);
				if(!checked)
					btn_Notifications_LoadShockSensorTEL.setImageResource(R.drawable.btn_tel_enabled);
				else
					btn_Notifications_LoadShockSensorTEL.setImageResource(R.drawable.btn_tel_disabled);
				break;
			}
			
//			case R.id.btn_Notifications_WarningZoneSMS:
//			{
//				boolean checked = btn_Notifications_WarningZoneSMS.isChecked();
//				btn_Notifications_WarningZoneSMS.setChecked(!checked);
//				if(!checked)
//					btn_Notifications_WarningZoneSMS.setImageResource(R.drawable.btn_sms_enabled);
//				else
//					btn_Notifications_WarningZoneSMS.setImageResource(R.drawable.btn_sms_disabled);
//				break;
//			}
//			case R.id.btn_Notifications_WarningZoneTEL:
//			{
//				boolean checked = btn_Notifications_WarningZoneTEL.isChecked();
//				btn_Notifications_WarningZoneTEL.setChecked(!checked);
//				if(!checked)
//					btn_Notifications_WarningZoneTEL.setImageResource(R.drawable.btn_tel_enabled);
//				else
//					btn_Notifications_WarningZoneTEL.setImageResource(R.drawable.btn_tel_disabled);
//				break;
//			}

			case R.id.btn_Notifications_LoadUniversalLimitSwitchSMS:
			{
				boolean checked = btn_Notifications_LoadUniversalLimitSwitchSMS.isChecked();
				btn_Notifications_LoadUniversalLimitSwitchSMS.setChecked(!checked);
				if(!checked)
					btn_Notifications_LoadUniversalLimitSwitchSMS.setImageResource(R.drawable.btn_sms_enabled);
				else
					btn_Notifications_LoadUniversalLimitSwitchSMS.setImageResource(R.drawable.btn_sms_disabled);
				break;
			}
			case R.id.btn_Notifications_LoadUniversalLimitSwitchTEL:
			{
				boolean checked = btn_Notifications_LoadUniversalLimitSwitchTEL.isChecked();
				btn_Notifications_LoadUniversalLimitSwitchTEL.setChecked(!checked);
				if(!checked)
					btn_Notifications_LoadUniversalLimitSwitchTEL.setImageResource(R.drawable.btn_tel_enabled);
				else
					btn_Notifications_LoadUniversalLimitSwitchTEL.setImageResource(R.drawable.btn_tel_disabled);
				break;
			}

			case R.id.btn_Notifications_LowBatterySMS:
			{
				boolean checked = btn_Notifications_LowBatterySMS.isChecked();
				btn_Notifications_LowBatterySMS.setChecked(!checked);
				if(!checked)
					btn_Notifications_LowBatterySMS.setImageResource(R.drawable.btn_sms_enabled);
				else
					btn_Notifications_LowBatterySMS.setImageResource(R.drawable.btn_sms_disabled);
				break;
			}
//			case R.id.btn_Notifications_LowBatteryTEL:
//			{
//				boolean checked = btn_Notifications_LowBatteryTEL.isChecked();
//				btn_Notifications_LowBatteryTEL.setChecked(!checked);
//				if(!checked)
//					btn_Notifications_LowBatteryTEL.setImageResource(R.drawable.btn_tel_enabled);
//				else
//					btn_Notifications_LowBatteryTEL.setImageResource(R.drawable.btn_tel_disabled);
//				break;
//			}
			
			case R.id.btn_Notifications_GSM_SMS:
			{
				boolean checked = btn_Notifications_GSM_SMS.isChecked();
				btn_Notifications_GSM_SMS.setChecked(!checked);
				if(!checked)
					btn_Notifications_GSM_SMS.setImageResource(R.drawable.btn_sms_enabled);
				else
					btn_Notifications_GSM_SMS.setImageResource(R.drawable.btn_sms_disabled);
				break;
			}
//			case R.id.btn_Notifications_GSM_TEL:
//			{
//				boolean checked = btn_Notifications_GSM_TEL.isChecked();
//				btn_Notifications_GSM_TEL.setChecked(!checked);
//				if(!checked)
//					btn_Notifications_GSM_TEL.setImageResource(R.drawable.btn_tel_enabled);
//				else
//					btn_Notifications_GSM_TEL.setImageResource(R.drawable.btn_tel_disabled);
//				break;
//			}
			
			case R.id.btn_Notifications_SystemDisarmSMS:
			{
				boolean checked = btn_Notifications_SystemDisarmSMS.isChecked();
				btn_Notifications_SystemDisarmSMS.setChecked(!checked);
				if(!checked)
					btn_Notifications_SystemDisarmSMS.setImageResource(R.drawable.btn_sms_enabled);
				else
					btn_Notifications_SystemDisarmSMS.setImageResource(R.drawable.btn_sms_disabled);
				break;
			}
			case R.id.btn_Notifications_SystemDisarmTEL:
			{
				boolean checked = btn_Notifications_SystemDisarmTEL.isChecked();
				btn_Notifications_SystemDisarmTEL.setChecked(!checked);
				if(!checked)
					btn_Notifications_SystemDisarmTEL.setImageResource(R.drawable.btn_tel_enabled);
				else
					btn_Notifications_SystemDisarmTEL.setImageResource(R.drawable.btn_tel_disabled);
				break;
			}

			case R.id.btn_Notifications_SystemArmingSMS:
			{
				boolean checked = btn_Notifications_SystemArmingSMS.isChecked();
				btn_Notifications_SystemArmingSMS.setChecked(!checked);
				if(!checked)
					btn_Notifications_SystemArmingSMS.setImageResource(R.drawable.btn_sms_enabled);
				else
					btn_Notifications_SystemArmingSMS.setImageResource(R.drawable.btn_sms_disabled);
				break;
			}
			case R.id.btn_Notifications_SystemArmingTEL:
			{
				boolean checked = btn_Notifications_SystemArmingTEL.isChecked();
				btn_Notifications_SystemArmingTEL.setChecked(!checked);
				if(!checked)
					btn_Notifications_SystemArmingTEL.setImageResource(R.drawable.btn_tel_enabled);
				else
					btn_Notifications_SystemArmingTEL.setImageResource(R.drawable.btn_tel_disabled);
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
		setTitle(R.string.btn_Main_Notifications);
		//
		cb_Notifications_User.setText(R.string.cb_Notifications_User);
		TextView tv_Notifications_PhoneNumber = (TextView) findViewById(R.id.tv_Notifications_PhoneNumber);
		tv_Notifications_PhoneNumber.setText(R.string.tv_Notifications_PhoneNumber);
		TextView tv_Notifications_AllowChangeNotification = (TextView) findViewById(R.id.tv_Notifications_AllowChangeNotification);
		tv_Notifications_AllowChangeNotification.setText(R.string.tv_Notifications_AllowChangeNotification);
		TextView tv_Notifications_ReasonAlarmServiceNotifications = (TextView) findViewById(R.id.tv_Notifications_ReasonAlarmServiceNotifications);
		tv_Notifications_ReasonAlarmServiceNotifications.setText(R.string.tv_Notifications_ReasonAlarmServiceNotifications);
		TextView tv_Notifications_ReasonAlarmServiceNotificationsTEL = (TextView) findViewById(R.id.tv_Notifications_ReasonAlarmServiceNotificationsTEL);
		tv_Notifications_ReasonAlarmServiceNotificationsTEL.setText(R.string.cb_Notifications_TEL);
		TextView tv_Notifications_ReasonAlarmServiceNotificationsSMS = (TextView) findViewById(R.id.tv_Notifications_ReasonAlarmServiceNotificationsSMS);
		tv_Notifications_ReasonAlarmServiceNotificationsSMS.setText(R.string.cb_Notifications_SMS);
		TextView tv_Notifications_CallButtonPressed = (TextView) findViewById(R.id.tv_Notifications_CallButtonPressed);
		tv_Notifications_CallButtonPressed.setText(R.string.tv_Notifications_CallButtonPressed);
		TextView tv_Notifications_IngineActive = (TextView) findViewById(R.id.tv_Notifications_IngineActive);
		tv_Notifications_IngineActive.setText(R.string.tv_Notifications_IngineActive);
		TextView tv_Notifications_LoadLimitSwitch = (TextView) findViewById(R.id.tv_Notifications_LoadLimitSwitch);
		tv_Notifications_LoadLimitSwitch.setText(R.string.tv_Notifications_LoadLimitSwitch);
		TextView tv_Notifications_LoadShockSensor = (TextView) findViewById(R.id.tv_Notifications_LoadShockSensor);
		tv_Notifications_LoadShockSensor.setText(R.string.tv_Notifications_LoadShockSensor);
		TextView tv_Notifications_LoadUniversalLimitSwitch = (TextView) findViewById(R.id.tv_Notifications_LoadUniversalLimitSwitch);
		tv_Notifications_LoadUniversalLimitSwitch.setText(R.string.tv_Notifications_LoadUniversalLimitSwitch);
		TextView tv_Notifications_LowBattery = (TextView) findViewById(R.id.tv_Notifications_LowBattery);
		tv_Notifications_LowBattery.setText(R.string.tv_Notifications_LowBattery);
		TextView tv_Notifications_GSM = (TextView) findViewById(R.id.tv_Notifications_GSM);
		tv_Notifications_GSM.setText(R.string.tv_Notifications_GSM);
		TextView tv_Notifications_SystemDisarm = (TextView) findViewById(R.id.tv_Notifications_SystemDisarm);
		tv_Notifications_SystemDisarm.setText(R.string.tv_Notifications_SystemDisarm);
		TextView tv_Notifications_SystemArming = (TextView) findViewById(R.id.tv_Notifications_SystemArming);
		tv_Notifications_SystemArming.setText(R.string.tv_Notifications_SystemArming);
	}
	
	@Override
	public void finish() 
	{
	    super.finish();
	    instance = null;
	}
	
	@Override
	protected void onDestroy() {
		instance=null;
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
