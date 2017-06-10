package utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;

public class EditTextWatcherInt implements TextWatcher
{
	String m_lastValue = "";
	Integer m_minValue = Integer.MIN_VALUE;
	Integer m_maxValue = Integer.MAX_VALUE;
	Integer m_defaultValue = 0;
	EditText m_editText = null;
	SeekBar m_seekBar = null;
	
	public EditTextWatcherInt(EditText editText)
	{
		super();
		m_editText = editText;
	}
	
	public EditTextWatcherInt(EditText editText, SeekBar seekbar, String defaultValue, String minValue, String maxValue)
	{
		super();
		m_editText = editText;
		m_defaultValue = Integer.parseInt(defaultValue);
		m_minValue = Integer.parseInt(minValue);
		m_maxValue = Integer.parseInt(maxValue);
		m_seekBar = seekbar;
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
			if(IntegerValue > m_maxValue)
				IntegerValue = m_maxValue;
			if(IntegerValue < m_minValue)
				IntegerValue = m_minValue;
			value = IntegerValue.toString();
			
			if(m_seekBar != null)
				m_seekBar.setProgress(m_maxValue - IntegerValue);
	    } 
		catch (NumberFormatException e)
	    {
			value = "";
			if(m_seekBar != null)
				m_seekBar.setProgress(Integer.valueOf(m_maxValue - m_defaultValue));
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
