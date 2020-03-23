package com.zhongke.controller;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RestController
public class GetDataController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/getdata")
    public String getdata(@RequestParam("name") String s,@RequestParam("age") int b){
        System.out.println(s+b);
        return "hongmo";
    }

    @RequestMapping("/getrest")
    public String getrest(){
        String url = "http://localhost:8080/getdata?name={name}&age={age}";
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","jack");
        map.put("age",13);
        String json = restTemplate.getForObject(url, String.class, map);
        //String json = restTemplate.getForObject(url, String.class);
        System.out.println(json);
        return json;
    }
}
