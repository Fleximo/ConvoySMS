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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

import stateholders.UserSettingsStateHolder;
import utils.EditTextWatcherInt;
import utils.ForwardEditTextWatcherInt;

public class UserSettings extends Activity implements OnCheckedChangeListener, OnSeekBarChangeListener, OnItemSelectedListener, OnFocusChangeListener
{
	public static UserSettings instance = null;
	
	//0)CURRENT PIN
	public CheckBox cb_UserSettings_CurrentPIN = null;
	public EditText et_UserSettings_CurrentPIN = null;
	//1)NEW PIN
	public CheckBox cb_UserSettings_NewPIN = null;
	public EditText et_UserSettings_NewPIN = null;
	//2)BALANCE CHECKING NUMBER
	public CheckBox cb_UserSettings_BalanceChecking = null;
	public Spinner spin_UsetSettings_BalanceChecking = null;
	public EditText et_UserSettings_BalanceCheckingAnother = null;
	//3)BALANCE AUTO-CHECKING
	public CheckBox cb_UserSettings_AccBalance = null;
	public EditText etNumSumPosition = null;
	public EditText etCriticalBalance = null;
	public EditText etBalanceCheckinngFrequency = null;
	//4) NUMBER OF CALL ATTEMPTS
	public CheckBox cb_UserSettings_NumberCallAttempts = null;
	public EditText etNumberCallAttempts = null;
	// REARM
	public CheckBox cb_UserSettings_Rearm = null;
	public Spinner spin_UserSettings_Rearm = null;
	//5) SIREN
	public CheckBox cb_UserSettings_Siren = null;
	public Spinner spin_UserSettings_SystemSetUnset = null;
	public Spinner spin_UserSettings_AlarmMode = null;
	public Spinner spin_UserSettings_SignalsNumberWarningZone = null;
	//6) TWO STAGE DISARMING SYSTEM
	public CheckBox cb_UserSettings_DisarmingSystem = null;
	public Spinner spin_UserSettings_DisarmingSystem = null;
	//7) ALL SYSTEM SENSORS
	public CheckBox cb_UserSettings_AllSensorsDisabled = null;
	public Spinner spin_UserSettings_TiltSens_AllSensEnabled = null;
	//8) SHOCK SENSOR
	public CheckBox cb_UserSettings_ShockSensSettings = null;
	public CheckBox cb_UserSettings_WarningTrasholdDisabled = null;
	public SeekBar sb_UserSettings_WarningTrashold = null;
	public EditText et_UserSettings_WarningTrashold = null;
	public CheckBox cb_UserSettings_AlarmTrasholdDisabled = null;
	public SeekBar sb_UserSettings_AlarmTrashold = null;
	public EditText et_UserSettings_AlarmTrashold = null;
	//9) TILT SENSOR
	public CheckBox cb_UserSettings_TiltSensor = null;
	public CheckBox cb_UserSettings_TiltSensorDisabled = null;
	public SeekBar sbTiltSensor = null;
	public EditText etTiltSensor = null;
	//10) MICROPHONE
	public CheckBox cb_UserSettings_PhoneSensitivity = null;
	public CheckBox cb_UserSettings_PhoneSensitivityDisabled = null;
	public SeekBar sbPhoneSensitivity = null;
	public EditText etPhoneSensitivity = null;
	//11) SPEAKER
	public CheckBox cb_UserSettings_SpeakerVolume = null;
	public CheckBox cb_UserSettings_SpeakerVolumeDisabled = null;
	public SeekBar sbSpeakerVolume = null;
	public EditText etSpeakerVolume = null;
    //11) LABEL SETTINGS
    public CheckBox cb_UserSettings_LabelSettings = null;
    public CheckBox cb_UserSettings_LabelSettingsDisabled = null;
    public SeekBar sbLabelSettings = null;
    public EditText etLabelSettings = null;

	public Spinner spin_UserSettings_LabelEnter = null;
	public Spinner spin_UserSettings_LabelExit = null;
	public Spinner spin_UserSettings_LabelConfirmFuncDisarming = null;
	//SMS REPORTING ARMING
	public CheckBox cb_UserSettings_SMSReportingArming = null;
	public Spinner spin_UserSettings_SMSReportingArming = null;
	//12) MONITORING
	public CheckBox cb_UserSettings_MonitoringSettings = null;
	public Spinner spin_UserSettings_MonitoringSettings = null;
	//13) ACCESS POINT NUMBER OF MOBILE OPERATOR
	public CheckBox cb_UserSettings_AccessPointNameMobOper = null;
	public Spinner spin_UsetSettings_AccessPointNameMobOper = null;
	public EditText et_UserSettings_AccessPointNameMobOperAnother = null;
	
	//State holder
	static UserSettingsStateHolder m_stateholder = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		instance = this;
		setLanguage();
		setContentView(R.layout.user_settings);
		
