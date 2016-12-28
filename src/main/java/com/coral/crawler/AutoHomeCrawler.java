package com.coral.crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * Created by CCC on 2016/12/28.
 */
public class AutoHomeCrawler extends WebCrawler {

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        boolean rs = href.startsWith("http://car.autohome.com.cn/config/series/");
        return rs;
    }

}
