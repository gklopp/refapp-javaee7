package net.gklopp.refapp.web.controller;


import net.gklopp.refapp.domain.Actor;
import net.gklopp.refapp.domain.Movie;
import net.gklopp.refapp.service.ActorService;
import net.gklopp.refapp.service.MovieService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Controller that creates a new Movie.
 */
@Named
@ViewScoped
public class CreateMovieController implements Serializable {

    private Movie newMovie;

    private List<Actor> actorList;

    private Actor newActor;

    @Inject
    private MovieService movieService;

    @Inject
    private ActorService actorService;

    @Inject
    private transient Instance<FacesContext> facesContext;

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

    public void setNewActor(Actor newActor) {
        this.newActor = newActor;
    }

    // ***************************** INITIALIZATION ****************************

    @PostConstruct
    public void init() {

        newMovie = new Movie();
        actorList = actorService.getActors();
    }

    public void addActor() {

        if (newActor != null) {
            newMovie.addActor(newActor);
            actorList.remove(newActor);
        }
    }

    public void createMovie() {

        movieService.createMovie(newMovie);
        facesContext.get().addMessage(null, new FacesMessage("Movie '" + newMovie.getTitle() + "' has been created"));
        init();
    }
}
