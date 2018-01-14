package com.international.group.bat;



import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Pattern;

import stateholders.TextSMSStateHolder;
import utils.SMSGeneratorUtils;

public class TextSMS extends Activity implements OnClickListener, OnCheckedChangeListener
{
	private static final int MAX_CHARS_NUMBER = 111;
	Vector<String> m_messages = new Vector<String>();
	
	public static TextSMS instance = null;
	
	EditText et_TextSMS_TextSMS = null;
	EditText et_TextSMS_PhoneForSMS = null;
	Button btn_TextSMS_Send = null;
	Button btn_TextSMS_Save = null;
	CheckBox cb_TextSMS_ManualMode = null;
	
	//Generators
	static GeneratorQueryConfigurations m_generatorQueryConfigurations = null;
	static GeneratorUserSettings m_generatorUserSettings = null;
	static GeneratorInputs m_generatorInputs = null;
	static GeneratorOutputs m_generatorOutputs = null;
	static GeneratorTimer m_geneGeneratorTimer = null;
	static GeneratorNotifications m_generatorNorifications = null;
	static GeneratorCAN m_generatorCAN = null;
	
	//State holder
	static TextSMSStateHolder m_stateholder = null;
	
	//STRING: QUERY CONFIGURATIONS
	static final String QUERYCONFIGURATIONS_PHONE = "QUERYCONFIGURATIONS_PHONE";
	static final String QUERYCONFIGURATIONS_HARDWARECONFIGSYST = "QUERYCONFIGURATIONS_HARDWARECONFIGSYST";
	static final String QUERYCONFIGURATIONS_USERSYSTEMSETTINGS = "QUERYCONFIGURATIONS_USERSYSTEMSETTINGS";
	static final String QUERYCONFIGURATIONS_MONITORINGMODESETTINGS = "QUERYCONFIGURATIONS_MONITORINGMODESETTINGS";
	static final String QUERYCONFIGURATIONS_SUBSCRIBERNOTIFICATIONSETTINGS = "QUERYCONFIGURATIONS_SUBSCRIBERNOTIFICATIONSETTINGS";
	
