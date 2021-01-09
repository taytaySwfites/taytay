package cn.edu.jsu.cq.test;

import java.util.Random;

import cn.edu.jsu.cq.sql.ConnectService;
import cn.edu.jsu.cq.tool.ClockThread;

public class insertData {

	private static String[] phoneHead = { "13", "15", "17", "18" };

	private static StringBuffer username;
	private static String password = "123";
	private static int bean;
	private static StringBuffer phone;
	private static int scoreRan; // 随机成绩
	private static int scoreBin; // 二分成绩
	private static int money;

	public static int rand(int low, int n) {
		Random rand = new Random();
		return rand.nextInt(n) + low;
	}

	public static void main(String args[]) {
		int i = 1;
		while (i <= 1) {
			int nameLength = rand(2,6);
			i++;

			int j = 1;
			username = new StringBuffer();
			while (j <= nameLength) {
				username.append((char) (rand(0, 26) + 'a'));
				j++;
			}
			if (ConnectService.isExist("user", username.toString())) {
				System.out.println("用户已存在");
				System.out.println();
				continue;
			}
			
			bean = rand(1, 100); //随机生成豆子 

			j = 3;
			phone = new StringBuffer(phoneHead[rand(0, 3)]);
			while (j <= 11) {
				j++;
				phone.append((char) (rand(0, 10) + '0'));
			}
			
			System.out.println("username = " + username + " password = " + password + " bean = " + bean + " phone = " + phone);

			//插入user表中
			ConnectService.addUser(username.toString(), password, String.valueOf(bean), phone.toString());
			
			//插入随机排行榜
			scoreRan = rand(0, 51) * 2;
			System.out.println("username = " + username + " scoreRan = " + scoreRan);
			ConnectService.addScore("record_random", username.toString(), scoreRan);
			
			//插入二分排行榜
			scoreBin = rand(0, 51) * 2;
			System.out.println("username = " + username + " scoreBin = " + scoreBin);
			ConnectService.addScore("record_binary", username.toString(), scoreBin);
			
			//插入充值表
			j = 1; // 生成四条以下的充值记录
			int total = rand(0, 4);
			while (j <= total) {
				money = rand(1, 4);
				System.out.println("username = " + username + " money = " + money);
				ConnectService.addChargeRecord(username.toString(), money);
				j++;
			}
			System.out.println(ClockThread.getTime());
		}
	}
}
