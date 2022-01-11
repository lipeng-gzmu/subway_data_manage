package com.gzmu.lpzyf.crawler;

import com.alibaba.fastjson.JSON;
import com.gzmu.lpzyf.bean.City;
import com.gzmu.lpzyf.bean.Line_Station;
import com.gzmu.lpzyf.bean.MetroLine;
import com.gzmu.lpzyf.bean.MetroStation;
import com.gzmu.lpzyf.bean.crawler.CitySubway;
import com.gzmu.lpzyf.bean.crawler.SubwayLine;
import com.gzmu.lpzyf.bean.crawler.SubwayStation;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class CrawlerGetSubwayData {
    public static final Logger logger = LoggerFactory.getLogger(CrawlerGetSubwayData.class);
    public Map<String,List> getSubwayData(List<City> allCities){
        Map<String,List> resultMap = new HashMap<>();
        List<MetroLine> metroLines = new LinkedList<>();
        List<MetroStation> metroStations = new LinkedList<>();
        List<Line_Station> line_stations = new LinkedList<>();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet;
        int status =1;
        for (City city : allCities) {
            Date timestamp = new Date();
            httpGet = new HttpGet("http://map.amap.com/service/subway?_"+timestamp.getTime()+"&srhdata="+city.getId()+"_drw_"+city.getNameEn()+".json");
            httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:95.0) Gecko/20100101 Firefox/95.0");
            try {
                CloseableHttpResponse response = httpClient.execute(httpGet);
                if (response.getStatusLine().getStatusCode()==200){
                    HttpEntity entity = response.getEntity();
                    String content = EntityUtils.toString(entity, "utf-8");
                    CitySubway citySubway = JSON.toJavaObject(JSON.parseObject(content),CitySubway.class);
                    //获取地铁线路
                    List<SubwayLine> lines = citySubway.getL();
                    for (SubwayLine line : lines) {
                        MetroLine metroLine = new MetroLine();
                        metroLine.setId(line.getLs());
                        metroLine.setMetroName(line.getLn());
                        metroLine.setMetroNameAll(line.getKn());
                        metroLine.setCity(city);
                        metroLines.add(metroLine);
                        List<SubwayStation> stations = line.getSt();
                        for (SubwayStation station : stations) {
                            MetroStation metroStation = new MetroStation();
                            metroStation.setId(station.getSid());
                            metroStation.setName(station.getN());
                            metroStation.setNameEn(station.getSp());
                            String[] split = station.getSl().split(",");
                            metroStation.setLatitude(split[0]);
                            metroStation.setLongitude(split[1]);
                            metroStations.add(metroStation);
                            String[] lineIds = station.getR().split("\\|");
                            for(int i=0;i<lineIds.length;i++){
                                Line_Station line_station = new Line_Station();
                                line_station.setLineId(lineIds[i]);
                                line_station.setStationId(station.getSid());
                                line_stations.add(line_station);
                            }

                        }
                    }
                    logger.info("正在爬取城市地铁数据："+(status++)+"/"+allCities.size());
                    //System.out.println(citySubway);
                   // result.add(JSON.toJSONString(content));
                }else{
                    throw new RuntimeException("数据爬取失败");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       resultMap.put("metroLines",metroLines);
       resultMap.put("metroStations",metroStations);
       resultMap.put("line_station",line_stations);

        return resultMap;
    }
}
