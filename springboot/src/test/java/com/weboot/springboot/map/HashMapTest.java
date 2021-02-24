package com.weboot.springboot.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class HashMapTest {
    public static void main(String[] args) {
        HashMap<String, ArrayList<String>> hashMap = new HashMap<>();
        ArrayList<String> sanguo = new ArrayList<>();
        sanguo.add("诸葛亮");
        sanguo.add("刘备");
        hashMap.put("三国演义",sanguo);
        ArrayList<String> xiyou = new ArrayList<>();
        xiyou.add("孙悟空");
        xiyou.add("唐僧");
        hashMap.put("西游记",xiyou);
        ArrayList<String> shuihu = new ArrayList<>();
        shuihu.add("武松");
        shuihu.add("鲁智深");
        hashMap.put("水浒传",shuihu);

        Set<String> strings = hashMap.keySet();
        for(String str : strings){
            ArrayList<String> arrayList = hashMap.get(str);
            System.out.println(str);
            for(String string : arrayList){
                System.out.println("\t"+string);
            }
        }
    }
}
