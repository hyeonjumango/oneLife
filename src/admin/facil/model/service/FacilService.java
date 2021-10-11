package admin.facil.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import admin.facil.model.dao.FacilDao;
import admin.facil.model.vo.Facil;
import admin.facil.model.vo.Search;
import common.PageInfo;

public class FacilService {
	private FacilDao fd = null;
	
	public FacilService() {
		fd = new FacilDao();
	}

	// 부대시설 목록 조회
	public Map<String, Object> selectListFacil(int page, Search sc) {
		Connection conn = getConnection();
		
		// 게시글 전체 목록 조회
		int listCount = fd.getListCount(conn, sc);
		// System.out.println("listCount : " + listCount);
		
		// 페이징 객체 만들기
		PageInfo pi = new PageInfo(page, listCount, 10, 10);
		// System.out.println(pi);
		
		// 목록 가져오기
		List<Facil> fList = fd.selectListFacil(conn, pi, sc);
		// System.out.println(fList);
		
		close(conn);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("pi", pi);
		returnMap.put("fList", fList);
		
		return returnMap;
	}

	// 게시글 삭제
	public int removeFacil(String[] facilCheck) {
		Connection conn = getConnection();
		
		int result = fd.removeFacil(conn, facilCheck);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	// 독서실 예약현황 자리표
	public List<Facil> librarySelectList(String day) {
		Connection conn = getConnection();
		
		List<Facil> fList = fd.librarySelectList(conn, day);
		
		close(conn);
		
		return fList;
	}
	
	// 독서실 예약 카운트
	public int libraryDayCount(String day, int uNo) {
		Connection conn = getConnection();
		
		int result = fd.libraryDayCount(conn, day, uNo);
		
		close(conn);
		
		if(result > 0) {
			return -1;
		}else {
			return 0;
		}
		
	}


	// 독서실 멤버 조회
	public Facil libraryInfo(int fcSeatNo, String dayInput) {
		Connection conn = getConnection();
		
		Facil facil = fd.libraryInfo(conn, fcSeatNo, dayInput);
		
		close(conn);
		
		return facil;
	}

	// 멀티코트장 예약현황 자리표
	public List<Facil> multicourtSelectList(String day) {
		Connection conn = getConnection();
		
		List<Facil> fList = fd.multicourtSelectList(conn, day);
		
		close(conn);
		
		return fList;
	}

	// 멀티코트장 멤버 조회
	public Facil multiInfo(int fcNo, String dayInput) {
		Connection conn = getConnection();
		
		Facil f = fd.multiInfo(conn, fcNo, dayInput);
		
		close(conn);
		
		return f;
	}

		
	

}
