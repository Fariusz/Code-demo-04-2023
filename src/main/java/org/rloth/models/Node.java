package org.rloth.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Node {

    public Node(Integer id) {
        this.id = id;
    }
    @JsonProperty("id")
    Integer id;
    @JsonProperty("x")
    Double x;
    @JsonProperty("y")
    Double y;

    public String toString() {
        return "\nNode(id=" + this.getId() + ", x=" + this.getX() + ", y=" + this.getY() + ")";
    }
}
