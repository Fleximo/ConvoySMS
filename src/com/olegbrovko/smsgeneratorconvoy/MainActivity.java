package com.olegbrovko.smsgeneratorconvoy;




import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Locale;

import stateholders.CANStateHolder;
import stateholders.InputsStateHolder;
import stateholders.NotificationStateHolder;
import stateholders.OutputsStateHolder;
import stateholders.QueryConfigurationStateHolder;
import stateholders.TextSMSStateHolder;
import stateholders.TimerStateHolder;
import stateholders.TypicalSettingsHolder;
import stateholders.UserSettingsStateHolder;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.olegbrovko.smsgeneratorconvoy.R;

public class MainActivity extends Activity implements OnClickListener
{
	public enum ApplicationMode
	{
		APPLICATION_MODE_AUTO,
		APPLICATION_MODE_MANUAL,
	}
	
	public static final String DEFAULT_SETTINGS = "StandartSettingsConvoySMS_28102015";
	public static final String FUNCTION_301 = "Function301_28102015";
	public static final String FUNCTION_302 = "Function302_28102015";
	public static final String FUNCTION_303 = "Function303_28102015";
	public static final String FUNCTION_304 = "Function304_28102015";
	public static final String FUNCTION_305 = "Function305_28102015";
	public static final String FUNCTION_306 = "Function306_28102015";
	public static final String STAND_USER_SETTINGS = "STAND_USER_SETTINGS_28102015";
	
	public static ApplicationMode applicationMode = ApplicationMode.APPLICATION_MODE_AUTO;
	public static MainActivity instance = null;
	
	public static final String FOLDER_WITH_SETTINGS_NAME = "ConvoySMSSettings";
	
	public final static String BACK_BTN_PRESS_CNT = "com.olegbrovko.BACK_BTN_PRESS_CNT";
	public final static String USER_ALREADY_LOGGED_IN = "USER_ALREADY_LOGGED_IN";
	public final static int RUN_LOGIN_ACTIVITY_REQUEST = 101;
	
	public static SharedPreferences m_sharedPreferences = null;
	
	private static boolean m_userAlreadyLoggedIn = false;
	private int m_BackButtonPressedCount = 0;
	
	Button btnCustomSettings = null;
	Button btnInputs = null;
	Button btnOutputs = null;
	Button btnUniversalTimeChannels = null;
	Button btnNotifications = null;
	Button btnQueryConfigurations = null;
	Button btnCAN = null;
	Button btnTextSMS = null;
	ImageButton imgbtnInfo = null;
	
	//StateHolders
	public static UserSettingsStateHolder stateholderUserSettings = new UserSettingsStateHolder();
	public static InputsStateHolder stateholderInputs = new InputsStateHolder();
	public static OutputsStateHolder stateholderOutputs = new OutputsStateHolder();
	public static TimerStateHolder stateholderTimer = new TimerStateHolder();
	public static NotificationStateHolder stateholderNotification = new NotificationStateHolder();
	public static QueryConfigurationStateHolder stateholderQueryConfigurations = new QueryConfigurationStateHolder();
	public static CANStateHolder stateholderCAN = new CANStateHolder();
	public static TextSMSStateHolder stateholderTextSMS = new TextSMSStateHolder();
	
	//Generators
	public static GeneratorQueryConfigurations generatorQueryConfigurations = null;
	public static GeneratorUserSettings generatorUserSettings = null;
	public static GeneratorInputs generatorInputs = null;
	public static GeneratorOutputs generatorOutputs = null;
	public static GeneratorTimer generatorTimer = null;
	public static GeneratorNotifications generatorNotifications = null;
	public static GeneratorCAN generatorCAN = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		if (getIntent().getBooleanExtra("EXIT", false)) 
		{
		    finish();
		    return;
		}
		instance = this;
		m_sharedPreferences = getPreferences(MODE_PRIVATE);
		setLanguage();
		setContentView(R.layout.activity_main);
		
