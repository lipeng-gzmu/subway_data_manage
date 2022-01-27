package com.gzmu.lpzyf;

import com.gzmu.lpzyf.util.FileUtil;
import com.gzmu.lpzyf.util.SendSmsUtil;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Base64;

public class TestSm {
    @Test
    public void testTest(){
        SendSmsUtil.send("478989","+8618786551925");
    }

    @Test
    public void testDecode(){

    }
    @Test
    public void testFile(){

    }


}
