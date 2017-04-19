package com.example.hh.sidebartest;

import java.util.Comparator;

/**
 * Created by haohe on 2017/4/19 0019.
 * 拼音排序器
 */

public class PinyinComparator implements Comparator<SortModel>{

    @Override
    public int compare(SortModel o1, SortModel o2) {
        if (o1.getSortLetters().equals("@")
                || o2.getSortLetters().equals("#")) {
            return -1;
        } else if (o1.getSortLetters().equals("#")
                || o2.getSortLetters().equals("@")) {
            return 1;
        } else {
            return o1.getSortLetters().compareTo(o2.getSortLetters());
        }
    }
}
