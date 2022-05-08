package org.example.network.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王超
 * @description TODO
 * @date 2022-05-09 2:15
 */
@RestController
@RequestMapping("hello")
public class TestController {
    @PutMapping()
    public String test(){
        return "helloworld";
    }
}
