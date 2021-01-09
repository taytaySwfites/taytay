package cn.edu.jsu.cq.tool;

public class User {
    private String username;
    private String userpassword;
    private int bean;
    private String phone;
    
	public User(String username, String userpassword, String phone) {
		this.username = username;
		this.userpassword = userpassword;
		this.bean = 10; //ĞÂÕËºÅÔùËÍ10¸ö¶¹
		this.phone = phone;
	}
	
	public User(String username, String userpassword, int bean, String phone) {
		this.username = username;
		this.userpassword = userpassword;
		this.bean = bean;
		this.phone = phone;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public int getBean() {
		return bean;
	}
	public void setBean(int bean) {
		this.bean = bean;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
