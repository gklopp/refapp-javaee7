package net.gklopp.refapp.rest;


import net.gklopp.refapp.domain.Actor;
import net.gklopp.refapp.service.ActorService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/actors")
public class ActorResource {

    @Inject
    private ActorService actorService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Actor> getActors() {
        return actorService.getActors();
    }
}
