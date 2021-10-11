package user.member.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;

import user.member.model.dao.MemberDao;
import user.member.model.vo.Member;

public class MemberService {

	private MemberDao md = new MemberDao();

	// 1. 로그인 기능
	public Member loginMember(String id, String pwd) {

		Connection conn = getConnection();
		Member loginUser = new MemberDao().loginMember(conn, id, pwd);

		close(conn);

		return loginUser;
	}
	
	// 2. 회원가입 기능
	public int insertMember(Member mem) {
		Connection conn = getConnection();

		int result = new MemberDao().insertMember(conn, mem);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return result;
	}

	// 주민 =? 회원
	public int checkR(String name, String email) {
		Connection conn = getConnection();

		int result = new MemberDao().checkR(conn, name, email);

		close(conn);
		return result;
	}

	// 아이디 중복 확인
	public int idCheck(String userId) {
		Connection conn = getConnection();

		int result = new MemberDao().idCheck(conn, userId);

		close(conn);

		return result;
	}

	// 중복 닉네임 체크
	public int nickCheck(String nickName) {
		Connection conn = getConnection();

		int result = new MemberDao().nickCheck(conn, nickName);

		close(conn);

		return result;
	}

	// 회원 상태 바꾸기
	public int changeStatus(int rno) {
		Connection conn = getConnection();

		int result = new MemberDao().changeStatus(conn, rno);

		close(conn);

		return result;
	}
	
//	// 주민 원라이프 가입여부 상태(중복 가입 방지)
//	public int changeRstatus(int rno) {
//		return 0;
//	}

	// 3. 회원 정보 수정 기능
	public Member updateMember(Member mem) {
		Connection conn = getConnection();
		Member updateMember = null;

		int result = new MemberDao().updateMember(conn, mem);

		// 수정 잘 되었다면 mem객체 select후 리턴
		if (result > 0) {
			updateMember = new MemberDao().selectMember(conn, mem.getU_NO());
			commit(conn);
		} else {
			rollback(conn);
		}

		return updateMember;
	}

	// 비밀번호 변경 기능
	public Member updatePwd(int userNo, String userPwd, String newPwd1) {
		Connection conn = getConnection();
		Member updateMember = null;

		int result = new MemberDao().updatePwd(conn, userNo, userPwd, newPwd1);

		if (result > 0) {
			updateMember = new MemberDao().selectMember(conn, userNo);
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return updateMember;
	}

	// 회원 탈퇴 기능
	public int deleteMember(int userNo) {
		Connection conn = getConnection();

		int result = new MemberDao().deleteMember(conn, userNo);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);

		return result;
	}

	// 아이디 찾기
	public Member findUserId(String name, String email) {
		Connection conn = getConnection();

		Member findUserId = new MemberDao().findUserId(conn, name, email);

		close(conn);

		return findUserId;
	}

	// 비밀번호 찾기
	public Member findUserPwd(String userId, String name, String email) {
		Connection conn = getConnection();

		Member findUserPwd = new MemberDao().findUserPwd(conn, userId, name, email);

		close(conn);

		return findUserPwd;
	}

	public int checkJoin(int rno) {
		Connection conn = getConnection();

		int result = new MemberDao().checkJoin(conn, rno);

		close(conn);

		return result;
	}

	
	public Member findMemberByEmail(String email) {
		Connection conn = getConnection();

		Member findUserPwd = new MemberDao().selectMemberByEmail(conn, email);

		close(conn);

		return findUserPwd;
	}
}