		//Init all members
		initMembers();
		initTwoStageDisarming();			
		//Set values to the balance checking number
		setValuesToTheBalanceCheckingSpinner();
		//RestoreState
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
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev)
	{
	    View v = getCurrentFocus();
	     
	    if (v != null && 
	            (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && 
	            v instanceof EditText && 
	            !v.getClass().getName().startsWith("android.webkit."))
	    {
	        int scrcoords[] = new int[2];
	        v.getLocationOnScreen(scrcoords);
	        float x = ev.getRawX() + v.getLeft() - scrcoords[0];
	        float y = ev.getRawY() + v.getTop() - scrcoords[1];
	        
	        EditText et = (EditText) v;
	        if ((x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom()) && (et != null))
	        {
	        	switch(et.getId())
	        	{
		        	//CURRENT PIN
	        		case R.id.et_UserSettings_CurrentPIN:
	        		{
	        			if(et.getText().toString().length() != 4)
	        				et.setText(m_stateholder.getEtCurrentPINValue());
	        			break;
	        		}
	        		//NEW PIN
	        		case R.id.et_UserSettings_NewPIN:
	        		{
	        			if(et.getText().toString().length() != 4)
	        				et.setText("0000");
	        			break;
	        		}
	        		//ACCOUNT BALANCE AUTOCHECK
	        		case R.id.et_UserSettings_NumSumPosition:
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
	        		case R.id.et_UserSettings_CriticalBalance:
	        		{
	        			try 
	        			{
	        				Integer value = Integer.parseInt(et.getText().toString());
		        			if(value < 1)
		        				et.setText("1");
	        			} catch(NumberFormatException e)
	        			{et.setText("10");}
	        			break;
	        		}
	        		case R.id.et_UserSettings_BalanceCheckinngFrequency:
	        		{
	        			try 
	        			{
		        			Integer value = Integer.parseInt(et.getText().toString());
		        			if(value < 1)
		        				et.setText("1");
	        			}catch(NumberFormatException e)
	        			{et.setText("24");}
	        			break;
	        		}
	        		//NUMBER OF CALL ATTEMPTS
	        		case R.id.et_UserSettings_NumberCallAttempts:
	        		{
	        			try 
	        			{
		        			Integer value = Integer.parseInt(et.getText().toString());
		        			if(value < 1)
		        				et.setText("1");
	        			}catch(NumberFormatException e)
	        			{et.setText("1");}
	        			break;
	        		}
	        		//SHOCK SENSOR
	        		case R.id.et_UserSettings_WarningTrashold:
	        		{
	        			try 
	        			{
		        			Integer value = Integer.parseInt(et.getText().toString());
		        			if(value < 5)
		        				et.setText("5");
	        			}catch(NumberFormatException e)
	        			{et.setText("18");}
	        			break;
	        		}
	        		case R.id.et_UserSettings_AlarmTrashold:
	        		{
	        			try 
	        			{
		        			Integer value = Integer.parseInt(et.getText().toString());
		        			if(value < 5)
		        				et.setText("5");
	        			}catch(NumberFormatException e)
	        			{et.setText("32");}
	        			break;
	        		}
	        		//TILT SENSOR
	        		case R.id.et_UserSettings_TiltSensor:
	        		{
	        			try 
	        			{
		        			Integer value = Integer.parseInt(et.getText().toString());
		        			if(value < 1)
		        				et.setText("1");
	        			}catch(NumberFormatException e)
	        			{et.setText("1");}
	        			break;
	        		}
	        		//PHONE SENSATIVITY
	        		case R.id.et_UserSettings_PhoneSensitivity:
	        		{
	        			try 
	        			{
		        			Integer value = Integer.parseInt(et.getText().toString());
		        			if(value < 1)
		        				et.setText("1");
	        			}catch(NumberFormatException e)
	        			{et.setText("5");}
	        			break;
	        		}
	        		//SPEAKER
	        		case R.id.et_UserSettings_SpeakerVolume:
	        		{
	        			try 
	        			{
		        			Integer value = Integer.parseInt(et.getText().toString());
		        			if(value < 1)
		        				et.setText("1");
	        			}catch(NumberFormatException e)
	        			{et.setText("50");}
	        			break;
	        		}
	        		
	        	}
	        }
	    }
	    return super.dispatchTouchEvent(ev);
	}
//================================= LISENERS ==============================================	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) 
	{
		switch(buttonView.getId())
		{
			//0) CURRENT PIN
			case R.id.cb_UserSettings_CurrentPIN:
			{
				et_UserSettings_CurrentPIN.setEnabled(isChecked);
				break;
			}
			//1) NEW PIN
			case R.id.cb_UserSettings_NewPIN:
			{
				et_UserSettings_NewPIN.setEnabled(isChecked);
				break;
			}
			//2) BALANCE CHECKING NUMBER
			case R.id.cb_UserSettings_BalanceChecking:
			{
				spin_UsetSettings_BalanceChecking.setEnabled(isChecked);
				if(isChecked == true && spin_UsetSettings_BalanceChecking.getSelectedItemPosition() == 3)
					et_UserSettings_BalanceCheckingAnother.setEnabled(true);
				else
					et_UserSettings_BalanceCheckingAnother.setEnabled(false );
				break;
			}
			//2)
			case R.id.cb_UserSettings_AccessPointNameMobOper:
			{
				spin_UsetSettings_AccessPointNameMobOper.setEnabled(isChecked);
				if(isChecked == true && spin_UsetSettings_AccessPointNameMobOper.getSelectedItemPosition() == 3)
					et_UserSettings_AccessPointNameMobOperAnother.setEnabled(true);
				else
					et_UserSettings_AccessPointNameMobOperAnother.setEnabled(false );
				break;
			}
			//3)
			case R.id.cb_UserSettings_NumberCallAttempts:
			{
				etNumberCallAttempts.setEnabled(isChecked);
				break;
			}
			//5) SIREN
			case R.id.cb_UserSettings_Siren:
			{
				spin_UserSettings_SystemSetUnset.setEnabled(isChecked);
				spin_UserSettings_AlarmMode.setEnabled(isChecked);
                spin_UserSettings_SignalsNumberWarningZone.setEnabled(isChecked);
				break;
			}
			//6) TWO STAGE DISARMING SYSTEM
			case R.id.cb_UserSettings_DisarmingSystem:
			{
				spin_UserSettings_DisarmingSystem.setEnabled(isChecked);
				break;
			}
			//5) Shock sensor
			case R.id.cb_UserSettings_ShockSensSettings:
			{
				cb_UserSettings_WarningTrasholdDisabled.setEnabled(isChecked);
				cb_UserSettings_AlarmTrasholdDisabled.setEnabled(isChecked);
				
				if(isChecked==true && !cb_UserSettings_WarningTrasholdDisabled.isChecked())
				{
					sb_UserSettings_WarningTrashold.setEnabled(true);
					et_UserSettings_WarningTrashold.setEnabled(true);
				}
				else
				{
					sb_UserSettings_WarningTrashold.setEnabled(false);
					et_UserSettings_WarningTrashold.setEnabled(false);
				}
				
				if(isChecked==true && !cb_UserSettings_AlarmTrasholdDisabled.isChecked())
				{
					sb_UserSettings_AlarmTrashold.setEnabled(true);
					et_UserSettings_AlarmTrashold.setEnabled(true);
				}
				else
				{
					sb_UserSettings_AlarmTrashold.setEnabled(false);
					et_UserSettings_AlarmTrashold.setEnabled(false);
				}
				break;
			}
			case R.id.cb_UserSettings_WarningTrasholdDisabled:
			{
				sb_UserSettings_WarningTrashold.setEnabled(!isChecked);
				et_UserSettings_WarningTrashold.setEnabled(!isChecked);
				break;
			}
			case R.id.cb_UserSettings_AlarmTrasholdDisabled:
			{
				sb_UserSettings_AlarmTrashold.setEnabled(!isChecked);
				et_UserSettings_AlarmTrashold.setEnabled(!isChecked);
				break;
			}
			//6) TILT SENSOR
			case R.id.cb_UserSettings_TiltSensor:
			{
				cb_UserSettings_TiltSensorDisabled.setEnabled(isChecked);
				
				if(isChecked==true && !cb_UserSettings_TiltSensorDisabled.isChecked())
				{
					sbTiltSensor.setEnabled(true);
					etTiltSensor.setEnabled(true);
				}
				else
				{
					sbTiltSensor.setEnabled(false);
					etTiltSensor.setEnabled(false);
				}
				break;
			}
			case R.id.cb_UserSettings_TiltSensorDisabled:
			{
				sbTiltSensor.setEnabled(!isChecked);
				etTiltSensor.setEnabled(!isChecked);
				break;
			}
			//7) ALL SYSTEM SENSORS
			case R.id.cb_UserSettings_AllSensorsDisabled:
			{
				spin_UserSettings_TiltSens_AllSensEnabled.setEnabled(isChecked);
				break;
			}
			//SMS REPORTING ARMING
			case R.id.cb_UserSettings_SMSReportingArming:
			{
				spin_UserSettings_SMSReportingArming.setEnabled(isChecked);
				break;
			}
			//7)
			case R.id.cb_UserSettings_MonitoringSettings:
			{
				spin_UserSettings_MonitoringSettings.setEnabled(isChecked);
				break;
			}
			//9) MICROPHONE
			case R.id.cb_UserSettings_PhoneSensitivity:
			{
				cb_UserSettings_PhoneSensitivityDisabled.setEnabled(isChecked);
				
				if(isChecked==true && !cb_UserSettings_PhoneSensitivityDisabled.isChecked())
				{
					sbPhoneSensitivity.setEnabled(true);
					etPhoneSensitivity.setEnabled(true);
				}
				else
				{
					sbPhoneSensitivity.setEnabled(false);
					etPhoneSensitivity.setEnabled(false);
				}
				break;
			}
			case R.id.cb_UserSettings_PhoneSensitivityDisabled:
			{
				sbPhoneSensitivity.setEnabled(!isChecked);
				etPhoneSensitivity.setEnabled(!isChecked);
				break;
			}
			//10) SPEAKER
			case R.id.cb_UserSettings_SpeakerVolume:
			{
				cb_UserSettings_SpeakerVolumeDisabled.setEnabled(isChecked);
				
				if(isChecked==true && !cb_UserSettings_SpeakerVolumeDisabled.isChecked())
				{
					sbSpeakerVolume.setEnabled(true);
					etSpeakerVolume.setEnabled(true);
				}
				else
				{
					sbSpeakerVolume.setEnabled(false);
					etSpeakerVolume.setEnabled(false);
				}
				break;
			}
			case R.id.cb_UserSettings_SpeakerVolumeDisabled:
			{
				sbSpeakerVolume.setEnabled(!isChecked);
				etSpeakerVolume.setEnabled(!isChecked);
				break;
			}
            //12) LABEL SETTING
            case R.id.cb_UserSettings_LabelSettings:
            {
                cb_UserSettings_LabelSettingsDisabled.setEnabled(isChecked);
				spin_UserSettings_LabelEnter.setEnabled(isChecked);
				spin_UserSettings_LabelExit.setEnabled(isChecked);
				spin_UserSettings_LabelConfirmFuncDisarming.setEnabled(isChecked);

                if(isChecked==true && !cb_UserSettings_LabelSettingsDisabled.isChecked())
                {
                    sbLabelSettings.setEnabled(true);
                    etLabelSettings.setEnabled(true);
                }
                else
                {
                    sbLabelSettings.setEnabled(false);
                    etLabelSettings.setEnabled(false);
                }
                break;
            }
            case R.id.cb_UserSettings_LabelSettingsDisabled:
            {
                sbLabelSettings.setEnabled(!isChecked);
                etLabelSettings.setEnabled(!isChecked);
                break;
            }
			//11)
			case R.id.cb_UserSettings_AccBalance:
			{
				etNumSumPosition.setEnabled(isChecked);
				etCriticalBalance.setEnabled(isChecked);
				etBalanceCheckinngFrequency.setEnabled(isChecked);
				break;
			}
			//REARM
			case R.id.cb_UserSettings_Rearm:
			{
				spin_UserSettings_Rearm.setEnabled(isChecked);
				break;
			}
			
		}
	}
//=================================== SPINNER LISTENERS ===================================	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int selectedItem, long arg3) 
	{
		switch(parent.getId())
		{
			case R.id.spin_UsetSettings_BalanceChecking:
			{
				if(selectedItem == 3 && cb_UserSettings_BalanceChecking.isChecked())
					et_UserSettings_BalanceCheckingAnother.setEnabled(true);
				else
					et_UserSettings_BalanceCheckingAnother.setEnabled(false);
				break;
			}
			
			case R.id.spin_UsetSettings_AccessPointNameMobOper:
			{
				if(selectedItem == 3 && cb_UserSettings_AccessPointNameMobOper.isChecked())
					et_UserSettings_AccessPointNameMobOperAnother.setEnabled(true);
				else
					et_UserSettings_AccessPointNameMobOperAnother.setEnabled(false);
				break;
			}
			//ALL SENSORS
			case R.id.spin_UserSettings_TiltSens_AllSensEnabled:
			{
				if(selectedItem == 0)//disabled
				{
					//Shock sensors
					cb_UserSettings_ShockSensSettings.setEnabled(false);
					cb_UserSettings_WarningTrasholdDisabled.setEnabled(false);
					cb_UserSettings_AlarmTrasholdDisabled.setEnabled(false);
					sb_UserSettings_WarningTrashold.setEnabled(false);
					et_UserSettings_WarningTrashold.setEnabled(false);
					sb_UserSettings_AlarmTrashold.setEnabled(false);
					et_UserSettings_AlarmTrashold.setEnabled(false);
					
					//Tilt sensor
					cb_UserSettings_TiltSensor.setEnabled(false);
					cb_UserSettings_TiltSensorDisabled.setEnabled(false);
					sbTiltSensor.setEnabled(false);
					etTiltSensor.setEnabled(false);
				}
				else if(selectedItem == 1)//enabled
				{
					//Shock sensors
					cb_UserSettings_ShockSensSettings.setEnabled(true);
					boolean isCheckedShockSens = cb_UserSettings_ShockSensSettings.isChecked();
					
					cb_UserSettings_WarningTrasholdDisabled.setEnabled(isCheckedShockSens);
					cb_UserSettings_AlarmTrasholdDisabled.setEnabled(isCheckedShockSens);
					
					if(isCheckedShockSens==true && !cb_UserSettings_WarningTrasholdDisabled.isChecked())
					{
						sb_UserSettings_WarningTrashold.setEnabled(true);
						et_UserSettings_WarningTrashold.setEnabled(true);
					}
					else
					{
						sb_UserSettings_WarningTrashold.setEnabled(false);
						et_UserSettings_WarningTrashold.setEnabled(false);
					}
					
					if(isCheckedShockSens==true && !cb_UserSettings_AlarmTrasholdDisabled.isChecked())
					{
						sb_UserSettings_AlarmTrashold.setEnabled(true);
						et_UserSettings_AlarmTrashold.setEnabled(true);
					}
					else
					{
						sb_UserSettings_AlarmTrashold.setEnabled(false);
						et_UserSettings_AlarmTrashold.setEnabled(false);
					}
					
					//Tilt sensor
					cb_UserSettings_TiltSensor.setEnabled(true);
					boolean isCheckedTiltSens = cb_UserSettings_TiltSensor.isChecked();
					
					cb_UserSettings_TiltSensorDisabled.setEnabled(isCheckedTiltSens);
					
					if(isCheckedTiltSens==true && !cb_UserSettings_TiltSensorDisabled.isChecked())
					{
						sbTiltSensor.setEnabled(true);
						etTiltSensor.setEnabled(true);
					}
					else
					{
						sbTiltSensor.setEnabled(false);
						etTiltSensor.setEnabled(false);
					}
				}
				break;
			}
			
		}
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) 
	{}
	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{
		et_UserSettings_NewPIN.clearFocus();
		return super.onTouchEvent(event);
	}
//=================================== SEEKBAR LISTENERS ===================================		
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) 
	{
		switch(seekBar.getId())
		{
			//SHOCK SENSOR
			case R.id.sb_UserSettings_WarningTrashold:
			{
				try
				{
					Integer currProgress = Integer.valueOf(et_UserSettings_WarningTrashold.getText().toString());
					if((100 - progress) != currProgress);
					et_UserSettings_WarningTrashold.setText(Integer.toString(100 - progress));
				}
				catch(NumberFormatException e)
				{
					et_UserSettings_WarningTrashold.setText(Integer.toString(100-progress));
				}
				et_UserSettings_WarningTrashold.setSelection(et_UserSettings_WarningTrashold.getText().length());
				break;
			}
			case R.id.sb_UserSettings_AlarmTrashold:
			{
				try
				{
					Integer currProgress = Integer.valueOf(et_UserSettings_AlarmTrashold.getText().toString());
					if((100 - progress) != currProgress);
					et_UserSettings_AlarmTrashold.setText(Integer.toString(100 - progress));
				}
				catch(NumberFormatException e)
				{
					et_UserSettings_AlarmTrashold.setText(Integer.toString(100 - progress));
				}
				et_UserSettings_AlarmTrashold.setSelection(et_UserSettings_AlarmTrashold.getText().length());
				break;
			}
			//TILT SENSOR
			case R.id.sb_UserSettings_TiltSensor:
			{
				try
				{
					Integer currProgress = Integer.valueOf(etTiltSensor.getText().toString());
					if((9 - progress) != currProgress);
					etTiltSensor.setText(Integer.toString(9-progress));
				}
				catch(NumberFormatException e)
				{
					etTiltSensor.setText(Integer.toString(9-progress));
				}
				etTiltSensor.setSelection(etTiltSensor.getText().length());
				break;
			}
			//MICROPHONE
			case R.id.sbPhoneSensitivity:
			{
				try
				{
					Integer currProgress = Integer.valueOf(etPhoneSensitivity.getText().toString());
					if((progress + 1) != currProgress);
					etPhoneSensitivity.setText(Integer.toString(progress + 1));
				}
				catch(NumberFormatException e)
				{
					etPhoneSensitivity.setText(Integer.toString(progress + 1));
				}
				etPhoneSensitivity.setSelection(etPhoneSensitivity.getText().length());
				break;
			}
			//SPEAKER
			case R.id.sb_UserSettings_SpeakerVolume:
			{
				try
				{
					Integer currProgress = Integer.valueOf(etSpeakerVolume.getText().toString());
					if((progress + 1) != currProgress);
						etSpeakerVolume.setText(Integer.toString(progress + 1));
				}
				catch(NumberFormatException e)
				{
					etSpeakerVolume.setText(Integer.toString(progress + 1));
				}
				etSpeakerVolume.setSelection(etSpeakerVolume.getText().length());
				break;
			}
            //LABEL SETTINGS
            case R.id.sb_UserSettings_LabelSettings:
            {
                try
                {
                    Integer currProgress = Integer.valueOf(etLabelSettings.getText().toString());
                    if((progress + 1) != currProgress);
                    etLabelSettings.setText(Integer.toString(progress + 1));
                }
                catch(NumberFormatException e)
                {
                    etLabelSettings.setText(Integer.toString(progress + 1));
                }
                etLabelSettings.setSelection(etLabelSettings.getText().length());
                break;
            }
		}
	}
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) 
	{}
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) 
	{}
