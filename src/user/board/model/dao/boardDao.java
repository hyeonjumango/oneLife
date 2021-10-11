package user.board.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import user.board.model.vo.Board;
import user.board.model.vo.Board_Comment;
import user.board.model.vo.Board_Like;
import user.board.model.vo.PageInfo;
import user.board.model.vo.Search;

import static common.JDBCTemplate.close;


public class boardDao {
	private Properties query = new Properties();
	
	public boardDao() {
		String fileName = boardDao.class.getResource("/sql/board/board-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
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
		
		if (s.getSearchCondition() != null && s.getSearchValue() != null) {
			if (s.getSearchCondition().equals("title")) {    // 제목 검색
				sql = query.getProperty("getTitleListCount");
			} else if(s.getSearchCondition().equals("content")) {  // 내용 검색
				sql = query.getProperty("getContentListCount");
			} else if(s.getSearchCondition().equals("writer")) {  // 작성자 검색
				sql = query.getProperty("getWriterListCount");
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

	// 페이징 처리 된 BoardList 조회
	public List<Board> selectList(Connection conn, PageInfo pi, Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Board> boardList = new ArrayList<>();
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
				boardList.add(new Board(rset.getInt("b_no"),
												  rset.getString("b_title"),
												  rset.getString("b_content"),
												  rset.getTimestamp("b_enroll_date"),
												  rset.getTimestamp("b_modify_date"),
												  rset.getInt("b_count"),
												  rset.getString("b_status"),
												  rset.getInt("u_no"),
												  rset.getString("u_nickname"),
												  rset.getInt("b_reply_count"),
												  rset.getInt("b_likecnt")));
												 
												  
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return boardList;
	}

	// 자유게시판 글 작성
	public int insertBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getB_title());
			pstmt.setString(2, b.getB_content());
			pstmt.setInt(3, b.getU_no());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 게시글 b_no 번호 가져오기
	public int selectBoardNo(Connection conn) {
		 int b_no = 0;
	      PreparedStatement pstmt = null;
	      String sql = query.getProperty("selectbno");
	      ResultSet rset = null;
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         rset = pstmt.executeQuery();
	         

	         if (rset.next()) {
	        	 b_no = rset.getInt(1);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(pstmt);
	         close(rset);
	      }
	      
	      return b_no;
	}

	// 조회수 증가
	public int increaseCount(Connection conn, int b_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("increaseCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, b_no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 게시글 1개 조회
	public Board selectBoard(Connection conn, int b_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board b = null;
		String sql = query.getProperty("selectBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_no);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				b = new Board(rset.getInt("b_no"),
							  rset.getString("b_title"),
							  rset.getString("b_content"),
							  rset.getTimestamp("b_enroll_date"),
							  rset.getTimestamp("b_modify_date"),
							  rset.getInt("b_count"),
							  rset.getString("b_status"),
							  rset.getInt("u_no"),
							  rset.getString("u_nickname"),
							  rset.getInt("bc_no"),
							  rset.getInt("b_reply_count"),
							  rset.getInt("b_likecnt"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return b;
	}

	// 게시글 수정
	public int updateBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("updateBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, b.getB_title());
			pstmt.setString(2, b.getB_content());
			pstmt.setInt(3, b.getB_no());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 게시글 삭제
	public int deleteBoard(Connection conn, int b_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, b_no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 댓글 작성
	public int insertReply(Connection conn, Board_Comment b) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getB_no());
			pstmt.setString(2, b.getBc_content());
			pstmt.setInt(3, b.getU_no());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 댓글 조회
	public List<Board_Comment> selectReplyList(Connection conn, int b_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Board_Comment> replayList = new ArrayList<>();
		String sql = query.getProperty("selectReplyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_no);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				replayList.add(new Board_Comment(rset.getInt("bc_no"),
										rset.getInt("b_no"),
										rset.getString("bc_content"),
										rset.getTimestamp("bc_enroll_date"),
										rset.getTimestamp("bc_modify_date"),
										rset.getString("bc_status"),
										rset.getInt("u_no"),
										rset.getString("u_nickname")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return replayList;
	}

	// 댓글 삭제
	public int deleteReply(Connection conn, Board_Comment b) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getBc_no());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	

	// 댓글 수 업데이트
	public int countReply(Connection conn, Board_Comment b) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("countReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getB_no());
			pstmt.setInt(2, b.getB_no());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 좋아요 추가 
	public int insertHeart(Connection conn, Board_Like bl) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertHeart");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bl.getU_no());
			pstmt.setInt(2, bl.getB_no());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 게시글 및 댓글 신고
	public int report(Connection conn, String bno, String bcno, int uno) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		
		System.out.println("bno : " + bno);
		System.out.println("bcno : " + bcno);
		if (bno.equals("")) {
			sql = query.getProperty("reportReply");
		} else {
			sql = query.getProperty("reportWrite");
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, uno);
			if (bno.equals("")) {
				pstmt.setString(2, bcno);
			} else {
				pstmt.setString(2, bno);
			}
      	result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	// 좋아요 수 업데이트
	public int countHeart(Connection conn, Board_Like bl) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("countHeart");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bl.getB_no());
			pstmt.setInt(2, bl.getB_no());

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

  //게시글 신고 한 사람인지 아닌지 
public int chekcUno(Connection conn, String bno, String bcno, int uno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = "";
		
		if (bno.equals("")) {
			sql = query.getProperty("chekcUnoReply");
		} else {
			sql = query.getProperty("chekcUnoWrite");
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, uno);
			if (bno.equals("")) {
				pstmt.setString(2, bcno);
			} else {
				pstmt.setString(2, bno);
			}
			
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	

	// 좋아요수 조회
	public Board selectHeart(Connection conn, Board_Like bl) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board b = null;
		String sql = query.getProperty("selectHeart");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bl.getB_no());
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				b = new Board(rset.getInt("b_likecnt"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return b;
	}

	// 좋아요 삭제
	public int deleteHeart(Connection conn, Board_Like bl) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteHeart");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bl.getB_no());
			pstmt.setInt(2, bl.getU_no());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
	public int selectUsercnt(Connection conn, int b_no, int u_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = query.getProperty("selectUsercnt");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_no);
			pstmt.setInt(2, u_no);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	
	public int replyCnt(Connection conn, int b_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = query.getProperty("replyCnt");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_no);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
}
