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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	// doPostメソッド
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String userIdParam = request.getParameter("userid");
		String password = request.getParameter("password");

		// エラーメッセージの初期化
		boolean hasError = false;

		// ユーザーIDの検証
		Integer userid = null;
		if (userIdParam == null || userIdParam.isEmpty()) {
			request.setAttribute("userIdError", "社員IDを入力してください。");
			hasError = true;
		} else {
			try {
				userid = Integer.parseInt(userIdParam);
			} catch (NumberFormatException e) {
				request.setAttribute("userIdError", "社員IDは数字でなければなりません。");
				hasError = true;
			}
		}

		// パスワードの検証
		if (password == null || password.isEmpty()) {
			request.setAttribute("passwordError", "パスワードを入力してください。");
			hasError = true;
		}

		// エラーがあった場合は、トップ画面に戻す
		if (hasError) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("top.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// usersdaoインスタンス化
		UsersDao usersdao = new UsersDao();
		MemberBean mb = new MemberBean();

		mb.setUserId(userid); // setId -> setUserId
		mb.setPassword(password); // setPassword

		// usersdaoのfindAccountメソッドの呼び出し
		try {
			MemberBean returnmb = usersdao.findAccount(mb);

			if (returnmb != null) { // ログイン成功
				// セッションスコープに社員コードと名前を保存
				HttpSession session = request.getSession();
				session.setAttribute("userid", userid);
				session.setAttribute("name", returnmb.getName()); // nameをセッションに保存

				// フォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("confirmForm.jsp");
				dispatcher.forward(request, response);
			} else { // ログイン失敗
				request.setAttribute("error", "※社員IDまたはパスワードに誤りがあります。");
				// フォワードでログイン画面に戻す
				RequestDispatcher dispatcher = request.getRequestDispatcher("top.jsp");
				dispatcher.forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "データベースエラーが発生しました。再度お試しください。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("top.jsp");
			dispatcher.forward(request, response);
		}
	}

	// doGetメソッド (GETリクエスト用)
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("top.jsp").forward(request, response);
	}
}