//================================= SPINNERS SET ==============================================	
	private void setValuesToTheBalanceCheckingSpinner()
	{
		//Balance checking
		ArrayAdapter<String> balanceCheckingMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.balance_checking));
		spin_UsetSettings_BalanceChecking.setAdapter(balanceCheckingMenu);
		
		//Access point number of mobile operator
		ArrayAdapter<String> accessPointNameMobOper = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.UserSettings_PointNameMobOper));
		spin_UsetSettings_AccessPointNameMobOper.setAdapter(accessPointNameMobOper);
				
		//Siren
		ArrayAdapter<String> sirenSetUnset = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.siren_set_unset));
		spin_UserSettings_SystemSetUnset.setAdapter(sirenSetUnset);
				
		ArrayAdapter<String> sirenAlarmMode = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.siren_alarm_mode));
		spin_UserSettings_AlarmMode.setAdapter(sirenAlarmMode);

        ArrayAdapter<String> signalsNumberWarningZone = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.siren_number_warning_zone));
        spin_UserSettings_SignalsNumberWarningZone.setAdapter(signalsNumberWarningZone);
		
		//Monitoring settings
		ArrayAdapter<String> monitoringSettingsArray = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.UserSettings_Monitoring));
		spin_UserSettings_MonitoringSettings.setAdapter(monitoringSettingsArray);

		//Label settings
		ArrayAdapter<String> labelEnterArray = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.UserSettings_LabelEnter));
		spin_UserSettings_LabelEnter.setAdapter(labelEnterArray);

		ArrayAdapter<String> labelExitArray = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.UserSettings_LabelExit));
		spin_UserSettings_LabelExit.setAdapter(labelExitArray);

		ArrayAdapter<String> labelConfirmFuncDisarmingArray = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.UserSettings_LabelConfirmFuncDisarming));
		spin_UserSettings_LabelConfirmFuncDisarming.setAdapter(labelConfirmFuncDisarmingArray);
		
		//All sensors
		ArrayAdapter<String> tiltSensorArray = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.UserSettings_TiltSensor));
		spin_UserSettings_TiltSens_AllSensEnabled.setAdapter(tiltSensorArray);
	}
		
