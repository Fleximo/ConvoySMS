package com.international.group.bat;

import java.io.Serializable;

import android.widget.Spinner;


public class CustomSpinner implements Serializable
{
	private static final long serialVersionUID = 46548976L;
	
	private Spinner m_spinner = null;
	
	public CustomSpinner(Spinner spinner)
	{
		m_spinner = spinner;
	}
	
	public Spinner getSpinner()
	{
		return m_spinner;
	}
}
