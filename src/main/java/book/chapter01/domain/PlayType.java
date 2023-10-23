package book.chapter01.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PlayType {
    @JsonProperty("tragedy")
    TRAGEDY,

    @JsonProperty("comedy")
    COMEDY
}