		//Views initialization
		btnCustomSettings = (Button) findViewById(R.id.btnCustomSettings);
		btnInputs = (Button) findViewById(R.id.btnInputs);
		btnOutputs = (Button) findViewById(R.id.btnOutputs);
		btnUniversalTimeChannels = (Button) findViewById(R.id.btnUniversalTimeChannels);
		btnNotifications = (Button) findViewById(R.id.btnNotifications);
		btnQueryConfigurations = (Button) findViewById(R.id.btnQueryConfigurations);
		btnCAN = (Button) findViewById(R.id.btnCAN);
		btnTextSMS = (Button) findViewById(R.id.btnTextSMS);
		imgbtnInfo = (ImageButton) findViewById(R.id.imgbtnInfo);
		
		//Generators
		generatorQueryConfigurations = new GeneratorQueryConfigurations();
		generatorUserSettings = new GeneratorUserSettings();
		generatorInputs = new GeneratorInputs();
		generatorOutputs = new GeneratorOutputs();
		generatorTimer = new GeneratorTimer();
		generatorNotifications = new GeneratorNotifications();
		generatorCAN = new GeneratorCAN();
		
		//Restore state
		if(savedInstanceState != null)
		{
			m_BackButtonPressedCount = savedInstanceState.getInt(BACK_BTN_PRESS_CNT);
			m_userAlreadyLoggedIn = savedInstanceState.getBoolean(USER_ALREADY_LOGGED_IN);
		}
		
		//If user already logged in - not to start login activity
//		if(!m_userAlreadyLoggedIn)
//		{
//			Intent intent = new Intent(this, LoginActivity.class);
//			startActivityForResult(intent, RUN_LOGIN_ACTIVITY_REQUEST);
//		}
		
		//Listeners
		btnCustomSettings.setOnClickListener(this);
		btnInputs.setOnClickListener(this);
		btnOutputs.setOnClickListener(this);
		btnUniversalTimeChannels.setOnClickListener(this);
		btnNotifications.setOnClickListener(this);
		btnQueryConfigurations.setOnClickListener(this);
		btnCAN.setOnClickListener(this);
		btnTextSMS.setOnClickListener(this);
		imgbtnInfo.setOnClickListener(this);
		
		createStateholders();
		
