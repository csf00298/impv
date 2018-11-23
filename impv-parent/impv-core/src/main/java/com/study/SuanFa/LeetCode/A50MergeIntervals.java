package com.study.SuanFa.LeetCode;

import java.util.*;

/**
 * 区间合并
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 */
public class A50MergeIntervals {

    public static void main(String[] args) {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(1, 3));
        list.add(new Interval(15, 18));
        list.add(new Interval(8, 16));
        list.add(new Interval(2, 6));
        merge(list);
    }

    public static List<Interval> merge(List<Interval> sourceList) {
        List<Interval> list = sortByStart(sourceList);
        for (int i = 0; i < list.size()-1; i++) {
            if (list.get(i).end > list.get(i + 1).start) {
                Interval interval = new Interval(list.get(i).start, list.get(i + 1).end);
                list.set(i + 1, interval);
                list.set(i, null);
            }
        }
        List<Interval> result = new ArrayList<>();
        list.forEach(e -> {
            if (e != null) result.add(e);
        });
        return result;
    }

    public static List<Interval> sortByStart(List<Interval> list) {
        TreeMap<Integer, Interval> sortMap = new TreeMap<>();
        list.forEach(e -> sortMap.put(e.start, e));
        List<Interval> sortedList = new ArrayList<>();
        sortMap.entrySet().forEach(e -> sortedList.add(e.getValue()));
        return sortedList;
    }
}

class Interval {
    Integer start;
    Integer end;

    public Interval(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
