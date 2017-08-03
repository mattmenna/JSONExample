package com.company.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class HomeController {

    @RequestMapping(value = {"/", "getweatherjson"}, method = RequestMethod.GET)

    public ModelAndView helloWorld(Model model) {

        String prodCenter = "";
        try {

            // the HTTPClient Interface represents the contract for the HTTP Request execution
            HttpClient http = HttpClientBuilder.create().build();

            //HTTPHost holds the variables needed for the connection
            // default port for http is 80
            // default port for https is 443
            HttpHost host = new HttpHost("forecast.weather.gov", 80, "http");

            // HttpGet retrieves the info identified by the request URI (in the form of an entity)
            HttpGet getPage = new HttpGet("/MapClick.php?lat=42.331427&lon=-83.045754&FcstType=json");

            // execute the http request and pull the response
            HttpResponse resp = http.execute(host, getPage);

            String jsonString = EntityUtils.toString(resp.getEntity());

            // assign the returned result to a json object
            JSONObject json = new JSONObject(jsonString);

            prodCenter = json.get("productionCenter").toString();

            // this is for me as a developer to identify that my API is working
            System.out.println("Response code: " + resp.getStatusLine().getStatusCode());

            //String to hold data for our loop once we return the json array

            String text = "";

            //create a json array to hold the data in the "text" array node
            //also think of this as the json array has an array of text nested inside of the data object

            JSONArray ar = json.getJSONObject("data").getJSONArray("text");

            //loop through json array
            for (int i = 0; i < ar.length(); i++) {
                text+=("<h6>" + ar.getString(i) + "</h6>");
            }

          model.addAttribute("jsonArray", text);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new
                ModelAndView("welcome", "message", prodCenter);

    }


}