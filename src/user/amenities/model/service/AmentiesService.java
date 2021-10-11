package user.amenities.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import user.amenities.model.dao.AmentiesDao;

import user.amenities.model.vo.Facility;
import user.amenities.model.vo.PageInfo;
import user.amenities.model.vo.Search;

public class AmentiesService {
	private AmentiesDao ad = new AmentiesDao();
	
	// 독서실 예약신청
	public int srResInsert(String dayInput, int seatNumber, int uNo) {
		Connection conn = getConnection();
		
		int result = new AmentiesDao().srResInsert(conn, dayInput, seatNumber, uNo);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	// 독서실 예약된 날짜 가져오기
	public List<String> srDateList(int uNo) {
		Connection conn = getConnection();
		
		List<String> srDateList = new AmentiesDao().srDateList(conn, uNo);
		
		close(conn);
		
		return srDateList;
	}
	
	
	public Map<String, Object> selectlist(int page, Search s) {
		Connection conn = getConnection();
		
		// 1. 게시글 총 개수 구하기
		int listCount = ad.getListCount(conn, s);
		
		// 2. PageInfo 객체 만들기
		PageInfo pi = new PageInfo(page, listCount, 10, 10);
		
		// 3. 페이징 처리가 된 게시글 목록 조회
		List<Facility> studyRoomList = ad.selectList(conn, pi, s);
		
		// 리턴용 Map 선언
		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put("pi", pi);
		returnMap.put("studyRoomList", studyRoomList);
		
		return returnMap;
	}
	
	// 멀티코트 예약신청
		public int mcResInsert(String dayInput, int courtNumber, int uNo) {
			Connection conn = getConnection();
			
			int result = new AmentiesDao().mcResInsert(conn, dayInput, courtNumber, uNo);
			
			if(result > 0) {
				commit(conn);
			}else {
				rollback(conn);
			}
			
			close(conn);
			
			return result;
		}
	
		// 멀티코트 예약된 날짜 가져오기
		public List<String> mcDateList(int uNo) {
			Connection conn = getConnection();
			
			List<String> mcDateList = new AmentiesDao().mcDateList(conn, uNo);
			
			close(conn);
			
			return mcDateList;
		}
		
		public Map<String, Object> selectMultilist(int page, Search s) {
			Connection conn = getConnection();
			
			// 1. 게시글 총 개수 구하기
			int listCount = ad.getListCount(conn, s);
			
			// 2. PageInfo 객체 만들기
			PageInfo pi = new PageInfo(page, listCount, 10, 10);
			
			// 3. 페이징 처리가 된 게시글 목록 조회
			List<Facility> multiCourtList = ad.selectMultiList(conn, pi, s);
			
			// 리턴용 Map 선언
			Map<String, Object> returnMap = new HashMap<>();
			
			returnMap.put("pi", pi);
			returnMap.put("multiCourtList", multiCourtList);
			
			return returnMap;
		}

}
