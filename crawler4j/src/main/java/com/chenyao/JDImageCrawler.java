package com.chenyao;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 京东游戏本搜索结果
 * 图片获取
 */
public class JDImageCrawler extends WebCrawler {
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp4|zip|gz))$");
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();//爬取的网址 转小写
        //这里定义过滤的网址，我的需求是只爬取京东搜索出来的页面中的商品，url需要以https://search.jd.com/Search开头
        boolean b =!FILTERS.matcher(href).matches()&&href.startsWith("https://search.jd.com/search");
        return b;
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        System.out.println(url);
        //判断page是否为真正的网页
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String html = htmlParseData.getHtml();//页面html内容
            Document doc = Jsoup.parse(html);//采用jsoup解析html，这个大家不会可以简单搜一下

            //使用选择器的时候需要了解网页中的html规则，自己去网页中F12一下，
            Elements elements = doc.select(".gl-item");
            if(elements.size()==0){
                return;
            }
            for (Element element : elements) {
                Elements img = element.select(".err-product");
                if(img!=null){
                    System.out.println(img.attr("src"));
                    System.out.println(img.attr("data-lazy-img"));
                }
            }
        }
    }

}
