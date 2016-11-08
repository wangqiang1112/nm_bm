package ns.major.config.dao.domain;

public class Department {

	private int DepartID;
	private String ParentDepart;
	private String DepartName;
	private String DPrincipal;
	private String DPhone;
	private String DDescription;
	private Integer DPrincipalId;
	private String DPrincPost;//扩展-负责人职位
	
	public String getDPrincPost() {
		return DPrincPost;
	}
	public void setDPrincPost(String dPrincPost) {
		DPrincPost = dPrincPost;
	}
	public int getDepartID() {
		return DepartID;
	}
	public void setDepartID(int departID) {
		DepartID = departID;
	}
	public String getParentDepart() {
		return ParentDepart;
	}
	public void setParentDepart(String parentDepart) {
		ParentDepart = parentDepart;
	}
	public String getDepartName() {
		return DepartName;
	}
	public void setDepartName(String departName) {
		DepartName = departName;
	}
	public String getDPrincipal() {
		return DPrincipal;
	}
	public void setDPrincipal(String dPrincipal) {
		DPrincipal = dPrincipal;
	}
	public String getDPhone() {
		return DPhone;
	}
	public void setDPhone(String dPhone) {
		DPhone = dPhone;
	}
	public String getDDescription() {
		return DDescription;
	}
	public void setDDescription(String dDescription) {
		DDescription = dDescription;
	}
	public Integer getDPrincipalId() {
		return DPrincipalId;
	}
	public void setDPrincipalId(Integer dPrincipalId) {
		DPrincipalId = dPrincipalId;
	}
	
	
	
	
	
}
