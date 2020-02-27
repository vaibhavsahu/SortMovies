package com.movies.hackerrank.sortmovies.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieResponse {
    private final int page;
    private final int per_page;
    private final int total;
    private final int total_pages;
    private final List<Movie> data;

    @JsonCreator
    private MovieResponse(
            @JsonProperty("page") int page,
            @JsonProperty("per_page") int per_page,
            @JsonProperty("total") int total,
            @JsonProperty("toal_pages") int total_pages,
            @JsonProperty("data") List<Movie> data
    ){
        this.page = page;
        this.per_page = per_page;
        this.total = total;
        this.total_pages = total_pages;
        this.data = data;
    }
}
