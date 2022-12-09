package com.example.demojwt;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class GsonDemo {
    public static void main(String[] args) {
        Gson gson = new Gson();

        Map<String, Object> map = new HashMap<>();
        map.put("name", "Tom");
        map.put("age", 23);

        String json = gson.toJson(map);

        System.out.println(json);
        // {"name":"Tom","age":23}
    }
}
