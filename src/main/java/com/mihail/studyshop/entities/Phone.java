package com.mihail.studyshop.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID guid;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private LocalDateTime dateCreate;
    private String phoneNumber;
    private Boolean isPrimary;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JoinColumn(name="manager_guid", nullable=false)
    private Manager manager;

    public Phone() {
    }

    public Phone(String phoneNumber, Boolean isPrimary){
        this.phoneNumber = phoneNumber;
        this.isPrimary = isPrimary;
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
