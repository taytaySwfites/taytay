package cn.edu.jsu.cq.tool;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JLabel;

public class ClockThread extends Thread {
	private JLabel clock;

	public ClockThread(JLabel clock) {
		this.clock = clock;
	}

	public void run() {
		while (true) {
			clock.setText(getTime());
			try {
				// 休眠一秒钟
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	//获取本地时间
	public static String getTime() {

		Calendar c = new GregorianCalendar();

		// 获取日期
		String time = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE) + " ";
		// 获取时刻
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		// 当时间不足10的时候，在个位数前加一个0，使时间整齐
		String ph = hour < 10 ? "0" : "";
		String pm = minute < 10 ? "0" : "";
		String ps = second < 10 ? "0" : "";

		time += ph + hour + ":" + pm + minute + ":" + ps + second;
		return time;
	}
}
