package ns.major.config.dao.mapper;

import java.util.List;
import ns.major.config.dao.domain.StaffJob;


public interface StaffJobMapper {
	int add(StaffJob staffJob)throws Exception ;
	int update(StaffJob staffJob)throws Exception ;
	int del(int jobID)throws Exception ;
	int getStaffIdByJobID(int jobID)throws Exception ;
	
	List<StaffJob>getList(int stafferId)throws Exception ;
	StaffJob getStaffJobByID(int jobID)throws Exception ;
}
