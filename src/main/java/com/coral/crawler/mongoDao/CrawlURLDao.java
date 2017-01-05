package com.coral.crawler.mongoDao;

import java.util.List;

import com.coral.crawler.mongoModel.CrawlURL;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.cccis.base.mongo.MBaseDao;

/**
 * Created by CCC on 2016/12/27.
 */
@Repository(CrawlURLDao.SPRING_BEAN_NAME)
public class CrawlURLDao extends MBaseDao<CrawlURL> {

    public final static String SPRING_BEAN_NAME = "mongo.dao.CrawlURLDao";

    public List<CrawlURL> getCrawlURLs() {
        Query query = new Query(Criteria.where("status").is("1"));
        query.with(new Sort(new Sort.Order(Sort.Direction.ASC,"createDate")));
        return findByQuery(query);
    }

    public List<CrawlURL> loadAll() {
        return findAll();
    }

    public boolean isExistedUrl(String url) {
        Query query = new Query(Criteria.where("url").is(url));
        List<CrawlURL> crawlURLs = findByQuery(query);
        return crawlURLs.size() != 0;
    }

    @Override
    public Class getEntityClass() {
        return CrawlURL.class;
    }
}
