package com.international.group.bat;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class LoadSettingPopup extends Activity implements OnClickListener
{
	String m_fileForLoading = "";
	ArrayList<TextView> m_allFiles = new ArrayList<TextView>();
	
	public static final String FOLDER_WITH_SETTINGS_NAME = "ConvoySMSSettings";
	public static final String LANGUAGE = "USER_LANGUAGE";
	public static final String PARENT_ACTIVITY_NAME = "PARENT_ACTIVITY_NAME";
	String nameOfParentActivity = null;

	TextView tv_LoadSettings_Header = null;
	TextView tv_LoadSettings_EnterNameForNewSettings = null;
	LinearLayout ll_LoadSettings_SavedSettings = null;
	Button btn_LoadSettings_Save = null;
	Button btn_LoadSettings_Cancel = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.load_settings_popup);
		
		nameOfParentActivity = getIntent().getStringExtra(PARENT_ACTIVITY_NAME);
		
		init();
		setListeners();
		setDisplayMetrics();
		loadFilesToTheList();

		setLanguage();
		updateUI();
	}

	@Override
	public void onClick(View v) 
	{
		//List of files
		TextView tvFileToLoad = (TextView) v;
		
		String name = v.getClass().getName();
		if(tvFileToLoad != null && name.equals("android.widget.TextView"))
		{
			for (TextView temp : m_allFiles) 
			{
				temp.setBackgroundColor(Color.parseColor("#ffffff"));
			}

			//Replace file names
			String fileToLoad = tvFileToLoad.getText().toString();
			if(fileToLoad.endsWith("(A)")) {
				fileToLoad = fileToLoad.replace("(A)", ".txt");
			}
			else if(fileToLoad.endsWith("(M)")) {
				fileToLoad = fileToLoad.replace("(M)", ".xml");
			}
			
			tvFileToLoad.setBackgroundColor(Color.parseColor("#4fa5d5"));
			m_fileForLoading = fileToLoad;
		}
		
		//Buttons
		switch(v.getId())
		{
			case R.id.btn_LoadSettings_Save:
			{
				loadSettings(m_fileForLoading);
				setResult(RESULT_OK);
		        finish();
				break;
			}
			case R.id.btn_LoadSettings_Cancel:
			{
				finish();
				break;
			}
		}
	}
	
	private void init()
	{
		ll_LoadSettings_SavedSettings = (LinearLayout) findViewById(R.id.ll_LoadSettings_SavedSettings);
		btn_LoadSettings_Save = (Button) findViewById(R.id.btn_LoadSettings_Save);
		btn_LoadSettings_Cancel = (Button) findViewById(R.id.btn_LoadSettings_Cancel);
		tv_LoadSettings_Header = (TextView) findViewById(R.id.tv_LoadSettings_Header);
		tv_LoadSettings_EnterNameForNewSettings = (TextView) findViewById(R.id.tv_LoadSettings_EnterNameForNewSettings);
	}
	
	private void setListeners()
	{
		btn_LoadSettings_Save.setOnClickListener(this);
		btn_LoadSettings_Cancel.setOnClickListener(this);
	}
	
	private void setDisplayMetrics()
	{
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		int width = dm.widthPixels;
		int height = dm.heightPixels;
		
		getWindow().setLayout((int)(width*1.0), (int)(height*1.0));
	}
	
	private void loadFilesToTheList()
	{
		String settingsStoragePath = Environment.getExternalStorageDirectory().toString()+"/"+FOLDER_WITH_SETTINGS_NAME;
		File settingsStorage = new File(settingsStoragePath);
		if(!settingsStorage.exists())
			return;
			
		File filesNames[] = settingsStorage.listFiles();
		for (int i=0; i < filesNames.length; i++)
		{
			String fileName = filesNames[i].getName().toString();
			boolean acceptFileCreation = true;
			acceptFileCreation &= !fileName.equals(MainActivity.DEFAULT_SETTINGS+".txt");
			acceptFileCreation &= !fileName.equals(MainActivity.FUNCTION_301+".txt");
			acceptFileCreation &= !fileName.equals(MainActivity.FUNCTION_302+".txt");
			acceptFileCreation &= !fileName.equals(MainActivity.FUNCTION_303+".txt");
			acceptFileCreation &= !fileName.equals(MainActivity.FUNCTION_304+".txt");
			acceptFileCreation &= !fileName.equals(MainActivity.FUNCTION_305+".txt");
			acceptFileCreation &= !fileName.equals(MainActivity.FUNCTION_306+".txt");
			acceptFileCreation &= !fileName.equals(MainActivity.STAND_USER_SETTINGS+".txt");
			
			boolean correctFormat = false;
			if(MainActivity.stateholderTextSMS.getManualModeChecked())
				correctFormat = fileName.endsWith(".xml");
			else
				correctFormat = fileName.endsWith(".txt");
			
			if(acceptFileCreation && correctFormat)
			{
				TextView view = new TextView(this);
				//Replace file names
				if(fileName.endsWith(".txt")) {
					fileName = fileName.replace(".txt", "(A)");
				}
				else if(fileName.endsWith(".xml")) {
					fileName = fileName.replace(".xml", "(M)");
				}
				view.setText(fileName);
				view.setTextSize(TypedValue.COMPLEX_UNIT_PX, 
				           getResources().getDimension(R.dimen.header_text_size));
				LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				int myMarginPx = (int) getResources().getDimension(R.dimen.settings_list_margin);
				params.setMargins(myMarginPx, myMarginPx, myMarginPx, myMarginPx);
				view.setLayoutParams(params);
				view.setPadding(myMarginPx, myMarginPx, myMarginPx, myMarginPx);
				view.setGravity(Gravity.CENTER);
				view.setBackgroundResource(R.drawable.tv_border);
				view.setOnClickListener(this);
				if(i == 8)
				{
					view.setBackgroundColor(Color.parseColor("#4fa5d5"));

					if(fileName.endsWith("(A)")) {
						fileName = fileName.replace("(A)", ".txt");
					}
					else if(fileName.endsWith("(M)")) {
						fileName = fileName.replace("(M)", ".xml");
					}

					m_fileForLoading = fileName;
				}
				
				m_allFiles.add(view);
				ll_LoadSettings_SavedSettings.addView(view);
			}
		}
	}
	
	static private String openTag(String str)
	{ return "<"+ str+ ">"; }
	
	static private String closedTag(String str)
	{ return "</"+ str+ ">"; }
	
	static private String substringBetween(String str, String open, String close) 
	{
	    if (str == null || open == null || close == null) 
	    {
	        return null;
	    }
	    int start = str.indexOf(open);
	    if (start != -1) 
	    {
	        int end = str.indexOf(close, start + open.length());
	        if (end != -1) 
	        {
	            return str.substring(start + open.length(), end);
	        }
	    }
	    return null;
	}
	
	static private String getValue(String source, String key)
	{
		return substringBetween(source.toString(), openTag(key), closedTag(key));
	}

	private void setLanguage() {
		String language = MainActivity.m_sharedPreferences.getString(ChangeLanguagePopup.LANGUAGE, "ru");
		Locale locale = new Locale(language);
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
	}

	private void updateUI() {
		tv_LoadSettings_Header.setText(R.string.tv_LoadPopup_Header);
		tv_LoadSettings_EnterNameForNewSettings.setText(R.string.tv_LoadPopup_SubHeader);
	}
	
	static public boolean loadSettings(String fileNameToLoad)
	{
		if(fileNameToLoad.length() == 0)
		{
			//Toast.makeText(this, "Nothing to load", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		String settingsStoragePath = Environment.getExternalStorageDirectory().toString()+"/"+FOLDER_WITH_SETTINGS_NAME;
		File settingsStorage = new File(settingsStoragePath);
		if(!settingsStorage.exists())
			return false;
		
		StringBuilder text = new StringBuilder();
		try 
		{
			File file = new File(settingsStorage, fileNameToLoad);
		    BufferedReader br = new BufferedReader(new FileReader(file));
		    String line;

		    while ((line = br.readLine()) != null) 
		    {
		        text.append(line);
		        text.append('\n');
		    }
		    br.close();
		}
		catch (IOException e) 
		{
		    //You'll need to add proper error handling here
		}
		
		if(MainActivity.stateholderTextSMS.getManualModeChecked())
		{
			MainActivity.stateholderTextSMS.setTextSMSValue(text.toString());
			return true;
		}
		
		String sourceString = text.toString();
//QUERY CONFIGURATIONS
		//1.Query configurations
		MainActivity.stateholderQueryConfigurations.setCbQueryConfigurationsEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key1)));
		MainActivity.stateholderQueryConfigurations.setCbQueryConfigurationsChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key2)));
		MainActivity.stateholderQueryConfigurations.setQueryConfigurationsEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key3)));
		MainActivity.stateholderQueryConfigurations.setQueryConfigurationsValue(getValue(sourceString, SaveSettingPopup.key4));
		//2.Hardware configuration system
		MainActivity.stateholderQueryConfigurations.setCbHardwareConfigSystEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key5)));
		MainActivity.stateholderQueryConfigurations.setCbHardwareConfigSystChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key6)));
		//3.User system settings
		MainActivity.stateholderQueryConfigurations.setCbUserSystSettingsEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key7)));
		MainActivity.stateholderQueryConfigurations.setCbUserSystSettingsChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key8)));
		//4.Monitoring mode settings
		MainActivity.stateholderQueryConfigurations.setCbMonitoringModeSettingsEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key605)));
		MainActivity.stateholderQueryConfigurations.setCbMonitoringModeSettingsChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key606)));
