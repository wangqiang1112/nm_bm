package ns.common;

import java.io.Serializable;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public class BaseService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(BaseService.class);
	@Autowired
	protected SqlSessionFactory sqlSessionFactory;
	
	protected SqlSession session = null;
	
	
	/**
	 * 通用分页查询
	 * @param mapperClass
	 * @param sqlID  对应mapper.xml文件中的每个操作ID
	 * @param params 参数
	 * @return
	 */
	protected Page pageQuery(Class<?> mapperClass,String sqlID,BaseQuery query){
		
		try {
			logger.info(mapperClass.getName()+"."+sqlID+"-查询开始");
			session = sqlSessionFactory.openSession();
			PageBounds pageBounds = new PageBounds(query.getPageIndex(),query.getPageSize());
			PageList<?> list = (PageList<?>)session.selectList(mapperClass.getName()+"."+sqlID,query,pageBounds);
			Page p =new Page();
			p.setResult(list);
			p.setPageIndex(query.getPageIndex());
			p.setPageSize(query.getPageSize());
			p.setPageCount(list.getPaginator().getTotalPages());
			p.setTotalCount(list.getPaginator().getTotalCount());
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}


	
	
}
