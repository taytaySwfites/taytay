package cn.edu.jsu.cq.tool;

import cn.edu.jsu.cq.sql.ConnectService;

public class Bean {
	static String tableName = "user";
	public static int getUserBean(String userName) {
		int bean = ConnectService.getBean(tableName, userName);
		System.out.println("用户豆子数:" + bean);
		return bean;
	}
	
	public static void addUserBean(String userName, int bean) {
		ConnectService.updataBean(tableName, userName, bean);
	}
}
