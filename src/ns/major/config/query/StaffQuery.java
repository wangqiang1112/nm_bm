package ns.major.config.query;

import ns.common.BaseQuery;

public class StaffQuery extends BaseQuery{
    
	private String StafferIds;
	private String StafferName;
	private String SPost;
	private String SDepart;
	public String getStafferIds() {
		return StafferIds;
	}
	public void setStafferIds(String stafferIds) {
		StafferIds = stafferIds;
	}
	public String getStafferName() {
		return StafferName;
	}
	public void setStafferName(String stafferName) {
		StafferName = stafferName;
	}
	public String getSPost() {
		return SPost;
	}
	public void setSPost(String sPost) {
		SPost = sPost;
	}
	public String getSDepart() {
		return SDepart;
	}
	public void setSDepart(String sDepart) {
		SDepart = sDepart;
	}
	
}
