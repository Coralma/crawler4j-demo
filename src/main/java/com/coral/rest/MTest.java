package com.coral.rest;

import java.util.Random;

/**
 * Created by CCC on 2015/10/28.
 */
public class MTest {

    public static void main(String[] args) throws Exception {
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            int nextInt = rand.nextInt(5000);
            System.out.println(nextInt);
        }
    }
}
