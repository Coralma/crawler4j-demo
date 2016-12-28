package com.coral.crawler.model;

/**
 * Created by CCC on 2015/11/19.
 */
public class VehicleOption {

    Integer returncode;
    String message;
    VehicleOptionSeries result;

    public Integer getReturncode() {
        return returncode;
    }

    public void setReturncode(Integer returncode) {
        this.returncode = returncode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public VehicleOptionSeries getResult() {
        return result;
    }

    public void setResult(VehicleOptionSeries result) {
        this.result = result;
    }
}
