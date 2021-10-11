package user.complaint.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import user.complaint.model.vo.PageInfo;
import user.complaint.model.vo.Search;
import user.complaint.model.vo.complaint;
import user.complaint.model.vo.complaint_manager;

import static common.JDBCTemplate.close;


public class complaintDao {
	private Properties query = new Properties();
	
	public complaintDao() {
		String fileNme = complaintDao.class.getResource("/sql/complaint/complaint-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileNme));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 게시글 총 개수 구하기 (select 구문 수행)
		public int getListCount(Connection conn, Search s) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			int listCount = 0;
			String sql = query.getProperty("getListCount");
			
			if (s.getSearchCondition() != null && s.getSearchValue() != null)  {
				if (s.getSearchCondition().equals("title")) {    // 제목 검색
					sql = query.getProperty("getTitleListCount");
				} else if(s.getSearchCondition().equals("content")) {  // 내용 검색
					sql = query.getProperty("getContentListCount");
				} else if(s.getSearchCondition().equals("writer")) {  // 작성자 검색
					sql = query.getProperty("getWriterListCount");
				}
			}
			
			if (s.getU_id() != null) {
				sql = query.getProperty("getWriterListCount");
			}
			
			
			try {
				pstmt= conn.prepareStatement(sql);
				
				if (s.getSearchCondition() != null && s.getSearchValue() != null) {
					pstmt.setString(1, s.getSearchValue());
				}
				
				if (s.getU_id() != null) {
					pstmt.setString(1, s.getU_id());
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

		// 페이징 처리 된 complaintList 조회
		public List<complaint> selectList(Connection conn, PageInfo pi, Search s) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			List<complaint> complaintList = new ArrayList<>();
			String sql = query.getProperty("selectsearchList");
			
			// 검색 조건과 검색 값이 넘어왔을 경우
			if(s.getSearchCondition() != null && s.getSearchValue() != null) {
				if (s.getSearchCondition().equals("title")) {    // 제목 검색
					sql = query.getProperty("selecTitleList");
				} else if(s.getSearchCondition().equals("content")) {  // 내용 검색
					sql = query.getProperty("selecContentList");
				} else if(s.getSearchCondition().equals("writer")) {   // 작성자 검색
					sql = query.getProperty("selecWriterList");
				}
			}
			
			if (s.getU_id() != null) {
				sql = query.getProperty("selecWriterList");
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
				
				if (s.getU_id() != null) {
					pstmt.setString(paramIndex++, s.getU_id());
				}
				
				
				pstmt.setInt(paramIndex++, startRow);
				pstmt.setInt(paramIndex++, endRow);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					complaintList.add(new complaint(rset.getInt("c_no"),
													  rset.getString("c_title"),
													  rset.getString("c_content"),
													  rset.getTimestamp("c_enroll_date"),
													  rset.getTimestamp("c_modify_date"),
													  rset.getString("c_open"),
													  rset.getString("c_status"),
													  rset.getInt("u_no"),
													  rset.getString("u_id"),
													  rset.getInt("r_dong"),
													  rset.getInt("r_ho"),
													  rset.getString("r_name"),
													  rset.getInt("c_reply_count")));
													  
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return complaintList;
		}


	// 1. 건의합니다 목록 조회
//	public List<complaint> selectList(Connection conn) {
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		List<complaint> complaintList = new ArrayList<>();
//		String sql = query.getProperty("selectList");
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			
//			rset = pstmt.executeQuery();
//			
//			
//			while(rset.next()) {
//				complaintList.add(new complaint(rset.getInt("c_no"),
//										  rset.getString("c_title"),
//										  rset.getString("c_content"),
//										  rset.getTimestamp("enroll_date"),
//										  rset.getTimestamp("modify_date"),
//										  rset.getString("open"),
//										  rset.getString("status"),
//										  rset.getInt("u_no"),
//										  rset.getString("u_id"),
//										  rset.getInt("r_dong"),
//										  rset.getInt("r_ho"),
//										  rset.getString("r_name")));
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(rset);
//			close(pstmt);
//		}
//		
//		
//		return complaintList;
//	}

	
	// 2. 건의합니다 글 작성
	public int insertComplaint(Connection conn, complaint c) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertComplaint");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getC_title());
			pstmt.setString(2, c.getC_content());
			pstmt.setString(3, c.getOpen());
			pstmt.setInt(4, c.getU_no());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 3. 건의합니다 게시글 c_no 번호 가져오기
	public int selectComplaintNo(Connection conn) {
		  int c_no = 0;
	      PreparedStatement pstmt = null;
	      String sql = query.getProperty("selectcno");
	      ResultSet rset = null;
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         rset = pstmt.executeQuery();
	         

	         if (rset.next()) {
	        	 c_no = rset.getInt(1);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(pstmt);
	         close(rset);
	      }
	      
	      return c_no;
	}

	// 4. 건의합니다 게시글 1개 조회
	public complaint selectComplaint(Connection conn, int c_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		complaint c = null;
		String sql = query.getProperty("selectComplaint");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_no);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				c = new complaint(rset.getInt("c_no"),
								  rset.getString("c_title"),
								  rset.getString("c_content"),
								  rset.getTimestamp("c_enroll_date"),
								  rset.getTimestamp("c_modify_date"),
								  rset.getString("c_open"),
								  rset.getString("c_status"),
								  rset.getInt("u_no"),
								  rset.getString("u_id"),
								  rset.getInt("r_dong"),
								  rset.getInt("r_ho"),
								  rset.getString("r_name"),
								  rset.getInt("cm_no"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return c;
	}

	// 5. 건의합니다 글 수정
	public int updatecomplaint(Connection conn, complaint c) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("updatecomplaint");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, c.getC_title());
			pstmt.setString(2, c.getC_content());
			pstmt.setString(3, c.getOpen());
			pstmt.setInt(4, c.getC_no());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 6. 건의합니다 글 삭제
	public int deleteComplaint(Connection conn, int c_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteComplaint");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, c_no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 7. 댓글 작성
	public int insertReply(Connection conn, complaint_manager r) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, r.getC_no());
			pstmt.setString(2, r.getCm_content());
			pstmt.setInt(3, r.getM_no());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 8. 댓글 조회
	public List<complaint_manager> selectReplyList(Connection conn, int c_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<complaint_manager> replayList = new ArrayList<>();
		String sql = query.getProperty("selectReplyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_no);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				replayList.add(new complaint_manager(rset.getInt("cm_no"),
										rset.getInt("c_no"),
										rset.getString("cm_content"),
										rset.getTimestamp("cm_enroll_date"),
										rset.getTimestamp("cm_modify_date"),
										rset.getString("cm_status"),
										rset.getInt("m_no"),
										rset.getString("m_nick")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return replayList;
	}

	// 9. 댓글 삭제 
	public int deleteReply(Connection conn, complaint_manager r) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, r.getCm_no());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 10. 댓글 전체 조회
	public List<complaint_manager> selectList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<complaint_manager> complaintmanList = new ArrayList<>();
		String sql = query.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			
			while(rset.next()) {
				complaintmanList.add(new complaint_manager(rset.getInt("cm_no"),
										  rset.getInt("c_no"),
										  rset.getString("cm_content"),
										  rset.getTimestamp("cm_enroll_date"),
										  rset.getTimestamp("cm_modify_date"),
										  rset.getString("cm_status"),
										  rset.getInt("m_no")));
										
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return complaintmanList;
	}

	// 댓글 수 업데이트
	public int countReply(Connection conn, int c_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("countReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_no);
			pstmt.setInt(2, c_no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int countReply(Connection conn, complaint_manager r) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("countReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, r.getC_no());
			pstmt.setInt(2, r.getC_no());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
	
	
	

}
