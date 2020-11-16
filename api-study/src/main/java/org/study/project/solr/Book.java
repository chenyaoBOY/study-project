package org.study.project.solr;

import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;

/**
 * @author chenyao
 * @Description: 索引的类
 * @Filed 注解下的字段对应schema.xml中的filed字段，可以不使用document直接使用bean
 *      solrserver.addBean(Book)
 *      solrserver.add(document)
 *
 * @date 2018/6/7/15:49
 */
public class Book implements Serializable{

    @Field
    private String id;
    @Field
    private String name;
    @Field
    private String picture;
    private Integer size;
    @Field
    private String title;

    public Book(String id,String name,String picture,Integer size,String title){
        this.id=id;
        this.name=name;
        this.picture=picture;
        this.size=size;
        this.title=title;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
