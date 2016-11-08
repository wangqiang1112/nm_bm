package ns.major.config.service;

import java.util.List;

import ns.common.Page;
import ns.major.config.dao.domain.Department;
import ns.major.config.dao.domain.Staff;
import ns.major.config.query.DeptQuery;

public interface DeptConfigService {

	public Page getAll(DeptQuery queryForm)throws Exception ;

	public int delete(int deptId)throws Exception ;

	public List<Staff> getAllStaff()throws Exception ;

	public int add(Department dept)throws Exception ;

	public int update(Department dept)throws Exception ;

	public Department getDeptById(int deptId)throws Exception ;

	public List<Staff> getStaffByDept(int departId)throws Exception ;
    //部门名字除重
	public Boolean deptNameCheck(String departName)throws Exception ;



}
