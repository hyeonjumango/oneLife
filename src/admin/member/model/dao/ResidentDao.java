package admin.member.model.dao;

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

import admin.member.model.vo.Resident;
import admin.member.model.vo.Search;
import common.PageInfo;

public class ResidentDao {
	private Properties query = new Properties();
	
	public ResidentDao() {
		String fileName = ResidentDao.class.getResource("/sql/resident/resident-query.xml").getPath();
		
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 전체 게시글 갯수 구하기
	public int listCount(Connection conn, Search sc) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = query.getProperty("listCount");	// 기본 목록조회일시
		
		// 검색조건이 있을시
		if(sc != null) {
			sql = "SELECT "
			    + "COUNT(*) AS COUNT "
			    + "FROM INFO_RESIDENT ";
			
			// 동
			if(sc.getrDong().equals("all")) {
				sql += "WHERE R_DONG IS NOT NULL ";
			}else {
				sql += "WHERE R_DONG = ? ";
			}
			
			// 호
			if(sc.getrHo().equals("")) {
				sql += "AND R_HO IS NOT NULL ";
			}else {
				sql += "AND R_HO = ? ";
			}
			
			// 거주 타입
			if(sc.getrType().equals("all")) {
				sql += "AND R_TYPE IS NOT NULL ";
			}else {
				sql += "AND R_TYPE = ? ";
			}
			
			// 가입여부
			if(sc.getrStatus().equals("all")) {
				sql += "AND R_STATUS IS NOT NULL ";
			}else {
				sql += "AND R_STATUS = ? ";
			}
			
			if(sc.getSearchName().equals("name") && !sc.getSearchValue().equals("")) {
				sql += "AND R_NAME LIKE '%' || ? || '%' ";
			}else if(sc.getSearchName().equals("email") && !sc.getSearchValue().equals("")) {
				sql += "AND R_EMAIL LIKE '%' || ? || '%' ";
			}else {
				sql += "AND R_EMAIL IS NOT NULL ";
			}
			
			// System.out.println(sql);
			
			
		}
		
		try {
			int paramIndex = 1;
			
			pstmt = conn.prepareStatement(sql);
			
			if(sc != null) {
				// 동
				if(sc.getrDong().equals("all")) {
				}else {
					pstmt.setInt(paramIndex++, Integer.parseInt(sc.getrDong()));
				}
				
				// 호
				if(sc.getrHo().equals("")) {
				}else {
					pstmt.setInt(paramIndex++, Integer.parseInt(sc.getrHo()));
				}
				
				// 거주 타입
				if(sc.getrType().equals("all")) {
				}else {
					pstmt.setString(paramIndex++, sc.getrType());
				}
				
				// 가입여부
				if(sc.getrStatus().equals("all")) {
				}else {
					pstmt.setString(paramIndex++, sc.getrStatus());
				}
				
				// 검색 조건
				if(sc.getSearchName().equals("name") && !sc.getSearchValue().equals("")) {
					pstmt.setString(paramIndex++, sc.getSearchValue());
				}else if(sc.getSearchName().equals("email") && !sc.getSearchValue().equals("")) {
					pstmt.setString(paramIndex++, sc.getSearchValue());
				}else {
					
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

	// 전체 게시글 조회
	public List<Resident> residentAllList(Connection conn, PageInfo pi, Search sc) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Resident> rList = new ArrayList<>();
		String sql = query.getProperty("residentAllList");
		
		// 검색조건이 있을시
				if(sc != null) {
					sql = "SELECT "
					    + " * "
					    + "FROM(SELECT "
					    + "ROWNUM ROWN "
					    + ", R_NO "
					    + ", R_DONG "
					    + ", R_HO "
					    + ", R_NAME "
					    + ", R_EMAIL "
					    + ", R_TYPE "
					    + ", R_STATUS "
					    + ", R_DATE "
					    + "FROM(SELECT "
					    + "  R_NO "
					    + ", R_DONG "
					    + ", R_HO "
					    + ", R_NAME "
					    + ", R_EMAIL "
					    + ", R_TYPE "
					    + ", R_STATUS "
					    + ", R_DATE "
					    + "FROM INFO_RESIDENT ";
					// 동
					if(sc.getrDong().equals("all")) {
						sql += "WHERE R_DONG IS NOT NULL ";
					}else {
						sql += "WHERE R_DONG = ? ";
					}
					
					// 호
					if(sc.getrHo().equals("")) {
						sql += "AND R_HO IS NOT NULL ";
					}else {
						sql += "AND R_HO = ? ";
					}
					
					// 거주 타입
					if(sc.getrType().equals("all")) {
						sql += "AND R_TYPE IS NOT NULL ";
					}else {
						sql += "AND R_TYPE = ? ";
					}
					
					// 가입여부
					if(sc.getrStatus().equals("all")) {
						sql += "AND R_STATUS IS NOT NULL ";
					}else {
						sql += "AND R_STATUS = ? ";
					}
					
					if(sc.getSearchName().equals("name") && !sc.getSearchValue().equals("")) {
						sql += "AND R_NAME LIKE '%' || ? || '%' ";
					}else if(sc.getSearchName().equals("email") && !sc.getSearchValue().equals("")) {
						sql += "AND R_EMAIL LIKE '%' || ? || '%' ";
					}else {
						sql += "AND R_EMAIL IS NOT NULL ";
					}
					
					sql += "ORDER BY R_DONG, R_HO, R_TYPE DESC, R_DATE)) "
					     + "WHERE ROWN BETWEEN ? AND ?";
					
					// System.out.println(sql);
				}
		
		try {
			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			int paramIndex = 1;
			
			
			pstmt = conn.prepareStatement(sql);
			
			if(sc != null) {
				// 동
				if(sc.getrDong().equals("all")) {
				}else {
					pstmt.setInt(paramIndex++, Integer.parseInt(sc.getrDong()));
				}
				
				// 호
				if(sc.getrHo().equals("")) {
				}else {
					pstmt.setInt(paramIndex++, Integer.parseInt(sc.getrHo()));
				}
				
				// 거주 타입
				if(sc.getrType().equals("all")) {
				}else {
					pstmt.setString(paramIndex++, sc.getrType());
				}
				
				// 가입여부
				if(sc.getrStatus().equals("all")) {
				}else {
					pstmt.setString(paramIndex++, sc.getrStatus());
				}
				
				// 검색 조건
				if(sc.getSearchName().equals("name") && !sc.getSearchValue().equals("")) {
					pstmt.setString(paramIndex++, sc.getSearchValue());
				}else if(sc.getSearchName().equals("email") && !sc.getSearchValue().equals("")) {
					pstmt.setString(paramIndex++, sc.getSearchValue());
				}else {
					
				}
			}
			
			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Resident resident = new Resident();
				resident.setrNo(rset.getInt("R_NO"));
				resident.setrDong(rset.getInt("R_DONG"));
				resident.setrHo(rset.getInt("R_HO"));
				resident.setrName(rset.getString("R_NAME"));
				resident.setrEmail(rset.getString("R_EMAIL"));
				resident.setrType(rset.getString("R_TYPE"));
				resident.setrStatus(rset.getString("R_STATUS"));
				resident.setrDate(rset.getTimestamp("R_DATE"));
				
				rList.add(resident);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return rList;
	}

	// 동, 호 로 목록 조회
	public List<Resident> oneSelectResident(Connection conn, Resident r) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("oneSelectResident");
		List<Resident> rList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, r.getrDong());
			pstmt.setInt(2, r.getrHo());
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Resident rAdd = new Resident();
				rAdd.setrNo(rset.getInt("R_NO"));
				rAdd.setrDong(rset.getInt("R_DONG"));
				rAdd.setrHo(rset.getInt("R_HO"));
				rAdd.setrName(rset.getString("R_NAME"));
				rAdd.setrEmail(rset.getString("R_EMAIL"));
				rAdd.setrType(rset.getString("R_TYPE"));
				rAdd.setrStatus(rset.getString("R_STATUS"));
				rAdd.setrDate(rset.getDate("R_DATE"));
				
				rList.add(rAdd);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return rList;
	}
	
	// 게시글 최대 번호
	public int maxListCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = query.getProperty("maxListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("MAX");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return result;
	}
	
	// 동,호,이름,이메일 검색
	public int oneSelect(Connection conn, Resident r) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = query.getProperty("oneSelect");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, r.getrDong());
			pstmt.setInt(2, r.getrHo());
			pstmt.setString(3, r.getrName());
			
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

	// 입주자 명부 변경
	public int changeResident(Connection conn, List<Resident> rList) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		
		for(int i = 0; i < rList.size(); i++) {
				int paramIndex = 1;
				int number = oneSelect(conn, rList.get(i));
				
				if(number > 0) {
					// System.out.println(i + " : 존재함 업데이트해야함");
					sql = query.getProperty("oneSelectUpdate");
				}else {
					// System.out.println(i + " : 없음 신규추가");
					sql = query.getProperty("oneSelectInsert");
				}
				
				try {
					pstmt = conn.prepareStatement(sql);
					
					if(number > 0) {
						pstmt.setString(paramIndex++, rList.get(i).getrName());
						pstmt.setString(paramIndex++, rList.get(i).getrEmail());
						pstmt.setString(paramIndex++, rList.get(i).getrType());
						pstmt.setInt(paramIndex++, rList.get(i).getrDong());
						pstmt.setInt(paramIndex++, rList.get(i).getrHo());
						pstmt.setString(paramIndex++, rList.get(i).getrName());
						
					}else {
						pstmt.setInt(paramIndex++, maxListCount(conn) + 1);
						pstmt.setInt(paramIndex++, rList.get(i).getrDong());
						pstmt.setInt(paramIndex++, rList.get(i).getrHo());
						pstmt.setString(paramIndex++, rList.get(i).getrName());
						pstmt.setString(paramIndex++, rList.get(i).getrEmail());
						pstmt.setString(paramIndex++, rList.get(i).getrType());
					}
					
					result += pstmt.executeUpdate();
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					close(pstmt);
				}
		}
		
		return result;
	}