//USER SETTINGS
		//0) CURRENT PIN
		MainActivity.stateholderUserSettings.setCurrentPinEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key575)));
		MainActivity.stateholderUserSettings.setCurrentPinChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key576)));
		MainActivity.stateholderUserSettings.setEtCurrentPINEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key577)));
		MainActivity.stateholderUserSettings.setEtCurrentPINValue(getValue(sourceString, SaveSettingPopup.key578));
		//1) NEW PIN
		MainActivity.stateholderUserSettings.setPinEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key9)));
		MainActivity.stateholderUserSettings.setPinChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key10)));
		MainActivity.stateholderUserSettings.setEtPINEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key11)));
		MainActivity.stateholderUserSettings.setEtPINValue(getValue(sourceString, SaveSettingPopup.key12));
		//2) BALANCE CHECKING NUMBER
		MainActivity.stateholderUserSettings.setBalanceChekingNumberEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key13)));
		MainActivity.stateholderUserSettings.setBalanceChekingNumberChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key14)));
		MainActivity.stateholderUserSettings.setBalanceChekingNumberSpinEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key15)));
		MainActivity.stateholderUserSettings.setBalanceChekingNumberSpinValue(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key16)));
		MainActivity.stateholderUserSettings.setEtBalanceCheckingAnotherEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key17)));
		MainActivity.stateholderUserSettings.setEtBalanceCheckingAnotherValue(getValue(sourceString, SaveSettingPopup.key18));
		//3) BALANCE AUTO-CHECKING
		MainActivity.stateholderUserSettings.setCbAccBalanceEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key19)));
		MainActivity.stateholderUserSettings.setCbAccBalanceChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key20)));
		MainActivity.stateholderUserSettings.setEtNumSumPositionEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key21)));
		MainActivity.stateholderUserSettings.setEtNumSumPositionValue(getValue(sourceString, SaveSettingPopup.key22));
		MainActivity.stateholderUserSettings.setEtCriticalBalanceEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key23)));
		MainActivity.stateholderUserSettings.setEtCriticalBalanceValue(getValue(sourceString, SaveSettingPopup.key24));
		MainActivity.stateholderUserSettings.setEtBalanceCheckingFrequencyEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key25)));
		MainActivity.stateholderUserSettings.setEtBalanceCheckingFrequencyValue(getValue(sourceString, SaveSettingPopup.key26));
		//4) NUMBER OF CALL ATTEMPTS
		MainActivity.stateholderUserSettings.setNuberCallAttemptsEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key27)));
		MainActivity.stateholderUserSettings.setNuberCallAttemptsChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key28)));
		MainActivity.stateholderUserSettings.setEtNumberCallAttemptsEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key29)));
		MainActivity.stateholderUserSettings.setEtNumberCallAttemptsValue(getValue(sourceString, SaveSettingPopup.key30));
		//REARM
		MainActivity.stateholderUserSettings.setRearmEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key601)));
		MainActivity.stateholderUserSettings.setRearmChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key602)));
		MainActivity.stateholderUserSettings.setSpinRearmEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key603)));
		//MainActivity.stateholderUserSettings.setSpinRearmValue(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key604)));
		//5) SIREN
		MainActivity.stateholderUserSettings.setCbSirenEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key31)));
		MainActivity.stateholderUserSettings.setCbSirenChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key32)));
		MainActivity.stateholderUserSettings.setSpinSystemSetUnsetEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key33)));
		MainActivity.stateholderUserSettings.setSpinSystemSetUnsetValue(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key34)));
		MainActivity.stateholderUserSettings.setSpinAlarmModeEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key35)));
		MainActivity.stateholderUserSettings.setSpinAlarmModeValue(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key36)));
		//7) ALL SYSTEM SENSORS
		MainActivity.stateholderUserSettings.setCbAllSystemSensorsEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key37)));
		MainActivity.stateholderUserSettings.setCbAllSystemSensorsChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key38)));
		MainActivity.stateholderUserSettings.setSpinTiltSensorEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key39)));
		MainActivity.stateholderUserSettings.setSpinTiltSensorValue(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key40)));
		//2) ACCESS POINT NAME MOB OPER
		MainActivity.stateholderUserSettings.setAccessPointNameMobOperEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key41)));
		MainActivity.stateholderUserSettings.setAccessPointNameMobOperChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key42)));
		MainActivity.stateholderUserSettings.setAccessPointNameMobOperSpinEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key43)));
		MainActivity.stateholderUserSettings.setAccessPointNameMobOperSpinValue(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key44)));
		MainActivity.stateholderUserSettings.setEtAccessPointNameMobOperEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key45)));
		MainActivity.stateholderUserSettings.setEtAccessPointNameMobOperValue(getValue(sourceString, SaveSettingPopup.key46));
		//4) SENSOR IN SECURITY MODE
		MainActivity.stateholderUserSettings.setCbSensInSecurityModeEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key47)));
		MainActivity.stateholderUserSettings.setCbSensInSecurityModeChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key48)));
		MainActivity.stateholderUserSettings.setSpinSensInSecurityModeEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key49)));
		MainActivity.stateholderUserSettings.setSpinSensInSecurityModeValue(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key50)));
		//5) SHOCK SENSOR SETTINGS
		MainActivity.stateholderUserSettings.setCbShockSensSettingsEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key51)));
		MainActivity.stateholderUserSettings.setCbShockSensSettingsChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key52)));
		MainActivity.stateholderUserSettings.setCbWarningZoneDisabledEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key53)));
		MainActivity.stateholderUserSettings.setCbWarningZoneDisabledChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key54)));
		MainActivity.stateholderUserSettings.setSbWarningTrasholdEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key55)));
		MainActivity.stateholderUserSettings.setSbWarningTrasholdValue(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key56)));
		MainActivity.stateholderUserSettings.setEtWarningTrasholdEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key57)));
		MainActivity.stateholderUserSettings.setEtWarningTrasholdValue(getValue(sourceString, SaveSettingPopup.key58));
		MainActivity.stateholderUserSettings.setCbAlarmZoneDisabledEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key59)));
		MainActivity.stateholderUserSettings.setCbAlarmZoneDisabledChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key60)));
		MainActivity.stateholderUserSettings.setSbAlarmTrasholdEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key61)));
		MainActivity.stateholderUserSettings.setSbAlarmTrasholdValue(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key62)));
		MainActivity.stateholderUserSettings.setEtAlarmTrasholdEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key63)));
		MainActivity.stateholderUserSettings.setEtAlarmTrasholdValue(getValue(sourceString, SaveSettingPopup.key64));
		//6) TILT SENSOR
		MainActivity.stateholderUserSettings.setCbTiltSensorEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key65)));
		MainActivity.stateholderUserSettings.setCbTiltSensorChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key66)));
		MainActivity.stateholderUserSettings.setCbTiltSensorDisabledEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key67)));
		MainActivity.stateholderUserSettings.setCbTiltSensorDisabledChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key68)));
		MainActivity.stateholderUserSettings.setSbTiltSensorEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key69)));
		MainActivity.stateholderUserSettings.setSbTiltSensorValue(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key70)));
		MainActivity.stateholderUserSettings.setEtTiltSensorEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key71)));
		MainActivity.stateholderUserSettings.setEtTiltSensorValue(getValue(sourceString, SaveSettingPopup.key72));
		MainActivity.stateholderUserSettings.setCbTiltSensorInArmModeEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key73)));
		MainActivity.stateholderUserSettings.setTiltSensorInArmModeChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key74)));
		//7) MONITORING SETTINGS
		MainActivity.stateholderUserSettings.setCbMonitoringSettingsEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key75)));
		MainActivity.stateholderUserSettings.setCbMonitoringSettingsChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key76)));
		MainActivity.stateholderUserSettings.setMonitoringSettingsEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key77)));
		MainActivity.stateholderUserSettings.setMonitoringSettingsValue(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key78)));
		//8) DISARMING SYSTEM
		MainActivity.stateholderUserSettings.setCbDisarmingSystemEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key79)));
		MainActivity.stateholderUserSettings.setCbDisarmingSystemChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key80)));
		MainActivity.stateholderUserSettings.setSpinDisarmingSystemEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key81)));
		MainActivity.stateholderUserSettings.setSpinDisarmingSystemValue(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key82)));
		//9) MICROPHONE
		MainActivity.stateholderUserSettings.setCbPhoneSensitivityEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key83)));
		MainActivity.stateholderUserSettings.setCbPhoneSensitivityChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key84)));
		MainActivity.stateholderUserSettings.setCbPhoneSensitivityDisabledEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key85)));
		MainActivity.stateholderUserSettings.setCbPhoneSensitivityDisabledChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key86)));
		MainActivity.stateholderUserSettings.setSbPhoneSensitivityEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key87)));
		MainActivity.stateholderUserSettings.setSbPhoneSensitivityValue(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key88)));
		MainActivity.stateholderUserSettings.setEtPhoneSensitivityEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key89)));
		MainActivity.stateholderUserSettings.setEtPhoneSensitivityValue(getValue(sourceString, SaveSettingPopup.key90));
		//10) SPEAKER
		MainActivity.stateholderUserSettings.setCbSpeakerVolumeEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key91)));
		MainActivity.stateholderUserSettings.setCbSpeakerVolumeChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key92)));
		MainActivity.stateholderUserSettings.setCbSpeakerVolumeDisabledEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key93)));
		MainActivity.stateholderUserSettings.setCbSpeakerVolumeDisabledChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key94)));
		MainActivity.stateholderUserSettings.setSbSpeakerVolumeEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key95)));
		MainActivity.stateholderUserSettings.setSbSpeakerVolumeValue(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key96)));
		MainActivity.stateholderUserSettings.setEtSpeakerVolumeEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key97)));
		MainActivity.stateholderUserSettings.setEtSpeakerVolumeValue(getValue(sourceString, SaveSettingPopup.key98));
		//11) LABEL SETTINGS
		MainActivity.stateholderUserSettings.setCbLabelSettingsEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key607)));
		MainActivity.stateholderUserSettings.setCbLabelSettingsChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key608)));
		MainActivity.stateholderUserSettings.setCbLabelSettingsDisabledEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key609)));
		MainActivity.stateholderUserSettings.setCbLabelSettingsDisabledChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key610)));
		MainActivity.stateholderUserSettings.setSbLabelSettingsEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key611)));
		MainActivity.stateholderUserSettings.setSbLabelSettingsValue(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key612)));
		MainActivity.stateholderUserSettings.setEtLabelSettingsEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key613)));
		MainActivity.stateholderUserSettings.setEtLabelSettingsValue(getValue(sourceString, SaveSettingPopup.key614));
		//7) SMS MONITORING ARMING
		MainActivity.stateholderUserSettings.setCbSMSReportingArmingEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key615)));
		MainActivity.stateholderUserSettings.setCbSMSReportingArmingChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key616)));
		MainActivity.stateholderUserSettings.setSMSReportingArmingEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key617)));
		MainActivity.stateholderUserSettings.setSMSReportingArmingValue(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key618)));
