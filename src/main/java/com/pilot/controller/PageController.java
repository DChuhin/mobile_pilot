package com.pilot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getIndexPage() {
        return "index";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String getDashboardPage() {
        return "dashboard";
    }

    @RequestMapping(value = "/logs", method = RequestMethod.GET)
    public String getLogsPage() {
        return "logs";
    }

}
