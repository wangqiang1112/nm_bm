package ns.major.secrecy.dao.domain;

import java.io.File;

public class InfoPublish {

	private int articleId;// 文章ID
	private String topicType;// 主题类别
	private int departId;// 部门ID
	private String departName;// 部门
	private String publisher;// 发布人
	private String publishTime;// 发布时间
	private int articleLevel;// 文章密级
	private String topic;// 主题
	private String contentLevel;// 正文内容
	private String articleFilePath;// 附件路径
	private int pid;
	private File infoFile;// 上传文件

	private String typeName;// 主题类别名字
	private String fileTemp;// 临时文件

	public String getFileTemp() {
		return fileTemp;
	}

	public void setFileTemp(String fileTemp) {
		this.fileTemp = fileTemp;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getTopicType() {
		return topicType;
	}

	public void setTopicType(String topicType) {
		this.topicType = topicType;
	}

	public int getDepartId() {
		return departId;
	}

	public void setDepartId(int departId) {
		this.departId = departId;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public int getArticleLevel() {
		return articleLevel;
	}

	public void setArticleLevel(int articleLevel) {
		this.articleLevel = articleLevel;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getContentLevel() {
		return contentLevel;
	}

	public void setContentLevel(String contentLevel) {
		this.contentLevel = contentLevel;
	}

	public String getArticleFilePath() {
		return articleFilePath;
	}

	public void setArticleFilePath(String articleFilePath) {
		this.articleFilePath = articleFilePath;
	}

	public File getInfoFile() {
		return infoFile;
	}

	public void setInfoFile(File infoFile) {
		this.infoFile = infoFile;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

}
