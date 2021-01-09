package cn.edu.jsu.cq.tool;

import java.util.List;

import javax.swing.table.AbstractTableModel;

/*
 * 将List集合写了Table
 * 使用的自定义TableModel
 */
public class UserBeanTableModel extends AbstractTableModel{
 
	private List<UserBean> users;//数据集合，即需要展示的数据集
	
	public UserBeanTableModel(List<UserBean> list){//构造方法，在创建对象时就将数据传入
		this.users = list;
	}
 
	@Override
	public int getRowCount() {//行数，即数据集合List的大小
		return this.users.size();
	}
 
	@Override
	public int getColumnCount() {
		return 3;//列数，固定值，即User类有几个字段就有几列
	}
 
	//这个方法是关键，给定行号及列号，返回一个元素。我们以List的下标为行号，列号对应User的不同字段
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return rowIndex+1+"";
		case 1:
			return this.users.get(rowIndex).getUsername();
		case 2:
			return this.users.get(rowIndex).getBean();
		default:
			return "-";
		}
	}
 
	//列名，给定列号，返回列名
	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "序号";
		case 1:
			return "账号";
		case 2:
			return "豆子";
		default:
			return "-";
		}
	}
 
	public List<UserBean> getUser() {
		return users;
	}
 
	public void setUser(List<UserBean> users) {
		this.users = users;
	}
 
}