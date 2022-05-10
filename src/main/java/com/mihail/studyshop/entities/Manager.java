package com.mihail.studyshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Manager {
    @Transient
    @JsonIgnore
    public static final String ON_HOLIDAY = "on holiday";
    @Transient
    @JsonIgnore
    public static final String NOT_ON_HOLIDAY = "not on holiday";
    @Transient
    @JsonIgnore
    public static final String BLOCKED = "blocked";
    @Transient
    @JsonIgnore
    public static final String NOT_BLOCKED = "on duty";

    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    private UUID guid;
    private String firstName;
    private String lastName;
    private String inn;
    @CreationTimestamp
    private LocalDateTime dateCreate;
    private Boolean isBlocked;
    private Boolean isOnHoliday;
    private LocalDateTime employmentDate;
    private LocalDateTime firedDate;

   @OneToMany(mappedBy = "manager",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JsonManagedReference
    private List<Phone> phones = new ArrayList<>();
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
