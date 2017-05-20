package com.international.group.bat;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import stateholders.TimerStateHolder;
import utils.EditTextWatcherInt;

public class UniversalTimeChannels extends Activity implements OnCheckedChangeListener, OnFocusChangeListener, OnTouchListener
{
	public static UniversalTimeChannels instance = null;
	private static Spinner m_activeSpinner = null;
	public static final int RESULT_CODE_SPINNER = 12335;
	public static final String SPINNER_UTC = "com.international.group.bat.SPINNER_UTC";
	
	//1)Universal channel #1
	CheckBox cb_UTC_UniversalChannel1 = null;
	
	CheckBox cb_UTC_ArmingUC1 = null;
	CheckBox cb_UTC_SysDisarmUC1 = null;
	CheckBox cb_UTC_IgnitionSwitchOnUC1 = null;
	CheckBox cb_UTC_IgnitionSwitchOffUC1 = null;
	CheckBox cb_UTC_CommandFromPhoneUC1 = null;
	
	EditText et_UTC_Arming_DelayUC1 = null;
	EditText et_UTC_Arming_OperationTimeUC1 = null;
	EditText et_UTC_SysDisarm_DelayUC1 = null;
	EditText et_UTC_SysDisarm_OperationTimeUC1 = null;
	EditText et_UTC_IgnitionSwitchOn_DelayUC1 = null;
	EditText et_UTC_IgnitionSwitchOn_OperationTimeUC1 = null;
	EditText et_UTC_IgnitionSwitchOff_DelayUC1 = null;
	EditText et_UTC_IgnitionSwitchOff_OperationTimeUC1 = null;
	EditText et_UTC_CommandFromPhone_DelayUC1 = null;
	EditText et_UTC_CommandFromPhone_OperationTimeUC1 = null;
	
	//2)Universal channel #2
	CheckBox cb_UTC_UniversalChannel2 = null;
		
	CheckBox cb_UTC_ArmingUC2 = null;
	CheckBox cb_UTC_SysDisarmUC2 = null;
	CheckBox cb_UTC_IgnitionSwitchOnUC2 = null;
	CheckBox cb_UTC_IgnitionSwitchOffUC2 = null;
	CheckBox cb_UTC_CommandFromPhoneUC2 = null;
		
	EditText et_UTC_Arming_DelayUC2 = null;
	EditText et_UTC_Arming_OperationTimeUC2 = null;
	EditText et_UTC_SysDisarm_DelayUC2 = null;
	EditText et_UTC_SysDisarm_OperationTimeUC2 = null;
	EditText et_UTC_IgnitionSwitchOn_DelayUC2 = null;
	EditText et_UTC_IgnitionSwitchOn_OperationTimeUC2 = null;
	EditText et_UTC_IgnitionSwitchOff_DelayUC2 = null;
	EditText et_UTC_IgnitionSwitchOff_OperationTimeUC2 = null;
	EditText et_UTC_CommandFromPhone_DelayUC2 = null;
	EditText et_UTC_CommandFromPhone_OperationTimeUC2 = null;
	
	//3)
	CheckBox cb_UserSettings_InputTimerOnArming = null;
	EditText et_UTC_OutputTimerOnArmOperationTime = null;
	EditText et_UTC_OutputTimerOnArmDelay = null;
	
	//5) TURBO-TIMER
	CheckBox cb_UTC_TurboTimerSettings = null;
	EditText et_UTC_TurbotimerWorkingTime = null;
	EditText et_UTC_IngnTimeLockedEngine = null;

	//STARTTIMER
	CheckBox cb_UTC_Starttimer = null;
	EditText et_UTC_Starttimer = null;

	//ARMTIMER
	CheckBox cb_UTC_Armtimer = null;
	EditText et_UTC_Armtimer = null;

	//Function 13
	CheckBox cb_UTC_CentralLockTimer13 = null;
	TextView tv_UTC_LockImpulsCloseCL13 = null;
	Spinner spin_UTC_LockImpulsCloseCL13 = null;
	TextView tv_UTC_FirstImpulsLongtime13 = null;
	EditText et_UTC_FirstImpulsLongtime13 = null;
	TextView tv_UTC_PauseTimeBetwImpulses13 = null;
	EditText et_UTC_PauseTimeBetwImpulses13 = null;
	TextView tv_UTC_SecondImpulsLongtime13 = null;
	EditText et_UTC_SecondImpulsLongtime13 = null;
	TextView tv_UTC_PauseTimeAfterIngAndImpStart13 = null;
	EditText et_UTC_PauseTimeAfterIngAndImpStart13 = null;
	//Function 14
	CheckBox cb_UTC_CentralLockTimer14 = null;
	TextView tv_UTC_LockImpulsCloseCL14 = null;
	Spinner spin_UTC_LockImpulsCloseCL14 = null;
	TextView tv_UTC_FirstImpulsLongtime14 = null;
	EditText et_UTC_FirstImpulsLongtime14 = null;
	TextView tv_UTC_PauseTimeBetwImpulses14 = null;
	EditText et_UTC_PauseTimeBetwImpulses14 = null;
	TextView tv_UTC_SecondImpulsLongtime14 = null;
	EditText et_UTC_SecondImpulsLongtime14 = null;
	
	//State holder
	static TimerStateHolder m_stateholder = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		instance = this;
		setLanguage();
		setContentView(R.layout.universal_time_channels);
		
		initUniversalChannel1();
		initUniversalChannel2();
		initOutputTimerOnArmDelay();
		initStarttimer();
		iniArmtimer();
		initTurboTimerSettings();
		//initDelayTimeEngineStart();
		//initDelayTimeLimitSwitchesSurvey();
		setFunction13();
		setFunction14();
		
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
	
	private void initUniversalChannel1()
	{
		//---------------------------------------- INITIALIZATION ------------------------------------
		cb_UTC_UniversalChannel1 = (CheckBox) findViewById(R.id.cb_UTC_UniversalChannel1);
		
		cb_UTC_ArmingUC1 = (CheckBox) findViewById(R.id.cb_UTC_ArmingUC1);
		cb_UTC_SysDisarmUC1 = (CheckBox) findViewById(R.id.cb_UTC_SysDisarmUC1);
		cb_UTC_IgnitionSwitchOnUC1 = (CheckBox) findViewById(R.id.cb_UTC_IgnitionSwitchOnUC1);
		cb_UTC_IgnitionSwitchOffUC1 = (CheckBox) findViewById(R.id.cb_UTC_IgnitionSwitchOffUC1);
		cb_UTC_CommandFromPhoneUC1 = (CheckBox) findViewById(R.id.cb_UTC_CommandFromPhoneUC1);
		
		et_UTC_Arming_DelayUC1 = (EditText) findViewById(R.id.et_UTC_Arming_DelayUC1);
		et_UTC_Arming_OperationTimeUC1 = (EditText) findViewById(R.id.et_UTC_Arming_OperationTimeUC1);
		et_UTC_SysDisarm_DelayUC1 = (EditText) findViewById(R.id.et_UTC_SysDisarm_DelayUC1);
		et_UTC_SysDisarm_OperationTimeUC1 = (EditText) findViewById(R.id.et_UTC_SysDisarm_OperationTimeUC1);
		et_UTC_IgnitionSwitchOn_DelayUC1 = (EditText) findViewById(R.id.et_UTC_IgnitionSwitchOn_DelayUC1);
		et_UTC_IgnitionSwitchOn_OperationTimeUC1 = (EditText) findViewById(R.id.et_UTC_IgnitionSwitchOn_OperationTimeUC1);
		et_UTC_IgnitionSwitchOff_DelayUC1 = (EditText) findViewById(R.id.et_UTC_IgnitionSwitchOff_DelayUC1);
		et_UTC_IgnitionSwitchOff_OperationTimeUC1 = (EditText) findViewById(R.id.et_UTC_IgnitionSwitchOff_OperationTimeUC1);
		et_UTC_CommandFromPhone_DelayUC1 = (EditText) findViewById(R.id.et_UTC_CommandFromPhone_DelayUC1);
		et_UTC_CommandFromPhone_OperationTimeUC1 = (EditText) findViewById(R.id.et_UTC_CommandFromPhone_OperationTimeUC1);
		
		//---------------------------------------- SET VALUES ----------------------------------------
		et_UTC_Arming_DelayUC1.setText("0");
		et_UTC_Arming_OperationTimeUC1.setText("1");
		et_UTC_SysDisarm_DelayUC1.setText("0");
		et_UTC_SysDisarm_OperationTimeUC1.setText("1");
		et_UTC_IgnitionSwitchOn_DelayUC1.setText("0");
		et_UTC_IgnitionSwitchOn_OperationTimeUC1.setText("1");
		et_UTC_IgnitionSwitchOff_DelayUC1.setText("0");
		et_UTC_IgnitionSwitchOff_OperationTimeUC1.setText("1");
		et_UTC_CommandFromPhone_DelayUC1.setText("0");
		et_UTC_CommandFromPhone_OperationTimeUC1.setText("1");
		
		//---------------------------------------- SET STATES ----------------------------------------
		cb_UTC_ArmingUC1.setEnabled(false);
		cb_UTC_SysDisarmUC1.setEnabled(false);
		cb_UTC_IgnitionSwitchOnUC1.setEnabled(false);
		cb_UTC_IgnitionSwitchOffUC1.setEnabled(false);
		cb_UTC_CommandFromPhoneUC1.setEnabled(false);
		
		et_UTC_Arming_DelayUC1.setEnabled(false);
		et_UTC_Arming_OperationTimeUC1.setEnabled(false);
		et_UTC_SysDisarm_DelayUC1.setEnabled(false);
		et_UTC_SysDisarm_OperationTimeUC1.setEnabled(false);
		et_UTC_IgnitionSwitchOn_DelayUC1.setEnabled(false);
		et_UTC_IgnitionSwitchOn_OperationTimeUC1.setEnabled(false);
		et_UTC_IgnitionSwitchOff_DelayUC1.setEnabled(false);
		et_UTC_IgnitionSwitchOff_OperationTimeUC1.setEnabled(false);
		et_UTC_CommandFromPhone_DelayUC1.setEnabled(false);
		et_UTC_CommandFromPhone_OperationTimeUC1.setEnabled(false);
	}
	
