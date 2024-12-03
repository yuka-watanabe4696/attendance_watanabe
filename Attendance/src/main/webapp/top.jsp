<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠管理</title>
<style>
    /* 全体を中央に配置する */
    body {
        display: flex;
        justify-content: center;  /* 横方向の中央揃え */
        align-items: center;  /* 縦方向の中央揃え */
        height: 100vh;  /* ビューポートの高さを100%に */
        margin: 0;  /* デフォルトの余白を削除 */
        font-family: Arial, sans-serif;
    }

    /* フォーム全体のスタイル */
    .form-container {
        max-width: 400px;
        width: 100%;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 10px;
        box-sizing: border-box;
        position: relative; /* 外枠に近い位置で調整するために相対位置に設定 */
    }

    .form-group {
        margin-bottom: 10px;
    }

    label {
        font-weight: bold;
    }

    input[type="text"], input[type="password"] {
        width: 100%;
        padding: 8px;
        margin-top: 5px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

    /* エラーメッセージのスタイル */
    .error-message {
        color: red;
        font-size: 12px; /* 小さめのフォント */
        margin-top: 5px;
    }

    /* 社員IDまたはパスワードに誤りがあるエラーメッセージ */
    .general-error {
        color: red;
        font-size: 14px; /* 少し目立つフォントサイズ */
        margin-bottom: 10px;
        width: 100%;  /* 幅を100%に設定 */
        box-sizing: border-box;  /* 幅にパディングを含める */
        padding: 10px; /* 少しパディングを追加 */
        margin-bottom: 20px; /* 少し下に余白を加える */
    }

    /* ログインボタンの右寄せ */
    .submit-container {
        text-align: right;  /* 右寄せ */
        margin-top: 10px;  /* 上部余白 */
    }

    input[type="submit"] {
        background-color: #4CAF50; /* 緑色 */
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        width: auto;  /* ボタンサイズを自動調整 */
    }

    input[type="submit"]:hover {
        background-color: #45a049;
    }
</style>
</head>
<body>

    <!-- フォームを外枠で囲む -->
    <div class="form-container">
        <!-- 社員IDまたはパスワードに誤りがあるエラーメッセージ（フォームの一番上に表示） -->
        <% 
            String error = (String) request.getAttribute("error");
            if (error != null) { 
        %>
            <div class="general-error"><%= error %></div>
        <% 
            } 
        %>

        <form action="login" method="post">
<!-- 社員ID -->
<div class="form-group">
    <label for="userid">社員ID</label>
      <% 
        String userIdError = (String) request.getAttribute("userIdError");
        if (userIdError != null) { 
    %>
        <!-- 社員IDのエラーメッセージを右横に小さく表示 -->
        <span class="error-message"><%= userIdError %></span>
    <% 
        } 
    %>
    <input type="text" name="userid" id="userid">
  
</div>

<!-- パスワード -->
<div class="form-group">
    <label for="password">パスワード</label>
     <% 
        String passwordError = (String) request.getAttribute("passwordError");
        if (passwordError != null) { 
    %>
        <!-- パスワードのエラーメッセージを右横に小さく表示 -->
        <span class="error-message"><%= passwordError %></span>
    <% 
        } 
    %>
    <input type="password" name="password" id="password">
   
</div>
            <!-- ログインボタンの右寄せ -->
            <div class="submit-container">
                <input type="submit" value="ログイン">
            </div>
        </form>
    </div>

</body>
</html>