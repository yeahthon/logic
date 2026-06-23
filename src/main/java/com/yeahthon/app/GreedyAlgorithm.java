package com.yeahthon.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

// 贪心算法
public class GreedyAlgorithm {
    public static void main(String[] args) {
        // 创建电台，放入到Map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        // 加入电台
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        // 存放所有的地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        // 存放当前选择电台的集合
        ArrayList<String> selects = new ArrayList<>();
        // 存放已经覆盖的地区 ∩ 尚未覆盖的地区
        HashSet<String> tempSet = new HashSet<>();
        // 每次遍历中，能够覆盖尚最大未覆盖区域对应的电台
        String maxKey = null;
        while (!allAreas.isEmpty()) {
            // allAreas.size() 不为0则表示尚未覆盖到所有区域
            // 每次遍历都需重置maxKey
            maxKey = null;

            // 遍历broadcasts，取出对应key
            for (String key : broadcasts.keySet()) {
                tempSet.clear();
                // 当前key能够覆盖的区域
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                tempSet.retainAll(allAreas);
                if (!tempSet.isEmpty() &&
                        (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }

            if (maxKey != null) {
                selects.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println(selects);
    }
}