		//Create files for typical settings
		createSettings(DEFAULT_SETTINGS);
		createSettings(FUNCTION_301);
		createSettings(FUNCTION_302);
		createSettings(FUNCTION_303);
		createSettings(FUNCTION_304);
		createSettings(FUNCTION_305);
		createSettings(FUNCTION_306);
		createSettings(STAND_USER_SETTINGS);
		
	}
	
	private void createStateholders()
	{
		UserSettings.m_stateholder = stateholderUserSettings;
		Inputs.m_stateholder = stateholderInputs;
		Outputs.m_stateholder = stateholderOutputs;
		UniversalTimeChannels.m_stateholder = stateholderTimer;
		Notifications.m_stateholder = stateholderNotification;
		QueryConfiguration.m_stateholder = stateholderQueryConfigurations;
		CAN.m_stateholder = stateholderCAN;
		TextSMS.m_stateholder = stateholderTextSMS;
	}
	
	@Override
	public void onBackPressed() 
	{
		//super.onBackPressed();
		++m_BackButtonPressedCount;
		
		if(m_BackButtonPressedCount == 1)
		{
			Toast.makeText(this, "Press one more time to exit from application", Toast.LENGTH_SHORT).show();
		}
		else if(m_BackButtonPressedCount == 2)
		{
			//finish
			m_BackButtonPressedCount = 0;
			//moveTaskToBack (true);
			Intent intent = new Intent(getApplicationContext(), MainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("EXIT", true);
			startActivity(intent);
			System.exit(0);
		}
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) 
	{
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
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
	public void onSaveInstanceState(Bundle savedInstanceState) 
	{
		savedInstanceState.putInt(BACK_BTN_PRESS_CNT, m_BackButtonPressedCount);
		savedInstanceState.putBoolean(USER_ALREADY_LOGGED_IN, m_userAlreadyLoggedIn);
		super.onSaveInstanceState(savedInstanceState);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		if (data == null) {return;}
		
		if (requestCode == RUN_LOGIN_ACTIVITY_REQUEST) 
		{
	        if (resultCode == RESULT_OK) 
	        {
	        	m_userAlreadyLoggedIn = data.getBooleanExtra(LoginActivity.USER_LOGGED_IN, false);
	        }
	    }
	}

	@Override
	public void onClick(View v) 
	{
		Intent intent = null;
		switch(v.getId())
		{
			case R.id.btnCustomSettings:
			{
				intent = new Intent(this, UserSettings.class);
				break;
			}
			case R.id.btnInputs:
			{
				intent = new Intent(this, Inputs.class);
				break;
			}
			case R.id.btnOutputs:
			{
				intent = new Intent(this, Outputs.class);
				break;
			}
			case R.id.btnUniversalTimeChannels:
			{
				intent = new Intent(this, UniversalTimeChannels.class);
				break;
			}
			case R.id.btnNotifications:
			{
				intent = new Intent(this, Notifications.class);
				break;
			}
			case R.id.btnQueryConfigurations:
			{
				intent = new Intent(this, QueryConfiguration.class);
				break;
			}
			case R.id.btnCAN:
			{
				intent = new Intent(this, CAN.class);
				break;
			}
			case R.id.btnTextSMS:
			{
				intent = new Intent(this, TextSMS.class);
				break;
			}
			case R.id.imgbtnInfo:
			{
				intent = new Intent(this, Info.class);
				break;
			}
		}
		
		m_BackButtonPressedCount = 0;
		startActivity(intent);
	}
	
	public void updateUI()
	{
		btnCustomSettings.setText(R.string.btn_Main_UserSettings);
		btnInputs.setText(R.string.btn_Main_Inputs);
		btnOutputs.setText(R.string.btn_Main_Outputs);
		btnUniversalTimeChannels.setText(R.string.btn_Main_Timer);
		btnNotifications.setText(R.string.btn_Main_Notifications);
		btnQueryConfigurations.setText(R.string.btn_Main_QueryConfigurations);
		btnCAN.setText(R.string.btn_Main_CAN);
		btnTextSMS.setText(R.string.btn_Main_TextSMS);
	}
	
	private void setLanguage()
	{
		String language = m_sharedPreferences.getString(ChangeLanguagePopup.LANGUAGE, "ru");
		Locale locale = new Locale(language);
		Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
	}
	
	@Override
	protected void onRestart() 
	{
		updateUI();
		super.onRestart();
	}
	
	@Override
	protected void onResume() 
	{
		super.onResume();
		
		boolean buttonsEnabled = !stateholderTextSMS.getManualModeChecked();
		btnCustomSettings.setEnabled(buttonsEnabled);
		btnInputs.setEnabled(buttonsEnabled);
		btnOutputs.setEnabled(buttonsEnabled);
		btnUniversalTimeChannels.setEnabled(buttonsEnabled);
		btnNotifications.setEnabled(buttonsEnabled);
		btnQueryConfigurations.setEnabled(buttonsEnabled);
		btnCAN.setEnabled(buttonsEnabled);
	}
	
	@Override
	protected void onDestroy() 
	{
		super.onDestroy();
	}
	
//======================================================== TYPICAL SETTINGS ========================================================
	private void createSettings(String fileWithSettingsName)
	{
		File folder = new File(Environment.getExternalStorageDirectory() + "/" + FOLDER_WITH_SETTINGS_NAME);
		boolean success = true;
		if (!folder.exists()) 
		{
		    success = folder.mkdir();
		}
		
		if (success) 
		{	
			try 
			{
				String fname = fileWithSettingsName +".txt";
			    File file = new File (folder, fname);
			    if (file.exists ()) 
			    	file.delete ();
			    
			    FileOutputStream fOut = new FileOutputStream(file);
			    OutputStreamWriter osw = new OutputStreamWriter(fOut);
			    osw.write(TypicalSettingsHolder.getSettingsByName(fileWithSettingsName));
			    osw.flush();
			    osw.close();
			} 
			catch (Exception e) 
			{
				Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			}
		} 
		else 
		{
		    // Do something else on failure 
		}
	}
	
}
