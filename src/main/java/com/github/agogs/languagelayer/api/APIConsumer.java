package com.github.agogs.languagelayer.api;

import com.github.agogs.languagelayer.model.APIResult;
import com.github.agogs.languagelayer.model.Batch;
import com.github.agogs.languagelayer.model.QueryParams;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * This interface defines methods to interact with the languagelayer API
 */
public interface APIConsumer {

    /**
     * Call the detect API which detects the language of the supplied string
     * @param params - query string whose language has to be detected
     * @return - result of the API call
     */
    APIResult detect(QueryParams params) throws IOException;

    /**
     * The languagelayer API also offers the capability to process multiple query texts in a single API request
     * This API is available only for paid accounts.
     * @param batch
     * @return
     * @throws IOException
     */
    APIResult batch(Batch batch) throws IOException;

    enum ApiEndPoint{

        DETECT("detect"),
        BATCH("batch");

        private String apiName;

        ApiEndPoint(String apiName) {
            this.apiName = apiName;
        }

        public String apiName(){
            return this.apiName;
        }
    }

}
