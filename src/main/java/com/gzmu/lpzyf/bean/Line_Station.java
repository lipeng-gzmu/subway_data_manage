package com.gzmu.lpzyf.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Line_Station {
    @ExcelProperty(value = "id",index = 0)
    private String id;
    @ExcelProperty(value = "线路id",index = 1)
    private String lineId;
    @ExcelProperty(value = "站点id",index = 2)
    private String stationId;
    @ExcelProperty(value = "站点顺序",index = 3)
    private Integer orderNum;
}
