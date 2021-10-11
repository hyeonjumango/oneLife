package admin.vehicle.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import admin.vehicle.model.dao.MemberCarDao;
import admin.vehicle.model.vo.HouseHoldCar;
import admin.vehicle.model.vo.InvehicleSearch;
import admin.vehicle.model.vo.MemberCar;
import user.VisitCar.model.vo.PageInfo;

public class MemberCarService {
	private MemberCarDao md = new MemberCarDao();

	public Map<String, Object> selectMemberCar(int page, InvehicleSearch is) {
		Connection conn = getConnection();

		int listCount = md.getMemberCarCount(conn, is);
		
		PageInfo pi = new PageInfo(page, listCount, 10, 10);

		List<HouseHoldCar> houseHoldCarList = md.selectMemberCar(conn, pi, is);
		List<HouseHoldCar> excelDownList = md.selectMemberCar(conn, is);
//		System.out.println(houseHoldCarList);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("pi", pi);
		returnMap.put("houseHoldCarList", houseHoldCarList);
		returnMap.put("excelDownList", excelDownList);

		close(conn);

		return returnMap;
	}

	public HouseHoldCar insertMemberCar(MemberCar m, int dong, int ho) {
		Connection conn = getConnection();
		int result = md.insertMemberCar(conn, m);
		HouseHoldCar houseHoldCar = null;
		if (result > 0 ) {
			commit(conn);		
			houseHoldCar = md.oneHouseHoldCarList(conn, dong, ho);  
		} else {
			rollback(conn);
		}
		
		close(conn);
		return houseHoldCar;
	}

	public int selectRno(int dong, int ho, String rName) {
		Connection conn = getConnection();

		int rno = md.selectRno(conn, dong, ho, rName);

		close(conn);

		return rno;
	}

	public int deleteMemberCar(int mcId) {
		Connection conn = getConnection();
		int result = md.deleteMemberCar(conn, mcId);
		
		if (result > 0 ) {
			commit(conn);		
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public List<MemberCar> userSelectMemberCar(int dong, int ho) {
		Connection conn = getConnection();
		List<MemberCar> memberCarList = md.userSelectMemberCar(conn, dong, ho);
		
		close(conn);
		
		return memberCarList;
	}
	
}
