package com.coral.crawler;

import java.util.List;

import com.coral.crawler.mongoModel.SaleURL;
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
        String crawlStorageFolder = "C:\\home\\crawlHistory";
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
        List<SaleURL> saleURLs = autoHomeParseService.getAllSaleURLDao();
        int index = 9000001;
        for(SaleURL saleURL : saleURLs) {
            String url = saleURL.getModelUrl();
            System.out.println("Model URL: " + url);
            controller.addSeed(url, index++);
        }
        /*controller.addSeed("http://car.autohome.com.cn/config/series/364-100.html", 999001);
        controller.addSeed("http://car.autohome.com.cn/config/series/364-2357.html", 999002);
        controller.addSeed("http://car.autohome.com.cn/config/series/364-1589.html", 999003);*/

        controller.start(AutoHomeHistoryCrawler.class, numberOfCrawlers);
    }
}
