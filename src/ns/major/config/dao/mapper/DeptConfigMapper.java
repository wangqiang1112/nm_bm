package ns.major.config.dao.mapper;

import java.util.List;

import ns.major.config.dao.domain.Department;
import ns.major.config.dao.domain.Staff;

public interface DeptConfigMapper {

	int delete(int deptId)throws Exception ;

	List<Staff> getAllStaff()throws Exception ;

	int update(Department dept)throws Exception ;

	int add(Department dept)throws Exception ;

	Department getDeptById(int deptId)throws Exception ;

	List<Staff> getStaffByDept(int departId)throws Exception ;

	int deptNameCheck(String departName)throws Exception ;

	int getLastId();

}
