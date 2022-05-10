package com.mihail.studyshop.entities.dto;

public class PhoneDto {
    String phoneNumber;
    String isPrimary;
    String managerGuid;

    public PhoneDto() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(String isPrimary) {
        this.isPrimary = isPrimary;
    }

    public String getManagerGuid() {
        return managerGuid;
    }

    public void setManagerGuid(String managerGuid) {
        this.managerGuid = managerGuid;
    }

    @Override
    public String toString() {
        return "PhoneDto{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", isPrimary=" + isPrimary +
                ", managerGuid='" + managerGuid + '\'' +
                '}';
    }
}
