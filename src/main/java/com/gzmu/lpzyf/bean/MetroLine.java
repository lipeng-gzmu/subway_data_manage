package com.gzmu.lpzyf.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MetroLine implements Serializable {
    //线路id
    @ExcelProperty(value = "id",index = 0)
    private String id;
    //线路名
    @ExcelProperty(value="线路名",index = 1)
    private String metroName;
    //完整线路名
    @ExcelProperty(value="线路全名",index = 2)
    private String metroNameAll;
    //线路状态
    @ExcelProperty(value = "线路状态",index = 3)
    private Integer lineStatus;
    //城市id
    private City city;
    //是否环形线
    @ExcelProperty(value = "环线",index = 4)
    private Integer ifRing;
    //线路颜色
    private String lineColor;
    //每个city_id 出现的次数
    private Integer num;
}