	private void initUniversalChannel2()
	{
		//---------------------------------------- INITIALIZATION ------------------------------------
		cb_UTC_UniversalChannel2 = (CheckBox) findViewById(R.id.cb_UTC_UniversalChannel2);
		
		cb_UTC_ArmingUC2 = (CheckBox) findViewById(R.id.cb_UTC_ArmingUC2);
		cb_UTC_SysDisarmUC2 = (CheckBox) findViewById(R.id.cb_UTC_SysDisarmUC2);
		cb_UTC_IgnitionSwitchOnUC2 = (CheckBox) findViewById(R.id.cb_UTC_IgnitionSwitchOnUC2);
		cb_UTC_IgnitionSwitchOffUC2 = (CheckBox) findViewById(R.id.cb_UTC_IgnitionSwitchOffUC2);
		cb_UTC_CommandFromPhoneUC2 = (CheckBox) findViewById(R.id.cb_UTC_CommandFromPhoneUC2);
		
		et_UTC_Arming_DelayUC2 = (EditText) findViewById(R.id.et_UTC_Arming_DelayUC2);
		et_UTC_Arming_OperationTimeUC2 = (EditText) findViewById(R.id.et_UTC_Arming_OperationTimeUC2);
		et_UTC_SysDisarm_DelayUC2 = (EditText) findViewById(R.id.et_UTC_SysDisarm_DelayUC2);
		et_UTC_SysDisarm_OperationTimeUC2 = (EditText) findViewById(R.id.et_UTC_SysDisarm_OperationTimeUC2);
		et_UTC_IgnitionSwitchOn_DelayUC2 = (EditText) findViewById(R.id.et_UTC_IgnitionSwitchOn_DelayUC2);
		et_UTC_IgnitionSwitchOn_OperationTimeUC2 = (EditText) findViewById(R.id.et_UTC_IgnitionSwitchOn_OperationTimeUC2);
		et_UTC_IgnitionSwitchOff_DelayUC2 = (EditText) findViewById(R.id.et_UTC_IgnitionSwitchOff_DelayUC2);
		et_UTC_IgnitionSwitchOff_OperationTimeUC2 = (EditText) findViewById(R.id.et_UTC_IgnitionSwitchOff_OperationTimeUC2);
		et_UTC_CommandFromPhone_DelayUC2 = (EditText) findViewById(R.id.et_UTC_CommandFromPhone_DelayUC2);
		et_UTC_CommandFromPhone_OperationTimeUC2 = (EditText) findViewById(R.id.et_UTC_CommandFromPhone_OperationTimeUC2);
		
		//---------------------------------------- SET VALUES ----------------------------------------
		et_UTC_Arming_DelayUC2.setText("0");
		et_UTC_Arming_OperationTimeUC2.setText("1");
		et_UTC_SysDisarm_DelayUC2.setText("0");
		et_UTC_SysDisarm_OperationTimeUC2.setText("1");
		et_UTC_IgnitionSwitchOn_DelayUC2.setText("0");
		et_UTC_IgnitionSwitchOn_OperationTimeUC2.setText("1");
		et_UTC_IgnitionSwitchOff_DelayUC2.setText("0");
		et_UTC_IgnitionSwitchOff_OperationTimeUC2.setText("1");
		et_UTC_CommandFromPhone_DelayUC2.setText("0");
		et_UTC_CommandFromPhone_OperationTimeUC2.setText("1");
		
		//---------------------------------------- SET STATES ----------------------------------------
		cb_UTC_ArmingUC2.setEnabled(false);
		cb_UTC_SysDisarmUC2.setEnabled(false);
		cb_UTC_IgnitionSwitchOnUC2.setEnabled(false);
		cb_UTC_IgnitionSwitchOffUC2.setEnabled(false);
		cb_UTC_CommandFromPhoneUC2.setEnabled(false);
		
		et_UTC_Arming_DelayUC2.setEnabled(false);
		et_UTC_Arming_OperationTimeUC2.setEnabled(false);
		et_UTC_SysDisarm_DelayUC2.setEnabled(false);
		et_UTC_SysDisarm_OperationTimeUC2.setEnabled(false);
		et_UTC_IgnitionSwitchOn_DelayUC2.setEnabled(false);
		et_UTC_IgnitionSwitchOn_OperationTimeUC2.setEnabled(false);
		et_UTC_IgnitionSwitchOff_DelayUC2.setEnabled(false);
		et_UTC_IgnitionSwitchOff_OperationTimeUC2.setEnabled(false);
		et_UTC_CommandFromPhone_DelayUC2.setEnabled(false);
		et_UTC_CommandFromPhone_OperationTimeUC2.setEnabled(false);
	}
	
	private void initOutputTimerOnArmDelay()
	{
		cb_UserSettings_InputTimerOnArming = (CheckBox) findViewById(R.id.cb_UserSettings_InputTimerOnArming);
		et_UTC_OutputTimerOnArmOperationTime = (EditText) findViewById(R.id.et_UTC_OutputTimerOnArmOperationTime);
		et_UTC_OutputTimerOnArmDelay = (EditText) findViewById(R.id.et_UTC_OutputTimerOnArmDelay);
		
		et_UTC_OutputTimerOnArmOperationTime.setText("30");
		et_UTC_OutputTimerOnArmDelay.setText("0");
		et_UTC_OutputTimerOnArmOperationTime.setEnabled(false);
		et_UTC_OutputTimerOnArmDelay.setEnabled(false);
	}
	
	private void initStarttimer()
	{
		//---------------------------------------- INITIALIZATION ------------------------------------
		cb_UTC_Starttimer = (CheckBox) findViewById(R.id.cb_UTC_Starttimer);
		et_UTC_Starttimer = (EditText) findViewById(R.id.et_UTC_Starttimer);

		//---------------------------------------- SET VALUES ----------------------------------------
		et_UTC_Starttimer.setText("60");

		//---------------------------------------- SET STATES ----------------------------------------
		et_UTC_Starttimer.setEnabled(false);
	}

	private void iniArmtimer()
	{
		//---------------------------------------- INITIALIZATION ------------------------------------
		cb_UTC_Armtimer = (CheckBox) findViewById(R.id.cb_UTC_Armtimer);
		et_UTC_Armtimer = (EditText) findViewById(R.id.et_UTC_Armtimer);

		//---------------------------------------- SET VALUES ----------------------------------------
		et_UTC_Armtimer.setText("15");

		//---------------------------------------- SET STATES ----------------------------------------
		et_UTC_Armtimer.setEnabled(false);
	}
	
	private void initTurboTimerSettings()
	{
		//---------------------------------------- INITIALIZATION ------------------------------------
		cb_UTC_TurboTimerSettings = (CheckBox) findViewById(R.id.cb_UTC_TurboTimerSettings);
		et_UTC_TurbotimerWorkingTime = (EditText) findViewById(R.id.et_UTC_TurbotimerWorkingTime);
		et_UTC_IngnTimeLockedEngine = (EditText) findViewById(R.id.et_UTC_IngnTimeLockedEngine);

		//---------------------------------------- SET VALUES ----------------------------------------
		et_UTC_TurbotimerWorkingTime.setText("0");
		et_UTC_IngnTimeLockedEngine.setText("0");

		//---------------------------------------- SET STATES ----------------------------------------
		et_UTC_TurbotimerWorkingTime.setEnabled(false);
		et_UTC_IngnTimeLockedEngine.setEnabled(false);
	}
	
//	private void initDelayTimeEngineStart()
//	{
//		//---------------------------------------- INITIALIZATION ------------------------------------
//		cb_UTC_DelayTimeEngineStart = (CheckBox) findViewById(R.id.cb_UTC_DelayTimeEngineStart);
//		et_UTC_DelayTimeEngineStart = (EditText) findViewById(R.id.et_UTC_DelayTimeEngineStart);
//
//		//---------------------------------------- SET STATES ----------------------------------------
//		et_UTC_DelayTimeEngineStart.setEnabled(false);
//	}
	
//	private void initDelayTimeLimitSwitchesSurvey()
//	{
//		//---------------------------------------- INITIALIZATION ------------------------------------
//		cb_UTC_DelayTimeLimitSwitchesSurvey = (CheckBox) findViewById(R.id.cb_UTC_DelayTimeLimitSwitchesSurvey);
//		et_UTC_DelayTimeLimitSwitchesSurvey = (EditText) findViewById(R.id.et_UTC_DelayTimeLimitSwitchesSurvey);
//
//		//---------------------------------------- SET STATES ----------------------------------------
//		et_UTC_DelayTimeLimitSwitchesSurvey.setEnabled(false);
//	}

