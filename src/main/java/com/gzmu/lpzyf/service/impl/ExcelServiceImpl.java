package com.gzmu.lpzyf.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.gzmu.lpzyf.bean.City;
import com.gzmu.lpzyf.bean.Line_Station;
import com.gzmu.lpzyf.bean.MetroLine;
import com.gzmu.lpzyf.bean.MetroStation;
import com.gzmu.lpzyf.mapper.CityMapper;
import com.gzmu.lpzyf.mapper.Line_StationMapper;
import com.gzmu.lpzyf.mapper.MetroLineMapper;
import com.gzmu.lpzyf.mapper.MetroStationMapper;
import com.gzmu.lpzyf.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private Line_StationMapper line_stationMapper;
    @Autowired
    private MetroLineMapper metroLineMapper;
    @Autowired
    private MetroStationMapper metroStationMapper;
    @Override
    public void dataToEs(String filePathName) {
        List<City> allCities = cityMapper.findAll();
        List<MetroLine> allLines = metroLineMapper.findAll();
        List<MetroStation> allStations = metroStationMapper.findAll();
        List<Line_Station> allStationLine = line_stationMapper.findAll();
        ExcelWriter excelWriter = EasyExcel.write("G://subwaydata.xlsx").build();
        WriteSheet city = EasyExcel.writerSheet(0,"城市").head(City.class).build();
        WriteSheet line = EasyExcel.writerSheet(1,"线路").head(MetroLine.class).build();
        WriteSheet station = EasyExcel.writerSheet(2,"站点").head(MetroStation.class).build();
        WriteSheet lineStation  = EasyExcel.writerSheet(3,"站点线路关联").head(Line_Station.class).build();
        excelWriter.write(allCities,city);
        excelWriter.write(allLines,line);
        excelWriter.write(allStations,station);
        excelWriter.write(allStationLine,lineStation);
        excelWriter.finish();

    }
}
