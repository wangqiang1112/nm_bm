package ns.major.analysis.dao.domain;
//任务统计
public class UnitCensus {
	public Integer num;//序号
	public Integer unitId;
	public String  unitName;
	public Integer dealA;//下发量 1
	public Integer dealB;//未报量  2
	public Integer dealC;//已报量 3
	public Integer dealD;//已关闭  4
	public Double dealE;//完成百分比
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
	public Integer getDealA() {
		return dealA;
	}
	public void setDealA(Integer dealA) {
		this.dealA = dealA;
	}
	public Integer getDealB() {
		return dealB;
	}
	public void setDealB(Integer dealB) {
		this.dealB = dealB;
	}
	public Integer getDealC() {
		return dealC;
	}
	public void setDealC(Integer dealC) {
		this.dealC = dealC;
	}
	public Integer getDealD() {
		return dealD;
	}
	public void setDealD(Integer dealD) {
		this.dealD = dealD;
	}
	public Double getDealE() {
		return dealE;
	}
	public void setDealE(Double dealE) {
		this.dealE = dealE;
	}
	@Override
	public String toString() {
		return "UnitCensus [num=" + num + ", unitId=" + unitId + ", unitName="
				+ unitName + ", dealA=" + dealA + ", dealB=" + dealB
				+ ", dealC=" + dealC + ", dealD=" + dealD + ", dealE=" + dealE
				+ ", getNum()=" + getNum() + ", getUnitId()=" + getUnitId()
				+ ", getUnitName()=" + getUnitName() + ", getDealA()="
				+ getDealA() + ", getDealB()=" + getDealB() + ", getDealC()="
				+ getDealC() + ", getDealD()=" + getDealD() + ", getDealE()="
				+ getDealE() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	

}
