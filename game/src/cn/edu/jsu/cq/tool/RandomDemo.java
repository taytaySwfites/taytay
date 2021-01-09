package cn.edu.jsu.cq.tool;

import java.util.Random;

public class RandomDemo {
    public static int rand(int n) {    	
    	Random rand = new Random();
        return rand.nextInt(n) + 1;
    } 
}
