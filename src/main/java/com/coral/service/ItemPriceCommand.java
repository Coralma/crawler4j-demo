package com.coral.service;

/**
 * Created by CCC on 2015/10/28.
 */
public class ItemPriceCommand extends AbstractCommand {

    public ItemPriceCommand(String name) {
        super("ItemPrice", defaultTO);
        super.name = name;
    }
}
