package admin.report.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import admin.report.model.vo.Reply;
import admin.report.model.vo.Search;
import common.PageInfo;

public class ReplyDao {

	private Properties query = new Properties();
	
	public ReplyDao() {
		String fileName = ReplyDao.class.getResource("/sql/reply/reply-query.xml").getPath();
		
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 게시글 갯수 구하기
	public int getListCount(Connection conn, Search sc) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = query.getProperty("getListCount");
		
		// 검색어가 있을시
		if(sc.getStatus() != null && sc.getReportNum() != null && sc.getSearchValue() != null) {
			sql = query.getProperty("getListSearchCount");
			
			if(sc.getStatus().equals("all")) {
				sql += " AND COMMENT_STATUS IS NOT NULL ";
			}else {
				sql += " AND COMMENT_STATUS = ? ";
			}
			
//			if(sc.getReportNum().equals("all")) {
//				sql += " AND REPORT IS NOT NULL ";
//			}else if(sc.getReportNum().equals("reportDown")) {
//				sql += " AND REPORT BETWEEN 0 AND 4 ";
//			}else if(sc.getReportNum().equals("reportUp")){
//				sql += " AND REPORT >= 5 ";
//			}
			
			if(sc.getSearchName().equals("comment") && !sc.getSearchValue().equals("")) {
				sql += " AND BC_CONTENT LIKE '%' || ? || '%' ";
			}else if(sc.getSearchName().equals("nick") && !sc.getSearchValue().equals("")) {
				sql += " AND COMMENT_U_NICKNAME LIKE '%' || ? || '%' ";
			}else {
				sql += " AND BC_CONTENT IS NOT NULL ";
			}
		}
		
		
		try {
			int paramIndex = 1;
			pstmt = conn.prepareStatement(sql);
			
			if(sc.getStatus() != null && sc.getReportNum() != null && sc.getSearchValue() != null) {
				if(sc.getStatus().equals("all")) {
				}else {
					pstmt.setString(paramIndex++, sc.getStatus());
				}
				
				if(sc.getSearchName().equals("comment") && !sc.getSearchValue().equals("")) {
					pstmt.setString(paramIndex++, sc.getSearchValue());
				}else if(sc.getSearchName().equals("nick") && !sc.getSearchValue().equals("")) {
					pstmt.setString(paramIndex++, sc.getSearchValue());
				}
			}
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
	
	
	// 댓글 게시판 목록 조회
	public List<Reply> selectReplyList(Connection conn, int page, PageInfo pi, Search sc) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Reply> rList = new ArrayList<>();
		String sql = query.getProperty("selectReplyList");
		// 검색어가 있을시
		if(sc.getStatus() != null && sc.getReportNum() != null && sc.getSearchValue() != null) {
			sql = query.getProperty("selectReplySearchList");
			
			if(sc.getStatus().equals("all")) {
				sql += " AND COMMENT_STATUS IS NOT NULL ";
			}else {
				sql += " AND COMMENT_STATUS = ? ";
			}
			
//			if(sc.getReportNum().equals("all")) {
//				sql += " AND REPORT IS NOT NULL ";
//			}else if(sc.getReportNum().equals("reportDown")) {
//				sql += " AND REPORT BETWEEN 0 AND 4 ";
//			}else if(sc.getReportNum().equals("reportUp")) {
//				sql += " AND REPORT >= 5 ";
//			}
			
			if(sc.getSearchName().equals("comment") && !sc.getSearchValue().equals("")) {
				sql += " AND BC_CONTENT LIKE '%' || ? || '%' ";
			}else if(sc.getSearchName().equals("nick") && !sc.getSearchValue().equals("")) {
				sql += " AND COMMENT_U_NICKNAME LIKE '%' || ? || '%' ";
			}else {
				sql += " AND BC_CONTENT IS NOT NULL ";
			}
			
			sql += ") WHERE ROWN BETWEEN ? AND ?";
		}
		try {
			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			int paramIndex = 1;
			
			pstmt = conn.prepareStatement(sql);
			
			// 검색어가 있을시
			if(sc.getStatus() != null && sc.getReportNum() != null && sc.getSearchValue() != null) {
				
				if(sc.getStatus().equals("all")) {
				}else {
					pstmt.setString(paramIndex++, sc.getStatus());
				}
				
				if(sc.getSearchName().equals("comment") && !sc.getSearchValue().equals("")) {
					pstmt.setString(paramIndex++, sc.getSearchValue());
				}else if(sc.getSearchName().equals("nick") && !sc.getSearchValue().equals("")) {
					pstmt.setString(paramIndex++, sc.getSearchValue());
				}
			}
			
			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Reply r = new Reply();
				r.setbNo(rset.getInt("B_NO"));
				r.setuNickname(rset.getString("U_NICKNAME"));
				r.setbTitle(rset.getString("B_TITLE"));
				r.setbContent(rset.getString("B_CONTENT"));
				r.setCommentBcNo(rset.getInt("COMMENT_BC_NO"));
				r.setCommentEnrollDate(rset.getTimestamp("COMMENT_ENROLL_DATE"));
				r.setBcContent(rset.getString("BC_CONTENT"));
				r.setCommentuNo(rset.getInt("COMMENT_U_NO"));
				r.setCommentuNickname(rset.getString("COMMENT_U_NICKNAME"));
				r.setReport(rset.getInt("REPORT"));
				r.setCommentStatus(rset.getString("COMMENT_STATUS"));
				
				rList.add(r);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return rList;
	}

	// 댓글 삭제
	public int removeReply(Connection conn, String[] reportChecks) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("removeReply");
		
		for(int i = 0; i < reportChecks.length; i++) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(reportChecks[i]));
				
				result += pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
		}
		
		return result;
	}

	

}
