package com.gzmu.lpzyf.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    private String phone;
    private String name;
    private String password;
    private Date createTime;
}
