package admin.manager.model.dao;

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

import admin.manager.model.vo.Manager;
import admin.manager.model.vo.Search;
import common.PageInfo;

public class ManagerDao {
	private Properties query = new Properties();
	
	public ManagerDao() {
		String fileName = ManagerDao.class.getResource("/sql/manager/manager-query.xml").getPath();
		
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 매니저 목록 조회
	public List<Manager> managerList(Connection conn, PageInfo pi, Search sc) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("managerList");
		List<Manager> mList = new ArrayList<>();
		
		if(sc.getManagerListSearch() != null && sc.getManagerListValue() != null) {
			if(sc.getManagerListSearch().equals("id") && !sc.getManagerListValue().equals("")) {
				// 검색조건이 아이디 일시
				sql = query.getProperty("managerIdList");
				
			}else if(sc.getManagerListSearch().equals("name") && !sc.getManagerListValue().equals("")) {
				// 검색조건이 이름 일시
				sql = query.getProperty("managerNameList");
			}
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			// System.out.println(sql);
			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			int paramIndex = 1;
			
			if(sc.getManagerListSearch() != null && sc.getManagerListValue() != null) {
				if(sc.getManagerListSearch().equals("id") && !sc.getManagerListValue().equals("")) {
					// 검색조건이 아이디 일시
					pstmt.setString(paramIndex++, sc.getManagerListValue());
					
				}else if(sc.getManagerListSearch().equals("name") && !sc.getManagerListValue().equals("")) {
					// 검색조건이 이름 일시
					pstmt.setString(paramIndex++, sc.getManagerListValue());
				}
			}
			
			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Manager m = new Manager();
				m.setmNo(rset.getInt("M_NO"));
				m.setmId(rset.getString("M_ID"));
				m.setmName(rset.getString("M_NAME"));
				m.setmJobName(rset.getString("M_JOBNAME"));
				m.setmPhone(rset.getString("M_PHONE"));
				
				mList.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(rset);
			close(pstmt);
		}
		
		return mList;
	}
	
	// 게시글 총갯수 구하기(전체목록 조회)
	public int getListCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = query.getProperty("getListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
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
	
	// 게시글 총갯수 구하기(검색조건)
		public int getListCount(Connection conn, Search sc) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			int result = 0;
			String sql = query.getProperty("getListCount");
			
			if(sc.getManagerListSearch() != null && sc.getManagerListValue() != null) {
				if(sc.getManagerListSearch().equals("id") && !sc.getManagerListValue().equals("")) {
					// 검색조건이 아이디 일시
					sql = query.getProperty("getListIdCount");
				}else if(sc.getManagerListSearch().equals("name") && !sc.getManagerListValue().equals("")) {
					// 검색조건이 이름 일시
					sql = query.getProperty("getListNameCount");
				}
			}
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				if(sc.getManagerListSearch() != null && sc.getManagerListValue() != null) {
					// 검색조건이 있을시
					if(sc.getManagerListSearch().equals("id") && !sc.getManagerListValue().equals("")) {
						pstmt.setString(1, sc.getManagerListValue());
					}else if(sc.getManagerListSearch().equals("name") && !sc.getManagerListValue().equals("")) {
						pstmt.setString(1, sc.getManagerListValue());
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

	// 아이디 중복확인
	public int idCheck(Connection conn, String mId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = query.getProperty("idCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mId);
			
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

	// 관리자 계정생성
	public int createManager(Connection conn, Manager manager) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("createManager");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, getListCount(conn) + 1 ); // 최대게시글에서 + 1 하여 게시글번호 부여
			pstmt.setString(2, manager.getmId());
			pstmt.setString(3, manager.getmPassword());
			pstmt.setString(4, manager.getmName());
			pstmt.setString(5, manager.getmPhone());
		
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 관리자 번호로 조회
	public Manager classManager(Connection conn, int mNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Manager manager = null;
		String sql = query.getProperty("classManager");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, mNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				manager = new Manager(rset.getInt("M_NO"),
							          rset.getString("M_ID"),
							          rset.getString("M_NAME"),
							          rset.getString("M_PHONE"),
							          rset.getString("M_JOBCODE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return manager;
	}
	
	// 관리자 계급 변경
	public int classChangeManager(Connection conn, int mNo, String mJobcode) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("classChangeManager");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mJobcode);
			pstmt.setString(2, mJobcode);
			pstmt.setInt(3, mNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	// 매니저 로그인관리
	public Manager managerLogin(Connection conn, String mId, String mPassword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("managerLogin");
		Manager mLogin = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, mPassword);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				mLogin = new Manager();
				mLogin.setmNo(rset.getInt("M_NO"));
				mLogin.setmId(rset.getString("M_ID"));
				mLogin.setmPassword(rset.getString("M_PASSWORD"));
				mLogin.setmName(rset.getString("M_NAME"));
				mLogin.setmNick(rset.getString("M_NICK"));
				mLogin.setmPhone(rset.getString("M_PHONE"));
				mLogin.setmJobcode(rset.getString("M_JOBCODE"));
				mLogin.setmJobName(rset.getString("M_JOBNAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return mLogin;
	}

	// 개인정보 변경
	public int infoChangeManager(Connection conn, Manager m, String mPwdNew) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("infoChangeManager");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mPwdNew);
			pstmt.setString(2, m.getmPhone());
			pstmt.setInt(3, m.getmNo());
			pstmt.setString(4, m.getmPassword());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
