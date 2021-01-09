package cn.edu.jsu.cq.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
import cn.edu.jsu.cq.tool.ScoreHelper;

public class ChooseMenu {
	JFrame frame;

	int bean = 0; // 用户豆子数量

	public ChooseMenu(String userName) {

		bean = Bean.getUserBean(userName); // 获取用户豆子数量

		// 背景图片
		ImageIcon backIcon = new ImageIcon("images/image08.jpg");// 加载图片
		JLabel backLabel = new JLabel(backIcon);// 将图片放入label中
		backLabel.setBounds(0, 0, 550, 600);// 设置label的大小

		frame = new JFrame();
		frame.setBounds(70, 70, 550, 600);
		frame.setLayout(null);
		frame.getLayeredPane().add(backLabel, new Integer(Integer.MIN_VALUE));// 获取窗口的第二层，将label放入

		JPanel panel = (JPanel) frame.getContentPane();// 获取frame的顶层容器
		panel.setOpaque(false);// 设置为透明

		// 标题
		JLabel labelChoose = new JLabel("请 选 择 :");
		labelChoose.setHorizontalAlignment(SwingConstants.CENTER);
		labelChoose.setForeground(Color.black);
		labelChoose.setFont(new Font("楷体", Font.PLAIN, 36));
		labelChoose.setBounds(100, 70, 200, 40);
		frame.add(labelChoose);

		/*
		 * 充值界面
		 */
		JLabel iconBean = new JLabel(new ImageIcon("images/image11.jpg"));// 将图片放入label中
		frame.add(iconBean);
		iconBean.setBounds(250, 23, 150, 40);

		JButton chargeBean = new JButton("充值-->");
		frame.add(chargeBean);
		chargeBean.setFont(new Font("楷体", Font.PLAIN, 16));
		chargeBean.setBounds(410, 55, 150, 40);
		chargeBean.setContentAreaFilled(false);
		chargeBean.setBorderPainted(false);

		JLabel labelBean = new JLabel("豆子数 :" + bean);
		labelBean.setHorizontalAlignment(SwingConstants.CENTER);
		labelBean.setFont(new Font("楷体", Font.PLAIN, 24));
		labelBean.setBounds(330, 25, 200, 40);
		frame.add(labelBean);

		chargeBean.addActionListener(new ActionListener() {
			// 设置监听
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.removeNotify();
				new ChargeDemo(userName); // 进入充值界面
			}
		});

		JLabel iconRandom = new JLabel(new ImageIcon("images/image12.jpg"));// 将图片放入label中
		frame.add(iconRandom);
		iconRandom.setBounds(100, 140, 40, 40);

		/*
		 * 跳转界面
		 */
		// 按钮
		JButton buttonRandom = new JButton("随机");
		buttonRandom.setFont(new Font("楷体", Font.PLAIN, 30));
		buttonRandom.setBounds(110, 140, 120, 40);
		frame.add(buttonRandom);
		buttonRandom.setContentAreaFilled(false);
		buttonRandom.setBorderPainted(false);

