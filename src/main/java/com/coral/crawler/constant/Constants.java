package com.coral.crawler.constant;

/**
 * Created by CCC on 2017/1/5.
 */
public class Constants {

    public static int sleepTime = 0;
    private static Random random = new Random();

    public static int getRandomSleepTime(Integer scope) {
        int max=scope;
        int min=1000;
        if(max < min) {
            throw new RuntimeException("The max scope have to bigger than 1000.");
        }
        return random.nextInt(max)%(max-min+1) + min;
    }

    public static void main(String[] args) {
        for(int i=0;i<100;i++) {
            System.out.println(getRandomSleepTime(10000));
        }
    }
}
