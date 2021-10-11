package admin.vehicle.model.dao;

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

import admin.vehicle.model.vo.HouseHoldCar;
import admin.vehicle.model.vo.InvehicleSearch;
import admin.vehicle.model.vo.MemberCar;
import user.VisitCar.model.vo.PageInfo;
import user.member.model.dao.MemberDao;

public class MemberCarDao {
	private Properties query = new Properties();

	public MemberCarDao() {
		String fileName = MemberDao.class.getResource("/sql/MemberCar/membercar-query.xml").getPath();

		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<HouseHoldCar> selectMemberCar(Connection conn, PageInfo pi, InvehicleSearch is) {
		List<HouseHoldCar> selectList = new ArrayList<>();
		HouseHoldCar houseHoldCar = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("selectMemberCar");
		String comp = "";

		// 검색 결과가 있을 시
		if (is.getDong() != null && is.getHo() != null && is.getCarNO() != null) {
			sql = query.getProperty("searchSelectMemberCar");
			// 동
			if (is.getDong().equals("all")) {
				sql += "and r_dong is not null ";
			} else {
				sql += "and r_dong = ? ";
			}

			// 호
			if (is.getHo().equals("all")) {
				sql += "and r_ho is not null ";
			} else {
				sql += "and r_ho = ? ";
			}

			// 차량 번호
			if (!is.getCarNO().equals("all")) {
				sql += "and mc_no = ? ";
			} else {
				sql += "and mc_no is not null ";
			}
			sql += "order by r_dong asc, r_ho asc, mc_id) VLIST) WHERE RNUM BETWEEN ? AND ?";
		}
		try {
			pstmt = conn.prepareStatement(sql);

			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			int paramIndex = 1;

			//검색 결과가 있을 시
			if (is.getDong() != null && is.getHo() != null && is.getCarNO() != null) {
				// 동
				if (is.getDong().equals("all")) {
				} else {
					pstmt.setString(paramIndex++, is.getDong());
				}

				// 호
				if (is.getHo().equals("all")) {
				} else {
					pstmt.setString(paramIndex++, is.getHo());
				}

				// 차량 번호
				if (is.getCarNO().equals("all")) {
				} else {
					pstmt.setString(paramIndex++, is.getCarNO());
				}
			}
			
			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				String input = rset.getInt("r_dong") + "" + rset.getInt("r_ho");
				if (!comp.equals(input)) {

					houseHoldCar = new HouseHoldCar();
					houseHoldCar.setDong(rset.getInt("r_dong"));
					houseHoldCar.setHo(rset.getInt("r_ho"));

					MemberCar mc = new MemberCar();
					mc.setMcId(rset.getInt("mc_id"));
					mc.setMcNo(rset.getString("mc_no"));
					mc.setrName(rset.getString("r_name"));
					mc.setcPhone(rset.getString("c_phone"));

					houseHoldCar.getMemberCarList().add(mc);

					comp = input;
					selectList.add(houseHoldCar);
				} else {
					MemberCar mc = new MemberCar();
					mc.setMcId(rset.getInt("mc_id"));
					mc.setMcNo(rset.getString("mc_no"));
					mc.setrName(rset.getString("r_name"));
					mc.setcPhone(rset.getString("c_phone"));

					houseHoldCar.getMemberCarList().add(mc);
					int i = selectList.size();
					selectList.remove(i - 1);
					selectList.add(houseHoldCar);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return selectList;
	}

	public HouseHoldCar oneHouseHoldCarList(Connection conn, int dong, int ho) {
		HouseHoldCar houseHoldCar = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("oneHouseHoldCarList");
		int count = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dong);
			pstmt.setInt(2, ho);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				if (count++ < 1) {

					houseHoldCar = new HouseHoldCar();
					houseHoldCar.setDong(rset.getInt("r_dong"));
					houseHoldCar.setHo(rset.getInt("r_ho"));

				}

				MemberCar mc = new MemberCar();
				mc.setMcNo(rset.getString("mc_no"));
				mc.setrName(rset.getString("r_name"));
				mc.setcPhone(rset.getString("c_phone"));

				houseHoldCar.getMemberCarList().add(mc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return houseHoldCar;
	}

	public int insertMemberCar(Connection conn, MemberCar m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertMemberCar");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getMcNo());
			pstmt.setInt(2, m.getmNo());
			pstmt.setInt(3, m.getrNo());
			pstmt.setString(4, m.getcPhone());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int selectRno(Connection conn, int dong, int ho, String rName) {
		int uno = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("selectRno");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dong);
			pstmt.setInt(2, ho);
			pstmt.setString(3, rName);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				uno = rset.getInt("r_no");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return uno;
	}

	public int deleteMemberCar(Connection conn, int mcId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = query.getProperty("deleteMemberCar");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, mcId);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int getMemberCarCount(Connection conn, InvehicleSearch is) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("getMemberCarCount");

		// 검색 결과가 있을 시
		if (is.getDong() != null && is.getHo() != null && is.getCarNO() != null) {

			// 동
			if (is.getDong().equals("all")) {
				sql += "and r_dong is not null ";
			} else {
				sql += "and r_dong = ? ";
			}

			// 호
			if (is.getHo().equals("all")) {
				sql += "and r_ho is not null ";
			} else {
				sql += "and r_ho = ? ";
			}

			// 차량 번호
			if (is.getCarNO().equals("all")) {
				sql += "and mc_no is not null ";
			} else {
				sql += "and mc_no = ? ";
			}

		}
		try {

			int paramIndex = 1;

			pstmt = conn.prepareStatement(sql);

			if (is.getDong() != null && is.getHo() != null && is.getCarNO() != null) {
				// 동
				if (is.getDong().equals("all")) {
				} else {
					pstmt.setString(paramIndex++, is.getDong());
				}

				// 호
				if (is.getHo().equals("all")) {
				} else {
					pstmt.setString(paramIndex++, is.getHo());
				}

				// 차량 번호
				if (is.getCarNO().equals("all")) {
				} else {
					pstmt.setString(paramIndex++, is.getCarNO());
				}
			}

			rset = pstmt.executeQuery();
			if (rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return listCount;
	}

	public List<MemberCar> userSelectMemberCar(Connection conn, int dong, int ho) {
		List<MemberCar> memberCarList = new ArrayList<>();
		MemberCar mc = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("oneHouseHoldCarList");
//		입주차량 신규추가시 결과 확인 sql문과 동일 sql

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dong);
			pstmt.setInt(2, ho);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				mc = new MemberCar();
				mc.setMcNo(rset.getString("mc_no"));
				mc.setrName(rset.getString("r_name"));
				mc.setcPhone(rset.getString("c_phone"));
				mc.setMcId(Integer.parseInt(rset.getString("mc_id")));
				
				memberCarList.add(mc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return memberCarList;
	}
	
	//excel 다운 조회
	public List<HouseHoldCar> selectMemberCar(Connection conn, InvehicleSearch is) {
		List<HouseHoldCar> selectList = new ArrayList<>();
		HouseHoldCar houseHoldCar = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("excelSelectMemberCar");
		String comp = "";

		// 검색 결과가 있을 시
		if (is.getDong() != null && is.getHo() != null && is.getCarNO() != null) {
			sql = query.getProperty("searchSelectMemberCar");
			// 동
			if (is.getDong().equals("all")) {
				sql += "and r_dong is not null ";
			} else {
				sql += "and r_dong = ? ";
			}

			// 호
			if (is.getHo().equals("all")) {
				sql += "and r_ho is not null ";
			} else {
				sql += "and r_ho = ? ";
			}

			// 차량 번호
			if (!is.getCarNO().equals("all")) {
				sql += "and mc_no = ? ";
			} else {
				sql += "and mc_no is not null ";
			}
			sql += "order by r_dong asc, r_ho asc, mc_id) VLIST)";
		}
		try {
			pstmt = conn.prepareStatement(sql);

			int paramIndex = 1;

			//검색 결과가 있을 시
			if (is.getDong() != null && is.getHo() != null && is.getCarNO() != null) {
				// 동
				if (is.getDong().equals("all")) {
				} else {
					pstmt.setString(paramIndex++, is.getDong());
				}

				// 호
				if (is.getHo().equals("all")) {
				} else {
					pstmt.setString(paramIndex++, is.getHo());
				}

				// 차량 번호
				if (is.getCarNO().equals("all")) {
				} else {
					pstmt.setString(paramIndex++, is.getCarNO());
				}
			}

			rset = pstmt.executeQuery();
			while (rset.next()) {
				String input = rset.getInt("r_dong") + "" + rset.getInt("r_ho");
				if (!comp.equals(input)) {

					houseHoldCar = new HouseHoldCar();
					houseHoldCar.setDong(rset.getInt("r_dong"));
					houseHoldCar.setHo(rset.getInt("r_ho"));

					MemberCar mc = new MemberCar();
					mc.setMcId(rset.getInt("mc_id"));
					mc.setMcNo(rset.getString("mc_no"));
					mc.setrName(rset.getString("r_name"));
					mc.setcPhone(rset.getString("c_phone"));

					houseHoldCar.getMemberCarList().add(mc);

					comp = input;
					selectList.add(houseHoldCar);
				} else {
					MemberCar mc = new MemberCar();
					mc.setMcId(rset.getInt("mc_id"));
					mc.setMcNo(rset.getString("mc_no"));
					mc.setrName(rset.getString("r_name"));
					mc.setcPhone(rset.getString("c_phone"));

					houseHoldCar.getMemberCarList().add(mc);
					int i = selectList.size();
					selectList.remove(i - 1);
					selectList.add(houseHoldCar);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return selectList;
	}

}
