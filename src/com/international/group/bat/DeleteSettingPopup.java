package com.international.group.bat;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

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
import android.widget.Toast;

public class DeleteSettingPopup extends Activity implements OnClickListener
{
	final String LOG_TAG = "myLogs";
	
	String m_fileForDeleting = "";
	ArrayList<TextView> m_allFiles = new ArrayList<TextView>();
	
	public static final String FOLDER_WITH_SETTINGS_NAME = "ConvoySMSSettings";
	public static final String LANGUAGE = "USER_LANGUAGE";
	public static final String PARENT_ACTIVITY_NAME = "PARENT_ACTIVITY_NAME";
	String nameOfParentActivity = null;

	TextView tv_DeleteSettings_Header = null;
	TextView tv_DeleteSettings_EnterNameForNewSettings = null;
	LinearLayout ll_DeleteSettings_SavedSettings = null;
	Button btn_DeleteSettings_Save = null;
	Button btn_DeleteSettings_Cancel = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delete_settings_popup);
		
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
		TextView tvFileToDelete = (TextView) v;
		
		String name = v.getClass().getName();
		if(tvFileToDelete != null && name.equals("android.widget.TextView"))
		{
			for (TextView temp : m_allFiles) 
			{
				temp.setBackgroundColor(Color.parseColor("#ffffff"));
			}
			
			tvFileToDelete.setBackgroundColor(Color.parseColor("#4fa5d5"));
			//Replace file names
			String fileToDelete = tvFileToDelete.getText().toString();
			if(fileToDelete.endsWith("(A)")) {
				fileToDelete = fileToDelete.replace("(A)", ".txt");
			}
			else if(fileToDelete.endsWith("(M)")) {
				fileToDelete = fileToDelete.replace("(M)", ".xml");
			}
			m_fileForDeleting = fileToDelete;
		}
		
		//Buttons
		switch(v.getId())
		{
			case R.id.btn_DeleteSettings_Save:
			{
				deleteSettings();
				setResult(RESULT_OK);
		        finish();
				break;
			}
			case R.id.btn_DeleteSettings_Cancel:
			{
				finish();
				break;
			}
		}
	}
	
	private void init()
	{
		ll_DeleteSettings_SavedSettings = (LinearLayout) findViewById(R.id.ll_DeleteSettings_SavedSettings);
		btn_DeleteSettings_Save = (Button) findViewById(R.id.btn_DeleteSettings_Save);
		btn_DeleteSettings_Cancel = (Button) findViewById(R.id.btn_DeleteSettings_Cancel);
		tv_DeleteSettings_Header = (TextView) findViewById(R.id.tv_DeleteSettings_Header);
		tv_DeleteSettings_EnterNameForNewSettings = (TextView) findViewById(R.id.tv_DeleteSettings_EnterNameForNewSettings);
	}
	
	private void setListeners()
	{
		btn_DeleteSettings_Save.setOnClickListener(this);
		btn_DeleteSettings_Cancel.setOnClickListener(this);
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
			if(acceptFileCreation)
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

					m_fileForDeleting = fileName;
				}
				
				m_allFiles.add(view);
				ll_DeleteSettings_SavedSettings.addView(view);
			}
		}
	}
	
	private void deleteSettings()
	{
		if(m_fileForDeleting.length() == 0)
		{
			Toast.makeText(this, "Nothing to delete", Toast.LENGTH_SHORT).show();
			return;
		}
		
		String settingsStoragePath = Environment.getExternalStorageDirectory().toString()+"/"+FOLDER_WITH_SETTINGS_NAME;
		File settingsStorage = new File(settingsStoragePath);
		if(!settingsStorage.exists())
			return;
		
		try 
		{
			File file = new File(settingsStorage, m_fileForDeleting);
			file.delete();
		}
		catch (Exception e) 
		{
		    //You'll need to add proper error handling here
		}
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
		tv_DeleteSettings_Header.setText(R.string.tv_DeletePopup_Header);
		tv_DeleteSettings_EnterNameForNewSettings.setText(R.string.tv_DeletePopup_SubHeader);
	}
	
}
