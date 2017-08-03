package com.company.controller;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class HomeController {

    @RequestMapping(value = {"/", "getweatherjson"}, method = RequestMethod.GET)

    public ModelAndView helloWorld() // gets a webpage using the ModelandView method
    {

        String prodCenter = ""; // declared outside for scope issues
        try {
            //httpclient interface represents the contract for the hteep request excuection
            HttpClient http = HttpClientBuilder.create().build();
            // htttphost holds the variables needed for connection
            // http port default http 80 https 443

            HttpHost host = new HttpHost("forecast.weather.gov", 80, "http");
            //HttpGet retreives the infor identified by the request uri (in form of an entity)
            HttpGet getPage = new HttpGet("MapClick.php?lat=42.331427&lon=-83.045754&FcstType=json");


            HttpResponse resp = http.execute(host, getPage);

            String jsonString = EntityUtils.toString(resp.getEntity());

            // assign the returned result to a json object

            JSONObject json = new JSONObject(jsonString);

            prodCenter = json.get("productionCenter").toString();

            //Response code prints to console
            System.out.println("Response code: " + resp.getStatusLine().getStatusCode());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return new // useful for adding one or two things to a page the model is the data
                ModelAndView("welcome", "message", prodCenter);
    }


}