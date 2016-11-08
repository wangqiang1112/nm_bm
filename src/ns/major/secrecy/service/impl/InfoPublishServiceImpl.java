package ns.major.secrecy.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ns.common.BaseService;
import ns.common.Page;
import ns.major.secrecy.dao.domain.InfoPublish;
import ns.major.secrecy.dao.mapper.InfoPublishMapper;
import ns.major.secrecy.query.InfoPublishQuery;
import ns.major.secrecy.service.InfoPublishService;
import ns.util.UploadFileUtil;

@Service
public class InfoPublishServiceImpl extends BaseService implements
		InfoPublishService {
	
	private static final long serialVersionUID = 1L;
	@Autowired
	private InfoPublishMapper infoPublishMapper;
	

	
	public Page main(InfoPublishQuery query) throws Exception {
		Page p = pageQuery(InfoPublishMapper.class, "mainA", query);
		
		// 将内容转换成纯文本
		String reEx_html = "<[^>]+>"; // html标签正则
		Pattern p_html ;
		Matcher m_html ;
		List<?> infoList = p.getResult();
		for(int i = 0 ; i < infoList.size() ; i++){
			InfoPublish info = (InfoPublish) infoList.get(i);
			p_html = Pattern.compile(reEx_html,Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(info.getContentLevel());
			info.setContentLevel(m_html.replaceAll(""));//过滤html标签
		}
		return p;
	}

	public Page list(InfoPublishQuery query) throws Exception {
		return pageQuery(InfoPublishMapper.class, "list", query);
	}

	public List<Map<Integer,String>> getTopicType() throws Exception {
		return infoPublishMapper.getTopicType();
	}

	public List<Map<Integer,String>> getDeptList() throws Exception {
		return infoPublishMapper.getDeptList();
	}

	public Map<Integer,String> getDeptOne(int departId) throws Exception {
		return infoPublishMapper.getDeptOne(departId);
	}

	public InfoPublish query(int ArticleId) throws Exception {
		return infoPublishMapper.query(ArticleId);
	}

	public void save(InfoPublish infoPublish) throws Exception {
		//根据ID查询部门名称和主题类别重新赋值
		try {
			infoPublish.setDepartName(this.getDeptOne(infoPublish.getDepartId()).get("departName").toString());
			infoPublish.setTopicType(this.getTopicTypeName(infoPublish.getPid()).get("typeName").toString());
			//移动附件到正式目录 并得到路径 
			if(infoPublish.getFileTemp() != null && infoPublish.getFileTemp() != ""){
				String filepath = UploadFileUtil.moveUploadFile(infoPublish.getFileTemp());
				//设置新的路径
				infoPublish.setArticleFilePath(filepath);
			}
			
			//数据入库
			infoPublishMapper.save(infoPublish);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(int ArticleId) throws Exception {
		infoPublishMapper.delete(ArticleId);
	}

	public Map<Integer,String> getTopicTypeName(int pid) throws Exception {
		return infoPublishMapper.getTopicTypeName(pid);
	}

	public List<InfoPublish> main(List<Map<Integer,String>> list) throws Exception {
		// 写入主题类别
		List<String> typeList = new ArrayList<String>();
		for(Map<Integer,String> a : list){
			typeList.add(a.get("pid"));
		}
		
		// 将内容转成纯文本
		String reEx_html = "<[^>]+>"; // html标签正则
		Pattern p_html ;
		Matcher m_html ;
		List<InfoPublish> infoList = infoPublishMapper.main(typeList);
		for(InfoPublish info : infoList){
			p_html = Pattern.compile(reEx_html,Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(info.getContentLevel());
			info.setContentLevel(m_html.replaceAll(""));//过滤html标签
		}
		return infoList;
	}
}
