package org.atlas.shared.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtil {

    private static final ConcurrentMap<String, SimpleDateFormat> simpleDateFormatCache = new ConcurrentHashMap<>();

    public static Date parse(String source, String pattern) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = simpleDateFormatCache.computeIfAbsent(pattern, SimpleDateFormat::new);
        try {
            return simpleDateFormat.parse(source);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String format(Date source, String pattern) {
        if (source == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = simpleDateFormatCache.computeIfAbsent(pattern, SimpleDateFormat::new);
        return simpleDateFormat.format(source);
    }

    public static String now(String pattern) {
        return format(new Date(), pattern);
    }

    public static Date tomorrow() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        resetToMidnight(calendar);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }

    public static Date yesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        resetToMidnight(calendar);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime();
    }

    private static void resetToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
}
