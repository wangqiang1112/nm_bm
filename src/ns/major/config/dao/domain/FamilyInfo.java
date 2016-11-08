package ns.major.config.dao.domain;
//家庭成员信息
public class FamilyInfo {
	private Integer InfoID;//主键
	private String IDNumber;//扩展
	private Integer StafferId;
	private String StafferName;
	private String Relation;
	private String FName;
	private int FSex;
	private String FSex_str;
	private int FAge;
	private String FNationality;
	private String FNationality_str;
	private String FUnit;
	private String FPost;
	private String FJob;
	private String FAddress;
	private String FPhone;
	
	
	public String getFSex_str() {
		return FSex_str;
	}
	public void setFSex_str(String fSex_str) {
		FSex_str = fSex_str;
	}
	public String getFNationality_str() {
		return FNationality_str;
	}
	public void setFNationality_str(String fNationality_str) {
		FNationality_str = fNationality_str;
	}
	public String getFJob() {
		return FJob;
	}
	public void setFJob(String fJob) {
		FJob = fJob;
	}
	public String getIDNumber() {
		return IDNumber;
	}
	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}
	public Integer getInfoID() {
		return InfoID;
	}
	public void setInfoID(Integer infoID) {
		InfoID = infoID;
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
	public String getRelation() {
		return Relation;
	}
	public void setRelation(String relation) {
		Relation = relation;
	}
	public String getFName() {
		return FName;
	}
	public void setFName(String fName) {
		FName = fName;
	}
	public int getFSex() {
		return FSex;
	}
	public void setFSex(int fSex) {
		FSex = fSex;
	}
	public int getFAge() {
		return FAge;
	}
	public void setFAge(int fAge) {
		FAge = fAge;
	}
	public String getFNationality() {
		return FNationality;
	}
	public void setFNationality(String fNationality) {
		FNationality = fNationality;
	}
	public String getFUnit() {
		return FUnit;
	}
	public void setFUnit(String fUnit) {
		FUnit = fUnit;
	}
	public String getFPost() {
		return FPost;
	}
	public void setFPost(String fPost) {
		FPost = fPost;
	}
	public String getFAddress() {
		return FAddress;
	}
	public void setFAddress(String fAddress) {
		FAddress = fAddress;
	}
	public String getFPhone() {
		return FPhone;
	}
	public void setFPhone(String fPhone) {
		FPhone = fPhone;
	}
	@Override
	public String toString() {
		return "FamilyInfo [InfoID=" + InfoID + ", IDNumber=" + IDNumber
				+ ", StafferId=" + StafferId + ", StafferName=" + StafferName
				+ ", Relation=" + Relation + ", FName=" + FName + ", FSex="
				+ FSex + ", FAge=" + FAge + ", FNationality=" + FNationality
				+ ", FUnit=" + FUnit + ", FPost=" + FPost + ", FJob=" + FJob
				+ ", FAddress=" + FAddress + ", FPhone=" + FPhone + "]";
	}
	

}
