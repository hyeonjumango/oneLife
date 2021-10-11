package admin.vehicle.model.vo;


import java.io.File;
import java.util.List;
 
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class MakeExcelFile {
	public String exportData(String fileName, List<HouseHoldCar> houseHoldCarList) {
        try {
            // 엑셀파일 객체 생성
            WritableWorkbook workbook = null;
 
            // 시트 객체 생성
            WritableSheet sheet = null;
 
            // 셀 객체 생성
            Label label = null;
 
            
            // 저장할 파일 객체 생성
            File file = null;
            if (houseHoldCarList != null) {
            	file = new File("C:\\Users\\김재형\\git\\oneLife\\" + fileName + ".xls" );
            }
 
            // 테스트 데이터 (S)
//            HashMap hm_0 = new HashMap();
//            hm_0.put("name", "홍길동");
//            hm_0.put("age", "21");
// 
//            HashMap hm_1 = new HashMap();
//            hm_1.put("name", "아무개");
//            hm_1.put("age", "20");
// 
//            List list = new ArrayList();
//            list.add(hm_0);
//            list.add(hm_1);
            // 테스트 데이터 (E)
 
            // 파일 생성
            workbook = Workbook.createWorkbook(file);
 
            // 시트 생성
            workbook.createSheet("sheet1", 0);
            sheet = workbook.getSheet(0);
 
            // 셀에 쓰기
            label = new Label(0, 0, "동");
            sheet.addCell(label);
 
            label = new Label(1, 0, "호");
            sheet.addCell(label);
            
            label = new Label(2, 0, "차량번호");
            sheet.addCell(label);
            
            label = new Label(3, 0, "신청인");
            sheet.addCell(label);
            
            label = new Label(4, 0, "전화번호");
            sheet.addCell(label);
            
            int num = 1;
            // 데이터 삽입
            for (int i = 0; i < houseHoldCarList.size(); i++) {
                for (int j = 0; j < houseHoldCarList.get(i).getMemberCarList().size() ; j++) {
                	label = new Label(0, num, Integer.toString(houseHoldCarList.get(i).getDong()));
                    sheet.addCell(label);
                    
                    label = new Label(1, num, Integer.toString(houseHoldCarList.get(i).getHo()));
                    sheet.addCell(label);
                    
                    label = new Label(2, num, houseHoldCarList.get(i).getMemberCarList().get(j).getMcNo());
                    sheet.addCell(label);
                    
                    label = new Label(3, num, houseHoldCarList.get(i).getMemberCarList().get(j).getrName());
                    sheet.addCell(label);
                    
                    label = new Label(4, num, houseHoldCarList.get(i).getMemberCarList().get(j).getcPhone());
                    sheet.addCell(label);
                    
                    num++;
                }
            }
 
            workbook.write();
            workbook.close();
            
            return "success";
        } catch (Exception e) {
        	return "fail";
        }
    }
}
