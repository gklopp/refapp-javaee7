package net.gklopp.refapp.web.controller;

import net.gklopp.refapp.domain.Movie;
import net.gklopp.refapp.service.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * {@@link ViewMovieListController} unit test.
 */
@RunWith(MockitoJUnitRunner.class)
public class ViewMovieListControllerTest {

    @InjectMocks
    private ViewMovieListController viewMovieListController;

    @Mock
    private MovieService movieService;

    // ***************************** INITIALIZATION ****************************

    @Before
    public void init() {

        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie("movie1", LocalDate.of(2015, 1, 1));
        Movie movie2 = new Movie("movie2", LocalDate.of(2015, 2, 1));
        Movie movie3 = new Movie("movie3", LocalDate.of(2015, 3, 1));
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        when(movieService.getMovies()).thenReturn(movies);

        viewMovieListController.init();
    }

    // *************************************************************************

    @Test
    public void shouldGetMovieList() throws Exception {

        // Given
        List<Movie> movieList = viewMovieListController.getMovieList();

        // When
        List<Movie> movieListRetrieved = viewMovieListController.getMovieList();

        // Then
        verify(movieService).getMovies();
        assertThat(movieListRetrieved).isEqualTo(movieList);
    }
}