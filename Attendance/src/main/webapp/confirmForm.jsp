<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<style>
    /* 全体を真ん中に配置し、背景色を白に設定 */
    body {
        display: flex;
        justify-content: center;  /* 横中央配置 */
        align-items: center;  /* 縦中央配置 */
        height: 100vh;  /* ビュー全体を占める */
        margin: 0;  /* 余白を削除 */
        background-color: #FFFFFF;  /* 背景色を白に設定 */
        font-size: 20px;  /* 全体的に文字を大きく */
        font-family: Arial, sans-serif;  /* フォントを指定 */
    }

    /* 外枠をつけるためのラッパー */
    .wrapper {
        border: 5px solid black;  /* 外枠の色を黒に設定 */
        border-radius: 15px;  /* 角を丸く */
        padding: 50px;  /* 枠内の余白を大きく設定 */
        width: 350px;  /* 幅を指定 */
        text-align: center;  /* 中央揃え */
    }

    /* ボタンを縦に並べるコンテナ */
    .button-container {
        display: flex;
        flex-direction: column;  /* 横並びから縦並びに変更 */
        align-items: center;  /* ボタンを中央に配置 */
        justify-content: center; /* ボタン間の余白を均等に配置 */
    }

    /* 各ボタンのスタイル */
    .button-container input[type="submit"] {
        background-color: #E6F4FF;  /* 背景色を薄い青に */
        color: #007BFF;  /* 文字色は青 */
        padding: 15px 0;  /* ボタンの内側の余白を調整（上下のみ余白を追加） */
        font-size: 18px;  /* ボタン内の文字を大きく */
        font-weight: bold;  /* 文字を太く */
        border: 2px solid #007BFF;  /* 青い枠線を追加 */
        border-radius: 5px;  /* 角を丸く */
        cursor: pointer;
        width: 100%;  /* ボタンの幅を親要素に合わせて100%に設定 */
        margin-bottom: 15px;  /* ボタン間の余白 */
        text-align: center;  /* テキストを中央に配置 */
    }

    /* ボタンにホバー時のスタイル */
    .button-container input[type="submit"]:hover {
        background-color: #CCE5FF;  /* ホバー時に背景色を少し濃く */
    }
</style>
</head>
<body>
    <div class="wrapper">
        <div class="button-container">
            <input type="submit" value="勤怠情報一覧">
            <input type="submit" value="打刻">
            <input type="submit" value="休暇申請">
        </div>
    </div>
</body>
</html>