	//STRING: USER SETTINGS
	static final String USERSETTINGS_CURRENTPIN = "USERSETTINGS_CURRENTPIN";
	static final String USERSETTINGS_NEWPIN = "USERSETTINGS_NEWPIN";
	static final String USERSETTINGS_BALANCECHECKINGNUMBER = "USERSETTINGS_BALANCECHECKINGNUMBER";
	static final String USERSETTINGS_ACCBALANCE = "USERSETTINGS_ACCBALANCE";
	static final String USERSETTINGS_NUMBERCALLATTEMPTS = "USERSETTINGS_NUMBERCALLATTEMPTS";
	static final String USERSETTINGS_SIREN = "USERSETTINGS_SIREN";
	static final String USERSETTINGS_DISARMSYSTEM = "USERSETTINGS_DISARMSYSTEM";
	static final String USERSETTINGS_ALLSYSTSENSORS = "USERSETTINGS_ALLSYSTSENSORS";
	static final String USERSETTINGS_SHOCKSENSSETTINGS = "USERSETTINGS_SHOCKSENSSETTINGS";
	static final String USERSETTINGS_TILTSENSOR = "USERSETTINGS_TILTSENSOR";
	static final String USERSETTINGS_PHONESENSITIVITY = "USERSETTINGS_PHONESENSITIVITY";
	static final String USERSETTINGS_SPEAKERVOLUME = "USERSETTINGS_SPEAKERVOLUME";
	static final String USERSETTINGS_SENSITIVITYLEVEL = "USERSETTINGS_SENSITIVITYLEVEL";
	static final String USERSETTINGS_LABELENTER = "USERSETTINGS_LABELENTER";
	static final String USERSETTINGS_LABELEXIT = "USERSETTINGS_LABELEXIT";
	static final String USERSETTINGS_LABELCONFIRMFUNCDISARMING = "USERSETTINGS_LABELCONFIRMFUNCDISARMING";
	static final String USERSETTINGS_SMSREPORTINGARMING = "USERSETTINGS_SMSREPORTINGARMING";
	static final String USERSETTINGS_MONITORINGSETTINGS = "USERSETTINGS_MONITORINGSETTINGS";
	static final String USERSETTINGS_ACCESSPOINTNUMBMOBOPER = "USERSETTINGS_ACCESSPOINTNUMBMOBOPER";
	static final String USERSETTINGS_REARM = "USERSETTINGS_REARM";
	//STRING: INPUTS
	static final String INPUTS_INPUT1 = "INPUTS_INPUT1";
	static final String INPUTS_INPUT2 = "INPUTS_INPUT2";
	static final String INPUTS_INPUT3 = "INPUTS_INPUT3";
	static final String INPUTS_INPUT4 = "INPUTS_INPUT4";
	static final String INPUTS_INPUT5 = "INPUTS_INPUT5";
	static final String INPUTS_INPUT6 = "INPUTS_INPUT6";
	static final String INPUTS_INPUT7 = "INPUTS_INPUT7";
	static final String INPUTS_INPUT8 = "INPUTS_INPUT8";
	//STRING: OUTPUTS
	static final String OUTPUTS_OUTPUT1 = "OUTPUTS_OUTPUT1";
	static final String OUTPUTS_OUTPUT2 = "OUTPUTS_OUTPUT2";
	static final String OUTPUTS_OUTPUT3 = "OUTPUTS_OUTPUT3";
	static final String OUTPUTS_OUTPUT4 = "OUTPUTS_OUTPUT4";
	static final String OUTPUTS_OUTPUT5 = "OUTPUTS_OUTPUT5";
	static final String OUTPUTS_OUTPUT6 = "OUTPUTS_OUTPUT6";
	//STRING: TIMER
	static final String TIMER_TIMER1 = "TIMER_TIMER1";
	static final String TIMER_TIMER2 = "TIMER_TIMER2";
	static final String TIMER_OUTPUTTIMERONARMMODE = "TIMER_OUTPUTTIMERONARMMODE";
	static final String TIMER_TURBOTIMER = "TIMER_TURBOTIMER";
	static final String TIMER_STARTTIMER = "TIMER_STARTTIMER";
	static final String TIMER_ARMTIMER = "TIMER_ARMTIMER";
	static final String TIMER_FUNCTION13 = "TIMER_FUNCTION13";
	static final String TIMER_FUNCTION14 = "TIMER_FUNCTION14";
	//STRING: NOTIFICATIONS
	static final String NOTIFICATIONS_USER1 = "NOTIFICATIONS_USER1";
	static final String NOTIFICATIONS_USER2 = "NOTIFICATIONS_USER2";
	static final String NOTIFICATIONS_USER3 = "NOTIFICATIONS_USER3";
	static final String NOTIFICATIONS_USER4 = "NOTIFICATIONS_USER4";
	static final String NOTIFICATIONS_USER5 = "NOTIFICATIONS_USER5";
	//STRING: CAN
	static final String CAN_SYSARMDISARM = "CAN_SYSARMDISARM";
	static final String CAN_IGNITION = "CAN_IGNITION";
	static final String CAN_DRIVECONTROL = "CAN_DRIVECONTROL";
	static final String CAN_COMFORTSIGNAL = "CAN_COMFORTSIGNAL";
	static final String CAN_MANAGSTAFFSEQURSYST = "CAN_MANAGSTAFFSEQURSYST";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{	
		//Create Activity
		super.onCreate(savedInstanceState);
		instance = this;
		setLanguage();
		setContentView(R.layout.text_sms);
		
		//Init memebers
		init();
		//Restore state
		restoreState();
		//Set Listeners
		setListeners();
		//Generate SMS
		generateSMS();
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
		setTitle(R.string.btn_Main_TextSMS);
		//
		TextView tv_TextSMS_PhoneForSMSSENDING = (TextView) findViewById(R.id.tv_TextSMS_PhoneForSMSSENDING);
		tv_TextSMS_PhoneForSMSSENDING.setText(R.string.tv_TextSMS_PhoneForSMSSENDING);
		btn_TextSMS_Send.setText(R.string.btn_TextSMS_Send);
		btn_TextSMS_Save.setText(R.string.btn_TextSMS_Save);
		cb_TextSMS_ManualMode.setText(R.string.cb_TextSMS_ManualMode);
	}
	
