package com.coral.crawler.model;

/**
 * Created by CCC on 2016/12/23.
 */
public class VehicleOptionItem {

    private String name;
    private VehicleOptionValueItem[] valueitems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VehicleOptionValueItem[] getValueitems() {
        return valueitems;
    }

    public void setValueitems(VehicleOptionValueItem[] valueitems) {
        this.valueitems = valueitems;
    }
}
