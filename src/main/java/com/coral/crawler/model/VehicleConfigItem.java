package com.coral.crawler.model;

/**
 * Created by CCC on 2015/11/19.
 */
public class VehicleConfigItem {

    String name;

    VehicleConfigParamItem[] paramitems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VehicleConfigParamItem[] getParamitems() {
        return paramitems;
    }

    public void setParamitems(VehicleConfigParamItem[] paramitems) {
        this.paramitems = paramitems;
    }
}
