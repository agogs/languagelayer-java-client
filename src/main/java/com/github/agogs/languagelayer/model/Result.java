package com.github.agogs.languagelayer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "language_code",
        "language_name",
        "probability",
        "percentage",
        "reliable_result"
})
@Getter
@Setter
public class Result {

    @JsonProperty("language_code")
    public String languageCode;
    @JsonProperty("language_name")
    public String languageName;
    @JsonProperty("probability")
    public Double probability;
    @JsonProperty("percentage")
    public Integer percentage;
    @JsonProperty("reliable_result")
    public Boolean reliableResult;

}
