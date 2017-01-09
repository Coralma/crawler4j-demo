package com.coral.crawler;

import java.util.List;

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
 * Created by CCC on 2017/1/6.
 */
public class AutoHomeSaleController {


    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "D:\\home\\crawlSale";
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
        List<CrawlURL> crawlURLs = autoHomeParseService.getAllCrawlURLs();
        for(CrawlURL crawlURL : crawlURLs ) {
            Integer cid = Integer.parseInt(crawlURL.getCid());
            String saleURL = "http://www.autohome.com.cn/"+ cid +"/sale.html";
            System.out.println(saleURL);
            controller.addSeed(saleURL, cid);
        }
        /*String url = "http://www.autohome.com.cn/135/sale.html";
        controller.addSeed(url, 135);*/

        controller.start(AutoHomeSaleCrawler.class, numberOfCrawlers);
    }
}
