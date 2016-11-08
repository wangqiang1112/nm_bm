package ns.major.common.service;

import java.util.List;

import ns.major.config.dao.domain.Department;
import ns.major.config.dao.domain.Dictionary;
import ns.major.employ.dao.domain.Unit;

public interface BaseDataService {
	//从字典表中读取相关信息
	public Dictionary getDictionaryByDictid(Integer dictId)throws Exception;
	
    //获取单位列表
	public List<Unit> getAllUnit()throws Exception;
	//获取部门列表
	public List<Department> getAllDept()throws Exception;
	public List<Integer>getDepartIDByDepartName(String str)throws Exception;
	//获取国籍列表
	public List<Dictionary>getNationalityList()throws Exception;
	public Integer getNationalityDictId(String str)throws Exception;
	//获取政治面貌列表
	public List<Dictionary> getPoliticalStatusList()throws Exception;
	public Integer getPoliticalStatusDictId(String str)throws Exception;
	//获取民族列表
	public List<Dictionary>getNationList()throws Exception;
	public Integer getNationDictId(String str)throws Exception;
	//获取职务列表
	public List<Dictionary> getSPostList()throws Exception;
	public Integer getSPostDictId(String str)throws Exception;
	//获取职称列表
	public List<Dictionary> getSJobList()throws Exception;
	public Integer getSJobDictId(String str)throws Exception;
	//根据单位id获取单位
	public Unit getUnitById(int unitId)throws Exception;
    //根据字典名字找到对应Id
	public int getDicIdByName(String dicName)throws Exception;
}
