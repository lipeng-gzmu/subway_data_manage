package com.gzmu.lpzyf.util;

import org.springframework.core.io.ClassPathResource;
import sun.misc.Resource;

import java.io.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class FileUtil {

    /**
     * 解码
     * @param security
     * @return
     */
    public static String[] getDecode(String security){
        byte[] decode = Base64.getDecoder().decode(security);
        String[] result = new String(decode).split(",");
        return result;
    }

    /**
     * 获得短信验证路径
     * @return
     */
    public static String getPath(){
        String property = System.getProperty("user.dir");
        //String path = new File(property).getParent();
        String path = property;
        System.out.println(path);
        return path+"/security";
    }
    //读取密钥
    public static String readFile(String path){
        BufferedReader br = null;
        File file = new File(path);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fileInputStream);
            br = new BufferedReader(isr);
            String s=null;
            while((s=br.readLine())!=null){
                return s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String,String>  readFileToMap(String path){
        Map<String,String> resultMap = new HashMap<>();
        BufferedReader br= null;
        ClassPathResource classPathResource = new ClassPathResource(path);
        try {
            InputStream is = classPathResource.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String s;
            while ((s=br.readLine())!=null){
                String[] split = s.split("=");
                resultMap.put(split[0],split[1]);
            }
            return resultMap;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static void writeFile(String path,String content){
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(path);
            fileWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
