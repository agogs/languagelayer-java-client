package com.github.agogs.languagelayer.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.Setter;

import static com.github.agogs.languagelayer.util.Util.MAPPER;

/**
 * This encapsulates the complete JSON API response
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "success",
        "results",
        "error"
})
@Getter
@Setter
public class APIResult{
    @JsonProperty("success")
    public Boolean success;
    @JsonProperty("results")
    public List<Result> results;
    @JsonProperty("error")
    public Error error;

    /**
     * Convert the object to json string representation
     * @return
     * @throws JsonProcessingException
     */
    public String toJsonString() throws JsonProcessingException {
        return MAPPER.writeValueAsString(this);
    }

    @Override
    public String toString() {
        return "APIResult{" +
                "success=" + success +
                ", results=" + results +
                ", error=" + error +
                '}';
    }
}
