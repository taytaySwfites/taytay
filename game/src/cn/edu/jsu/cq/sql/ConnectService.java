package cn.edu.jsu.cq.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.jsu.cq.tool.ChargeRecord;
import cn.edu.jsu.cq.tool.ClockThread;
import cn.edu.jsu.cq.tool.DBCharge;
import cn.edu.jsu.cq.tool.Record;
import cn.edu.jsu.cq.tool.User;
import cn.edu.jsu.cq.tool.UserBean;

public class ConnectService {
	/*
	 * 获得ChargeRecord类型的所有记录
	 */
	public static List<ChargeRecord> getAllChargeRecord(String sql, String[] str) {
		List<ChargeRecord> list = new ArrayList<>();
		try {
			ResultSet res = Connect.search(sql, str);

			while (res.next()) {
				String userName = res.getString("username");
				int chargemoney = res.getInt("chargemoney");
				String chargetime = res.getString("chargetime");
				list.add(new ChargeRecord(userName, chargemoney, chargetime));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/*
	 * 获得Record类型的所有记录
	 */
	public static List<Record> getAllRecord(String sql) {
		List<Record> list = new ArrayList<>();
		try {
			ResultSet res = Connect.search(sql, null);

			while (res.next()) {
				String userName = res.getString("username");
				int score = res.getInt("score");
				String scoretime = res.getString("scoretime");
				list.add(new Record(userName, score, scoretime));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/*
	 * 获得UserBean类型的所有记录
	 */
	public static List<UserBean> getAll(String sql) {
		List<UserBean> list = new ArrayList<>();
		try {
			ResultSet res = Connect.search(sql, null);
			while (res.next()) {
				String userName = res.getString(1);
				int bean = res.getInt(2);
				UserBean userBean = new UserBean(userName, bean);
				list.add(userBean);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/*
	 * 获得User类型的所有记录
	 */
	public static List<User> getAllUser(String sql, String[] str) {
		List<User> list = new ArrayList<>();
		try {
			ResultSet res = Connect.search(sql, str);
			while (res.next()) {
				String userName = res.getString(1);
				String userpassword = res.getString(2);
			    int bean = res.getInt(3);
			    String phone = res.getString(4);
				User user = new User(userName, userpassword, bean, phone);
				list.add(user);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/*
	 * 获得Excel导出类型
	 */
	public static List<DBCharge> getAllExcel(String sql, String[] str) {
		List<DBCharge> list = new ArrayList<>();
		try {
			ResultSet res = Connect.search(sql, str);
			while (res.next()) {
				String userName = res.getString(1);
				int totalMoney = res.getInt(2);
				DBCharge dbCharge = new DBCharge(userName, totalMoney);
				list.add(dbCharge);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 通过账号判断是否存在
	 */
	public static boolean isExist(String tableName, String userName) {
		try {
			ResultSet res = Connect.search("select * from " + tableName + " where username=?", new String[] { userName });
			if (res.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 通过删除账号
	 */
	public static void deleteUser(String tableName, String userName, String foreign) {
		String sql1 = "alter table " + tableName + " drop constraint " + foreign;
		String sql2 = "delete from " + tableName + " where username=?";
		String sql3 = "alter table " + tableName + " add constraint " + foreign + " foreign key(username) references user(username)";
		String[] str = new String[] { userName };
		Connect.add(sql1, null);
		Connect.add(sql2, str);
	    Connect.add(sql3, null);
	}
	
	public static void deleteUsername(String userName) {
		
	}


	/**
	 * 验证密码与账号是否相匹配
	 */
	public static boolean isTrue(String tableName, String userName, String password) {
		try {
			ResultSet res = Connect.search("select * from " + tableName + " where username=? and password=?",
					new String[] { userName, password });
			if (res.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static int getBean(String tableName, String userName) {
		try {
			ResultSet rs = Connect.search("select * from " + tableName + " where username=?", new String[] { userName });
			if (rs.next()) {
				return Integer.parseInt(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int getTotalMoney(String userName) {
		try {
			ResultSet rs = Connect.search("select sum(chargemoney) from record_charge where username =? group by username", new String[] { userName });
			if (rs.next()) {
				return Integer.parseInt(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;	
	}
	
	public static int updataBean(String tableName, String userName, int bean) {

		int a = Connect.add("update " + tableName + " set bean=? where username=?", new String[] { String.valueOf(bean), userName});
		return a;
	}
	
	public static int updataScore(String tableName, String userName, int score) {

		String time = ClockThread.getTime();
		int a = Connect.add("update " + tableName + " set score=?, scoretime=? where username=?", new String[] { String.valueOf(score), time, userName});
		return a;
	}
	
	public static int addUser(String userName, String password, String bean, String phone) {
		String sql = "insert into user (username,password,bean,phone) values(?,?,?,?)";
	    String[] str = new String[] {userName, password, bean, phone};
	    int a = Connect.add(sql, str);
	    return a;
	}
	
	public static int addScore(String tableName, String userName, int score) {

		String time = ClockThread.getTime();
		String sql = "insert into " + tableName + "(username,score,scoretime) values(?,?,?)";
	    String[] str = new String[] {userName, String.valueOf(score), time};
		int a = Connect.add(sql, str);
		return a;
	}
	
	public static int addChargeRecord(String userName, int money) {

		String time = ClockThread.getTime();
		String sql = "insert into record_charge (username,chargemoney,chargetime) values(?,?,?)";
	    String[] str = new String[] {userName, String.valueOf(money), time};
		int a = Connect.add(sql, str);
		return a;
	}
}
