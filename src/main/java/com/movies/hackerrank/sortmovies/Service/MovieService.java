package com.movies.hackerrank.sortmovies.Service;

import com.movies.hackerrank.sortmovies.dto.Movie;
import com.movies.hackerrank.sortmovies.dto.MovieResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.page}")
    private int page;

    @Value("${api.title}")
    private String title;

    @Autowired
    RestTemplate restTemplate;

    public List<Movie> getMovies(){

        List<List<Movie>> movieList = new ArrayList<>();
        int totalPages = getTotalPages();
        int page = 1;

        while(totalPages > 0){
            List<Movie> list = getMovieListPerPage(title, page);
             movieList.add(list);
             page++;
             totalPages--;
        }


        List<Movie> list = movieList.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return list;
    }

    public int getTotalPages(){
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("Title", title)
                .queryParam("page", page)
                .build().toString();

        ResponseEntity<MovieResponse> responseEntity = restTemplate.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<MovieResponse>() { });

        int totalPages = responseEntity.getBody().getTotal_pages();
        return totalPages;
    }

    public List<Movie> getMovieListPerPage(String title, int page){
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("Title", title)
                .queryParam("page", page)
                .build().toString();
        ResponseEntity<MovieResponse> responseEntity = restTemplate.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<MovieResponse>() { });
        return responseEntity.getBody().getData();
    }

    public List<String> sortMoviesByTitle(){
        List<Movie> movieList = getMovies();
        Predicate<Movie> predicate = movie -> movie.getTitle().toLowerCase().contains(title.toLowerCase());
        List<String> filteredMoviesList = movieList.stream().filter(predicate).map(Movie::getTitle).collect(Collectors.toList());
        Collections.sort(filteredMoviesList);
        return filteredMoviesList;
    }
}
