package com.wfx.quality.award;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 14-9-2.
 */
public class Test {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH_mm_ss");
        String dateStr = sdf.format(new Date());
        System.out.println(dateStr);
    }
}
