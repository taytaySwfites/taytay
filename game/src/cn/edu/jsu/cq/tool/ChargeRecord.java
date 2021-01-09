package cn.edu.jsu.cq.tool;

public class ChargeRecord {
    private String username;
    private int chargemoney;
    private String chargetime;
    
    public ChargeRecord() {}
	public ChargeRecord(String username, int chargemoney, String chargetime) {
		this.username = username;
		this.chargemoney = chargemoney;
		this.chargetime = chargetime;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getChargemoney() {
		return chargemoney;
	}
	public void setChargemoney(int chargemoney) {
		this.chargemoney = chargemoney;
	}
	public String getChargetime() {
		return chargetime;
	}
	public void setChargetime(String chargetime) {
		this.chargetime = chargetime;
	}
}
