package com.yun.entities;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Dept {
    @TableId
    private Long deptId;

    private String deptName;

    private String leader;

    private String phone;

    private String email;



}
