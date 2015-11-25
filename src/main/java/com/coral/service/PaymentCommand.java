package com.coral.service;

/**
 * Created by CCC on 2015/10/28.
 */
public class PaymentCommand extends AbstractCommand {

    public PaymentCommand(String name) {
        super("Payment", defaultTO);
        super.name = name;
    }
}
