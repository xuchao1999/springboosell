package com.xc.sell.dataobject;

import com.xc.sell.util.DateUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@DynamicUpdate //更新任何字段后，可自动更新updateData字段
@Data
public class ProductCategory {
    @Id
    @GeneratedValue
    private Integer categoryId;

    private String categoryName;

    private Date createTime;

    private Date updateTime;

    public ProductCategory(){}

    public ProductCategory(String categoryName) {
        this.categoryName = categoryName;
        this.createTime = DateUtil.getCurrentDate();
        this.updateTime = DateUtil.getCurrentDate();
    }
}
