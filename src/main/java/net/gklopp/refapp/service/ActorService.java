package net.gklopp.refapp.service;

import net.gklopp.refapp.domain.Actor;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ActorService {

    public static final List<Actor> actors = new ArrayList<>();
    static {
        actors.add(new Actor("Actor1 Id", "Actor1 First Name", "Actor1 Last Name"));
        actors.add(new Actor("Actor2 Id", "Actor2 First Name", "Actor2 Last Name"));
        actors.add(new Actor("Actor3 Id", "Actor3 First Name", "Actor3 Last Name"));
    }

    public List<Actor> getActors() {

        return actors;
    }

    public void createActor(Actor actor) {

        if (actor != null) {
            actors.add(actor);
        }
    }
}
