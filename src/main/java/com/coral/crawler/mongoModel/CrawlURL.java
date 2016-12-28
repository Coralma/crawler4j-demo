package com.coral.crawler.mongoModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cccis.base.mongo.MBaseEntity;

/**
 * Created by CCC on 2016/12/27.
 */
@Document(collection="CrawlURL")
public class CrawlURL extends MBaseEntity {

    @Id
    private String id;
    private String url;
    private String cid;
    private String status = "1";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
