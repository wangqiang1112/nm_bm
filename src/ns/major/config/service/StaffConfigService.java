package ns.major.config.service;

import java.util.List;

import ns.common.Page;
import ns.major.config.query.StaffQuery;
import ns.major.config.dao.domain.FamilyInfo;
import ns.major.config.dao.domain.Staff;
import ns.major.config.dao.domain.StaffEdu;
import ns.major.config.dao.domain.StaffJob;
public interface StaffConfigService {

	Page getAll(StaffQuery queryForm)throws Exception ;

	int delete(List<Integer> ids)throws Exception ;
	
	//int getStafferIdByIDNumber(String idNumber);
	Staff getStaffByIDNumber(String idNumber)throws Exception ;
	Staff getStaffByStafferId(int stafferId)throws Exception ;
	
	//人员新增--基本资料
	Staff baseSave(Staff staff)throws Exception ;
	//人员新增--单位信息
	Staff unitInfoUpdate(Staff staff)throws Exception ;
	//人员信息--选择密级
	Staff declassifiedUpdate(Staff staff)throws Exception ;
	//人员信息--出境信息
	Staff expelUpdate(Staff staff)throws Exception ;
	
	/**
	 * 教育经历
	 */
	//人员信息--教育经历
	int staffEduAdd(StaffEdu staffEdu)throws Exception ;
	int staffEduImport(StaffEdu staffEdu)throws Exception ;
	int staffEduUpdate(StaffEdu staffEdu)throws Exception ;
	int staffEduDel(int eduID)throws Exception ;
	Integer getStaffIdByEduID(int eduID)throws Exception ;
	//获取教育经历列表
	List<StaffEdu>getStaffEduList(int stafferId)throws Exception ;
	//根据主键获取教育经历
	StaffEdu getStaffEduByID(int eduID)throws Exception ;
	
	/**
	 * 工作经历
	 */
	//人员信息--工作经历
	int staffJobAdd(StaffJob staffJob)throws Exception ;
	int staffJobImport(StaffJob staffJob)throws Exception ;
	int staffJobUpdate(StaffJob staffJob)throws Exception ;
	int staffJobDel(int jobID)throws Exception ;
	int getStaffIdByJobID(int jobID)throws Exception ;
	//获取工作经历列表
	List<StaffJob>getStaffJobList(int stafferId)throws Exception ;
	//根据主键获取工作经历
	StaffJob getStaffJobByID(int jobID)throws Exception ;
	
	/**
	 * 家庭成员
	 */
	//人员信息--家庭成员
	int familyInfoAdd(FamilyInfo familyInfo)throws Exception ;
	int familyInfoImport(FamilyInfo familyInfo)throws Exception ;
	int familyInfoUpdate(FamilyInfo familyInfo)throws Exception ;
	int familyInfoDel(int infoID)throws Exception ;
	int getStaffIdByInfoID(int infoID)throws Exception ;
	//获取家庭成员列表
	List<FamilyInfo>getFamilyInfoList(int stafferId)throws Exception ;
	//根据主键获取家庭成员
	FamilyInfo getFamilyInfoByID(int infoID)throws Exception ;
	
	//人员信息--附件
	int fileUpdate(Staff staff)throws Exception ;
	
    //导入人员基本信息
	int importBaseAdd(Staff staff)throws Exception ;
	
	//得到部门下所有员工信息
	List<Staff> getStaffByDeptName(String deptName)throws Exception ;

}
