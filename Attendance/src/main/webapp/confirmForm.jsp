<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.MemberBean"%>
<%@ page import="javax.servlet.http.HttpSession"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>勤怠管理一覧</title>
<link rel="stylesheet" type="text/css" href="styles/confirmForm.css">
</head>
<body>

	<!-- サイドバーのインクルード -->
	<jsp:include page="sidebar.jsp" />

	<!-- メインコンテンツ -->
	<div class="main-content">
		<div class="wrapper">
			<div class="button-container">
				<!-- 勤怠情報一覧ボタン -->
				<form action="AttendanceServlet" method="get">
					<button type="submit">勤怠情報一覧</button>
				</form>

				<form action="clockIn.jsp" method="get">
					<button type="submit">打刻</button>
				</form>

				<form action="vacationRequest.jsp" method="get">
					<button type="submit">休暇申請</button>
				</form>
			</div>
		</div>
	</div>

</body>
</html>

