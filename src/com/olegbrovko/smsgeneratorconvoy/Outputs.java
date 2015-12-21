package com.olegbrovko.smsgeneratorconvoy;

import java.util.ArrayList;
import java.util.Locale;

import stateholders.OutputsStateHolder;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.olegbrovko.smsgeneratorconvoy.R;

public class Outputs extends Activity implements OnCheckedChangeListener, OnTouchListener
{
	public static final String SPINNER_OUTPUT = "com.olegbrovko.SPINNER_OUTPUT";
	public static final int RESULT_CODE_SPINNER = 12335;
	public static Outputs instance = null;
	private static Spinner m_activeSpinner = null;
	
	//1) OUT1
	CheckBox cb_Outputs_OUT1 = null;
	Spinner spin_Outputs_OutputFunctionsOUT1 = null;
	
	//2) OUT2
	CheckBox cb_Outputs_OUT2 = null;
	Spinner spin_Outputs_OutputFunctionsOUT2 = null;
	
	//3) OUT3
	CheckBox cb_Outputs_OUT3 = null;
	Spinner spin_Outputs_OutputFunctionsOUT3 = null;
	
	//4) OUT4
	CheckBox cb_Outputs_OUT4 = null;
	Spinner spin_Outputs_OutputFunctionsOUT4 = null;
	
	//5) OUT5
	CheckBox cb_Outputs_OUT5 = null;
	Spinner spin_Outputs_OutputFunctionsOUT5 = null;
	
	//6) OUT6
	CheckBox cb_Outputs_OUT6 = null;
	Spinner spin_Outputs_OutputFunctionsOUT6 = null;
	
	//State holder
	static OutputsStateHolder m_stateholder = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		instance = this;
		setLanguage();
		setContentView(R.layout.outputs);
		
		initOUT1();
		initOUT2();
		initOUT3();
		initOUT4();
		initOUT5();
		initOUT6();
		
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
	
	private void initOUT1()
	{
		//---------------------------------------- INITIALIZATION ------------------------------------
		//OUT1 members initialization
		cb_Outputs_OUT1 = (CheckBox) findViewById(R.id.cb_Outputs_OUT1);
		spin_Outputs_OutputFunctionsOUT1 = (Spinner) findViewById(R.id.spin_Outputs_OutputFunctionsOUT1);
		
		//---------------------------------------- SET VALUES ----------------------------------------
		//OUT1 Spinners initialization
		ArrayAdapter<String> outputFunctionMenu = new ArrayAdapter<String>(this, R.array.output_function_OUT1, android.R.layout.simple_spinner_item);
		spin_Outputs_OutputFunctionsOUT1.setAdapter(outputFunctionMenu);
		
		
		//---------------------------------------- SET STATES ----------------------------------------
		spin_Outputs_OutputFunctionsOUT1.setEnabled(false);
		spin_Outputs_OutputFunctionsOUT1.setSelection(0);
	}
	
