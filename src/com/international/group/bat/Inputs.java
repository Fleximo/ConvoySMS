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
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import stateholders.InputsStateHolder;
import utils.EditTextWatcherDouble;

public class Inputs extends Activity implements OnCheckedChangeListener, OnFocusChangeListener, View.OnTouchListener
{
	
	public static Inputs instance = null;
	private static Spinner m_activeSpinner = null;
	public static final String SPINNER_INPUTS = "com.international.group.bat.SPINNER_INPUTS";
	public static final int RESULT_CODE_SPINNER = 12335;
	
	//1) IN1
	CheckBox cb_Inputs_IN1 = null;
	Spinner spin_Inputs_InputFunctionsIN1 = null;
	Spinner spin_Inputs_PolarityIN1 = null;
	Spinner spin_Inputs_InputIsActiveIN1 = null;
	EditText et_Inputs_MinClosingTimeIN1 = null;
	EditText et_Inputs_MinOpeningTimeIN1 = null;
	//2) IN2
	CheckBox cb_Inputs_IN2 = null;
	Spinner spin_Inputs_InputFunctionsIN2 = null;
	Spinner spin_Inputs_PolarityIN2 = null;
	Spinner spin_Inputs_InputIsActiveIN2 = null;
	EditText et_Inputs_MinClosingTimeIN2 = null;
	EditText et_Inputs_MinOpeningTimeIN2 = null;
	//3) IN3
	CheckBox cb_Inputs_IN3 = null;
	Spinner spin_Inputs_InputFunctionsIN3 = null;
	Spinner spin_Inputs_PolarityIN3 = null;
	Spinner spin_Inputs_InputIsActiveIN3 = null;
	EditText et_Inputs_MinClosingTimeIN3 = null;
	EditText et_Inputs_MinOpeningTimeIN3 = null;
	//4) IN4
	CheckBox cb_Inputs_IN4 = null;
	Spinner spin_Inputs_InputFunctionsIN4 = null;
	Spinner spin_Inputs_PolarityIN4 = null;
	Spinner spin_Inputs_InputIsActiveIN4 = null;
	EditText et_Inputs_MinClosingTimeIN4 = null;
	EditText et_Inputs_MinOpeningTimeIN4 = null;
	//5) IN5
	CheckBox cb_Inputs_IN5 = null;
	Spinner spin_Inputs_InputFunctionsIN5 = null;
	Spinner spin_Inputs_PolarityIN5 = null;
	Spinner spin_Inputs_InputIsActiveIN5 = null;
	EditText et_Inputs_MinClosingTimeIN5 = null;
	EditText et_Inputs_MinOpeningTimeIN5 = null;
	//6) IN6
	CheckBox cb_Inputs_IN6 = null;
	Spinner spin_Inputs_InputFunctionsIN6 = null;
	Spinner spin_Inputs_PolarityIN6 = null;
	Spinner spin_Inputs_InputIsActiveIN6 = null;
	EditText et_Inputs_MinClosingTimeIN6 = null;
	EditText et_Inputs_MinOpeningTimeIN6 = null;
	//7) IN7
	CheckBox cb_Inputs_IN7 = null;
	Spinner spin_Inputs_InputFunctionsIN7 = null;
	Spinner spin_Inputs_PolarityIN7 = null;
	Spinner spin_Inputs_InputIsActiveIN7 = null;
	EditText et_Inputs_MinClosingTimeIN7 = null;
	EditText et_Inputs_MinOpeningTimeIN7 = null;
	//8) IN8
	CheckBox cb_Inputs_IN8 = null;
	Spinner spin_Inputs_InputFunctionsIN8 = null;
	Spinner spin_Inputs_PolarityIN8 = null;
	Spinner spin_Inputs_InputIsActiveIN8 = null;
	EditText et_Inputs_MinClosingTimeIN8 = null;
	EditText et_Inputs_MinOpeningTimeIN8 = null;
	
	//State holder
	static InputsStateHolder m_stateholder = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		instance = this;
		setLanguage();
		setContentView(R.layout.inputs);
		
		//1) IN1
		initN1();
		//2) IN2
		initN2();
		//3) IN3
		initN3();
		//4) IN4
		initN4();
		//5) IN5
		initN5();
		//6) IN6
		initN6();
		//7) IN7
		initN7();
		//8) IN8
		initN8();
		
