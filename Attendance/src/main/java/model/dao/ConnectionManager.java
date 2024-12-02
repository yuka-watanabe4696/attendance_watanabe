package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
* データベース接続を管理するクラス
*/
public class ConnectionManager {
	/**
	* データベースのURL
	*/
	private final static String URL = "jdbc:mysql://localhost:3306/attendance_management";
	/**
	* データベースのユーザー名
	*/
	private final static String USER = "tweetU";
	/**
	* データベースのパスワード
	*/
	private final static String PASSWORD = "pass";

	/**
	* データベースへの接続を取得して返します。
	*
	* @return データベース接続
	* @throws SQLException
	* @throws ClassNotFoundException
	*/
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		// JDBCドライバのロード
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(URL, USER, PASSWORD);

	}
}
