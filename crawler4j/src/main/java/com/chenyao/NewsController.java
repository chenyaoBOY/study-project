package com.chenyao;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * 爬虫控制器
 * @author
 *
 */
public class NewsController {
    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "/data/crawl/root";
        int numberOfCrawlers = 1;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);

        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */
        controller.addSeed("http://www.bjnews.com.cn/house/");//时代周报
//        controller.addSeed("http://www.zqrb.cn/");//证券日报
//        controller.addSeed("http://www.bjnews.com.cn/");//新京报网
//        controller.addSeed("http://www.chinanews.com/");//中国新闻网
//        controller.addSeed("http://www.jjckb.cn/");//经济参考报
//        controller.addSeed("http://www.zgswcn.com/");//中国商报

//        if(url.startsWith("http://www.time-weekly.com/")){
//
//        }else if(url.startsWith("http://www.zqrb.cn/")){
//
//        }else if(url.startsWith("http://www.bjnews.com.cn/")){
//
//        }else if(url.startsWith("http://www.chinanews.com/")){
//
//        }else if(url.startsWith("http://www.jjckb.cn/")){
//
//        }else if(url.startsWith("http://www.zgswcn.com/")){
//
//        }
        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        controller.start(NewsCrawler.class, numberOfCrawlers);
    }

}
