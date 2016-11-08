package ns.major.analysis.dao.domain;

public class WarnCensus {
	private Integer num;
	private Integer unitId;
	private String unitName;
	private Integer countA;
	private Integer countB;
	private Double countC;
	
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
	public Integer getCountA() {
		return countA;
	}
	public void setCountA(Integer countA) {
		this.countA = countA;
	}
	public Integer getCountB() {
		return countB;
	}
	public void setCountB(Integer countB) {
		this.countB = countB;
	}
	public Double getCountC() {
		return countC;
	}
	public void setCountC(Double countC) {
		this.countC = countC;
	}
	@Override
	public String toString() {
		return "WarnCensus [unitId=" + unitId + ", unitName=" + unitName
				+ ", countA=" + countA + ", countB=" + countB + ", countC="
				+ countC + "]";
	}
	
}
