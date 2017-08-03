package com.company.controller;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class HomeController {

    @RequestMapping(value = {"/", "getweatherjson"}, method = RequestMethod.GET)

    public ModelAndView helloWorld() // gets a webpage using the ModelandView method
    {
        //httpclient interface represents the contract for the hteep request excuection
        HttpClient http = HttpClientBuilder.create().build();
        // htttphost holds the variables needed for connection
        // http port default http 80 https 443

        HttpHost host = new HttpHost("forecast.weather.gov", 80, "http");
        //HttpGet retreives the infor identified by the request uri (in form of an entity)
        HttpGet getPage = new HttpGet("MapClick.php?lat=42.331427&lon=-83.045754&FcstType=json");

        return new // useful for adding one or two things to a page the model is the data
                ModelAndView("welcome", "message", "Hello World");
    }


}