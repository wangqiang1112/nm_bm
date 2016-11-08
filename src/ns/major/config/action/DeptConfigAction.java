package ns.major.config.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import ns.common.BaseAction;
import ns.common.Global;
import ns.common.Page;
import ns.major.config.dao.domain.Department;
import ns.major.config.dao.domain.Staff;
import ns.major.config.query.DeptQuery;
import ns.major.config.query.StaffQuery;
import ns.major.config.service.DeptConfigService;
import ns.major.config.service.StaffConfigService;

public class DeptConfigAction extends BaseAction {

	private DeptConfigService deptConfServ;
	private StaffConfigService  staffConfServ;
    private List<Staff> stafflist;
    private Department dept;
	
	public String list()throws Exception {
		try {
			DeptQuery queryForm=getForm(DeptQuery.class);
			setPage(deptConfServ.getAll(queryForm));
			setQuery(queryForm);
		} catch (Exception e) {
			e.printStackTrace();
			//写入系统异常日志
			this.logSysExcption(Global.CONFIG_MODEL, e.toString());
			return "error";
		}
		
		return "success";
	}
    public void delete()throws Exception {
    	int deptId=getForm(DeptQuery.class).getDepartId();
    	try {
    		
    		deptConfServ.delete(deptId);
			this.success("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.CONFIG_MODEL, e.toString());
			this.error("删除失败");
		}
    	getHttpResponse().sendRedirect(getContextPath()+"/config/department/list.do");
    }
    public String addOneDept()throws Exception {
    	
    	stafflist= deptConfServ.getAllStaff();
    	String type=getForm(DeptQuery.class).getType();
    	if(type.equals("edit")){
    		int deptId=getForm(DeptQuery.class).getDepartId();
    		dept=deptConfServ.getDeptById(deptId);
    	}
    	getHttpRequest().setAttribute("type", type);
    	return "success";
    }
    public void add() throws Exception {
    	Department dept=getForm(Department.class);
    	String type=getForm(DeptQuery.class).getType();
    	try {
    		if(type.equals("add")){
    			if(dept.getDPrincipalId()+""==""||dept.getDPrincipalId()==null){
    				dept.setDPrincipalId(0);
    			}
    			this.success("添加成功",true);
        	}
    		if(type.equals("edit")){
    			deptConfServ.update(dept);
    			this.success("编辑成功",true);
    		}
    		
		} catch (Exception e) {
			e.printStackTrace();
			this.error("操作失败",true);
		}
    	 getHttpResponse().sendRedirect(getContextPath()+"/toMessage.do");
    }
    public String searchStaff()throws Exception {
    	int departId=getForm(Department.class).getDepartID();
    	StaffQuery queryForm=getForm(StaffQuery.class);
    	queryForm.setSDepart(departId+"");
		setPage(staffConfServ.getAll(queryForm));
		setQuery(queryForm);
    	//List<Staff> staffs=deptConfServ.getStaffByDept(departId);
    	//getHttpRequest().setAttribute("staffs", staffs);
		return "success";
	}
    //部门下是否存在员工，存在不允许删除
    public void checkStaff() throws Exception {
    	boolean flag=true;
    	StaffQuery staffQuery=getForm(StaffQuery.class);
    	Page page=staffConfServ.getAll(staffQuery);
    	if(page.getResult().isEmpty())
    	{
    		flag=false;
    	}
    	getHttpResponse().setContentType("text/json;charset=utf-8");
    	getHttpResponse().getWriter().write(flag+"");
    	getHttpResponse().getWriter().close();
    }
    //部门名字除重
    public void deptNameCheck() throws Exception {
		Map<String,String> map=new HashMap<String,String>();
		try {
			String departName=getHttpRequest().getParameter("param");
			Boolean ifExit=deptConfServ.deptNameCheck(departName);
			if(ifExit==true){
				map.put("info", "通过验证");
				map.put("status","y");
			}else{
				map.put("info", "部门名字已存在");
				map.put("status","n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.CONFIG_MODEL, e.toString());
		}
		JSONObject json=JSONObject.fromObject(map);
		getHttpResponse().setContentType("text/javascript;charset=UTF-8");
		getHttpResponse().getWriter().write(json.toString());
	}
	public DeptConfigService getDeptConfServ() {
		return deptConfServ;
	}

	public void setDeptConfServ(DeptConfigService deptConfServ) {
		this.deptConfServ = deptConfServ;
	}
	public List<Staff> getStafflist() {
		return stafflist;
	}
	public void setStafflist(List<Staff> stafflist) {
		this.stafflist = stafflist;
	}
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	public StaffConfigService getStaffConfServ() {
		return staffConfServ;
	}
	public void setStaffConfServ(StaffConfigService staffConfServ) {
		this.staffConfServ = staffConfServ;
	}
	
	
}
