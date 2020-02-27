package com.movies.hackerrank.sortmovies.Controller;

import com.movies.hackerrank.sortmovies.Service.MovieService;
import com.movies.hackerrank.sortmovies.dto.Movie;
import com.movies.hackerrank.sortmovies.dto.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

import static javax.servlet.http.HttpServletResponse.*;

@RestController
public class MovieController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MovieService movieService;


    @Value("${api.page}")
    private int page;

    @Value("${api.title}")
    private String title;



    @RequestMapping("/api/movies")
    public ResponseEntity<List<Movie>> getMoviesData(){

            List<Movie> movieResponse = movieService.getMovies();
            if(Objects.nonNull(movieResponse)){
                return ResponseEntity.status(SC_OK).body(movieResponse);
            }
            return ResponseEntity.status(SC_NOT_FOUND).body(null);
    }

    @RequestMapping("/api/sortedtitles")
    public ResponseEntity<List<String>> getSortedMoviesByTitle(){
        List<String> list = movieService.sortMoviesByTitle();
        if(Objects.nonNull(list)) {
            return ResponseEntity.status(SC_OK).body(list);
        }
        return ResponseEntity.status(SC_NOT_FOUND).body(null);
    }
}
