package com.international.group.bat;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;



public class CustomSpinnerPopup extends Activity implements OnClickListener
{
	ArrayList<String> m_stringList = null;
	ArrayList<TextView> m_textViews = null;
	LinearLayout m_itemsList = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_spinner);
		
		init();
		setListeners();
		setDisplayMetrics();

		ArrayList<String> arrayListOutput = getIntent().getStringArrayListExtra(Outputs.SPINNER_OUTPUT);
		if(arrayListOutput != null)
			m_stringList = arrayListOutput;
		else if(getIntent().getStringArrayListExtra(Inputs.SPINNER_INPUTS) != null) {
			m_stringList = getIntent().getStringArrayListExtra(Inputs.SPINNER_INPUTS);
		}
		else if (getIntent().getStringArrayListExtra(UniversalTimeChannels.SPINNER_UTC) != null) {
			m_stringList = getIntent().getStringArrayListExtra(UniversalTimeChannels.SPINNER_UTC);
		}

		setLanguage();
		loadFilesToTheList();
	}
	
	void init()
	{
		m_itemsList = (LinearLayout) findViewById(R.id.m_itemsList);
		m_textViews = new ArrayList<TextView>();
	}
	
	void setListeners()
	{
		
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
		int items_count = m_stringList.size();
		for(int i = 0; i < items_count; ++i)
		{
			String text = m_stringList.get(i);
			
			//Create text view
			TextView textView = new TextView(this);
			textView.setText(text);
			textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 
			           getResources().getDimension(R.dimen.header_text_size));
			LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			int myMarginPx = (int) getResources().getDimension(R.dimen.menu_padding);
			//params.setMargins(myMarginPx, myMarginPx, myMarginPx, myMarginPx);
			textView.setLayoutParams(params);
			textView.setTextColor(getResources().getColor(R.color.button_text));
			textView.setBackgroundColor(getResources().getColor(R.color.background_menu));
			textView.setPadding(myMarginPx, myMarginPx, myMarginPx, myMarginPx);
			//textView.setGravity(Gravity.CENTER);
			textView.setOnClickListener(this);
			
			//Create separator
			View view = new View(this);
			final float scale = this.getResources().getDisplayMetrics().density;
			int pixels = (int) (2 * scale + 0.5f);
			view.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
			LayoutParams params2 = new LayoutParams(LayoutParams.MATCH_PARENT, pixels);
			view.setLayoutParams(params2);
			
			m_textViews.add(textView);
			m_itemsList.addView(textView);
			m_itemsList.addView(view);
		}
	}

	@Override
	public void onClick(View v) 
	{
		TextView tv = (TextView) v;
		if(tv != null)
		{
			int itemId = 0;
			final int itemsCount = m_textViews.size();
			for(int i = 0; i < itemsCount; ++i)
			{
				if(tv.equals(m_textViews.get(i)))
					itemId = i;
			}
			
			Intent intent = new Intent();
		    intent.putExtra(Outputs.SPINNER_OUTPUT, itemId);
		    setResult(RESULT_OK, intent);
		    tv.setBackgroundColor(Color.parseColor("#4fa5d5"));
		    finish();
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
}
