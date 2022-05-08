package org.example.network;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 王超
 * @description TODO
 * @date 2022-05-09 1:49
 */
@SpringBootApplication(scanBasePackages = "org.example")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
