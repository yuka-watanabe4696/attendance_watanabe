<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠管理</title>
</head>
<body>
<!-- 	リクエストスコープにセットされたエラーメッセージの取得 -->
<!-- 取得されたエラーメッセージの値があればエラーメッセージの表示 -->
	<h1>勤怠管理ログイン</h1>
	<form action="login" method="post">
		社員ID <input type="text" name="userid"> パスワード <input
			type="password" name="password"> <input type="submit"
			value="ログイン">
	</form>
</body>