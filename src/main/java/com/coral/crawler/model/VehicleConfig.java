package com.coral.crawler.model;

/**
 * Created by CCC on 2015/11/19.
 */
public class VehicleConfig {

    Integer returncode;
    String message;
    VehicleConfigSeries result;

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

    public VehicleConfigSeries getResult() {
        return result;
    }

    public void setResult(VehicleConfigSeries result) {
        this.result = result;
    }
}
