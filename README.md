# languagelayer-java-client
This is a java client library to consume the [languagelayer API](https://languagelayer.com/).

Some of the features of the API are:

* Powerful language detection JSON API for Developers
* Seamless Language Detection powered by Artificial Intelligence
* Simple REST API supporting 173 languages

## Sample code

        
        //initialization
        APIConsumer con = new LanguageLayerAPIConsumer("http://apilayer.net/api", "808738a62d914a403ee177bb9bd17d36");
        
        //build the query
        QueryParams params = new QueryParams().query("La\u2029 section\u2029 est\u2029 dirigée\u2029 par\u2029 son\u2029 directeur");
        //execute the "detect" API
        APIResult result = con.detect(params);
        System.out.println(new ObjectMapper().writeValueAsString(result.getResults()));

        //build the batch API - this is available only for paid accounts
        Batch batch = new Batch();
        batch.addQuery("Hello world");
        batch.addQuery("La\u2029 section\u2029 est\u2029 dirigée\u2029 par\u2029 son\u2029 directeur");
        batch.addQuery("網站有中、英文版本，也有繁、簡體版，可通過每頁左上角的連結隨時調整");

        //execute the "batch" API
        APIResult batchResult = con.batch(batch);
        System.out.println(new ObjectMapper().writeValueAsString(batchResult));
