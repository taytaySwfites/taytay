package cn.edu.jsu.cq.tool;
/*
 * ¼ÇÂ¼ÀàÐÍ
 */
public class Record {
    private String username;
    private int score;
    private String scoretime;
    
    public Record() {}
	public Record(String username, int score, String scoretime) {
		this.username = username;
		this.score = score;
		this.scoretime = scoretime;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getScoretime() {
		return scoretime;
	}
	public void setScoretime(String scoretime) {
		this.scoretime = scoretime;
	}
	
	public String toString() {
		return "userName:" + username + " score:" + score + " socretime" + scoretime;
	}
}
