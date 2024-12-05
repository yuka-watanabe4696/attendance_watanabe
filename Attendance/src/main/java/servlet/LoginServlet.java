package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.MemberBean;
import model.dao.UsersDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("top.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// ユーザーから送信されたユーザーIDとパスワードを取得する。
		Integer userid = Integer.parseInt(request.getParameter("userid"));
		String password = request.getParameter("password");

		// usersdaoインスタンス化
		UsersDao usersdao = new UsersDao();
		MemberBean mb = new MemberBean();

		mb.setUserId(userid); // 修正: setId -> setUserId
		mb.setPassword(password); // 修正: setPasword -> setPassword

		// usersdaoのfindAccountメソッドの呼び出し
		try {
			MemberBean returnmb = usersdao.findAccount(mb);

			// ログイン処理の成功、失敗によって処理を分岐
			if (returnmb != null) { // ログイン成功
				// セッションスコープに社員コードと名前を保存
				HttpSession session = request.getSession();
				session.setAttribute("userid", userid);
				session.setAttribute("name", returnmb.getName()); // 追加: nameをセッションに保存

				// フォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("confirmForm.jsp");
				dispatcher.forward(request, response);
			} else { // ログイン失敗
				// 社員IDとパスワードのエラーチェック
//				if (userid == null ) {
//					request.setAttribute("userIdError", "社員IDを入力してください。");
//				}
				if (password == null || password.isEmpty()) {
					request.setAttribute("passwordError", "パスワードを入力してください。");
				}

				// 両方が誤っている場合やエラーメッセージを設定する場合
				if ((userid != null ) && (password != null && !password.isEmpty())
						&& returnmb == null) {
					request.setAttribute("error", "※社員IDまたはパスワードに誤りがあります。");
				}

				// フォワードでログイン画面に戻す
				RequestDispatcher dispatcher = request.getRequestDispatcher("top.jsp");
				dispatcher.forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}