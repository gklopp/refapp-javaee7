package net.gklopp.refapp.web.controller;


import net.gklopp.refapp.domain.Movie;
import net.gklopp.refapp.service.MovieService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Controller that creates a new Movie.
 */
@Named
@RequestScoped
public class CreateMovieController {

    private Movie newMovie;

    @Inject
    private MovieService movieService;

    // *************************** GETTERS / SETTERS ***************************

    public Movie getNewMovie() {
        return newMovie;
    }

    // ***************************** INITIALIZATION ****************************

    @PostConstruct
    public void init() {

        newMovie = new Movie();
    }

    public void createMovie() {

        movieService.createMovie(newMovie);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Movie '" + newMovie.getTitle() + "' has been created"));
        init();
    }
}
