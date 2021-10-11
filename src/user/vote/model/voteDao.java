package user.vote.model;

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

import user.board.model.dao.boardDao;
import user.board.model.vo.PageInfo;
import user.board.model.vo.Search;
import user.vote.vo.Vote;
import user.vote.vo.Vote_choice;

public class voteDao {
	private Properties query = new Properties();

	public voteDao() {
		String fileName = boardDao.class.getResource("/sql/vote/vote-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// 게시판 게시글 총 개수 구하기 (select 구문 수행)
	public int getListCount(Connection conn, Search s) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			int listCount = 0;
			String sql = query.getProperty("getListCount");
			
			if (s.getSearchCondition() != null && s.getSearchValue() != null) {
				if (s.getSearchCondition().equals("title")) {    // 제목 검색
					sql = query.getProperty("getTitleListCount");
				} else if(s.getSearchCondition().equals("content")) {  // 내용 검색
					sql = query.getProperty("getContentListCount");
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
	
	// 페이징 처리 된 VoteList 조회
		public List<Vote> selectList(Connection conn, PageInfo pi, Search s) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			List<Vote> voteList = new ArrayList<>();
			String sql = query.getProperty("selectsearchList");
			
			// 검색 조건과 검색 값이 넘어왔을 경우
			if(s.getSearchCondition() != null && s.getSearchValue() != null) {
				if (s.getSearchCondition().equals("title")) {    // 제목 검색
					sql = query.getProperty("selecTitleList");
				} else if(s.getSearchCondition().equals("content")) {  // 내용 검색
					sql = query.getProperty("selecContentList");
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
					voteList.add(new Vote(rset.getInt("v_no"),
													  rset.getString("v_title"),
													  rset.getString("v_content"),
													  rset.getInt("v_count"),
													  rset.getString("v_enroll_date"),
													  rset.getString("v_modify_date"),
													  rset.getString("v_status"),
													  rset.getInt("m_no"),
													  rset.getString("m_nick")));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return voteList;
		}
		
		// 투표게시판 글작성(관리자만)
		public int insertVote(Connection conn, Vote v) {
			PreparedStatement pstmt = null;
			int result = 0;
			String sql = query.getProperty("insertVote");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, v.getV_title());
				pstmt.setString(2, v.getV_content());
				pstmt.setString(3, v.getV_modify_date());
				pstmt.setInt(4, v.getM_no());
				pstmt.setString(5, v.getV_choice());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return result;
		}
		
		
		
		// 투표게시판 v_no 글가져오기
		public int selectVoteNo(Connection conn) {
			 int v_no = 0;
		      PreparedStatement pstmt = null;
		      String sql = query.getProperty("selectvno");
		      ResultSet rset = null;
		      
		      try {
		         pstmt = conn.prepareStatement(sql);
		         rset = pstmt.executeQuery();
		         
		         if (rset.next()) {
		        	 v_no = rset.getInt(1);
		         }
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } finally {
		         close(pstmt);
		         close(rset);
		      }
		      
		      return v_no;
		}
		
		// 투표게시판 선택지 글작성 
		public int insertVoteExample(Connection conn, Vote vv) {
			PreparedStatement pstmt = null;
			int result = 0;
			String sql = query.getProperty("insertVoteExample");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, vv.getV_no());
				pstmt.setString(2, vv.getVe_choice1());
				pstmt.setString(3, vv.getVe_choice2());
				pstmt.setString(4, vv.getVe_choice3());
				pstmt.setString(5, vv.getVe_choice4());
				pstmt.setString(6, vv.getVe_choice5());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return result;
		}
		
		// 투표게시판 조회수 증가
		public int increaseCount(Connection conn, int v_no) {
			PreparedStatement pstmt = null;
			int result = 0;
			String sql = query.getProperty("increaseCount");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, v_no);
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return result;
		}
		
		// 게시글 1개 조회
		public Vote selectVote(Connection conn, int v_no) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			Vote v = null;
			String sql = query.getProperty("selectVote");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, v_no);
				
				rset = pstmt.executeQuery();
				
				if (rset.next()) {
					v = new Vote(rset.getInt("v_no"),
								  rset.getString("v_title"),
								  rset.getString("v_content"),
								  rset.getInt("v_count"),
								  rset.getString("v_enroll_date"),
								  rset.getString("v_modify_date"),
								  rset.getString("v_status"),
								  rset.getInt("m_no"),
								  rset.getString("m_nick"),
								  rset.getString("v_choice"),
								  rset.getInt("ve_no"),
								  rset.getString("ve_choice1"),
								  rset.getString("ve_choice2"),
								  rset.getString("ve_choice3"),
								  rset.getString("ve_choice4"),
								  rset.getString("ve_choice5"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			return v;
		}
		
		// 투표게시판 게시글 삭제
		public int deleteVote(Connection conn, int v_no) {
			PreparedStatement pstmt = null;
			int result = 0;
			String sql = query.getProperty("deleteVote");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, v_no);
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return result;
		}
		
		// 사용자 투표 값
		public int insertVoteval(Connection conn, Vote_choice vc) {
			PreparedStatement pstmt = null;
			int result = 0;
			String sql = query.getProperty("insertVoteval");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, vc.getVe_no());
				pstmt.setInt(2, vc.getV_no());
				pstmt.setString(3, vc.getVc_val1());
				pstmt.setString(4, vc.getVc_val2());
				pstmt.setString(5, vc.getVc_val3());
				pstmt.setString(6, vc.getVc_val4()); 
				pstmt.setString(7, vc.getVc_val5());
				pstmt.setInt(8, vc.getU_no());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return result;
		}
		
		// 주민 세대주 구분
		public String selectType(Connection conn, int u_no) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String v = null;
			String sql = query.getProperty("selectType");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, u_no);
				
				rset = pstmt.executeQuery();
				
				if (rset.next()) {
					v = rset.getString("r_type");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			return v;
		}
		
		// 투표 값 조회
		public Vote_choice selectVal(Connection conn, int v_no) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			Vote_choice vc = null;
			String sql = query.getProperty("selectVal");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, v_no);
				
				rset = pstmt.executeQuery();
				
				if (rset.next()) {
					vc = new Vote_choice(rset.getInt("val1"),
										  rset.getInt("val2"),
										  rset.getInt("val3"),
										  rset.getInt("val4"),
										  rset.getInt("val5"));
								 
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			return vc;
		}
		
		// 주민 투표했는지 여부 확인문
		public int selectUnoCount(Connection conn, int v_no, int u_no) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			int v = 0;
			String sql = query.getProperty("selectUnoCount");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, u_no);
				pstmt.setInt(2, v_no);
				
				rset = pstmt.executeQuery();
				
				if (rset.next()) {
					v = rset.getInt("uno");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			return v;
		}
		
		
		
		
	}
