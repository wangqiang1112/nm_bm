package ns.major.config.dao.domain;

import java.util.Date;

public class StaffJob {
	private String IDNumber;//扩展
	private Integer JobID;//主键
	private Integer StafferId;
	private String StafferName;
	private Date WStartTime;
	private String WStartTime_str;//扩展
	private Date WEndTime;
	private String WUnitName;
	private String WEndTime_str;//扩展
	private String WPost;//职务
	private String WPost_str;
	private String WJob;//职称
	private String WJob_str;
	private String WDuty;
	private String Wwitness;
	private String WPhone;
	private String WRemarks;
	
	public String getWPost_str() {
		return WPost_str;
	}
	public void setWPost_str(String wPost_str) {
		WPost_str = wPost_str;
	}
	public String getWJob_str() {
		return WJob_str;
	}
	public void setWJob_str(String wJob_str) {
		WJob_str = wJob_str;
	}
	public String getWUnitName() {
		return WUnitName;
	}
	public void setWUnitName(String wUnitName) {
		WUnitName = wUnitName;
	}
	public String getIDNumber() {
		return IDNumber;
	}
	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}
	public Integer getJobID() {
		return JobID;
	}
	public void setJobID(Integer jobID) {
		JobID = jobID;
	}
	public Integer getStafferId() {
		return StafferId;
	}
	public void setStafferId(Integer stafferId) {
		StafferId = stafferId;
	}
	public String getStafferName() {
		return StafferName;
	}
	public void setStafferName(String stafferName) {
		StafferName = stafferName;
	}
	public Date getWStartTime() {
		return WStartTime;
	}
	public void setWStartTime(Date wStartTime) {
		WStartTime = wStartTime;
	}
	public String getWStartTime_str() {
		return WStartTime_str;
	}
	public void setWStartTime_str(String wStartTime_str) {
		WStartTime_str = wStartTime_str;
	}
	public Date getWEndTime() {
		return WEndTime;
	}
	public void setWEndTime(Date wEndTime) {
		WEndTime = wEndTime;
	}
	public String getWEndTime_str() {
		return WEndTime_str;
	}
	public void setWEndTime_str(String wEndTime_str) {
		WEndTime_str = wEndTime_str;
	}
	public String getWPost() {
		return WPost;
	}
	public void setWPost(String wPost) {
		WPost = wPost;
	}
	public String getWJob() {
		return WJob;
	}
	public void setWJob(String wJob) {
		WJob = wJob;
	}
	public String getWDuty() {
		return WDuty;
	}
	public void setWDuty(String wDuty) {
		WDuty = wDuty;
	}
	public String getWwitness() {
		return Wwitness;
	}
	public void setWwitness(String wwitness) {
		Wwitness = wwitness;
	}
	public String getWPhone() {
		return WPhone;
	}
	public void setWPhone(String wPhone) {
		WPhone = wPhone;
	}
	public String getWRemarks() {
		return WRemarks;
	}
	public void setWRemarks(String wRemarks) {
		WRemarks = wRemarks;
	}
	@Override
	public String toString() {
		return "StaffJob [IDNumber=" + IDNumber + ", JobID=" + JobID
				+ ", StafferId=" + StafferId + ", StafferName=" + StafferName
				+ ", WStartTime=" + WStartTime + ", WStartTime_str="
				+ WStartTime_str + ", WEndTime=" + WEndTime + ", WUnitName="
				+ WUnitName + ", WEndTime_str=" + WEndTime_str + ", WPost="
				+ WPost + ", WJob=" + WJob + ", WDuty=" + WDuty + ", Wwitness="
				+ Wwitness + ", WPhone=" + WPhone + ", WRemarks=" + WRemarks
				+ ", getWUnitName()=" + getWUnitName() + ", getIDNumber()="
				+ getIDNumber() + ", getJobID()=" + getJobID()
				+ ", getStafferId()=" + getStafferId() + ", getStafferName()="
				+ getStafferName() + ", getWStartTime()=" + getWStartTime()
				+ ", getWStartTime_str()=" + getWStartTime_str()
				+ ", getWEndTime()=" + getWEndTime() + ", getWEndTime_str()="
				+ getWEndTime_str() + ", getWPost()=" + getWPost()
				+ ", getWJob()=" + getWJob() + ", getWDuty()=" + getWDuty()
				+ ", getWwitness()=" + getWwitness() + ", getWPhone()="
				+ getWPhone() + ", getWRemarks()=" + getWRemarks()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	

	
}
