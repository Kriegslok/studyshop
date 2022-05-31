package com.mihail.studyshop.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Goods {
    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    private UUID guid;
    @CreationTimestamp
    private LocalDateTime dateCreate;

    private String name;
    private String description;
    private String photo;
    @OneToOne
    @JsonBackReference
    private VendorCode vendorCode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vendor_guid")
    @JsonBackReference
    private Vendor vendor;

    @OneToMany(mappedBy = "goods", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Price> priceList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "goods_category_guid")
    @JsonBackReference
    private GoodsCategory goodsCategory;

    protected Goods() {
    }

    public Goods(String name, String description, String photo, @NotNull VendorCode vendorCode, Price price, GoodsCategory goodsCategory) {
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.vendorCode = vendorCode;
        this.vendor = vendorCode.getVendor();
        this.priceList.add(price);
        this.goodsCategory = goodsCategory;
        this.dateCreate = LocalDateTime.now();
    }

    public Goods(String name, String description, String photo, @NotNull VendorCode vendorCode, GoodsCategory goodsCategory) {
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.vendorCode = vendorCode;
        this.vendor = vendorCode.getVendor();
        this.goodsCategory = goodsCategory;
        this.dateCreate = LocalDateTime.now();
    }


    public Goods(String name, @NotNull VendorCode vendorCode, GoodsCategory goodsCategory) {
        this.name = name;
        this.vendorCode = vendorCode;
        this.vendor = vendorCode.getVendor();
        this.goodsCategory = goodsCategory;
        this.dateCreate = LocalDateTime.now();
    }

    public List<Price> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<Price> priceList) {
        this.priceList = priceList;
    }

    public GoodsCategory getGoodsCategory() {
        return goodsCategory;
    }

    public void setGoodsCategory(GoodsCategory goodsCategory) {
        this.goodsCategory = goodsCategory;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public VendorCode getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(VendorCode vendorCode) {
        this.vendorCode = vendorCode;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

}
