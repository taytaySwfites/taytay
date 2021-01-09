package cn.edu.jsu.cq.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import cn.edu.jsu.cq.sql.ConnectService;
import cn.edu.jsu.cq.tool.Bean;
import cn.edu.jsu.cq.tool.ClockThread;

public class ChargeDemo {
	JFrame frame;

	int bean = 0; // 用户豆子数量

	public ChargeDemo(String userName) {
		bean = Bean.getUserBean(userName); // 获取用户豆子数量

		// 背景图片
		ImageIcon backIcon = new ImageIcon("images/image08.jpg");// 加载图片
		JLabel backLabel = new JLabel(backIcon);// 将图片放入label中
		backLabel.setBounds(0, 0, 550, 600);// 设置label的大小

		frame = new JFrame();
		frame.setBounds(70, 70, 1300, 650);
		frame.setLayout(null);
		frame.getLayeredPane().add(backLabel, new Integer(Integer.MIN_VALUE));// 获取窗口的第二层，将label放入

		JPanel panel = (JPanel) frame.getContentPane();// 获取frame的顶层容器
		panel.setOpaque(false);// 设置为透明

		// 显示当前豆子数量
		JLabel iconBean = new JLabel(new ImageIcon("images/image11.jpg"));// 将图片放入label中
		frame.add(iconBean);
		iconBean.setBounds(315, 25, 30, 30);

		JLabel labelBean = new JLabel("豆子数 :" + bean);
		labelBean.setHorizontalAlignment(SwingConstants.CENTER);
		labelBean.setFont(new Font("楷体", Font.PLAIN, 24));
		labelBean.setBounds(345, 20, 170, 40);
		frame.add(labelBean);

		/*
		 * 查看用户消费记录
		 */
		JLabel iconCharge = new JLabel(new ImageIcon("images/image11.jpg"));// 将图片放入label中
		frame.add(iconCharge);
		iconCharge.setBounds(360, 70, 30, 30);

		JButton buttonChargeRecord = new JButton("查看消费记录");
		buttonChargeRecord.setHorizontalAlignment(SwingConstants.CENTER);
		buttonChargeRecord.setFont(new Font("楷体", Font.PLAIN, 20));
		buttonChargeRecord.setBounds(360, 65, 200, 40);
		buttonChargeRecord.setContentAreaFilled(false);
		buttonChargeRecord.setBorderPainted(false);
		frame.add(buttonChargeRecord);
		
		/*
		 * 跳转到消费记录界面
		 */
		buttonChargeRecord.addActionListener(new ActionListener() {
			// 设置监听
			@Override
			public void actionPerformed(ActionEvent e) {
				// 关闭窗口释放屏幕资源
				frame.removeNotify();
				new ChargeRecordDemo(userName);
			}
		});

		//标题
		JLabel labelMoney = new JLabel("充值金额 :");
		labelMoney.setForeground(Color.black);
		labelMoney.setFont(new Font("楷体", Font.PLAIN, 36));
		labelMoney.setBounds(60, 120, 200, 40);
		frame.add(labelMoney);

		/*
		 * 1元
		 */
		JLabel chargeIcon01 = new JLabel(new ImageIcon("images/qq01.jpg"));// 将图片放入label中
		frame.add(chargeIcon01);
		chargeIcon01.setBounds(80, 190, 40, 40);

		JButton chargeButton01 = new JButton("1元");
		chargeButton01.setFont(new Font("楷体", Font.PLAIN, 32));
		chargeButton01.setBounds(100, 190, 120, 40);
		frame.add(chargeButton01);
		chargeButton01.setContentAreaFilled(false);
		chargeButton01.setBorderPainted(false);

		chargeButton01.addActionListener(new ActionListener() {
			// 设置监听
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "充值1元成功,获得5豆子!");
				bean += 5; // 充值获得5豆子
				Bean.addUserBean(userName, bean);
				ConnectService.addChargeRecord(userName, 1);//添加充值记录
				labelBean.setText("豆子数 :" + bean);
			}
		});

		/*
		 * 2元
		 */
		JLabel chargeIcon02 = new JLabel(new ImageIcon("images/qq02.jpg"));// 将图片放入label中
		frame.add(chargeIcon02);
		chargeIcon02.setBounds(290, 190, 40, 40);

		JButton chargeButton02 = new JButton("2元");
		chargeButton02.setFont(new Font("楷体", Font.PLAIN, 32));
		chargeButton02.setBounds(310, 190, 120, 40);
		frame.add(chargeButton02);
		chargeButton02.setContentAreaFilled(false);
		chargeButton02.setBorderPainted(false);

		chargeButton02.addActionListener(new ActionListener() {
			// 设置监听
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "充值2元成功,获得10豆子!");
				bean += 10; // 充值获得10豆子
				Bean.addUserBean(userName, bean);
				ConnectService.addChargeRecord(userName, 2);//添加充值记录
				labelBean.setText("豆子数 :" + bean);
			}
		});

		/*
		 * 3元
		 */
		JLabel chargeIcon03 = new JLabel(new ImageIcon("images/qq03.jpg"));// 将图片放入label中
		frame.add(chargeIcon03);
		chargeIcon03.setBounds(80, 310, 40, 40);

		JButton chargeButton03 = new JButton("3元");
		chargeButton03.setFont(new Font("楷体", Font.PLAIN, 32));
		chargeButton03.setBounds(100, 310, 120, 40);
		frame.add(chargeButton03);
		chargeButton03.setContentAreaFilled(false);
		chargeButton03.setBorderPainted(false);

		chargeButton03.addActionListener(new ActionListener() {
			// 设置监听
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "充值3元成功,获得15豆子!");
				bean += 15; // 充值获得15豆子
				Bean.addUserBean(userName, bean);
				ConnectService.addChargeRecord(userName, 3);//添加充值记录
				labelBean.setText("豆子数 :" + bean);
			}
		});

		/*
		 * 4元
		 */
		JLabel chargeIcon04 = new JLabel(new ImageIcon("images/qq04.jpg"));// 将图片放入label中
		frame.add(chargeIcon04);
		chargeIcon04.setBounds(290, 310, 40, 40);

		JButton chargeButton04 = new JButton("4元");
		chargeButton04.setFont(new Font("楷体", Font.PLAIN, 32));
		chargeButton04.setBounds(310, 310, 120, 40);
		frame.add(chargeButton04);
		chargeButton04.setContentAreaFilled(false);
		chargeButton04.setBorderPainted(false);

		chargeButton04.addActionListener(new ActionListener() {
			// 设置监听
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "充值4元成功,获得20豆子!");
				bean += 20; // 充值获得20豆子
				Bean.addUserBean(userName, bean);
				ConnectService.addChargeRecord(userName, 4);//添加充值记录
				labelBean.setText("豆子数 :" + bean);
			}
		});

		// 返回按钮
		JButton returnButton = new JButton("返回");
		returnButton.setFont(new Font("楷体", Font.PLAIN, 20));
		returnButton.setBounds(350, 430, 120, 40);
		frame.add(returnButton);
		// returnButton.setContentAreaFilled(false);
		// returnButton.setBorderPainted(false);

		returnButton.addActionListener(new ActionListener() {
			// 设置监听
			@Override
			public void actionPerformed(ActionEvent e) {
				// 关闭窗口释放屏幕资源
				frame.removeNotify();
				new ChooseMenu(userName);
			}
		});

		// 生成本地时间
		JLabel clockLabel = new JLabel("Clock");
		clockLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		clockLabel.setFont(new Font("楷体", Font.PLAIN, 20));
		clockLabel.setBounds(290, 490, 200, 40);
		frame.add(clockLabel);
		// 多线程开始
		Thread t1 = new ClockThread(clockLabel);
		t1.start();

		// 设置窗口图标
		Toolkit took = Toolkit.getDefaultToolkit();
		Image image = took.getImage("images/image02.jpg");
		frame.setIconImage(image);

		// 设置窗口大小
		frame.setSize(550, 600);
		// 设置窗口位置
		frame.setLocation(500, 160);
		// 设置窗口标题
		frame.setTitle("充值界面");
		// 设置界面和虚拟机一起关闭
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置界面可显示
		frame.setVisible(true);
		// 设置界面可不可改变大小
		frame.setResizable(false);
	}

	public static void main(String args[]) {
		new ChargeDemo("tay");
	}
}