//INPUTS
		//IN1
		MainActivity.stateholderInputs.setCbInputsIn1Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key99)));
		MainActivity.stateholderInputs.setCbInputsIn1Checked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key100)));
		MainActivity.stateholderInputs.setSpinInputFunctionsIN1Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key101)));
		MainActivity.stateholderInputs.setSpinInputFunctionsIN1Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key102)));
		MainActivity.stateholderInputs.setSpinPolarityIN1Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key103)));
		MainActivity.stateholderInputs.setSpinPolarityIN1Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key104)));
		MainActivity.stateholderInputs.setSpinInputIsActiveIN1Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key105)));
		MainActivity.stateholderInputs.setSpinInputIsActiveIN1Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key106)));
		MainActivity.stateholderInputs.setEtMinClosingTimeIN1Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key107)));
		MainActivity.stateholderInputs.setEtMinClosingTimeIN1Value(getValue(sourceString, SaveSettingPopup.key108));
		MainActivity.stateholderInputs.setEtMinOpeningTimeIN1Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key109)));
		MainActivity.stateholderInputs.setEtMinOpeningTimeIN1Value(getValue(sourceString, SaveSettingPopup.key110));
		//IN2
		MainActivity.stateholderInputs.setCbInputsIn2Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key111)));
		MainActivity.stateholderInputs.setCbInputsIn2Checked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key112)));
		MainActivity.stateholderInputs.setSpinInputFunctionsIN2Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key113)));
		MainActivity.stateholderInputs.setSpinInputFunctionsIN2Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key114)));
		MainActivity.stateholderInputs.setSpinPolarityIN2Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key115)));
		MainActivity.stateholderInputs.setSpinPolarityIN2Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key116)));
		MainActivity.stateholderInputs.setSpinInputIsActiveIN2Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key117)));
		MainActivity.stateholderInputs.setSpinInputIsActiveIN2Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key118)));
		MainActivity.stateholderInputs.setEtMinClosingTimeIN2Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key119)));
		MainActivity.stateholderInputs.setEtMinClosingTimeIN2Value(getValue(sourceString, SaveSettingPopup.key120));
		MainActivity.stateholderInputs.setEtMinOpeningTimeIN2Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key121)));
		MainActivity.stateholderInputs.setEtMinOpeningTimeIN2Value(getValue(sourceString, SaveSettingPopup.key122));
		//IN3
		MainActivity.stateholderInputs.setCbInputsIn3Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key123)));
		MainActivity.stateholderInputs.setCbInputsIn3Checked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key124)));
		MainActivity.stateholderInputs.setSpinInputFunctionsIN3Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key125)));
		MainActivity.stateholderInputs.setSpinInputFunctionsIN3Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key126)));
		MainActivity.stateholderInputs.setSpinPolarityIN3Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key127)));
		MainActivity.stateholderInputs.setSpinPolarityIN3Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key128)));
		MainActivity.stateholderInputs.setSpinInputIsActiveIN3Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key129)));
		MainActivity.stateholderInputs.setSpinInputIsActiveIN3Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key130)));
		MainActivity.stateholderInputs.setEtMinClosingTimeIN3Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key131)));
		MainActivity.stateholderInputs.setEtMinClosingTimeIN3Value(getValue(sourceString, SaveSettingPopup.key132));
		MainActivity.stateholderInputs.setEtMinOpeningTimeIN3Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key133)));
		MainActivity.stateholderInputs.setEtMinOpeningTimeIN3Value(getValue(sourceString, SaveSettingPopup.key134));
		//IN4
		MainActivity.stateholderInputs.setCbInputsIn4Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key135)));
		MainActivity.stateholderInputs.setCbInputsIn4Checked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key136)));
		MainActivity.stateholderInputs.setSpinInputFunctionsIN4Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key137)));
		MainActivity.stateholderInputs.setSpinInputFunctionsIN4Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key138)));
		MainActivity.stateholderInputs.setSpinPolarityIN4Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key139)));
		MainActivity.stateholderInputs.setSpinPolarityIN4Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key140)));
		MainActivity.stateholderInputs.setSpinInputIsActiveIN4Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key141)));
		MainActivity.stateholderInputs.setSpinInputIsActiveIN4Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key142)));
		MainActivity.stateholderInputs.setEtMinClosingTimeIN4Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key143)));
		MainActivity.stateholderInputs.setEtMinClosingTimeIN4Value(getValue(sourceString, SaveSettingPopup.key144));
		MainActivity.stateholderInputs.setEtMinOpeningTimeIN4Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key145)));
		MainActivity.stateholderInputs.setEtMinOpeningTimeIN4Value(getValue(sourceString, SaveSettingPopup.key146));
		//IN5
		MainActivity.stateholderInputs.setCbInputsIn5Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key147)));
		MainActivity.stateholderInputs.setCbInputsIn5Checked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key148)));
		MainActivity.stateholderInputs.setSpinInputFunctionsIN5Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key149)));
		MainActivity.stateholderInputs.setSpinInputFunctionsIN5Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key150)));
		MainActivity.stateholderInputs.setSpinPolarityIN5Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key151)));
		MainActivity.stateholderInputs.setSpinPolarityIN5Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key152)));
		MainActivity.stateholderInputs.setSpinInputIsActiveIN5Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key153)));
		MainActivity.stateholderInputs.setSpinInputIsActiveIN5Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key154)));
		MainActivity.stateholderInputs.setEtMinClosingTimeIN5Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key155)));
		MainActivity.stateholderInputs.setEtMinClosingTimeIN5Value(getValue(sourceString, SaveSettingPopup.key156));
		MainActivity.stateholderInputs.setEtMinOpeningTimeIN5Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key157)));
		MainActivity.stateholderInputs.setEtMinOpeningTimeIN5Value(getValue(sourceString, SaveSettingPopup.key158));
		//IN6
		MainActivity.stateholderInputs.setCbInputsIn6Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key159)));
		MainActivity.stateholderInputs.setCbInputsIn6Checked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key160)));
		MainActivity.stateholderInputs.setSpinInputFunctionsIN6Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key161)));
		MainActivity.stateholderInputs.setSpinInputFunctionsIN6Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key162)));
		MainActivity.stateholderInputs.setSpinPolarityIN6Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key163)));
		MainActivity.stateholderInputs.setSpinPolarityIN6Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key164)));
		MainActivity.stateholderInputs.setSpinInputIsActiveIN6Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key165)));
		MainActivity.stateholderInputs.setSpinInputIsActiveIN6Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key166)));
		MainActivity.stateholderInputs.setEtMinClosingTimeIN6Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key167)));
		MainActivity.stateholderInputs.setEtMinClosingTimeIN6Value(getValue(sourceString, SaveSettingPopup.key168));
		MainActivity.stateholderInputs.setEtMinOpeningTimeIN6Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key169)));
		MainActivity.stateholderInputs.setEtMinOpeningTimeIN6Value(getValue(sourceString, SaveSettingPopup.key170));
		//IN7
		MainActivity.stateholderInputs.setCbInputsIn7Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key171)));
		MainActivity.stateholderInputs.setCbInputsIn7Checked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key172)));
		MainActivity.stateholderInputs.setSpinInputFunctionsIN7Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key173)));
		MainActivity.stateholderInputs.setSpinInputFunctionsIN7Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key174)));
		MainActivity.stateholderInputs.setSpinPolarityIN7Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key175)));
		MainActivity.stateholderInputs.setSpinPolarityIN7Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key176)));
		MainActivity.stateholderInputs.setSpinInputIsActiveIN7Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key177)));
		MainActivity.stateholderInputs.setSpinInputIsActiveIN7Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key178)));
		MainActivity.stateholderInputs.setEtMinClosingTimeIN7Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key179)));
		MainActivity.stateholderInputs.setEtMinClosingTimeIN7Value(getValue(sourceString, SaveSettingPopup.key180));
		MainActivity.stateholderInputs.setEtMinOpeningTimeIN7Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key181)));
		MainActivity.stateholderInputs.setEtMinOpeningTimeIN7Value(getValue(sourceString, SaveSettingPopup.key182));
		//IN8
		MainActivity.stateholderInputs.setCbInputsIn8Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key183)));
		MainActivity.stateholderInputs.setCbInputsIn8Checked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key184)));
		MainActivity.stateholderInputs.setSpinInputFunctionsIN8Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key185)));
		MainActivity.stateholderInputs.setSpinInputFunctionsIN8Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key186)));
		MainActivity.stateholderInputs.setSpinPolarityIN8Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key187)));
		MainActivity.stateholderInputs.setSpinPolarityIN8Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key188)));
		MainActivity.stateholderInputs.setSpinInputIsActiveIN8Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key189)));
		MainActivity.stateholderInputs.setSpinInputIsActiveIN8Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key190)));
		MainActivity.stateholderInputs.setEtMinClosingTimeIN8Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key191)));
		MainActivity.stateholderInputs.setEtMinClosingTimeIN8Value(getValue(sourceString, SaveSettingPopup.key192));
		MainActivity.stateholderInputs.setEtMinOpeningTimeIN8Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key193)));
		MainActivity.stateholderInputs.setEtMinOpeningTimeIN8Value(getValue(sourceString, SaveSettingPopup.key194));
