<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者ページ</title>
</head>
<body>
	管理者メニュー
	<br>
	<c:choose>

		<p>
			<a href="/login/Logout"><button type="button">勤怠情報管理</button></a>
		</p>
		<a href="user.jsp"><button type="button">休暇申請管理</button></a>

	</c:choose>
</body>
</html>