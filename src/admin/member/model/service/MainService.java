package admin.member.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import admin.facil.model.vo.Facil;
import admin.member.model.dao.MainDao;
import admin.week.model.vo.Week;
import user.VisitCar.model.vo.VisitCar;
import user.complaint.model.vo.complaint;
import user.notice.model.vo.Notice;

public class MainService {
	MainDao md = null;
	
	public MainService() {
		md = new MainDao();
	}
	
	// 금일 주요일정
	public List<Week> weekTodayList() {
		Connection conn = getConnection();
		
		List<Week> weekList = md.weekTodayList(conn);
		
		close(conn);
		
		return weekList;
	}
	
	// 공지사항 목록
	public List<Notice> noticeList() {
		Connection conn = getConnection();
		
		List<Notice> noticeList = md.noticeList(conn);
		
		close(conn);
		
		return noticeList;
	}
	
	// 미답변 건의현황
	public List<complaint> complaintList() {
		Connection conn = getConnection();
		
		List<complaint> complaintList = md.complaintList(conn);
		
		close(conn);
		
		return complaintList;
	}
	
	// 금일 부대시설 예약현황
	public List<Facil> facilList() {
		Connection conn = getConnection();
		
		List<Facil> facilList = md.facilList(conn);
		
		close(conn);
		
		return facilList;
	}

	// 금일 방문차량 예약현황
	public List<VisitCar> visitCarList() {
		Connection conn = getConnection();
		
		List<VisitCar> visitCarList = md.visitCarList(conn);
		
		close(conn);
		
		return visitCarList;
	}
	
}
