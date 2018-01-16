package com.coral.crawler.controller;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * Created by CCC on 2015/11/19.
 */
public class PcAutoController {

    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "C:\\home\\crawl";
        int numberOfCrawlers = 1;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);

        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

/*
        int totalIndex = 6000;
        for(int i=1; i< totalIndex; i++) {
            String url = "http://price.pcauto.com.cn/sg" + i + "/config.html";
            controller.addSeed(url, i);
            System.out.println(url);
        }
*/

        String url = "http://price.pcauto.com.cn/sg1726/config.html";
        controller.addSeed(url, 1726);

        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        controller.start(PcAutoCrawler.class, numberOfCrawlers);
    }

}
