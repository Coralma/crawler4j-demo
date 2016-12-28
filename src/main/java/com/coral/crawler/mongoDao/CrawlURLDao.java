package com.coral.crawler.mongoDao;

import com.coral.crawler.mongoModel.CrawlURL;
import org.springframework.stereotype.Repository;

import com.cccis.base.mongo.MBaseDao;

/**
 * Created by CCC on 2016/12/27.
 */
@Repository(CrawlURLDao.SPRING_BEAN_NAME)
public class CrawlURLDao extends MBaseDao<CrawlURL> {

    public final static String SPRING_BEAN_NAME = "mongo.dao.CrawlURLDao";

    @Override
    public Class getEntityClass() {
        return CrawlURL.class;
    }
}
