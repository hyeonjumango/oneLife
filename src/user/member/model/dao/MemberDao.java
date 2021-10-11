package user.member.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import user.member.model.vo.Member;

public class MemberDao {
private Properties query = new Properties();
	
	public MemberDao() {
		String fileName = MemberDao.class.getResource("/sql/member/member-query.xml").getPath()	;
		
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Member loginMember(Connection conn, String id, String pwd) {
		Member loginUser = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				loginUser = new Member(rset.getInt("u_no"),
						rset.getString("u_id"),
						rset.getString("u_nickname"),
						rset.getString("u_pw"),
						rset.getString("u_phone"),
						rset.getDate("reg_date"),
						rset.getDate("mod_date"),
						rset.getString("u_status"),
						rset.getInt("r_dong"),
						rset.getInt("r_ho"),
						rset.getString("r_name"),
						rset.getString("r_email"),
						rset.getString("r_type"),
						rset.getString("r_status"),
						rset.getDate("r_date"),
						rset.getInt("r_no"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}	
		
		return loginUser;
	}

	// 2. 회원가입 기능
	public int insertMember(Connection conn, Member mem) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = query.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mem.getU_ID());
			pstmt.setString(2, mem.getU_NICKNAME());
			pstmt.setString(3, mem.getU_PW());
			pstmt.setString(4, mem.getU_PHONE());
			pstmt.setInt(5, mem.getR_NO());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int checkR(Connection conn, String name, String email) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null; 
		String sql = query.getProperty("checkR");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return result;
	}

	// 아이디 중복 체크 
	public int idCheck(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = query.getProperty("idCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return result;
	}

	// 닉네임 중복확인
	public int nickCheck(Connection conn, String nickName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = query.getProperty("nickCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickName);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return result;
	}

	// 주민 회원가입 상태 바꾸기
	public int changeStatus(Connection conn, int rno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = query.getProperty("changeStatus");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, rno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 3. 회원 정보 수정 기능
	public int updateMember(Connection conn, Member mem) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = query.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mem.getU_NICKNAME());
			pstmt.setString(2, mem.getU_PHONE());
			pstmt.setInt(3, mem.getU_NO());
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// userNo로 member 객체 조회
	public Member selectMember(Connection conn, int u_NO) {
		Member mem = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("selectMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, u_NO);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {  
				mem = new Member(rset.getInt("u_no"),
						rset.getString("u_id"),
						rset.getString("u_nickname"),
						rset.getString("u_pw"),
						rset.getString("u_phone"),
						rset.getDate("reg_date"),
						rset.getDate("mod_date"),
						rset.getString("u_status"),
						rset.getInt("r_dong"),
						rset.getInt("r_ho"),
						rset.getString("r_name"),
						rset.getString("r_email"),
						rset.getString("r_type"),
						rset.getString("r_status"),
						rset.getDate("r_date"),
						rset.getInt("r_no"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return mem;
	}

	// 비밀번호 수정 기능
	public int updatePwd(Connection conn, int userNo, String userPwd, String newPwd1) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = query.getProperty("updatePwd");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, newPwd1);
			pstmt.setInt(2, userNo);
			pstmt.setString(3, userPwd);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 회원 탈퇴 기능
	public int deleteMember(Connection conn, int userNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = query.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 아이디 찾기 기능
	public Member findUserId(Connection conn, String name, String email) {
		Member findUserId = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("findId");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {  
				findUserId = new Member(rset.getString("U_ID"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return findUserId;
	}

	// 비밀번호 찾기
	public Member findUserPwd(Connection conn, String userId, String name, String email) {
		Member findUserPwd = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("findPwd");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				findUserPwd = new Member();
				findUserPwd.setU_PW(rset.getString("u_pw"));
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return findUserPwd;
	}

	public int checkJoin(Connection conn, int rno) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("checkJoin");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
      
      } catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
      }
		
		return result;
	}
	
	public Member selectMemberByEmail(Connection conn, String email) {
		Member mem = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("findInfoResident");
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, email);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {  
				mem = new Member(
						rset.getInt("r_dong"),
						rset.getInt("r_ho"),
						rset.getString("r_name"),
						rset.getString("r_email"),
						rset.getString("r_type"),
						rset.getString("r_status"),
						rset.getDate("r_date"),
						rset.getInt("r_no"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}		
		return mem;
	}

}
