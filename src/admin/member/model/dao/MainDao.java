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

import admin.facil.model.vo.Facil;
import admin.week.model.vo.Week;
import user.VisitCar.model.vo.VisitCar;
import user.complaint.model.vo.complaint;
import user.notice.model.vo.Notice;

public class MainDao {
	private Properties query = new Properties();
	
	public MainDao() {
		String fileName = MainDao.class.getResource("/sql/main/main-query.xml").getPath();
		
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 금일 주요일정
	public List<Week> weekTodayList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("weekTodayList");
		List<Week> weekList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Week w = new Week();
				w.setScNo(rset.getInt("SC_NO"));
				w.setScTitle(rset.getString("SC_TITLE"));
				w.setScCateName(rset.getString("SC_CATE_NAME"));
				
				weekList.add(w);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return weekList;
	}
	
	// 공지사항 목록
	public List<Notice> noticeList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("noticeList");
		List<Notice> noticeList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Notice n = new Notice();
				n.setN_no(rset.getInt("N_NO"));
				n.setN_title(rset.getString("N_TITLE"));
				n.setEnroll_date(rset.getDate("ENROLL_DATE"));
				n.setM_nick(rset.getString("M_NICK"));
				
				noticeList.add(n);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
	
		return noticeList;
	}
	
	// 미답변 건의현황
	public List<complaint> complaintList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("complaintList");
		List<complaint> comList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				complaint c = new complaint();
				c.setC_no(rset.getInt("C_NO"));
				c.setC_title(rset.getString("C_TITLE"));
				c.setEnroll_date(rset.getDate("C_ENROLL_DATE"));
				c.setR_nickName(rset.getString("U_NICKNAME"));
				c.setR_name(rset.getString("R_NAME"));
				c.setR_dong(rset.getInt("R_DONG"));
				c.setR_ho(rset.getInt("R_HO"));
				
				comList.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return comList;
	}
	
	// 금일 부대시설 예약현황
	public List<Facil> facilList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("facilList");
		List<Facil> facilList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Facil f = new Facil();
				f.setFcNo(rset.getInt("FC_NO"));
				f.setFcName(rset.getString("FC_NAME"));
				f.setFcSeatNo(rset.getInt("FC_SEAT_NO"));
				f.setFcSeatType(rset.getString("FC_SEAT_TYPE"));
				f.setuId(rset.getString("U_ID"));
				f.setrName(rset.getString("R_NAME"));
				f.setuPhone(rset.getString("U_PHONE"));
				f.setFaDate(rset.getTimestamp("FC_DATE"));
				f.setFcStart(rset.getTimestamp("FC_START"));
				f.setFcEnd(rset.getTimestamp("FC_END"));
				f.setFcStatus(rset.getString("FC_STATUS"));
				
				facilList.add(f);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return facilList;
	}
	
	// 금일 방문차량 예약현황
	public List<VisitCar> visitCarList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("visitCarList");
		List<VisitCar> visitCarList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				VisitCar v = new VisitCar();
				
				v.setVC_ID(rset.getInt("VC_ID"));
				v.setVC_DATE(rset.getDate("VC_DATE"));
				v.setVC_NO(rset.getString("VC_NO"));
				v.setVC_PHONE(rset.getString("VC_PHONE"));
				v.setR_DONG(rset.getInt("R_DONG"));
				v.setR_HO(rset.getInt("R_HO"));
				v.setVC_PURPOSE(rset.getString("VC_PURPOSE"));
				v.setR_NAME(rset.getString("R_NAME"));
				v.setVC_STATUS(rset.getString("VC_STATUS"));
				
				visitCarList.add(v);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return visitCarList;
	}

}

