package ns.major.config.dao.mapper;

import java.util.List;

import ns.major.config.dao.domain.FamilyInfo;

public interface FamilyInfoMapper {
	int add(FamilyInfo familyInfo)throws Exception ;
	int update(FamilyInfo familyInfo)throws Exception ;
	int del(int infoID)throws Exception ;
	int getStaffIdByInfoID(int infoID)throws Exception ;
	
	List<FamilyInfo>getList(int stafferId)throws Exception ;
	FamilyInfo getFamilyInfoByID(int infoID)throws Exception ;
}
