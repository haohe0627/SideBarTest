package com.example.hh.sidebartest;

import java.util.Comparator;

/**
 * Created by haohe on 2017/4/19 0019.
 * 拼音排序器
 */

public class PinyinComparator implements Comparator<SortModel>{

    // model2 是默认排序中靠前的字符，model1是model2的下一位
    // compare 函数中 0 相等，-1 小于 model1在前，1 大于 model2在前
    @Override
    public int compare(SortModel o1, SortModel o2) {
        if (o1.getSortLetters().equals("@")
                || o2.getSortLetters().equals("#")) { // 如果model2 属性属于# 那么默认model2小于model1 ，即model1排序在前
            return -1; //
        } else if (o1.getSortLetters().equals("#")
                || o2.getSortLetters().equals("@")) { // 与上相反，如果model2排序在前
            return 1;
        } else {
            return o1.getSortLetters().compareTo(o2.getSortLetters());
        }
    }
}
