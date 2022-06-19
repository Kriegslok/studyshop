package com.mihail.studyshop.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class GoodsCategory {

    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    private UUID guid;
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "parent_category_guid")
    @JsonBackReference
    private GoodsCategory parentCategory;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<GoodsCategory> childrenCategories = new ArrayList<>();

    private String description;


    protected GoodsCategory() {
    }

    public GoodsCategory(GoodsCategory parentCategory, String description) {
        this.parentCategory = parentCategory;
        this.description = description;
        this.dateCreate = LocalDateTime.now();
    }

    public GoodsCategory(GoodsCategory parentCategory, List<GoodsCategory> childrenCategories, String description) {
        this.parentCategory = parentCategory;
        this.childrenCategories = childrenCategories;
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

    public GoodsCategory getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(GoodsCategory parentCategory) {
        this.parentCategory = parentCategory;
    }

    public List<GoodsCategory> getChildrenCategories() {
        return childrenCategories;
    }

    public void setChildrenCategories(List<GoodsCategory> childrenCategories) {
        this.childrenCategories = childrenCategories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "GoodsCategory{" +
                "guid=" + guid +
                ", dateCreate=" + dateCreate +
                ", parentCategory=" + parentCategory +
                ", childrenCategories=" + childrenCategories +
                ", description='" + description + '\'' +
                '}';
    }
}
