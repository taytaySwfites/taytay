package cn.edu.jsu.cq.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import cn.edu.jsu.cq.sql.ConnectService;
import cn.edu.jsu.cq.tool.Bean;
import cn.edu.jsu.cq.tool.ClockThread;
import cn.edu.jsu.cq.tool.ScoreHelper;
import cn.edu.jsu.cq.tool.RandomDemo;

public class BinaryMenu {
	JFrame frame;

	int count = 50; // 用户猜数字的机会
	int randNumber = RandomDemo.rand(100); // 系统随机生成数字
	int maxZone = 100; // 猜测数字的最大范围
	int minZone = 1; // 猜测数字的最小范围

	public BinaryMenu(String userName) {
		System.out.println("系统随机生成数字:" + randNumber);
		JPanel panel;

		// 背景图片
		ImageIcon backIcon = new ImageIcon("images/image08.jpg");// 加载图片
		JLabel backLabel = new JLabel(backIcon);// 将图片放入label中
		backLabel.setBounds(0, 0, 550, 600);// 设置label的大小

		frame = new JFrame();
		frame.setBounds(70, 70, 550, 600);
		frame.setLayout(null);
		frame.getLayeredPane().add(backLabel, new Integer(Integer.MIN_VALUE));// 获取窗口的第二层，将label放入

		panel = (JPanel) frame.getContentPane();// 获取frame的顶层容器
		panel.setOpaque(false);// 设置为透明

		// 标签
		JLabel textLabel = new JLabel("已生成随机数字，请猜测:");
		textLabel.setHorizontalAlignment(SwingConstants.CENTER);
		textLabel.setForeground(Color.black);
		textLabel.setFont(new Font("楷体", Font.PLAIN, 32));
		textLabel.setBounds(30, 120, 400, 40);
		frame.add(textLabel);

		JTextField guessTextField = new JTextField("");
		guessTextField.setFont(new Font("楷体", Font.PLAIN, 22));
		guessTextField.setBounds(120, 230, 150, 40);
		frame.add(guessTextField);

		// 标签1
		JLabel chanceLabel1 = new JLabel("");
		chanceLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		chanceLabel1.setForeground(Color.black);
		chanceLabel1.setFont(new Font("楷体", Font.PLAIN, 24));
		chanceLabel1.setBounds(10, 180, 400, 40);
		frame.add(chanceLabel1);
		
		// 提示范围标签
		JLabel messageLabel = new JLabel("猜测范围" + minZone + " -- " + maxZone);
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		messageLabel.setForeground(Color.black);
		messageLabel.setFont(new Font("楷体", Font.PLAIN, 26));
		messageLabel.setBounds(1, 320, 400, 40);
		frame.add(messageLabel);

		// 提示机会标签
		JLabel messageLabe2 = new JLabel("您还有" + count + "次机会！！！");
		messageLabe2.setHorizontalAlignment(SwingConstants.CENTER);
		messageLabe2.setForeground(Color.black);
		messageLabe2.setFont(new Font("楷体", Font.PLAIN, 26));
		messageLabe2.setBounds(10, 370, 400, 40);
		frame.add(messageLabe2);

		/*
		 * 确定按钮
		 */
		JButton ensureButton = new JButton("确定");
		ensureButton.setFont(new Font("楷体", Font.PLAIN, 16));
		ensureButton.setBounds(280, 230, 100, 40);
		//ensureButton.setContentAreaFilled(false);
		//ensureButton.setBorderPainted(false);
		frame.add(ensureButton);
		
		guessTextField.addKeyListener(new KeyAdapter() {//增加用户名输入框键盘事件焦点
			@Override
			  public void keyPressed(KeyEvent e) {//监听键盘键入内容
			    if(e.getKeyChar()==KeyEvent.VK_ENTER) {//如果是回车键
			    	ensureButton.doClick(); 
			   }
			}
		});

		// 把图片当成确定按钮，生成监听事件
		ensureButton.addActionListener(new ActionListener() {
			// 设置监听
			@Override
			public void actionPerformed(ActionEvent e) {
				String number = guessTextField.getText();
				if (Integer.parseInt(number) == randNumber && count >= 1) {
					if (count >= 45) {
						Bean.addUserBean(userName, Bean.getUserBean(userName) + 5);// 奖励5豆子
						JOptionPane.showMessageDialog(null, "您猜对了,奖励豆子5！！！");
					} else if (count >= 40) {
						Bean.addUserBean(userName, Bean.getUserBean(userName) + 3);// 奖励3豆子
						JOptionPane.showMessageDialog(null, "您猜对了,奖励豆子3！！！");
					} else if (count >= 30) {
						Bean.addUserBean(userName, Bean.getUserBean(userName) + 2);// 奖励2豆子
						JOptionPane.showMessageDialog(null, "您猜对了,奖励豆子2！！！");
					} else {
						JOptionPane.showMessageDialog(null, "您猜对了！！！");
					}
					// 如果是新成绩
					int score = count * 2;
					if (ScoreHelper.isNewScore("record_binary", userName, score)) {
						// 注册成提示界面
						Object[] options = { "加入", "不加入" };
						int i = JOptionPane.showOptionDialog(null, "新纪录，是否加入排行榜！", "加入排行榜?",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
						if (i == 0) {
							// 加入排行榜
							System.out.println("成绩 = " + score);
							ConnectService.updataScore("record_binary", userName, score);
						}
						frame.removeNotify();
						new ChooseMenu(userName);
					}
					else if(!ScoreHelper.isScoreExist("record_binary", userName)) {
						// 注册成提示界面
						Object[] options = { "加入", "不加入" };
						int i = JOptionPane.showOptionDialog(null, "新纪录，是否加入排行榜！", "加入排行榜?",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
						if (i == 0) {
							// 加入排行榜
							ConnectService.addScore("record_binary", userName, score);
						}
						frame.removeNotify();
						new ChooseMenu(userName);
					}
					else {// 如果不是新成绩就直接退出界面
					
						frame.removeNotify();
						new ChooseMenu(userName);
					}
					
				} else if (Integer.parseInt(number) > maxZone || Integer.parseInt(number) < minZone) {
					chanceLabel1.setText("超出范围!!!");
					count--;
					messageLabe2.setText("您还有" + count + "次机会！！！");
				} else {
					if (Integer.parseInt(number) > randNumber) {
						chanceLabel1.setText("比数字 " + Integer.parseInt(number) + " 要小哦!!!");
						maxZone = Integer.parseInt(number) - 1;
					}
					if (Integer.parseInt(number) < randNumber) {
						chanceLabel1.setText("比数字 " + Integer.parseInt(number) + " 要大哦!!!");
						minZone = Integer.parseInt(number) + 1;
					}
					/*
					if(maxZone == minZone) {
						count--;
						if (count >= 45) {
							Bean.addUserBean(userName, Bean.getUserBean(userName) + 5);// 奖励5豆子
							JOptionPane.showMessageDialog(null, "您猜对了,奖励豆子5！！！");
						} else if (count >= 40) {
							Bean.addUserBean(userName, Bean.getUserBean(userName) + 3);// 奖励3豆子
							JOptionPane.showMessageDialog(null, "您猜对了,奖励豆子3！！！");
						} else if (count >= 30) {
							Bean.addUserBean(userName, Bean.getUserBean(userName) + 2);// 奖励2豆子
							JOptionPane.showMessageDialog(null, "您猜对了,奖励豆子2！！！");
						} else {
							JOptionPane.showMessageDialog(null, "您猜对了！！！");
						}
						// 如果是新成绩
						int score = count * 2;
						if (ScoreHelper.isNewScore("record_binary", userName, score)) {
							// 注册成提示界面
							Object[] options = { "加入", "不加入" };
							int i = JOptionPane.showOptionDialog(null, "新纪录，是否加入排行榜！", "加入排行榜?",
									JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
									options[0]);
							if (i == 0) {
								// 加入排行榜
								ConnectService.updataScore("record_binary", userName, score);
							}
							frame.removeNotify();
							new ChooseMenu(userName);
						} else {// 如果不是新成绩就直接退出界面
							frame.removeNotify();
							new ChooseMenu(userName);
						}
					}
					*/
					messageLabel.setText("猜测范围" + minZone + " -- " + maxZone);
					count--;
					messageLabe2.setText("您还有" + count + "次机会！！！");
				}
			}

		});

		// 返回按钮
		JButton returnButton = new JButton("返回");
		returnButton.setFont(new Font("楷体", Font.PLAIN, 20));
		returnButton.setBounds(370, 430, 120, 40);
		frame.add(returnButton);
		//returnButton.setContentAreaFilled(false);
		//returnButton.setBorderPainted(false);

		returnButton.addActionListener(new ActionListener() {
			// 设置监听
			@Override
			public void actionPerformed(ActionEvent e) {
				if (count == 50) {
					Bean.addUserBean(userName, Bean.getUserBean(userName) + 1);// 如果没有进行游戏，不消耗豆子
				}
				// 关闭窗口释放屏幕资源
				frame.removeNotify();
				new ChooseMenu(userName);
			}
		});
		
		/*
		 * 查看数字按钮
		 */
		JLabel iconCheckNumber = new JLabel(new ImageIcon("images/image11.jpg"));// 将图片放入label中
		frame.add(iconCheckNumber);
		iconCheckNumber.setBounds(370, 8, 40, 40);

		JButton buttonCheck = new JButton("查看数字");
		buttonCheck.setFont(new Font("楷体", Font.PLAIN, 20));
		buttonCheck.setBounds(400, 10, 120, 40);
		frame.add(buttonCheck);
		buttonCheck.setContentAreaFilled(false);
		buttonCheck.setBorderPainted(false);
		
		buttonCheck.addActionListener(new ActionListener() {
			// 设置监听
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] options = { "是", "否" };
				int i = JOptionPane.showOptionDialog(null, "是否消耗7豆子，查看数字！", "查看数字", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				int bean = Bean.getUserBean(userName); // 获取用户豆子数量
				if (i == 0) {
					if (bean >= 7) {
						Bean.addUserBean(userName, bean - 7);
						JOptionPane.showMessageDialog(null, "数字为" + randNumber);
					} else {
						System.out.println("豆子不够!");
						JOptionPane.showMessageDialog(null, "豆子不够！！！");
					}
				}
			}
		});
		
		/*
		 * 重新开局按钮
		 */
		JLabel iconResist = new JLabel(new ImageIcon("images/image11.jpg"));// 将图片放入label中
		frame.add(iconResist);
		iconResist.setBounds(370, 48, 40, 40);
		
		JButton buttonResist = new JButton("重新开局");
		buttonResist.setFont(new Font("楷体", Font.PLAIN, 20));
		buttonResist.setBounds(400, 50, 120, 40);
		frame.add(buttonResist);
		buttonResist.setContentAreaFilled(false);
		buttonResist.setBorderPainted(false);
		
		/*
		 * 重新开始
		 */
		buttonResist.addActionListener(new ActionListener() {
			// 设置监听
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] options = { "是", "否" };
				int i = JOptionPane.showOptionDialog(null, "是否消耗1豆子，重新开始游戏！", "开始游戏？", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				int bean = Bean.getUserBean(userName); // 获取用户豆子数量
				if (i == 0) {
					if (bean >= 1) {
						if(count < 50) {
							Bean.addUserBean(userName, bean - 1);
						}
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
		 *  生成本地时间
		 */
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

		frame.setSize(550, 600); // 设置窗口大小
		frame.setLocation(500, 160); // 设置窗口位置
		frame.setTitle("二分猜数字"); // 设置窗口标题
		// frame.setAlwaysOnTop(true);// 设置窗口总是在最上方
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置界面和虚拟机一起关闭
		frame.setVisible(true);// 设置界面可显示
		frame.setResizable(false);
	}

	public static void main(String args[]) {
		new BinaryMenu("tay");
	}
}
