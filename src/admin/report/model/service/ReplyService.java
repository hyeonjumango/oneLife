package admin.report.model.service;

import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import admin.report.model.dao.ReplyDao;
import admin.report.model.vo.Reply;
import admin.report.model.vo.Search;
import common.PageInfo;

public class ReplyService {
	private ReplyDao da;
	
	public ReplyService() {
		da = new ReplyDao();
	}
	
	
	// 댓글 게시판 조회
	public Map<String, Object> selectReplyList(int page, Search sc) {
		Connection conn = getConnection();
		
		// 게시글 갯수 구하기
		int listCount = da.getListCount(conn, sc);
		// System.out.println("listCount : " + listCount);
		
		// 페이징 객체만들기
		PageInfo pi = new PageInfo(page, listCount, 10, 10);
		// System.out.println(pi);
		
		// 목록 출력
		List<Reply> rList = da.selectReplyList(conn, page, pi, sc);
		// System.out.println(rList);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("pi", pi);
		returnMap.put("rList", rList);
		
		return returnMap;
	}

	// 댓글 삭제
	public int removeRelpy(String[] reportChecks) {
		Connection conn = getConnection();
		
		int result = da.removeReply(conn, reportChecks);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		return result;
	}
	
	
	
	
	
}
