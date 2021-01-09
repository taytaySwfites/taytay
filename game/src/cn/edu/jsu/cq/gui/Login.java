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

import cn.edu.jsu.cq.gui.Register;
import cn.edu.jsu.cq.sql.ConnectService;
import cn.edu.jsu.cq.tool.ClockThread;
import cn.edu.jsu.cq.tool.MusicThread;

/*
 * @author taytay
 */

public class Login {
	/**
	* login 方法的简述
	* <p>login 界面及其跳转<br>
	* @return 没有返回值
	*/
	public static JFrame frame;

	public Login() {
		start();
	}

	public static void start() {
		JPanel panel1, panel2, panel3, panel4;
		JLabel label1;
        JLabel clockLabel; //时钟 
		
		JLabel label2, label3;
		JTextField jtf1;
		JPasswordField jpf1;

		JButton button1, button2, button3;
		
		// 背景图片
		ImageIcon backIcon = new ImageIcon("images/image08.jpg");// 加载图片
		JLabel backLabel = new JLabel(backIcon);// 将图片放入label中
		backLabel.setBounds(0, 0, 400, 350);// 设置label的大小

		JFrame frame = new JFrame();
		frame.setLayout(new GridLayout(4, 1));
		frame.getLayeredPane().add(backLabel, new Integer(Integer.MIN_VALUE));// 获取窗口的第二层，将label放入

		panel1 = (JPanel) frame.getContentPane();// 获取frame的顶层容器
		panel1.setOpaque(false);// 设置为透明

		/*
		 * 容器1 包含JLabel
		 */
		panel1 = new JPanel();
		label1 = new JLabel("我爱猜数字");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setForeground(Color.black);
		label1.setFont(new Font("楷体", Font.PLAIN, 42));
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
		// 设计对话框长度
		jtf1 = new JTextField(13);

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
		// 设计密码框的长度
		jpf1 = new JPasswordField(13);
		panel3.add(label3);
		panel3.add(jpf1);
		panel3.setOpaque(false);// 必须设置为透明的, 否则看不到图片
		frame.add(panel3);

		/*
		 * 容器4 包含JButton
		 */
		panel4 = new JPanel();
		button1 = new JButton("清除");
		button1.setFont(new Font("楷体", Font.PLAIN, 22));
		button1.setContentAreaFilled(false);
		button1.setBorderPainted(false);

		button2 = new JButton("登录");
		button2.setFont(new Font("楷体", Font.PLAIN, 22));
		button2.setContentAreaFilled(false);
		button2.setBorderPainted(false);
		
		button3 = new JButton("注册");
		button3.setFont(new Font("楷体", Font.PLAIN, 22));
		button3.setContentAreaFilled(false);
		button3.setBorderPainted(false);
		panel4.add(button1);
		panel4.add(button2);
		panel4.add(button3);
		panel4.setOpaque(false);// 必须设置为透明的, 否则看不到图片
		frame.add(panel4);
		
		clockLabel = new JLabel("Clock");
		clockLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		clockLabel.setFont(new Font("楷体", Font.PLAIN, 18));
		panel4.add(clockLabel);
		
		//多线程开始
		Thread t1 = new ClockThread(clockLabel);
		t1.start();
		
		/*
		 * 回车的设置
		 */
		jtf1.addKeyListener(new KeyAdapter() {//增加用户名输入框键盘事件焦点
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
			    	//button2.requestFocus();
			    	button2.doClick(); 
			   }
			}
		});
		
		/*
		 * 清除JButton中内容
		 */
		button1.addActionListener(new ActionListener() {
			// 设置监听
			@Override
			public void actionPerformed(ActionEvent e) {
				jtf1.setText(null);
				jpf1.setText(null);
			}
		});
		
		button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String tableName = "user";
                //获取用户名和密码
                String userName = jtf1.getText();
                String password = new String(jpf1.getPassword());
                
                if(userName.equals("admin") && password.equals("admin")){
                	//管理员界面,登录成功
                    JOptionPane.showMessageDialog(null, "管理员登录");              
                	frame.removeNotify();
                	new AdministratorDemo();
                }
                else if(userName.length() == 0 || password.length() == 0){
                    JOptionPane.showMessageDialog(null, "用户名密码不允许为空");
                }  
                else if(!ConnectService.isExist("user", userName)) {
                	JOptionPane.showMessageDialog(null, "用户不存在");
                }
                //匹配账户和密码
                else if(!ConnectService.isTrue(tableName, userName, password)){
                	JOptionPane.showMessageDialog(null, "用户名或密码错误");
                }
                else{
                	//用户界面,登录成功
                	JOptionPane.showMessageDialog(null, "登录成功");
                	frame.removeNotify();
                	new ChooseMenu(userName);
                }
          }
        });
		
		/*
		 * 跳转注册界面
		 */
		button3.addActionListener(new ActionListener() {
			// 设置监听
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.removeNotify();
				// 关闭窗口释放屏幕资源
				new Register();
			}
		});

		Toolkit took = Toolkit.getDefaultToolkit();// 设置窗口图标
		Image image = took.getImage("images/image02.jpg");
		frame.setIconImage(image);

		frame.setSize(400, 350); // 设置窗口大小
		frame.setLocation(500, 160); // 设置窗口位置
		frame.setTitle("Login"); // 设置窗口标题
		//frame.setAlwaysOnTop(true);// 设置窗口总是在最上方
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置界面和虚拟机一起关闭
		frame.setVisible(true);// 设置界面可显示
		frame.setResizable(false);
	}

	public static void main(String[] args) {
		
		// 音乐多线程开始
		Thread musicThread = new MusicThread();
		musicThread.start();
		
		new Login();
	}
}
