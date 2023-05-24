package org.rloth.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Value {
    @JsonProperty("element_id")
    Integer elementId;
    @JsonProperty("value")
    Double value;

    public String toString() {
        return "\nValue(id=" + this.getElementId() + ", value=" + this.getValue() + ")";
    }
}
