package com.gzmu.lpzyf.bean.crawler;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SubwayLine {
    //线路id
    private String ls;
    //线路名
    private String ln;
    private String kn;

    //线路颜色
    private String cl;
    //是否环线
    private String lo;
    //线路状态
    private String su;
    //站
    private List<SubwayStation> st;
}
