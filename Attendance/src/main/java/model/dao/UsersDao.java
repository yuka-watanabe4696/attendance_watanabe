package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import bean.MemberBean;

public class UsersDao {

	private static final Logger logger = Logger.getLogger(UsersDao.class.getName());

	// ログインアカウントを探す
	public MemberBean findAccount(MemberBean mb) throws ClassNotFoundException, SQLException {

		MemberBean returnmb = null; // 戻り値用のオブジェクト

		String sql = "SELECT user_id, user_password, name FROM `user` WHERE user_id = ? AND user_password = ?";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, mb.getUserId());
			ps.setString(2, mb.getPassword());

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					returnmb = new MemberBean();
					returnmb.setUserId(rs.getInt("user_id"));
					returnmb.setPassword(rs.getString("user_password"));
					returnmb.setName(rs.getString("name"));
				}
			}
		} catch (SQLException e) {
			logger.severe("SQLエラー: " + e.getMessage());
			throw e;
		}

		return returnmb;
	}
}
