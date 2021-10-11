package user.VisitCar.model.dao;

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

import admin.vehicle.model.vo.VisitCarSearch;
import user.VisitCar.model.vo.PageInfo;
import user.VisitCar.model.vo.UserVisitCarSearch;
import user.VisitCar.model.vo.VisitCar;
import user.member.model.dao.MemberDao;

public class VisitCarDao {
	private Properties query = new Properties();

	public VisitCarDao() {
		String fileName = MemberDao.class.getResource("/sql/VisitCar/visitCar-query.xml").getPath();

		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<VisitCar> selectList(Connection conn, PageInfo pi, int dong, int ho, UserVisitCarSearch vs) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<VisitCar> visitCarList = new ArrayList<>();
		String sql = query.getProperty("selectList");

		if (vs.getDate() != null && vs.getCarNo() != null && vs.getApplicant() != null) {
			sql = query.getProperty("userSearchVisitCar");

			if (vs.getDate().equals("all")) {
				sql += "and vc_date is not null ";
			} else {
				sql += "and vc_date = to_date(?, 'YYYY-MM-DD') ";
			}

			if (vs.getCarNo().equals("all")) {
				sql += "and vc_no is not null ";
			} else {
				sql += "and vc_no = ? ";
			}

			if (vs.getApplicant().equals("all")) {
				sql += "and u_no is not null ";
			} else if (vs.getApplicant().equals("관리자")) {
				sql += "and m_no is not null ";
			} else {
				sql += "and (r_name = ? or m_name = ?) ";
			}

			sql += "order by vc_id desc) VLIST) WHERE RNUM BETWEEN ? AND ?";
		}

		try {
			pstmt = conn.prepareStatement(sql);

			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;

			int paramIndex = 1;

			pstmt.setInt(paramIndex++, dong);
			pstmt.setInt(paramIndex++, ho);
			if (vs.getDate() != null && vs.getCarNo() != null && vs.getApplicant() != null) {

				if (vs.getDate().equals("all")) {
				} else {
					pstmt.setString(paramIndex++, vs.getDate());
				}

				if (vs.getCarNo().equals("all")) {
				} else {
					pstmt.setString(paramIndex++, vs.getCarNo());
				}

				if (vs.getApplicant().equals("all")) {
				} else if (vs.getApplicant().equals("관리자")){
				} else {
					pstmt.setString(paramIndex++, vs.getApplicant());
					pstmt.setString(paramIndex++, vs.getApplicant());
				}
			}
			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				visitCarList.add(new VisitCar(rset.getInt("vc_id"), rset.getDate("vc_date"), rset.getString("vc_no"),
						rset.getString("vc_purpose"), rset.getString("vc_phone"), rset.getDate("vc_modifydate"),
						rset.getString("vc_status"), rset.getString("r_name"), rset.getInt("u_no"), rset.getString("m_name")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return visitCarList;
	}

	public int getListCount(Connection conn, int dong, int ho, UserVisitCarSearch vs) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("getListCount");
		if (vs.getDate() != null && vs.getCarNo() != null && vs.getApplicant() != null) {
			if (vs.getDate().equals("all")) {
				sql += "and vc_date is not null ";
			} else {
				sql += "and vc_date = to_date(?, 'YYYY-MM-DD') ";
			}

			if (vs.getCarNo().equals("all")) {
				sql += "and vc_no is not null ";
			} else {
				sql += "and vc_no = ? ";
			}

			if (vs.getApplicant().equals("all")) {
				sql += "and u_no is not null ";
			} else if (vs.getApplicant().equals("관리자")) {
				sql += "and m_no is not null ";
			} else {
				sql += "and (r_name = ? or m_name = ?) ";
			}
		}
		try {
			int paramIndex = 1;

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(paramIndex++, dong);
			pstmt.setInt(paramIndex++, ho);
			if (vs.getDate() != null && vs.getCarNo() != null && vs.getApplicant() != null) {

				if (vs.getDate().equals("all")) {
				} else {
					pstmt.setString(paramIndex++, vs.getDate());
				}

				if (vs.getCarNo().equals("all")) {
				} else {
					pstmt.setString(paramIndex++, vs.getCarNo());
				}

				if (vs.getApplicant().equals("all")) {
				} else if (vs.getApplicant().equals("관리자")){
				} else {
					pstmt.setString(paramIndex++, vs.getApplicant());
					pstmt.setString(paramIndex++, vs.getApplicant());
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

	public int insertVisitCar(Connection conn, String dateString, String carNo, String purpose, String phone,
			int userNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = query.getProperty("insertVisitCar");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dateString);
			pstmt.setString(2, carNo);
			pstmt.setString(3, purpose);
			pstmt.setString(4, phone);
			pstmt.setInt(5, userNo);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int selectVid(Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = query.getProperty("selectVid");
		ResultSet rset = null;

		try {
			pstmt = conn.prepareStatement(sql);
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

	public VisitCar selectDetail(Connection conn, int vid) {
		VisitCar vs = null;
		PreparedStatement pstmt = null;
		String sql = query.getProperty("selectDetail");
		ResultSet rset = null;

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, vid);
			;
			rset = pstmt.executeQuery();

			if (rset.next()) {
				vs = new VisitCar(rset.getInt("vc_id"), rset.getDate("vc_date"), rset.getString("vc_no"),
						rset.getString("vc_purpose"), rset.getString("vc_phone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}

		return vs;
	}

	public int updateVisitCar(Connection conn, String dateString, String carNo, String purpose, String phone, int vid) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = query.getProperty("updateVisitCar");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dateString);
			pstmt.setString(2, carNo);
			pstmt.setString(3, purpose);
			pstmt.setString(4, phone);
			pstmt.setInt(5, vid);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int deleteVisitCar(Connection conn, int vid) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = query.getProperty("deleteVisitCar");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vid);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int adminGetListCount(Connection conn, VisitCarSearch vs) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("adminGetListCount");

		if (vs.getDong() != null && vs.getHo() != null && vs.getCarNo() != null && vs.getDate() != null
				&& vs.getStatus() != null) {
			// 동
			if (vs.getDong().equals("all")) {
				sql += "and r_dong is not null ";
			} else {
				sql += "and r_dong = ? ";
			}

			if (vs.getHo().equals("all")) {
				sql += "and r_ho is not null ";
			} else {
				sql += "and r_ho = ? ";
			}

			if (vs.getCarNo().equals("all")) {
				sql += "and vc_no is not null ";
			} else {
				sql += "and vc_no = ? ";
			}

			if (vs.getDate().equals("all")) {
				sql += "and vc_date is not null ";
			} else {
				sql += "and vc_date = to_date(?, 'YYYY-MM-DD') ";
			}

			if (vs.getStatus().equals("all")) {
				sql += "and vc_status is not null ";
			} else {
				sql += "and vc_status = ? ";
			}
		}

		try {

			int paramIndex = 1;

			pstmt = conn.prepareStatement(sql);

			if (vs.getDong() != null && vs.getHo() != null && vs.getCarNo() != null && vs.getDate() != null
					&& vs.getStatus() != null) {
				if (vs.getDong().equals("all")) {
				} else {
					pstmt.setString(paramIndex++, vs.getDong());
				}

				if (vs.getHo().equals("all")) {
				} else {
					pstmt.setString(paramIndex++, vs.getHo());
				}

				if (vs.getCarNo().equals("all")) {
				} else {
					pstmt.setString(paramIndex++, vs.getCarNo());
				}

				if (vs.getDate().equals("all")) {
				} else {
					pstmt.setString(paramIndex++, vs.getDate());
				}

				if (vs.getStatus().equals("all")) {
				} else {
					pstmt.setString(paramIndex++, vs.getStatus());
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

	public List<VisitCar> adminSelectList(Connection conn, PageInfo pi, VisitCarSearch vs) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<VisitCar> visitCarList = new ArrayList<>();
		String sql = query.getProperty("adminSelectList");

		if (vs.getDong() != null && vs.getHo() != null && vs.getCarNo() != null && vs.getDate() != null
				&& vs.getStatus() != null) {
			sql = query.getProperty("searchSelectVisitCar");

			// 동
			if (vs.getDong().equals("all")) {
				sql += "and r_dong is not null ";
			} else {
				sql += "and r_dong = ? ";
			}

			if (vs.getHo().equals("all")) {
				sql += "and r_ho is not null ";
			} else {
				sql += "and r_ho = ? ";
			}

			if (vs.getCarNo().equals("all")) {
				sql += "and vc_no is not null ";
			} else {
				sql += "and vc_no = ? ";
			}

			if (vs.getDate().equals("all")) {
				sql += "and vc_date is not null ";
			} else {
				sql += "and vc_date = to_date(?, 'YYYY-MM-DD') ";
			}

			if (vs.getStatus().equals("all")) {
				sql += "and vc_status is not null ";
			} else {
				sql += "and vc_status = ? ";
			}

			sql += "order by vc_id desc) VLIST) WHERE RNUM BETWEEN ? AND ?";
		}

		try {
			pstmt = conn.prepareStatement(sql);

			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			int paramIndex = 1;

			if (vs.getDong() != null && vs.getHo() != null && vs.getCarNo() != null && vs.getDate() != null
					&& vs.getStatus() != null) {
				if (vs.getDong().equals("all")) {
				} else {
					pstmt.setString(paramIndex++, vs.getDong());
				}

				if (vs.getHo().equals("all")) {
				} else {
					pstmt.setString(paramIndex++, vs.getHo());
				}

				if (vs.getCarNo().equals("all")) {
				} else {
					pstmt.setString(paramIndex++, vs.getCarNo());
				}

				if (vs.getDate().equals("all")) {
				} else {
					pstmt.setString(paramIndex++, vs.getDate());
				}

				if (vs.getStatus().equals("all")) {
				} else {
					pstmt.setString(paramIndex++, vs.getStatus());
				}
			}

			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				visitCarList.add(new VisitCar(rset.getInt("vc_id"), rset.getDate("vc_date"), rset.getString("vc_no"),
						rset.getString("vc_purpose"), rset.getString("vc_phone"), rset.getDate("vc_modifydate"),
						rset.getString("vc_status"), rset.getString("r_name"), rset.getInt("r_dong"),
						rset.getInt("r_ho"), rset.getInt("u_no"), rset.getString("m_name")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return visitCarList;
	}

	public int checkVisitCar(Connection conn, int vid) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("checkVisitCar");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vid);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int cancelVisitCar(Connection conn, int vid) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("cancelVisitCar");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vid);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int adminInsertVisitCar(Connection conn, int rno, int uno, String dateString, String carNo, String purpose,
			int dong, int ho, String phone) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = query.getProperty("adminInsertVisitCar");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dateString);
			pstmt.setString(2, carNo);
			pstmt.setString(3, purpose);
			pstmt.setString(4, phone);
			pstmt.setInt(5, uno);
			pstmt.setInt(6, rno);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int selectUno(Connection conn, int dong, int ho) {
		int uno = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("selectUno");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dong);
			pstmt.setInt(2, ho);

			rset = pstmt.executeQuery();
			if (rset.next()) {
				uno = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return uno;
	}

}
