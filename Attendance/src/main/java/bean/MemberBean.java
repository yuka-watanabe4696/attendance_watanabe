package bean;

import java.io.Serializable;

public class MemberBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private int userid;
	private String name; // nameフィールドを追加
	private String password;

	// デフォルトコンストラクタ
	public MemberBean() {
	}

	// 引数付きコンストラクタ
	public MemberBean(int userid, String password, String name) {
		this.userid = userid;
		this.password = password;
		this.name = name; // nameも設定
	}

	// useridのgetterとsetter
	public int getUserId() {
		return userid;
	}

	public void setUserId(int userid) {
		this.userid = userid;
	}

	// passwordのgetterとsetter
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// nameのgetterとsetter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}