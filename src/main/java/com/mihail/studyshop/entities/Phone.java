package com.mihail.studyshop.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Phone {

    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    private UUID guid;
    @CreationTimestamp
    private LocalDateTime dateCreate;
    private String phoneNumber;
    private Boolean isPrimary;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_guid")
    @JsonBackReference
    private Manager manager;

    protected Phone() {
    }

    public Phone(String phoneNumber, Boolean isPrimary){
        this.phoneNumber = phoneNumber;
        this.isPrimary = isPrimary;
        this.dateCreate = LocalDateTime.now();
    }

    public Phone(String phoneNumber, Boolean isPrimary, UUID managerGuid){
        this.phoneNumber = phoneNumber;
        this.isPrimary = isPrimary;
        this.dateCreate = LocalDateTime.now();
        this.manager = new Manager(managerGuid);
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
