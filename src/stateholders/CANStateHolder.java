package stateholders;

public class CANStateHolder 
{
	//1)
	private boolean cb_CAN_SystArmDisarm_enabled = true;
	private boolean cb_CAN_SystArmDisarm_checked = false;
	public void setCbSystArmDisarmEnabled(boolean enabled) { cb_CAN_SystArmDisarm_enabled = enabled; }
	public void setCbSystArmDisarmChecked(boolean checked) { cb_CAN_SystArmDisarm_checked = checked; }
	public boolean getCbSystArmDisarmEnabled() { return cb_CAN_SystArmDisarm_enabled; }
	public boolean getCbSystArmDisarmChecked() { return cb_CAN_SystArmDisarm_checked; }
	
	private boolean spin_CAN_SystArmDisarm_enabled = false;
	private int spin_CAN_SystArmDisarm_value = 1;
	public void setSpinSystArmDisarmEnabled(boolean enabled) { spin_CAN_SystArmDisarm_enabled = enabled; }
	public void setSpinSystArmDisarmValue(int value) { spin_CAN_SystArmDisarm_value = value; }
	public boolean getSpinSystArmDisarmEnabled() { return spin_CAN_SystArmDisarm_enabled; }
	public int getSpinSystArmDisarmValue() { return spin_CAN_SystArmDisarm_value; }
	
	//2)
	private boolean cb_CAN_Ignition_enabled = true;
	private boolean cb_CAN_Ignition_checked = false;
	public void setCbIgnitionEnabled(boolean enabled) { cb_CAN_Ignition_enabled = enabled; }
	public void setCbIgnitionChecked(boolean checked) { cb_CAN_Ignition_checked = checked; }
	public boolean getCbIgnitionEnabled() { return cb_CAN_Ignition_enabled; }
	public boolean getCbIgnitionChecked() { return cb_CAN_Ignition_checked; }
	
	private boolean spin_CAN_Ignition_enabled = false;
	private int spin_CAN_Ignition_value = 1;
	public void setSpinIgnitionEnabled(boolean enabled) { spin_CAN_Ignition_enabled = enabled; }
	public void setSpinIgnitionValue(int value) { spin_CAN_Ignition_value = value; }
	public boolean getSpinIgnitionEnabled() { return spin_CAN_Ignition_enabled; }
	public int getSpinIgnitionValue() { return spin_CAN_Ignition_value; }
	
	//3)
	private boolean cb_CAN_DriveControl_enabled = true;
	private boolean cb_CAN_DriveControl_checked = false;
	public void setCbDriveControlEnabled(boolean enabled) { cb_CAN_DriveControl_enabled = enabled; }
	public void setCbDriveControlChecked(boolean checked) { cb_CAN_DriveControl_checked = checked; }
	public boolean getCbDriveControlEnabled() { return cb_CAN_DriveControl_enabled; }
	public boolean getCbDriveControlChecked() { return cb_CAN_DriveControl_checked; }
	
	private boolean spin_CAN_DriveControl_enabled = false;
	private int spin_CAN_DriveControl_value = 1;
	public void setSpinDriveControlEnabled(boolean enabled) { spin_CAN_DriveControl_enabled = enabled; }
	public void setSpinDriveControlValue(int value) { spin_CAN_DriveControl_value = value; }
	public boolean getSpinDriveControlEnabled() { return spin_CAN_DriveControl_enabled; }
	public int getSpinDriveControlValue() { return spin_CAN_DriveControl_value; }
	
	//4)
	private boolean cb_CAN_ComfortSignal_enabled = true;
	private boolean cb_CAN_ComfortSignal_checked = false;
	public void setCbComfortSignalEnabled(boolean enabled) { cb_CAN_ComfortSignal_enabled = enabled; }
	public void setCbComfortSignalChecked(boolean checked) { cb_CAN_ComfortSignal_checked = checked; }
	public boolean getCbComfortSignalEnabled() { return cb_CAN_ComfortSignal_enabled; }
	public boolean getCbComfortSignalChecked() { return cb_CAN_ComfortSignal_checked; }
	
	private boolean spin_CAN_ComfortSignal_enabled = false;
	private int spin_CAN_ComfortSignal_value = 1;
	public void setSpinComfortSignalEnabled(boolean enabled) { spin_CAN_ComfortSignal_enabled = enabled; }
	public void setSpinComfortSignalValue(int value) { spin_CAN_ComfortSignal_value = value; }
	public boolean getSpinComfortSignalEnabled() { return spin_CAN_ComfortSignal_enabled; }
	public int getSpinComfortSignalValue() { return spin_CAN_ComfortSignal_value; }
	
	//5)
	private boolean cb_CAN_ManagingStaffSecuritySystem_enabled = true;
	private boolean cb_CAN_ManagingStaffSecuritySystem_checked = false;
	public void setCbManagingStaffSecuritySystemEnabled(boolean enabled) { cb_CAN_ManagingStaffSecuritySystem_enabled = enabled; }
	public void setCbManagingStaffSecuritySystemChecked(boolean checked) { cb_CAN_ManagingStaffSecuritySystem_checked = checked; }
	public boolean getCbManagingStaffSecuritySystemEnabled() { return cb_CAN_ManagingStaffSecuritySystem_enabled; }
	public boolean getCbManagingStaffSecuritySystemChecked() { return cb_CAN_ManagingStaffSecuritySystem_checked; }
		
	private boolean spin_CAN_ManagingStaffSecuritySystem_enabled = false;
	private int spin_CAN_ManagingStaffSecuritySystem_value = 1;
	public void setSpinManagingStaffSecuritySystemEnabled(boolean enabled) { spin_CAN_ManagingStaffSecuritySystem_enabled = enabled; }
	public void setSpinManagingStaffSecuritySystemValue(int value) { spin_CAN_ManagingStaffSecuritySystem_value = value; }
	public boolean getSpinManagingStaffSecuritySystemEnabled() { return spin_CAN_ManagingStaffSecuritySystem_enabled; }
	public int getSpinManagingStaffSecuritySystemValue() { return spin_CAN_ManagingStaffSecuritySystem_value; }
}