	@Override
	public void finish() 
	{
	    super.finish();
	}
	
	@Override
	protected void onPause() 
	{
		//Super onPause
		super.onPause();
		
		//Save Activity State
		saveState();
	}
	
	@Override
	protected void onDestroy() 
	{
		super.onDestroy();
	}

	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{
			case R.id.btn_TextSMS_Send:
			{
				handleSendSMS();
				break;
			}
			
			case R.id.btn_TextSMS_Save:
			{
				Intent intent = new Intent(this, SaveSettingPopup.class);
				intent.putExtra(SaveSettingPopup.PARENT_ACTIVITY_NAME, getClass().getName());
				startActivityForResult(intent, 0);
				break;
			}
		}
	}
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		et_TextSMS_TextSMS.setEnabled(isChecked);
		
	}
	
	private void init()
	{
		et_TextSMS_TextSMS = (EditText) findViewById(R.id.et_TextSMS_TextSMS);
		et_TextSMS_PhoneForSMS = (EditText) findViewById(R.id.et_TextSMS_PhoneForSMS);
		btn_TextSMS_Send = (Button) findViewById(R.id.btn_TextSMS_Send);
		btn_TextSMS_Save = (Button) findViewById(R.id.btn_TextSMS_Save);
		cb_TextSMS_ManualMode = (CheckBox) findViewById(R.id.cb_TextSMS_ManualMode);
	}
	
	private void setListeners()
	{
		btn_TextSMS_Send.setOnClickListener(this);
		btn_TextSMS_Save.setOnClickListener(this);
		cb_TextSMS_ManualMode.setOnCheckedChangeListener(this);
	}
	
	private void saveState()
	{
		if(m_stateholder == null)
			m_stateholder = MainActivity.stateholderTextSMS;
		
		//Messages
		m_stateholder.setMessages(m_messages);
		
		//Text SMS
		m_stateholder.setTextSMSEnabled(et_TextSMS_TextSMS.isEnabled());
		m_stateholder.setTextSMSValue(et_TextSMS_TextSMS.getText().toString());
		
		//Phone for SMS
		m_stateholder.setPhoneForSMSEnabled(et_TextSMS_PhoneForSMS.isEnabled());
		m_stateholder.setPhoneForSMSValue(et_TextSMS_PhoneForSMS.getText().toString());
		
		//Manual Mode
		m_stateholder.setManualModeEnabled(cb_TextSMS_ManualMode.isEnabled());
		m_stateholder.setManualModeChecked(cb_TextSMS_ManualMode.isChecked());
	}
	
	private void restoreState()
	{
		if (m_stateholder != null) 
		{
			//Messages
			m_messages.clear();
			m_messages = m_stateholder.getMessages();
			
			//TextSMS
			et_TextSMS_TextSMS.setEnabled(m_stateholder.getTextSMSEnabled());
			et_TextSMS_TextSMS.setText(m_stateholder.getTextSMSValue());
			
			//Phone for SMS
			et_TextSMS_PhoneForSMS.setEnabled(m_stateholder.getPhoneForSMSEnabled());
			et_TextSMS_PhoneForSMS.setText(m_stateholder.getPhoneForSMSValue());
			
			//Manual Mode
			cb_TextSMS_ManualMode.setEnabled(m_stateholder.getManualModemEnabled());
			cb_TextSMS_ManualMode.setChecked(m_stateholder.getManualModeChecked());
		}
	}
	
