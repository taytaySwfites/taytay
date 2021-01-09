package cn.edu.jsu.cq.tool;

import java.io.File;
import java.util.List;

import cn.edu.jsu.cq.sql.ConnectService;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class DBToExcel {
	// 创建可写入的Excel工作薄
	private static StringBuffer fileName = new StringBuffer("D:\\");
	
	public static void toWriteExcel(String name) {
		try {
			// 创建Excel名
			fileName.append(name);
			fileName.append(".xls");
			
    		WritableWorkbook excelBook = null;
    		// 打开文件
    		File file = new File(fileName.toString());
    		// 查看文件是否存在
    		if(!file.exists()) {
    			// 不存在创建
    			file.createNewFile();
    		}
    		
    		//以fileName为文件名来创建一个Workbook
    		excelBook = Workbook.createWorkbook(file);
    		
    		//创建工作表
    		WritableSheet excelSheet = excelBook.createSheet("Test 1", 0);
    		
    		//导出数据中所有数据
    		List<DBCharge> list = ConnectService.getAllExcel("select username,sum(chargemoney) as totalMoney from record_charge group by username\r\n" + 
    				"order by totalMoney desc", null);
    		
    		//要插入到的Excel表格的行号，默认从0开始
    		Label labelUserName = new Label(0, 0 , "username");
    		Label LabelTotalMoney = new Label(1, 0 , "totalMoney");
    		
    		//添加第一列到单元格
    		excelSheet.addCell(labelUserName);
    		excelSheet.addCell(LabelTotalMoney);
    		
    		// 导入
    		for(int i = 0; i < list.size(); i++) {
    			//要插入到的Excel表格的行号，默认从0开始
        		Label label_UserName = new Label(0, i+1 , list.get(i).getUsername());
        		Label label_TotalMoney = new Label(1, i+1 , list.get(i).getTotalMoney() + "");
        		
        		//添加第一列到单元格
        		excelSheet.addCell(label_UserName);
        		excelSheet.addCell(label_TotalMoney);
        		
    		}
    		
    		//写入文档
    		excelBook.write();
    		//关闭Excel工作薄对象
    		excelBook.close();
    	}
    	 catch (Exception e) {
             e.printStackTrace();
         }
	}
	
    public static void main(String args[]) {
    	toWriteExcel("我爱猜数字-用户消费表1");
    }
}
