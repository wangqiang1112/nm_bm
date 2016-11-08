package ns.major.employ.query;

import java.util.List;

import ns.common.BaseQuery;

public class UnitQuery extends BaseQuery {

	private String unitName;// 单位名称
	private String unitProperty;// 单位性质
	private String unitCity;// 单位所在市
	private int checkStatus;// 审核状态：1待审核；2已通过；3未通过
	private String unitIds;// 批量删除Id
	private String type; // 通过或打回
	private List<Integer> list; // 批量审核
	private int unitId;// 单位Id
	private int id;// 快照表ID
	private String updateTime; // 变更时间
	private int clas;// 类型= 审核;查询
	private int level; // 级别= 部门;单位

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getClas() {
		return clas;
	}

	public void setClas(int clas) {
		this.clas = clas;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
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

	public int getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(int checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getUnitIds() {
		return unitIds;
	}

	public void setUnitIds(String unitIds) {
		this.unitIds = unitIds;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Integer> getList() {
		return list;
	}

	public void setList(List<Integer> list) {
		this.list = list;
	}

}
