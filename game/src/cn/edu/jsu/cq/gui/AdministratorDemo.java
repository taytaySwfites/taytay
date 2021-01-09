package cn.edu.jsu.cq.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import cn.edu.jsu.cq.sql.Connect;
import cn.edu.jsu.cq.sql.ConnectService;
import cn.edu.jsu.cq.tool.ChargeRecord;
import cn.edu.jsu.cq.tool.ChargeRecordTableModel;
import cn.edu.jsu.cq.tool.DBToExcel;
import cn.edu.jsu.cq.tool.ExcelToDB;
import cn.edu.jsu.cq.tool.User;
import cn.edu.jsu.cq.tool.UserTableModel;

public class AdministratorDemo {
	public static JFrame frame;
	private JPanel contentPane;// 定义窗体内容面板，放置各组件
	private static JTable table;
	private static JScrollPane scrollPane;

	public AdministratorDemo() {
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
		
		contentPane = new JPanel();
		// 设置面板布局为绝对布局
		contentPane.setLayout(null);
		// 设置面板边框
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		// 设置滚动面板
		scrollPane = new JScrollPane();
		// 设置大小与位置
		scrollPane.setBounds(20, 30, 560, 420);
		// 设置滚动条的默认大小
		scrollPane.setPreferredSize(new Dimension(200, 100));
		// 将滚动面板加入到内容面板中
		contentPane.add(scrollPane);

		restore("select * from user order by bean desc", null);
		
		/*
		 * 创建一个菜单栏
		 */
		JMenuBar menuBar = new JMenuBar();

		/*
		 * 创建一级菜单
		 */
		JMenu fileMenu = new JMenu("文件");
		JMenu deleteMenu = new JMenu("删除");
		JMenu updataMenu = new JMenu("修改");
		JMenu checkMenu = new JMenu("查询");
		// 一级菜单添加到菜单栏
		menuBar.add(fileMenu);
		menuBar.add(deleteMenu);
		menuBar.add(updataMenu);
		menuBar.add(checkMenu);

		/*
		 * 创建 "文件" 一级菜单的子菜单
		 */
		JMenuItem newMenuItem = new JMenuItem("导出消费记录表");
		JMenuItem insertItem = new JMenuItem("导入用户消费表记录表");
		JMenuItem chargeItem = new JMenuItem("查看消费记录表");
		JMenuItem userItem = new JMenuItem("查看用户记录表");
		JMenuItem exitMenuItem = new JMenuItem("退出");
		
		// 子菜单添加到一级菜单
		fileMenu.add(newMenuItem);
		fileMenu.add(insertItem);
		fileMenu.addSeparator(); // 添加一条分割线
		fileMenu.add(chargeItem);
		fileMenu.add(userItem);
		fileMenu.addSeparator(); // 添加一条分割线
		fileMenu.add(exitMenuItem);

		/*
		 * 创建 "删除" 一级菜单的子菜单
		 */
		JMenuItem deleteMenuItem = new JMenuItem("删除账号");
		// 子菜单添加到一级菜单
		deleteMenu.add(deleteMenuItem);

		/*
		 * 创建 "修改" 一级菜单的子菜单
		 */
		JMenuItem passwordItem = new JMenuItem("修改密码");
		JMenuItem beanItem = new JMenuItem("修改豆子");
		JMenuItem phonemailItem = new JMenuItem("修改手机号码");
		
		// 子菜单添加到一级菜单
		updataMenu.add(passwordItem);
		updataMenu.add(beanItem);
		updataMenu.add(phonemailItem);
		
		/*
		 * 创建 "查询" 一级菜单的子菜单
		 */
		JMenuItem checkUserItem = new JMenuItem("在账号表的记录");
		JMenuItem checkChargeItem = new JMenuItem("在消费表的记录");
		
		// 子菜单添加到一级菜单
		checkMenu.add(checkUserItem);
		checkMenu.add(checkChargeItem);
		
		/*
		 * 图片
		 */
		JLabel iconBean = new JLabel(new ImageIcon("images/image11.jpg"));// 将图片放入label中
		frame.add(iconBean);
		iconBean.setBounds(40, 470, 40, 40);


		JLabel labelBean = new JLabel("账号:");
		labelBean.setHorizontalAlignment(SwingConstants.CENTER);
		labelBean.setFont(new Font("楷体", Font.PLAIN, 24));
		labelBean.setBounds(30, 470, 170, 40);
		frame.add(labelBean);
		
		JTextField searchText = new JTextField();
		searchText.setFont(new Font("楷体", Font.PLAIN, 24));
		searchText.setBounds(170, 470, 280, 40);
		frame.add(searchText);
		
        /*
         * 导出Excel文件
         */
		newMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = new String(
						JOptionPane.showInputDialog(null, "请输入Excel名\n", "输入Excel名", JOptionPane.PLAIN_MESSAGE));
				if(isFileName(name)) {
					DBToExcel.toWriteExcel(name);
					JOptionPane.showMessageDialog(null, "已导出在D盘");
				}
				else {
					JOptionPane.showMessageDialog(null, "Excel名错误");
				}
			}
		});
		
		/*
         * 导入Excel文件
         */
		insertItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = new String(
						JOptionPane.showInputDialog(null, "请输入Excel名\n", "输入Excel名", JOptionPane.PLAIN_MESSAGE));
				if(isFileName(name) && isExist(name)) {
					// 返回有几条重复或者出错的数据
					int count = ExcelToDB.toReadExcel(name);
					JOptionPane.showMessageDialog(null, "已导入数据库");
					if(count > 0) {
						JOptionPane.showMessageDialog(null, "有 " + count + " 条重复或者出错的数据");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Excel名错误");
				}
			}
		});
		
		chargeItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				load("select * from record_charge order by chargetime desc, chargemoney desc", null);//隐藏上一个表
			}
		});
		
		userItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				restore("select * from user order by bean desc", null);//隐藏上一个表
			}
		});

		// 设置 "退出" 子菜单被点击的监听器
		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "管理员退出");
				System.exit(0);
			}
		});

		/*
		 * 如果触碰删除按钮
		 */
		deleteMenuItem.addActionListener(new ActionListener() {
			// 设置监听
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();//// 获取你选中的行号（记录）
				String textWord = table.getValueAt(row, 1).toString();// 读取你获取行号的某一列的值（也就是字段）

				// 删除外键，删除值，添加外键
				ConnectService.deleteUser("record_binary", textWord, "usernameBin");
				// 删除外键，删除值，添加外键
				ConnectService.deleteUser("record_random", textWord, "usernameRan");
				// 删除外键，删除值，添加外键
				ConnectService.deleteUser("record_charge", textWord, "chargeusername");
				String sql2 = "delete from user where username=?";
				Connect.add(sql2, new String[] { textWord });
				restore("select * from user order by bean desc", null);
			}
		});

		/*
		 * 设置 "删除" 子菜单被点击的监听器
		 */
		// 修改密码
		passwordItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String password = new String(
						JOptionPane.showInputDialog(null, "请输入需要修改的密码:\n", "修改信息", JOptionPane.PLAIN_MESSAGE));
				if (password.equals(null)) {
					JOptionPane.showMessageDialog(null, "管理员登录");
				} else {
					int row = table.getSelectedRow();//// 获取你选中的行号（记录）
					String username = table.getValueAt(row, 1).toString();// 读取你获取行号的某一列的值（也就是字段）
					String sql = "update user set password=? where username=?";
					Connect.add(sql, new String[] { password, username });
					restore("select * from user order by bean desc", null);
				}
			}
		});

		// 修改豆子
		beanItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String bean = new String(
						JOptionPane.showInputDialog(null, "请输入需要修改的豆子密码:\n", "修改信息", JOptionPane.PLAIN_MESSAGE));
				if (bean.equals(null)) {
					JOptionPane.showMessageDialog(null, "管理员登录");
				} else {
					int row = table.getSelectedRow();//// 获取你选中的行号（记录）
					String username = table.getValueAt(row, 1).toString();// 读取你获取行号的某一列的值（也就是字段）
					String sql = "update user set bean=? where username=?";
					Connect.add(sql, new String[] { bean, username });
					restore("select * from user order by bean desc", null);
				}
			}
		});

		// 修改手机号码
		phonemailItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String phone = new String(
						JOptionPane.showInputDialog(null, "请输入需要修改的手机号码:\n", "修改信息", JOptionPane.PLAIN_MESSAGE));
				if (phone.equals(null)) {
					JOptionPane.showMessageDialog(null, "管理员登录");
				} else {
					int row = table.getSelectedRow();//// 获取你选中的行号（记录）
					String username = table.getValueAt(row, 1).toString();// 读取你获取行号的某一列的值（也就是字段）
					String sql = "update user set phone=? where username=?";
					Connect.add(sql, new String[] { phone, username });
					restore("select * from user order by bean desc", null);
				}
			}
		});

		/*
		 * 查询用户记录表的记录
		 */
		checkUserItem.addActionListener(new ActionListener() {
			// 设置监听
			@Override
			public void actionPerformed(ActionEvent e) {
				String userName = searchText.getText();
				restore("select * from user where username =?", new String[] { userName });
			}
		});
		
		/*
		 * 查询消费记录表的记录
		 */
		checkChargeItem.addActionListener(new ActionListener() {
			// 设置监听
			@Override
			public void actionPerformed(ActionEvent e) {
				String userName = searchText.getText();
				load("select * from record_charge where username =? order by chargetime desc, chargemoney desc", new String[] { userName });
			}
		});
		

		/*
		 * 最后 把菜单栏设置到窗口
		 */
		frame.setJMenuBar(menuBar);
		// 实例化内容面板
		

		// 设置窗口图标
		Toolkit took = Toolkit.getDefaultToolkit();
		Image image = took.getImage("images/image02.jpg");
		frame.setIconImage(image);

		// 设置窗口大小
		frame.setSize(600, 600);
		// 设置窗口位置
		frame.setLocation(500, 160);
		// 设置窗口标题
		frame.setTitle("管理员界面");
		// 设置界面和虚拟机一起关闭
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置界面可显示
		frame.setVisible(true);
		// 设置界面可不可改变大小
		frame.setResizable(false);
	}

	private static void restore(String sql, String[] str) {
		// 将所有记录读出
		List<User> list = ConnectService.getAllUser(sql, str);

		// 创建UserTableModel，传入内容数据集合
		UserTableModel tableModel = new UserTableModel(list);
		// 使用接受TableModel参数的构造方法创建JTable
		table = new JTable(tableModel);
		// 设置第一列的列宽
		table.getColumnModel().getColumn(1).setPreferredWidth(180);
		// 设置第二列的列宽
		table.getColumnModel().getColumn(2).setPreferredWidth(180);
		// 设置第四列的列宽
		table.getColumnModel().getColumn(4).setPreferredWidth(250);

		// 单元格渲染器
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		// 居中显示
		tcr.setHorizontalAlignment(JLabel.CENTER);
		// 设置渲染器
		table.setDefaultRenderer(Object.class, tcr);

		// 将table放入scrollPanel
		scrollPane.add(table);

		table.setFont(new Font("楷体", Font.PLAIN, 16));
		table.setRowHeight(30);// 设置行高
		scrollPane.setViewportView(table);// 设置使用滚动面板显示表格，如果不使用滚动面板显示，则表格的列标题无法显示
	}
	
	private static void load(String sql, String[] str) {
		// 将所有记录读出
	    List<ChargeRecord> list = ConnectService.getAllChargeRecord(sql, str);

		// 创建ChargeRecordTableModel，传入内容数据集合
	    ChargeRecordTableModel tableModel = new ChargeRecordTableModel(list);
		// 使用接受TableModel参数的构造方法创建JTable
		table = new JTable(tableModel);
		// 设置第一列的列宽
	    table.getColumnModel().getColumn(1).setPreferredWidth(200);
		// 设置第四列的列宽
		table.getColumnModel().getColumn(3).setPreferredWidth(220);

		// 单元格渲染器
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		// 居中显示
		tcr.setHorizontalAlignment(JLabel.CENTER);
		// 设置渲染器
		table.setDefaultRenderer(Object.class, tcr);

		// 将table放入scrollPanel
		scrollPane.add(table);
		
		table.setFont(new Font("楷体", Font.PLAIN, 16));
		table.setRowHeight(30);// 设置行高
	    scrollPane.setViewportView(table);// 设置使用滚动面板显示表格，如果不使用滚动面板显示，则表格的列标题无法显示
	}
	
	private static boolean isFileName(String fileName) {
		if(fileName.length() > 0 && fileName.length() < 255) {
			for(int i = 0; i < fileName.length(); i++) {
				if(fileName.charAt(i) == '\\' && fileName.charAt(i) == '/' &&fileName.charAt(i) == ':' && fileName.charAt(i) == '*' && fileName.charAt(i) == '?' && fileName.charAt(i) == '#' &&fileName.charAt(i) == '”' && fileName.charAt(i) == '<' && fileName.charAt(i) == '>' && fileName.charAt(i) == '|') {
					return false;
				}
			}
		}
		else {
			return false;
		}
		return true; 
	}
	
	private static boolean isExist(String name) {
		
		StringBuffer fileName = new StringBuffer("D:\\");
		// 创建Excel名
		fileName.append(name);
		fileName.append(".xls");
					
		// 打开文件
		File file = new File(fileName.toString());
		// 查看文件是否存在
		if(!file.exists()) {
			// 不存在创建, 就返回出错
			return false;
		}
		return true;
	}

	public static void main(String args[]) {
		new AdministratorDemo();
	}
}
