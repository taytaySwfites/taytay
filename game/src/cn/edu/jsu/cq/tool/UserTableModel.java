package cn.edu.jsu.cq.tool;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class UserTableModel extends AbstractTableModel{
	 
	private List<User> user;//数据集合，即需要展示的数据集
	
	public UserTableModel(List<User> list){//构造方法，在创建对象时就将数据传入
		this.user = list;
	}
 
	@Override
	public int getRowCount() {//行数，即数据集合List的大小
		return this.user.size();
	}
 
	@Override
	public int getColumnCount() {
		return 5;//列数，固定值，即ChargeRecord类有几个字段就有几列
	}
 
	//这个方法是关键，给定行号及列号，返回一个元素。我们以List的下标为行号，列号对应ChargeRecord的不同字段
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return rowIndex+1+"";
		case 1:
			return this.user.get(rowIndex).getUsername();
		case 2:
			return this.user.get(rowIndex).getUserpassword();
		case 3:
			return this.user.get(rowIndex).getBean();
		case 4:
			return this.user.get(rowIndex).getPhone();
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
			return "密码";
		case 3:
			return "豆子";
		case 4:
			return "手机号码";
		default:
			return "-";
		}
	}
 
	public List<User> getUser() {
		return user;
	}
 
	public void setUser(List<User> user) {
		this.user = user;
	}	
}