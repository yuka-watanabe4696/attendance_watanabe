package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import bean.AttendanceBean;

public class AttendanceDao {

	private static final Logger logger = Logger.getLogger(AttendanceDao.class.getName());

	public List<AttendanceBean> getAttendanceListForMonth(int userId, int year, int month)
			throws SQLException, ClassNotFoundException {
		List<AttendanceBean> attendanceList = new ArrayList<>();
		String query = "SELECT id, date, attendance_time, break_time, time_to_clock_out, total_hours, status, user_id "
				+ "FROM attendance WHERE user_id = ? AND YEAR(date) = ? AND MONTH(date) = ? ORDER BY date ASC";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(query)) {

			stmt.setInt(1, userId);
			stmt.setInt(2, year);
			stmt.setInt(3, month);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					AttendanceBean bean = new AttendanceBean();
					bean.setId(rs.getInt("id"));
					bean.setDate(rs.getDate("date").toLocalDate());

					// 出勤時間を変換して設定
					java.sql.Time attendanceTime = rs.getTime("attendance_time");
					bean.setAttendanceTime(attendanceTime != null ? attendanceTime.toLocalTime() : null);

					// 休憩時間を変換して設定
					java.sql.Time breakTime = rs.getTime("break_time");
					bean.setBreakTime(breakTime != null ? breakTime.toLocalTime() : null);

					// 退勤時間を変換して設定
					java.sql.Time timeToClockOut = rs.getTime("time_to_clock_out");
					bean.setTimeToClockOut(timeToClockOut != null ? timeToClockOut.toLocalTime() : null);

					// 総労働時間を適切に変換する処理
					java.sql.Time totalHoursTime = rs.getTime("total_hours");
					double totalHoursInHours = 0.0;
					if (totalHoursTime != null) {
						totalHoursInHours = totalHoursTime.getHours() +
								totalHoursTime.getMinutes() / 60.0 +
								totalHoursTime.getSeconds() / 3600.0;
					}
					bean.setTotalHours(totalHoursInHours);

					// ステータスとユーザーIDを設定
					bean.setStatus(rs.getString("status"));
					bean.setUserId(rs.getInt("user_id"));

					// リストに追加
					attendanceList.add(bean);
				}
			}
		}

		return attendanceList;
	}
}