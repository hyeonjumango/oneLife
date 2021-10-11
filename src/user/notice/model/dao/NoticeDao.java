package user.notice.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;



import static common.JDBCTemplate.close;

import user.board.model.vo.Board;
import user.board.model.vo.PageInfo;
import user.notice.model.vo.Notice;
import user.notice.model.vo.Search;

public class NoticeDao {
	private Properties query = new Properties();
	
	public NoticeDao() {
		String fileNme = NoticeDao.class.getResource("/sql/notice/notice-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileNme));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getListCount(Connection conn, Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String sql = query.getProperty("getListCount");
		
		if (s.getSearchCondition() != null && s.getSearchValue() != null) {
			if (s.getSearchCondition().equals("title")) {    // 제목 검색
				sql = query.getProperty("getTitleListCount");
			} 
		}
		
		try {
			pstmt= conn.prepareStatement(sql);
			
			if (s.getSearchCondition() != null && s.getSearchValue() != null) {
				pstmt.setString(1, s.getSearchValue());
			}
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	
	public List<Notice> selectList(Connection conn, PageInfo pi, Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Notice> noticeList = new ArrayList<>();
		String sql = query.getProperty("selectsearchList");
		
		// 검색 조건과 검색 값이 넘어왔을 경우
		if(s.getSearchCondition() != null && s.getSearchValue() != null) {
			if (s.getSearchCondition().equals("title")) {    // 제목 검색
				sql = query.getProperty("selecTitleList");
			} 
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			int paramIndex = 1;
			
			// 검색 조건과 검색 값이 넘어온 경우
			if(s.getSearchCondition() != null && s.getSearchValue() != null) {
				pstmt.setString(paramIndex++, s.getSearchValue());
			}
			
			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				noticeList.add(new Notice(rset.getInt("n_no"),
											  rset.getString("n_title"),
											  rset.getString("n_content"),
											  rset.getInt("n_count"),
											  rset.getDate("enroll_date"),
											  rset.getDate("modify_date"),
											  rset.getString("status"),
											  rset.getInt("m_no"),
											  rset.getString("m_nick")));
												 
												  
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return noticeList;
	}

	
	
	// 1. 공지사항 목록 조회
	public List<Notice> selectList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Notice> noticeList = new ArrayList<>();
		String sql = query.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			
			while(rset.next()) {
				noticeList.add(new Notice(rset.getInt("n_no"),
										  rset.getString("n_title"),
										  rset.getString("n_content"),
										  rset.getInt("n_count"),
										  rset.getDate("enroll_date"),
										  rset.getDate("modify_date"),
										  rset.getString("status"),
										  rset.getInt("m_no"),
										  rset.getString("m_nick")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return noticeList;
	}

	// 2. 게시글 조회 시 조회수 증가
	public int increaseCount(Connection conn, int n_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("increaseCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, n_no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 3. 게시글 1개 조회
	public Notice selectNotice(Connection conn, int n_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice n = null;
		String sql = query.getProperty("selectNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, n_no);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				n = new Notice(rset.getInt("n_no"),
						  rset.getString("n_title"),
						  rset.getString("n_content"),
						  rset.getInt("n_count"),
						  rset.getDate("enroll_date"),
						  rset.getDate("modify_date"),
						  rset.getString("status"),
						  rset.getInt("m_no"),
						  rset.getString("m_nick"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return n;
	}

	// 4. 공지사항 글 작성
	public int insertNotice(Connection conn, Notice n) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, n.getN_title());
			pstmt.setString(2, n.getN_content());
			pstmt.setInt(3, n.getM_no());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 5. 공지사항 글 수정
	public int updateNotice(Connection conn, Notice n) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("updateNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, n.getN_title());
			pstmt.setString(2, n.getN_content());
			pstmt.setInt(3, n.getN_no());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 6. 공지사항 글 삭제
	public int deleteNotice(Connection conn, int n_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, n_no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 7. 공지사항 no번호 가져오기
	public int selectNoticeNo(Connection conn) {
		  int n_no = 0;
	      PreparedStatement pstmt = null;
	      String sql = query.getProperty("selectVid");
	      ResultSet rset = null;
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         rset = pstmt.executeQuery();
	         

	         if (rset.next()) {
	        	 n_no = rset.getInt(1);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(pstmt);
	         close(rset);
	      }
	      
	      return n_no;

	}

	
	
	
	

}
