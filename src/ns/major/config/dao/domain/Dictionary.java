package ns.major.config.dao.domain;

public class Dictionary {
	private int DictId;
	private String Pid;
	private String FirstName;
	private String SecondName;
	public int getDictId() {
		return DictId;
	}
	public void setDictId(int dictId) {
		DictId = dictId;
	}
	public String getPid() {
		return Pid;
	}
	public void setPid(String pid) {
		Pid = pid;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getSecondName() {
		return SecondName;
	}
	public void setSecondName(String secondName) {
		SecondName = secondName;
	}
	
}