//OUTPUTS
		//OUT1
		MainActivity.stateholderOutputs.setCbOutputsOUT1Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key195)));
		MainActivity.stateholderOutputs.setCbOutputsOUT1Checked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key196)));
		MainActivity.stateholderOutputs.setSpinOutputFunctionsOUT11Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key197)));
		MainActivity.stateholderOutputs.setSpinOutputFunctionsOUT1Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key198)));
		//OUT2
		MainActivity.stateholderOutputs.setCbOutputsOUT2Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key199)));
		MainActivity.stateholderOutputs.setCbOutputsOUT2Checked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key200)));
		MainActivity.stateholderOutputs.setSpinOutputFunctionsOUT22Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key201)));
		MainActivity.stateholderOutputs.setSpinOutputFunctionsOUT2Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key202)));
		//OUT3
		MainActivity.stateholderOutputs.setCbOutputsOUT3Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key203)));
		MainActivity.stateholderOutputs.setCbOutputsOUT3Checked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key204)));
		MainActivity.stateholderOutputs.setSpinOutputFunctionsOUT33Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key205)));
		MainActivity.stateholderOutputs.setSpinOutputFunctionsOUT3Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key206)));
		//OUT4
		MainActivity.stateholderOutputs.setCbOutputsOUT4Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key207)));
		MainActivity.stateholderOutputs.setCbOutputsOUT4Checked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key208)));
		MainActivity.stateholderOutputs.setSpinOutputFunctionsOUT44Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key209)));
		MainActivity.stateholderOutputs.setSpinOutputFunctionsOUT4Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key210)));
		//OUT5
		MainActivity.stateholderOutputs.setCbOutputsOUT5Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key211)));
		MainActivity.stateholderOutputs.setCbOutputsOUT5Checked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key212)));
		MainActivity.stateholderOutputs.setSpinOutputFunctionsOUT55Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key213)));
		MainActivity.stateholderOutputs.setSpinOutputFunctionsOUT5Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key214)));
		//OUT6
		MainActivity.stateholderOutputs.setCbOutputsOUT6Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key215)));
		MainActivity.stateholderOutputs.setCbOutputsOUT6Checked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key216)));
		MainActivity.stateholderOutputs.setSpinOutputFunctionsOUT66Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key217)));
		MainActivity.stateholderOutputs.setSpinOutputFunctionsOUT6Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key218)));
