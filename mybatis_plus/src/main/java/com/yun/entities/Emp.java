package com.yun.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author 代云
 */
     //生成带参构造
public class Emp {
    @TableId
    private Integer userId;

    private Integer deptId;

    private String userName;

    private String email;

    private String phone;

    private String sex;

    public Emp() {
    }

    public Emp(Integer userId, Integer deptId, String userName, String email, String phone, String sex) {
        this.userId = userId;
        this.deptId = deptId;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.sex = sex;
    }

    public Emp(Long userId, Long deptId) {
    }



    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phonenumber) {
        this.phone = phonenumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
