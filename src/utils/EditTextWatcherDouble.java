package utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class EditTextWatcherDouble implements TextWatcher
{
	String m_lastValue = "";
	Double m_minValue = Double.MIN_VALUE;
	Double m_maxValue = Double.MAX_VALUE;
	Double m_defaultValue = 0.;
	EditText m_editText = null;
	
	public EditTextWatcherDouble(EditText editText)
	{
		super();
		m_editText = editText;
	}
	
	public EditTextWatcherDouble(EditText editText, String defaultValue, String minValue, String maxValue)
	{
		super();
		m_editText = editText;
		m_defaultValue = Double.parseDouble(defaultValue);
		m_minValue = Double.parseDouble(minValue);
		m_maxValue = Double.parseDouble(maxValue);
	}

	@Override
	public void afterTextChanged(Editable s) 
	{
		String value = m_defaultValue.toString();
		
		try 
		{
			value = m_editText.getText().toString();
			if(value.equals(m_lastValue))
				return;
			else
				m_lastValue = value;
				
			Double IntegerValue = Double.valueOf(value);
//			if(IntegerValue < m_minValue)
//				IntegerValue = m_minValue;
			if(IntegerValue > m_maxValue)
				IntegerValue = m_maxValue;
			
			if(IntegerValue.equals(m_maxValue))
			{
				value = IntegerValue.toString();
			}
			else if(!value.equals(IntegerValue.toString()))
			{
			}
			else
			{
				value = IntegerValue.toString();
			}
	    } 
		catch (NumberFormatException e) 
	    {
			value = "";
	    }
		
		String currValue = m_editText.getText().toString();
		if(!currValue.equals(value))
		{
			m_editText.setText(value);
			//m_editText.setSelection(m_editText.getText().length());
		}
		m_editText.setSelection(m_editText.getText().length());
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) 
	{
		// TODO Auto-generated method stub
	}
}
