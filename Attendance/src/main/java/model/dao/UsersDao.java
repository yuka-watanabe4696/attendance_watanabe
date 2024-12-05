package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.MemberBean;

public class UsersDao {

	// ログインアカウントを探す
	public MemberBean findAccount(MemberBean mb) throws ClassNotFoundException, SQLException {

		// 戻り値の用意
		MemberBean returnmb = null;

		// データベースへ接続
		try (Connection con = ConnectionManager.getConnection()) {

			String sql = "SELECT user_id, user_password, name FROM user WHERE user_id = ? AND user_password = ?";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, mb.getUserId()); // getuserId() → getUserId() に修正
			ps.setString(2, mb.getPassword());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				returnmb = new MemberBean();
				// 見つかったアカウント情報を戻り値にセット
				returnmb.setUserId(rs.getInt("user_id"));
				returnmb.setPassword(rs.getString("user_password"));
				returnmb.setName(rs.getString("name")); // nameをセット

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return returnmb;
	}
}