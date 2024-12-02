package bean;

import java.io.Serializable;

public class MemberBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userid;
	private String password;

	public MemberBean() {
	}

	public MemberBean(String userid, String password) {
		this.userid = userid;
		this.password = password;
	}

	public String getuserId() {
		return userid;
	}

	public void setId(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPasword(String password) {
		this.password = password;
	}

}
