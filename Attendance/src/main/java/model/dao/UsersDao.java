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
		MemberBean returnmb = new MemberBean();

		// データベースへ接続
		try (Connection con = ConnectionManager.getConnection()) {

			String sql = "SELECT user_id,user_password FROM user WHERE user_id = ? AND user_password = ?";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, mb.getuserId());
			ps.setString(2, mb.getPassword());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				// 見つかったアカウント情報を戻り値にセット
				returnmb.setId(rs.getString("user_id"));
				returnmb.setPasword(rs.getString("user_password"));

			}

		}

		return returnmb;
	}

}