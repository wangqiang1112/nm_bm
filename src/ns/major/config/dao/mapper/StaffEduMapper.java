package ns.major.config.dao.mapper;

import java.util.List;

import ns.major.config.dao.domain.StaffEdu;

public interface StaffEduMapper {
	int add(StaffEdu staffEdu)throws Exception ;
	int update(StaffEdu staffEdu)throws Exception ;
	int del(int eduID)throws Exception ;
	int getStaffIdByEduID(int eduID)throws Exception ;
	
	List<StaffEdu>getList(int stafferId)throws Exception ;
	StaffEdu getStaffEduByID(int eduID)throws Exception ;
}
