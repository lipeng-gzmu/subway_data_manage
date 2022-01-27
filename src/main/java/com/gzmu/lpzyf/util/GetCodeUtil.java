package com.gzmu.lpzyf.util;

import java.util.Random;

public class GetCodeUtil {
    public static String getCode(){
        String code = "";
        int max=9;
        int min = 0;
        for(int i=0;i<6;i++){
            code += (int)(Math.random()*(max-min)+min);
        }
        return code;
    }
}
