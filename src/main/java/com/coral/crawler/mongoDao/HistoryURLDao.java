package com.coral.crawler.mongoDao;

import com.coral.crawler.mongoModel.HistoryURL;
import org.springframework.stereotype.Repository;

import com.cccis.base.mongo.MBaseDao;

/**
 * Created by CCC on 2016/12/27.
 */
@Repository(HistoryURLDao.SPRING_BEAN_NAME)
public class HistoryURLDao extends MBaseDao<HistoryURL> {

    public final static String SPRING_BEAN_NAME = "mongo.dao.HistoryURLDao";

    @Override
    public Class getEntityClass() {
        return HistoryURL.class;
    }
}
