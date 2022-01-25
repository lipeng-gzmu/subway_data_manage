package com.gzmu.lpzyf.bean.crawler;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SubwayStation {
    //站点id
    private String sid;
    //站点名称
    private String n;
    //站点英文
    private String sp;
    //站点状态
    private String su;
    //经纬度
    private String sl;

    //站点ids
    private String r;
}
