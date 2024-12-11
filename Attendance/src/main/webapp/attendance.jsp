<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="bean.AttendanceBean"%>
<%@ page import="model.dao.AttendanceDao"%>
<%@ page import="java.util.logging.Logger"%>
<%@ page import="java.util.logging.Level"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.util.Locale"%>

<%
Logger logger = Logger.getLogger("AttendanceJSP");
List<AttendanceBean> attendanceList = null;
String message = null;

try {
	Integer userId = (Integer) session.getAttribute("userid");
	String name = (String) session.getAttribute("name");

	if (userId == null) {
		logger.warning("セッションが無効またはユーザーIDが見つかりません。ログインページにリダイレクトします。");
		response.sendRedirect("top.jsp");
		return;
	}

	AttendanceDao dao = new AttendanceDao();
	attendanceList = dao.getAttendanceList(userId);

	if (attendanceList.isEmpty()) {
		message = "勤怠情報が見つかりませんでした。";
		logger.info("ユーザーID " + userId + " の勤怠情報が見つかりませんでした。");
	} else {
		logger.info("ユーザーID " + userId + " の勤怠情報を正常に取得しました。");
	}

} catch (Exception e) {
	logger.log(Level.SEVERE, "エラーが発生しました: ", e);
	response.setContentType("text/html; charset=UTF-8");
	response.getWriter().println("<h3>エラーが発生しました。管理者に連絡してください。</h3>");
	return;
}
%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>勤怠情報一覧</title>
<link rel="stylesheet" type="text/css" href="styles/attendance.css">

</head>
<body>
	<!-- サイドバーの開始 -->
	<div class="sidebar">
		<h2>メニュー</h2>
		<a href="attendance.jsp">勤怠情報一覧</a> <a href="clockIn.jsp">打刻</a> <a
			href="vacationRequest.jsp">休暇申請</a>



		<!-- ログアウトリンク -->
		<a href="logout.jsp" class="logout-link">ログアウト</a>
	</div>
	<!-- サイドバーの終了 -->

	<!-- メインコンテンツの開始 -->
	<div class="main-content">
		<h1>勤怠情報一覧</h1>

		<%
		if (message != null) {
		%>
		<p class="message"><%=message%></p>
		<%
		} else if (attendanceList != null && !attendanceList.isEmpty()) {
		double totalHoursSum = 0.0; // 合計時間の計算用
		%>

		<table>
			<thead>
				<tr>
					<th>日付</th>
					<th>出勤時間</th>
					<th>休憩時間</th>
					<th>退勤時間</th>
					<th>労働時間</th>
					<th>ステータス</th>
				</tr>
			</thead>
			<tbody>
				<%
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M月d日（E）", Locale.JAPANESE);

				for (AttendanceBean attendance : attendanceList) {
					LocalDate date = attendance.getDate(); // LocalDate型を取得
					String formattedDate = date != null ? date.format(formatter) : "N/A";
					String status = attendance.getStatus();

					// 合計時間を計算
					Double totalHours = attendance.getTotalHours();
					if (totalHours != null) {
						totalHoursSum += totalHours; // 累計に加算
					}
				%>
				<tr>
					<td><%=formattedDate%></td>
					<td><%=attendance.getAttendanceTime() != null ? attendance.getAttendanceTime() : ""%></td>
					<td><%=attendance.getBreakTime() != null ? attendance.getBreakTime() : ""%></td>
					<td><%=attendance.getTimeToClockOut() != null ? attendance.getTimeToClockOut() : ""%></td>
					<td>
						<%
						// ステータスが "休み" の場合は労働時間を非表示
						if (!"休み".equals(status)) {
							if (totalHours != null) {
								int wholeHours = totalHours.intValue(); // 時間部分
								int minutes = (int) ((totalHours - wholeHours) * 60); // 分部分
								out.print(String.format("%d:%02d", wholeHours, minutes)); // 労働時間を表示
							} else {
								out.print("N/A");
							}
						}
						%>
					</td>
					<td>
						<%
						if ("休み".equals(status)) {
							out.print(""); // 何も表示しない
						} else if ("Present".equals(status)) {
							out.print("出勤");
						} else {
							out.print(status != null ? status : "");
						}
						%>
					</td>
				</tr>
				<%
				}
				%>

				<!-- 合計時間を最下部に表示 -->
				<tr>
					<td colspan="6" style="text-align: center;">
						<%
						int totalWholeHours = (int) totalHoursSum;
						int totalMinutes = (int) ((totalHoursSum - totalWholeHours) * 60);
						%> <strong>合計時間:</strong> <%=totalWholeHours%>時間<%=totalMinutes%>分
					</td>
				</tr>
			</tbody>
		</table>

		<%
		}
		%>
	</div>
	<!-- メインコンテンツの終了 -->
</body>
</html>
