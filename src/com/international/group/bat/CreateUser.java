package com.international.group.bat;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateUser extends Activity 
{
	static final String TEXT_PHONE = "USER_PHONE_NUMBER";
	static final String TEXT_PIN = "USER_PIN";
	private int m_BackButtonPressedCount = 0;
	
	SharedPreferences m_sharedPreferences = null;
	
	EditText et_CreateUser_PhoneNumber = null;
	EditText et_CreateUser_PIN = null;
	EditText et_CreateUser_PINConfirm = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_user);
		
		et_CreateUser_PhoneNumber = (EditText) findViewById(R.id.et_CreateUser_PhoneNumber);
		et_CreateUser_PIN = (EditText) findViewById(R.id.et_CreateUser_PIN);
		et_CreateUser_PINConfirm = (EditText) findViewById(R.id.et_CreateUser_PINConfirm);
		
		
		et_CreateUser_PhoneNumber.setOnKeyListener
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
							finish();
						}
	                    return true;
	                } 
					return false;
				}
			}
		);
		et_CreateUser_PIN.setOnKeyListener
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
							finish();
						}
	                    return true;
	                } 
					return false;
				}
			}
		);
		et_CreateUser_PINConfirm.setOnKeyListener
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
							finish();
						}
	                    return true;
	                } 
					return false;
				}
			}
		);
		
		et_CreateUser_PhoneNumber.setSelection(et_CreateUser_PhoneNumber.getText().length());
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
		m_sharedPreferences = LoginActivity.m_sharedPreferences;//getPreferences(MODE_PRIVATE);
	    String userPhoneNumber = m_sharedPreferences.getString(TEXT_PHONE, "");
	    String userPIN = m_sharedPreferences.getString(TEXT_PIN, "");
	    
	    if(userPhoneNumber.equals("") || userPIN.equals(""))
	    {
	    	if(correctPhoneAndPINFormat())
	    	{
	    		m_sharedPreferences = LoginActivity.m_sharedPreferences;//getPreferences(MODE_PRIVATE);
	    	    Editor ed = m_sharedPreferences.edit();
	    	    ed.putString(TEXT_PHONE, et_CreateUser_PhoneNumber.getText().toString());
	    	    ed.putString(TEXT_PIN, et_CreateUser_PIN.getText().toString());
	    	    ed.commit();
	    	    return true;
	    	}
	    }
	    
		return false;
	}
	
	private boolean correctPhoneAndPINFormat()
	{
		if(et_CreateUser_PhoneNumber.getText().toString().length() != 13)
		{
			Toast.makeText(this, "Phone number length does not match", Toast.LENGTH_SHORT).show();
			return false;
		}
		else if(!et_CreateUser_PhoneNumber.getText().toString().substring(0, 4).equals("+380"))
		{
			Toast.makeText(this, "Incorrect country number, should be: +380...", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		if(et_CreateUser_PIN.getText().toString().length() != 4)
		{
			Toast.makeText(this, "Incorrect PIN format", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		if(!et_CreateUser_PIN.getText().toString().equals(et_CreateUser_PINConfirm.getText().toString()))
		{
			Toast.makeText(this, "PIN Confirmation failed", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		return true;
	}
}
