package cn.edu.jsu.cq.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connect {
	/*
     * 数据库工具
     */
	private static final String DATABASE_DRVIER = "com.mysql.cj.jdbc.Driver";
    // 数据库的位置，url是统一资源定位符
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/game?serverTimezone=GMT%2B8";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "123";

	private static Connection con = null;
	private static ResultSet res = null;
	public static void DataBase() {
		try {
			Class.forName(DATABASE_DRVIER);
			con = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
		} catch (ClassNotFoundException e) {
			System.err.println("装载 JDBC/ODBC 驱动程序失败");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("无法连接数据库");
			e.printStackTrace();
		}
	}
	
	public static ResultSet search(String sql, String str[]) {
    	DataBase();
    	try {
    		PreparedStatement pst = con.prepareStatement(sql);//数据库的操作对象
            if (str != null) {
                for (int i = 0; i < str.length; i++) {
                	//把str[i]设为第i+1个数据
                	//sql语句没有第0个数据，1开始
                    pst.setString(i + 1, str[i]);
                }
            }
            res = pst.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
	
	// 增删修改
    public static int add(String sql, String str[]) {
        int a = 0;
        DataBase();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            if (str != null) {
                for (int i = 0; i < str.length; i++) {
                    pst.setString(i + 1, str[i]);
                }
            }
            a = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }
}
