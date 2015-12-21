package stateholders;

import java.util.Vector;

public class TextSMSStateHolder 
{
	//Messages
	private Vector<String> m_messages = new Vector<String>();
	public void setMessages(Vector<String> messages) { m_messages = messages; }
	public Vector<String> getMessages() { return m_messages; }
	
	//Text SMS
	private boolean et_TextSMS_TextSMS_enabled = false;
	private String et_TextSMS_TextSMS_value = "";
	public void setTextSMSEnabled(boolean enabled) { et_TextSMS_TextSMS_enabled = enabled; }
	public void setTextSMSValue(String value) { et_TextSMS_TextSMS_value = value; }
	public boolean getTextSMSEnabled() { return et_TextSMS_TextSMS_enabled; }
	public String getTextSMSValue() { return et_TextSMS_TextSMS_value; }
	
	private boolean et_PhoneForSMS_enabled = true;
	private String et_PhoneForSMS_value = "";
	public void setPhoneForSMSEnabled(boolean enabled) { et_PhoneForSMS_enabled = enabled; }
	public void setPhoneForSMSValue(String value) { et_PhoneForSMS_value = value; }
	public boolean getPhoneForSMSEnabled() { return et_PhoneForSMS_enabled; }
	public String getPhoneForSMSValue() { return et_PhoneForSMS_value; }
	
	//Manual mode
	private boolean cb_TextSMS_ManualMode_enabled = true;
	private boolean cb_TextSMS_ManualMode_checked = false;
	public void setManualModeEnabled(boolean enabled) { cb_TextSMS_ManualMode_enabled = enabled; }
	public void setManualModeChecked(boolean checked) { cb_TextSMS_ManualMode_checked = checked; }
	public boolean getManualModemEnabled() { return cb_TextSMS_ManualMode_enabled; }
	public boolean getManualModeChecked() { return cb_TextSMS_ManualMode_checked; }
	
}
