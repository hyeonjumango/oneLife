package admin.report.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import admin.report.model.dao.ReportDao;
import admin.report.model.vo.Reply;
import admin.report.model.vo.Report;
import admin.report.model.vo.Search;
import common.PageInfo;

public class ReportService {
	private ReportDao da;
	public ReportService() {
		da = new ReportDao();
	}

	// 게시글 목록 전체 조회
	public Map<String, Object> selectReportList(int page, Search sc) {
		Connection conn = getConnection();
		
		// 리스트 목록 전체 구하기
		int listCount = da.getListCount(conn, sc);
		// System.out.println("listCount : " + listCount);
		
		// 페이징 객체 만들기
		PageInfo pi = new PageInfo(page, listCount, 10, 10);
		// System.out.println(pi);
		
		// 리스트가져오기
		List<Report> rList = da.selectListReport(conn, pi, sc);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("pi", pi);
		returnMap.put("rList", rList);
		
		return returnMap;
	}

	// 신고 게시글 삭제
	public int removeReport(String[] reportChecks) {
		Connection conn = getConnection();
		
		int result = da.removeReport(conn, reportChecks);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

}
