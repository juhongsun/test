package com.kitri.board.dao;

import java.sql.*;
import java.util.*;

import com.kitri.board.model.ReboardDto;
import com.kitri.util.db.DBClose;
import com.kitri.util.db.DBConnection;

public class ReboardDaoImpl implements ReboardDao {

	private static ReboardDao reboardDao;

	static {
		reboardDao = new ReboardDaoImpl();
	}

	private ReboardDaoImpl() {
	}

	public static ReboardDao getReboardDao() {
		return reboardDao;
	}

	@Override
	public int writeArticle(ReboardDto reboardDto) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "";
			sql += "insert all \n";
			sql += "	into board (seq, name, id, email, subject, content, hit, logtime, bcode) \n";
			sql += "	values (?, ?, ?, ?, ?, ?, 0, sysdate, ?) \n";
			sql += "	into reboard (rseq, seq, ref, lev, step, pseq, reply) \n";
			sql += "	values (reboard_rseq.nextval, ?, ?, 0, 0, 0, 0) \n";
			sql += "select * from dual";
			pstmt = conn.prepareStatement(sql);
			int idx = 0;
			pstmt.setInt(++idx, reboardDto.getSeq());
			pstmt.setString(++idx, reboardDto.getName());
			pstmt.setString(++idx, reboardDto.getId());
			pstmt.setString(++idx, reboardDto.getEmail());
			pstmt.setString(++idx, reboardDto.getSubject());
			pstmt.setString(++idx, reboardDto.getContent());
			pstmt.setInt(++idx, reboardDto.getBcode());
			pstmt.setInt(++idx, reboardDto.getSeq());
			pstmt.setInt(++idx, reboardDto.getSeq());
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return cnt;
	}

	@Override
	public int replyArticle(ReboardDto reboardDto) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			String update_step = "";
			update_step += "update reboard set step = step + 1 \n";
			update_step += "where ref = ? and step > ?";
			pstmt = conn.prepareStatement(update_step);
			pstmt.setInt(1, reboardDto.getRef());
			pstmt.setInt(2, reboardDto.getStep());
			pstmt.executeUpdate();
			pstmt.close();
			
			String insert_reply = "";
			insert_reply += "insert all \n";
			insert_reply += "	into board (seq, name, id, email, subject, content, hit, logtime, bcode) \n";
			insert_reply += "	values (?, ?, ?, ?, ?, ?, 0, sysdate, ?) \n";
			insert_reply += "	into reboard (rseq, seq, ref, lev, step, pseq, reply) \n";
			insert_reply += "	values (reboard_rseq.nextval, ?, ?, ?, ?, ?, 0) \n";
			insert_reply += "select * from dual";
			pstmt = conn.prepareStatement(insert_reply);
			int idx = 0;
			pstmt.setInt(++idx, reboardDto.getSeq());
			pstmt.setString(++idx, reboardDto.getName());
			pstmt.setString(++idx, reboardDto.getId());
			pstmt.setString(++idx, reboardDto.getEmail());
			pstmt.setString(++idx, reboardDto.getSubject());
			pstmt.setString(++idx, reboardDto.getContent());
			pstmt.setInt(++idx, reboardDto.getBcode());
			pstmt.setInt(++idx, reboardDto.getSeq());
			pstmt.setInt(++idx, reboardDto.getRef());
			pstmt.setInt(++idx, reboardDto.getLev() + 1);
			pstmt.setInt(++idx, reboardDto.getStep() + 1);
			pstmt.setInt(++idx, reboardDto.getPseq());
			pstmt.executeUpdate();
			pstmt.close();
			
			String update_reply = "";
			update_reply += "update reboard set reply = reply + 1 \n";
			update_reply += "where seq = ?";
			pstmt = conn.prepareStatement(update_reply);
			pstmt.setInt(1, reboardDto.getPseq());
			pstmt.executeUpdate();
					
			conn.commit();
			cnt = 1;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			cnt = 0;
		} finally {
			DBClose.close(conn, pstmt);
		}
		return cnt;
	}

	@Override
	public List<ReboardDto> listArticle(Map<String, String> map) {
		List<ReboardDto> list = new ArrayList<ReboardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * \n");
			sql.append("from ( \n");
			sql.append("	select rownum as rn, a.* \n");
			sql.append("	from ( \n");
			sql.append("		select b.seq, name, id, email, subject, content, hit, \n");
			sql.append("			case when to_char(logtime, 'yyyymmdd') = to_char(sysdate, 'yyyymmdd') \n");
			sql.append("	 			 then to_char(logtime,'hh24:mi:ss') \n");
			sql.append("	 			 else to_char(logtime, 'yy.mm.dd')  \n");
			sql.append("			end logtime,  \n");
			sql.append("			bcode, rseq, ref, lev, step, pseq, reply \n");
			sql.append("		from board b, reboard r \n");
			sql.append("		where b.seq = r.seq \n");
			sql.append("		and b.bcode = ? \n");
			String key = map.get("key");
			String word = map.get("word");
			if(!key.isEmpty() && !word.isEmpty()) {
				if(key.equals("subject"))
					sql.append("		and b.subject like '%'||?||'%' \n");
				else
					sql.append("		and b." + key + " = ? \n");
			}
			sql.append("		order by ref desc, step) a) \n");
			sql.append("where rn > ? and rn <= ? \n");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setString(++idx, map.get("bcode"));
			if(!key.isEmpty() && !word.isEmpty())
				pstmt.setString(++idx, word);
			pstmt.setString(++idx, map.get("start"));
			pstmt.setString(++idx, map.get("end"));			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReboardDto reboardDto = new ReboardDto();
				reboardDto.setSeq(rs.getInt("seq"));
				reboardDto.setName(rs.getString("name"));
				reboardDto.setId(rs.getString("id"));
				reboardDto.setEmail(rs.getString("email"));
				reboardDto.setSubject(rs.getString("subject"));
				reboardDto.setContent(rs.getString("content"));
				reboardDto.setHit(rs.getInt("hit"));
				reboardDto.setLogtime(rs.getString("logtime"));
				reboardDto.setBcode(rs.getInt("bcode"));
				reboardDto.setRseq(rs.getInt("rseq"));
				reboardDto.setRef(rs.getInt("ref"));
				reboardDto.setLev(rs.getInt("lev"));
				reboardDto.setStep(rs.getInt("step"));
				reboardDto.setPseq(rs.getInt("pseq"));
				reboardDto.setReply(rs.getInt("reply"));
				
				list.add(reboardDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return list;
	}

	@Override
	public ReboardDto getArticle(int seq) {
		ReboardDto reboardDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "";
			sql += "select b.seq, name, id, email, subject, content, hit, \n";
			sql += "		logtime, bcode, rseq, ref, lev, step, pseq, reply \n";
			sql += "from board b, reboard r \n";
			sql += "where b.seq = r.seq \n";
			sql += "and b.seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reboardDto = new ReboardDto();
				reboardDto.setSeq(seq);
				reboardDto.setName(rs.getString("name"));
				reboardDto.setId(rs.getString("id"));
				reboardDto.setEmail(rs.getString("email"));
				reboardDto.setSubject(rs.getString("subject"));
				reboardDto.setContent(rs.getString("content"));
				reboardDto.setHit(rs.getInt("hit"));
				reboardDto.setLogtime(rs.getString("logtime"));
				reboardDto.setBcode(rs.getInt("bcode"));
				reboardDto.setRseq(rs.getInt("rseq"));
				reboardDto.setRef(rs.getInt("ref"));
				reboardDto.setLev(rs.getInt("lev"));
				reboardDto.setStep(rs.getInt("step"));
				reboardDto.setPseq(rs.getInt("pseq"));
				reboardDto.setReply(rs.getInt("reply"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return reboardDto;
	}

	@Override
	public int modifyArticle(ReboardDto reboardDto) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "";
			sql += "update board \n";
			sql	+= "set subject = ? , content = ? \n";
			sql	+= "where seq = ?";			
			pstmt = conn.prepareStatement(sql);
			int idx = 0;
			pstmt.setString(++idx, reboardDto.getSubject());
			pstmt.setString(++idx, reboardDto.getContent());
			pstmt.setInt(++idx, reboardDto.getSeq());
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return cnt;
	}

	@Override
	public int deleteArticle(Map<String, String> map) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			if(Integer.parseInt(map.get("reply")) == 0) {
				conn = DBConnection.getConnection();
				conn.setAutoCommit(false);
				String update_reply = "";
				update_reply += "update reboard set reply = reply - 1 \n";
				update_reply += "where seq = ?";
				pstmt = conn.prepareStatement(update_reply);
				pstmt.setString(1, map.get("pseq"));
				pstmt.executeUpdate();
				pstmt.close();
				
				String delete_reboard = "";
				delete_reboard += "delete from reboard \n";
				delete_reboard += "where seq = ? \n";			
				pstmt = conn.prepareStatement(delete_reboard);
				pstmt.setString(1, map.get("seq"));
				pstmt.executeUpdate();
				pstmt.close();
				
				String delete_board = "";
				delete_board += "delete from board \n";
				delete_board += "where seq = ? \n";			
				pstmt = conn.prepareStatement(delete_board);
				pstmt.setString(1, map.get("seq"));
				pstmt.executeUpdate();
				
				conn.commit();
				cnt = 1;		
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			cnt = 0;
		} finally {
			DBClose.close(conn, pstmt);
		}
		return cnt;
	}

}
