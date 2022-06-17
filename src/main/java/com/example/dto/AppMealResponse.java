
package com.example.dto;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "date",
    "slot",
    "position",
    "type",
    "value",
    "id"
})
@Generated("jsonschema2pojo")
public class AppMealResponse {

    @JsonProperty("date")
    private Integer date;
    @JsonProperty("slot")
    private Integer slot;
    @JsonProperty("position")
    private Integer position;
    @JsonProperty("type")
    private String type;
    @JsonProperty("value")
    private Value value;
    @JsonProperty("id")
    private Value id;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("date")
    public Integer getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(Integer date) {
        this.date = date;
    }

    @JsonProperty("slot")
    public Integer getSlot() {
        return slot;
    }

    @JsonProperty("slot")
    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    @JsonProperty("position")
    public Integer getPosition() {
        return position;
    }

    @JsonProperty("position")
    public void setPosition(Integer position) {
        this.position = position;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("value")
    public Value getValue() {
        return value;
    }

    @JsonProperty("id")
    public String getId() {
        return type;
    }

    @JsonProperty("id")
    public void setValue(Value value) {
        this.value = value;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
