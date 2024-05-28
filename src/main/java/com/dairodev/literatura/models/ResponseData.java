package com.dairodev.literatura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponseData(
        @JsonAlias("count") Integer count,
        @JsonAlias("results") List<BookData> results
) {
}
