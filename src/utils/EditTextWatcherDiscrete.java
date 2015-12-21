package utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class EditTextWatcherDiscrete implements TextWatcher
{
	String m_lastValue = "";
	Integer m_minValue = Integer.MIN_VALUE;
	Integer m_maxValue = Integer.MAX_VALUE;
	Integer m_defaultValue = 0;
	EditText m_editText = null;
	
	public EditTextWatcherDiscrete(EditText editText)
	{
		super();
		m_editText = editText;
	}
	
	public EditTextWatcherDiscrete(EditText editText, String defaultValue, String minValue, String maxValue)
	{
		super();
		m_editText = editText;
		m_defaultValue = Integer.parseInt(defaultValue);
		m_minValue = Integer.parseInt(minValue);
		m_maxValue = Integer.parseInt(maxValue);
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
				
			Integer IntegerValue = Integer.parseInt(value);
//			if(IntegerValue < m_minValue)
//				IntegerValue = m_minValue;
			if(IntegerValue > m_maxValue)
				IntegerValue = m_maxValue;
			
			Integer discIntValue = IntegerValue / 4;
			discIntValue*=4;
			
			value = IntegerValue.toString();
			
	    } 
		catch (NumberFormatException e) 
	    {
			value = "";
	    }
		
		String currValue = m_editText.getText().toString();
		if(!currValue.equals(value))
		{
			m_editText.setText(value);
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
