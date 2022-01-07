package com.gzmu.lpzyf.util;

/**
 * 导入数据进度
 */
public class SpeedOfProgress {
    public static double cityInsertProgress;
    public volatile static double suwayInsertProgress;
    //导入数据控制
    public volatile static boolean suwayInsertProgressCheck =false;
    public volatile static String suwayCheck = "false";
    public volatile static String message;
}
