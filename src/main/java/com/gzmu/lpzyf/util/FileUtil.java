package com.gzmu.lpzyf.util;

import java.io.*;
import java.util.Base64;

public class FileUtil {


    public static String[] getDecode(String security){
        byte[] decode = Base64.getDecoder().decode(security);
        String[] result = new String(decode).split(",");
        return result;
    }

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
