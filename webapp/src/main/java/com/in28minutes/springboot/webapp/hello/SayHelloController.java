package com.in28minutes.springboot.webapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {

    @GetMapping("say-hello")
    @ResponseBody
    public String sayHello() {
        return "Hello! What are you learning today?ddd";
    }

    @GetMapping("say-hello-html")
    @ResponseBody
    public String sayHelloHtml() {
        StringBuffer sb = new StringBuffer();

        return "Hello! What are you learning today?ddd";
    }
}
