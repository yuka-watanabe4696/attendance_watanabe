package CheckForm;

public class CheckForm {
	private String errorMessage;

	public CheckForm() {
		errorMessage = ""; // エラーメッセージ文頭のnullを消すため
	}

	public String checkAll(String userid, String password) {
		checkUserid(userid);
		checkPassword(password);
		return errorMessage;
	}

	// 文字列を受け取って空かどうか判定するメソッド
	private void checkNull(String target, String targetUserid) {
		if (target == null || target.isEmpty()) {
			errorMessage += targetUserid + "を入力してください。<br>";
		}
	}

	private void checkUserid(String userid) {
		checkNull(userid, "社員ID");
		// 以下チェック条件省略
	}

	private void checkPassword(String password) {
		checkNull(password, "パスワード");
		// 以下チェック条件省略
	}

}