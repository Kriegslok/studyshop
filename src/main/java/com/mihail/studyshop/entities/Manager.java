package com.mihail.studyshop.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID guid;
    private String firstName;
    private String lastName;
    private String inn;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private LocalDateTime dateCreate;
    private Boolean isBlocked;
    private Boolean isOnHoliday;
    private LocalDateTime employmentDate;
    private LocalDateTime firedDate;
    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Phone> phones;
    //private List<Email> emails;

    protected Manager() {
    }

    public Manager(String firstName, String lastName, String inn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.inn = inn;
        this.dateCreate = LocalDateTime.now();

    }

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public Boolean getOnHoliday() {
        return isOnHoliday;
    }

    public void setOnHoliday(Boolean onHoliday) {
        isOnHoliday = onHoliday;
    }

    public LocalDateTime getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDateTime employmentDate) {
        this.employmentDate = employmentDate;
    }

    public LocalDateTime getFiredDate() {
        return firedDate;
    }

    public void setFiredDate(LocalDateTime firedDate) {
        this.firedDate = firedDate;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
}
