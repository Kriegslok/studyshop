package com.mihail.studyshop.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Vendor {
    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    private UUID guid;
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreate;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<VendorCode> vendorCodes = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Goods> goodsList = new ArrayList<>();

    private String name;

    private String description;

    protected Vendor() {
    }

    public Vendor(String name, String description) {
        this.name = name;
        this.description = description;
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

    public List<VendorCode> getVendorCodes() {
        return vendorCodes;
    }

    public void setVendorCodes(List<VendorCode> vendorCodes) {
        this.vendorCodes = vendorCodes;
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

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vendor vendor = (Vendor) o;
        return Objects.equals(name, vendor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
