package com.bestv.wechat.liteapp.premierleague.controller;

import com.bestv.wechat.liteapp.premierleague.function.SinaTimeLineFunction;
import com.bestv.wechat.liteapp.premierleague.service.impl.SinaTimeLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/premierleague")
public class SinaTimeLineController {

    @Autowired
    SinaTimeLineService sinaTimeLineService;
    @Autowired
    SinaTimeLineFunction sinaTimeLineFunction;
    @RequestMapping(value = "/timeline/pull", method = RequestMethod.GET)
    void pullSinaTimeLine(HttpServletRequest request, HttpServletResponse response) {
        sinaTimeLineFunction.pullTimeLineFromSina();

    }

}
