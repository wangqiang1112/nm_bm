package ns.major.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import ns.common.BaseService;
import ns.major.common.dao.mapper.BaseDataMapper;
import ns.major.common.service.BaseDataService;
import ns.major.config.dao.domain.Department;
import ns.major.config.dao.domain.Dictionary;
import ns.major.employ.dao.domain.Unit;
public class BaseDataServiceImpl extends BaseService implements BaseDataService {
	private static final long serialVersionUID = 1L;
	@Autowired
	private BaseDataMapper baseDataDao;
  
  	//从字典表中读取相关信息
	public Dictionary getDictionaryByDictid(Integer dictId)throws Exception{
		return baseDataDao.getDictionaryByDictid(dictId);
	}
  
	public List<Unit> getAllUnit()throws Exception {
		return baseDataDao.getAllUnit();
	}

	public List<Department> getAllDept()throws Exception {
		return baseDataDao.getAllDept();
	}
	public List<Integer>getDepartIDByDepartName(String str)throws Exception{
		return baseDataDao.getDepartIDByDepartName(str);
	}
    //获取国籍列表
	public List<Dictionary> getNationalityList()throws Exception {
		return baseDataDao.getNationalityList();
	}
	public Integer getNationalityDictId(String str)throws Exception{
		return baseDataDao.getNationalityDictId(str);
	}
	//获取政治面貌列表
	public List<Dictionary> getPoliticalStatusList()throws Exception {
		return baseDataDao.getPoliticalStatusList();
	}
	public Integer getPoliticalStatusDictId(String str)throws Exception{
		return baseDataDao.getPoliticalStatusDictId(str);
	}
	//获取民族列表
	public List<Dictionary> getNationList()throws Exception {
		return baseDataDao.getNationList();
	}
	public Integer getNationDictId(String str)throws Exception{
		return baseDataDao.getNationDictId(str);
	}
	//获取职务列表
	public List<Dictionary> getSPostList()throws Exception {
		return baseDataDao.getSPostList();
	}
	public Integer getSPostDictId(String str)throws Exception{
		return baseDataDao.getSPostDictId(str);
	}
	//获取职称列表
	public List<Dictionary> getSJobList()throws Exception {
		return baseDataDao.getSJobList();
	}
	public Integer getSJobDictId(String str)throws Exception{
		return baseDataDao.getSJobDictId(str);
	}
	//根据单位id获取单位
	public Unit getUnitById(int unitId)throws Exception {
		return baseDataDao.getUnitById(unitId);
	}
	//根据字典名字找到对应Id
	public int getDicIdByName(String dicName)throws Exception {
		return baseDataDao.getDicIdByName(dicName);
	}
	
}
