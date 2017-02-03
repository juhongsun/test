package com.kitri.admin.board.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kitri.admin.board.model.*;
import com.kitri.util.db.DBClose;
import com.kitri.util.db.DBConnection;

public class BoardAdminDaoImpl implements BoardAdminDao {

	private static BoardAdminDao boardAdminDao;

	static {
		boardAdminDao = new BoardAdminDaoImpl();
	}

	private BoardAdminDaoImpl() {
	}

	public static BoardAdminDao getBoardAdminDao() {
		return boardAdminDao;
	}

	@Override
	public List<BoardListDto> getBoardList() {
		List<BoardListDto> list = new ArrayList<BoardListDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "";
			sql += "select c.cname, c.ccode, bl.btype, bl.bname, bl.bcode, \n";
			sql += "	   decode(bl.btype, 5, 'reboard', \n";
			sql += "						6, 'album', \n";
			sql += "						7, 'bbs', \n";
			sql += "						'board') controller \n";
			sql += "from category c, board_list bl \n";
			sql += "where c.ccode = bl.ccode";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardListDto boardListDto = new BoardListDto();
				boardListDto.setBcode(rs.getInt("bcode"));
				boardListDto.setCcode(rs.getInt("ccode"));
				boardListDto.setBtype(rs.getInt("btype"));
				boardListDto.setCname(rs.getString("cname"));
				boardListDto.setBname(rs.getString("bname"));
				boardListDto.setController(rs.getString("controller"));
				
				list.add(boardListDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);			
		}
		return list;
	}

	@Override
	public List<CategoryDto> getCategory() {
		List<CategoryDto> list = new ArrayList<CategoryDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "";
			sql += "select cname, ccode \n";
			sql += "from category \n";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CategoryDto categoryDto = new CategoryDto();
				categoryDto.setCcode(rs.getInt("ccode"));
				categoryDto.setCname(rs.getString("cname"));
				
				list.add(categoryDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);			
		}
		return list;
	}

	@Override
	public int makeCategory(String cname) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "";
			sql += "insert into category (ccode, cname) \n";
			sql += "values (category_cseq.nextval, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cname);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);			
		}
		return cnt;
	}

	@Override
	public List<BoardTypeDto> getBoardType() {
		List<BoardTypeDto> list = new ArrayList<BoardTypeDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "";
			sql += "select btype, btype_name \n";
			sql += "from board_type \n";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardTypeDto boardTypeDto = new BoardTypeDto();
				boardTypeDto.setBtype(rs.getInt("btype"));
				boardTypeDto.setBtypeName(rs.getString("btype_name"));
				
				list.add(boardTypeDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);			
		}
		return list;
	}

	@Override
	public int makeBoard(BoardListDto boardListDto) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "";
			sql += "insert into board_list (bcode, bname, btype, ccode) \n";
			sql += "values (board_list_seq.nextval, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardListDto.getBname());
			pstmt.setInt(2, boardListDto.getBtype());
			pstmt.setInt(3, boardListDto.getCcode());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);			
		}
		return cnt;
	}

}
