package ns.major.analysis.dao.domain;
//任务统计
public class TaskCensus {
	public Integer num;//序号
	public Integer departId;
	public String  departName;
	public Integer dealA;//已下发 status 1
	public Integer dealB;//已接收 status 2
	public Integer dealC;//已打回 status 3
	public Integer dealD;//已上报 status 4
	public Integer dealE;//已关闭 status 5
	public Double dealE_A;//完成百分比

	
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Double getDealE_A() {
		return dealE_A;
	}
	public void setDealE_A(Double dealE_A) {
		this.dealE_A = dealE_A;
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
	public Integer getDealE() {
		return dealE;
	}
	public void setDealE(Integer dealE) {
		this.dealE = dealE;
	}
	@Override
	public String toString() {
		return "TaskCensus [departId=" + departId + ", departName="
				+ departName + ", dealA=" + dealA + ", dealB=" + dealB
				+ ", dealC=" + dealC + ", dealD=" + dealD + ", dealE=" + dealE
				+ "]";
	}
	
}