	private void setFunction13()
	{
		cb_UTC_CentralLockTimer13 = (CheckBox) findViewById(R.id.cb_UTC_CentralLockTimer13);

		tv_UTC_LockImpulsCloseCL13 = (TextView) findViewById(R.id.tv_UTC_LockImpulsCloseCL13);
		spin_UTC_LockImpulsCloseCL13 = (Spinner) findViewById(R.id.spin_UTC_LockImpulsCloseCL13);
		ArrayAdapter<String> function13 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.utc_function_13));
		spin_UTC_LockImpulsCloseCL13.setAdapter(function13);
		spin_UTC_LockImpulsCloseCL13.setSelection(0);

		tv_UTC_FirstImpulsLongtime13 = (TextView) findViewById(R.id.tv_UTC_FirstImpulsLongtime13);
		et_UTC_FirstImpulsLongtime13 = (EditText) findViewById(R.id.et_UTC_FirstImpulsLongtime13);
		tv_UTC_PauseTimeBetwImpulses13 = (TextView) findViewById(R.id.tv_UTC_PauseTimeBetwImpulses13);
		et_UTC_PauseTimeBetwImpulses13 = (EditText) findViewById(R.id.et_UTC_PauseTimeBetwImpulses13);
		tv_UTC_SecondImpulsLongtime13 = (TextView) findViewById(R.id.tv_UTC_SecondImpulsLongtime13);
		et_UTC_SecondImpulsLongtime13 = (EditText) findViewById(R.id.et_UTC_SecondImpulsLongtime13);
		tv_UTC_PauseTimeAfterIngAndImpStart13 = (TextView) findViewById(R.id.tv_UTC_PauseTimeAfterIngAndImpStart13);
		et_UTC_PauseTimeAfterIngAndImpStart13 = (EditText) findViewById(R.id.et_UTC_PauseTimeAfterIngAndImpStart13);
	}

	private void setFunction14()
	{
		cb_UTC_CentralLockTimer14 = (CheckBox) findViewById(R.id.cb_UTC_CentralLockTimer14);
		tv_UTC_LockImpulsCloseCL14 = (TextView) findViewById(R.id.tv_UTC_LockImpulsCloseCL14);
		spin_UTC_LockImpulsCloseCL14 = (Spinner) findViewById(R.id.spin_UTC_LockImpulsCloseCL14);
		ArrayAdapter<String> function14 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.utc_function_14));
		spin_UTC_LockImpulsCloseCL14.setAdapter(function14);
		spin_UTC_LockImpulsCloseCL14.setSelection(0);

		tv_UTC_FirstImpulsLongtime14 = (TextView) findViewById(R.id.tv_UTC_FirstImpulsLongtime14);
		et_UTC_FirstImpulsLongtime14 = (EditText) findViewById(R.id.et_UTC_FirstImpulsLongtime14);
		tv_UTC_PauseTimeBetwImpulses14 = (TextView) findViewById(R.id.tv_UTC_PauseTimeBetwImpulses14);
		et_UTC_PauseTimeBetwImpulses14 = (EditText) findViewById(R.id.et_UTC_PauseTimeBetwImpulses14);
		tv_UTC_SecondImpulsLongtime14 = (TextView) findViewById(R.id.tv_UTC_SecondImpulsLongtime14);
		et_UTC_SecondImpulsLongtime14 = (EditText) findViewById(R.id.et_UTC_SecondImpulsLongtime14);
	}
	
	private void setListeners()
	{
		//1) CHANNEL 1
		cb_UTC_UniversalChannel1.setOnCheckedChangeListener(this);
		cb_UTC_ArmingUC1.setOnCheckedChangeListener(this);
		cb_UTC_SysDisarmUC1.setOnCheckedChangeListener(this);
		cb_UTC_IgnitionSwitchOnUC1.setOnCheckedChangeListener(this);
		cb_UTC_IgnitionSwitchOffUC1.setOnCheckedChangeListener(this);
		cb_UTC_CommandFromPhoneUC1.setOnCheckedChangeListener(this);
		
		EditTextWatcherInt et_UTC_Arming_DelayUC1TextWatcher = new EditTextWatcherInt(et_UTC_Arming_DelayUC1, null, "0", "0", "255");
		et_UTC_Arming_DelayUC1.addTextChangedListener(et_UTC_Arming_DelayUC1TextWatcher);
		EditTextWatcherInt et_UTC_Arming_OperationTimeUC1TextWatcher = new EditTextWatcherInt(et_UTC_Arming_OperationTimeUC1, null, "1", "0", "255");
		et_UTC_Arming_OperationTimeUC1.addTextChangedListener(et_UTC_Arming_OperationTimeUC1TextWatcher);
		et_UTC_Arming_DelayUC1.setOnFocusChangeListener(this);
		et_UTC_Arming_OperationTimeUC1.setOnFocusChangeListener(this);
		//
		EditTextWatcherInt et_UTC_SysDisarm_DelayUC1TextWatcher = new EditTextWatcherInt(et_UTC_SysDisarm_DelayUC1, null, "0", "0", "255");
		et_UTC_SysDisarm_DelayUC1.addTextChangedListener(et_UTC_SysDisarm_DelayUC1TextWatcher);
		EditTextWatcherInt et_UTC_SysDisarm_OperationTimeUC1TextWatcher = new EditTextWatcherInt(et_UTC_SysDisarm_OperationTimeUC1, null, "1", "0", "255");
		et_UTC_SysDisarm_OperationTimeUC1.addTextChangedListener(et_UTC_SysDisarm_OperationTimeUC1TextWatcher);
		et_UTC_SysDisarm_DelayUC1.setOnFocusChangeListener(this);
		et_UTC_SysDisarm_OperationTimeUC1.setOnFocusChangeListener(this);
		//
		EditTextWatcherInt et_UTC_IgnitionSwitchOn_DelayUC1TextWatcher = new EditTextWatcherInt(et_UTC_IgnitionSwitchOn_DelayUC1, null, "0", "0", "255");
		et_UTC_IgnitionSwitchOn_DelayUC1.addTextChangedListener(et_UTC_IgnitionSwitchOn_DelayUC1TextWatcher);
		EditTextWatcherInt et_UTC_IgnitionSwitchOn_OperationTimeUC1TextWatcher = new EditTextWatcherInt(et_UTC_IgnitionSwitchOn_OperationTimeUC1, null, "1", "0", "255");
		et_UTC_IgnitionSwitchOn_OperationTimeUC1.addTextChangedListener(et_UTC_IgnitionSwitchOn_OperationTimeUC1TextWatcher);
		et_UTC_IgnitionSwitchOn_DelayUC1.setOnFocusChangeListener(this);
		et_UTC_IgnitionSwitchOn_OperationTimeUC1.setOnFocusChangeListener(this);
		//
		EditTextWatcherInt et_UTC_IgnitiOffSwitchOff_DelayUC1TextWatcher = new EditTextWatcherInt(et_UTC_IgnitionSwitchOff_DelayUC1, null, "0", "0", "255");
		et_UTC_IgnitionSwitchOff_DelayUC1.addTextChangedListener(et_UTC_IgnitiOffSwitchOff_DelayUC1TextWatcher);
		EditTextWatcherInt et_UTC_IgnitiOffSwitchOff_OperatiOffTimeUC1TextWatcher = new EditTextWatcherInt(et_UTC_IgnitionSwitchOff_OperationTimeUC1, null, "1", "0", "255");
		et_UTC_IgnitionSwitchOff_OperationTimeUC1.addTextChangedListener(et_UTC_IgnitiOffSwitchOff_OperatiOffTimeUC1TextWatcher);
		et_UTC_IgnitionSwitchOff_DelayUC1.setOnFocusChangeListener(this);
		et_UTC_IgnitionSwitchOff_OperationTimeUC1.setOnFocusChangeListener(this);
		//
		EditTextWatcherInt et_UTC_CommandFromPhone_DelayUC1TextWatcher = new EditTextWatcherInt(et_UTC_CommandFromPhone_DelayUC1, null, "0", "0", "255");
		et_UTC_CommandFromPhone_DelayUC1.addTextChangedListener(et_UTC_CommandFromPhone_DelayUC1TextWatcher);
		EditTextWatcherInt et_UTC_CommandFromPhone_OperationTimeUC1TextWatcher = new EditTextWatcherInt(et_UTC_CommandFromPhone_OperationTimeUC1, null, "1", "0", "255");
		et_UTC_CommandFromPhone_OperationTimeUC1.addTextChangedListener(et_UTC_CommandFromPhone_OperationTimeUC1TextWatcher);
		et_UTC_CommandFromPhone_DelayUC1.setOnFocusChangeListener(this);
		et_UTC_CommandFromPhone_OperationTimeUC1.setOnFocusChangeListener(this);
		
		//2) CHANNEL 2
		cb_UTC_UniversalChannel2.setOnCheckedChangeListener(this);
		cb_UTC_ArmingUC2.setOnCheckedChangeListener(this);
		cb_UTC_SysDisarmUC2.setOnCheckedChangeListener(this);
		cb_UTC_IgnitionSwitchOnUC2.setOnCheckedChangeListener(this);
		cb_UTC_IgnitionSwitchOffUC2.setOnCheckedChangeListener(this);
		cb_UTC_CommandFromPhoneUC2.setOnCheckedChangeListener(this);
		
		EditTextWatcherInt et_UTC_Arming_DelayUC2TextWatcher = new EditTextWatcherInt(et_UTC_Arming_DelayUC2, null, "0", "0", "255");
		et_UTC_Arming_DelayUC2.addTextChangedListener(et_UTC_Arming_DelayUC2TextWatcher);
		EditTextWatcherInt et_UTC_Arming_OperationTimeUC2TextWatcher = new EditTextWatcherInt(et_UTC_Arming_OperationTimeUC2, null, "1", "0", "255");
		et_UTC_Arming_OperationTimeUC2.addTextChangedListener(et_UTC_Arming_OperationTimeUC2TextWatcher);
		et_UTC_Arming_DelayUC2.setOnFocusChangeListener(this);
		et_UTC_Arming_OperationTimeUC2.setOnFocusChangeListener(this);
		//
		EditTextWatcherInt et_UTC_SysDisarm_DelayUC2TextWatcher = new EditTextWatcherInt(et_UTC_SysDisarm_DelayUC2, null, "0", "0", "255");
		et_UTC_SysDisarm_DelayUC2.addTextChangedListener(et_UTC_SysDisarm_DelayUC2TextWatcher);
		EditTextWatcherInt et_UTC_SysDisarm_OperationTimeUC2TextWatcher = new EditTextWatcherInt(et_UTC_SysDisarm_OperationTimeUC2, null, "1", "0", "255");
		et_UTC_SysDisarm_OperationTimeUC2.addTextChangedListener(et_UTC_SysDisarm_OperationTimeUC2TextWatcher);
		et_UTC_SysDisarm_DelayUC2.setOnFocusChangeListener(this);
		et_UTC_SysDisarm_OperationTimeUC2.setOnFocusChangeListener(this);
		//
		EditTextWatcherInt et_UTC_IgnitionSwitchOn_DelayUC2TextWatcher = new EditTextWatcherInt(et_UTC_IgnitionSwitchOn_DelayUC2, null, "0", "0", "255");
		et_UTC_IgnitionSwitchOn_DelayUC2.addTextChangedListener(et_UTC_IgnitionSwitchOn_DelayUC2TextWatcher);
		EditTextWatcherInt et_UTC_IgnitionSwitchOn_OperationTimeUC2TextWatcher = new EditTextWatcherInt(et_UTC_IgnitionSwitchOn_OperationTimeUC2, null, "1", "0", "255");
		et_UTC_IgnitionSwitchOn_OperationTimeUC2.addTextChangedListener(et_UTC_IgnitionSwitchOn_OperationTimeUC2TextWatcher);
		et_UTC_IgnitionSwitchOn_DelayUC2.setOnFocusChangeListener(this);
		et_UTC_IgnitionSwitchOn_OperationTimeUC2.setOnFocusChangeListener(this);
		//
		EditTextWatcherInt et_UTC_IgnitiOffSwitchOff_DelayUC2TextWatcher = new EditTextWatcherInt(et_UTC_IgnitionSwitchOff_DelayUC2, null, "0", "0", "255");
		et_UTC_IgnitionSwitchOff_DelayUC2.addTextChangedListener(et_UTC_IgnitiOffSwitchOff_DelayUC2TextWatcher);
		EditTextWatcherInt et_UTC_IgnitiOffSwitchOff_OperatiOffTimeUC2TextWatcher = new EditTextWatcherInt(et_UTC_IgnitionSwitchOff_OperationTimeUC2, null, "1", "0", "255");
		et_UTC_IgnitionSwitchOff_OperationTimeUC2.addTextChangedListener(et_UTC_IgnitiOffSwitchOff_OperatiOffTimeUC2TextWatcher);
		et_UTC_IgnitionSwitchOff_DelayUC2.setOnFocusChangeListener(this);
		et_UTC_IgnitionSwitchOff_OperationTimeUC2.setOnFocusChangeListener(this);
		//
		EditTextWatcherInt et_UTC_CommandFromPhone_DelayUC2TextWatcher = new EditTextWatcherInt(et_UTC_CommandFromPhone_DelayUC2, null, "0", "0", "255");
		et_UTC_CommandFromPhone_DelayUC2.addTextChangedListener(et_UTC_CommandFromPhone_DelayUC2TextWatcher);
		EditTextWatcherInt et_UTC_CommandFromPhone_OperationTimeUC2TextWatcher = new EditTextWatcherInt(et_UTC_CommandFromPhone_OperationTimeUC2, null, "1", "0", "255");
		et_UTC_CommandFromPhone_OperationTimeUC2.addTextChangedListener(et_UTC_CommandFromPhone_OperationTimeUC2TextWatcher);
		et_UTC_CommandFromPhone_DelayUC2.setOnFocusChangeListener(this);
		et_UTC_CommandFromPhone_OperationTimeUC2.setOnFocusChangeListener(this);
		
		//3) OUTPUT TIMER ON ARMING
		cb_UserSettings_InputTimerOnArming.setOnCheckedChangeListener(this);
		
		EditTextWatcherInt et_UTC_OutputTimerOnArmOperationTimeTextWatcher = new EditTextWatcherInt(et_UTC_OutputTimerOnArmOperationTime, null, "30", "0", "255");
		et_UTC_OutputTimerOnArmOperationTime.addTextChangedListener(et_UTC_OutputTimerOnArmOperationTimeTextWatcher);
		et_UTC_OutputTimerOnArmOperationTime.setOnFocusChangeListener(this);
		//
		EditTextWatcherInt et_UTC_OutputTimerOnArmDelayTextWatcher = new EditTextWatcherInt(et_UTC_OutputTimerOnArmDelay, null, "0", "0", "255");
		et_UTC_OutputTimerOnArmDelay.addTextChangedListener(et_UTC_OutputTimerOnArmDelayTextWatcher);
		et_UTC_OutputTimerOnArmDelay.setOnFocusChangeListener(this);
		
		//4) STARTTIMER
		cb_UTC_Starttimer.setOnCheckedChangeListener(this);

		EditTextWatcherInt et_UTC_StarttimerWatcher = new EditTextWatcherInt(et_UTC_Starttimer, null, "60", "0", "255");
		et_UTC_Starttimer.addTextChangedListener(et_UTC_StarttimerWatcher);
		et_UTC_Starttimer.setOnFocusChangeListener(this);

		//4) ARMTIMER
		cb_UTC_Armtimer.setOnCheckedChangeListener(this);
		EditTextWatcherInt et_UTC_ArmtimerWatcher = new EditTextWatcherInt(et_UTC_Armtimer, null, "15", "1", "255");
		et_UTC_Armtimer.addTextChangedListener(et_UTC_ArmtimerWatcher);
		et_UTC_Armtimer.setOnFocusChangeListener(this);


		//5) TURBO-TIMER
		cb_UTC_TurboTimerSettings.setOnCheckedChangeListener(this);

		EditTextWatcherInt et_UTC_TurbotimerWorkingTimeTextWatcher = new EditTextWatcherInt(et_UTC_TurbotimerWorkingTime, null, "0", "0", "255");
		et_UTC_TurbotimerWorkingTime.addTextChangedListener(et_UTC_TurbotimerWorkingTimeTextWatcher);
		et_UTC_TurbotimerWorkingTime.setOnFocusChangeListener(this);
		//
		EditTextWatcherInt et_UTC_IngnTimeLockedEngineTextWatcher = new EditTextWatcherInt(et_UTC_IngnTimeLockedEngine, null, "0", "0", "255");
		et_UTC_IngnTimeLockedEngine.addTextChangedListener(et_UTC_IngnTimeLockedEngineTextWatcher);
		et_UTC_IngnTimeLockedEngine.setOnFocusChangeListener(this);

		//Function 13
		cb_UTC_CentralLockTimer13.setOnCheckedChangeListener(this);
		spin_UTC_LockImpulsCloseCL13.setOnTouchListener(this);

		EditTextWatcherInt et_UTC_FirstImpulsLongtime13TextWatcher = new EditTextWatcherInt(et_UTC_FirstImpulsLongtime13, null, "1", "0", "255");
		et_UTC_FirstImpulsLongtime13.addTextChangedListener(et_UTC_FirstImpulsLongtime13TextWatcher);
		et_UTC_FirstImpulsLongtime13.setOnFocusChangeListener(this);

		EditTextWatcherInt et_UTC_PauseTimeBetwImpulses13TextWatcher = new EditTextWatcherInt(et_UTC_PauseTimeBetwImpulses13, null, "0", "0", "255");
		et_UTC_PauseTimeBetwImpulses13.addTextChangedListener(et_UTC_PauseTimeBetwImpulses13TextWatcher);
		et_UTC_PauseTimeBetwImpulses13.setOnFocusChangeListener(this);

		EditTextWatcherInt et_UTC_SecondImpulsLongtime13TextWatcher = new EditTextWatcherInt(et_UTC_SecondImpulsLongtime13, null, "0", "0", "255");
		et_UTC_SecondImpulsLongtime13.addTextChangedListener(et_UTC_SecondImpulsLongtime13TextWatcher);
		et_UTC_SecondImpulsLongtime13.setOnFocusChangeListener(this);

		EditTextWatcherInt et_UTC_PauseTimeAfterIngAndImpStart13TextWatcher = new EditTextWatcherInt(et_UTC_PauseTimeAfterIngAndImpStart13, null, "8", "0", "255");
		et_UTC_PauseTimeAfterIngAndImpStart13.addTextChangedListener(et_UTC_PauseTimeAfterIngAndImpStart13TextWatcher);
		et_UTC_PauseTimeAfterIngAndImpStart13.setOnFocusChangeListener(this);

		//Function 14
		cb_UTC_CentralLockTimer14.setOnCheckedChangeListener(this);
		spin_UTC_LockImpulsCloseCL14.setOnTouchListener(this);

		EditTextWatcherInt et_UTC_FirstImpulsLongtime14TextWatcher = new EditTextWatcherInt(et_UTC_FirstImpulsLongtime14, null, "1", "0", "255");
		et_UTC_FirstImpulsLongtime14.addTextChangedListener(et_UTC_FirstImpulsLongtime14TextWatcher);
		et_UTC_FirstImpulsLongtime14.setOnFocusChangeListener(this);

		EditTextWatcherInt et_UTC_PauseTimeBetwImpulses14TextWatcher = new EditTextWatcherInt(et_UTC_PauseTimeBetwImpulses14, null, "0", "0", "255");
		et_UTC_PauseTimeBetwImpulses14.addTextChangedListener(et_UTC_PauseTimeBetwImpulses14TextWatcher);
		et_UTC_PauseTimeBetwImpulses14.setOnFocusChangeListener(this);

		EditTextWatcherInt et_UTC_SecondImpulsLongtime14TextWatcher = new EditTextWatcherInt(et_UTC_SecondImpulsLongtime14, null, "0", "0", "255");
		et_UTC_SecondImpulsLongtime14.addTextChangedListener(et_UTC_SecondImpulsLongtime14TextWatcher);
		et_UTC_SecondImpulsLongtime14.setOnFocusChangeListener(this);
	}
	
	private void saveState()
	{
		if(m_stateholder == null)
			m_stateholder = MainActivity.stateholderTimer;
		
		//Channel1
		m_stateholder.setCbUniversalChannel1Enabled(cb_UTC_UniversalChannel1.isEnabled());
		m_stateholder.setCbUniversalChannel1Checked(cb_UTC_UniversalChannel1.isChecked());
		m_stateholder.setCbArmingUC1Enabled(cb_UTC_ArmingUC1.isEnabled());
		m_stateholder.setCbArmingUC1Checked(cb_UTC_ArmingUC1.isChecked());
		m_stateholder.setCbSysDisarmUC1Enabled(cb_UTC_SysDisarmUC1.isEnabled());
		m_stateholder.setCbSysDisarmUC1Checked(cb_UTC_SysDisarmUC1.isChecked());
		m_stateholder.setCbIgnitionSwitchOnUC1Enabled(cb_UTC_IgnitionSwitchOnUC1.isEnabled());
		m_stateholder.setCbIgnitionSwitchOnUC1Checked(cb_UTC_IgnitionSwitchOnUC1.isChecked());
		m_stateholder.setCbIgnitiOffSwitchOffUC1Enabled(cb_UTC_IgnitionSwitchOffUC1.isEnabled());
		m_stateholder.setCbIgnitiOffSwitchOffUC1Checked(cb_UTC_IgnitionSwitchOffUC1.isChecked());
		m_stateholder.setCbCommandFromPhoneUC1Enabled(cb_UTC_CommandFromPhoneUC1.isEnabled());
		m_stateholder.setCbCommandFromPhoneUC1Checked(cb_UTC_CommandFromPhoneUC1.isChecked());
		
		m_stateholder.setArming_DelayUC1Enabled(et_UTC_Arming_DelayUC1.isEnabled());
		m_stateholder.setArming_DelayUC1Value(et_UTC_Arming_DelayUC1.getText().toString());
		m_stateholder.setArming_OperationTimeUC1Enabled(et_UTC_Arming_OperationTimeUC1.isEnabled());
		m_stateholder.setArming_OperationTimeUC1Value(et_UTC_Arming_OperationTimeUC1.getText().toString());
		m_stateholder.setSysDisarm_DelayUC1Enabled(et_UTC_SysDisarm_DelayUC1.isEnabled());
		m_stateholder.setSysDisarm_DelayUC1Value(et_UTC_SysDisarm_DelayUC1.getText().toString());
		m_stateholder.setSysDisarm_OperationTimeUC1Enabled(et_UTC_SysDisarm_OperationTimeUC1.isEnabled());
		m_stateholder.setSysDisarm_OperationTimeUC1Value(et_UTC_SysDisarm_OperationTimeUC1.getText().toString());
		m_stateholder.setIgnitionSwitchOn_DelayUC1Enabled(et_UTC_IgnitionSwitchOn_DelayUC1.isEnabled());
		m_stateholder.setIgnitionSwitchOn_DelayUC1Value(et_UTC_IgnitionSwitchOn_DelayUC1.getText().toString());
		m_stateholder.setIgnitionSwitchOn_OperationTimeUC1Enabled(et_UTC_IgnitionSwitchOn_OperationTimeUC1.isEnabled());
		m_stateholder.setIgnitionSwitchOn_OperationTimeUC1Value(et_UTC_IgnitionSwitchOn_OperationTimeUC1.getText().toString());
		m_stateholder.setIgnitionSwitchOff_DelayUC1Enabled(et_UTC_IgnitionSwitchOff_DelayUC1.isEnabled());
		m_stateholder.setIgnitionSwitchOff_DelayUC1Value(et_UTC_IgnitionSwitchOff_DelayUC1.getText().toString());
		m_stateholder.setIgnitionSwitchOff_OperationTimeUC1Enabled(et_UTC_IgnitionSwitchOff_OperationTimeUC1.isEnabled());
		m_stateholder.setIgnitionSwitchOff_OperationTimeUC1Value(et_UTC_IgnitionSwitchOff_OperationTimeUC1.getText().toString());
		m_stateholder.setCommandFromPhone_DelayUC1Enabled(et_UTC_CommandFromPhone_DelayUC1.isEnabled());
		m_stateholder.setCommandFromPhone_DelayUC1Value(et_UTC_CommandFromPhone_DelayUC1.getText().toString());
		m_stateholder.setCommandFromPhone_OperationTimeUC1Enabled(et_UTC_CommandFromPhone_OperationTimeUC1.isEnabled());
		m_stateholder.setCommandFromPhone_OperationTimeUC1Value(et_UTC_CommandFromPhone_OperationTimeUC1.getText().toString());
		
		//Channel2
		m_stateholder.setCbUniversalChannel2Enabled(cb_UTC_UniversalChannel2.isEnabled());
		m_stateholder.setCbUniversalChannel2Checked(cb_UTC_UniversalChannel2.isChecked());
		m_stateholder.setCbArmingUC2Enabled(cb_UTC_ArmingUC2.isEnabled());
		m_stateholder.setCbArmingUC2Checked(cb_UTC_ArmingUC2.isChecked());
		m_stateholder.setCbSysDisarmUC2Enabled(cb_UTC_SysDisarmUC2.isEnabled());
		m_stateholder.setCbSysDisarmUC2Checked(cb_UTC_SysDisarmUC2.isChecked());
		m_stateholder.setCbIgnitionSwitchOnUC2Enabled(cb_UTC_IgnitionSwitchOnUC2.isEnabled());
		m_stateholder.setCbIgnitionSwitchOnUC2Checked(cb_UTC_IgnitionSwitchOnUC2.isChecked());
		m_stateholder.setCbIgnitiOffSwitchOffUC2Enabled(cb_UTC_IgnitionSwitchOffUC2.isEnabled());
		m_stateholder.setCbIgnitiOffSwitchOffUC2Checked(cb_UTC_IgnitionSwitchOffUC2.isChecked());
		m_stateholder.setCbCommandFromPhoneUC2Enabled(cb_UTC_CommandFromPhoneUC2.isEnabled());
		m_stateholder.setCbCommandFromPhoneUC2Checked(cb_UTC_CommandFromPhoneUC2.isChecked());
				
		m_stateholder.setArming_DelayUC2Enabled(et_UTC_Arming_DelayUC2.isEnabled());
		m_stateholder.setArming_DelayUC2Value(et_UTC_Arming_DelayUC2.getText().toString());
		m_stateholder.setArming_OperationTimeUC2Enabled(et_UTC_Arming_OperationTimeUC2.isEnabled());
		m_stateholder.setArming_OperationTimeUC2Value(et_UTC_Arming_OperationTimeUC2.getText().toString());
		m_stateholder.setSysDisarm_DelayUC2Enabled(et_UTC_SysDisarm_DelayUC2.isEnabled());
		m_stateholder.setSysDisarm_DelayUC2Value(et_UTC_SysDisarm_DelayUC2.getText().toString());
		m_stateholder.setSysDisarm_OperationTimeUC2Enabled(et_UTC_SysDisarm_OperationTimeUC2.isEnabled());
		m_stateholder.setSysDisarm_OperationTimeUC2Value(et_UTC_SysDisarm_OperationTimeUC2.getText().toString());
		m_stateholder.setIgnitionSwitchOn_DelayUC2Enabled(et_UTC_IgnitionSwitchOn_DelayUC2.isEnabled());
		m_stateholder.setIgnitionSwitchOn_DelayUC2Value(et_UTC_IgnitionSwitchOn_DelayUC2.getText().toString());
		m_stateholder.setIgnitionSwitchOn_OperationTimeUC2Enabled(et_UTC_IgnitionSwitchOn_OperationTimeUC2.isEnabled());
		m_stateholder.setIgnitionSwitchOn_OperationTimeUC2Value(et_UTC_IgnitionSwitchOn_OperationTimeUC2.getText().toString());
		m_stateholder.setIgnitionSwitchOff_DelayUC2Enabled(et_UTC_IgnitionSwitchOff_DelayUC2.isEnabled());
		m_stateholder.setIgnitionSwitchOff_DelayUC2Value(et_UTC_IgnitionSwitchOff_DelayUC2.getText().toString());
		m_stateholder.setIgnitionSwitchOff_OperationTimeUC2Enabled(et_UTC_IgnitionSwitchOff_OperationTimeUC2.isEnabled());
		m_stateholder.setIgnitionSwitchOff_OperationTimeUC2Value(et_UTC_IgnitionSwitchOff_OperationTimeUC2.getText().toString());
		m_stateholder.setCommandFromPhone_DelayUC2Enabled(et_UTC_CommandFromPhone_DelayUC2.isEnabled());
		m_stateholder.setCommandFromPhone_DelayUC2Value(et_UTC_CommandFromPhone_DelayUC2.getText().toString());
		m_stateholder.setCommandFromPhone_OperationTimeUC2Enabled(et_UTC_CommandFromPhone_OperationTimeUC2.isEnabled());
		m_stateholder.setCommandFromPhone_OperationTimeUC2Value(et_UTC_CommandFromPhone_OperationTimeUC2.getText().toString());
		
		//Output Timer On Arm Delay
		m_stateholder.setCbOutputTimerOnArmDelayEnabled(cb_UserSettings_InputTimerOnArming.isEnabled());
		m_stateholder.setCbOutputTimerOnArmDelayChecked(cb_UserSettings_InputTimerOnArming.isChecked());
		m_stateholder.setOutputTimerOnArmOperationTimeEnabled(et_UTC_OutputTimerOnArmOperationTime.isEnabled());
		m_stateholder.setOutputTimerOnArmOperationTimeValue(et_UTC_OutputTimerOnArmOperationTime.getText().toString());
		m_stateholder.setOutputTimerOnArmDelayEnabled(et_UTC_OutputTimerOnArmDelay.isEnabled());
		m_stateholder.setOutputTimerOnArmDelayValue(et_UTC_OutputTimerOnArmDelay.getText().toString());
		
		//Turbotimer
		m_stateholder.setCbTurboTimerSettingsEnabled(cb_UTC_TurboTimerSettings.isEnabled());
		m_stateholder.setCbTurboTimerSettingsChecked(cb_UTC_TurboTimerSettings.isChecked());
		m_stateholder.setTurbotimerWorkingTimeEnabled(et_UTC_TurbotimerWorkingTime.isEnabled());
		m_stateholder.setTurbotimerWorkingTimeValue(et_UTC_TurbotimerWorkingTime.getText().toString());
		m_stateholder.setIngnTimeLockedEngineEnabled(et_UTC_IngnTimeLockedEngine.isEnabled());
		m_stateholder.setIngnTimeLockedEngineValue(et_UTC_IngnTimeLockedEngine.getText().toString());

		//Starttimer
		m_stateholder.setCbStarttimerEnabled(cb_UTC_Starttimer.isEnabled());
		m_stateholder.setCbStarttimerChecked(cb_UTC_Starttimer.isChecked());
		m_stateholder.setStarttimerEnabled(et_UTC_Starttimer.isEnabled());
		m_stateholder.setStarttimerValue(et_UTC_Starttimer.getText().toString());

		//Armtimer
		m_stateholder.setCbArmtimerEnabled(cb_UTC_Armtimer.isEnabled());
		m_stateholder.setCbArmtimerChecked(cb_UTC_Armtimer.isChecked());
		m_stateholder.setArmtimerEnabled(et_UTC_Armtimer.isEnabled());
		m_stateholder.setArmtimerValue(et_UTC_Armtimer.getText().toString());

		//Function13
		m_stateholder.setCbCentralLockTimer13Enabled(cb_UTC_CentralLockTimer13.isEnabled());
		m_stateholder.setCbCentralLockTimer13Checked(cb_UTC_CentralLockTimer13.isChecked());
		m_stateholder.setSpinLockImpulsCloseCL13Enabled(spin_UTC_LockImpulsCloseCL13.isEnabled());
		m_stateholder.setSpinLockImpulsCloseCL13Value(spin_UTC_LockImpulsCloseCL13.getSelectedItemPosition());

		m_stateholder.setFirstImpulsLongtime13Enabled(et_UTC_FirstImpulsLongtime13.isEnabled());
		m_stateholder.setFirstImpulsLongtime13Value(et_UTC_FirstImpulsLongtime13.getText().toString());
		m_stateholder.setPauseTimeBetwImpulses13Enabled(et_UTC_PauseTimeBetwImpulses13.isEnabled());
		m_stateholder.setPauseTimeBetwImpulses13Value(et_UTC_PauseTimeBetwImpulses13.getText().toString());
		m_stateholder.setSecondImpulsLongtime13Enabled(et_UTC_SecondImpulsLongtime13.isEnabled());
		m_stateholder.setSecondImpulsLongtime13Value(et_UTC_SecondImpulsLongtime13.getText().toString());
		m_stateholder.setPauseTimeAfterIngAndImpStart13Enabled(et_UTC_PauseTimeAfterIngAndImpStart13.isEnabled());
		m_stateholder.setPauseTimeAfterIngAndImpStart13Value(et_UTC_PauseTimeAfterIngAndImpStart13.getText().toString());

		//Function14
		m_stateholder.setCbCentralLockTimer14Enabled(cb_UTC_CentralLockTimer14.isEnabled());
		m_stateholder.setCbCentralLockTimer14Checked(cb_UTC_CentralLockTimer14.isChecked());
		m_stateholder.setSpinLockImpulsCloseCL14Enabled(spin_UTC_LockImpulsCloseCL14.isEnabled());
		m_stateholder.setSpinLockImpulsCloseCL14Value(spin_UTC_LockImpulsCloseCL14.getSelectedItemPosition());

		m_stateholder.setFirstImpulsLongtime14Enabled(et_UTC_FirstImpulsLongtime14.isEnabled());
		m_stateholder.setFirstImpulsLongtime14Value(et_UTC_FirstImpulsLongtime14.getText().toString());
		m_stateholder.setPauseTimeBetwImpulses14Enabled(et_UTC_PauseTimeBetwImpulses14.isEnabled());
		m_stateholder.setPauseTimeBetwImpulses14Value(et_UTC_PauseTimeBetwImpulses14.getText().toString());
		m_stateholder.setSecondImpulsLongtime14Enabled(et_UTC_SecondImpulsLongtime14.isEnabled());
		m_stateholder.setSecondImpulsLongtime14Value(et_UTC_SecondImpulsLongtime14.getText().toString());
	}
	
	private void restoreState()
	{
		if (m_stateholder != null) 
		{
			//Channel1
			cb_UTC_UniversalChannel1.setEnabled(m_stateholder.getCbUniversalChannel1Enabled());
			cb_UTC_UniversalChannel1.setChecked(m_stateholder.getCbUniversalChannel1Checked());
			cb_UTC_ArmingUC1.setEnabled(m_stateholder.getCbArmingUC1Enabled());
			cb_UTC_ArmingUC1.setChecked(m_stateholder.getCbArmingUC1Checked());
			cb_UTC_SysDisarmUC1.setEnabled(m_stateholder.getCbSysDisarmUC1Enabled());
			cb_UTC_SysDisarmUC1.setChecked(m_stateholder.getCbSysDisarmUC1Checked());
			cb_UTC_IgnitionSwitchOnUC1.setEnabled(m_stateholder.getCbIgnitionSwitchOnUC1Enabled());
			cb_UTC_IgnitionSwitchOnUC1.setChecked(m_stateholder.getCbIgnitionSwitchOnUC1Checked());
			cb_UTC_IgnitionSwitchOffUC1.setEnabled(m_stateholder.getCbIgnitiOffSwitchOffUC1Enabled());
			cb_UTC_IgnitionSwitchOffUC1.setChecked(m_stateholder.getCbIgnitiOffSwitchOffUC1Checked());
			cb_UTC_CommandFromPhoneUC1.setEnabled(m_stateholder.getCbCommandFromPhoneUC1Enabled());
			cb_UTC_CommandFromPhoneUC1.setChecked(m_stateholder.getCbCommandFromPhoneUC1Checked());
			
			et_UTC_Arming_DelayUC1.setEnabled(m_stateholder.getArming_DelayUC1Enabled());
			et_UTC_Arming_DelayUC1.setText(m_stateholder.getArming_DelayUC1Value());
			et_UTC_Arming_OperationTimeUC1.setEnabled(m_stateholder.getArming_OperationTimeUC1Enabled());
			et_UTC_Arming_OperationTimeUC1.setText(m_stateholder.getArming_OperationTimeUC1Value());
			et_UTC_SysDisarm_DelayUC1.setEnabled(m_stateholder.getSysDisarm_DelayUC1Enabled());
			et_UTC_SysDisarm_DelayUC1.setText(m_stateholder.getSysDisarm_DelayUC1Value());
			et_UTC_SysDisarm_OperationTimeUC1.setEnabled(m_stateholder.getSysDisarm_OperationTimeUC1Enabled());
			et_UTC_SysDisarm_OperationTimeUC1.setText(m_stateholder.getSysDisarm_OperationTimeUC1Value());
			et_UTC_IgnitionSwitchOn_DelayUC1.setEnabled(m_stateholder.getIgnitionSwitchOn_DelayUC1Enabled());
			et_UTC_IgnitionSwitchOn_DelayUC1.setText(m_stateholder.getIgnitionSwitchOn_DelayUC1Value());
			et_UTC_IgnitionSwitchOn_OperationTimeUC1.setEnabled(m_stateholder.getIgnitionSwitchOn_OperationTimeUC1Enabled());
			et_UTC_IgnitionSwitchOn_OperationTimeUC1.setText(m_stateholder.getIgnitionSwitchOn_OperationTimeUC1Value());
			et_UTC_IgnitionSwitchOff_DelayUC1.setEnabled(m_stateholder.getIgnitionSwitchOff_DelayUC1Enabled());
			et_UTC_IgnitionSwitchOff_DelayUC1.setText(m_stateholder.getIgnitionSwitchOff_DelayUC1Value());
			et_UTC_IgnitionSwitchOff_OperationTimeUC1.setEnabled(m_stateholder.getIgnitionSwitchOff_OperationTimeUC1Enabled());
			et_UTC_IgnitionSwitchOff_OperationTimeUC1.setText(m_stateholder.getIgnitionSwitchOff_OperationTimeUC1Value());
			et_UTC_CommandFromPhone_DelayUC1.setEnabled(m_stateholder.getCommandFromPhone_DelayUC1Enabled());
			et_UTC_CommandFromPhone_DelayUC1.setText(m_stateholder.getCommandFromPhone_DelayUC1Value());
			et_UTC_CommandFromPhone_OperationTimeUC1.setEnabled(m_stateholder.getCommandFromPhone_OperationTimeUC1Enabled());
			et_UTC_CommandFromPhone_OperationTimeUC1.setText(m_stateholder.getCommandFromPhone_OperationTimeUC1Value());
			
			//Channel2
			cb_UTC_UniversalChannel2.setEnabled(m_stateholder.getCbUniversalChannel2Enabled());
			cb_UTC_UniversalChannel2.setChecked(m_stateholder.getCbUniversalChannel2Checked());
			cb_UTC_ArmingUC2.setEnabled(m_stateholder.getCbArmingUC2Enabled());
			cb_UTC_ArmingUC2.setChecked(m_stateholder.getCbArmingUC2Checked());
			cb_UTC_SysDisarmUC2.setEnabled(m_stateholder.getCbSysDisarmUC2Enabled());
			cb_UTC_SysDisarmUC2.setChecked(m_stateholder.getCbSysDisarmUC2Checked());
			cb_UTC_IgnitionSwitchOnUC2.setEnabled(m_stateholder.getCbIgnitionSwitchOnUC2Enabled());
			cb_UTC_IgnitionSwitchOnUC2.setChecked(m_stateholder.getCbIgnitionSwitchOnUC2Checked());
			cb_UTC_IgnitionSwitchOffUC2.setEnabled(m_stateholder.getCbIgnitiOffSwitchOffUC2Enabled());
			cb_UTC_IgnitionSwitchOffUC2.setChecked(m_stateholder.getCbIgnitiOffSwitchOffUC2Checked());
			cb_UTC_CommandFromPhoneUC2.setEnabled(m_stateholder.getCbCommandFromPhoneUC2Enabled());
			cb_UTC_CommandFromPhoneUC2.setChecked(m_stateholder.getCbCommandFromPhoneUC2Checked());
			
			et_UTC_Arming_DelayUC2.setEnabled(m_stateholder.getArming_DelayUC2Enabled());
			et_UTC_Arming_DelayUC2.setText(m_stateholder.getArming_DelayUC2Value());
			et_UTC_Arming_OperationTimeUC2.setEnabled(m_stateholder.getArming_OperationTimeUC2Enabled());
			et_UTC_Arming_OperationTimeUC2.setText(m_stateholder.getArming_OperationTimeUC2Value());
			et_UTC_SysDisarm_DelayUC2.setEnabled(m_stateholder.getSysDisarm_DelayUC2Enabled());
			et_UTC_SysDisarm_DelayUC2.setText(m_stateholder.getSysDisarm_DelayUC2Value());
			et_UTC_SysDisarm_OperationTimeUC2.setEnabled(m_stateholder.getSysDisarm_OperationTimeUC2Enabled());
			et_UTC_SysDisarm_OperationTimeUC2.setText(m_stateholder.getSysDisarm_OperationTimeUC2Value());
			et_UTC_IgnitionSwitchOn_DelayUC2.setEnabled(m_stateholder.getIgnitionSwitchOn_DelayUC2Enabled());
			et_UTC_IgnitionSwitchOn_DelayUC2.setText(m_stateholder.getIgnitionSwitchOn_DelayUC2Value());
			et_UTC_IgnitionSwitchOn_OperationTimeUC2.setEnabled(m_stateholder.getIgnitionSwitchOn_OperationTimeUC2Enabled());
			et_UTC_IgnitionSwitchOn_OperationTimeUC2.setText(m_stateholder.getIgnitionSwitchOn_OperationTimeUC2Value());
			et_UTC_IgnitionSwitchOff_DelayUC2.setEnabled(m_stateholder.getIgnitionSwitchOff_DelayUC2Enabled());
			et_UTC_IgnitionSwitchOff_DelayUC2.setText(m_stateholder.getIgnitionSwitchOff_DelayUC2Value());
			et_UTC_IgnitionSwitchOff_OperationTimeUC2.setEnabled(m_stateholder.getIgnitionSwitchOff_OperationTimeUC2Enabled());
			et_UTC_IgnitionSwitchOff_OperationTimeUC2.setText(m_stateholder.getIgnitionSwitchOff_OperationTimeUC2Value());
			et_UTC_CommandFromPhone_DelayUC2.setEnabled(m_stateholder.getCommandFromPhone_DelayUC2Enabled());
			et_UTC_CommandFromPhone_DelayUC2.setText(m_stateholder.getCommandFromPhone_DelayUC2Value());
			et_UTC_CommandFromPhone_OperationTimeUC2.setEnabled(m_stateholder.getCommandFromPhone_OperationTimeUC2Enabled());
			et_UTC_CommandFromPhone_OperationTimeUC2.setText(m_stateholder.getCommandFromPhone_OperationTimeUC2Value());
			
			//Output Timer On Arm Delay
			cb_UserSettings_InputTimerOnArming.setEnabled(m_stateholder.getCbOutputTimerOnArmDelayEnabled());
			cb_UserSettings_InputTimerOnArming.setChecked(m_stateholder.getCbOutputTimerOnArmDelayChecked());
			et_UTC_OutputTimerOnArmOperationTime.setEnabled(m_stateholder.getOutputTimerOnArmOperationTimeEnabled());
			et_UTC_OutputTimerOnArmOperationTime.setText(m_stateholder.getOutputTimerOnArmOperationTimeValue());
			et_UTC_OutputTimerOnArmDelay.setEnabled(m_stateholder.getOutputTimerOnArmDelayEnabled());
			et_UTC_OutputTimerOnArmDelay.setText(m_stateholder.getOutputTimerOnArmDelayValue());

			//Turbotimer
			cb_UTC_TurboTimerSettings.setEnabled(m_stateholder.getCbTurboTimerSettingsEnabled());
			cb_UTC_TurboTimerSettings.setChecked(m_stateholder.getCbTurboTimerSettingsChecked());
			et_UTC_TurbotimerWorkingTime.setEnabled(m_stateholder.getTurbotimerWorkingTimeEnabled());
			et_UTC_TurbotimerWorkingTime.setText(m_stateholder.getTurbotimerWorkingTimeValue());
			et_UTC_IngnTimeLockedEngine.setEnabled(m_stateholder.getIngnTimeLockedEngineEnabled());
			et_UTC_IngnTimeLockedEngine.setText(m_stateholder.getIngnTimeLockedEngineValue());

			//Starttimer
			cb_UTC_Starttimer.setEnabled(m_stateholder.getCbStarttimerEnabled());
			cb_UTC_Starttimer.setChecked(m_stateholder.getCbStarttimerChecked());
			et_UTC_Starttimer.setEnabled(m_stateholder.getStarttimerEnabled());
			et_UTC_Starttimer.setText(m_stateholder.getStarttimerValue());

			//Armtimer
			cb_UTC_Armtimer.setEnabled(m_stateholder.getCbArmtimerEnabled());
			cb_UTC_Armtimer.setChecked(m_stateholder.getCbArmtimerChecked());
			et_UTC_Armtimer.setEnabled(m_stateholder.getArmtimerEnabled());
			et_UTC_Armtimer.setText(m_stateholder.getArmtimerValue());

			//Function 13
			cb_UTC_CentralLockTimer13.setEnabled(m_stateholder.getCbCentralLockTimer13Enabled());
			cb_UTC_CentralLockTimer13.setChecked(m_stateholder.getCbCentralLockTimer13Checked());
			spin_UTC_LockImpulsCloseCL13.setEnabled(m_stateholder.getSpinLockImpulsCloseCL13Enabled());
			spin_UTC_LockImpulsCloseCL13.setSelection(m_stateholder.getSpinLockImpulsCloseCL13Value());

			et_UTC_FirstImpulsLongtime13.setEnabled(m_stateholder.getFirstImpulsLongtime13Enabled());
			et_UTC_FirstImpulsLongtime13.setText(m_stateholder.getFirstImpulsLongtime13Value());
			et_UTC_PauseTimeBetwImpulses13.setEnabled(m_stateholder.getPauseTimeBetwImpulses13Enabled());
			et_UTC_PauseTimeBetwImpulses13.setText(m_stateholder.getPauseTimeBetwImpulses13Value());
			et_UTC_SecondImpulsLongtime13.setEnabled(m_stateholder.getSecondImpulsLongtime13Enabled());
			et_UTC_SecondImpulsLongtime13.setText(m_stateholder.getSecondImpulsLongtime13Value());
			et_UTC_PauseTimeAfterIngAndImpStart13.setEnabled(m_stateholder.getPauseTimeAfterIngAndImpStart13Enabled());
			et_UTC_PauseTimeAfterIngAndImpStart13.setText(m_stateholder.getPauseTimeAfterIngAndImpStart13Value());

			//Function 14
			cb_UTC_CentralLockTimer14.setEnabled(m_stateholder.getCbCentralLockTimer14Enabled());
			cb_UTC_CentralLockTimer14.setChecked(m_stateholder.getCbCentralLockTimer14Checked());
			spin_UTC_LockImpulsCloseCL14.setEnabled(m_stateholder.getSpinLockImpulsCloseCL14Enabled());
			spin_UTC_LockImpulsCloseCL14.setSelection(m_stateholder.getSpinLockImpulsCloseCL14Value());

			et_UTC_FirstImpulsLongtime14.setEnabled(m_stateholder.getFirstImpulsLongtime14Enabled());
			et_UTC_FirstImpulsLongtime14.setText(m_stateholder.getFirstImpulsLongtime14Value());
			et_UTC_PauseTimeBetwImpulses14.setEnabled(m_stateholder.getPauseTimeBetwImpulses14Enabled());
			et_UTC_PauseTimeBetwImpulses14.setText(m_stateholder.getPauseTimeBetwImpulses14Value());
			et_UTC_SecondImpulsLongtime14.setEnabled(m_stateholder.getSecondImpulsLongtime14Enabled());
			et_UTC_SecondImpulsLongtime14.setText(m_stateholder.getSecondImpulsLongtime14Value());
		}
		else
		{
			cb_UTC_CommandFromPhoneUC1.setChecked(true);
			cb_UTC_CommandFromPhoneUC2.setChecked(true);
		}
	}
	
	//================================= LISENERS ==============================================
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) 
	{
		switch(buttonView.getId())
		{
			//1)
			case R.id.cb_UTC_UniversalChannel1:
			{
				cb_UTC_ArmingUC1.setEnabled(isChecked);
				cb_UTC_SysDisarmUC1.setEnabled(isChecked);
				cb_UTC_IgnitionSwitchOnUC1.setEnabled(isChecked);
				cb_UTC_IgnitionSwitchOffUC1.setEnabled(isChecked);
				cb_UTC_CommandFromPhoneUC1.setEnabled(isChecked);
				
				if(cb_UTC_ArmingUC1.isChecked() && isChecked)
				{
					et_UTC_Arming_DelayUC1.setEnabled(true);
					et_UTC_Arming_OperationTimeUC1.setEnabled(true);
				}
				else
				{
					et_UTC_Arming_DelayUC1.setEnabled(false);
					et_UTC_Arming_OperationTimeUC1.setEnabled(false);
				}
				
				if(cb_UTC_SysDisarmUC1.isChecked() && isChecked)
				{
					et_UTC_SysDisarm_DelayUC1.setEnabled(true);
					et_UTC_SysDisarm_OperationTimeUC1.setEnabled(true);
				}
				else
				{
					et_UTC_SysDisarm_DelayUC1.setEnabled(false);
					et_UTC_SysDisarm_OperationTimeUC1.setEnabled(false);
				}
				
				if(cb_UTC_IgnitionSwitchOnUC1.isChecked() && isChecked)
				{
					et_UTC_IgnitionSwitchOn_DelayUC1.setEnabled(true);
					et_UTC_IgnitionSwitchOn_OperationTimeUC1.setEnabled(true);
				}
				else
				{
					et_UTC_IgnitionSwitchOn_DelayUC1.setEnabled(false);
					et_UTC_IgnitionSwitchOn_OperationTimeUC1.setEnabled(false);
				}
				
				if(cb_UTC_IgnitionSwitchOffUC1.isChecked() && isChecked)
				{
					et_UTC_IgnitionSwitchOff_DelayUC1.setEnabled(true);
					et_UTC_IgnitionSwitchOff_OperationTimeUC1.setEnabled(true);
				}
				else
				{
					et_UTC_IgnitionSwitchOff_DelayUC1.setEnabled(false);
					et_UTC_IgnitionSwitchOff_OperationTimeUC1.setEnabled(false);
				}
				
				if(cb_UTC_CommandFromPhoneUC1.isChecked() && isChecked)
				{
					et_UTC_CommandFromPhone_DelayUC1.setEnabled(true);
					et_UTC_CommandFromPhone_OperationTimeUC1.setEnabled(true);
				}
				else
				{
					et_UTC_CommandFromPhone_DelayUC1.setEnabled(false);
					et_UTC_CommandFromPhone_OperationTimeUC1.setEnabled(false);
				}
				break;
			}
			
			case R.id.cb_UTC_ArmingUC1:
			{
				if(cb_UTC_UniversalChannel1.isChecked() && isChecked)
				{
					et_UTC_Arming_DelayUC1.setEnabled(true);
					et_UTC_Arming_OperationTimeUC1.setEnabled(true);
				}
				else
				{
					et_UTC_Arming_DelayUC1.setEnabled(false);
					et_UTC_Arming_OperationTimeUC1.setEnabled(false);
				}
				break;
			}
			
			case R.id.cb_UTC_SysDisarmUC1:
			{
				if(cb_UTC_UniversalChannel1.isChecked() && isChecked)
				{
					et_UTC_SysDisarm_DelayUC1.setEnabled(true);
					et_UTC_SysDisarm_OperationTimeUC1.setEnabled(true);
				}
				else
				{
					et_UTC_SysDisarm_DelayUC1.setEnabled(false);
					et_UTC_SysDisarm_OperationTimeUC1.setEnabled(false);
				}
				break;
			}
			
			case R.id.cb_UTC_IgnitionSwitchOnUC1:
			{
				if(cb_UTC_UniversalChannel1.isChecked() && isChecked)
				{
					et_UTC_IgnitionSwitchOn_DelayUC1.setEnabled(true);
					et_UTC_IgnitionSwitchOn_OperationTimeUC1.setEnabled(true);
				}
				else
				{
					et_UTC_IgnitionSwitchOn_DelayUC1.setEnabled(false);
					et_UTC_IgnitionSwitchOn_OperationTimeUC1.setEnabled(false);
				}
				break;
			}
			
			case R.id.cb_UTC_IgnitionSwitchOffUC1:
			{
				if(cb_UTC_UniversalChannel1.isChecked() && isChecked)
				{
					et_UTC_IgnitionSwitchOff_DelayUC1.setEnabled(true);
					et_UTC_IgnitionSwitchOff_OperationTimeUC1.setEnabled(true);
				}
				else
				{
					et_UTC_IgnitionSwitchOff_DelayUC1.setEnabled(false);
					et_UTC_IgnitionSwitchOff_OperationTimeUC1.setEnabled(false);
				}
				break;
			}
			
			case R.id.cb_UTC_CommandFromPhoneUC1:
			{
				if(cb_UTC_UniversalChannel1.isChecked() && isChecked)
				{
					et_UTC_CommandFromPhone_DelayUC1.setEnabled(true);
					et_UTC_CommandFromPhone_OperationTimeUC1.setEnabled(true);
				}
				else
				{
					et_UTC_CommandFromPhone_DelayUC1.setEnabled(false);
					et_UTC_CommandFromPhone_OperationTimeUC1.setEnabled(false);
				}
				break;
			}
			
			//2)
			case R.id.cb_UTC_UniversalChannel2:
			{
				cb_UTC_ArmingUC2.setEnabled(isChecked);
				cb_UTC_SysDisarmUC2.setEnabled(isChecked);
				cb_UTC_IgnitionSwitchOnUC2.setEnabled(isChecked);
				cb_UTC_IgnitionSwitchOffUC2.setEnabled(isChecked);
				cb_UTC_CommandFromPhoneUC2.setEnabled(isChecked);
				
				if(cb_UTC_ArmingUC2.isChecked() && isChecked)
				{
					et_UTC_Arming_DelayUC2.setEnabled(true);
					et_UTC_Arming_OperationTimeUC2.setEnabled(true);
				}
				else
				{
					et_UTC_Arming_DelayUC2.setEnabled(false);
					et_UTC_Arming_OperationTimeUC2.setEnabled(false);
				}
				
				if(cb_UTC_SysDisarmUC2.isChecked() && isChecked)
				{
					et_UTC_SysDisarm_DelayUC2.setEnabled(true);
					et_UTC_SysDisarm_OperationTimeUC2.setEnabled(true);
				}
				else
				{
					et_UTC_SysDisarm_DelayUC2.setEnabled(false);
					et_UTC_SysDisarm_OperationTimeUC2.setEnabled(false);
				}
				
				if(cb_UTC_IgnitionSwitchOnUC2.isChecked() && isChecked)
				{
					et_UTC_IgnitionSwitchOn_DelayUC2.setEnabled(true);
					et_UTC_IgnitionSwitchOn_OperationTimeUC2.setEnabled(true);
				}
				else
				{
					et_UTC_IgnitionSwitchOn_DelayUC2.setEnabled(false);
					et_UTC_IgnitionSwitchOn_OperationTimeUC2.setEnabled(false);
				}
				
				if(cb_UTC_IgnitionSwitchOffUC2.isChecked() && isChecked)
				{
					et_UTC_IgnitionSwitchOff_DelayUC2.setEnabled(true);
					et_UTC_IgnitionSwitchOff_OperationTimeUC2.setEnabled(true);
				}
				else
				{
					et_UTC_IgnitionSwitchOff_DelayUC2.setEnabled(false);
					et_UTC_IgnitionSwitchOff_OperationTimeUC2.setEnabled(false);
				}
				
				if(cb_UTC_CommandFromPhoneUC2.isChecked() && isChecked)
				{
					et_UTC_CommandFromPhone_DelayUC2.setEnabled(true);
					et_UTC_CommandFromPhone_OperationTimeUC2.setEnabled(true);
				}
				else
				{
					et_UTC_CommandFromPhone_DelayUC2.setEnabled(false);
					et_UTC_CommandFromPhone_OperationTimeUC2.setEnabled(false);
				}
				break;
			}
			
			case R.id.cb_UTC_ArmingUC2:
			{
				if(cb_UTC_UniversalChannel2.isChecked() && isChecked)
				{
					et_UTC_Arming_DelayUC2.setEnabled(true);
					et_UTC_Arming_OperationTimeUC2.setEnabled(true);
				}
				else
				{
					et_UTC_Arming_DelayUC2.setEnabled(false);
					et_UTC_Arming_OperationTimeUC2.setEnabled(false);
				}
				break;
			}
			
			case R.id.cb_UTC_SysDisarmUC2:
			{
				if(cb_UTC_UniversalChannel2.isChecked() && isChecked)
				{
					et_UTC_SysDisarm_DelayUC2.setEnabled(true);
					et_UTC_SysDisarm_OperationTimeUC2.setEnabled(true);
				}
				else
				{
					et_UTC_SysDisarm_DelayUC2.setEnabled(false);
					et_UTC_SysDisarm_OperationTimeUC2.setEnabled(false);
				}
				break;
			}
			
			case R.id.cb_UTC_IgnitionSwitchOnUC2:
			{
				if(cb_UTC_UniversalChannel2.isChecked() && isChecked)
				{
					et_UTC_IgnitionSwitchOn_DelayUC2.setEnabled(true);
					et_UTC_IgnitionSwitchOn_OperationTimeUC2.setEnabled(true);
				}
				else
				{
					et_UTC_IgnitionSwitchOn_DelayUC2.setEnabled(false);
					et_UTC_IgnitionSwitchOn_OperationTimeUC2.setEnabled(false);
				}
				break;
			}
			
			case R.id.cb_UTC_IgnitionSwitchOffUC2:
			{
				if(cb_UTC_UniversalChannel2.isChecked() && isChecked)
				{
					et_UTC_IgnitionSwitchOff_DelayUC2.setEnabled(true);
					et_UTC_IgnitionSwitchOff_OperationTimeUC2.setEnabled(true);
				}
				else
				{
					et_UTC_IgnitionSwitchOff_DelayUC2.setEnabled(false);
					et_UTC_IgnitionSwitchOff_OperationTimeUC2.setEnabled(false);
				}
				break;
			}
			
			case R.id.cb_UTC_CommandFromPhoneUC2:
			{
				if(cb_UTC_UniversalChannel2.isChecked() && isChecked)
				{
					et_UTC_CommandFromPhone_DelayUC2.setEnabled(true);
					et_UTC_CommandFromPhone_OperationTimeUC2.setEnabled(true);
				}
				else
				{
					et_UTC_CommandFromPhone_DelayUC2.setEnabled(false);
					et_UTC_CommandFromPhone_OperationTimeUC2.setEnabled(false);
				}
				break;
			}
			
			case R.id.cb_UserSettings_InputTimerOnArming:
			{
				et_UTC_OutputTimerOnArmOperationTime.setEnabled(isChecked);
				et_UTC_OutputTimerOnArmDelay.setEnabled(isChecked);
				break;
			}

			//3)
			case R.id.cb_UTC_TurboTimerSettings:
			{
				et_UTC_TurbotimerWorkingTime.setEnabled(isChecked);
				et_UTC_IngnTimeLockedEngine.setEnabled(isChecked);
				break;
			}

			//Starttimer
			case R.id.cb_UTC_Starttimer:
			{
				et_UTC_Starttimer.setEnabled(isChecked);
				break;
			}

			//Armtimer
			case R.id.cb_UTC_Armtimer:
			{
				et_UTC_Armtimer.setEnabled(isChecked);
				break;
			}


			case R.id.cb_UTC_CentralLockTimer13:
			{
				spin_UTC_LockImpulsCloseCL13.setEnabled(isChecked);
				et_UTC_FirstImpulsLongtime13.setEnabled(isChecked);
				et_UTC_PauseTimeBetwImpulses13.setEnabled(isChecked);
				et_UTC_SecondImpulsLongtime13.setEnabled(isChecked);
				et_UTC_PauseTimeAfterIngAndImpStart13.setEnabled(isChecked);
				break;
			}

			case R.id.cb_UTC_CentralLockTimer14:
			{
				spin_UTC_LockImpulsCloseCL14.setEnabled(isChecked);
				et_UTC_FirstImpulsLongtime14.setEnabled(isChecked);
				et_UTC_PauseTimeBetwImpulses14.setEnabled(isChecked);
				et_UTC_SecondImpulsLongtime14.setEnabled(isChecked);
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
		setTitle(R.string.btn_Main_Timer);
		//1) UNIVERSAL CHANNEL #1
		cb_UTC_UniversalChannel1.setText(R.string.cb_UTC_UniversalChannel1);
		TextView tv_UTC_ActivationConditions1 = (TextView) findViewById(R.id.tv_UTC_ActivationConditions1);
		tv_UTC_ActivationConditions1.setText(R.string.tv_UTC_ActivationConditions);
		TextView tv_UTC_OnDelay1 = (TextView) findViewById(R.id.tv_UTC_OnDelay1);
		tv_UTC_OnDelay1.setText(R.string.tv_UTC_OnDelay);
		TextView tv_UTC_OperationTime1 = (TextView) findViewById(R.id.tv_UTC_OperationTime1);
		tv_UTC_OperationTime1.setText(R.string.tv_UTC_OperationTime);
		cb_UTC_ArmingUC1.setText(R.string.cb_UTC_Arming);
		cb_UTC_SysDisarmUC1.setText(R.string.cb_UTC_SysDisarm);
		cb_UTC_IgnitionSwitchOnUC1.setText(R.string.cb_UTC_IgnitionSwitchOn);
		cb_UTC_IgnitionSwitchOffUC1.setText(R.string.cb_UTC_IgnitionSwitchOff);
		cb_UTC_CommandFromPhoneUC1.setText(R.string.cb_UTC_CommandFromPhone);
		//2) UNIVERSAL CHANNEL #2
		cb_UTC_UniversalChannel2.setText(R.string.cb_UTC_UniversalChannel2);
		TextView tv_UTC_ActivationConditions2 = (TextView) findViewById(R.id.tv_UTC_ActivationConditionsUC2);
		tv_UTC_ActivationConditions2.setText(R.string.tv_UTC_ActivationConditions);
		TextView tv_UTC_OnDelay2 = (TextView) findViewById(R.id.tv_UTC_OnDelayUC2);
		tv_UTC_OnDelay2.setText(R.string.tv_UTC_OnDelay);
		TextView tv_UTC_OperationTime2 = (TextView) findViewById(R.id.tv_UTC_OperationUC2);
		tv_UTC_OperationTime2.setText(R.string.tv_UTC_OperationTime);
		cb_UTC_ArmingUC2.setText(R.string.cb_UTC_Arming);
		cb_UTC_SysDisarmUC2.setText(R.string.cb_UTC_SysDisarm);
		cb_UTC_IgnitionSwitchOnUC2.setText(R.string.cb_UTC_IgnitionSwitchOn);
		cb_UTC_IgnitionSwitchOffUC2.setText(R.string.cb_UTC_IgnitionSwitchOff);
		cb_UTC_CommandFromPhoneUC2.setText(R.string.cb_UTC_CommandFromPhone);
		//3) OUTPUT TIMER ON ARMING
		cb_UserSettings_InputTimerOnArming.setText(R.string.cb_UserSettings_InputTimerOnArming);
		TextView tv_UTC_OutputTimerOnArmOperationTime = (TextView) findViewById(R.id.tv_UTC_OutputTimerOnArmOperationTime);
		tv_UTC_OutputTimerOnArmOperationTime.setText(R.string.tv_UTC_OperationTime);
		TextView tv_UTC_OutputTimerOnArmDelay = (TextView) findViewById(R.id.tv_UTC_OutputTimerOnArmDelay);
		tv_UTC_OutputTimerOnArmDelay.setText(R.string.tv_UTC_OnDelay);
		//5) TURBOTIMER
		cb_UTC_TurboTimerSettings.setText(R.string.cb_UTC_TurboTimerSettings);
		TextView tv_UTC_TurbotimerWorkingTime = (TextView) findViewById(R.id.tv_UTC_TurbotimerWorkingTime);
		tv_UTC_TurbotimerWorkingTime.setText(R.string.tv_UTC_TurbotimerWorkingTime);
		TextView tv_UTC_Minutes = (TextView) findViewById(R.id.tv_UTC_Minutes);
		tv_UTC_Minutes.setText(R.string.tv_UTC_Minutes);
		TextView tv_UTC_IngnTimeLockedEngine = (TextView) findViewById(R.id.tv_UTC_IngnTimeLockedEngine);
		tv_UTC_IngnTimeLockedEngine.setText(R.string.tv_UTC_IngnTimeLockedEngine);
		TextView tv_UTC_Seconds = (TextView) findViewById(R.id.tv_UTC_Seconds);
		tv_UTC_Seconds.setText(R.string.tv_UTC_Seconds);
		//6) STARTTIMER
		cb_UTC_Starttimer.setText(R.string.cb_UTC_Starttimer);
		cb_UTC_Starttimer.setText(R.string.cb_UTC_Starttimer);
		//TextView tv_UTC_Starttimer = (TextView) findViewById(R.id.tv_UTC_IngnTimeLockedEngine);
		//tv_UTC_IngnTimeLockedEngine.setText(R.string.tv_UTC_IngnTimeLockedEngine);
		TextView tv_UTC_StarttimerSeconds = (TextView) findViewById(R.id.tv_UTC_StarttimerSeconds);
		tv_UTC_StarttimerSeconds.setText(R.string.tv_UTC_Seconds);
		//6) ARMTIMER
		cb_UTC_Armtimer.setText(R.string.cb_UTC_Armtimer);
		cb_UTC_Armtimer.setText(R.string.cb_UTC_Armtimer);
		//TextView tv_UTC_Starttimer = (TextView) findViewById(R.id.tv_UTC_IngnTimeLockedEngine);
		//tv_UTC_IngnTimeLockedEngine.setText(R.string.tv_UTC_IngnTimeLockedEngine);
		TextView tv_UTC_ArmtimerSeconds = (TextView) findViewById(R.id.tv_UTC_ArmtimerSeconds);
		tv_UTC_ArmtimerSeconds.setText(R.string.tv_UTC_Seconds);
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
		
		//Prevent empty edit text
		preventEmptyFocus();
		
		//Save Activity State
		saveState();
	}
	
	@Override
	protected void onDestroy() 
	{
		super.onDestroy();
	}
	
	@Override
	public void onFocusChange(View view, boolean hasFocus) 
	{
		EditText et = (EditText) view;
		if(hasFocus || et == null)
			return;
		
		switch(et.getId())
    	{
    		//1) UNIVERSAL TIMER CHANNEL #1
    		case R.id.et_UTC_Arming_DelayUC1:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("0");}
    			break;
    		}
    		case R.id.et_UTC_Arming_OperationTimeUC1:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("1");}
    			break;
    		}
    		case R.id.et_UTC_SysDisarm_DelayUC1:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("0");}
    			break;
    		}
    		case R.id.et_UTC_SysDisarm_OperationTimeUC1:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("1");}
    			break;
    		}
    		case R.id.et_UTC_IgnitionSwitchOn_DelayUC1:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("0");}
    			break;
    		}
    		case R.id.et_UTC_IgnitionSwitchOn_OperationTimeUC1:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("1");}
    			break;
    		}
    		case R.id.et_UTC_IgnitionSwitchOff_DelayUC1:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("0");}
    			break;
    		}
    		case R.id.et_UTC_IgnitionSwitchOff_OperationTimeUC1:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("1");}
    			break;
    		}
    		case R.id.et_UTC_CommandFromPhone_DelayUC1:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("0");}
    			break;
    		}
    		case R.id.et_UTC_CommandFromPhone_OperationTimeUC1:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("1");}
    			break;
    		}
    		//2) UNIVERSAL TIMER CHANNEL #2
    		case R.id.et_UTC_Arming_DelayUC2:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("0");}
    			break;
    		}
    		case R.id.et_UTC_Arming_OperationTimeUC2:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("1");}
    			break;
    		}
    		case R.id.et_UTC_SysDisarm_DelayUC2:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("0");}
    			break;
    		}
    		case R.id.et_UTC_SysDisarm_OperationTimeUC2:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("1");}
    			break;
    		}
    		case R.id.et_UTC_IgnitionSwitchOn_DelayUC2:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("0");}
    			break;
    		}
    		case R.id.et_UTC_IgnitionSwitchOn_OperationTimeUC2:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("1");}
    			break;
    		}
    		case R.id.et_UTC_IgnitionSwitchOff_DelayUC2:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("0");}
    			break;
    		}
    		case R.id.et_UTC_IgnitionSwitchOff_OperationTimeUC2:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("1");}
    			break;
    		}
    		case R.id.et_UTC_CommandFromPhone_DelayUC2:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("0");}
    			break;
    		}
    		case R.id.et_UTC_CommandFromPhone_OperationTimeUC2:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("1");}
    			break;
    		}
    		//3) OUTPUT TIMER ON ARMING
    		case R.id.et_UTC_OutputTimerOnArmOperationTime:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("30");}
    			break;
    		}
    		case R.id.et_UTC_OutputTimerOnArmDelay:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("0");}
    			break;
    		}
