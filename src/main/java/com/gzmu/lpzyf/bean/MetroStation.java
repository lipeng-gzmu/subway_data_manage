package com.gzmu.lpzyf.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MetroStation {
    //站点id
    @ExcelProperty(value = "id",index = 0)
    private String id;
    //站点名
    @ExcelProperty(value = "站点名",index = 1)
    private String name;
    //站点英文名
    @ExcelProperty(value = "英文名",index = 2)
    private String nameEn;
    //站点经度
    @ExcelProperty(value = "经度",index = 3)
    private String longitude;
    //站点伟度
    @ExcelProperty(value = "纬度",index = 4)
    private String latitude;
    //站点x坐标
    @ExcelProperty(value = "x坐标",index = 5)
    private Double coordinateX;
    //站点y坐标
    @ExcelProperty(value = "y坐标",index = 6)
    private Double coordinateY;
    //是否换乘站
    @ExcelProperty(value = "中转站",index = 7)
    private Integer ifTransfer;
    //站点状态
    @ExcelProperty(value="状态",index = 8)
    private Integer stationStatus;
}
