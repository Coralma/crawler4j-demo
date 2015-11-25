package com.coral.service;

/**
 * Created by CCC on 2015/10/28.
 */
public class TotalPriceCommand extends AbstractCommand {

    public TotalPriceCommand(String name) {
        super("TotalPrice", defaultTO);
        super.name = name;
    }

}
