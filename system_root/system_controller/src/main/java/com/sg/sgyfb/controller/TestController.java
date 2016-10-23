package com.sg.sgyfb.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.nio.channels.ReadableByteChannel;
import java.util.Map;

/**
 * Created by lizhe on 2016/9/29.
 */
@Controller
public class TestController {
    static final Logger logger = LogManager.getLogger(TestController.class.getName());
    private class Sessiong{
        private String teacherid;

        @Override
        public String toString() {
            return "Sessiong{" +
                    "teacherid='" + teacherid + '\'' +
                    '}';
        }

        public String getTeacherid() {
            return teacherid;
        }

        public void setTeacherid(String teacherid) {
            this.teacherid = teacherid;
        }
    }

    @ModelAttribute
    public void modelAttribute(Map<String,Object> map){

       Sessiong session = new Sessiong();
        session.setTeacherid("1234");
        System.out.println("test");
        map.put("sessiong",session);

    }

    @RequestMapping("/test.shtml")
    @ResponseBody
    public String test(Sessiong sessiong){

        System.out.println(sessiong.toString());

        return "ok";
    }
}
