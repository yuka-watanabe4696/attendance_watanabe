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

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userid") == null) {
			response.sendRedirect("top.jsp");
			return;
		}

		Object userIdObj = session.getAttribute("userid");
		if (!(userIdObj instanceof Integer)) {
			response.sendRedirect("top.jsp");
			return;
		}
		Integer userId = (Integer) userIdObj;

		try {
			AttendanceDao dao = new AttendanceDao();

			// 初期表示を2024年9月に設定
			int year = 2024;
			int month = 9;

			// attendanceListを取得
			List<AttendanceBean> attendanceList = dao.getAttendanceListForMonth(userId, year, month);

			// 勤怠情報が取得できた場合に合計勤務時間を計算
			double totalWorkHours = 0;
			if (attendanceList != null && !attendanceList.isEmpty()) {
				for (AttendanceBean attendance : attendanceList) {
					totalWorkHours += attendance.getWorkHours();
				}
			}

			// totalWorkHoursをリクエスト属性として設定
			request.setAttribute("totalWorkHours", totalWorkHours);

			// そのままJSPに渡す
			request.setAttribute("attendanceList", attendanceList);
			request.setAttribute("currentYear", year);
			request.setAttribute("currentMonth", month);

			request.getRequestDispatcher("attendance.jsp").forward(request, response);

		} catch (SQLException | ClassNotFoundException e) {
			handleError(response, "データベースエラーが発生しました。管理者に連絡してください。", e);
		}
	}

	/**
	 * エラーレスポンスを送信する共通メソッド
	 * 
	 * @param response HttpServletResponse
	 * @param message  エラーメッセージ
	 * @param e        例外情報
	 * @throws IOException
	 */
	private void handleError(HttpServletResponse response, String message, Exception e) throws IOException {
		logger.log(Level.SEVERE, message, e);
		response.setContentType("text/html; charset=UTF-8");
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.getWriter().println("<html><body><h3>" + message + "</h3></body></html>");
	}
}
