package com.gzmu.lpzyf.bean;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MetroLine implements Serializable {
    //线路id
    private String id;
    //线路名
    private String metro_name;
    //完整线路名
    private String metro_name_all;
    //线路状态
    private Integer line_status;
    //城市id
    private City city;
    //每个city_id 出现的次数
    private Integer num;
}
