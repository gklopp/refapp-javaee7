package net.gklopp.refapp.web.controller;

import net.gklopp.refapp.domain.Movie;
import net.gklopp.refapp.service.MovieService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Controller that displays the list of Movies.
 */
@Named
@RequestScoped
public class ViewMovieListController {

    private List<Movie> movieList;

    @Inject
    private MovieService movieService;

    // *************************** GETTERS / SETTERS ***************************

    public List<Movie> getMovieList() {
        return movieList;
    }

    // ***************************** INITIALIZATION ****************************

    @PostConstruct
    public void init() {

        movieList = movieService.getMovies();
    }
}
