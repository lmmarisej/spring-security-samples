package org.javaboy.receive_report;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
    @RequestMapping("/report")
    public void report(@RequestBody String report) {
        System.out.println("report = " + report);
    }
}
