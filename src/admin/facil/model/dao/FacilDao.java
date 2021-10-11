package admin.facil.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import admin.facil.model.vo.Facil;
import admin.facil.model.vo.Search;
import common.PageInfo;

import static common.JDBCTemplate.*;


public class FacilDao {
	
private Properties query = new Properties();
	
	public FacilDao() {
		String fileName = FacilDao.class.getResource("/sql/facil/facil-query.xml").getPath();
		
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 게시글 갯수 조회
	public int getListCount(Connection conn, Search sc) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = query.getProperty("getListCount");
		
		// 검색결과 가 있을시
		if(sc.getFacilName() != null && sc.getFacilType() != null) {
			// 예약 시설
			if(sc.getFacilName().equals("all")) {
				sql += " WHERE FC_NAME IS NOT NULL ";
			}else {
				sql += " WHERE FC_NAME = ? ";
			}
			
			// 예약 타입
			if(sc.getFacilType().equals("all")) {
				sql += " AND FC_SEAT_TYPE IS NOT NULL ";
			}else {
				sql += "AND FC_SEAT_TYPE = ? ";
			}
			
			// 예약 상태
			if(sc.getFacilStatus().equals("all")) {
				sql += "AND FC_STATUS IS NOT NULL ";
			}else if(sc.getFacilStatus().equals("예약취소")) {
				sql += "AND FC_STATUS = ? ";
			}else if(sc.getFacilStatus().equals("사용전")) {
				sql += "AND FC_STATUS = ? ";
			}else if(sc.getFacilStatus().equals("사용중")) {
				sql += "AND FC_STATUS = ? ";
			}else if(sc.getFacilStatus().equals("사용완료")) {
				sql += "AND FC_STATUS = ? ";
			} else {
				sql += "AND FC_STATUS IS NOT NULL ";
			}
			
			// 예약일자
			if(sc.getAllDay() != null && sc.getAllDay().equals("on")) {
				sql += " AND FC_START IS NOT NULL ";
			}else {
				sql += " AND TO_CHAR(FC_START, 'YYYY-MM-DD') = ? ";
			}
			
			// 검색조건
			if(sc.getSearchName().equals("id") && !sc.getSearchValue().equals("")) {
				sql += " AND U_ID LIKE '%' || ? || '%' ";
			}else if(sc.getSearchName().equals("name") && !sc.getSearchValue().equals("")) {
				sql += " AND R_NAME LIKE '%' || ? || '%' ";
			}else if(sc.getSearchName().equals("phone") && !sc.getSearchValue().equals("")) {
				sql += " AND U_PHONE LIKE '%' || ? || '%' ";
			}
		}
		
		try {
			int paramIndex = 1;
			pstmt = conn.prepareStatement(sql);
			
			// 검색결과 가 있을시
			if(sc.getFacilName() != null && sc.getFacilType() != null) {
				// 예약 시설
				if(sc.getFacilName().equals("all")) {
				}else {
					pstmt.setString(paramIndex++, sc.getFacilName());
				}
				
				// 예약 타입
				if(sc.getFacilType().equals("all")) {
				}else {
					pstmt.setString(paramIndex++, sc.getFacilType());
				}
				
				// 예약 상태
				if(sc.getFacilStatus().equals("all")) {
				}else if(sc.getFacilStatus().equals("예약취소")) {
					pstmt.setString(paramIndex++, sc.getFacilStatus());
				}else if(sc.getFacilStatus().equals("사용전")) {
					pstmt.setString(paramIndex++, sc.getFacilStatus());
				}else if(sc.getFacilStatus().equals("사용중")) {
					pstmt.setString(paramIndex++, sc.getFacilStatus());
				}else if(sc.getFacilStatus().equals("사용완료")) {
					pstmt.setString(paramIndex++, sc.getFacilStatus());
				}
				
				
				// 예약일자
				if(sc.getAllDay() != null && sc.getAllDay().equals("on")) {
				}else {
					pstmt.setString(paramIndex++, sc.getFacilDay());
				}
				
				// 검색조건
				if(sc.getSearchName().equals("id") && !sc.getSearchValue().equals("")) {
					pstmt.setString(paramIndex++, sc.getSearchValue());
				}else if(sc.getSearchName().equals("name") && !sc.getSearchValue().equals("")) {
					pstmt.setString(paramIndex++, sc.getSearchValue());
				}else if(sc.getSearchName().equals("phone") && !sc.getSearchValue().equals("")) {
					pstmt.setString(paramIndex++, sc.getSearchValue());
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
	
	// 부대시설 목록 조회
	public List<Facil> selectListFacil(Connection conn, PageInfo pi, Search sc) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("selectListFacil");
		List<Facil> fList = new ArrayList<>();
		
		// 검색결과 가 있을시
		if(sc.getFacilName() != null && sc.getFacilType() != null) {
			sql = query.getProperty("selectSearchListFacil");
			
			// 예약 시설
			if(sc.getFacilName().equals("all")) {
				sql += " WHERE FC_NAME IS NOT NULL ";
			}else {
				sql += " WHERE FC_NAME = ? ";
			}
			
			// 예약 타입
			if(sc.getFacilType().equals("all")) {
				sql += " AND FC_SEAT_TYPE IS NOT NULL ";
			}else {
				sql += "AND FC_SEAT_TYPE = ? ";
			}
			
			// 예약 상태
			if(sc.getFacilStatus().equals("all")) {
				sql += "AND FC_STATUS IS NOT NULL ";
			}else if(sc.getFacilStatus().equals("예약취소")) {
				sql += "AND FC_STATUS = ? ";
			}else if(sc.getFacilStatus().equals("사용전")) {
				sql += "AND FC_STATUS = ? ";
			}else if(sc.getFacilStatus().equals("사용중")) {
				sql += "AND FC_STATUS = ? ";
			}else if(sc.getFacilStatus().equals("사용완료")) {
				sql += "AND FC_STATUS = ? ";
			} else {
				sql += "AND FC_STATUS IS NOT NULL ";
			}
			
			// 예약일자
			if(sc.getAllDay() != null && sc.getAllDay().equals("on")) {
				sql += " AND FC_START IS NOT NULL ";
			}else {
				sql += " AND TO_CHAR(FC_START, 'YYYY-MM-DD') = ? ";
			}
			
			// 검색조건
			if(sc.getSearchName().equals("id") && !sc.getSearchValue().equals("")) {
				sql += " AND U_ID LIKE '%' || ? || '%' ";
			}else if(sc.getSearchName().equals("name") && !sc.getSearchValue().equals("")) {
				sql += " AND R_NAME LIKE '%' || ? || '%' ";
			}else if(sc.getSearchName().equals("phone") && !sc.getSearchValue().equals("")) {
				sql += " AND U_PHONE LIKE '%' || ? || '%' ";
			}
			
			sql += " ) WHERE ROWN BETWEEN ? AND ?";
		}

		try {
			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			int paramIndex = 1;
			
			pstmt = conn.prepareStatement(sql);
			
			// 검색결과 가 있을시
			if(sc.getFacilName() != null && sc.getFacilType() != null) {
				// 예약 시설
				if(sc.getFacilName().equals("all")) {
				}else {
					pstmt.setString(paramIndex++, sc.getFacilName());
				}
				
				// 예약 타입
				if(sc.getFacilType().equals("all")) {
				}else {
					pstmt.setString(paramIndex++, sc.getFacilType());
				}
				
				// 예약 상태
				if(sc.getFacilStatus().equals("all")) {
				}else if(sc.getFacilStatus().equals("예약취소")) {
					pstmt.setString(paramIndex++, sc.getFacilStatus());
				}else if(sc.getFacilStatus().equals("사용전")) {
					pstmt.setString(paramIndex++, sc.getFacilStatus());
				}else if(sc.getFacilStatus().equals("사용중")) {
					pstmt.setString(paramIndex++, sc.getFacilStatus());
				}else if(sc.getFacilStatus().equals("사용완료")) {
					pstmt.setString(paramIndex++, sc.getFacilStatus());
				}
				
				// 예약일자
				if(sc.getAllDay() != null && sc.getAllDay().equals("on")) {
				}else {
					pstmt.setString(paramIndex++, sc.getFacilDay());
				}
				
				// 검색조건
				if(sc.getSearchName().equals("id") && !sc.getSearchValue().equals("")) {
					pstmt.setString(paramIndex++, sc.getSearchValue());
				}else if(sc.getSearchName().equals("name") && !sc.getSearchValue().equals("")) {
					pstmt.setString(paramIndex++, sc.getSearchValue());
				}else if(sc.getSearchName().equals("phone") && !sc.getSearchValue().equals("")) {
					pstmt.setString(paramIndex++, sc.getSearchValue());
				}
				
			}
			
			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Facil f = new Facil();
				f.setFcNo(rset.getInt("FC_NO"));
				f.setFcName(rset.getString("FC_NAME"));
				f.setFcSeatNo(rset.getInt("FC_SEAT_NO"));
				f.setFcSeatType(rset.getString("FC_SEAT_TYPE"));
				f.setuId(rset.getString("U_ID"));
				f.setrName(rset.getString("R_NAME"));
				f.setuPhone(rset.getString("U_PHONE"));
				f.setFaDate(rset.getTimestamp("FC_DATE"));
				f.setFcStart(rset.getTimestamp("FC_START"));
				f.setFcEnd(rset.getTimestamp("FC_END"));
				f.setFcStatus(rset.getString("FC_STATUS"));
				
				// System.out.println(f);
				
				fList.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return fList;
	}
	
	// 게시글 삭제
	public int removeFacil(Connection conn, String[] facilCheck) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("removeFacil");
		
		for(int i = 0; i < facilCheck.length; i++) {
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, Integer.parseInt(facilCheck[i]));
				
				result += pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
		}
		
		return result;
	}
	
	// 독서실 예약현황 자리표
	public List<Facil> librarySelectList(Connection conn, String day) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("librarySelectList");
		List<Facil> fList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, day);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Facil f = new Facil();
				f.setFcNo(rset.getInt("FC_NO"));
				f.setFcName(rset.getString("FC_NAME"));
				f.setFcSeatNo(rset.getInt("FC_SEAT_NO"));
				f.setFcSeatType(rset.getString("FC_SEAT_TYPE"));
				f.setuId(rset.getString("U_ID"));
				f.setrName(rset.getString("R_NAME"));
				f.setuPhone(rset.getString("U_PHONE"));
				f.setFaDate(rset.getTimestamp("FC_DATE"));
				f.setFcStart(rset.getTimestamp("FC_START"));
				f.setFcEnd(rset.getTimestamp("FC_END"));
				f.setFcStatus(rset.getString("FC_STATUS"));
				
				fList.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return fList;
	}
	
