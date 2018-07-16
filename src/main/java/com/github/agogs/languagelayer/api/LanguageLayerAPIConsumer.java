package com.github.agogs.languagelayer.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.agogs.languagelayer.model.APIResult;
import com.github.agogs.languagelayer.model.Batch;
import com.github.agogs.languagelayer.model.QueryParams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This is an implementation of the methods defined in {@link APIConsumer}
 */
public class LanguageLayerAPIConsumer implements APIConsumer {

    private static final Logger log = LogManager.getLogger(LanguageLayerAPIConsumer.class);
    private String baseUrl;
    private String accessKey;
    private static ObjectMapper mapper;

    public LanguageLayerAPIConsumer(String baseUrl, String accessKey) {
        log.debug("instantiating with api endpoint : {}", baseUrl);
        this.baseUrl = baseUrl;
        this.accessKey = QueryParams.Parameters.ACCESS_KEY.getParamName()+ '=' + accessKey;
        this.mapper = new ObjectMapper();
    }

    @Override
    public APIResult detect(QueryParams params) throws IOException {
        log.info("calling detect API with params : {}", params);

        String queryString = new StringBuilder()
                .append(baseUrl).append('/')
                .append(ApiEndPoint.DETECT.apiName())
                .append('?').append(accessKey)
                .append('&')
                .append(params.queryString()).toString();

        log.info("connecting to url : {}", queryString);
        URL url = new URL(queryString);

        //attempt the https connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        log.info("connected to url : {}", queryString);

        int responseCode = connection.getResponseCode();
        log.info("response code for url {} is {}", queryString, responseCode);


        //return the response
        return mapper.readValue(readResponse(connection), APIResult.class);

    }

    @Override
    public APIResult batch(Batch batch) throws IOException {
        log.info("calling batch API with batch params : {}", batch);

        String queryString = new StringBuilder()
                .append(baseUrl)
                .append('/')
                .append(ApiEndPoint.BATCH.apiName())
                .append('?').append(accessKey).append('&')
                .append(batch.getBatchQuery()).toString();

        log.info("connecting to url : {}", queryString);
        URL url = new URL(queryString);

        //attempt the https connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.connect();
        log.info("connected to url : {}", queryString);

        int responseCode = connection.getResponseCode();
        log.info("response code for url {} is {}", queryString, responseCode);


        //return the response
        return mapper.readValue(readResponse(connection), APIResult.class);
    }

    private String readResponse(HttpURLConnection connection) throws IOException {
        //uninitialized objects for later use
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = null;
        String line = null;

        int responseCode = connection.getResponseCode();

        //check for the response code
        if (responseCode == 200) {
            log.info("preparing to read content from inputstream");
            //read from inputstream if response code is 200
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            log.info("preparing to read content from errorstream");
            //read from errorstream when the response code is not 200
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        log.info("reading response content");
        //read the contents of the response
        while ((line = bufferedReader.readLine()) != null) {
            builder.append(line);
        }
        bufferedReader.close();
        String response = builder.toString();

        log.info("returning response string {}", response);
        return response;
    }
}