//================================================ PRIVATE ===========================================================
	private void initMembers()
	{
		//0)CURRENT PIN
		cb_UserSettings_CurrentPIN = (CheckBox) findViewById(R.id.cb_UserSettings_CurrentPIN);
		et_UserSettings_CurrentPIN = (EditText) findViewById(R.id.et_UserSettings_CurrentPIN);
		//1)NEW PIN
		cb_UserSettings_NewPIN = (CheckBox) findViewById(R.id.cb_UserSettings_NewPIN);
		et_UserSettings_NewPIN = (EditText) findViewById(R.id.et_UserSettings_NewPIN);
		//2)NUMBER FOR BALANCE CHECKING
		cb_UserSettings_BalanceChecking = (CheckBox) findViewById(R.id.cb_UserSettings_BalanceChecking);
		spin_UsetSettings_BalanceChecking = (Spinner) findViewById(R.id.spin_UsetSettings_BalanceChecking);
		et_UserSettings_BalanceCheckingAnother = (EditText) findViewById(R.id.et_UserSettings_BalanceCheckingAnother);
		//3)BALANCE AUTO-CHECK
		cb_UserSettings_AccBalance = (CheckBox) findViewById(R.id.cb_UserSettings_AccBalance);
		etNumSumPosition = (EditText) findViewById(R.id.et_UserSettings_NumSumPosition);
		etCriticalBalance = (EditText) findViewById(R.id.et_UserSettings_CriticalBalance);
		etBalanceCheckinngFrequency = (EditText) findViewById(R.id.et_UserSettings_BalanceCheckinngFrequency);
		//4)NUMBER OF CALL ATTEMPTS
		cb_UserSettings_NumberCallAttempts = (CheckBox) findViewById(R.id.cb_UserSettings_NumberCallAttempts);
		etNumberCallAttempts = (EditText) findViewById(R.id.et_UserSettings_NumberCallAttempts);
		//REARM
		cb_UserSettings_Rearm = (CheckBox) findViewById(R.id.cb_UserSettings_Rearm);
		spin_UserSettings_Rearm = (Spinner) findViewById(R.id.spin_UserSettings_Rearm);
		//5)SIREN
		cb_UserSettings_Siren = (CheckBox) findViewById(R.id.cb_UserSettings_Siren);
		spin_UserSettings_SystemSetUnset = (Spinner) findViewById(R.id.spin_UserSettings_SystemSetUnset);
		spin_UserSettings_AlarmMode = (Spinner) findViewById(R.id.spin_UserSettings_AlarmMode);
        spin_UserSettings_SignalsNumberWarningZone = (Spinner) findViewById(R.id.spin_SignalsNumberWarningZone);
		//7) ALL SYSTEM SENSORS
		cb_UserSettings_AllSensorsDisabled  = (CheckBox) findViewById(R.id.cb_UserSettings_AllSensorsDisabled);
		//6)Shock sensor settings
		cb_UserSettings_ShockSensSettings = (CheckBox) findViewById(R.id.cb_UserSettings_ShockSensSettings);
		cb_UserSettings_WarningTrasholdDisabled = (CheckBox) findViewById(R.id.cb_UserSettings_WarningTrasholdDisabled);
		sb_UserSettings_WarningTrashold = (SeekBar) findViewById(R.id.sb_UserSettings_WarningTrashold);
		et_UserSettings_WarningTrashold = (EditText) findViewById(R.id.et_UserSettings_WarningTrashold);
		cb_UserSettings_AlarmTrasholdDisabled = (CheckBox) findViewById(R.id.cb_UserSettings_AlarmTrasholdDisabled);
		sb_UserSettings_AlarmTrashold = (SeekBar) findViewById(R.id.sb_UserSettings_AlarmTrashold);
		et_UserSettings_AlarmTrashold = (EditText) findViewById(R.id.et_UserSettings_AlarmTrashold);
		//2)Name of access point of mobile operator
		cb_UserSettings_AccessPointNameMobOper = (CheckBox) findViewById(R.id.cb_UserSettings_AccessPointNameMobOper);
		spin_UsetSettings_AccessPointNameMobOper = (Spinner) findViewById(R.id.spin_UsetSettings_AccessPointNameMobOper);
		et_UserSettings_AccessPointNameMobOperAnother = (EditText) findViewById(R.id.et_UserSettings_AccessPointNameMobOperAnother);
		spin_UsetSettings_AccessPointNameMobOper.setEnabled(false);
		et_UserSettings_AccessPointNameMobOperAnother.setEnabled(false);
		//6)Tilt sensor settings
		cb_UserSettings_TiltSensor = (CheckBox) findViewById(R.id.cb_UserSettings_TiltSensor);
		cb_UserSettings_TiltSensorDisabled = (CheckBox) findViewById(R.id.cb_UserSettings_TiltSensorDisabled);
		sbTiltSensor = (SeekBar) findViewById(R.id.sb_UserSettings_TiltSensor);
		etTiltSensor = (EditText) findViewById(R.id.et_UserSettings_TiltSensor);
		spin_UserSettings_TiltSens_AllSensEnabled = (Spinner) findViewById(R.id.spin_UserSettings_TiltSens_AllSensEnabled);
		cb_UserSettings_TiltSensorDisabled.setChecked(true);
		sbTiltSensor.setEnabled(false);
		etTiltSensor.setEnabled(false);
		spin_UserSettings_TiltSens_AllSensEnabled.setEnabled(false);
		spin_UserSettings_TiltSens_AllSensEnabled.setSelection(1);
		//SMS Reporting Arming
		cb_UserSettings_SMSReportingArming = (CheckBox) findViewById(R.id.cb_UserSettings_SMSReportingArming);
		spin_UserSettings_SMSReportingArming = (Spinner) findViewById(R.id.spin_UserSettings_SMSReportingArming);
		spin_UserSettings_SMSReportingArming.setEnabled(false);
		//7)Monitoring settings
		cb_UserSettings_MonitoringSettings = (CheckBox) findViewById(R.id.cb_UserSettings_MonitoringSettings);
		spin_UserSettings_MonitoringSettings = (Spinner) findViewById(R.id.spin_UserSettings_MonitoringSettings);
		spin_UserSettings_MonitoringSettings.setEnabled(false);
		//9)Phone sensitivity
		cb_UserSettings_PhoneSensitivity = (CheckBox) findViewById(R.id.cb_UserSettings_PhoneSensitivity);
		cb_UserSettings_PhoneSensitivityDisabled = (CheckBox) findViewById(R.id.cb_UserSettings_PhoneSensitivityDisabled);
		sbPhoneSensitivity = (SeekBar)findViewById(R.id.sbPhoneSensitivity);
		etPhoneSensitivity = (EditText) findViewById(R.id.et_UserSettings_PhoneSensitivity);
		sbPhoneSensitivity.setEnabled(false);
		etPhoneSensitivity.setEnabled(false);
		//10)Phone sensitivity
		cb_UserSettings_SpeakerVolume = (CheckBox) findViewById(R.id.cb_UserSettings_SpeakerVolume);
		cb_UserSettings_SpeakerVolumeDisabled = (CheckBox) findViewById(R.id.cb_UserSettings_SpeakerVolumeDisabled);
		sbSpeakerVolume = (SeekBar) findViewById(R.id.sb_UserSettings_SpeakerVolume);
		etSpeakerVolume = (EditText) findViewById(R.id.et_UserSettings_SpeakerVolume);
		sbSpeakerVolume.setEnabled(false);
		etSpeakerVolume.setEnabled(false);
        //12) LABEL SETTINGS
        cb_UserSettings_LabelSettings = (CheckBox) findViewById(R.id.cb_UserSettings_LabelSettings);
        cb_UserSettings_LabelSettingsDisabled = (CheckBox) findViewById(R.id.cb_UserSettings_LabelSettingsDisabled);
        sbLabelSettings = (SeekBar) findViewById(R.id.sb_UserSettings_LabelSettings);
        etLabelSettings = (EditText) findViewById(R.id.et_UserSettings_LabelSettings);
        sbLabelSettings.setEnabled(false);
        etLabelSettings.setEnabled(false);

		spin_UserSettings_LabelEnter = (Spinner) findViewById(R.id.spin_UserSettings_LabelEnter);
		spin_UserSettings_LabelEnter.setEnabled(false);

		spin_UserSettings_LabelExit = (Spinner) findViewById(R.id.spin_UserSettings_LabelExit);
		spin_UserSettings_LabelExit.setEnabled(false);

		spin_UserSettings_LabelConfirmFuncDisarming = (Spinner) findViewById(R.id.spin_UserSettings_LabelConfirmFuncDisarming);
		spin_UserSettings_LabelConfirmFuncDisarming.setEnabled(false);

		//------------------------------- SET STATE -------------------------------
		//0)Current PIN
		et_UserSettings_CurrentPIN.setEnabled(false);
		//1)New PIN
		et_UserSettings_NewPIN.setEnabled(false);
		//2)Number for balance checking
		spin_UsetSettings_BalanceChecking.setEnabled(false);
		et_UserSettings_BalanceCheckingAnother.setEnabled(false);
		//3)BALANCE AUTO-CHECK
		etNumSumPosition.setEnabled(false);
		EditTextWatcherInt etNumSumPositionTextWatcher = new EditTextWatcherInt(etNumSumPosition, null, "1", "0", "10");
		etNumSumPosition.addTextChangedListener(etNumSumPositionTextWatcher);
		etCriticalBalance.setEnabled(false);
		EditTextWatcherInt etCriticalBalanceTextWatcher = new EditTextWatcherInt(etCriticalBalance, null, "10", "1", "255");
		etCriticalBalance.addTextChangedListener(etCriticalBalanceTextWatcher);
		etBalanceCheckinngFrequency.setEnabled(false);
		EditTextWatcherInt BalanceCheckinngFrequencyTextWatcher = new EditTextWatcherInt(etBalanceCheckinngFrequency, null, "24", "1", "24");
		etBalanceCheckinngFrequency.addTextChangedListener(BalanceCheckinngFrequencyTextWatcher);
		//4)NUMBER OF CALL ATTEMPTS
		etNumberCallAttempts.setEnabled(false);
		EditTextWatcherInt numberCallAttemptsTextWatcher = new EditTextWatcherInt(etNumberCallAttempts, null, "155", "0", "10");
		etNumberCallAttempts.addTextChangedListener(numberCallAttemptsTextWatcher);
		//5)SIREN
		spin_UserSettings_SystemSetUnset.setSelection(1);
		spin_UserSettings_AlarmMode.setSelection(1);
        spin_UserSettings_SignalsNumberWarningZone.setSelection(3);
		spin_UserSettings_SystemSetUnset.setEnabled(false);
		spin_UserSettings_AlarmMode.setEnabled(false);
        spin_UserSettings_SignalsNumberWarningZone.setEnabled(false);
		//6)Shock sensor settings
		cb_UserSettings_WarningTrasholdDisabled.setEnabled(false);
		sb_UserSettings_WarningTrashold.setEnabled(false);
		et_UserSettings_WarningTrashold.setEnabled(false);
		cb_UserSettings_AlarmTrasholdDisabled.setEnabled(false);
		sb_UserSettings_AlarmTrashold.setEnabled(false);
		et_UserSettings_AlarmTrashold.setEnabled(false);
		EditTextWatcherInt etWarningTrasholdTextWatcher = new EditTextWatcherInt(et_UserSettings_WarningTrashold, sb_UserSettings_WarningTrashold, "18", "5", "100");
		et_UserSettings_WarningTrashold.addTextChangedListener(etWarningTrasholdTextWatcher);
		EditTextWatcherInt etAlarmTrasholdTextWatcher = new EditTextWatcherInt(et_UserSettings_AlarmTrashold, sb_UserSettings_AlarmTrashold, "32", "5", "100");
		et_UserSettings_AlarmTrashold.addTextChangedListener(etAlarmTrasholdTextWatcher);
		//TILT SENSOR
		EditTextWatcherInt etTiltSensorTextWatcher = new EditTextWatcherInt(etTiltSensor, sbTiltSensor, "0", "1", "9");
		etTiltSensor.addTextChangedListener(etTiltSensorTextWatcher);
		//MICROPHONE SENSATIVITY
		ForwardEditTextWatcherInt etPhoneSensitivityTextWatcher = new ForwardEditTextWatcherInt(etPhoneSensitivity, sbPhoneSensitivity, "5", "1", "15");
		etPhoneSensitivity.addTextChangedListener(etPhoneSensitivityTextWatcher);
		//SPEAKER VOLUME
		ForwardEditTextWatcherInt etSpeakerVolumeTextWatcher = new ForwardEditTextWatcherInt(etSpeakerVolume, sbSpeakerVolume, "50", "1", "100");
		etSpeakerVolume.addTextChangedListener(etSpeakerVolumeTextWatcher);
        //LABEL SETTINGS
        ForwardEditTextWatcherInt etLabelSettingsTextWatcher = new ForwardEditTextWatcherInt(etLabelSettings, sbLabelSettings, "5", "1", "9");
        etLabelSettings.addTextChangedListener(etLabelSettingsTextWatcher);
	}
	private void initTwoStageDisarming()
	{
		cb_UserSettings_DisarmingSystem = (CheckBox) findViewById(R.id.cb_UserSettings_DisarmingSystem);
		spin_UserSettings_DisarmingSystem = (Spinner) findViewById(R.id.spin_UserSettings_DisarmingSystem);
		
		spin_UserSettings_DisarmingSystem.setEnabled(false);
		
		ArrayAdapter<String> disarmingMode = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_timer_sys_disarm));
		spin_UserSettings_DisarmingSystem.setAdapter(disarmingMode);
		
		spin_UserSettings_DisarmingSystem.setSelection(0);
	}

	private void setListeners()
	{
		//1)CURRENT PIN
		cb_UserSettings_CurrentPIN.setOnCheckedChangeListener(this);
		et_UserSettings_CurrentPIN.setOnFocusChangeListener(this);
		//1)NEW PIN
		cb_UserSettings_NewPIN.setOnCheckedChangeListener(this);
		et_UserSettings_NewPIN.setOnFocusChangeListener(this);
		//2)BALANCE CHECKING NUMBER
		cb_UserSettings_BalanceChecking.setOnCheckedChangeListener(this);
		spin_UsetSettings_BalanceChecking.setOnItemSelectedListener(this);
		et_UserSettings_BalanceCheckingAnother.setOnFocusChangeListener(this);
		//3)BALANCE AUTO-CHECKING
		cb_UserSettings_AccBalance.setOnCheckedChangeListener(this);
		etNumSumPosition.setOnFocusChangeListener(this);
		etCriticalBalance.setOnFocusChangeListener(this);
		etBalanceCheckinngFrequency.setOnFocusChangeListener(this);
		//4) NUMBER OF CALL ATTEMPTS
		cb_UserSettings_NumberCallAttempts.setOnCheckedChangeListener(this);
		etNumberCallAttempts.setOnFocusChangeListener(this);
		//REARM
		cb_UserSettings_Rearm.setOnCheckedChangeListener(this);
		//5) SIREN
		cb_UserSettings_Siren.setOnCheckedChangeListener(this);
		//6) TWO STAGE DISARMING SYSTEM
		cb_UserSettings_DisarmingSystem.setOnCheckedChangeListener(this);
		//7) ALL SYSTEM SENSORS
		cb_UserSettings_AllSensorsDisabled.setOnCheckedChangeListener(this);
		spin_UserSettings_TiltSens_AllSensEnabled.setOnItemSelectedListener(this);
		//8) SHOCK SENSOR
		cb_UserSettings_ShockSensSettings.setOnCheckedChangeListener(this);
		cb_UserSettings_WarningTrasholdDisabled.setOnCheckedChangeListener(this);
		cb_UserSettings_AlarmTrasholdDisabled.setOnCheckedChangeListener(this);
		sb_UserSettings_WarningTrashold.setOnSeekBarChangeListener(this);
		sb_UserSettings_AlarmTrashold.setOnSeekBarChangeListener(this);
		et_UserSettings_WarningTrashold.setOnFocusChangeListener(this);
		et_UserSettings_AlarmTrashold.setOnFocusChangeListener(this);
		//10) TILT SENSOR
		cb_UserSettings_TiltSensor.setOnCheckedChangeListener(this);			
		cb_UserSettings_TiltSensorDisabled.setOnCheckedChangeListener(this);
		sbTiltSensor.setOnSeekBarChangeListener(this);
		sbPhoneSensitivity.setOnSeekBarChangeListener(this);
		etTiltSensor.setOnFocusChangeListener(this);
		//11) MICROPHONE
		cb_UserSettings_PhoneSensitivityDisabled.setOnCheckedChangeListener(this);
		cb_UserSettings_PhoneSensitivity.setOnCheckedChangeListener(this);
		etPhoneSensitivity.setOnFocusChangeListener(this);
		//12) SPEAKER
		cb_UserSettings_SpeakerVolumeDisabled.setOnCheckedChangeListener(this);
		cb_UserSettings_SpeakerVolume.setOnCheckedChangeListener(this);
		sbSpeakerVolume.setOnSeekBarChangeListener(this);
		etSpeakerVolume.setOnFocusChangeListener(this);
        //12) LABEL SETTINGS
        cb_UserSettings_LabelSettingsDisabled.setOnCheckedChangeListener(this);
        cb_UserSettings_LabelSettings.setOnCheckedChangeListener(this);
        sbLabelSettings.setOnSeekBarChangeListener(this);
        etLabelSettings.setOnFocusChangeListener(this);
		//13) ACCESS POINT NUMBER OF MOBILE OPERATOR
		cb_UserSettings_AccessPointNameMobOper.setOnCheckedChangeListener(this);
		spin_UsetSettings_AccessPointNameMobOper.setOnItemSelectedListener(this);
		et_UserSettings_AccessPointNameMobOperAnother.setOnFocusChangeListener(this);
		//SMS REPORTING ARMING
		cb_UserSettings_SMSReportingArming.setOnCheckedChangeListener(this);
		//7)MONITORING SETTINGS
		cb_UserSettings_MonitoringSettings.setOnCheckedChangeListener(this);	
	}
		
	private void saveState()
	{
		if(m_stateholder == null)
			m_stateholder = MainActivity.stateholderUserSettings;
		
		//0) CURRENT PIN
		m_stateholder.setCurrentPinEnabled(cb_UserSettings_CurrentPIN.isEnabled());
		m_stateholder.setCurrentPinChecked(cb_UserSettings_CurrentPIN.isChecked());
		m_stateholder.setEtCurrentPINEnabled(et_UserSettings_CurrentPIN.isEnabled());
		String currentPin = et_UserSettings_CurrentPIN.getText().toString();
		if(currentPin.length() != 4)
			currentPin = m_stateholder.getEtCurrentPINValue();
		m_stateholder.setEtCurrentPINValue(currentPin);
		//1) NEW PIN
		m_stateholder.setPinEnabled(cb_UserSettings_NewPIN.isEnabled());
		m_stateholder.setPinChecked(cb_UserSettings_NewPIN.isChecked());
		m_stateholder.setEtPINEnabled(et_UserSettings_NewPIN.isEnabled());
		String pin = et_UserSettings_NewPIN.getText().toString();
		if(pin.length() != 4)
			//pin = LoginActivity.m_sharedPreferences.getString(LoginActivity.TEXT_PIN, "");
			pin = "0000";
		m_stateholder.setEtPINValue(pin);
		
		//2) BALANCE CHECKING NUMBER
		m_stateholder.setBalanceChekingNumberEnabled(cb_UserSettings_BalanceChecking.isEnabled());
		m_stateholder.setBalanceChekingNumberChecked(cb_UserSettings_BalanceChecking.isChecked());
		m_stateholder.setBalanceChekingNumberSpinEnabled(spin_UsetSettings_BalanceChecking.isEnabled());
		m_stateholder.setBalanceChekingNumberSpinValue(spin_UsetSettings_BalanceChecking.getSelectedItemPosition());
		m_stateholder.setEtBalanceCheckingAnotherEnabled(et_UserSettings_BalanceCheckingAnother.isEnabled());
		m_stateholder.setEtBalanceCheckingAnotherValue(et_UserSettings_BalanceCheckingAnother.getText().toString());
		
		//2) ACCESS POINT NAME OF MOBILE OPERATOR
		m_stateholder.setAccessPointNameMobOperEnabled(cb_UserSettings_AccessPointNameMobOper.isEnabled());
		m_stateholder.setAccessPointNameMobOperChecked(cb_UserSettings_AccessPointNameMobOper.isChecked());
		m_stateholder.setAccessPointNameMobOperSpinEnabled(spin_UsetSettings_AccessPointNameMobOper.isEnabled());
		m_stateholder.setAccessPointNameMobOperSpinValue(spin_UsetSettings_AccessPointNameMobOper.getSelectedItemPosition());
		m_stateholder.setEtAccessPointNameMobOperEnabled(et_UserSettings_AccessPointNameMobOperAnother.isEnabled());
		m_stateholder.setEtAccessPointNameMobOperValue(et_UserSettings_AccessPointNameMobOperAnother.getText().toString());
		
		//3)
		m_stateholder.setNuberCallAttemptsEnabled(cb_UserSettings_NumberCallAttempts.isEnabled());
		m_stateholder.setNuberCallAttemptsChecked(cb_UserSettings_NumberCallAttempts.isChecked());
		m_stateholder.setEtNumberCallAttemptsEnabled(etNumberCallAttempts.isEnabled());
		m_stateholder.setEtNumberCallAttemptsValue(etNumberCallAttempts.getText().toString());
		
		//5) SIREN
		m_stateholder.setCbSirenEnabled(cb_UserSettings_Siren.isEnabled());
		m_stateholder.setCbSirenChecked(cb_UserSettings_Siren.isChecked());
		m_stateholder.setSpinSystemSetUnsetEnabled(spin_UserSettings_SystemSetUnset.isEnabled());
		m_stateholder.setSpinSystemSetUnsetValue(spin_UserSettings_SystemSetUnset.getSelectedItemPosition());
		m_stateholder.setSpinAlarmModeEnabled(spin_UserSettings_AlarmMode.isEnabled());
		m_stateholder.setSpinAlarmModeValue(spin_UserSettings_AlarmMode.getSelectedItemPosition());
        m_stateholder.setSpinSignalsNumberWarningZoneEnabled(spin_UserSettings_SignalsNumberWarningZone.isEnabled());
        m_stateholder.setSpinSignalsNumberWarningZoneValue(spin_UserSettings_SignalsNumberWarningZone.getSelectedItemPosition());
		
		//6) TWO STAGE DISARMING SYSTEM
		m_stateholder.setCbDisarmingSystemEnabled(cb_UserSettings_DisarmingSystem.isEnabled());
		m_stateholder.setCbDisarmingSystemChecked(cb_UserSettings_DisarmingSystem.isChecked());
		m_stateholder.setSpinDisarmingSystemEnabled(spin_UserSettings_DisarmingSystem.isEnabled());
		m_stateholder.setSpinDisarmingSystemValue(spin_UserSettings_DisarmingSystem.getSelectedItemPosition());
		
		//7) ALL SYSTEM SENSORS
		m_stateholder.setCbAllSystemSensorsEnabled(cb_UserSettings_AllSensorsDisabled.isEnabled());
		m_stateholder.setCbAllSystemSensorsChecked(cb_UserSettings_AllSensorsDisabled.isChecked());
		m_stateholder.setSpinTiltSensorEnabled(spin_UserSettings_TiltSens_AllSensEnabled.isEnabled());
		m_stateholder.setSpinTiltSensorValue(spin_UserSettings_TiltSens_AllSensEnabled.getSelectedItemPosition());
		
		//5)
		m_stateholder.setCbShockSensSettingsEnabled(cb_UserSettings_ShockSensSettings.isEnabled());
		m_stateholder.setCbShockSensSettingsChecked(cb_UserSettings_ShockSensSettings.isChecked());
		m_stateholder.setCbWarningZoneDisabledEnabled(cb_UserSettings_WarningTrasholdDisabled.isEnabled());
		m_stateholder.setCbWarningZoneDisabledChecked(cb_UserSettings_WarningTrasholdDisabled.isChecked());
		m_stateholder.setSbWarningTrasholdEnabled(sb_UserSettings_WarningTrashold.isEnabled());
		m_stateholder.setSbWarningTrasholdValue(sb_UserSettings_WarningTrashold.getProgress());
		m_stateholder.setEtWarningTrasholdEnabled(et_UserSettings_WarningTrashold.isEnabled());
		m_stateholder.setEtWarningTrasholdValue(et_UserSettings_WarningTrashold.getText().toString());
		m_stateholder.setCbAlarmZoneDisabledEnabled(cb_UserSettings_AlarmTrasholdDisabled.isEnabled());
		m_stateholder.setCbAlarmZoneDisabledChecked(cb_UserSettings_AlarmTrasholdDisabled.isChecked());
		m_stateholder.setSbAlarmTrasholdEnabled(sb_UserSettings_AlarmTrashold.isEnabled());
		m_stateholder.setSbAlarmTrasholdValue(sb_UserSettings_AlarmTrashold.getProgress());
		m_stateholder.setEtAlarmTrasholdEnabled(et_UserSettings_AlarmTrashold.isEnabled());
		m_stateholder.setEtAlarmTrasholdValue(et_UserSettings_AlarmTrashold.getText().toString());
		
		//6) TILT SENSOR
		m_stateholder.setCbTiltSensorEnabled(cb_UserSettings_TiltSensor.isEnabled());
		m_stateholder.setCbTiltSensorChecked(cb_UserSettings_TiltSensor.isChecked());
		m_stateholder.setCbTiltSensorDisabledEnabled(cb_UserSettings_TiltSensorDisabled.isEnabled());
		m_stateholder.setCbTiltSensorDisabledChecked(cb_UserSettings_TiltSensorDisabled.isChecked());
		m_stateholder.setSbTiltSensorEnabled(sbTiltSensor.isEnabled());
		m_stateholder.setSbTiltSensorValue(sbTiltSensor.getProgress());
		m_stateholder.setEtTiltSensorEnabled(etTiltSensor.isEnabled());
		m_stateholder.setEtTiltSensorValue(etTiltSensor.getText().toString());

		// SMS MONITORING ARMING
		m_stateholder.setCbSMSReportingArmingEnabled(cb_UserSettings_SMSReportingArming.isEnabled());
		m_stateholder.setCbSMSReportingArmingChecked(cb_UserSettings_SMSReportingArming.isChecked());
		m_stateholder.setSMSReportingArmingEnabled(spin_UserSettings_SMSReportingArming.isEnabled());
		m_stateholder.setSMSReportingArmingValue(spin_UserSettings_SMSReportingArming.getSelectedItemPosition());
		
		//7) MONITORING SETTINGS
		m_stateholder.setCbMonitoringSettingsEnabled(cb_UserSettings_MonitoringSettings.isEnabled());
		m_stateholder.setCbMonitoringSettingsChecked(cb_UserSettings_MonitoringSettings.isChecked());
		m_stateholder.setMonitoringSettingsEnabled(spin_UserSettings_MonitoringSettings.isEnabled());
		m_stateholder.setMonitoringSettingsValue(spin_UserSettings_MonitoringSettings.getSelectedItemPosition());
		
		//9) MICROPHONE
		m_stateholder.setCbPhoneSensitivityEnabled(cb_UserSettings_PhoneSensitivity.isEnabled());
		m_stateholder.setCbPhoneSensitivityChecked(cb_UserSettings_PhoneSensitivity.isChecked());
		m_stateholder.setCbPhoneSensitivityDisabledEnabled(cb_UserSettings_PhoneSensitivityDisabled.isEnabled());
		m_stateholder.setCbPhoneSensitivityDisabledChecked(cb_UserSettings_PhoneSensitivityDisabled.isChecked());
		m_stateholder.setSbPhoneSensitivityEnabled(sbPhoneSensitivity.isEnabled());
		m_stateholder.setSbPhoneSensitivityValue(sbPhoneSensitivity.getProgress());
		m_stateholder.setEtPhoneSensitivityEnabled(etPhoneSensitivity.isEnabled());
		m_stateholder.setEtPhoneSensitivityValue(etPhoneSensitivity.getText().toString());
		
		//10) SPEAKER
		m_stateholder.setCbSpeakerVolumeEnabled(cb_UserSettings_SpeakerVolume.isEnabled());
		m_stateholder.setCbSpeakerVolumeChecked(cb_UserSettings_SpeakerVolume.isChecked());
		m_stateholder.setCbSpeakerVolumeDisabledEnabled(cb_UserSettings_SpeakerVolumeDisabled.isEnabled());
		m_stateholder.setCbSpeakerVolumeDisabledChecked(cb_UserSettings_SpeakerVolumeDisabled.isChecked());
		m_stateholder.setSbSpeakerVolumeEnabled(sbSpeakerVolume.isEnabled());
		m_stateholder.setSbSpeakerVolumeValue(sbSpeakerVolume.getProgress());
		m_stateholder.setEtSpeakerVolumeEnabled(etSpeakerVolume.isEnabled());
		m_stateholder.setEtSpeakerVolumeValue(etSpeakerVolume.getText().toString());

        //12) LABEL SETTINGS
        m_stateholder.setCbLabelSettingsEnabled(cb_UserSettings_LabelSettings.isEnabled());
        m_stateholder.setCbLabelSettingsChecked(cb_UserSettings_LabelSettings.isChecked());
        m_stateholder.setCbLabelSettingsDisabledEnabled(cb_UserSettings_LabelSettingsDisabled.isEnabled());
        m_stateholder.setCbLabelSettingsDisabledChecked(cb_UserSettings_LabelSettingsDisabled.isChecked());
        m_stateholder.setSbLabelSettingsEnabled(sbLabelSettings.isEnabled());
        m_stateholder.setSbLabelSettingsValue(sbLabelSettings.getProgress());
        m_stateholder.setEtLabelSettingsEnabled(etLabelSettings.isEnabled());
        m_stateholder.setEtLabelSettingsValue(etLabelSettings.getText().toString());

		m_stateholder.setLabelEnterEnabled(spin_UserSettings_LabelEnter.isEnabled());
		m_stateholder.setLabelEnterValue(spin_UserSettings_LabelEnter.getSelectedItemPosition());
		m_stateholder.setLabelExitEnabled(spin_UserSettings_LabelExit.isEnabled());
		m_stateholder.setLabelExitValue(spin_UserSettings_LabelExit.getSelectedItemPosition());
		m_stateholder.setLabelConfirmFuncDisarmingEnabled(spin_UserSettings_LabelConfirmFuncDisarming.isEnabled());
		m_stateholder.setLabelConfirmFuncDisarmingValue(spin_UserSettings_LabelConfirmFuncDisarming.getSelectedItemPosition());
		
		//11)
		m_stateholder.setCbAccBalanceEnabled(cb_UserSettings_AccBalance.isEnabled());
		m_stateholder.setCbAccBalanceChecked(cb_UserSettings_AccBalance.isChecked());
		m_stateholder.setEtNumSumPositionEnabled(etNumSumPosition.isEnabled());
		m_stateholder.setEtNumSumPositionValue(etNumSumPosition.getText().toString());
		m_stateholder.setEtCriticalBalanceEnabled(etCriticalBalance.isEnabled());
		m_stateholder.setEtCriticalBalanceValue(etCriticalBalance.getText().toString());
		m_stateholder.setEtBalanceCheckingFrequencyEnabled(etBalanceCheckinngFrequency.isEnabled());
		m_stateholder.setEtBalanceCheckingFrequencyValue(etBalanceCheckinngFrequency.getText().toString());
		
		//REARM
		m_stateholder.setRearmEnabled(cb_UserSettings_Rearm.isEnabled());
		m_stateholder.setRearmChecked(cb_UserSettings_Rearm.isChecked());
		m_stateholder.setSpinRearmEnabled(spin_UserSettings_Rearm.isEnabled());
		m_stateholder.setSpinRearmValue(spin_UserSettings_Rearm.getSelectedItemPosition());
	}
		
	private void restoreState()
	{
		if (m_stateholder != null) 
		{
			//0) CURRENT PIN
			cb_UserSettings_CurrentPIN.setEnabled(m_stateholder.getCurrentPinEnabled());
			cb_UserSettings_CurrentPIN.setChecked(m_stateholder.getCurrentPinChecked());
			et_UserSettings_CurrentPIN.setEnabled(m_stateholder.getEtCurrentPINEnabled());
			et_UserSettings_CurrentPIN.setText(m_stateholder.getEtCurrentPINValue());
			
	        //1) NEW PIN
			cb_UserSettings_NewPIN.setEnabled(m_stateholder.getPinEnabled());
			cb_UserSettings_NewPIN.setChecked(m_stateholder.getPinChecked());
			et_UserSettings_NewPIN.setEnabled(m_stateholder.getEtPINEnabled());
			et_UserSettings_NewPIN.setText(m_stateholder.getEtPINValue());
			
			//2) BALANCE CHECKING NUMBER
			cb_UserSettings_BalanceChecking.setEnabled(m_stateholder.getBalanceChekingNumberEnabled());
			cb_UserSettings_BalanceChecking.setChecked(m_stateholder.getBalanceChekingNumberChecked());
			spin_UsetSettings_BalanceChecking.setEnabled(m_stateholder.getBalanceChekingNumberSpinEnabled());
			spin_UsetSettings_BalanceChecking.setSelection(m_stateholder.getBalanceChekingNumberSpinValue());
			et_UserSettings_BalanceCheckingAnother.setEnabled(m_stateholder.getEtBalanceCheckingAnotherEnabled());
			et_UserSettings_BalanceCheckingAnother.setText(m_stateholder.getEtBalanceCheckingAnotherValue());
			
			//5) SIREN
			cb_UserSettings_Siren.setEnabled(m_stateholder.getCbSirenEnabled());
			cb_UserSettings_Siren.setChecked(m_stateholder.getCbSirenChecked());
			spin_UserSettings_SystemSetUnset.setEnabled(m_stateholder.getSpinSystemSetUnsetEnabled());
			spin_UserSettings_SystemSetUnset.setSelection(m_stateholder.getSpinSystemSetUnsetValue());
			spin_UserSettings_AlarmMode.setEnabled(m_stateholder.getSpinAlarmModeEnabled());
			spin_UserSettings_AlarmMode.setSelection(m_stateholder.getSpinAlarmModeValue());
            spin_UserSettings_SignalsNumberWarningZone.setEnabled(m_stateholder.getSpinSignalsNumberWarningZoneEnabled());
            spin_UserSettings_SignalsNumberWarningZone.setSelection(m_stateholder.getSpinSignalsNumberWarningZoneValue());
			
			//6) TWO STAGE DISARMING SYSTEM
			cb_UserSettings_DisarmingSystem.setEnabled(m_stateholder.getCbDisarmingSystemEnabled());
			cb_UserSettings_DisarmingSystem.setChecked(m_stateholder.getCbDisarmingSystemChecked());
			spin_UserSettings_DisarmingSystem.setEnabled(m_stateholder.getSpinDisarmingSystemEnabled());
			spin_UserSettings_DisarmingSystem.setSelection(m_stateholder.getSpinDisarmingSystemValue());
			
			//7) ALL SYSTEM SENSORS
			cb_UserSettings_AllSensorsDisabled.setEnabled(m_stateholder.geCbtAllSystemSensorsEnabled());
			cb_UserSettings_AllSensorsDisabled.setChecked(m_stateholder.getCbAllSystemSensorsChecked());
			spin_UserSettings_TiltSens_AllSensEnabled.setEnabled(m_stateholder.getSpinTiltSensorEnabled());
			spin_UserSettings_TiltSens_AllSensEnabled.setSelection(m_stateholder.getSpinTiltSensorValue());
			
			//6) SHOCK SENSOR
			cb_UserSettings_ShockSensSettings.setEnabled(m_stateholder.getCbShockSensSettingsEnabled());
			cb_UserSettings_ShockSensSettings.setChecked(m_stateholder.getCbShockSensSettingsChecked());
			cb_UserSettings_WarningTrasholdDisabled.setEnabled(m_stateholder.getCbWarningZoneDisabledEnabled());
			cb_UserSettings_WarningTrasholdDisabled.setChecked(m_stateholder.getCbWarningZoneDisabledChecked());
			sb_UserSettings_WarningTrashold.setEnabled(m_stateholder.getSbWarningTrasholdEnabled());
			sb_UserSettings_WarningTrashold.setProgress(m_stateholder.getSbWarningTrasholdValue());
			et_UserSettings_WarningTrashold.setEnabled(m_stateholder.getEtWarningTrasholdEnabled());
			et_UserSettings_WarningTrashold.setText(m_stateholder.getEtWarningTrasholdValue());
			cb_UserSettings_AlarmTrasholdDisabled.setEnabled(m_stateholder.getCbAlarmZoneDisabledEnabled());
			cb_UserSettings_AlarmTrasholdDisabled.setChecked(m_stateholder.getCbAlarmZoneDisabledChecked());
			sb_UserSettings_AlarmTrashold.setEnabled(m_stateholder.getSbAlarmTrasholdEnabled());
			sb_UserSettings_AlarmTrashold.setProgress(m_stateholder.getSbAlarmTrasholdValue());
			et_UserSettings_AlarmTrashold.setEnabled(m_stateholder.getEtAlarmTrasholdEnabled());
			et_UserSettings_AlarmTrashold.setText(m_stateholder.getEtAlarmTrasholdValue());
			
			//7)TILT SENSOR
			cb_UserSettings_TiltSensor.setEnabled(m_stateholder.getCbTiltSensorEnabled());
			cb_UserSettings_TiltSensor.setChecked(m_stateholder.getCbTiltSensorChecked());
			cb_UserSettings_TiltSensorDisabled.setEnabled(m_stateholder.getCbTiltSensorDisabledEnabled());
			cb_UserSettings_TiltSensorDisabled.setChecked(m_stateholder.getCbTiltSensorDisabledChecked());
			sbTiltSensor.setEnabled(m_stateholder.getSbTiltSensorEnabled());
			sbTiltSensor.setProgress(m_stateholder.getSbTiltSensorValue());
			etTiltSensor.setEnabled(m_stateholder.getEtTiltSensorEnabled());
			etTiltSensor.setText(m_stateholder.getEtTiltSensorValue());
			
			//2) ACCESS POINT NAME OF MOBILE OPERATOR
			cb_UserSettings_AccessPointNameMobOper.setEnabled(m_stateholder.getAccessPointNameMobOperEnabled());
			cb_UserSettings_AccessPointNameMobOper.setChecked(m_stateholder.getAccessPointNameMobOperChecked());
			spin_UsetSettings_AccessPointNameMobOper.setEnabled(m_stateholder.getAccessPointNameMobOperSpinEnabled());
			spin_UsetSettings_AccessPointNameMobOper.setSelection(m_stateholder.getAccessPointNameMobOperSpinValue());
			et_UserSettings_AccessPointNameMobOperAnother.setEnabled(m_stateholder.getEtAccessPointNameMobOperEnabled());
			et_UserSettings_AccessPointNameMobOperAnother.setText(m_stateholder.getEtAccessPointNameMobOperValue());
			
			//3)
			cb_UserSettings_NumberCallAttempts.setEnabled(m_stateholder.getNuberCallAttemptsEnabled());
			cb_UserSettings_NumberCallAttempts.setChecked(m_stateholder.getNuberCallAttemptsChecked());
			etNumberCallAttempts.setEnabled(m_stateholder.getEtNumberCallAttemptsEnabled());
			etNumberCallAttempts.setText(m_stateholder.getEtNumberCallAttemptsValue());

			//SMS REPORTING ARMING
			cb_UserSettings_SMSReportingArming.setEnabled(m_stateholder.getCbSMSReportingArmingEnabled());
			cb_UserSettings_SMSReportingArming.setChecked(m_stateholder.getCbSMSReportingArmingChecked());
			spin_UserSettings_SMSReportingArming.setEnabled(m_stateholder.getSMSReportingArmingEnabled());
			spin_UserSettings_SMSReportingArming.setSelection(m_stateholder.getSMSReportingArmingValue());

			//7)
			cb_UserSettings_MonitoringSettings.setEnabled(m_stateholder.getCbMonitoringSettingsEnabled());
			cb_UserSettings_MonitoringSettings.setChecked(m_stateholder.getCbMonitoringSettingsChecked());
			spin_UserSettings_MonitoringSettings.setEnabled(m_stateholder.getMonitoringSettingsEnabled());
			spin_UserSettings_MonitoringSettings.setSelection(m_stateholder.getMonitoringSettingsValue());
			
			//9) MICROPHONE
			cb_UserSettings_PhoneSensitivity.setEnabled(m_stateholder.getCbPhoneSensitivityEnabled());
			cb_UserSettings_PhoneSensitivity.setChecked(m_stateholder.getCbPhoneSensitivityChecked());
			cb_UserSettings_PhoneSensitivityDisabled.setEnabled(m_stateholder.getCbPhoneSensitivityDisabledEnabled());
			cb_UserSettings_PhoneSensitivityDisabled.setChecked(m_stateholder.getCbPhoneSensitivityDisabledChecked());
			sbPhoneSensitivity.setEnabled(m_stateholder.getSbPhoneSensitivityEnabled());
			sbPhoneSensitivity.setProgress(m_stateholder.getSbPhoneSensitivityValue());
			etPhoneSensitivity.setEnabled(m_stateholder.getEtPhoneSensitivityEnabled());
			etPhoneSensitivity.setText(m_stateholder.getEtPhoneSensitivityValue());
			
			//10) SPEAKER
			cb_UserSettings_SpeakerVolume.setEnabled(m_stateholder.getCbSpeakerVolumeEnabled());
			cb_UserSettings_SpeakerVolume.setChecked(m_stateholder.getCbSpeakerVolumeChecked());
			cb_UserSettings_SpeakerVolumeDisabled.setEnabled(m_stateholder.getCbSpeakerVolumeDisabledEnabled());
			cb_UserSettings_SpeakerVolumeDisabled.setChecked(m_stateholder.getCbSpeakerVolumeDisabledChecked());
			sbSpeakerVolume.setEnabled(m_stateholder.getSbSpeakerVolumeEnabled());
			sbSpeakerVolume.setProgress(m_stateholder.getSbSpeakerVolumeValue());
			etSpeakerVolume.setEnabled(m_stateholder.getEtSpeakerVolumeEnabled());
			etSpeakerVolume.setText(m_stateholder.getEtSpeakerVolumeValue());

            //12) LABEL SETTINGS
            cb_UserSettings_LabelSettings.setEnabled(m_stateholder.getCbLabelSettingsEnabled());
            cb_UserSettings_LabelSettings.setChecked(m_stateholder.getCbLabelSettingsChecked());
            cb_UserSettings_LabelSettingsDisabled.setEnabled(m_stateholder.getCbLabelSettingsDisabledEnabled());
            cb_UserSettings_LabelSettingsDisabled.setChecked(m_stateholder.getCbLabelSettingsDisabledChecked());
            sbLabelSettings.setEnabled(m_stateholder.getSbLabelSettingsEnabled());
            sbLabelSettings.setProgress(m_stateholder.getSbLabelSettingsValue());
            etLabelSettings.setEnabled(m_stateholder.getEtLabelSettingsEnabled());
            etLabelSettings.setText(m_stateholder.getEtLabelSettingsValue());

			spin_UserSettings_LabelEnter.setEnabled(m_stateholder.getLabelEnterEnabled());
			spin_UserSettings_LabelEnter.setSelection(m_stateholder.getLabelEnterValue());
			spin_UserSettings_LabelExit.setEnabled(m_stateholder.getLabelExitEnabled());
			spin_UserSettings_LabelExit.setSelection(m_stateholder.getLabelExitValue());
			spin_UserSettings_LabelConfirmFuncDisarming.setEnabled(m_stateholder.getLabelConfirmFuncDisarmingEnabled());
			spin_UserSettings_LabelConfirmFuncDisarming.setSelection(m_stateholder.getLabelConfirmFuncDisarmingValue());
			
			//11)
			cb_UserSettings_AccBalance.setEnabled(m_stateholder.getCbAccBalanceEnabled());
			cb_UserSettings_AccBalance.setChecked(m_stateholder.getCbAccBalanceChecked());
			etNumSumPosition.setEnabled(m_stateholder.getEtNumSumPositionEnabled());
			etNumSumPosition.setText(m_stateholder.getEtNumSumPositionValue());
			etCriticalBalance.setEnabled(m_stateholder.getEtCriticalBalanceEnabled());
			etCriticalBalance.setText(m_stateholder.getEtCriticalBalanceValue());
			etBalanceCheckinngFrequency.setEnabled(m_stateholder.getEtBalanceCheckingFrequencyEnabled());
			etBalanceCheckinngFrequency.setText(m_stateholder.getEtBalanceCheckingFrequencyValue());

			//REARM
			cb_UserSettings_Rearm.setEnabled(m_stateholder.getRearmEnabled());
			cb_UserSettings_Rearm.setChecked(m_stateholder.getRearmChecked());
			spin_UserSettings_Rearm.setEnabled(m_stateholder.getSpinRearmEnabled());
			spin_UserSettings_Rearm.setSelection(m_stateholder.getSpinRearmValue());
	    }
		else
		{
			//5) SIREN
			spin_UserSettings_SystemSetUnset.setSelection(1);
			spin_UserSettings_AlarmMode.setSelection(1);
            spin_UserSettings_SignalsNumberWarningZone.setSelection(3);
			
			//6) SHOCK SENSOR
			
			//7) TILT SENSOR
			spin_UserSettings_TiltSens_AllSensEnabled.setSelection(1);
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
		setTitle(R.string.btn_Main_UserSettings);
		//1) NEW PIN
		cb_UserSettings_NewPIN.setText(R.string.cb_UserSettings_PIN);
		//2)
		cb_UserSettings_BalanceChecking.setText(R.string.cb_UserSettings_BalanceChecking);
		int pos_UserSettings_BalanceChecking = spin_UsetSettings_BalanceChecking.getSelectedItemPosition();
		ArrayAdapter<String> balanceCheckingMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.balance_checking));
		spin_UsetSettings_BalanceChecking.setAdapter(balanceCheckingMenu);
		spin_UsetSettings_BalanceChecking.setSelection(pos_UserSettings_BalanceChecking);
		TextView tv_UserSettings_BalanceCheckingAnother = (TextView) findViewById(R.id.tv_UserSettings_BalanceCheckingAnother);
		tv_UserSettings_BalanceCheckingAnother.setText(R.string.tv_UserSettings_BalanceCheckingAnother);
		//3)
		cb_UserSettings_AccBalance.setText(R.string.cb_UserSettings_AccBalance);
		TextView tv_UserSettings_NumSumPosition = (TextView) findViewById(R.id.tv_UserSettings_NumSumPosition);
		tv_UserSettings_NumSumPosition.setText(R.string.tv_UserSettings_NumSumPosition);
		TextView tv_UserSettings_CriticalBalance = (TextView) findViewById(R.id.tv_UserSettings_CriticalBalance);
		tv_UserSettings_CriticalBalance.setText(R.string.tv_UserSettings_CriticalBalance);
		TextView et_UserSettings_CriticalBalanceDimen = (TextView) findViewById(R.id.et_UserSettings_CriticalBalanceDimen);
		et_UserSettings_CriticalBalanceDimen.setText(R.string.et_UserSettings_CriticalBalanceDimen);
		TextView tv_UserSettings_BalanceCheckinngFrequency = (TextView) findViewById(R.id.tv_UserSettings_BalanceCheckinngFrequency);
		tv_UserSettings_BalanceCheckinngFrequency.setText(R.string.tv_UserSettings_BalanceCheckinngFrequency);
		TextView tv_UserSettings_BalanceCheckinngFrequencyDimen = (TextView) findViewById(R.id.tv_UserSettings_BalanceCheckinngFrequencyDimen);
		tv_UserSettings_BalanceCheckinngFrequencyDimen.setText(R.string.tv_UserSettings_BalanceCheckinngFrequencyDimen);
		//4)
		cb_UserSettings_NumberCallAttempts.setText(R.string.cb_UserSettings_NumberCallAttempts);
		//REARM
		cb_UserSettings_Rearm.setText(R.string.cb_UserSettings_Rearm);
		int pos_UserSettings_Rearm = spin_UserSettings_Rearm.getSelectedItemPosition();
		ArrayAdapter<String> balanceRearm = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.rearm));
		spin_UserSettings_Rearm.setAdapter(balanceRearm);
		spin_UserSettings_Rearm.setSelection(pos_UserSettings_Rearm);
		//5) SIREN
		cb_UserSettings_Siren.setText(R.string.cb_UserSettings_Siren);
		TextView tv_UserSettings_SystemSetUnset = (TextView) findViewById(R.id.tv_UserSettings_SystemSetUnset);
		tv_UserSettings_SystemSetUnset.setText(R.string.tv_UserSettings_SystemSetUnset);
		int pos_UserSettings_SystemSetUnset = spin_UserSettings_SystemSetUnset.getSelectedItemPosition();
		ArrayAdapter<String> sirenSetUnset = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.siren_set_unset));
		spin_UserSettings_SystemSetUnset.setAdapter(sirenSetUnset);
		spin_UserSettings_SystemSetUnset.setSelection(pos_UserSettings_SystemSetUnset);
		TextView tv_UserSettings_AlarmMode = (TextView) findViewById(R.id.tv_UserSettings_AlarmMode);
		tv_UserSettings_AlarmMode.setText(R.string.tv_UserSettings_AlarmMode);
		int pos_UserSettings_AlarmMode = spin_UserSettings_AlarmMode.getSelectedItemPosition();
		ArrayAdapter<String> sirenAlarmMode = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.siren_alarm_mode));
		spin_UserSettings_AlarmMode.setAdapter(sirenAlarmMode);
		spin_UserSettings_AlarmMode.setSelection(pos_UserSettings_AlarmMode);
        TextView tv_UserSettings_SignalsNumberWarningZone = (TextView) findViewById(R.id.tv_UserSettings_SignalsNumberWarningZone);
        tv_UserSettings_SignalsNumberWarningZone.setText(R.string.tv_UserSettings_SignalsNumberWarningZone);
        int pos_UserSettings_SignalsNumberWarningZone = spin_UserSettings_SignalsNumberWarningZone.getSelectedItemPosition();
        ArrayAdapter<String> sirenSignalsNumberWarningZone = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.siren_number_warning_zone));
        spin_UserSettings_SignalsNumberWarningZone.setAdapter(sirenSignalsNumberWarningZone);
        spin_UserSettings_SignalsNumberWarningZone.setSelection(pos_UserSettings_SignalsNumberWarningZone);
		//6)
		cb_UserSettings_DisarmingSystem.setText(R.string.cb_UserSettings_DisarmingSystem);
		int pos_UserSettings_DisarmingSystem = spin_UserSettings_DisarmingSystem.getSelectedItemPosition();
		ArrayAdapter<String> disarmingMode = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_timer_sys_disarm));
		spin_UserSettings_DisarmingSystem.setAdapter(disarmingMode);
		spin_UserSettings_DisarmingSystem.setSelection(pos_UserSettings_DisarmingSystem);
		//7)
		cb_UserSettings_AllSensorsDisabled.setText(R.string.cb_UserSettings_TiltSens_AllSensEnabled);
		int pos_UserSettings_TiltSens_AllSensEnabled  = spin_UserSettings_TiltSens_AllSensEnabled.getSelectedItemPosition();
		ArrayAdapter<String> tiltSensorArray = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.UserSettings_TiltSensor));
		spin_UserSettings_TiltSens_AllSensEnabled.setAdapter(tiltSensorArray);
		spin_UserSettings_TiltSens_AllSensEnabled.setSelection(pos_UserSettings_TiltSens_AllSensEnabled);
		//8)
		cb_UserSettings_ShockSensSettings.setText(R.string.cb_UserSettings_ShockSensSettings);
		TextView tv_UserSettings_WarningThreshold = (TextView) findViewById(R.id.tv_UserSettings_WarningThreshold);
		tv_UserSettings_WarningThreshold.setText(R.string.tv_UserSettings_WarningThreshold);
		cb_UserSettings_WarningTrasholdDisabled.setText(R.string.cb_UserSettings_WarningTrasholdDisabled);
		TextView tv_UserSettings_WarningTrashold_Weak = (TextView) findViewById(R.id.tv_UserSettings_WarningTrashold_Weak);
		tv_UserSettings_WarningTrashold_Weak.setText(R.string.tv_UserSettings_WarningTrashold_Weak);
		TextView tv_UserSettings_WarningTrashold_Heavy = (TextView) findViewById(R.id.tv_UserSettings_WarningTrashold_Heavy);
		tv_UserSettings_WarningTrashold_Heavy.setText(R.string.tv_UserSettings_WarningTrashold_Heavy);
		TextView tv_UserSettings_WarningTrashold_Value = (TextView) findViewById(R.id.tv_UserSettings_WarningTrashold_Value);
		tv_UserSettings_WarningTrashold_Value.setText(R.string.tv_UserSettings_WarningTrashold_Value);
		TextView tv_UserSettings_AlarmThresholdShockSensor = (TextView) findViewById(R.id.tv_UserSettings_AlarmThresholdShockSensor);
		tv_UserSettings_AlarmThresholdShockSensor.setText(R.string.tv_UserSettings_AlarmThresholdShockSensor);
		cb_UserSettings_AlarmTrasholdDisabled.setText(R.string.cb_UserSettings_AlarmTrasholdDisabled);
		TextView tv_UserSettings_AlarmThresholdWeak = (TextView) findViewById(R.id.tv_UserSettings_AlarmThresholdWeak);
		tv_UserSettings_AlarmThresholdWeak.setText(R.string.tv_UserSettings_AlarmThresholdWeak);
		TextView tv_UserSettings_AlarmThresholdHeavy = (TextView) findViewById(R.id.tv_UserSettings_AlarmThresholdHeavy);
		tv_UserSettings_AlarmThresholdHeavy.setText(R.string.tv_UserSettings_AlarmThresholdHeavy);
		TextView tv_UserSettings_AlarmThresholdValue = (TextView) findViewById(R.id.tv_UserSettings_AlarmThresholdValue);
		tv_UserSettings_AlarmThresholdValue.setText(R.string.tv_UserSettings_AlarmThresholdValue);
		//9)
		cb_UserSettings_TiltSensor.setText(R.string.cb_UserSettings_TiltSensor);
		TextView tv_UserSettings_TiltSensorThreshold = (TextView) findViewById(R.id.tv_UserSettings_TiltSensorThreshold);
		tv_UserSettings_TiltSensorThreshold.setText(R.string.tv_UserSettings_TiltSensorThreshold);
		cb_UserSettings_TiltSensorDisabled.setText(R.string.cb_UserSettings_TiltSensorDisabled);
		TextView tv_UserSettings_TiltSensorLow = (TextView) findViewById(R.id.tv_UserSettings_TiltSensorLow);
		tv_UserSettings_TiltSensorLow.setText(R.string.tv_UserSettings_TiltSensorLow);
		TextView tv_UserSettings_TiltSensorHight = (TextView) findViewById(R.id.tv_UserSettings_TiltSensorHight);
		tv_UserSettings_TiltSensorHight.setText(R.string.tv_UserSettings_TiltSensorHight);
		TextView tv_UserSettings_TiltSensorValue = (TextView) findViewById(R.id.tv_UserSettings_TiltSensorValue);
		tv_UserSettings_TiltSensorValue.setText(R.string.tv_UserSettings_TiltSensorValue);
		//10)
		cb_UserSettings_PhoneSensitivity.setText(R.string.cb_UserSettings_PhoneSensitivity);
		TextView tv_UserSettings_MicrophoneSensativiti = (TextView) findViewById(R.id.tv_UserSettings_MicrophoneSensativiti);
		tv_UserSettings_MicrophoneSensativiti.setText(R.string.tv_UserSettings_MicrophoneSensativiti);
		TextView tv_UserSettings_PhoneSensetivLow = (TextView) findViewById(R.id.tv_UserSettings_PhoneSensetivLow);
		tv_UserSettings_PhoneSensetivLow.setText(R.string.tv_UserSettings_PhoneSensetivLow);
		TextView tv_UserSettings_PhoneSensetivHight = (TextView) findViewById(R.id.tv_UserSettings_PhoneSensetivHight);
		tv_UserSettings_PhoneSensetivHight.setText(R.string.tv_UserSettings_PhoneSensetivHight);
		TextView tv_UserSettings_PhoneSensetivValue = (TextView) findViewById(R.id.tv_UserSettings_PhoneSensetivValue);
		tv_UserSettings_PhoneSensetivValue.setText(R.string.tv_UserSettings_PhoneSensetivValue);
		cb_UserSettings_PhoneSensitivityDisabled.setText(R.string.cb_UserSettings_PhoneSensitivityDisabled);
		//11)
		cb_UserSettings_SpeakerVolume.setText(R.string.cb_UserSettings_SpeakerVolume);
		TextView tv_UserSettings_DinamicVolumeSettings = (TextView) findViewById(R.id.tv_UserSettings_DinamicVolumeSettings);
		tv_UserSettings_DinamicVolumeSettings.setText(R.string.tv_UserSettings_DinamicVolumeSettings);
		TextView tv_UserSettings_SpeakerVolumeLow = (TextView) findViewById(R.id.tv_UserSettings_SpeakerVolumeLow);
		tv_UserSettings_SpeakerVolumeLow.setText(R.string.tv_UserSettings_SpeakerVolumeLow);
		TextView tv_UserSettings_SpeakerVolumeHight = (TextView) findViewById(R.id.tv_UserSettings_SpeakerVolumeHight);
		tv_UserSettings_SpeakerVolumeHight.setText(R.string.tv_UserSettings_SpeakerVolumeHight);
		TextView tv_UserSettings_SpeakerVolumeValue = (TextView) findViewById(R.id.tv_UserSettings_SpeakerVolumeValue);
		tv_UserSettings_SpeakerVolumeValue.setText(R.string.tv_UserSettings_SpeakerVolumeValue);
		cb_UserSettings_SpeakerVolumeDisabled.setText(R.string.cb_UserSettings_SpeakerVolumeDisabled);
        //12) LABEL SETTINGS
        cb_UserSettings_LabelSettings.setText(R.string.cb_UserSettings_LabelSettings);
        TextView tv_UserSettings_LabelSettings = (TextView) findViewById(R.id.tv_UserSettings_LabelSettings);
        tv_UserSettings_LabelSettings.setText(R.string.tv_UserSettings_LabelSettings);
        TextView tv_UserSettings_LabelSettingsLow = (TextView) findViewById(R.id.tv_UserSettings_LabelSettingsLow);
        tv_UserSettings_LabelSettingsLow.setText(R.string.tv_UserSettings_LabelSettingsLow);
        TextView tv_UserSettings_LabelSettingsHight = (TextView) findViewById(R.id.tv_UserSettings_LabelSettingsHight);
        tv_UserSettings_LabelSettingsHight.setText(R.string.tv_UserSettings_LabelSettingsHight);
        TextView tv_UserSettings_LabelSettingsValue = (TextView) findViewById(R.id.tv_UserSettings_LabelSettingsValue);
        tv_UserSettings_LabelSettingsValue.setText(R.string.tv_UserSettings_LabelSettingsValue);
        cb_UserSettings_LabelSettingsDisabled.setText(R.string.cb_UserSettings_LabelSettingsDisabled);

        int pos_UserSettings_LabelEnter = spin_UserSettings_LabelEnter.getSelectedItemPosition();
        ArrayAdapter<String> labelEnterSettingsArray = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.UserSettings_LabelEnter));
        spin_UserSettings_LabelEnter.setAdapter(labelEnterSettingsArray);
        spin_UserSettings_LabelEnter.setSelection(pos_UserSettings_LabelEnter);

        int pos_UserSettings_LabelExit = spin_UserSettings_LabelExit.getSelectedItemPosition();
        ArrayAdapter<String> labelExitArray = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.UserSettings_LabelExit));
        spin_UserSettings_LabelExit.setAdapter(labelExitArray);
        spin_UserSettings_LabelExit.setSelection(pos_UserSettings_LabelExit);

		int pos_UserSettings_LabelConfirmFuncDisarming = spin_UserSettings_LabelConfirmFuncDisarming.getSelectedItemPosition();
		ArrayAdapter<String> labelConfirmFuncDisarmingArray = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.UserSettings_LabelConfirmFuncDisarming));
		spin_UserSettings_LabelConfirmFuncDisarming.setAdapter(labelConfirmFuncDisarmingArray);
		spin_UserSettings_LabelConfirmFuncDisarming.setSelection(pos_UserSettings_LabelConfirmFuncDisarming);
		//SMS REPORTING ARMING
		cb_UserSettings_SMSReportingArming.setText(R.string.cb_UserSettings_SMSReportingArming);
		int pos_UserSettings_SMSReportingArming = spin_UserSettings_SMSReportingArming.getSelectedItemPosition();
		ArrayAdapter<String> smsReportingArmingArray = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.UserSettings_SMSReportingArming));
		spin_UserSettings_SMSReportingArming.setAdapter(smsReportingArmingArray);
		spin_UserSettings_SMSReportingArming.setSelection(pos_UserSettings_SMSReportingArming);
		//12)
		cb_UserSettings_MonitoringSettings.setText(R.string.cb_UserSettings_MonitoringSettings);
		int pos_UserSettings_MonitoringSettings = spin_UserSettings_MonitoringSettings.getSelectedItemPosition();
		ArrayAdapter<String> monitoringSettingsArray = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.UserSettings_Monitoring));
		spin_UserSettings_MonitoringSettings.setAdapter(monitoringSettingsArray);
		spin_UserSettings_MonitoringSettings.setSelection(pos_UserSettings_MonitoringSettings);
		//13)
		cb_UserSettings_AccessPointNameMobOper.setText(R.string.cb_UserSettings_AccessPointNameMobOper);
		int pos_UsetSettings_AccessPointNameMobOper = spin_UsetSettings_AccessPointNameMobOper.getSelectedItemPosition();
		ArrayAdapter<String> accessPointNameMobOper = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.UserSettings_PointNameMobOper));
		spin_UsetSettings_AccessPointNameMobOper.setAdapter(accessPointNameMobOper);
		spin_UsetSettings_AccessPointNameMobOper.setSelection(pos_UsetSettings_AccessPointNameMobOper);
		TextView tv_UserSettings_AccessPointNameMobOperAnother = (TextView) findViewById(R.id.tv_UserSettings_AccessPointNameMobOperAnother);
		tv_UserSettings_AccessPointNameMobOperAnother.setText(R.string.tv_UserSettings_AccessPointNameMobOperAnother);
	}
		
	@Override
	public void finish() 
	{
		super.finish();
		//instance = null;
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
		//instance=null;
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
    		//NEW PIN
    		case R.id.et_UserSettings_NewPIN:
    		{
    			if(et.getText().toString().length() != 4)
    				et.setText("0000");
    			break;
    		}
    		//ACCOUNT BALANCE AUTOCHECK
    		case R.id.et_UserSettings_NumSumPosition:
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
    		case R.id.et_UserSettings_CriticalBalance:
    		{
    			try 
    			{
    				Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 1)
        				et.setText("1");
    			} catch(NumberFormatException e)
    			{et.setText("10");}
    			break;
    		}
    		case R.id.et_UserSettings_BalanceCheckinngFrequency:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 1)
        				et.setText("1");
    			}catch(NumberFormatException e)
    			{et.setText("24");}
    			break;
    		}
    		//NUMBER OF CALL ATTEMPTS
    		case R.id.et_UserSettings_NumberCallAttempts:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 1)
        				et.setText("1");
    			}catch(NumberFormatException e)
    			{et.setText("1");}
    			break;
    		}
    		//SHOCK SENSOR
    		case R.id.et_UserSettings_WarningTrashold:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 5)
        				et.setText("5");
    			}catch(NumberFormatException e)
    			{et.setText("18");}
    			break;
    		}
    		case R.id.et_UserSettings_AlarmTrashold:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 5)
        				et.setText("5");
    			}catch(NumberFormatException e)
    			{et.setText("32");}
    			break;
    		}
    		//TILT SENSOR
    		case R.id.et_UserSettings_TiltSensor:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 1)
        				et.setText("1");
    			}catch(NumberFormatException e)
    			{et.setText("1");}
    			break;
    		}
    		//PHONE SENSATIVITY
    		case R.id.et_UserSettings_PhoneSensitivity:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 1)
        				et.setText("1");
    			}catch(NumberFormatException e)
    			{et.setText("5");}
    			break;
    		}
    		//SPEAKER
    		case R.id.et_UserSettings_SpeakerVolume:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 1)
        				et.setText("1");
    			}catch(NumberFormatException e)
    			{et.setText("50");}
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
    		//NEW PIN
    		case R.id.et_UserSettings_NewPIN:
    		{
    			if(et.getText().toString().length() != 4)
    				et.setText("0000");
    			break;
    		}
    		//ACCOUNT BALANCE AUTOCHECK
    		case R.id.et_UserSettings_NumSumPosition:
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
    		case R.id.et_UserSettings_CriticalBalance:
    		{
    			try 
    			{
    				Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 1)
        				et.setText("1");
    			} catch(NumberFormatException e)
    			{et.setText("10");}
    			break;
    		}
    		case R.id.et_UserSettings_BalanceCheckinngFrequency:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 1)
        				et.setText("1");
    			}catch(NumberFormatException e)
    			{et.setText("24");}
    			break;
    		}
    		//NUMBER OF CALL ATTEMPTS
    		case R.id.et_UserSettings_NumberCallAttempts:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 1)
        				et.setText("1");
    			}catch(NumberFormatException e)
    			{et.setText("1");}
    			break;
    		}
    		//SHOCK SENSOR
    		case R.id.et_UserSettings_WarningTrashold:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 5)
        				et.setText("5");
    			}catch(NumberFormatException e)
    			{et.setText("18");}
    			break;
    		}
    		case R.id.et_UserSettings_AlarmTrashold:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 5)
        				et.setText("5");
    			}catch(NumberFormatException e)
    			{et.setText("32");}
    			break;
    		}
    		//TILT SENSOR
    		case R.id.et_UserSettings_TiltSensor:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 1)
        				et.setText("1");
    			}catch(NumberFormatException e)
    			{et.setText("1");}
    			break;
    		}
    		//PHONE SENSATIVITY
    		case R.id.et_UserSettings_PhoneSensitivity:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 1)
        				et.setText("1");
    			}catch(NumberFormatException e)
    			{et.setText("5");}
    			break;
    		}
    		//SPEAKER
    		case R.id.et_UserSettings_SpeakerVolume:
    		{
    			try 
    			{
        			Integer value = Integer.parseInt(et.getText().toString());
        			if(value < 1)
        				et.setText("1");
    			}catch(NumberFormatException e)
    			{et.setText("50");}
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
		}
	}
}
