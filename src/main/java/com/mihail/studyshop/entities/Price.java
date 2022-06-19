package com.mihail.studyshop.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Price {

    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    private UUID guid;
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price1 = (Price) o;
        return Objects.equals(price, price1.price) && Objects.equals(comment, price1.comment) && Objects.equals(vendorCode, price1.vendorCode) && Objects.equals(goods, price1.goods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, comment, vendorCode, goods);
    }
}
