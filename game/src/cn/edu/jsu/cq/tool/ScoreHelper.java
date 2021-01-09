package cn.edu.jsu.cq.tool;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.jsu.cq.sql.Connect;

public class ScoreHelper {
	
	public static boolean isNewScore(String tableName, String userName, int score) {
		try {
			Connect con = new Connect();
			ResultSet res = con.search("select * from " + tableName + " where username=? and score<?", new String[] { userName, String.valueOf(score)});
			if (res.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean isScoreExist(String tableName, String userName) {
		try {
			Connect con = new Connect();
			ResultSet res = con.search("select * from " + tableName + " where username=?", new String[] { userName });
			if (res.next()) {		
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
