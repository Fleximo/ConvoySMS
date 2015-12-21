package com.olegbrovko.smsgeneratorconvoy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Locale;

import stateholders.TextSMSStateHolder;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.olegbrovko.smsgeneratorconvoy.R;

public class SaveSettingPopup extends Activity implements OnClickListener
{
	final String LOG_TAG = "myLogs";
	
	public static final String FOLDER_WITH_SETTINGS_NAME = "ConvoySMSSettings";
	public static final String LANGUAGE = "USER_LANGUAGE";
	public static final String PARENT_ACTIVITY_NAME = "PARENT_ACTIVITY_NAME";
	String nameOfParentActivity = null;
	
	//private static SharedPreferences m_sharedPreferences = MainActivity.m_sharedPreferences;
	EditText et_SaveSettings_EnterNameForNewSettings = null;
	LinearLayout ll_SaveSettings_SavedSettings = null;
	Button btn_SaveSettings_Save = null;
	Button btn_SaveSettings_Cancel = null;
	//Text SMS state holder
	TextSMSStateHolder m_stateholder = MainActivity.stateholderTextSMS;
	TextView tv_SavePopup_Header = null;
	TextView tv_SaveSettings_EnterNameForNewSettings = null;
	
//QUERY CONFIGURATIONS
	//1.Query configurations
	public static String key1 = "cb_QueryConfigurations_QueryConfigurations_enabled";
	public static String key2 = "cb_QueryConfigurations_QueryConfigurations_checked";
	public static String key3 = "et_QueryConfigurations_PhoneNumber_enabled";
	public static String key4 = "et_QueryConfigurations_PhoneNumber_value";
	//2.Hardware configuration system
	public static String key5 = "cb_QueryConfigurations_HardwareConfigSyst_enabled";
	public static String key6 = "cb_QueryConfigurations_HardwareConfigSyst_checked";
	//3.User system settings
	public static String key7 = "cb_QueryConfigurations_UserSystSettings_enabled";
	public static String key8 = "cb_QueryConfigurations_UserSystSettings_checked";
//USER SETTINGS
	//0) CURRENT PIN
	public static String key575 = "cb_UserSettings_CurrentPIN_enabled";
	public static String key576 = "cb_UserSettings_CurrentPIN_checked";
	public static String key577 = "et_UserSettings_CurrentPIN_enabled";
	public static String key578 = "et_UserSettings_CurrentPIN_value";
	//1) NEW PIN
	public static String key9 = "cb_PIN_enabled";
	public static String key10 = "cb_PIN_checked";
	public static String key11 = "et_PIN_enabled";
	public static String key12 = "et_PIN_value";
	//2) BALANCE CHECKING NUMBER
	public static String key13 = "cb_BalanceChecking_enabled";
	public static String key14 = "cb_BalanceChecking_checked";
	public static String key15 = "spinBalanceChecking_enabled";
	public static String key16 = "spinBalanceChecking_value";
	public static String key17 = "et_UserSettings_BalanceCheckingAnother_enabled";
	public static String key18 = "et_UserSettings_BalanceCheckingAnother_value";
	//3) BALANCE AUTO-CHECKING
	public static String key19 = "cbAccBalance_enabled";
	public static String key20 = "cbAccBalance_checked";
	public static String key21 = "etNumSumPosition_enabled";
	public static String key22 = "etNumSumPosition_value";
	public static String key23 = "etCriticalBalance_enabled";
	public static String key24 = "etCriticalBalance_value";
	public static String key25 = "etBalanceCheckingFrequency_enabled";
	public static String key26 = "etBalanceCheckingFrequency_value";
	//4) NUMBER OF CALL ATTEMPTS
	public static String key27 = "cbNumberCallAttempts_enabled";
	public static String key28 = "cbNumberCallAttempts_checked";
	public static String key29 = "etNumberCallAttempts_enabled";
	public static String key30 = "etNumberCallAttempts_value";
	//REARM
	public static String key601 = "cb_UserSettings_Rearm_enabled";
	public static String key602 = "cb_UserSettings_Rearm_checked";
	public static String key603 = "spin_UserSettings_Rearm_enabled";
	public static String key604 = "spin_UserSettings_Rearm_value";
	//5) SIREN
	public static String key31 = "cbSiren_enabled";
	public static String key32 = "cbSiren_checked";
	public static String key33 = "spinSystemSetUnset_enabled";
	public static String key34 = "spinSystemSetUnset_value";
	public static String key35 = "spinAlarmMode_enabled";
	public static String key36 = "spinAlarmMode_value";
	//7) ALL SYSTEM SENSORS
	public static String key37 = "cb_UserSettings_AllSensorsDisabled_enabled";
	public static String key38 = "cb_UserSettings_AllSensorsDisabled_checked";
	public static String key39 = "spinTiltSensor_enabled";
	public static String key40 = "spinTiltSensor_value";
	//2) ACCESS POINT NAME MOB OPER
	public static String key41 = "cb_AccessPointNameMobOper_enabled";
	public static String key42 = "cb_AccessPointNameMobOper_checked";
	public static String key43 = "spinAccessPointNameMobOper_enabled";
	public static String key44 = "spinAccessPointNameMobOper_value";
	public static String key45 = "et_UserSettings_AccessPointNameMobOper_enabled";
	public static String key46 = "et_UserSettings_AccessPointNameMobOper_value";
	//4) SENSOR IN SECURITY MODE
	public static String key47 = "cbSensInSecurityMode_enabled";
	public static String key48 = "cbSensInSecurityMode_checked";
	public static String key49 = "spinSensInSecurityMode_enabled";
	public static String key50 = "spinSensInSecurityMode_value";
	//5) SHOCK SENSOR SETTINGS
	public static String key51 = "cbShockSensSettings_enabled";
	public static String key52 = "cbShockSensSettings_checked";
	public static String key53 = "cbWarningZoneDisabled_enabled";
	public static String key54 = "cbWarningZoneDisabled_checked";
	public static String key55 = "sbWarningTrashold_enabled";
	public static String key56 = "sbWarningTrashold_value";
	public static String key57 = "etWarningTrashold_enabled";
	public static String key58 = "etWarningTrashold_value";
	public static String key59 = "cbAlarmZoneDisabled_enabled";
	public static String key60 = "cbAlarmZoneDisabled_checked";
	public static String key61 = "sbAlarmTrashold_enabled";
	public static String key62 = "sbAlarmTrashold_value";
	public static String key63 = "etAlarmTrashold_enabled";
	public static String key64 = "etAlarmTrashold_value";
	//6) TILT SENSOR
	public static String key65 = "cbTiltSensor_enabled";
	public static String key66 = "cbTiltSensor_checked";
	public static String key67 = "cb_UserSettings_TiltSensorDisabled_enabled";
	public static String key68 = "cb_UserSettings_TiltSensorDisabled_checked";
	public static String key69 = "sbTiltSensor_enabled";
	public static String key70 = "sbTiltSensor_value";
	public static String key71 = "etTiltSensor_enabled";
	public static String key72 = "etTiltSensor_value";
	public static String key73 = "cbTiltSensorInArmMode_enabled";
	public static String key74 = "cbTiltSensorInArmMode_value";
	//7) MONITORING SETTINGS
	public static String key75 = "cbMonitoringSettings_enabled";
	public static String key76 = "cbMonitoringSettings_checked";
	public static String key77 = "spinMonitoringSettings_enabled";
	public static String key78 = "spinMonitoringSettings_value";
	//8) DISARMING SYSTEM
	public static String key79 = "cbDisarmingSystem_enabled";
	public static String key80 = "cbDisarmingSystem_checked";
	public static String key81 = "spinDisarmingSystem_enabled";
	public static String key82 = "spinDisarmingSystem_value";
	//9) MICROPHONE
	public static String key83 = "cbPhoneSensitivity_enabled";
	public static String key84 = "cbPhoneSensitivity_checked";
	public static String key85 = "cbPhoneSensitivityDisabled_enabled";
	public static String key86 = "cbPhoneSensitivityDisabled_checked";
	public static String key87 = "sbPhoneSensitivity_enabled";
	public static String key88 = "sbPhoneSensitivity_value";
	public static String key89 = "etPhoneSensitivity_enabled";
	public static String key90 = "etPhoneSensitivity_value";
	//10) SPEAKER
	public static String key91 = "cbSpeakerVolume_enabled";
	public static String key92 = "cbSpeakerVolume_checked";
	public static String key93 = "cbSpeakerVolumeDisabled_enabled";
	public static String key94 = "cbSpeakerVolumeDisabled_checked";
	public static String key95 = "sbSpeakerVolume_enabled";
	public static String key96 = "sbSpeakerVolume_value";
	public static String key97 = "etSpeakerVolume_enabled";
	public static String key98 = "etSpeakerVolume_value";
//INPUTS
	//IN1
	public static String key99 = "cb_Inputs_IN1_enabled";
	public static String key100 = "cb_Inputs_IN1_checked";
	public static String key101 = "spin_Inputs_InputFunctionsIN1_enabled";
	public static String key102 = "spin_Inputs_InputFunctionsIN1_value";
	public static String key103 = "spin_Inputs_PolarityIN1_enabled";
	public static String key104 = "spin_Inputs_PolarityIN1_value";
	public static String key105 = "spin_Inputs_InputIsActiveIN1_enabled";
	public static String key106 = "spin_Inputs_InputIsActiveIN1_value";
	public static String key107 = "et_Inputs_MinClosingTimeIN1_enabled";
	public static String key108 = "et_Inputs_MinClosingTimeIN1_value";
	public static String key109 = "et_Inputs_MinOpeningTimeIN1_enabled";
	public static String key110 = "et_Inputs_MinOpeningTimeIN1_value";
	//IN2
	public static String key111 = "cb_Inputs_IN2_enabled";
	public static String key112 = "cb_Inputs_IN2_checked";
	public static String key113 = "spin_Inputs_InputFunctionsIN2_enabled";
	public static String key114 = "spin_Inputs_InputFunctionsIN2_value";
	public static String key115 = "spin_Inputs_PolarityIN2_enabled";
	public static String key116 = "spin_Inputs_PolarityIN2_value";
	public static String key117 = "spin_Inputs_InputIsActiveIN2_enabled";
	public static String key118 = "spin_Inputs_InputIsActiveIN2_value";
	public static String key119 = "et_Inputs_MinClosingTimeIN2_enabled";
	public static String key120 = "et_Inputs_MinClosingTimeIN2_value";
	public static String key121 = "et_Inputs_MinOpeningTimeIN2_enabled";
	public static String key122 = "et_Inputs_MinOpeningTimeIN2_value";
	//IN3
	public static String key123 = "cb_Inputs_IN3_enabled";
	public static String key124 = "cb_Inputs_IN3_checked";
	public static String key125 = "spin_Inputs_InputFunctionsIN3_enabled";
	public static String key126 = "spin_Inputs_InputFunctionsIN3_value";
	public static String key127 = "spin_Inputs_PolarityIN3_enabled";
	public static String key128 = "spin_Inputs_PolarityIN3_value";
	public static String key129 = "spin_Inputs_InputIsActiveIN3_enabled";
	public static String key130 = "spin_Inputs_InputIsActiveIN3_value";
	public static String key131 = "et_Inputs_MinClosingTimeIN3_enabled";
	public static String key132 = "et_Inputs_MinClosingTimeIN3_value";
	public static String key133 = "et_Inputs_MinOpeningTimeIN3_enabled";
	public static String key134 = "et_Inputs_MinOpeningTimeIN3_value";
	//IN4
	public static String key135 = "cb_Inputs_IN4_enabled";
	public static String key136 = "cb_Inputs_IN4_checked";
	public static String key137 = "spin_Inputs_InputFunctionsIN4_enabled";
	public static String key138 = "spin_Inputs_InputFunctionsIN4_value";
	public static String key139 = "spin_Inputs_PolarityIN4_enabled";
	public static String key140 = "spin_Inputs_PolarityIN4_value";
	public static String key141 = "spin_Inputs_InputIsActiveIN4_enabled";
	public static String key142 = "spin_Inputs_InputIsActiveIN4_value";
	public static String key143 = "et_Inputs_MinClosingTimeIN4_enabled";
	public static String key144 = "et_Inputs_MinClosingTimeIN4_value";
	public static String key145 = "et_Inputs_MinOpeningTimeIN4_enabled";
	public static String key146 = "et_Inputs_MinOpeningTimeIN4_value";
	//IN5
	public static String key147 = "cb_Inputs_IN5_enabled";
	public static String key148 = "cb_Inputs_IN5_checked";
	public static String key149 = "spin_Inputs_InputFunctionsIN5_enabled";
	public static String key150 = "spin_Inputs_InputFunctionsIN5_value";
	public static String key151 = "spin_Inputs_PolarityIN5_enabled";
	public static String key152 = "spin_Inputs_PolarityIN5_value";
	public static String key153 = "spin_Inputs_InputIsActiveIN5_enabled";
	public static String key154 = "spin_Inputs_InputIsActiveIN5_value";
	public static String key155 = "et_Inputs_MinClosingTimeIN5_enabled";
	public static String key156 = "et_Inputs_MinClosingTimeIN5_value";
	public static String key157 = "et_Inputs_MinOpeningTimeIN5_enabled";
	public static String key158 = "et_Inputs_MinOpeningTimeIN5_value";
	//IN6
	public static String key159 = "cb_Inputs_IN6_enabled";
	public static String key160 = "cb_Inputs_IN6_checked";
	public static String key161 = "spin_Inputs_InputFunctionsIN6_enabled";
	public static String key162 = "spin_Inputs_InputFunctionsIN6_value";
	public static String key163 = "spin_Inputs_PolarityIN6_enabled";
	public static String key164 = "spin_Inputs_PolarityIN6_value";
	public static String key165 = "spin_Inputs_InputIsActiveIN6_enabled";
	public static String key166 = "spin_Inputs_InputIsActiveIN6_value";
	public static String key167 = "et_Inputs_MinClosingTimeIN6_enabled";
	public static String key168 = "et_Inputs_MinClosingTimeIN6_value";
	public static String key169 = "et_Inputs_MinOpeningTimeIN6_enabled";
	public static String key170 = "et_Inputs_MinOpeningTimeIN6_value";
	//IN7
	public static String key171 = "cb_Inputs_IN7_enabled";
	public static String key172 = "cb_Inputs_IN7_checked";
	public static String key173 = "spin_Inputs_InputFunctionsIN7_enabled";
	public static String key174 = "spin_Inputs_InputFunctionsIN7_value";
	public static String key175 = "spin_Inputs_PolarityIN7_enabled";
	public static String key176 = "spin_Inputs_PolarityIN7_value";
	public static String key177 = "spin_Inputs_InputIsActiveIN7_enabled";
	public static String key178 = "spin_Inputs_InputIsActiveIN7_value";
	public static String key179 = "et_Inputs_MinClosingTimeIN7_enabled";
	public static String key180 = "et_Inputs_MinClosingTimeIN7_value";
	public static String key181 = "et_Inputs_MinOpeningTimeIN7_enabled";
	public static String key182 = "et_Inputs_MinOpeningTimeIN7_value";
	//IN8
	public static String key183 = "cb_Inputs_IN8_enabled";
	public static String key184 = "cb_Inputs_IN8_checked";
	public static String key185 = "spin_Inputs_InputFunctionsIN8_enabled";
	public static String key186 = "spin_Inputs_InputFunctionsIN8_value";
	public static String key187 = "spin_Inputs_PolarityIN8_enabled";
	public static String key188 = "spin_Inputs_PolarityIN8_value";
	public static String key189 = "spin_Inputs_InputIsActiveIN8_enabled";
	public static String key190 = "spin_Inputs_InputIsActiveIN8_value";
	public static String key191 = "et_Inputs_MinClosingTimeIN8_enabled";
	public static String key192 = "et_Inputs_MinClosingTimeIN8_value";
	public static String key193 = "et_Inputs_MinOpeningTimeIN8_enabled";
	public static String key194 = "et_Inputs_MinOpeningTimeIN8_value";
//OUTPUTS
	//OUT1
	public static String key195 = "cb_Outputs_OUT1_enabled";
	public static String key196 = "cb_Outputs_OUT1_checked";
	public static String key197 = "spin_Outputs_OutputFunctionsOUT1_enabled";
	public static String key198 = "spin_Outputs_OutputFunctionsOUT1_value";
	//OUT2
	public static String key199 = "cb_Outputs_OUT2_enabled";
	public static String key200 = "cb_Outputs_OUT2_checked";
	public static String key201 = "spin_Outputs_OutputFunctionsOUT2_enabled";
	public static String key202 = "spin_Outputs_OutputFunctionsOUT2_value";
	//OUT3
	public static String key203 = "cb_Outputs_OUT3_enabled";
	public static String key204 = "cb_Outputs_OUT3_checked";
	public static String key205 = "spin_Outputs_OutputFunctionsOUT3_enabled";
	public static String key206 = "spin_Outputs_OutputFunctionsOUT3_value";
	//OUT4
	public static String key207 = "cb_Outputs_OUT4_enabled";
	public static String key208 = "cb_Outputs_OUT4_checked";
	public static String key209 = "spin_Outputs_OutputFunctionsOUT4_enabled";
	public static String key210 = "spin_Outputs_OutputFunctionsOUT4_value";
	//OUT5
	public static String key211 = "cb_Outputs_OUT5_enabled";
	public static String key212 = "cb_Outputs_OUT5_checked";
	public static String key213 = "spin_Outputs_OutputFunctionsOUT5_enabled";
	public static String key214 = "spin_Outputs_OutputFunctionsOUT5_value";
	//OUT6
	public static String key215 = "cb_Outputs_OUT6_enabled";
	public static String key216 = "cb_Outputs_OUT6_checked";
	public static String key217 = "spin_Outputs_OutputFunctionsOUT6_enabled";
	public static String key218 = "spin_Outputs_OutputFunctionsOUT6_value";
//TIMERS
	//CHANNEL1
	public static String key219 = "cb_UTC_UniversalChannel1_enabled";
	public static String key220 = "cb_UTC_UniversalChannel1_checked";
	public static String key221 = "cb_UTC_ArmingUC1_enabled";
	public static String key222 = "cb_UTC_ArmingUC1_chedked";
	public static String key223 = "cb_UTC_SysDisarmUC1_enabled";
	public static String key224 = "cb_UTC_SysDisarmUC1_checked";
	public static String key225 = "cb_UTC_IgnitionSwitchOnUC1_enabled";
	public static String key226 = "cb_UTC_IgnitionSwitchOnUC1_checked";
	public static String key227 = "cb_UTC_IgnitiOffSwitchOffUC1_enabled";
	public static String key228 = "cb_UTC_IgnitiOffSwitchOffUC1_checked";
	public static String key229 = "cb_UTC_CommandFromPhoneUC1_enabled";
	public static String key230 = "cb_UTC_CommandFromPhoneUC1_checked";
	public static String key231 = "et_UTC_Arming_DelayUC1_enabled";
	public static String key232 = "et_UTC_Arming_DelayUC1_value";
	public static String key233 = "et_UTC_Arming_OperationTimeUC1_enabled";
	public static String key234 = "et_UTC_Arming_OperationTimeUC1_value";
	public static String key235 = "et_UTC_SysDisarm_DelayUC1_enabled";
	public static String key236 = "et_UTC_SysDisarm_DelayUC1_value";
	public static String key237 = "et_UTC_SysDisarm_OperationTimeUC1_enabled";
	public static String key238 = "et_UTC_SysDisarm_OperationTimeUC1_value";
	public static String key239 = "et_UTC_IgnitionSwitchOn_DelayUC1_enabled";
	public static String key240 = "et_UTC_IgnitionSwitchOn_DelayUC1_checked";
	public static String key241 = "et_UTC_IgnitionSwitchOn_OperationTimeUC1_enabled";
	public static String key242 = "et_UTC_IgnitionSwitchOn_OperationTimeUC1_checked";
	public static String key243 = "et_UTC_IgnitionSwitchOff_DelayUC1_enabled";
	public static String key244 = "et_UTC_IgnitionSwitchOff_DelayUC1_checked";
	public static String key245 = "et_UTC_IgnitionSwitchOff_OperationTimeUC1_enabled";
	public static String key246 = "et_UTC_IgnitionSwitchOff_OperationTimeUC1_checked";
	public static String key247 = "et_UTC_CommandFromPhone_DelayUC1_enabled";
	public static String key248 = "et_UTC_CommandFromPhone_DelayUC1_value";
	public static String key249 = "et_UTC_CommandFromPhone_OperationTimeUC1_enabled";
	public static String key250 = "et_UTC_CommandFromPhone_OperationTimeUC1_value";
	//CHANNEL2
	public static String key251 = "cb_UTC_UniversalChannel2_enabled";
	public static String key252 = "cb_UTC_UniversalChannel2_checked";
	public static String key253 = "cb_UTC_ArmingUC2_enabled";
	public static String key254 = "cb_UTC_ArmingUC2_chedked";
	public static String key255 = "cb_UTC_SysDisarmUC2_enabled";
	public static String key256 = "cb_UTC_SysDisarmUC2_checked";
	public static String key257 = "cb_UTC_IgnitionSwitchOnUC2_enabled";
	public static String key258 = "cb_UTC_IgnitionSwitchOnUC2_checked";
	public static String key259 = "cb_UTC_IgnitiOffSwitchOffUC2_enabled";
	public static String key260 = "cb_UTC_IgnitiOffSwitchOffUC2_checked";
	public static String key261 = "cb_UTC_CommandFromPhoneUC2_enabled";
	public static String key262 = "cb_UTC_CommandFromPhoneUC2_checked";
	public static String key263 = "et_UTC_Arming_DelayUC2_enabled";
	public static String key264 = "et_UTC_Arming_DelayUC2_value";
	public static String key265 = "et_UTC_Arming_OperationTimeUC2_enabled";
	public static String key266 = "et_UTC_Arming_OperationTimeUC2_value";
	public static String key267 = "et_UTC_SysDisarm_DelayUC2_enabled";
	public static String key268 = "et_UTC_SysDisarm_DelayUC2_value";
	public static String key269 = "et_UTC_SysDisarm_OperationTimeUC2_enabled";
	public static String key270 = "et_UTC_SysDisarm_OperationTimeUC2_value";
	public static String key271 = "et_UTC_IgnitionSwitchOn_DelayUC2_enabled";
	public static String key272 = "et_UTC_IgnitionSwitchOn_DelayUC2_checked";
	public static String key273 = "et_UTC_IgnitionSwitchOn_OperationTimeUC2_enabled";
	public static String key274 = "et_UTC_IgnitionSwitchOn_OperationTimeUC2_checked";
	public static String key275 = "et_UTC_IgnitionSwitchOff_DelayUC2_enabled";
	public static String key276 = "et_UTC_IgnitionSwitchOff_DelayUC2_checked";
	public static String key277 = "et_UTC_IgnitionSwitchOff_OperationTimeUC2_enabled";
	public static String key278 = "et_UTC_IgnitionSwitchOff_OperationTimeUC2_checked";
	public static String key279 = "et_UTC_CommandFromPhone_DelayUC2_enabled";
	public static String key280 = "et_UTC_CommandFromPhone_DelayUC2_value";
	public static String key281 = "et_UTC_CommandFromPhone_OperationTimeUC2_enabled";
	public static String key282 = "et_UTC_CommandFromPhone_OperationTimeUC2_value";
	//OutputTimerOnArmDelay
	public static String key283 = "cbOutputTimerOnArmDelay_enabled";
	public static String key284 = "cbOutputTimerOnArmDelay_checked";
	public static String key285 = "et_UTC_OutputTimerOnArmOperationTime_enabled";
	public static String key286 = "et_UTC_OutputTimerOnArmOperationTime_value";
	public static String key287 = "et_UTC_OutputTimerOnArmDelay_enabled";
	public static String key288 = "et_UTC_OutputTimerOnArmDelay_value";
	//Two stage disarming system
	public static String key289 = "cbDisarmingSystem_enabled";
	public static String key290 = "cbDisarmingSystem_checked";
	public static String key291 = "spinDisarmingSystem_enabled";
	public static String key292 = "spinDisarmingSystem_value";
	//Rearming
	public static String key293 = "cbRearmingSystem_enabled";
	public static String key294 = "cbRearmingSystem_checked";
	public static String key295 = "spinRearmingSystem_enabled";
	public static String key296 = "spinRearmingSystem_value";
	//Turbotimer
	public static String key297 = "cb_UTC_TurboTimerSettings_enabled";
	public static String key298 = "cb_UTC_TurboTimerSettings_checked";
	public static String key299 = "et_UTC_TurbotimerWorkingTime_enabled";
	public static String key300 = "et_UTC_TurbotimerWorkingTime_value";
	public static String key301 = "et_UTC_IngnTimeLockedEngine_enabled";
	public static String key302 = "et_UTC_IngnTimeLockedEngine_value";
	//Delay Time Engine Start
	public static String key303 = "cb_UTC_DelayTimeEngineStart_enabled";
	public static String key304 = "cb_UTC_DelayTimeEngineStart_checked";
	public static String key305 = "et_UTC_DelayTimeEngineStart_enabled";
	public static String key306 = "et_UTC_DelayTimeEngineStart_value";
	//Delay Time Limit Switches Survey
	public static String key307 = "cb_UTC_DelayTimeLimitSwitchesSurvey_enabled";
	public static String key308 = "cb_UTC_DelayTimeLimitSwitchesSurvey_checked";
	public static String key309 = "et_UTC_DelayTimeLimitSwitchesSurvey_enabled";
	public static String key310 = "et_UTC_DelayTimeLimitSwitchesSurvey_value";
	//FUNCTION 13
	public static String key579 = "cb_UTC_CentralLockTimer13_enabled";
	public static String key580 = "cb_UTC_CentralLockTimer13_checked";
	public static String key581 = "spin_UTC_LockImpulsCloseCL13_enabled";
	public static String key582 = "spin_UTC_LockImpulsCloseCL13_value";
	public static String key583 = "et_UTC_FirstImpulsLongtime13_enabled";
	public static String key584 = "et_UTC_FirstImpulsLongtime13_value";
	public static String key585 = "et_UTC_PauseTimeBetwImpulses13_enabled";
	public static String key586 = "et_UTC_PauseTimeBetwImpulses13_value";
	public static String key587 = "et_UTC_SecondImpulsLongtime13_enabled";
	public static String key588 = "et_UTC_SecondImpulsLongtime13_value";
	public static String key589 = "et_UTC_PauseTimeAfterIngAndImpStart13_enabled";
	public static String key590 = "et_UTC_PauseTimeAfterIngAndImpStart13_value";
	//FUNCTION 14
	public static String key591 = "cb_UTC_CentralLockTimer14_enabled";
	public static String key592 = "cb_UTC_CentralLockTimer14_checked";
	public static String key593 = "spin_UTC_LockImpulsCloseCL14_enabled";
	public static String key594 = "spin_UTC_LockImpulsCloseCL14_value";
	public static String key595 = "et_UTC_FirstImpulsLongtime14_enabled";
	public static String key596 = "et_UTC_FirstImpulsLongtime14_value";
	public static String key597 = "et_UTC_PauseTimeBetwImpulses14_enabled";
	public static String key598 = "et_UTC_PauseTimeBetwImpulses14_value";
	public static String key599 = "et_UTC_SecondImpulsLongtime14_enabled";
	public static String key600 = "et_UTC_SecondImpulsLongtime14_value";
//NOTIFICATIONS
	//User Enable checkbox
	public static String key311 = "cb_Notifications_User1_enabled";
	public static String key312 = "cb_Notifications_User1_checked";
	public static String key313 = "cb_Notifications_User2_enabled";
	public static String key314 = "cb_Notifications_User2_checked";
	public static String key315 = "cb_Notifications_User3_enabled";
	public static String key316 = "cb_Notifications_User3_checked";
	public static String key317 = "cb_Notifications_User4_enabled";
	public static String key318 = "cb_Notifications_User4_checked";
	public static String key319 = "cb_Notifications_User5_enabled";
	public static String key320 = "cb_Notifications_User5_checked";
	//Phone number
	public static String key321 = "et_Notifications_PhoneNumberUser1_enabled";
	public static String key322 = "et_Notifications_PhoneNumberUser1_value";
	public static String key323 = "et_Notifications_PhoneNumberUser2_enabled";
	public static String key324 = "et_Notifications_PhoneNumberUser2_value";
	public static String key325 = "et_Notifications_PhoneNumberUser3_enabled";
	public static String key326 = "et_Notifications_PhoneNumberUser3_value";
	public static String key327 = "et_Notifications_PhoneNumberUser4_enabled";
	public static String key328 = "et_Notifications_PhoneNumberUser4_value";
	public static String key329 = "et_Notifications_PhoneNumberUser5_enabled";
	public static String key330 = "et_Notifications_PhoneNumberUser5_value";
	//Allow Notification SMS
	public static String key331 = "cb_Notidications_AllowNotificationsSMSUser1_enabled";
	public static String key332 = "cb_Notidications_AllowNotificationsSMSUser1_checked";
	public static String key333 = "cb_Notidications_AllowNotificationsSMSUser2_enabled";
	public static String key334 = "cb_Notidications_AllowNotificationsSMSUser2_checked";
	public static String key335 = "cb_Notidications_AllowNotificationsSMSUser3_enabled";
	public static String key336 = "cb_Notidications_AllowNotificationsSMSUser3_checked";
	public static String key337 = "cb_Notidications_AllowNotificationsSMSUser4_enabled";
	public static String key338 = "cb_Notidications_AllowNotificationsSMSUser4_checked";
	public static String key339 = "cb_Notidications_AllowNotificationsSMSUser5_enabled";
	public static String key340 = "cb_Notidications_AllowNotificationsSMSUser5_checked";
	//Allow Notification TEL
	public static String key341 = "cb_Notidications_AllowNotificationsTELUser1_enabled";
	public static String key342 = "cb_Notidications_AllowNotificationsTELUser1_checked";
	public static String key343 = "cb_Notidications_AllowNotificationsTELUser2_enabled";
	public static String key344 = "cb_Notidications_AllowNotificationsTELUser2_checked";
	public static String key345 = "cb_Notidications_AllowNotificationsTELUser3_enabled";
	public static String key346 = "cb_Notidications_AllowNotificationsTELUser3_checked";
	public static String key347 = "cb_Notidications_AllowNotificationsTELUser4_enabled";
	public static String key348 = "cb_Notidications_AllowNotificationsTELUser4_checked";
	public static String key349 = "cb_Notidications_AllowNotificationsTELUser5_enabled";
	public static String key350 = "cb_Notidications_AllowNotificationsTELUser5_checked";
	//Button Call button pressed SMS
	public static String key351 = "btn_Notifications_CallBtnPressedSMSUser1_enabled";
	public static String key352 = "btn_Notifications_CallBtnPressedSMSUser1_checked";
	public static String key353 = "btn_Notifications_CallBtnPressedSMSUser2_enabled";
	public static String key354 = "btn_Notifications_CallBtnPressedSMSUser2_checked";
	public static String key355 = "btn_Notifications_CallBtnPressedSMSUser3_enabled";
	public static String key356 = "btn_Notifications_CallBtnPressedSMSUser3_checked";
	public static String key357 = "btn_Notifications_CallBtnPressedSMSUser4_enabled";
	public static String key358 = "btn_Notifications_CallBtnPressedSMSUser4_checked";
	public static String key359 = "btn_Notifications_CallBtnPressedSMSUser5_enabled";
	public static String key360 = "btn_Notifications_CallBtnPressedSMSUser5_checked";
	//Button Call button pressed TEL
	public static String key361 = "btn_Notifications_CallBtnPressedTELUser1_enabled";
	public static String key362 = "btn_Notifications_CallBtnPressedTELUser1_checked";
	public static String key363 = "btn_Notifications_CallBtnPressedTELUser2_enabled";
	public static String key364 = "btn_Notifications_CallBtnPressedTELUser2_checked";
	public static String key365 = "btn_Notifications_CallBtnPressedTELUser3_enabled";
	public static String key366 = "btn_Notifications_CallBtnPressedTELUser3_checked";
	public static String key367 = "btn_Notifications_CallBtnPressedTELUser4_enabled";
	public static String key368 = "btn_Notifications_CallBtnPressedTELUser4_checked";
	public static String key369 = "btn_Notifications_CallBtnPressedTELUser5_enabled";
	public static String key370 = "btn_Notifications_CallBtnPressedTELUser5_checked";
	//Ingine Active SMS
	public static String key371 = "btn_Notifications_IngineActiveSMSUser1_enabled";
	public static String key372 = "btn_Notifications_IngineActiveSMSUser1_checked";
	public static String key373 = "btn_Notifications_IngineActiveSMSUser2_enabled";
	public static String key374 = "btn_Notifications_IngineActiveSMSUser2_checked";
	public static String key375 = "btn_Notifications_IngineActiveSMSUser3_enabled";
	public static String key376 = "btn_Notifications_IngineActiveSMSUser3_checked";
	public static String key377 = "btn_Notifications_IngineActiveSMSUser4_enabled";
	public static String key378 = "btn_Notifications_IngineActiveSMSUser4_checked";
	public static String key379 = "btn_Notifications_IngineActiveSMSUser5_enabled";
	public static String key380 = "btn_Notifications_IngineActiveSMSUser5_checked";
	//Ingine Active TEL
	public static String key381 = "btn_Notifications_IngineActiveTELUser1_enabled";
	public static String key382 = "btn_Notifications_IngineActiveTELUser1_checked";
	public static String key383 = "btn_Notifications_IngineActiveTELUser2_enabled";
	public static String key384 = "btn_Notifications_IngineActiveTELUser2_checked";
	public static String key385 = "btn_Notifications_IngineActiveTELUser3_enabled";
	public static String key386 = "btn_Notifications_IngineActiveTELUser3_checked";
	public static String key387 = "btn_Notifications_IngineActiveTELUser4_enabled";
	public static String key388 = "btn_Notifications_IngineActiveTELUser4_checked";
	public static String key389 = "btn_Notifications_IngineActiveTELUser5_enabled";
	public static String key390 = "btn_Notifications_IngineActiveTELUser5_checked";
	//Load Limit Switch SMS
	public static String key391 = "btn_Notifications_LoadLimitSwitchSMSUser1_enabled";
	public static String key392 = "btn_Notifications_LoadLimitSwitchSMSUser1_checked";
	public static String key393 = "btn_Notifications_LoadLimitSwitchSMSUser2_enabled";
	public static String key394 = "btn_Notifications_LoadLimitSwitchSMSUser2_checked";
	public static String key395 = "btn_Notifications_LoadLimitSwitchSMSUser3_enabled";
	public static String key396 = "btn_Notifications_LoadLimitSwitchSMSUser3_checked";
	public static String key397 = "btn_Notifications_LoadLimitSwitchSMSUser4_enabled";
	public static String key398 = "btn_Notifications_LoadLimitSwitchSMSUser4_checked";
	public static String key399 = "btn_Notifications_LoadLimitSwitchSMSUser5_enabled";
	public static String key400 = "btn_Notifications_LoadLimitSwitchSMSUser5_checked";
	//Load Limit Switch TEL
	public static String key401 = "btn_Notifications_LoadLimitSwitchTELUser1_enabled";
	public static String key402 = "btn_Notifications_LoadLimitSwitchTELUser1_checked";
	public static String key403 = "btn_Notifications_LoadLimitSwitchTELUser2_enabled";
	public static String key404 = "btn_Notifications_LoadLimitSwitchTELUser2_checked";
	public static String key405 = "btn_Notifications_LoadLimitSwitchTELUser3_enabled";
	public static String key406 = "btn_Notifications_LoadLimitSwitchTELUser3_checked";
	public static String key407 = "btn_Notifications_LoadLimitSwitchTELUser4_enabled";
	public static String key408 = "btn_Notifications_LoadLimitSwitchTELUser4_checked";
	public static String key409 = "btn_Notifications_LoadLimitSwitchTELUser5_enabled";
	public static String key410 = "btn_Notifications_LoadLimitSwitchTELUser5_checked";
	//Button Load Shock Sensor SMS
	public static String key411 = "btn_Notifications_LoadShockSensorSMSUser1_enabled";
	public static String key412 = "btn_Notifications_LoadShockSensorSMSUser1_checked";
	public static String key413 = "btn_Notifications_LoadShockSensorSMSUser2_enabled";
	public static String key414 = "btn_Notifications_LoadShockSensorSMSUser2_checked";
	public static String key415 = "btn_Notifications_LoadShockSensorSMSUser3_enabled";
	public static String key416 = "btn_Notifications_LoadShockSensorSMSUser3_checked";
	public static String key417 = "btn_Notifications_LoadShockSensorSMSUser4_enabled";
	public static String key418 = "btn_Notifications_LoadShockSensorSMSUser4_checked";
	public static String key419 = "btn_Notifications_LoadShockSensorSMSUser5_enabled";
	public static String key420 = "btn_Notifications_LoadShockSensorSMSUser5_checked";
	//Button Load Shock Sensor TEL
	public static String key421 = "btn_Notifications_LoadShockSensorTELUser1_enabled";
	public static String key422 = "btn_Notifications_LoadShockSensorTELUser1_checked";
	public static String key423 = "btn_Notifications_LoadShockSensorTELUser2_enabled";
	public static String key424 = "btn_Notifications_LoadShockSensorTELUser2_checked";
	public static String key425 = "btn_Notifications_LoadShockSensorTELUser3_enabled";
	public static String key426 = "btn_Notifications_LoadShockSensorTELUser3_checked";
	public static String key427 = "btn_Notifications_LoadShockSensorTELUser4_enabled";
	public static String key428 = "btn_Notifications_LoadShockSensorTELUser4_checked";
	public static String key429 = "btn_Notifications_LoadShockSensorTELUser5_enabled";
	public static String key430 = "btn_Notifications_LoadShockSensorTELUser5_checked";
	//Warning Zone SMS
	public static String key431 = "btn_Notifications_WarningZoneSMSUser1_enabled";
	public static String key432 = "btn_Notifications_WarningZoneSMSUser1_checked";
	public static String key433 = "btn_Notifications_WarningZoneSMSUser2_enabled";
	public static String key434 = "btn_Notifications_WarningZoneSMSUser2_checked";
	public static String key435 = "btn_Notifications_WarningZoneSMSUser3_enabled";
	public static String key436 = "btn_Notifications_WarningZoneSMSUser3_checked";
	public static String key437 = "btn_Notifications_WarningZoneSMSUser4_enabled";
	public static String key438 = "btn_Notifications_WarningZoneSMSUser4_checked";
	public static String key439 = "btn_Notifications_WarningZoneSMSUser5_enabled";
	public static String key440 = "btn_Notifications_WarningZoneSMSUser5_checked";
	//Warning Zone TEL
	public static String key441 = "btn_Notifications_WarningZoneTELUser1_enabled";
	public static String key442 = "btn_Notifications_WarningZoneTELUser1_checked";
	public static String key443 = "btn_Notifications_WarningZoneTELUser2_enabled";
	public static String key444 = "btn_Notifications_WarningZoneTELUser2_checked";
	public static String key445 = "btn_Notifications_WarningZoneTELUser3_enabled";
	public static String key446 = "btn_Notifications_WarningZoneTELUser3_checked";
	public static String key447 = "btn_Notifications_WarningZoneTELUser4_enabled";
	public static String key448 = "btn_Notifications_WarningZoneTELUser4_checked";
	public static String key449 = "btn_Notifications_WarningZoneTELUser5_enabled";
	public static String key450 = "btn_Notifications_WarningZoneTELUser5_checked";
	//Load Universal Limit Switch SMS
	public static String key451 = "btn_Notifications_LoadUniversalLimitSwitchSMSUser1_enabled";
	public static String key452 = "btn_Notifications_LoadUniversalLimitSwitchSMSUser1_checked";
	public static String key453 = "btn_Notifications_LoadUniversalLimitSwitchSMSUser2_enabled";
	public static String key454 = "btn_Notifications_LoadUniversalLimitSwitchSMSUser2_checked";
	public static String key455 = "btn_Notifications_LoadUniversalLimitSwitchSMSUser3_enabled";
	public static String key456 = "btn_Notifications_LoadUniversalLimitSwitchSMSUser3_checked";
	public static String key457 = "btn_Notifications_LoadUniversalLimitSwitchSMSUser4_enabled";
	public static String key458 = "btn_Notifications_LoadUniversalLimitSwitchSMSUser4_checked";
	public static String key459 = "btn_Notifications_LoadUniversalLimitSwitchSMSUser5_enabled";
	public static String key460 = "btn_Notifications_LoadUniversalLimitSwitchSMSUser5_checked";
	//Load Universal Limit Switch TEL
	public static String key461 = "btn_Notifications_LoadUniversalLimitSwitchTELUser1_enabled";
	public static String key462 = "btn_Notifications_LoadUniversalLimitSwitchTELUser1_checked";
	public static String key463 = "btn_Notifications_LoadUniversalLimitSwitchTELUser2_enabled";
	public static String key464 = "btn_Notifications_LoadUniversalLimitSwitchTELUser2_checked";
	public static String key465 = "btn_Notifications_LoadUniversalLimitSwitchTELUser3_enabled";
	public static String key466 = "btn_Notifications_LoadUniversalLimitSwitchTELUser3_checked";
	public static String key467 = "btn_Notifications_LoadUniversalLimitSwitchTELUser4_enabled";
	public static String key468 = "btn_Notifications_LoadUniversalLimitSwitchTELUser4_checked";
	public static String key469 = "btn_Notifications_LoadUniversalLimitSwitchTELUser5_enabled";
	public static String key470 = "btn_Notifications_LoadUniversalLimitSwitchTELUser5_checked";
	//Button Low Buttery SMS
	public static String key471 = "btn_Notifications_LowBatterySMSUser1_enabled";
	public static String key472 = "btn_Notifications_LowBatterySMSUser1_checked";
	public static String key473 = "btn_Notifications_LowBatterySMSUser2_enabled";
	public static String key474 = "btn_Notifications_LowBatterySMSUser2_checked";
	public static String key475 = "btn_Notifications_LowBatterySMSUser3_enabled";
	public static String key476 = "btn_Notifications_LowBatterySMSUser3_checked";
	public static String key477 = "btn_Notifications_LowBatterySMSUser4_enabled";
	public static String key478 = "btn_Notifications_LowBatterySMSUser4_checked";
	public static String key479 = "btn_Notifications_LowBatterySMSUser5_enabled";
	public static String key480 = "btn_Notifications_LowBatterySMSUser5_checked";
	//Button Low Buttery TEL
	public static String key481 = "btn_Notifications_LowBatteryTELUser1_enabled";
	public static String key482 = "btn_Notifications_LowBatteryTELUser1_checked";
	public static String key483 = "btn_Notifications_LowBatteryTELUser2_enabled";
	public static String key484 = "btn_Notifications_LowBatteryTELUser2_checked";
	public static String key485 = "btn_Notifications_LowBatteryTELUser3_enabled";
	public static String key486 = "btn_Notifications_LowBatteryTELUser3_checked";
	public static String key487 = "btn_Notifications_LowBatteryTELUser4_enabled";
	public static String key488 = "btn_Notifications_LowBatteryTELUser4_checked";
	public static String key489 = "btn_Notifications_LowBatteryTELUser5_enabled";
	public static String key490 = "btn_Notifications_LowBatteryTELUser5_checked";
	//GSM SMS
	public static String key491 = "btn_Notifications_GSM_SMSUser1_enabled";
	public static String key492 = "btn_Notifications_GSM_SMSUser1_checked";
	public static String key493 = "btn_Notifications_GSM_SMSUser2_enabled";
	public static String key494 = "btn_Notifications_GSM_SMSUser2_checked";
	public static String key495 = "btn_Notifications_GSM_SMSUser3_enabled";
	public static String key496 = "btn_Notifications_GSM_SMSUser3_checked";
	public static String key497 = "btn_Notifications_GSM_SMSUser4_enabled";
	public static String key498 = "btn_Notifications_GSM_SMSUser4_checked";
	public static String key499 = "btn_Notifications_GSM_SMSUser5_enabled";
	public static String key500 = "btn_Notifications_GSM_SMSUser5_checked";
	//GSM TEL
	public static String key501 = "btn_Notifications_GSM_TELUser1_enabled";
	public static String key502 = "btn_Notifications_GSM_TELUser1_checked";
	public static String key503 = "btn_Notifications_GSM_TELUser2_enabled";
	public static String key504 = "btn_Notifications_GSM_TELUser2_checked";
	public static String key505 = "btn_Notifications_GSM_TELUser3_enabled";
	public static String key506 = "btn_Notifications_GSM_TELUser3_checked";
	public static String key507 = "btn_Notifications_GSM_TELUser4_enabled";
	public static String key508 = "btn_Notifications_GSM_TELUser4_checked";
	public static String key509 = "btn_Notifications_GSM_TELUser5_enabled";
	public static String key510 = "btn_Notifications_GSM_TELUser5_checked";
	//Button System Disarm SMS
	public static String key511 = "btn_Notifications_SystemDisarmSMSUser1_enabled";
	public static String key512 = "btn_Notifications_SystemDisarmSMSUser1_checked";
	public static String key513 = "btn_Notifications_SystemDisarmSMSUser2_enabled";
	public static String key514 = "btn_Notifications_SystemDisarmSMSUser2_checked";
	public static String key515 = "btn_Notifications_SystemDisarmSMSUser3_enabled";
	public static String key516 = "btn_Notifications_SystemDisarmSMSUser3_checked";
	public static String key517 = "btn_Notifications_SystemDisarmSMSUser4_enabled";
	public static String key518 = "btn_Notifications_SystemDisarmSMSUser4_checked";
	public static String key519 = "btn_Notifications_SystemDisarmSMSUser5_enabled";
	public static String key520 = "btn_Notifications_SystemDisarmSMSUser5_checked";
	//Button System Disarm TEL
	public static String key521 = "btn_Notifications_SystemDisarmTELUser1_enabled";
	public static String key522 = "btn_Notifications_SystemDisarmTELUser1_checked";
	public static String key523 = "btn_Notifications_SystemDisarmTELUser2_enabled";
	public static String key524 = "btn_Notifications_SystemDisarmTELUser2_checked";
	public static String key525 = "btn_Notifications_SystemDisarmTELUser3_enabled";
	public static String key526 = "btn_Notifications_SystemDisarmTELUser3_checked";
	public static String key527 = "btn_Notifications_SystemDisarmTELUser4_enabled";
	public static String key528 = "btn_Notifications_SystemDisarmTELUser4_checked";
	public static String key529 = "btn_Notifications_SystemDisarmTELUser5_enabled";
	public static String key530 = "btn_Notifications_SystemDisarmTELUser5_checked";
	//Button System Arming SMS
	public static String key531 = "btn_Notifications_SystemArmingSMSUser1_enabled";
	public static String key532 = "btn_Notifications_SystemArmingSMSUser1_checked";
	public static String key533 = "btn_Notifications_SystemArmingSMSUser2_enabled";
	public static String key534 = "btn_Notifications_SystemArmingSMSUser2_checked";
	public static String key535 = "btn_Notifications_SystemArmingSMSUser3_enabled";
	public static String key536 = "btn_Notifications_SystemArmingSMSUser3_checked";
	public static String key537 = "btn_Notifications_SystemArmingSMSUser4_enabled";
	public static String key538 = "btn_Notifications_SystemArmingSMSUser4_checked";
	public static String key539 = "btn_Notifications_SystemArmingSMSUser5_enabled";
	public static String key540 = "btn_Notifications_SystemArmingSMSUser5_checked";
	//Button System Arming TEL
	public static String key541 = "btn_Notifications_SystemArmingTELUser1_enabled";
	public static String key542 = "btn_Notifications_SystemArmingTELUser1_checked";
	public static String key543 = "btn_Notifications_SystemArmingTELUser2_enabled";
	public static String key544 = "btn_Notifications_SystemArmingTELUser2_checked";
	public static String key545 = "btn_Notifications_SystemArmingTELUser3_enabled";
	public static String key546 = "btn_Notifications_SystemArmingTELUser3_checked";
	public static String key547 = "btn_Notifications_SystemArmingTELUser4_enabled";
	public static String key548 = "btn_Notifications_SystemArmingTELUser4_checked";
	public static String key549 = "btn_Notifications_SystemArmingTELUser5_enabled";
	public static String key550 = "btn_Notifications_SystemArmingTELUser5_checked";
//CAN
	//1)
	public static String key551 = "cb_CAN_SystArmDisarm_enabled";
	public static String key552 = "cb_CAN_SystArmDisarm_checked";
	public static String key553 = "spin_CAN_SystArmDisarm_enabled";
	public static String key554 = "spin_CAN_SystArmDisarm_value";
	//2)
	public static String key555 = "cb_CAN_Ignition_enabled";
	public static String key556 = "cb_CAN_Ignition_checked";
	public static String key557 = "spin_CAN_Ignition_enabled";
	public static String key558 = "spin_CAN_Ignition_value";
	//3)
	public static String key559 = "cb_CAN_DriveControl_enabled";
	public static String key560 = "cb_CAN_DriveControl_checked";
	public static String key561 = "spin_CAN_DriveControl_enabled";
	public static String key562 = "spin_CAN_DriveControl_value";
	//4)
	public static String key563 = "cb_CAN_ComfortSignal_enabled";
	public static String key564 = "cb_CAN_ComfortSignal_checked";
	public static String key565 = "spin_CAN_ComfortSignal_enabled";
	public static String key566 = "spin_CAN_ComfortSignal_value";
	//5)
	public static String key567 = "cb_CAN_ManagingStaffSecuritySystem_enabled";
	public static String key568 = "cb_CAN_ManagingStaffSecuritySystem_checked";
	public static String key569 = "spin_CAN_ManagingStaffSecuritySystem_enabled";
	public static String key570 = "spin_CAN_ManagingStaffSecuritySystem_value";
//TEXT SMS
	//Text SMS
	public static String key571 = "et_TextSMS_TextSMS_enabled";
	public static String key572 = "et_TextSMS_TextSMS_value";
	public static String key573 = "et_PhoneForSMS_enabled";
	public static String key574 = "et_PhoneForSMS_value";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.save_settings_popup);
		
		nameOfParentActivity = getIntent().getStringExtra(PARENT_ACTIVITY_NAME);
		
		init();
		setListeners();
		setDisplayMetrics();
		loadFilesToTheList();

		setLanguage();
		updateUI();
	}

	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{
			case R.id.btn_SaveSettings_Save:
			{
				saveSettings();
		        finish();
				break;
			}
			case R.id.btn_SaveSettings_Cancel:
			{
				finish();
				break;
			}
		}
	}
	
	private void init()
	{
		et_SaveSettings_EnterNameForNewSettings = (EditText) findViewById(R.id.et_SaveSettings_EnterNameForNewSettings);
		ll_SaveSettings_SavedSettings = (LinearLayout) findViewById(R.id.ll_SaveSettings_SavedSettings);
		btn_SaveSettings_Save = (Button) findViewById(R.id.btn_SaveSettings_Save);
		btn_SaveSettings_Cancel = (Button) findViewById(R.id.btn_SaveSettings_Cancel);

		tv_SavePopup_Header = (TextView) findViewById(R.id.tv_SavePopup_Header);
		tv_SaveSettings_EnterNameForNewSettings = (TextView) findViewById(R.id.tv_SaveSettings_EnterNameForNewSettings);
	}
	
	private void setListeners()
	{
		btn_SaveSettings_Save.setOnClickListener(this);
		btn_SaveSettings_Cancel.setOnClickListener(this);
	}
	
	private void setDisplayMetrics()
	{
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		int width = dm.widthPixels;
		int height = dm.heightPixels;
		
		getWindow().setLayout((int)(width*1.0), (int)(height*1.0));
	}
	
	private void saveSettings()
	{
		File folder = new File(Environment.getExternalStorageDirectory() + "/" + FOLDER_WITH_SETTINGS_NAME);
		boolean success = true;
		if (!folder.exists()) 
		{
		    success = folder.mkdir();
		}
		
		if (success && !et_SaveSettings_EnterNameForNewSettings.getText().toString().isEmpty()) 
		{	
			try 
			{
				String format = "";
				if(m_stateholder.getManualModeChecked())
					format = ".xml";
				else
					format = ".txt";
				
				String fname = et_SaveSettings_EnterNameForNewSettings.getText().toString() + format;
			    File file = new File (folder, fname);
			    if (file.exists ()) 
			    	file.delete ();
			    
			    FileOutputStream fOut = new FileOutputStream(file);
			    OutputStreamWriter osw = new OutputStreamWriter(fOut);
			    if(m_stateholder.getManualModeChecked())
			    	osw.write(m_stateholder.getTextSMSValue());
				else
					osw.write(getSettingsString());
			    
			    osw.flush();
			    osw.close();
			} 
			catch (Exception e) 
			{
				Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			}
		} 
		else 
		{
		    // Do something else on failure 
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

	private void updateUI() {
		tv_SavePopup_Header.setText(R.string.tv_SavePopup_Header);
		tv_SaveSettings_EnterNameForNewSettings.setText(R.string.tv_SavePopup_SubHeader);
	}
	
	private void loadFilesToTheList()
	{
		String settingsStoragePath = Environment.getExternalStorageDirectory().toString()+"/"+FOLDER_WITH_SETTINGS_NAME;
		File settingsStorage = new File(settingsStoragePath);
		if(!settingsStorage.exists())
			return;
			
		File filesNames[] = settingsStorage.listFiles();
		for (int i=0; i < filesNames.length; i++)
		{
			String fileName = filesNames[i].getName().toString();
			boolean acceptFileCreation = true;
			acceptFileCreation &= !fileName.equals(MainActivity.DEFAULT_SETTINGS+".txt");
			acceptFileCreation &= !fileName.equals(MainActivity.FUNCTION_301+".txt");
			acceptFileCreation &= !fileName.equals(MainActivity.FUNCTION_302+".txt");
			acceptFileCreation &= !fileName.equals(MainActivity.FUNCTION_303+".txt");
			acceptFileCreation &= !fileName.equals(MainActivity.FUNCTION_304+".txt");
			acceptFileCreation &= !fileName.equals(MainActivity.FUNCTION_305+".txt");
			acceptFileCreation &= !fileName.equals(MainActivity.FUNCTION_306+".txt");
			acceptFileCreation &= !fileName.equals(MainActivity.STAND_USER_SETTINGS+".txt");
			
			boolean correctFormat = false;
			if(m_stateholder.getManualModeChecked())
				correctFormat = fileName.endsWith(".xml");
			else
				correctFormat = fileName.endsWith(".txt");
			
			if(acceptFileCreation && correctFormat)
			{
				TextView view = new TextView(this);
				//Replace file names
				if(fileName.endsWith(".txt")) {
					fileName = fileName.replace(".txt", "(A)");
				}
				else if(fileName.endsWith(".xml")) {
					fileName = fileName.replace(".xml", "(M)");
				}
				view.setText(fileName);
				view.setTextSize(TypedValue.COMPLEX_UNIT_PX, 
				           getResources().getDimension(R.dimen.header_text_size));
				LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				int myMarginPx = (int) getResources().getDimension(R.dimen.settings_list_margin);
				params.setMargins(myMarginPx, myMarginPx, myMarginPx, myMarginPx);
				view.setLayoutParams(params);
				ll_SaveSettings_SavedSettings.addView(view);
			}
		}
	}
	
	private String getSettingsString()
	{
		String settings = "";
		
//QUERY CONFIGURATIONS
		//1.Query configurations
		settings += "<"+ key1 +">" + MainActivity.stateholderQueryConfigurations.getCbQueryConfigurationsEnabled() + "</"+ key1 +">\n";
		settings += "<"+ key2 +">" + MainActivity.stateholderQueryConfigurations.getCbQueryConfigurationsChecked() + "</"+ key2 +">\n";
		settings += "<"+ key3 +">" + MainActivity.stateholderQueryConfigurations.getQueryConfigurationsEnabled() + "</"+ key3 +">\n";
		settings += "<"+ key4 +">" + MainActivity.stateholderQueryConfigurations.getQueryConfigurationsValue() + "</"+ key4 +">\n";
		//2.Hardware configuration system
		settings += "<"+ key5 +">" + MainActivity.stateholderQueryConfigurations.getCbHardwareConfigSystEnabled() + "</"+ key5 +">\n";
		settings += "<"+ key6 +">" + MainActivity.stateholderQueryConfigurations.getCbHardwareConfigSystChecked() + "</"+ key6 +">\n";
		//3.User system settings
		settings += "<"+ key7 +">" + MainActivity.stateholderQueryConfigurations.getCbUserSystSettingsEnabled() + "</"+ key7 +">\n";
		settings += "<"+ key8 +">" + MainActivity.stateholderQueryConfigurations.getCbUserSystSettingsChecked() + "</"+ key8 +">\n";
//USER SETTINGS
		//0) CURRENT PIN
		settings += "<"+ key575 +">" + MainActivity.stateholderUserSettings.getCurrentPinEnabled() + "</"+ key575 +">\n";
		settings += "<"+ key576 +">" + MainActivity.stateholderUserSettings.getCurrentPinChecked() + "</"+ key576 +">\n";
		settings += "<"+ key577 +">" + MainActivity.stateholderUserSettings.getEtCurrentPINEnabled() + "</"+ key577 +">\n";
		settings += "<"+ key578 +">" + MainActivity.stateholderUserSettings.getEtCurrentPINValue() + "</"+ key578 +">\n";
		//1) NEW PIN
		settings += "<"+ key9 +">" + MainActivity.stateholderUserSettings.getPinEnabled() + "</"+ key9 +">\n";
		settings += "<"+ key10 +">" + MainActivity.stateholderUserSettings.getPinChecked() + "</"+ key10 +">\n";
		settings += "<"+ key11 +">" + MainActivity.stateholderUserSettings.getEtPINEnabled() + "</"+ key11 +">\n";
		settings += "<"+ key12 +">" + MainActivity.stateholderUserSettings.getEtPINValue() + "</"+ key12 +">\n";
		//2) BALANCE CHECKING NUMBER
		settings += "<"+ key13 +">" + MainActivity.stateholderUserSettings.getBalanceChekingNumberEnabled() + "</"+ key13 +">\n";
		settings += "<"+ key14 +">" + MainActivity.stateholderUserSettings.getBalanceChekingNumberChecked() + "</"+ key14 +">\n";
		settings += "<"+ key15 +">" + MainActivity.stateholderUserSettings.getBalanceChekingNumberSpinEnabled() + "</"+ key15 +">\n";
		settings += "<"+ key16 +">" + MainActivity.stateholderUserSettings.getBalanceChekingNumberSpinValue() + "</"+ key16 +">\n";
		settings += "<"+ key17 +">" + MainActivity.stateholderUserSettings.getEtBalanceCheckingAnotherEnabled() + "</"+ key17 +">\n";
		settings += "<"+ key18 +">" + MainActivity.stateholderUserSettings.getEtBalanceCheckingAnotherValue() + "</"+ key18 +">\n";
		//3) BALANCE AUTO-CHECKING
		settings += "<"+ key19 +">" + MainActivity.stateholderUserSettings.getCbAccBalanceEnabled() + "</"+ key19 +">\n";
		settings += "<"+ key20 +">" + MainActivity.stateholderUserSettings.getCbAccBalanceChecked() + "</"+ key20 +">\n";
		settings += "<"+ key21 +">" + MainActivity.stateholderUserSettings.getEtNumSumPositionEnabled() + "</"+ key21 +">\n";
		settings += "<"+ key22 +">" + MainActivity.stateholderUserSettings.getEtNumSumPositionValue() + "</"+ key22 +">\n";
		settings += "<"+ key23 +">" + MainActivity.stateholderUserSettings.getEtCriticalBalanceEnabled() + "</"+ key23 +">\n";
		settings += "<"+ key24 +">" + MainActivity.stateholderUserSettings.getEtCriticalBalanceValue() + "</"+ key24 +">\n";
		settings += "<"+ key25 +">" + MainActivity.stateholderUserSettings.getEtBalanceCheckingFrequencyEnabled() + "</"+ key25 +">\n";
		settings += "<"+ key26 +">" + MainActivity.stateholderUserSettings.getEtBalanceCheckingFrequencyValue() + "</"+ key26 +">\n";
		//4) NUMBER OF CALL ATTEMPTS
		settings += "<"+ key27 +">" + MainActivity.stateholderUserSettings.getNuberCallAttemptsEnabled() + "</"+ key27 +">\n";
		settings += "<"+ key28 +">" + MainActivity.stateholderUserSettings.getNuberCallAttemptsChecked() + "</"+ key28 +">\n";
		settings += "<"+ key29 +">" + MainActivity.stateholderUserSettings.getEtNumberCallAttemptsEnabled() + "</"+ key29 +">\n";
		settings += "<"+ key30 +">" + MainActivity.stateholderUserSettings.getEtNumberCallAttemptsValue() + "</"+ key30 +">\n";
		//REARM
		settings += "<"+ key601 +">" + MainActivity.stateholderUserSettings.getRearmEnabled() + "</"+ key601 +">\n";
		settings += "<"+ key602 +">" + MainActivity.stateholderUserSettings.getRearmChecked() + "</"+ key602 +">\n";
		settings += "<"+ key603 +">" + MainActivity.stateholderUserSettings.getSpinRearmEnabled() + "</"+ key603 +">\n";
		settings += "<"+ key604 +">" + MainActivity.stateholderUserSettings.getSpinRearmValue() + "</"+ key604 +">\n";
		//5) SIREN
		settings += "<"+ key31 +">" + MainActivity.stateholderUserSettings.getCbSirenEnabled() + "</"+ key31 +">\n";
		settings += "<"+ key32 +">" + MainActivity.stateholderUserSettings.getCbSirenChecked() + "</"+ key32 +">\n";
		settings += "<"+ key33 +">" + MainActivity.stateholderUserSettings.getSpinSystemSetUnsetEnabled() + "</"+ key33 +">\n";
		settings += "<"+ key34 +">" + MainActivity.stateholderUserSettings.getSpinSystemSetUnsetValue() + "</"+ key34 +">\n";
		settings += "<"+ key35 +">" + MainActivity.stateholderUserSettings.getSpinAlarmModeEnabled() + "</"+ key35 +">\n";
		settings += "<"+ key36 +">" + MainActivity.stateholderUserSettings.getSpinAlarmModeValue() + "</"+ key36 +">\n";
		//7) ALL SYSTEM SENSORS
		settings += "<"+ key37 +">" + MainActivity.stateholderUserSettings.geCbtAllSystemSensorsEnabled() + "</"+ key37 +">\n";
		settings += "<"+ key38 +">" + MainActivity.stateholderUserSettings.getCbAllSystemSensorsChecked() + "</"+ key38 +">\n";
		settings += "<"+ key39 +">" + MainActivity.stateholderUserSettings.getSpinTiltSensorEnabled() + "</"+ key39 +">\n";
		settings += "<"+ key40 +">" + MainActivity.stateholderUserSettings.getSpinTiltSensorValue() + "</"+ key40 +">\n";
		//2) ACCESS POINT NAME MOB OPER
		settings += "<"+ key41 +">" + MainActivity.stateholderUserSettings.getAccessPointNameMobOperEnabled() + "</"+ key41 +">\n";
		settings += "<"+ key42 +">" + MainActivity.stateholderUserSettings.getAccessPointNameMobOperChecked() + "</"+ key42 +">\n";
		settings += "<"+ key43 +">" + MainActivity.stateholderUserSettings.getAccessPointNameMobOperSpinEnabled() + "</"+ key43 +">\n";
		settings += "<"+ key44 +">" + MainActivity.stateholderUserSettings.getAccessPointNameMobOperSpinValue() + "</"+ key44 +">\n";
		settings += "<"+ key45 +">" + MainActivity.stateholderUserSettings.getEtAccessPointNameMobOperEnabled() + "</"+ key45 +">\n";
		settings += "<"+ key46 +">" + MainActivity.stateholderUserSettings.getEtAccessPointNameMobOperValue() + "</"+ key46 +">\n";
		//4) SENSOR IN SECURITY MODE
		settings += "<"+ key47 +">" + MainActivity.stateholderUserSettings.getCbSensInSecurityModeEnabled() + "</"+ key47 +">\n";
		settings += "<"+ key48 +">" + MainActivity.stateholderUserSettings.getCbSensInSecurityModeChecked() + "</"+ key48 +">\n";
		settings += "<"+ key49 +">" + MainActivity.stateholderUserSettings.getSpinSensInSecurityModeEnabled() + "</"+ key49 +">\n";
		settings += "<"+ key50 +">" + MainActivity.stateholderUserSettings.getSpinSensInSecurityModeValue() + "</"+ key50 +">\n";
		//5) SHOCK SENSOR SETTINGS
		settings += "<"+ key51 +">" + MainActivity.stateholderUserSettings.getCbShockSensSettingsEnabled() + "</"+ key51 +">\n";
		settings += "<"+ key52 +">" + MainActivity.stateholderUserSettings.getCbShockSensSettingsChecked() + "</"+ key52 +">\n";
		settings += "<"+ key53 +">" + MainActivity.stateholderUserSettings.getCbWarningZoneDisabledEnabled() + "</"+ key53 +">\n";
		settings += "<"+ key54 +">" + MainActivity.stateholderUserSettings.getCbWarningZoneDisabledChecked() + "</"+ key54 +">\n";
		settings += "<"+ key55 +">" + MainActivity.stateholderUserSettings.getSbWarningTrasholdEnabled() + "</"+ key55 +">\n";
		settings += "<"+ key56 +">" + MainActivity.stateholderUserSettings.getSbWarningTrasholdValue() + "</"+ key56 +">\n";
		settings += "<"+ key57 +">" + MainActivity.stateholderUserSettings.getEtWarningTrasholdEnabled() + "</"+ key57 +">\n";
		settings += "<"+ key58 +">" + MainActivity.stateholderUserSettings.getEtWarningTrasholdValue() + "</"+ key58 +">\n";
		settings += "<"+ key59 +">" + MainActivity.stateholderUserSettings.getCbAlarmZoneDisabledEnabled() + "</"+ key59 +">\n";
		settings += "<"+ key60 +">" + MainActivity.stateholderUserSettings.getCbAlarmZoneDisabledChecked() + "</"+ key60 +">\n";
		settings += "<"+ key61 +">" + MainActivity.stateholderUserSettings.getSbAlarmTrasholdEnabled() + "</"+ key61 +">\n";
		settings += "<"+ key62 +">" + MainActivity.stateholderUserSettings.getSbAlarmTrasholdValue() + "</"+ key62 +">\n";
		settings += "<"+ key63 +">" + MainActivity.stateholderUserSettings.getEtAlarmTrasholdEnabled() + "</"+ key63 +">\n";
		settings += "<"+ key64 +">" + MainActivity.stateholderUserSettings.getEtAlarmTrasholdValue() + "</"+ key64 +">\n";
		//6) TILT SENSOR
		settings += "<"+ key65 +">" + MainActivity.stateholderUserSettings.getCbTiltSensorEnabled() + "</"+ key65 +">\n";
		settings += "<"+ key66 +">" + MainActivity.stateholderUserSettings.getCbTiltSensorChecked() + "</"+ key66 +">\n";
		settings += "<"+ key67 +">" + MainActivity.stateholderUserSettings.getCbTiltSensorDisabledEnabled() + "</"+ key67 +">\n";
		settings += "<"+ key68 +">" + MainActivity.stateholderUserSettings.getCbTiltSensorDisabledChecked() + "</"+ key68 +">\n";
		settings += "<"+ key69 +">" + MainActivity.stateholderUserSettings.getSbTiltSensorEnabled() + "</"+ key69 +">\n";
		settings += "<"+ key70 +">" + MainActivity.stateholderUserSettings.getSbTiltSensorValue() + "</"+ key70 +">\n";
		settings += "<"+ key71 +">" + MainActivity.stateholderUserSettings.getEtTiltSensorEnabled() + "</"+ key71 +">\n";
		settings += "<"+ key72 +">" + MainActivity.stateholderUserSettings.getEtTiltSensorValue() + "</"+ key72 +">\n";
		settings += "<"+ key73 +">" + MainActivity.stateholderUserSettings.getCbTiltSensorInArmModeEnabled() + "</"+ key73 +">\n";
		settings += "<"+ key74 +">" + MainActivity.stateholderUserSettings.getTiltSensorInArmModeChecked() + "</"+ key74 +">\n";
		//7) MONITORING SETTINGS
		settings += "<"+ key75 +">" + MainActivity.stateholderUserSettings.getCbMonitoringSettingsEnabled() + "</"+ key75 +">\n";
		settings += "<"+ key76 +">" + MainActivity.stateholderUserSettings.getCbMonitoringSettingsChecked() + "</"+ key76 +">\n";
		settings += "<"+ key77 +">" + MainActivity.stateholderUserSettings.getMonitoringSettingsEnabled() + "</"+ key77 +">\n";
		settings += "<"+ key78 +">" + MainActivity.stateholderUserSettings.getMonitoringSettingsValue() + "</"+ key78 +">\n";
		//8) DISARMING SYSTEM
		settings += "<"+ key79 +">" + MainActivity.stateholderUserSettings.getCbDisarmingSystemEnabled() + "</"+ key79 +">\n";
		settings += "<"+ key80 +">" + MainActivity.stateholderUserSettings.getCbDisarmingSystemChecked() + "</"+ key80 +">\n";
		settings += "<"+ key81 +">" + MainActivity.stateholderUserSettings.getSpinDisarmingSystemEnabled() + "</"+ key81 +">\n";
		settings += "<"+ key82 +">" + MainActivity.stateholderUserSettings.getSpinDisarmingSystemValue() + "</"+ key82 +">\n";
		//9) MICROPHONE
		settings += "<"+ key83 +">" + MainActivity.stateholderUserSettings.getCbPhoneSensitivityEnabled() + "</"+ key83 +">\n";
		settings += "<"+ key84 +">" + MainActivity.stateholderUserSettings.getCbPhoneSensitivityChecked() + "</"+ key84 +">\n";
		settings += "<"+ key85 +">" + MainActivity.stateholderUserSettings.getCbPhoneSensitivityDisabledEnabled() + "</"+ key85 +">\n";
		settings += "<"+ key86 +">" + MainActivity.stateholderUserSettings.getCbPhoneSensitivityDisabledChecked() + "</"+ key86 +">\n";
		settings += "<"+ key87 +">" + MainActivity.stateholderUserSettings.getSbPhoneSensitivityEnabled() + "</"+ key87 +">\n";
		settings += "<"+ key88 +">" + MainActivity.stateholderUserSettings.getSbPhoneSensitivityValue() + "</"+ key88 +">\n";
		settings += "<"+ key89 +">" + MainActivity.stateholderUserSettings.getEtPhoneSensitivityEnabled() + "</"+ key89 +">\n";
		settings += "<"+ key90 +">" + MainActivity.stateholderUserSettings.getEtPhoneSensitivityValue() + "</"+ key90 +">\n";
		//10) SPEAKER
		settings += "<"+ key91 +">" + MainActivity.stateholderUserSettings.getCbSpeakerVolumeEnabled() + "</"+ key91 +">\n";
		settings += "<"+ key92 +">" + MainActivity.stateholderUserSettings.getCbSpeakerVolumeChecked() + "</"+ key92 +">\n";
		settings += "<"+ key93 +">" + MainActivity.stateholderUserSettings.getCbSpeakerVolumeDisabledEnabled() + "</"+ key93 +">\n";
		settings += "<"+ key94 +">" + MainActivity.stateholderUserSettings.getCbSpeakerVolumeDisabledChecked() + "</"+ key94 +">\n";
		settings += "<"+ key95 +">" + MainActivity.stateholderUserSettings.getSbSpeakerVolumeEnabled() + "</"+ key95 +">\n";
		settings += "<"+ key96 +">" + MainActivity.stateholderUserSettings.getSbSpeakerVolumeValue() + "</"+ key96 +">\n";
		settings += "<"+ key97 +">" + MainActivity.stateholderUserSettings.getEtSpeakerVolumeEnabled() + "</"+ key97 +">\n";
		settings += "<"+ key98 +">" + MainActivity.stateholderUserSettings.getEtSpeakerVolumeValue() + "</"+ key98 +">\n";
//INPUTS
		//IN1
		settings += "<"+ key99 +">" + MainActivity.stateholderInputs.getCbInputsIn1Enabled() + "</"+ key99 +">\n";
		settings += "<"+ key100 +">" + MainActivity.stateholderInputs.getCbInputsIn1Checked() + "</"+ key100 +">\n";
		settings += "<"+ key101 +">" + MainActivity.stateholderInputs.getSpinInputFunctionsIN1Enabled() + "</"+ key101 +">\n";
		settings += "<"+ key102 +">" + MainActivity.stateholderInputs.getSpinInputFunctionsIN1Value() + "</"+ key102 +">\n";
		settings += "<"+ key103 +">" + MainActivity.stateholderInputs.getSpinPolarityIN1Enabled() + "</"+ key103 +">\n";
		settings += "<"+ key104 +">" + MainActivity.stateholderInputs.getSpinPolarityIN1Value() + "</"+ key104 +">\n";
		settings += "<"+ key105 +">" + MainActivity.stateholderInputs.getSpinInputIsActiveIN1Enabled() + "</"+ key105 +">\n";
		settings += "<"+ key106 +">" + MainActivity.stateholderInputs.getSpinInputIsActiveIN1Value() + "</"+ key106 +">\n";
		settings += "<"+ key107 +">" + MainActivity.stateholderInputs.getEtMinClosingTimeIN1Enabled() + "</"+ key107 +">\n";
		settings += "<"+ key108 +">" + MainActivity.stateholderInputs.getEtMinClosingTimeIN1Value() + "</"+ key108 +">\n";
		settings += "<"+ key109 +">" + MainActivity.stateholderInputs.getEtMinOpeningTimeIN1Enabled() + "</"+ key109 +">\n";
		settings += "<"+ key110 +">" + MainActivity.stateholderInputs.getEtMinOpeningTimeIN1Value() + "</"+ key110 +">\n";
		//IN2
		settings += "<"+ key111 +">" + MainActivity.stateholderInputs.getCbInputsIn2Enabled() + "</"+ key111 +">\n";
		settings += "<"+ key112 +">" + MainActivity.stateholderInputs.getCbInputsIn2Checked() + "</"+ key112 +">\n";
		settings += "<"+ key113 +">" + MainActivity.stateholderInputs.getSpinInputFunctionsIN2Enabled() + "</"+ key113 +">\n";
		settings += "<"+ key114 +">" + MainActivity.stateholderInputs.getSpinInputFunctionsIN2Value() + "</"+ key114 +">\n";
		settings += "<"+ key115 +">" + MainActivity.stateholderInputs.getSpinPolarityIN2Enabled() + "</"+ key115 +">\n";
		settings += "<"+ key116 +">" + MainActivity.stateholderInputs.getSpinPolarityIN2Value() + "</"+ key116 +">\n";
		settings += "<"+ key117 +">" + MainActivity.stateholderInputs.getSpinInputIsActiveIN2Enabled() + "</"+ key117 +">\n";
		settings += "<"+ key118 +">" + MainActivity.stateholderInputs.getSpinInputIsActiveIN2Value() + "</"+ key118 +">\n";
		settings += "<"+ key119 +">" + MainActivity.stateholderInputs.getEtMinClosingTimeIN2Enabled() + "</"+ key119 +">\n";
		settings += "<"+ key120 +">" + MainActivity.stateholderInputs.getEtMinClosingTimeIN2Value() + "</"+ key120 +">\n";
		settings += "<"+ key121 +">" + MainActivity.stateholderInputs.getEtMinOpeningTimeIN2Enabled() + "</"+ key121 +">\n";
		settings += "<"+ key122 +">" + MainActivity.stateholderInputs.getEtMinOpeningTimeIN2Value() + "</"+ key122 +">\n";
		//IN3
		settings += "<"+ key123 +">" + MainActivity.stateholderInputs.getCbInputsIn3Enabled() + "</"+ key123 +">\n";
		settings += "<"+ key124 +">" + MainActivity.stateholderInputs.getCbInputsIn3Checked() + "</"+ key124 +">\n";
		settings += "<"+ key125 +">" + MainActivity.stateholderInputs.getSpinInputFunctionsIN3Enabled() + "</"+ key125 +">\n";
		settings += "<"+ key126 +">" + MainActivity.stateholderInputs.getSpinInputFunctionsIN3Value() + "</"+ key126 +">\n";
		settings += "<"+ key127 +">" + MainActivity.stateholderInputs.getSpinPolarityIN3Enabled() + "</"+ key127 +">\n";
		settings += "<"+ key128 +">" + MainActivity.stateholderInputs.getSpinPolarityIN3Value() + "</"+ key128 +">\n";
		settings += "<"+ key129 +">" + MainActivity.stateholderInputs.getSpinInputIsActiveIN3Enabled() + "</"+ key129 +">\n";
		settings += "<"+ key130 +">" + MainActivity.stateholderInputs.getSpinInputIsActiveIN3Value() + "</"+ key130 +">\n";
		settings += "<"+ key131 +">" + MainActivity.stateholderInputs.getEtMinClosingTimeIN3Enabled() + "</"+ key131 +">\n";
		settings += "<"+ key132 +">" + MainActivity.stateholderInputs.getEtMinClosingTimeIN3Value() + "</"+ key132 +">\n";
		settings += "<"+ key133 +">" + MainActivity.stateholderInputs.getEtMinOpeningTimeIN3Enabled() + "</"+ key133 +">\n";
		settings += "<"+ key134 +">" + MainActivity.stateholderInputs.getEtMinOpeningTimeIN3Value() + "</"+ key134 +">\n";
		//IN4
		settings += "<"+ key135 +">" + MainActivity.stateholderInputs.getCbInputsIn4Enabled() + "</"+ key135 +">\n";
		settings += "<"+ key136 +">" + MainActivity.stateholderInputs.getCbInputsIn4Checked() + "</"+ key136 +">\n";
		settings += "<"+ key137 +">" + MainActivity.stateholderInputs.getSpinInputFunctionsIN4Enabled() + "</"+ key137 +">\n";
		settings += "<"+ key138 +">" + MainActivity.stateholderInputs.getSpinInputFunctionsIN4Value() + "</"+ key138 +">\n";
		settings += "<"+ key139 +">" + MainActivity.stateholderInputs.getSpinPolarityIN4Enabled() + "</"+ key139 +">\n";
		settings += "<"+ key140 +">" + MainActivity.stateholderInputs.getSpinPolarityIN4Value() + "</"+ key140 +">\n";
		settings += "<"+ key141 +">" + MainActivity.stateholderInputs.getSpinInputIsActiveIN4Enabled() + "</"+ key141 +">\n";
		settings += "<"+ key142 +">" + MainActivity.stateholderInputs.getSpinInputIsActiveIN4Value() + "</"+ key142 +">\n";
		settings += "<"+ key143 +">" + MainActivity.stateholderInputs.getEtMinClosingTimeIN4Enabled() + "</"+ key143 +">\n";
		settings += "<"+ key144 +">" + MainActivity.stateholderInputs.getEtMinClosingTimeIN4Value() + "</"+ key144 +">\n";
		settings += "<"+ key145 +">" + MainActivity.stateholderInputs.getEtMinOpeningTimeIN4Enabled() + "</"+ key145 +">\n";
		settings += "<"+ key146 +">" + MainActivity.stateholderInputs.getEtMinOpeningTimeIN4Value() + "</"+ key146 +">\n";
		//IN5
		settings += "<"+ key147 +">" + MainActivity.stateholderInputs.getCbInputsIn5Enabled() + "</"+ key147 +">\n";
		settings += "<"+ key148 +">" + MainActivity.stateholderInputs.getCbInputsIn5Checked() + "</"+ key148 +">\n";
		settings += "<"+ key149 +">" + MainActivity.stateholderInputs.getSpinInputFunctionsIN5Enabled() + "</"+ key149 +">\n";
		settings += "<"+ key150 +">" + MainActivity.stateholderInputs.getSpinInputFunctionsIN5Value() + "</"+ key150 +">\n";
		settings += "<"+ key151 +">" + MainActivity.stateholderInputs.getSpinPolarityIN5Enabled() + "</"+ key151 +">\n";
		settings += "<"+ key152 +">" + MainActivity.stateholderInputs.getSpinPolarityIN5Value() + "</"+ key152 +">\n";
		settings += "<"+ key153 +">" + MainActivity.stateholderInputs.getSpinInputIsActiveIN5Enabled() + "</"+ key153 +">\n";
		settings += "<"+ key154 +">" + MainActivity.stateholderInputs.getSpinInputIsActiveIN5Value() + "</"+ key154 +">\n";
		settings += "<"+ key155 +">" + MainActivity.stateholderInputs.getEtMinClosingTimeIN5Enabled() + "</"+ key155 +">\n";
		settings += "<"+ key156 +">" + MainActivity.stateholderInputs.getEtMinClosingTimeIN5Value() + "</"+ key156 +">\n";
		settings += "<"+ key157 +">" + MainActivity.stateholderInputs.getEtMinOpeningTimeIN5Enabled() + "</"+ key157 +">\n";
		settings += "<"+ key158 +">" + MainActivity.stateholderInputs.getEtMinOpeningTimeIN5Value() + "</"+ key158 +">\n";
		//IN6
		settings += "<"+ key159 +">" + MainActivity.stateholderInputs.getCbInputsIn6Enabled() + "</"+ key159 +">\n";
		settings += "<"+ key160 +">" + MainActivity.stateholderInputs.getCbInputsIn6Checked() + "</"+ key160 +">\n";
		settings += "<"+ key161 +">" + MainActivity.stateholderInputs.getSpinInputFunctionsIN6Enabled() + "</"+ key161 +">\n";
		settings += "<"+ key162 +">" + MainActivity.stateholderInputs.getSpinInputFunctionsIN6Value() + "</"+ key162 +">\n";
		settings += "<"+ key163 +">" + MainActivity.stateholderInputs.getSpinPolarityIN6Enabled() + "</"+ key163 +">\n";
		settings += "<"+ key164 +">" + MainActivity.stateholderInputs.getSpinPolarityIN6Value() + "</"+ key164 +">\n";
		settings += "<"+ key165 +">" + MainActivity.stateholderInputs.getSpinInputIsActiveIN6Enabled() + "</"+ key165 +">\n";
		settings += "<"+ key166 +">" + MainActivity.stateholderInputs.getSpinInputIsActiveIN6Value() + "</"+ key166 +">\n";
		settings += "<"+ key167 +">" + MainActivity.stateholderInputs.getEtMinClosingTimeIN6Enabled() + "</"+ key167 +">\n";
		settings += "<"+ key168 +">" + MainActivity.stateholderInputs.getEtMinClosingTimeIN6Value() + "</"+ key168 +">\n";
		settings += "<"+ key169 +">" + MainActivity.stateholderInputs.getEtMinOpeningTimeIN6Enabled() + "</"+ key169 +">\n";
		settings += "<"+ key170 +">" + MainActivity.stateholderInputs.getEtMinOpeningTimeIN6Value() + "</"+ key170 +">\n";
		//IN7
		settings += "<"+ key171 +">" + MainActivity.stateholderInputs.getCbInputsIn7Enabled() + "</"+ key171 +">\n";
		settings += "<"+ key172 +">" + MainActivity.stateholderInputs.getCbInputsIn7Checked() + "</"+ key172 +">\n";
		settings += "<"+ key173 +">" + MainActivity.stateholderInputs.getSpinInputFunctionsIN7Enabled() + "</"+ key173 +">\n";
		settings += "<"+ key174 +">" + MainActivity.stateholderInputs.getSpinInputFunctionsIN7Value() + "</"+ key174 +">\n";
		settings += "<"+ key175 +">" + MainActivity.stateholderInputs.getSpinPolarityIN7Enabled() + "</"+ key175 +">\n";
		settings += "<"+ key176 +">" + MainActivity.stateholderInputs.getSpinPolarityIN7Value() + "</"+ key176 +">\n";
		settings += "<"+ key177 +">" + MainActivity.stateholderInputs.getSpinInputIsActiveIN7Enabled() + "</"+ key177 +">\n";
		settings += "<"+ key178 +">" + MainActivity.stateholderInputs.getSpinInputIsActiveIN7Value() + "</"+ key178 +">\n";
		settings += "<"+ key179 +">" + MainActivity.stateholderInputs.getEtMinClosingTimeIN7Enabled() + "</"+ key179 +">\n";
		settings += "<"+ key180 +">" + MainActivity.stateholderInputs.getEtMinClosingTimeIN7Value() + "</"+ key180 +">\n";
		settings += "<"+ key181 +">" + MainActivity.stateholderInputs.getEtMinOpeningTimeIN7Enabled() + "</"+ key181 +">\n";
		settings += "<"+ key182 +">" + MainActivity.stateholderInputs.getEtMinOpeningTimeIN7Value() + "</"+ key182 +">\n";
		//IN8
		settings += "<"+ key183 +">" + MainActivity.stateholderInputs.getCbInputsIn8Enabled() + "</"+ key183 +">\n";
		settings += "<"+ key184 +">" + MainActivity.stateholderInputs.getCbInputsIn8Checked() + "</"+ key184 +">\n";
		settings += "<"+ key185 +">" + MainActivity.stateholderInputs.getSpinInputFunctionsIN8Enabled() + "</"+ key185 +">\n";
		settings += "<"+ key186 +">" + MainActivity.stateholderInputs.getSpinInputFunctionsIN8Value() + "</"+ key186 +">\n";
		settings += "<"+ key187 +">" + MainActivity.stateholderInputs.getSpinPolarityIN8Enabled() + "</"+ key187 +">\n";
		settings += "<"+ key188 +">" + MainActivity.stateholderInputs.getSpinPolarityIN8Value() + "</"+ key188 +">\n";
		settings += "<"+ key189 +">" + MainActivity.stateholderInputs.getSpinInputIsActiveIN8Enabled() + "</"+ key189 +">\n";
		settings += "<"+ key190 +">" + MainActivity.stateholderInputs.getSpinInputIsActiveIN8Value() + "</"+ key190 +">\n";
		settings += "<"+ key191 +">" + MainActivity.stateholderInputs.getEtMinClosingTimeIN8Enabled() + "</"+ key191 +">\n";
		settings += "<"+ key192 +">" + MainActivity.stateholderInputs.getEtMinClosingTimeIN8Value() + "</"+ key192 +">\n";
		settings += "<"+ key193 +">" + MainActivity.stateholderInputs.getEtMinOpeningTimeIN8Enabled() + "</"+ key193 +">\n";
		settings += "<"+ key194 +">" + MainActivity.stateholderInputs.getEtMinOpeningTimeIN8Value() + "</"+ key194 +">\n";
//OUTPUTS
		//OUT1
		settings += "<"+ key195 +">" + MainActivity.stateholderOutputs.getCbOutputsOUT1Enabled() + "</"+ key195 +">\n";
		settings += "<"+ key196 +">" + MainActivity.stateholderOutputs.getCbOutputsOUT1Checked() + "</"+ key196 +">\n";
		settings += "<"+ key197 +">" + MainActivity.stateholderOutputs.getSpinOutputFunctionsOUT11Enabled() + "</"+ key197 +">\n";
		settings += "<"+ key198 +">" + MainActivity.stateholderOutputs.getSpinOutputFunctionsOUT1Value() + "</"+ key198 +">\n";
		//OUT2
		settings += "<"+ key199 +">" + MainActivity.stateholderOutputs.getCbOutputsOUT2Enabled() + "</"+ key199 +">\n";
		settings += "<"+ key200 +">" + MainActivity.stateholderOutputs.getCbOutputsOUT2Checked() + "</"+ key200 +">\n";
		settings += "<"+ key201 +">" + MainActivity.stateholderOutputs.getSpinOutputFunctionsOUT22Enabled() + "</"+ key201 +">\n";
		settings += "<"+ key202 +">" + MainActivity.stateholderOutputs.getSpinOutputFunctionsOUT2Value() + "</"+ key202 +">\n";
		//OUT3
		settings += "<"+ key203 +">" + MainActivity.stateholderOutputs.getCbOutputsOUT3Enabled() + "</"+ key203 +">\n";
		settings += "<"+ key204 +">" + MainActivity.stateholderOutputs.getCbOutputsOUT3Checked() + "</"+ key204 +">\n";
		settings += "<"+ key205 +">" + MainActivity.stateholderOutputs.getSpinOutputFunctionsOUT33Enabled() + "</"+ key205 +">\n";
		settings += "<"+ key206 +">" + MainActivity.stateholderOutputs.getSpinOutputFunctionsOUT3Value() + "</"+ key206 +">\n";
		//OUT4
		settings += "<"+ key207 +">" + MainActivity.stateholderOutputs.getCbOutputsOUT4Enabled() + "</"+ key207 +">\n";
		settings += "<"+ key208 +">" + MainActivity.stateholderOutputs.getCbOutputsOUT4Checked() + "</"+ key208 +">\n";
		settings += "<"+ key209 +">" + MainActivity.stateholderOutputs.getSpinOutputFunctionsOUT44Enabled() + "</"+ key209 +">\n";
		settings += "<"+ key210 +">" + MainActivity.stateholderOutputs.getSpinOutputFunctionsOUT4Value() + "</"+ key210 +">\n";
		//OUT5
		settings += "<"+ key211 +">" + MainActivity.stateholderOutputs.getCbOutputsOUT5Enabled() + "</"+ key211 +">\n";
		settings += "<"+ key212 +">" + MainActivity.stateholderOutputs.getCbOutputsOUT5Checked() + "</"+ key212 +">\n";
		settings += "<"+ key213 +">" + MainActivity.stateholderOutputs.getSpinOutputFunctionsOUT55Enabled() + "</"+ key213 +">\n";
		settings += "<"+ key214 +">" + MainActivity.stateholderOutputs.getSpinOutputFunctionsOUT5Value() + "</"+ key214 +">\n";
		//OUT6
		settings += "<"+ key215 +">" + MainActivity.stateholderOutputs.getCbOutputsOUT6Enabled() + "</"+ key215 +">\n";
		settings += "<"+ key216 +">" + MainActivity.stateholderOutputs.getCbOutputsOUT6Checked() + "</"+ key216 +">\n";
		settings += "<"+ key217 +">" + MainActivity.stateholderOutputs.getSpinOutputFunctionsOUT66Enabled() + "</"+ key217 +">\n";
		settings += "<"+ key218 +">" + MainActivity.stateholderOutputs.getSpinOutputFunctionsOUT6Value() + "</"+ key218 +">\n";
//TIMERS
		//Channel1
		settings += "<"+ key219 +">" + MainActivity.stateholderTimer.getCbUniversalChannel1Enabled() + "</"+ key219+">\n";
		settings += "<"+ key220 +">" + MainActivity.stateholderTimer.getCbUniversalChannel1Checked() + "</"+ key220 +">\n";
		settings += "<"+ key221 +">" + MainActivity.stateholderTimer.getCbArmingUC1Enabled() + "</"+ key221 +">\n";
		settings += "<"+ key222 +">" + MainActivity.stateholderTimer.getCbArmingUC1Checked() + "</"+ key222 +">\n";
		settings += "<"+ key223 +">" + MainActivity.stateholderTimer.getCbSysDisarmUC1Enabled() + "</"+ key223 +">\n";
		settings += "<"+ key224 +">" + MainActivity.stateholderTimer.getCbSysDisarmUC1Checked() + "</"+ key224 +">\n";
		settings += "<"+ key225 +">" + MainActivity.stateholderTimer.getCbIgnitionSwitchOnUC1Enabled() + "</"+ key225 +">\n";
		settings += "<"+ key226 +">" + MainActivity.stateholderTimer.getCbIgnitionSwitchOnUC1Checked() + "</"+ key226 +">\n";
		settings += "<"+ key227 +">" + MainActivity.stateholderTimer.getCbIgnitiOffSwitchOffUC1Enabled() + "</"+ key227 +">\n";
		settings += "<"+ key228 +">" + MainActivity.stateholderTimer.getCbIgnitiOffSwitchOffUC1Checked() + "</"+ key228 +">\n";
		settings += "<"+ key229 +">" + MainActivity.stateholderTimer.getCbCommandFromPhoneUC1Enabled() + "</"+ key229 +">\n";
		settings += "<"+ key230 +">" + MainActivity.stateholderTimer.getCbCommandFromPhoneUC1Checked() + "</"+ key230 +">\n";
		settings += "<"+ key231 +">" + MainActivity.stateholderTimer.getArming_DelayUC1Enabled() + "</"+ key231 +">\n";
		settings += "<"+ key232 +">" + MainActivity.stateholderTimer.getArming_DelayUC1Value() + "</"+ key232 +">\n";
		settings += "<"+ key233 +">" + MainActivity.stateholderTimer.getArming_OperationTimeUC1Enabled() + "</"+ key233 +">\n";
		settings += "<"+ key234 +">" + MainActivity.stateholderTimer.getArming_OperationTimeUC1Value() + "</"+ key234 +">\n";
		settings += "<"+ key235 +">" + MainActivity.stateholderTimer.getSysDisarm_DelayUC1Enabled() + "</"+ key235 +">\n";
		settings += "<"+ key236 +">" + MainActivity.stateholderTimer.getSysDisarm_DelayUC1Value() + "</"+ key236 +">\n";
		settings += "<"+ key237 +">" + MainActivity.stateholderTimer.getSysDisarm_OperationTimeUC1Enabled() + "</"+ key237 +">\n";
		settings += "<"+ key238 +">" + MainActivity.stateholderTimer.getSysDisarm_OperationTimeUC1Value() + "</"+ key238 +">\n";
		settings += "<"+ key239 +">" + MainActivity.stateholderTimer.getIgnitionSwitchOn_DelayUC1Enabled() + "</"+ key239 +">\n";
		settings += "<"+ key240 +">" + MainActivity.stateholderTimer.getIgnitionSwitchOn_DelayUC1Value() + "</"+ key240 +">\n";
		settings += "<"+ key241 +">" + MainActivity.stateholderTimer.getIgnitionSwitchOn_OperationTimeUC1Enabled() + "</"+ key241 +">\n";
		settings += "<"+ key242 +">" + MainActivity.stateholderTimer.getIgnitionSwitchOn_OperationTimeUC1Value() + "</"+ key242 +">\n";
		settings += "<"+ key243 +">" + MainActivity.stateholderTimer.getIgnitionSwitchOff_DelayUC1Enabled() + "</"+ key243 +">\n";
		settings += "<"+ key244 +">" + MainActivity.stateholderTimer.getIgnitionSwitchOff_DelayUC1Value() + "</"+ key244 +">\n";
		settings += "<"+ key245 +">" + MainActivity.stateholderTimer.getIgnitionSwitchOff_OperationTimeUC1Enabled() + "</"+ key245 +">\n";
		settings += "<"+ key246 +">" + MainActivity.stateholderTimer.getIgnitionSwitchOff_OperationTimeUC1Value() + "</"+ key246 +">\n";
		settings += "<"+ key247 +">" + MainActivity.stateholderTimer.getCommandFromPhone_DelayUC1Enabled() + "</"+ key247 +">\n";
		settings += "<"+ key248 +">" + MainActivity.stateholderTimer.getCommandFromPhone_DelayUC1Value() + "</"+ key248 +">\n";
		settings += "<"+ key249 +">" + MainActivity.stateholderTimer.getCommandFromPhone_OperationTimeUC1Enabled() + "</"+ key249 +">\n";
		settings += "<"+ key250 +">" + MainActivity.stateholderTimer.getCommandFromPhone_OperationTimeUC1Value() + "</"+ key250 +">\n";
		//Channel2
		settings += "<"+ key251 +">" + MainActivity.stateholderTimer.getCbUniversalChannel2Enabled() + "</"+ key251+">\n";
		settings += "<"+ key252 +">" + MainActivity.stateholderTimer.getCbUniversalChannel2Checked() + "</"+ key252 +">\n";
		settings += "<"+ key253 +">" + MainActivity.stateholderTimer.getCbArmingUC2Enabled() + "</"+ key253 +">\n";
		settings += "<"+ key254 +">" + MainActivity.stateholderTimer.getCbArmingUC2Checked() + "</"+ key254 +">\n";
		settings += "<"+ key255 +">" + MainActivity.stateholderTimer.getCbSysDisarmUC2Enabled() + "</"+ key255 +">\n";
		settings += "<"+ key256 +">" + MainActivity.stateholderTimer.getCbSysDisarmUC2Checked() + "</"+ key256 +">\n";
		settings += "<"+ key257 +">" + MainActivity.stateholderTimer.getCbIgnitionSwitchOnUC2Enabled() + "</"+ key257 +">\n";
		settings += "<"+ key258 +">" + MainActivity.stateholderTimer.getCbIgnitionSwitchOnUC2Checked() + "</"+ key258 +">\n";
		settings += "<"+ key259 +">" + MainActivity.stateholderTimer.getCbIgnitiOffSwitchOffUC2Enabled() + "</"+ key259 +">\n";
		settings += "<"+ key260 +">" + MainActivity.stateholderTimer.getCbIgnitiOffSwitchOffUC2Checked() + "</"+ key260 +">\n";
		settings += "<"+ key261 +">" + MainActivity.stateholderTimer.getCbCommandFromPhoneUC2Enabled() + "</"+ key261 +">\n";
		settings += "<"+ key262 +">" + MainActivity.stateholderTimer.getCbCommandFromPhoneUC2Checked() + "</"+ key262 +">\n";
		settings += "<"+ key263 +">" + MainActivity.stateholderTimer.getArming_DelayUC2Enabled() + "</"+ key263 +">\n";
		settings += "<"+ key264 +">" + MainActivity.stateholderTimer.getArming_DelayUC2Value() + "</"+ key264 +">\n";
		settings += "<"+ key265 +">" + MainActivity.stateholderTimer.getArming_OperationTimeUC2Enabled() + "</"+ key265 +">\n";
		settings += "<"+ key266 +">" + MainActivity.stateholderTimer.getArming_OperationTimeUC2Value() + "</"+ key266 +">\n";
		settings += "<"+ key267 +">" + MainActivity.stateholderTimer.getSysDisarm_DelayUC2Enabled() + "</"+ key267 +">\n";
		settings += "<"+ key268 +">" + MainActivity.stateholderTimer.getSysDisarm_DelayUC2Value() + "</"+ key268 +">\n";
		settings += "<"+ key269 +">" + MainActivity.stateholderTimer.getSysDisarm_OperationTimeUC2Enabled() + "</"+ key269 +">\n";
		settings += "<"+ key270 +">" + MainActivity.stateholderTimer.getSysDisarm_OperationTimeUC2Value() + "</"+ key270 +">\n";
		settings += "<"+ key271 +">" + MainActivity.stateholderTimer.getIgnitionSwitchOn_DelayUC2Enabled() + "</"+ key271 +">\n";
		settings += "<"+ key272 +">" + MainActivity.stateholderTimer.getIgnitionSwitchOn_DelayUC2Value() + "</"+ key272 +">\n";
		settings += "<"+ key273 +">" + MainActivity.stateholderTimer.getIgnitionSwitchOn_OperationTimeUC2Enabled() + "</"+ key273 +">\n";
		settings += "<"+ key274 +">" + MainActivity.stateholderTimer.getIgnitionSwitchOn_OperationTimeUC2Value() + "</"+ key274 +">\n";
		settings += "<"+ key275 +">" + MainActivity.stateholderTimer.getIgnitionSwitchOff_DelayUC2Enabled() + "</"+ key275 +">\n";
		settings += "<"+ key276 +">" + MainActivity.stateholderTimer.getIgnitionSwitchOff_DelayUC2Value() + "</"+ key276 +">\n";
		settings += "<"+ key277 +">" + MainActivity.stateholderTimer.getIgnitionSwitchOff_OperationTimeUC2Enabled() + "</"+ key277 +">\n";
		settings += "<"+ key278 +">" + MainActivity.stateholderTimer.getIgnitionSwitchOff_OperationTimeUC2Value() + "</"+ key278 +">\n";
		settings += "<"+ key279 +">" + MainActivity.stateholderTimer.getCommandFromPhone_DelayUC2Enabled() + "</"+ key279 +">\n";
		settings += "<"+ key280 +">" + MainActivity.stateholderTimer.getCommandFromPhone_DelayUC2Value() + "</"+ key280 +">\n";
		settings += "<"+ key281 +">" + MainActivity.stateholderTimer.getCommandFromPhone_OperationTimeUC2Enabled() + "</"+ key281 +">\n";
		settings += "<"+ key282 +">" + MainActivity.stateholderTimer.getCommandFromPhone_OperationTimeUC2Value() + "</"+ key282 +">\n";
		//OutputTimerOnArmDelay
		settings += "<"+ key283 +">" + MainActivity.stateholderTimer.getCbOutputTimerOnArmDelayEnabled() + "</"+ key283 +">\n";
		settings += "<"+ key284 +">" + MainActivity.stateholderTimer.getCbOutputTimerOnArmDelayChecked() + "</"+ key284 +">\n";
		settings += "<"+ key285 +">" + MainActivity.stateholderTimer.getOutputTimerOnArmOperationTimeEnabled() + "</"+ key285 +">\n";
		settings += "<"+ key286 +">" + MainActivity.stateholderTimer.getOutputTimerOnArmOperationTimeValue() + "</"+ key286 +">\n";
		settings += "<"+ key287 +">" + MainActivity.stateholderTimer.getOutputTimerOnArmDelayEnabled() + "</"+ key287 +">\n";
		settings += "<"+ key288 +">" + MainActivity.stateholderTimer.getOutputTimerOnArmDelayValue() + "</"+ key288 +">\n";
		//Two stage disarming system
		settings += "<"+ key289 +">" + MainActivity.stateholderTimer.getCbDisarmingSystemEnabled() + "</"+ key289 +">\n";
		settings += "<"+ key290 +">" + MainActivity.stateholderTimer.getCbDisarmingSystemChecked() + "</"+ key290 +">\n";
		settings += "<"+ key291 +">" + MainActivity.stateholderTimer.getSpinDisarmingSystemEnabled() + "</"+ key291 +">\n";
		settings += "<"+ key292 +">" + MainActivity.stateholderTimer.getSpinDisarmingSystemValue() + "</"+ key292 +">\n";
		//Rearming
		settings += "<"+ key293 +">" + MainActivity.stateholderTimer.getCbRearmingSystemEnabled() + "</"+ key293 +">\n";
		settings += "<"+ key294 +">" + MainActivity.stateholderTimer.getCbRearmingSystemChecked() + "</"+ key294 +">\n";
		settings += "<"+ key295 +">" + MainActivity.stateholderTimer.getSpinRearmingSystemEnabled() + "</"+ key295 +">\n";
		settings += "<"+ key296 +">" + MainActivity.stateholderTimer.getSpinRearmingSystemValue() + "</"+ key296 +">\n";
		//Turbotimer
		settings += "<"+ key297 +">" + MainActivity.stateholderTimer.getCbTurboTimerSettingsEnabled() + "</"+ key297 +">\n";
		settings += "<"+ key298 +">" + MainActivity.stateholderTimer.getCbUTurboTimerSettingsChecked() + "</"+ key298 +">\n";
		settings += "<"+ key299 +">" + MainActivity.stateholderTimer.getTurbotimerWorkingTimeEnabled() + "</"+ key299 +">\n";
		settings += "<"+ key300 +">" + MainActivity.stateholderTimer.getTurbotimerWorkingTimeValue() + "</"+ key300 +">\n";
		settings += "<"+ key301 +">" + MainActivity.stateholderTimer.getIngnTimeLockedEngineEnabled() + "</"+ key301 +">\n";
		settings += "<"+ key302 +">" + MainActivity.stateholderTimer.getIngnTimeLockedEngineValue() + "</"+ key302 +">\n";
		//Delay Time Engine Start
		settings += "<"+ key303 +">" + MainActivity.stateholderTimer.getCbDelayTimeEngineStartEnabled() + "</"+ key303 +">\n";
		settings += "<"+ key304 +">" + MainActivity.stateholderTimer.getCbDelayTimeEngineStartChecked() + "</"+ key304 +">\n";
		settings += "<"+ key305 +">" + MainActivity.stateholderTimer.getDelayTimeEngineStartEnabled() + "</"+ key305 +">\n";
		settings += "<"+ key306 +">" + MainActivity.stateholderTimer.getDelayTimeEngineStartValue() + "</"+ key306 +">\n";
		//Delay Time Limit Switches Survey
		settings += "<"+ key307 +">" + MainActivity.stateholderTimer.getCbDelayTimeLimitSwitchesSurveyEnabled() + "</"+ key307 +">\n";
		settings += "<"+ key308 +">" + MainActivity.stateholderTimer.getCbDelayTimeLimitSwitchesSurveyChecked() + "</"+ key308 +">\n";
		settings += "<"+ key309 +">" + MainActivity.stateholderTimer.getDelayTimeLimitSwitchesSurveyEnabled() + "</"+ key309 +">\n";
		settings += "<"+ key310 +">" + MainActivity.stateholderTimer.getDelayTimeLimitSwitchesSurveyValue() + "</"+ key310 +">\n";
		//Function13
		settings += "<"+ key579 +">" + MainActivity.stateholderTimer.getCbCentralLockTimer13Enabled() + "</"+ key579 +">\n";
		settings += "<"+ key580 +">" + MainActivity.stateholderTimer.getCbCentralLockTimer13Checked() + "</"+ key580 +">\n";
		settings += "<"+ key581 +">" + MainActivity.stateholderTimer.getSpinLockImpulsCloseCL13Enabled() + "</"+ key581 +">\n";
		settings += "<"+ key582 +">" + MainActivity.stateholderTimer.getSpinLockImpulsCloseCL13Value() + "</"+ key582 +">\n";
		settings += "<"+ key583 +">" + MainActivity.stateholderTimer.getFirstImpulsLongtime13Enabled() + "</"+ key583 +">\n";
		settings += "<"+ key584 +">" + MainActivity.stateholderTimer.getFirstImpulsLongtime13Value() + "</"+ key584 +">\n";
		settings += "<"+ key585 +">" + MainActivity.stateholderTimer.getPauseTimeBetwImpulses13Enabled() + "</"+ key585 +">\n";
		settings += "<"+ key586 +">" + MainActivity.stateholderTimer.getPauseTimeBetwImpulses13Value() + "</"+ key586 +">\n";
		settings += "<"+ key587 +">" + MainActivity.stateholderTimer.getSecondImpulsLongtime13Enabled() + "</"+ key587 +">\n";
		settings += "<"+ key588 +">" + MainActivity.stateholderTimer.getSecondImpulsLongtime13Value() + "</"+ key588 + ">\n";
		settings += "<"+ key589 +">" + MainActivity.stateholderTimer.getPauseTimeAfterIngAndImpStart13Enabled() + "</"+ key589 +">\n";
		settings += "<"+ key590 +">" + MainActivity.stateholderTimer.getPauseTimeAfterIngAndImpStart13Value() + "</"+ key590 + ">\n";
		//Function14
		settings += "<"+ key591 +">" + MainActivity.stateholderTimer.getCbCentralLockTimer14Enabled() + "</"+ key591 +">\n";
		settings += "<"+ key592 +">" + MainActivity.stateholderTimer.getCbCentralLockTimer14Checked() + "</"+ key592 +">\n";
		settings += "<"+ key593 +">" + MainActivity.stateholderTimer.getSpinLockImpulsCloseCL14Enabled() + "</"+ key593 +">\n";
		settings += "<"+ key594 +">" + MainActivity.stateholderTimer.getSpinLockImpulsCloseCL14Value() + "</"+ key594 +">\n";
		settings += "<"+ key595 +">" + MainActivity.stateholderTimer.getFirstImpulsLongtime14Enabled() + "</"+ key595 +">\n";
		settings += "<"+ key596 +">" + MainActivity.stateholderTimer.getFirstImpulsLongtime14Value() + "</"+ key596 +">\n";
		settings += "<"+ key597 +">" + MainActivity.stateholderTimer.getPauseTimeBetwImpulses14Enabled() + "</"+ key597 +">\n";
		settings += "<"+ key598 +">" + MainActivity.stateholderTimer.getPauseTimeBetwImpulses14Value() + "</"+ key598 +">\n";
		settings += "<"+ key599 +">" + MainActivity.stateholderTimer.getSecondImpulsLongtime14Enabled() + "</"+ key599 +">\n";
		settings += "<"+ key600 +">" + MainActivity.stateholderTimer.getSecondImpulsLongtime14Value() + "</"+ key600 + ">\n";
//NOTIFICATIONS
		//User Enable checkbox
		settings += "<"+ key311 +">" + MainActivity.stateholderNotification.getCbInputsInEnabled(0) + "</"+ key311 +">\n";
		settings += "<"+ key312 +">" + MainActivity.stateholderNotification.getCbInputsInChecked(0) + "</"+ key312 +">\n";
		settings += "<"+ key313 +">" + MainActivity.stateholderNotification.getCbInputsInEnabled(1) + "</"+ key313 +">\n";
		settings += "<"+ key314 +">" + MainActivity.stateholderNotification.getCbInputsInChecked(1) + "</"+ key314 +">\n";
		settings += "<"+ key315 +">" + MainActivity.stateholderNotification.getCbInputsInEnabled(2) + "</"+ key315 +">\n";
		settings += "<"+ key316 +">" + MainActivity.stateholderNotification.getCbInputsInChecked(2) + "</"+ key316 +">\n";
		settings += "<"+ key317 +">" + MainActivity.stateholderNotification.getCbInputsInEnabled(3) + "</"+ key317 +">\n";
		settings += "<"+ key318 +">" + MainActivity.stateholderNotification.getCbInputsInChecked(3) + "</"+ key318 +">\n";
		settings += "<"+ key319 +">" + MainActivity.stateholderNotification.getCbInputsInEnabled(4) + "</"+ key319 +">\n";
		settings += "<"+ key320 +">" + MainActivity.stateholderNotification.getCbInputsInChecked(4) + "</"+ key320 +">\n";
		//Phone number
		settings += "<"+ key321 +">" + MainActivity.stateholderNotification.getEtPhoneNumberEnabled(0) + "</"+ key321 +">\n";
		settings += "<"+ key322 +">" + MainActivity.stateholderNotification.getEtPhoneNumberValue(0) + "</"+ key322 +">\n";
		settings += "<"+ key323 +">" + MainActivity.stateholderNotification.getEtPhoneNumberEnabled(1) + "</"+ key323 +">\n";
		settings += "<"+ key324 +">" + MainActivity.stateholderNotification.getEtPhoneNumberValue(1) + "</"+ key324 +">\n";
		settings += "<"+ key325 +">" + MainActivity.stateholderNotification.getEtPhoneNumberEnabled(2) + "</"+ key325 +">\n";
		settings += "<"+ key326 +">" + MainActivity.stateholderNotification.getEtPhoneNumberValue(2) + "</"+ key326 +">\n";
		settings += "<"+ key327 +">" + MainActivity.stateholderNotification.getEtPhoneNumberEnabled(3) + "</"+ key327 +">\n";
		settings += "<"+ key328 +">" + MainActivity.stateholderNotification.getEtPhoneNumberValue(3) + "</"+ key328 +">\n";
		settings += "<"+ key329 +">" + MainActivity.stateholderNotification.getEtPhoneNumberEnabled(4) + "</"+ key329 +">\n";
		settings += "<"+ key330 +">" + MainActivity.stateholderNotification.getEtPhoneNumberValue(4) + "</"+ key330 +">\n";
		//Allow Notification SMS
		settings += "<"+ key331 +">" + MainActivity.stateholderNotification.getCbAllowNotificationsSMSEnabled(0) + "</"+ key331 +">\n";
		settings += "<"+ key332 +">" + MainActivity.stateholderNotification.getCbAllowNotificationsSMSChecked(0) + "</"+ key332 +">\n";
		settings += "<"+ key333 +">" + MainActivity.stateholderNotification.getCbAllowNotificationsSMSEnabled(1) + "</"+ key333 +">\n";
		settings += "<"+ key334 +">" + MainActivity.stateholderNotification.getCbAllowNotificationsSMSChecked(1) + "</"+ key334 +">\n";
		settings += "<"+ key335 +">" + MainActivity.stateholderNotification.getCbAllowNotificationsSMSEnabled(2) + "</"+ key335 +">\n";
		settings += "<"+ key336 +">" + MainActivity.stateholderNotification.getCbAllowNotificationsSMSChecked(2) + "</"+ key336 +">\n";
		settings += "<"+ key337 +">" + MainActivity.stateholderNotification.getCbAllowNotificationsSMSEnabled(3) + "</"+ key337 +">\n";
		settings += "<"+ key338 +">" + MainActivity.stateholderNotification.getCbAllowNotificationsSMSChecked(3) + "</"+ key338 +">\n";
		settings += "<"+ key339 +">" + MainActivity.stateholderNotification.getCbAllowNotificationsSMSEnabled(4) + "</"+ key339 +">\n";
		settings += "<"+ key340 +">" + MainActivity.stateholderNotification.getCbAllowNotificationsSMSChecked(4) + "</"+ key340 +">\n";
		//Allow Notification TEL
		settings += "<"+ key341 +">" + MainActivity.stateholderNotification.getCbAllowNotificationsTELEnabled(0) + "</"+ key341 +">\n";
		settings += "<"+ key342 +">" + MainActivity.stateholderNotification.getCbAllowNotificationsTELChecked(0) + "</"+ key342 +">\n";
		settings += "<"+ key343 +">" + MainActivity.stateholderNotification.getCbAllowNotificationsTELEnabled(1) + "</"+ key343 +">\n";
		settings += "<"+ key344 +">" + MainActivity.stateholderNotification.getCbAllowNotificationsTELChecked(1) + "</"+ key344 +">\n";
		settings += "<"+ key345 +">" + MainActivity.stateholderNotification.getCbAllowNotificationsTELEnabled(2) + "</"+ key345 +">\n";
		settings += "<"+ key346 +">" + MainActivity.stateholderNotification.getCbAllowNotificationsTELChecked(2) + "</"+ key346 +">\n";
		settings += "<"+ key347 +">" + MainActivity.stateholderNotification.getCbAllowNotificationsTELEnabled(3) + "</"+ key347 +">\n";
		settings += "<"+ key348 +">" + MainActivity.stateholderNotification.getCbAllowNotificationsTELChecked(3) + "</"+ key348 +">\n";
		settings += "<"+ key349 +">" + MainActivity.stateholderNotification.getCbAllowNotificationsTELEnabled(4) + "</"+ key349 +">\n";
		settings += "<"+ key350 +">" + MainActivity.stateholderNotification.getCbAllowNotificationsTELChecked(4) + "</"+ key350 +">\n";
		//Button Call button pressed SMS
		settings += "<"+ key351 +">" + MainActivity.stateholderNotification.getBtnCallBtnPressedSMSEnabled(0) + "</"+ key351 +">\n";
		settings += "<"+ key352 +">" + MainActivity.stateholderNotification.getBtnCallBtnPressedSMSChecked(0) + "</"+ key352 +">\n";
		settings += "<"+ key353 +">" + MainActivity.stateholderNotification.getBtnCallBtnPressedSMSEnabled(1) + "</"+ key353 +">\n";
		settings += "<"+ key354 +">" + MainActivity.stateholderNotification.getBtnCallBtnPressedSMSChecked(1) + "</"+ key354 +">\n";
		settings += "<"+ key355 +">" + MainActivity.stateholderNotification.getBtnCallBtnPressedSMSEnabled(2) + "</"+ key355 +">\n";
		settings += "<"+ key356 +">" + MainActivity.stateholderNotification.getBtnCallBtnPressedSMSChecked(2) + "</"+ key356 +">\n";
		settings += "<"+ key357 +">" + MainActivity.stateholderNotification.getBtnCallBtnPressedSMSEnabled(3) + "</"+ key357 +">\n";
		settings += "<"+ key358 +">" + MainActivity.stateholderNotification.getBtnCallBtnPressedSMSChecked(3) + "</"+ key358 +">\n";
		settings += "<"+ key359 +">" + MainActivity.stateholderNotification.getBtnCallBtnPressedSMSEnabled(4) + "</"+ key359 +">\n";
		settings += "<"+ key360 +">" + MainActivity.stateholderNotification.getBtnCallBtnPressedSMSChecked(4) + "</"+ key360 +">\n";
		//Button Call button pressed TEL
		settings += "<"+ key361 +">" + MainActivity.stateholderNotification.getBtnCallBtnPressedTELEnabled(0) + "</"+ key361 +">\n";
		settings += "<"+ key362 +">" + MainActivity.stateholderNotification.getBtnCallBtnPressedTELChecked(0) + "</"+ key362 +">\n";
		settings += "<"+ key363 +">" + MainActivity.stateholderNotification.getBtnCallBtnPressedTELEnabled(1) + "</"+ key363 +">\n";
		settings += "<"+ key364 +">" + MainActivity.stateholderNotification.getBtnCallBtnPressedTELChecked(1) + "</"+ key364 +">\n";
		settings += "<"+ key365 +">" + MainActivity.stateholderNotification.getBtnCallBtnPressedTELEnabled(2) + "</"+ key365 +">\n";
		settings += "<"+ key366 +">" + MainActivity.stateholderNotification.getBtnCallBtnPressedTELChecked(2) + "</"+ key366 +">\n";
		settings += "<"+ key367 +">" + MainActivity.stateholderNotification.getBtnCallBtnPressedTELEnabled(3) + "</"+ key367 +">\n";
		settings += "<"+ key368 +">" + MainActivity.stateholderNotification.getBtnCallBtnPressedTELChecked(3) + "</"+ key368 +">\n";
		settings += "<"+ key369 +">" + MainActivity.stateholderNotification.getBtnCallBtnPressedTELEnabled(4) + "</"+ key369 +">\n";
		settings += "<"+ key370 +">" + MainActivity.stateholderNotification.getBtnCallBtnPressedTELChecked(4) + "</"+ key370 +">\n";
		//Ingine Active SMS
		settings += "<"+ key371 +">" + MainActivity.stateholderNotification.getBtnIngineActiveSMSEnabled(0) + "</"+ key371 +">\n";
		settings += "<"+ key372 +">" + MainActivity.stateholderNotification.getBtnIngineActiveSMSChecked(0) + "</"+ key372 +">\n";
		settings += "<"+ key373 +">" + MainActivity.stateholderNotification.getBtnIngineActiveSMSEnabled(1) + "</"+ key373 +">\n";
		settings += "<"+ key374 +">" + MainActivity.stateholderNotification.getBtnIngineActiveSMSChecked(1) + "</"+ key374 +">\n";
		settings += "<"+ key375 +">" + MainActivity.stateholderNotification.getBtnIngineActiveSMSEnabled(2) + "</"+ key375 +">\n";
		settings += "<"+ key376 +">" + MainActivity.stateholderNotification.getBtnIngineActiveSMSChecked(2) + "</"+ key376 +">\n";
		settings += "<"+ key377 +">" + MainActivity.stateholderNotification.getBtnIngineActiveSMSEnabled(3) + "</"+ key377 +">\n";
		settings += "<"+ key378 +">" + MainActivity.stateholderNotification.getBtnIngineActiveSMSChecked(3) + "</"+ key378 +">\n";
		settings += "<"+ key379 +">" + MainActivity.stateholderNotification.getBtnIngineActiveSMSEnabled(4) + "</"+ key379 +">\n";
		settings += "<"+ key380 +">" + MainActivity.stateholderNotification.getBtnIngineActiveSMSChecked(4) + "</"+ key380 +">\n";
		//Ingine Active TEL
		settings += "<"+ key381 +">" + MainActivity.stateholderNotification.getBtnIngineActiveTELEnabled(0) + "</"+ key381 +">\n";
		settings += "<"+ key382 +">" + MainActivity.stateholderNotification.getBtnIngineActiveTELChecked(0) + "</"+ key382 +">\n";
		settings += "<"+ key383 +">" + MainActivity.stateholderNotification.getBtnIngineActiveTELEnabled(1) + "</"+ key383 +">\n";
		settings += "<"+ key384 +">" + MainActivity.stateholderNotification.getBtnIngineActiveTELChecked(1) + "</"+ key384 +">\n";
		settings += "<"+ key385 +">" + MainActivity.stateholderNotification.getBtnIngineActiveTELEnabled(2) + "</"+ key385 +">\n";
		settings += "<"+ key386 +">" + MainActivity.stateholderNotification.getBtnIngineActiveTELChecked(2) + "</"+ key386 +">\n";
		settings += "<"+ key387 +">" + MainActivity.stateholderNotification.getBtnIngineActiveTELEnabled(3) + "</"+ key387 +">\n";
		settings += "<"+ key388 +">" + MainActivity.stateholderNotification.getBtnIngineActiveTELChecked(3) + "</"+ key388 +">\n";
		settings += "<"+ key389 +">" + MainActivity.stateholderNotification.getBtnIngineActiveTELEnabled(4) + "</"+ key389 +">\n";
		settings += "<"+ key390 +">" + MainActivity.stateholderNotification.getBtnIngineActiveTELChecked(4) + "</"+ key390 +">\n";
		//Load Limit Switch SMS
		settings += "<"+ key391 +">" + MainActivity.stateholderNotification.getBtnLoadLimitSwitchSMSEnabled(0) + "</"+ key391 +">\n";
		settings += "<"+ key392 +">" + MainActivity.stateholderNotification.getBtnLoadLimitSwitchSMSChecked(0) + "</"+ key392 +">\n";
		settings += "<"+ key393 +">" + MainActivity.stateholderNotification.getBtnLoadLimitSwitchSMSEnabled(1) + "</"+ key393 +">\n";
		settings += "<"+ key394 +">" + MainActivity.stateholderNotification.getBtnLoadLimitSwitchSMSChecked(1) + "</"+ key394 +">\n";
		settings += "<"+ key395 +">" + MainActivity.stateholderNotification.getBtnLoadLimitSwitchSMSEnabled(2) + "</"+ key395 +">\n";
		settings += "<"+ key396 +">" + MainActivity.stateholderNotification.getBtnLoadLimitSwitchSMSChecked(2) + "</"+ key396 +">\n";
		settings += "<"+ key397 +">" + MainActivity.stateholderNotification.getBtnLoadLimitSwitchSMSEnabled(3) + "</"+ key397 +">\n";
		settings += "<"+ key398 +">" + MainActivity.stateholderNotification.getBtnLoadLimitSwitchSMSChecked(3) + "</"+ key398 +">\n";
		settings += "<"+ key399 +">" + MainActivity.stateholderNotification.getBtnLoadLimitSwitchSMSEnabled(4) + "</"+ key399 +">\n";
		settings += "<"+ key400 +">" + MainActivity.stateholderNotification.getBtnLoadLimitSwitchSMSChecked(4) + "</"+ key400 +">\n";
		//Load Limit Switch TEL
		settings += "<"+ key401 +">" + MainActivity.stateholderNotification.getBtnLoadLimitSwitchTELEnabled(0) + "</"+ key401 +">\n";
		settings += "<"+ key402 +">" + MainActivity.stateholderNotification.getBtnLoadLimitSwitchTELChecked(0) + "</"+ key402 +">\n";
		settings += "<"+ key403 +">" + MainActivity.stateholderNotification.getBtnLoadLimitSwitchTELEnabled(1) + "</"+ key403 +">\n";
		settings += "<"+ key404 +">" + MainActivity.stateholderNotification.getBtnLoadLimitSwitchTELChecked(1) + "</"+ key404 +">\n";
		settings += "<"+ key405 +">" + MainActivity.stateholderNotification.getBtnLoadLimitSwitchTELEnabled(2) + "</"+ key405 +">\n";
		settings += "<"+ key406 +">" + MainActivity.stateholderNotification.getBtnLoadLimitSwitchTELChecked(2) + "</"+ key406 +">\n";
		settings += "<"+ key407 +">" + MainActivity.stateholderNotification.getBtnLoadLimitSwitchTELEnabled(3) + "</"+ key407 +">\n";
		settings += "<"+ key408 +">" + MainActivity.stateholderNotification.getBtnLoadLimitSwitchTELChecked(3) + "</"+ key408 +">\n";
		settings += "<"+ key409 +">" + MainActivity.stateholderNotification.getBtnLoadLimitSwitchTELEnabled(4) + "</"+ key409 +">\n";
		settings += "<"+ key410 +">" + MainActivity.stateholderNotification.getBtnLoadLimitSwitchTELChecked(4) + "</"+ key410 +">\n";
		//Button Load Shock Sensor SMS
		settings += "<"+ key411 +">" + MainActivity.stateholderNotification.getBtnLoadShockSensorSMSEnabled(0) + "</"+ key411 +">\n";
		settings += "<"+ key412 +">" + MainActivity.stateholderNotification.getBtnLoadShockSensorSMSChecked(0) + "</"+ key412 +">\n";
		settings += "<"+ key413 +">" + MainActivity.stateholderNotification.getBtnLoadShockSensorSMSEnabled(1) + "</"+ key413 +">\n";
		settings += "<"+ key414 +">" + MainActivity.stateholderNotification.getBtnLoadShockSensorSMSChecked(1) + "</"+ key414 +">\n";
		settings += "<"+ key415 +">" + MainActivity.stateholderNotification.getBtnLoadShockSensorSMSEnabled(2) + "</"+ key415 +">\n";
		settings += "<"+ key416 +">" + MainActivity.stateholderNotification.getBtnLoadShockSensorSMSChecked(2) + "</"+ key416 +">\n";
		settings += "<"+ key417 +">" + MainActivity.stateholderNotification.getBtnLoadShockSensorSMSEnabled(3) + "</"+ key417 +">\n";
		settings += "<"+ key418 +">" + MainActivity.stateholderNotification.getBtnLoadShockSensorSMSChecked(3) + "</"+ key418 +">\n";
		settings += "<"+ key419 +">" + MainActivity.stateholderNotification.getBtnLoadShockSensorSMSEnabled(4) + "</"+ key419 +">\n";
		settings += "<"+ key420 +">" + MainActivity.stateholderNotification.getBtnLoadShockSensorSMSChecked(4) + "</"+ key420 +">\n";
		//Button Load Shock Sensor TEL
		settings += "<"+ key421 +">" + MainActivity.stateholderNotification.getBtnLoadShockSensorTELEnabled(0) + "</"+ key421 +">\n";
		settings += "<"+ key422 +">" + MainActivity.stateholderNotification.getBtnLoadShockSensorTELChecked(0) + "</"+ key422 +">\n";
		settings += "<"+ key423 +">" + MainActivity.stateholderNotification.getBtnLoadShockSensorTELEnabled(1) + "</"+ key423 +">\n";
		settings += "<"+ key424 +">" + MainActivity.stateholderNotification.getBtnLoadShockSensorTELChecked(1) + "</"+ key424 +">\n";
		settings += "<"+ key425 +">" + MainActivity.stateholderNotification.getBtnLoadShockSensorTELEnabled(2) + "</"+ key425 +">\n";
		settings += "<"+ key426 +">" + MainActivity.stateholderNotification.getBtnLoadShockSensorTELChecked(2) + "</"+ key426 +">\n";
		settings += "<"+ key427 +">" + MainActivity.stateholderNotification.getBtnLoadShockSensorTELEnabled(3) + "</"+ key427 +">\n";
		settings += "<"+ key428 +">" + MainActivity.stateholderNotification.getBtnLoadShockSensorTELChecked(3) + "</"+ key428 +">\n";
		settings += "<"+ key429 +">" + MainActivity.stateholderNotification.getBtnLoadShockSensorTELEnabled(4) + "</"+ key429 +">\n";
		settings += "<"+ key430 +">" + MainActivity.stateholderNotification.getBtnLoadShockSensorTELChecked(4) + "</"+ key430 +">\n";
		//Load Universal Limit Switch SMS
		settings += "<"+ key451 +">" + MainActivity.stateholderNotification.getBtnLoadUniversalLimitSwitchSMSEnabled(0) + "</"+ key451 +">\n";
		settings += "<"+ key452 +">" + MainActivity.stateholderNotification.getBtnLoadUniversalLimitSwitchSMSChecked(0) + "</"+ key452 +">\n";
		settings += "<"+ key453 +">" + MainActivity.stateholderNotification.getBtnLoadUniversalLimitSwitchSMSEnabled(1) + "</"+ key453 +">\n";
		settings += "<"+ key454 +">" + MainActivity.stateholderNotification.getBtnLoadUniversalLimitSwitchSMSChecked(1) + "</"+ key454 +">\n";
		settings += "<"+ key455 +">" + MainActivity.stateholderNotification.getBtnLoadUniversalLimitSwitchSMSEnabled(2) + "</"+ key455 +">\n";
		settings += "<"+ key456 +">" + MainActivity.stateholderNotification.getBtnLoadUniversalLimitSwitchSMSChecked(2) + "</"+ key456 +">\n";
		settings += "<"+ key457 +">" + MainActivity.stateholderNotification.getBtnLoadUniversalLimitSwitchSMSEnabled(3) + "</"+ key457 +">\n";
		settings += "<"+ key458 +">" + MainActivity.stateholderNotification.getBtnLoadUniversalLimitSwitchSMSChecked(3) + "</"+ key458 +">\n";
		settings += "<"+ key459 +">" + MainActivity.stateholderNotification.getBtnLoadUniversalLimitSwitchSMSEnabled(4) + "</"+ key459 +">\n";
		settings += "<"+ key460 +">" + MainActivity.stateholderNotification.getBtnLoadUniversalLimitSwitchSMSChecked(4) + "</"+ key460 +">\n";
		//Load Universal Limit Switch TEL
		settings += "<"+ key461 +">" + MainActivity.stateholderNotification.getBtnLoadUniversalLimitSwitchTELEnabled(0) + "</"+ key461 +">\n";
		settings += "<"+ key462 +">" + MainActivity.stateholderNotification.getBtnLoadUniversalLimitSwitchTELChecked(0) + "</"+ key462 +">\n";
		settings += "<"+ key463 +">" + MainActivity.stateholderNotification.getBtnLoadUniversalLimitSwitchTELEnabled(1) + "</"+ key463 +">\n";
		settings += "<"+ key464 +">" + MainActivity.stateholderNotification.getBtnLoadUniversalLimitSwitchTELChecked(1) + "</"+ key464 +">\n";
		settings += "<"+ key465 +">" + MainActivity.stateholderNotification.getBtnLoadUniversalLimitSwitchTELEnabled(2) + "</"+ key465 +">\n";
		settings += "<"+ key466 +">" + MainActivity.stateholderNotification.getBtnLoadUniversalLimitSwitchTELChecked(2) + "</"+ key466 +">\n";
		settings += "<"+ key467 +">" + MainActivity.stateholderNotification.getBtnLoadUniversalLimitSwitchTELEnabled(3) + "</"+ key467 +">\n";
		settings += "<"+ key468 +">" + MainActivity.stateholderNotification.getBtnLoadUniversalLimitSwitchTELChecked(3) + "</"+ key468 +">\n";
		settings += "<"+ key469 +">" + MainActivity.stateholderNotification.getBtnLoadUniversalLimitSwitchTELEnabled(4) + "</"+ key469 +">\n";
		settings += "<"+ key470 +">" + MainActivity.stateholderNotification.getBtnLoadUniversalLimitSwitchTELChecked(4) + "</"+ key470 +">\n";
		//Button Low Buttery SMS
		settings += "<"+ key471 +">" + MainActivity.stateholderNotification.getBtnLowBatterySMSEnabled(0) + "</"+ key471 +">\n";
		settings += "<"+ key472 +">" + MainActivity.stateholderNotification.getBtnLowBatterySMSChecked(0) + "</"+ key472 +">\n";
		settings += "<"+ key473 +">" + MainActivity.stateholderNotification.getBtnLowBatterySMSEnabled(1) + "</"+ key473 +">\n";
		settings += "<"+ key474 +">" + MainActivity.stateholderNotification.getBtnLowBatterySMSChecked(1) + "</"+ key474 +">\n";
		settings += "<"+ key475 +">" + MainActivity.stateholderNotification.getBtnLowBatterySMSEnabled(2) + "</"+ key475 +">\n";
		settings += "<"+ key476 +">" + MainActivity.stateholderNotification.getBtnLowBatterySMSChecked(2) + "</"+ key476 +">\n";
		settings += "<"+ key477 +">" + MainActivity.stateholderNotification.getBtnLowBatterySMSEnabled(3) + "</"+ key477 +">\n";
		settings += "<"+ key478 +">" + MainActivity.stateholderNotification.getBtnLowBatterySMSChecked(3) + "</"+ key478 +">\n";
		settings += "<"+ key479 +">" + MainActivity.stateholderNotification.getBtnLowBatterySMSEnabled(4) + "</"+ key479 +">\n";
		settings += "<"+ key480 +">" + MainActivity.stateholderNotification.getBtnLowBatterySMSChecked(4) + "</"+ key480 +">\n";
		//Button Low Buttery TEL
		settings += "<"+ key481 +">" + MainActivity.stateholderNotification.getBtnLowBatteryTELEnabled(0) + "</"+ key481 +">\n";
		settings += "<"+ key482 +">" + MainActivity.stateholderNotification.getBtnLowBatteryTELChecked(0) + "</"+ key482 +">\n";
		settings += "<"+ key483 +">" + MainActivity.stateholderNotification.getBtnLowBatteryTELEnabled(1) + "</"+ key483 +">\n";
		settings += "<"+ key484 +">" + MainActivity.stateholderNotification.getBtnLowBatteryTELChecked(1) + "</"+ key484 +">\n";
		settings += "<"+ key485 +">" + MainActivity.stateholderNotification.getBtnLowBatteryTELEnabled(2) + "</"+ key485 +">\n";
		settings += "<"+ key486 +">" + MainActivity.stateholderNotification.getBtnLowBatteryTELChecked(2) + "</"+ key486 +">\n";
		settings += "<"+ key487 +">" + MainActivity.stateholderNotification.getBtnLowBatteryTELEnabled(3) + "</"+ key487 +">\n";
		settings += "<"+ key488 +">" + MainActivity.stateholderNotification.getBtnLowBatteryTELChecked(3) + "</"+ key488 +">\n";
		settings += "<"+ key489 +">" + MainActivity.stateholderNotification.getBtnLowBatteryTELEnabled(4) + "</"+ key489 +">\n";
		settings += "<"+ key490 +">" + MainActivity.stateholderNotification.getBtnLowBatteryTELChecked(4) + "</"+ key490 +">\n";
		//GSM SMS
		settings += "<"+ key491 +">" + MainActivity.stateholderNotification.getBtnGSMSMSEnabled(0) + "</"+ key491 +">\n";
		settings += "<"+ key492 +">" + MainActivity.stateholderNotification.getBtnGSMSMSChecked(0) + "</"+ key492 +">\n";
		settings += "<"+ key493 +">" + MainActivity.stateholderNotification.getBtnGSMSMSEnabled(1) + "</"+ key493 +">\n";
		settings += "<"+ key494 +">" + MainActivity.stateholderNotification.getBtnGSMSMSChecked(1) + "</"+ key494 +">\n";
		settings += "<"+ key495 +">" + MainActivity.stateholderNotification.getBtnGSMSMSEnabled(2) + "</"+ key495 +">\n";
		settings += "<"+ key496 +">" + MainActivity.stateholderNotification.getBtnGSMSMSChecked(2) + "</"+ key496 +">\n";
		settings += "<"+ key497 +">" + MainActivity.stateholderNotification.getBtnGSMSMSEnabled(3) + "</"+ key497 +">\n";
		settings += "<"+ key498 +">" + MainActivity.stateholderNotification.getBtnGSMSMSChecked(3) + "</"+ key498 +">\n";
		settings += "<"+ key499 +">" + MainActivity.stateholderNotification.getBtnGSMSMSEnabled(4) + "</"+ key499 +">\n";
		settings += "<"+ key500 +">" + MainActivity.stateholderNotification.getBtnGSMSMSChecked(4) + "</"+ key500 +">\n";
		//GSM TEL
		settings += "<"+ key501 +">" + MainActivity.stateholderNotification.getBtnGTELMSEnabled(0) + "</"+ key501 +">\n";
		settings += "<"+ key502 +">" + MainActivity.stateholderNotification.getBtnGTELMSChecked(0) + "</"+ key502 +">\n";
		settings += "<"+ key503 +">" + MainActivity.stateholderNotification.getBtnGTELMSEnabled(1) + "</"+ key503 +">\n";
		settings += "<"+ key504 +">" + MainActivity.stateholderNotification.getBtnGTELMSChecked(1) + "</"+ key504 +">\n";
		settings += "<"+ key505 +">" + MainActivity.stateholderNotification.getBtnGTELMSEnabled(2) + "</"+ key505 +">\n";
		settings += "<"+ key506 +">" + MainActivity.stateholderNotification.getBtnGTELMSChecked(2) + "</"+ key506 +">\n";
		settings += "<"+ key507 +">" + MainActivity.stateholderNotification.getBtnGTELMSEnabled(3) + "</"+ key507 +">\n";
		settings += "<"+ key508 +">" + MainActivity.stateholderNotification.getBtnGTELMSChecked(3) + "</"+ key508 +">\n";
		settings += "<"+ key509 +">" + MainActivity.stateholderNotification.getBtnGTELMSEnabled(4) + "</"+ key509 +">\n";
		settings += "<"+ key510 +">" + MainActivity.stateholderNotification.getBtnGTELMSChecked(4) + "</"+ key510 +">\n";
		//Button System Disarm SMS
		settings += "<"+ key511 +">" + MainActivity.stateholderNotification.getBtnSystemDisarmSMSEnabled(0) + "</"+ key511 +">\n";
		settings += "<"+ key512 +">" + MainActivity.stateholderNotification.getBtnSystemDisarmSMSChecked(0) + "</"+ key512 +">\n";
		settings += "<"+ key513 +">" + MainActivity.stateholderNotification.getBtnSystemDisarmSMSEnabled(1) + "</"+ key513 +">\n";
		settings += "<"+ key514 +">" + MainActivity.stateholderNotification.getBtnSystemDisarmSMSChecked(1) + "</"+ key514 +">\n";
		settings += "<"+ key515 +">" + MainActivity.stateholderNotification.getBtnSystemDisarmSMSEnabled(2) + "</"+ key515 +">\n";
		settings += "<"+ key516 +">" + MainActivity.stateholderNotification.getBtnSystemDisarmSMSChecked(2) + "</"+ key516 +">\n";
		settings += "<"+ key517 +">" + MainActivity.stateholderNotification.getBtnSystemDisarmSMSEnabled(3) + "</"+ key517 +">\n";
		settings += "<"+ key518 +">" + MainActivity.stateholderNotification.getBtnSystemDisarmSMSChecked(3) + "</"+ key518 +">\n";
		settings += "<"+ key519 +">" + MainActivity.stateholderNotification.getBtnSystemDisarmSMSEnabled(4) + "</"+ key519 +">\n";
		settings += "<"+ key520 +">" + MainActivity.stateholderNotification.getBtnSystemDisarmSMSChecked(4) + "</"+ key520 +">\n";
		//Button System Disarm TEL
		settings += "<"+ key521 +">" + MainActivity.stateholderNotification.getBtnSystemDisarmTELEnabled(0) + "</"+ key521 +">\n";
		settings += "<"+ key522 +">" + MainActivity.stateholderNotification.getBtnSystemDisarmTELChecked(0) + "</"+ key522 +">\n";
		settings += "<"+ key523 +">" + MainActivity.stateholderNotification.getBtnSystemDisarmTELEnabled(1) + "</"+ key523 +">\n";
		settings += "<"+ key524 +">" + MainActivity.stateholderNotification.getBtnSystemDisarmTELChecked(1) + "</"+ key524 +">\n";
		settings += "<"+ key525 +">" + MainActivity.stateholderNotification.getBtnSystemDisarmTELEnabled(2) + "</"+ key525 +">\n";
		settings += "<"+ key526 +">" + MainActivity.stateholderNotification.getBtnSystemDisarmTELChecked(2) + "</"+ key526 +">\n";
		settings += "<"+ key527 +">" + MainActivity.stateholderNotification.getBtnSystemDisarmTELEnabled(3) + "</"+ key527 +">\n";
		settings += "<"+ key528 +">" + MainActivity.stateholderNotification.getBtnSystemDisarmTELChecked(3) + "</"+ key528 +">\n";
		settings += "<"+ key529 +">" + MainActivity.stateholderNotification.getBtnSystemDisarmTELEnabled(4) + "</"+ key529 +">\n";
		settings += "<"+ key530 +">" + MainActivity.stateholderNotification.getBtnSystemDisarmTELChecked(4) + "</"+ key530 +">\n";
		//Button System Arming SMS
		settings += "<"+ key531 +">" + MainActivity.stateholderNotification.getBtnSystemArmingSMSEnabled(0) + "</"+ key531 +">\n";
		settings += "<"+ key532 +">" + MainActivity.stateholderNotification.getBtnSystemArmingSMSChecked(0) + "</"+ key532 +">\n";
		settings += "<"+ key533 +">" + MainActivity.stateholderNotification.getBtnSystemArmingSMSEnabled(1) + "</"+ key533 +">\n";
		settings += "<"+ key534 +">" + MainActivity.stateholderNotification.getBtnSystemArmingSMSChecked(1) + "</"+ key534 +">\n";
		settings += "<"+ key535 +">" + MainActivity.stateholderNotification.getBtnSystemArmingSMSEnabled(2) + "</"+ key535 +">\n";
		settings += "<"+ key536 +">" + MainActivity.stateholderNotification.getBtnSystemArmingSMSChecked(2) + "</"+ key536 +">\n";
		settings += "<"+ key537 +">" + MainActivity.stateholderNotification.getBtnSystemArmingSMSEnabled(3) + "</"+ key537 +">\n";
		settings += "<"+ key538 +">" + MainActivity.stateholderNotification.getBtnSystemArmingSMSChecked(3) + "</"+ key538 +">\n";
		settings += "<"+ key539 +">" + MainActivity.stateholderNotification.getBtnSystemArmingSMSEnabled(4) + "</"+ key539 +">\n";
		settings += "<"+ key540 +">" + MainActivity.stateholderNotification.getBtnSystemArmingSMSChecked(4) + "</"+ key540 +">\n";
		//Button System Arming TEL
		settings += "<"+ key541 +">" + MainActivity.stateholderNotification.getBtnSystemArmingTELEnabled(0) + "</"+ key541 +">\n";
		settings += "<"+ key542 +">" + MainActivity.stateholderNotification.getBtnSystemArmingTELChecked(0) + "</"+ key542 +">\n";
		settings += "<"+ key543 +">" + MainActivity.stateholderNotification.getBtnSystemArmingTELEnabled(1) + "</"+ key543 +">\n";
		settings += "<"+ key544 +">" + MainActivity.stateholderNotification.getBtnSystemArmingTELChecked(1) + "</"+ key544 +">\n";
		settings += "<"+ key545 +">" + MainActivity.stateholderNotification.getBtnSystemArmingTELEnabled(2) + "</"+ key545 +">\n";
		settings += "<"+ key546 +">" + MainActivity.stateholderNotification.getBtnSystemArmingTELChecked(2) + "</"+ key546 +">\n";
		settings += "<"+ key547 +">" + MainActivity.stateholderNotification.getBtnSystemArmingTELEnabled(3) + "</"+ key547 +">\n";
		settings += "<"+ key548 +">" + MainActivity.stateholderNotification.getBtnSystemArmingTELChecked(3) + "</"+ key548 +">\n";
		settings += "<"+ key549 +">" + MainActivity.stateholderNotification.getBtnSystemArmingTELEnabled(4) + "</"+ key549 +">\n";
		settings += "<"+ key550 +">" + MainActivity.stateholderNotification.getBtnSystemArmingTELChecked(4) + "</"+ key550 +">\n";
//CAN
		//1)
		settings += "<"+ key551 +">" + MainActivity.stateholderCAN.getCbSystArmDisarmEnabled() + "</"+ key551 +">\n";
		settings += "<"+ key552 +">" + MainActivity.stateholderCAN.getCbSystArmDisarmChecked() + "</"+ key552 +">\n";
		settings += "<"+ key553 +">" + MainActivity.stateholderCAN.getSpinSystArmDisarmEnabled() + "</"+ key553 +">\n";
		settings += "<"+ key554 +">" + MainActivity.stateholderCAN.getSpinSystArmDisarmValue() + "</"+ key554 +">\n";
		//2)
		settings += "<"+ key555 +">" + MainActivity.stateholderCAN.getCbIgnitionEnabled() + "</"+ key555 +">\n";
		settings += "<"+ key556 +">" + MainActivity.stateholderCAN.getCbIgnitionChecked() + "</"+ key556 +">\n";
		settings += "<"+ key557 +">" + MainActivity.stateholderCAN.getSpinIgnitionEnabled() + "</"+ key557 +">\n";
		settings += "<"+ key558 +">" + MainActivity.stateholderCAN.getSpinIgnitionValue() + "</"+ key558 +">\n";
		//3)
		settings += "<"+ key559 +">" + MainActivity.stateholderCAN.getCbDriveControlEnabled() + "</"+ key559 +">\n";
		settings += "<"+ key560 +">" + MainActivity.stateholderCAN.getCbDriveControlChecked() + "</"+ key560 +">\n";
		settings += "<"+ key561 +">" + MainActivity.stateholderCAN.getSpinDriveControlEnabled() + "</"+ key561 +">\n";
		settings += "<"+ key562 +">" + MainActivity.stateholderCAN.getSpinDriveControlValue() + "</"+ key562 +">\n";
		//4)
		settings += "<"+ key563 +">" + MainActivity.stateholderCAN.getCbComfortSignalEnabled() + "</"+ key563 +">\n";
		settings += "<"+ key564 +">" + MainActivity.stateholderCAN.getCbComfortSignalChecked() + "</"+ key564 +">\n";
		settings += "<"+ key565 +">" + MainActivity.stateholderCAN.getSpinComfortSignalEnabled() + "</"+ key565 +">\n";
		settings += "<"+ key566 +">" + MainActivity.stateholderCAN.getSpinComfortSignalValue() + "</"+ key566 +">\n";
		//5)
		settings += "<"+ key567 +">" + MainActivity.stateholderCAN.getCbManagingStaffSecuritySystemEnabled() + "</"+ key567 +">\n";
		settings += "<"+ key568 +">" + MainActivity.stateholderCAN.getCbManagingStaffSecuritySystemChecked() + "</"+ key568 +">\n";
		settings += "<"+ key569 +">" + MainActivity.stateholderCAN.getSpinManagingStaffSecuritySystemEnabled() + "</"+ key569 +">\n";
		settings += "<"+ key570 +">" + MainActivity.stateholderCAN.getSpinManagingStaffSecuritySystemValue() + "</"+ key570 +">\n";
//TEXT SMS
		//Text SMS
		settings += "<"+ key571 +">" + MainActivity.stateholderTextSMS.getTextSMSEnabled() + "</"+ key571 +">\n";
		settings += "<"+ key572 +">" + MainActivity.stateholderTextSMS.getTextSMSValue() + "</"+ key572 +">\n";
		settings += "<"+ key573 +">" + MainActivity.stateholderTextSMS.getPhoneForSMSEnabled() + "</"+ key573 +">\n";
		settings += "<"+ key574 +">" + MainActivity.stateholderTextSMS.getPhoneForSMSValue() + "</"+ key574 +">\n";
		
		return settings;
	}
}
