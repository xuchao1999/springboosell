package com.xc.sell.dataobject;

import com.xc.sell.util.DateUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@DynamicUpdate
@Data
public class UserInfo {
    @Id
    private String id;
    private String openId;

    private String username;
    private String password;
    private String phone;
    private Date createTime = DateUtil.getCurrentDate();
    private Date updateTime = DateUtil.getCurrentDate();

}
