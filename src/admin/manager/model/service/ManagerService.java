package admin.manager.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import admin.manager.model.dao.ManagerDao;
import admin.manager.model.vo.Manager;
import admin.manager.model.vo.Search;
import common.PageInfo;

public class ManagerService {
	private ManagerDao DAO = null;
	
	public ManagerService() {
		DAO = new ManagerDao();
	}
	
	// 매니저 로그인 관리
	public Manager ManagerLogin(String mId, String mPassword) {
		Connection conn = getConnection();
		
		Manager managerLogin = DAO.managerLogin(conn, mId, mPassword);
		
		close(conn);
		
		return managerLogin;
	}

	// 매니저 목록 조회
	public Map<String, Object> managerList(int page, Search sc) {
		Connection conn = getConnection();
		
		// 게시글 총갯수 구하기
		int listCount = new ManagerDao().getListCount(conn, sc);
		// System.out.println("listCount : " + listCount);
		
		// 페이징 객체 만들기
		PageInfo pi = new PageInfo(page, listCount, 10, 10);
		
		// 리스트 목록 만들기
		List<Manager> mList = DAO.managerList(conn, pi, sc);
		
		// 페이징 객체, 리스트목록 저장
		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put("pi", pi);
		returnMap.put("mList", mList);
		
		return returnMap;
	}

	// 아이디 중복확인
	public int idCheck(String mId) {
		Connection conn = getConnection();
		
		int result = DAO.idCheck(conn, mId);
		
		close(conn);
		
		return result;
	}
	
	// 관리자 계정생성
	public int createManager(Manager manager) {
		Connection conn = getConnection();
		
		int result = DAO.createManager(conn, manager);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	// 관리자 번호로 조회
	public Manager classManager(int mNo) {
		Connection conn = getConnection();
		
		Manager manager = DAO.classManager(conn, mNo);
		
		close(conn);
		
		return manager;
	}

	// 관리자 계급 변경
	public int classChangeManager(int mNo, String mJobcode) {
		Connection conn = getConnection();
		
		int result = DAO.classChangeManager(conn, mNo, mJobcode);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	// 개인정보 변경
	public Manager infoChangeManager(Manager m, String mPwdNew) {
		Connection conn = getConnection();
		
		int result = DAO.infoChangeManager(conn, m, mPwdNew);
		Manager manager = null;
		if(result > 0) {
			commit(conn);
			
			manager = DAO.managerLogin(conn, m.getmId(), mPwdNew);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return manager;
	}

}
