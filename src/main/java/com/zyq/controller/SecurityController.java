package com.zyq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SecurityController {
    @GetMapping("/level/1")
    public String getLevel1(){
        return "level1";
    }
    @GetMapping("/level/2")
    public String getLevel2(){
        return "level2";
    }
    @GetMapping("/level/3")
    public String getLevel3(){
        return "level3";
    }
}
