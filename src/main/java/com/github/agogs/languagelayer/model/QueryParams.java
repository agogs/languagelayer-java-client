package com.github.agogs.languagelayer.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This class is used to encapsulate the query parameters for the API call.
 * The methods can be chained together for easier and readable coding.
 *
 * The enum {@link Parameters} provides enumeration for the allowed query parameters
 * <ul>
 *     <li>ACCESS_KEY - you access_key for consuming the API</li>
 *     <li>QUERY - your query</li>
 *     <li>FORMAT - to prettify the JSON response</li>
 *     <li>SHOW_QUERY - to show the query in the JSON response</li>
 * </ul>
 */
public class QueryParams {

    private Map<String, String> params;

    public QueryParams() {
        params = new HashMap<>();
    }

    public QueryParams query(String query) throws UnsupportedEncodingException {
        params.put(Parameters.QUERY.getParamName(), URLEncoder.encode(query, StandardCharsets.UTF_8.name()));
        return this;
    }

    public QueryParams showQuery(boolean showQuery) {
        params.put(Parameters.SHOW_QUERY.getParamName(), showQuery ? "1" : "0");
        return this;
    }

    public QueryParams format(boolean format) {
        params.put(Parameters.FORMAT.getParamName(), format ? "1" : "0");
        return this;
    }

    public String queryString(){
        StringBuilder builder = new StringBuilder();
        Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, String> entry = it.next();
            builder.append(entry.getKey()).append('=').append(entry.getValue());
            if(it.hasNext()){
                builder.append('&');
            }
        }

        return builder.toString();
    }

    @Override
    public String toString() {
        return "QueryParams{" +
                "params=" + params +
                '}';
    }

    public enum Parameters {
        ACCESS_KEY("access_key"),
        QUERY("query"),
        SHOW_QUERY("show_query"),
        FORMAT("format"),
        BATCH_QUERY("query[]");

        private String paramName;

        Parameters(String paramName) {
            this.paramName = paramName;
        }

        public String getParamName() {
            return this.paramName;
        }
    }
}
