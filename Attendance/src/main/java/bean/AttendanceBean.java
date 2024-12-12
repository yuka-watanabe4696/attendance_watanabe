package bean;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AttendanceBean {

	private int id; // 出席ID
	private LocalDate date; // 出席日
	private LocalTime attendanceTime; // 出席時間
	private LocalTime breakTime; // 休憩時間
	private LocalTime timeToClockOut; // 退勤時間
	private double totalHours; // 総労働時間
	private String status; // 状態
	private int userId; // ユーザーID

	// デフォルトコンストラクタ
	public AttendanceBean() {
	}

	// コンストラクタ
	public AttendanceBean(int id, LocalDate date, LocalTime attendanceTime, LocalTime breakTime,
			LocalTime timeToClockOut, double totalHours, String status, int userId) {
		this.id = id;
		this.date = date;
		this.attendanceTime = attendanceTime;
		this.breakTime = breakTime;
		this.timeToClockOut = timeToClockOut;
		this.totalHours = totalHours;
		this.status = status;
		this.userId = userId;
	}

	// GetterとSetter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getAttendanceTime() {
		return attendanceTime;
	}

	public void setAttendanceTime(LocalTime attendanceTime) {
		this.attendanceTime = attendanceTime;
	}

	public LocalTime getBreakTime() {
		return breakTime;
	}

	public void setBreakTime(LocalTime breakTime) {
		this.breakTime = breakTime;
	}

	public LocalTime getTimeToClockOut() {
		return timeToClockOut;
	}

	public void setTimeToClockOut(LocalTime timeToClockOut) {
		this.timeToClockOut = timeToClockOut;
	}

	public double getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(double totalHours) {
		this.totalHours = totalHours;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	// 日付のフォーマットメソッド
	public String getFormattedDate() {
		if (date != null) {
			return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}
		return "";
	}

	// 出勤時間のフォーマットメソッド
	public String getFormattedStartTime() {
		if (attendanceTime != null) {
			return attendanceTime.format(DateTimeFormatter.ofPattern("HH:mm"));
		}
		return "";
	}

	// 休憩時間のフォーマットメソッド
	public String getFormattedBreakTime() {
		if (breakTime != null) {
			return breakTime.format(DateTimeFormatter.ofPattern("HH:mm"));
		}
		return "";
	}

	// 退勤時間のフォーマットメソッド
	public String getFormattedEndTime() {
		if (timeToClockOut != null) {
			return timeToClockOut.format(DateTimeFormatter.ofPattern("HH:mm"));
		}
		return "";
	}

	// 勤務時間を計算して返すメソッド
	public double getWorkHours() {
		if (attendanceTime != null && timeToClockOut != null && breakTime != null) {
			Duration workDuration = Duration.between(attendanceTime, timeToClockOut); // 退勤時間 - 出勤時間
			Duration breakDuration = Duration.between(LocalTime.MIDNIGHT, breakTime); // 休憩時間
			long workMinutes = workDuration.toMinutes() - breakDuration.toMinutes(); // 勤務時間から休憩時間を引く
			return workMinutes / 60.0; // 時間単位で返す
		}
		return 0.0; // 出勤時間や退勤時間が設定されていない場合は0
	}

	// ステータスを返すメソッド
	public String getStatusString() {
		if ("Present".equals(status)) {
			return "出勤";
		} else if ("Leave".equals(status)) {
			return "休み";
		}
		return status;
	}

	@Override
	public String toString() {
		return String.format(
				"AttendanceBean [id=%d, date=%s, attendanceTime=%s, breakTime=%s, timeToClockOut=%s, totalHours=%.2f, status=%s, userId=%d]",
				id, date, attendanceTime, breakTime, timeToClockOut, totalHours, status, userId);
	}
}
