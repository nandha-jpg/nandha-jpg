package com.nandha;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.BufferedInputStream;
//import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
//import java.nio.charset.StandardCharsets;

public class Json_POST2 {
    public static void main(String[] args) {
        Json_POST2.Json_POST2();

    }
    public static void Json_POST2(){
        String queryurl = "https://gurujsonrpc.appspot.com/guru";
        String json = "{ \"method\" : \"guru.test\", \"params\" : [ \"Nandha\" ], \"id\" : 123 }";
        try{
            URL url = new URL(queryurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(json.getBytes("UTF-8"));
            outputStream.close();

            //read the response
            InputStream inputStream=new BufferedInputStream(connection.getInputStream());
            String result = IOUtils.toString(inputStream, "UTF-8");
            System.out.println(result);
            System.out.println("result after Reading JSON Response");
            JSONObject myResponse = new JSONObject(result);
            System.out.println("jsonrpc- "+myResponse.getString("jsonrpc"));
            System.out.println("id- "+myResponse.getInt("id"));
            System.out.println("result- "+myResponse.getString("result"));
            inputStream.close();
            connection.disconnect();



        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
