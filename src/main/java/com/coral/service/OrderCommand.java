package com.coral.service;

/**
 * Created by CCC on 2015/10/28.
 */
public class OrderCommand extends AbstractCommand {

    public OrderCommand(String name) {
        super("Order", defaultTO);
        super.name = name;
    }
}
