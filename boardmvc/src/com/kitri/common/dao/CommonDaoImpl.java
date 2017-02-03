package com.kitri.common.dao;

import java.sql.*;
import java.util.Map;

import com.kitri.util.db.DBClose;
import com.kitri.util.db.DBConnection;

public class CommonDaoImpl implements CommonDao {

	private static CommonDao commonDao;

	static {
		commonDao = new CommonDaoImpl();
	}

	private CommonDaoImpl() {
	}

	public static CommonDao getCommonDao() {
		return commonDao;
	}

	@Override
	public int getNextSeq() {
		int seq = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "select board_seq.nextval from dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			seq = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return seq;
	}

	@Override
	public void updateHit(int seq) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "update board \n";
			sql += "set hit = hit + 1 \n";
			sql += "where seq = ? \n";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
	}

	@Override
	public int getNewArticleCount(int bcode) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		try {
			conn = DBConnection.getConnection();
			String sql = "";
			sql += "select count(*) \n";
			sql += "from board \n";
			sql += "where bcode = ? \n";
			sql += "and to_char(logtime, 'yyyymmdd') = to_char(sysdate, 'yyyymmdd')";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bcode);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int getTotalArticleCount(Map<String, String> map) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		try {
			conn = DBConnection.getConnection();
			String sql = "";
			sql += "select count(*) \n";
			sql += "from board \n";
			sql += "where bcode = ? \n";
			String key = map.get("key");
			String word = map.get("word");
			if(!key.isEmpty() && !word.isEmpty()) {
				if(key.equals("subject"))
					sql += "and subject like '%'||?||'%' \n";
				else
					sql += "and " + key + " = ? \n";
			}
			pstmt = conn.prepareStatement(sql);
			int idx = 0;
			pstmt.setString(++idx, map.get("bcode"));
			if(!key.isEmpty() && !word.isEmpty())
				pstmt.setString(++idx, word);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return cnt;
	}

}
