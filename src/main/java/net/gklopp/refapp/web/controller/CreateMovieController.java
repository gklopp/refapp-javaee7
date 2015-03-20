package net.gklopp.refapp.web.controller;


import net.gklopp.refapp.domain.Actor;
import net.gklopp.refapp.domain.Movie;
import net.gklopp.refapp.service.ActorService;
import net.gklopp.refapp.service.MovieService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Controller that creates a new Movie.
 */
@Named
@RequestScoped
public class CreateMovieController {

    private Movie newMovie;

    private List<Actor> actorList;

    private Actor newActor;

    @Inject
    private MovieService movieService;

    @Inject
    private ActorService actorService;

    // *************************** GETTERS / SETTERS ***************************

    public Movie getNewMovie() {
        return newMovie;
    }

    public List<Actor> getActorList() {
        return actorList;
    }

    public Actor getNewActor() {
        return newActor;
    }

    // ***************************** INITIALIZATION ****************************

    @PostConstruct
    public void init() {

        newMovie = new Movie();
        actorList = actorService.getActors();
    }

    public void createMovie() {

        movieService.createMovie(newMovie);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Movie '" + newMovie.getTitle() + "' has been created"));
        init();
    }
}
