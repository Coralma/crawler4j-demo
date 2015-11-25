package com.coral.service;

/**
 * Created by CCC on 2015/10/28.
 */
public class CustomerCommand extends AbstractCommand {

    public CustomerCommand(String name) {
        super("Customer", 195);
        super.name = name;
    }

    @Override
    public String getFallback() {
        System.out.println("Run the getFallback() in CustomerCommand");
        return "CustomerFallback";
    }
}
