package admin.vehicle.model.vo;

public class InvehicleSearch {
	private String dong;
	private String ho;
	private String carNO;
	
	public InvehicleSearch () {}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getCarNO() {
		return carNO;
	}

	public void setCarNO(String carNO) {
		this.carNO = carNO;
	}

	public InvehicleSearch(String dong, String ho, String carNO) {
		super();
		this.dong = dong;
		this.ho = ho;
		this.carNO = carNO;
	}

	@Override
	public String toString() {
		return "InvehicleSearch [dong=" + dong + ", ho=" + ho + ", carNO=" + carNO + "]";
	}

	
	
}
