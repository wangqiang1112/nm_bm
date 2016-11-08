package ns.major.config.dao.domain;

import java.util.Date;

public class StaffEdu {
	private Integer EduID;//主键
	private String IDNumber;//扩展
	private int StafferId;
	private String StafferName;
	private Date EStartTime;
	private String EStartTime_str;//扩展
	private Date EEndTime;
	private String EEndTime_str;//扩展
	private String InstitutionName;
	private String Discipline;
	private int ESystem;
	private String Degree;
	private String Degree_str;
	private String Ewitness;
	private String ERemarks;
	
	public String getDegree_str() {
		return Degree_str;
	}
	public void setDegree_str(String degree_str) {
		Degree_str = degree_str;
	}
	public Integer getEduID() {
		return EduID;
	}
	public void setEduID(Integer eduID) {
		EduID = eduID;
	}
	public int getStafferId() {
		return StafferId;
	}
	public void setStafferId(int stafferId) {
		StafferId = stafferId;
	}
	public String getStafferName() {
		return StafferName;
	}
	public void setStafferName(String stafferName) {
		StafferName = stafferName;
	}
	public Date getEStartTime() {
		return EStartTime;
	}
	public void setEStartTime(Date eStartTime) {
		EStartTime = eStartTime;
	}
	public String getEStartTime_str() {
		return EStartTime_str;
	}
	public void setEStartTime_str(String eStartTime_str) {
		EStartTime_str = eStartTime_str;
	}
	public Date getEEndTime() {
		return EEndTime;
	}
	public void setEEndTime(Date eEndTime) {
		EEndTime = eEndTime;
	}
	public String getEEndTime_str() {
		return EEndTime_str;
	}
	public void setEEndTime_str(String eEndTime_str) {
		EEndTime_str = eEndTime_str;
	}
	public String getInstitutionName() {
		return InstitutionName;
	}
	public void setInstitutionName(String institutionName) {
		InstitutionName = institutionName;
	}
	public String getDiscipline() {
		return Discipline;
	}
	public void setDiscipline(String discipline) {
		Discipline = discipline;
	}
	public int getESystem() {
		return ESystem;
	}
	public void setESystem(int eSystem) {
		ESystem = eSystem;
	}
	public String getDegree() {
		return Degree;
	}
	public void setDegree(String degree) {
		Degree = degree;
	}
	public String getEwitness() {
		return Ewitness;
	}
	public void setEwitness(String ewitness) {
		Ewitness = ewitness;
	}
	public String getERemarks() {
		return ERemarks;
	}
	public void setERemarks(String eRemarks) {
		ERemarks = eRemarks;
	}
	public String getIDNumber() {
		return IDNumber;
	}
	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}
	@Override
	public String toString() {
		return "StaffEdu [EduID=" + EduID + ", IDNumber=" + IDNumber
				+ ", StafferId=" + StafferId + ", StafferName=" + StafferName
				+ ", EStartTime=" + EStartTime + ", EStartTime_str="
				+ EStartTime_str + ", EEndTime=" + EEndTime + ", EEndTime_str="
				+ EEndTime_str + ", InstitutionName=" + InstitutionName
				+ ", Discipline=" + Discipline + ", ESystem=" + ESystem
				+ ", Degree=" + Degree + ", Degree_str=" + Degree_str
				+ ", Ewitness=" + Ewitness + ", ERemarks=" + ERemarks + "]";
	}
	
	
	
}
