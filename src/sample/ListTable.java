package sample;

import java.io.*;
import java.util.ArrayList;

public class ListTable {
    public static ArrayList<String> xmlView(File file) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
        ArrayList<String> list = new ArrayList<>();
        String str;
        String [] arr;
        while ((str = reader.readLine()) != null) {
            if (!str.isEmpty()) {
                String regex = ">";
                arr = str.split(regex);
                for (String anArr : arr) {
                    list.add(anArr + regex);
                    if (list.size() > 100) break;
                }
            }
        }
        reader.close();
        return list;
    }
}
