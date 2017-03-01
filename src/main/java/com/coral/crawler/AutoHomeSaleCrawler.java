package com.coral.crawler;

import java.util.List;

import com.coral.crawler.constant.Constants;
import com.coral.crawler.mongoModel.SaleURL;
import com.coral.crawler.service.AutoHomeParseService;
import com.google.common.collect.Lists;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by CCC on 2017/1/6.
 */
public class AutoHomeSaleCrawler extends WebCrawler {

    AutoHomeParseService service;

    public AutoHomeSaleCrawler() {
        String paths[] = { "META-INF/applicationContext.xml" };
        //这个xml文件是Spring配置beans的文件，顺带一提，路径 在整个应用的根目录
        ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
        service = (AutoHomeParseService) ctx.getBean("AutoHomeParseService");
        System.out.println("Init AutoHomeHistoryCrawler done.");
    }

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        return false;
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        int docId = page.getWebURL().getDocid();
        System.out.println("Sale URL is " + url);
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();
            parseSaleURL(html, url, docId);
        }

        try {
            Thread.sleep(Constants.sleepTime);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void parseSaleURL(String html, String url, int docId) {
        //System.out.println(html);
        List<String> saleUrls = Lists.newArrayList();
        int dataStartIndex = html.lastIndexOf("<div class=\"salecars\">");
        int dataEndIndex = html.lastIndexOf("<!--end 停售款-->");
        if(dataStartIndex > 0 && dataEndIndex > 0) {
            String innerHtml = html.substring(dataStartIndex, dataEndIndex);
            String tagListString = innerHtml.substring(innerHtml.indexOf("<dd>"), innerHtml.lastIndexOf("</dd>"));
            String[] tagArray = tagListString.split("<dd><a target=\"_self\" href=\"");
            for (String tag : tagArray) {
                if ("".equals(tag)) {
                    continue;
                }
                String value = tag.substring(tag.indexOf("#Year") + 5, tag.indexOf("\">"));
                String cid = docId + "-" + value;
                SaleURL saleURL = new SaleURL();
                saleURL.setSaleUrl(url);
                saleURL.setModelUrl("http://car.autohome.com.cn/config/series/" + cid + ".html");
                saleURL.setCid(cid);
                service.saveSaleURLDao(saleURL);
                System.out.println(saleURL.getModelUrl());
            }
        }
    }
}
