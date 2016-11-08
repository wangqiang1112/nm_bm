package ns.major.config.dao.domain;

import java.util.Date;

public class Staff {
	private int stafferId;
	private String stafferName;
	private int stafferSex;
	private Date birthDay;
	private String birthDay_str;//扩展
	private Integer nationality;
	private String nationality_str;//扩展
	private Integer politicalStatus;
	private String politicalStatus_str;//扩展
	private Integer nation;
	private String nation_str;//扩展
	private String IDNumber;
	private String IDNumber_decode;//扩展
	private String associations;
	private String mobilePone;
	private String phone;
	private String phone_a;//扩展
	private String phone_b;//扩展
	private int isMarry;
	private String censusAddress;
	private String liveAddress;
	private String archivesAdd;
	private String crimeRecord;
	private String overSeasMarry;
	
	private Integer SUnit;
	private String  SUnit_str;//扩展
	private Integer SDepart;
	private String SDepart_str;
	private Integer flags3;//部门负责人 1是 2不是
	private Integer SPost;//职务
	private String SPost_str;
	private Integer SJob;//职称
	private String SJob_str;//扩展
	private String SEmail;
	private String SUnitPhone;
	private String SUnitPhone_a;//扩展
	private String SUnitPhone_b;//扩展
	private int secrecyYears;
	private int passport;
	private int greenCard;
	private int level;
	private String IDCardFile;
	private String SFilePath;
	
	
	public Integer getFlags3() {
		return flags3;
	}
	public void setFlags3(Integer flags3) {
		this.flags3 = flags3;
	}
	public String getIDNumber_decode() {
		return IDNumber_decode;
	}
	public void setIDNumber_decode(String iDNumber_decode) {
		IDNumber_decode = iDNumber_decode;
	}
	public int getStafferId() {
		return stafferId;
	}
	public void setStafferId(int stafferId) {
		this.stafferId = stafferId;
	}
	public String getStafferName() {
		return stafferName;
	}
	public void setStafferName(String stafferName) {
		this.stafferName = stafferName;
	}
	public int getStafferSex() {
		return stafferSex;
	}
	public void setStafferSex(int stafferSex) {
		this.stafferSex = stafferSex;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	public String getBirthDay_str() {
		return birthDay_str;
	}
	public void setBirthDay_str(String birthDay_str) {
		this.birthDay_str = birthDay_str;
	}
	public Integer getNationality() {
		return nationality;
	}
	public void setNationality(Integer nationality) {
		this.nationality = nationality;
	}
	public String getNationality_str() {
		return nationality_str;
	}
	public void setNationality_str(String nationality_str) {
		this.nationality_str = nationality_str;
	}
	public Integer getPoliticalStatus() {
		return politicalStatus;
	}
	public void setPoliticalStatus(Integer politicalStatus) {
		this.politicalStatus = politicalStatus;
	}
	public String getPoliticalStatus_str() {
		return politicalStatus_str;
	}
	public void setPoliticalStatus_str(String politicalStatus_str) {
		this.politicalStatus_str = politicalStatus_str;
	}
	public Integer getNation() {
		return nation;
	}
	public void setNation(Integer nation) {
		this.nation = nation;
	}
	public String getNation_str() {
		return nation_str;
	}
	public void setNation_str(String nation_str) {
		this.nation_str = nation_str;
	}
	public String getIDNumber() {
		return IDNumber;
	}
	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}
	public String getAssociations() {
		return associations;
	}
	public void setAssociations(String associations) {
		this.associations = associations;
	}
	public String getMobilePone() {
		return mobilePone;
	}
	public void setMobilePone(String mobilePone) {
		this.mobilePone = mobilePone;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhone_a() {
		return phone_a;
	}
	public void setPhone_a(String phone_a) {
		this.phone_a = phone_a;
	}
	public String getPhone_b() {
		return phone_b;
	}
	public void setPhone_b(String phone_b) {
		this.phone_b = phone_b;
	}
	public int getIsMarry() {
		return isMarry;
	}
	public void setIsMarry(int isMarry) {
		this.isMarry = isMarry;
	}
	public String getCensusAddress() {
		return censusAddress;
	}
	public void setCensusAddress(String censusAddress) {
		this.censusAddress = censusAddress;
	}
	public String getLiveAddress() {
		return liveAddress;
	}
	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
	}
	public String getArchivesAdd() {
		return archivesAdd;
	}
	public void setArchivesAdd(String archivesAdd) {
		this.archivesAdd = archivesAdd;
	}
	public String getCrimeRecord() {
		return crimeRecord;
	}
	public void setCrimeRecord(String crimeRecord) {
		this.crimeRecord = crimeRecord;
	}
	public String getOverSeasMarry() {
		return overSeasMarry;
	}
	public void setOverSeasMarry(String overSeasMarry) {
		this.overSeasMarry = overSeasMarry;
	}
	public Integer getSUnit() {
		return SUnit;
	}
	public void setSUnit(Integer sUnit) {
		SUnit = sUnit;
	}
	public String getSUnit_str() {
		return SUnit_str;
	}
	public void setSUnit_str(String sUnit_str) {
		SUnit_str = sUnit_str;
	}
	public Integer getSDepart() {
		return SDepart;
	}
	public void setSDepart(Integer sDepart) {
		SDepart = sDepart;
	}
	public String getSDepart_str() {
		return SDepart_str;
	}
	public void setSDepart_str(String sDepart_str) {
		SDepart_str = sDepart_str;
	}
	public Integer getSPost() {
		return SPost;
	}
	public void setSPost(Integer sPost) {
		SPost = sPost;
	}
	public String getSPost_str() {
		return SPost_str;
	}
	public void setSPost_str(String sPost_str) {
		SPost_str = sPost_str;
	}
	public Integer getSJob() {
		return SJob;
	}
	public void setSJob(Integer sJob) {
		SJob = sJob;
	}
	public String getSJob_str() {
		return SJob_str;
	}
	public void setSJob_str(String sJob_str) {
		SJob_str = sJob_str;
	}
	public String getSEmail() {
		return SEmail;
	}
	public void setSEmail(String sEmail) {
		SEmail = sEmail;
	}
	public String getSUnitPhone() {
		return SUnitPhone;
	}
	public void setSUnitPhone(String sUnitPhone) {
		SUnitPhone = sUnitPhone;
	}
	public String getSUnitPhone_a() {
		return SUnitPhone_a;
	}
	public void setSUnitPhone_a(String sUnitPhone_a) {
		SUnitPhone_a = sUnitPhone_a;
	}
	public String getSUnitPhone_b() {
		return SUnitPhone_b;
	}
	public void setSUnitPhone_b(String sUnitPhone_b) {
		SUnitPhone_b = sUnitPhone_b;
	}
	public int getSecrecyYears() {
		return secrecyYears;
	}
	public void setSecrecyYears(int secrecyYears) {
		this.secrecyYears = secrecyYears;
	}
	public int getPassport() {
		return passport;
	}
	public void setPassport(int passport) {
		this.passport = passport;
	}
	public int getGreenCard() {
		return greenCard;
	}
	public void setGreenCard(int greenCard) {
		this.greenCard = greenCard;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getIDCardFile() {
		return IDCardFile;
	}
	public void setIDCardFile(String iDCardFile) {
		IDCardFile = iDCardFile;
	}
	public String getSFilePath() {
		return SFilePath;
	}
	public void setSFilePath(String sFilePath) {
		SFilePath = sFilePath;
	}
	@Override
	public String toString() {
		return "Staff [stafferId=" + stafferId + ", stafferName=" + stafferName
				+ ", stafferSex=" + stafferSex + ", birthDay=" + birthDay
				+ ", birthDay_str=" + birthDay_str + ", nationality="
				+ nationality + ", nationality_str=" + nationality_str
				+ ", politicalStatus=" + politicalStatus
				+ ", politicalStatus_str=" + politicalStatus_str + ", nation="
				+ nation + ", nation_str=" + nation_str + ", IDNumber="
				+ IDNumber + ", IDNumber_decode=" + IDNumber_decode
				+ ", associations=" + associations + ", mobilePone="
				+ mobilePone + ", phone=" + phone + ", phone_a=" + phone_a
				+ ", phone_b=" + phone_b + ", isMarry=" + isMarry
				+ ", censusAddress=" + censusAddress + ", liveAddress="
				+ liveAddress + ", archivesAdd=" + archivesAdd
				+ ", crimeRecord=" + crimeRecord + ", overSeasMarry="
				+ overSeasMarry + ", SUnit=" + SUnit + ", SUnit_str="
				+ SUnit_str + ", SDepart=" + SDepart + ", SDepart_str="
				+ SDepart_str + ", flags3=" + flags3 + ", SPost=" + SPost
				+ ", SPost_str=" + SPost_str + ", SJob=" + SJob + ", SJob_str="
				+ SJob_str + ", SEmail=" + SEmail + ", SUnitPhone="
				+ SUnitPhone + ", SUnitPhone_a=" + SUnitPhone_a
				+ ", SUnitPhone_b=" + SUnitPhone_b + ", secrecyYears="
				+ secrecyYears + ", passport=" + passport + ", greenCard="
				+ greenCard + ", level=" + level + ", IDCardFile=" + IDCardFile
				+ ", SFilePath=" + SFilePath + "]";
	}
	
	
	
	
}
