package com.coral.service;

import java.util.Random;

/**
 * Created by CCC on 2015/10/28.
 */
public class AccountCommand extends AbstractCommand {

    public AccountCommand(String name) {
        super("Account", 180);
        super.name = name;
    }

    @Override
    public String getFallback() {
        System.out.println("Run the getFallback() in AccountCommand");
        return "AccountFallback";
    }

    @Override
    protected String run() throws Exception {
        Random rand = new Random();
        int nextInt = rand.nextInt(200);
        if (nextInt == 180 || nextInt == 150 || nextInt == 100) {
            System.out.println("Throw Exception at the run() in " + name + "Command");
            throw new Exception("TestException");
        }
        Thread.sleep(nextInt);
        /*System.out.println("Run the run() in " + name + "Command");*/
        return "Hello " + name + "!";
    }
}
