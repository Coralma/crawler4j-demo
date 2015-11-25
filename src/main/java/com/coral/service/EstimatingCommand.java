package com.coral.service;

/**
 * Created by CCC on 2015/10/28.
 */
public class EstimatingCommand extends AbstractCommand {

    public EstimatingCommand(String name) {
        super("Estimating", defaultTO);
        super.name = name;
    }

}
