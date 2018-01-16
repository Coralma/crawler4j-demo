package com.coral.crawler.controller;

import com.coral.crawler.model.pcauto.PcAutoConfig;
import com.coral.crawler.mongoDao.CrawlURLDao;
import com.coral.crawler.mongoDao.HistoryURLDao;
import com.coral.crawler.mongoDao.VehicleDao;
import com.coral.crawler.mongoModel.HistoryURL;
import com.coral.crawler.service.PcAutoService;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by CCC on 2015/11/19.
 */
public class PcAutoHistoryCrawler extends WebCrawler {

    private Gson gson = new Gson();
    /*private VehicleDao dao;
    private CrawlURLDao crawlURLDao;
    private HistoryURLDao historyURLDao;*/
    private PcAutoService service;

    public PcAutoHistoryCrawler() {
        initDao();
    }

    public PcAutoHistoryCrawler(String test) {
    }
    /**
     * This method receives two parameters. The first parameter is the page
     * in which we have discovered this new url and the second parameter is
     * the new url. You should implement this function to specify whether
     * the given url should be crawled or not (based on your crawling logic).
     * In this example, we are instructing the crawler to ignore urls that
     * have css, js, git, ... extensions and to only accept urls that start
     * with "http://www.ics.uci.edu/". In this case, we didn't need the
     * referringPage parameter to make the decision.
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        /*String href = url.getURL().toLowerCase();
        boolean rs = href.startsWith("http://price.pcauto.com.cn/s");
        boolean ed = href.endsWith("config.html");
        return rs && ed;*/
        return false;
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        int docId = page.getWebURL().getDocid();

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String html = htmlParseData.getHtml();
            service.historyVehicle(url,html,docId);
        }

        /*try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    private void initDao() {
        String paths[] = { "META-INF/applicationContext.xml" };
        //这个xml文件是Spring配置beans的文件，顺带一提，路径 在整个应用的根目录
        ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
        service = (PcAutoService) ctx.getBean(PcAutoService.SPRING_BEAN_NAME);
    }

}