//============================================================= SMS GENERATION =============================================================
	private void sendSMS(String phoneNumber, String message)
    {        
		SmsManager sms = SmsManager.getDefault();
	    sms.sendTextMessage(phoneNumber, null, message, null, null);
    }
	
	private void handleSendSMS()
	{
		SMSGeneratorUtils util = new SMSGeneratorUtils();
		String phoneNumber = et_TextSMS_PhoneForSMS.getText().toString();
		switch(util.isPhoneNumberInCorrectFormat(phoneNumber))
		{
			case SMSGeneratorUtils.NO_ERROR:
			{
				//getStringForTextFiels().equals(et_TextSMS_TextSMS.getText().toString())
				if(!cb_TextSMS_ManualMode.isChecked())
				{
					for(int i = 0; i < m_messages.size(); ++i)
					{
						sendSMS(phoneNumber, m_messages.elementAt(i));
						Toast.makeText(this, "SMS " + (i+1) + " was sent", Toast.LENGTH_SHORT).show();
					}
				}
				else	//If text field was edited by user
				{
					Vector<String> messages = getMessagesFromEditedTextFields();
					for(int i = 0; i < messages.size(); ++i)
					{
						sendSMS(phoneNumber, messages.elementAt(i));
						Toast.makeText(this, "SMS " + (i+1) + " was sent", Toast.LENGTH_SHORT).show();
					}
				}
				break;
			}
			
			case SMSGeneratorUtils.ERR_PHONE_LEN:
			{
				Toast.makeText(this, "Phone number length does not match", Toast.LENGTH_SHORT).show();
				break;
			}
			
			case SMSGeneratorUtils.ERR_COUNTRY_NUM:
			{
				Toast.makeText(this, "Incorrect country number, should be: +380...", Toast.LENGTH_SHORT).show();
				break;
			}
		}
	}
	
	private void generateSMS()
	{
		if(m_generatorQueryConfigurations == null)
			m_generatorQueryConfigurations = MainActivity.generatorQueryConfigurations;
		if(m_generatorUserSettings == null)
			m_generatorUserSettings = MainActivity.generatorUserSettings;
		if(m_generatorInputs == null)
			m_generatorInputs = MainActivity.generatorInputs;
		if(m_generatorOutputs == null)
			m_generatorOutputs = MainActivity.generatorOutputs;
		if(m_geneGeneratorTimer == null)
			m_geneGeneratorTimer = MainActivity.generatorTimer;
		if(m_generatorNorifications == null)
			m_generatorNorifications = MainActivity.generatorNotifications;
		if(m_generatorCAN == null)
			m_generatorCAN = MainActivity.generatorCAN;
		
		Map<String, String> generatedMap = new HashMap<String, String>();
		
		generatedMap.putAll(m_generatorQueryConfigurations.getGeneratedMap());
		generatedMap.putAll(m_generatorUserSettings.getGeneratedMap());
		generatedMap.putAll(m_generatorInputs.getGeneratedMap());
		generatedMap.putAll(m_generatorOutputs.getGeneratedMap());
		generatedMap.putAll(m_geneGeneratorTimer.getGeneratedMap());
		generatedMap.putAll(m_generatorNorifications.getGeneratedMap());
		generatedMap.putAll(m_generatorCAN.getGeneratedMap());
		
		createMessagesFromMap(generatedMap);
	}
	
	private void createMessagesFromMap(Map<String, String> mapForGeneration)
	{
//PREFIX
		m_messages.clear();
		
		int localMaxCharNumber = MAX_CHARS_NUMBER;
		
		//PIN
		String prefix = "PIN:" + mapForGeneration.get(TextSMS.USERSETTINGS_CURRENTPIN);
		String currString = "";
		
		if(mapForGeneration.get(TextSMS.QUERYCONFIGURATIONS_PHONE).length() != 0)
		{
			prefix+=mapForGeneration.get(TextSMS.QUERYCONFIGURATIONS_PHONE);
			localMaxCharNumber+=mapForGeneration.get(TextSMS.QUERYCONFIGURATIONS_PHONE).length();
		}
		//
		if(mapForGeneration.get(TextSMS.QUERYCONFIGURATIONS_HARDWARECONFIGSYST).length() != 0)
		{
			m_messages.add(prefix+mapForGeneration.get(TextSMS.QUERYCONFIGURATIONS_HARDWARECONFIGSYST));
		}
		//
		if(mapForGeneration.get(TextSMS.QUERYCONFIGURATIONS_USERSYSTEMSETTINGS).length() != 0)
		{
			m_messages.add(prefix+mapForGeneration.get(TextSMS.QUERYCONFIGURATIONS_USERSYSTEMSETTINGS));
		}
		//
		if(mapForGeneration.get(TextSMS.QUERYCONFIGURATIONS_MONITORINGMODESETTINGS).length() != 0)
		{
			m_messages.add(prefix+mapForGeneration.get(TextSMS.QUERYCONFIGURATIONS_MONITORINGMODESETTINGS));
		}
		//
		if(mapForGeneration.get(TextSMS.QUERYCONFIGURATIONS_SUBSCRIBERNOTIFICATIONSETTINGS).length() != 0)
		{
			m_messages.add(prefix+mapForGeneration.get(TextSMS.QUERYCONFIGURATIONS_SUBSCRIBERNOTIFICATIONSETTINGS));
		}
		
//USER SETTINGS
		if( (currString.length()+mapForGeneration.get(TextSMS.USERSETTINGS_BALANCECHECKINGNUMBER).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.USERSETTINGS_BALANCECHECKINGNUMBER);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.USERSETTINGS_ACCBALANCE).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.USERSETTINGS_ACCBALANCE);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.USERSETTINGS_NUMBERCALLATTEMPTS).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.USERSETTINGS_NUMBERCALLATTEMPTS);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.USERSETTINGS_SIREN).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.USERSETTINGS_SIREN);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.USERSETTINGS_DISARMSYSTEM).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.USERSETTINGS_DISARMSYSTEM);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.USERSETTINGS_ALLSYSTSENSORS).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.USERSETTINGS_ALLSYSTSENSORS);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.USERSETTINGS_SHOCKSENSSETTINGS).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.USERSETTINGS_SHOCKSENSSETTINGS);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.USERSETTINGS_TILTSENSOR).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.USERSETTINGS_TILTSENSOR);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.USERSETTINGS_PHONESENSITIVITY).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.USERSETTINGS_PHONESENSITIVITY);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.USERSETTINGS_SPEAKERVOLUME).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.USERSETTINGS_SPEAKERVOLUME);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.USERSETTINGS_SENSITIVITYLEVEL).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.USERSETTINGS_SENSITIVITYLEVEL);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.USERSETTINGS_LABELENTER).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.USERSETTINGS_LABELENTER);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.USERSETTINGS_LABELEXIT).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.USERSETTINGS_LABELEXIT);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.USERSETTINGS_LABELCONFIRMFUNCDISARMING).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.USERSETTINGS_LABELCONFIRMFUNCDISARMING);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.USERSETTINGS_SMSREPORTINGARMING).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.USERSETTINGS_SMSREPORTINGARMING);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.USERSETTINGS_MONITORINGSETTINGS).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.USERSETTINGS_MONITORINGSETTINGS);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.USERSETTINGS_ACCESSPOINTNUMBMOBOPER).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.USERSETTINGS_ACCESSPOINTNUMBMOBOPER);
		//REARM
		if( (currString.length()+mapForGeneration.get(TextSMS.USERSETTINGS_REARM).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.USERSETTINGS_REARM);
		
//INPUTS
		if( (currString.length()+mapForGeneration.get(TextSMS.INPUTS_INPUT1).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.INPUTS_INPUT1);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.INPUTS_INPUT2).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.INPUTS_INPUT2);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.INPUTS_INPUT3).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.INPUTS_INPUT3);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.INPUTS_INPUT4).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.INPUTS_INPUT4);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.INPUTS_INPUT5).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.INPUTS_INPUT5);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.INPUTS_INPUT6).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.INPUTS_INPUT6);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.INPUTS_INPUT7).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.INPUTS_INPUT7);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.INPUTS_INPUT8).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.INPUTS_INPUT8);
		
