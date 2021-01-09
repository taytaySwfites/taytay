package cn.edu.jsu.cq.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import cn.edu.jsu.cq.sql.Connect;
import cn.edu.jsu.cq.sql.ConnectService;
import cn.edu.jsu.cq.tool.ClockThread;

public class Register {
	JFrame frame;
	int text_len = 15; //文本框文字长度
	public Register() {
		JPanel panel1, panel2, panel3, panel4, panel5;
		JLabel label1, label2, label3, label4;
		JTextField jtf1, jtf2;
		JPasswordField jpf1;
		JButton button1, button2, button3, button4;
		
		JLabel clockLabel; //时钟 
		
		frame = new JFrame();
		// 背景图片
		ImageIcon backIcon = new ImageIcon("images/image08.jpg");// 加载图片
		JLabel backLabel = new JLabel(backIcon);// 将图片放入label中
		backLabel.setBounds(0, 0, 500, 550);// 设置label的大小

		JFrame frame = new JFrame();
		frame.setLayout(new GridLayout(5, 1));
		frame.getLayeredPane().add(backLabel, new Integer(Integer.MIN_VALUE));// 获取窗口的第二层，将label放入

		panel1 = (JPanel) frame.getContentPane();// 获取frame的顶层容器
		panel1.setOpaque(false);// 设置为透明

		/*
		 * 容器1 包含JLabel
		 */
		panel1 = new JPanel();
		label1 = new JLabel("注 册 用 户");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setForeground(Color.black);
		label1.setFont(new Font("楷体", Font.PLAIN, 54));
		label1.setBackground(Color.black);
		panel1.add(label1);
		panel1.setOpaque(false);// 必须设置为透明的, 否则看不到图片
		frame.add(panel1);
		
		/*
		 * 容器2 输入用户名
		 */
		panel2 = new JPanel();
		label2 = new JLabel("用户名 : ");
		label2.setForeground(Color.black);
		label2.setFont(new Font("楷体", Font.PLAIN, 24));
		label2.setBackground(Color.black);
		jtf1 = new JTextField(16);// 设计对话框长度

		panel2.add(label2);
		panel2.add(jtf1);
		panel2.setOpaque(false);// 必须设置为透明的, 否则看不到图片
		frame.add(panel2);
		
		/*
		 * 容器3 输入密码
		 */
		panel3 = new JPanel();
		label3 = new JLabel("密码 : ");
		label3.setForeground(Color.black);
		label3.setFont(new Font("楷体", Font.PLAIN, 24));
		label3.setBackground(Color.black);
		jpf1 = new JPasswordField(16);// 设计密码框的长度
		panel3.add(label3);
		panel3.add(jpf1);
		panel3.setOpaque(false);// 必须设置为透明的, 否则看不到图片
		frame.add(panel3);
		
		/*
		 * 容器4 输入QQ号码
		 */
		panel4 = new JPanel();
		label4 = new JLabel("电话: ");
		label4.setForeground(Color.black);
		label4.setFont(new Font("楷体", Font.PLAIN, 24));
		label4.setBackground(Color.black);
		jtf2 = new JTextField(16);// 设计对话框长度

		panel4.add(label4);
		panel4.add(jtf2);
		panel4.setOpaque(false);// 必须设置为透明的, 否则看不到图片
		frame.add(panel4);
		
		/*
		 * 容器5 包含JButton
		 */
		panel5 = new JPanel();
		button1 = new JButton("清除");
		button1.setFont(new Font("楷体", Font.PLAIN, 22));
		button1.setContentAreaFilled(false);
		button1.setBorderPainted(false);
		/*
		 * 清除JButton中内容
		 */
		button1.addActionListener(new ActionListener() {
			// 设置监听
			@Override
			public void actionPerformed(ActionEvent e) {
				jtf1.setText(null);
				jtf2.setText(null);
				jpf1.setText(null);
			}
		});

		button2 = new JButton("注册");
		button2.setFont(new Font("楷体", Font.PLAIN, 22));
		button2.setContentAreaFilled(false);
		button2.setBorderPainted(false);
		
		/*
		 * 回车的设置
		 */
		jtf1.addKeyListener(new KeyAdapter() {//增加用户名输入框键盘事件焦点
			@Override
			  public void keyPressed(KeyEvent e) {//监听键盘键入内容
			    if(e.getKeyChar()==KeyEvent.VK_ENTER) {//如果是回车键
			    	jtf2.requestFocus();
			   }
			}
		});
		
		jtf2.addKeyListener(new KeyAdapter() {//增加用户名输入框键盘事件焦点
			@Override
			  public void keyPressed(KeyEvent e) {//监听键盘键入内容
			    if(e.getKeyChar()==KeyEvent.VK_ENTER) {//如果是回车键
			    	jpf1.requestFocus();
			   }
			}
		});
		
		jpf1.addKeyListener(new KeyAdapter() {//增加用户名输入框键盘事件焦点
			@Override
			  public void keyPressed(KeyEvent e) {//监听键盘键入内容
			    if(e.getKeyChar()==KeyEvent.VK_ENTER) {//如果是回车键
			    	button2.doClick(); 
			   }
			}
		});	
		
		
		/*
		 * 跳转注册界面
		 */
		button2.addActionListener(new ActionListener() {
			// 设置监听
			@Override
			public void actionPerformed(ActionEvent e) {
				String user = jtf1.getText();
                String password = new String(jpf1.getPassword());
                String phone = jtf2.getText();
                if(user.equals("admin")){
                	JOptionPane.showMessageDialog(null, "此账号名不可以被注册!", "错误", JOptionPane.ERROR_MESSAGE);
                }
                else if(!islen(user, text_len) || !islen(password, text_len)) {
                	JOptionPane.showMessageDialog(null, "用户账号,密码长度出错!", "错误", JOptionPane.ERROR_MESSAGE);
                }
                else if(!isNumber(phone) || phone.length() != 11) {
                	JOptionPane.showMessageDialog(null, "手机号码错误!", "错误", JOptionPane.ERROR_MESSAGE);
                }
                else {
                	String tableName = "user";
                	//不存在，就注册
                	if(!ConnectService.isExist(tableName, user)) {
                		ConnectService.addUser(user, password, "10", phone); //添加进数据库
                	    //注册成提示界面
                	    Object[] options = {"登录", "退出"};
                	    int i = JOptionPane.showOptionDialog(null, "注册成功！","注册提示框",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
						if(i == 0) {
							frame.removeNotify();
	                    	new Login();
						}
						else {
							System.exit(0);
						}
                	}
                	else {
                		// 存在就进入报错界面
						JOptionPane.showMessageDialog(null, "用户名已存在！", "错误", JOptionPane.ERROR_MESSAGE);	
                	}
                }	
			}
		});

		button3 = new JButton("退出");
		button3.setFont(new Font("楷体", Font.PLAIN, 22));
		button3.setContentAreaFilled(false);
		button3.setBorderPainted(false);
		/*
		 * 退出注册界面
		 */
		button3.addActionListener(new ActionListener() {
			// 设置监听
			@Override
			public void actionPerformed(ActionEvent e) {
				// 关闭窗口释放屏幕资源
				frame.removeNotify();
				System.exit(0);
			}
		});
		
		button4 = new JButton("返回");
		button4.setFont(new Font("楷体", Font.PLAIN, 22));
		button4.setContentAreaFilled(false);
		button4.setBorderPainted(false);
		/*
		 * 返回登录界面
		 */
		button4.addActionListener(new ActionListener() {
			// 设置监听
			@Override
			public void actionPerformed(ActionEvent e) {
				// 关闭窗口释放屏幕资源
				frame.removeNotify();	
				new Login();
			}
		});
		
		panel5.add(button1);
		panel5.add(button2);
		panel5.add(button3);
		panel5.add(button4);
		panel5.setOpaque(false);// 必须设置为透明的, 否则看不到图片
		frame.add(panel5);
		
		JLabel c = new JLabel("         ");
		clockLabel = new JLabel("Clock");
		clockLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		clockLabel.setFont(new Font("楷体", Font.PLAIN, 18));
		panel5.add(c);
		panel5.add(clockLabel);
		
		//多线程开始
		Thread t1 = new ClockThread(clockLabel);
		t1.start();
		
		
		Toolkit took = Toolkit.getDefaultToolkit();// 设置窗口图标
		Image image = took.getImage("images/image02.jpg");
		frame.setIconImage(image);

		frame.setSize(450, 550); // 设置窗口大小
		frame.setLocation(500, 160); // 设置窗口位置
		frame.setTitle("Register"); // 设置窗口标题
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置界面和虚拟机一起关闭
		frame.setVisible(true);// 设置界面可显示
		frame.setResizable(false);
	}
	
	public boolean islen(String str, int length) {
		if(str.length() <= length && str.length() > 0) return true;
		else return false;
	}
	
	public boolean islen(String str) {
		if(str.length() == 11) return true;
		else return false;
	}
	
	public boolean isNumber(String str) {
		for(int i = 0; i < str.length();) {
			if(str.charAt(i) >= '0' && str.charAt(i) <= '9') i++;
			else return false;
		}
		return true;
	}
}
