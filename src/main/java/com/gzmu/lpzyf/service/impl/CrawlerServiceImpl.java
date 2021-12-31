package com.gzmu.lpzyf.service.impl;

import com.gzmu.lpzyf.bean.City;
import com.gzmu.lpzyf.bean.Line_Station;
import com.gzmu.lpzyf.bean.MetroLine;
import com.gzmu.lpzyf.bean.MetroStation;
import com.gzmu.lpzyf.crawler.CrawlerGetSubwayData;
import com.gzmu.lpzyf.mapper.CityMapper;
import com.gzmu.lpzyf.mapper.Line_StationMapper;
import com.gzmu.lpzyf.mapper.MetroLineMapper;
import com.gzmu.lpzyf.mapper.MetroStationMapper;
import com.gzmu.lpzyf.service.CrawlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class CrawlerServiceImpl implements CrawlerService {
    public static final Logger logger = LoggerFactory.getLogger(CrawlerServiceImpl.class);
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private Line_StationMapper line_stationMapper;
    @Autowired
    private MetroStationMapper metroStationMapper;
    @Autowired
    private MetroLineMapper metroLineMapper;
    @Autowired
    private CrawlerGetSubwayData crawlerGetSubwayData;

    @Transactional
    @Override
    public String getSubwayData() {
        List<City> allCities = cityMapper.findAll();
        Map<String, List> subwayData = crawlerGetSubwayData.getSubwayData(allCities);
        logger.info("数据爬取完成，正在导入数据库");
        List<MetroLine> metroLines = subwayData.get("metroLines");
        List<MetroStation> metroStations = subwayData.get("metroStations");
        List<Line_Station> line_stations = subwayData.get("line_station");


        for(int i=0;i<metroLines.size();i++){
            metroLineMapper.insert(metroLines.get(i));
            logger.info("正在导入地铁线路数据到数据库:"+(i+1)+"/"+metroLines.size());
        }

        int stationIndex=1;
        for (MetroStation metroStation : metroStations) {
            //System.out.println(metroStation);
            metroStationMapper.insert(metroStation);
            logger.info("正在导入站点数据到数据库："+(stationIndex++)+"/"+metroStations.size());
        }
        int lineStationIndex = 1;
        for (Line_Station line_station : line_stations) {
            //System.out.println(line_station);
            line_stationMapper.insert(line_station);
            logger.info("正在导入站点与线路关联关系到数据库"+(lineStationIndex++)+"/"+line_stations.size());
        }
        return "插入数据成功";
    }
}
