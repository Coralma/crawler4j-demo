package com.coral.crawler.controller;

import com.coral.crawler.mongoModel.HistoryURL;
import com.coral.crawler.service.PcAutoService;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by ccc on 2018/1/15.
 */
public class PcAutoHistoryController {


    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "C:\\home\\PcAutoHistoryCrawl";
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


        String paths[] = { "META-INF/applicationContext.xml" };
        ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
        PcAutoService service = (PcAutoService) ctx.getBean(PcAutoService.SPRING_BEAN_NAME);
        List<HistoryURL> urls = service.loadHistoryURL();

        int i=10000000;
        for(HistoryURL url : urls) {
            controller.addSeed(url.getUrl(), i++);
        }
        controller.start(PcAutoHistoryCrawler.class, numberOfCrawlers);
    }

}
