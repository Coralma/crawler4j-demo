package com.coral.crawler.model.pcauto;

/**
 * Created by ccc on 2018/1/12.
 */
public class PcAutoModelExcessId {

    private String Value;
    private Integer Id;
    private Integer jss;
    private String optionPrice1;
    private String Price;
    private String optionPrice2;
    private String optionPrice;
    private String bvalueAddition;

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getJss() {
        return jss;
    }

    public void setJss(Integer jss) {
        this.jss = jss;
    }

    public String getOptionPrice1() {
        return optionPrice1;
    }

    public void setOptionPrice1(String optionPrice1) {
        this.optionPrice1 = optionPrice1;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getOptionPrice2() {
        return optionPrice2;
    }

    public void setOptionPrice2(String optionPrice2) {
        this.optionPrice2 = optionPrice2;
    }

    public String getOptionPrice() {
        return optionPrice;
    }

    public void setOptionPrice(String optionPrice) {
        this.optionPrice = optionPrice;
    }

    public String getBvalueAddition() {
        return bvalueAddition;
    }

    public void setBvalueAddition(String bvalueAddition) {
        this.bvalueAddition = bvalueAddition;
    }
}