		//Restore state
		restoreState();
		//Listeners set
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
			updateMenuItems();
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
	
//===================================== INITIALIZING =====================================//
	private void initN1()
	{
		//---------------------------------------- INITIALIZATION ------------------------------------
		//N1 members initialization
		cb_Inputs_IN1 = (CheckBox) findViewById(R.id.cb_Inputs_IN1);
		spin_Inputs_InputFunctionsIN1 = (Spinner) findViewById(R.id.spin_Inputs_InputFunctionsIN1);
		spin_Inputs_PolarityIN1 = (Spinner) findViewById(R.id.spin_Inputs_PolarityIN1);
		spin_Inputs_InputIsActiveIN1 = (Spinner) findViewById(R.id.spin_Inputs_InputIsActiveIN1);
		et_Inputs_MinClosingTimeIN1 = (EditText) findViewById(R.id.et_Inputs_MinClosingTimeIN1);
		et_Inputs_MinOpeningTimeIN1 = (EditText) findViewById(R.id.et_Inputs_MinOpeningTimeIN1);
		
		//N1 Spinners initialization
		ArrayAdapter<String> inputFunctionMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_function_N1));
		spin_Inputs_InputFunctionsIN1.setAdapter(inputFunctionMenu);

		ArrayAdapter<String> polarityMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.polarity));
		spin_Inputs_PolarityIN1.setAdapter(polarityMenu);
		ArrayAdapter<String> inputIsActiveMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_is_active));
		spin_Inputs_InputIsActiveIN1.setAdapter(inputIsActiveMenu);
		
		et_Inputs_MinClosingTimeIN1.setText("0.1");
		et_Inputs_MinOpeningTimeIN1.setText("0.1");
		spin_Inputs_InputFunctionsIN1.setEnabled(false);
		spin_Inputs_InputFunctionsIN1.setSelection(1);
		spin_Inputs_PolarityIN1.setEnabled(false);
		spin_Inputs_PolarityIN1.setSelection(1);
		spin_Inputs_InputIsActiveIN1.setEnabled(false);
		et_Inputs_MinClosingTimeIN1.setEnabled(false);
		et_Inputs_MinOpeningTimeIN1.setEnabled(false);
	}
	private void initN2()
	{
		//---------------------------------------- INITIALIZATION ------------------------------------
		//N2 members initialization
		cb_Inputs_IN2 = (CheckBox) findViewById(R.id.cb_Inputs_IN2);
		spin_Inputs_InputFunctionsIN2 = (Spinner) findViewById(R.id.spin_Inputs_InputFunctionsIN2);
		spin_Inputs_PolarityIN2 = (Spinner) findViewById(R.id.spin_Inputs_PolarityIN2);
		spin_Inputs_InputIsActiveIN2 = (Spinner) findViewById(R.id.spin_Inputs_InputIsActiveIN2);
		et_Inputs_MinClosingTimeIN2 = (EditText) findViewById(R.id.et_Inputs_MinClosingTimeIN2);
		et_Inputs_MinOpeningTimeIN2 = (EditText) findViewById(R.id.et_Inputs_MinOpeningTimeIN2);
		
		//N2 Spinners initialization
		ArrayAdapter<String> inputFunctionMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_function_N2));
		spin_Inputs_InputFunctionsIN2.setAdapter(inputFunctionMenu);
		ArrayAdapter<String> polarityMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.polarity));
		spin_Inputs_PolarityIN2.setAdapter(polarityMenu);
		ArrayAdapter<String> inputIsActiveMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_is_active));
		spin_Inputs_InputIsActiveIN2.setAdapter(inputIsActiveMenu);
		
		et_Inputs_MinClosingTimeIN2.setText("0.1");
		et_Inputs_MinOpeningTimeIN2.setText("0.1");
		spin_Inputs_InputFunctionsIN2.setEnabled(false);
		spin_Inputs_InputFunctionsIN2.setSelection(2);
		spin_Inputs_PolarityIN2.setEnabled(false);
		spin_Inputs_PolarityIN2.setSelection(1);
		spin_Inputs_InputIsActiveIN2.setEnabled(false);
		et_Inputs_MinClosingTimeIN2.setEnabled(false);
		et_Inputs_MinOpeningTimeIN2.setEnabled(false);
	}
	private void initN3()
	{
		//---------------------------------------- INITIALIZATION ------------------------------------
		//N3 members initialization
		cb_Inputs_IN3 = (CheckBox) findViewById(R.id.cb_Inputs_IN3);
		spin_Inputs_InputFunctionsIN3 = (Spinner) findViewById(R.id.spin_Inputs_InputFunctionsIN3);
		spin_Inputs_PolarityIN3 = (Spinner) findViewById(R.id.spin_Inputs_PolarityIN3);
		spin_Inputs_InputIsActiveIN3 = (Spinner) findViewById(R.id.spin_Inputs_InputIsActiveIN3);
		et_Inputs_MinClosingTimeIN3 = (EditText) findViewById(R.id.et_Inputs_MinClosingTimeIN3);
		et_Inputs_MinOpeningTimeIN3 = (EditText) findViewById(R.id.et_Inputs_MinOpeningTimeIN3);
		
		//N3 Spinners initialization
		ArrayAdapter<String> inputFunctionMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_function_N3));
		spin_Inputs_InputFunctionsIN3.setAdapter(inputFunctionMenu);
		ArrayAdapter<String> polarityMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.polarity));
		spin_Inputs_PolarityIN3.setAdapter(polarityMenu);
		ArrayAdapter<String> inputIsActiveMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_is_active));
		spin_Inputs_InputIsActiveIN3.setAdapter(inputIsActiveMenu);
		
		et_Inputs_MinClosingTimeIN3.setText("0.1");
		et_Inputs_MinOpeningTimeIN3.setText("0.1");
		spin_Inputs_InputFunctionsIN3.setEnabled(false);
		spin_Inputs_InputFunctionsIN3.setSelection(3);
		spin_Inputs_PolarityIN3.setEnabled(false);
		spin_Inputs_PolarityIN3.setSelection(1);
		spin_Inputs_InputIsActiveIN3.setEnabled(false);
		et_Inputs_MinClosingTimeIN3.setEnabled(false);
		et_Inputs_MinOpeningTimeIN3.setEnabled(false);
	}
	private void initN4()
	{
		//---------------------------------------- INITIALIZATION ------------------------------------
		//N4 members initialization
		cb_Inputs_IN4 = (CheckBox) findViewById(R.id.cb_Inputs_IN4);
		spin_Inputs_InputFunctionsIN4 = (Spinner) findViewById(R.id.spin_Inputs_InputFunctionsIN4);
		spin_Inputs_PolarityIN4 = (Spinner) findViewById(R.id.spin_Inputs_PolarityIN4);
		spin_Inputs_InputIsActiveIN4 = (Spinner) findViewById(R.id.spin_Inputs_InputIsActiveIN4);
		et_Inputs_MinClosingTimeIN4 = (EditText) findViewById(R.id.et_Inputs_MinClosingTimeIN4);
		et_Inputs_MinOpeningTimeIN4 = (EditText) findViewById(R.id.et_Inputs_MinOpeningTimeIN4);
		
		//N4 Spinners initialization
		ArrayAdapter<String> inputFunctionMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_function_N4));
		spin_Inputs_InputFunctionsIN4.setAdapter(inputFunctionMenu);
		ArrayAdapter<String> polarityMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.polarity));
		spin_Inputs_PolarityIN4.setAdapter(polarityMenu);
		ArrayAdapter<String> inputIsActiveMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_is_active));
		spin_Inputs_InputIsActiveIN4.setAdapter(inputIsActiveMenu);
		
		et_Inputs_MinClosingTimeIN4.setText("0.1");
		et_Inputs_MinOpeningTimeIN4.setText("0.1");
		spin_Inputs_InputFunctionsIN4.setEnabled(false);
		spin_Inputs_InputFunctionsIN4.setSelection(0);
		spin_Inputs_PolarityIN4.setEnabled(false);
		spin_Inputs_PolarityIN4.setSelection(1);
		spin_Inputs_InputIsActiveIN4.setEnabled(false);
		et_Inputs_MinClosingTimeIN4.setEnabled(false);
		et_Inputs_MinOpeningTimeIN4.setEnabled(false);
	}
	private void initN5()
	{
		//---------------------------------------- INITIALIZATION ------------------------------------
		//N5 members initialization
		cb_Inputs_IN5 = (CheckBox) findViewById(R.id.cb_Inputs_IN5);
		spin_Inputs_InputFunctionsIN5 = (Spinner) findViewById(R.id.spin_Inputs_InputFunctionsIN5);
		spin_Inputs_PolarityIN5 = (Spinner) findViewById(R.id.spin_Inputs_PolarityIN5);
		spin_Inputs_InputIsActiveIN5 = (Spinner) findViewById(R.id.spin_Inputs_InputIsActiveIN5);
		et_Inputs_MinClosingTimeIN5 = (EditText) findViewById(R.id.et_Inputs_MinClosingTimeIN5);
		et_Inputs_MinOpeningTimeIN5 = (EditText) findViewById(R.id.et_Inputs_MinOpeningTimeIN5);
		
		//N5 Spinners initialization
		ArrayAdapter<String> inputFunctionMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_function_N5));
		spin_Inputs_InputFunctionsIN5.setAdapter(inputFunctionMenu);
		ArrayAdapter<String> polarityMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.polarity));
		spin_Inputs_PolarityIN5.setAdapter(polarityMenu);
		ArrayAdapter<String> inputIsActiveMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_is_active));
		spin_Inputs_InputIsActiveIN5.setAdapter(inputIsActiveMenu);
		
		et_Inputs_MinClosingTimeIN5.setText("0.1");
		et_Inputs_MinOpeningTimeIN5.setText("0.1");
		spin_Inputs_InputFunctionsIN5.setEnabled(false);
		spin_Inputs_InputFunctionsIN5.setSelection(5);
		spin_Inputs_PolarityIN5.setEnabled(false);
		spin_Inputs_PolarityIN5.setSelection(1);
		spin_Inputs_InputIsActiveIN5.setEnabled(false);
		et_Inputs_MinClosingTimeIN5.setEnabled(false);
		et_Inputs_MinOpeningTimeIN5.setEnabled(false);
	}
	private void initN6()
	{
		//---------------------------------------- INITIALIZATION ------------------------------------
		//N6 members initialization
		cb_Inputs_IN6 = (CheckBox) findViewById(R.id.cb_Inputs_IN6);
		spin_Inputs_InputFunctionsIN6 = (Spinner) findViewById(R.id.spin_Inputs_InputFunctionsIN6);
		spin_Inputs_PolarityIN6 = (Spinner) findViewById(R.id.spin_Inputs_PolarityIN6);
		spin_Inputs_InputIsActiveIN6 = (Spinner) findViewById(R.id.spin_Inputs_InputIsActiveIN6);
		et_Inputs_MinClosingTimeIN6 = (EditText) findViewById(R.id.et_Inputs_MinClosingTimeIN6);
		et_Inputs_MinOpeningTimeIN6 = (EditText) findViewById(R.id.et_Inputs_MinOpeningTimeIN6);
		
		//N6 Spinners initialization
		ArrayAdapter<String> inputFunctionMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_function_N6));
		spin_Inputs_InputFunctionsIN6.setAdapter(inputFunctionMenu);
		ArrayAdapter<String> polarityMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.polarity));
		spin_Inputs_PolarityIN6.setAdapter(polarityMenu);
		ArrayAdapter<String> inputIsActiveMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_is_active));
		spin_Inputs_InputIsActiveIN6.setAdapter(inputIsActiveMenu);
		
		et_Inputs_MinClosingTimeIN6.setText("0.1");
		et_Inputs_MinOpeningTimeIN6.setText("0.1");
		spin_Inputs_InputFunctionsIN6.setEnabled(false);
		spin_Inputs_InputFunctionsIN6.setSelection(0);
		spin_Inputs_PolarityIN6.setEnabled(false);
		spin_Inputs_PolarityIN6.setSelection(0);
		spin_Inputs_InputIsActiveIN6.setEnabled(false);
		et_Inputs_MinClosingTimeIN6.setEnabled(false);
		et_Inputs_MinOpeningTimeIN6.setEnabled(false);
	}
	private void initN7()
	{
		//---------------------------------------- INITIALIZATION ------------------------------------
		//N7 members initialization
		cb_Inputs_IN7 = (CheckBox) findViewById(R.id.cb_Inputs_IN7);
		spin_Inputs_InputFunctionsIN7 = (Spinner) findViewById(R.id.spin_Inputs_InputFunctionsIN7);
		spin_Inputs_PolarityIN7 = (Spinner) findViewById(R.id.spin_Inputs_PolarityIN7);
		spin_Inputs_InputIsActiveIN7 = (Spinner) findViewById(R.id.spin_Inputs_InputIsActiveIN7);
		et_Inputs_MinClosingTimeIN7 = (EditText) findViewById(R.id.et_Inputs_MinClosingTimeIN7);
		et_Inputs_MinOpeningTimeIN7 = (EditText) findViewById(R.id.et_Inputs_MinOpeningTimeIN7);
		
		//N7 Spinners initialization
		ArrayAdapter<String> inputFunctionMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_function_N7));
		spin_Inputs_InputFunctionsIN7.setAdapter(inputFunctionMenu);
		ArrayAdapter<String> polarityMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.polarity));
		spin_Inputs_PolarityIN7.setAdapter(polarityMenu);
		ArrayAdapter<String> inputIsActiveMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_is_active));
		spin_Inputs_InputIsActiveIN7.setAdapter(inputIsActiveMenu);
		
		et_Inputs_MinClosingTimeIN7.setText("0.1");
		et_Inputs_MinOpeningTimeIN7.setText("0.1");
		spin_Inputs_InputFunctionsIN7.setEnabled(false);
		spin_Inputs_InputFunctionsIN7.setSelection(7);
		spin_Inputs_PolarityIN7.setEnabled(false);
		spin_Inputs_PolarityIN7.setSelection(1);
		spin_Inputs_InputIsActiveIN7.setEnabled(false);
		et_Inputs_MinClosingTimeIN7.setEnabled(false);
		et_Inputs_MinOpeningTimeIN7.setEnabled(false);
	}
	private void initN8()
	{
		//---------------------------------------- INITIALIZATION ------------------------------------
		//N8 members initialization
		cb_Inputs_IN8 = (CheckBox) findViewById(R.id.cb_Inputs_IN8);
		spin_Inputs_InputFunctionsIN8 = (Spinner) findViewById(R.id.spin_Inputs_InputFunctionsIN8);
		spin_Inputs_PolarityIN8 = (Spinner) findViewById(R.id.spin_Inputs_PolarityIN8);
		spin_Inputs_InputIsActiveIN8 = (Spinner) findViewById(R.id.spin_Inputs_InputIsActiveIN8);
		et_Inputs_MinClosingTimeIN8 = (EditText) findViewById(R.id.et_Inputs_MinClosingTimeIN8);
		et_Inputs_MinOpeningTimeIN8 = (EditText) findViewById(R.id.et_Inputs_MinOpeningTimeIN8);
		
		//N8 Spinners initialization
		ArrayAdapter<String> inputFunctionMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_function_N8));
		spin_Inputs_InputFunctionsIN8.setAdapter(inputFunctionMenu);
		ArrayAdapter<String> polarityMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.polarity));
		spin_Inputs_PolarityIN8.setAdapter(polarityMenu);
		ArrayAdapter<String> inputIsActiveMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_is_active));
		spin_Inputs_InputIsActiveIN8.setAdapter(inputIsActiveMenu);
		
		et_Inputs_MinClosingTimeIN8.setText("0.1");
		et_Inputs_MinOpeningTimeIN8.setText("0.1");
		spin_Inputs_InputFunctionsIN8.setEnabled(false);
		spin_Inputs_InputFunctionsIN8.setSelection(8);
		spin_Inputs_PolarityIN8.setEnabled(false);
		spin_Inputs_PolarityIN8.setSelection(1);
		spin_Inputs_InputIsActiveIN8.setEnabled(false);
		et_Inputs_MinClosingTimeIN8.setEnabled(false);
		et_Inputs_MinOpeningTimeIN8.setEnabled(false);
	}
	
	private void setListeners()
	{
		//---------------------------------------- SET LISTENERS ------------------------------------
		cb_Inputs_IN1.setOnCheckedChangeListener(this);
		cb_Inputs_IN2.setOnCheckedChangeListener(this);
		cb_Inputs_IN3.setOnCheckedChangeListener(this);
		cb_Inputs_IN4.setOnCheckedChangeListener(this);
		cb_Inputs_IN5.setOnCheckedChangeListener(this);
		cb_Inputs_IN6.setOnCheckedChangeListener(this);
		cb_Inputs_IN7.setOnCheckedChangeListener(this);
		cb_Inputs_IN8.setOnCheckedChangeListener(this);
		
		//IN1
		EditTextWatcherDouble etMinClosingTimeIN1TextWatcher = new EditTextWatcherDouble(et_Inputs_MinClosingTimeIN1, "0.1", "1", "25.5");
		et_Inputs_MinClosingTimeIN1.addTextChangedListener(etMinClosingTimeIN1TextWatcher);
		EditTextWatcherDouble etMinOpeningTimeIN1TextWatcher = new EditTextWatcherDouble(et_Inputs_MinOpeningTimeIN1, "0.1", "1", "25.5");
		et_Inputs_MinOpeningTimeIN1.addTextChangedListener(etMinOpeningTimeIN1TextWatcher);
		et_Inputs_MinClosingTimeIN1.setOnFocusChangeListener(this);
		et_Inputs_MinOpeningTimeIN1.setOnFocusChangeListener(this);
		spin_Inputs_InputFunctionsIN1.setOnTouchListener(this);
		spin_Inputs_PolarityIN1.setOnTouchListener(this);
		spin_Inputs_InputIsActiveIN1.setOnTouchListener(this);
		//IN2
		EditTextWatcherDouble etMinClosingTimeIN2TextWatcher = new EditTextWatcherDouble(et_Inputs_MinClosingTimeIN2, "0.1", "0", "25.5");
		et_Inputs_MinClosingTimeIN2.addTextChangedListener(etMinClosingTimeIN2TextWatcher);
		EditTextWatcherDouble etMinOpeningTimeIN2TextWatcher = new EditTextWatcherDouble(et_Inputs_MinOpeningTimeIN2, "0.1", "0", "25.5");
		et_Inputs_MinOpeningTimeIN2.addTextChangedListener(etMinOpeningTimeIN2TextWatcher);
		et_Inputs_MinClosingTimeIN2.setOnFocusChangeListener(this);
		et_Inputs_MinOpeningTimeIN2.setOnFocusChangeListener(this);
		spin_Inputs_InputFunctionsIN2.setOnTouchListener(this);
		spin_Inputs_PolarityIN2.setOnTouchListener(this);
		spin_Inputs_InputIsActiveIN2.setOnTouchListener(this);
		//IN3
		EditTextWatcherDouble etMinClosingTimeIN3TextWatcher = new EditTextWatcherDouble(et_Inputs_MinClosingTimeIN3, "0.1", "0", "25.5");
		et_Inputs_MinClosingTimeIN3.addTextChangedListener(etMinClosingTimeIN3TextWatcher);
		EditTextWatcherDouble etMinOpeningTimeIN3TextWatcher = new EditTextWatcherDouble(et_Inputs_MinOpeningTimeIN3, "0.1", "0", "25.5");
		et_Inputs_MinOpeningTimeIN3.addTextChangedListener(etMinOpeningTimeIN3TextWatcher);
		et_Inputs_MinClosingTimeIN3.setOnFocusChangeListener(this);
		et_Inputs_MinOpeningTimeIN3.setOnFocusChangeListener(this);
		spin_Inputs_InputFunctionsIN3.setOnTouchListener(this);
		spin_Inputs_PolarityIN3.setOnTouchListener(this);
		spin_Inputs_InputIsActiveIN3.setOnTouchListener(this);
		//IN4
		EditTextWatcherDouble etMinClosingTimeIN4TextWatcher = new EditTextWatcherDouble(et_Inputs_MinClosingTimeIN4, "0.1", "0", "25.5");
		et_Inputs_MinClosingTimeIN4.addTextChangedListener(etMinClosingTimeIN4TextWatcher);
		EditTextWatcherDouble etMinOpeningTimeIN4TextWatcher = new EditTextWatcherDouble(et_Inputs_MinOpeningTimeIN4, "0.1", "0", "25.5");
		et_Inputs_MinOpeningTimeIN4.addTextChangedListener(etMinOpeningTimeIN4TextWatcher);
		et_Inputs_MinClosingTimeIN4.setOnFocusChangeListener(this);
		et_Inputs_MinOpeningTimeIN4.setOnFocusChangeListener(this);
		spin_Inputs_InputFunctionsIN4.setOnTouchListener(this);
		spin_Inputs_PolarityIN4.setOnTouchListener(this);
		spin_Inputs_InputIsActiveIN4.setOnTouchListener(this);
		//IN5
		EditTextWatcherDouble etMinClosingTimeIN5TextWatcher = new EditTextWatcherDouble(et_Inputs_MinClosingTimeIN5, "0.1", "0", "25.5");
		et_Inputs_MinClosingTimeIN5.addTextChangedListener(etMinClosingTimeIN5TextWatcher);
		EditTextWatcherDouble etMinOpeningTimeIN5TextWatcher = new EditTextWatcherDouble(et_Inputs_MinOpeningTimeIN5, "0.1", "0", "25.5");
		et_Inputs_MinOpeningTimeIN5.addTextChangedListener(etMinOpeningTimeIN5TextWatcher);
		et_Inputs_MinClosingTimeIN5.setOnFocusChangeListener(this);
		et_Inputs_MinOpeningTimeIN5.setOnFocusChangeListener(this);
		spin_Inputs_InputFunctionsIN5.setOnTouchListener(this);
		spin_Inputs_PolarityIN5.setOnTouchListener(this);
		spin_Inputs_InputIsActiveIN5.setOnTouchListener(this);
		//IN6
		EditTextWatcherDouble etMinClosingTimeIN6TextWatcher = new EditTextWatcherDouble(et_Inputs_MinClosingTimeIN6, "0.1", "0", "25.5");
		et_Inputs_MinClosingTimeIN6.addTextChangedListener(etMinClosingTimeIN6TextWatcher);
		EditTextWatcherDouble etMinOpeningTimeIN6TextWatcher = new EditTextWatcherDouble(et_Inputs_MinOpeningTimeIN6, "0.1", "0", "25.5");
		et_Inputs_MinOpeningTimeIN6.addTextChangedListener(etMinOpeningTimeIN6TextWatcher);
		et_Inputs_MinClosingTimeIN6.setOnFocusChangeListener(this);
		et_Inputs_MinOpeningTimeIN6.setOnFocusChangeListener(this);
		spin_Inputs_InputFunctionsIN6.setOnTouchListener(this);
		spin_Inputs_PolarityIN6.setOnTouchListener(this);
		spin_Inputs_InputIsActiveIN6.setOnTouchListener(this);
		//IN7
		EditTextWatcherDouble etMinClosingTimeIN7TextWatcher = new EditTextWatcherDouble(et_Inputs_MinClosingTimeIN7, "0.1", "0", "25.5");
		et_Inputs_MinClosingTimeIN7.addTextChangedListener(etMinClosingTimeIN7TextWatcher);
		EditTextWatcherDouble etMinOpeningTimeIN7TextWatcher = new EditTextWatcherDouble(et_Inputs_MinOpeningTimeIN7, "0.1", "0", "25.5");
		et_Inputs_MinOpeningTimeIN7.addTextChangedListener(etMinOpeningTimeIN7TextWatcher);
		et_Inputs_MinClosingTimeIN7.setOnFocusChangeListener(this);
		et_Inputs_MinOpeningTimeIN7.setOnFocusChangeListener(this);
		spin_Inputs_InputFunctionsIN7.setOnTouchListener(this);
		spin_Inputs_PolarityIN7.setOnTouchListener(this);
		spin_Inputs_InputIsActiveIN7.setOnTouchListener(this);
		//IN8
		EditTextWatcherDouble etMinClosingTimeIN8TextWatcher = new EditTextWatcherDouble(et_Inputs_MinClosingTimeIN8, "0.1", "0", "25.5");
		et_Inputs_MinClosingTimeIN8.addTextChangedListener(etMinClosingTimeIN8TextWatcher);
		EditTextWatcherDouble etMinOpeningTimeIN8TextWatcher = new EditTextWatcherDouble(et_Inputs_MinOpeningTimeIN8, "0.1", "0", "25.5");
		et_Inputs_MinOpeningTimeIN8.addTextChangedListener(etMinOpeningTimeIN8TextWatcher);
		et_Inputs_MinClosingTimeIN8.setOnFocusChangeListener(this);
		et_Inputs_MinOpeningTimeIN8.setOnFocusChangeListener(this);
		spin_Inputs_InputFunctionsIN8.setOnTouchListener(this);
		spin_Inputs_PolarityIN8.setOnTouchListener(this);
		spin_Inputs_InputIsActiveIN8.setOnTouchListener(this);
	}
	
	private void saveState()
	{
		if(m_stateholder == null)
			m_stateholder = MainActivity.stateholderInputs;
		
		//IN1
		m_stateholder.setCbInputsIn1Enabled(cb_Inputs_IN1.isEnabled());
		m_stateholder.setCbInputsIn1Checked(cb_Inputs_IN1.isChecked());
		m_stateholder.setSpinInputFunctionsIN1Enabled(spin_Inputs_InputFunctionsIN1.isEnabled());
		m_stateholder.setSpinInputFunctionsIN1Value(spin_Inputs_InputFunctionsIN1.getSelectedItemPosition());
		m_stateholder.setSpinPolarityIN1Enabled(spin_Inputs_PolarityIN1.isEnabled());
		m_stateholder.setSpinPolarityIN1Value(spin_Inputs_PolarityIN1.getSelectedItemPosition());
		m_stateholder.setSpinInputIsActiveIN1Enabled(spin_Inputs_InputIsActiveIN1.isEnabled());
		m_stateholder.setSpinInputIsActiveIN1Value(spin_Inputs_InputIsActiveIN1.getSelectedItemPosition());
		m_stateholder.setEtMinClosingTimeIN1Enabled(et_Inputs_MinClosingTimeIN1.isEnabled());
		m_stateholder.setEtMinClosingTimeIN1Value(et_Inputs_MinClosingTimeIN1.getText().toString());
		m_stateholder.setEtMinOpeningTimeIN1Enabled(et_Inputs_MinOpeningTimeIN1.isEnabled());
		m_stateholder.setEtMinOpeningTimeIN1Value(et_Inputs_MinOpeningTimeIN1.getText().toString());
		
		//IN2
		m_stateholder.setCbInputsIn2Enabled(cb_Inputs_IN2.isEnabled());
		m_stateholder.setCbInputsIn2Checked(cb_Inputs_IN2.isChecked());
		m_stateholder.setSpinInputFunctionsIN2Enabled(spin_Inputs_InputFunctionsIN2.isEnabled());
		m_stateholder.setSpinInputFunctionsIN2Value(spin_Inputs_InputFunctionsIN2.getSelectedItemPosition());
		m_stateholder.setSpinPolarityIN2Enabled(spin_Inputs_PolarityIN2.isEnabled());
		m_stateholder.setSpinPolarityIN2Value(spin_Inputs_PolarityIN2.getSelectedItemPosition());
		m_stateholder.setSpinInputIsActiveIN2Enabled(spin_Inputs_InputIsActiveIN2.isEnabled());
		m_stateholder.setSpinInputIsActiveIN2Value(spin_Inputs_InputIsActiveIN2.getSelectedItemPosition());
		m_stateholder.setEtMinClosingTimeIN2Enabled(et_Inputs_MinClosingTimeIN2.isEnabled());
		m_stateholder.setEtMinClosingTimeIN2Value(et_Inputs_MinClosingTimeIN2.getText().toString());
		m_stateholder.setEtMinOpeningTimeIN2Enabled(et_Inputs_MinOpeningTimeIN2.isEnabled());
		m_stateholder.setEtMinOpeningTimeIN2Value(et_Inputs_MinOpeningTimeIN2.getText().toString());
		
		//IN3
		m_stateholder.setCbInputsIn3Enabled(cb_Inputs_IN3.isEnabled());
		m_stateholder.setCbInputsIn3Checked(cb_Inputs_IN3.isChecked());
		m_stateholder.setSpinInputFunctionsIN3Enabled(spin_Inputs_InputFunctionsIN3.isEnabled());
		m_stateholder.setSpinInputFunctionsIN3Value(spin_Inputs_InputFunctionsIN3.getSelectedItemPosition());
		m_stateholder.setSpinPolarityIN3Enabled(spin_Inputs_PolarityIN3.isEnabled());
		m_stateholder.setSpinPolarityIN3Value(spin_Inputs_PolarityIN3.getSelectedItemPosition());
		m_stateholder.setSpinInputIsActiveIN3Enabled(spin_Inputs_InputIsActiveIN3.isEnabled());
		m_stateholder.setSpinInputIsActiveIN3Value(spin_Inputs_InputIsActiveIN3.getSelectedItemPosition());
		m_stateholder.setEtMinClosingTimeIN3Enabled(et_Inputs_MinClosingTimeIN3.isEnabled());
		m_stateholder.setEtMinClosingTimeIN3Value(et_Inputs_MinClosingTimeIN3.getText().toString());
		m_stateholder.setEtMinOpeningTimeIN3Enabled(et_Inputs_MinOpeningTimeIN3.isEnabled());
		m_stateholder.setEtMinOpeningTimeIN3Value(et_Inputs_MinOpeningTimeIN3.getText().toString());
		
		//IN4
		m_stateholder.setCbInputsIn4Enabled(cb_Inputs_IN4.isEnabled());
		m_stateholder.setCbInputsIn4Checked(cb_Inputs_IN4.isChecked());
		m_stateholder.setSpinInputFunctionsIN4Enabled(spin_Inputs_InputFunctionsIN4.isEnabled());
		m_stateholder.setSpinInputFunctionsIN4Value(spin_Inputs_InputFunctionsIN4.getSelectedItemPosition());
		m_stateholder.setSpinPolarityIN4Enabled(spin_Inputs_PolarityIN4.isEnabled());
		m_stateholder.setSpinPolarityIN4Value(spin_Inputs_PolarityIN4.getSelectedItemPosition());
		m_stateholder.setSpinInputIsActiveIN4Enabled(spin_Inputs_InputIsActiveIN4.isEnabled());
		m_stateholder.setSpinInputIsActiveIN4Value(spin_Inputs_InputIsActiveIN4.getSelectedItemPosition());
		m_stateholder.setEtMinClosingTimeIN4Enabled(et_Inputs_MinClosingTimeIN4.isEnabled());
		m_stateholder.setEtMinClosingTimeIN4Value(et_Inputs_MinClosingTimeIN4.getText().toString());
		m_stateholder.setEtMinOpeningTimeIN4Enabled(et_Inputs_MinOpeningTimeIN4.isEnabled());
		m_stateholder.setEtMinOpeningTimeIN4Value(et_Inputs_MinOpeningTimeIN4.getText().toString());
		
		//IN5
		m_stateholder.setCbInputsIn5Enabled(cb_Inputs_IN5.isEnabled());
		m_stateholder.setCbInputsIn5Checked(cb_Inputs_IN5.isChecked());
		m_stateholder.setSpinInputFunctionsIN5Enabled(spin_Inputs_InputFunctionsIN5.isEnabled());
		m_stateholder.setSpinInputFunctionsIN5Value(spin_Inputs_InputFunctionsIN5.getSelectedItemPosition());
		m_stateholder.setSpinPolarityIN5Enabled(spin_Inputs_PolarityIN5.isEnabled());
		m_stateholder.setSpinPolarityIN5Value(spin_Inputs_PolarityIN5.getSelectedItemPosition());
		m_stateholder.setSpinInputIsActiveIN5Enabled(spin_Inputs_InputIsActiveIN5.isEnabled());
		m_stateholder.setSpinInputIsActiveIN5Value(spin_Inputs_InputIsActiveIN5.getSelectedItemPosition());
		m_stateholder.setEtMinClosingTimeIN5Enabled(et_Inputs_MinClosingTimeIN5.isEnabled());
		m_stateholder.setEtMinClosingTimeIN5Value(et_Inputs_MinClosingTimeIN5.getText().toString());
		m_stateholder.setEtMinOpeningTimeIN5Enabled(et_Inputs_MinOpeningTimeIN5.isEnabled());
		m_stateholder.setEtMinOpeningTimeIN5Value(et_Inputs_MinOpeningTimeIN5.getText().toString());
		
		//IN6
		m_stateholder.setCbInputsIn6Enabled(cb_Inputs_IN6.isEnabled());
		m_stateholder.setCbInputsIn6Checked(cb_Inputs_IN6.isChecked());
		m_stateholder.setSpinInputFunctionsIN6Enabled(spin_Inputs_InputFunctionsIN6.isEnabled());
		m_stateholder.setSpinInputFunctionsIN6Value(spin_Inputs_InputFunctionsIN6.getSelectedItemPosition());
		m_stateholder.setSpinPolarityIN6Enabled(spin_Inputs_PolarityIN6.isEnabled());
		m_stateholder.setSpinPolarityIN6Value(spin_Inputs_PolarityIN6.getSelectedItemPosition());
		m_stateholder.setSpinInputIsActiveIN6Enabled(spin_Inputs_InputIsActiveIN6.isEnabled());
		m_stateholder.setSpinInputIsActiveIN6Value(spin_Inputs_InputIsActiveIN6.getSelectedItemPosition());
		m_stateholder.setEtMinClosingTimeIN6Enabled(et_Inputs_MinClosingTimeIN6.isEnabled());
		m_stateholder.setEtMinClosingTimeIN6Value(et_Inputs_MinClosingTimeIN6.getText().toString());
		m_stateholder.setEtMinOpeningTimeIN6Enabled(et_Inputs_MinOpeningTimeIN6.isEnabled());
		m_stateholder.setEtMinOpeningTimeIN6Value(et_Inputs_MinOpeningTimeIN6.getText().toString());
		
		//IN7
		m_stateholder.setCbInputsIn7Enabled(cb_Inputs_IN7.isEnabled());
		m_stateholder.setCbInputsIn7Checked(cb_Inputs_IN7.isChecked());
		m_stateholder.setSpinInputFunctionsIN7Enabled(spin_Inputs_InputFunctionsIN7.isEnabled());
		m_stateholder.setSpinInputFunctionsIN7Value(spin_Inputs_InputFunctionsIN7.getSelectedItemPosition());
		m_stateholder.setSpinPolarityIN7Enabled(spin_Inputs_PolarityIN7.isEnabled());
		m_stateholder.setSpinPolarityIN7Value(spin_Inputs_PolarityIN7.getSelectedItemPosition());
		m_stateholder.setSpinInputIsActiveIN7Enabled(spin_Inputs_InputIsActiveIN7.isEnabled());
		m_stateholder.setSpinInputIsActiveIN7Value(spin_Inputs_InputIsActiveIN7.getSelectedItemPosition());
		m_stateholder.setEtMinClosingTimeIN7Enabled(et_Inputs_MinClosingTimeIN7.isEnabled());
		m_stateholder.setEtMinClosingTimeIN7Value(et_Inputs_MinClosingTimeIN7.getText().toString());
		m_stateholder.setEtMinOpeningTimeIN7Enabled(et_Inputs_MinOpeningTimeIN7.isEnabled());
		m_stateholder.setEtMinOpeningTimeIN7Value(et_Inputs_MinOpeningTimeIN7.getText().toString());
		
		//IN8
		m_stateholder.setCbInputsIn8Enabled(cb_Inputs_IN8.isEnabled());
		m_stateholder.setCbInputsIn8Checked(cb_Inputs_IN8.isChecked());
		m_stateholder.setSpinInputFunctionsIN8Enabled(spin_Inputs_InputFunctionsIN8.isEnabled());
		m_stateholder.setSpinInputFunctionsIN8Value(spin_Inputs_InputFunctionsIN8.getSelectedItemPosition());
		m_stateholder.setSpinPolarityIN8Enabled(spin_Inputs_PolarityIN8.isEnabled());
		m_stateholder.setSpinPolarityIN8Value(spin_Inputs_PolarityIN8.getSelectedItemPosition());
		m_stateholder.setSpinInputIsActiveIN8Enabled(spin_Inputs_InputIsActiveIN8.isEnabled());
		m_stateholder.setSpinInputIsActiveIN8Value(spin_Inputs_InputIsActiveIN8.getSelectedItemPosition());
		m_stateholder.setEtMinClosingTimeIN8Enabled(et_Inputs_MinClosingTimeIN8.isEnabled());
		m_stateholder.setEtMinClosingTimeIN8Value(et_Inputs_MinClosingTimeIN8.getText().toString());
		m_stateholder.setEtMinOpeningTimeIN8Enabled(et_Inputs_MinOpeningTimeIN8.isEnabled());
		m_stateholder.setEtMinOpeningTimeIN8Value(et_Inputs_MinOpeningTimeIN8.getText().toString());
	}
	
	private void restoreState()
	{
		if (m_stateholder != null) 
		{
			//IN1
			cb_Inputs_IN1.setEnabled(m_stateholder.getCbInputsIn1Enabled());
			cb_Inputs_IN1.setChecked(m_stateholder.getCbInputsIn1Checked());
			spin_Inputs_InputFunctionsIN1.setEnabled(m_stateholder.getSpinInputFunctionsIN1Enabled());
			spin_Inputs_InputFunctionsIN1.setSelection(m_stateholder.getSpinInputFunctionsIN1Value());
			spin_Inputs_PolarityIN1.setEnabled(m_stateholder.getSpinPolarityIN1Enabled());
			spin_Inputs_PolarityIN1.setSelection(m_stateholder.getSpinPolarityIN1Value());
			spin_Inputs_InputIsActiveIN1.setEnabled(m_stateholder.getSpinInputIsActiveIN1Enabled());
			spin_Inputs_InputIsActiveIN1.setSelection(m_stateholder.getSpinInputIsActiveIN1Value());
			et_Inputs_MinClosingTimeIN1.setEnabled(m_stateholder.getEtMinClosingTimeIN1Enabled());
			et_Inputs_MinClosingTimeIN1.setText(m_stateholder.getEtMinClosingTimeIN1Value());
			et_Inputs_MinOpeningTimeIN1.setEnabled(m_stateholder.getEtMinOpeningTimeIN1Enabled());
			et_Inputs_MinOpeningTimeIN1.setText(m_stateholder.getEtMinOpeningTimeIN1Value());
			
			//IN2
			cb_Inputs_IN2.setEnabled(m_stateholder.getCbInputsIn2Enabled());
			cb_Inputs_IN2.setChecked(m_stateholder.getCbInputsIn2Checked());
			spin_Inputs_InputFunctionsIN2.setEnabled(m_stateholder.getSpinInputFunctionsIN2Enabled());
			spin_Inputs_InputFunctionsIN2.setSelection(m_stateholder.getSpinInputFunctionsIN2Value());
			spin_Inputs_PolarityIN2.setEnabled(m_stateholder.getSpinPolarityIN2Enabled());
			spin_Inputs_PolarityIN2.setSelection(m_stateholder.getSpinPolarityIN2Value());
			spin_Inputs_InputIsActiveIN2.setEnabled(m_stateholder.getSpinInputIsActiveIN2Enabled());
			spin_Inputs_InputIsActiveIN2.setSelection(m_stateholder.getSpinInputIsActiveIN2Value());
			et_Inputs_MinClosingTimeIN2.setEnabled(m_stateholder.getEtMinClosingTimeIN2Enabled());
			et_Inputs_MinClosingTimeIN2.setText(m_stateholder.getEtMinClosingTimeIN2Value());
			et_Inputs_MinOpeningTimeIN2.setEnabled(m_stateholder.getEtMinOpeningTimeIN2Enabled());
			et_Inputs_MinOpeningTimeIN2.setText(m_stateholder.getEtMinOpeningTimeIN2Value());
			
			//IN3
			cb_Inputs_IN3.setEnabled(m_stateholder.getCbInputsIn3Enabled());
			cb_Inputs_IN3.setChecked(m_stateholder.getCbInputsIn3Checked());
			spin_Inputs_InputFunctionsIN3.setEnabled(m_stateholder.getSpinInputFunctionsIN3Enabled());
			spin_Inputs_InputFunctionsIN3.setSelection(m_stateholder.getSpinInputFunctionsIN3Value());
			spin_Inputs_PolarityIN3.setEnabled(m_stateholder.getSpinPolarityIN3Enabled());
			spin_Inputs_PolarityIN3.setSelection(m_stateholder.getSpinPolarityIN3Value());
			spin_Inputs_InputIsActiveIN3.setEnabled(m_stateholder.getSpinInputIsActiveIN3Enabled());
			spin_Inputs_InputIsActiveIN3.setSelection(m_stateholder.getSpinInputIsActiveIN3Value());
			et_Inputs_MinClosingTimeIN3.setEnabled(m_stateholder.getEtMinClosingTimeIN3Enabled());
			et_Inputs_MinClosingTimeIN3.setText(m_stateholder.getEtMinClosingTimeIN3Value());
			et_Inputs_MinOpeningTimeIN3.setEnabled(m_stateholder.getEtMinOpeningTimeIN3Enabled());
			et_Inputs_MinOpeningTimeIN3.setText(m_stateholder.getEtMinOpeningTimeIN3Value());
			
			//IN4
			cb_Inputs_IN4.setEnabled(m_stateholder.getCbInputsIn4Enabled());
			cb_Inputs_IN4.setChecked(m_stateholder.getCbInputsIn4Checked());
			spin_Inputs_InputFunctionsIN4.setEnabled(m_stateholder.getSpinInputFunctionsIN4Enabled());
			spin_Inputs_InputFunctionsIN4.setSelection(m_stateholder.getSpinInputFunctionsIN4Value());
			spin_Inputs_PolarityIN4.setEnabled(m_stateholder.getSpinPolarityIN4Enabled());
			spin_Inputs_PolarityIN4.setSelection(m_stateholder.getSpinPolarityIN4Value());
			spin_Inputs_InputIsActiveIN4.setEnabled(m_stateholder.getSpinInputIsActiveIN4Enabled());
			spin_Inputs_InputIsActiveIN4.setSelection(m_stateholder.getSpinInputIsActiveIN4Value());
			et_Inputs_MinClosingTimeIN4.setEnabled(m_stateholder.getEtMinClosingTimeIN4Enabled());
			et_Inputs_MinClosingTimeIN4.setText(m_stateholder.getEtMinClosingTimeIN4Value());
			et_Inputs_MinOpeningTimeIN4.setEnabled(m_stateholder.getEtMinOpeningTimeIN4Enabled());
			et_Inputs_MinOpeningTimeIN4.setText(m_stateholder.getEtMinOpeningTimeIN4Value());
			
			//IN5
			cb_Inputs_IN5.setEnabled(m_stateholder.getCbInputsIn5Enabled());
			cb_Inputs_IN5.setChecked(m_stateholder.getCbInputsIn5Checked());
			spin_Inputs_InputFunctionsIN5.setEnabled(m_stateholder.getSpinInputFunctionsIN5Enabled());
			spin_Inputs_InputFunctionsIN5.setSelection(m_stateholder.getSpinInputFunctionsIN5Value());
			spin_Inputs_PolarityIN5.setEnabled(m_stateholder.getSpinPolarityIN5Enabled());
			spin_Inputs_PolarityIN5.setSelection(m_stateholder.getSpinPolarityIN5Value());
			spin_Inputs_InputIsActiveIN5.setEnabled(m_stateholder.getSpinInputIsActiveIN5Enabled());
			spin_Inputs_InputIsActiveIN5.setSelection(m_stateholder.getSpinInputIsActiveIN5Value());
			et_Inputs_MinClosingTimeIN5.setEnabled(m_stateholder.getEtMinClosingTimeIN5Enabled());
			et_Inputs_MinClosingTimeIN5.setText(m_stateholder.getEtMinClosingTimeIN5Value());
			et_Inputs_MinOpeningTimeIN5.setEnabled(m_stateholder.getEtMinOpeningTimeIN5Enabled());
			et_Inputs_MinOpeningTimeIN5.setText(m_stateholder.getEtMinOpeningTimeIN5Value());
			
			//IN6
			cb_Inputs_IN6.setEnabled(m_stateholder.getCbInputsIn6Enabled());
			cb_Inputs_IN6.setChecked(m_stateholder.getCbInputsIn6Checked());
			spin_Inputs_InputFunctionsIN6.setEnabled(m_stateholder.getSpinInputFunctionsIN6Enabled());
			spin_Inputs_InputFunctionsIN6.setSelection(m_stateholder.getSpinInputFunctionsIN6Value());
			spin_Inputs_PolarityIN6.setEnabled(m_stateholder.getSpinPolarityIN6Enabled());
			spin_Inputs_PolarityIN6.setSelection(m_stateholder.getSpinPolarityIN6Value());
			spin_Inputs_InputIsActiveIN6.setEnabled(m_stateholder.getSpinInputIsActiveIN6Enabled());
			spin_Inputs_InputIsActiveIN6.setSelection(m_stateholder.getSpinInputIsActiveIN6Value());
			et_Inputs_MinClosingTimeIN6.setEnabled(m_stateholder.getEtMinClosingTimeIN6Enabled());
			et_Inputs_MinClosingTimeIN6.setText(m_stateholder.getEtMinClosingTimeIN6Value());
			et_Inputs_MinOpeningTimeIN6.setEnabled(m_stateholder.getEtMinOpeningTimeIN6Enabled());
			et_Inputs_MinOpeningTimeIN6.setText(m_stateholder.getEtMinOpeningTimeIN6Value());
			
			//IN7
			cb_Inputs_IN7.setEnabled(m_stateholder.getCbInputsIn7Enabled());
			cb_Inputs_IN7.setChecked(m_stateholder.getCbInputsIn7Checked());
			spin_Inputs_InputFunctionsIN7.setEnabled(m_stateholder.getSpinInputFunctionsIN7Enabled());
			spin_Inputs_InputFunctionsIN7.setSelection(m_stateholder.getSpinInputFunctionsIN7Value());
			spin_Inputs_PolarityIN7.setEnabled(m_stateholder.getSpinPolarityIN7Enabled());
			spin_Inputs_PolarityIN7.setSelection(m_stateholder.getSpinPolarityIN7Value());
			spin_Inputs_InputIsActiveIN7.setEnabled(m_stateholder.getSpinInputIsActiveIN7Enabled());
			spin_Inputs_InputIsActiveIN7.setSelection(m_stateholder.getSpinInputIsActiveIN7Value());
			et_Inputs_MinClosingTimeIN7.setEnabled(m_stateholder.getEtMinClosingTimeIN7Enabled());
			et_Inputs_MinClosingTimeIN7.setText(m_stateholder.getEtMinClosingTimeIN7Value());
			et_Inputs_MinOpeningTimeIN7.setEnabled(m_stateholder.getEtMinOpeningTimeIN7Enabled());
			et_Inputs_MinOpeningTimeIN7.setText(m_stateholder.getEtMinOpeningTimeIN7Value());
			
			//IN8
			cb_Inputs_IN8.setEnabled(m_stateholder.getCbInputsIn8Enabled());
			cb_Inputs_IN8.setChecked(m_stateholder.getCbInputsIn8Checked());
			spin_Inputs_InputFunctionsIN8.setEnabled(m_stateholder.getSpinInputFunctionsIN8Enabled());
			spin_Inputs_InputFunctionsIN8.setSelection(m_stateholder.getSpinInputFunctionsIN8Value());
			spin_Inputs_PolarityIN8.setEnabled(m_stateholder.getSpinPolarityIN8Enabled());
			spin_Inputs_PolarityIN8.setSelection(m_stateholder.getSpinPolarityIN8Value());
			spin_Inputs_InputIsActiveIN8.setEnabled(m_stateholder.getSpinInputIsActiveIN8Enabled());
			spin_Inputs_InputIsActiveIN8.setSelection(m_stateholder.getSpinInputIsActiveIN8Value());
			et_Inputs_MinClosingTimeIN8.setEnabled(m_stateholder.getEtMinClosingTimeIN8Enabled());
			et_Inputs_MinClosingTimeIN8.setText(m_stateholder.getEtMinClosingTimeIN8Value());
			et_Inputs_MinOpeningTimeIN8.setEnabled(m_stateholder.getEtMinOpeningTimeIN8Enabled());
			et_Inputs_MinOpeningTimeIN8.setText(m_stateholder.getEtMinOpeningTimeIN8Value());
		}
		else
		{
			//IN1
			et_Inputs_MinClosingTimeIN1.setText("0.1");
			et_Inputs_MinOpeningTimeIN1.setText("0.1");
			spin_Inputs_InputFunctionsIN1.setEnabled(false);
			spin_Inputs_PolarityIN1.setEnabled(false);
			spin_Inputs_InputIsActiveIN1.setEnabled(false);
			et_Inputs_MinClosingTimeIN1.setEnabled(false);
			et_Inputs_MinOpeningTimeIN1.setEnabled(false);
			
			//IN2
			et_Inputs_MinClosingTimeIN2.setText("0.1");
			et_Inputs_MinOpeningTimeIN2.setText("0.1");
			spin_Inputs_InputFunctionsIN2.setEnabled(false);
			spin_Inputs_PolarityIN2.setEnabled(false);
			spin_Inputs_InputIsActiveIN2.setEnabled(false);
			et_Inputs_MinClosingTimeIN2.setEnabled(false);
			et_Inputs_MinOpeningTimeIN2.setEnabled(false);
			
			//IN3
			et_Inputs_MinClosingTimeIN3.setText("0.1");
			et_Inputs_MinOpeningTimeIN3.setText("0.1");
			spin_Inputs_InputFunctionsIN3.setEnabled(false);
			spin_Inputs_PolarityIN3.setEnabled(false);
			spin_Inputs_InputIsActiveIN3.setEnabled(false);
			et_Inputs_MinClosingTimeIN3.setEnabled(false);
			et_Inputs_MinOpeningTimeIN3.setEnabled(false);
			
			//IN4
			et_Inputs_MinClosingTimeIN4.setText("0.1");
			et_Inputs_MinOpeningTimeIN4.setText("0.1");
			spin_Inputs_InputFunctionsIN4.setEnabled(false);
			spin_Inputs_PolarityIN4.setEnabled(false);
			spin_Inputs_InputIsActiveIN4.setEnabled(false);
			et_Inputs_MinClosingTimeIN4.setEnabled(false);
			et_Inputs_MinOpeningTimeIN4.setEnabled(false);
			
			//IN5
			et_Inputs_MinClosingTimeIN5.setText("0.1");
			et_Inputs_MinOpeningTimeIN5.setText("0.1");
			spin_Inputs_InputFunctionsIN5.setEnabled(false);
			spin_Inputs_PolarityIN5.setEnabled(false);
			spin_Inputs_InputIsActiveIN5.setEnabled(false);
			et_Inputs_MinClosingTimeIN5.setEnabled(false);
			et_Inputs_MinOpeningTimeIN5.setEnabled(false);
			
			//IN6
			et_Inputs_MinClosingTimeIN6.setText("0.1");
			et_Inputs_MinOpeningTimeIN6.setText("0.1");
			spin_Inputs_InputFunctionsIN6.setEnabled(false);
			spin_Inputs_PolarityIN6.setEnabled(false);
			spin_Inputs_InputIsActiveIN6.setEnabled(false);
			et_Inputs_MinClosingTimeIN6.setEnabled(false);
			et_Inputs_MinOpeningTimeIN6.setEnabled(false);
			
			//IN7
			et_Inputs_MinClosingTimeIN7.setText("0.1");
			et_Inputs_MinOpeningTimeIN7.setText("0.1");
			spin_Inputs_InputFunctionsIN7.setEnabled(false);
			spin_Inputs_PolarityIN7.setEnabled(false);
			spin_Inputs_InputIsActiveIN7.setEnabled(false);
			et_Inputs_MinClosingTimeIN7.setEnabled(false);
			et_Inputs_MinOpeningTimeIN7.setEnabled(false);
			
			//IN8
			et_Inputs_MinClosingTimeIN8.setText("0.1");
			et_Inputs_MinOpeningTimeIN8.setText("0.1");
			spin_Inputs_InputFunctionsIN8.setEnabled(false);
			spin_Inputs_PolarityIN8.setEnabled(false);
			spin_Inputs_InputIsActiveIN8.setEnabled(false);
			et_Inputs_MinClosingTimeIN8.setEnabled(false);
			et_Inputs_MinOpeningTimeIN8.setEnabled(false);
		}
	}
	
	
	
