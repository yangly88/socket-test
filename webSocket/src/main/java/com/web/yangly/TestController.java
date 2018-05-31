package com.web.yangly;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yangly@eastcom-sw.com
 * @create 2018-05-29 9:18
 **/
@Controller
public class TestController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        logger.info("test");
        logger.error("test");
        logger.debug("test");
        logger.trace("test");
        logger.warn("test");
        return "123";
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
