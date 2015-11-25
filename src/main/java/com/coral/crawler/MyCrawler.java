package com.coral.crawler;

import java.util.regex.Pattern;

import com.coral.crawler.model.VehicleConfig;
import com.coral.crawler.model.VehicleConfigItem;
import com.coral.crawler.model.VehicleConfigParamItem;
import com.coral.crawler.model.VehicleConfigValueItem;
import com.coral.crawler.mongoDao.VehicleDao;
import com.coral.crawler.mongoModel.Vehicle;
import com.google.gson.Gson;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by CCC on 2015/11/19.
 */
public class MyCrawler extends WebCrawler {

    private Gson gson = new Gson();
    private VehicleDao dao;
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|mp3|mp3|zip|gz))$");

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
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches()
                && href.startsWith("http://www.ics.uci.edu/");
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        System.out.println("URL: " + url);

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();

            Vehicle[] vehicles;
            // get json
            try {
                VehicleConfig vehicleConfig = buildVehicleConfig(html);
                int size = vehicleConfig.getResult().getParamtypeitems()[0].getParamitems()[0].getValueitems().length;
                vehicles = new Vehicle[size];
                for(int i=0;i<size;i++) {
                    vehicles[i] = new Vehicle();
                }
                for(VehicleConfigItem item : vehicleConfig.getResult().getParamtypeitems()) {
                /*System.out.println(item.getName());*/
                    for(VehicleConfigParamItem paramItem : item.getParamitems()) {
                        String itemName = paramItem.getName();
                        System.out.println(itemName);
                        VehicleConfigValueItem[] valueItems = paramItem.getValueitems();
                        for(int i=0; i < valueItems.length;i++) {
                            Vehicle v = vehicles[i];
                            String value = valueItems[i].getValue();
                            if(itemName.equals("车型名称")) {
                                v.setName(value);
                            } else if(itemName.equals("厂商指导价(元)")) {
                                v.setPrice(value);
                            }else if(itemName.equals("厂商")) {
                                v.setManufacturer(value);
                            }else if(itemName.equals("级别")) {
                                v.setLevel(value);
                            }else if(itemName.equals("发动机")) {
                                v.setEngine(value);
                            }else if(itemName.equals("变速箱")) {
                                v.setGearBox(value);
                            }else if(itemName.equals("长*宽*高(mm)")) {
                                v.setSize(value);
                            }else if(itemName.equals("最高车速(km/h)")) {
                                v.setSpeed(value);
                            }else if(itemName.equals("官方0-100km/h加速(s)")) {
                                v.setSpeedUp(value);
                            }else if(itemName.equals("发动机型号")) {
                                v.setEngineModel(value);
                            }else if(itemName.equals("排量(mL)")) {
                                v.setDisplacement(value);
                            }else if(itemName.equals("进气形式")) {
                                v.setIntakeForm(value);
                            }
                        }
                    }
                }

               /* String paths[] = {"META-INF/applicationContext.xml"};
                //这个xml文件是Spring配置beans的文件，顺带一提，路径 在整个应用的根目录
                ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
                VehicleDao dao = (VehicleDao)ctx.getBean(VehicleDao.SPRING_BEAN_NAME);*/
                VehicleDao dao = initDao();
                for(Vehicle v : vehicles) {
                    dao.save(v);
                }
            } catch(Exception e) {
  /*              e.printStackTrace();*/
                return;
            }

/*
            Set<WebURL> links = htmlParseData.getOutgoingUrls();

            System.out.println("Text length: " + text.length());
            System.out.println("Html length: " + html.length());
            System.out.println("Number of outgoing links: " + links.size());*/
        }
    }

    private VehicleConfig buildVehicleConfig(String html) {
        String startStr = "var config =";
        int jsonStart = html.lastIndexOf(startStr);
        String configJson = html.substring(jsonStart);
        String endStr = "}]}]}]}};";
        int jsonEndIndex = configJson.indexOf(endStr);
        configJson = configJson.substring(startStr.length(),jsonEndIndex + (endStr.length()-1)).trim();
        /*System.out.println(configJson);*/
        VehicleConfig vehicleConfig = gson.fromJson(configJson, VehicleConfig.class);
        return vehicleConfig;
    }

    private VehicleDao initDao() {
        if(dao == null) {
            String paths[] = { "META-INF/applicationContext.xml" };
            //这个xml文件是Spring配置beans的文件，顺带一提，路径 在整个应用的根目录
            ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
            dao = (VehicleDao) ctx.getBean(VehicleDao.SPRING_BEAN_NAME);
        }
        return dao;
    }
}
