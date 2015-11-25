package com.coral.service;

/**
 * Created by CCC on 2015/10/28.
 */
public class OperatorCommand extends AbstractCommand {

    public OperatorCommand(String name) {
        super("Operator", defaultTO);
        super.name = name;
    }
}
