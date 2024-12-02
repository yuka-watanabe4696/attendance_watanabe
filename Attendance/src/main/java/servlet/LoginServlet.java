package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("top.jsp").forward(request, response);
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// ユーザーから送信されたユーザーIDとパスワードを取得する。
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");

		//usersdaoインスタンス化
		UsersDao usersdao = new UsersDao();
		MemberBean mb = new MemberBean();

		mb.setId(userid);
		mb.setPasword(password);

		//usersdaoのfindAccountメソッドの呼び出し
		try {
			MemberBean returnmb = usersdao.findAccount(mb);

			//一致すればメニュー画面へフォワード
			RequestDispatcher dispatch = request.getRequestDispatcher("confirmForm.jsp");
			dispatch.forward(request, response);

				//リクエストスコープにエラーメッセージをセット
				
				request.setAttribute("error", "useridを入力してください。");
				RequestDispatcher rd = request.getRequestDispatcher("error");
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("top.jsp");
				dispatcher.forward(request, response);

			}

			System.out.println(returnmb.getuserId());
		}catch(ClassNotFoundException|

	SQLException e)
	{
		// TODO 自動生成された catch ブロック
		e.printStackTrace();

		// フォワードでログイン画面に戻す
		RequestDispatcher dispatcher = request.getRequestDispatcher("top.jsp");
		dispatcher.forward(request, response);

	}
}}
