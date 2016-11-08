package ns.major.common.dao.mapper;

import java.util.List;

import ns.major.config.dao.domain.Department;
import ns.major.config.dao.domain.Dictionary;
import ns.major.employ.dao.domain.Unit;

public interface BaseDataMapper {
	Dictionary getDictionaryByDictid(Integer dictId)throws Exception;
	
	List<Unit> getAllUnit()throws Exception;

	List<Department> getAllDept()throws Exception;
	List<Integer> getDepartIDByDepartName(String str)throws Exception;
	
	List<Dictionary> getNationalityList()throws Exception;
	Integer getNationalityDictId(String str)throws Exception;
	
	List<Dictionary> getPoliticalStatusList()throws Exception;
	Integer getPoliticalStatusDictId(String str)throws Exception;
	
	List<Dictionary> getNationList()throws Exception;
	Integer getNationDictId(String str)throws Exception;
	
	List<Dictionary> getSPostList()throws Exception;
	Integer getSPostDictId(String str)throws Exception;
	
	List<Dictionary> getSJobList()throws Exception;
	Integer getSJobDictId(String str)throws Exception;
	
	Unit getUnitById(int unitId)throws Exception;

	int getDicIdByName(String dicName)throws Exception;


}
