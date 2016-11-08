package ns.major.secrecy.query;

import ns.common.BaseQuery;

public class InfoPublishQuery extends BaseQuery {

	private int topicType;// 主题类别
	private int departId;// 部门ID
	private String topic;// 主题
	private String publisher;// 发布人
	private int pid; // pid
	private String typeName;// 主题类别名称

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getTopicType() {
		return topicType;
	}

	public void setTopicType(int topicType) {
		this.topicType = topicType;
	}

	public int getDepartId() {
		return departId;
	}

	public void setDepartId(int departId) {
		this.departId = departId;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

}
