package ns.major.secrecy.dao.mapper;

import java.util.List;
import java.util.Map;

import ns.major.secrecy.dao.domain.InfoPublish;

public interface InfoPublishMapper {

	List<Map<Integer,String>> getTopicType() throws Exception;
	
	List<Map<Integer,String>> getDeptList() throws Exception;
	
	Map<Integer,String> getDeptOne(int departId) throws Exception;
	
	Map<Integer,String> getTopicTypeName(int pid) throws Exception;
	
	InfoPublish query(int ArticleId) throws Exception;
	
	void save(InfoPublish infoPublish) throws Exception;
	
	void delete(int ArticleId) throws Exception;
	
	List<InfoPublish> main(List<String> list) throws Exception;
	
}
