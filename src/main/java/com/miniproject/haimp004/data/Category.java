package com.miniproject.haimp004.data;

import org.springframework.data.repository.query.Param;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.List;

@Entity
public class Category {
    @Id
    private String nameCategory;
    private String detailCategory;

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getDetailCategory() {
        return detailCategory;
    }

    public void setDetailCategory(String detailCategory) {
        this.detailCategory = detailCategory;
    }
}
