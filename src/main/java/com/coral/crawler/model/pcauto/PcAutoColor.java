package com.coral.crawler.model.pcauto;

/**
 * Created by Administrator on 4/18/2018.
 */
public class PcAutoColor {

    Integer success;
    String message;
    PcAutoColorBody body;

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

    public PcAutoColorBody getBody() {
        return body;
    }

    public void setBody(PcAutoColorBody body) {
        this.body = body;
    }
}
