<%@ page import="java.util.List"%>
<%@ page import="bean.AttendanceBean"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠情報</title>
<link rel="stylesheet" type="text/css" href="styles/attendance.css">
</head>
<body>
	<div class="sidebar">
		<jsp:include page="sidebar.jsp" />
	</div>

	<div class="main-content">
		<table>
			<thead>
				<tr>
					<th>日付</th>
					<th>出勤時間</th>
					<th>休憩時間</th>
					<th>退勤時間</th>
					<th>累計時間</th>
					<th>ステータス</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<AttendanceBean> attendanceList = (List<AttendanceBean>) request.getAttribute("attendanceList");
				double totalWorkHours = 0.0; // 累計時間を計算するための変数

				if (attendanceList != null && !attendanceList.isEmpty()) {
					for (AttendanceBean attendance : attendanceList) {
						String status = attendance.getStatus();
						if (!"休み".equals(status)) {
					totalWorkHours += attendance.getWorkHours();
						}
				%>
				<tr>
					<td><%=attendance.getFormattedDate()%></td>
					<td><%=("休み".equals(status) ? "" : attendance.getFormattedStartTime())%></td>
					<td><%=("休み".equals(status) ? "" : attendance.getFormattedBreakTime())%></td>
					<td><%=("休み".equals(status) ? "" : attendance.getFormattedEndTime())%></td>
					<td><%=("休み".equals(status) ? "" : String.format("%.2f", attendance.getWorkHours()))%></td>
					<td><%=attendance.getStatusString()%></td>
				</tr>
				<%
				} // forの終了
				} // ifの終了
				%>

				<%
				if (attendanceList == null || attendanceList.isEmpty()) {
				%>
				<tr>
					<td colspan="6">勤怠情報がありません。</td>
				</tr>
				<%
				}
				%>

			</tbody>
		</table>

		<div>
			<strong>合計時間:</strong>
			<%
			// 合計時間を計算して表示
			int totalWholeHours = (int) totalWorkHours;
			int totalMinutes = (int) ((totalWorkHours - totalWholeHours) * 60);
			// 時間と分を「時間:分」の形式で表示
			// 例えば「8:00」のような形式で表示
			String formattedTime = String.format("%d:%02d", totalWholeHours, totalMinutes);
			%>
			<%=formattedTime%>
		</div>

	</div>
</body>
</html>
