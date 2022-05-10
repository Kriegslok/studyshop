package com.mihail.studyshop.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private Double price;

    protected Goods() {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
