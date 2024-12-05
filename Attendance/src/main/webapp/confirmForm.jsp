<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="bean.MemberBean"%>
<%@ page import="javax.servlet.http.HttpSession"%>

<%
// レスポンスのエンコーディングを設定
response.setCharacterEncoding("UTF-8");
response.setContentType("text/html; charset=UTF-8");

// セッションからnameを取得
String name = (String) session.getAttribute("name");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>勤怠管理一覧</title>
<link rel="stylesheet" type="text/css" href="styles/confirmForm.css">
</head>
<body>

	<!-- サイドバー -->
	<div class="sidebar">
		<h2>メニュー</h2>
		<a href="attendance.jsp">勤怠情報一覧</a> <a href="#">打刻</a> <a href="#">休暇申請</a>

		<!-- サイドバーに名前を表示 -->
		<div class="user-name">
			<%=(name != null && !name.isEmpty()) ? name : "ゲスト"%>
		</div>

		<!-- ログアウトリンクをサイドバーに追加 -->
		<a href="logout.jsp" class="logout-link">ログアウト</a>
	</div>

	<!-- メインコンテンツ -->
	<div class="main-content">
		<div class="wrapper">
			<div class="button-container">
				<!-- 勤怠情報一覧ボタン -->
				<form action="AttendanceServlet" method="get" accept-charset="UTF-8">
					<input type="submit" value="勤怠情報一覧">
				</form>


				<form action="clockIn.jsp" method="get">
					<input type="submit" value="打刻">
				</form>

				<form action="vacationRequest.jsp" method="get">
					<input type="submit" value="休暇申請">
				</form>
			</div>
		</div>
	</div>

</body>
</html>
