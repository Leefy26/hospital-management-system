package com.hospital.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     * 处理对网站根路径 ("/") 的访问请求
     */
    @GetMapping("/")
    public String home() {
        // 重定向到病人列表页面
        return "redirect:/patient/list";
    }
}