//TIMERS
		//Channel1
		MainActivity.stateholderTimer.setCbUniversalChannel1Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key219)));
		MainActivity.stateholderTimer.setCbUniversalChannel1Checked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key220)));
		MainActivity.stateholderTimer.setCbArmingUC1Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key221)));
		MainActivity.stateholderTimer.setCbArmingUC1Checked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key222)));
		MainActivity.stateholderTimer.setCbSysDisarmUC1Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key223)));
		MainActivity.stateholderTimer.setCbSysDisarmUC1Checked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key224)));
		MainActivity.stateholderTimer.setCbIgnitionSwitchOnUC1Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key225)));
		MainActivity.stateholderTimer.setCbIgnitionSwitchOnUC1Checked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key226)));
		MainActivity.stateholderTimer.setCbIgnitiOffSwitchOffUC1Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key227)));
		MainActivity.stateholderTimer.setCbIgnitiOffSwitchOffUC1Checked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key228)));
		MainActivity.stateholderTimer.setCbCommandFromPhoneUC1Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key229)));
		MainActivity.stateholderTimer.setCbCommandFromPhoneUC1Checked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key230)));
		MainActivity.stateholderTimer.setArming_DelayUC1Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key231)));
		MainActivity.stateholderTimer.setArming_DelayUC1Value(getValue(sourceString, SaveSettingPopup.key232));
		MainActivity.stateholderTimer.setArming_OperationTimeUC1Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key233)));
		MainActivity.stateholderTimer.setArming_OperationTimeUC1Value(getValue(sourceString, SaveSettingPopup.key234));
		MainActivity.stateholderTimer.setSysDisarm_DelayUC1Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key235)));
		MainActivity.stateholderTimer.setSysDisarm_DelayUC1Value(getValue(sourceString, SaveSettingPopup.key236));
		MainActivity.stateholderTimer.setSysDisarm_OperationTimeUC1Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key237)));
		MainActivity.stateholderTimer.setSysDisarm_OperationTimeUC1Value(getValue(sourceString, SaveSettingPopup.key238));
		MainActivity.stateholderTimer.setIgnitionSwitchOn_DelayUC1Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key239)));
		MainActivity.stateholderTimer.setIgnitionSwitchOn_DelayUC1Value(getValue(sourceString, SaveSettingPopup.key240));
		MainActivity.stateholderTimer.setIgnitionSwitchOn_OperationTimeUC1Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key241)));
		MainActivity.stateholderTimer.setIgnitionSwitchOn_OperationTimeUC1Value(getValue(sourceString, SaveSettingPopup.key242));
		MainActivity.stateholderTimer.setIgnitionSwitchOff_DelayUC1Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key243)));
		MainActivity.stateholderTimer.setIgnitionSwitchOff_DelayUC1Value(getValue(sourceString, SaveSettingPopup.key244));
		MainActivity.stateholderTimer.setIgnitionSwitchOff_OperationTimeUC1Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key245)));
		MainActivity.stateholderTimer.setIgnitionSwitchOff_OperationTimeUC1Value(getValue(sourceString, SaveSettingPopup.key246));
		MainActivity.stateholderTimer.setCommandFromPhone_DelayUC1Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key247)));
		MainActivity.stateholderTimer.setCommandFromPhone_DelayUC1Value(getValue(sourceString, SaveSettingPopup.key248));
		MainActivity.stateholderTimer.setCommandFromPhone_OperationTimeUC1Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key249)));
		MainActivity.stateholderTimer.setCommandFromPhone_OperationTimeUC1Value(getValue(sourceString, SaveSettingPopup.key250));
		//Channel2
		MainActivity.stateholderTimer.setCbUniversalChannel2Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key251)));
		MainActivity.stateholderTimer.setCbUniversalChannel2Checked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key252)));
		MainActivity.stateholderTimer.setCbArmingUC2Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key253)));
		MainActivity.stateholderTimer.setCbArmingUC2Checked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key254)));
		MainActivity.stateholderTimer.setCbSysDisarmUC2Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key255)));
		MainActivity.stateholderTimer.setCbSysDisarmUC2Checked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key256)));
		MainActivity.stateholderTimer.setCbIgnitionSwitchOnUC2Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key257)));
		MainActivity.stateholderTimer.setCbIgnitionSwitchOnUC2Checked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key258)));
		MainActivity.stateholderTimer.setCbIgnitiOffSwitchOffUC2Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key259)));
		MainActivity.stateholderTimer.setCbIgnitiOffSwitchOffUC2Checked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key260)));
		MainActivity.stateholderTimer.setCbCommandFromPhoneUC2Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key261)));
		MainActivity.stateholderTimer.setCbCommandFromPhoneUC2Checked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key262)));
		MainActivity.stateholderTimer.setArming_DelayUC2Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key263)));
		MainActivity.stateholderTimer.setArming_DelayUC2Value(getValue(sourceString, SaveSettingPopup.key264));
		MainActivity.stateholderTimer.setArming_OperationTimeUC2Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key265)));
		MainActivity.stateholderTimer.setArming_OperationTimeUC2Value(getValue(sourceString, SaveSettingPopup.key266));
		MainActivity.stateholderTimer.setSysDisarm_DelayUC2Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key267)));
		MainActivity.stateholderTimer.setSysDisarm_DelayUC2Value(getValue(sourceString, SaveSettingPopup.key268));
		MainActivity.stateholderTimer.setSysDisarm_OperationTimeUC2Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key269)));
		MainActivity.stateholderTimer.setSysDisarm_OperationTimeUC2Value(getValue(sourceString, SaveSettingPopup.key270));
		MainActivity.stateholderTimer.setIgnitionSwitchOn_DelayUC2Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key271)));
		MainActivity.stateholderTimer.setIgnitionSwitchOn_DelayUC2Value(getValue(sourceString, SaveSettingPopup.key272));
		MainActivity.stateholderTimer.setIgnitionSwitchOn_OperationTimeUC2Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key273)));
		MainActivity.stateholderTimer.setIgnitionSwitchOn_OperationTimeUC2Value(getValue(sourceString, SaveSettingPopup.key274));
		MainActivity.stateholderTimer.setIgnitionSwitchOff_DelayUC2Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key275)));
		MainActivity.stateholderTimer.setIgnitionSwitchOff_DelayUC2Value(getValue(sourceString, SaveSettingPopup.key276));
		MainActivity.stateholderTimer.setIgnitionSwitchOff_OperationTimeUC2Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key277)));
		MainActivity.stateholderTimer.setIgnitionSwitchOff_OperationTimeUC2Value(getValue(sourceString, SaveSettingPopup.key278));
		MainActivity.stateholderTimer.setCommandFromPhone_DelayUC2Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key279)));
		MainActivity.stateholderTimer.setCommandFromPhone_DelayUC2Value(getValue(sourceString, SaveSettingPopup.key280));
		MainActivity.stateholderTimer.setCommandFromPhone_OperationTimeUC2Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key281)));
		MainActivity.stateholderTimer.setCommandFromPhone_OperationTimeUC2Value(getValue(sourceString, SaveSettingPopup.key282));

		//Turbotimer
		MainActivity.stateholderTimer.setCbTurboTimerSettingsEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key619)));
		MainActivity.stateholderTimer.setCbTurboTimerSettingsChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key620)));
		MainActivity.stateholderTimer.setTurbotimerWorkingTimeEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key621)));
		MainActivity.stateholderTimer.setTurbotimerWorkingTimeValue(getValue(sourceString, SaveSettingPopup.key622));
		MainActivity.stateholderTimer.setIngnTimeLockedEngineEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key623)));
		MainActivity.stateholderTimer.setIngnTimeLockedEngineValue(getValue(sourceString, SaveSettingPopup.key624));

        //Starttimer
        MainActivity.stateholderTimer.setCbStarttimerEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key625)));
        MainActivity.stateholderTimer.setCbStarttimerChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key626)));
        MainActivity.stateholderTimer.setStarttimerEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key627)));
        MainActivity.stateholderTimer.setStarttimerValue(getValue(sourceString, SaveSettingPopup.key628));

        //Armtimer
        MainActivity.stateholderTimer.setCbArmtimerEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key629)));
        MainActivity.stateholderTimer.setCbArmtimerChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key630)));
        MainActivity.stateholderTimer.setArmtimerEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key631)));
        MainActivity.stateholderTimer.setArmtimerValue(getValue(sourceString, SaveSettingPopup.key632));

		//OutputTimerOnArmDelay
		MainActivity.stateholderTimer.setCbOutputTimerOnArmDelayEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key283)));
		MainActivity.stateholderTimer.setCbOutputTimerOnArmDelayChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key284)));
		MainActivity.stateholderTimer.setOutputTimerOnArmOperationTimeEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key285)));
		MainActivity.stateholderTimer.setOutputTimerOnArmOperationTimeValue(getValue(sourceString, SaveSettingPopup.key286));
		MainActivity.stateholderTimer.setOutputTimerOnArmDelayEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key287)));
		MainActivity.stateholderTimer.setOutputTimerOnArmDelayValue(getValue(sourceString, SaveSettingPopup.key288));

		//Function13
		MainActivity.stateholderTimer.setCbCentralLockTimer13Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key579)));
		MainActivity.stateholderTimer.setCbCentralLockTimer13Checked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key580)));
		MainActivity.stateholderTimer.setSpinLockImpulsCloseCL13Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key581)));
		MainActivity.stateholderTimer.setSpinLockImpulsCloseCL13Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key582)));
		MainActivity.stateholderTimer.setFirstImpulsLongtime13Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key583)));
		MainActivity.stateholderTimer.setFirstImpulsLongtime13Value(getValue(sourceString, SaveSettingPopup.key584));
		MainActivity.stateholderTimer.setPauseTimeBetwImpulses13Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key585)));
		MainActivity.stateholderTimer.setPauseTimeBetwImpulses13Value(getValue(sourceString, SaveSettingPopup.key586));
		MainActivity.stateholderTimer.setSecondImpulsLongtime13Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key587)));
		MainActivity.stateholderTimer.setSecondImpulsLongtime13Value(getValue(sourceString, SaveSettingPopup.key588));
		MainActivity.stateholderTimer.setPauseTimeAfterIngAndImpStart13Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key589)));
		MainActivity.stateholderTimer.setPauseTimeAfterIngAndImpStart13Value(getValue(sourceString, SaveSettingPopup.key590));
		//Function14
		MainActivity.stateholderTimer.setCbCentralLockTimer14Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key591)));
		MainActivity.stateholderTimer.setCbCentralLockTimer14Checked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key592)));
		MainActivity.stateholderTimer.setSpinLockImpulsCloseCL14Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key593)));
		MainActivity.stateholderTimer.setSpinLockImpulsCloseCL14Value(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key594)));
		MainActivity.stateholderTimer.setFirstImpulsLongtime14Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key595)));
		MainActivity.stateholderTimer.setFirstImpulsLongtime14Value(getValue(sourceString, SaveSettingPopup.key596));
		MainActivity.stateholderTimer.setPauseTimeBetwImpulses14Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key597)));
		MainActivity.stateholderTimer.setPauseTimeBetwImpulses14Value(getValue(sourceString, SaveSettingPopup.key598));
		MainActivity.stateholderTimer.setSecondImpulsLongtime14Enabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key599)));
		MainActivity.stateholderTimer.setSecondImpulsLongtime14Value(getValue(sourceString, SaveSettingPopup.key600));
