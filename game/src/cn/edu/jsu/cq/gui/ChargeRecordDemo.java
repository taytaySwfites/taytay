package cn.edu.jsu.cq.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import cn.edu.jsu.cq.sql.ConnectService;
import cn.edu.jsu.cq.tool.ChargeRecord;
import cn.edu.jsu.cq.tool.ChargeRecordTableModel;
import cn.edu.jsu.cq.tool.Record;
import cn.edu.jsu.cq.tool.RecordTableModel;

public class ChargeRecordDemo {
	private JFrame frame;
	private JPanel contentPane;// 定义窗体内容面板，放置各组件
	private JTable table;

	public ChargeRecordDemo(String userName) {
		frame = new JFrame();

		// 实例化内容面板
		contentPane = new JPanel();
		// 设置面板布局为绝对布局
		contentPane.setLayout(null);
		// 设置面板边框
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		// 设置滚动面板
		JScrollPane scrollPane = new JScrollPane();
		// 设置大小与位置
		scrollPane.setBounds(40,80, 470, 400);
		//设置滚动条的默认大小
		scrollPane.setPreferredSize(new Dimension(100,100));	
		// 将滚动面板加入到内容面板中
		contentPane.add(scrollPane);

		// 将所有记录读出
	    List<ChargeRecord> list = ConnectService.getAllChargeRecord("select * from record_charge where userName=? order by chargetime desc", new String[] { userName });
        
		// 创建StudentTableModel，传入内容数据集合
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
		
		/*
		 * 图片
		 */
		JLabel chargeIcon01 = new JLabel(new ImageIcon("images/image11.jpg"));// 将图片放入label中
		frame.add(chargeIcon01);
		chargeIcon01.setBounds(50, 35, 40, 40);

		/*
		 * 标题JLabel
		 */
		JLabel titleLabel = new JLabel("- 充值记录 -");
		titleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		titleLabel.setFont(new Font("楷体", Font.PLAIN, 23));
		titleLabel.setBounds(60, 35, 200, 40);
		frame.add(titleLabel);
		
		/*
		 * 提示充值金额
		 */
		JLabel totalMoneyLabel = new JLabel("您总共消费: " + ConnectService.getTotalMoney(userName) +  " 元");
		totalMoneyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalMoneyLabel.setFont(new Font("楷体", Font.PLAIN, 23));
		totalMoneyLabel.setBounds(50, 500, 200, 40);
		frame.add(totalMoneyLabel);

		/*
		 *  返回按钮
		 */
		JButton returnButton = new JButton("返回");
		returnButton.setFont(new Font("楷体", Font.PLAIN, 20));
		returnButton.setBounds(370, 500, 120, 40);
		frame.add(returnButton);
		//returnButton.setContentAreaFilled(false);
		//returnButton.setBorderPainted(false);

		returnButton.addActionListener(new ActionListener() {
			// 设置监听
			@Override
			public void actionPerformed(ActionEvent e) {
				// 关闭窗口释放屏幕资源
				frame.removeNotify();
				new ChargeDemo(userName);
			}
		});

		// 设置窗口图标
		Toolkit took = Toolkit.getDefaultToolkit();
		Image image = took.getImage("images/image02.jpg");
		frame.setIconImage(image);
		
		
		frame.setIconImage(image);
		frame.setSize(550, 600); // 设置窗口大小
		frame.setLocation(500, 160); // 设置窗口位置
		frame.setTitle(userName + "的充值记录"); // 设置窗口标题
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置界面和虚拟机一起关闭
		frame.setVisible(true);// 设置界面可显示
		frame.setResizable(false);
	}

	public static void main(String args[]) {
		new ChargeRecordDemo("tay");
	}
}
