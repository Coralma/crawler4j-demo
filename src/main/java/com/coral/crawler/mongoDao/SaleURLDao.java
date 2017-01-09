package com.coral.crawler.mongoDao;

import com.coral.crawler.mongoModel.SaleURL;
import org.springframework.stereotype.Repository;

import com.cccis.base.mongo.MBaseDao;

/**
 * Created by CCC on 2017/1/9.
 */
@Repository(SaleURLDao.SPRING_BEAN_NAME)
public class SaleURLDao extends MBaseDao<SaleURL> {

    public final static String SPRING_BEAN_NAME = "mongo.dao.SaleURLDao";

    @Override
    public Class getEntityClass() {
        return SaleURL.class;
    }
}