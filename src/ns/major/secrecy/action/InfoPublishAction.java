package ns.major.secrecy.action;

import java.io.*;
import java.util.*;

import ns.common.BaseAction;
import ns.major.secrecy.dao.domain.InfoPublish;
import ns.major.secrecy.query.InfoPublishQuery;
import ns.major.secrecy.service.InfoPublishService;

import org.springframework.stereotype.Controller;

@Controller
public class InfoPublishAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private InfoPublishService infoPublishService;
	private InfoPublishQuery infoPublishQuery;
	private InfoPublish infoPublish;
	private List<InfoPublish> list;
	private List<Map<Integer,String>> topicType;
	private List<Map<Integer,String>> dept;
	private String Pid;

	/**
	 * 首页
	 * 
	 * @return
	 */
	public String main() throws Exception {
		try {
			topicType = infoPublishService.getTopicType();
			list = infoPublishService.main(topicType);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 首页信息
	 * @return
	 */
	public String list() throws Exception{
		infoPublishQuery = this.getForm(InfoPublishQuery.class);
		infoPublishQuery.setPid(Integer.parseInt(Pid));
		Map<Integer,String> topicName = infoPublishService.getTopicTypeName(Integer.parseInt(Pid));
		infoPublishQuery.setTypeName(topicName.get("typeName").toString());
		// 写到前台
		setPage(infoPublishService.main(infoPublishQuery));
		setQuery(infoPublishQuery);
		
		Pid = String.valueOf(infoPublishQuery.getPid());
		// 主题类别
		topicType = infoPublishService.getTopicType();
		return "success";
	}

	/**
	 * 信息列表
	 * 
	 * @return
	 */
	public String index() throws Exception {
		infoPublishQuery = this.getForm(InfoPublishQuery.class);
		// 写到前台
		setPage(infoPublishService.list(infoPublishQuery));
		setQuery(infoPublishQuery);
		topicType = infoPublishService.getTopicType();
		dept = infoPublishService.getDeptList();
		return "success";
	}

	/**
	 * 信息详情
	 * 
	 * @return
	 */
	public String query() throws Exception {
		try {
			InfoPublish infoPublishTemp = this.getForm(InfoPublish.class);
			infoPublish = infoPublishService.query(infoPublishTemp.getArticleId());
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 跳转
	 * 
	 * @return
	 */
	public String jump()  throws Exception{
		try {
			topicType = infoPublishService.getTopicType();
			dept = infoPublishService.getDeptList();
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 新增信息
	 * 
	 * @return
	 * @throws IOException
	 */
	public void save() throws Exception {
		try {
			infoPublish = this.getForm(InfoPublish.class);
			infoPublishService.save(infoPublish);
			success("添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			error("添加失败！");
		}
		getHttpResponse().sendRedirect(getContextPath()+"/secrecy/index.do");
	}

	/**
	 * 删除信息
	 * 
	 * @return
	 * @throws IOException 
	 */
	public void delete() throws Exception {
		try {
			infoPublish = this.getForm(InfoPublish.class);
			infoPublishService.delete(infoPublish.getArticleId());
			success("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			error("删除失败");
		}
		getHttpResponse().sendRedirect(getContextPath()+"/secrecy/index.do");
	}
	

	// ---------------

	public InfoPublishQuery getInfoPublishQuery() {
		return infoPublishQuery;
	}

	public void setInfoPublishQuery(InfoPublishQuery infoPublishQuery) {
		this.infoPublishQuery = infoPublishQuery;
	}

	public List<Map<Integer, String>> getTopicType() {
		return topicType;
	}

	public void setTopicType(List<Map<Integer, String>> topicType) {
		this.topicType = topicType;
	}

	public List<Map<Integer, String>> getDept() {
		return dept;
	}

	public void setDept(List<Map<Integer, String>> dept) {
		this.dept = dept;
	}

	public InfoPublish getInfoPublish() {
		return infoPublish;
	}

	public void setInfoPublish(InfoPublish infoPublish) {
		this.infoPublish = infoPublish;
	}

	public InfoPublishService getInfoPublishService() {
		return infoPublishService;
	}

	public void setInfoPublishService(InfoPublishService infoPublishService) {
		this.infoPublishService = infoPublishService;
	}

	public List<InfoPublish> getList() {
		return list;
	}

	public void setList(List<InfoPublish> list) {
		this.list = list;
	}

	public String getPid() {
		return Pid;
	}

	public void setPid(String pid) {
		Pid = pid;
	}

}
