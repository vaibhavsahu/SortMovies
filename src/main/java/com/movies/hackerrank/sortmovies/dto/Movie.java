package com.movies.hackerrank.sortmovies.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.Year;

@Builder
@Getter
@ToString
public class Movie {
    private String title;
    private Year year;
    private String imdbID;

    @JsonCreator
    private Movie(
            @JsonProperty("Title") String title,
            @JsonProperty("Year") Year year,
            @JsonProperty("imdbID") String imdbID
    ){
        this.title = title;
        this.year = year;
        this.imdbID = imdbID;
    }
}
