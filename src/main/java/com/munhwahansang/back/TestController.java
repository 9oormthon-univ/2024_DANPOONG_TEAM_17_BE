package com.munhwahansang.back;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String home() {
        return "안녕하세요 문화한상입니다~~~~~~!!!!!!!";
    }
}
