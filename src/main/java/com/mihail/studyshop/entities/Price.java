package com.mihail.studyshop.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Price {

    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    private UUID guid;
    @CreationTimestamp
    private LocalDateTime dateCreate;
    private Double price;
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vendor_code_guid")
    @JsonBackReference
    private VendorCode vendorCode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "goods_guid")
    @JsonBackReference
    private Goods goods;

    public Price() {
        this.dateCreate = LocalDateTime.now();
    }

    public Price(Double price, String comment, VendorCode vendorCode, Goods goods) {
        this.price = price;
        this.comment = comment;
        this.vendorCode = vendorCode;
        this.goods = goods;
        this.dateCreate = LocalDateTime.now();
    }

    public Price(Double price, String comment, @NotNull Goods goods) {
        this.price = price;
        this.comment = comment;
        this.vendorCode = goods.getVendorCode();
        this.goods = goods;
        this.dateCreate = LocalDateTime.now();
    }

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public VendorCode getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(VendorCode vendorCode) {
        this.vendorCode = vendorCode;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Price{" +
                "guid=" + guid +
                ", dateCreate=" + dateCreate +
                ", price=" + price +
                ", comment='" + comment + '\'' +
                ", vendorCode=" + vendorCode +
                ", goods=" + goods +
                '}';
    }
}
