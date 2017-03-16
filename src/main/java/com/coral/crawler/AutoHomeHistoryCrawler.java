package com.coral.crawler;

import java.util.Date;

import com.coral.crawler.constant.Constants;
import com.coral.crawler.mongoModel.HistoryURL;
import com.coral.crawler.mongoModel.Vehicle;
import com.coral.crawler.service.AutoHomeParseService;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by CCC on 2016/12/28.
 */
public class AutoHomeHistoryCrawler extends WebCrawler {

    AutoHomeParseService service;

    public AutoHomeHistoryCrawler() {
        String paths[] = { "META-INF/applicationContext.xml" };
        //这个xml文件是Spring配置beans的文件，顺带一提，路径 在整个应用的根目录
        ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
        service = (AutoHomeParseService) ctx.getBean("AutoHomeParseService");
        System.out.println("Init AutoHomeHistoryCrawler done.");
    }

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        /*String href = url.getURL().toLowerCase();
        boolean rs = href.startsWith("http://car.autohome.com.cn/config/series/");
        return rs;*/
        return false;
    }

    @Override
    public void visit(Page page) {
        try {
            Thread.sleep(Constants.getRandomSleepTime(20000));
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            String url = page.getWebURL().getURL();
            if(!service.checkHistoryUrl(url)) {
                return ;
            }
            System.out.println("History URL is " + url);
            Vehicle[] vehicles = service.parse(page);
            for(Vehicle v : vehicles) {
                if(!service.checkSameVehicle(v.getName())) {
                    v.setCreateDate(new Date());
                    v.setLastModifyDate(new Date());
                    service.saveVehicle(v);
                }
            }
            // if the vehicle is correct
            if(vehicles.length > 0 && service.checkHistoryUrl(url)) {
                HistoryURL historyURL = new HistoryURL();
                historyURL.setUrl(url);
                String cid = url.substring(url.lastIndexOf("/")+1, url.lastIndexOf(".html"));
                historyURL.setCid(String.valueOf(cid));
                historyURL.setCreateDate(new Date());
                service.saveHistory(historyURL);
            }
        }catch (Exception e) {
            return;
        }
    }
}
