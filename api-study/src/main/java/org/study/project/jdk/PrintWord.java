package org.study.project.jdk;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenyao
 * @date 2020/3/19 15:06
 * @description
 */
public class PrintWord {

    public static void main(String[] args) throws IOException {
        Map<String, Integer> map = new HashMap<>();

        //从文件中读取文章 或者从流中读取
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("E:\\IDEA\\WorkSpace\\my-project\\study-project\\api-study\\src\\main\\java\\org\\study\\project\\jdk\\article.txt")));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] words = line.split(" ");
            for (String word : words) {
                if (word.length() == 0) continue;
                word = getRealString(word).toLowerCase();
                if (map.containsKey(word)) {
                    map.put(word, map.get(word) + 1);
                } else {
                    map.put(word, 1);
                }
            }
        }
        reader.close();





        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.print(entry.getKey());
            System.out.print("  ");
            System.out.println(entry.getValue());
        }

    }

    private static String getRealString(String word) {
        StringBuilder sb = new StringBuilder();
        for (char c : word.toCharArray()) {
            if ((c <= 'z' && c >= 'a') || (c <='Z' && c >= 'A')) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
