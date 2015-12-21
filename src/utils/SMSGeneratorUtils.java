package utils;

public class SMSGeneratorUtils
{
	public static final int NO_ERROR = 0;
	public static final int ERR_PHONE_LEN = 1;
	public static final int ERR_COUNTRY_NUM = 2;
	public int isPhoneNumberInCorrectFormat(String phoneNumber)
	{
		if(phoneNumber.length() != 13)
		{
			return ERR_PHONE_LEN;
		}
		else if(!phoneNumber.substring(0, 4).equals("+380"))
		{
			return ERR_COUNTRY_NUM;
		}
		
		return NO_ERROR;
	}
}
