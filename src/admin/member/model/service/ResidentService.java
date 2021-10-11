package admin.member.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import admin.member.model.dao.ResidentDao;
import admin.member.model.vo.Resident;
import admin.member.model.vo.Search;
import common.PageInfo;

public class ResidentService {
	private ResidentDao rd = null;
	
	public ResidentService() {
		rd = new ResidentDao();
	}
	
	
	
	// 입주자 명부 목록
	public Map<String, Object> residentList(int page, Search sc) {
		Connection conn = getConnection();
		
		// 전체 게시글 구하기
		int listCount = rd.listCount(conn, sc);
		// System.out.println("listCount : " + listCount);
		
		// 페이징 객체 만들기
		PageInfo pi = new PageInfo(page, listCount, 10, 10);
		// System.out.println(pi);
		
		// 리스트 목록 만들기
		List<Resident> rList = rd.residentAllList(conn, pi, sc);
		// System.out.println(rList);
		
		close(conn);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("pi", pi);
		returnMap.put("rList", rList);
		
		return returnMap;
		
	}

	// 동, 호 로 목록 조회
	public List<Resident> oneSelectResident(Resident r) {
		Connection conn = getConnection();
		
		List<Resident> rList = rd.oneSelectResident(conn, r);
		
		close(conn);
		
		return rList;
	}
	
	// 입주자 명부 변경
	public int changeResident(List<Resident> rList) {
		Connection conn = getConnection();
		
		int result = rd.changeResident(conn, rList);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	// 세대 정보 삭제
	public int deleteResident(List<Resident> rList) {
		Connection conn = getConnection();
		
		int result = rd.deleteResident(conn, rList);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		return result;
	}

	// 회원가입 정보
	public Resident userInfoSelect(int rNo) {
		Connection conn = getConnection();
		
		Resident r = rd.userInfoSelect(conn, rNo);
		
		close(conn);
		
		return r;
	}
	
	
}