	private void initOUT2()
	{
		//---------------------------------------- INITIALIZATION ------------------------------------
		//OUT1 members initialization
		cb_Outputs_OUT2 = (CheckBox) findViewById(R.id.cb_Outputs_OUT2);
		spin_Outputs_OutputFunctionsOUT2 = (Spinner) findViewById(R.id.spin_Outputs_OutputFunctionsOUT2);
		
		//---------------------------------------- SET VALUES ----------------------------------------
		//OUT1 Spinners initialization
		ArrayAdapter<String> outputFunctionMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.output_function_OUT2));
		spin_Outputs_OutputFunctionsOUT2.setAdapter(outputFunctionMenu);
		
		//---------------------------------------- SET STATES ----------------------------------------
		spin_Outputs_OutputFunctionsOUT2.setEnabled(false);
		spin_Outputs_OutputFunctionsOUT2.setSelection(1);
	}
	
	private void initOUT3()
	{
		//---------------------------------------- INITIALIZATION ------------------------------------
		//OUT1 members initialization
		cb_Outputs_OUT3 = (CheckBox) findViewById(R.id.cb_Outputs_OUT3);
		spin_Outputs_OutputFunctionsOUT3 = (Spinner) findViewById(R.id.spin_Outputs_OutputFunctionsOUT3);
		
		//---------------------------------------- SET VALUES ----------------------------------------
		//OUT1 Spinners initialization
		ArrayAdapter<String> outputFunctionMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.output_function_OUT3));
		spin_Outputs_OutputFunctionsOUT3.setAdapter(outputFunctionMenu);
		
		//---------------------------------------- SET STATES ----------------------------------------
		spin_Outputs_OutputFunctionsOUT3.setEnabled(false);
		spin_Outputs_OutputFunctionsOUT3.setSelection(2);
	}
	
	private void initOUT4()
	{
		//---------------------------------------- INITIALIZATION ------------------------------------
		//OUT1 members initialization
		cb_Outputs_OUT4 = (CheckBox) findViewById(R.id.cb_Outputs_OUT4);
		spin_Outputs_OutputFunctionsOUT4 = (Spinner) findViewById(R.id.spin_Outputs_OutputFunctionsOUT4);
		
		//---------------------------------------- SET VALUES ----------------------------------------
		//OUT1 Spinners initialization
		ArrayAdapter<String> outputFunctionMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.output_function_OUT4));
		spin_Outputs_OutputFunctionsOUT4.setAdapter(outputFunctionMenu);
		
		//---------------------------------------- SET STATES ----------------------------------------
		spin_Outputs_OutputFunctionsOUT4.setEnabled(false);
		spin_Outputs_OutputFunctionsOUT4.setSelection(3);
	}
	
	private void initOUT5()
	{
		//---------------------------------------- INITIALIZATION ------------------------------------
		//OUT1 members initialization
		cb_Outputs_OUT5 = (CheckBox) findViewById(R.id.cb_Outputs_OUT5);
		spin_Outputs_OutputFunctionsOUT5 = (Spinner) findViewById(R.id.spin_Outputs_OutputFunctionsOUT5);
		
		//---------------------------------------- SET VALUES ----------------------------------------
		//OUT1 Spinners initialization
		ArrayAdapter<String> outputFunctionMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.output_function_OUT5));
		spin_Outputs_OutputFunctionsOUT5.setAdapter(outputFunctionMenu);
		
		//---------------------------------------- SET STATES ----------------------------------------
		spin_Outputs_OutputFunctionsOUT5.setEnabled(false);
		spin_Outputs_OutputFunctionsOUT5.setSelection(0);
	}
	
	private void initOUT6()
	{
		//---------------------------------------- INITIALIZATION ------------------------------------
		//OUT1 members initialization
		cb_Outputs_OUT6 = (CheckBox) findViewById(R.id.cb_Outputs_OUT6);
		spin_Outputs_OutputFunctionsOUT6 = (Spinner) findViewById(R.id.spin_Outputs_OutputFunctionsOUT6);
		
		//---------------------------------------- SET VALUES ----------------------------------------
		//OUT1 Spinners initialization
		ArrayAdapter<String> outputFunctionMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.output_function_OUT6));
		spin_Outputs_OutputFunctionsOUT6.setAdapter(outputFunctionMenu);
		
		//---------------------------------------- SET STATES ----------------------------------------
		spin_Outputs_OutputFunctionsOUT6.setEnabled(false);
		spin_Outputs_OutputFunctionsOUT6.setSelection(5);
	}
	
	private void setListeners()
	{
		cb_Outputs_OUT1.setOnCheckedChangeListener(this);
		cb_Outputs_OUT2.setOnCheckedChangeListener(this);
		cb_Outputs_OUT3.setOnCheckedChangeListener(this);
		cb_Outputs_OUT4.setOnCheckedChangeListener(this);
		cb_Outputs_OUT5.setOnCheckedChangeListener(this);
		cb_Outputs_OUT6.setOnCheckedChangeListener(this);
		
		spin_Outputs_OutputFunctionsOUT1.setOnTouchListener(this);
		spin_Outputs_OutputFunctionsOUT2.setOnTouchListener(this);
		spin_Outputs_OutputFunctionsOUT3.setOnTouchListener(this);
		spin_Outputs_OutputFunctionsOUT4.setOnTouchListener(this);
		spin_Outputs_OutputFunctionsOUT5.setOnTouchListener(this);
		spin_Outputs_OutputFunctionsOUT6.setOnTouchListener(this);
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
			m_stateholder = MainActivity.stateholderOutputs;
		
		//OUT1
		m_stateholder.setCbOutputsOUT1Enabled(cb_Outputs_OUT1.isEnabled());
		m_stateholder.setCbOutputsOUT1Checked(cb_Outputs_OUT1.isChecked());
		m_stateholder.setSpinOutputFunctionsOUT11Enabled(spin_Outputs_OutputFunctionsOUT1.isEnabled());
		m_stateholder.setSpinOutputFunctionsOUT1Value(spin_Outputs_OutputFunctionsOUT1.getSelectedItemPosition());
		
		//OUT2
		m_stateholder.setCbOutputsOUT2Enabled(cb_Outputs_OUT2.isEnabled());
		m_stateholder.setCbOutputsOUT2Checked(cb_Outputs_OUT2.isChecked());
		m_stateholder.setSpinOutputFunctionsOUT22Enabled(spin_Outputs_OutputFunctionsOUT2.isEnabled());
		m_stateholder.setSpinOutputFunctionsOUT2Value(spin_Outputs_OutputFunctionsOUT2.getSelectedItemPosition());
		
		//OUT3
		m_stateholder.setCbOutputsOUT3Enabled(cb_Outputs_OUT3.isEnabled());
		m_stateholder.setCbOutputsOUT3Checked(cb_Outputs_OUT3.isChecked());
		m_stateholder.setSpinOutputFunctionsOUT33Enabled(spin_Outputs_OutputFunctionsOUT3.isEnabled());
		m_stateholder.setSpinOutputFunctionsOUT3Value(spin_Outputs_OutputFunctionsOUT3.getSelectedItemPosition());
		
		//OUT4
		m_stateholder.setCbOutputsOUT4Enabled(cb_Outputs_OUT4.isEnabled());
		m_stateholder.setCbOutputsOUT4Checked(cb_Outputs_OUT4.isChecked());
		m_stateholder.setSpinOutputFunctionsOUT44Enabled(spin_Outputs_OutputFunctionsOUT4.isEnabled());
		m_stateholder.setSpinOutputFunctionsOUT4Value(spin_Outputs_OutputFunctionsOUT4.getSelectedItemPosition());
		
		//OUT5
		m_stateholder.setCbOutputsOUT5Enabled(cb_Outputs_OUT5.isEnabled());
		m_stateholder.setCbOutputsOUT5Checked(cb_Outputs_OUT5.isChecked());
		m_stateholder.setSpinOutputFunctionsOUT55Enabled(spin_Outputs_OutputFunctionsOUT5.isEnabled());
		m_stateholder.setSpinOutputFunctionsOUT5Value(spin_Outputs_OutputFunctionsOUT5.getSelectedItemPosition());
		
		//OUT6
		m_stateholder.setCbOutputsOUT6Enabled(cb_Outputs_OUT6.isEnabled());
		m_stateholder.setCbOutputsOUT6Checked(cb_Outputs_OUT6.isChecked());
		m_stateholder.setSpinOutputFunctionsOUT66Enabled(spin_Outputs_OutputFunctionsOUT6.isEnabled());
		m_stateholder.setSpinOutputFunctionsOUT6Value(spin_Outputs_OutputFunctionsOUT6.getSelectedItemPosition());
	}
	
	private void restoreState()
	{
		if (m_stateholder != null) 
		{
			//OUT1
			cb_Outputs_OUT1.setEnabled(m_stateholder.getCbOutputsOUT1Enabled());
			cb_Outputs_OUT1.setChecked(m_stateholder.getCbOutputsOUT1Checked());
			spin_Outputs_OutputFunctionsOUT1.setEnabled(m_stateholder.getSpinOutputFunctionsOUT11Enabled());
			spin_Outputs_OutputFunctionsOUT1.setSelection(m_stateholder.getSpinOutputFunctionsOUT1Value());
			
			//OUT2
			cb_Outputs_OUT2.setEnabled(m_stateholder.getCbOutputsOUT2Enabled());
			cb_Outputs_OUT2.setChecked(m_stateholder.getCbOutputsOUT2Checked());
			spin_Outputs_OutputFunctionsOUT2.setEnabled(m_stateholder.getSpinOutputFunctionsOUT22Enabled());
			spin_Outputs_OutputFunctionsOUT2.setSelection(m_stateholder.getSpinOutputFunctionsOUT2Value());
			
			//OUT3
			cb_Outputs_OUT3.setEnabled(m_stateholder.getCbOutputsOUT3Enabled());
			cb_Outputs_OUT3.setChecked(m_stateholder.getCbOutputsOUT3Checked());
			spin_Outputs_OutputFunctionsOUT3.setEnabled(m_stateholder.getSpinOutputFunctionsOUT33Enabled());
			spin_Outputs_OutputFunctionsOUT3.setSelection(m_stateholder.getSpinOutputFunctionsOUT3Value());
			
			//OUT4
			cb_Outputs_OUT4.setEnabled(m_stateholder.getCbOutputsOUT4Enabled());
			cb_Outputs_OUT4.setChecked(m_stateholder.getCbOutputsOUT4Checked());
			spin_Outputs_OutputFunctionsOUT4.setEnabled(m_stateholder.getSpinOutputFunctionsOUT44Enabled());
			spin_Outputs_OutputFunctionsOUT4.setSelection(m_stateholder.getSpinOutputFunctionsOUT4Value());
			
			//OUT5
			cb_Outputs_OUT5.setEnabled(m_stateholder.getCbOutputsOUT5Enabled());
			cb_Outputs_OUT5.setChecked(m_stateholder.getCbOutputsOUT5Checked());
			spin_Outputs_OutputFunctionsOUT5.setEnabled(m_stateholder.getSpinOutputFunctionsOUT55Enabled());
			spin_Outputs_OutputFunctionsOUT5.setSelection(m_stateholder.getSpinOutputFunctionsOUT5Value());
			
			//OUT6
			cb_Outputs_OUT6.setEnabled(m_stateholder.getCbOutputsOUT6Enabled());
			cb_Outputs_OUT6.setChecked(m_stateholder.getCbOutputsOUT6Checked());
			spin_Outputs_OutputFunctionsOUT6.setEnabled(m_stateholder.getSpinOutputFunctionsOUT66Enabled());
			spin_Outputs_OutputFunctionsOUT6.setSelection(m_stateholder.getSpinOutputFunctionsOUT6Value());
		}
	}
	
	//================================= LISENERS ==============================================
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) 
		{
			switch(buttonView.getId())
			{
				//1)
				case R.id.cb_Outputs_OUT1:
				{
					spin_Outputs_OutputFunctionsOUT1.setEnabled(isChecked);
					break;
				}
				//2)
				case R.id.cb_Outputs_OUT2:
				{
					spin_Outputs_OutputFunctionsOUT2.setEnabled(isChecked);
					break;
				}
				//3)
				case R.id.cb_Outputs_OUT3:
				{
					spin_Outputs_OutputFunctionsOUT3.setEnabled(isChecked);
					break;
				}
				//4)
				case R.id.cb_Outputs_OUT4:
				{
					spin_Outputs_OutputFunctionsOUT4.setEnabled(isChecked);
					break;
				}
				//5)
				case R.id.cb_Outputs_OUT5:
				{
					spin_Outputs_OutputFunctionsOUT5.setEnabled(isChecked);
					break;
				}
				//6)
				case R.id.cb_Outputs_OUT6:
				{	
					spin_Outputs_OutputFunctionsOUT6.setEnabled(isChecked);
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
			setTitle(R.string.btn_Main_Outputs);
			//OUT1
			cb_Outputs_OUT1.setText(R.string.cb_Outputs_OUT1);
			TextView tv_Outputs_InputFunctionsOUT1 = (TextView) findViewById(R.id.tv_Outputs_InputFunctionsOUT1);
			tv_Outputs_InputFunctionsOUT1.setText(R.string.tv_Outputs_OutputFunction);
			int pos_Outputs_OutputFunctionsOUT1 = spin_Outputs_OutputFunctionsOUT1.getSelectedItemPosition();
			ArrayAdapter<String> outputFunctionMenuOUT1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.output_function_OUT1));
			spin_Outputs_OutputFunctionsOUT1.setAdapter(outputFunctionMenuOUT1);
			spin_Outputs_OutputFunctionsOUT1.setSelection(pos_Outputs_OutputFunctionsOUT1);
			//OUT2
			cb_Outputs_OUT2.setText(R.string.cb_Outputs_OUT2);
			TextView tv_Outputs_InputFunctionsOUT2 = (TextView) findViewById(R.id.tv_Outputs_InputFunctionsOUT2);
			tv_Outputs_InputFunctionsOUT2.setText(R.string.tv_Outputs_OutputFunction);
			int pos_Outputs_OutputFunctionsOUT2 = spin_Outputs_OutputFunctionsOUT2.getSelectedItemPosition();
			ArrayAdapter<String> outputFunctionMenuOUT2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.output_function_OUT2));
			spin_Outputs_OutputFunctionsOUT2.setAdapter(outputFunctionMenuOUT2);
			spin_Outputs_OutputFunctionsOUT2.setSelection(pos_Outputs_OutputFunctionsOUT2);
			//OUT3
			cb_Outputs_OUT3.setText(R.string.cb_Outputs_OUT3);
			TextView tv_Outputs_InputFunctionsOUT3 = (TextView) findViewById(R.id.tv_Outputs_InputFunctionsOUT3);
			tv_Outputs_InputFunctionsOUT3.setText(R.string.tv_Outputs_OutputFunction);
			int pos_Outputs_OutputFunctionsOUT3 = spin_Outputs_OutputFunctionsOUT3.getSelectedItemPosition();
			ArrayAdapter<String> outputFunctionMenuOUT3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.output_function_OUT3));
			spin_Outputs_OutputFunctionsOUT3.setAdapter(outputFunctionMenuOUT3);
			spin_Outputs_OutputFunctionsOUT3.setSelection(pos_Outputs_OutputFunctionsOUT3);
			//OUT4
			cb_Outputs_OUT4.setText(R.string.cb_Outputs_OUT4);
			TextView tv_Outputs_InputFunctionsOUT4 = (TextView) findViewById(R.id.tv_Outputs_InputFunctionsOUT4);
			tv_Outputs_InputFunctionsOUT4.setText(R.string.tv_Outputs_OutputFunction);
			int pos_Outputs_OutputFunctionsOUT4 = spin_Outputs_OutputFunctionsOUT4.getSelectedItemPosition();
			ArrayAdapter<String> outputFunctionMenuOUT4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.output_function_OUT4));
			spin_Outputs_OutputFunctionsOUT4.setAdapter(outputFunctionMenuOUT4);
			spin_Outputs_OutputFunctionsOUT4.setSelection(pos_Outputs_OutputFunctionsOUT4);
			//OUT5
			cb_Outputs_OUT5.setText(R.string.cb_Outputs_OUT5);
			TextView tv_Outputs_InputFunctionsOUT5 = (TextView) findViewById(R.id.tv_Outputs_InputFunctionsOUT5);
			tv_Outputs_InputFunctionsOUT5.setText(R.string.tv_Outputs_OutputFunction);
			int pos_Outputs_OutputFunctionsOUT5 = spin_Outputs_OutputFunctionsOUT5.getSelectedItemPosition();
			ArrayAdapter<String> outputFunctionMenuOUT5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.output_function_OUT5));
			spin_Outputs_OutputFunctionsOUT5.setAdapter(outputFunctionMenuOUT5);
			spin_Outputs_OutputFunctionsOUT5.setSelection(pos_Outputs_OutputFunctionsOUT5);
			//OUT6
			cb_Outputs_OUT6.setText(R.string.cb_Outputs_OUT6);
			TextView tv_Outputs_InputFunctionsOUT6 = (TextView) findViewById(R.id.tv_Outputs_InputFunctionsOUT6);
			tv_Outputs_InputFunctionsOUT6.setText(R.string.tv_Outputs_OutputFunction);
			int pos_Outputs_OutputFunctionsOUT6 = spin_Outputs_OutputFunctionsOUT6.getSelectedItemPosition();
			ArrayAdapter<String> outputFunctionMenuOUT6 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.output_function_OUT6));
			spin_Outputs_OutputFunctionsOUT6.setAdapter(outputFunctionMenuOUT6);
			spin_Outputs_OutputFunctionsOUT6.setSelection(pos_Outputs_OutputFunctionsOUT6);
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
				intent.putExtra(SPINNER_OUTPUT, listOfItems);
				startActivityForResult(intent, RESULT_CODE_SPINNER);
			}
			
			return true;
		}
}
