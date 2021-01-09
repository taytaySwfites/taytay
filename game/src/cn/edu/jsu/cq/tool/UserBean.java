package cn.edu.jsu.cq.tool;

public class UserBean {
	private String username;
    private int bean;
    
	public UserBean(String username, int bean) {
		this.username = username;
		this.bean = bean;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getBean() {
		return bean;
	}
	public void setBean(int bean) {
		this.bean = bean;
	}
	
	public String toString() {
		return "username = " + username + "  bean = " + bean;
	}
}
