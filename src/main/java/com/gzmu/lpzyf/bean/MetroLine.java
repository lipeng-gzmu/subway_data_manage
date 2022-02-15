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
    private String metroName;
    //完整线路名
    private String metroNameAll;
    //线路状态
    private Integer lineStatus;
    //城市id
    private City city;
    //是否环形线
    private Integer ifRing;
    //线路颜色
    private String lineColor;
    //每个city_id 出现的次数
    private Integer num;
}
