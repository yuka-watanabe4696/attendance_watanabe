package com.example.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.AttendanceBean;
import model.dao.AttendanceDao;

@WebServlet("/AttendanceServlet")
public class AttendanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AttendanceServlet.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// セッションを取得
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userid") == null) {
			logger.warning("セッションが無効、またはユーザーIDが見つかりません。ログインページにリダイレクトします。");
			response.sendRedirect("top.jsp");
			return;
		}

		// セッションからユーザーIDを取得
		Object userIdObj = session.getAttribute("userid");
		if (!(userIdObj instanceof Integer)) {
			logger.warning("セッションに保存されたユーザーIDが無効です。ログインページにリダイレクトします。");
			response.sendRedirect("top.jsp");
			return;
		}
		Integer userId = (Integer) userIdObj;

		try {

			// ユーザーIDに基づいて勤怠情報を取得
			AttendanceDao dao = new AttendanceDao();
			List<AttendanceBean> attendanceList = dao.getAttendanceList(userId);

			if (attendanceList.isEmpty()) {

				logger.info("ユーザーID " + userId + " の勤怠情報が見つかりませんでした。");
				request.setAttribute("message", "勤怠情報が見つかりませんでした。");
			} else {

				logger.info("ユーザーID " + userId + " の勤怠情報を正常に取得しました。");
				request.setAttribute("attendanceList", attendanceList);

			}

			// 勤怠情報を表示するJSPにフォワード
			request.getRequestDispatcher("attendance.jsp").forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace(); // スタックトレースを表示
			logger.log(Level.SEVERE, "データベースエラーが発生しました: ", e);
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().println("データベースエラーが発生しました。管理者に連絡してください。");
		}

		catch (ClassNotFoundException e) {
			logger.log(Level.SEVERE, "システムエラーが発生しました: ", e);
			handleError(response, "システムエラーが発生しました。管理者に連絡してください。");
		}

	}

	/**
	 * エラーレスポンスを送信する共通メソッド
	 * 
	 * @param response HttpServletResponse
	 * @param message  エラーメッセージ
	 * @throws IOException
	 */
	private void handleError(HttpServletResponse response, String message) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.getWriter().println("<html><body><h3>" + message + "</h3></body></html>");
	}
}
