package com.coral.crawler;

import java.util.List;

import com.coral.crawler.constant.Constants;
import com.coral.crawler.mongoModel.CrawlURL;
import com.coral.crawler.service.AutoHomeParseService;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by CCC on 2016/12/28.
 */
public class AutoHomeHistoryController {

    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "D:\\home\\crawlHistory";
        int numberOfCrawlers = 1;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        String paths[] = { "META-INF/applicationContext.xml" };
        ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
        AutoHomeParseService autoHomeParseService = (AutoHomeParseService)ctx.getBean("AutoHomeParseService");
        List<CrawlURL> crawlURLs = autoHomeParseService.getNoRunCrawlURLs();
        for(CrawlURL crawlURL : crawlURLs ) {
            String url = crawlURL.getUrl();
            String urlData = url.substring(0, url.lastIndexOf("/"));
            System.out.println(urlData);
            for(int i=0; i<10000; i++) {
                String hisUrl = urlData + "/" + crawlURL.getCid() + "-" + i + ".html";
                System.out.println(hisUrl);
                controller.addSeed(url);
                Thread.sleep(Constants.sleepTime);
            }
            crawlURL.setStatus("2");
            autoHomeParseService.saveCrawlURL(crawlURL);
        }
        /*String url = "http://car.autohome.com.cn/config/series/364-23571.html";
        controller.addSeed(url);*/

        controller.start(AutoHomeHistoryCrawler.class, numberOfCrawlers);
    }
}
