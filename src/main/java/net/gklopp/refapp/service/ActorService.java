package net.gklopp.refapp.service;

import net.gklopp.refapp.domain.Actor;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ActorService {

    @PersistenceContext(unitName = "refappJavaee7PersistenceUnit")
    private EntityManager entityManager;

    // *************************************************************************

    public List<Actor> getActors() {

        List<Actor> actors = entityManager.createNamedQuery(Actor.GET_ACTORS_QUERY, Actor.class).getResultList();
        return actors;
    }

    public void createActor(Actor actor) {

        if (actor != null) {
            entityManager.persist(actor);
        }
    }
}
