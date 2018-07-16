package com.github.agogs.languagelayer.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Encapsulate multiple query values in a ArrayList before calling the batch API
 */
public class Batch {

    List<String> queries = new ArrayList<>();

    /**
     * Add a query to the batch, the query should be url encoded otherwise it can lead to undesired results.
     *
     * @param query - the query to be added to the batch
     * @return - true (as specified by {@link java.util.Collection#add(Object)}
     */
    public boolean addQuery(String query) {
        return queries.add(query);
    }

    /**
     * Add a list of queries to the param list.
     *
     * @param queries
     * @return
     */
    public boolean addQueries(List<String> queries) {
        return this.queries.addAll(queries);
    }

    /**
     * Build the string representation of the batch query
     *
     * @return - string representation of the batch query
     */
    public String getBatchQuery() throws UnsupportedEncodingException {
        Iterator it = queries.iterator();
        StringBuilder builder = new StringBuilder();
        while (it.hasNext()) {
            builder.append(QueryParams.Parameters.BATCH_QUERY.getParamName())
                    .append(it.next());
            if (it.hasNext()) {
                builder.append("&");
            }
        }

        return URLEncoder.encode(builder.toString(), StandardCharsets.UTF_8.name());
    }
}
