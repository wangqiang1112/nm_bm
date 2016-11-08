package ns.major.config.dao.mapper;

import java.util.List;

import ns.major.config.dao.domain.Dictionary;

public interface DictionaryMapper {

	List<Dictionary> getList(String key)throws Exception ;
	List<Dictionary> getNationalityList()throws Exception ;//国籍20
	List<Dictionary> getPoliticalStatusList()throws Exception ;//政治面貌 50
	List<Dictionary> getNationList()throws Exception ;//30 民族
}