	// 독서실 예약 카운트
	public int libraryDayCount(Connection conn, String day, int uNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("libraryDayCount");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, day);
			pstmt.setInt(2, uNo);
			
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
	
	
	
	// 독서실 멤버조회
	public Facil libraryInfo(Connection conn, int fcSeatNo, String dayInput) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("libraryInfo");
		Facil f = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dayInput);
			pstmt.setInt(2, fcSeatNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				f = new Facil();
				f.setFcNo(rset.getInt("FC_NO"));
				f.setFcName(rset.getString("FC_NAME"));
				f.setFcSeatNo(rset.getInt("FC_SEAT_NO"));
				f.setFcSeatType(rset.getString("FC_SEAT_TYPE"));
				f.setuId(rset.getString("U_ID"));
				f.setrName(rset.getString("R_NAME"));
				f.setuPhone(rset.getString("U_PHONE"));
				f.setFaDate(rset.getTimestamp("FC_DATE"));
				f.setFcStart(rset.getTimestamp("FC_START"));
				f.setFcEnd(rset.getTimestamp("FC_END"));
				f.setFcStatus(rset.getString("FC_STATUS"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return f;
	}

	// 멀티코트장 예약현황 자리표
	public List<Facil> multicourtSelectList(Connection conn, String day) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("multicourtSelectList");
		List<Facil> fList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, day);
			
			/*
			 * if(time != null) { pstmt.setString(2, time); }else { pstmt.setString(2,
			 * "09"); }
			 */
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Facil f = new Facil();
				f.setFcNo(rset.getInt("FC_NO"));
				f.setFcName(rset.getString("FC_NAME"));
				f.setFcSeatNo(rset.getInt("FC_SEAT_NO"));
				f.setFcSeatType(rset.getString("FC_SEAT_TYPE"));
				f.setuId(rset.getString("U_ID"));
				f.setrName(rset.getString("R_NAME"));
				f.setuPhone(rset.getString("U_PHONE"));
				f.setFaDate(rset.getTimestamp("FC_DATE"));
				f.setFcStart(rset.getTimestamp("FC_START"));
				f.setFcEnd(rset.getTimestamp("FC_END"));
				f.setFcStatus(rset.getString("FC_STATUS"));
				
				fList.add(f);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return fList;
	}
	
	// 멀티 코트장 멤버 조회
	public Facil multiInfo(Connection conn, int fcNo, String dayInput) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Facil f = null;
		String sql = query.getProperty("multiInfo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dayInput);
			// pstmt.setString(2, time);
			pstmt.setInt(2, fcNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				f = new Facil();
				f.setFcNo(rset.getInt("FC_NO"));
				f.setFcName(rset.getString("FC_NAME"));
				f.setFcSeatNo(rset.getInt("FC_SEAT_NO"));
				f.setFcSeatType(rset.getString("FC_SEAT_TYPE"));
				f.setuId(rset.getString("U_ID"));
				f.setrName(rset.getString("R_NAME"));
				f.setuPhone(rset.getString("U_PHONE"));
				f.setFaDate(rset.getTimestamp("FC_DATE"));
				f.setFcStart(rset.getTimestamp("FC_START"));
				f.setFcEnd(rset.getTimestamp("FC_END"));
				f.setFcStatus(rset.getString("FC_STATUS"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return f;
	}

	

}

