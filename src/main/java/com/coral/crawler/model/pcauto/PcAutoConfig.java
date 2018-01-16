package com.coral.crawler.model.pcauto;

/**
 * Created by ccc on 2018/1/12.
 */
public class PcAutoConfig {

    private Integer success;
    private String message;
    private PcAutoBody body;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PcAutoBody getBody() {
        return body;
    }

    public void setBody(PcAutoBody body) {
        this.body = body;
    }
}
