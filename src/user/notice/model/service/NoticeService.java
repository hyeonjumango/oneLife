package user.notice.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static common.JDBCTemplate.*;


import user.board.model.vo.PageInfo;
import user.notice.model.dao.NoticeDao;
import user.notice.model.vo.Notice;
import user.notice.model.vo.Search;

public class NoticeService {
	private NoticeDao nd = new NoticeDao();
	
	public Map<String, Object> selectlist(int page, Search s) {
		Connection conn = getConnection();
		
		int listCount = nd.getListCount(conn, s);
		
		PageInfo pi = new PageInfo(page, listCount, 10, 10);
		
		List<Notice> noticeList = nd.selectList(conn, pi, s);
		
		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put("pi", pi);
		returnMap.put("noticeList", noticeList);
		
		
		return returnMap;
	}


	// 1. 공지사항 리스트 조회용 서비스 메소드
	public List<Notice> selectList() {
		Connection conn = getConnection();
		List<Notice> noticeList = nd.selectList(conn);
		close(conn);
		
		return noticeList;
	}

	// 2. 공지사항 게시글 1개 조회
	public Notice selectNotice(int n_no) {
		Connection conn = getConnection();
		
		Notice n = nd.selectNotice(conn, n_no);
	
		close(conn);
		
		return n;
	}

	// 2. 공지사항 게시글 조회수 증가
	public int increaseCount(int n_no) {
		Connection conn = getConnection();
		
		int result =  nd.increaseCount(conn, n_no);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	// 3. 공지사항 글 작성 
	public int insertNotice(Notice n) {
		Connection conn = getConnection();
		
		int result =  nd.insertNotice(conn, n);
		int n_no = 0;
		if (result > 0) {
			n_no = nd.selectNoticeNo(conn);
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return n_no;
	
	}

	// 4. 공지사항 글 수정
	public int updateNotice(Notice n) {
		Connection conn = getConnection();
		
		int result =  nd.updateNotice(conn, n);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	// 5. 공지사항 글 삭제
	public int deleteNotice(int n_no) {
		Connection conn = getConnection();
		
		int result =  nd.deleteNotice(conn, n_no);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	


}







