package ns.major.config.dao.mapper;

import java.util.List;

import ns.major.config.dao.domain.Staff;

public interface StaffConfigMapper {

	int delete(List<Integer> ids)throws Exception ;
	//人员新增--基本资料
	int baseAdd(Staff staff)throws Exception ;
	//人员新增--基本资料
	int baseUpdate(Staff staff)throws Exception ;
	int getCount_IDNumber(String IDNumber)throws Exception ;
	
	//人员新增--单位信息
	int unitInfoUpdate(Staff staff)throws Exception ;
	//人员信息--选择密级
	int declassifiedUpdate(Staff staff)throws Exception ;
	//人员信息--出境信息
	int expelUpdate(Staff staff)throws Exception ;
	
	//人员信息--附件
	int fileUpdate(Staff staff)throws Exception ;	
	
	Staff getStaffByIDNumber(String IDNumber)throws Exception ;
	Staff getStaffByStafferId(int stafferId)throws Exception ;
	//导入基本信息
	int importBaseAdd(Staff staff)throws Exception ;
	
	//得到部门下所有员工信息
	List<Staff> getStaffByDeptName(String deptName)throws Exception ;
}
