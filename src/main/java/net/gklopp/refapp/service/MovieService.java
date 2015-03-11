package net.gklopp.refapp.service;

import net.gklopp.refapp.domain.Movie;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class MovieService {

    @PersistenceContext(unitName = "refappJavaee7PersistenceUnit")
    private EntityManager entityManager;

    // *************************************************************************

    public List<Movie> getMovies() {

        List<Movie> movies = entityManager.createNamedQuery(Movie.GET_MOVIES_QUERY, Movie.class).getResultList();
        return movies;
    }

    public void createMovie(Movie movie) {

        if (movie != null) {
            entityManager.persist(movie);
        }
    }
}