//NOTIFICATIONS
		//User Enable checkbox
		MainActivity.stateholderNotification.setCbInputsInEnabled(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key311)));
		MainActivity.stateholderNotification.setCbInputsInChecked(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key312)));
		MainActivity.stateholderNotification.setCbInputsInEnabled(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key313)));
		MainActivity.stateholderNotification.setCbInputsInChecked(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key314)));
		MainActivity.stateholderNotification.setCbInputsInEnabled(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key315)));
		MainActivity.stateholderNotification.setCbInputsInChecked(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key316)));
		MainActivity.stateholderNotification.setCbInputsInEnabled(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key317)));
		MainActivity.stateholderNotification.setCbInputsInChecked(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key318)));
		MainActivity.stateholderNotification.setCbInputsInEnabled(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key319)));
		MainActivity.stateholderNotification.setCbInputsInChecked(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key320)));
		//Phone number
		MainActivity.stateholderNotification.setEtPhoneNumberEnabled(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key321)));
		MainActivity.stateholderNotification.setEtPhoneNumberValue(0, getValue(sourceString, SaveSettingPopup.key322));
		MainActivity.stateholderNotification.setEtPhoneNumberEnabled(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key323)));
		MainActivity.stateholderNotification.setEtPhoneNumberValue(1, getValue(sourceString, SaveSettingPopup.key324));
		MainActivity.stateholderNotification.setEtPhoneNumberEnabled(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key325)));
		MainActivity.stateholderNotification.setEtPhoneNumberValue(2, getValue(sourceString, SaveSettingPopup.key326));
		MainActivity.stateholderNotification.setEtPhoneNumberEnabled(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key327)));
		MainActivity.stateholderNotification.setEtPhoneNumberValue(3, getValue(sourceString, SaveSettingPopup.key328));
		MainActivity.stateholderNotification.setEtPhoneNumberEnabled(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key329)));
		MainActivity.stateholderNotification.setEtPhoneNumberValue(4, getValue(sourceString, SaveSettingPopup.key330));
		//Allow Notification SMS
		MainActivity.stateholderNotification.setCbAllowNotificationsSMSEnabled(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key331)));
		MainActivity.stateholderNotification.setCbAllowNotificationsSMSChecked(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key332)));
		MainActivity.stateholderNotification.setCbAllowNotificationsSMSEnabled(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key333)));
		MainActivity.stateholderNotification.setCbAllowNotificationsSMSChecked(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key334)));
		MainActivity.stateholderNotification.setCbAllowNotificationsSMSEnabled(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key335)));
		MainActivity.stateholderNotification.setCbAllowNotificationsSMSChecked(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key336)));
		MainActivity.stateholderNotification.setCbAllowNotificationsSMSEnabled(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key337)));
		MainActivity.stateholderNotification.setCbAllowNotificationsSMSChecked(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key338)));
		MainActivity.stateholderNotification.setCbAllowNotificationsSMSEnabled(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key339)));
		MainActivity.stateholderNotification.setCbAllowNotificationsSMSChecked(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key340)));
		//Allow Notification TEL
		MainActivity.stateholderNotification.setCbAllowNotificationsTELEnabled(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key341)));
		MainActivity.stateholderNotification.setCbAllowNotificationsTELChecked(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key342)));
		MainActivity.stateholderNotification.setCbAllowNotificationsTELEnabled(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key343)));
		MainActivity.stateholderNotification.setCbAllowNotificationsTELChecked(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key344)));
		MainActivity.stateholderNotification.setCbAllowNotificationsTELEnabled(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key345)));
		MainActivity.stateholderNotification.setCbAllowNotificationsTELChecked(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key346)));
		MainActivity.stateholderNotification.setCbAllowNotificationsTELEnabled(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key347)));
		MainActivity.stateholderNotification.setCbAllowNotificationsTELChecked(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key348)));
		MainActivity.stateholderNotification.setCbAllowNotificationsTELEnabled(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key349)));
		MainActivity.stateholderNotification.setCbAllowNotificationsTELChecked(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key350)));
		//Button Call button pressed SMS
		MainActivity.stateholderNotification.setBtnCallBtnPressedSMSEnabled(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key351)));
		MainActivity.stateholderNotification.setBtnCallBtnPressedSMSChecked(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key352)));
		MainActivity.stateholderNotification.setBtnCallBtnPressedSMSEnabled(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key353)));
		MainActivity.stateholderNotification.setBtnCallBtnPressedSMSChecked(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key354)));
		MainActivity.stateholderNotification.setBtnCallBtnPressedSMSEnabled(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key355)));
		MainActivity.stateholderNotification.setBtnCallBtnPressedSMSChecked(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key356)));
		MainActivity.stateholderNotification.setBtnCallBtnPressedSMSEnabled(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key357)));
		MainActivity.stateholderNotification.setBtnCallBtnPressedSMSChecked(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key358)));
		MainActivity.stateholderNotification.setBtnCallBtnPressedSMSEnabled(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key359)));
		MainActivity.stateholderNotification.setBtnCallBtnPressedSMSChecked(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key360)));
		//Button Call button pressed TEL
		MainActivity.stateholderNotification.setBtnCallBtnPressedTELEnabled(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key361)));
		MainActivity.stateholderNotification.setBtnCallBtnPressedTELChecked(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key362)));
		MainActivity.stateholderNotification.setBtnCallBtnPressedTELEnabled(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key363)));
		MainActivity.stateholderNotification.setBtnCallBtnPressedTELChecked(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key364)));
		MainActivity.stateholderNotification.setBtnCallBtnPressedTELEnabled(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key365)));
		MainActivity.stateholderNotification.setBtnCallBtnPressedTELChecked(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key366)));
		MainActivity.stateholderNotification.setBtnCallBtnPressedTELEnabled(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key367)));
		MainActivity.stateholderNotification.setBtnCallBtnPressedTELChecked(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key368)));
		MainActivity.stateholderNotification.setBtnCallBtnPressedTELEnabled(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key369)));
		MainActivity.stateholderNotification.setBtnCallBtnPressedTELChecked(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key370)));
		//Ingine Active SMS
		MainActivity.stateholderNotification.setBtnIngineActiveSMSEnabled(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key371)));
		MainActivity.stateholderNotification.setBtnIngineActiveSMSChecked(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key372)));
		MainActivity.stateholderNotification.setBtnIngineActiveSMSEnabled(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key373)));
		MainActivity.stateholderNotification.setBtnIngineActiveSMSChecked(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key374)));
		MainActivity.stateholderNotification.setBtnIngineActiveSMSEnabled(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key375)));
		MainActivity.stateholderNotification.setBtnIngineActiveSMSChecked(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key376)));
		MainActivity.stateholderNotification.setBtnIngineActiveSMSEnabled(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key377)));
		MainActivity.stateholderNotification.setBtnIngineActiveSMSChecked(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key378)));
		MainActivity.stateholderNotification.setBtnIngineActiveSMSEnabled(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key379)));
		MainActivity.stateholderNotification.setBtnIngineActiveSMSChecked(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key380)));
		//Ingine Active TEL
		MainActivity.stateholderNotification.setBtnIngineActiveTELEnabled(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key381)));
		MainActivity.stateholderNotification.setBtnIngineActiveTELChecked(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key382)));
		MainActivity.stateholderNotification.setBtnIngineActiveTELEnabled(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key383)));
		MainActivity.stateholderNotification.setBtnIngineActiveTELChecked(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key384)));
		MainActivity.stateholderNotification.setBtnIngineActiveTELEnabled(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key385)));
		MainActivity.stateholderNotification.setBtnIngineActiveTELChecked(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key386)));
		MainActivity.stateholderNotification.setBtnIngineActiveTELEnabled(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key387)));
		MainActivity.stateholderNotification.setBtnIngineActiveTELChecked(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key388)));
		MainActivity.stateholderNotification.setBtnIngineActiveTELEnabled(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key389)));
		MainActivity.stateholderNotification.setBtnIngineActiveTELChecked(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key390)));
		//Load Limit Switch SMS
		MainActivity.stateholderNotification.setBtnLoadLimitSwitchSMSEnabled(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key391)));
		MainActivity.stateholderNotification.setBtnLoadLimitSwitchSMSChecked(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key392)));
		MainActivity.stateholderNotification.setBtnLoadLimitSwitchSMSEnabled(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key393)));
		MainActivity.stateholderNotification.setBtnLoadLimitSwitchSMSChecked(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key394)));
		MainActivity.stateholderNotification.setBtnLoadLimitSwitchSMSEnabled(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key395)));
		MainActivity.stateholderNotification.setBtnLoadLimitSwitchSMSChecked(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key396)));
		MainActivity.stateholderNotification.setBtnLoadLimitSwitchSMSEnabled(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key397)));
		MainActivity.stateholderNotification.setBtnLoadLimitSwitchSMSChecked(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key398)));
		MainActivity.stateholderNotification.setBtnLoadLimitSwitchSMSEnabled(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key399)));
		MainActivity.stateholderNotification.setBtnLoadLimitSwitchSMSChecked(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key400)));
		//Load Limit Switch TEL
		MainActivity.stateholderNotification.setBtnLoadLimitSwitchTELEnabled(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key401)));
		MainActivity.stateholderNotification.setBtnLoadLimitSwitchTELChecked(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key402)));
		MainActivity.stateholderNotification.setBtnLoadLimitSwitchTELEnabled(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key403)));
		MainActivity.stateholderNotification.setBtnLoadLimitSwitchTELChecked(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key404)));
		MainActivity.stateholderNotification.setBtnLoadLimitSwitchTELEnabled(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key405)));
		MainActivity.stateholderNotification.setBtnLoadLimitSwitchTELChecked(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key406)));
		MainActivity.stateholderNotification.setBtnLoadLimitSwitchTELEnabled(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key407)));
		MainActivity.stateholderNotification.setBtnLoadLimitSwitchTELChecked(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key408)));
		MainActivity.stateholderNotification.setBtnLoadLimitSwitchTELEnabled(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key409)));
		MainActivity.stateholderNotification.setBtnLoadLimitSwitchTELChecked(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key410)));
		//Button Load Shock Sensor SMS
		MainActivity.stateholderNotification.setBtnLoadShockSensorSMSEnabled(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key411)));
		MainActivity.stateholderNotification.setBtnLoadShockSensorSMSChecked(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key412)));
		MainActivity.stateholderNotification.setBtnLoadShockSensorSMSEnabled(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key413)));
		MainActivity.stateholderNotification.setBtnLoadShockSensorSMSChecked(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key414)));
		MainActivity.stateholderNotification.setBtnLoadShockSensorSMSEnabled(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key415)));
		MainActivity.stateholderNotification.setBtnLoadShockSensorSMSChecked(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key416)));
		MainActivity.stateholderNotification.setBtnLoadShockSensorSMSEnabled(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key417)));
		MainActivity.stateholderNotification.setBtnLoadShockSensorSMSChecked(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key418)));
		MainActivity.stateholderNotification.setBtnLoadShockSensorSMSEnabled(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key419)));
		MainActivity.stateholderNotification.setBtnLoadShockSensorSMSChecked(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key420)));
		//Button Load Shock Sensor TEL
		MainActivity.stateholderNotification.setBtnLoadShockSensorTELEnabled(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key421)));
		MainActivity.stateholderNotification.setBtnLoadShockSensorTELChecked(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key422)));
		MainActivity.stateholderNotification.setBtnLoadShockSensorTELEnabled(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key423)));
		MainActivity.stateholderNotification.setBtnLoadShockSensorTELChecked(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key424)));
		MainActivity.stateholderNotification.setBtnLoadShockSensorTELEnabled(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key425)));
		MainActivity.stateholderNotification.setBtnLoadShockSensorTELChecked(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key426)));
		MainActivity.stateholderNotification.setBtnLoadShockSensorTELEnabled(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key427)));
		MainActivity.stateholderNotification.setBtnLoadShockSensorTELChecked(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key428)));
		MainActivity.stateholderNotification.setBtnLoadShockSensorTELEnabled(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key429)));
		MainActivity.stateholderNotification.setBtnLoadShockSensorTELChecked(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key430)));
		//Load Universal Limit Switch SMS
		MainActivity.stateholderNotification.setBtnLoadUniversalLimitSwitchSMSEnabled(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key451)));
		MainActivity.stateholderNotification.setBtnLoadUniversalLimitSwitchSMSChecked(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key452)));
		MainActivity.stateholderNotification.setBtnLoadUniversalLimitSwitchSMSEnabled(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key453)));
		MainActivity.stateholderNotification.setBtnLoadUniversalLimitSwitchSMSChecked(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key454)));
		MainActivity.stateholderNotification.setBtnLoadUniversalLimitSwitchSMSEnabled(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key455)));
		MainActivity.stateholderNotification.setBtnLoadUniversalLimitSwitchSMSChecked(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key456)));
		MainActivity.stateholderNotification.setBtnLoadUniversalLimitSwitchSMSEnabled(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key457)));
		MainActivity.stateholderNotification.setBtnLoadUniversalLimitSwitchSMSChecked(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key458)));
		MainActivity.stateholderNotification.setBtnLoadUniversalLimitSwitchSMSEnabled(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key459)));
		MainActivity.stateholderNotification.setBtnLoadUniversalLimitSwitchSMSChecked(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key460)));
		//Load Universal Limit Switch TEL
		MainActivity.stateholderNotification.setBtnLoadUniversalLimitSwitchTELEnabled(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key461)));
		MainActivity.stateholderNotification.setBtnLoadUniversalLimitSwitchTELChecked(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key462)));
		MainActivity.stateholderNotification.setBtnLoadUniversalLimitSwitchTELEnabled(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key463)));
		MainActivity.stateholderNotification.setBtnLoadUniversalLimitSwitchTELChecked(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key464)));
		MainActivity.stateholderNotification.setBtnLoadUniversalLimitSwitchTELEnabled(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key465)));
		MainActivity.stateholderNotification.setBtnLoadUniversalLimitSwitchTELChecked(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key466)));
		MainActivity.stateholderNotification.setBtnLoadUniversalLimitSwitchTELEnabled(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key467)));
		MainActivity.stateholderNotification.setBtnLoadUniversalLimitSwitchTELChecked(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key468)));
		MainActivity.stateholderNotification.setBtnLoadUniversalLimitSwitchTELEnabled(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key469)));
		MainActivity.stateholderNotification.setBtnLoadUniversalLimitSwitchTELChecked(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key470)));
		//Button Low Buttery SMS
		MainActivity.stateholderNotification.setBtnLowBatterySMSEnabled(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key471)));
		MainActivity.stateholderNotification.setBtnLowBatterySMSChecked(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key472)));
		MainActivity.stateholderNotification.setBtnLowBatterySMSEnabled(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key473)));
		MainActivity.stateholderNotification.setBtnLowBatterySMSChecked(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key474)));
		MainActivity.stateholderNotification.setBtnLowBatterySMSEnabled(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key475)));
		MainActivity.stateholderNotification.setBtnLowBatterySMSChecked(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key476)));
		MainActivity.stateholderNotification.setBtnLowBatterySMSEnabled(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key477)));
		MainActivity.stateholderNotification.setBtnLowBatterySMSChecked(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key478)));
		MainActivity.stateholderNotification.setBtnLowBatterySMSEnabled(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key479)));
		MainActivity.stateholderNotification.setBtnLowBatterySMSChecked(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key480)));
		//Button Low Buttery TEL
		MainActivity.stateholderNotification.setBtnLowBatteryTELEnabled(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key481)));
		MainActivity.stateholderNotification.setBtnLowBatteryTELChecked(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key482)));
		MainActivity.stateholderNotification.setBtnLowBatteryTELEnabled(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key483)));
		MainActivity.stateholderNotification.setBtnLowBatteryTELChecked(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key484)));
		MainActivity.stateholderNotification.setBtnLowBatteryTELEnabled(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key485)));
		MainActivity.stateholderNotification.setBtnLowBatteryTELChecked(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key486)));
		MainActivity.stateholderNotification.setBtnLowBatteryTELEnabled(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key487)));
		MainActivity.stateholderNotification.setBtnLowBatteryTELChecked(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key488)));
		MainActivity.stateholderNotification.setBtnLowBatteryTELEnabled(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key489)));
		MainActivity.stateholderNotification.setBtnLowBatteryTELChecked(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key490)));
		//GSM SMS
		MainActivity.stateholderNotification.setBtnGSMSMSEnabled(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key491)));
		MainActivity.stateholderNotification.setBtnGSMSMSChecked(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key492)));
		MainActivity.stateholderNotification.setBtnGSMSMSEnabled(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key493)));
		MainActivity.stateholderNotification.setBtnGSMSMSChecked(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key494)));
		MainActivity.stateholderNotification.setBtnGSMSMSEnabled(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key495)));
		MainActivity.stateholderNotification.setBtnGSMSMSChecked(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key496)));
		MainActivity.stateholderNotification.setBtnGSMSMSEnabled(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key497)));
		MainActivity.stateholderNotification.setBtnGSMSMSChecked(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key498)));
		MainActivity.stateholderNotification.setBtnGSMSMSEnabled(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key499)));
		MainActivity.stateholderNotification.setBtnGSMSMSChecked(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key500)));
		//GSM TEL
		MainActivity.stateholderNotification.setBtnGTELMSEnabled(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key501)));
		MainActivity.stateholderNotification.setBtnGTELMSChecked(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key502)));
		MainActivity.stateholderNotification.setBtnGTELMSEnabled(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key503)));
		MainActivity.stateholderNotification.setBtnGTELMSChecked(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key504)));
		MainActivity.stateholderNotification.setBtnGTELMSEnabled(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key505)));
		MainActivity.stateholderNotification.setBtnGTELMSChecked(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key506)));
		MainActivity.stateholderNotification.setBtnGTELMSEnabled(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key507)));
		MainActivity.stateholderNotification.setBtnGTELMSChecked(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key508)));
		MainActivity.stateholderNotification.setBtnGTELMSEnabled(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key509)));
		MainActivity.stateholderNotification.setBtnGTELMSChecked(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key510)));
		//Button System Disarm SMS
		MainActivity.stateholderNotification.setBtnSystemDisarmSMSEnabled(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key511)));
		MainActivity.stateholderNotification.setBtnSystemDisarmSMSChecked(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key512)));
		MainActivity.stateholderNotification.setBtnSystemDisarmSMSEnabled(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key513)));
		MainActivity.stateholderNotification.setBtnSystemDisarmSMSChecked(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key514)));
		MainActivity.stateholderNotification.setBtnSystemDisarmSMSEnabled(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key515)));
		MainActivity.stateholderNotification.setBtnSystemDisarmSMSChecked(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key516)));
		MainActivity.stateholderNotification.setBtnSystemDisarmSMSEnabled(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key517)));
		MainActivity.stateholderNotification.setBtnSystemDisarmSMSChecked(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key518)));
		MainActivity.stateholderNotification.setBtnSystemDisarmSMSEnabled(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key519)));
		MainActivity.stateholderNotification.setBtnSystemDisarmSMSChecked(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key520)));
		//Button System Disarm TEL
		MainActivity.stateholderNotification.setBtnSystemDisarmTELEnabled(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key521)));
		MainActivity.stateholderNotification.setBtnSystemDisarmTELChecked(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key522)));
		MainActivity.stateholderNotification.setBtnSystemDisarmTELEnabled(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key523)));
		MainActivity.stateholderNotification.setBtnSystemDisarmTELChecked(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key524)));
		MainActivity.stateholderNotification.setBtnSystemDisarmTELEnabled(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key525)));
		MainActivity.stateholderNotification.setBtnSystemDisarmTELChecked(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key526)));
		MainActivity.stateholderNotification.setBtnSystemDisarmTELEnabled(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key527)));
		MainActivity.stateholderNotification.setBtnSystemDisarmTELChecked(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key528)));
		MainActivity.stateholderNotification.setBtnSystemDisarmTELEnabled(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key529)));
		MainActivity.stateholderNotification.setBtnSystemDisarmTELChecked(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key530)));
		//Button System Arming SMS
		MainActivity.stateholderNotification.setBtnSystemArmingSMSEnabled(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key531)));
		MainActivity.stateholderNotification.setBtnSystemArmingSMSChecked(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key532)));
		MainActivity.stateholderNotification.setBtnSystemArmingSMSEnabled(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key533)));
		MainActivity.stateholderNotification.setBtnSystemArmingSMSChecked(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key534)));
		MainActivity.stateholderNotification.setBtnSystemArmingSMSEnabled(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key535)));
		MainActivity.stateholderNotification.setBtnSystemArmingSMSChecked(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key536)));
		MainActivity.stateholderNotification.setBtnSystemArmingSMSEnabled(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key537)));
		MainActivity.stateholderNotification.setBtnSystemArmingSMSChecked(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key538)));
		MainActivity.stateholderNotification.setBtnSystemArmingSMSEnabled(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key539)));
		MainActivity.stateholderNotification.setBtnSystemArmingSMSChecked(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key540)));
		//Button System Arming TEL
		MainActivity.stateholderNotification.setBtnSystemArmingTELEnabled(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key541)));
		MainActivity.stateholderNotification.setBtnSystemArmingTELChecked(0, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key542)));
		MainActivity.stateholderNotification.setBtnSystemArmingTELEnabled(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key543)));
		MainActivity.stateholderNotification.setBtnSystemArmingTELChecked(1, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key544)));
		MainActivity.stateholderNotification.setBtnSystemArmingTELEnabled(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key545)));
		MainActivity.stateholderNotification.setBtnSystemArmingTELChecked(2, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key546)));
		MainActivity.stateholderNotification.setBtnSystemArmingTELEnabled(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key547)));
		MainActivity.stateholderNotification.setBtnSystemArmingTELChecked(3, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key548)));
		MainActivity.stateholderNotification.setBtnSystemArmingTELEnabled(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key549)));
		MainActivity.stateholderNotification.setBtnSystemArmingTELChecked(4, Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key550)));
