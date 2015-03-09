package net.gklopp.refapp.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private LocalDate releaseDate;

    private Set<Actor> actors = new HashSet<>();

    // ***************************** CONSTRUCTORS ******************************

    public Movie() {
    }

    public Movie(String title, LocalDate releaseDate) {

        this.title = title;
        this.releaseDate = releaseDate;
    }

    // *************************** GETTERS / SETTERS ***************************

    public long getId() {
        return id;
    }

    void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<Actor> getActors() {
        return Collections.unmodifiableSet(actors);
    }

    public void addActor(Actor actor) {

        if (actor != null) {
            actors.add(actor);
        }
    }

    // *************************************************************************
    
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (releaseDate != null ? !releaseDate.equals(movie.releaseDate) : movie.releaseDate != null) return false;
        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", actors=" + actors +
                '}';
    }
}