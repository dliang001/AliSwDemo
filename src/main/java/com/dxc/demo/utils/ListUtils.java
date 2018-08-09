package com.dxc.demo.utils;

import java.util.Collection;

public class ListUtils {

    private ListUtils() {
    }

    public static boolean isNotEmpty(Collection<?> list) {
        return list != null && list.size() > 0;
    }

    public static boolean isEmpty(Collection<?> list) {
        return !isNotEmpty(list);
    }
}
