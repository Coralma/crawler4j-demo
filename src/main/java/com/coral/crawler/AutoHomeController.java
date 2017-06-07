package com.coral.crawler;

import com.coral.crawler.constant.Constants;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * Created by CCC on 2016/12/28.
 */
public class AutoHomeController {

    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "C:\\home\\crawl";
        int numberOfCrawlers = 1;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        for(int i=1; i< 6000; i++) {
            String url = "http://car.autohome.com.cn/config/series/" + i + ".html";
            controller.addSeed(url, i);
            System.out.println(url);
            Thread.sleep(Constants.sleepTime);
        }
        /*controller.addSeed("http://car.autohome.com.cn/config/series/834.html", 18);*/

        controller.start(AutoHomeCrawler.class, numberOfCrawlers);
    }
}
