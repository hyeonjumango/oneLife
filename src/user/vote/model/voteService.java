package user.vote.model;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import user.board.model.vo.PageInfo;
import user.board.model.vo.Search;
import user.vote.vo.Vote;
import user.vote.vo.Vote_choice;

public class voteService {
	private voteDao vd = new voteDao();
	
	// 1. 투표게시판 리스트 조회용 서비스 메소드
	public Map<String, Object> selectlist(int page, Search s) {
		Connection conn = getConnection();
		
		int listCount = vd.getListCount(conn, s);
		System.out.println("listCount : " + listCount);
		
		PageInfo pi = new PageInfo(page, listCount, 10, 10);
		System.out.println("pi : " + pi);
		
		List<Vote> voteList = vd.selectList(conn, pi, s);
		System.out.println("voteList : " + voteList);
		
		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put("pi", pi);
		returnMap.put("voteList", voteList);
		
		return returnMap;
	}
	
		// 투표게시판 글 작성 
		public int insertVote(Vote v) {
			Connection conn = getConnection();
			
			int result = vd.insertVote(conn, v);
			
			int v_no = 0;
			if (result > 0 ) {
				v_no =vd.selectVoteNo(conn);
				commit(conn);
			} else {
				rollback(conn);
			}
			close(conn);
			
			return v_no;
		}
		
		// 투표게시판 선택지 작성
		public int insertVoteExample(Vote vv) {
			Connection conn = getConnection();
			
			int result = vd.insertVoteExample(conn, vv);
			
			if (result > 0 ) {
				commit(conn);
			} else {
				rollback(conn);
			}
			close(conn);
			
			return result;
		}
	
		
		// 조회수 증가
		public int increaseCount(int v_no) {
			Connection conn = getConnection();
			
			int result =  vd.increaseCount(conn, v_no);
			
			if (result > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
			close(conn);
			
			return result;
		}
		
		// 관리자 게시글 1개 조회
		public Vote selectVote(int v_no) {
			Connection conn = getConnection();
			
			Vote v = vd.selectVote(conn, v_no);
			
			
			close(conn);
			
			return v;
		}
		
		// 게시글 삭제
		public int deleteVote(int v_no) {
			Connection conn = getConnection();
			
			int result =  vd.deleteVote(conn, v_no);
			
			if (result > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
			close(conn);
			
			return result;
		}

		// 사용자 투표 값
		public int insertVoteval(Vote_choice vc) {
			Connection conn = getConnection();
			
			int result =  vd.insertVoteval(conn, vc);
			
			if (result > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
			close(conn);
			
			return result;
		}

		// 투표값 조회
		public Vote_choice selectVal(int v_no) {
			Connection conn = getConnection();
			
			Vote_choice vc = vd.selectVal(conn, v_no);
			
			
			close(conn);
			
			return vc;
		}

		// 주민일때 게시글 1개조회
		public Vote selectVote(int v_no, int u_no) {
			Connection conn = getConnection();
			
			Vote v = vd.selectVote(conn, v_no);
			
			v.setU_nocount(vd.selectUnoCount(conn, v_no, u_no));
			
			v.setR_type(vd.selectType(conn, u_no));
			
			close(conn);
			
			return v;
		}

		
	
}
