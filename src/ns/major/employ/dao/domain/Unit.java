package ns.major.employ.dao.domain;


public class Unit {

	private int unitId;// 单位Id
	private String unitName;// 单位名称
	private String unitProperty;// 单位性质
	private String unitCity;// 单位所在市
	private String unitCountry;// 单位所在县
	private String unitAddress;// 单位地址
	private String unitTelePhone;// 联系人电话（手机）
	private String credentialsNum;// 组织机构代码证号 -- 加密
	private String principal;// 责任人
	private String pricipalPhone;// 责任人电话
	private String filePath;// 附件地址
	private int checkStatus;// 审核状态：1待审核；2已通过；3未通过
	private String uuid;// UUID
	private String unitContact; // 联系人

	private String fileTemp;// 临时文件
	private String cityName;// 所在市名字
	private String country;// 所在区县名字
	private String property;// 性质名字
	private String type; // 审核结果
	private String updateTime; // 更改时间
	private int dealType;// 操作类型
	private String fileName; // 文件名字
	private int id;// 快照表ID

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUnitContact() {
		return unitContact;
	}

	public void setUnitContact(String unitContact) {
		this.unitContact = unitContact;
	}

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitProperty() {
		return unitProperty;
	}

	public void setUnitProperty(String unitProperty) {
		this.unitProperty = unitProperty;
	}

	public String getUnitCity() {
		return unitCity;
	}

	public void setUnitCity(String unitCity) {
		this.unitCity = unitCity;
	}

	public String getUnitCountry() {
		return unitCountry;
	}

	public void setUnitCountry(String unitCountry) {
		this.unitCountry = unitCountry;
	}

	public String getUnitAddress() {
		return unitAddress;
	}

	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}

	public String getUnitTelePhone() {
		return unitTelePhone;
	}

	public void setUnitTelePhone(String unitTelePhone) {
		this.unitTelePhone = unitTelePhone;
	}

	public String getCredentialsNum() {
		return credentialsNum;
	}

	public void setCredentialsNum(String credentialsNum) {
		this.credentialsNum = credentialsNum;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getPricipalPhone() {
		return pricipalPhone;
	}

	public void setPricipalPhone(String pricipalPhone) {
		this.pricipalPhone = pricipalPhone;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(int checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Unit() {
		super();
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getFileTemp() {
		return fileTemp;
	}

	public void setFileTemp(String fileTemp) {
		this.fileTemp = fileTemp;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getDealType() {
		return dealType;
	}

	public void setDealType(int dealType) {
		this.dealType = dealType;
	}

	@Override
	public String toString() {
		return "Unit [unitId=" + unitId + ", unitName=" + unitName
				+ ", unitProperty=" + unitProperty + ", unitCity=" + unitCity
				+ ", unitCountry=" + unitCountry + ", unitAddress="
				+ unitAddress + ", unitTelePhone=" + unitTelePhone
				+ ", credentialsNum=" + credentialsNum + ", principal="
				+ principal + ", pricipalPhone=" + pricipalPhone
				+ ", filePath=" + filePath + ", checkStatus=" + checkStatus
				+ ", uuid=" + uuid + ", fileTemp=" + fileTemp + ", cityName="
				+ cityName + ", property=" + property + ", type=" + type
				+ ", updateTime=" + updateTime + "]";
	}

}
