package user.amenities.model.vo;

import java.util.Date;

public class Facility {
	private int fc_no;
	private String fc_name;
	private int fc_seat_no;
	private Date fc_date;
	private Date fc_start;
	private Date fc_end;
	private int u_no;
	private String fc_status;
	
	/*FC_NO	NUMBER
		FC_NAME	VARCHAR2(20 BYTE)
		FC_SEAT_NO	NUMBER
		FC_DATE	DATE
		FC_START	DATE
		FC_END	DATE
		U_NO	NUMBER
		FC_STATUS	VARCHAR2(1 BYTE)*/
	
	public Facility () {}

	public Facility(int fc_no, String fc_name, int fc_seat_no, Date fc_date, Date fc_start, Date fc_end, int u_no,
			String fc_status) {
		super();
		this.fc_no = fc_no;
		this.fc_name = fc_name;
		this.fc_seat_no = fc_seat_no;
		this.fc_date = fc_date;
		this.fc_start = fc_start;
		this.fc_end = fc_end;
		this.u_no = u_no;
		this.fc_status = fc_status;
	}

	public int getFc_no() {
		return fc_no;
	}

	public void setFc_no(int fc_no) {
		this.fc_no = fc_no;
	}

	public String getFc_name() {
		return fc_name;
	}

	public void setFc_name(String fc_name) {
		this.fc_name = fc_name;
	}

	public int getFc_seat_no() {
		return fc_seat_no;
	}

	public void setFc_seat_no(int fc_seat_no) {
		this.fc_seat_no = fc_seat_no;
	}

	public Date getFc_date() {
		return fc_date;
	}

	public void setFc_date(Date fc_date) {
		this.fc_date = fc_date;
	}

	public Date getFc_start() {
		return fc_start;
	}

	public void setFc_start(Date fc_start) {
		this.fc_start = fc_start;
	}

	public Date getFc_end() {
		return fc_end;
	}

	public void setFc_end(Date fc_end) {
		this.fc_end = fc_end;
	}

	public int getU_no() {
		return u_no;
	}

	public void setU_no(int u_no) {
		this.u_no = u_no;
	}

	public String getFc_status() {
		return fc_status;
	}

	public void setFc_status(String fc_status) {
		this.fc_status = fc_status;
	}

	@Override
	public String toString() {
		return "Facility [fc_no=" + fc_no + ", fc_name=" + fc_name + ", fc_seat_no=" + fc_seat_no + ", fc_date="
				+ fc_date + ", fc_start=" + fc_start + ", fc_end=" + fc_end + ", u_no=" + u_no + ", fc_status="
				+ fc_status + "]";
	}

	
	

}
