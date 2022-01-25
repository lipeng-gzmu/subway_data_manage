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
}
