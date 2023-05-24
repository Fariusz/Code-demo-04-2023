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
public class Mesh {
    @JsonProperty("elements")
    List<Element> elements = new ArrayList<>();
    @JsonProperty("nodes")
    List<Node> nodes = new ArrayList<>();
    @JsonProperty("values")
    List<Value> values = new ArrayList<>();

    public String toString() {
        return "Mesh(elements=" + this.getElements() + ", nodes=" + this.getNodes() + ", values=" + this.getValues() + ")";
    }
}
