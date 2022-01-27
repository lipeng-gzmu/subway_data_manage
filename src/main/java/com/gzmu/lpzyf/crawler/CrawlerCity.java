package com.gzmu.lpzyf.crawler;

import com.gzmu.lpzyf.bean.City;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Component
public class CrawlerCity {

    public  List<City> getCites(){
        List<City> result = new LinkedList<>();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://map.amap.com/subway/index.html");
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode()==200){
                HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity, "utf-8");
                Document doc = Jsoup.parse(content);
                Elements cityElements = doc.select(".city,.other-city");
                for (Element cityElement : cityElements) {
                    City city = new City();
                    city.setId(Integer.valueOf(cityElement.attr("id")));
                    city.setName_cn(cityElement.text());
                    city.setName_en(cityElement.attr("cityname"));
                    result.add(city);
                }
                return result;
            }else{
                throw new RuntimeException("爬取数据失败，请检查网络或其他");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
