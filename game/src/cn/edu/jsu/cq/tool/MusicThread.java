package cn.edu.jsu.cq.tool;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class MusicThread extends Thread {

	public void run() {
		String filename1 = "D:\\CloudMusic\\6th Cosmos - 旅行青蛙 - 青蛙旅行BGM原声（第六宇宙 remix）.mp3";// 文件路径
		String filename2 = "D:\\CloudMusic\\6th Cosmos - 旅行青蛙 - 青蛙旅行BGM原声（第六宇宙 remix）.mp3";// 文件路径
		while (true) {
			try {
				BufferedInputStream buffer1 = new BufferedInputStream(new FileInputStream(filename1));
				Player player1 = new Player(buffer1);// 加载
				player1.play();// 开始播放
				
				BufferedInputStream buffer2 = new BufferedInputStream(new FileInputStream(filename2));
				Player player2 = new Player(buffer2);// 加载
				player2.play();// 开始播放
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public static void main(String args[]) {
		// 多线程开始
		Thread music = new MusicThread();
		music.start();
	}
}
