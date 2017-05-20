package com.international.group.bat;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import java.util.Locale;

public class CustomMenuPopup extends Activity implements OnClickListener
{
	TextView menu_item_load_file;
	TextView menu_item_save_file;
	TextView menu_item_delete_file;
	TextView menu_item_factory_reset;
	TextView menu_item_fun1;
	TextView menu_item_fun2;
	TextView menu_item_fun3;
	TextView menu_item_fun4;
	TextView menu_item_fun5;
	TextView menu_item_fun6;
	TextView menu_item_stand_usr_stn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_menu);


		
		init();
		setListeners();

		setDisplayMetrics();

		setLanguage();
		updateUI();
	}
	
	void init()
	{
		menu_item_load_file = (TextView) findViewById(R.id.menu_item_load_file);
		menu_item_save_file = (TextView) findViewById(R.id.menu_item_save_file);
		menu_item_delete_file = (TextView) findViewById(R.id.menu_item_delete_file);
		menu_item_factory_reset = (TextView) findViewById(R.id.menu_item_factory_reset);
		menu_item_fun1 = (TextView) findViewById(R.id.menu_item_fun1);
		menu_item_fun2 = (TextView) findViewById(R.id.menu_item_fun2);
		menu_item_fun3 = (TextView) findViewById(R.id.menu_item_fun3);
		menu_item_fun4 = (TextView) findViewById(R.id.menu_item_fun4);
		menu_item_fun5 = (TextView) findViewById(R.id.menu_item_fun5);
		menu_item_fun6 = (TextView) findViewById(R.id.menu_item_fun6);
		menu_item_stand_usr_stn = (TextView) findViewById(R.id.menu_item_stand_usr_stn);
	}
	
	void setListeners()
	{
		menu_item_load_file.setOnClickListener(this);
		menu_item_save_file.setOnClickListener(this);
		menu_item_delete_file.setOnClickListener(this);
		menu_item_factory_reset.setOnClickListener(this);
		menu_item_fun1.setOnClickListener(this);
		menu_item_fun2.setOnClickListener(this);
		menu_item_fun3.setOnClickListener(this);
		menu_item_fun4.setOnClickListener(this);
		menu_item_fun5.setOnClickListener(this);
		menu_item_fun6.setOnClickListener(this);
		menu_item_stand_usr_stn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) 
	{
		v.setBackgroundColor(Color.parseColor("#4fa5d5"));
		boolean typicalSettingsLoaded = false;
		
		switch(v.getId())
		{
			case R.id.menu_item_load_file:
			{
				Intent intent = new Intent(this, LoadSettingPopup.class);
				intent.putExtra(ChangeLanguagePopup.PARENT_ACTIVITY_NAME, getClass().getName());
				startActivityForResult(intent, 0);
				break;
			}
			case R.id.menu_item_save_file:
			{
				Intent intent = new Intent(this, SaveSettingPopup.class);
				intent.putExtra(SaveSettingPopup.PARENT_ACTIVITY_NAME, getClass().getName());
				startActivityForResult(intent, 0);
				break;
			}
			case R.id.menu_item_delete_file:
			{
				Intent intent = new Intent(this, DeleteSettingPopup.class);
				intent.putExtra(SaveSettingPopup.PARENT_ACTIVITY_NAME, getClass().getName());
				startActivityForResult(intent, 0);
				break;
			}
			
			case R.id.menu_item_factory_reset:
			{
				loadSettings(MainActivity.DEFAULT_SETTINGS);
				typicalSettingsLoaded = true;
				break;
			}
			case R.id.menu_item_fun1:
			{
				loadSettings(MainActivity.FUNCTION_301);
				typicalSettingsLoaded = true;
				break;
			}
			case R.id.menu_item_fun2:
			{
				loadSettings(MainActivity.FUNCTION_302);
				typicalSettingsLoaded = true;
				break;
			}
			case R.id.menu_item_fun3:
			{
				loadSettings(MainActivity.FUNCTION_303);
				typicalSettingsLoaded = true;
				break;
			}
			case R.id.menu_item_fun4:
			{
				loadSettings(MainActivity.FUNCTION_304);
				typicalSettingsLoaded = true;
				break;
			}
			case R.id.menu_item_fun5:
			{
				loadSettings(MainActivity.FUNCTION_305);
				typicalSettingsLoaded = true;
				break;
			}
			case R.id.menu_item_fun6:
			{
				loadSettings(MainActivity.FUNCTION_306);
				typicalSettingsLoaded = true;
				break;
			}
			case R.id.menu_item_stand_usr_stn:
			{
				loadSettings(MainActivity.STAND_USER_SETTINGS);
				typicalSettingsLoaded = true;
				break;
			}
		}
		
		if(typicalSettingsLoaded)
			MainActivity.stateholderTextSMS.setManualModeChecked(false);
		
		Intent intent = new Intent();
	    intent.putExtra("name", typicalSettingsLoaded);
	    setResult(RESULT_OK, intent);
		finish();
	}
	
	private void setDisplayMetrics()
	{
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		int width = dm.widthPixels;
		int height = dm.heightPixels;
		
		getWindow().setLayout((int) (width * 1.0), (int) (height * 1.0));
	}
	
	private void loadSettings(String fileWithSettingsName)
	{
		LoadSettingPopup.loadSettings(fileWithSettingsName + ".txt");
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
		menu_item_load_file.setText(R.string.menu_item_load_file);
		menu_item_save_file.setText(R.string.menu_item_save_file);
		menu_item_delete_file.setText(R.string.menu_item_delete_file);
		menu_item_factory_reset.setText(R.string.menu_item_factory_reset);
		menu_item_fun1.setText(R.string.menu_item_fun1);
		menu_item_fun2.setText(R.string.menu_item_fun2);
		menu_item_fun3.setText(R.string.menu_item_fun3);
		menu_item_fun4.setText(R.string.menu_item_fun4);
		menu_item_fun5.setText(R.string.menu_item_fun5);
		menu_item_fun6.setText(R.string.menu_item_fun6);
		menu_item_stand_usr_stn.setText(R.string.menu_item_stand_usr_stn);
	}
}
