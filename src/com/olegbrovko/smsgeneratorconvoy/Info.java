package com.olegbrovko.smsgeneratorconvoy;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.olegbrovko.smsgeneratorconvoy.R;

public class Info extends Activity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);
		
		setDisplayMetrics();
	}
	
	private void setDisplayMetrics()
	{
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		int width = dm.widthPixels;
		int height = dm.heightPixels;
		
		getWindow().setLayout((int)(width*1.0), (int)(height*1.0));
	}
}
