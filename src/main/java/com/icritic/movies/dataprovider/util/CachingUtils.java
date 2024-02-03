package com.icritic.movies.dataprovider.util;

public class CachingUtils {

    public static String buildPaginationCachekey(String mainKey, int page, int size) {
        return mainKey + ":" + String.valueOf(page) + ":" + String.valueOf(size);
    }
}
