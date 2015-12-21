package com.olegbrovko.smsgeneratorconvoy;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.olegbrovko.smsgeneratorconvoy.R;

public class LoginActivity extends Activity 
{
	public static final String TEXT_PHONE = "USER_PHONE_NUMBER";
	public static final String TEXT_PIN = "USER_PIN";
	public static final String USER_LOGGED_IN = "USER_LOGGED_IN";
	private int m_BackButtonPressedCount = 0;
	
	public static SharedPreferences m_sharedPreferences = null;
	
	EditText et_Login_PhoneNumber = null;
	EditText et_Login_PIN = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		et_Login_PhoneNumber = (EditText) findViewById(R.id.et_Login_PhoneNumber);
		et_Login_PIN = (EditText) findViewById(R.id.et_Login_PIN);
		
		et_Login_PhoneNumber.setSelection(et_Login_PhoneNumber.getText().length());
		
		m_sharedPreferences = getPreferences(MODE_PRIVATE);
	    String userPhoneNumber = m_sharedPreferences.getString(TEXT_PHONE, "");
	    String userPIN = m_sharedPreferences.getString(TEXT_PIN, "");
		if(userPhoneNumber.equals("") || userPIN.equals(""))
	    {
			Intent i = new Intent(this, CreateUser.class);
			startActivity(i);
	    }
		else
		{
			et_Login_PhoneNumber.setText(userPhoneNumber);
		}
		
		et_Login_PhoneNumber.setOnKeyListener
		(
			new EditText.OnKeyListener()
			{
	

				@Override
				public boolean onKey(View view, int key_code, KeyEvent key_event) 
				{
					// TODO Auto-generated method stub
					if (key_event.getAction() == KeyEvent.ACTION_DOWN && key_event.getKeyCode() == KeyEvent.KEYCODE_ENTER) 
	                {
						if(login())
						{
							returnResultUserLoggedIn();
							finish();
						}
	                    return true;
	                } 
					return false;
				}
			}
		);
		et_Login_PIN.setOnKeyListener
		(
			new EditText.OnKeyListener()
			{
	

				@Override
				public boolean onKey(View view, int key_code, KeyEvent key_event) 
				{
					// TODO Auto-generated method stub
					if (key_event.getAction() == KeyEvent.ACTION_DOWN && key_event.getKeyCode() == KeyEvent.KEYCODE_ENTER) 
	                {
						if(login())
						{
							returnResultUserLoggedIn();
							finish();
						}
	                    return true;
	                } 
					return false;
				}
			}
		);
		
	}
	
	@Override
	public void onBackPressed() 
	{
		// TODO Auto-generated method stub
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
			moveTaskToBack (true);
		}
	}
	
	private boolean login()
	{
		m_sharedPreferences = getPreferences(MODE_PRIVATE);
	    String userPhoneNumber = m_sharedPreferences.getString(TEXT_PHONE, "");
	    String userPIN = m_sharedPreferences.getString(TEXT_PIN, "");
	    
	    if(userPhoneNumber.equals("") || userPIN.equals(""))
	    {
	    	if(correctPhoneAndPINFormat())
	    	{
	    		m_sharedPreferences = getPreferences(MODE_PRIVATE);
	    	    Editor ed = m_sharedPreferences.edit();
	    	    ed.putString(TEXT_PHONE, et_Login_PhoneNumber.getText().toString());
	    	    ed.putString(TEXT_PIN, et_Login_PIN.getText().toString());
	    	    ed.commit();
	    	    return true;
	    	}
	    }
	    else
	    {
	    	boolean phoneCorrect = (userPhoneNumber.equals(et_Login_PhoneNumber.getText().toString()));
	    	boolean pinCorrect = (userPIN.equals(et_Login_PIN.getText().toString()));
	    	
	    	if(!phoneCorrect)
	    	{
	    		Toast.makeText(this, "Incorrect phone number", Toast.LENGTH_SHORT).show();
				return false;
	    	}
	    	
	    	if(!pinCorrect)
	    	{
	    		Toast.makeText(this, "Incorrect PIN", Toast.LENGTH_SHORT).show();
				return false;
	    	}
	    	
	    	return true;
	    }
	    
		return false;
	}
	
	private boolean correctPhoneAndPINFormat()
	{
		if(et_Login_PhoneNumber.getText().toString().length() != 13)
		{
			Toast.makeText(this, "Phone number length does not match", Toast.LENGTH_SHORT).show();
			return false;
		}
		else if(!et_Login_PhoneNumber.getText().toString().substring(0, 4).equals("+380"))
		{
			Toast.makeText(this, "Incorrect country number, should be: +380...", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		if(et_Login_PIN.getText().toString().length() != 4)
		{
			Toast.makeText(this, "Incorrect PIN format", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		return true;
	}
	
	private void returnResultUserLoggedIn()
	{
		Intent intent = new Intent();
	    intent.putExtra(USER_LOGGED_IN, true);
	    setResult(RESULT_OK, intent);
	}
}
