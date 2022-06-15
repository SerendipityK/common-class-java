package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class TestList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();
        list.add("第一");
        list.add("第二");
        list.add("第三");
        list.add("第四");
        for (int i = 0; i < list.size(); i++) {
            buffer.append(list.get(i));
            if (i != list.size() - 1){
                buffer.append(",");
            }
        }
        System.out.println(buffer);
    }
}
