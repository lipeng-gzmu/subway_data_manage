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


    @Override
    public Map<String,List> getSubwayData(String type) {
        Map<String,List> resultMap = new HashMap<>();
        List<City> allCities = cityMapper.findAll();
        Map<String, List> subwayData = crawlerGetSubwayData.getSubwayData(allCities);
        List<MetroLine> metroLines = subwayData.get("metroLines");
        List<MetroStation> metroStations = subwayData.get("metroStations");
        List<Line_Station> line_stations = subwayData.get("line_station");
        logger.info("数据爬取完成，正在导入数据库");
        int i=0;
       /* for (MetroLine metroLine : metroLines) {
            metroLineMapper.insert(metroLine);
            logger.info("正在导入线路数据:"+(++i)+"/"+metroLines.size());
        }
        i=0;
        for (MetroStation metroStation : metroStations) {
            metroStationMapper.insert(metroStation);
            logger.info("正在导入站点数据:"+(++i)+"/"+metroStations.size());
        }*/
        i=0;
        for (Line_Station line_station : line_stations) {
            line_stationMapper.insert(line_station);
            logger.info("正在导入关联数据:"+(++i)+"/"+line_stations.size());
        }
        /*int sum = metroLines.size()+metroStaStationtions.size()+line_stations.size();
            new Thread(new Runnable() {
                @Transactional
                @Override
                public void run() {
                    synchronized (SpeedOfProgress.suwayCheck){
                        try {
                            SpeedOfProgress.suwayInsertProgressCheck=false;
                            SpeedOfProgress.suwayCheck.wait(1000*60);
                            if (!SpeedOfProgress.suwayInsertProgressCheck){
                                SpeedOfProgress.suwayInsertProgress=-1.0;
                                logger.info("等待超时，放弃插入数据");
                                return;
                            }else {
                                SpeedOfProgress.suwayInsertProgress=0.0;
                                for(int i=0;i<metroLines.size();i++){
                                    metroLineMapper.insert(metroLines.get(i));
                                    SpeedOfProgress.suwayInsertProgress=((i+1)*1.0/sum)*100;
                                    System.out.printf("%.2f\n",SpeedOfProgress.suwayInsertProgress);
                                    logger.info("正在导入地铁线路数据到数据库:"+(i+1)+"/"+sum);
                                }
                                for(int i=0;i<metroStations.size();i++){
                                    metroStationMapper.insert(metroStations.get(i));
                                    SpeedOfProgress.suwayInsertProgress=((i+1+metroLines.size())*1.0/sum)*100;
                                    System.out.printf("%.2f\n",SpeedOfProgress.suwayInsertProgress);
                                    logger.info("正在导入地铁线路数据到数据库:"+(i+1+metroLines.size())+"/"+sum);
                                }
                                for(int i=0;i<line_stations.size();i++){
                                    line_stationMapper.insert(line_stations.get(i));
                                    SpeedOfProgress.suwayInsertProgress=((i+1+metroStations.size()+metroLines.size())*1.0/sum)*100;
                                    System.out.printf("%.2f\n",SpeedOfProgress.suwayInsertProgress);
                                    logger.info("正在导入地铁线路数据到数据库:"+(i+1+metroStations.size()+metroLines.size())+"/"+sum);
                                }
                                return;
                            }

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }).start();*/

            return subwayData;
    }

    @Override
    public Map<String, List> getStationData() {
        return null;
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
