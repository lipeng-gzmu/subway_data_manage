package com.gzmu.lpzyf.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class ElasticSearchConfig {
    public static final Logger logger = LoggerFactory.getLogger(ElasticSearchConfig.class);
    @Value("${elasticsearch-client.host}")
    private  String host;
    @Value("${elasticsearch-client.port}")
    private  Integer port;
    @Value("${elasticsearch-client.ifConnect}")
    private String ifConnect;
    @Bean(destroyMethod = "close")
    public RestHighLevelClient getRestHighLevelClient(){
        RestClientBuilder clientBuilder = RestClient.builder(new HttpHost(host, port, "http"))
                .setRequestConfigCallback(r->r.setConnectTimeout(90000)
                .setSocketTimeout(90000)
                .setConnectionRequestTimeout(90000));
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(clientBuilder);
        if(ifConnect.equals("true")){
            while(true){
                try{
                    if(restHighLevelClient.ping(RequestOptions.DEFAULT)){
                        break;
                    }
                }catch (Exception e){
                    logger.warn("ElasticSearch连接失败，10s后将再次尝试连接....");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            logger.info("ElasticSearch连接成功!!!!!");
        }
        return restHighLevelClient;
    }
}
