package com.nandha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Post_JSON {

    public static void main(String[] args) throws IOException {
        URL url=new URL("https://gurujsonrpc.appspot.com/guru");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("content-type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
        String jsonIP ="{\"method\" : \"guru.test\", \"params\" : [ \"Guru\" ], \"id\" : 123}";
        try(OutputStream outputStream=connection.getOutputStream()){
            byte[] input = jsonIP.getBytes("utf-8");
            outputStream.write(input, 0, input.length);
        }
        try (BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"))){
            StringBuilder response = new StringBuilder();
            String responseLine=null;
            while ((responseLine=bufferedReader.readLine())!=null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());

        }


    }
}
