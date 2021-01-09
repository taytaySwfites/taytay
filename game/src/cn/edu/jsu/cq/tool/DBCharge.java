package cn.edu.jsu.cq.tool;

public class DBCharge {
    private String username;
    private int totalMoney;
    
    public DBCharge() {}
	public DBCharge(String username, int totalMoney) {
		this.username = username;
		this.totalMoney = totalMoney;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(int chargemoney) {
		this.totalMoney = chargemoney;
	}
}
