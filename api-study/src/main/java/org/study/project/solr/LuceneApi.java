package org.study.project.solr;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericDocValuesField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.QueryBuilder;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenyao
 * @Description:
 * @date 2018/6/7/15:47
 */
public class LuceneApi {


    public static void main(String[] args) throws Exception {
//        createIndexDataBase();

        queryIndexDataBase();

    }

    /**
     * 删除索引
     * @throws Exception
     */
    public static void deleteIndex() throws Exception {
        Directory direactory = FSDirectory.open(Paths.get("C:\\Users\\chenyao\\Desktop\\lucene"));
        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
        IndexWriter writer = new IndexWriter(direactory, config);

        //指定删除的索引
        Term term = new Term("title","世界");
        long l = writer.deleteDocuments(term);

        //删除所有
        long all = writer.deleteAll();
        writer.close();
    }
    /**
     * 根据索引 进行搜索
     * @throws Exception
     */
    public static void queryIndexDataBase() throws Exception {
        StandardAnalyzer analyzer = new StandardAnalyzer();
        //创建query工厂类，分词器的目的是把搜索的文本进行分词
        QueryBuilder builder = new QueryBuilder(analyzer);
        Query query = builder.createPhraseQuery("name", "名字");
        //创建一个指向索引路的输入流
        Directory directory = FSDirectory.open(Paths.get("C:\\Users\\chenyao\\Desktop\\lucene"));
        DirectoryReader reader = DirectoryReader.open(directory);
        //终极索引搜索类
        IndexSearcher searcher = new IndexSearcher(reader);
        //查询出指定条数的数据
        TopDocs topDocs = searcher.search(query, 5);
        //获取最大的符合记录的值
        float maxScore = topDocs.getMaxScore();
        //获取搜索集
        ScoreDoc[] docs = topDocs.scoreDocs;
        for (ScoreDoc doc : docs) {
            int docId = doc.doc;
            Document document = searcher.doc(docId);
            System.out.println(document.get("id"));
            System.out.println(document.get("name"));
            System.out.println(document.get("picture"));
            System.out.println(document.get("size"));
            System.out.println(document.get("title"));
        }
        reader.close();

    }

    /**
     * 添加索引
     * @throws IOException
     */
    public static void createIndexDataBase() throws IOException {
        //1.数据采集
        List<Book> books = getBookList();
        //2.创建文档对象
        ArrayList<Document> documents = new ArrayList<>();
        for (Book book : books) {
            Document doc = new Document();

            doc.add(new TextField("id",book.getId(), Field.Store.YES));
            doc.add(new TextField("name",book.getName(), Field.Store.YES));
            doc.add(new TextField("picure",book.getPicture(), Field.Store.YES));
            doc.add(new TextField("size",book.getSize()+"", Field.Store.YES));
            doc.add(new TextField("title",book.getTitle(), Field.Store.YES));

            documents.add(doc);
        }
        //3.分词
        StandardAnalyzer analyzer = new StandardAnalyzer();
        //4.声明 索引库的位置
        Directory directory = FSDirectory.open(Paths.get("C:\\Users\\chenyao\\Desktop\\lucene"));
        //如果不传入自定义分词器，默认构造器使用的就是标准分词器 StandardAnalyzer
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(directory,config);
        //将文档写入索引库
        for (Document document : documents) {
            writer.addDocument(document);
        }
        writer.close();
    }

    /**
     * 数据采集
     * @return
     */
    public static List<Book> getBookList(){
        ArrayList<Book> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Book book = new Book(String.valueOf(i), "名字" + i, "图片" + i, i, "title" + i);
            list.add(book);
        }

        return list;
    }
}