//================================= LISENERS ==============================================
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) 
	{
		switch(buttonView.getId())
		{
			//1)
			case R.id.cb_Inputs_IN1:
			{
				spin_Inputs_InputFunctionsIN1.setEnabled(isChecked);
				spin_Inputs_PolarityIN1.setEnabled(isChecked);
				spin_Inputs_InputIsActiveIN1.setEnabled(isChecked);
				et_Inputs_MinClosingTimeIN1.setEnabled(isChecked);
				et_Inputs_MinOpeningTimeIN1.setEnabled(isChecked);
				break;
			}
			//2)
			case R.id.cb_Inputs_IN2:
			{
				spin_Inputs_InputFunctionsIN2.setEnabled(isChecked);
				spin_Inputs_PolarityIN2.setEnabled(isChecked);
				spin_Inputs_InputIsActiveIN2.setEnabled(isChecked);
				et_Inputs_MinClosingTimeIN2.setEnabled(isChecked);
				et_Inputs_MinOpeningTimeIN2.setEnabled(isChecked);
				break;
			}
			//3)
			case R.id.cb_Inputs_IN3:
			{
				spin_Inputs_InputFunctionsIN3.setEnabled(isChecked);
				spin_Inputs_PolarityIN3.setEnabled(isChecked);
				spin_Inputs_InputIsActiveIN3.setEnabled(isChecked);
				et_Inputs_MinClosingTimeIN3.setEnabled(isChecked);
				et_Inputs_MinOpeningTimeIN3.setEnabled(isChecked);
				break;
			}
			//4)
			case R.id.cb_Inputs_IN4:
			{
				spin_Inputs_InputFunctionsIN4.setEnabled(isChecked);
				spin_Inputs_PolarityIN4.setEnabled(isChecked);
				spin_Inputs_InputIsActiveIN4.setEnabled(isChecked);
				et_Inputs_MinClosingTimeIN4.setEnabled(isChecked);
				et_Inputs_MinOpeningTimeIN4.setEnabled(isChecked);
				break;
			}
			//5)
			case R.id.cb_Inputs_IN5:
			{
				spin_Inputs_InputFunctionsIN5.setEnabled(isChecked);
				spin_Inputs_PolarityIN5.setEnabled(isChecked);
				spin_Inputs_InputIsActiveIN5.setEnabled(isChecked);
				et_Inputs_MinClosingTimeIN5.setEnabled(isChecked);
				et_Inputs_MinOpeningTimeIN5.setEnabled(isChecked);
				break;
			}
			//6)
			case R.id.cb_Inputs_IN6:
			{	
				spin_Inputs_InputFunctionsIN6.setEnabled(isChecked);
				spin_Inputs_PolarityIN6.setEnabled(isChecked);
				spin_Inputs_InputIsActiveIN6.setEnabled(isChecked);
				et_Inputs_MinClosingTimeIN6.setEnabled(isChecked);
				et_Inputs_MinOpeningTimeIN6.setEnabled(isChecked);
				break;
			}
			//7)
			case R.id.cb_Inputs_IN7:
			{
				spin_Inputs_InputFunctionsIN7.setEnabled(isChecked);
				spin_Inputs_PolarityIN7.setEnabled(isChecked);
				spin_Inputs_InputIsActiveIN7.setEnabled(isChecked);
				et_Inputs_MinClosingTimeIN7.setEnabled(isChecked);
				et_Inputs_MinOpeningTimeIN7.setEnabled(isChecked);
				break;
			}
			//8)
			case R.id.cb_Inputs_IN8:
			{
				spin_Inputs_InputFunctionsIN8.setEnabled(isChecked);
				spin_Inputs_PolarityIN8.setEnabled(isChecked);
				spin_Inputs_InputIsActiveIN8.setEnabled(isChecked);
				et_Inputs_MinClosingTimeIN8.setEnabled(isChecked);
				et_Inputs_MinOpeningTimeIN8.setEnabled(isChecked);
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
		setTitle(R.string.btn_Main_Inputs);
		//IN1
		cb_Inputs_IN1.setText(R.string.cb_Inputs_IN1);
		TextView tv_Inputs_InputFunctionsIN1 = (TextView) findViewById(R.id.tv_Inputs_InputFunctionsIN1);
		tv_Inputs_InputFunctionsIN1.setText(R.string.tv_Inputs_InputFunction);
		int pos_Inputs_InputFunctionsIN1 = spin_Inputs_InputFunctionsIN1.getSelectedItemPosition();
		ArrayAdapter<String> inputFunctionMenuIN1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_function_N1));
		spin_Inputs_InputFunctionsIN1.setAdapter(inputFunctionMenuIN1);
		spin_Inputs_InputFunctionsIN1.setSelection(pos_Inputs_InputFunctionsIN1);
		TextView tv_Inputs_PolarityIN1 = (TextView) findViewById(R.id.tv_Inputs_PolarityIN1);
		tv_Inputs_PolarityIN1.setText(R.string.tv_Inputs_Polarity);
		int pos_Inputs_PolarityIN1 = spin_Inputs_PolarityIN1.getSelectedItemPosition();
		ArrayAdapter<String> polarityMenuIN1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.polarity));
		spin_Inputs_PolarityIN1.setAdapter(polarityMenuIN1);
		spin_Inputs_PolarityIN1.setSelection(pos_Inputs_PolarityIN1);
		TextView tv_Inputs_InputIsActiveIN1 = (TextView) findViewById(R.id.tv_Inputs_InputIsActiveIN1);
		tv_Inputs_InputIsActiveIN1.setText(R.string.tv_Inputs_InputIsActive);
		int pos_Inputs_InputIsActiveIN1 = spin_Inputs_InputIsActiveIN1.getSelectedItemPosition();
		ArrayAdapter<String> inputIsActiveMenuIN1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_is_active));
		spin_Inputs_InputIsActiveIN1.setAdapter(inputIsActiveMenuIN1);
		spin_Inputs_InputIsActiveIN1.setSelection(pos_Inputs_InputIsActiveIN1);
		TextView tv_Inputs_MinClosingTime1IN1 = (TextView) findViewById(R.id.tv_Inputs_MinClosingTime1IN1);
		tv_Inputs_MinClosingTime1IN1.setText(R.string.tv_Inputs_MinClosingTime);
		TextView tv_Inputs_MinClosingTime2IN1 = (TextView) findViewById(R.id.tv_Inputs_MinClosingTime2IN1);
		tv_Inputs_MinClosingTime2IN1.setText(R.string.tv_Inputs_Seconds);
		TextView tv_Inputs_MinOpeningTime1IN1 = (TextView) findViewById(R.id.tv_Inputs_MinOpeningTime1IN1);
		tv_Inputs_MinOpeningTime1IN1.setText(R.string.tv_Inputs_MinOpeningTime);
		TextView tv_Inputs_MinOpeningTime2IN1 = (TextView) findViewById(R.id.tv_Inputs_MinOpeningTime2IN1);
		tv_Inputs_MinOpeningTime2IN1.setText(R.string.tv_Inputs_Seconds);
		//IN2
		cb_Inputs_IN2.setText(R.string.cb_Inputs_IN2);
		TextView tv_Inputs_InputFunctionsIN2 = (TextView) findViewById(R.id.tv_Inputs_InputFunctionsIN2);
		tv_Inputs_InputFunctionsIN2.setText(R.string.tv_Inputs_InputFunction);
		int pos_Inputs_InputFunctionsIN2 = spin_Inputs_InputFunctionsIN2.getSelectedItemPosition();
		ArrayAdapter<String> inputFunctionMenuIN2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_function_N2));
		spin_Inputs_InputFunctionsIN2.setAdapter(inputFunctionMenuIN2);
		spin_Inputs_InputFunctionsIN2.setSelection(pos_Inputs_InputFunctionsIN2);
		TextView tv_Inputs_PolarityIN2 = (TextView) findViewById(R.id.tv_Inputs_PolarityIN2);
		tv_Inputs_PolarityIN2.setText(R.string.tv_Inputs_Polarity);
		int pos_Inputs_PolarityIN2 = spin_Inputs_PolarityIN2.getSelectedItemPosition();
		ArrayAdapter<String> polarityMenuIN2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.polarity));
		spin_Inputs_PolarityIN2.setAdapter(polarityMenuIN2);
		spin_Inputs_PolarityIN2.setSelection(pos_Inputs_PolarityIN2);
		TextView tv_Inputs_InputIsActiveIN2 = (TextView) findViewById(R.id.tv_Inputs_InputIsActiveIN2);
		tv_Inputs_InputIsActiveIN2.setText(R.string.tv_Inputs_InputIsActive);
		int pos_Inputs_InputIsActiveIN2 = spin_Inputs_InputIsActiveIN2.getSelectedItemPosition();
		ArrayAdapter<String> inputIsActiveMenuIN2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_is_active));
		spin_Inputs_InputIsActiveIN2.setAdapter(inputIsActiveMenuIN2);
		spin_Inputs_InputIsActiveIN2.setSelection(pos_Inputs_InputIsActiveIN2);
		TextView tv_Inputs_MinClosingTime1IN2 = (TextView) findViewById(R.id.tv_Inputs_MinClosingTime1IN2);
		tv_Inputs_MinClosingTime1IN2.setText(R.string.tv_Inputs_MinClosingTime);
		TextView tv_Inputs_MinClosingTime2IN2 = (TextView) findViewById(R.id.tv_Inputs_MinClosingTime2IN2);
		tv_Inputs_MinClosingTime2IN2.setText(R.string.tv_Inputs_Seconds);
		TextView tv_Inputs_MinOpeningTime1IN2 = (TextView) findViewById(R.id.tv_Inputs_MinOpeningTime1IN2);
		tv_Inputs_MinOpeningTime1IN2.setText(R.string.tv_Inputs_MinOpeningTime);
		TextView tv_Inputs_MinOpeningTime2IN2 = (TextView) findViewById(R.id.tv_Inputs_MinOpeningTime2IN2);
		tv_Inputs_MinOpeningTime2IN2.setText(R.string.tv_Inputs_Seconds);
		//IN3
		cb_Inputs_IN3.setText(R.string.cb_Inputs_IN3);
		TextView tv_Inputs_InputFunctionsIN3 = (TextView) findViewById(R.id.tv_Inputs_InputFunctionsIN3);
		tv_Inputs_InputFunctionsIN3.setText(R.string.tv_Inputs_InputFunction);
		int pos_Inputs_InputFunctionsIN3 = spin_Inputs_InputFunctionsIN3.getSelectedItemPosition();
		ArrayAdapter<String> inputFunctionMenuIN3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_function_N3));
		spin_Inputs_InputFunctionsIN3.setAdapter(inputFunctionMenuIN3);
		spin_Inputs_InputFunctionsIN3.setSelection(pos_Inputs_InputFunctionsIN3);
		TextView tv_Inputs_PolarityIN3 = (TextView) findViewById(R.id.tv_Inputs_PolarityIN3);
		tv_Inputs_PolarityIN3.setText(R.string.tv_Inputs_Polarity);
		int pos_Inputs_PolarityIN3 = spin_Inputs_PolarityIN3.getSelectedItemPosition();
		ArrayAdapter<String> polarityMenuIN3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.polarity));
		spin_Inputs_PolarityIN3.setAdapter(polarityMenuIN3);
		spin_Inputs_PolarityIN3.setSelection(pos_Inputs_PolarityIN3);
		TextView tv_Inputs_InputIsActiveIN3 = (TextView) findViewById(R.id.tv_Inputs_InputIsActiveIN3);
		tv_Inputs_InputIsActiveIN3.setText(R.string.tv_Inputs_InputIsActive);
		int pos_Inputs_InputIsActiveIN3 = spin_Inputs_InputIsActiveIN3.getSelectedItemPosition();
		ArrayAdapter<String> inputIsActiveMenuIN3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_is_active));
		spin_Inputs_InputIsActiveIN3.setAdapter(inputIsActiveMenuIN3);
		spin_Inputs_InputIsActiveIN3.setSelection(pos_Inputs_InputIsActiveIN3);
		TextView tv_Inputs_MinClosingTime1IN3 = (TextView) findViewById(R.id.tv_Inputs_MinClosingTime1IN3);
		tv_Inputs_MinClosingTime1IN3.setText(R.string.tv_Inputs_MinClosingTime);
		TextView tv_Inputs_MinClosingTime2IN3 = (TextView) findViewById(R.id.tv_Inputs_MinClosingTime2IN3);
		tv_Inputs_MinClosingTime2IN3.setText(R.string.tv_Inputs_Seconds);
		TextView tv_Inputs_MinOpeningTime1IN3 = (TextView) findViewById(R.id.tv_Inputs_MinOpeningTime1IN3);
		tv_Inputs_MinOpeningTime1IN3.setText(R.string.tv_Inputs_MinOpeningTime);
		TextView tv_Inputs_MinOpeningTime2IN3 = (TextView) findViewById(R.id.tv_Inputs_MinOpeningTime2IN3);
		tv_Inputs_MinOpeningTime2IN3.setText(R.string.tv_Inputs_Seconds);
		//IN4
		cb_Inputs_IN4.setText(R.string.cb_Inputs_IN4);
		TextView tv_Inputs_InputFunctionsIN4 = (TextView) findViewById(R.id.tv_Inputs_InputFunctionsIN4);
		tv_Inputs_InputFunctionsIN4.setText(R.string.tv_Inputs_InputFunction);
		int pos_Inputs_InputFunctionsIN4 = spin_Inputs_InputFunctionsIN4.getSelectedItemPosition();
		ArrayAdapter<String> inputFunctionMenuIN4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_function_N4));
		spin_Inputs_InputFunctionsIN4.setAdapter(inputFunctionMenuIN4);
		spin_Inputs_InputFunctionsIN4.setSelection(pos_Inputs_InputFunctionsIN4);
		TextView tv_Inputs_PolarityIN4 = (TextView) findViewById(R.id.tv_Inputs_PolarityIN4);
		tv_Inputs_PolarityIN4.setText(R.string.tv_Inputs_Polarity);
		int pos_Inputs_PolarityIN4 = spin_Inputs_PolarityIN4.getSelectedItemPosition();
		ArrayAdapter<String> polarityMenuIN4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.polarity));
		spin_Inputs_PolarityIN4.setAdapter(polarityMenuIN4);
		spin_Inputs_PolarityIN4.setSelection(pos_Inputs_PolarityIN4);
		TextView tv_Inputs_InputIsActiveIN4 = (TextView) findViewById(R.id.tv_Inputs_InputIsActiveIN4);
		tv_Inputs_InputIsActiveIN4.setText(R.string.tv_Inputs_InputIsActive);
		int pos_Inputs_InputIsActiveIN4 = spin_Inputs_InputIsActiveIN4.getSelectedItemPosition();
		ArrayAdapter<String> inputIsActiveMenuIN4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_is_active));
		spin_Inputs_InputIsActiveIN4.setAdapter(inputIsActiveMenuIN4);
		spin_Inputs_InputIsActiveIN4.setSelection(pos_Inputs_InputIsActiveIN4);
		TextView tv_Inputs_MinClosingTime1IN4 = (TextView) findViewById(R.id.tv_Inputs_MinClosingTime1IN4);
		tv_Inputs_MinClosingTime1IN4.setText(R.string.tv_Inputs_MinClosingTime);
		TextView tv_Inputs_MinClosingTime2IN4 = (TextView) findViewById(R.id.tv_Inputs_MinClosingTime2IN4);
		tv_Inputs_MinClosingTime2IN4.setText(R.string.tv_Inputs_Seconds);
		TextView tv_Inputs_MinOpeningTime1IN4 = (TextView) findViewById(R.id.tv_Inputs_MinOpeningTime1IN4);
		tv_Inputs_MinOpeningTime1IN4.setText(R.string.tv_Inputs_MinOpeningTime);
		TextView tv_Inputs_MinOpeningTime2IN4 = (TextView) findViewById(R.id.tv_Inputs_MinOpeningTime2IN4);
		tv_Inputs_MinOpeningTime2IN4.setText(R.string.tv_Inputs_Seconds);
		//IN5
		cb_Inputs_IN5.setText(R.string.cb_Inputs_IN5);
		TextView tv_Inputs_InputFunctionsIN5 = (TextView) findViewById(R.id.tv_Inputs_InputFunctionsIN5);
		tv_Inputs_InputFunctionsIN5.setText(R.string.tv_Inputs_InputFunction);
		int pos_Inputs_InputFunctionsIN5 = spin_Inputs_InputFunctionsIN5.getSelectedItemPosition();
		ArrayAdapter<String> inputFunctionMenuIN5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_function_N5));
		spin_Inputs_InputFunctionsIN5.setAdapter(inputFunctionMenuIN5);
		spin_Inputs_InputFunctionsIN5.setSelection(pos_Inputs_InputFunctionsIN5);
		TextView tv_Inputs_PolarityIN5 = (TextView) findViewById(R.id.tv_Inputs_PolarityIN5);
		tv_Inputs_PolarityIN5.setText(R.string.tv_Inputs_Polarity);
		int pos_Inputs_PolarityIN5 = spin_Inputs_PolarityIN5.getSelectedItemPosition();
		ArrayAdapter<String> polarityMenuIN5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.polarity));
		spin_Inputs_PolarityIN5.setAdapter(polarityMenuIN5);
		spin_Inputs_PolarityIN5.setSelection(pos_Inputs_PolarityIN5);
		TextView tv_Inputs_InputIsActiveIN5 = (TextView) findViewById(R.id.tv_Inputs_InputIsActiveIN5);
		tv_Inputs_InputIsActiveIN5.setText(R.string.tv_Inputs_InputIsActive);
		int pos_Inputs_InputIsActiveIN5 = spin_Inputs_InputIsActiveIN5.getSelectedItemPosition();
		ArrayAdapter<String> inputIsActiveMenuIN5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_is_active));
		spin_Inputs_InputIsActiveIN5.setAdapter(inputIsActiveMenuIN5);
		spin_Inputs_InputIsActiveIN5.setSelection(pos_Inputs_InputIsActiveIN5);
		TextView tv_Inputs_MinClosingTime1IN5 = (TextView) findViewById(R.id.tv_Inputs_MinClosingTime1IN5);
		tv_Inputs_MinClosingTime1IN5.setText(R.string.tv_Inputs_MinClosingTime);
		TextView tv_Inputs_MinClosingTime2IN5 = (TextView) findViewById(R.id.tv_Inputs_MinClosingTime2IN5);
		tv_Inputs_MinClosingTime2IN5.setText(R.string.tv_Inputs_Seconds);
		TextView tv_Inputs_MinOpeningTime1IN5 = (TextView) findViewById(R.id.tv_Inputs_MinOpeningTime1IN5);
		tv_Inputs_MinOpeningTime1IN5.setText(R.string.tv_Inputs_MinOpeningTime);
		TextView tv_Inputs_MinOpeningTime2IN5 = (TextView) findViewById(R.id.tv_Inputs_MinOpeningTime2IN5);
		tv_Inputs_MinOpeningTime2IN5.setText(R.string.tv_Inputs_Seconds);
		//IN6
		cb_Inputs_IN6.setText(R.string.cb_Inputs_IN6);
		TextView tv_Inputs_InputFunctionsIN6 = (TextView) findViewById(R.id.tv_Inputs_InputFunctionsIN6);
		tv_Inputs_InputFunctionsIN6.setText(R.string.tv_Inputs_InputFunction);
		int pos_Inputs_InputFunctionsIN6 = spin_Inputs_InputFunctionsIN6.getSelectedItemPosition();
		ArrayAdapter<String> inputFunctionMenuIN6 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_function_N6));
		spin_Inputs_InputFunctionsIN6.setAdapter(inputFunctionMenuIN6);
		spin_Inputs_InputFunctionsIN6.setSelection(pos_Inputs_InputFunctionsIN6);
		TextView tv_Inputs_PolarityIN6 = (TextView) findViewById(R.id.tv_Inputs_PolarityIN6);
		tv_Inputs_PolarityIN6.setText(R.string.tv_Inputs_Polarity);
		int pos_Inputs_PolarityIN6 = spin_Inputs_PolarityIN6.getSelectedItemPosition();
		ArrayAdapter<String> polarityMenuIN6 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.polarity));
		spin_Inputs_PolarityIN6.setAdapter(polarityMenuIN6);
		spin_Inputs_PolarityIN6.setSelection(pos_Inputs_PolarityIN6);
		TextView tv_Inputs_InputIsActiveIN6 = (TextView) findViewById(R.id.tv_Inputs_InputIsActiveIN6);
		tv_Inputs_InputIsActiveIN6.setText(R.string.tv_Inputs_InputIsActive);
		int pos_Inputs_InputIsActiveIN6 = spin_Inputs_InputIsActiveIN6.getSelectedItemPosition();
		ArrayAdapter<String> inputIsActiveMenuIN6 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_is_active));
		spin_Inputs_InputIsActiveIN6.setAdapter(inputIsActiveMenuIN6);
		spin_Inputs_InputIsActiveIN6.setSelection(pos_Inputs_InputIsActiveIN6);
		TextView tv_Inputs_MinClosingTime1IN6 = (TextView) findViewById(R.id.tv_Inputs_MinClosingTime1IN6);
		tv_Inputs_MinClosingTime1IN6.setText(R.string.tv_Inputs_MinClosingTime);
		TextView tv_Inputs_MinClosingTime2IN6 = (TextView) findViewById(R.id.tv_Inputs_MinClosingTime2IN6);
		tv_Inputs_MinClosingTime2IN6.setText(R.string.tv_Inputs_Seconds);
		TextView tv_Inputs_MinOpeningTime1IN6 = (TextView) findViewById(R.id.tv_Inputs_MinOpeningTime1IN6);
		tv_Inputs_MinOpeningTime1IN6.setText(R.string.tv_Inputs_MinOpeningTime);
		TextView tv_Inputs_MinOpeningTime2IN6 = (TextView) findViewById(R.id.tv_Inputs_MinOpeningTime2IN6);
		tv_Inputs_MinOpeningTime2IN6.setText(R.string.tv_Inputs_Seconds);
		//IN7
		cb_Inputs_IN7.setText(R.string.cb_Inputs_IN7);
		TextView tv_Inputs_InputFunctionsIN7 = (TextView) findViewById(R.id.tv_Inputs_InputFunctionsIN7);
		tv_Inputs_InputFunctionsIN7.setText(R.string.tv_Inputs_InputFunction);
		int pos_Inputs_InputFunctionsIN7 = spin_Inputs_InputFunctionsIN7.getSelectedItemPosition();
		ArrayAdapter<String> inputFunctionMenuIN7 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_function_N7));
		spin_Inputs_InputFunctionsIN7.setAdapter(inputFunctionMenuIN7);
		spin_Inputs_InputFunctionsIN7.setSelection(pos_Inputs_InputFunctionsIN7);
		TextView tv_Inputs_PolarityIN7 = (TextView) findViewById(R.id.tv_Inputs_PolarityIN7);
		tv_Inputs_PolarityIN7.setText(R.string.tv_Inputs_Polarity);
		int pos_Inputs_PolarityIN7 = spin_Inputs_PolarityIN7.getSelectedItemPosition();
		ArrayAdapter<String> polarityMenuIN7 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.polarity));
		spin_Inputs_PolarityIN7.setAdapter(polarityMenuIN7);
		spin_Inputs_PolarityIN7.setSelection(pos_Inputs_PolarityIN7);
		TextView tv_Inputs_InputIsActiveIN7 = (TextView) findViewById(R.id.tv_Inputs_InputIsActiveIN7);
		tv_Inputs_InputIsActiveIN7.setText(R.string.tv_Inputs_InputIsActive);
		int pos_Inputs_InputIsActiveIN7 = spin_Inputs_InputIsActiveIN7.getSelectedItemPosition();
		ArrayAdapter<String> inputIsActiveMenuIN7 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_is_active));
		spin_Inputs_InputIsActiveIN7.setAdapter(inputIsActiveMenuIN7);
		spin_Inputs_InputIsActiveIN7.setSelection(pos_Inputs_InputIsActiveIN7);
		TextView tv_Inputs_MinClosingTime1IN7 = (TextView) findViewById(R.id.tv_Inputs_MinClosingTime1IN7);
		tv_Inputs_MinClosingTime1IN7.setText(R.string.tv_Inputs_MinClosingTime);
		TextView tv_Inputs_MinClosingTime2IN7 = (TextView) findViewById(R.id.tv_Inputs_MinClosingTime2IN7);
		tv_Inputs_MinClosingTime2IN7.setText(R.string.tv_Inputs_Seconds);
		TextView tv_Inputs_MinOpeningTime1IN7 = (TextView) findViewById(R.id.tv_Inputs_MinOpeningTime1IN7);
		tv_Inputs_MinOpeningTime1IN7.setText(R.string.tv_Inputs_MinOpeningTime);
		TextView tv_Inputs_MinOpeningTime2IN7 = (TextView) findViewById(R.id.tv_Inputs_MinOpeningTime2IN7);
		tv_Inputs_MinOpeningTime2IN7.setText(R.string.tv_Inputs_Seconds);
		//IN8
		cb_Inputs_IN8.setText(R.string.cb_Inputs_IN8);
		TextView tv_Inputs_InputFunctionsIN8 = (TextView) findViewById(R.id.tv_Inputs_InputFunctionsIN8);
		tv_Inputs_InputFunctionsIN8.setText(R.string.tv_Inputs_InputFunction);
		int pos_Inputs_InputFunctionsIN8 = spin_Inputs_InputFunctionsIN8.getSelectedItemPosition();
		ArrayAdapter<String> inputFunctionMenuIN8 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_function_N8));
		spin_Inputs_InputFunctionsIN8.setAdapter(inputFunctionMenuIN8);
		spin_Inputs_InputFunctionsIN8.setSelection(pos_Inputs_InputFunctionsIN8);
		TextView tv_Inputs_PolarityIN8 = (TextView) findViewById(R.id.tv_Inputs_PolarityIN8);
		tv_Inputs_PolarityIN8.setText(R.string.tv_Inputs_Polarity);
		int pos_Inputs_PolarityIN8 = spin_Inputs_PolarityIN8.getSelectedItemPosition();
		ArrayAdapter<String> polarityMenuIN8 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.polarity));
		spin_Inputs_PolarityIN8.setAdapter(polarityMenuIN8);
		spin_Inputs_PolarityIN8.setSelection(pos_Inputs_PolarityIN8);
		TextView tv_Inputs_InputIsActiveIN8 = (TextView) findViewById(R.id.tv_Inputs_InputIsActiveIN8);
		tv_Inputs_InputIsActiveIN8.setText(R.string.tv_Inputs_InputIsActive);
		int pos_Inputs_InputIsActiveIN8 = spin_Inputs_InputIsActiveIN8.getSelectedItemPosition();
		ArrayAdapter<String> inputIsActiveMenuIN8 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.input_is_active));
		spin_Inputs_InputIsActiveIN8.setAdapter(inputIsActiveMenuIN8);
		spin_Inputs_InputIsActiveIN8.setSelection(pos_Inputs_InputIsActiveIN8);
		TextView tv_Inputs_MinClosingTime1IN8 = (TextView) findViewById(R.id.tv_Inputs_MinClosingTime1IN8);
		tv_Inputs_MinClosingTime1IN8.setText(R.string.tv_Inputs_MinClosingTime);
		TextView tv_Inputs_MinClosingTime2IN8 = (TextView) findViewById(R.id.tv_Inputs_MinClosingTime2IN8);
		tv_Inputs_MinClosingTime2IN8.setText(R.string.tv_Inputs_Seconds);
		TextView tv_Inputs_MinOpeningTime1IN8 = (TextView) findViewById(R.id.tv_Inputs_MinOpeningTime1IN8);
		tv_Inputs_MinOpeningTime1IN8.setText(R.string.tv_Inputs_MinOpeningTime);
		TextView tv_Inputs_MinOpeningTime2IN8 = (TextView) findViewById(R.id.tv_Inputs_MinOpeningTime2IN8);
		tv_Inputs_MinOpeningTime2IN8.setText(R.string.tv_Inputs_Seconds);
	}
	
	private void updateMenuItems()
	{
//		m_menuInstance.findItem(R.id.menu_item_factory_reset).setTitle(R.string.menu_item_factory_reset);
//		m_menuInstance.findItem(R.id.menu_item_fun1).setTitle(R.string.menu_item_fun1);
//		m_menuInstance.findItem(R.id.menu_item_fun2).setTitle(R.string.menu_item_fun2);
//		m_menuInstance.findItem(R.id.menu_item_fun3).setTitle(R.string.menu_item_fun3);
//		m_menuInstance.findItem(R.id.menu_item_fun4).setTitle(R.string.menu_item_fun4);
//		m_menuInstance.findItem(R.id.menu_item_fun5).setTitle(R.string.menu_item_fun5);
//		m_menuInstance.findItem(R.id.menu_item_fun6).setTitle(R.string.menu_item_fun6);
//		m_menuInstance.findItem(R.id.menu_item_stand_usr_stn).setTitle(R.string.menu_item_stand_usr_stn);
//		m_menuInstance.findItem(R.id.menu_item_save_file).setTitle(R.string.menu_item_save_file);
//		m_menuInstance.findItem(R.id.menu_item_load_file).setTitle(R.string.menu_item_load_file);
//		m_menuInstance.findItem(R.id.menu_item_delete_file).setTitle(R.string.menu_item_delete_file);
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
    		//IN1
    		case R.id.et_Inputs_MinClosingTimeIN1:
    		{
    			try 
    			{
        			Double value = Double.parseDouble(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("0.1");}
    			break;
    		}
    		case R.id.et_Inputs_MinOpeningTimeIN1:
    		{
    			try 
    			{
    				Double value = Double.parseDouble(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("0.1");}
    			break;
    		}
    		//IN2
    		case R.id.et_Inputs_MinClosingTimeIN2:
    		{
    			try 
    			{
    				Double value = Double.parseDouble(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("0.1");}
    			break;
    		}
    		case R.id.et_Inputs_MinOpeningTimeIN2:
    		{
    			try 
    			{
    				Double value = Double.parseDouble(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("0.1");}
    			break;
    		}
    		//IN3
    		case R.id.et_Inputs_MinClosingTimeIN3:
    		{
    			try 
    			{
    				Double value = Double.parseDouble(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("0.1");}
    			break;
    		}
    		case R.id.et_Inputs_MinOpeningTimeIN3:
    		{
    			try 
    			{
    				Double value = Double.parseDouble(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("0.1");}
    			break;
    		}
    		//IN4
    		case R.id.et_Inputs_MinClosingTimeIN4:
    		{
    			try 
    			{
    				Double value = Double.parseDouble(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("0.1");}
    			break;
    		}
    		case R.id.et_Inputs_MinOpeningTimeIN4:
    		{
    			try 
    			{
    				Double value = Double.parseDouble(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("0.1");}
    			break;
    		}
    		//IN5
    		case R.id.et_Inputs_MinClosingTimeIN5:
    		{
    			try 
    			{
    				Double value = Double.parseDouble(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("0.1");}
    			break;
    		}
    		case R.id.et_Inputs_MinOpeningTimeIN5:
    		{
    			try 
    			{
    				Double value = Double.parseDouble(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("0.1");}
    			break;
    		}
    		//IN6
    		case R.id.et_Inputs_MinClosingTimeIN6:
    		{
    			try 
    			{
    				Double value = Double.parseDouble(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("0.1");}
    			break;
    		}
    		case R.id.et_Inputs_MinOpeningTimeIN6:
    		{
    			try 
    			{
    				Double value = Double.parseDouble(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("0.1");}
    			break;
    		}
    		//IN7
    		case R.id.et_Inputs_MinClosingTimeIN7:
    		{
    			try 
    			{
    				Double value = Double.parseDouble(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("0.1");}
    			break;
    		}
    		case R.id.et_Inputs_MinOpeningTimeIN7:
    		{
    			try 
    			{
    				Double value = Double.parseDouble(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("0.1");}
    			break;
    		}
    		//IN8
    		case R.id.et_Inputs_MinClosingTimeIN8:
    		{
    			try 
    			{
    				Double value = Double.parseDouble(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("0.1");}
    			break;
    		}
    		case R.id.et_Inputs_MinOpeningTimeIN8:
    		{
    			try 
    			{
    				Double value = Double.parseDouble(et.getText().toString());
        			if(value < 0)
        				et.setText("0");
    			}catch(NumberFormatException e)
    			{et.setText("0.1");}
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
	    	//IN1
			case R.id.et_Inputs_MinClosingTimeIN1:
			{
				try 
				{
					Double value = Double.parseDouble(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("0.1");}
				break;
			}
			case R.id.et_Inputs_MinOpeningTimeIN1:
			{
				try 
				{
					Double value = Double.parseDouble(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("0.1");}
				break;
			}
			//IN2
			case R.id.et_Inputs_MinClosingTimeIN2:
			{
				try 
				{
					Double value = Double.parseDouble(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("0.1");}
				break;
			}
			case R.id.et_Inputs_MinOpeningTimeIN2:
			{
				try 
				{
					Double value = Double.parseDouble(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("0.1");}
				break;
			}
			//IN3
			case R.id.et_Inputs_MinClosingTimeIN3:
			{
				try 
				{
					Double value = Double.parseDouble(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("0.1");}
				break;
			}
			case R.id.et_Inputs_MinOpeningTimeIN3:
			{
				try 
				{
					Double value = Double.parseDouble(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("0.1");}
				break;
			}
			//IN4
			case R.id.et_Inputs_MinClosingTimeIN4:
			{
				try 
				{
					Double value = Double.parseDouble(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("0.1");}
				break;
			}
			case R.id.et_Inputs_MinOpeningTimeIN4:
			{
				try 
				{
					Double value = Double.parseDouble(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("0.1");}
				break;
			}
			//IN5
			case R.id.et_Inputs_MinClosingTimeIN5:
			{
				try 
				{
					Double value = Double.parseDouble(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("0.1");}
				break;
			}
			case R.id.et_Inputs_MinOpeningTimeIN5:
			{
				try 
				{
					Double value = Double.parseDouble(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("0.1");}
				break;
			}
			//IN6
			case R.id.et_Inputs_MinClosingTimeIN6:
			{
				try 
				{
					Double value = Double.parseDouble(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("0.1");}
				break;
			}
			case R.id.et_Inputs_MinOpeningTimeIN6:
			{
				try 
				{
					Double value = Double.parseDouble(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("0.1");}
				break;
			}
			//IN7
			case R.id.et_Inputs_MinClosingTimeIN7:
			{
				try 
				{
					Double value = Double.parseDouble(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("0.1");}
				break;
			}
			case R.id.et_Inputs_MinOpeningTimeIN7:
			{
				try 
				{
					Double value = Double.parseDouble(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("0.1");}
				break;
			}
			//IN8
			case R.id.et_Inputs_MinClosingTimeIN8:
			{
				try 
				{
					Double value = Double.parseDouble(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("0.1");}
				break;
			}
			case R.id.et_Inputs_MinOpeningTimeIN8:
			{
				try 
				{
					Double value = Double.parseDouble(et.getText().toString());
	    			if(value < 0)
	    				et.setText("0");
				}catch(NumberFormatException e)
				{et.setText("0.1");}
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
	public boolean onTouch(View view, MotionEvent motionEvent) {
		Spinner spinner = (Spinner) view;
		if (spinner != null && motionEvent.getAction() == MotionEvent.ACTION_UP) {
			m_activeSpinner = spinner;


			final int itemsCount = m_activeSpinner.getAdapter().getCount();
			ArrayList<String> listOfItems = new ArrayList<String>();
			for (int item = 0; item < itemsCount; ++item) {
				listOfItems.add(m_activeSpinner.getItemAtPosition(item).toString());
			}

			Intent intent = new Intent(this, CustomSpinnerPopup.class);
			intent.putExtra(SPINNER_INPUTS, listOfItems);
			startActivityForResult(intent, RESULT_CODE_SPINNER);
		}

		return true;
	}
}