//OUTPUTS
		if( (currString.length()+mapForGeneration.get(TextSMS.OUTPUTS_OUTPUT1).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.OUTPUTS_OUTPUT1);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.OUTPUTS_OUTPUT2).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.OUTPUTS_OUTPUT2);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.OUTPUTS_OUTPUT3).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.OUTPUTS_OUTPUT3);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.OUTPUTS_OUTPUT4).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.OUTPUTS_OUTPUT4);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.OUTPUTS_OUTPUT5).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.OUTPUTS_OUTPUT5);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.OUTPUTS_OUTPUT6).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.OUTPUTS_OUTPUT6);

//TIMER
		if( (currString.length()+mapForGeneration.get(TextSMS.TIMER_TIMER1).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.TIMER_TIMER1);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.TIMER_TIMER2).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.TIMER_TIMER2);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.TIMER_OUTPUTTIMERONARMMODE).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.TIMER_OUTPUTTIMERONARMMODE);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.TIMER_TURBOTIMER).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.TIMER_TURBOTIMER);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.TIMER_STARTTIMER).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.TIMER_STARTTIMER);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.TIMER_ARMTIMER).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.TIMER_ARMTIMER);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.TIMER_FUNCTION13).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.TIMER_FUNCTION13);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.TIMER_FUNCTION14).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.TIMER_FUNCTION14);
		