		buttonRandom.addActionListener(new ActionListener() {
			// 设置监听
			@Override
			public void actionPerformed(ActionEvent e) {

				Object[] options = { "是", "否" };
				int i = JOptionPane.showOptionDialog(null, "会消耗1豆子，是否要开始游戏！", "开始游戏？", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if (i == 0) {
					if (bean >= 1) {
						Bean.addUserBean(userName, bean - 1);
						System.out.println("消耗1豆子!");
						frame.removeNotify();
						new RandomMenu(userName);
					} else {
						System.out.println("豆子不够!");
						JOptionPane.showMessageDialog(null, "豆子不够！！！");
					}
				}
			}
		});

		/*
		 * 跳转到排行榜界面
		 */
		JButton buttonRanRank = new JButton("查看排行榜");
		buttonRanRank.setFont(new Font("楷体", Font.PLAIN, 18));
		buttonRanRank.setBounds(240, 140, 140, 40);
		frame.add(buttonRanRank);
		buttonRanRank.setContentAreaFilled(false);
		buttonRanRank.setBorderPainted(false);
		
		buttonRanRank.addActionListener(new ActionListener() {
			// 设置监听
			@Override
			public void actionPerformed(ActionEvent e) {
				new RandomRankDemo(userName);
				// 关闭窗口释放屏幕资源
				frame.removeNotify();
			}
		});
		
		
		JLabel iconBinary = new JLabel(new ImageIcon("images/image12.jpg"));// 将图片放入label中
		frame.add(iconBinary);
		iconBinary.setBounds(100, 220, 40, 40);

		/*
		 * 跳转界面
		 */
		JButton buttonBinary = new JButton("二分");
		buttonBinary.setFont(new Font("楷体", Font.PLAIN, 26));
		buttonBinary.setBounds(110, 220, 120, 40);
		frame.add(buttonBinary);
		buttonBinary.setContentAreaFilled(false);
		buttonBinary.setBorderPainted(false);

		buttonBinary.addActionListener(new ActionListener() {
			// 设置监听
			@Override
			public void actionPerformed(ActionEvent e) {

				Object[] options = { "是", "否" };
				int i = JOptionPane.showOptionDialog(null, "会消耗1豆子，是否要开始游戏！", "开始游戏？", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if (i == 0) {
					if (bean >= 10) {
						Bean.addUserBean(userName, bean - 1);
						System.out.println("消耗1豆子!");
						frame.removeNotify();
						new BinaryMenu(userName);
					} else {
						System.out.println("豆子不够!");
						JOptionPane.showMessageDialog(null, "豆子不够！！！");
					}
				}
			}
		});

		/*
		 * 跳转到排行榜界面
		 */
		JButton buttonBinRank = new JButton("查看排行榜");
		buttonBinRank.setFont(new Font("楷体", Font.PLAIN, 18));
		buttonBinRank.setBounds(240, 220, 150, 40);
		frame.add(buttonBinRank);
		buttonBinRank.setContentAreaFilled(false);
		buttonBinRank.setBorderPainted(false);
		
		buttonBinRank.addActionListener(new ActionListener() {
			// 设置监听
			@Override
			public void actionPerformed(ActionEvent e) {
				new BinaryRankDemo(userName);
				// 关闭窗口释放屏幕资源
				frame.removeNotify();
			}
		});
		

		/*
		 * 跳转到豆子排行榜
		 */
		JLabel iconBeanRank = new JLabel(new ImageIcon("images/image12.jpg"));// 将图片放入label中
		frame.add(iconBeanRank);
		iconBeanRank.setBounds(100, 300, 40, 40);

		/*
		 * 跳转界面
		 */
		JButton buttonBeanRank = new JButton("查看豆子排行榜");
		buttonBeanRank.setFont(new Font("楷体", Font.PLAIN, 26));
		buttonBeanRank.setBounds(115, 300, 250, 40);
		frame.add(buttonBeanRank);
		buttonBeanRank.setContentAreaFilled(false);
		buttonBeanRank.setBorderPainted(false);

		buttonBeanRank.addActionListener(new ActionListener() {
			// 设置监听
			@Override
			public void actionPerformed(ActionEvent e) {
				new BeanRankDemo(userName);
				// 关闭窗口释放屏幕资源
				frame.removeNotify();
			}
		});

		/*
		 * 退出按钮
		 */
		JButton logoutButton = new JButton("退出");
		logoutButton.setFont(new Font("楷体", Font.PLAIN, 24));
		logoutButton.setBounds(390, 430, 120, 40);
		frame.add(logoutButton);
		// logoutButton.setContentAreaFilled(false);
		// logoutButton.setBorderPainted(false);

		logoutButton.addActionListener(new ActionListener() {
			// 设置监听
			@Override
			public void actionPerformed(ActionEvent e) {
				// 关闭窗口释放屏幕资源
				frame.removeNotify();
				System.exit(0);
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
		frame.setTitle("请选择:");
		// 设置界面和虚拟机一起关闭
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置界面可显示
		frame.setVisible(true);
		// 设置界面可不可改变大小
		frame.setResizable(false);
	}

	public static void main(String args[]) {
		new ChooseMenu("tay"); // 测试
	}
}
