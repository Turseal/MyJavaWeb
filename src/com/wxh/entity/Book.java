package com.wxh.entity;

public class Book {
    private Integer id;
    private String name;
    private Float price;
    private   Integer sales;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getSales() {
        return sales;
    }

    public Book(Integer id, String name, Float price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sales=-1;
    }

    public Book(Integer id, String name, Float price, Integer sales) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sales = sales;
    }
}
