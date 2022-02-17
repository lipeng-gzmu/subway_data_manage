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
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
            httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:96.0) Gecko/20100101 Firefox/95.0");
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
                        metroLine.setLineStatus(Integer.valueOf(line.getSu()));
                        metroLine.setIfRing(Integer.valueOf(line.getLo()));
                        metroLine.setLineColor(line.getCl());
                        metroLine.setCity(city);
                        metroLines.add(metroLine);
                        List<SubwayStation> stations = line.getSt();

                        Integer orderNum=1;
                        for (SubwayStation station : stations) {
                            MetroStation metroStation = new MetroStation();
                            metroStation.setId(station.getSid());
                            metroStation.setName(station.getN());
                            metroStation.setNameEn(station.getSp());
                            String[] split = station.getSl().split(",");
                            metroStation.setLatitude(split[0]);
                            metroStation.setLongitude(split[1]);
                            String[] coordinate = station.getP().split(" ");
                            metroStation.setCoordinateX(Double.valueOf(coordinate[0])/10);
                            metroStation.setCoordinateY(Double.valueOf(coordinate[1])/10);
                            metroStation.setIfTransfer(Integer.valueOf(station.getT()));
                            metroStation.setStationStatus(Integer.valueOf(station.getSu()));
                            metroStations.add(metroStation);
                           /* String[] lineIds = station.getR().split("\\|");
                            for(int i=0;i<lineIds.length;i++){
                                Line_Station line_station = new Line_Station();
                                line_station.setLineId(lineIds[i]);
                                line_station.setStationId(station.getSid());
                                String id = DigestUtils.md5DigestAsHex((lineIds[i]+station.getSid()).getBytes());
                                line_station.setId(id);
                                if(lineIds[i].equals(line.getLs())){
                                    line_station.setOrderNum(orderNum);
                                    line_stations.add(line_station);
                                }
                            }*/
                            Line_Station line_station = new Line_Station();
                            line_station.setLineId(line.getLs());
                            line_station.setStationId(station.getSid());
                            String id = DigestUtils.md5DigestAsHex((line.getLs()+station.getSid()).getBytes());
                            line_station.setId(id);
                            line_station.setOrderNum(orderNum);
                            line_stations.add(line_station);
                            orderNum++;
                        }
                        orderNum=1;
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
