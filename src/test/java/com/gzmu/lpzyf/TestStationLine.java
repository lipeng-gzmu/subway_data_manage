package com.gzmu.lpzyf;

import com.gzmu.lpzyf.service.CrawlerService;
import com.gzmu.lpzyf.service.Line_StationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestStationLine {
    @Autowired
    private Line_StationService line_statiionService;
    @Autowired
    private CrawlerService crawlerService;
    @Test
    public void testLineStation(){
        line_statiionService.findByCityId("");
    }

    @Test
    public void testInsertData(){
        crawlerService.getSubwayData("");
    }
}
