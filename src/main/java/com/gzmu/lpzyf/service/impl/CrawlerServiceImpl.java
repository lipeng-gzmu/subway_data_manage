package com.gzmu.lpzyf.service.impl;

import com.gzmu.lpzyf.bean.City;
import com.gzmu.lpzyf.bean.Line_Station;
import com.gzmu.lpzyf.bean.MetroLine;
import com.gzmu.lpzyf.bean.MetroStation;
import com.gzmu.lpzyf.crawler.CrawlerCity;
import com.gzmu.lpzyf.crawler.CrawlerGetSubwayData;
import com.gzmu.lpzyf.mapper.CityMapper;
import com.gzmu.lpzyf.mapper.Line_StationMapper;
import com.gzmu.lpzyf.mapper.MetroLineMapper;
import com.gzmu.lpzyf.mapper.MetroStationMapper;
import com.gzmu.lpzyf.service.CrawlerService;
import com.gzmu.lpzyf.util.SpeedOfProgress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.spi.http.HttpContext;
import java.util.HashMap;
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
    @Autowired
    private CrawlerCity crawlerCity;

    @Transactional
    @Override
    public Map<String,List> getSubwayData() {
        Map<String,List> resultMap = new HashMap<>();
        List<City> allCities = cityMapper.findAll();
        Map<String, List> subwayData = crawlerGetSubwayData.getSubwayData(allCities);
        logger.info("数据爬取完成，正在导入数据库");
        List<MetroLine> metroLines = subwayData.get("metroLines");
        List<MetroStation> metroStations = subwayData.get("metroStations");
        List<Line_Station> line_stations = subwayData.get("line_station");
        int sum = metroLines.size();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (SpeedOfProgress.suwayCheck){
                    try {
                        SpeedOfProgress.suwayInsertProgressCheck=false;
                        SpeedOfProgress.suwayCheck.wait(10000);
                        if (!SpeedOfProgress.suwayInsertProgressCheck){
                            SpeedOfProgress.suwayInsertProgress=-1.0;
                            logger.info("等待超时，放弃插入数据");
                            return;
                        }else {
                            SpeedOfProgress.suwayInsertProgress=0.0;
                            for(int i=0;i<metroLines.size();i++){
                                metroLineMapper.insert(metroLines.get(i));
                                SpeedOfProgress.suwayInsertProgress=((i+1)*1.0/sum)*100;
                                System.out.println(SpeedOfProgress.suwayInsertProgress);
                                logger.info("正在导入地铁线路数据到数据库:"+(i+1)+"/"+metroLines.size());
                            }
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();


       /* int stationIndex=1;
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
        }*/
        return subwayData;
    }

    @Transactional
    @Override
    public String getCitiesData() {
        List<City> cites = crawlerCity.getCites();
        logger.info("爬取城市数据完成");
        int status = 1;
        for (City city : cites) {
            cityMapper.insert(city);
            logger.info("正在导入城市数据到数据库："+(status++)+"/"+cites.size());
        }
        return "导入数据成功";
    }
}
