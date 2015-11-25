package com.coral.service;

/**
 * Created by CCC on 2015/10/28.
 */
public class CreditCardCommand extends AbstractCommand {

    public CreditCardCommand(String name) {
        super("CreditCard", 190);
        super.name = name;
    }

    @Override
    public String getFallback() {
        System.out.println("Run the getFallback() in CreditCardCommand");
        return "CreditCardFallback";
    }
}