//CAN
		//1)
		MainActivity.stateholderCAN.setCbSystArmDisarmEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key551)));
		MainActivity.stateholderCAN.setCbSystArmDisarmChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key552)));
		MainActivity.stateholderCAN.setSpinSystArmDisarmEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key553)));
		MainActivity.stateholderCAN.setSpinSystArmDisarmValue(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key554)));
		//2)
		MainActivity.stateholderCAN.setCbIgnitionEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key555)));
		MainActivity.stateholderCAN.setCbIgnitionChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key556)));
		MainActivity.stateholderCAN.setSpinIgnitionEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key557)));
		MainActivity.stateholderCAN.setSpinIgnitionValue(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key558)));
		//3)
		MainActivity.stateholderCAN.setCbDriveControlEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key559)));
		MainActivity.stateholderCAN.setCbDriveControlChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key560)));
		MainActivity.stateholderCAN.setSpinDriveControlEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key561)));
		MainActivity.stateholderCAN.setSpinDriveControlValue(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key562)));
		//4)
		MainActivity.stateholderCAN.setCbComfortSignalEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key563)));
		MainActivity.stateholderCAN.setCbComfortSignalChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key564)));
		MainActivity.stateholderCAN.setSpinComfortSignalEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key565)));
		MainActivity.stateholderCAN.setSpinComfortSignalValue(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key566)));
		//5)
		MainActivity.stateholderCAN.setCbManagingStaffSecuritySystemEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key567)));
		MainActivity.stateholderCAN.setCbManagingStaffSecuritySystemChecked(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key568)));
		MainActivity.stateholderCAN.setSpinManagingStaffSecuritySystemEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key569)));
		MainActivity.stateholderCAN.setSpinManagingStaffSecuritySystemValue(Integer.valueOf(getValue(sourceString, SaveSettingPopup.key570)));
//TEXT SMS
		MainActivity.stateholderTextSMS.setTextSMSEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key571)));
		MainActivity.stateholderTextSMS.setTextSMSValue(getValue(sourceString, SaveSettingPopup.key572));
		MainActivity.stateholderTextSMS.setPhoneForSMSEnabled(Boolean.valueOf(getValue(sourceString, SaveSettingPopup.key573)));
		MainActivity.stateholderTextSMS.setPhoneForSMSValue(getValue(sourceString, SaveSettingPopup.key574));
		
		return true;
		//Toast.makeText(this, "Load settings: " + m_fileForLoading, Toast.LENGTH_SHORT).show();
	}
	
}
