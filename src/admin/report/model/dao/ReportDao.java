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
import admin.report.model.vo.Report;
import admin.report.model.vo.Search;
import common.PageInfo;

public class ReportDao {
	
	private Properties query = new Properties();
	
	public ReportDao() {
		String fileName = ReportDao.class.getResource("/sql/report/report-query.xml").getPath();
		
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
		
		if(sc.getStatus() != null && sc.getReportNum() != null) {
			sql = query.getProperty("getListSearchCount");
			// 검색어 있을시
			// 삭제여부
			if(sc.getStatus().equals("all")) {

				 sql += " and B_STATUS IS NOT NULL ";
			}else if(sc.getStatus().equals("Y")){
				 sql += " and B_STATUS = 'Y' ";
			}else if(sc.getStatus().equals("N")){
				sql += " and B_STATUS = 'N' ";
			}
			
			// 신고누적
//			if(sc.getReportNum().equals("all")) {
//				// 전체
//				sql += "AND REPORT IS NOT NULL ";
//			}else if(sc.getReportNum().equals("reportDown")){
//				// 0 ~ 4
//				sql += "AND REPORT BETWEEN 0 AND 4 ";
//			}else {
//				// 5 이상일시
//				sql += "AND REPORT >= 5 ";
//			}
			
			// 검색조건
			if(sc.getSearchName().equals("title") && !sc.getSearchValue().equals("")) {
				sql += "AND B_TITLE LIKE '%' || ? || '%' "; 
			}else if(sc.getSearchName().equals("content") && !sc.getSearchValue().equals("")){
				sql += "AND B_CONTENT LIKE '%' || ? || '%' ";
			}else {
				sql += "AND B_TITLE IS NOT NULL ";
			}
		}
		
		// System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			
			if(sc.getStatus() != null && sc.getReportNum() != null) {
				// 검색조건
				if(sc.getSearchName().equals("title") && !sc.getSearchValue().equals("")) {
					pstmt.setString(1, sc.getSearchValue());
				}else if(sc.getSearchName().equals("content") && !sc.getSearchValue().equals("")) {
					pstmt.setString(1, sc.getSearchValue());
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
	
	// 게시글 전체 가져오기
	public List<Report> selectListReport(Connection conn, PageInfo pi, Search sc) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Report> rList = new ArrayList<>();
		String sql = query.getProperty("selectListReport");
		
		// 검색어가있을시
		if(sc.getStatus() != null && sc.getReportNum() != null) {
			sql = query.getProperty("selectListSearchReport");
			
			if(sc.getStatus().equals("all")) {
				sql += " WHERE B_STATUS IS NOT NULL ";
			}else if(sc.getStatus().equals("Y")) {
				sql += " WHERE B_STATUS = 'Y' ";
			}else if(sc.getStatus().equals("N")) {
				sql += " WHERE B_STATUS = 'N' ";
			}
			
			sql += " ORDER BY B_STATUS DESC , REPORT DESC, B_NO DESC) where REPORT >= 5";
			
//			if(sc.getReportNum().equals("all")) {
//				sql += " WHERE REPORT IS NOT NULL ";
//			}else if(sc.getReportNum().equals("reportDown")){
//				sql += " WHERE REPORT BETWEEN 0 AND 4 ";
//			}else {
//				sql += " WHERE REPORT >= 5 ";
//			}
			
			if(sc.getSearchName().equals("title") && !sc.getSearchValue().equals("")) {
				sql += "and B_TITLE LIKE '%' || ? || '%' ";
			}else if(sc.getSearchName().equals("content") && !sc.getSearchValue().equals("")) {
				sql += "and B_CONTENT LIKE '%' || ? || '%' ";
			}else {
				sql += "and B_TITLE IS NOT NULL ";
			}
			
			sql += ")";
			sql += "WHERE ROWN BETWEEN ? AND ?";
		}
		
		try {
			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			int paramIndex = 1;
			
			pstmt = conn.prepareStatement(sql);
			
			// 검색어가있을시
			if(sc.getStatus() != null && sc.getReportNum() != null) {
				if(sc.getSearchName().equals("title") && !sc.getSearchValue().equals("")) {
					pstmt.setString(paramIndex++, sc.getSearchValue());
				}else if(sc.getSearchName().equals("content") && !sc.getSearchValue().equals("")) {
					pstmt.setString(paramIndex++, sc.getSearchValue());
				}else {
				}
			}
			
			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Report r = new Report();
				r.setbNo(rset.getInt("B_NO"));
				r.setbTtitle(rset.getString("B_TITLE"));
				r.setbContent(rset.getString("B_CONTENT"));
				r.setEnrollDate(rset.getTimestamp("B_ENROLL_DATE"));
				r.setModfiyDate(rset.getTimestamp("B_MODIFY_DATE"));
				r.setbCount(rset.getInt("B_COUNT"));
				r.setStatus(rset.getString("B_STATUS"));
				r.setuNo(rset.getInt("U_NO"));
				r.setuNickName(rset.getString("U_NICKNAME"));
				r.setReport(rset.getInt("REPORT"));
				
				rList.add(r);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(rset);
			close(pstmt);
		}
		
		return rList;
	}

	public int removeReport(Connection conn, String[] reportChecks) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("removeReport");
		
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
