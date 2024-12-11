package bean;

import java.time.LocalDate;
import java.time.LocalTime;

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
			LocalTime timeToClockOut,
			double totalHours, String status, int userId) {
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

	@Override
	public String toString() {
		return String.format(
				"AttendanceBean [id=%d, date=%s, attendanceTime=%s, breakTime=%s, timeToClockOut=%s, totalHours=%.2f, status=%s, userId=%d]",
				id, date, attendanceTime, breakTime, timeToClockOut, totalHours, status, userId);
	}
}
