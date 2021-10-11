package admin.vehicle.model.vo;

import java.util.ArrayList;
import java.util.List;

public class HouseHoldCar {
	private int dong;
	private int ho;
	private List<MemberCar> memberCarList = new ArrayList<>();
	
	public HouseHoldCar() {}
	
	public HouseHoldCar(int dong, int ho, List<MemberCar> memberCarList) {
		super();
		this.dong = dong;
		this.ho = ho;
		this.memberCarList = memberCarList;
	}
	
	public int getDong() {
		return dong;
	}
	public void setDong(int dong) {
		this.dong = dong;
	}
	public int getHo() {
		return ho;
	}
	public void setHo(int ho) {
		this.ho = ho;
	}
	public List<MemberCar> getMemberCarList() {
		return memberCarList;
	}
	public void setMemberCarList(List<MemberCar> memberCarList) {
		this.memberCarList = memberCarList;
	}
	@Override
	public String toString() {
		return "MemberCarList [dong=" + dong + ", ho=" + ho + ", memberCarList=" + memberCarList + "]";
	}
	
	
}
