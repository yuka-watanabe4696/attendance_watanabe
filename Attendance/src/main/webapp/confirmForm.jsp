<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.MemberBean"%>
<%
MemberBean memberBean = (MemberBean) session.getAttribute("memberBean");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>勤怠管理</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="flex-item wrapper">
		<h1>勤怠管理メニュー</h1>
	</div>
	<input type="submit" value="勤怠情報一覧">
	</div>
	<div class="row">
		<input type="submit" value="打刻">
	</div>
	<div class="row">
		<input type="submit" value="休暇申請">
	</div>
	<div class="row">
		</main>
</body>
</html>