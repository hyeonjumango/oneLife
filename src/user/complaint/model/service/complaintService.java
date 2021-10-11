package user.complaint.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import user.complaint.model.dao.complaintDao;
import user.complaint.model.vo.PageInfo;
import user.complaint.model.vo.Search;
import user.complaint.model.vo.complaint;
import user.complaint.model.vo.complaint_manager;

import static common.JDBCTemplate.*;


public class complaintService {
	private complaintDao cd = new complaintDao();
	
	public Map<String, Object> selectlist(int page, Search s) {
		Connection conn = getConnection();
		
		// 1. 게시글 총 개수 구하기
		int listCount = cd.getListCount(conn, s);
		
		// 2. PageInfo 객체 만들기
		PageInfo pi = new PageInfo(page, listCount, 10, 10);
		
		// 3. 페이징 처리가 된 게시글 목록 조회
		List<complaint> complaintList = cd.selectList(conn, pi, s);
		
		// 리턴용 Map 선언
		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put("pi", pi);
		returnMap.put("complaintList", complaintList);
		
		return returnMap;
	}
	



	// 2. 건의합니다 글 작성
	public int insertComplaint(complaint c) {
		Connection conn = getConnection();
		
		int result = cd.insertComplaint(conn, c);
		
		int c_no = 0;
		if (result > 0) {
			c_no = cd.selectComplaintNo(conn);
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return c_no;
	}

	// 3. 건의합니다 게시글 1개 조회
	public complaint selectComplaint(int c_no) {
		Connection conn = getConnection();
		
		complaint c = cd.selectComplaint(conn, c_no);
		
		// 댓글 조회
		c.setReplyList(cd.selectReplyList(conn, c_no));
		
		close(conn);
		
		return c;
	}

	// 4. 건의합니다  글 수정 
	public int updatecomplaint(complaint c) {
		Connection conn = getConnection();
		
		int result =  cd.updatecomplaint(conn, c);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	// 5. 건의합니다 글 삭제
	public int deleteComplaint(int c_no) {
		Connection conn = getConnection();
		
		int result =  cd.deleteComplaint(conn, c_no);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	
	}

	// 6. 댓글 추가 + 새로 갱신 된 댓글 리스트 조회
	public List<complaint_manager> insertReply(complaint_manager r) {
		Connection conn = getConnection();
		
		int result1 = cd.insertReply(conn, r);
		
		int result2 = cd.countReply(conn, r);
		
		List<complaint_manager> replyList = null;
		
		if (result1 > 0 && result2 > 0) {
			commit(conn);
			replyList = cd.selectReplyList(conn, r.getC_no());
		} else {
			rollback(conn);
		}
		close(conn);
		
		return replyList;
	}

	// 7. 댓글 삭제 
	public List<complaint_manager> deleteReply(complaint_manager r) {
		Connection conn = getConnection();
		
		int result1 = cd.deleteReply(conn, r);
		
		int result2 = cd.countReply(conn, r);
		
		List<complaint_manager> replyList = null;
		
		if (result1 > 0 && result2 > 0) {
			commit(conn);
			replyList = cd.selectReplyList(conn, r.getC_no());
		} else {
			rollback(conn);
		}
		close(conn);
		
		return replyList;
	}

    // 8. 댓글 전체 조회
	public List<complaint_manager> selectList() {
		Connection conn = getConnection();
		List<complaint_manager> complaintmanList = cd.selectList(conn); 
		close(conn);
		return complaintmanList;
	}

	

	
}
	
	
	
	
	