//    		//5) TURBO-TIMER
    		case R.id.et_UTC_TurbotimerWorkingTime:
    		{
    			try
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("0");}
    			break;
    		}

    	}
	}
	
	private void preventEmptyFocus()
	{
		View v = getCurrentFocus();
	    EditText et = (EditText) v;
	    
	    if(et == null)
	    	return;
	    
    	switch(et.getId())
    	{
	   		//1) UNIVERSAL TIMER CHANNEL #1
			case R.id.et_UTC_Arming_DelayUC1:
			{
				try 
				{
	    			Integer value = Integer.parseInt(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("0");}
				break;
			}
			case R.id.et_UTC_Arming_OperationTimeUC1:
			{
				try 
				{
	    			Integer value = Integer.parseInt(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("1");}
				break;
			}
			case R.id.et_UTC_SysDisarm_DelayUC1:
			{
				try 
				{
	    			Integer value = Integer.parseInt(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("0");}
				break;
			}
			case R.id.et_UTC_SysDisarm_OperationTimeUC1:
			{
				try 
				{
	    			Integer value = Integer.parseInt(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("1");}
				break;
			}
			case R.id.et_UTC_IgnitionSwitchOn_DelayUC1:
			{
				try 
				{
	    			Integer value = Integer.parseInt(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("0");}
				break;
			}
			case R.id.et_UTC_IgnitionSwitchOn_OperationTimeUC1:
			{
				try 
				{
	    			Integer value = Integer.parseInt(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("1");}
				break;
			}
			case R.id.et_UTC_IgnitionSwitchOff_DelayUC1:
			{
				try 
				{
	    			Integer value = Integer.parseInt(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("0");}
				break;
			}
			case R.id.et_UTC_IgnitionSwitchOff_OperationTimeUC1:
			{
				try 
				{
	    			Integer value = Integer.parseInt(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("1");}
				break;
			}
			case R.id.et_UTC_CommandFromPhone_DelayUC1:
			{
				try 
				{
	    			Integer value = Integer.parseInt(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("0");}
				break;
			}
			case R.id.et_UTC_CommandFromPhone_OperationTimeUC1:
			{
				try 
				{
	    			Integer value = Integer.parseInt(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("1");}
				break;
			}
			//2) UNIVERSAL TIMER CHANNEL #2
			case R.id.et_UTC_Arming_DelayUC2:
			{
				try 
				{
	    			Integer value = Integer.parseInt(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("0");}
				break;
			}
			case R.id.et_UTC_Arming_OperationTimeUC2:
			{
				try 
				{
	    			Integer value = Integer.parseInt(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("1");}
				break;
			}
			case R.id.et_UTC_SysDisarm_DelayUC2:
			{
				try 
				{
	    			Integer value = Integer.parseInt(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("0");}
				break;
			}
			case R.id.et_UTC_SysDisarm_OperationTimeUC2:
			{
				try 
				{
	    			Integer value = Integer.parseInt(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("1");}
				break;
			}
			case R.id.et_UTC_IgnitionSwitchOn_DelayUC2:
			{
				try 
				{
	    			Integer value = Integer.parseInt(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("0");}
				break;
			}
			case R.id.et_UTC_IgnitionSwitchOn_OperationTimeUC2:
			{
				try 
				{
	    			Integer value = Integer.parseInt(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("1");}
				break;
			}
			case R.id.et_UTC_IgnitionSwitchOff_DelayUC2:
			{
				try 
				{
	    			Integer value = Integer.parseInt(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("0");}
				break;
			}
			case R.id.et_UTC_IgnitionSwitchOff_OperationTimeUC2:
			{
				try 
				{
	    			Integer value = Integer.parseInt(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("1");}
				break;
			}
			case R.id.et_UTC_CommandFromPhone_DelayUC2:
			{
				try 
				{
	    			Integer value = Integer.parseInt(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("0");}
				break;
			}
			case R.id.et_UTC_CommandFromPhone_OperationTimeUC2:
			{
				try 
				{
	    			Integer value = Integer.parseInt(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("1");}
				break;
			}
			//3) OUTPUT TIMER ON ARMING
			case R.id.et_UTC_OutputTimerOnArmOperationTime:
			{
				try 
				{
	    			Integer value = Integer.parseInt(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("30");}
				break;
			}
			case R.id.et_UTC_OutputTimerOnArmDelay:
			{
				try 
				{
	    			Integer value = Integer.parseInt(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("0");}
				break;
			}
			//5) TURBO-TIMER
			case R.id.et_UTC_TurbotimerWorkingTime:
			{
				try
				{
	    			Integer value = Integer.parseInt(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("0");}
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

			if(requestCode == RESULT_CODE_SPINNER && data != null)
			{
				int extra = data.getIntExtra(Outputs.SPINNER_OUTPUT, 0);
				m_activeSpinner.setSelection(extra);
			}
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		Spinner spinner = (Spinner) v;
		if(spinner != null && event.getAction() == MotionEvent.ACTION_UP)
		{
			m_activeSpinner = spinner;


			final int itemsCount = m_activeSpinner.getAdapter().getCount();
			ArrayList<String> listOfItems = new ArrayList<String>();
			for(int item = 0; item < itemsCount; ++item)
			{
				listOfItems.add(m_activeSpinner.getItemAtPosition(item).toString());
			}

			Intent intent = new Intent(this, CustomSpinnerPopup.class);
			intent.putExtra(SPINNER_UTC, listOfItems);
			startActivityForResult(intent, RESULT_CODE_SPINNER);
		}

		return true;
	}
}
