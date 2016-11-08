package ns.major.analysis.dao.domain;
//任务统计
public class EmployAnalyse {
	public Integer num;//序号
	public Integer unitId;
	public String  unitName;
	public Integer unitAmount;//下发量 
	public Integer wAmount;//未报量  
	public Integer yAmount;//已报量 
	public Integer closeAmount;//已关闭  
	public Double percent;//完成百分比
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public Integer getUnitAmount() {
		return unitAmount;
	}
	public void setUnitAmount(Integer unitAmount) {
		this.unitAmount = unitAmount;
	}
	public Integer getwAmount() {
		return wAmount;
	}
	public void setwAmount(Integer wAmount) {
		this.wAmount = wAmount;
	}
	public Integer getyAmount() {
		return yAmount;
	}
	public void setyAmount(Integer yAmount) {
		this.yAmount = yAmount;
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
