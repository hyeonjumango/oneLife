package user.board.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import user.board.model.dao.boardDao;
import user.board.model.vo.Board;
import user.board.model.vo.Board_Comment;
import user.board.model.vo.Board_Like;
import user.board.model.vo.PageInfo;
import user.board.model.vo.Search;

import static common.JDBCTemplate.*;


public class boardService {
	private boardDao bd = new boardDao();
	
	// 1. 자유게시판 리스트 조회용 서비스 메소드
	public Map<String, Object> selectlist(int page, Search s) {
		Connection conn = getConnection();
		
		int listCount = bd.getListCount(conn, s);
		
		PageInfo pi = new PageInfo(page, listCount, 10, 10);
		
		List<Board> boardList = bd.selectList(conn, pi, s);
		
		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put("pi", pi);
		returnMap.put("boardList", boardList);
		
		return returnMap;
	}

	// 2. 자유게시판 글 작성 
	public int insertBoard(Board b) {
		Connection conn = getConnection();
		
		int result = bd.insertBoard(conn, b);
		
		int b_no = 0;
		if (result > 0) {
			b_no = bd.selectBoardNo(conn);
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return b_no;
	}

	// 조회수 증가
	public int increaseCount(int b_no) {
		Connection conn = getConnection();
		
		int result =  bd.increaseCount(conn, b_no);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	// 게시글 1개 조회
	public Board selectBoard(int b_no) {
		Connection conn = getConnection();
		
		Board c = bd.selectBoard(conn, b_no);
		
		// 댓글 조회
		c.setReplyList(bd.selectReplyList(conn, b_no));
			
		close(conn);
		
		return c;
	}

	// 게시글 수정
	public int updateBoard(Board b) {
		Connection conn = getConnection();
		
		int result =  bd.updateBoard(conn, b);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	// 게시글 삭제
	public int deleteBoard(int b_no) {
		Connection conn = getConnection();
		
		int result =  bd.deleteBoard(conn, b_no);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	// 댓글 추가 + 새로 갱신 된 댓글 리스트 조회
	public List<Board_Comment> insertReply(Board_Comment b) {
		Connection conn = getConnection();
		
		int result1 = bd.insertReply(conn, b);
		
		int result2 = bd.countReply(conn, b);
		
		List<Board_Comment> replyList = null;
		
		if (result1 > 0 && result2 > 0) {
			commit(conn);
			replyList = bd.selectReplyList(conn, b.getB_no());
		} else {
			rollback(conn);
		}
		close(conn);
		
		return replyList;
	}

	// 댓글 삭제
	public List<Board_Comment> deleteReply(Board_Comment b) {
		Connection conn = getConnection();
		
		int result1 = bd.deleteReply(conn, b);
		
		int result2 = bd.countReply(conn, b);
		
		List<Board_Comment> replyList = null;
		
		if (result1 > 0 && result2 > 0) {
			commit(conn);
			replyList = bd.selectReplyList(conn, b.getB_no());
		} else {
			rollback(conn);
		}
		close(conn);
		
		return replyList;
	}

	// 좋아요 추가 + 조회
	public Board insertHeart(Board_Like bl) {
		Connection conn = getConnection();
		
		int result1 = bd.insertHeart(conn, bl);
		
		
		int result2 = bd.countHeart(conn, bl);
		
		Board b = null;
		
		if (result1 > 0 && result2 > 0) {
			commit(conn);
			b = bd.selectHeart(conn, bl);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return b;
	}

	// 종아요 삭제
	public Board deleteHeart(Board_Like bl) {
		Connection conn = getConnection();
		
		int result1 = bd.deleteHeart(conn, bl);
		
		int result2 = bd.countHeart(conn, bl);
		
		Board b = null;
		
		if (result1 > 0 && result2 > 0) {
			commit(conn);
			b = bd.selectHeart(conn, bl);
		} else {
			rollback(conn);
		}
		close(conn);
		
		
		return b;
	}

	public int report(String bno, String bcno, int uno) {
		Connection conn = getConnection();
		
		int result = bd.report(conn, bno, bcno, uno);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public int chekcUno(String bno, String bcno, int uno) {
		Connection conn = getConnection();
		
		int result = bd.chekcUno(conn, bno, bcno, uno);
		
		close(conn);
		
		return result;
	}

	
	public int selectUsercnt(int b_no, int u_no) {
		Connection conn = getConnection();
		
		int result = bd.selectUsercnt(conn, b_no, u_no);
			
		close(conn);
		
		return result;
	}

	

	public int replyCnt(int b_no) {
		Connection conn = getConnection();
		
		int result = bd.replyCnt(conn, b_no);
			
		close(conn);
		
		return result;
	}

	

	


	

	
}
	
	
	
	
	





