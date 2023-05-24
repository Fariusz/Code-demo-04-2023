package org.rloth.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class Element {
    @JsonProperty("id")
    Integer id;
    @JsonProperty("nodes")
    List<Integer> nodeIDs = new ArrayList<>(3);

    public String toString() {
        return "\nElement(id=" + this.getId() + ", nodes=" + this.getNodeIDs() + ")";
    }
}
