package ns.major.secrecy.service;

import java.util.List;
import java.util.Map;

import ns.common.Page;
import ns.major.secrecy.dao.domain.InfoPublish;
import ns.major.secrecy.query.InfoPublishQuery;

public interface InfoPublishService {
	
	List<InfoPublish> main(List<Map<Integer,String>> list) throws Exception;
	
	Page main(InfoPublishQuery query) throws Exception;
	
	Page list(InfoPublishQuery query) throws Exception;
	
	List<Map<Integer,String>> getTopicType() throws Exception;
	
	List<Map<Integer,String>> getDeptList() throws Exception;
	
	Map<Integer,String> getDeptOne(int departId) throws Exception;
	
	Map<Integer,String> getTopicTypeName(int pid) throws Exception;
	
	InfoPublish query(int ArticleId) throws Exception;
	
	void save(InfoPublish infoPublish) throws Exception;
	
	void delete(int ArticleId) throws Exception;
	
}
