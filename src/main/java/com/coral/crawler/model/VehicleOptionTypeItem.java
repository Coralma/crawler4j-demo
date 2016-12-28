package com.coral.crawler.model;

/**
 * Created by CCC on 2016/12/23.
 */
public class VehicleOptionTypeItem {

    private String name;
    private VehicleOptionItem[] configitems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VehicleOptionItem[] getConfigitems() {
        return configitems;
    }

    public void setConfigitems(VehicleOptionItem[] configitems) {
        this.configitems = configitems;
    }
}
