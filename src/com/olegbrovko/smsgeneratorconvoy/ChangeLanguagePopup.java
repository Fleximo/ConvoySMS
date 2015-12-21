package com.olegbrovko.smsgeneratorconvoy;

import java.util.Locale;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.olegbrovko.smsgeneratorconvoy.R;

public class ChangeLanguagePopup extends Activity implements OnClickListener
{
	public static final String LANGUAGE = "USER_LANGUAGE";
	public static final String PARENT_ACTIVITY_NAME = "PARENT_ACTIVITY_NAME";
	String nameOfParentActivity = null;
	
	private static SharedPreferences m_sharedPreferences = MainActivity.m_sharedPreferences;

	TextView tv_ChangeLanguage_Header = null;
	
	Button btn_ChangeLanguage_OK = null;
	Button btn_ChangeLanguage_Cancel = null;
	
	RadioButton rbtn_ChangeLanguage_English = null;
	RadioButton rbtn_ChangeLanguage_Russian = null;
	RadioButton rbtn_ChangeLanguage_Ukrainian = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_language_popup);
		
		nameOfParentActivity = getIntent().getStringExtra(PARENT_ACTIVITY_NAME);
		
		init();
		setListeners();
		setDisplayMetrics();
		setCheckBoxStatus();

		setLanguage();
		updateUI();
	}

	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{
			case R.id.btn_ChangeLanguage_OK:
			{
				String language = changeLanguage();
				Toast.makeText(this, "Language was changed to: " + language, Toast.LENGTH_SHORT).show();
				
				if(nameOfParentActivity.equals("MainActivity"))
				{
					MainActivity.instance.updateUI();
				}
				else if(nameOfParentActivity.equals("CAN"))
				{
					CAN.instance.updateUI();
				}
				else if(nameOfParentActivity.equals("UserSettings"))
				{
					UserSettings.instance.updateUI();
				}
				else if(nameOfParentActivity.equals("Inputs"))
				{
					Inputs.instance.updateUI();
				}
				else if(nameOfParentActivity.equals("Notifications"))
				{
					Notifications.instance.updateUI();
				}
				else if(nameOfParentActivity.equals("Outputs"))
				{
					Outputs.instance.updateUI();
				}
				else if(nameOfParentActivity.equals("QueryConfiguration"))
				{
					QueryConfiguration.instance.updateUI();
				}
				else if(nameOfParentActivity.equals("TextSMS"))
				{
					TextSMS.instance.updateUI();
				}
				else if(nameOfParentActivity.equals("UniversalTimeChannels"))
				{
					UniversalTimeChannels.instance.updateUI();
				}
		        finish();
				break;
			}
			case R.id.btn_ChangeLanguage_Cancel:
			{
				finish();
				break;
			}
		}
		
		
	}
	
	private void init()
	{
		tv_ChangeLanguage_Header = (TextView) findViewById(R.id.tv_ChangeLanguage_Header);
		btn_ChangeLanguage_OK = (Button) findViewById(R.id.btn_ChangeLanguage_OK);
		btn_ChangeLanguage_Cancel = (Button) findViewById(R.id.btn_ChangeLanguage_Cancel);
		
		rbtn_ChangeLanguage_English = (RadioButton) findViewById(R.id.rbtn_ChangeLanguage_English);
		rbtn_ChangeLanguage_Russian = (RadioButton) findViewById(R.id.rbtn_ChangeLanguage_Russian);
		rbtn_ChangeLanguage_Ukrainian = (RadioButton) findViewById(R.id.rbtn_ChangeLanguage_Espanian);
	}
	
	private void setListeners()
	{
		btn_ChangeLanguage_OK.setOnClickListener(this);
		btn_ChangeLanguage_Cancel.setOnClickListener(this);
	}
	
	private void setDisplayMetrics()
	{
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		int width = dm.widthPixels;
		int height = dm.heightPixels;

		getWindow().setLayout((int) (width * 0.8), (int) (height * 0.5));
	}
	
	private void setCheckBoxStatus()
	{
		String language = m_sharedPreferences.getString(ChangeLanguagePopup.LANGUAGE, "ru");
		
		if(language.equals("ru"))
			rbtn_ChangeLanguage_Russian.setChecked(true);
		else if(language.equals("en"))
			rbtn_ChangeLanguage_English.setChecked(true);
		else if(language.equals("ua"))
			rbtn_ChangeLanguage_Ukrainian.setChecked(true);
	}
	
	private String changeLanguage()
	{
		Locale locale = null;
		String language = null;
		
		if(rbtn_ChangeLanguage_English.isChecked())
		{
			language = new String("en");
			locale = new Locale(language); 
		}
		else if(rbtn_ChangeLanguage_Russian.isChecked())
		{
			language = new String("ru");
			locale = new Locale(language);
		}
		else if(rbtn_ChangeLanguage_Ukrainian.isChecked())
		{
			language = new String("ua");
			locale = new Locale(language);
		}
		else
		{
			language = new String("ru");
			locale = new Locale(language);
		}
		
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        
        //m_sharedPreferences = MainActivity.m_sharedPreferences;
        Editor ed = m_sharedPreferences.edit();
	    ed.putString(LANGUAGE, language);
	    ed.commit();
        return language;
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
		tv_ChangeLanguage_Header.setText(R.string.tv_ChangeLanguage_Header);
	}
}
