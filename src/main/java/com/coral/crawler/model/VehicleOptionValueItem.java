package com.coral.crawler.model;

/**
 * Created by CCC on 2015/11/19.
 */
public class VehicleOptionValueItem {

    Integer specid;
    String value;
    VehicleOptionPriceItem[] price;

    public Integer getSpecid() {
        return specid;
    }

    public void setSpecid(Integer specid) {
        this.specid = specid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public VehicleOptionPriceItem[] getPrice() {
        return price;
    }

    public void setPrice(VehicleOptionPriceItem[] price) {
        this.price = price;
    }
}
