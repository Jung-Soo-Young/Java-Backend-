package com.koreait.board5.cmt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.koreait.board5.DBUtils;

public class CmtDAO {
	public static void insCmt(CmtVO param) {	// Insert 부분 (값 삽입)
		Connection con = null;
		PreparedStatement ps= null;
		
		String sql = " INSERT INTO t_board_cmt (iboard, iuser, cmt) "
					+ " VALUES(?, ?, ?) ";
				
		try {
			con = DBUtils.getCon();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, param.getIboard());
			ps.setInt(2, param.getIuser());
			ps.setString(3, param.getCmt());
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps);
		}
	}
	
	public static List<CmtVO> selCmtList(CmtVO param) {
		List<CmtVO> list = new ArrayList();		// CmtVO 객체 담아주기 위함
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT A.icmt, A.cmt, A.iuser, A.regdate, B.unm "
					+ " FROM t_board_cmt A "
					+ " INNER JOIN t_user B "
					+ " ON A.iuser = B.iuser "
					+ " WHERE A.iboard = ? ";
		
		try {
			con = DBUtils.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getIboard());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CmtVO vo = new CmtVO();
				list.add(vo);
				
				vo.setIcmt(rs.getInt("icmt"));
				vo.setCmt(rs.getString("cmt"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setIuser(rs.getInt("iuser"));
				vo.setUnm(rs.getString("unm"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		} finally {
			DBUtils.close(con, ps, rs);
		}
		return list;
	}
	
	public static void updCmt(CmtVO param) {	// Update (수정 부분)
		Connection con = null;
		PreparedStatement ps = null;
		String sql = " UPDATE t_board_cmt "		// Update 테이블명 - Set
					+ " SET cmt = ? "
					+ " WHERE iuser = ? AND icmt = ? ";
		
		try {
			con = DBUtils.getCon();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, param.getCmt());
			ps.setInt(2, param.getIuser());
			ps.setInt(3, param.getIcmt());
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps);
		}
	}
	
	public static void delCmt(CmtVO param) {	// Delete (삭제 부분) - icmt와 iuser의 값이 일치할 때
		Connection con = null;
		PreparedStatement ps = null;
		String sql = " DELETE FROM t_board_cmt "
					+ " WHERE icmt = ? And iuser = ? ";
		
		try {
			con = DBUtils.getCon();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, param.getIcmt());
			ps.setInt(2, param.getIuser());
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps);
		}
	}
}
