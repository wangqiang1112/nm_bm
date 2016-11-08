package ns.major.employ.service;

import java.util.List;
import java.util.Map;

import ns.common.Page;
import ns.major.config.dao.domain.User;
import ns.major.employ.dao.domain.Unit;
import ns.major.employ.query.UnitQuery;

public interface UnitService {
	
	List<Map<Integer,String>> getCity() throws Exception;
	
	List<Map<Integer,String>> getCountry(int Pid) throws Exception;
	
	List<Map<Integer,String>> getProperty() throws Exception;
	
	void save(Unit unit) throws Exception;
	
	void savePhoto(Unit unit) throws Exception;
	
	Boolean queryName(String unitName) throws Exception;
	
	Page list(UnitQuery query,String page1) throws Exception;
	
	Unit getOne(Unit unit) throws Exception;
	
	void update(UnitQuery query) throws Exception;
	
	Unit getUnit_uuid(String uuid) throws Exception;
	
	Page photoList(UnitQuery query) throws Exception;
	
	List<Unit> getContrast(UnitQuery query) throws Exception;
	
	Unit report(User user) throws Exception;
	
	void update(Unit unit) throws Exception;
	
	List<String> getAllFilePath(Unit unit) throws Exception;
	
	Boolean credCheck(String cred) throws Exception;

}