//NOTIFICATIONS
		if( (currString.length()+mapForGeneration.get(TextSMS.NOTIFICATIONS_USER1).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.NOTIFICATIONS_USER1);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.NOTIFICATIONS_USER2).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.NOTIFICATIONS_USER2);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.NOTIFICATIONS_USER3).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.NOTIFICATIONS_USER3);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.NOTIFICATIONS_USER4).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.NOTIFICATIONS_USER4);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.NOTIFICATIONS_USER5).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.NOTIFICATIONS_USER5);
		
//CAN
		if( (currString.length()+mapForGeneration.get(TextSMS.CAN_SYSARMDISARM).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.CAN_SYSARMDISARM);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.CAN_IGNITION).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.CAN_IGNITION);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.CAN_DRIVECONTROL).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.CAN_DRIVECONTROL);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.CAN_COMFORTSIGNAL).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.CAN_COMFORTSIGNAL);
		//
		if( (currString.length()+mapForGeneration.get(TextSMS.CAN_MANAGSTAFFSEQURSYST).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.CAN_MANAGSTAFFSEQURSYST);
		
//==================================================== USER SETTINGS: NEW PIN ==================================================
		if( (currString.length()+mapForGeneration.get(TextSMS.USERSETTINGS_NEWPIN).length()) > localMaxCharNumber)
		{
			m_messages.add(prefix+currString);
			currString = "";
		}
		currString+=mapForGeneration.get(TextSMS.USERSETTINGS_NEWPIN);
//FILL EDIT TEXT
		if(currString.length() != 0)
		{
			m_messages.add(prefix+currString);
		}
		
		
		if(cb_TextSMS_ManualMode.isChecked())
			et_TextSMS_TextSMS.setText(m_stateholder.getTextSMSValue());
		else
			et_TextSMS_TextSMS.setText(getStringForTextFiels());
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
	    
		if (resultCode == RESULT_OK) 
		{
			restoreState();
		}
		
		if(data != null)
		{
			boolean genSMS = data.getBooleanExtra("name", false);
		    if(genSMS)
		    {
		    	generateSMS();
		    }
		}
	}
	
	private String getStringForTextFiels()
	{
		String stringToStore = "";
		for(int i = 0; i < m_messages.size(); ++i)
		{
			stringToStore += (m_messages.elementAt(i) + '\n' + '\n');
		}
		return stringToStore;
	}
	
	private Vector<String> getMessagesFromEditedTextFields()
	{
		Vector<String> messages = new Vector<String>();
		String text = et_TextSMS_TextSMS.getText().toString();
		text = text.replace("\n", "");
		String[] tokens = text.split(Pattern.quote("PIN:"));
		
		int size = tokens.length;
		for(int i = 1; i < size; ++i)
		{
			messages.add("PIN:"+tokens[i]);
		}
		
		return messages;
	}
}
