package ns.major.employ.dao.mapper;

import java.util.List;
import java.util.Map;

import ns.major.employ.dao.domain.Unit;
import ns.major.employ.query.UnitQuery;

public interface UnitMapper {
	
	List<Map<Integer,String>> getCity() throws Exception;
	
	List<Map<Integer,String>> getCountry(int Pid) throws Exception;
	
	List<Map<Integer,String>> getProperty() throws Exception;
	
	void addUnit(Unit unit) throws Exception;
	
	void addUnitPhoto(Unit unit) throws Exception;
	
	int queryName(String unitName) throws Exception;
	
	Unit getOne(Unit unit) throws Exception;
	
	void updateUnit(UnitQuery query) throws Exception;
	
	void updateUnitPhoto(UnitQuery query) throws Exception;
	
	Unit getUnit_uuid(String uuid) throws Exception;
	
	List<Unit> getContrast(UnitQuery unit) throws Exception;
	
	void update(Unit unit) throws Exception;
	
	List<String> getAllFilePath(Unit unit) throws Exception;
	
	int credCheck(String cred) throws Exception;
}
