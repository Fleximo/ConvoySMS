package utils;

import com.olegbrovko.smsgeneratorconvoy.R;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

public class GenericTextWatcher implements TextWatcher
{
    private View view = null;
    
    public GenericTextWatcher(View view) 
    {
        this.view = view;
    }
    
    public void setViewForWatch(View view)
    {
    	this.view = view;
    }
    
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) 
    {
    	
    }
    
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) 
    {
    	
    }

	@Override
	public void afterTextChanged(Editable editable) 
	{
        switch(view.getId())
        {
            case R.id.et_Notifications_PhoneNumber:
            {
            	if(view == null)
            	{
            		return;
            	}
                break;
            }   
        }
	}
}
