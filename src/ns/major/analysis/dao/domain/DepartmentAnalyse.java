package ns.major.analysis.dao.domain;
//部门分析
public class DepartmentAnalyse{
	public Integer num;//序号
	public Integer departId;
	public String  departName;
	public Integer deptAmount;//已下发 
	public Integer closeAmount;//已经关闭 
	public Double  percent;//完成百分比 
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getDepartId() {
		return departId;
	}
	public void setDepartId(Integer departId) {
		this.departId = departId;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public Integer getDeptAmount() {
		return deptAmount;
	}
	public void setDeptAmount(Integer deptAmount) {
		this.deptAmount = deptAmount;
	}
	public Integer getCloseAmount() {
		return closeAmount;
	}
	public void setCloseAmount(Integer closeAmount) {
		this.closeAmount = closeAmount;
	}
	public Double getPercent() {
		return percent;
	}
	public void setPercent(Double percent) {
		this.percent = percent;
	}

	
	
}
