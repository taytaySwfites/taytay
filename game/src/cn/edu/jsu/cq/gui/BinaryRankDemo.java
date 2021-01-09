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
import cn.edu.jsu.cq.tool.Record;
import cn.edu.jsu.cq.tool.RecordTableModel;
import cn.edu.jsu.cq.tool.UserBean;

public class BinaryRankDemo {
	private JFrame frame;
	private JPanel contentPane;// 定义窗体内容面板，放置各组件
	private JTable table;

	public BinaryRankDemo(String userName) {
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
	    List<Record> list = ConnectService.getAllRecord("select * from record_binary order by score desc, scoretime asc limit 100");

		// 创建StudentTableModel，传入内容数据集合
		RecordTableModel tableModel = new RecordTableModel(list);
		// 使用接受TableModel参数的构造方法创建JTable
		table = new JTable(tableModel);
		// 设置第一列的列宽
	    table.getColumnModel().getColumn(1).setPreferredWidth(200);
		// 设置第三列的列宽
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
		JLabel titleLabel = new JLabel("- 二分排行榜 -");
		titleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		titleLabel.setFont(new Font("楷体", Font.PLAIN, 23));
		titleLabel.setBounds(60, 35, 200, 40);
		frame.add(titleLabel);
			
		/*
		 * 提示排名JLabel
		 */
		JLabel rankLabel = new JLabel();
		//获取排名
		int rank = rankInHundred(list, userName);
		if(rank == 101) {
			rankLabel.setText("您未入榜哦,继续往前冲吧!");
		}
		else {
			rankLabel.setText("您的排名: " + rank);
		}
		//rankLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		rankLabel.setFont(new Font("楷体", Font.PLAIN, 23));
		rankLabel.setBounds(70, 500, 300, 40);
		frame.add(rankLabel);

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
				new ChooseMenu(userName);
			}
		});

		// 设置窗口图标
		Toolkit took = Toolkit.getDefaultToolkit();
		Image image = took.getImage("images/image02.jpg");
		frame.setIconImage(image);
				
		frame.setSize(550, 600); // 设置窗口大小
		frame.setLocation(500, 160); // 设置窗口位置
		frame.setTitle("二分模式积分排行榜"); // 设置窗口标题
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置界面和虚拟机一起关闭
		frame.setVisible(true);// 设置界面可显示
		frame.setResizable(false);
	}
	
	/*
	 * 返回100以内排名
	 */
	private int rankInHundred(List<Record> list, String userName) {
		
		int rank = 0;
		for(Record user : list) {
			rank++;
			if(user.getUsername().equals(userName)) {
				return rank;
			}
		}
		/*
		 * 101 代表未进入排行榜
		 */
		return 101;
	}

	public static void main(String args[]) {
		new BinaryRankDemo("tay");
	}
}
