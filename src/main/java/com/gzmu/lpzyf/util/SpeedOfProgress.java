package com.gzmu.lpzyf.util;

/**
 * 导入数据进度
 */
public class SpeedOfProgress {
    public static double cityInsertProgress;
    public volatile static double suwayInsertProgress;
    //导入数据控制
    public volatile static boolean suwayInsertProgressCheck =false;
    //锁对象，判断是否开启用户上传
    public volatile static String suwayCheck = "false";
    public volatile static String message;
}
