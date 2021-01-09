package cn.edu.jsu.cq.tool;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.jsu.cq.sql.Connect;
import jxl.Sheet;
import jxl.Workbook;

public class ExcelToDB {
	private static StringBuffer fileName = new StringBuffer("D:\\");
	// 返回有几条重复或者出错的数据
	private static int count = 0;

	public static int toReadExcel(String name) {
		// 创建Excel名
		fileName.append(name);
		fileName.append(".xls");
		
		// 插入Excel中的值
		getAllByExcel();
		
		return count;
	}

	/*
	 * 获取Excel中所有的数据
	 * 
	 * @param fileName Excel文件完整路径
	 * 
	 * @return
	 */
	private static void getAllByExcel() {

		ChargeRecord data = new ChargeRecord();
		

		try {
			// 获得工作薄
			Workbook workBook = Workbook.getWorkbook(new File(fileName.toString()));
			// 获得工作表
			Sheet sheet = workBook.getSheet(0);

			// 获得行和列的长度
			int col = sheet.getColumns();
			int row = sheet.getRows();

			System.out.println("col = " + col + " rows = " + row);

			for (int i = 1; i < row; i++) {
				for (int j = 0; j < col; j++) {
					// 获得数据
					String username = sheet.getCell(j++, i).getContents();
					String chargemoney = sheet.getCell(j++, i).getContents();
					String chargetime = sheet.getCell(j, i).getContents();

					// 输出数据
					System.out.println(" username = " + username + " chargemoney = " + chargemoney + " chargeTime = "
							+ chargetime);

					/*
					 * 添加到数据库 
					 * 如果数据库中的表设置主键 
					 * 应该先判断插入的值是否在数据库中存在， 
					 * 存在就更新，没有就直接插入
					 * 
					 */
					String[] str = new String[] { username, chargemoney, chargetime };
					if (canInsert(username, str)) {		
						// 插入数据库
						insertToDB(str);
					}
					else {
						System.out.println("插入 失败");
						count++;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void insertToDB(String[] str) {
		String sql = "insert into record_charge (username,chargemoney,chargeTime) values(?,?,?)";
		// 添加到数据库
		Connect.add(sql, str);
	}

	/*
	 * 判断是否在user表,判断record_charge是否有重复数据
	 */
	private static boolean canInsert(String username, String[] str) {
		try {
			ResultSet res1 = Connect.search("select * from user where username=?", new String[] { username });
			ResultSet res2 = Connect.search("select * from record_charge where username=? and chargemoney=? and chargeTime=?", str);
			/*
			 *  res1要找到username
			 *  res2不能找到相同数据
			 */
			if (res1.next() && !res2.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String args[]) {
		toReadExcel("我爱猜数字-用户消费表123");
	}
}
