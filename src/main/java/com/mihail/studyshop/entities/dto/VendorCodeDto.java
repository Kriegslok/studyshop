package com.mihail.studyshop.entities.dto;

public class VendorCodeDto {

    String vendorName;
    String vendorGuid;
    String code;

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorGuid() {
        return vendorGuid;
    }

    public void setVendorGuid(String vendorGuid) {
        this.vendorGuid = vendorGuid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "VendorCodeDto{" +
                "vendorName='" + vendorName + '\'' +
                ", vendorGuid='" + vendorGuid + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
