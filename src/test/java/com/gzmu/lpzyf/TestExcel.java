package com.gzmu.lpzyf;

import com.gzmu.lpzyf.service.ExcelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestExcel {
    @Autowired
    private ExcelService excelService;

    @Test
    public void testexcel(){
        excelService.dataToEs("");
    }
}
