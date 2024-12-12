<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="styles/sidebar.css">

<%
String name = (session != null && session.getAttribute("name") != null)
		? (String) session.getAttribute("name")
		: "ゲスト";
%>

<div class="sidebar">
	<h2>メニュー</h2>
	<a href="attendance.jsp" aria-label="勤怠情報一覧">勤怠情報一覧</a> <a
		href="clockIn.jsp" aria-label="打刻">打刻</a> <a
		href="vacationRequest.jsp" aria-label="休暇申請">休暇申請</a>

	<!-- サイドバーに名前を表示 -->
	<div class="user-name">
		<%=name%>
	</div>

	<!-- ログアウトリンク -->
	<a href="logout.jsp" class="logout-link" aria-label="ログアウト">ログアウト</a>
</div>
