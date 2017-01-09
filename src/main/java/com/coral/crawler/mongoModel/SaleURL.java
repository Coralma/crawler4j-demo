package com.coral.crawler.mongoModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cccis.base.mongo.MBaseEntity;

/**
 * Created by CCC on 2017/1/6.
 */
@Document(collection="SaleURL")
public class SaleURL extends MBaseEntity {

    @Id
    private String id;
    private String saleUrl;
    private String modelUrl;
    private String cid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSaleUrl() {
        return saleUrl;
    }

    public void setSaleUrl(String saleUrl) {
        this.saleUrl = saleUrl;
    }

    public String getModelUrl() {
        return modelUrl;
    }

    public void setModelUrl(String modelUrl) {
        this.modelUrl = modelUrl;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

}
