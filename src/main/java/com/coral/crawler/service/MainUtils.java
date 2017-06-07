package com.coral.crawler.service;

/**
 * Created by ccc on 2017/6/7.
 */
public class MainUtils {

    public static void main(String[] args) throws Exception {
        System.out.println(cleanSpan("<span class='hs_kw2_configpl'></span>A6L 2017款 TFSI 技术型"));
        System.out.println(replayValue("三<span class='hs_kw22_configpl'></span>10<span class='hs_kw12_configpl'></span>公里"));
        System.out.println(cleanSpan("三<span class='hs_kw22_configpl'></span>10<span class='hs_kw12_configpl'></span>公里"));
        System.out.println(cleanSpan("<span class='hs_kw2_configpl'></span>A6L 2017款 TFSI <span class='hs_kw3_configpl'></span><span class='hs_kw4_configpl'></span>型"));
    }

    public static String replayValue(String value) {
        value = value.replaceAll("<span class='hs_kw22_configpl'></span>","年或");
        value = value.replaceAll("<span class='hs_kw12_configpl'></span>","万");
        return value;
    }

    public static String cleanSpan(String value) {
        int spanStar = value.indexOf("<span");
        if(spanStar < 0) {
            return value;
        } else {
            String head = value.substring(0, spanStar);
            int spanEnd = value.indexOf("</span>") + 7; // 7 is the length of </span> string
            String other = value.substring(spanEnd);
            value = head + other;
            return cleanSpan(value);
        }
    }
}
