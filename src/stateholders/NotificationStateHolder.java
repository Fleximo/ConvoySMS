package stateholders;

public class NotificationStateHolder
{
	//Current user id
	private int currentUserId = 0;
	public void setCurrentUserId(int currId) { currentUserId = currId;}
	public int getCurrentUserId() { return currentUserId;}
	
	//User Enable checkbox
	private boolean cb_Notifications_User1_enabled = true;
	private boolean cb_Notifications_User1_checked = false;
	private boolean cb_Notifications_User2_enabled = true;
	private boolean cb_Notifications_User2_checked = false;
	private boolean cb_Notifications_User3_enabled = true;
	private boolean cb_Notifications_User3_checked = false;
	private boolean cb_Notifications_User4_enabled = true;
	private boolean cb_Notifications_User4_checked = false;
	private boolean cb_Notifications_User5_enabled = true;
	private boolean cb_Notifications_User5_checked = false;
	public void setCbInputsInEnabled(int userId, boolean enabled) 
	{
		switch(userId)
		{
			case 0:
			{
				cb_Notifications_User1_enabled = enabled;
				break;
			}
			case 1:
			{
				cb_Notifications_User2_enabled = enabled;
				break;
			}
			case 2:
			{
				cb_Notifications_User3_enabled = enabled;
				break;
			}
			case 3:
			{
				cb_Notifications_User4_enabled = enabled;
				break;
			}
			case 4:
			{
				cb_Notifications_User5_enabled = enabled;
				break;
			}
		}
	}
	public void setCbInputsInChecked(int userId, boolean checked) 
	{
		switch(userId)
		{
			case 0:
			{
				cb_Notifications_User1_checked = checked;
				break;
			}
			case 1:
			{
				cb_Notifications_User2_checked = checked;
				break;
			}
			case 2:
			{
				cb_Notifications_User3_checked = checked;
				break;
			}
			case 3:
			{
				cb_Notifications_User4_checked = checked;
				break;
			}
			case 4:
			{
				cb_Notifications_User5_checked = checked;
				break;
			}
		}
	}
	public boolean getCbInputsInEnabled(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return cb_Notifications_User1_enabled;
			}
			case 1:
			{
				return cb_Notifications_User2_enabled;
			}
			case 2:
			{
				return cb_Notifications_User3_enabled;
			}
			case 3:
			{
				return cb_Notifications_User4_enabled;
			}
			case 4:
			{
				return cb_Notifications_User5_enabled;
			}
			default:
			{
				return true;
			}
		}
	}
	public boolean getCbInputsInChecked(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return cb_Notifications_User1_checked;
			}
			case 1:
			{
				return cb_Notifications_User2_checked;
			}
			case 2:
			{
				return cb_Notifications_User3_checked;
			}
			case 3:
			{
				return cb_Notifications_User4_checked;
			}
			case 4:
			{
				return cb_Notifications_User5_checked;
			}
			default:
			{
				return false;
			}
		}
	}
	
	//Phone number
	private boolean et_Notifications_PhoneNumberUser1_enabled = false;
	private String et_Notifications_PhoneNumberUser1_value = "";
	private boolean et_Notifications_PhoneNumberUser2_enabled = false;
	private String et_Notifications_PhoneNumberUser2_value = "";
	private boolean et_Notifications_PhoneNumberUser3_enabled = false;
	private String et_Notifications_PhoneNumberUser3_value = "";
	private boolean et_Notifications_PhoneNumberUser4_enabled = false;
	private String et_Notifications_PhoneNumberUser4_value = "";
	private boolean et_Notifications_PhoneNumberUser5_enabled = false;
	private String et_Notifications_PhoneNumberUser5_value = "";
	public void setEtPhoneNumberEnabled(int userId, boolean enabled) 
	{ 
		switch(userId)
		{
			case 0:
			{
				et_Notifications_PhoneNumberUser1_enabled = enabled;
				break;
			}
			case 1:
			{
				et_Notifications_PhoneNumberUser2_enabled = enabled;
				break;
			}
			case 2:
			{
				et_Notifications_PhoneNumberUser3_enabled = enabled;
				break;
			}
			case 3:
			{
				et_Notifications_PhoneNumberUser4_enabled = enabled;
				break;
			}
			case 4:
			{
				et_Notifications_PhoneNumberUser5_enabled = enabled;
				break;
			}
		} 
	}
	public void setEtPhoneNumberValue(int userId, String value) 
	{
		switch(userId)
		{
			case 0:
			{
				et_Notifications_PhoneNumberUser1_value = value;
				break;
			}
			case 1:
			{
				et_Notifications_PhoneNumberUser2_value = value;
				break;
			}
			case 2:
			{
				et_Notifications_PhoneNumberUser3_value = value;
				break;
			}
			case 3:
			{
				et_Notifications_PhoneNumberUser4_value = value;
				break;
			}
			case 4:
			{
				et_Notifications_PhoneNumberUser5_value = value;
				break;
			}
		} 
	}
	public boolean getEtPhoneNumberEnabled(int userId) 
	{ 
		switch(userId)
		{
			case 0:
			{
				return et_Notifications_PhoneNumberUser1_enabled;
			}
			case 1:
			{
				return et_Notifications_PhoneNumberUser2_enabled;
			}
			case 2:
			{
				return et_Notifications_PhoneNumberUser3_enabled;
			}
			case 3:
			{
				return et_Notifications_PhoneNumberUser4_enabled;
			}
			case 4:
			{
				return et_Notifications_PhoneNumberUser5_enabled;
			}
			default:
			{
				return false;
			}
		} 
	}
	public String getEtPhoneNumberValue(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return et_Notifications_PhoneNumberUser1_value;
			}
			case 1:
			{
				return et_Notifications_PhoneNumberUser2_value;
			}
			case 2:
			{
				return et_Notifications_PhoneNumberUser3_value;
			}
			case 3:
			{
				return et_Notifications_PhoneNumberUser4_value;
			}
			case 4:
			{
				return et_Notifications_PhoneNumberUser5_value;
			}
			default:
			{
				return "";
			}
		} 
	}
	
	//Allow Notification
	private boolean cb_Notidications_AllowNotificationsSMSUser1_enabled = false;
	private boolean cb_Notidications_AllowNotificationsSMSUser1_checked = false;
	private boolean cb_Notidications_AllowNotificationsSMSUser2_enabled = false;
	private boolean cb_Notidications_AllowNotificationsSMSUser2_checked = false;
	private boolean cb_Notidications_AllowNotificationsSMSUser3_enabled = false;
	private boolean cb_Notidications_AllowNotificationsSMSUser3_checked = false;
	private boolean cb_Notidications_AllowNotificationsSMSUser4_enabled = false;
	private boolean cb_Notidications_AllowNotificationsSMSUser4_checked = false;
	private boolean cb_Notidications_AllowNotificationsSMSUser5_enabled = false;
	private boolean cb_Notidications_AllowNotificationsSMSUser5_checked = false;
	public void setCbAllowNotificationsSMSEnabled(int userId, boolean enabled) 
	{
		switch(userId)
		{
			case 0:
			{
				cb_Notidications_AllowNotificationsSMSUser1_enabled = enabled;
				break;
			}
			case 1:
			{
				cb_Notidications_AllowNotificationsSMSUser2_enabled = enabled;
				break;
			}
			case 2:
			{
				cb_Notidications_AllowNotificationsSMSUser3_enabled = enabled;
				break;
			}
			case 3:
			{
				cb_Notidications_AllowNotificationsSMSUser4_enabled = enabled;
				break;
			}
			case 4:
			{
				cb_Notidications_AllowNotificationsSMSUser5_enabled = enabled;
				break;
			}
		}
	}
	public void setCbAllowNotificationsSMSChecked(int userId, boolean checked) 
	{
		switch(userId)
		{
			case 0:
			{
				cb_Notidications_AllowNotificationsSMSUser1_checked = checked;
				break;
			}
			case 1:
			{
				cb_Notidications_AllowNotificationsSMSUser2_checked = checked;
				break;
			}
			case 2:
			{
				cb_Notidications_AllowNotificationsSMSUser3_checked = checked;
				break;
			}
			case 3:
			{
				cb_Notidications_AllowNotificationsSMSUser4_checked = checked;
				break;
			}
			case 4:
			{
				cb_Notidications_AllowNotificationsSMSUser5_checked = checked;
				break;
			}
		}
	}
	public boolean getCbAllowNotificationsSMSEnabled(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return cb_Notidications_AllowNotificationsSMSUser1_enabled;
			}
			case 1:
			{
				return cb_Notidications_AllowNotificationsSMSUser2_enabled;
			}
			case 2:
			{
				return cb_Notidications_AllowNotificationsSMSUser3_enabled;
			}
			case 3:
			{
				return cb_Notidications_AllowNotificationsSMSUser4_enabled;
			}
			case 4:
			{
				return cb_Notidications_AllowNotificationsSMSUser5_enabled;
			}
			default:
			{
				return false;
			}
		}
	}
	public boolean getCbAllowNotificationsSMSChecked(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return cb_Notidications_AllowNotificationsSMSUser1_checked;
			}
			case 1:
			{
				return cb_Notidications_AllowNotificationsSMSUser2_checked;
			}
			case 2:
			{
				return cb_Notidications_AllowNotificationsSMSUser3_checked;
			}
			case 3:
			{
				return cb_Notidications_AllowNotificationsSMSUser4_checked;
			}
			case 4:
			{
				return cb_Notidications_AllowNotificationsSMSUser5_checked;
			}
			default:
			{
				return false;
			}
		}
	}
	
	private boolean cb_Notidications_AllowNotificationsTELUser1_enabled = false;
	private boolean cb_Notidications_AllowNotificationsTELUser1_checked = false;
	private boolean cb_Notidications_AllowNotificationsTELUser2_enabled = false;
	private boolean cb_Notidications_AllowNotificationsTELUser2_checked = false;
	private boolean cb_Notidications_AllowNotificationsTELUser3_enabled = false;
	private boolean cb_Notidications_AllowNotificationsTELUser3_checked = false;
	private boolean cb_Notidications_AllowNotificationsTELUser4_enabled = false;
	private boolean cb_Notidications_AllowNotificationsTELUser4_checked = false;
	private boolean cb_Notidications_AllowNotificationsTELUser5_enabled = false;
	private boolean cb_Notidications_AllowNotificationsTELUser5_checked = false;
	public void setCbAllowNotificationsTELEnabled(int userId, boolean enabled) 
	{
		switch(userId)
		{
			case 0:
			{
				cb_Notidications_AllowNotificationsTELUser1_enabled = enabled;
				break;
			}
			case 1:
			{
				cb_Notidications_AllowNotificationsTELUser2_enabled = enabled;
				break;
			}
			case 2:
			{
				cb_Notidications_AllowNotificationsTELUser3_enabled = enabled;
				break;
			}
			case 3:
			{
				cb_Notidications_AllowNotificationsTELUser4_enabled = enabled;
				break;
			}
			case 4:
			{
				cb_Notidications_AllowNotificationsTELUser5_enabled = enabled;
				break;
			}
		}
	}
	public void setCbAllowNotificationsTELChecked(int userId, boolean checked) 
	{
		switch(userId)
		{
			case 0:
			{
				cb_Notidications_AllowNotificationsTELUser1_checked = checked;
				break;
			}
			case 1:
			{
				cb_Notidications_AllowNotificationsTELUser2_checked = checked;
				break;
			}
			case 2:
			{
				cb_Notidications_AllowNotificationsTELUser3_checked = checked;
				break;
			}
			case 3:
			{
				cb_Notidications_AllowNotificationsTELUser4_checked = checked;
				break;
			}
			case 4:
			{
				cb_Notidications_AllowNotificationsTELUser5_checked = checked;
				break;
			}
		}
	}
	public boolean getCbAllowNotificationsTELEnabled(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return cb_Notidications_AllowNotificationsTELUser1_enabled;
			}
			case 1:
			{
				return cb_Notidications_AllowNotificationsTELUser2_enabled;
			}
			case 2:
			{
				return cb_Notidications_AllowNotificationsTELUser3_enabled;
			}
			case 3:
			{
				return cb_Notidications_AllowNotificationsTELUser4_enabled;
			}
			case 4:
			{
				return cb_Notidications_AllowNotificationsTELUser5_enabled;
			}
			default:
			{
				return false;
			}
		}
	}
	public boolean getCbAllowNotificationsTELChecked(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return cb_Notidications_AllowNotificationsTELUser1_checked;
			}
			case 1:
			{
				return cb_Notidications_AllowNotificationsTELUser2_checked;
			}
			case 2:
			{
				return cb_Notidications_AllowNotificationsTELUser3_checked;
			}
			case 3:
			{
				return cb_Notidications_AllowNotificationsTELUser4_checked;
			}
			case 4:
			{
				return cb_Notidications_AllowNotificationsTELUser5_checked;
			}
			default:
			{
				return false;
			}
		}
	}
	
	//Button Call button pressed
	private boolean btn_Notifications_CallBtnPressedSMSUser1_enabled = false;
	private boolean btn_Notifications_CallBtnPressedSMSUser1_checked = false;
	private boolean btn_Notifications_CallBtnPressedSMSUser2_enabled = false;
	private boolean btn_Notifications_CallBtnPressedSMSUser2_checked = false;
	private boolean btn_Notifications_CallBtnPressedSMSUser3_enabled = false;
	private boolean btn_Notifications_CallBtnPressedSMSUser3_checked = false;
	private boolean btn_Notifications_CallBtnPressedSMSUser4_enabled = false;
	private boolean btn_Notifications_CallBtnPressedSMSUser4_checked = false;
	private boolean btn_Notifications_CallBtnPressedSMSUser5_enabled = false;
	private boolean btn_Notifications_CallBtnPressedSMSUser5_checked = false;
	public void setBtnCallBtnPressedSMSEnabled(int userId, boolean enabled) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_CallBtnPressedSMSUser1_enabled = enabled;
				break;
			}
			case 1:
			{
				btn_Notifications_CallBtnPressedSMSUser2_enabled = enabled;
				break;
			}
			case 2:
			{
				btn_Notifications_CallBtnPressedSMSUser3_enabled = enabled;
				break;
			}
			case 3:
			{
				btn_Notifications_CallBtnPressedSMSUser4_enabled = enabled;
				break;
			}
			case 4:
			{
				btn_Notifications_CallBtnPressedSMSUser5_enabled = enabled;
				break;
			}
		}
	}
	public void setBtnCallBtnPressedSMSChecked(int userId, boolean checked) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_CallBtnPressedSMSUser1_checked = checked;
				break;
			}
			case 1:
			{
				btn_Notifications_CallBtnPressedSMSUser2_checked = checked;
				break;
			}
			case 2:
			{
				btn_Notifications_CallBtnPressedSMSUser3_checked = checked;
				break;
			}
			case 3:
			{
				btn_Notifications_CallBtnPressedSMSUser4_checked = checked;
				break;
			}
			case 4:
			{
				btn_Notifications_CallBtnPressedSMSUser5_checked = checked;
				break;
			}
		}
	}
	public boolean getBtnCallBtnPressedSMSEnabled(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_CallBtnPressedSMSUser1_enabled;
			}
			case 1:
			{
				return btn_Notifications_CallBtnPressedSMSUser2_enabled;
			}
			case 2:
			{
				return btn_Notifications_CallBtnPressedSMSUser3_enabled;
			}
			case 3:
			{
				return btn_Notifications_CallBtnPressedSMSUser4_enabled;
			}
			case 4:
			{
				return btn_Notifications_CallBtnPressedSMSUser5_enabled;
			}
			default:
			{
				return false;
			}
		}
	}
	public boolean getBtnCallBtnPressedSMSChecked(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_CallBtnPressedSMSUser1_checked;
			}
			case 1:
			{
				return btn_Notifications_CallBtnPressedSMSUser2_checked;
			}
			case 2:
			{
				return btn_Notifications_CallBtnPressedSMSUser3_checked;
			}
			case 3:
			{
				return btn_Notifications_CallBtnPressedSMSUser4_checked;
			}
			case 4:
			{
				return btn_Notifications_CallBtnPressedSMSUser5_checked;
			}
			default:
			{
				return false;
			}
		}
	}
	
	private boolean btn_Notifications_CallBtnPressedTELUser1_enabled = false;
	private boolean btn_Notifications_CallBtnPressedTELUser1_checked = false;
	private boolean btn_Notifications_CallBtnPressedTELUser2_enabled = false;
	private boolean btn_Notifications_CallBtnPressedTELUser2_checked = false;
	private boolean btn_Notifications_CallBtnPressedTELUser3_enabled = false;
	private boolean btn_Notifications_CallBtnPressedTELUser3_checked = false;
	private boolean btn_Notifications_CallBtnPressedTELUser4_enabled = false;
	private boolean btn_Notifications_CallBtnPressedTELUser4_checked = false;
	private boolean btn_Notifications_CallBtnPressedTELUser5_enabled = false;
	private boolean btn_Notifications_CallBtnPressedTELUser5_checked = false;
	public void setBtnCallBtnPressedTELEnabled(int userId, boolean enabled) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_CallBtnPressedTELUser1_enabled = enabled;
				break;
			}
			case 1:
			{
				btn_Notifications_CallBtnPressedTELUser2_enabled = enabled;
				break;
			}
			case 2:
			{
				btn_Notifications_CallBtnPressedTELUser3_enabled = enabled;
				break;
			}
			case 3:
			{
				btn_Notifications_CallBtnPressedTELUser4_enabled = enabled;
				break;
			}
			case 4:
			{
				btn_Notifications_CallBtnPressedTELUser5_enabled = enabled;
				break;
			}
		}
	}
	public void setBtnCallBtnPressedTELChecked(int userId, boolean checked) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_CallBtnPressedTELUser1_checked = checked;
				break;
			}
			case 1:
			{
				btn_Notifications_CallBtnPressedTELUser2_checked = checked;
				break;
			}
			case 2:
			{
				btn_Notifications_CallBtnPressedTELUser3_checked = checked;
				break;
			}
			case 3:
			{
				btn_Notifications_CallBtnPressedTELUser4_checked = checked;
				break;
			}
			case 4:
			{
				btn_Notifications_CallBtnPressedTELUser5_checked = checked;
				break;
			}
		}
	}
	public boolean getBtnCallBtnPressedTELEnabled(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_CallBtnPressedTELUser1_enabled;
			}
			case 1:
			{
				return btn_Notifications_CallBtnPressedTELUser2_enabled;
			}
			case 2:
			{
				return btn_Notifications_CallBtnPressedTELUser3_enabled;
			}
			case 3:
			{
				return btn_Notifications_CallBtnPressedTELUser4_enabled;
			}
			case 4:
			{
				return btn_Notifications_CallBtnPressedTELUser5_enabled;
			}
			default:
			{
				return false;
			}
		}
	}
	public boolean getBtnCallBtnPressedTELChecked(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_CallBtnPressedTELUser1_checked;
			}
			case 1:
			{
				return btn_Notifications_CallBtnPressedTELUser2_checked;
			}
			case 2:
			{
				return btn_Notifications_CallBtnPressedTELUser3_checked;
			}
			case 3:
			{
				return btn_Notifications_CallBtnPressedTELUser4_checked;
			}
			case 4:
			{
				return btn_Notifications_CallBtnPressedTELUser5_checked;
			}
			default:
			{
				return false;
			}
		}
	}
	
	//Ingine Active
	private boolean btn_Notifications_IngineActiveSMSUser1_enabled = false;
	private boolean btn_Notifications_IngineActiveSMSUser1_checked = false;
	private boolean btn_Notifications_IngineActiveSMSUser2_enabled = false;
	private boolean btn_Notifications_IngineActiveSMSUser2_checked = false;
	private boolean btn_Notifications_IngineActiveSMSUser3_enabled = false;
	private boolean btn_Notifications_IngineActiveSMSUser3_checked = false;
	private boolean btn_Notifications_IngineActiveSMSUser4_enabled = false;
	private boolean btn_Notifications_IngineActiveSMSUser4_checked = false;
	private boolean btn_Notifications_IngineActiveSMSUser5_enabled = false;
	private boolean btn_Notifications_IngineActiveSMSUser5_checked = false;
	public void setBtnIngineActiveSMSEnabled(int userId, boolean enabled) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_IngineActiveSMSUser1_enabled = enabled;
				break;
			}
			case 1:
			{
				btn_Notifications_IngineActiveSMSUser2_enabled = enabled;
				break;
			}
			case 2:
			{
				btn_Notifications_IngineActiveSMSUser3_enabled = enabled;
				break;
			}
			case 3:
			{
				btn_Notifications_IngineActiveSMSUser4_enabled = enabled;
				break;
			}
			case 4:
			{
				btn_Notifications_IngineActiveSMSUser5_enabled = enabled;
				break;
			}
		}
	}
	public void setBtnIngineActiveSMSChecked(int userId, boolean checked) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_IngineActiveSMSUser1_checked = checked;
				break;
			}
			case 1:
			{
				btn_Notifications_IngineActiveSMSUser2_checked = checked;
				break;
			}
			case 2:
			{
				btn_Notifications_IngineActiveSMSUser3_checked = checked;
				break;
			}
			case 3:
			{
				btn_Notifications_IngineActiveSMSUser4_checked = checked;
				break;
			}
			case 4:
			{
				btn_Notifications_IngineActiveSMSUser5_checked = checked;
				break;
			}
		}
	}
	public boolean getBtnIngineActiveSMSEnabled(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_IngineActiveSMSUser1_enabled;
			}
			case 1:
			{
				return btn_Notifications_IngineActiveSMSUser2_enabled;
			}
			case 2:
			{
				return btn_Notifications_IngineActiveSMSUser3_enabled;
			}
			case 3:
			{
				return btn_Notifications_IngineActiveSMSUser4_enabled;
			}
			case 4:
			{
				return btn_Notifications_IngineActiveSMSUser5_enabled;
			}
			default:
			{
				return false;
			}
		}
	}
	public boolean getBtnIngineActiveSMSChecked(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_IngineActiveSMSUser1_checked;
			}
			case 1:
			{
				return btn_Notifications_IngineActiveSMSUser2_checked;
			}
			case 2:
			{
				return btn_Notifications_IngineActiveSMSUser3_checked;
			}
			case 3:
			{
				return btn_Notifications_IngineActiveSMSUser4_checked;
			}
			case 4:
			{
				return btn_Notifications_IngineActiveSMSUser5_checked;
			}
			default:
			{
				return false;
			}
		}
	}
	
	private boolean btn_Notifications_IngineActiveTELUser1_enabled = false;
	private boolean btn_Notifications_IngineActiveTELUser1_checked = false;
	private boolean btn_Notifications_IngineActiveTELUser2_enabled = false;
	private boolean btn_Notifications_IngineActiveTELUser2_checked = false;
	private boolean btn_Notifications_IngineActiveTELUser3_enabled = false;
	private boolean btn_Notifications_IngineActiveTELUser3_checked = false;
	private boolean btn_Notifications_IngineActiveTELUser4_enabled = false;
	private boolean btn_Notifications_IngineActiveTELUser4_checked = false;
	private boolean btn_Notifications_IngineActiveTELUser5_enabled = false;
	private boolean btn_Notifications_IngineActiveTELUser5_checked = false;
	public void setBtnIngineActiveTELEnabled(int userId, boolean enabled) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_IngineActiveTELUser1_enabled = enabled;
				break;
			}
			case 1:
			{
				btn_Notifications_IngineActiveTELUser2_enabled = enabled;
				break;
			}
			case 2:
			{
				btn_Notifications_IngineActiveTELUser3_enabled = enabled;
				break;
			}
			case 3:
			{
				btn_Notifications_IngineActiveTELUser4_enabled = enabled;
				break;
			}
			case 4:
			{
				btn_Notifications_IngineActiveTELUser5_enabled = enabled;
				break;
			}
		}
	}
	public void setBtnIngineActiveTELChecked(int userId, boolean checked) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_IngineActiveTELUser1_checked = checked;
				break;
			}
			case 1:
			{
				btn_Notifications_IngineActiveTELUser2_checked = checked;
				break;
			}
			case 2:
			{
				btn_Notifications_IngineActiveTELUser3_checked = checked;
				break;
			}
			case 3:
			{
				btn_Notifications_IngineActiveTELUser4_checked = checked;
				break;
			}
			case 4:
			{
				btn_Notifications_IngineActiveTELUser5_checked = checked;
				break;
			}
		}
	}
	public boolean getBtnIngineActiveTELEnabled(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_IngineActiveTELUser1_enabled;
			}
			case 1:
			{
				return btn_Notifications_IngineActiveTELUser2_enabled;
			}
			case 2:
			{
				return btn_Notifications_IngineActiveTELUser3_enabled;
			}
			case 3:
			{
				return btn_Notifications_IngineActiveTELUser4_enabled;
			}
			case 4:
			{
				return btn_Notifications_IngineActiveTELUser5_enabled;
			}
			default:
			{
				return false;
			}
		}
	}
	public boolean getBtnIngineActiveTELChecked(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_IngineActiveTELUser1_checked;
			}
			case 1:
			{
				return btn_Notifications_IngineActiveTELUser2_checked;
			}
			case 2:
			{
				return btn_Notifications_IngineActiveTELUser3_checked;
			}
			case 3:
			{
				return btn_Notifications_IngineActiveTELUser4_checked;
			}
			case 4:
			{
				return btn_Notifications_IngineActiveTELUser5_checked;
			}
			default:
			{
				return false;
			}
		}
	}
	
	//Load Limit Switch
	private boolean btn_Notifications_LoadLimitSwitchSMSUser1_enabled = false;
	private boolean btn_Notifications_LoadLimitSwitchSMSUser1_checked = false;
	private boolean btn_Notifications_LoadLimitSwitchSMSUser2_enabled = false;
	private boolean btn_Notifications_LoadLimitSwitchSMSUser2_checked = false;
	private boolean btn_Notifications_LoadLimitSwitchSMSUser3_enabled = false;
	private boolean btn_Notifications_LoadLimitSwitchSMSUser3_checked = false;
	private boolean btn_Notifications_LoadLimitSwitchSMSUser4_enabled = false;
	private boolean btn_Notifications_LoadLimitSwitchSMSUser4_checked = false;
	private boolean btn_Notifications_LoadLimitSwitchSMSUser5_enabled = false;
	private boolean btn_Notifications_LoadLimitSwitchSMSUser5_checked = false;
	public void setBtnLoadLimitSwitchSMSEnabled(int userId, boolean enabled) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_LoadLimitSwitchSMSUser1_enabled = enabled;
				break;
			}
			case 1:
			{
				btn_Notifications_LoadLimitSwitchSMSUser2_enabled = enabled;
				break;
			}
			case 2:
			{
				btn_Notifications_LoadLimitSwitchSMSUser3_enabled = enabled;
				break;
			}
			case 3:
			{
				btn_Notifications_LoadLimitSwitchSMSUser4_enabled = enabled;
				break;
			}
			case 4:
			{
				btn_Notifications_LoadLimitSwitchSMSUser5_enabled = enabled;
				break;
			}
		}
	}
	public void setBtnLoadLimitSwitchSMSChecked(int userId, boolean checked) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_LoadLimitSwitchSMSUser1_checked = checked;
				break;
			}
			case 1:
			{
				btn_Notifications_LoadLimitSwitchSMSUser2_checked = checked;
				break;
			}
			case 2:
			{
				btn_Notifications_LoadLimitSwitchSMSUser3_checked = checked;
				break;
			}
			case 3:
			{
				btn_Notifications_LoadLimitSwitchSMSUser4_checked = checked;
				break;
			}
			case 4:
			{
				btn_Notifications_LoadLimitSwitchSMSUser5_checked = checked;
				break;
			}
		}
	}
	public boolean getBtnLoadLimitSwitchSMSEnabled(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_LoadLimitSwitchSMSUser1_enabled;
			}
			case 1:
			{
				return btn_Notifications_LoadLimitSwitchSMSUser2_enabled;
			}
			case 2:
			{
				return btn_Notifications_LoadLimitSwitchSMSUser3_enabled;
			}
			case 3:
			{
				return btn_Notifications_LoadLimitSwitchSMSUser4_enabled;
			}
			case 4:
			{
				return btn_Notifications_LoadLimitSwitchSMSUser5_enabled;
			}
			default:
			{
				return false;
			}
		}
	}
	public boolean getBtnLoadLimitSwitchSMSChecked(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_LoadLimitSwitchSMSUser1_checked;
			}
			case 1:
			{
				return btn_Notifications_LoadLimitSwitchSMSUser2_checked;
			}
			case 2:
			{
				return btn_Notifications_LoadLimitSwitchSMSUser3_checked;
			}
			case 3:
			{
				return btn_Notifications_LoadLimitSwitchSMSUser4_checked;
			}
			case 4:
			{
				return btn_Notifications_LoadLimitSwitchSMSUser5_checked;
			}
			default:
			{
				return false;
			}
		}
	}
	
	private boolean btn_Notifications_LoadLimitSwitchTELUser1_enabled = false;
	private boolean btn_Notifications_LoadLimitSwitchTELUser1_checked = false;
	private boolean btn_Notifications_LoadLimitSwitchTELUser2_enabled = false;
	private boolean btn_Notifications_LoadLimitSwitchTELUser2_checked = false;
	private boolean btn_Notifications_LoadLimitSwitchTELUser3_enabled = false;
	private boolean btn_Notifications_LoadLimitSwitchTELUser3_checked = false;
	private boolean btn_Notifications_LoadLimitSwitchTELUser4_enabled = false;
	private boolean btn_Notifications_LoadLimitSwitchTELUser4_checked = false;
	private boolean btn_Notifications_LoadLimitSwitchTELUser5_enabled = false;
	private boolean btn_Notifications_LoadLimitSwitchTELUser5_checked = false;
	public void setBtnLoadLimitSwitchTELEnabled(int userId, boolean enabled) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_LoadLimitSwitchTELUser1_enabled = enabled;
				break;
			}
			case 1:
			{
				btn_Notifications_LoadLimitSwitchTELUser2_enabled = enabled;
				break;
			}
			case 2:
			{
				btn_Notifications_LoadLimitSwitchTELUser3_enabled = enabled;
				break;
			}
			case 3:
			{
				btn_Notifications_LoadLimitSwitchTELUser4_enabled = enabled;
				break;
			}
			case 4:
			{
				btn_Notifications_LoadLimitSwitchTELUser5_enabled = enabled;
				break;
			}
		}
	}
	public void setBtnLoadLimitSwitchTELChecked(int userId, boolean checked) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_LoadLimitSwitchTELUser1_checked = checked;
				break;
			}
			case 1:
			{
				btn_Notifications_LoadLimitSwitchTELUser2_checked = checked;
				break;
			}
			case 2:
			{
				btn_Notifications_LoadLimitSwitchTELUser3_checked = checked;
				break;
			}
			case 3:
			{
				btn_Notifications_LoadLimitSwitchTELUser4_checked = checked;
				break;
			}
			case 4:
			{
				btn_Notifications_LoadLimitSwitchTELUser5_checked = checked;
				break;
			}
		}
	}
	public boolean getBtnLoadLimitSwitchTELEnabled(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_LoadLimitSwitchTELUser1_enabled;
			}
			case 1:
			{
				return btn_Notifications_LoadLimitSwitchTELUser2_enabled;
			}
			case 2:
			{
				return btn_Notifications_LoadLimitSwitchTELUser3_enabled;
			}
			case 3:
			{
				return btn_Notifications_LoadLimitSwitchTELUser4_enabled;
			}
			case 4:
			{
				return btn_Notifications_LoadLimitSwitchTELUser5_enabled;
			}
			default:
			{
				return false;
			}
		}
	}
	public boolean getBtnLoadLimitSwitchTELChecked(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_LoadLimitSwitchTELUser1_checked;
			}
			case 1:
			{
				return btn_Notifications_LoadLimitSwitchTELUser2_checked;
			}
			case 2:
			{
				return btn_Notifications_LoadLimitSwitchTELUser3_checked;
			}
			case 3:
			{
				return btn_Notifications_LoadLimitSwitchTELUser4_checked;
			}
			case 4:
			{
				return btn_Notifications_LoadLimitSwitchTELUser5_checked;
			}
			default:
			{
				return false;
			}
		}
	}
	
	//Button Load Shock Sensor
	private boolean btn_Notifications_LoadShockSensorSMSUser1_enabled = false;
	private boolean btn_Notifications_LoadShockSensorSMSUser1_checked = false;
	private boolean btn_Notifications_LoadShockSensorSMSUser2_enabled = false;
	private boolean btn_Notifications_LoadShockSensorSMSUser2_checked = false;
	private boolean btn_Notifications_LoadShockSensorSMSUser3_enabled = false;
	private boolean btn_Notifications_LoadShockSensorSMSUser3_checked = false;
	private boolean btn_Notifications_LoadShockSensorSMSUser4_enabled = false;
	private boolean btn_Notifications_LoadShockSensorSMSUser4_checked = false;
	private boolean btn_Notifications_LoadShockSensorSMSUser5_enabled = false;
	private boolean btn_Notifications_LoadShockSensorSMSUser5_checked = false;
	public void setBtnLoadShockSensorSMSEnabled(int userId, boolean enabled) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_LoadShockSensorSMSUser1_enabled = enabled;
				break;
			}
			case 1:
			{
				btn_Notifications_LoadShockSensorSMSUser2_enabled = enabled;
				break;
			}
			case 2:
			{
				btn_Notifications_LoadShockSensorSMSUser3_enabled = enabled;
				break;
			}
			case 3:
			{
				btn_Notifications_LoadShockSensorSMSUser4_enabled = enabled;
				break;
			}
			case 4:
			{
				btn_Notifications_LoadShockSensorSMSUser5_enabled = enabled;
				break;
			}
		}
	}
	public void setBtnLoadShockSensorSMSChecked(int userId, boolean checked) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_LoadShockSensorSMSUser1_checked = checked;
				break;
			}
			case 1:
			{
				btn_Notifications_LoadShockSensorSMSUser2_checked = checked;
				break;
			}
			case 2:
			{
				btn_Notifications_LoadShockSensorSMSUser3_checked = checked;
				break;
			}
			case 3:
			{
				btn_Notifications_LoadShockSensorSMSUser4_checked = checked;
				break;
			}
			case 4:
			{
				btn_Notifications_LoadShockSensorSMSUser5_checked = checked;
				break;
			}
		}
	}
	public boolean getBtnLoadShockSensorSMSEnabled(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_LoadShockSensorSMSUser1_enabled;
			}
			case 1:
			{
				return btn_Notifications_LoadShockSensorSMSUser2_enabled;
			}
			case 2:
			{
				return btn_Notifications_LoadShockSensorSMSUser3_enabled;
			}
			case 3:
			{
				return btn_Notifications_LoadShockSensorSMSUser4_enabled;
			}
			case 4:
			{
				return btn_Notifications_LoadShockSensorSMSUser5_enabled;
			}
			default:
			{
				return false;
			}
		}
	}
	public boolean getBtnLoadShockSensorSMSChecked(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_LoadShockSensorSMSUser1_checked;
			}
			case 1:
			{
				return btn_Notifications_LoadShockSensorSMSUser2_checked;
			}
			case 2:
			{
				return btn_Notifications_LoadShockSensorSMSUser3_checked;
			}
			case 3:
			{
				return btn_Notifications_LoadShockSensorSMSUser4_checked;
			}
			case 4:
			{
				return btn_Notifications_LoadShockSensorSMSUser5_checked;
			}
			default:
			{
				return false;
			}
		}
	}
	
	private boolean btn_Notifications_LoadShockSensorTELUser1_enabled = false;
	private boolean btn_Notifications_LoadShockSensorTELUser1_checked = false;
	private boolean btn_Notifications_LoadShockSensorTELUser2_enabled = false;
	private boolean btn_Notifications_LoadShockSensorTELUser2_checked = false;
	private boolean btn_Notifications_LoadShockSensorTELUser3_enabled = false;
	private boolean btn_Notifications_LoadShockSensorTELUser3_checked = false;
	private boolean btn_Notifications_LoadShockSensorTELUser4_enabled = false;
	private boolean btn_Notifications_LoadShockSensorTELUser4_checked = false;
	private boolean btn_Notifications_LoadShockSensorTELUser5_enabled = false;
	private boolean btn_Notifications_LoadShockSensorTELUser5_checked = false;
	public void setBtnLoadShockSensorTELEnabled(int userId, boolean enabled) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_LoadShockSensorTELUser1_enabled = enabled;
				break;
			}
			case 1:
			{
				btn_Notifications_LoadShockSensorTELUser2_enabled = enabled;
				break;
			}
			case 2:
			{
				btn_Notifications_LoadShockSensorTELUser3_enabled = enabled;
				break;
			}
			case 3:
			{
				btn_Notifications_LoadShockSensorTELUser4_enabled = enabled;
				break;
			}
			case 4:
			{
				btn_Notifications_LoadShockSensorTELUser5_enabled = enabled;
				break;
			}
		}
	}
	public void setBtnLoadShockSensorTELChecked(int userId, boolean checked) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_LoadShockSensorTELUser1_checked = checked;
				break;
			}
			case 1:
			{
				btn_Notifications_LoadShockSensorTELUser2_checked = checked;
				break;
			}
			case 2:
			{
				btn_Notifications_LoadShockSensorTELUser3_checked = checked;
				break;
			}
			case 3:
			{
				btn_Notifications_LoadShockSensorTELUser4_checked = checked;
				break;
			}
			case 4:
			{
				btn_Notifications_LoadShockSensorTELUser5_checked = checked;
				break;
			}
		}
	}
	public boolean getBtnLoadShockSensorTELEnabled(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_LoadShockSensorTELUser1_enabled;
			}
			case 1:
			{
				return btn_Notifications_LoadShockSensorTELUser2_enabled;
			}
			case 2:
			{
				return btn_Notifications_LoadShockSensorTELUser3_enabled;
			}
			case 3:
			{
				return btn_Notifications_LoadShockSensorTELUser4_enabled;
			}
			case 4:
			{
				return btn_Notifications_LoadShockSensorTELUser5_enabled;
			}
			default:
			{
				return false;
			}
		}
	}
	public boolean getBtnLoadShockSensorTELChecked(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_LoadShockSensorTELUser1_checked;
			}
			case 1:
			{
				return btn_Notifications_LoadShockSensorTELUser2_checked;
			}
			case 2:
			{
				return btn_Notifications_LoadShockSensorTELUser3_checked;
			}
			case 3:
			{
				return btn_Notifications_LoadShockSensorTELUser4_checked;
			}
			case 4:
			{
				return btn_Notifications_LoadShockSensorTELUser5_checked;
			}
			default:
			{
				return false;
			}
		}
	}
	
	//Load Universal Limit Switch
	private boolean btn_Notifications_LoadUniversalLimitSwitchSMSUser1_enabled = false;
	private boolean btn_Notifications_LoadUniversalLimitSwitchSMSUser1_checked = false;
	private boolean btn_Notifications_LoadUniversalLimitSwitchSMSUser2_enabled = false;
	private boolean btn_Notifications_LoadUniversalLimitSwitchSMSUser2_checked = false;
	private boolean btn_Notifications_LoadUniversalLimitSwitchSMSUser3_enabled = false;
	private boolean btn_Notifications_LoadUniversalLimitSwitchSMSUser3_checked = false;
	private boolean btn_Notifications_LoadUniversalLimitSwitchSMSUser4_enabled = false;
	private boolean btn_Notifications_LoadUniversalLimitSwitchSMSUser4_checked = false;
	private boolean btn_Notifications_LoadUniversalLimitSwitchSMSUser5_enabled = false;
	private boolean btn_Notifications_LoadUniversalLimitSwitchSMSUser5_checked = false;
	public void setBtnLoadUniversalLimitSwitchSMSEnabled(int userId, boolean enabled) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_LoadUniversalLimitSwitchSMSUser1_enabled = enabled;
				break;
			}
			case 1:
			{
				btn_Notifications_LoadUniversalLimitSwitchSMSUser2_enabled = enabled;
				break;
			}
			case 2:
			{
				btn_Notifications_LoadUniversalLimitSwitchSMSUser3_enabled = enabled;
				break;
			}
			case 3:
			{
				btn_Notifications_LoadUniversalLimitSwitchSMSUser4_enabled = enabled;
				break;
			}
			case 4:
			{
				btn_Notifications_LoadUniversalLimitSwitchSMSUser5_enabled = enabled;
				break;
			}
		}
	}
	public void setBtnLoadUniversalLimitSwitchSMSChecked(int userId, boolean checked) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_LoadUniversalLimitSwitchSMSUser1_checked = checked;
				break;
			}
			case 1:
			{
				btn_Notifications_LoadUniversalLimitSwitchSMSUser2_checked = checked;
				break;
			}
			case 2:
			{
				btn_Notifications_LoadUniversalLimitSwitchSMSUser3_checked = checked;
				break;
			}
			case 3:
			{
				btn_Notifications_LoadUniversalLimitSwitchSMSUser4_checked = checked;
				break;
			}
			case 4:
			{
				btn_Notifications_LoadUniversalLimitSwitchSMSUser5_checked = checked;
				break;
			}
		}
	}
	public boolean getBtnLoadUniversalLimitSwitchSMSEnabled(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_LoadUniversalLimitSwitchSMSUser1_enabled;
			}
			case 1:
			{
				return btn_Notifications_LoadUniversalLimitSwitchSMSUser2_enabled;
			}
			case 2:
			{
				return btn_Notifications_LoadUniversalLimitSwitchSMSUser3_enabled;
			}
			case 3:
			{
				return btn_Notifications_LoadUniversalLimitSwitchSMSUser4_enabled;
			}
			case 4:
			{
				return btn_Notifications_LoadUniversalLimitSwitchSMSUser5_enabled;
			}
			default:
			{
				return false;
			}
		}
	}
	public boolean getBtnLoadUniversalLimitSwitchSMSChecked(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_LoadUniversalLimitSwitchSMSUser1_checked;
			}
			case 1:
			{
				return btn_Notifications_LoadUniversalLimitSwitchSMSUser2_checked;
			}
			case 2:
			{
				return btn_Notifications_LoadUniversalLimitSwitchSMSUser3_checked;
			}
			case 3:
			{
				return btn_Notifications_LoadUniversalLimitSwitchSMSUser4_checked;
			}
			case 4:
			{
				return btn_Notifications_LoadUniversalLimitSwitchSMSUser5_checked;
			}
			default:
			{
				return false;
			}
		}
	}
	
	private boolean btn_Notifications_LoadUniversalLimitSwitchTELUser1_enabled = false;
	private boolean btn_Notifications_LoadUniversalLimitSwitchTELUser1_checked = false;
	private boolean btn_Notifications_LoadUniversalLimitSwitchTELUser2_enabled = false;
	private boolean btn_Notifications_LoadUniversalLimitSwitchTELUser2_checked = false;
	private boolean btn_Notifications_LoadUniversalLimitSwitchTELUser3_enabled = false;
	private boolean btn_Notifications_LoadUniversalLimitSwitchTELUser3_checked = false;
	private boolean btn_Notifications_LoadUniversalLimitSwitchTELUser4_enabled = false;
	private boolean btn_Notifications_LoadUniversalLimitSwitchTELUser4_checked = false;
	private boolean btn_Notifications_LoadUniversalLimitSwitchTELUser5_enabled = false;
	private boolean btn_Notifications_LoadUniversalLimitSwitchTELUser5_checked = false;
	public void setBtnLoadUniversalLimitSwitchTELEnabled(int userId, boolean enabled) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_LoadUniversalLimitSwitchTELUser1_enabled = enabled;
				break;
			}
			case 1:
			{
				btn_Notifications_LoadUniversalLimitSwitchTELUser2_enabled = enabled;
				break;
			}
			case 2:
			{
				btn_Notifications_LoadUniversalLimitSwitchTELUser3_enabled = enabled;
				break;
			}
			case 3:
			{
				btn_Notifications_LoadUniversalLimitSwitchTELUser4_enabled = enabled;
				break;
			}
			case 4:
			{
				btn_Notifications_LoadUniversalLimitSwitchTELUser5_enabled = enabled;
				break;
			}
		}
	}
	public void setBtnLoadUniversalLimitSwitchTELChecked(int userId, boolean checked) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_LoadUniversalLimitSwitchTELUser1_checked = checked;
				break;
			}
			case 1:
			{
				btn_Notifications_LoadUniversalLimitSwitchTELUser2_checked = checked;
				break;
			}
			case 2:
			{
				btn_Notifications_LoadUniversalLimitSwitchTELUser3_checked = checked;
				break;
			}
			case 3:
			{
				btn_Notifications_LoadUniversalLimitSwitchTELUser4_checked = checked;
				break;
			}
			case 4:
			{
				btn_Notifications_LoadUniversalLimitSwitchTELUser5_checked = checked;
				break;
			}
		}
	}
	public boolean getBtnLoadUniversalLimitSwitchTELEnabled(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_LoadUniversalLimitSwitchTELUser1_enabled;
			}
			case 1:
			{
				return btn_Notifications_LoadUniversalLimitSwitchTELUser2_enabled;
			}
			case 2:
			{
				return btn_Notifications_LoadUniversalLimitSwitchTELUser3_enabled;
			}
			case 3:
			{
				return btn_Notifications_LoadUniversalLimitSwitchTELUser4_enabled;
			}
			case 4:
			{
				return btn_Notifications_LoadUniversalLimitSwitchTELUser5_enabled;
			}
			default:
			{
				return false;
			}
		}
	}
	public boolean getBtnLoadUniversalLimitSwitchTELChecked(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_LoadUniversalLimitSwitchTELUser1_checked;
			}
			case 1:
			{
				return btn_Notifications_LoadUniversalLimitSwitchTELUser2_checked;
			}
			case 2:
			{
				return btn_Notifications_LoadUniversalLimitSwitchTELUser3_checked;
			}
			case 3:
			{
				return btn_Notifications_LoadUniversalLimitSwitchTELUser4_checked;
			}
			case 4:
			{
				return btn_Notifications_LoadUniversalLimitSwitchTELUser5_checked;
			}
			default:
			{
				return false;
			}
		}
	}
	
	//Button Low Buttery
	private boolean btn_Notifications_LowBatterySMSUser1_enabled = false;
	private boolean btn_Notifications_LowBatterySMSUser1_checked = false;
	private boolean btn_Notifications_LowBatterySMSUser2_enabled = false;
	private boolean btn_Notifications_LowBatterySMSUser2_checked = false;
	private boolean btn_Notifications_LowBatterySMSUser3_enabled = false;
	private boolean btn_Notifications_LowBatterySMSUser3_checked = false;
	private boolean btn_Notifications_LowBatterySMSUser4_enabled = false;
	private boolean btn_Notifications_LowBatterySMSUser4_checked = false;
	private boolean btn_Notifications_LowBatterySMSUser5_enabled = false;
	private boolean btn_Notifications_LowBatterySMSUser5_checked = false;
	public void setBtnLowBatterySMSEnabled(int userId, boolean enabled) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_LowBatterySMSUser1_enabled = enabled;
				break;
			}
			case 1:
			{
				btn_Notifications_LowBatterySMSUser2_enabled = enabled;
				break;
			}
			case 2:
			{
				btn_Notifications_LowBatterySMSUser3_enabled = enabled;
				break;
			}
			case 3:
			{
				btn_Notifications_LowBatterySMSUser4_enabled = enabled;
				break;
			}
			case 4:
			{
				btn_Notifications_LowBatterySMSUser5_enabled = enabled;
				break;
			}
		}
	}
	public void setBtnLowBatterySMSChecked(int userId, boolean checked) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_LowBatterySMSUser1_checked = checked;
				break;
			}
			case 1:
			{
				btn_Notifications_LowBatterySMSUser2_checked = checked;
				break;
			}
			case 2:
			{
				btn_Notifications_LowBatterySMSUser3_checked = checked;
				break;
			}
			case 3:
			{
				btn_Notifications_LowBatterySMSUser4_checked = checked;
				break;
			}
			case 4:
			{
				btn_Notifications_LowBatterySMSUser5_checked = checked;
				break;
			}
		}
	}
	public boolean getBtnLowBatterySMSEnabled(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_LowBatterySMSUser1_enabled;
			}
			case 1:
			{
				return btn_Notifications_LowBatterySMSUser2_enabled;
			}
			case 2:
			{
				return btn_Notifications_LowBatterySMSUser3_enabled;
			}
			case 3:
			{
				return btn_Notifications_LowBatterySMSUser4_enabled;
			}
			case 4:
			{
				return btn_Notifications_LowBatterySMSUser5_enabled;
			}
			default:
			{
				return false;
			}
		}
	}
	public boolean getBtnLowBatterySMSChecked(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_LowBatterySMSUser1_checked;
			}
			case 1:
			{
				return btn_Notifications_LowBatterySMSUser2_checked;
			}
			case 2:
			{
				return btn_Notifications_LowBatterySMSUser3_checked;
			}
			case 3:
			{
				return btn_Notifications_LowBatterySMSUser4_checked;
			}
			case 4:
			{
				return btn_Notifications_LowBatterySMSUser5_checked;
			}
			default:
			{
				return false;
			}
		}
	}
	
	private boolean btn_Notifications_LowBatteryTELUser1_enabled = false;
	private boolean btn_Notifications_LowBatteryTELUser1_checked = false;
	private boolean btn_Notifications_LowBatteryTELUser2_enabled = false;
	private boolean btn_Notifications_LowBatteryTELUser2_checked = false;
	private boolean btn_Notifications_LowBatteryTELUser3_enabled = false;
	private boolean btn_Notifications_LowBatteryTELUser3_checked = false;
	private boolean btn_Notifications_LowBatteryTELUser4_enabled = false;
	private boolean btn_Notifications_LowBatteryTELUser4_checked = false;
	private boolean btn_Notifications_LowBatteryTELUser5_enabled = false;
	private boolean btn_Notifications_LowBatteryTELUser5_checked = false;
	public void setBtnLowBatteryTELEnabled(int userId, boolean enabled) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_LowBatteryTELUser1_enabled = enabled;
				break;
			}
			case 1:
			{
				btn_Notifications_LowBatteryTELUser2_enabled = enabled;
				break;
			}
			case 2:
			{
				btn_Notifications_LowBatteryTELUser3_enabled = enabled;
				break;
			}
			case 3:
			{
				btn_Notifications_LowBatteryTELUser4_enabled = enabled;
				break;
			}
			case 4:
			{
				btn_Notifications_LowBatteryTELUser5_enabled = enabled;
				break;
			}
		}
	}
	public void setBtnLowBatteryTELChecked(int userId, boolean checked) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_LowBatteryTELUser1_checked = checked;
				break;
			}
			case 1:
			{
				btn_Notifications_LowBatteryTELUser2_checked = checked;
				break;
			}
			case 2:
			{
				btn_Notifications_LowBatteryTELUser3_checked = checked;
				break;
			}
			case 3:
			{
				btn_Notifications_LowBatteryTELUser4_checked = checked;
				break;
			}
			case 4:
			{
				btn_Notifications_LowBatteryTELUser5_checked = checked;
				break;
			}
		}
	}
	public boolean getBtnLowBatteryTELEnabled(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_LowBatteryTELUser1_enabled;
			}
			case 1:
			{
				return btn_Notifications_LowBatteryTELUser2_enabled;
			}
			case 2:
			{
				return btn_Notifications_LowBatteryTELUser3_enabled;
			}
			case 3:
			{
				return btn_Notifications_LowBatteryTELUser4_enabled;
			}
			case 4:
			{
				return btn_Notifications_LowBatteryTELUser5_enabled;
			}
			default:
			{
				return false;
			}
		}
	}
	public boolean getBtnLowBatteryTELChecked(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_LowBatteryTELUser1_checked;
			}
			case 1:
			{
				return btn_Notifications_LowBatteryTELUser2_checked;
			}
			case 2:
			{
				return btn_Notifications_LowBatteryTELUser3_checked;
			}
			case 3:
			{
				return btn_Notifications_LowBatteryTELUser4_checked;
			}
			case 4:
			{
				return btn_Notifications_LowBatteryTELUser5_checked;
			}
			default:
			{
				return false;
			}
		}
	}
	
	//GSM
	private boolean btn_Notifications_GSM_SMSUser1_enabled = false;
	private boolean btn_Notifications_GSM_SMSUser1_checked = false;
	private boolean btn_Notifications_GSM_SMSUser2_enabled = false;
	private boolean btn_Notifications_GSM_SMSUser2_checked = false;
	private boolean btn_Notifications_GSM_SMSUser3_enabled = false;
	private boolean btn_Notifications_GSM_SMSUser3_checked = false;
	private boolean btn_Notifications_GSM_SMSUser4_enabled = false;
	private boolean btn_Notifications_GSM_SMSUser4_checked = false;
	private boolean btn_Notifications_GSM_SMSUser5_enabled = false;
	private boolean btn_Notifications_GSM_SMSUser5_checked = false;
	public void setBtnGSMSMSEnabled(int userId, boolean enabled) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_GSM_SMSUser1_enabled = enabled;
				break;
			}
			case 1:
			{
				btn_Notifications_GSM_SMSUser2_enabled = enabled;
				break;
			}
			case 2:
			{
				btn_Notifications_GSM_SMSUser3_enabled = enabled;
				break;
			}
			case 3:
			{
				btn_Notifications_GSM_SMSUser4_enabled = enabled;
				break;
			}
			case 4:
			{
				btn_Notifications_GSM_SMSUser5_enabled = enabled;
				break;
			}
		}
	}
	public void setBtnGSMSMSChecked(int userId, boolean checked) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_GSM_SMSUser1_checked = checked;
				break;
			}
			case 1:
			{
				btn_Notifications_GSM_SMSUser2_checked = checked;
				break;
			}
			case 2:
			{
				btn_Notifications_GSM_SMSUser3_checked = checked;
				break;
			}
			case 3:
			{
				btn_Notifications_GSM_SMSUser4_checked = checked;
				break;
			}
			case 4:
			{
				btn_Notifications_GSM_SMSUser5_checked = checked;
				break;
			}
		}
	}
	public boolean getBtnGSMSMSEnabled(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_GSM_SMSUser1_enabled;
			}
			case 1:
			{
				return btn_Notifications_GSM_SMSUser2_enabled;
			}
			case 2:
			{
				return btn_Notifications_GSM_SMSUser3_enabled;
			}
			case 3:
			{
				return btn_Notifications_GSM_SMSUser4_enabled;
			}
			case 4:
			{
				return btn_Notifications_GSM_SMSUser5_enabled;
			}
			default:
			{
				return false;
			}
		}
	}
	public boolean getBtnGSMSMSChecked(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_GSM_SMSUser1_checked;
			}
			case 1:
			{
				return btn_Notifications_GSM_SMSUser2_checked;
			}
			case 2:
			{
				return btn_Notifications_GSM_SMSUser3_checked;
			}
			case 3:
			{
				return btn_Notifications_GSM_SMSUser4_checked;
			}
			case 4:
			{
				return btn_Notifications_GSM_SMSUser5_checked;
			}
			default:
			{
				return false;
			}
		}
	}
	
	private boolean btn_Notifications_GSM_TELUser1_enabled = false;
	private boolean btn_Notifications_GSM_TELUser1_checked = false;
	private boolean btn_Notifications_GSM_TELUser2_enabled = false;
	private boolean btn_Notifications_GSM_TELUser2_checked = false;
	private boolean btn_Notifications_GSM_TELUser3_enabled = false;
	private boolean btn_Notifications_GSM_TELUser3_checked = false;
	private boolean btn_Notifications_GSM_TELUser4_enabled = false;
	private boolean btn_Notifications_GSM_TELUser4_checked = false;
	private boolean btn_Notifications_GSM_TELUser5_enabled = false;
	private boolean btn_Notifications_GSM_TELUser5_checked = false;
	public void setBtnGTELMSEnabled(int userId, boolean enabled) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_GSM_TELUser1_enabled = enabled;
				break;
			}
			case 1:
			{
				btn_Notifications_GSM_TELUser2_enabled = enabled;
				break;
			}
			case 2:
			{
				btn_Notifications_GSM_TELUser3_enabled = enabled;
				break;
			}
			case 3:
			{
				btn_Notifications_GSM_TELUser4_enabled = enabled;
				break;
			}
			case 4:
			{
				btn_Notifications_GSM_TELUser5_enabled = enabled;
				break;
			}
		}
	}
	public void setBtnGTELMSChecked(int userId, boolean checked) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_GSM_TELUser1_checked = checked;
				break;
			}
			case 1:
			{
				btn_Notifications_GSM_TELUser2_checked = checked;
				break;
			}
			case 2:
			{
				btn_Notifications_GSM_TELUser3_checked = checked;
				break;
			}
			case 3:
			{
				btn_Notifications_GSM_TELUser4_checked = checked;
				break;
			}
			case 4:
			{
				btn_Notifications_GSM_TELUser5_checked = checked;
				break;
			}
		}
	}
	public boolean getBtnGTELMSEnabled(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_GSM_TELUser1_enabled;
			}
			case 1:
			{
				return btn_Notifications_GSM_TELUser2_enabled;
			}
			case 2:
			{
				return btn_Notifications_GSM_TELUser3_enabled;
			}
			case 3:
			{
				return btn_Notifications_GSM_TELUser4_enabled;
			}
			case 4:
			{
				return btn_Notifications_GSM_TELUser5_enabled;
			}
			default:
			{
				return false;
			}
		}
	}
	public boolean getBtnGTELMSChecked(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_GSM_TELUser1_checked;
			}
			case 1:
			{
				return btn_Notifications_GSM_TELUser2_checked;
			}
			case 2:
			{
				return btn_Notifications_GSM_TELUser3_checked;
			}
			case 3:
			{
				return btn_Notifications_GSM_TELUser4_checked;
			}
			case 4:
			{
				return btn_Notifications_GSM_TELUser5_checked;
			}
			default:
			{
				return false;
			}
		}
	}
	
	//Button System Disarm
	private boolean btn_Notifications_SystemDisarmSMSUser1_enabled = false;
	private boolean btn_Notifications_SystemDisarmSMSUser1_checked = false;
	private boolean btn_Notifications_SystemDisarmSMSUser2_enabled = false;
	private boolean btn_Notifications_SystemDisarmSMSUser2_checked = false;
	private boolean btn_Notifications_SystemDisarmSMSUser3_enabled = false;
	private boolean btn_Notifications_SystemDisarmSMSUser3_checked = false;
	private boolean btn_Notifications_SystemDisarmSMSUser4_enabled = false;
	private boolean btn_Notifications_SystemDisarmSMSUser4_checked = false;
	private boolean btn_Notifications_SystemDisarmSMSUser5_enabled = false;
	private boolean btn_Notifications_SystemDisarmSMSUser5_checked = false;
	public void setBtnSystemDisarmSMSEnabled(int userId, boolean enabled) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_SystemDisarmSMSUser1_enabled = enabled;
				break;
			}
			case 1:
			{
				btn_Notifications_SystemDisarmSMSUser2_enabled = enabled;
				break;
			}
			case 2:
			{
				btn_Notifications_SystemDisarmSMSUser3_enabled = enabled;
				break;
			}
			case 3:
			{
				btn_Notifications_SystemDisarmSMSUser4_enabled = enabled;
				break;
			}
			case 4:
			{
				btn_Notifications_SystemDisarmSMSUser5_enabled = enabled;
				break;
			}
		}
	}
	public void setBtnSystemDisarmSMSChecked(int userId, boolean checked) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_SystemDisarmSMSUser1_checked = checked;
				break;
			}
			case 1:
			{
				btn_Notifications_SystemDisarmSMSUser2_checked = checked;
				break;
			}
			case 2:
			{
				btn_Notifications_SystemDisarmSMSUser3_checked = checked;
				break;
			}
			case 3:
			{
				btn_Notifications_SystemDisarmSMSUser4_checked = checked;
				break;
			}
			case 4:
			{
				btn_Notifications_SystemDisarmSMSUser5_checked = checked;
				break;
			}
		}
	}
	public boolean getBtnSystemDisarmSMSEnabled(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_SystemDisarmSMSUser1_enabled;
			}
			case 1:
			{
				return btn_Notifications_SystemDisarmSMSUser2_enabled;
			}
			case 2:
			{
				return btn_Notifications_SystemDisarmSMSUser3_enabled;
			}
			case 3:
			{
				return btn_Notifications_SystemDisarmSMSUser4_enabled;
			}
			case 4:
			{
				return btn_Notifications_SystemDisarmSMSUser5_enabled;
			}
			default:
			{
				return false;
			}
		}
	}
	public boolean getBtnSystemDisarmSMSChecked(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_SystemDisarmSMSUser1_checked;
			}
			case 1:
			{
				return btn_Notifications_SystemDisarmSMSUser2_checked;
			}
			case 2:
			{
				return btn_Notifications_SystemDisarmSMSUser3_checked;
			}
			case 3:
			{
				return btn_Notifications_SystemDisarmSMSUser4_checked;
			}
			case 4:
			{
				return btn_Notifications_SystemDisarmSMSUser5_checked;
			}
			default:
			{
				return false;
			}
		}
	}
	
	private boolean btn_Notifications_SystemDisarmTELUser1_enabled = false;
	private boolean btn_Notifications_SystemDisarmTELUser1_checked = false;
	private boolean btn_Notifications_SystemDisarmTELUser2_enabled = false;
	private boolean btn_Notifications_SystemDisarmTELUser2_checked = false;
	private boolean btn_Notifications_SystemDisarmTELUser3_enabled = false;
	private boolean btn_Notifications_SystemDisarmTELUser3_checked = false;
	private boolean btn_Notifications_SystemDisarmTELUser4_enabled = false;
	private boolean btn_Notifications_SystemDisarmTELUser4_checked = false;
	private boolean btn_Notifications_SystemDisarmTELUser5_enabled = false;
	private boolean btn_Notifications_SystemDisarmTELUser5_checked = false;
	public void setBtnSystemDisarmTELEnabled(int userId, boolean enabled) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_SystemDisarmTELUser1_enabled = enabled;
				break;
			}
			case 1:
			{
				btn_Notifications_SystemDisarmTELUser2_enabled = enabled;
				break;
			}
			case 2:
			{
				btn_Notifications_SystemDisarmTELUser3_enabled = enabled;
				break;
			}
			case 3:
			{
				btn_Notifications_SystemDisarmTELUser4_enabled = enabled;
				break;
			}
			case 4:
			{
				btn_Notifications_SystemDisarmTELUser5_enabled = enabled;
				break;
			}
		}
	}
	public void setBtnSystemDisarmTELChecked(int userId, boolean checked) 
	{
		switch(userId)
		{
			case 0:
			{
				btn_Notifications_SystemDisarmTELUser1_checked = checked;
				break;
			}
			case 1:
			{
				btn_Notifications_SystemDisarmTELUser2_checked = checked;
				break;
			}
			case 2:
			{
				btn_Notifications_SystemDisarmTELUser3_checked = checked;
				break;
			}
			case 3:
			{
				btn_Notifications_SystemDisarmTELUser4_checked = checked;
				break;
			}
			case 4:
			{
				btn_Notifications_SystemDisarmTELUser5_checked = checked;
				break;
			}
		}
	}
	public boolean getBtnSystemDisarmTELEnabled(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_SystemDisarmTELUser1_enabled;
			}
			case 1:
			{
				return btn_Notifications_SystemDisarmTELUser2_enabled;
			}
			case 2:
			{
				return btn_Notifications_SystemDisarmTELUser3_enabled;
			}
			case 3:
			{
				return btn_Notifications_SystemDisarmTELUser4_enabled;
			}
			case 4:
			{
				return btn_Notifications_SystemDisarmTELUser5_enabled;
			}
			default:
			{
				return false;
			}
		}
	}
	public boolean getBtnSystemDisarmTELChecked(int userId) 
	{
		switch(userId)
		{
			case 0:
			{
				return btn_Notifications_SystemDisarmTELUser1_checked;
			}
			case 1:
			{
				return btn_Notifications_SystemDisarmTELUser2_checked;
			}
			case 2:
			{
				return btn_Notifications_SystemDisarmTELUser3_checked;
			}
			case 3:
			{
				return btn_Notifications_SystemDisarmTELUser4_checked;
			}
			case 4:
			{
				return btn_Notifications_SystemDisarmTELUser5_checked;
			}
			default:
			{
				return false;
			}
		}
	}
	
	//Button System Arming
		private boolean btn_Notifications_SystemArmingSMSUser1_enabled = false;
		private boolean btn_Notifications_SystemArmingSMSUser1_checked = false;
		private boolean btn_Notifications_SystemArmingSMSUser2_enabled = false;
		private boolean btn_Notifications_SystemArmingSMSUser2_checked = false;
		private boolean btn_Notifications_SystemArmingSMSUser3_enabled = false;
		private boolean btn_Notifications_SystemArmingSMSUser3_checked = false;
		private boolean btn_Notifications_SystemArmingSMSUser4_enabled = false;
		private boolean btn_Notifications_SystemArmingSMSUser4_checked = false;
		private boolean btn_Notifications_SystemArmingSMSUser5_enabled = false;
		private boolean btn_Notifications_SystemArmingSMSUser5_checked = false;
		public void setBtnSystemArmingSMSEnabled(int userId, boolean enabled) 
		{
			switch(userId)
			{
				case 0:
				{
					btn_Notifications_SystemArmingSMSUser1_enabled = enabled;
					break;
				}
				case 1:
				{
					btn_Notifications_SystemArmingSMSUser2_enabled = enabled;
					break;
				}
				case 2:
				{
					btn_Notifications_SystemArmingSMSUser3_enabled = enabled;
					break;
				}
				case 3:
				{
					btn_Notifications_SystemArmingSMSUser4_enabled = enabled;
					break;
				}
				case 4:
				{
					btn_Notifications_SystemArmingSMSUser5_enabled = enabled;
					break;
				}
			}
		}
		public void setBtnSystemArmingSMSChecked(int userId, boolean checked) 
		{
			switch(userId)
			{
				case 0:
				{
					btn_Notifications_SystemArmingSMSUser1_checked = checked;
					break;
				}
				case 1:
				{
					btn_Notifications_SystemArmingSMSUser2_checked = checked;
					break;
				}
				case 2:
				{
					btn_Notifications_SystemArmingSMSUser3_checked = checked;
					break;
				}
				case 3:
				{
					btn_Notifications_SystemArmingSMSUser4_checked = checked;
					break;
				}
				case 4:
				{
					btn_Notifications_SystemArmingSMSUser5_checked = checked;
					break;
				}
			}
		}
		public boolean getBtnSystemArmingSMSEnabled(int userId) 
		{
			switch(userId)
			{
				case 0:
				{
					return btn_Notifications_SystemArmingSMSUser1_enabled;
				}
				case 1:
				{
					return btn_Notifications_SystemArmingSMSUser2_enabled;
				}
				case 2:
				{
					return btn_Notifications_SystemArmingSMSUser3_enabled;
				}
				case 3:
				{
					return btn_Notifications_SystemArmingSMSUser4_enabled;
				}
				case 4:
				{
					return btn_Notifications_SystemArmingSMSUser5_enabled;
				}
				default:
				{
					return false;
				}
			}
		}
		public boolean getBtnSystemArmingSMSChecked(int userId) 
		{
			switch(userId)
			{
				case 0:
				{
					return btn_Notifications_SystemArmingSMSUser1_checked;
				}
				case 1:
				{
					return btn_Notifications_SystemArmingSMSUser2_checked;
				}
				case 2:
				{
					return btn_Notifications_SystemArmingSMSUser3_checked;
				}
				case 3:
				{
					return btn_Notifications_SystemArmingSMSUser4_checked;
				}
				case 4:
				{
					return btn_Notifications_SystemArmingSMSUser5_checked;
				}
				default:
				{
					return false;
				}
			}
		}
		
		private boolean btn_Notifications_SystemArmingTELUser1_enabled = false;
		private boolean btn_Notifications_SystemArmingTELUser1_checked = false;
		private boolean btn_Notifications_SystemArmingTELUser2_enabled = false;
		private boolean btn_Notifications_SystemArmingTELUser2_checked = false;
		private boolean btn_Notifications_SystemArmingTELUser3_enabled = false;
		private boolean btn_Notifications_SystemArmingTELUser3_checked = false;
		private boolean btn_Notifications_SystemArmingTELUser4_enabled = false;
		private boolean btn_Notifications_SystemArmingTELUser4_checked = false;
		private boolean btn_Notifications_SystemArmingTELUser5_enabled = false;
		private boolean btn_Notifications_SystemArmingTELUser5_checked = false;
		public void setBtnSystemArmingTELEnabled(int userId, boolean enabled) 
		{
			switch(userId)
			{
				case 0:
				{
					btn_Notifications_SystemArmingTELUser1_enabled = enabled;
					break;
				}
				case 1:
				{
					btn_Notifications_SystemArmingTELUser2_enabled = enabled;
					break;
				}
				case 2:
				{
					btn_Notifications_SystemArmingTELUser3_enabled = enabled;
					break;
				}
				case 3:
				{
					btn_Notifications_SystemArmingTELUser4_enabled = enabled;
					break;
				}
				case 4:
				{
					btn_Notifications_SystemArmingTELUser5_enabled = enabled;
					break;
				}
			}
		}
		public void setBtnSystemArmingTELChecked(int userId, boolean checked) 
		{
			switch(userId)
			{
				case 0:
				{
					btn_Notifications_SystemArmingTELUser1_checked = checked;
					break;
				}
				case 1:
				{
					btn_Notifications_SystemArmingTELUser2_checked = checked;
					break;
				}
				case 2:
				{
					btn_Notifications_SystemArmingTELUser3_checked = checked;
					break;
				}
				case 3:
				{
					btn_Notifications_SystemArmingTELUser4_checked = checked;
					break;
				}
				case 4:
				{
					btn_Notifications_SystemArmingTELUser5_checked = checked;
					break;
				}
			}
		}
		public boolean getBtnSystemArmingTELEnabled(int userId) 
		{
			switch(userId)
			{
				case 0:
				{
					return btn_Notifications_SystemArmingTELUser1_enabled;
				}
				case 1:
				{
					return btn_Notifications_SystemArmingTELUser2_enabled;
				}
				case 2:
				{
					return btn_Notifications_SystemArmingTELUser3_enabled;
				}
				case 3:
				{
					return btn_Notifications_SystemArmingTELUser4_enabled;
				}
				case 4:
				{
					return btn_Notifications_SystemArmingTELUser5_enabled;
				}
				default:
				{
					return false;
				}
			}
		}
		public boolean getBtnSystemArmingTELChecked(int userId) 
		{
			switch(userId)
			{
				case 0:
				{
					return btn_Notifications_SystemArmingTELUser1_checked;
				}
				case 1:
				{
					return btn_Notifications_SystemArmingTELUser2_checked;
				}
				case 2:
				{
					return btn_Notifications_SystemArmingTELUser3_checked;
				}
				case 3:
				{
					return btn_Notifications_SystemArmingTELUser4_checked;
				}
				case 4:
				{
					return btn_Notifications_SystemArmingTELUser5_checked;
				}
				default:
				{
					return false;
				}
			}
		}
}
