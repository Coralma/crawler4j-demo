package com.coral.crawler.model;

/**
 * Created by CCC on 2015/11/19.
 */
public class VehicleConfigParamItem {

    String name;

    VehicleConfigValueItem[] valueitems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VehicleConfigValueItem[] getValueitems() {
        return valueitems;
    }

    public void setValueitems(VehicleConfigValueItem[] valueitems) {
        this.valueitems = valueitems;
    }
}
