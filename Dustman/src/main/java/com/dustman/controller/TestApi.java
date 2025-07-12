package com.dustman.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("/api/test")
public class TestApi {

    private static final Logger logger = LoggerFactory.getLogger(TestApi.class);

    @GetMapping("/test")
    public String testLog() {
        logger.info("INFO: /api/test endpoint was called");
        logger.warn("WARN: Just a test warning log");
        logger.error("ERROR: Just a test error log");

        return "Log test completed!";
    }
}
