package ns.major.config.query;

import ns.common.BaseQuery;

public class DeptQuery extends BaseQuery {
	//负责人
  private String DPrincipal;
  private String DepartName;
  private int DepartId;
  private String type;
public String getdPrincipal() {
	return DPrincipal;
}
public void setdPrincipal(String DPrincipal) {
	this.DPrincipal = DPrincipal;
}
public String getDPrincipal() {
	return DPrincipal;
}
public void setDPrincipal(String dPrincipal) {
	DPrincipal = dPrincipal;
}

public String getDepartName() {
	return DepartName;
}
public void setDepartName(String departName) {
	DepartName = departName;
}
public int getDepartId() {
	return DepartId;
}
public void setDepartId(int departId) {
	DepartId = departId;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}

  
}
