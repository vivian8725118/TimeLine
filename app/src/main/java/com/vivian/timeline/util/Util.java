package com.vivian.timeline.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * *          _       _
 * *   __   _(_)_   _(_) __ _ _ __
 * *   \ \ / / \ \ / / |/ _` | '_ \
 * *    \ V /| |\ V /| | (_| | | | |
 * *     \_/ |_| \_/ |_|\__,_|_| |_|
 * <p>
 * Created by vivian on 2017/6/12.
 */

public class Util {
    /**
     * long转成2015.01.03格式
     *
     * @param time 单位ms s的话需要*1000
     * @return
     */
    public static String LongtoStringFormat(long time) {
        Date currentTime = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd a HH:mm");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
}
