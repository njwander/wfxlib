package wfx.utils.pojo;

import wfx.utils.annotation.Table;

import java.util.Date;

/**
 * Created by admin on 14-4-27.
 */
@Table(tableName = "hehe_book")
public class Book {
    private String name;
    private Date publishDate;
    private Integer id;
    private Double price;
    private int page;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public static void main(String[] args){
        Object b = null;
        System.out.println(b + "");
    }
}
