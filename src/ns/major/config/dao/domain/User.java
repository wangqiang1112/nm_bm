package ns.major.config.dao.domain;
/**
 * 用户实体类
 * @author Administrator
 *
 */
public class User {

	private int userId;
	private String userName;
	private String stafferName;
	private String upassword;
	private int userType;
	private int departId;
	private String departName;
	private String userPhone;
	private String offerPhone;
	private String userFax;
	private String uRemarks;
	private String Email;
	private String unitName;
	private int unitId;
	private int IsEnable;
	private int roleId;
	
	private String UUID;
	
	
	public String getUUID() {
		return UUID;
	}
	public void setUUID(String uUID) {
		UUID = uUID;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public int getUnitId() {
		return unitId;
	}
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStafferName() {
		return stafferName;
	}
	public void setStafferName(String stafferName) {
		this.stafferName = stafferName;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public int getDepartId() {
		return departId;
	}
	public void setDepartId(int departId) {
		this.departId = departId;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getOfferPhone() {
		return offerPhone;
	}
	public void setOfferPhone(String offerPhone) {
		this.offerPhone = offerPhone;
	}
	public String getUserFax() {
		return userFax;
	}
	public void setUserFax(String userFax) {
		this.userFax = userFax;
	}
	public String getuRemarks() {
		return uRemarks;
	}
	public void setuRemarks(String uRemarks) {
		this.uRemarks = uRemarks;
	}
	public int getIsEnable() {
		return IsEnable;
	}
	public void setIsEnable(int isEnable) {
		IsEnable = isEnable;
	}
	
	
	
}
