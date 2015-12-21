package widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.ImageButton;

public class CheckableImageButton extends ImageButton implements Checkable
{
	private boolean isChecked = false;
	
	public CheckableImageButton(Context context) 
	{
        super(context);
        // TODO Auto-generated constructor stub
    }

    public CheckableImageButton(Context context, AttributeSet attrs) 
    {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }
	

	public CheckableImageButton(Context context, AttributeSet attrs, int defStyleAttr) 
	{
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isChecked() 
	{
		// TODO Auto-generated method stub
		return isChecked;
	}

	@Override
	public void setChecked(boolean checked) 
	{
		isChecked = checked;
	}

	@Override
	public void toggle() 
	{
		// TODO Auto-generated method stub
		
	}

}