	// 세대 정보 삭제
	public int deleteResident(Connection conn, List<Resident> rList) {
		PreparedStatement pstmt = null;
		int result = 0;
		int paramIndex = 1;
		String sql = query.getProperty("deleteResident");
		
		for(int i = 0; i < rList.size(); i++) {
			sql += " AND R_NAME != ? ";
			sql += " AND R_EMAIL != ? ";
		}
		
		// System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(paramIndex++, rList.get(0).getrDong());
			pstmt.setInt(paramIndex++, rList.get(0).getrHo());
			
			for(int i = 0; i < rList.size(); i++) {
				pstmt.setString(paramIndex++, rList.get(i).getrName());
				pstmt.setString(paramIndex++, rList.get(i).getrEmail());
			}
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 회원가입 정보
	public Resident userInfoSelect(Connection conn, int rNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("userInfoSelect");
		Resident r = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, rNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				r = new Resident();
				r.setuNo(rset.getInt("U_NO"));
				r.setuId(rset.getString("U_ID"));
				r.setuNickName(rset.getString("U_NICKNAME"));
				r.setuPhone(rset.getString("U_PHONE"));
				r.setrDate(rset.getDate("REG_DATE"));
				r.setrName(rset.getString("R_NAME"));
				r.setrDong(rset.getInt("R_DONG"));
				r.setrHo(rset.getInt("R_HO"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return r;
	}

	

}
