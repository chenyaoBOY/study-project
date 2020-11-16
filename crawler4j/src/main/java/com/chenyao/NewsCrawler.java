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

public class NewsCrawler extends WebCrawler {
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp4|zip|gz))$");
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        boolean b =(!FILTERS.matcher(href).matches()&&href.startsWith("http://www.bjnews.com.cn/house/"))||href.startsWith("http://www.bjnews.com.cn/house/2018/");
        return b;
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();

        if(!url.startsWith("http://www.bjnews.com.cn/house/2018/")){
            return;
        }
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String html = htmlParseData.getHtml();//页面html内容
            Document doc = Jsoup.parse(html);//采用jsoup解析html，这个大家不会可以简单搜一下

            //使用选择器的时候需要了解网页中的html规则，自己去网页中F12一下，
            Elements elements = doc.select(".nleft");
            if(elements.size()==0){
                return;
            }
            Element element = elements.get(0);
            String date = element.select(".date").text();
            String author = element.select(".author").text();
            String title = element.select(".title").text();
            String content = element.select(".content").html();

            System.out.println("date:"+date);
            System.out.println("author:"+author);
            System.out.println("title:"+title);
            System.out.println("content:"+content);
        }

    }

}
