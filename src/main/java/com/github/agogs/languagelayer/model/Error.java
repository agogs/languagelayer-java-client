package com.github.agogs.languagelayer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "code",
        "type",
        "info"
})
public class Error {

    @JsonProperty("code")
    public Integer code;
    @JsonProperty("type")
    public String type;
    @JsonProperty("info")
    public String info;

}