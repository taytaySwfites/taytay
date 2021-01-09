package cn.edu.jsu.cq.tool;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class RecordTableModel extends AbstractTableModel{
	 
	private List<Record> students;//数据集合，即需要展示的数据集
	
	public RecordTableModel(List<Record> list){//构造方法，在创建对象时就将数据传入
		this.students = list;
	}
 
	@Override
	public int getRowCount() {//行数，即数据集合List的大小
		return this.students.size();
	}
 
	@Override
	public int getColumnCount() {
		return 4;//列数，固定值，即Record类有几个字段就有几列
	}
 
	//这个方法是关键，给定行号及列号，返回一个元素。我们以List的下标为行号，列号对应Record的不同字段
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return rowIndex+1+"";
		case 1:
			return this.students.get(rowIndex).getUsername();
		case 2:
			return this.students.get(rowIndex).getScore();
		case 3:
			return this.students.get(rowIndex).getScoretime();
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
			return "成绩";
		case 3:
			return "创建成绩时间";
		default:
			return "-";
		}
	}
 
	public List<Record> getRecord() {
		return students;
	}
 
	public void setRecord(List<Record> students) {
		this.students = students;
	}	
}

