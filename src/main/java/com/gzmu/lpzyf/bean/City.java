package com.gzmu.lpzyf.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class City {
    @ExcelProperty(value = "id",index = 0)
    private Integer id;
    @ExcelProperty(value="中文名",index = 1)
    private String nameCn;
    @ExcelProperty(value="英文名",index=2)
    private String nameEn;
}
