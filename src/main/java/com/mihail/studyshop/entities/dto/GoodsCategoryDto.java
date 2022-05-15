package com.mihail.studyshop.entities.dto;

public class GoodsCategoryDto {
    String description;
    String parentGuid;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParentGuid() {
        return parentGuid;
    }

    public void setParentGuid(String parentGuid) {
        this.parentGuid = parentGuid;
    }
}
