package cn.edu.jsu.cq.tool;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ChargeRecordTableModel extends AbstractTableModel{
	 
	private List<ChargeRecord> chargeRecord;//数据集合，即需要展示的数据集
	
	public ChargeRecordTableModel(List<ChargeRecord> list){//构造方法，在创建对象时就将数据传入
		this.chargeRecord = list;
	}
 
	@Override
	public int getRowCount() {//行数，即数据集合List的大小
		return this.chargeRecord.size();
	}
 
	@Override
	public int getColumnCount() {
		return 4;//列数，固定值，即ChargeRecord类有几个字段就有几列
	}
 
	//这个方法是关键，给定行号及列号，返回一个元素。我们以List的下标为行号，列号对应ChargeRecord的不同字段
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return rowIndex+1+"";
		case 1:
			return this.chargeRecord.get(rowIndex).getUsername();
		case 2:
			return this.chargeRecord.get(rowIndex).getChargemoney() + " 元";
		case 3:
			return this.chargeRecord.get(rowIndex).getChargetime();
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
			return "充值金额";
		case 3:
			return "充值时间";
		default:
			return "-";
		}
	}
 
	public List<ChargeRecord> getChargeRecord() {
		return chargeRecord;
	}
 
	public void setChargeRecord(List<ChargeRecord> chargeRecord) {
		this.chargeRecord = chargeRecord;
	}	
}