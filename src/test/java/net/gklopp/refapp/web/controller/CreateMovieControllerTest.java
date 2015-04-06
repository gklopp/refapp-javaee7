package net.gklopp.refapp.web.controller;

import net.gklopp.refapp.domain.Actor;
import net.gklopp.refapp.domain.Movie;
import net.gklopp.refapp.service.ActorService;
import net.gklopp.refapp.service.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.enterprise.inject.Instance;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * {@link CreateMovieController} unit test.
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateMovieControllerTest {

    @InjectMocks
    private CreateMovieController createMovieController;

    @Mock
    private ActorService actorService;

    @Mock
    private MovieService movieService;

    @Mock
    private Instance<FacesContext> facesContextProducer;

    @Mock
    private FacesContext facesContext;

    @Before
    public void init() {

        List<Actor> actorList = new ArrayList<>();
        Actor actor1 = new Actor("actor1", "actor1FirstName", "actor1LastName");
        Actor actor2 = new Actor("actor2", "actor2FirstName", "actor2LastName");
        Actor actor3 = new Actor("actor3", "actor3FirstName", "actor3LastName");
        actorList.add(actor1);
        actorList.add(actor2);
        actorList.add(actor3);
        when(actorService.getActors()).thenReturn(actorList);

        when(facesContextProducer.get()).thenReturn(facesContext);

        createMovieController.init();
    }

    @Test
    public void shouldAddActor() throws Exception {

        // Given
        Actor newActor = new Actor("newActor", "newActorFirstName", "newActorLastName");
        createMovieController.setNewActor(newActor);

        // When
        createMovieController.addActor();

        // Then
        Movie movie = createMovieController.getNewMovie();
        assertThat(movie.getActors()).contains(newActor);

        List<Actor> actorList = createMovieController.getActorList();
        assertThat(actorList).excludes(newActor);
    }

    @Test
    public void addActor_should_do_nothing_if_actor_is_null() {


        // Given
        createMovieController.setNewActor(null);
        Movie newMovie = createMovieController.getNewMovie();
        int nrOfActorsBeforeAdd = newMovie.getActors().size();
        int nrOfActorsInListBeforeAdd = createMovieController.getActorList().size();

        // When
        createMovieController.addActor();

        // Then
        Set<Actor> actors = createMovieController.getNewMovie().getActors();
        assertThat(actors).hasSize(nrOfActorsBeforeAdd);

        List<Actor> actorList = createMovieController.getActorList();
        assertThat(actorList).hasSize(nrOfActorsInListBeforeAdd);
    }

    @Test
    public void shouldCreateMovie() throws Exception {

        // Given
        Movie newMovie = createMovieController.getNewMovie();

        // When
        createMovieController.createMovie();

        // Then
        verify(movieService).createMovie(newMovie);
        verify(facesContext).addMessage(anyString(), anyObject());
    }
}