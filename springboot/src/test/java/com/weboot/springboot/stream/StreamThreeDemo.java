package com.weboot.springboot.stream;

import java.util.*;
import java.util.stream.Stream;

public class StreamThreeDemo {
    public static void main(String[] args) {
        //Collection的default默认方法生成流
        List<String> list = new ArrayList<>();
        Stream<String> listStream = list.stream();
        
        Set<String> set = new HashSet<>();
        Stream<String> setStream = set.stream();

        Map<String,String> map = new HashMap<>();
        Set<String> keySet = map.keySet();
        Stream<String> keySetStream = keySet.stream();
        Stream<String> valueStream = map.values().stream();

        Stream<Map.Entry<String, String>> entriesStream = map.entrySet().stream();

        String[] array = {"hello","world","java"};
        Stream<String> arrayStream = Stream.of(array);
        Stream<String> stringStream = Stream.of("hello", "world", "java");
        Stream<Integer> integerStream = Stream.of(11, 22, 33);

    }
}
