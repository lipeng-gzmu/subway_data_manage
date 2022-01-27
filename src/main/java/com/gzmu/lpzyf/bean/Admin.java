package com.gzmu.lpzyf.bean;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Admin {
    private String phone;
    private String name;
    private String password;
    private Date createTime;
}
